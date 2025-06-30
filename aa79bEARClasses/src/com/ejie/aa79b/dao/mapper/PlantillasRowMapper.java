package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.Plantillas;

public class PlantillasRowMapper implements RowMapper<Plantillas> {

	@Override()
	public Plantillas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final Plantillas plantillas = new Plantillas();
		plantillas.setId0A7(resultSet.getLong("ID0A7"));
		plantillas.setNombrePlantilla0A7(resultSet.getString("NOMBREPLANTILLA0A7"));
		plantillas.setVarPlantilla0A7(resultSet.getString("VARPLANTILLA0A7"));
		plantillas.setIdFicheroPlantilla0A7(resultSet.getBigDecimal("IDFICHEROPLANTILLA0A7"));
		plantillas.setCodigo(resultSet.getLong("IDFICHERO088"));
		plantillas.setTitulo(resultSet.getString("TITULOFICHERO088"));
		plantillas.setExtension(resultSet.getString("EXTENSIONFICHERO088"));
		plantillas.setContentType(resultSet.getString("CONTENTTYPEFICHERO088"));
		plantillas.setTamano(resultSet.getLong("TAMANOFICHERO088"));
		plantillas.setEncriptado(resultSet.getString("INDENCRIPTADO088"));
		plantillas.setRutaPif(resultSet.getString("RUTAPIFFICHERO088"));
		plantillas.setOidFichero(resultSet.getString("OIDFICHERO088"));
		plantillas.setNombre(resultSet.getString("NOMBREFICHERO088"));

		return plantillas;
	}

}
