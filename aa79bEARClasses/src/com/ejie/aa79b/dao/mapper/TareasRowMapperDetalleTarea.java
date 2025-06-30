package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.EmpresasProveedoras;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.aa79b.model.TiposTarea;

public class TareasRowMapperDetalleTarea implements RowMapper<Tareas> {

	@Override()
	public Tareas mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Tareas tareas = new Tareas();
		tareas.setAnyo(rs.getLong(DBConstants.ANYO));
		tareas.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		tareas.setIdTareaRel(rs.getBigDecimal(DBConstants.IDTAREAREL));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);
		tareas.setFechaIni(rs.getDate(DBConstants.FECHAINI));
		tareas.setHoraIni(rs.getString(DBConstants.HORAINI));
		tareas.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		tareas.setHoraFin(rs.getString(DBConstants.HORAFIN));
		tareas.setRecursoAsignacion(rs.getString(DBConstants.RECURSOASIGNACION));

		TiposRevision tipoRevision = new TiposRevision();
		tipoRevision.setId018(rs.getLong("IDTIPOREVISION"));
		tipoRevision.setDescEs018(rs.getString("TIPOREVISIONDESCES"));
		tipoRevision.setDescEu018(rs.getString("TIPOREVISIONDESCEU"));
		tareas.setTipoRevision(tipoRevision);

		Lotes lotes = new Lotes();
		lotes.setNombreLote(rs.getString(DBConstants.NOMBRELOTE));
		EmpresasProveedoras empresaProveedora = new EmpresasProveedoras();
		empresaProveedora.setCif(rs.getString(DBConstants.CIFEMPRESA));
		empresaProveedora.setDescAmpEu(rs.getString(DBConstants.DESCAMPEMPEU));
		empresaProveedora.setDescAmpEs(rs.getString(DBConstants.DESCAMPEMPES));
		empresaProveedora.setDescEu(rs.getString(DBConstants.DESCEMPEU));
		empresaProveedora.setDescEs(rs.getString(DBConstants.DESCEMPES));
		lotes.setEmpresasProveedoras(empresaProveedora);
		tareas.setLotes(lotes);
		tareas.setEstadoAsignado(rs.getInt(DBConstants.ESTADOASIGID));
		tareas.setEstadoAsignadoDesc(rs.getString(DBConstants.ESTADOASIGNACIONDESC));
		tareas.setEstadoEjecucion(rs.getInt(DBConstants.ESTADOEJECID));
		tareas.setEstadoEjecucionDesc(rs.getString(DBConstants.ESTADOEJECUCIONDESC));
		tareas.setObservaciones(rs.getString(DBConstants.OBSERV));
		PersonalIZO personaAsignador = new PersonalIZO();
		personaAsignador.setDni(rs.getString(DBConstants.DNIASIGNADOR));
		tareas.setPersonaAsignador(personaAsignador);
		tareas.setFechaAsignacion(rs.getDate(DBConstants.FECHAASIGNACION));
		tareas.setHoraAsignacion(rs.getString(DBConstants.HORAASIGNACION));
		tareas.setFechaAceptacion(rs.getDate(DBConstants.FECHAACEPTACION));
		tareas.setIndRetrasada(rs.getString(DBConstants.INDRETRASADA));
		tareas.setIndConfidencialExp(rs.getString(DBConstants.INDCONFIDENCIAL));
        tareas.setIndMostrarNotasATrad(rs.getString(DBConstants.IND_MOSTRAR_NOTAS_A_TRAD));
		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		final Date fechaEjecucion = rs.getTimestamp(DBConstants.FECHAEJECUCION);

		if (fechaEjecucion != null) {
			ejecucionTareas.setFechaEjecucion(fechaEjecucion);
		}
		ejecucionTareas.setHoraEjecucion(rs.getString("HORAEJECUCION"));
		tareas.setEjecucionTareas(ejecucionTareas);

		return tareas;
	}

}
