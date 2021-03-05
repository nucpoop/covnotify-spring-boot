package com.nucpoop.covserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.nucpoop.covserver.model.CovData;

public class Utils {

	public static void getResponseXML(StringBuilder sb) {
		// CovResponse response = new CovResponse();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CovData.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			CovData covResponse;
			covResponse = (CovData) unmarshaller.unmarshal(new StringReader(sb.toString()));

			covResponse.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// public static void getNodeListFromXML(StringBuilder sb)
	// throws ParserConfigurationException, SAXException, IOException {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder documentBuilder = factory.newDocumentBuilder();

	// Xml to NodeList
	// Document document = documentBuilder.parse(new InputSource(new
	// StringReader(sb.toString())));

	// Element root = document.getDocumentElement();

	// NodeList children = root.getChildNodes();
	// for (int i = 0; i < children.getLength(); i++) {
	// Node node = children.item(i);
	// if(node.getNodeType() == Node.ELEMENT_NODE) {
	// Element element = (Element)node;
	// String nodeName = element.getNodeName();

	// }
	// }

	// }

	public static StringBuilder getCovData() throws IOException {

		final String SERVICE_KEY = "In700GpDhOczBBTNPW9EKqfV2XwqE5ff7638azwe2D9uetiEFgIRLsnK%2FIwzUVJc0xorUJOma6aR4bKJYRu7uQ%3D%3D";
		final String ENCODING = "UTF-8";

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", ENCODING) + "=" + SERVICE_KEY); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", ENCODING) + "=" + URLEncoder.encode("1", ENCODING)); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", ENCODING) + "="
				+ URLEncoder.encode("10", ENCODING)); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("startCreateDt", ENCODING) + "="
				+ URLEncoder.encode("20200310", ENCODING)); /* 검색할 생성일 범위의 시작 */
		urlBuilder.append("&" + URLEncoder.encode("endCreateDt", ENCODING) + "="
				+ URLEncoder.encode("20200315", ENCODING)); /* 검색할 생성일 범위의 종료 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		getResponseXML(sb);
		// try {
		// getNodeListFromXML(sb);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return sb;
	}

	public static StringBuilder getCovDataLocal() throws IOException{

		final String SERVICE_KEY = "In700GpDhOczBBTNPW9EKqfV2XwqE5ff7638azwe2D9uetiEFgIRLsnK%2FIwzUVJc0xorUJOma6aR4bKJYRu7uQ%3D%3D";
		final String ENCODING = "UTF-8";

		StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey",ENCODING) + "=" + SERVICE_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey",ENCODING) + "=" + URLEncoder.encode("-", ENCODING)); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo",ENCODING) + "=" + URLEncoder.encode("1", ENCODING)); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows",ENCODING) + "=" + URLEncoder.encode("10", ENCODING)); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt",ENCODING) + "=" + URLEncoder.encode("20200410", ENCODING)); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt",ENCODING) + "=" + URLEncoder.encode("20200410", ENCODING)); /*검색할 생성일 범위의 종료*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
		getResponseXML(sb);
        return sb;
	}

}
