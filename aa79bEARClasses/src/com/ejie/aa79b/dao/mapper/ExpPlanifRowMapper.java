package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;

public class ExpPlanifRowMapper implements RowMapper<ExpedientePlanificacion> {

	@Override
	public ExpedientePlanificacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ExpedientePlanificacion expPlanificacion = new ExpedientePlanificacion();
		final QueryUtils queryUtils = new QueryUtils();
		expPlanificacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expPlanificacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expPlanificacion.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expPlanificacion.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expPlanificacion.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expPlanificacion.setTitulo(resultSet.getString(DBConstants.TITULO));

		// bitacora expediente
		BitacoraExpediente bitacora = new BitacoraExpediente();

		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(resultSet.getLong(DBConstants.IDESTADOEXP));
		bitacora.setEstadoExp(estado);

		FasesExpediente fase = new FasesExpediente();
		fase.setId(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		fase.setDescEs(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCES));
		fase.setDescEu(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCEU));
		fase.setDescAbrEs(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCABRES));
		fase.setDescAbrEu(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCABREU));
		bitacora.setFaseExp(fase);
		expPlanificacion.setBitacoraExpediente(bitacora);

		if (!(TipoExpedienteEnum.INTERPRETACION.getValue().equals(expPlanificacion.getIdTipoExpediente()))) {
			// TradRev
			ExpedienteTradRev tradRev = new ExpedienteTradRev();
			tradRev.setIndTareasEntrega(resultSet.getString(DBConstants.TIENETAREASENTREGA));
			tradRev.setIndUrgente(resultSet.getString(DBConstants.ESURGENTE));
			expPlanificacion.setPrioridad(resultSet.getString(DBConstants.PRIORITARIO));
			expPlanificacion.setPrioridadDescEs(resultSet.getString(DBConstants.PRIORITARIODESCES));
			expPlanificacion.setPrioridadDescEu(resultSet.getString(DBConstants.PRIORITARIODESCEU));
			tradRev.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
			tradRev.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
			tradRev.setNumTotalPalIzo(resultSet.getInt(DBConstants.NUMTOTALPALIZO));
			tradRev.setIndPrevistoBopv(resultSet.getString("INDPREVISTOBOPV"));
			tradRev.setIndPublicarBopv(resultSet.getString("INDPUBLICARBOPV"));
			DatosTareaTrados tradosExp = new DatosTareaTrados();
			tradosExp.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPALTRADOS));
			tradosExp.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084090));
			tradosExp.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594090));
			tradosExp.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100090));
			tradosExp.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599090));
			tradosExp.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100090));
			tradRev.setTradosExp(tradosExp);

			tradRev.setIndPresupuesto(resultSet.getString(DBConstants.INDPRESUPUESTO));
			tradRev.setIdEstadoPresupuesto(resultSet.getInt(DBConstants.IDESTADOPRESUPUESTO));
			tradRev.setEstadopresupuestoDescEs(resultSet.getString(DBConstants.ESTADOPRESUPUESTODESCES));
			tradRev.setEstadoPresupuestoDescEu(resultSet.getString(DBConstants.ESTADOPRESUPUESTODESCEU));
			tradRev.setFechaLimitePresupuesto(resultSet.getDate(DBConstants.FECHALIMITEPRESUPUESTO));
			tradRev.setReqProyectoTrados(resultSet.getInt(DBConstants.REQUIERETRADOS));
			expPlanificacion.setResponsable(resultSet.getString(DBConstants.RESPONSABLE));
			expPlanificacion.setProyectoTradosEstadoAsignacion(resultSet.getInt(DBConstants.ESTADOASIGID));
			expPlanificacion.setProyectoTradosEstadoEjecucion(resultSet.getInt(DBConstants.ESTADOEJECID));
			expPlanificacion.setExpedienteTradRev(tradRev);
			// desc requiereproyectotrados
			queryUtils.obtenerRequiereProyectoTradosDesc(expPlanificacion);
			// desc reqPresupuesto
			queryUtils.obtenerRequierePresupuestoDesc(expPlanificacion);
			expPlanificacion.setIdEstadoNegociacion(resultSet.getInt("INDESTADONEGOCIACION"));

			Lotes lotes = new Lotes();
			lotes.setNombreLote(resultSet.getString("NOMBRELOTE"));
			expPlanificacion.setLotes(lotes);
		} else {
			// Interpretacion
			expPlanificacion.setFechaPrevistaInicio(resultSet.getDate(DBConstants.FECHAINICIOPREVISTA));
			expPlanificacion.setHoraPrevistaInicio(resultSet.getString(DBConstants.HORAINICIOPREVISTA));
		}

		// fechafinprevista
		expPlanificacion.setFechaPrevistaEntrega(resultSet.getDate(DBConstants.FECHAFINPREVISTA));
		expPlanificacion.setHoraPrevistaEntrega(resultSet.getString(DBConstants.HORAFINPREVISTA));

		// gestor
		Gestor gestorExpediente = new Gestor();
		Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setDni(resultSet.getString(DBConstants.DNIGESTOR));
		solicitanteAux.setPreDni(resultSet.getString(DBConstants.PREDNIGESTOR));
		solicitanteAux.setSufDni(resultSet.getString(DBConstants.SUFDNIGESTOR));
		solicitanteAux.setDniCompleto(resultSet.getString(DBConstants.DNICOMPLETOGESTOR));
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString(DBConstants.INDVIPGESTOR));
		solicitanteAux.setGestorExpedientesVIPDescEs(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCES));
		solicitanteAux.setGestorExpedientesVIPDescEu(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCEU));
		solicitanteAux.setNombre(resultSet.getString(DBConstants.NOMBREGESTOR));
		solicitanteAux.setApellido1(resultSet.getString(DBConstants.APEL1GESTOR));
		solicitanteAux.setApellido2(resultSet.getString(DBConstants.APEL2GESTOR));
		gestorExpediente.setSolicitante(solicitanteAux);
		Entidad entidadAux = new Entidad();
		entidadAux.setTipo(resultSet.getString(DBConstants.TIPOENTIDADGESTOR));
		entidadAux.setTipoDesc(Utils.getTipoEntidadDescLabel(entidadAux.getTipo()));
		entidadAux.setCodigo(resultSet.getInt(DBConstants.IDENTIDADGESTOR));
		entidadAux.setCif(resultSet.getString(DBConstants.CIFENTIDADGESTOR));
		entidadAux.setEstado(resultSet.getString(DBConstants.ESTADOENTIDADGESTOR));
		entidadAux.setDescEs(resultSet.getString(DBConstants.DESCENTIDADESGESTOR));
		entidadAux.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEUGESTOR));
		entidadAux.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPENTIDADESGESTOR));
		entidadAux.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPENTIDADEUGESTOR));
		gestorExpediente.setEntidad(entidadAux);
		expPlanificacion.setGestorExpediente(gestorExpediente);

		// tareas
		// desc formatter en jsp
		expPlanificacion.setIdEstadoTareasAsignadas(resultSet.getInt(DBConstants.IDESTADOTAREASASIGNADAS));

		expPlanificacion.setGrupoTrabajo(resultSet.getString("GRUPOTRABAJO"));

		// obtenemos la descripcion del estado de asignacion de las tareas
		queryUtils.obtenerEstadoTareasDesc(expPlanificacion);
		Persona asignador = new Persona();
		asignador.setDni(resultSet.getString(DBConstants.DNIASIGNADOR));
		asignador.setNombre(resultSet.getString(DBConstants.NOMBREASIGNADOR));
		asignador.setApellido1(resultSet.getString(DBConstants.APEL1ASIGNADOR));
		asignador.setApellido2(resultSet.getString(DBConstants.APEL2ASIGNADOR));
		expPlanificacion.setAsignador(asignador);

		Persona tecnico = new Persona();
		tecnico.setDni(resultSet.getString(DBConstants.DNITECNICO));
		tecnico.setNombre(resultSet.getString("NOMBRETECNICO"));
		tecnico.setApellido1(resultSet.getString("APEL1TECNICO"));
		tecnico.setApellido2(resultSet.getString("APEL2TECNICO"));
		expPlanificacion.setTecnico(tecnico);

		return expPlanificacion;
	}

}
