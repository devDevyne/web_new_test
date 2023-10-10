package com.devyne.newspring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.devyne.newspring.vo.DateVO;

public class RestDateAPITest {

	
	private String Service_Key = "7L5mJmCQBLt6pIi3cBLK1%2BHmta6WY652oEEi3NeiT3nIyCm1749NQi0ngTeP%2BU26m%2FNgYnsYaKHY9y1yAO5gvA%3D%3D";

	@Test
	public void callData() throws Exception, Throwable {
		List<DateVO> dataList = makeArrayListXMLData();
		
		for(int i = 0; i < dataList.size(); i++) {
			
			System.out.println(dataList.get(i).getDateName());
			System.out.println(dataList.get(i).getLocdate());
		}
		
		
	}
	
	
	
	private String callRestDateData() throws Exception {
		String result = null;
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /* URL */

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + Service_Key);
			urlBuilder.append(
					"&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode("2023", "UTF-8")); /* 연 */
			urlBuilder.append(
					"&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 월 */
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
			
			result = sb.toString();
					
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
		
	}
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = nlList.item(0);
		if(nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}
	
	private List<DateVO> makeArrayListXMLData() throws Exception, Throwable {
		List<DateVO> dataList = new ArrayList<>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc = null;
		
		InputSource is = new InputSource(new StringReader(callRestDateData()));
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
		
			NodeList nList = doc.getElementsByTagName("item");
			System.out.println("item parsing count : " + nList.getLength());
			
			for(int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					DateVO dateVO = new DateVO();
					Element eElement = (Element) node;
					
					String dateName = getTagValue("dateName", eElement);
					String locdate = getTagValue("locdate", eElement);
					
					dateVO.setDateName(dateName);
					dateVO.setLocdate(locdate);
					
					dataList.add(dateVO);
					
				}
			}
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dataList;
	}

}
