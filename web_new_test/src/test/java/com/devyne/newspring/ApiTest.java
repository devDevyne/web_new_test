package com.devyne.newspring;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.devyne.newspring.vo.WeatherVO;

public class ApiTest {

	private static String SERVICE_KEY = "7L5mJmCQBLt6pIi3cBLK1%2BHmta6WY652oEEi3NeiT3nIyCm1749NQi0ngTeP%2BU26m%2FNgYnsYaKHY9y1yAO5gvA%3D%3D";

	@Test
	public void test() throws Exception {
		List<Map<String, String>> datalist = Api();
		for(int i=0; i<datalist.size(); i++) {
			if(datalist.get(i).get("TMP") != null)
				System.out.println(datalist.get(i).get("TMP"));
			
		}
		
		
	}

	private List<Map<String, String>> Api() throws Exception {
		List<WeatherVO> datalist = new ArrayList<>();
		List<Map<String, String>> valueList = new ArrayList<>();
		WeatherVO weatherVO = new WeatherVO();
		String result = null;
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* URL */
		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICE_KEY);
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
					+ URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
					+ URLEncoder.encode("20230830", "UTF-8")); /* ‘21년 6월 28일 발표 */
			urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="
					+ URLEncoder.encode("1100", "UTF-8")); /* 06시 발표(정시단위) */
			urlBuilder.append(
					"&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /* 예보지점의 X 좌표값 */
			urlBuilder.append(
					"&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /* 예보지점의 Y 좌표값 */

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
//			System.out.println(sb.toString());

			if (sb.toString() != null && conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				result = sb.toString();
			}
			
			
			

			try {
				JSONParser parser = new JSONParser();

				JSONObject obj = (JSONObject) parser.parse(result);

				JSONObject parse_response = (JSONObject) obj.get("response");

				JSONObject parse_body = (JSONObject) parse_response.get("body");

				JSONObject parse_items = (JSONObject) parse_body.get("items");

				JSONArray parse_item = (JSONArray) parse_items.get("item");

				for (Object itemObject : parse_item) {
					JSONObject weather_info = (JSONObject) itemObject;
					Map<String, String> value = new HashMap<>();
					
					weatherVO.setFcstDate((String) weather_info.get("fcstDate"));
					weatherVO.setFcstTime((String) weather_info.get("fcstTime"));	
					
					String category = (String) weather_info.get("category");
					String fcstValue = (String) weather_info.get("fcstValue");

					switch (category) {
					case "TMP":
						value.put(category, fcstValue);
						weatherVO.setTmp(fcstValue);
						break;
					case "SKY":
						value.put(category, fcstValue);
						weatherVO.setSky(fcstValue);
						break;
					case "POP":
						value.put(category, fcstValue);
						weatherVO.setPop(fcstValue);
						break;
					case "PCP":
						value.put(category, fcstValue);
						weatherVO.setPcp(fcstValue);
						break;
					case "PTY":
						value.put(category, fcstValue);
						weatherVO.setPty(fcstValue);
						break;
					case "REH":
						value.put(category, fcstValue);
						weatherVO.setReh(fcstValue);
						break;
					}
					
					datalist.add(weatherVO);
					valueList.add(value);
					
					/*
					 * for (int i = 0; i < parse_item.size(); i++) { JSONObject weather_info =
					 * (JSONObject) parse_item.get(i);
					 * 
					 * String category = (String) weather_info.get("category"); String fcstValue =
					 * (String) weather_info.get("fcstValue");
					 * 
					 * WeatherVO weatherVO = new WeatherVO();
					 * 
					 * weatherVO.setFcstDate((String) weather_info.get("fcstDate"));
					 * weatherVO.setFcstTime((String) weather_info.get("fcstTime"));
					 * 
					 * switch(category) { case "TMP": // 1시간 기온 ℃ weatherVO.setValue(category,
					 * fcstValue); break; case "SKY": // 하늘상태 ( 맑음(1), 구름많음(3), 흐림(4) ) if
					 * (fcstValue.equals("1")) { weatherVO.setValue(category, "맑음"); } else if
					 * (fcstValue.equals("3")) { weatherVO.setValue(category, "구름많음"); } else if
					 * (fcstValue.equals("4")) { weatherVO.setValue(category, "흐림"); } break; case
					 * "PTY": // 강수형태 ( 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4) )
					 * weatherVO.setValue(category, fcstValue); break; case "POP": // 강수확률 %
					 * weatherVO.setValue(category, fcstValue); break; case "PCP": // 1시간 강수량 범주(1
					 * mm) weatherVO.setValue(category, fcstValue); break; case "REH": // 습도 %
					 * weatherVO.setValue(category, fcstValue); break; }
					 * 
					 * 
					 * 
					 * switch(category) { case "TMP": // 1시간 기온 ℃ weatherVO.setTmp(fcstValue);
					 * break; case "SKY": // 하늘상태 ( 맑음(1), 구름많음(3), 흐림(4) ) if
					 * (fcstValue.equals("1")) { weatherVO.setSky("맑음"); } else if
					 * (fcstValue.equals("3")) { weatherVO.setSky("구름많음"); } else if
					 * (fcstValue.equals("4")) { weatherVO.setSky("흐림"); } break; case "PTY": //
					 * 강수형태 ( 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4) ) weatherVO.setPty(fcstValue);
					 * break; case "POP": // 강수확률 % weatherVO.setPop(fcstValue); break; case "PCP":
					 * // 1시간 강수량 범주(1 mm) weatherVO.setPcp(fcstValue); break; case "REH": // 습도 %
					 * weatherVO.setReh(fcstValue); break; }
					 * 
					 * 
					 * weatherDataList.add(weatherVO); }
					 */
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		return valueList;
	}
	
	
}
