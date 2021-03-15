package com.nucpoop.covserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.nucpoop.covserver.model.SearchCondition;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.covdata.CovData;
import com.nucpoop.covserver.model.covdata.Item;
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
    public void checkTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        SimpleDateFormat todayFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
    
        try {
            Item item =getCovDataGrobal(todayFormat.format(date));

            List<User> users = userService.selectUsersForNotify(timeFormat.format(date));
            for (User user : users) {
                String data = "코로나 정보\n 확진자 : " +item.getDecideCnt()
                +"명\n 격리해제 : " + item.getClearCnt()
                +"명\n 치료중 : " + item.getCareCnt()
                +"명\n 사망자 : " + item.getDeathCnt()+"명";
                emailUtil.sendEmail(user.getEmail(),todayFormat.format(date)+" 코로나 알림",data);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private Item getCovDataGrobal(String condition) {
        SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt("20210315")
                .endCreateDt("20210315").build();
        try {
            Utils.getCovData(searchCondition);
            CovData covData = Utils.getCovData();
            return covData.getBody().getItems().get(0);
        } catch (Exception e) {
            return null;
        }

    }

    // @Scheduled(cron = "0 58 9 * * *")
    // public void updateCovData(){
    // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    // Date date = new Date();
    // String dateComplete = simpleDateFormat.format(date);

    // }

    // @Scheduled(cron = "0/3 * * * * *")
    // public void testSendMail() {

    // try {
    // emailUtil.sendEmail("kjm5546@gmail.com", "title", "확진환자 : 1111");
    // System.out.println("mail send complete");
    // } catch (Exception e) {
    // // TODO: handle exception
    // System.out.println(e);
    // }

    // }
}
