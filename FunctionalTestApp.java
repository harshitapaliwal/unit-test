package com.dellemc.iot.connectivity.provisioning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@ComponentScan
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
@Component
public class FunctionalTestApp {

	@Bean
	@Primary
	@Profile("test")
	public DataSource dataSource() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:database/hsql-mssql-compatibility.sql")
				.addScript("classpath:database/main.sql")
				.generateUniqueName(true).build();

		resetDataSource(dataSource);

		 ResourceDatabasePopulator rs = new ResourceDatabasePopulator();
	        rs.addScript(new ClassPathResource("database/stored-procedure.sql"));
	        rs.setSeparator("/;");
	        rs.execute(dataSource);

		return dataSource;
	}

	/**
	 * Reset the hsql dataSource for each test case
	 * 
	 * @param dataSource
	 */
	private void resetDataSource(DataSource dataSource) {
		try (Connection con = dataSource.getConnection()) {
			con.createStatement().executeQuery(readFile("database/test-data.sql"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 public static String readFile(String filePath) {
		    String sql = "";
		    try (BufferedReader br = new BufferedReader(new FileReader(
		        Thread.currentThread().getContextClassLoader().getResource(filePath).getFile()))) {
		      sql = br.lines().collect(Collectors.joining());
		    } catch (IOException ex) {
		      ex.printStackTrace();
		    }
		    return sql;
		  }


}
