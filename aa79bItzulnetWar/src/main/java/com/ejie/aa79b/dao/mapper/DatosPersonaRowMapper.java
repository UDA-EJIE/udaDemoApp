package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosPersona;

public class DatosPersonaRowMapper implements RowMapper<DatosPersona>{
	
	
	@Override
	public DatosPersona mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DatosPersona datosPersona = new DatosPersona();
        datosPersona.setDni(resultSet.getString(DBConstants.DNI));
        datosPersona.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
        datosPersona.setPreDni(resultSet.getString(DBConstants.PREDNI));
        datosPersona.setSufDni(resultSet.getString(DBConstants.SUFDNI));
        datosPersona.setNombre(resultSet.getString(DBConstants.NOMBRE));
        datosPersona.setApellido1(resultSet.getString(DBConstants.APEL1_DATOS_PERSONA));
        datosPersona.setApellido2(resultSet.getString(DBConstants.APEL2_DATOS_PERSONA));
			
		return datosPersona; 
	}

}
