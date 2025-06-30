package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ConfigHorasPrevistas;
import com.ejie.aa79b.model.TipoRelevancia;

public class ConfigHorasPrevistasDatosBasicosFilterRowMapper implements RowMapper<ConfigHorasPrevistas> {

	@Override
	public ConfigHorasPrevistas mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ConfigHorasPrevistas configHorasPrevistas = new ConfigHorasPrevistas();
		configHorasPrevistas.setNivUsuario(resultSet.getString("NIVELUSUARIO002"));
		TipoRelevancia tipoRelevancia = new TipoRelevancia(resultSet.getLong("IDTIPORELEVANCIA002"));
		tipoRelevancia.setDescRelevanciaEs(resultSet.getString("TIPORELEVANCIADESCES"));
		tipoRelevancia.setDescRelevanciaEu(resultSet.getString("TIPORELEVANCIADESCEU"));
		configHorasPrevistas.setTipoRelevancia(tipoRelevancia);
		configHorasPrevistas.setPalTradHora(resultSet.getBigDecimal("PALTRADHORA002"));
		configHorasPrevistas.setPalHoraConcor100(resultSet.getBigDecimal("PALHORACONCOR100002"));
		configHorasPrevistas.setPalHoraConcor9599(resultSet.getBigDecimal("PALHORACONCOR9599002"));
		configHorasPrevistas.setPalHoraConcor8594(resultSet.getBigDecimal("PALHORACONCOR8594002"));
		configHorasPrevistas.setPalHoraConcor084(resultSet.getBigDecimal("PALHORACONCOR084002"));
		configHorasPrevistas.setPalRevHora(resultSet.getBigDecimal("PALREVHORA002"));
		configHorasPrevistas.setPalSegConcor100(resultSet.getBigDecimal("PALSEGCONCOR100002"));
		configHorasPrevistas.setPalSegConcor9599(resultSet.getBigDecimal("PALSEGCONCOR9599002"));
		configHorasPrevistas.setPalSegConcor8594(resultSet.getBigDecimal("PALSEGCONCOR8594002"));
		configHorasPrevistas.setPalSegConcor084(resultSet.getBigDecimal("PALSEGCONCOR084002"));
		configHorasPrevistas.setPalTradSeg(resultSet.getBigDecimal("PALTRADSEG002"));
		configHorasPrevistas.setPalRevSeg(resultSet.getBigDecimal("PALREVSEG002"));
		return configHorasPrevistas;
	}

}
