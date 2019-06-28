package com.fengk.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    Map getMemberReport();

    List<Map> getSetmealReport();

    Map getBusinessReportData();

    List<Map> getMemberSexReport();

    List<Map> getMemberAgeReport();
}
