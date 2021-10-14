package com.nucpoop.covserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtil {

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmail(String toAddress, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        sender.send(simpleMailMessage);
    }
}
