package com.devyne.newspring.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.devyne.newspring.service.WeatherService;
import com.devyne.newspring.vo.WeatherVO;

@Controller
public class WeatherController {
	private Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping(value = "/wth/info")
	public String weatherList(Model model) throws IOException {
		WeatherVO weatherData = new WeatherVO();
		
		weatherData.setType("JSON");
		weatherData.setBase_date("20230830");
		weatherData.setBase_time("1100");
		weatherData.setNx("60");
		weatherData.setNy("127");
		
		weatherService.insWeatherDatatoDB(weatherData);
		
		return "/wth/info";
	}
	
}
