package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

public class DocsDetalleTareaRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULODOCORIGINAL));
		documentosExpediente.setExtension(resultSet.getString("EXTENSIONDOC"));
		documentosExpediente.setClaseDocumentoDesc(resultSet.getString(DBConstants.CLASEDOCUMENTODOCORIG));

		DatosContacto autor = new DatosContacto();
		autor.setEmail031(resultSet.getString("EMAIL"));
		autor.setTelmovil031(resultSet.getString("TELEFONO"));
		autor.setNombreApellidos(resultSet.getString("PERSONA"));
		documentosExpediente.setAutor(autor);

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setIdDocInsertado(resultSet.getBigDecimal(DBConstants.IDDOCORIGINAL));
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERODOCORIG));
		fichero.setNombre(resultSet.getString(DBConstants.NOMBREFICHERO));
		fichero.setContentType(resultSet.getString(DBConstants.CONTENTTYPE));
		fichero.setTamano(resultSet.getLong(DBConstants.TAMANODOC));
		listaFicheros.add(fichero);

		FicheroDocExp parejaFichero = new FicheroDocExp();
		parejaFichero.setIdDocInsertado(resultSet.getBigDecimal(DBConstants.IDDOCPAREJAORIG));
		parejaFichero.setOid(resultSet.getString(DBConstants.OIDFICHERODOCPAREJAORIG));
		parejaFichero.setNombre(resultSet.getString(DBConstants.NOMBREFICHEROPAREJAORIG));
		parejaFichero.setContentType(resultSet.getString(DBConstants.CONTENTTYPE2));
		parejaFichero.setTamano(resultSet.getLong(DBConstants.TAMANODOC2));
		listaFicheros.add(parejaFichero);

		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		return documentoTarea;
	}

}
