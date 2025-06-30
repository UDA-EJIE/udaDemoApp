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

public class DocumentoTareaRevisionRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA83));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal(DBConstants.IDDOC));
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		documentosExpediente.setClaseDocumento(resultSet.getLong(DBConstants.CLASEDOCUMENTO));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		fichero.setExtension(resultSet.getString(DBConstants.EXTENSIONDOC));
		fichero.setNombre(resultSet.getString("NOMBREDOCORIGINAL"));
		FicheroDocExp ficheroRel = new FicheroDocExp();
		ficheroRel.setOid(resultSet.getString(DBConstants.OIDFICHEROREL));
		ficheroRel.setIdDocRel(resultSet.getBigDecimal(DBConstants.IDDOCREL));
		ficheroRel.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOREL));
		ficheroRel.setExtension(resultSet.getString(DBConstants.EXTENSIONDOCREL));
		ficheroRel.setNombre(resultSet.getString("NOMBREDOCREL"));
		listaFicheros.add(fichero);
		listaFicheros.add(ficheroRel);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHEROREVISADO));
		documentoTareaAdjunto.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOFICHREV));
		documentoTareaAdjunto.setTitulo(resultSet.getString(DBConstants.TITULOFICHREV));
		documentoTareaAdjunto.setOid(resultSet.getString(DBConstants.OIDFICHEROREV));
		documentoTareaAdjunto.setNombre(resultSet.getString("NOMBREFICHEROREV"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		DocumentoTareaAdjunto documentoJustificante = new DocumentoTareaAdjunto();
		documentoJustificante.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHJUST));
		documentoJustificante.setIndVisible(resultSet.getString(DBConstants.INDVISIBLEFICHJUST));
		documentoJustificante.setNombre(resultSet.getString(DBConstants.NOMBREFICHJUST));
		documentoJustificante.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOJUST));
		documentoTarea.setDocumentoJustificante(documentoJustificante);

		return documentoTarea;
	}

}
