package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.webservices.Aa79bAuditoriaDocumentoSeccionExpediente;

/**
 *
 * @author eaguirresarobe
 *
 */
public class Aa79bAuditoriaDocumentoSeccionExpedienteRowMapper
		implements RowMapper<Aa79bAuditoriaDocumentoSeccionExpediente> {

	@Override
	public Aa79bAuditoriaDocumentoSeccionExpediente mapRow(ResultSet rs, int arg1) throws SQLException {
		Aa79bAuditoriaDocumentoSeccionExpediente documento = new Aa79bAuditoriaDocumentoSeccionExpediente();
		documento.setIdAuditoria(rs.getLong("IDAUDITORIA"));
		documento.setIdSeccion(rs.getInt("IDSECCION"));
		documento.setIdFicheroInterno(rs.getBigDecimal("IDFICHERO"));
		documento.setTituloFichero(rs.getString("TITULOFICHERO"));
		documento.setExtension(rs.getString("EXTENSIONFICHERO"));
		documento.setContentType(rs.getString("CONTENTTYPEFICHERO"));
		documento.setTamano(rs.getLong("TAMANOFICHERO"));
		documento.setEncriptado(rs.getString("INDENCRIPTADO"));
		documento.setRutaPif(rs.getString("RUTAPIFFICHERO"));
		documento.setOid(rs.getString("OIDFICHERO"));
		documento.setNombre(rs.getString("NOMBREFICHERO"));
		return documento;
	}

}
