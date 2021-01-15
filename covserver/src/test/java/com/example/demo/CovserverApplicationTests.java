package com.example.demo;

import java.sql.Connection;

import javax.sql.DataSource;

import com.nucpoop.covserver.CovserverApplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

	@Test public void testInsert(){
		
	}
}
