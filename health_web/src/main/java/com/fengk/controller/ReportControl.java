package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.Result;
import com.fengk.service.ReportService;
import com.fengk.utils.MessageConstant;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping(value = "/report")
public class ReportControl {
    @Reference
    ReportService reportService;


    @RequestMapping(value = "/getMemberAgeReport")
    public Result getMemberAgeReport() {
        try {
            Map map = new HashMap<>();
            List<String> memberAges = new ArrayList<>();
            List<Map> memberAgeCount = reportService.getMemberAgeReport();
            for (Map map1 : memberAgeCount) {
                String name = (String) map1.get("name");

                memberAges.add(name);
            }

            map.put("memberAges", memberAges);
            map.put("memberAgeCount", memberAgeCount);
            System.out.println("memberAge"+map);
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }


    @RequestMapping(value = "/getMemberSexReport")
    public Result getMemberSexReport() {
        try {
            Map map = new HashMap<>();
            List<String> memberSexNames = new ArrayList<>();
            List<Map> memberSexCount = reportService.getMemberSexReport();
            for (Map map1 : memberSexCount) {
                String name = (String) map1.get("name");
                memberSexNames.add(name);
            }

            map.put("memberSexNames", memberSexNames);
            map.put("memberSexCount", memberSexCount);
            System.out.println("memberSex"+map);
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }


    @RequestMapping(value = "/getMemberReport")
    public Result getMemberReport() {
        try {
            Map map = reportService.getMemberReport();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }
    @RequestMapping(value = "/getMemberReportByDate")
    public Result getMemberReportByDate(String dateBegin,String dateEnd) {
        try {
            Map map = reportService.getMemberReportByDate(dateBegin,dateEnd);
            System.out.println("会员统计"+map);
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }

    @RequestMapping(value = "/getSetmealReport")
    public Result getSetmealReport() {
        try {
            Map map = new HashMap<>();
            List<String> setmealNames = new ArrayList<>();
            List<Map> setmealCount = reportService.getSetmealReport();
            for (Map map1 : setmealCount) {
                String name = (String) map1.get("name");
                setmealNames.add(name);
            }
            map.put("setmealNames", setmealNames);
            map.put("setmealCount", setmealCount);
            System.out.println("setmealreport"+map);
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }

    @RequestMapping(value = "/getBusinessReportData")
    public Result getBusinessReportData() {
        try {
            Map map = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }

    @RequestMapping(value = "/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map result = reportService.getBusinessReportData();
            InputStream inputStream = request.getSession().getServletContext().getResourceAsStream("template/report_template.xlsx");
            String fileName = "report_template.xlsx";
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(2);
            XSSFCell sheetRowCell = row.getCell(5);
            sheetRowCell.setCellValue((String) result.get("reportDate"));
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");


            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);//新增会员数（本日）
            row.getCell(7).setCellValue(totalMember);//总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);//本月到诊数

            int mealLength = hotSetmeal.size();
            int rowMeal = 12;
            for (int i = 0; i < mealLength; i++) {
                row = sheet.getRow(rowMeal + i);
                row.getCell(4).setCellValue((String) hotSetmeal.get(i).get("name"));
                Long setmeal_count = (Long) hotSetmeal.get(i).get("setmeal_count");
                row.getCell(5).setCellValue(setmeal_count);
                BigDecimal share = (BigDecimal) hotSetmeal.get(i).get("proportion");
                row.getCell(6).setCellValue(share.doubleValue());

            }


            ServletOutputStream outputStream = response.getOutputStream();
            String fileType = request.getSession().getServletContext().getMimeType(fileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType(fileType);
            xssfWorkbook.write(outputStream);
            inputStream.close();
            outputStream.close();
            xssfWorkbook.close();

            return null;
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            return new Result(false, runtimeException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);

        }
    }
}
