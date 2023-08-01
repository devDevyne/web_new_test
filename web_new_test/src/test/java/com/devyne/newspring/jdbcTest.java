package com.devyne.newspring;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class jdbcTest {
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://152.70.253.170:3306/testdb", "root",
				"991204")) {
			log.info(conn);
			if (conn != null) {
				System.out.println("DB Connection Sucess!");
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
