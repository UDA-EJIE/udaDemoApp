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
import com.ejie.aa79b.model.Tareas;

public class DocumentoTareasRevEntregaClteRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Tareas tareas = new Tareas();
		tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));

		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal(DBConstants.IDDOCORIGINAL));
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULODOCORIGINAL));
		documentosExpediente.setClaseDocumento(resultSet.getLong(DBConstants.CLASEDOCUMENTODOCORIG));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERODOCORIG));
		fichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADODOCORIG));
		fichero.setExtension(resultSet.getString(DBConstants.EXTENSIONDOC));
		fichero.setNombre(resultSet.getString("NOMBREDOCORIGINAL"));
		listaFicheros.add(fichero);

		FicheroDocExp parejaFichero = new FicheroDocExp();
		parejaFichero.setOid(resultSet.getString(DBConstants.OIDFICHERODOCPAREJAORIG));
		parejaFichero.setIdDocRel(resultSet.getBigDecimal(DBConstants.IDDOCPAREJAORIG));
		parejaFichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTDOCPAREJAORIG));
		parejaFichero.setNombre(resultSet.getString("NOMBREDOCPAREJAORIG"));
		listaFicheros.add(parejaFichero);

		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoFinal = new DocumentoTareaAdjunto();
		documentoFinal.setIdFichero(resultSet.getBigDecimal(DBConstants.IDDOCFINAL));
		documentoFinal.setTitulo(resultSet.getString(DBConstants.TITULODOCFINAL));
		documentoFinal.setOid(resultSet.getString(DBConstants.OIDFICHERODOCFINAL));
		documentoFinal.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTDOCFINAL));
		documentoFinal.setNombre(resultSet.getString("NOMBREFICHERODOCFINAL"));
		// Se utilizar el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTarea.setDocumentoAdjunto(documentoFinal);

		DocumentoTareaAdjunto justificante = new DocumentoTareaAdjunto();
		justificante.setIdFichero(resultSet.getBigDecimal(DBConstants.IDDOCJUST));
		justificante.setTitulo(resultSet.getString(DBConstants.NOMBREFICHERODOCJUST));
		justificante.setOid(resultSet.getString(DBConstants.OIDFICHERODOCJUST));
		justificante.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTDOCJUST));
		justificante.setIndVisible(resultSet.getString(DBConstants.INDVISIBLEDOCJUST));
		justificante.setNombre(resultSet.getString("NOMBREFICHERODOCJUST"));
		documentoTarea.setDocumentoJustificante(justificante);

		DocumentoTareaAdjunto documentoFinalFirmado = new DocumentoTareaAdjunto();
		documentoFinalFirmado.setIdFichero(resultSet.getBigDecimal("IDDOCFINALFIRMADO"));
		documentoFinalFirmado.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
		documentoFinalFirmado.setOid(resultSet.getString("OIDFICHEROFINALFIRMADO"));
		documentoFinalFirmado.setNombre(resultSet.getString("NOMBREFICHEROFINALFIRMADO"));
		// Se utiliza el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTarea.setDocumentoFinalFirmado(documentoFinalFirmado);

		DocumentoTareaAdjunto documentoOriginalFirmado = new DocumentoTareaAdjunto();
		documentoOriginalFirmado.setIdFichero(resultSet.getBigDecimal("IDDOCORIGFIRMADO"));
		documentoOriginalFirmado.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
		documentoOriginalFirmado.setOid(resultSet.getString("OIDFICHEROORIGFIRMADO"));

		documentoTarea.setDocumentoOriginalFirmado(documentoOriginalFirmado);

		DocumentoTareaAdjunto documentoFinalOriginal = new DocumentoTareaAdjunto();
		documentoFinalOriginal.setIdFichero(resultSet.getBigDecimal("IDDOCFINALORIG"));
		documentoTarea.setDocumentoFinalOriginal(documentoFinalOriginal);

		tareas.setDocumentoTarea(documentoTarea);

		return tareas;
	}

}
