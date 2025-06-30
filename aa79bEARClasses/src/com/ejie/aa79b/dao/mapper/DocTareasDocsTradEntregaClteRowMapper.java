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
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.EmpresasProveedoras;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class DocTareasDocsTradEntregaClteRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Tareas tareas = new Tareas();
		tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		tareas.setIdTareaAgrupacion(resultSet.getBigDecimal(DBConstants.IDTAREAAGRUPACION));

		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setDescEs015(resultSet.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(resultSet.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);

		Lotes lotes = new Lotes();
		lotes.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));

		EmpresasProveedoras empresasProveedoras = new EmpresasProveedoras();
		empresasProveedoras.setDescEs(resultSet.getString(DBConstants.DESCES));
		empresasProveedoras.setDescEu(resultSet.getString(DBConstants.DESCEU));
		lotes.setEmpresasProveedoras(empresasProveedoras);

		tareas.setLotes(lotes);

		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		ejecucionTareas.setFechaEjecucion(resultSet.getDate(DBConstants.FECHAEJECUCIONTAREA));
		ejecucionTareas.setIndObservaciones(resultSet.getString(DBConstants.INDOBSERVACIONES));
		tareas.setEjecucionTareas(ejecucionTareas);
		tareas.setGroupByText(resultSet.getString(DBConstants.GROUPBYTEXT));

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
		documentoFinal.setNombre(resultSet.getString("NOMBREDOCFINAL"));
		// Se utilizar el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTarea.setDocumentoAdjunto(documentoFinal);

		DocumentoTareaAdjunto justificante = new DocumentoTareaAdjunto();
		justificante.setIdFichero(resultSet.getBigDecimal(DBConstants.IDDOCJUST));
		justificante.setTitulo(resultSet.getString(DBConstants.TITULODOCJUST));
		justificante.setOid(resultSet.getString(DBConstants.OIDFICHERODOCJUST));
		justificante.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTDOCJUST));
		justificante.setIndVisible(resultSet.getString(DBConstants.INDVISIBLEDOCJUST));
		justificante.setNombre(resultSet.getString("NOMBREDOCJUST"));
		documentoTarea.setDocumentoJustificante(justificante);

		tareas.setDocumentoTarea(documentoTarea);

		return tareas;
	}

}
