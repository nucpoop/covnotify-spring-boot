package com.nucpoop.covserver.util;

public interface EmailUtil {
    void sendEmail(String toAddress, String subject, String body);
}
