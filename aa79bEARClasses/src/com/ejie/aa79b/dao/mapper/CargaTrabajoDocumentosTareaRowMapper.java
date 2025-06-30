package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

public class CargaTrabajoDocumentosTareaRowMapper implements RowMapper<DocumentosExpediente> {
	@Override()
	public DocumentosExpediente mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(rs.getBigDecimal(DBConstants.IDDOC));
		documentosExpediente.setAnyo(rs.getLong(DBConstants.ANYO));
		documentosExpediente.setNumExp(rs.getInt(DBConstants.NUMEXP));
		documentosExpediente.setNumPalSolic(rs.getInt(DBConstants.NUMPALSOLIC));
		documentosExpediente.setNumPalIzo(rs.getInt(DBConstants.NUMPALIZO));
		documentosExpediente.setTipoDocumento(rs.getLong(DBConstants.TIPODOCUMENTO));
		documentosExpediente.setTipoDocumentoDescEs(rs.getString(DBConstants.TIPODOCUMENTODESCES));
		documentosExpediente.setTipoDocumentoDescEu(rs.getString(DBConstants.TIPODOCUMENTODESCEU));

		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(rs.getString(DBConstants.OIDFICHERO));
		fichero.setNombre(rs.getString(DBConstants.NOMBREFICHERO));
		listaFicheros.add(fichero);
		documentosExpediente.setFicheros(listaFicheros);
		return documentosExpediente;
	}
}