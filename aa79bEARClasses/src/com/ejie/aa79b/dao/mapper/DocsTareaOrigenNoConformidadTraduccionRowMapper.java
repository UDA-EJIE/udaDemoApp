package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

public class DocsTareaOrigenNoConformidadTraduccionRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea docTarea = new DocumentoTarea();
		docTarea.setIdTarea(resultSet.getBigDecimal("IDTAREA"));

		DocumentosExpediente docsExpediente = new DocumentosExpediente();
		docsExpediente.setIdDoc(resultSet.getBigDecimal("IDDOC"));
		docsExpediente.setTitulo(resultSet.getString("TITULO"));
		docsExpediente.setClaseDocumento(resultSet.getLong("CLASEDOCUMENTO"));

		List<FicheroDocExp> lFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString("OIDFICHERO"));
		fichero.setExtension(resultSet.getString("EXTENSIONDOC"));
		fichero.setEncriptado(resultSet.getString("INDENCRIPTADO"));
		fichero.setNombre(resultSet.getNString("NOMBREDOCORIGINAL"));
		lFicheros.add(fichero);
		docsExpediente.setFicheros(lFicheros);
		docTarea.setDocumentoOriginal(docsExpediente);

		DocumentoTareaAdjunto docTareaAdjunto = new DocumentoTareaAdjunto();
		docTareaAdjunto.setIdFichero(resultSet.getBigDecimal("IDFICHEROTRADUCIDO"));
		docTareaAdjunto.setEncriptado(resultSet.getString("INDENCRIPTADODOCTRAD"));
		docTareaAdjunto.setOid(resultSet.getString("OIDFICHERODOCTRAD"));
		docTareaAdjunto.setTitulo(resultSet.getString("TITULODOCTRADUCIDO"));
		docTareaAdjunto.setNombre(resultSet.getString("NOMBREFICHEROTRADUCIDO"));
		docTarea.setDocumentoAdjunto(docTareaAdjunto);

		DocumentoTareaAdjunto docTareaSubsanado = new DocumentoTareaAdjunto();
		docTareaSubsanado.setIdFichero(resultSet.getBigDecimal("IDFICHEROSUBSANADO"));
		docTareaSubsanado.setEncriptado(resultSet.getString("INDENCRIPTADODOCSUBS"));
		docTareaSubsanado.setOid(resultSet.getString("OIDFICHERODOCSUBS"));
		docTareaSubsanado.setTitulo(resultSet.getString("TITULODOCSUBSANADO"));
		docTareaSubsanado.setNombre(resultSet.getString("NOMBREFICHEROSUBSANADO"));
		docTarea.setDocumentoJustificante(docTareaSubsanado);

		return docTarea;
	}

}
