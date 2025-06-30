package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

public class DocsTareaOrigenNoConformidadTraductorRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet rs, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();
		documentoTarea.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(rs.getBigDecimal(DBConstants.IDDOC));
		documentosExpediente.setTitulo(rs.getString(DBConstants.TITULO));
		documentosExpediente.setClaseDocumento(rs.getLong(DBConstants.CLASEDOCUMENTO));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(rs.getString(DBConstants.OIDFICHERO));
		fichero.setExtension(rs.getString(DBConstants.EXTENSIONDOC));
		fichero.setEncriptado(rs.getString(DBConstants.INDENCRIPTADO));
		fichero.setNombre(rs.getString("NOMBREDOCORIGINAL"));
		listaFicheros.add(fichero);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(rs.getBigDecimal(DBConstants.IDFICHEROTRADUCIDO));
		documentoTareaAdjunto.setEncriptado(rs.getString(DBConstants.INDENCRIPTADODOCTRAD));
		documentoTareaAdjunto.setOid(rs.getString(DBConstants.OIDFICHERODOCTRAD));
		documentoTareaAdjunto.setTitulo(rs.getString(DBConstants.TITULODOCTRADUCIDO));
		documentoTareaAdjunto.setNombre(rs.getString("NOMBREFICHEROTRADUCIDO"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		DocumentoTareaAdjunto documentoTareaSubsanado = new DocumentoTareaAdjunto();
		documentoTareaSubsanado.setIdFichero(rs.getBigDecimal(DBConstants.IDFICHEROSUBSANADO));
		documentoTareaSubsanado.setEncriptado(rs.getString(DBConstants.INDENCRIPTADODOCSUBS));
		documentoTareaSubsanado.setOid(rs.getString(DBConstants.OIDFICHERODOCSUBS));
		documentoTareaSubsanado.setTitulo(rs.getString(DBConstants.TITULODOCSUBSANADO));
		documentoTareaSubsanado.setNombre(rs.getString("NOMBREFICHEROTAREA"));
		documentoTarea.setDocumentoJustificante(documentoTareaSubsanado);

		return documentoTarea;
	}

}
