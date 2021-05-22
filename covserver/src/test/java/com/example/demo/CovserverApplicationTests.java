package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.nucpoop.covserver.CovserverApplication;
import com.nucpoop.covserver.model.SearchCondition;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.covdata.CovData;
import com.nucpoop.covserver.model.covdata.CovDataLocal;
import com.nucpoop.covserver.model.covdata.Item;
import com.nucpoop.covserver.model.covdata.ItemLocal;
import com.nucpoop.covserver.service.UserService;
import com.nucpoop.covserver.util.EmailUtil;
import com.nucpoop.covserver.util.Utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovserverApplication.class)
class CovserverApplicationTests {

	static {
		System.setProperty("jasypt.encryptor.password", "jamjam");
	}
	@Autowired
	private DataSource dataSource;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private UserService userService;

	@Test
	public void testSendMail() {
		Utils util = new Utils();
		assertNotNull(util);
		try {
			emailUtil.sendEmail("kjm5546@gmail.com", "코로나 알림", "전국\n확진자:1111\n완치:1111\n사망:1111");
			// Utils.sendEmail("kjm5546@gmail.com","test","안녕하세요");
		} catch (Exception e) {

		}
	}

	@Test
	public void testCalDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String dateComplete = simpleDateFormat.format(date);
		System.out.println(dateComplete);

		SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt("20210315")
				.endCreateDt("20210315").build();
		try {
			Utils.getCovData(searchCondition);
			CovData covData = Utils.getCovData();
			Item item = covData.getBody().getItems().get(0);
			List<User> users = userService.selectUsersForNotify("11");
			for (User user : users) {
				String data = "코로나 정보\n 확진자 : " + item.getDecideCnt() + "명\n 격리해제 : " + item.getClearCnt()
						+ "명\n 치료중 : " + item.getCareCnt() + "명\n 사망자 : " + item.getDeathCnt() + "명";
				emailUtil.sendEmail(user.getEmail(), "20210315 코로나 알림", data);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Test
	public void testCovLocalData() {
		SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt("20210421")
				.endCreateDt("20210421").build();
		try {
			Utils.getCovData(searchCondition);
			CovData covData = Utils.getCovData();
			Item global = covData.getBody().getItems().get(0);
			Utils.getCovDataLocal(searchCondition);
			CovDataLocal covDataLocal = Utils.getCovDataLocal();
			List<ItemLocal> items = covDataLocal.getBody().getItems();

			searchCondition.setStartCreateDt("20210420");
			searchCondition.setEndCreateDt("20210420");

			Utils.getCovData(searchCondition);
			CovData covDataYes = Utils.getCovData();
			Item yesterday = covDataYes.getBody().getItems().get(0);

			List<User> users = userService.selectUsersForNotify("10");

			for (User user : users) {
				String dataGlobal = "<전국 코로나 정보>\n확진자 : " +NumberFormat.getInstance().format(global.getDecideCnt())
                +"명(" + NumberFormat.getInstance().format(global.getDecideCnt() - yesterday.getDecideCnt()) + "명)\n격리해제 : " + NumberFormat.getInstance().format(global.getClearCnt())
                +"명(" + NumberFormat.getInstance().format(global.getClearCnt() - yesterday.getClearCnt()) + "명)\n치료중 : " + NumberFormat.getInstance().format(global.getCareCnt())
                +"명(" + NumberFormat.getInstance().format(global.getCareCnt() - yesterday.getCareCnt()) + "명)\n사망자 : " + NumberFormat.getInstance().format(global.getDeathCnt())
				+"명(" + NumberFormat.getInstance().format(global.getDeathCnt() - yesterday.getDeathCnt()) + "명)\n\n";

				String dataLocal = "";
				for (ItemLocal item : items) {
					
					if(user.getLocation().equals(item.getGubun())){
						dataLocal ="<" + user.getLocation() +"지역 코로나 정보>\n확진자 : " +NumberFormat.getInstance().format(item.getDefCnt())
						+"명\n격리해제 : " + NumberFormat.getInstance().format(item.getIsolClearCnt())
						+"명\n치료중 : " + NumberFormat.getInstance().format(item.getIsolIngCnt())
						+"명\n사망자 : " + NumberFormat.getInstance().format(item.getDeathCnt())
						+"명\n전일대비 증감 수 : " + NumberFormat.getInstance().format(item.getIncDec())
						+"명\n\n";
						break;
					}
				}
				emailUtil.sendEmail(user.getEmail(), "20210421 코로나 알림", dataGlobal + dataLocal);
				System.out.println("Complete");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			//testCovLocalData();
		}
	}
}
