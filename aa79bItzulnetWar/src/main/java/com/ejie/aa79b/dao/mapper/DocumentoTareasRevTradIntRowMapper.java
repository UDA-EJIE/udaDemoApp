package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class DocumentoTareasRevTradIntRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet rs, int arg1) throws SQLException {
		Tareas tareas = new Tareas();
		tareas.setIdTarea(rs.getBigDecimal("IDTAREA"));

		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setDescEs015(rs.getString("TIPOTAREAES"));
		tiposTarea.setDescEu015(rs.getString("TIPOTAREAEU"));
		tareas.setTipoTarea(tiposTarea);

		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		ejecucionTareas.setFechaEjecucion(rs.getDate("FECHAEJECUCIONTAREA"));
		ejecucionTareas.setIndObservaciones(rs.getString("INDOBSERVACIONES"));
		tareas.setEjecucionTareas(ejecucionTareas);
		tareas.setGroupByText(rs.getString("GROUPBYTEXT"));
		tareas.setIdTareaAgrupacion(rs.getBigDecimal("IDTAREAAGRUPACION"));

		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(rs.getBigDecimal("IDTAREA"));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(rs.getBigDecimal("IDDOCORIGINAL"));
		documentosExpediente.setTitulo(rs.getString("TITULODOCORIGINAL"));
		documentosExpediente.setClaseDocumento(rs.getLong("CLASEDOCUMENTODOCORIG"));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(rs.getString("OIDFICHERODOCORIG"));
		fichero.setEncriptado(rs.getString("INDENCRIPTADODOCORIG"));
		fichero.setExtension(rs.getString("EXTENSIONDOC"));
		fichero.setNombre(rs.getString("NOMBREDOCORIGINAL"));
		listaFicheros.add(fichero);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaTraducido = new DocumentoTareaAdjunto();
		documentoTareaTraducido.setIdFichero(rs.getBigDecimal("IDDOCTRADUCIDO"));
		documentoTareaTraducido.setTitulo(rs.getString("TITULODOCTRADUCIDO"));
		documentoTareaTraducido.setOid(rs.getString("OIDFICHERODOCTRAD"));
		documentoTareaTraducido.setEncriptado(rs.getString("INDENCRIPTADODOCTRAD"));
		documentoTareaTraducido.setNombre(rs.getString("NOMBREDOCTRADUCIDO"));
		// Se utilizar el objeto documento adjunto para volcar los datos
		// asociados a un documento traducido
		documentoTarea.setDocumentoAdjunto(documentoTareaTraducido);

		DocumentoTareaAdjunto documentoTareaRevisado = new DocumentoTareaAdjunto();
		documentoTareaRevisado.setIdFichero(rs.getBigDecimal("IDDOCREVISADO"));
		documentoTareaRevisado.setTitulo(rs.getString("TITULODOCREVISADO"));
		documentoTareaRevisado.setOid(rs.getString("OIDFICHERODOCREV"));
		documentoTareaRevisado.setEncriptado(rs.getString("INDENCRIPTADODOCREV"));
		documentoTareaRevisado.setNombre(rs.getString("NOMBREDOCREV"));
		// Se utiliza el objeto documentoJustificante para volcar los datos
		// asociados a un documento revisado
		documentoTarea.setDocumentoJustificante(documentoTareaRevisado);

		tareas.setDocumentoTarea(documentoTarea);

		return tareas;
	}

}
