package com.nucpoop.covserver.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailScheduler {

    int temp = 0;
    @Scheduled(cron = "0 0 0/1 * * *")
    public void CheckTime(){
        //회원조회(알림 받기로함)

        //시간일치 조회

        //일치한 회원들에게 메일발송
    }
}
