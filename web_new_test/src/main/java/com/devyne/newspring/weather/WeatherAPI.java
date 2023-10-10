package com.devyne.newspring.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.devyne.newspring.vo.WeatherVO;

@Component
public class WeatherAPI {

	private static String SERVICE_KEY = "7L5mJmCQBLt6pIi3cBLK1%2BHmta6WY652oEEi3NeiT3nIyCm1749NQi0ngTeP%2BU26m%2FNgYnsYaKHY9y1yAO5gvA%3D%3D";

	// 공공데이터를 하나의 string으로 가져옴. 
	// 이를 json
	
	public List<WeatherVO> callWeatherData(WeatherVO weatherVO) throws IOException {
		
		List<WeatherVO> weatherDataList = new ArrayList<WeatherVO>();
		
		String result = null;
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
		
		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICE_KEY); /* Service Key */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
					+ URLEncoder.encode(weatherVO.getType(), "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
					+ URLEncoder.encode(weatherVO.getBase_date(), "UTF-8")); /* ‘21년 6월 28일 발표 */
			urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="
					+ URLEncoder.encode(weatherVO.getBase_time(), "UTF-8")); /* 06시 발표(정시단위) */
			urlBuilder.append(
					"&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(weatherVO.getNx(), "UTF-8")); /* 예보지점의 X 좌표값 */
			urlBuilder.append(
					"&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(weatherVO.getNy(), "UTF-8")); /* 예보지점의 Y 좌표값 */
			
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
	        
//	        System.out.println(sb.toString());
	        
	        if(sb.toString() != null && conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        	result = sb.toString();
	        }
	        
	        JSONParser parser = new JSONParser();
	        try {
				JSONObject obj = (JSONObject) parser.parse(result);
				
				JSONObject parse_response = (JSONObject) obj.get("response");
				
				JSONObject parse_body = (JSONObject) parse_response.get("body");
				
				JSONObject parse_items = (JSONObject) parse_body.get("items");
				
				JSONArray parse_item = (JSONArray) parse_items.get("item");
				
				for(int i = 0; i < parse_item.size(); i++) {
					JSONObject weather_info = (JSONObject) parse_item.get(i);
					
					weatherVO.setFcstDate((String) weather_info.get("fcstDate"));
					weatherVO.setFcstTime((String) weather_info.get("fcstTime"));
					
					String category = (String) weather_info.get("category");
					String fcstValue = (String) weather_info.get("fcstValue");
					
					System.out.println("weatherAPI : " + weatherVO.getFcstTime());
					
					weatherDataList.add(weatherVO);
					
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return weatherDataList; 
	}
	
	
}
