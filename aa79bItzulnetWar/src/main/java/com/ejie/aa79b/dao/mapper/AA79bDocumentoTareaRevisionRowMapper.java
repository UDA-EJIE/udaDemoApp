package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTareaAdjunto;
import com.ejie.aa79b.model.webservices.Aa79bDocumentosExpediente;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;

public class AA79bDocumentoTareaRevisionRowMapper implements RowMapper<Aa79bDocumentoTarea> {

	@Override
	public Aa79bDocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bDocumentoTarea documentoTarea = new Aa79bDocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));

		Aa79bDocumentosExpediente documentosExpediente = new Aa79bDocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal(DBConstants.IDDOC));
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		documentosExpediente.setClaseDocumento(resultSet.getLong(DBConstants.CLASEDOCUMENTO));

		List<Aa79bFicheroDocExp> listaFicheros = new ArrayList<Aa79bFicheroDocExp>();
		Aa79bFicheroDocExp fichero = new Aa79bFicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setExtension(resultSet.getString(DBConstants.EXTENSIONDOC));
		fichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		fichero.setNombre(resultSet.getString("NOMBREDOC"));
		Aa79bFicheroDocExp ficheroRel = new Aa79bFicheroDocExp();
		ficheroRel.setOid(resultSet.getString(DBConstants.OIDFICHEROREL));
		ficheroRel.setIdDocRel(resultSet.getBigDecimal(DBConstants.IDDOCREL));
		ficheroRel.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOREL));
		ficheroRel.setNombre(resultSet.getString("NOMBREDOCREL"));
		listaFicheros.add(fichero);
		listaFicheros.add(ficheroRel);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		Aa79bDocumentoTareaAdjunto documentoTareaAdjunto = new Aa79bDocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHEROREVISADO));
		documentoTareaAdjunto.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOFICHREV));
		documentoTareaAdjunto.setTitulo(resultSet.getString(DBConstants.TITULOFICHREV));
		documentoTareaAdjunto.setOid(resultSet.getString(DBConstants.OIDFICHEROREV));
		documentoTareaAdjunto.setNombre(resultSet.getString("NOMBREFICHEROREV"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		Aa79bDocumentoTareaAdjunto documentoJustificante = new Aa79bDocumentoTareaAdjunto();
		documentoJustificante.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHJUST));
		documentoJustificante.setIndVisible(resultSet.getString(DBConstants.INDVISIBLEFICHJUST));
		documentoJustificante.setNombre(resultSet.getString(DBConstants.NOMBREFICHJUST));
		documentoJustificante.setIndEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOJUST));
		documentoTarea.setDocumentoJustificante(documentoJustificante);

		return documentoTarea;
	}

}
