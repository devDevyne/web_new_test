package com.devyne.newspring.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devyne.newspring.dao.WeatherDao;
import com.devyne.newspring.service.WeatherService;
import com.devyne.newspring.vo.WeatherVO;
import com.devyne.newspring.weather.WeatherAPI;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherDao weatherDao;
	
	private WeatherAPI api = new WeatherAPI();
	
	@Override 
	public int insertWeatherData(WeatherVO weatherVO) {
		return weatherDao.insertWeatherData(weatherVO);
	}
	
	@Override
	public void insWeatherDatatoDB(WeatherVO weatherVO) throws IOException {
	//	WeatherVO weather = new WeatherVO();
		List<WeatherVO> weatherDataList = new ArrayList<WeatherVO>();
		weatherDataList=  api.callWeatherData(weatherVO);
		System.out.println("insWeatherDatatoDB : " + weatherDataList.get(0).getFcstTime());
		System.out.println("insWeatherDatatoDB : " + weatherDataList.get(14).getFcstTime());
		
		for(int i=0; i<weatherDataList.size(); i++) {
			
			weatherVO.setFcstDate(weatherDataList.get(i).getFcstDate());
			weatherVO.setFcstTime(weatherDataList.get(i).getFcstTime());
			
//			insertWeatherData(weatherVO);
		}
	}
	
}
