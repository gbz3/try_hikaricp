package com.github.gbz3.try_hikaricp;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfiguration {
	
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;
	
//	@Autowired
//	private MetricRegistry metricRegistry;
	
	@Bean
	public DataSource primaryDataSource() {
		Properties dsProps = new Properties();
		dsProps.setProperty( "url", url );
		dsProps.setProperty( "user", user );
		dsProps.setProperty( "password", password );
		
		Properties configProps = new Properties();
		configProps.setProperty( "jdbcUrl", url );
		configProps.setProperty( "poolName", "myPool" );
		
		HikariConfig hc = new HikariConfig( configProps );
		hc.setDataSourceProperties( dsProps );
//		hc.setMetricRegistry( metricRegistry );
		return new HikariDataSource( hc );
	}

}
