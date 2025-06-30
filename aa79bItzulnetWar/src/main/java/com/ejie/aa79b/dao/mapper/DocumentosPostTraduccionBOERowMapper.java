package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosPostTraduccionBOE;

/**
 * @author javarona
 *
 */
public class DocumentosPostTraduccionBOERowMapper implements RowMapper<DocumentosPostTraduccionBOE> {

	@Override()
	public DocumentosPostTraduccionBOE mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DocumentoTareaAdjunto ficheroInforme = new DocumentoTareaAdjunto();
		ficheroInforme.setIdFichero(resultSet.getBigDecimal("IDFICHEROINFORME088"));
		ficheroInforme.setTitulo(resultSet.getString("TITULOFICHEROINFORME088"));
		ficheroInforme.setExtension(resultSet.getString("EXTENSIONFICHEROINFORME088"));
		ficheroInforme.setContentType(resultSet.getString("CONTENTTYPEFICHEROINFORME088"));
		ficheroInforme.setTamano(resultSet.getLong("TAMANOFICHEROINFORME088"));
		ficheroInforme.setEncriptado(resultSet.getString("INDENCRIPTADOINFORME088"));
		ficheroInforme.setOid(resultSet.getString("OIDFICHEROINFORME088"));
		ficheroInforme.setNombre(resultSet.getString("NOMBREFICHEROINFORME088"));

		DocumentoTareaAdjunto ficheroDoc = new DocumentoTareaAdjunto();
		ficheroDoc.setIdFichero(resultSet.getBigDecimal("IDFICHERODOC088"));
		ficheroDoc.setTitulo(resultSet.getString("TITULOFICHERODOC088"));
		ficheroDoc.setExtension(resultSet.getString("EXTENSIONFICHERODOC088"));
		ficheroDoc.setContentType(resultSet.getString("CONTENTTYPEFICHERODOC088"));
		ficheroDoc.setTamano(resultSet.getLong("TAMANOFICHERODOC088"));
		ficheroDoc.setEncriptado(resultSet.getString("INDENCRIPTADODOC088"));
		ficheroDoc.setOid(resultSet.getString("OIDFICHERODOC088"));
		ficheroDoc.setNombre(resultSet.getString("NOMBREFICHERODOC088"));

		DocumentosPostTraduccionBOE docuPostTraduccionBOE = new DocumentosPostTraduccionBOE();
		docuPostTraduccionBOE.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		docuPostTraduccionBOE.setIndCorreciones(resultSet.getString("INDCORRECCIONES086"));
		docuPostTraduccionBOE.setFicheroInforme(ficheroInforme);
		docuPostTraduccionBOE.setFicheroDoc(ficheroDoc);
		docuPostTraduccionBOE.setFechaAltaInforme(resultSet.getDate("FECHAALTAINFORME086"));
		docuPostTraduccionBOE.setFechaAltaDoc(resultSet.getDate("FECHAALTADOC086"));
		docuPostTraduccionBOE.setIdFicheroInforme(resultSet.getBigDecimal("IDFICHEROINFORME088"));
		docuPostTraduccionBOE.setIdFicheroDoc(resultSet.getBigDecimal("IDFICHERODOC088"));

		return docuPostTraduccionBOE;
	}

}
