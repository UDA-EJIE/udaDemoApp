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
import com.ejie.aa79b.model.Tareas;

public class DocumentoTareasTraduccionFirmaRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Tareas tareaT = new Tareas();
		tareaT.setIdTarea(resultSet.getBigDecimal("ID_TAREA_083"));
		DocumentoTarea documentoTareaT = new DocumentoTarea();

		documentoTareaT.setIdTarea(resultSet.getBigDecimal("ID_TAREA_083"));

		DocumentosExpediente documentosExpedienteT = new DocumentosExpediente();
		documentosExpedienteT.setIdDoc(resultSet.getBigDecimal("ID_DOC_056"));
		documentosExpedienteT.setTitulo(resultSet.getString("TITULO_056"));
		documentosExpedienteT.setClaseDocumento(resultSet.getLong("CLASE_DOCUMENTO_056"));

		List<FicheroDocExp> listaFicherosT = new ArrayList<FicheroDocExp>();
		FicheroDocExp ficheroT = new FicheroDocExp();
		ficheroT.setOid(resultSet.getString("OID_FICHERO_056"));
		ficheroT.setEncriptado(resultSet.getString("IND_ENCRIPTADO_056"));
		ficheroT.setExtension(resultSet.getString("EXTENSION_DOC_056"));
		listaFicherosT.add(ficheroT);
		documentosExpedienteT.setFicheros(listaFicherosT);
		documentoTareaT.setDocumentoOriginal(documentosExpedienteT);

		DocumentoTareaAdjunto documentoTareaAdjuntoT = new DocumentoTareaAdjunto();
		documentoTareaAdjuntoT.setIdFichero(resultSet.getBigDecimal("ID_FICHERO_TRADUCIDO_087"));
		documentoTareaAdjuntoT.setEncriptado(resultSet.getString("IND_ENCRIPTADO_088"));
		documentoTareaAdjuntoT.setOid(resultSet.getString("OID_FICHERO_088"));
		documentoTareaT.setDocumentoAdjunto(documentoTareaAdjuntoT);

		DocumentoTareaAdjunto documentoFinalFirmadoT = new DocumentoTareaAdjunto();
		documentoFinalFirmadoT.setIdFichero(resultSet.getBigDecimal("IDDOCFINALFIRMADO"));
		documentoFinalFirmadoT.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
		documentoFinalFirmadoT.setOid(resultSet.getString("OIDFICHEROFINALFIRMADO"));
		documentoFinalFirmadoT.setNombre(resultSet.getString("NOMBREFICHEROFINALFIRMADO"));
		// Se utiliza el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTareaT.setDocumentoFinalFirmado(documentoFinalFirmadoT);

		DocumentoTareaAdjunto documentoOriginalFirmadoT = new DocumentoTareaAdjunto();
		documentoOriginalFirmadoT.setOid(resultSet.getString("OIDFICHEROORIGFIRMADO"));
		documentoOriginalFirmadoT.setIdFichero(resultSet.getBigDecimal("IDDOCORIGFIRMADO"));
		documentoOriginalFirmadoT.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
		// Se utiliza el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTareaT.setDocumentoOriginalFirmado(documentoOriginalFirmadoT);

		tareaT.setDocumentoTarea(documentoTareaT);
		return tareaT;
	}

}
