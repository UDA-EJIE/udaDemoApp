package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.CabeceraExpediente;
import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;

/**
 * Aa79bCabeceraExpedienteRowMapper
 * 
 * @author mrodriguez
 */
public class Aa79bCabeceraExpedienteRowMapper implements RowMapper<CabeceraExpediente> {

	@Override
	public CabeceraExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		CabeceraExpediente cabeceraExpediente = new CabeceraExpediente();

		cabeceraExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		cabeceraExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		cabeceraExpediente.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		cabeceraExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		cabeceraExpediente.setIdiomaDescEs(resultSet.getString("DESC_IDIOMA_ES_009"));
		cabeceraExpediente.setIdiomaDescEu(resultSet.getString("DESC_IDIOMA_EU_009"));

		Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();
		gestor.setDniGestor(resultSet.getString("DNISOLICITANTE"));
		gestor.setNombreGestor(resultSet.getString("NOMBRESOLICITANTE"));
		gestor.setApellido1Gestor(resultSet.getString("APEL1SOLICITANTE"));
		gestor.setApellido2Gestor(resultSet.getString("APEL2SOLICITANTE"));
		gestor.setIndVIPGestor(resultSet.getString("INDVIPSOLICITANTE"));
		gestor.setTipoEntidadGestor(resultSet.getString("TIPOENTIDAD"));
		gestor.setCodigoEntidadGestor(resultSet.getInt("IDENTIDAD"));
		gestor.setDescAmpEntidadEuGestor(resultSet.getString("DESCAMPENTIDADEU"));
		gestor.setDescAmpEntidadEsGestor(resultSet.getString("DESCAMPENTIDADES"));
		gestor.setDescEntidadEuGestor(resultSet.getString("DESCENTIDADEU"));
		gestor.setDescEntidadEsGestor(resultSet.getString("DESCENTIDADES"));
		gestor.setCifGestor(resultSet.getString("CIFENTIDAD"));

		cabeceraExpediente.setGestor(gestor);

		return cabeceraExpediente;
	}

}
