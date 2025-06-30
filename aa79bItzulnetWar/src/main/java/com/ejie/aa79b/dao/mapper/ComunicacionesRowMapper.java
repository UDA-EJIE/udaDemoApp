package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.Comunicaciones;

public class ComunicacionesRowMapper implements RowMapper<Comunicaciones> {

	@Override()
	public Comunicaciones mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final Comunicaciones comunicaciones = new Comunicaciones();
		comunicaciones.setId0D4(resultSet.getLong("ID_0D4"));
		comunicaciones.setNumExp(resultSet.getInt("NUM_EXP_0D4"));
		comunicaciones.setAnyo(resultSet.getLong("ANYO_0D4"));
		comunicaciones.setRefTramitagune(resultSet.getString("REF_TRAMITAGUNE_0D4"));
		comunicaciones.setFecha(resultSet.getTimestamp("FECHA_0D4"));
		comunicaciones.setTipo(resultSet.getString("TIPO_0D4"));
		comunicaciones.setRemitente(resultSet.getString("REMITENTE_0D4"));
		comunicaciones.setAsunto(resultSet.getString("ASUNTO_0D4"));
		comunicaciones.setMensaje(resultSet.getString("MENSAJE_0D4"));
		comunicaciones.setIdFichero0D4(resultSet.getBigDecimal("ID_FICHERO_0D4"));
		//Fichero tabla aa79b88t00
		comunicaciones.setCodigo(resultSet.getLong("IDFICHERO088"));
		comunicaciones.setTitulo(resultSet.getString("TITULOFICHERO088"));
		comunicaciones.setExtension(resultSet.getString("EXTENSIONFICHERO088"));
		comunicaciones.setContentType(resultSet.getString("CONTENTTYPEFICHERO088"));
		comunicaciones.setTamano(resultSet.getLong("TAMANOFICHERO088"));
		comunicaciones.setEncriptado(resultSet.getString("INDENCRIPTADO088"));
		comunicaciones.setRutaPif(resultSet.getString("RUTAPIFFICHERO088"));
		comunicaciones.setOidFichero(resultSet.getString("OIDFICHERO088"));
		comunicaciones.setNombre(resultSet.getString("NOMBREFICHERO088"));

		return comunicaciones;
	}

}
