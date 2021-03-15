package com.nucpoop.covserver.util;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailScheduler {

    @Autowired
    private EmailUtil emailUtil;

    @Scheduled(cron = "0 1 0/1 * * *")
    public void checkTime() {
        // 회원조회(알림 받기로함)

        // 시간일치 조회

        // 일치한 회원들에게 메일발송
    }

    // @Scheduled(cron = "0/3 * * * * *")
    // public void testSendMail() {
        
    //     try {
    //         emailUtil.sendEmail("kjm5546@gmail.com", "title", "확진환자 : 1111");
    //         System.out.println("mail send complete");
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         System.out.println(e);
    //     }

    // }
}
