package com.ejie.aa79b.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vdorantes
 *
 * @param <T>
 *            Tipo
 */
@Repository()
@Transactional()
public class BaseDaoImpl {
	private JdbcTemplate jdbcTemplate;

	/**
	 * Method use to set the datasource.
	 *
	 * @param dataSource
	 *            DataSource
	 * @return
	 */
	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @return JdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * @param secuencia String
	 * @return Integer
	 */
	public Long getNextVal(String secuencia) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").append(secuencia).append(".NEXTVAL FROM DUAL");
		return this.jdbcTemplate.queryForObject(query.toString(), Long.class);
	}
	

	protected String defaultIfBlank(String value, String defaultValue) {
		return StringUtils.isNotBlank(value) ? value: defaultValue;
	}
}
