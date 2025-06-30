package com.ejie.aa79b.dao;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.AsignadoAProveedoresRowMapper;
import com.ejie.aa79b.dao.mapper.ResumenGraficasRowMapper;
import com.ejie.aa79b.dao.mapper.TareasCorreccionProvRowMapper;
import com.ejie.aa79b.dao.mapper.TareasRowMapper;
import com.ejie.aa79b.dao.mapper.TareasRowMapperConfTareas;
import com.ejie.aa79b.dao.mapper.TareasRowMapperDetalleTarea;
import com.ejie.aa79b.dao.mapper.TareasRowMapperTareaIntPagoProveed;
import com.ejie.aa79b.dao.mapper.TareasRowMapperTareasAEliminar;
import com.ejie.aa79b.dao.mapper.TareasSubDocRowMapper;
import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EntradaDatosSolicitud;
import com.ejie.aa79b.model.EstudioEstimado;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FechaHoraIniFin;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.OrigenTareaNoConformidad;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.TareaIntPagoProveed;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasAsignado;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.PlanifExpedientesEnum;
import com.ejie.aa79b.model.enums.PlanifTareasEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.PlanificacionExpedienteUtils;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

@Repository()
@Transactional()
public class TareasDaoImpl extends GenericoDaoImpl<Tareas> implements TareasDao {
	public static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.IDTAREA };
	public static final String[] ORDER_BY_WHITE_LIST_ASIGNADO = new String[] { "IDTIPOTAREA", "NOMBRELOTE" };

	private static final Logger LOGGER = LoggerFactory.getLogger(TareasDaoImpl.class);

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String NUM_EXP_081 = "t1.NUM_EXP_081";
	protected static final String ID_TIPO_TAREA_081 = "t1.ID_TIPO_TAREA_081";
	protected static final String ESTADO_EJECUCION_081 = "t1.ESTADO_EJECUCION_081";
	protected static final String ANYO_081 = "t1.ANYO_081";
	protected static final String ID_TAREA_081 = "ID_TAREA_081";
	protected static final String AA79B81S01_SET = " AA79B81S01 SET ";
	protected static final String SELECTALLFROM_ROWNUM = "SELECT * FROM (SELECT rownum rnum, a.*  FROM (";
	protected static final String INTERROGACIONES_POR_4 = "?,?,?,?,";
	protected static final String SELECT_IDTAREA = DaoConstants.SELECT + DaoConstants.T3_MINUSCULA_PUNTO
			+ DBConstants.ID_TAREA_081 + DaoConstants.BLANK + DBConstants.IDTAREA + DaoConstants.BLANK;
	protected static final String FROM_TABLA_81 = DaoConstants.FROM + DBConstants.TABLA_81 + DaoConstants.BLANK
			+ DaoConstants.T3_MINUSCULA + DaoConstants.BLANK;
	protected static final String WHERE_IDTAREA_81 = DaoConstants.WHERE + DaoConstants.T3_MINUSCULA_PUNTO
			+ DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION;
	protected static final String LEFT_JOIN_TABLA_81_15 = DaoConstants.LEFT_JOIN + DBConstants.TABLA_15
			+ DaoConstants.BLANK + DaoConstants.T5_MINUSCULA + DaoConstants.BLANK + DaoConstants.ON
			+ DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL
			+ DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_015 + DaoConstants.BLANK;

	public TareasDaoImpl() {
		super(Tareas.class);
	}

	private RowMapper<Tareas> rwMap = new TareasRowMapperConfTareas();

	private RowMapper<TareasAsignado> rwMapAsignadoAProv = new AsignadoAProveedoresRowMapper();

	private RowMapper<Tareas> rwMapConfTareas = new TareasRowMapperConfTareas();

	private RowMapper<Tareas> rwMapTareasAEliminar = new TareasRowMapperTareasAEliminar();

	private RowMapper<TareaIntPagoProveed> rwMapTareaIntPagProveed = new TareasRowMapperTareaIntPagoProveed();

	private RowMapper<String> rwMapRecursoAsig = new RowMapper<String>() {
		@Override()
		public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return resultSet.getString("DNI_RECURSO");
		}
	};

	private RowMapper<EstudioEstimado> rwMapEstudio = new RowMapper<EstudioEstimado>() {
		@Override()
		public EstudioEstimado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			EstudioEstimado estudioEstimado = new EstudioEstimado();
			Locale locale = LocaleContextHolder.getLocale();

			String recurso;
			if (TipoRecursoEnum.INTERNO.getValue().equals(resultSet.getString("RECURSO"))) {
				recurso = TareasDaoImpl.this.msg.getMessage(TipoRecursoEnum.INTERNO.getLabel(), null, locale);
			} else {
				recurso = TareasDaoImpl.this.msg.getMessage(TipoRecursoEnum.EXTERNO.getLabel(), null, locale);
			}
			estudioEstimado.setRecurso(recurso);
			estudioEstimado.setNumRecursos(resultSet.getInt("NUM_RECURSOS"));
			estudioEstimado.setCosteHoras(resultSet.getString("COSTE_HORAS"));
			estudioEstimado.setCosteHorasRecurso(resultSet.getString("COSTE_HORAS_RECURSO"));
			return estudioEstimado;
		}
	};

	private RowMapper<Tareas> rwMapPK = new RowMapper<Tareas>() {
		@Override()
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			return tareas;
		}
	};

	private RowMapper<Lotes> rwMapLote = new RowMapper<Lotes>() {
		@Override()
		public Lotes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Lotes lotes = new Lotes();
			lotes.setIdLote(resultSet.getInt(DBConstants.IDLOTE));
			return lotes;
		}
	};
	private RowMapper<FechaHoraIniFin> fechaIniFinRwMap = new RowMapper<FechaHoraIniFin>() {
		@Override()
		public FechaHoraIniFin mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			FechaHoraIniFin fechas = new FechaHoraIniFin();
			fechas.setFechaIni(resultSet.getDate("FECHA_INI"));
			fechas.setHoraIni(resultSet.getString("HORA_INI"));
			fechas.setFechaFin(resultSet.getDate("FECHA_FIN"));
			fechas.setHoraFin(resultSet.getString("HORA_FIN"));
			return fechas;
		}
	};

	private RowMapper<Tareas> rwMapDetalleTarea = new TareasRowMapperDetalleTarea();

	private RowMapper<TiposRevision> rwMapTipoRevisionTareaRel = new RowMapper<TiposRevision>() {
		@Override()
		public TiposRevision mapRow(ResultSet rs, int rowNum) throws SQLException {
			TiposRevision tipoRevision = new TiposRevision();
			tipoRevision.setId018(rs.getLong("IDTIPOREVISION"));
			tipoRevision.setDescEs018(rs.getString("TIPOREVISIONDESCES"));
			tipoRevision.setDescEu018(rs.getString("TIPOREVISIONDESCEU"));
			return tipoRevision;
		}
	};

	private RowMapper<Tareas> rwMapDatosTareaTrados = new RowMapper<Tareas>() {
		@Override()
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			tareas.setEstadoEjecucion(resultSet.getInt(DBConstants.ESTADOEJECID));
			return tareas;
		}
	};

	private RowMapper<Tareas> rwMapUltimasInter = new RowMapper<Tareas>() {
		@Override()
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			tareas.setFechaIni(resultSet.getDate(DBConstants.FECHAINI));
			tareas.setHoraIni(resultSet.getString(DBConstants.HORAINI));
			tareas.setFechaFin(resultSet.getDate(DBConstants.FECHAFIN));
			tareas.setHoraFin(resultSet.getString(DBConstants.HORAFIN));
			tareas.setHorasTarea(resultSet.getString(DBConstants.HORASTAREA));
			PersonalIZO persona = new PersonalIZO();
			persona.setNombre(resultSet.getString(DBConstants.NOMBRE));
			persona.setApellido1(resultSet.getString(DBConstants.APEL1));
			persona.setApellido2(resultSet.getString(DBConstants.APEL2));
			tareas.setPersonaAsignador(persona);
			return tareas;
		}
	};

	private RowMapper<Tareas> rwMapTareas = new TareasRowMapper();

	private RowMapper<Tareas> rwMapTareaTrados = new RowMapper<Tareas>() {
		@Override()
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			tareas.setAnyo(resultSet.getLong(DBConstants.ANYO));
			tareas.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return tareas;
		}
	};

	private RowMapper<Tareas> rwMapSubDoc = new TareasSubDocRowMapper();

	private RowMapper<Tareas> rwMapCorreccionesProv = new TareasCorreccionProvRowMapper();

	public RowMapper<FechaHoraIniFin> getFechaIniFinRwMap() {
		return this.fechaIniFinRwMap;
	}

	private RowMapper<OrigenTareaNoConformidad> rwMapOrigenTareaNoConformidad = new RowMapper<OrigenTareaNoConformidad>() {
		@Override()
		public OrigenTareaNoConformidad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			OrigenTareaNoConformidad oTNC = new OrigenTareaNoConformidad();
			oTNC.setIdTareaRelacionada(resultSet.getBigDecimal("IDTAREA"));
			TiposTarea tipoTarea = new TiposTarea(resultSet.getLong("IDTIPOTAREA"));
			oTNC.setTipoTarea(tipoTarea);
			return oTNC;
		}
	};

	private RowMapper<PersonalIZO> rwMapTraductorAsignado = new RowMapper<PersonalIZO>() {
		@Override()
		public PersonalIZO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			PersonalIZO personaAsignada = new PersonalIZO();
			personaAsignada.setDni(resultSet.getString("DNI"));
			Entidad entidad = new Entidad();
			entidad.setTipo(resultSet.getString("TIPOENTIDAD"));
			entidad.setCodigo(resultSet.getInt("IDENTIDAD"));
			personaAsignada.setEntidad(entidad);
			return personaAsignada;
		}
	};

	private RowMapper<Tareas> rwMapTareaEstado = new RowMapper<Tareas>() {
		@Override()
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			tareas.setAnyo(resultSet.getLong(DBConstants.ANYO));
			tareas.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			tareas.setEstadoEjecucion(resultSet.getInt(DBConstants.ESTADOEJECID));
			return tareas;
		}
	};

	private RowMapper<Tareas> rwMapAsignadoTareaRevisionAnterior = new RowMapper<Tareas>() {
		@Override()
		public Tareas mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tareas tarea = new Tareas();
			tarea.setIdTarea(rs.getBigDecimal("IDTAREA"));
			TiposTarea tipoTarea = new TiposTarea();
			tipoTarea.setId015(rs.getLong("IDTIPOTAREA"));
			tarea.setTipoTarea(tipoTarea);
			tarea.setAnyo(rs.getLong("ANYO"));
			tarea.setNumExp(rs.getInt("NUMEXP"));
			tarea.setIdTarea(rs.getBigDecimal("IDTAREA"));
			PersonalIZO personaAsignada = new PersonalIZO();
			personaAsignada.setDni(rs.getString("DNI"));
			tarea.setPersonaAsignada(personaAsignada);
			tarea.setEstadoAsignado(rs.getInt("ESTADOASIGNACION"));
			tarea.setEstadoEjecucion(rs.getInt("ESTADOEJECUCION"));
			return tarea;
		}
	};

	@Override()
	public void removeTareas(List<String> listSelectedIds) {

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			// Eliminar registros de la tabla 81 y en cascada del resto
			StringBuilder query = new StringBuilder();
			query.append(DaoConstants.DELETE);
			query.append(DaoConstants.FROM);
			query.append(DBConstants.TABLA_81);
			query.append(DaoConstants.BLANK);
			query.append(QueryUtils.getWhereTareasAEliminar(listSelectedIds));

			this.getJdbcTemplate().update(query.toString());
		}

	}

	@Override()
	public void removeTareasDependientes(List<Tareas> listTareas) {
		if (listTareas != null && !listTareas.isEmpty()) {
			// Eliminar registros de la tabla 81 y del resto en cascada
			eliminarTareaDependiente(listTareas, DBConstants.TABLA_081, DaoConstants.T1_MINUSCULA,
					DBConstants.ID_TAREA_081);
		}
	}

	@Override()
	public void removeRelacionTareasDependientes(List<Tareas> listTareas) {
		if (listTareas != null && !listTareas.isEmpty()) {
			// la relacion
			eliminarRelacionTareaDependiente(listTareas, DBConstants.TABLA_081, DaoConstants.T1_MINUSCULA,
					DBConstants.ID_TAREA_081);
		}
	}

	/**
	 * @param listTareas
	 * @param nombreTabla
	 * @param idTabla
	 * @param campoIdTarea
	 */
	public void eliminarTareaDependiente(List<Tareas> listTareas, String nombreTabla, String idTabla,
			String campoIdTarea) {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.DELETE);
		query.append(DaoConstants.FROM);
		query.append(nombreTabla);
		query.append(DaoConstants.BLANK);
		query.append(idTabla);
		query.append(DaoConstants.WHERE);
		query.append(idTabla);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(campoIdTarea);
		query.append(DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(QueryUtils.getQueryTareasDependientes(listTareas));
		query.append(DaoConstants.CERRAR_PARENTESIS);

		this.getJdbcTemplate().update(query.toString());
	}

	/**
	 * @param listTareas
	 * @param nombreTabla
	 * @param idTabla
	 * @param campoIdTarea
	 */
	public void eliminarRelacionTareaDependiente(List<Tareas> listTareas, String nombreTabla, String idTabla,
			String campoIdTarea) {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.UPDATE);
		query.append(nombreTabla);
		query.append(DaoConstants.SET);
		query.append(DBConstants.ID_TAREA_REL_081);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.NULL);
		query.append(DaoConstants.WHERE);
		query.append(DBConstants.ID_TAREA_REL_081);
		query.append(DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(Utils.obtenerListIds(listTareas));
		query.append(DaoConstants.CERRAR_PARENTESIS);

		this.getJdbcTemplate().update(query.toString());
	}

	private RowMapper<ResumenGraficas> rwMapResumenGraficas = new ResumenGraficasRowMapper();

	@Override
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder str = getSelectPorIdioma(locale);

		return str.toString();
	}

	/**
	 * @param locale
	 * @return
	 */
	private StringBuilder getSelectPorIdioma(Locale locale) {
		StringBuilder str = new StringBuilder();
		str.append("SELECT ");
		// Tareas
		str.append("t1.ANYO_081 ANYO ");
		str.append(", t1.NUM_EXP_081 NUMEXP ");
		str.append(", t1.ID_TAREA_081 IDTAREA ");
		str.append(", t1.ID_TIPO_TAREA_081 IDTIPOTAREA ");
		str.append(", t1.IND_FACTURABLE_081 INDFACTURABLE ");
		str.append(", t1.FECHA_INICIO_081 FECHAINI ");
		str.append(", TO_CHAR(t1.FECHA_INICIO_081,'HH24:MI') HORAINI");
		str.append(", t1.FECHA_FIN_081 FECHAFIN ");
		str.append(", TO_CHAR(t1.FECHA_FIN_081,'HH24:MI') HORAFIN");
		str.append(", t1.HORAS_PREVISTAS_081 HORASPREVISTAS ");
		str.append(", t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION ");
		str.append(", t1.DNI_RECURSO_081 DNIRECURSO ");
		str.append(", t1.TIPO_ENTIDAD_081 TIPOENTIDAD ");
		str.append(DAOUtils.getDecodeAcciones("t1.TIPO_ENTIDAD_081", "TIPOENTIDADDESC", this.msg, "TipoEntidadEnum",
				locale));
		str.append(", t1.ID_ENTIDAD_081 IDENTIDAD ");
		str.append(", t1.IND_NO_CONFORMIDAD_081 INDNOCONF");
		str.append(", t1.ID_LOTE_081 IDLOTE ");
		str.append(", t1.ESTADO_ASIGNACION_081 ESTADOASIGID ");
		str.append(DAOUtils.getDecodeAcciones("t1.ESTADO_ASIGNACION_081", "ESTADOASIGNACIONDESC", this.msg,
				"EstadoAceptacionTareaEnum", locale));
		str.append(", t1.ESTADO_EJECUCION_081 ESTADOEJECID, ");
		str.append(" CASE WHEN (" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "=t1.ESTADO_EJECUCION_081 AND t1.FECHA_FIN_081 < SYSDATE) THEN '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "'");
		str.append(" ELSE (DECODE(t1.ESTADO_EJECUCION_081, " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ ",'" + this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale)
				+ "',");
		str.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ",'"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale)
				+ "')) END AS ESTADOEJECUCIONDESC");
		str.append(", t1.OBSERV_081 OBSERV ");
		str.append(", t1.ORDEN_081 ORDEN ");
		str.append(", t1.DNI_ASIGNADOR_081 DNIASIGNADOR ");
		str.append(", t1.FECHA_ASIGNACION_081 FECHAASIGNACION ");
		str.append(", TO_CHAR(t1.FECHA_ASIGNACION_081,'HH24:MI') HORAASIGNACION");
		str.append(", t1.FECHA_ACEPTACION_081 FECHAACEPTACION ");
		str.append(", TO_CHAR(t1.FECHA_ACEPTACION_081,'HH24:MI') HORAACEPTACION");
		str.append(", t1.ID_TAREA_REL_081 IDTAREAREL ");
		str.append(", t1.ID_REQUERIMIENTO_081 IDREQUERIMIENTO ");
		str.append(", t1.ID_TIPO_REVISION_081 IDTIPOREVISION ");
		str.append(", t1.IND_REQ_REVISION_081 INDREQREVISION_081 ");
        str.append(", t1.IND_MOSTRAR_NOTAS_A_TRAD_081 INDMOSTRARNOTASATRAD ");
		return str;
	}

	protected String getSelectCargaTrabajo() {
		StringBuilder str = new StringBuilder();
		Locale locale = LocaleContextHolder.getLocale();
		str.append(this.getSelect());
		str.append(DAOUtils.getDecodeAcciones(ID_TIPO_TAREA_081, "TAREA", this.msg, "TipoTareaGestionAsociadaEnum",
				locale));
		str.append(", t2.NOMBRE_LOTE_029 NOMBRELOTE ");

		return str.toString();
	}

	private String getSelectConfTareas() {
		StringBuilder query = new StringBuilder();

		query.append(this.getSelect());
		// ExpedienteInterpretacion
		query.append(", t3.FECHA_FIN_052 FECHAFININTER");
		query.append(", TO_CHAR(t3.FECHA_FIN_052,'HH24:MI') HORAFININTER");
		// ExpedienteTradRev
		query.append(", t4.FECHA_FINAL_IZO_053 FECHAFINALIZO");
		query.append(", TO_CHAR(t4.FECHA_FINAL_IZO_053,'HH24:MI') HORAFINALIZO");
		// Tipos de tarea
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_ES_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAES + DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_EU_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAEU + DaoConstants.BLANK);
		query.append(", t5.IND_REQ_CIERRE_015 INDREQCIERRE ");
		// Lotes
		query.append(", t6.NOMBRE_LOTE_029 NOMBRELOTE ");
		// Datos contacto
		query.append(", t7.NOMBRE_077 NOMBRERECURSO");
		query.append(", t7.APEL1_077 APELLIDO1RECURSO");
		query.append(", t7.APEL2_077 APELLIDO2RECURSO");
		query.append(", ES_RECURSO_DISPONIBLE(t1.DNI_RECURSO_081, t1.ID_TAREA_081,NULL) AS ESRECURSODISPONIBLE ");
		query.append(", TIENE_OTRAS_TAREAS(t1.DNI_RECURSO_081, t1.ID_TAREA_081,NULL) AS TIENEOTRASTAREAS ");
		query.append(", t1.IND_OBLIGAR_XLIFF_081 INDOBLIGARXLIFF ");
		return query.toString();
	}

	@Override
	protected String getFrom() {

		StringBuilder from = new StringBuilder();

		// Tareas
		from.append(" FROM AA79B81S01 t1 ");

		return from.toString();
	}

	private String getFromConfTareas() {

		StringBuilder from = new StringBuilder();

		// Tareas
		from.append(this.getFrom());
		// Expediente
		from.append("LEFT JOIN AA79B51S01 t2 ON t1.ANYO_081 = t2.ANYO_051 AND t1.NUM_EXP_081 = t2.NUM_EXP_051 ");
		// ExpedienteInterpretacion
		from.append("LEFT JOIN AA79B52S01 t3 ON t1.ANYO_081 = t3.ANYO_052 AND t1.NUM_EXP_081 = t3.NUM_EXP_052 ");
		// ExpedienteTradRev
		from.append("LEFT JOIN AA79B53S01 t4 ON t1.ANYO_081 = t4.ANYO_053 AND t1.NUM_EXP_081 = t4.NUM_EXP_053 ");
		// TiposTarea
		from.append(LEFT_JOIN_TABLA_81_15);
		// Lotes
		from.append("LEFT JOIN AA79B29S01 t6 on t1.ID_LOTE_081 = t6.ID_LOTE_029 ");
		// Datos contacto
		from.append("LEFT JOIN AA79B77S01 t7 on t1.DNI_RECURSO_081 = t7.DNI_077 ");

		return from.toString();
	}

	private String getFromTareasAutomaticas() {

		StringBuilder from = new StringBuilder();

		// Tareas
		from.append(this.getFrom());
		// TiposTarea
		from.append("LEFT JOIN AA79B15S01 tiposTarea ON t1.ID_TIPO_TAREA_081 = tiposTarea.ID_015 ");

		return from.toString();
	}

	@Override
	protected RowMapper<Tareas> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return TareasDaoImpl.ORDER_BY_WHITE_LIST;

	}

	protected String[] getOrderByAsignado() {
		return TareasDaoImpl.ORDER_BY_WHITE_LIST_ASIGNADO;

	}

	@Override
	protected String getPK() {
		return DBConstants.ID_TAREA_081;
	}

	@Override
	protected RowMapper<Tareas> getRwMapPK() {
		return this.rwMapPK;
	}

	public RowMapper<TareaIntPagoProveed> getRwMapTareaIntPagProveed() {
		return this.rwMapTareaIntPagProveed;
	}

	@Override
	protected String getWherePK(Tareas bean, List<Object> params) {
		params.add(bean.getIdTarea());
		return " WHERE t1.ID_TAREA_081 = ?";
	}

	@Override
	protected String getWhere(Tareas bean, List<Object> params) {
		StringBuilder strArea = new StringBuilder();
		if (bean.getLotes() != null) {
			strArea.append(SqlUtils.generarWhereIgual("t1.ID_LOTE_081", bean.getLotes().getIdLote(), params));
		}
		if (bean.getTipoTarea() != null) {
			strArea.append(SqlUtils.generarWhereIgual(ID_TIPO_TAREA_081, bean.getTipoTarea().getId015(), params));
		}
		strArea.append(SqlUtils.generarWhereIgual(ESTADO_EJECUCION_081, bean.getEstadoEjecucion(), params));
		strArea.append(SqlUtils.generarWhereIgual("t1.FECHA_INICIO_081", bean.getFechaIni(), params));
		strArea.append(SqlUtils.generarWhereIgual("t1.FECHA_FIN_081", bean.getFechaFin(), params));

		return strArea.toString();
	}

	private String getWhereTarea(Tareas bean, List<Object> params) {
		StringBuilder strArea = new StringBuilder();
		if (bean.getTipoTarea() != null) {
			strArea.append(SqlUtils.generarWhereIgual(ID_TIPO_TAREA_081, bean.getTipoTarea().getId015(), params));
		}
		strArea.append(SqlUtils.generarWhereIgual("t1.ID_TAREA_REL_081", bean.getIdTareaRel(), params));

		return strArea.toString();
	}

	@Override
	protected String getWhereLike(Tareas bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder strArea = new StringBuilder();
		if (bean.getLotes() != null) {
			strArea.append(SqlUtils.generarWhereIgual("t1.ID_LOTE_081", bean.getLotes().getIdLote(), params));
		}
		if (bean.getTipoTarea() != null) {
			strArea.append(SqlUtils.generarWhereIgual(ID_TIPO_TAREA_081, bean.getTipoTarea().getId015(), params));
		}
		strArea.append(SqlUtils.generarWhereIgual(ESTADO_EJECUCION_081, bean.getEstadoEjecucion(), params));
		strArea.append(SqlUtils.generarWhereIgual("t1.FECHA_INICIO_081", bean.getFechaIni(), params));
		strArea.append(SqlUtils.generarWhereIgual("t1.FECHA_FIN_081", bean.getFechaFin(), params));

		return strArea.toString();
	}

	private String getWhereTareasPendientesDeEjecutar(List<String> listSelectedIds) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			where.append("AND ESTADO_EJECUCION_081 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue()
					+ " AND ID_TAREA_081 IN (");
			where.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			where.append(")");
		}

		return where.toString();
	}

	private String getWhereReasignarTareas(List<String> listSelectedIds) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			where.append("AND ESTADO_EJECUCION_081 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue()
					+ " AND RECURSO_ASIGNACION_081 <> ' '" + " AND ID_TAREA_081 IN (");
			where.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			where.append(")");
		}

		return where.toString();
	}

	@Override()
	public Tareas add(Tareas tareas) {

		StringBuilder queryId = new StringBuilder("SELECT AA79B81Q00.NEXTVAL FROM dual ");
		BigDecimal id = this.getJdbcTemplate().queryForObject(queryId.toString(), BigDecimal.class);
		tareas.setIdTarea(id);

		Date fechaIni = tareas.getFechaIni();
		String horaIni = tareas.getHoraIni();

		Date fechaFin = tareas.getFechaFin();
		String horaFin = tareas.getHoraFin();

		String query = "INSERT INTO AA79B81S01 ("
				+ "ID_TAREA_081, ID_TIPO_TAREA_081, ANYO_081, NUM_EXP_081, IND_FACTURABLE_081, "
				+ "FECHA_INICIO_081, FECHA_FIN_081, HORAS_PREVISTAS_081, RECURSO_ASIGNACION_081, "
				+ "DNI_RECURSO_081, TIPO_ENTIDAD_081, ID_ENTIDAD_081, ID_LOTE_081, "
				+ "ESTADO_ASIGNACION_081, ESTADO_EJECUCION_081, OBSERV_081, ORDEN_081, DNI_ASIGNADOR_081, "
				+ "FECHA_ASIGNACION_081, FECHA_ACEPTACION_081, ID_TAREA_REL_081, ID_REQUERIMIENTO_081, "
				+ "IND_NO_CONFORMIDAD_081, ID_TIPO_REVISION_081, IND_REQ_REVISION_081, IND_MOSTRAR_NOTAS_A_TRAD_081, IND_OBLIGAR_XLIFF_081 ) " + "VALUES (?,?,?,?,?,"
				+ INTERROGACIONES_POR_4 + INTERROGACIONES_POR_4 + "?,?,?,?,?," + INTERROGACIONES_POR_4 + "?,?,?,?,?)";
		this.getJdbcTemplate().update(query, tareas.getIdTarea(), tareas.getTipoTarea().getId015(), tareas.getAnyo(),
				tareas.getNumExp(), Utils.getValueInd(tareas.getIndFacturacion()),
				DateUtils.obtFechaHoraFormateada(fechaIni, horaIni),
				DateUtils.obtFechaHoraFormateada(fechaFin, horaFin), tareas.getHorasPrevistas(),
				tareas.getRecursoAsignacion(), tareas.getPersonaAsignada().getDni(),
				tareas.getPersonaAsignada().getEntidad().getTipo(),
				tareas.getPersonaAsignada().getEntidad().getCodigo(), tareas.getLotes().getIdLote(),
				Utils.getEstadoAsignacion(tareas.getEstadoAsignado()),
				Utils.getEstadoEjecucion(tareas.getEstadoEjecucion()), tareas.getObservaciones(), tareas.getOrden(),
				tareas.getPersonaAsignador().getDni(), tareas.getFechaAsignacion(), tareas.getFechaAceptacion(),
				tareas.getIdTareaRel(), tareas.getIdRequerimiento(), Utils.getValueInd(tareas.getIndNoConformidad()),
				tareas.getTipoRevision().getId018(), tareas.getIndReqRevision(), tareas.getIndMostrarNotasATrad(), tareas.getIndObligarXliff());
		return tareas;
	}

	@Override
	public Tareas addTareaSubsanacion(Tareas tareas) {

		StringBuilder queryId = new StringBuilder("SELECT AA79B81Q00.NEXTVAL FROM dual ");
		BigDecimal id = this.getJdbcTemplate().queryForObject(queryId.toString(), BigDecimal.class);
		tareas.setIdTarea(id);

		List<Object> params = new ArrayList<Object>();
		StringBuilder queryOrden = new StringBuilder(
				"SELECT nvl(MIN(DECODE(ORDEN_081,0,1,ORDEN_081)),1) FROM AA79B81T00 WHERE ANYO_081 = ? AND NUM_EXP_081 = ? AND (ESTADO_EJECUCION_081 IS NULL OR ESTADO_EJECUCION_081 <> "
						+ EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ")");
		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		Integer orden = this.getJdbcTemplate().update(queryOrden.toString(), params.toArray());
		tareas.setOrden(orden);

		Date fechaIni = tareas.getFechaIni();
		String horaIni = tareas.getHoraIni();

		Date fechaFin = tareas.getFechaFin();
		String horaFin = tareas.getHoraFin();

		String query = "INSERT INTO AA79B81S01 (ID_TAREA_081, ID_TIPO_TAREA_081, ANYO_081, NUM_EXP_081, "
				+ "FECHA_INICIO_081, FECHA_FIN_081, HORAS_PREVISTAS_081, RECURSO_ASIGNACION_081, "
				+ "DNI_RECURSO_081, TIPO_ENTIDAD_081, ID_ENTIDAD_081, ID_LOTE_081, "
				+ "ESTADO_ASIGNACION_081, OBSERV_081, ORDEN_081, DNI_ASIGNADOR_081, "
				+ "FECHA_ASIGNACION_081, FECHA_ACEPTACION_081, ID_REQUERIMIENTO_081, " + "ID_TIPO_REVISION_081) "
				+ "VALUES (?,?,?,?," + INTERROGACIONES_POR_4 + INTERROGACIONES_POR_4 + INTERROGACIONES_POR_4
				+ "SYSDATE,SYSDATE,?,?)";

		this.getJdbcTemplate().update(query, tareas.getIdTarea(), tareas.getTipoTarea().getId015(), tareas.getAnyo(),
				tareas.getNumExp(), DateUtils.obtFechaHoraFormateada(fechaIni, horaIni),
				DateUtils.obtFechaHoraFormateada(fechaFin, horaFin), tareas.getHorasPrevistas(),
				tareas.getRecursoAsignacion(), tareas.getPersonaAsignada().getDni(),
				tareas.getPersonaAsignada().getEntidad().getTipo(),
				tareas.getPersonaAsignada().getEntidad().getCodigo(), tareas.getLotes().getIdLote(),
				tareas.getEstadoAsignado(), tareas.getObservaciones(), tareas.getOrden(),
				tareas.getPersonaAsignador().getDni(), tareas.getIdRequerimiento(),
				tareas.getTipoRevision().getId018());
		return tareas;
	}

	@Override()
	public Tareas update(Tareas tareas) {
		Date fechaIni = tareas.getFechaIni();
		String horaIni = tareas.getHoraIni();

		Date fechaFin = tareas.getFechaFin();
		String horaFin = tareas.getHoraFin();

		StringBuilder query = new StringBuilder(DaoConstants.UPDATE + DBConstants.TABLA_081);
		query.append(" SET IND_FACTURABLE_081=?");
		query.append(", FECHA_INICIO_081=?");
		query.append(", FECHA_FIN_081=?");
		query.append(", HORAS_PREVISTAS_081=?");
		query.append(", RECURSO_ASIGNACION_081=?");
		query.append(", DNI_RECURSO_081=?");
		query.append(", TIPO_ENTIDAD_081=?");
		query.append(", ID_ENTIDAD_081=?");
		query.append(", ID_LOTE_081=?");
		query.append(", ESTADO_ASIGNACION_081=?");
		query.append(", OBSERV_081=?");
		query.append(", ORDEN_081=?");
		query.append(", DNI_ASIGNADOR_081=?");
		query.append(", FECHA_ASIGNACION_081=?");
		query.append(", ID_TIPO_REVISION_081=?");
		query.append(", IND_REQ_REVISION_081=?");
		query.append(", IND_MOSTRAR_NOTAS_A_TRAD_081=?");
		query.append(", IND_OBLIGAR_XLIFF_081=?");
		query.append(DaoConstants.WHERE + DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);

		this.getJdbcTemplate().update(query.toString(), Utils.getValueInd(tareas.getIndFacturacion()),
				DateUtils.obtFechaHoraFormateada(fechaIni, horaIni),
				DateUtils.obtFechaHoraFormateada(fechaFin, horaFin), tareas.getHorasPrevistas(),
				tareas.getRecursoAsignacion(), tareas.getPersonaAsignada().getDni(),
				tareas.getPersonaAsignada().getEntidad().getTipo(),
				tareas.getPersonaAsignada().getEntidad().getCodigo(), tareas.getLotes().getIdLote(),
				Utils.getEstadoAsignacion(tareas.getEstadoAsignado()), tareas.getObservaciones(), tareas.getOrden(),
				tareas.getPersonaAsignador().getDni(), tareas.getFechaAsignacion(), tareas.getTipoRevision().getId018(),
				tareas.getIndReqRevision(), tareas.getIndMostrarNotasATrad(), tareas.getIndObligarXliff(), tareas.getIdTarea());
		return tareas;
	}

	@Override
	public List<Tareas> obtenerTareasConf(Tareas filter, JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(this.getSelectConfTareas());
		query.append(this.getFromConfTareas());
		query.append(QueryUtils.getWhereLikeTareasConf(filter, startsWith, params, false));

		if (jqGridRequestDto != null) {
			if (jqGridRequestDto.getSidx() != null) {
				if (jqGridRequestDto.getSidx().indexOf(',') > 0) {
					query.append(
							" ORDER BY ANYO_081 ASC, NUM_EXP_081 ASC, ORDEN_081 ASC, ID_TIPO_TAREA_081 ASC, ID_TAREA_081 ASC ");
				} else {
					query.append(" ORDER BY " + jqGridRequestDto.getSidx() + " ");
					query.append(jqGridRequestDto.getSord());
				}

			}

			Long rows = jqGridRequestDto.getRows();
			Long page = jqGridRequestDto.getPage();
			if ((page != null) && (rows != null)) {
				paginatedQuery.append(
						SELECTALLFROM_ROWNUM + query + ")a) where rnum > " + rows.longValue() * (page.longValue() - 1L)
								+ " and rnum < " + (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				paginatedQuery.append(
						SELECTALLFROM_ROWNUM + query + ")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			} else {
				paginatedQuery.append(query);
			}
		}
		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapConfTareas, params.toArray());
	}

	@Override
	public Long obtenerTareasConfCount(Tareas filter, boolean startsWith) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFromConfTareas());
		query.append(QueryUtils.getWhereLikeTareasConf(filter, startsWith, params, false));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public List<TableRowDto<Tareas>> reorderSelectionTareasConf(Tareas filter, JQGridRequestDto jqGridRequestDto,
			boolean startsWith) {
		// SELECT
		List<Object> params = new ArrayList<Object>();
		StringBuilder subQuery = new StringBuilder(this.getSelect());
		// FROM
		subQuery.append(this.getFromConfTareas());
		// FILTRADO
		subQuery.append(DaoConstants.WHERE_1_1);
		subQuery.append(QueryUtils.getWhereLikeTareasConf(filter, startsWith, params, false));

		StringBuilder query = new StringBuilder();
		query.append(" select " + this.getPK() + ", page, pageLine, tableLine from" + " ( select " + this.getPK()
				+ " , ceil(rownum/" + jqGridRequestDto.getRows() + ") page,  ").append(DBConstants.CASE_WHEN)
				.append("  (mod(rownum," + jqGridRequestDto.getRows() + ")=0) " + "then '" + jqGridRequestDto.getRows()
						+ "' else TO_CHAR(mod(rownum," + jqGridRequestDto.getRows()
						+ ")) end as pageLine, rownum as tableLine from (");
		query.append(subQuery.toString());
		query.append(")" + DaoConstants.WHERE_1_1);
		List<String> listSelectedIds = jqGridRequestDto.getMultiselection().getSelectedIds();
		for (int i = 0; i < listSelectedIds.size(); i++) {
			if (i >= 1) {
				query.append(DaoConstants.OR + " ");
			}
			if (i == 0) {
				query.append(DaoConstants.AND + " ");
			}
			String idCombinada = listSelectedIds.get(i);
			String[] parts = idCombinada.split(",");
			query.append("ANYO_081 = ? and NUM_EXP_081 = ? ");
			params.add(Long.parseLong(parts[0], 10));
			params.add(Integer.parseInt(parts[1]));
		}
		query.append(")");

		RowNumResultSetExtractor<Tareas> rowNumOrder = new RowNumResultSetExtractor<Tareas>(this.getRwMapPK(),
				jqGridRequestDto);

		return this.getJdbcTemplate().query(query.toString(), rowNumOrder, params.toArray());
	}

	@Override
	public boolean isVisiblePptoInterpretacion(Expediente expediente) {
		boolean rdo = false;
		List<Object> params = new ArrayList<Object>();
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		StringBuilder query = new StringBuilder(
				"SELECT count(1)" + " FROM AA79B81S01 LEFT JOIN AA79B89S01 ON ID_TAREA_081 = ID_TAREA_089");
		query.append(" WHERE ANYO_081 = ? AND NUM_EXP_081 = ? AND ID_TIPO_TAREA_081 = ")
				.append(TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue()).append("AND IND_VISIBLE_089 ='")
				.append(Constants.SI).append("'");

		try {
			Integer visible = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
			if (visible.equals(Integer.valueOf(1))) {
				rdo = true;
			}
		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			TareasDaoImpl.LOGGER.error(erdae.getMessage(), erdae);
		}
		return rdo;
	}

	@Override()
	public List<Tareas> findTareasPendientesDeEjecutar(List<String> listSelectedIds) {
		StringBuilder query = new StringBuilder();
		query.append(this.getSelect());
		// Tipos de tarea
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_ES_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAES + DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_EU_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAEU + DaoConstants.BLANK);
		query.append(this.getFrom());
		// TiposTarea
		query.append(LEFT_JOIN_TABLA_81_15);
		query.append(this.getWhereTareasPendientesDeEjecutar(listSelectedIds));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapTareasAEliminar);
	}

	@Override()
	public List<Tareas> findTareasAEliminar(List<String> listSelectedIds) {
		StringBuilder query = new StringBuilder();
		query.append(this.getSelect());
		// Tipos de tarea
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_ES_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAES + DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_EU_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAEU + DaoConstants.BLANK);
		query.append(this.getFrom());
		// TiposTarea
		query.append(LEFT_JOIN_TABLA_81_15);
		query.append(QueryUtils.getWhereTareasAEliminar(listSelectedIds));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapTareasAEliminar);
	}

	@Override()
	public List<Tareas> findTareasDependientesAEliminar(List<Tareas> listTareas) {
		StringBuilder query = new StringBuilder();
		query.append(this.getSelect());
		// Tipos de tarea
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_ES_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAES + DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_EU_015
				+ DaoConstants.BLANK + DBConstants.TIPOTAREAEU + DaoConstants.BLANK);
		query.append(this.getFrom());
		// TiposTarea
		query.append(LEFT_JOIN_TABLA_81_15);
		query.append(QueryUtils.getWhereTareasDependientesAEliminar(listTareas));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapTareasAEliminar);
	}

	@Override
	public ResumenGraficas getTareasPlanificacionCount(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		if (tipoConsulta == PlanifExpedientesEnum.SIN_ASIGNADOR.getId()) {
			PlanificacionExpedienteUtils.getSelectCountSinAsignadorCharts(dni, query, params);
		} else {
			PlanificacionExpedienteUtils.getSelectCountCharts(dni, query, params, "t2");
		}

		final PlanifTareasEnum planifTareasEnum = PlanifTareasEnum.getById(tipoConsulta);

		// ReflexiÃÂ³n
		final String classPackage = "com.ejie.aa79b.utils.PlanificacionExpedienteUtils";
		try {
			final Class<?> c = Class.forName(classPackage);
			Class<?>[] paramTypes = { String.class, StringBuilder.class, List.class };
			Method method = c.getDeclaredMethod(planifTareasEnum.getMethod(), paramTypes);
			method.invoke(c, dni, query, params);
		} catch (Exception e) {
			TareasDaoImpl.LOGGER.info("Error: ", e);
		}

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas, params.toArray());
	}

	@Override()
	public int reasignarTareas(Tareas tarea, List<String> listSelectedIds) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("UPDATE AA79B81S01 SET ");
		query.append(" RECURSO_ASIGNACION_081='").append(TipoRecursoEnum.INTERNO.getValue()).append("'");
		query.append(", DNI_RECURSO_081=?, ID_LOTE_081=? ");
		query.append(", ID_ENTIDAD_081=?, TIPO_ENTIDAD_081=? ");
		query.append(", FECHA_ACEPTACION_081=?, ESTADO_ASIGNACION_081=? ");
		query.append(this.getWhereReasignarTareas(listSelectedIds));

		params.add(tarea.getPersonaAsignada().getDni());
		params.add(null);
		params.add(tarea.getPersonaAsignada().getEntidad().getCodigo() == 0 ? null
				: tarea.getPersonaAsignada().getEntidad().getCodigo());
		params.add(StringUtils.isBlank(tarea.getPersonaAsignada().getEntidad().getTipo()) ? null
				: tarea.getPersonaAsignada().getEntidad().getTipo());
		params.add(tarea.getFechaAceptacion());
		params.add(Utils.getEstadoAsignacion(tarea.getEstadoAsignado()));

		return this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override()
	public void reordenarTareas(List<Tareas> listTareas) {

		for (Tareas tareas : listTareas) {
			this.reordenarTarea(tareas);
		}

	}

	private int reordenarTarea(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("UPDATE AA79B81S01 SET ORDEN_081=? WHERE ID_TAREA_081=? ");

		params.add(tareas.getOrden());
		params.add(tareas.getIdTarea());

		return this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override()
	public int comprobarOrdenTareas(Tareas tarea) {
		final long anyo = tarea.getAnyo();
		final int numExp = tarea.getNumExp();
		final long idTipoTarea = tarea.getTipoTarea().getId015();
		final int orden = tarea.getOrden();
		final BigDecimal idTarea = tarea.getIdTarea();

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{?= call COMPROBAR_ORDEN_TAREAS(?,?,?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, anyo);
				callableStatement.setInt(3, numExp);
				callableStatement.setLong(4, idTipoTarea);
				callableStatement.setLong(5, orden);
				callableStatement.setBigDecimal(6, idTarea);
				return callableStatement;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});
	}

	@Override()
	public BigDecimal calcularImportePresupuestoInter(Tareas tarea) {
		final long anyo = tarea.getAnyo();
		final int numExp = tarea.getNumExp();
		final long numInterpretes = tarea.getGestionInterpretacion().getNumInterpretes();
		final String horas = tarea.getGestionInterpretacion().getHorasPrevistasInterpretacion();
		final BigDecimal idTarea = tarea.getIdTarea();

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{?= call CALCULO_PRESUPUESTO_INTER(?,?,?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, anyo);
				callableStatement.setInt(3, numExp);
				callableStatement.setLong(4, numInterpretes);
				callableStatement.setString(5, horas);
				callableStatement.setBigDecimal(6, idTarea);
				return callableStatement;
			}
		}, new CallableStatementCallback<BigDecimal>() {

			@Override
			public BigDecimal doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getBigDecimal(1);
			}
		});
	}

	@Override()
	public BigDecimal calcularImportePrevistoLote(BigDecimal idTarea, Integer idLote) {
		final BigDecimal tareaId = idTarea;
		final int loteId = idLote;

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{?= call IMPORTE_PREVISTO_LOTE(?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setInt(2, loteId);
				callableStatement.setBigDecimal(3, tareaId);
				return callableStatement;
			}
		}, new CallableStatementCallback<BigDecimal>() {

			@Override
			public BigDecimal doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getBigDecimal(1);
			}
		});
	}

	protected String getSelectTareasAsignadasAProveedor(Tareas bean, List<Object> params) {
		StringBuilder str = new StringBuilder(DBConstants.SELECT)
				.append(" DISTINCT tareas.id_tipo_tarea_081 IDTIPOTAREA, ");
		str.append(" tareas.id_tarea_081 IDTAREA, ");
		str.append(" tareas.anyo_081 ANYO, tareas.num_exp_081 NUMEXP, ");
		str.append(" SUBSTR(tareas.anyo_081,3,4) || '/' || LPAD(tareas.num_exp_081,6,'0') ANYONUMEXPCONCATENADO, ");
		str.append(" desc_eu_015 TAREA, nombre_lote_029 NOMBRELOTE,id_lote_029 IDLOTE, ");
		str.append(" CASE WHEN tareas.estado_ejecucion_081 = 1 and SYSDATE > tareas.fecha_fin_081 THEN 'Atzeratuta' ");
		str.append(
				" ELSE DECODE(tareas.estado_ejecucion_081, 1, 'Egikaritu gabe', 3 ,'Egikaritua') END AS ESTADOEJECUCIONDESC, ");
		str.append(
				" sum(nvl(num_total_pal_091, num_pal_izo_056)) over (partition by tareas.id_tarea_081) AS num_pal_izo, ");
		str.append(
				" sum(nvl(num_total_pal_091, num_pal_izo_056)) over (partition by id_lote_029) as num_total_pal_izo, ");

		str.append(" tareas.FECHA_FIN_081 FECHAFIN, TO_CHAR(tareas.FECHA_FIN_081,'HH24:MI') HORAFIN, ");
		str.append(" tareas.ORDEN_081 ORDEN, ");
		str.append(" b.ID_TAREA_081 IDTAREAPROVEEDORES, ");
		str.append(" p.IND_ALBARAN_094 INDALBARAN ");
		str.append(" FROM aa79b81t00 tareas ");
		str.append(" JOIN aa79b51S01 ");
		str.append(" ON ANYO_081     = ANYO_051 ");
		str.append(" AND NUM_EXP_081 = NUM_EXP_051 ");
		str.append(" JOIN AA79B59S01 ");
		str.append(" ON ANYO_059                = ANYO_051 ");
		str.append(" AND NUM_EXP_059            = NUM_EXP_051 ");
		str.append(" AND ID_ESTADO_BITACORA_059 = ESTADO_BITACORA_051 ");
		str.append(" AND ID_ESTADO_EXP_059 IN (" + EstadoExpedienteEnum.EN_CURSO.getValue() + ", "
				+ EstadoExpedienteEnum.CERRADO.getValue() + ") ");
		str.append(" JOIN AA79B15T00 tipotarea ");
		str.append(" ON tareas.id_tipo_tarea_081 = tipotarea.id_015 ");
		str.append(" AND tipotarea.id_015 IN (" + TipoTareaGestionAsociadaEnum.TRADUCIR.getValue() + ", "
				+ TipoTareaGestionAsociadaEnum.REVISAR.getValue() + ", "
				+ TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue() + ") ");
		str.append(" JOIN aa79b29t00 lotes ");
		str.append(" ON tareas.id_lote_081            = lotes.id_lote_029 ");
		str.append(" JOIN aa79b83t00 documentostarea ");
		str.append(" ON tareas.ID_TAREA_081 = documentostarea.ID_TAREA_083 ");
		str.append(" JOIN aa79b56t00 documentosexp ");
		str.append(" ON documentostarea.id_doc_083                    = documentosexp.id_doc_056 ");
		str.append(" LEFT JOIN aa79b81t00 trados ");
		str.append(" ON tareas.anyo_081               = trados.anyo_081 ");
		str.append(" AND tareas.num_exp_081            = trados.num_exp_081 ");
		str.append(" AND trados.id_tipo_tarea_081      = 1  ");
		str.append(" LEFT JOIN AA79B91T00 documentotrados ");
		str.append(" ON trados.id_tarea_081           = documentotrados.id_tarea_091 ");
		str.append(" AND documentostarea.id_doc_083                    = documentotrados.id_doc_orig_091 ");
		str.append(" LEFT JOIN AA79B81T00 b ");
		str.append(" ON tareas.ID_TIPO_TAREA_081    IN (2,4) ");
		str.append(" AND tareas.ID_TAREA_081         = b.ID_TAREA_REL_081 ");
		str.append(" AND b.ID_TIPO_TAREA_081    = " + TipoTareaGestionAsociadaEnum.REVISION_PAGO_PROVEEDOR.getValue());
		str.append(" AND b.ESTADO_EJECUCION_081 = " + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		str.append(" LEFT JOIN AA79B94T00 p ");
		str.append(" ON p.ID_TAREA_094 = b.ID_TAREA_081 ");
		str.append(" WHERE tareas.recurso_asignacion_081 = '" + TipoRecursoEnum.EXTERNO.getValue() + "' ");

		return str.toString();
	}

	private String getWhereareasAsignadasAProveedor(Tareas bean, List<Object> params) {
		StringBuilder str = new StringBuilder();

		if (null != bean.getIdTarea()) {
			str.append(SqlUtils.generarWhereIgual("tareas.id_tarea_081", bean.getIdTarea(), params));
		}

		str.append(SqlUtils.generarWhereIgual("SUBSTR(tareas.ANYO_081,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));

		if (null != bean.getNumExp()) {
			str.append(SqlUtils.generarWhereIgual("tareas.num_exp_081", bean.getNumExp(), params));
		}
		if (bean.getLotes() != null) {
			str.append(SqlUtils.generarWhereIgual("tareas.ID_LOTE_081", bean.getLotes().getIdLote(), params));
		}
		if (bean.getTipoTarea() != null) {
			str.append(SqlUtils.generarWhereIgual("tareas.ID_TIPO_TAREA_081", bean.getTipoTarea().getId015(), params));
		}
		if (bean.getEstadoEjecucion() > Constants.CERO) {
			if( bean.getEstadoEjecucion()==3){
				str.append(SqlUtils.generarWhereIgual("tareas.ESTADO_EJECUCION_081", bean.getEstadoEjecucion(), params));
			}else{
				str.append(" AND tareas.ESTADO_EJECUCION_081=1");
				if( bean.getEstadoEjecucion()==1){
					str.append(" AND tareas.FECHA_FIN_081 >=trunc(SYSDATE) ");
				}else{

					str.append(" AND tareas.FECHA_FIN_081 <trunc(SYSDATE) ");

				}
			}
		}

		str.append(SqlUtils.generarWhereMayorIgualFecha("tareas.FECHA_INICIO_081", "DD/mm/YY", bean.getFechaIni(),
				params));
		str.append(
				SqlUtils.generarWhereMenorIgualFecha("tareas.FECHA_FIN_081", "DD/mm/YY", bean.getFechaFin(), params));
		return str.toString();
	}

	@Override
	public List<TareasAsignado> findTareasAsignadasAProveedor(Tareas bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelectTareasAsignadasAProveedor(bean, params));

		query.append(this.getWhereareasAsignadasAProveedor(bean, params));

		this.getOrderTareasAsignadas(jqGridRequestDto, query);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapAsignadoAProv, params.toArray());
	}

	private void getOrderTareasAsignadas(JQGridRequestDto jqGridRequestDto, StringBuilder query) {

		query.append(" ORDER BY NOMBRELOTE ASC, IDLOTE ASC");

		if (jqGridRequestDto != null) {
			if (jqGridRequestDto.getSidx() != null) {
				query.append(" , " + jqGridRequestDto.getSidx() + " ");
				query.append(jqGridRequestDto.getSord());
			}

			Long rows = jqGridRequestDto.getRows();
			Long page = jqGridRequestDto.getPage();

			if ((page != null) && (rows != null)) {
				query.insert(0, SELECTALLFROM_ROWNUM);
				query.append(")a) where rnum > " + rows.longValue() * (page.longValue() - 1L) + " and rnum < "
						+ (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				query.insert(0, SELECTALLFROM_ROWNUM);
				query.append(")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long findTareasAsignadasAProveedorCount(Tareas bean) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT).append("  FROM (");
		query.append(this.getSelectTareasAsignadasAProveedor(bean, params));
		query.append(this.getWhereareasAsignadasAProveedor(bean, params));
		query.append(")");

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 *
	 * @param idTarea Long
	 */
	@Override()
	public void updateTareaEjecutada(BigDecimal idTarea) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.UPDATE).append(AA79B81S01_SET);
		query.append(SqlUtils.generarUpdate("ESTADO_EJECUCION_081", EstadoEjecucionTareaEnum.EJECUTADA.getValue(),
				params, true));
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(DBConstants.ID_TAREA_081, idTarea, params));

		this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	/**
	 *
	 * @param idTarea        Long
	 *
	 * @param indFacturacion String
	 */
	@Override()
	public void updateIndFacturableRelacionada(BigDecimal idTarea, String indFacturacion) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.UPDATE).append(AA79B81S01_SET);
		query.append(SqlUtils.generarUpdate("IND_FACTURABLE_081", indFacturacion, params, true));
		query.append(DaoConstants.WHERE);
		query.append(DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT + DBConstants.ID_TAREA_REL_081);
		query.append(DaoConstants.FROM + DBConstants.TABLA_081);
		query.append(DaoConstants.WHERE + DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION
				+ DaoConstants.CERRAR_PARENTESIS);
		params.add(idTarea);
		this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	/**
	 *
	 * @param idTarea
	 * @param idSubsanacion
	 */
	@Override()
	public void updateIdSubsanacionTareaEjecutada(BigDecimal idTarea, Long idSubsanacion) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.UPDATE).append(AA79B81S01_SET);
		query.append(SqlUtils.generarUpdate("ID_REQUERIMIENTO_081", idSubsanacion, params, true));
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(ID_TAREA_081, idTarea, params));

		this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override()
	public int comprobarTareaAsignador(Tareas tareas) {
		int rst = 0;

		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(SELECT_IDTAREA);
		query.append(FROM_TABLA_81);
		query.append(WHERE_IDTAREA_81);
		query.append(" AND t3.DNI_RECURSO_081 = ? ");
		query.append(" AND t3.RECURSO_ASIGNACION_081 = '");
		query.append(TipoRecursoEnum.INTERNO.getValue());
		query.append("'");

		params.add(tareas.getIdTarea());
		params.add(tareas.getPersonaAsignada().getDni());

		List<Tareas> listTareas = this.getJdbcTemplate().query(query.toString(), this.getRwMapPK(), params.toArray());
		Tareas rstTarea = DataAccessUtils.uniqueResult(listTareas);

		if (rstTarea == null) {
			rst = 1;
		} else {
			params = new ArrayList<Object>();

			query = new StringBuilder(SELECT_IDTAREA);
			query.append(FROM_TABLA_81);
			query.append(WHERE_IDTAREA_81);
			query.append(" AND t3.ESTADO_ASIGNACION_081 = ");
			query.append(EstadoAceptacionTareaEnum.ACEPTADA.getValue());

			params.add(tareas.getIdTarea());

			listTareas = this.getJdbcTemplate().query(query.toString(), this.getRwMapPK(), params.toArray());
			rstTarea = DataAccessUtils.uniqueResult(listTareas);

			if (rstTarea == null) {
				rst = 4;
			} else {
				Long numTareasPdtes = comprobarTareasPendientes(tareas);

				if (numTareasPdtes > 0) {
					rst = 2;
				}
			}

		}

		return rst;
	}

	@Override()
	public long comprobarTareasPendientes(Tareas tareas) {
		List<Object> params;
		StringBuilder query;
		params = new ArrayList<Object>();

		query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(" WHERE t1.ESTADO_EJECUCION_081 <> ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND t1.ANYO_081 = ? AND t1.NUM_EXP_081 = ?");
		query.append(" AND t1.ORDEN_081 < ");
		query.append(" ( SELECT t2.ORDEN_081 ");
		query.append(" FROM AA79B81S01 t2 ");
		query.append(" WHERE t2.ID_TAREA_081 = ? ) ");

		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(tareas.getIdTarea());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 *
	 * @param tareas Tareas
	 * @return Long
	 */
	@Override()
	public Long findAllCountInterpretacionFin(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(
				" JOIN AA79B15S01 ON ID_015 = ID_TIPO_TAREA_081 AND IND_INTERPRETACION_015 = ? AND IND_REQ_CIERRE_015 = ?");
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND t1.ID_TAREA_081 != ?");
		query.append(" AND t1.ESTADO_EJECUCION_081 != ?");
		// tipo tarea 8

		params.add(Constants.SI);
		params.add(Constants.SI);
		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(tareas.getIdTarea());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 *
	 * @param tarea Tareas
	 */
	@Override()
	public void reiniciarCalculoInterpretacion(final Tareas tarea) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));

		this.getJdbcTemplate().call(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call REINICIAR_CALCULO_INTER (?,?)}");
				callableStatement.setLong(1, tarea.getAnyo());
				callableStatement.setInt(2, tarea.getNumExp());
				return callableStatement;
			}
		}, paramList);
	}

	/**
	 *
	 * @param tarea Tareas
	 */
	@Override()
	public void updateConformidad(Tareas tarea) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.UPDATE).append(AA79B81S01_SET);
		query.append(SqlUtils.generarUpdate("IND_NO_CONFORMIDAD_081",
				tarea.getIndNoConformidad() != null ? tarea.getIndNoConformidad() : Constants.NO, params, true));
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(DBConstants.ID_TAREA_081, tarea.getIdTarea(), params));

		this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	/**
	 *
	 * @param tareas Tareas
	 * @return Long
	 */
	@Override()
	public Long findAllCountNoReqCierreSinEjecutar(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(" JOIN AA79B15S01 ON ID_015 = ID_TIPO_TAREA_081 AND IND_REQ_CIERRE_015 = ? ");
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND t1.ID_TAREA_081 != ?");
		query.append(" AND t1.ESTADO_EJECUCION_081 != ?");

		params.add(Constants.NO);
		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(tareas.getIdTarea());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override()
	public Long findAllCountSinEjecutar(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(" JOIN AA79B15S01 ON ID_015 = ID_TIPO_TAREA_081 ");
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND t1.ESTADO_EJECUCION_081 != ?");

		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 *
	 * @param tarea Tareas
	 * @return Long
	 */
	@Override()
	public Long findAllCountMismoOrden(Tareas tarea) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(DaoConstants.JOIN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.SELECT).append(" ANYO_081, NUM_EXP_081, ORDEN_081 ").append(this.getFrom())
				.append(DaoConstants.WHERE_1_1)
				.append(SqlUtils.generarWhereIgual(ID_TAREA_081, tarea.getIdTarea(), params));
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(" t2 ").append(DaoConstants.ON);
		query.append(SqlUtils.generarOnCampos(ANYO_081, "t2.ANYO_081", true));
		query.append(SqlUtils.generarOnCampos(NUM_EXP_081, "t2.NUM_EXP_081", false));
		query.append(SqlUtils.generarOnCampos("t1.ORDEN_081", "t2.ORDEN_081", false));
		query.append(" t1.ID_TAREA_081 != ?");
		query.append(" t1.ESTADO_EJECUCION_081 != ?");

		params.add(tarea.getIdTarea());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public void procCrearTareasDefectoPL(final Long anyo, final Integer numExp) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));

		this.getJdbcTemplate().call(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call CREAR_TAREAS_DEFECTO (?,?)}");
				callableStatement.setLong(1, anyo);
				callableStatement.setInt(2, numExp);
				return callableStatement;
			}
		}, paramList);
	}

	@Override
	public TareaIntPagoProveed getDatosTareaInt(Long idTarea) {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT);
		query.append(" T1aux.FECHA_INICIO_081 FECHAINI, TO_CHAR(T1aux.FECHA_INICIO_081,'HH24:MI') HORAINI,");
		query.append(" T1aux.FECHA_FIN_081 FECHAFIN, TO_CHAR(T1aux.FECHA_FIN_081,'HH24:MI') HORAFIN ");

		query.append(", t2.HORAS_TAREA_082 HORASREALESTAREA");
		query.append(", t3.NOMBRE_077 NOMBRE");
		query.append(", t3.APEL1_077 APE1");
		query.append(", t3.APEL2_077 APE2");
		query.append(", t4.POR_IVA_095 PORCIVA");
		query.append(", t4.IMPORTE_TOTAL_095 IMPRTOTAL");
		query.append(", t4.IMPORTE_IVA_095 IMPTIVA");
		query.append(", t4.IMPORTE_BASE_095 IMPTBASE");
		query.append(", T1aux.HORAS_PREVISTAS_081 HORASPREVISTAS ");

		query.append(" FROM AA79B81S01 T1 JOIN AA79B81S01 T1aux ON T1aux.ID_TAREA_081 = T1.ID_TAREA_REL_081 ");
		query.append(" LEFT JOIN AA79B82S01 T2 ON T1aux.ID_TAREA_081 = T2.ID_TAREA_082 ");
		query.append(" LEFT JOIN AA79B77S01 T3 ON T1aux.DNI_RECURSO_081 = T3.DNI_077 ");
		query.append(" LEFT JOIN AA79B95S01 T4 ON T1.ID_TAREA_081 = T4.ID_TAREA_095 ");

		query.append(" WHERE t1.ID_TAREA_081=?");
		List<Object> params = new ArrayList<Object>();
		params.add(idTarea);
		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapTareaIntPagProveed, params.toArray());
	}

	@Override
	public String getRecursoAsignado(Tareas tarea) {

		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT);
		query.append(" DNI_RECURSO_081 DNI_RECURSO");
		query.append(" FROM AA79B81S01 ");
		query.append(" WHERE ANYO_081=? AND NUM_EXP_081=? AND ID_TIPO_TAREA_081=? AND ORDEN_081 < ? ");
		query.append(" ORDER BY FECHA_FIN_081 DESC");
		query.append(" FETCH FIRST ROW ONLY");

		List<Object> params = new ArrayList<Object>();
		params.add(tarea.getAnyo());
		params.add(tarea.getNumExp());
		params.add(tarea.getTipoTarea().getId015());
		params.add(tarea.getOrden());

		List<String> recurso = this.getJdbcTemplate().query(query.toString(), this.rwMapRecursoAsig, params.toArray());

		if (!recurso.isEmpty()) {
			return recurso.get(0);
		}
		return "";

	}

	/**
	 * Funcion que calcula las horas previstas para realizar la tarea de traducciÃ³n
	 * o revisiÃ³n, mediante plsql CALCULO_HORAS_TRADUCCION. Genera un String
	 * indicando las horas previstas para realizar la tarea
	 *
	 * @param indRecurso String
	 * @param expediente Expediente
	 * @param idDocs     String
	 * @return String, con el calculo de la horas previstas para realizar la tarea
	 */
	@Override()
	public String calcularHorasPrevistasTradRev(final String indRecurso, Expediente expediente, final String idDocs,
			final BigDecimal idTipoTarea, final Tareas tarea) {

		String horasPrevistas = StringUtils.EMPTY;

		if (expediente != null) {
			final long anyo = expediente.getAnyo();
			final int numExp = expediente.getNumExp();
			final long tipoRevision = this.tieneTipoRevisionInformado(tarea) &&
										this.esTareaConPorcTipoRevisionATenerEnCuenta(idTipoTarea) ? tarea.getTipoRevision().getId018() : 0L;
			horasPrevistas = this.getJdbcTemplate().execute(new CallableStatementCreator() {
				@Override()
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{?= call CALCULO_HORAS_TRADUCCION(?,?,?,?,?,?)}");
					callableStatement.registerOutParameter(1, Types.VARCHAR);
					callableStatement.setString(2, indRecurso);
					callableStatement.setLong(3, anyo);
					callableStatement.setInt(4, numExp);
					callableStatement.setString(5, idDocs);
					callableStatement.setBigDecimal(6, idTipoTarea);
					callableStatement.setLong(7, tipoRevision);
					return callableStatement;
				}
			}, new CallableStatementCallback<String>() {

				@Override
				public String doInCallableStatement(CallableStatement cs) throws SQLException {
					cs.execute();
					return cs.getString(1);
				}
			});
		}

		return horasPrevistas;
	}

	/**
	 * @param tarea Tareas
	 * @return boolean
	 */
	private boolean tieneTipoRevisionInformado(Tareas tarea) {
		return tarea != null && tarea.getTipoRevision() != null && tarea.getTipoRevision().getId018() != null ;
	}
	/**
	 * @param idTipoTarea BigDecimal
	 * @return boolean
	 */
	private boolean esTareaConPorcTipoRevisionATenerEnCuenta(BigDecimal idTipoTarea) {
		return 	TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue() == idTipoTarea.intValue() ||
				TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue() == idTipoTarea.intValue() ||
				TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION_INTERNA.getValue() == idTipoTarea.intValue();
	}

	/**
	 * Funcion que calcula las horas previstas para realizar la tarea de
	 * interpretacion, mediante plsql CALCULO_HORAS_INTERPRETACION. Genera un String
	 * indicando las horas previstas para realizar la tarea
	 *
	 * @param expediente Expediente
	 * @return String, con el calculo de la horas previstas para realizar la tarea
	 */
	@Override()
	public String calcularHorasPrevistasInter(Expediente expediente) {
		String horasPrevistas = StringUtils.EMPTY;

		if (expediente != null) {
			final long anyo = expediente.getAnyo();
			final int numExp = expediente.getNumExp();

			horasPrevistas = this.getJdbcTemplate().execute(new CallableStatementCreator() {
				@Override()
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{?= call CALCULO_HORAS_INTERPRETACION(?,?)}");
					callableStatement.registerOutParameter(1, Types.VARCHAR);
					callableStatement.setLong(2, anyo);
					callableStatement.setInt(3, numExp);
					return callableStatement;
				}
			}, new CallableStatementCallback<String>() {

				@Override
				public String doInCallableStatement(CallableStatement cs) throws SQLException {
					cs.execute();
					return cs.getString(1);
				}
			});
		}

		return horasPrevistas;
	}

	@Override()
	public int comprobarTipoTarea(Tareas tarea) {
		final long anyo = tarea.getAnyo();
		final int numExp = tarea.getNumExp();
		final long idTipoTarea = tarea.getTipoTarea().getId015();

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{?= call COMPROBAR_TIPO_TAREA(?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, anyo);
				callableStatement.setInt(3, numExp);
				callableStatement.setLong(4, idTipoTarea);
				return callableStatement;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});
	}

	@Override()
	public int comprobarFechaFinTarea(Tareas tarea) {
		final long anyo = tarea.getAnyo();
		final int numExp = tarea.getNumExp();
		final long idTipoTarea = tarea.getTipoTarea().getId015();

		Date fecha = tarea.getFechaFin();
		String hora = tarea.getHoraFin();

		final Date fechaFin = DateUtils.obtFechaHoraFormateada(fecha, hora);

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{?= call COMPROBAR_FECHA_FIN_TAREA(?,?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, anyo);
				callableStatement.setInt(3, numExp);
				callableStatement.setLong(4, idTipoTarea);
				callableStatement.setTimestamp(5, new java.sql.Timestamp(fechaFin.getTime()));
				return callableStatement;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});
	}

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	@Override()
	public Object ultimasTareasInterpretacion(JQGridRequestDto jqGridRequestDto, Boolean isCount) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);

		if (isCount) {
			query.append(DaoConstants.SELECT + DaoConstants.COUNT + DaoConstants.ABRIR_PARENTESIS
					+ DaoConstants.DISTINCT + "t1." + DBConstants.ID_TAREA_081 + DaoConstants.CERRAR_PARENTESIS);
		} else {
			query.append(DaoConstants.SELECT);
			query.append("t1." + DBConstants.ID_TAREA_081 + DaoConstants.BLANK + DBConstants.IDTAREA
					+ DaoConstants.SIGNO_COMA);
			query.append("t1." + DBConstants.FECHA_INICIO_081 + DaoConstants.BLANK + DBConstants.FECHAINI
					+ DaoConstants.SIGNO_COMA);
			query.append("TO_CHAR(t1." + DBConstants.FECHA_INICIO_081 + ",'HH24:MI')" + DaoConstants.BLANK
					+ DBConstants.HORAINI + DaoConstants.SIGNO_COMA);
			query.append("t1." + DBConstants.FECHA_FIN_081 + DaoConstants.BLANK + DBConstants.FECHAFIN
					+ DaoConstants.SIGNO_COMA);
			query.append("TO_CHAR(t1." + DBConstants.FECHA_FIN_081 + ",'HH24:MI')" + DaoConstants.BLANK
					+ DBConstants.HORAFIN + DaoConstants.SIGNO_COMA);
			query.append(
					"p1." + DBConstants.NOMBRE_077 + DaoConstants.BLANK + DBConstants.NOMBRE + DaoConstants.SIGNO_COMA);
			query.append(
					"p1." + DBConstants.APEL1_077 + DaoConstants.BLANK + DBConstants.APEL1 + DaoConstants.SIGNO_COMA);
			query.append(
					"p1." + DBConstants.APEL2_077 + DaoConstants.BLANK + DBConstants.APEL2 + DaoConstants.SIGNO_COMA);
			query.append("e1." + DBConstants.HORAS_TAREA_082 + DaoConstants.BLANK + DBConstants.HORASTAREA);
		}
		query.append(DaoConstants.FROM + DBConstants.TABLA_081 + DaoConstants.BLANK + "t1");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_82 + DaoConstants.BLANK + "e1");
		query.append(DaoConstants.ON + "t1." + DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL + "e1."
				+ DBConstants.ID_TAREA_082);
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + "p1");
		query.append(DaoConstants.ON + "t1." + DBConstants.DNI_RECURSO_081 + DaoConstants.SIGNOIGUAL + "p1."
				+ DBConstants.DNI_077);
		query.append(DaoConstants.WHERE);
		query.append(
				"t1." + DBConstants.ESTADO_EJECUCION_081 + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND);
		query.append("t1." + DBConstants.ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND);
		query.append("t1." + DBConstants.RECURSO_ASIGNACION_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue());
		params.add(TipoRecursoEnum.INTERNO.getValue());

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapUltimasInter, params.toArray());
		}
	}

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	@Override()
	public Tareas findConfTarea(Tareas bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelectConfTareas());
		query.append(this.getFromConfTareas());
		query.append(this.getWherePK(bean, params));

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapConfTareas, params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public List<EstudioEstimado> obtenerEstudio(DatosEstimacion datosEstimacion, JQGridRequestDto jqGridRequestDto) {
		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = getParamsEstudio(datosEstimacion);

		StringBuilder query = new StringBuilder();

		query.append("select RECURSO, NUM_RECURSOS, COSTE_HORAS");
		query.append(", COSTE_HORAS_RECURSO from table(OBTENER_ESTUDIO(?,?,?,?,?,?,?,?,?,?))");

		if (jqGridRequestDto != null) {
			Long rows = jqGridRequestDto.getRows();
			Long page = jqGridRequestDto.getPage();
			if ((page != null) && (rows != null)) {
				paginatedQuery.append(
						SELECTALLFROM_ROWNUM + query + ")a) where rnum > " + rows.longValue() * (page.longValue() - 1L)
								+ " and rnum < " + (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				paginatedQuery.append(
						SELECTALLFROM_ROWNUM + query + ")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			} else {
				paginatedQuery.append(query);
			}
		}

		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapEstudio, params.toArray());
	}

	@Override()
	public Long obtenerEstudioCount(DatosEstimacion datosEstimacion) {
		List<Object> params = getParamsEstudio(datosEstimacion);

		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT_COUNT);
		query.append(" from table(OBTENER_ESTUDIO(?,?,?,?,?,?,?,?,?,?))");

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 * @param datosEstimacion
	 * @return
	 */
	private List<Object> getParamsEstudio(DatosEstimacion datosEstimacion) {
		List<Object> params = new ArrayList<Object>();

		long tipoTarea = datosEstimacion.getTiposTarea().getId015();

		Date fechaInicio = datosEstimacion.getFechaInicio();
		String horaInicio = datosEstimacion.getHoraInicio();

		Date fechaIni = DateUtils.obtFechaHoraFormateada(fechaInicio, horaInicio);

		Date fecha = datosEstimacion.getFechaFin();
		String hora = datosEstimacion.getHoraFin();

		Date fechaFin = DateUtils.obtFechaHoraFormateada(fecha, hora);

		int numTotalPal = datosEstimacion.getDatosTareaTrados().getNumTotalPal() == null ? 0
				: datosEstimacion.getDatosTareaTrados().getNumTotalPal();
		int numPalConcor084 = datosEstimacion.getDatosTareaTrados().getNumPalConcor084() == null ? 0
				: datosEstimacion.getDatosTareaTrados().getNumPalConcor084();
		int numPalConcor8594 = datosEstimacion.getDatosTareaTrados().getNumPalConcor8594() == null ? 0
				: datosEstimacion.getDatosTareaTrados().getNumPalConcor8594();
		int numPalConcor9599 = datosEstimacion.getDatosTareaTrados().getNumPalConcor9599() == null ? 0
				: datosEstimacion.getDatosTareaTrados().getNumPalConcor9599();
		int numPalConcor100 = datosEstimacion.getDatosTareaTrados().getNumPalConcor100() == null ? 0
				: datosEstimacion.getDatosTareaTrados().getNumPalConcor100();

		params.add(tipoTarea);
		params.add(fechaIni);
		params.add(fechaFin);
		params.add(numTotalPal);
		params.add(numPalConcor084);
		params.add(numPalConcor8594);
		params.add(numPalConcor9599);
		params.add(numPalConcor100);
		params.add(datosEstimacion.getIdTipoRelevancia());
		params.add(datosEstimacion.getNivUsuario());

		return params;
	}

	@Override()
	public Integer findLoteTareaTradEjec(Tareas bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT t1.ID_LOTE_081");
		query.append(this.getFrom());
		query.append(this.getWherePK(bean, params));
		query.append(" AND t1.ESTADO_EJECUCION_081 = ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Tareas updateIdRequerimiento(Tareas tarea) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.UPDATE);
		query.append(DBConstants.TABLA_81 + DaoConstants.SET);
		query.append(SqlUtils.generarUpdate("ID_REQUERIMIENTO_081", tarea.getIdRequerimiento(), params, true));
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(ID_TAREA_081, tarea.getIdTarea(), params));

		this.getJdbcTemplate().update(query.toString(), params.toArray());

		return tarea;
	}

	@Override()
	public int findLoteTareaRevisionEjec(Tareas bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT t1.ID_LOTE_081 IDLOTE ");
		query.append(getQueryExtEjec(TipoTareaGestionAsociadaEnum.REVISAR.getValue()));

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		params.add(bean.getOrden());

		List<Lotes> listLotes = this.getJdbcTemplate().query(query.toString(), this.rwMapLote, params.toArray());

		int idLote = 0;
		if (!listLotes.isEmpty()) {
			Lotes lote = listLotes.get(Constants.CERO);
			idLote = lote.getIdLote();
		}

		return idLote;
	}

	@Override()
	public BigDecimal findTareaRevisionExtEjec(Tareas bean) {
		return findTareaExtEjec(bean, TipoTareaGestionAsociadaEnum.REVISAR.getValue());
	}

	@Override()
	public BigDecimal findTareaTraduccionExtEjec(Tareas bean) {
		return findTareaExtEjec(bean, TipoTareaGestionAsociadaEnum.TRADUCIR.getValue());
	}

	public BigDecimal findTareaExtEjec(Tareas bean, int tipoTarea) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT t1.ID_TAREA_081 IDTAREA ");
		query.append(getQueryExtEjec(tipoTarea));

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		params.add(bean.getOrden());

		List<Tareas> listTareas = this.getJdbcTemplate().query(query.toString(), this.getRwMapPK(), params.toArray());

		BigDecimal idTarea = new BigDecimal(0);
		if (!listTareas.isEmpty()) {
			Tareas tarea = listTareas.get(Constants.CERO);
			idTarea = tarea.getIdTarea();
		}

		return idTarea;
	}

	/**
	 * @param query
	 */
	private String getQueryExtEjec(int tipoTarea) {
		StringBuilder query = new StringBuilder();
		query.append(this.getFrom());
		query.append(" JOIN AA79B82S01 t2 ON t1.ID_TAREA_081 = t2.ID_TAREA_082 ");
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND t1.ID_TIPO_TAREA_081 = ").append(tipoTarea);
		query.append(" AND t1.ESTADO_EJECUCION_081 = ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND t1.RECURSO_ASIGNACION_081 = ");
		query.append(DaoConstants.SIGNO_APOSTROFO).append(TipoRecursoEnum.EXTERNO.getValue())
				.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(" AND t1.ORDEN_081 < ? ");
		query.append(" ORDER BY t2.FECHA_EJECUCION_082 desc ");

		return query.toString();
	}

	@Override
	public FechaHoraIniFin getDocumentoPresupuestoInterpretacionFechaIniFin(Long anyo, Integer numExp) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("SELECT FECHA_INI_052 FECHA_INI");
		query.append(", TO_CHAR(FECHA_INI_052,'HH24:MI') HORA_INI");
		query.append(", FECHA_FIN_052 FECHA_FIN");
		query.append(", TO_CHAR(FECHA_FIN_052,'HH24:MI') HORA_FIN");
		query.append(" FROM AA79B52T00 WHERE ANYO_052 = ? AND NUM_EXP_052 = ? ");
		params.add(anyo);
		params.add(numExp);

		List<FechaHoraIniFin> fechasList = this.getJdbcTemplate().query(query.toString(), this.getFechaIniFinRwMap(),
				params.toArray());
		return DataAccessUtils.uniqueResult(fechasList);

	}

	@Override()
	public Tareas findDetalleTarea(BigDecimal idTarea, String idioma) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		Locale locale = new Locale(idioma);

		query.append(this.getSelectPorIdioma(locale));
		query.append(", t1.ID_TIPO_REVISION_081 IDTIPOREVISION ");
		query.append(", t18.DESC_ES_018 TIPOREVISIONDESCES ");
		query.append(", t18.DESC_EU_018 TIPOREVISIONDESCEU ");
		// Ejecución tarea
		query.append(DaoConstants.SIGNO_COMA);
		query.append("datosEjecucion.FECHA_EJECUCION_082");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.FECHAEJECUCION);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("TO_CHAR(datosEjecucion.FECHA_EJECUCION_082,'HH24:MI')");
		query.append(DaoConstants.BLANK);
		query.append("HORAEJECUCION");
		// Tipos de tarea
		query.append(DaoConstants.SIGNO_COMA);
		query.append("tipoTarea.DESC_ES_015");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.TIPOTAREAES);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("tipoTarea.DESC_EU_015");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.TIPOTAREAEU);
		query.append(DaoConstants.BLANK);
		// Lote
		query.append(DaoConstants.SIGNO_COMA);
		query.append("lote.NOMBRE_LOTE_029");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.NOMBRELOTE);
		query.append(DaoConstants.BLANK);
		// Empresa proveedora
		query.append(DaoConstants.SIGNO_COMA);
		query.append("empresa.CIF");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.CIFEMPRESA);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("empresa.DESC_AMP_EU");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.DESCAMPEMPEU);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("empresa.DESC_AMP_ES");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.DESCAMPEMPES);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("empresa.DESC_EU");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.DESCEMPEU);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("empresa.DESC_ES");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.DESCEMPES);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.CASE + DaoConstants.WHEN + DaoConstants.ABRIR_PARENTESIS
				+ EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		query.append(DaoConstants.SIGNOIGUAL + " t1.ESTADO_EJECUCION_081 " + DaoConstants.AND + " t1.FECHA_FIN_081 "
				+ DaoConstants.SIGNO_MENOR_QUE + DaoConstants.SYSDATE);
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.THEN + "'" + ActivoEnum.SI.getValue() + "'"
				+ DaoConstants.ELSE);
		query.append("'" + ActivoEnum.NO.getValue() + "'" + DaoConstants.END + DaoConstants.AS
				+ DBConstants.INDRETRASADA + DaoConstants.SIGNO_COMA);
		query.append(" t53." + DBConstants.IND_CONFIDENCIAL_053 + DaoConstants.BLANK + DBConstants.INDCONFIDENCIAL);
		query.append(this.getFrom());
		// Ejecución tarea
		query.append(DaoConstants.LEFT_JOIN);
		query.append("AA79B82S01 datosEjecucion");
		query.append(DaoConstants.ON);
		query.append("t1.ID_TAREA_081 = datosEjecucion.ID_TAREA_082");
		query.append(DaoConstants.BLANK);
		// Tipos de tarea
		query.append(DaoConstants.LEFT_JOIN);
		query.append("AA79B15S01 tipoTarea");
		query.append(DaoConstants.ON);
		query.append("t1.ID_TIPO_TAREA_081 = tipoTarea.ID_015");
		query.append(DaoConstants.BLANK);
		// Lote
		query.append(DaoConstants.LEFT_JOIN);
		query.append("AA79B29S01 lote");
		query.append(DaoConstants.ON);
		query.append("t1.ID_LOTE_081 = lote.ID_LOTE_029");
		query.append(DaoConstants.BLANK);
		// Empresa proveedora
		query.append(DaoConstants.LEFT_JOIN);
		query.append("X54JAPI_EMPRESAS_PROV empresa");
		query.append(DaoConstants.ON);
		query.append("lote.ID_EMPRESA_PROV_029 = empresa.CODIGO");
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.AND);
		query.append("lote.TIPO_ENTIDAD_029 = empresa.TIPO");
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_53 + " t53 ");
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081 + DaoConstants.SIGNOIGUAL
				+ "t53." + DBConstants.ANYO_053);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL + "t53." + DBConstants.NUM_EXP_053);
		query.append(" LEFT JOIN AA79B18S01 t18 ON t1.ID_TIPO_REVISION_081 = t18.ID_018 ");
		query.append(DaoConstants.WHERE);
		query.append("t1.ID_TAREA_081");
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(idTarea);

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapDetalleTarea,
				params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public TiposRevision findTipoRevisionTareaRel(BigDecimal idTarea) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(
				"SELECT t1.ID_TIPO_REVISION_081 IDTIPOREVISION, t18.DESC_ES_018 TIPOREVISIONDESCES, t18.DESC_EU_018 TIPOREVISIONDESCEU ");
		query.append("FROM AA79B81S01 t1 ");
		query.append("LEFT JOIN AA79B18S01 t18 ON t1.ID_TIPO_REVISION_081 = t18.ID_018 ");
		query.append("WHERE t1.ID_TAREA_081 = (SELECT ID_TAREA_REL_081 FROM AA79B81S01 WHERE ID_TAREA_081 = ?)");
		params.add(idTarea);

		List<TiposRevision> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTipoRevisionTareaRel,
				params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public Tareas findTareaTrados(Long anyo, Integer numExp) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(this.getSelect());
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE);
		query.append(ANYO_081);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(NUM_EXP_081);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(ID_TIPO_TAREA_081);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());

		params.add(anyo);
		params.add(numExp);

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapDatosTareaTrados,
				params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public void reabrirTarea(BigDecimal idTarea) {

		StringBuilder query = new StringBuilder("UPDATE AA79B81S01");
		query.append(" SET ESTADO_EJECUCION_081=?");
		query.append(" WHERE ID_TAREA_081=?");

		this.getJdbcTemplate().update(query.toString(), EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(),
				idTarea);

		query = new StringBuilder("UPDATE AA79B82S01");
		query.append(" SET FECHA_EJECUCION_082=?");
		query.append(" WHERE ID_TAREA_082=?");

		this.getJdbcTemplate().update(query.toString(), null, idTarea);

	}

	@Override()
	public List<Tareas> findTareas(List<String> listSelectedIds) {
		List<Tareas> beanList = null;
		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			StringBuilder query = new StringBuilder(this.getSelect());
			query.append(this.getFrom());
			query.append(DaoConstants.WHERE);
			query.append(DBConstants.ESTADO_EJECUCION_081);
			query.append(DaoConstants.SIGNO_DISTINTO);
			query.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			query.append(DaoConstants.AND);
			query.append(DBConstants.ID_TAREA_081);
			query.append(DaoConstants.IN);
			query.append(DaoConstants.ABRIR_PARENTESIS);
			query.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			query.append(DaoConstants.CERRAR_PARENTESIS);

			beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTareas);
		}

		return beanList;
	}

	@Override()
	public Tareas findTarea(Tareas bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom());
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhereTarea(bean, params));

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTareas, params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public BigDecimal findIdTareaPorRequerimiento(Long idRequerimiento) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(SELECT_IDTAREA);
		query.append(FROM_TABLA_81);
		query.append(DaoConstants.WHERE + " ID_REQUERIMIENTO_081 = ?");
		params.add(idRequerimiento);
		try {
			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), BigDecimal.class);
		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			TareasDaoImpl.LOGGER.error("No existe tarea asociada al requerimiento", erdae);
			return new BigDecimal(0);
		}

	}

	@Override()
	public List<Tareas> findTareasRelEjecutadas(BigDecimal idTarea) {
		List<Tareas> beanList = null;
		if (idTarea != null) {
			List<Object> params = new ArrayList<Object>();
			StringBuilder query = new StringBuilder(this.getSelect());
			query.append(this.getFrom());
			query.append(DaoConstants.WHERE);
			query.append(DBConstants.ESTADO_EJECUCION_081);
			query.append(DaoConstants.SIGNOIGUAL);
			query.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			query.append(DaoConstants.AND);
			query.append(DBConstants.ID_TAREA_REL_081);
			query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

			params.add(idTarea);

			beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTareas);
		}

		return beanList;
	}

	@Override()
	public int findTareasAutomaticasCount(List<String> listSelectedIds, String tipoExpediente) {
		int rst = 0;
		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			List<Object> params = new ArrayList<Object>();
			StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
			query.append(this.getFromTareasAutomaticas());
			query.append(this.getWhereTareasAutomaticas(params, listSelectedIds, tipoExpediente));

			rst = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
		}

		return rst;
	}

	private String getWhereTareasAutomaticas(List<Object> params, List<String> listSelectedIds, String tipoExpediente) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);

		where.append(GenericoDaoImpl.DEFAULT_WHERE);

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			where.append(DaoConstants.AND);
			where.append(DaoConstants.T1_MINUSCULA);
			where.append(DaoConstants.SIGNO_PUNTO);
			where.append(DBConstants.ID_TAREA_081);
			where.append(DaoConstants.IN);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}

		where.append(SqlUtils.generarWhereIgual(
				DBConstants.TIPOSTAREA_MINUSCULAS + DaoConstants.SIGNO_PUNTO + DBConstants.IND_AUTOMATICA_015,
				Constants.SI, params));

		if (TipoExpedienteEnum.TRADUCCION.getValue().equals(tipoExpediente)) {
			where.append(SqlUtils.generarWhereIgual(
					DBConstants.TIPOSTAREA_MINUSCULAS + DaoConstants.SIGNO_PUNTO + DBConstants.IND_TRADUCCION_015,
					Constants.SI, params));
		} else if (TipoExpedienteEnum.REVISION.getValue().equals(tipoExpediente)) {
			where.append(SqlUtils.generarWhereIgual(
					DBConstants.TIPOSTAREA_MINUSCULAS + DaoConstants.SIGNO_PUNTO + DBConstants.IND_REVISION_015,
					Constants.SI, params));
		} else if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(tipoExpediente)) {
			where.append(SqlUtils.generarWhereIgual(
					DBConstants.TIPOSTAREA_MINUSCULAS + DaoConstants.SIGNO_PUNTO + DBConstants.IND_INTERPRETACION_015,
					Constants.SI, params));
		}

		return where.toString();
	}

	@Override
	public Tareas findTareaRevisionExterna(Long anyo, Integer numExp) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(this.getSelect());
		query.append(this.getFrom());
		query.append(DaoConstants.LEFT_JOIN);
		query.append(DBConstants.TABLA_51);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(ANYO_081);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(NUM_EXP_081);
		query.append(DaoConstants.WHERE);
		query.append(ANYO_081);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(NUM_EXP_081);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(ID_TIPO_TAREA_081);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(TipoTareaGestionAsociadaEnum.REVISAR.getValue());
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.RECURSO_ASIGNACION_081);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.SIGNO_APOSTROFO + TipoRecursoEnum.EXTERNO.getValue() + DaoConstants.SIGNO_APOSTROFO);

		params.add(anyo);
		params.add(numExp);

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapPK, params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Integer eliminar(BigDecimal idTarea, Integer intvalueTabla) {
		List<Object> paramsDelete = new ArrayList<Object>();
		StringBuilder queryDelete = new StringBuilder();
		queryDelete.append(DaoConstants.DELETE + DaoConstants.FROM + " AA79B" + intvalueTabla + "S01 ");
		queryDelete.append(DaoConstants.WHERE + " ID_TAREA_094 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		paramsDelete.add(idTarea);
		return Integer.valueOf(this.getJdbcTemplate().update(queryDelete.toString(), paramsDelete.toArray()));
	}

	@Override
	public Tareas findTareaTrados(List<Tareas> listTareas) {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TAREA_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDTAREA);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(ANYO_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.ANYO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(NUM_EXP_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.NUMEXP);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_81);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.WHERE);
		query.append(ID_TIPO_TAREA_081);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TAREA_081);
		query.append(DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(Utils.obtenerListIds(listTareas));
		query.append(DaoConstants.CERRAR_PARENTESIS);

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTareaTrados);

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public BigDecimal findTareaRelacionada(Tareas tarea) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(
				"SELECT T1.ID_TAREA_REL_081 IDTAREAREL FROM AA79B81S01 T1 WHERE T1.ID_TAREA_081 =?");
		params.add(tarea.getIdTarea());
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), BigDecimal.class);
	}

	@Override()
	public void removeObsrvRechazo(BigDecimal idTarea) {
		List<Tareas> listTareas = new ArrayList<Tareas>();
		Tareas tarea = new Tareas();
		tarea.setIdTarea(idTarea);
		listTareas.add(tarea);

		removeObsrvRechazo(listTareas);
	}

	@Override()
	public void removeObsrvRechazo(List<Tareas> listTareas) {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.DELETE);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_84);
		query.append(DaoConstants.WHERE);
		query.append(DBConstants.ID_TAREA_084);
		query.append(DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(Utils.obtenerListIds(listTareas));
		query.append(DaoConstants.CERRAR_PARENTESIS);

		this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public List<Tareas> tareasAsociadasASubDoc(Long idRequerimiento) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TAREA_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDTAREA);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.ANYO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.NUMEXP);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TIPO_TAREA_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDTIPOTAREA);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.RECURSO_ASIGNACION_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.RECURSOASIGNACION);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.DNI_RECURSO_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.DNIRECURSO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_LOTE_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDLOTE);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ESTADO_EJECUCION_081);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.ESTADOEJECID);
		query.append(DaoConstants.SIGNO_COMA);
		// Tipos de tarea
		query.append(DaoConstants.T4_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.DESC_ES_015);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.TIPOTAREAES);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T4_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.DESC_EU_015);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.TIPOTAREAEU);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_83);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.JOIN);
		query.append(DBConstants.TABLA_66);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_DOC_083);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_DOC_066);
		query.append(DaoConstants.JOIN);
		query.append(DBConstants.TABLA_81);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TAREA_083);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TAREA_081);
		query.append(DaoConstants.AND);
		query.append(DBConstants.ID_TIPO_TAREA_081);
		query.append(DaoConstants.SIGNO_DISTINTO_INTERROGACION);
		query.append(DaoConstants.LEFT_JOIN);
		query.append(DBConstants.TABLA_15);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T4_MINUSCULA);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TIPO_TAREA_081);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T4_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_015);
		query.append(DaoConstants.WHERE);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_066);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND T3.ESTADO_ASIGNACION_081 IN (?,?)");
		params.add(TipoTareaGestionAsociadaEnum.ESTUDIAR_SUBSANACION.getValue());
		params.add(idRequerimiento);
		params.add(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue());
		params.add(EstadoAceptacionTareaEnum.ACEPTADA.getValue());

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSubDoc, params.toArray());
	}

	@Override
	public Integer actualizarFechaFinalIzo(EntradaDatosSolicitud bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.UPDATE + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SET + DaoConstants.ABRIR_PARENTESIS + " t1.FECHA_FINAL_IZO_053 ");
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNOIGUAL + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.SELECT + " FECHA_ENTREGA_064 " + DaoConstants.FROM + DBConstants.TABLA_64
				+ DaoConstants.BLANK);
		query.append(DaoConstants.WHERE + " ID_064 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.WHERE + " t1.ANYO_053 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + " t1.NUM_EXP_053 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(bean.getIdRequerimiento());
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		return this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override()
	public String calcularHorasEntreFechas(Tareas tarea) {
		Date fecha = tarea.getFechaIni();
		String hora = tarea.getHoraIni();

		final Date fechaInicio = DateUtils.obtFechaHoraFormateada(fecha, hora);

		fecha = tarea.getFechaFin();
		hora = tarea.getHoraFin();
		final Date fechaFin = DateUtils.obtFechaHoraFormateada(fecha, hora);

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{?= call HORAS_ENTRE_FECHAS_CALENDAR(?,?)}");
				callableStatement.registerOutParameter(1, Types.VARCHAR);
				callableStatement.setTimestamp(2, new java.sql.Timestamp(fechaInicio.getTime()));
				callableStatement.setTimestamp(3, new java.sql.Timestamp(fechaFin.getTime()));
				return callableStatement;
			}
		}, new CallableStatementCallback<String>() {

			@Override
			public String doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getString(1);
			}
		});

	}

	@Override()
	public List<Tareas> findTareasRangoFechas(Tareas tarea) {
		return this.findTareasRangoFechas(tarea, Constants.CERO);
	}

	/**
	 *
	 * @param tarea
	 * @param esInterpretacion 0-sin tipo. 1- tipo interpretación. 2- todos los
	 *                         tipos menos interpretación
	 * @return
	 */
	@Override()
	public List<Tareas> findTareasRangoFechas(Tareas tarea, int tipoTarea) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		// Tipos de tarea
		query.append(", DESC_ES_015 TIPOTAREAES ");
		query.append(", DESC_EU_015 TIPOTAREAEU ");
		query.append(getFromTareasAutomaticas());
		query.append(" WHERE DNI_RECURSO_081 = ? ");
		query.append(DaoConstants.AND + "ESTADO_EJECUCION_081 != ")
				.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		if (tipoTarea == 1) {
			// solo tareas de interpretacion
			query.append(DaoConstants.AND + "ID_TIPO_TAREA_081 = ")
					.append(TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue());
		}
		if (tipoTarea == 2) {
			// solo tareas DISTINTAS de interpretacion
			query.append(DaoConstants.AND + "ID_TIPO_TAREA_081 != ")
					.append(TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue());

		}

		query.append(DaoConstants.AND
				+ " ( (?  BETWEEN TO_DATE(TO_CHAR( FECHA_INICIO_081,'DD/mm/YY'),'DD/mm/YY') AND  TO_DATE(TO_CHAR( FECHA_FIN_081,'DD/mm/YY'),'DD/mm/YY') ) ");
		query.append(
				" OR (?  BETWEEN TO_DATE( TO_CHAR( FECHA_INICIO_081,'DD/mm/YY'),'DD/mm/YY') AND  TO_DATE(TO_CHAR( FECHA_FIN_081,'DD/mm/YY'),'DD/mm/YY') ) ");
		query.append(
				" OR (? <= TO_DATE(TO_CHAR( FECHA_INICIO_081,'DD/mm/YY'),'DD/mm/YY') AND ? >= TO_DATE(TO_CHAR( FECHA_FIN_081,'DD/mm/YY'),'DD/mm/YY') ) ) ");

		params.add(tarea.getPersonaAsignada().getDni());
		params.add(tarea.getFechaIni());
		params.add(tarea.getFechaFin());
		params.add(tarea.getFechaIni());
		params.add(tarea.getFechaFin());

		return this.getJdbcTemplate().query(query.toString(), this.rwMapTareasAEliminar, params.toArray());
	}

	@Override()
	public long comprobarTareasAEliminar(List<String> listSelectedIds) {
		int rst = 0;

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			List<Object> params = new ArrayList<Object>();

			StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
			query.append(DaoConstants.FROM + DBConstants.TABLA_081 + DaoConstants.WHERE);
			query.append(DBConstants.ID_TAREA_081 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
			query.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
			query.append(DBConstants.ESTADO_EJECUCION_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.OR + DaoConstants.ABRIR_PARENTESIS);
			query.append(DBConstants.ESTADO_ASIGNACION_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + DBConstants.RECURSO_ASIGNACION_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.CERRAR_PARENTESIS);
			query.append(DaoConstants.OR + DBConstants.ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.OR + DaoConstants.ABRIR_PARENTESIS);
			query.append(DBConstants.ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + DBConstants.ID_TAREA_REL_081 + DaoConstants.IS_NOT_NULL);
			query.append(DaoConstants.CERRAR_PARENTESIS);

			query.append(DaoConstants.CERRAR_PARENTESIS);
			params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			params.add(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
			params.add(TipoRecursoEnum.INTERNO.getValue());
			params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue());
			params.add(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue());

			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
		}

		return rst;
	}

	@Override()
	public Tareas updateFechaAceptacion(Tareas tareas, boolean cambioTipoRecurso) {

		if (QueryUtils.isActualizarFechaAceptacion(tareas, cambioTipoRecurso)) {
			StringBuilder query = new StringBuilder(DaoConstants.UPDATE + DBConstants.TABLA_081);
			query.append(DaoConstants.SET + DBConstants.FECHA_ACEPTACION_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.WHERE + DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);

			this.getJdbcTemplate().update(query.toString(), tareas.getFechaAceptacion(), tareas.getIdTarea());
		}

		return tareas;
	}

	@Override()
	public List<Tareas> getTareasAsignadasNoEjecutadas(Long anyo, Integer numExp) {

		StringBuilder queryIdsTareasPedtesEjec = new StringBuilder(DaoConstants.SELECT
				+ " T1.ID_TAREA_081 IDTAREA , T1.ANYO_081 ANYO, T1.NUM_EXP_081 NUMEXP, ID_TIPO_TAREA_081 IDTIPOTAREA, T2.DESC_EU_015 TIPOTAREAEU, ");
		queryIdsTareasPedtesEjec.append(
				" T2.DESC_ES_015 TIPOTAREAES,T1.RECURSO_ASIGNACION_081 RECURSOASIGNACION,T1.DNI_RECURSO_081 DNIRECURSO,ID_LOTE_081 IDLOTE, T1.ESTADO_EJECUCION_081 ESTADOEJECID ");
		queryIdsTareasPedtesEjec.append(
				DaoConstants.FROM + " AA79B81S01 T1 LEFT JOIN AA79B15S01 T2 ON T2.ID_015 = T1.ID_TIPO_TAREA_081 ");
		queryIdsTareasPedtesEjec
				.append(DaoConstants.WHERE + " T1.ANYO_081 = ? AND T1.NUM_EXP_081 = ? AND T1.ESTADO_EJECUCION_081 <> ")
				.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		queryIdsTareasPedtesEjec.append(" AND (T1.ESTADO_ASIGNACION_081 = ")
				.append(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue())
				.append(" OR T1.ESTADO_ASIGNACION_081 = ").append(EstadoAceptacionTareaEnum.ACEPTADA.getValue())
				.append(")");

		List<Object> params = new ArrayList<Object>();
		params.add(anyo);
		params.add(numExp);

		return this.getJdbcTemplate().query(queryIdsTareasPedtesEjec.toString(), this.rwMapSubDoc, params.toArray());

	}

	@Override()
	public void desasignarTareasPdtesEjec(Long anyo, Integer numExp) {

		StringBuilder queryIdsTareasPedtesEjec = new StringBuilder(
				DaoConstants.SELECT + ID_TAREA_081 + DaoConstants.FROM + DBConstants.TABLA_081 + DaoConstants.T1
						+ DaoConstants.WHERE + ANYO_081 + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND
						+ NUM_EXP_081 + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + ESTADO_EJECUCION_081
						+ DaoConstants.SIGNO_DISTINTO + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		List<Object> params = new ArrayList<Object>();
		params.add(anyo);
		params.add(numExp);

		// Borro las ausencias del calendario correspondientes a las tareas, y
		// sus observ se borran en cascada
		StringBuilder query = new StringBuilder(DaoConstants.DELETE + DBConstants.TABLA_025);
		query.append(
				DaoConstants.WHERE + DBConstants.ID_TAREA_INTER_025 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
		query.append(queryIdsTareasPedtesEjec);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		this.getJdbcTemplate().update(query.toString(), params.toArray());

		// Borro las tareas relacionadas con las tareas no ejecutadas ( y en
		// cascada la T82)
		query = new StringBuilder(DaoConstants.DELETE + DBConstants.TABLA_081);
		query.append(DaoConstants.WHERE + " ID_TAREA_REL_081 " + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
		query.append(queryIdsTareasPedtesEjec);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		this.getJdbcTemplate().update(query.toString(), params.toArray());

		// Actualizar las tareas
		query = new StringBuilder(DaoConstants.UPDATE + DBConstants.TABLA_081);
		query.append(" SET RECURSO_ASIGNACION_081=''");
		query.append(", DNI_RECURSO_081=''");
		query.append(", TIPO_ENTIDAD_081=''");
		query.append(", ID_ENTIDAD_081=''");
		query.append(", ID_LOTE_081=''");
		query.append(", ESTADO_ASIGNACION_081=1");
		query.append(", FECHA_ASIGNACION_081=''");
		query.append(", FECHA_ACEPTACION_081=''");
		query.append(DaoConstants.WHERE + DBConstants.ID_TAREA_081 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
		query.append(queryIdsTareasPedtesEjec);
		query.append(DaoConstants.CERRAR_PARENTESIS);

		this.getJdbcTemplate().update(query.toString(), params.toArray());

	}

	@Override()
	public long findAllCountTareasEjecutadas(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_EJECUCION_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override()
	public BigDecimal findTareaEjecutadaPosttraduccionBOE(Expediente exp) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder("SELECT T1.ID_TAREA_081 ");
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_EJECUCION_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + " t1.ID_TIPO_TAREA_081 = ?");

		params.add(exp.getAnyo());
		params.add(exp.getNumExp());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(TipoTareaGestionAsociadaEnum.POST_TRADUCCION_BOE.getValue());

		BigDecimal idTarea = new BigDecimal(0);
		try {
			idTarea = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), BigDecimal.class);
		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			TareasDaoImpl.LOGGER.info("findTareaEjecutadaPosttraduccionBOE: no existe tarea", erdae);
		}

		return idTarea;
	}

	@Override()
	public long findAllCountTareasAsignadas(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_EJECUCION_081
				+ DaoConstants.SIGNO_DISTINTO + DaoConstants.SIGNOINTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_ASIGNACION_081
				+ DaoConstants.SIGNO_DISTINTO + DaoConstants.SIGNOINTERROGACION);

		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override()
	public int findCountTareasRevExt(Tareas tareas) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.RECURSO_ASIGNACION_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(TipoTareaGestionAsociadaEnum.REVISAR.getValue());
		params.add(TipoRecursoEnum.EXTERNO.getValue());

		if (tareas.getIdTarea() != null) {
			query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TAREA_081
					+ DaoConstants.SIGNO_DISTINTO_INTERROGACION);
			params.add(tareas.getIdTarea());
		}

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override()
	public int countTareasNoConfProvSinEjec(Tareas tareas, String documentosSelect) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(DaoConstants.JOIN + DBConstants.TABLA_83 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TAREA_081
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TAREA_083);
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_EJECUCION_081
				+ DaoConstants.SIGNO_DISTINTO + DaoConstants.SIGNOINTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TAREA_REL_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_DOC_083 + DaoConstants.IN
				+ DaoConstants.ABRIR_PARENTESIS + documentosSelect + DaoConstants.CERRAR_PARENTESIS);

		params.add(tareas.getAnyo());
		params.add(tareas.getNumExp());
		params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(tareas.getIdTareaRel());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override()
	public int countTareasNoConfClienteSinEjec(Expediente expediente) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_EJECUCION_081
				+ DaoConstants.SIGNO_DISTINTO + DaoConstants.SIGNOINTERROGACION);

		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public void procActDatosPagoProvPL(final DatosPagoProveedores datosPagoProveedores) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.VARCHAR));

		if (datosPagoProveedores.getNumPalRecargoFormato() == null) {
			datosPagoProveedores.setNumPalRecargoFormato(0);
		}

		if (datosPagoProveedores.getPorRecargoApremio() == null) {
			datosPagoProveedores.setPorRecargoApremio(new Long(0));
		}

		if (datosPagoProveedores.getPorPenalizacion() == null) {
			datosPagoProveedores.setPorPenalizacion(new Long(0));
		}

		try {
			this.getJdbcTemplate().call(new CallableStatementCreator() {
				@Override()
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{call ACT_DATOS_PAGO_PROV(?,?,?,?,?,?,?,?)}");
					callableStatement.setBigDecimal(1, datosPagoProveedores.getIdTarea());
					callableStatement.setString(2, datosPagoProveedores.getIndRecargoFormato());
					callableStatement.setInt(3, datosPagoProveedores.getNumPalRecargoFormato());
					callableStatement.setString(4, datosPagoProveedores.getIndRecargoApremio());
					callableStatement.setLong(5, datosPagoProveedores.getPorRecargoApremio());
					callableStatement.setString(6, datosPagoProveedores.getIndPenalizacion());
					callableStatement.setLong(7, datosPagoProveedores.getPorPenalizacion());

					if (datosPagoProveedores.getNivelCalidad() == null) {
						callableStatement.setNull(8, Types.VARCHAR);
					} else {
						callableStatement.setString(8, datosPagoProveedores.getNivelCalidad());
					}

					return callableStatement;
				}
			}, paramList);
		} catch (Exception e) {
			TareasDaoImpl.LOGGER.info("Error: ", e);
		}

	}

	@Override
	public Integer procCrearTareasRelacionadasPL(final BigDecimal idTarea) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlParameter(Types.VARCHAR));

		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		final String ip = credentials.getIpPuesto();
		final String usuario = credentials.getNif();

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{? = call CREAR_TAREAS_RELACIONADAS (?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setBigDecimal(2, idTarea);
				callableStatement.setString(3, ip);
				callableStatement.setString(4, usuario);
				return callableStatement;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});
	}

	@Override()
	public int comprobarReaperturaTarea(final BigDecimal idTarea) {

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{?= call COMPROBAR_REAPERTURA_TAREA(?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setBigDecimal(2, idTarea);
				return callableStatement;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});
	}

	@Override()
	public Tareas findDatosCorreccionesProv(Tareas bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(DaoConstants.SELECT);
		query.append(DBConstants.ID_FICHERO_088 + DaoConstants.BLANK + DBConstants.IDFICHERO + DaoConstants.SIGNO_COMA);
		query.append(DBConstants.NOMBRE_FICHERO_088 + DaoConstants.BLANK + DBConstants.NOMBREFICHERO
				+ DaoConstants.SIGNO_COMA);
		query.append(DBConstants.IND_ENCRIPTADO_088 + DaoConstants.BLANK + DBConstants.INDENCRIPTADO
				+ DaoConstants.SIGNO_COMA);
		query.append(DBConstants.OBSERV_081 + DaoConstants.BLANK + DBConstants.OBSERV);
		query.append(getFrom());
		// JOIN con la tabla B1
		query.append(DaoConstants.JOIN + DBConstants.TABLA_B1 + DaoConstants.ON + DBConstants.ID_TAREA_081
				+ DaoConstants.SIGNOIGUAL + DBConstants.ID_TAREA_0B1);
		// JOIN con la tabla 88
		query.append(DaoConstants.JOIN + DBConstants.TABLA_88 + DaoConstants.ON + DBConstants.ID_FICHERO_0B1
				+ DaoConstants.SIGNOIGUAL + DBConstants.ID_FICHERO_088);
		query.append(this.getWherePK(bean, params));

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapCorreccionesProv,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Long calcularFechaIni(Expediente expediente, String orden, BigDecimal idTarea) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(DaoConstants.SELECT);
		query.append(" NVL(FECHA_FIN_081,SYSDATE) ");
		query.append(" FROM AA79B81T00 ");
		query.append(" WHERE Anyo_081 = ? AND Num_Exp_081 = ? AND ORDEN_081 = ?  ");
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(Integer.parseInt(orden) - 1);
		if (idTarea != null) {
			query.append(" AND ID_TAREA_081 <> ? ");
			params.add(idTarea);
		}
		query.append(" order by fecha_fin_081 desc");
		query.append(" fetch first row only ");

		try {
			Date nuevaFechaIni = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Date.class);
			return nuevaFechaIni.getTime();
		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			if (LOGGER.isTraceEnabled()) {
				TareasDaoImpl.LOGGER.trace("NO existe tarea previa por lo que se devuelve la fecha actual");
			}
			return Calendar.getInstance().getTime().getTime();
		}

	}

	@Override
	public Long calcularFechaFinTarea(final Tareas tarea) {
		Date fecha = tarea.getFechaIni();
		String hora = tarea.getHoraIni();

		final Date fechaInicio = DateUtils.obtFechaHoraFormateada(fecha, hora);

		Timestamp fechaFin = this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{?= call CALCULAR_FECHA_FIN_TAREA(?,?)}");
				callableStatement.registerOutParameter(1, Types.DATE);
				callableStatement.setTimestamp(2, new java.sql.Timestamp(fechaInicio.getTime()));
				callableStatement.setString(3, tarea.getHorasPrevistas());
				return callableStatement;
			}
		}, new CallableStatementCallback<Timestamp>() {

			@Override
			public Timestamp doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getTimestamp(1);
			}
		});
		return fechaFin.getTime();
	}

	@Override
	public OrigenTareaNoConformidad obtenerOrigenNoConformidad(BigDecimal idTarea) {
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append(" SELECT t1.ID_TIPO_TAREA_081 IDTIPOTAREA, ");
		query.append(" t1.ID_TAREA_081 IDTAREA ");
		query.append(" FROM AA79B81S01 t1 ");
		query.append(" JOIN AA79B81S01 t2 ");
		query.append(" ON t1.ID_TAREA_081 = t2.ID_TAREA_REL_081 ");
		query.append(" AND t2.ID_TAREA_081 = ? ");
		params.add(idTarea);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(),
				this.rwMapOrigenTareaNoConformidad);
	}

	@Override
	public int countTareasNoConfRevInternaSinEjec(Tareas tarea, String documentosSelect) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(" SELECT COUNT(1) ");
		query.append(this.getFrom());
		query.append(" JOIN AA79B83S01 t2 ON t1.ID_TAREA_081 = t2.ID_TAREA_083 ");
		query.append(" WHERE t1.ANYO_081 = ? ");
		query.append(" AND t1.NUM_EXP_081 = ? ");
		query.append(" AND t1.ID_TIPO_TAREA_081 = ? ");
		query.append(" AND t1.ESTADO_EJECUCION_081 <> ? ");
		query.append(" AND t1.ID_TAREA_REL_081 = ? ");
		query.append(" AND t2.ID_DOC_083 IN ( " + documentosSelect + " )");

		params.add(tarea.getAnyo());
		params.add(tarea.getNumExp());
		params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_TRADUCTOR.getValue());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(tarea.getIdTareaRel());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public PersonalIZO findPersonaAsignadaTareaTradEjec(Tareas bean) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(" SELECT t1.DNI_RECURSO_081 DNI, ");
		query.append(" t1.TIPO_ENTIDAD_081 TIPOENTIDAD, ");
		query.append(" t1.ID_ENTIDAD_081 IDENTIDAD ");
		query.append(this.getFrom());
		query.append(this.getWherePK(bean, params));
		query.append(" AND t1.ESTADO_EJECUCION_081 = ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		List<PersonalIZO> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTraductorAsignado,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Integer comprobarFechaFinTareasNoSuperaFechaFinExp(Expediente expediente) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(" SELECT COUNT(1) ");
		query.append(" FROM AA79B81T00 ");
		query.append(" WHERE 1=1 ");
		query.append(SqlUtils.generarWhereIgual("NUM_EXP_081", expediente.getNumExp(), params));
		query.append(SqlUtils.generarWhereIgual("ANYO_081", expediente.getAnyo(), params));
		query.append(" AND COMPROBAR_FECHA_FIN_TAREA(ANYO_081, NUM_EXP_081, ID_TIPO_TAREA_081, FECHA_FIN_081) = 1 ");
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Tareas comprobarEstadoEjecucionTarea(Tareas tarea) {
		List<Object> paramsCEET = new ArrayList<Object>();

		StringBuilder queryCEET = new StringBuilder(" SELECT t1.ESTADO_EJECUCION_081 " + DBConstants.ESTADOEJECID);
		queryCEET.append(" , t1.ANYO_081 " + DBConstants.ANYO);
		queryCEET.append(" , t1.NUM_EXP_081 " + DBConstants.NUMEXP);
		queryCEET.append(" , t1.ID_TAREA_081 " + DBConstants.IDTAREA);
		queryCEET.append(" FROM AA79B81S01 t1 ");
		queryCEET.append(DaoConstants.WHERE_1_1);
		queryCEET.append(SqlUtils.generarWhereIgual("t1.ANYO_081", tarea.getAnyo(), paramsCEET));
		queryCEET.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_081", tarea.getNumExp(), paramsCEET));
		queryCEET.append(SqlUtils.generarWhereIgual("t1.ID_TAREA_081", tarea.getIdTarea(), paramsCEET));
		List<Tareas> lTarea = this.getJdbcTemplate().query(queryCEET.toString(), paramsCEET.toArray(),
				this.rwMapTareaEstado);
		return DataAccessUtils.uniqueResult(lTarea);
	}

	@Override()
	public Integer comprobarTareasPendientesTradRevUsuario(Tareas tareas) {
		List<Object> params;
		StringBuilder query;
		params = new ArrayList<Object>();

		query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(" JOIN AA79B51S01 t2 ON t1.ANYO_081 = t2.ANYO_051 AND t1.NUM_EXP_081 = t2.NUM_EXP_051");
		query.append(" JOIN AA79B59S01 t59 ON t2.anyo_051 = t59.anyo_059  AND t2.num_exp_051 = t59.num_exp_059 AND t2.estado_bitacora_051 = t59.id_estado_bitacora_059");
		query.append(" WHERE t1.ESTADO_EJECUCION_081 <> ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND t1.ESTADO_ASIGNACION_081 <> ").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		query.append(" AND t1.DNI_RECURSO_081 = ?");
		query.append(" AND t1.recurso_asignacion_081 = '").append(TipoRecursoEnum.INTERNO.getValue()).append("'");
		query.append(" AND t2.ID_TIPO_EXPEDIENTE_051 <> '").append(TipoExpedienteEnum.INTERPRETACION.getValue()).append("'");
		query.append(" AND t59.id_estado_exp_059 IN ( ").append(EstadoExpedienteEnum.EN_CURSO.getValue()).append(",").append(EstadoExpedienteEnum.CERRADO.getValue()).append(")");
		params.add(tareas.getPersonaAsignada().getDni());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);

	}


	@Override()
	public Integer comprobarTareasPendientesInterpretacion(Tareas tareas) {
		List<Object> params;
		StringBuilder query;
		params = new ArrayList<Object>();

		query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getFrom());
		query.append(" JOIN AA79B51S01 t2 ON t1.ANYO_081 = t2.ANYO_051 AND t1.NUM_EXP_081 = t2.NUM_EXP_051");
		query.append(" JOIN AA79B59S01 t59 ON t2.anyo_051 = t59.anyo_059  AND t2.num_exp_051 = t59.num_exp_059 AND t2.estado_bitacora_051 = t59.id_estado_bitacora_059");
		query.append(" WHERE t1.ESTADO_EJECUCION_081 <> ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND t1.ESTADO_ASIGNACION_081 <> ").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		query.append(" AND t1.DNI_RECURSO_081 = ?");
		query.append(" AND t1.recurso_asignacion_081 = '").append(TipoRecursoEnum.INTERNO.getValue()).append("'");
		query.append(" AND t2.ID_TIPO_EXPEDIENTE_051 = '").append(TipoExpedienteEnum.INTERPRETACION.getValue()).append("'");
		query.append(" AND t59.id_estado_exp_059 IN ( ").append(EstadoExpedienteEnum.EN_CURSO.getValue()).append(",").append(EstadoExpedienteEnum.CERRADO.getValue()).append(")");
		params.add(tareas.getPersonaAsignada().getDni());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Tareas getAsignadoTareaRevisionAnterior(Tareas tarea, List<Integer> tiposTarea) {
		List<Object> paramsATRA = new ArrayList<Object>();
		StringBuilder queryATRA = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		queryATRA.append(" SELECT t1.ID_TAREA_081 IDTAREA, ");
		queryATRA.append(" t1.ID_TIPO_TAREA_081 IDTIPOTAREA, ");
		queryATRA.append(" t1.ANYO_081 ANYO, ");
		queryATRA.append(" t1.NUM_EXP_081 NUMEXP, ");
		queryATRA.append(" t1.DNI_RECURSO_081 DNI, ");
		queryATRA.append(" t1.ESTADO_ASIGNACION_081 ESTADOASIGNACION, ");
		queryATRA.append(" t1.ESTADO_EJECUCION_081 ESTADOEJECUCION ");
		queryATRA.append(" FROM AA79B81S01 t1 ");
		queryATRA.append(TareasDaoImpl.DEFAULT_WHERE);
		queryATRA.append(SqlUtils.generarWhereIgual("t1.ANYO_081", tarea.getAnyo(), paramsATRA));
		queryATRA.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_081", tarea.getNumExp(), paramsATRA));
		queryATRA.append(SqlUtils.generarWhereIn("t1.ID_TIPO_TAREA_081", tiposTarea, paramsATRA));
		queryATRA.append(SqlUtils.generarWhereDistinto("t1.ID_TAREA_081", tarea.getIdTarea(), paramsATRA));
		queryATRA.append(SqlUtils.generarWhereDistinto("t1.ORDEN_081", tarea.getOrden(), paramsATRA));
		queryATRA.append(" ORDER BY FECHA_FIN_081 DESC");
		queryATRA.append(" FETCH FIRST ROW ONLY");
		List<Tareas> asignado = this.getJdbcTemplate().query(queryATRA.toString(), paramsATRA.toArray(), this.rwMapAsignadoTareaRevisionAnterior);
		if (!asignado.isEmpty()) {
			return asignado.get(0);
		}
		return null;
	}

	@Override
	public Tareas obtenerUltimaTareaEjecutadaConDocXliff(Tareas tareaFilter) {
		StringBuilder query = new StringBuilder(DocumentosGeneralDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append(" SELECT ");
		query.append(" MAX(t1.ID_TAREA_081) " + DBConstants.IDTAREA);
		query.append(" FROM AA79B81S01 t1");
		query.append(" JOIN AA79B51S01  t2 ON t1.ANYO_081 = t2.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_081 = t2.NUM_EXP_051 ");
		query.append(" JOIN AA79B59S01  t3 ON t2.ANYO_051 = t3.ANYO_059 ");
		query.append(" AND t2.NUM_EXP_051 = t3.NUM_EXP_059 ");
		query.append(" AND t2.ESTADO_BITACORA_051 = t3.ID_ESTADO_BITACORA_059 ");
		query.append(" JOIN AA79B96S01 t4 on t4.ID_TAREA_096 = t1.ID_TAREA_081 ");
		query.append(" WHERE 1=1 ");
		// estado de expediente cerrado o finailzado
		query.append(SqlUtils.generarWhereIn("t3.ID_ESTADO_EXP_059", Arrays.asList(EstadoExpedienteEnum.CERRADO.getValue(),
																					 EstadoExpedienteEnum.FINALIZADO.getValue()), params));
		// la tarea debe estar ejecutada
		query.append(SqlUtils.generarWhereIgual("t1.ESTADO_EJECUCION_081", EstadoEjecucionTareaEnum.EJECUTADA.getValue(), params));
		query.append(SqlUtils.generarWhereIgual("t1.ANYO_081", tareaFilter.getAnyo(), params));
		query.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_081", tareaFilter.getNumExp(), params));
		List<Tareas> lTarea = this.getJdbcTemplate().query(query.toString(), params.toArray(), this.rwMapPK);
		if (lTarea != null && !lTarea.isEmpty() && lTarea.get(0).getIdTarea() != null) {
			return DataAccessUtils.uniqueResult(lTarea);
		}
		return null;
	}

}
