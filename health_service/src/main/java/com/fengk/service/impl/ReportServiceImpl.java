package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.MemberDao;
import com.fengk.dao.OrderDao;
import com.fengk.dao.ReportDao;
import com.fengk.service.ReportService;
import com.fengk.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDao reportDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    OrderDao orderDao;


    @Override
    public Map getMemberReport() {
        Map map = new HashMap();
        List<String> months = new ArrayList<>();
        List<Integer> memberCount = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -12);
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH, 1);
            Date calendarTime = calendar.getTime();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String format = simpleDateFormat.format(calendarTime);

            months.add(format);
        }
        for (String month : months) {
            month = month + "-31";

            memberCount.add(reportDao.getCount(month));
        }
        map.put("months", months);
        map.put("memberCount", memberCount);
        System.out.println(map);
        return map;
    }

    @Override
    public List<Map> getSetmealReport() {


        return reportDao.getSetmealReport();
    }

    @Override
    public Map getBusinessReportData() {
        Map map = new HashMap();
        Date today = DateUtils.getToday();
        String todayString = null;
        String thisWeekMonday = null;
        String firstDay4ThisMonth = null;
        try {
            todayString = DateUtils.parseDate2String(today);
            thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
            firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map> hotSetmeal = reportDao.getHotSetMeal();


        int todayNewMember = memberDao.findMemberCountByDate(todayString);
        int totalMember = memberDao.findMemberTotalCount();
        int thisWeekNewMember = memberDao.findMemberCountAfterDate(thisWeekMonday);
        int thisMonthNewMember = memberDao.findMemberCountAfterDate(firstDay4ThisMonth);
        int todayOrderNumber = orderDao.findOrderCountByDate(todayString);
        int todayVisitsNumber = orderDao.findVisitsCountByDate(todayString);
        int thisWeekOrderNumber = orderDao.findOrderCountAfterDate(thisWeekMonday);
        int thisWeekVisitsNumber = orderDao.findVisitsCountAfterDate(thisWeekMonday);
        int thisMonthOrderNumber = orderDao.findOrderCountAfterDate(firstDay4ThisMonth);
        int thisMonthVisitsNumber = orderDao.findVisitsCountAfterDate(firstDay4ThisMonth);

        map.put("thisMonthNewMember", thisMonthNewMember);
        map.put("thisWeekNewMember", thisWeekNewMember);
        map.put("reportDate", todayString);
        map.put("todayNewMember", todayNewMember);
        map.put("totalMember", totalMember);
        map.put("todayOrderNumber", todayOrderNumber);
        map.put("thisWeekOrderNumber", thisWeekOrderNumber);
        map.put("todayVisitsNumber", todayVisitsNumber);
        map.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        map.put("thisMonthOrderNumber", thisMonthOrderNumber);
        map.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        map.put("hotSetmeal", hotSetmeal);
        return map;
    }

    @Override
    public List<Map> getMemberSexReport() {

        List<Map> list=reportDao.getMemberSexReport();
        for (Map map : list) {
            String name = (String) map.get("name");
            if ("1".equals(name)) {
                name = "男";
            }
            if ("2".equals(name)) {
                name = "女";
            }
            if (name == null || "null".equals(name)) {
                name = "未填写性别";
            }
            map.put("name",name);
        }

        System.out.println("service sex"+list);
        return list;

    }

    @Override
    public List<Map> getMemberAgeReport() {
        List<Map> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String age0Format = (simpleDateFormat.format(calendar.getTime()));
        System.out.println("age0Format"+age0Format);
        calendar.add(Calendar.YEAR, -18);
        String age18Format = (simpleDateFormat.format(calendar.getTime()));
        System.out.println("age18Format"+age18Format);
        calendar.add(Calendar.YEAR, -12);
        String age30Format = (simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.YEAR, -15);
        String age45Format = (simpleDateFormat.format(calendar.getTime()));

        String age18 = "0-18";
        String age30 = "18-30";
        String age45 = "30-45";
        String age45Up = "45以上";


        Map map18 = new HashMap();
        map18.put("name", age18);
        map18.put("value", reportDao.getAgeBetween(age0Format, age18Format));
        list.add(map18);

        Map map30 = new HashMap();
        map30.put("name", age30);
        map30.put("value", reportDao.getAgeBetween(age18Format, age30Format));
        list.add(map30);

        Map map45 = new HashMap();
        map45.put("name", age45);
        map45.put("value", reportDao.getAgeBetween(age30Format, age45Format));
        list.add(map45);

        Map map45Up = new HashMap();
        map45Up.put("name", age45Up);
        map45Up.put("value", reportDao.getAgeBetween(age45Format, "0000"));
        list.add(map45Up);

        Map ageNull = new HashMap();
        ageNull.put("name", "未填写生日");
        ageNull.put("value", reportDao.getAgeNull());
        list.add(ageNull);
        System.out.println("service age"+list);
        return list;
    }
}
