package com.ejie.aa79b.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class PLConnectionUtils extends SpringBeanAutowiringSupport {

	/**
	 * Constructor
	 */
	public PLConnectionUtils() {
		// Constructor vacío
	}

	public static long crearNumExp(final long anyo, final JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{?= call OBTENER_NUM_EXPEDIENTE(?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, anyo);
				return callableStatement;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});
	}

}
