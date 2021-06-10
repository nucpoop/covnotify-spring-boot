package com.nucpoop.covserver.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.nucpoop.covserver.model.SearchCondition;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.covdata.CovData;
import com.nucpoop.covserver.model.covdata.CovDataLocal;
import com.nucpoop.covserver.model.covdata.Item;
import com.nucpoop.covserver.model.covdata.ItemLocal;
import com.nucpoop.covserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 1 0/1 * * *")
    public void checkTimeAndNotify() {
        // define date
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        SimpleDateFormat todayFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        // send email
        try {
            Item global = getCovDataGrobal(todayFormat.format(date));
            Item yesterday = getCovDataGrobal(todayFormat.format(getYesterday()));

            List<ItemLocal> items = getCovDataLocal(todayFormat.format(date));

            List<User> users = userService.selectUsersForNotify(timeFormat.format(date));
            //+ "(" + NumberFormat.getInstance().format(global.getDecideCnt() - yesterday.getDecideCnt()) + "명)" +
            for (User user : users) {
                String dataGlobal = "<전국 코로나 정보>\n확진자 : " +NumberFormat.getInstance().format(global.getDecideCnt())
                +"명(" + NumberFormat.getInstance().format(global.getDecideCnt() - yesterday.getDecideCnt()) + "명)\n격리해제 : " + NumberFormat.getInstance().format(global.getClearCnt())
                +"명(" + NumberFormat.getInstance().format(global.getClearCnt() - yesterday.getClearCnt()) + "명)\n치료중 : " + NumberFormat.getInstance().format(global.getCareCnt())
                +"명(" + NumberFormat.getInstance().format(global.getCareCnt() - yesterday.getCareCnt()) + "명)\n사망자 : " + NumberFormat.getInstance().format(global.getDeathCnt())
				+"명(" + NumberFormat.getInstance().format(global.getDeathCnt() - yesterday.getDeathCnt()) + "명)\n\n";


                String dataLocal = "";
                for (ItemLocal item : items) {

                    if (user.getLocation().equals(item.getGubun())) {
                        dataLocal = "<" + user.getLocation() + " 지역 코로나 정보>\n확진자 : " +NumberFormat.getInstance().format(item.getDefCnt())
						+"명\n격리해제 : " + NumberFormat.getInstance().format(item.getIsolClearCnt())
						+"명\n치료중 : " + NumberFormat.getInstance().format(item.getIsolIngCnt())
						+"명\n사망자 : " + NumberFormat.getInstance().format(item.getDeathCnt())
						+"명\n전일대비 증감 수 : " + NumberFormat.getInstance().format(item.getIncDec())
						+"명\n\n";
                        break;
                    }
                }
                emailUtil.sendEmail(user.getEmail(), todayFormat.format(date) + " 코로나 알림", dataGlobal + dataLocal);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            //checkTimeAndNotify();
        }
    }

    private Item getCovDataGrobal(String condition) {
        SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt(condition)
                .endCreateDt(condition).build();
        try {
            Utils.getCovData(searchCondition);
            CovData covData = Utils.getCovData();
            return covData.getBody().getItems().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    private List<ItemLocal> getCovDataLocal(String condition) {
        SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt(condition)
                .endCreateDt(condition).build();
        try {
            Utils.getCovDataLocal(searchCondition);
            CovDataLocal covDataLocal = Utils.getCovDataLocal();
            return covDataLocal.getBody().getItems();
        } catch (Exception e) {
            return null;
        }
    }

    private Date getYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
