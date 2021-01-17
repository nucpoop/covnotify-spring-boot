package com.example.demo;

import java.sql.Connection;

import javax.sql.DataSource;

import com.nucpoop.covserver.CovserverApplication;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovserverApplication.class)
class CovserverApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void testConnection() {

		try (Connection conn = dataSource.getConnection()) {
			System.out.println("db connection success " + conn);

		} catch (Exception e) {
			System.out.println("db connection fail");
			e.printStackTrace();
		}

	}

	@Test 
	public void testInsert(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void encryptionTest(){
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("test");
        jasypt.setAlgorithm("PBEWithMD5AndDES");
 
 
        String encryptedText = jasypt.encrypt("test");
        String plainText = jasypt.decrypt(encryptedText);
 
        System.out.println("encryptedText:  " + encryptedText);
        System.out.println("plainText:  " + plainText);
	}
}
