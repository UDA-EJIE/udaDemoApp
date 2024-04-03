package com.ejie.x21a.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterReturningConnectionAdvice {

	private List<String> sqls;

	@AfterReturning(value = "execution(java.sql.Connection javax.sql.DataSource+.getConnection(..))", returning = "connection")
	public void prepare(Connection connection) throws Throwable {
		if (sqls != null) {
			Statement statement = null;
			try {

				statement = connection.createStatement();
				for (String sql : sqls) {
					statement.executeUpdate(sql);
				}
			} catch (SQLException ex) {
				throw new AopInvocationException("Se ha producido un error en la ejecución del aspecto", ex);
			} finally {
				if (statement != null)
					statement.close();
			}
		}
	}

	public void setSqls(List<String> sqls) {
		this.sqls = sqls;
	}
}
