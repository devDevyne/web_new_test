package com.devyne.newspring.service;

import java.io.IOException;

import com.devyne.newspring.vo.WeatherVO;

public interface WeatherService {

	int insertWeatherData(WeatherVO weatherVO);

	void insWeatherDatatoDB(WeatherVO weatherVO) throws IOException;

}
