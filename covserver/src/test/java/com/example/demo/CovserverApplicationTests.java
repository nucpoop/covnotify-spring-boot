package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;

import javax.sql.DataSource;

import com.nucpoop.covserver.CovserverApplication;
import com.nucpoop.covserver.util.Utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CovserverApplication.class)
@SpringBootTest(classes = CovserverApplication.class,args = "-Djasypt.encryptor.password=jamjam")
class CovserverApplicationTests {

	@Autowired
	private DataSource dataSource;

	//@Test
	// public void testConnection() {

	// 	try (Connection conn = dataSource.getConnection()) {
	// 		System.out.println("db connection success " + conn);

	// 	} catch (Exception e) {
	// 		System.out.println("db connection fail");
	// 		e.printStackTrace();
	// 	}

	// }

	@Test
	public void testSendMail(){
		Utils util = new Utils();
		assertNull(util);
		try {
			//Utils.sendEmail("kjm5546@gmail.com","test","안녕하세요");   
		} catch (Exception e) {
			
		}
	}

	// @Test
	// public void testGetCov(){
	// 	try {
	// 		//System.out.println(Utils.getCovData().toString());	
	// 	} catch (Exception e) {
	// 		//TODO: handle exception
	// 	}
		
	// }

	// @Test
	// public void encryptionTest(){
	// 	StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
    //     jasypt.setPassword("test");
    //     jasypt.setAlgorithm("PBEWithMD5AndDES");
 
 
    //     String encryptedText = jasypt.encrypt("test");
    //     String plainText = jasypt.decrypt(encryptedText);
 
    //     System.out.println("encryptedText:  " + encryptedText);
    //     System.out.println("plainText:  " + plainText);
	// }
}
