package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.FicheroVersionExp;

/**
 * @author javarona
 *
 */
public class FicheroVersionExpRowMapper implements RowMapper<FicheroVersionExp> {
	
	@Override()
	public FicheroVersionExp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	    
	    FicheroVersionExp ficheroVersionExp = new FicheroVersionExp();
	    ficheroVersionExp.setIdDocInsertado(resultSet.getBigDecimal("IDDOC"));
	    ficheroVersionExp.setPersona(resultSet.getString("PERSONACONTACTO"));
	    ficheroVersionExp.setOid(resultSet.getString(DBConstants.OIDFICHERO));
	    ficheroVersionExp.setFechaAlta(resultSet.getTimestamp(DBConstants.FECHAALTA));
	    ficheroVersionExp.setContentType(resultSet.getString("CONTENTTYPE"));
	    ficheroVersionExp.setNombre(resultSet.getString("NOMBREFICHERO"));
		
		return ficheroVersionExp;
	}

}
