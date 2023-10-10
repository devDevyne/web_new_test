package com.devyne.newspring.vo;

import java.util.Map;

import lombok.Data;

@Data
public class WeatherVO {
	
	private String type = "json"; 
	private String ftype = "SHRT"; // 단기예보
	private String base_date; // 발표일자
	private String base_time; // 발표시각
	private String nx; 
	private String ny;
	
	private String fcstDate; // 예보일자
	private String fcstTime; // 예보시각
	
	private Map<String, String> values;
	
	//value
	private String tmp; // 1시간 기온 ℃
	private String sky; // 하늘상태 ( 맑음(1), 구름많음(3), 흐림(4) ) 
	private String pty; // 강수형태 ( 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4) )
	private String pop; // 강수확률 %
	private String pcp; // 1시간 강수량 범주(1 mm)
	private String reh; // 습도 %

}
