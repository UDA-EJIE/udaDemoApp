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

public class DocumentoTareasRevTradExtRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Tareas tareas = new Tareas();
		tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));

		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setDescEs015(resultSet.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(resultSet.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);

		Lotes lotes = new Lotes();
		lotes.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));

		EmpresasProveedoras empresasProveedoras = new EmpresasProveedoras();
		empresasProveedoras.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPES));
		empresasProveedoras.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPEU));
		empresasProveedoras.setDescEs(resultSet.getString(DBConstants.DESCES));
		empresasProveedoras.setDescEu(resultSet.getString(DBConstants.DESCEU));
		lotes.setEmpresasProveedoras(empresasProveedoras);

		tareas.setLotes(lotes);

		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		ejecucionTareas.setFechaEjecucion(resultSet.getDate(DBConstants.FECHAEJECUCIONTAREA));
		ejecucionTareas.setIndObservaciones(resultSet.getString(DBConstants.INDOBSERVACIONES));
		tareas.setEjecucionTareas(ejecucionTareas);
		tareas.setGroupByText(resultSet.getString(DBConstants.GROUPBYTEXT));
		tareas.setIdTareaAgrupacion(resultSet.getBigDecimal(DBConstants.IDTAREAAGRUPACION));

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
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaTraducido = new DocumentoTareaAdjunto();
		documentoTareaTraducido.setIdFichero(resultSet.getBigDecimal("IDDOCTRADUCIDO"));
		documentoTareaTraducido.setTitulo(resultSet.getString("TITULODOCTRADUCIDO"));
		documentoTareaTraducido.setOid(resultSet.getString("OIDFICHERODOCTRAD"));
		documentoTareaTraducido.setEncriptado(resultSet.getString("INDENCRIPTADODOCTRAD"));
		documentoTareaTraducido.setNombre(resultSet.getString("NOMBREDOCTRADUCIDO"));
		// Se utilizar el objeto documento adjunto para volcar los datos
		// asociados a un documento traducido
		documentoTarea.setDocumentoAdjunto(documentoTareaTraducido);

		DocumentoTareaAdjunto documentoTareaRevisado = new DocumentoTareaAdjunto();
		documentoTareaRevisado.setIdFichero(resultSet.getBigDecimal("IDDOCREVISADO"));
		documentoTareaRevisado.setTitulo(resultSet.getString("TITULODOCREVISADO"));
		documentoTareaRevisado.setOid(resultSet.getString("OIDFICHERODOCREV"));
		documentoTareaRevisado.setEncriptado(resultSet.getString("INDENCRIPTADODOCREV"));
		documentoTareaRevisado.setNombre(resultSet.getString("NOMBREFICHEROREVISADO"));
		// Se utiliza el objeto documentoJustificante para volcar los datos
		// asociados a un documento revisado
		documentoTarea.setDocumentoJustificante(documentoTareaRevisado);

		tareas.setDocumentoTarea(documentoTarea);

		return tareas;
	}

}
