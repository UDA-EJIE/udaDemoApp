package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ConfigHorasPrevistasProveedorExt;

public class ConfigHorasPrevistasProvExtDatosBasicosFilterRowMapper
		implements RowMapper<ConfigHorasPrevistasProveedorExt> {

	@Override
	public ConfigHorasPrevistasProveedorExt mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ConfigHorasPrevistasProveedorExt configHorasPrevistas = new ConfigHorasPrevistasProveedorExt();
		configHorasPrevistas.setNivUsuario(resultSet.getString("NIVELUSUARIO045"));
		configHorasPrevistas.setNumPalTradHora(resultSet.getBigDecimal("PALTRADHORA045"));
		configHorasPrevistas.setPalHoraConcor100(resultSet.getBigDecimal("PALHORACONCOR100045"));
		configHorasPrevistas.setPalHoraConcor9599(resultSet.getBigDecimal("PALHORACONCOR9599045"));
		configHorasPrevistas.setPalHoraConcor8594(resultSet.getBigDecimal("PALHORACONCOR8594045"));
		configHorasPrevistas.setPalHoraConcor084(resultSet.getBigDecimal("PALHORACONCOR084045"));
		configHorasPrevistas.setPalSegConcor100(resultSet.getBigDecimal("PALSEGCONCOR100045"));
		configHorasPrevistas.setPalSegConcor9599(resultSet.getBigDecimal("PALSEGCONCOR9599045"));
		configHorasPrevistas.setPalSegConcor8594(resultSet.getBigDecimal("PALSEGCONCOR8594045"));
		configHorasPrevistas.setPalSegConcor084(resultSet.getBigDecimal("PALSEGCONCOR084045"));
		configHorasPrevistas.setPalReExtHora(resultSet.getBigDecimal("PALREVHORA045"));
		configHorasPrevistas.setPalTradSeg(resultSet.getBigDecimal("PALTRADSEG045"));
		configHorasPrevistas.setPalRevSeg(resultSet.getBigDecimal("PALREVSEG045"));
		return configHorasPrevistas;
	}

}
