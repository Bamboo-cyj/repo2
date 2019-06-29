package com.fengk.quartz;

import com.fengk.dao.CleanHistoryOrderSettingDao;
import com.fengk.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 */
@Component
public class CleanHistoryOrderSetting {
    @Autowired
    CleanHistoryOrderSettingDao cleanHistoryOrderSettingDao;
    public void cleanHistoryOrderSetting() {
        Date today = DateUtils.getToday();
        String todayString = null;
        try {
            todayString = DateUtils.parseDate2String(today);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cleanHistoryOrderSettingDao.cleanHistoryOrderSetting(todayString);
    }
}
