package com.devyne.newspring.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devyne.newspring.dao.WeatherDao;
import com.devyne.newspring.vo.WeatherVO;

@Repository
public class WeatherDaoImpl implements WeatherDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertWeatherData(WeatherVO weatherVO) {
		return sqlSession.insert("insert_winfo", weatherVO);
	}
	
}
