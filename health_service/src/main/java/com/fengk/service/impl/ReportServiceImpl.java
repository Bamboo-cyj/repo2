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
            months.add(new SimpleDateFormat("yyyy-HH").format(calendar.getTime()));
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

        List<Map> hotSetmeal=reportDao.getHotSetMeal();


        int todayNewMember = memberDao.findMemberCountByDate(todayString);
        int totalMember = memberDao.findMemberTotalCount();
        int thisWeekNewMember = memberDao.findMemberCountAfterDate(thisWeekMonday);
        int thisMonthNewMember=memberDao.findMemberCountAfterDate(firstDay4ThisMonth);
        int todayOrderNumber=orderDao.findOrderCountByDate(todayString);
        int todayVisitsNumber=orderDao.findVisitsCountByDate(todayString);
        int thisWeekOrderNumber=orderDao.findOrderCountAfterDate(thisWeekMonday);
        int thisWeekVisitsNumber=orderDao.findVisitsCountAfterDate(thisWeekMonday);
        int thisMonthOrderNumber=orderDao.findOrderCountAfterDate(firstDay4ThisMonth);
        int thisMonthVisitsNumber=orderDao.findVisitsCountAfterDate(firstDay4ThisMonth);

        map.put("thisMonthNewMember",thisMonthNewMember);
        map.put("thisWeekNewMember", thisWeekNewMember);
        map.put("reportDate", todayString);
        map.put("todayNewMember", todayNewMember);
        map.put("totalMember", totalMember);
        map.put("todayOrderNumber",todayOrderNumber);
        map.put("thisWeekOrderNumber", thisWeekOrderNumber);
        map.put("todayVisitsNumber",todayVisitsNumber);
        map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        map.put("thisMonthOrderNumber", thisMonthOrderNumber);
        map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        map.put("hotSetmeal",hotSetmeal);
        return map;
    }
}
