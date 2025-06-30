package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.TradosExp;

public class DocumentosTareaRowMapper implements RowMapper<DocumentosExpediente> {

	@Override
	public DocumentosExpediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("IDDOC056"));
		documentosExpediente.setTitulo(resultSet.getString("TITULO056"));
		documentosExpediente.setClaseDocumento(resultSet.getLong("CLASEDOCUMENTO056"));
		documentosExpediente.setNumPalSolic(resultSet.getInt("NUMPALSOLIC056"));
		documentosExpediente.setNumPalIzo(resultSet.getInt("NUMPALIZO056"));
		TradosExp trados = new TradosExp();
		trados.setNumTotalPal(resultSet.getInt("NUMTOTALPAL091"));
		trados.setNumPalConcor084090(resultSet.getInt("NUMPALCONCOR084091"));
		trados.setNumPalConcor8594090(resultSet.getInt("NUMPALCONCOR8594091"));
		trados.setNumPalConcor95100090(resultSet.getInt("NUMPALCONCOR95100091"));
		trados.setNumPalConcor9599090(resultSet.getInt("NUMPALCONCOR9599091"));
		trados.setNumPalConcor100090(resultSet.getInt("NUMPALCONCOR100091"));
		documentosExpediente.setTradosExp(trados);
		return documentosExpediente;
	}

}
