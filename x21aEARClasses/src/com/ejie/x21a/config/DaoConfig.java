package com.ejie.x21a.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

/**
 * Configuración del DAO.
 */
@ComponentScan("com.ejie.x21a.dao")
@Configuration
public class DaoConfig {

	/**
	 * Configuración del dataSource.
	 * 
	 * @return DataSource.
	 */
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		lookup.setResourceRef(false);
		return lookup.getDataSource("x21a.x21aDataSource");
	}

	@Bean
	public LobHandler lobHandler() {
		return new DefaultLobHandler();
	}

	/*@Bean
	public AfterReturningConnectionAdvice afterReturningConnectionAdvice() {
		List<String> sqls = new ArrayList<>();
		sqls.add("ALTER SESSION SET NLS_SORT='SPANISH_AI'");

		AfterReturningConnectionAdvice connectionAdvice = new AfterReturningConnectionAdvice();
		connectionAdvice.setSqls(sqls);

		return connectionAdvice;
	}*/

}
