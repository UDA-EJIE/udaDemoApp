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

public class DocsTareaOrigenNoConformidadRevisionRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal("IDTAREA"));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("IDDOC"));
		documentosExpediente.setTitulo(resultSet.getString("TITULO"));
		documentosExpediente.setClaseDocumento(resultSet.getLong("CLASEDOCUMENTO"));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString("OIDFICHERO"));
		fichero.setExtension(resultSet.getString("EXTENSIONDOC"));
		fichero.setEncriptado(resultSet.getString("INDENCRIPTADO"));
		FicheroDocExp ficheroRel = new FicheroDocExp();
		ficheroRel.setOid(resultSet.getString("OIDFICHEROREL"));
		ficheroRel.setIdDocRel(resultSet.getBigDecimal("IDDOCREL"));
		ficheroRel.setEncriptado(resultSet.getString("INDENCRIPTADOREL"));
		listaFicheros.add(fichero);
		listaFicheros.add(ficheroRel);
		FicheroDocExp ficheroSubsanado = new FicheroDocExp();
		ficheroSubsanado.setIdDocRel(resultSet.getBigDecimal("IDFICHEROSUBSANADO"));
		ficheroSubsanado.setEncriptado(resultSet.getString("INDENCRIPTADODOCSUBS"));
		ficheroSubsanado.setOid(resultSet.getString("OIDFICHERODOCSUBS"));
		ficheroSubsanado.setNombre(resultSet.getString("TITULODOCSUBSANADO"));
		listaFicheros.add(ficheroSubsanado);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal("IDFICHEROREVISADO"));
		documentoTareaAdjunto.setEncriptado(resultSet.getString("INDENCRIPTADOFICHREV"));
		documentoTareaAdjunto.setTitulo(resultSet.getString("TITULOFICHREV"));
		documentoTareaAdjunto.setOid(resultSet.getString("OIDFICHEROREV"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		DocumentoTareaAdjunto documentoJustificante = new DocumentoTareaAdjunto();
		documentoJustificante.setIdFichero(resultSet.getBigDecimal("IDFICHJUST"));
		documentoJustificante.setIndVisible(resultSet.getString("INDVISIBLEFICHJUST"));
		documentoJustificante.setNombre(resultSet.getString("NOMBREFICHJUST"));
		documentoJustificante.setEncriptado(resultSet.getString("INDENCRIPTADOJUST"));
		documentoTarea.setDocumentoJustificante(documentoJustificante);

		return documentoTarea;
	}

}
