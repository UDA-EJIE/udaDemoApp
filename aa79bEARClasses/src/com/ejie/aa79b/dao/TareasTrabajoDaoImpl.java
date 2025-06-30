package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.DatosTareaTrabajoRowMapper;
import com.ejie.aa79b.dao.mapper.TareasTrabajoRowMapperConfTareas;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ObservacionesTareas;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository()
@Transactional()
public class TareasTrabajoDaoImpl extends GenericoDaoImpl<TareasTrabajo> implements TareasTrabajoDao {
	public static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ORDEN, DBConstants.IDTAREA };

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired
	OtrosTrabajosDao otrosTrabajosDao;

	public TareasTrabajoDaoImpl() {
		super(TareasTrabajo.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<TareasTrabajo> rwMap = new RowMapper<TareasTrabajo>() {
		@Override
		public TareasTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasTrabajo tareaTrabajo = new TareasTrabajo();
			tareaTrabajo.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
			OtrosTrabajos trabajo = new OtrosTrabajos();
			trabajo.setIdTrabajo(resultSet.getBigDecimal("IDTRABAJO"));
			trabajo.setDescTrabajo(resultSet.getString("DESCTRABAJO"));
			tareaTrabajo.setTrabajo(trabajo);
			TiposTarea tipoTarea = new TiposTarea();
			tipoTarea.setId015(resultSet.getLong("IDTIPOTAREA"));
			tipoTarea.setDescEu015(resultSet.getString("DESCTIPOTAREAEU"));
			tipoTarea.setDescEs015(resultSet.getString("DESCTIPOTAREAES"));
			tareaTrabajo.setTipoTarea(tipoTarea);
			tareaTrabajo.setFechaInicio(resultSet.getDate("FECHAINICIO"));
			tareaTrabajo.setHoraInicio(resultSet.getString("HORAINICIO"));
			tareaTrabajo.setFechaFin(resultSet.getDate("FECHAFIN"));
			tareaTrabajo.setHoraFin(resultSet.getString("HORAFIN"));
			PersonalIZO personaAsignada = new PersonalIZO();
			personaAsignada.setDni(resultSet.getString("DNIRECURSO"));
			personaAsignada.setNombre(resultSet.getString("NOMBRERECURSO"));
			personaAsignada.setApellido1(resultSet.getString("APELLIDO1RECURSO"));
			personaAsignada.setApellido2(resultSet.getString("APELLIDO2RECURSO"));
			tareaTrabajo.setPersonaAsignada(personaAsignada);

			tareaTrabajo.setEstadoAsignado(resultSet.getInt("ESTADOASIGID"));
			tareaTrabajo.setEstadoAsignadoDesc(resultSet.getString("ESTADOASIGNACIONDESC"));

			tareaTrabajo.setEstadoEjecucion(resultSet.getInt("ESTADOEJECID"));
			tareaTrabajo.setEstadoEjecucionDesc(resultSet.getString("ESTADOEJECUCIONDESC"));
			tareaTrabajo.setOrden(resultSet.getInt("ORDEN"));


			tareaTrabajo.setFechaAsignacion(resultSet.getDate("FECHAASIGNACION"));
			tareaTrabajo.setHoraAsignacion(resultSet.getString("HORAASIGNACION"));

			tareaTrabajo.setFechaAceptacion(resultSet.getDate("FECHAACEPTACION"));
			tareaTrabajo.setHoraAceptacion(resultSet.getString("HORAACEPTACION"));

			tareaTrabajo.setObservaciones(resultSet.getString("OBSERV"));
			PersonalIZO personaAsignador = new PersonalIZO();
			personaAsignador.setDni(resultSet.getString("DNIASIGNADOR"));
			tareaTrabajo.setPersonaAsignador(personaAsignador);
			tareaTrabajo.setRecursoDisponible(resultSet.getString("ESRECURSODISPONIBLE"));
			tareaTrabajo.setTieneOtrasTareas(resultSet.getString("TIENEOTRASTAREAS"));
			return tareaTrabajo;
		}
	};

	private RowMapper<TareasTrabajo> rwMapPK = new RowMapper<TareasTrabajo>() {
		@Override
		public TareasTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasTrabajo tareaTrabajo = new TareasTrabajo();
			tareaTrabajo.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
			OtrosTrabajos trabajo = new OtrosTrabajos();
			trabajo.setIdTrabajo(resultSet.getBigDecimal("IDTRABAJO"));
			trabajo.setDescTrabajo(resultSet.getString("DESCTRABAJO"));
			tareaTrabajo.setTrabajo(trabajo);
			TiposTarea tipoTarea = new TiposTarea();
			tipoTarea.setId015(resultSet.getLong("IDTIPOTAREA"));
			tipoTarea.setDescEu015(resultSet.getString("DESCTIPOTAREAEU"));
			tipoTarea.setDescEs015(resultSet.getString("DESCTIPOTAREAES"));
			tareaTrabajo.setTipoTarea(tipoTarea);
			tareaTrabajo.setFechaInicio(resultSet.getDate("FECHAINICIO"));
			tareaTrabajo.setHoraInicio(resultSet.getString("HORAINICIO"));
			tareaTrabajo.setFechaFin(resultSet.getDate("FECHAFIN"));
			tareaTrabajo.setHoraFin(resultSet.getString("HORAFIN"));
			PersonalIZO personaAsignada = new PersonalIZO();
			personaAsignada.setDni(resultSet.getString("DNIRECURSO"));
			personaAsignada.setNombre(resultSet.getString("NOMBRERECURSO"));
			personaAsignada.setApellido1(resultSet.getString("APELLIDO1RECURSO"));
			personaAsignada.setApellido2(resultSet.getString("APELLIDO2RECURSO"));
			tareaTrabajo.setPersonaAsignada(personaAsignada);

			tareaTrabajo.setEstadoAsignado(resultSet.getInt("ESTADOASIGID"));
			tareaTrabajo.setEstadoAsignadoDesc(resultSet.getString("ESTADOASIGNACIONDESC"));

			tareaTrabajo.setEstadoEjecucion(resultSet.getInt("ESTADOEJECID"));
			tareaTrabajo.setEstadoEjecucionDesc(resultSet.getString("ESTADOEJECUCIONDESC"));
			tareaTrabajo.setOrden(resultSet.getInt("ORDEN"));


			tareaTrabajo.setFechaAsignacion(resultSet.getDate("FECHAASIGNACION"));
			tareaTrabajo.setHoraAsignacion(resultSet.getString("HORAASIGNACION"));

			tareaTrabajo.setFechaAceptacion(resultSet.getDate("FECHAACEPTACION"));
			tareaTrabajo.setHoraAceptacion(resultSet.getString("HORAACEPTACION"));

			tareaTrabajo.setObservaciones(resultSet.getString("OBSERV"));
			PersonalIZO personaAsignador = new PersonalIZO();
			personaAsignador.setDni(resultSet.getString("DNIASIGNADOR"));
			tareaTrabajo.setPersonaAsignador(personaAsignador);

			tareaTrabajo.setRecursoDisponible(resultSet.getString("ESRECURSODISPONIBLE"));
			tareaTrabajo.setTieneOtrasTareas(resultSet.getString("TIENEOTRASTAREAS"));
			return tareaTrabajo;
		}
	};

	private RowMapper<TareasTrabajo> rwMapPrimaryKey = new RowMapper<TareasTrabajo>() {
		@Override
		public TareasTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasTrabajo tareaTrabajo = new TareasTrabajo();
			tareaTrabajo.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
			return tareaTrabajo;
		}
	};

	private RowMapper<TareasTrabajo> rwMapEjecTarea = new RowMapper<TareasTrabajo>() {
		@Override
		public TareasTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasTrabajo tareaTrabajo = new TareasTrabajo();
			tareaTrabajo.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
			OtrosTrabajos trabajo = new OtrosTrabajos();
			trabajo.setIdTrabajo(resultSet.getBigDecimal("IDTRABAJO"));
			tareaTrabajo.setTrabajo(trabajo);
			DocumentoTareaAdjunto documentoSalida = new DocumentoTareaAdjunto();
			documentoSalida.setIdFichero(resultSet.getBigDecimal("IDFICHEROSALIDA"));
			documentoSalida.setTitulo(resultSet.getString("TITULOFICHEROSALIDA"));
			documentoSalida.setNombre(resultSet.getString("NOMBREFICHEROSALIDA"));
			documentoSalida.setEncriptado(resultSet.getString("INDENCRIPTADO"));
			documentoSalida.setOid(resultSet.getString("OIDFICHERO"));
			tareaTrabajo.setDocumentoSalida(documentoSalida);
			ObservacionesTareas obsTarea = new ObservacionesTareas();
			obsTarea.setObsvEjecucion(resultSet.getString("OBSERVACIONES"));
			tareaTrabajo.setObservacionesTareas(obsTarea);
			return tareaTrabajo;
		}
	};

	private RowMapper<TareasTrabajo> rwMapDatosTarea = new DatosTareaTrabajoRowMapper();

	private RowMapper<Tareas> rwMapConfTareasTrabajo = new TareasTrabajoRowMapperConfTareas();


	private RowMapper<TareasTrabajo> rwMapConfTareas = new RowMapper<TareasTrabajo>() {
		@Override()
		public TareasTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasTrabajo tareaTrabajo = new TareasTrabajo();
			tareaTrabajo.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
			OtrosTrabajos trabajo = new OtrosTrabajos();
			trabajo.setIdTrabajo(resultSet.getBigDecimal("IDTRABAJO"));
			trabajo.setDescTrabajo(resultSet.getString("DESCTRABAJO"));
			tareaTrabajo.setTrabajo(trabajo);
			TiposTarea tipoTarea = new TiposTarea();
			tipoTarea.setId015(resultSet.getLong("IDTIPOTAREA"));
			tipoTarea.setDescEu015(resultSet.getString("DESCTIPOTAREAEU"));
			tipoTarea.setDescEs015(resultSet.getString("DESCTIPOTAREAES"));
			tareaTrabajo.setTipoTarea(tipoTarea);
			tareaTrabajo.setFechaInicio(resultSet.getDate("FECHAINICIO"));
			tareaTrabajo.setHoraInicio(resultSet.getString("HORAINICIO"));
			tareaTrabajo.setFechaFin(resultSet.getDate("FECHAFIN"));
			tareaTrabajo.setHoraFin(resultSet.getString("HORAFIN"));
			PersonalIZO personaAsignada = new PersonalIZO();
			personaAsignada.setDni(resultSet.getString("DNIRECURSO"));
			personaAsignada.setNombre(resultSet.getString("NOMBRERECURSO"));
			personaAsignada.setApellido1(resultSet.getString("APELLIDO1RECURSO"));
			personaAsignada.setApellido2(resultSet.getString("APELLIDO2RECURSO"));
			tareaTrabajo.setPersonaAsignada(personaAsignada);

			tareaTrabajo.setEstadoAsignado(resultSet.getInt("ESTADOASIGID"));
			tareaTrabajo.setEstadoAsignadoDesc(resultSet.getString("ESTADOASIGNACIONDESC"));

			tareaTrabajo.setEstadoEjecucion(resultSet.getInt("ESTADOEJECID"));
			tareaTrabajo.setEstadoEjecucionDesc(resultSet.getString("ESTADOEJECUCIONDESC"));
			tareaTrabajo.setOrden(resultSet.getInt("ORDEN"));


			tareaTrabajo.setFechaAsignacion(resultSet.getDate("FECHAASIGNACION"));
			tareaTrabajo.setHoraAsignacion(resultSet.getString("HORAASIGNACION"));

			tareaTrabajo.setFechaAceptacion(resultSet.getDate("FECHAACEPTACION"));
			tareaTrabajo.setHoraAceptacion(resultSet.getString("HORAACEPTACION"));

			tareaTrabajo.setObservaciones(resultSet.getString("OBSERV"));
			PersonalIZO personaAsignador = new PersonalIZO();
			personaAsignador.setDni(resultSet.getString("DNIASIGNADOR"));
			tareaTrabajo.setPersonaAsignador(personaAsignador);
			tareaTrabajo.setRecursoDisponible(resultSet.getString(DBConstants.ESRECURSODISPONIBLE));
			tareaTrabajo.setTieneOtrasTareas(resultSet.getString("TIENEOTRASTAREAS"));
			//Los datos del fichero
			DocumentoTareaAdjunto documentoTarea = new DocumentoTareaAdjunto();
			documentoTarea.setIdFichero(resultSet.getBigDecimal("IDFICHERO"));
			documentoTarea.setTitulo(resultSet.getString("TITULOFICHERO"));
			documentoTarea.setExtension(resultSet.getString("EXTENSIONFICHERO"));
			documentoTarea.setContentType(resultSet.getString("CONTENTTYPEFICHERO"));
			documentoTarea.setTamano(resultSet.getLong("TAMANOFICHERO"));
			documentoTarea.setEncriptado(resultSet.getString("INDENCRIPTADO"));
			documentoTarea.setRutaPif(resultSet.getString("RUTAPIF"));
			documentoTarea.setOid(resultSet.getString("OIDFICHERO"));
			documentoTarea.setNombre(resultSet.getString("NOMBREFICHERO"));
			tareaTrabajo.setDocumentoEntrada(documentoTarea);
			return tareaTrabajo;
		}
	};

	private RowMapper<TareasTrabajo> rwMapTareaEstado = new RowMapper<TareasTrabajo>() {
		@Override()
		public TareasTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasTrabajo tareas = new TareasTrabajo();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			tareas.setEstadoEjecucion(resultSet.getInt(DBConstants.ESTADOEJECID));
			return tareas;
		}
	};

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
		StringBuilder str = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		str.append("SELECT ");
		// Tareas
		str.append("t1.ID_TRABAJO_0C7 IDTRABAJO ");
		str.append(", t1.ID_TAREA_0C7 IDTAREA ");
		str.append(", t1.ID_TIPO_TAREA_0C7 IDTIPOTAREA ");
		str.append(", t1.FECHA_INICIO_0C7 FECHAINICIO ");
		str.append(", TO_CHAR(t1.FECHA_INICIO_0C7,'HH24:MI') HORAINICIO");
		str.append(", t1.FECHA_FIN_0C7 FECHAFIN ");
		str.append(", TO_CHAR(t1.FECHA_FIN_0C7,'HH24:MI') HORAFIN");
		str.append(", t1.DNI_RECURSO_0C7 DNIRECURSO ");
		str.append(", t1.ESTADO_ASIGNACION_0C7 ESTADOASIGID ");
		str.append(DAOUtils.getDecodeAcciones("t1.ESTADO_ASIGNACION_0C7", "ESTADOASIGNACIONDESC", this.msg,
				"EstadoAceptacionTareaEnum", locale));
		str.append(", t1.ESTADO_EJECUCION_0C7 ESTADOEJECID, ");
		str.append(" CASE WHEN (" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "=t1.ESTADO_EJECUCION_0C7 AND t1.FECHA_FIN_0C7 < SYSDATE) THEN '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "'");
		str.append(" ELSE (DECODE(t1.ESTADO_EJECUCION_0C7, " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ ",'" + this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale)
				+ "',");
		str.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ",'"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale)
				+ "')) END AS ESTADOEJECUCIONDESC");
		str.append(", t1.OBSERV_0C7 OBSERV ");
		str.append(", t1.ORDEN_0C7 ORDEN ");
		str.append(", t1.DNI_ASIGNADOR_0C7 DNIASIGNADOR ");
		str.append(", t1.FECHA_ASIGNACION_0C7 FECHAASIGNACION ");
		str.append(", TO_CHAR(t1.FECHA_ASIGNACION_0C7,'HH24:MI') HORAASIGNACION");
		str.append(", t1.FECHA_ACEPTACION_0C7 FECHAACEPTACION ");
		str.append(", TO_CHAR(t1.FECHA_ACEPTACION_0C7,'HH24:MI') HORAACEPTACION");

		str.append(", t2.DESC_EU_015 DESCTIPOTAREAEU");
		str.append(", t2.DESC_ES_015 DESCTIPOTAREAES");
		str.append(", t3.NOMBRE_077 NOMBRERECURSO");
		str.append(", t3.APEL1_077 APELLIDO1RECURSO");
		str.append(", t3.APEL2_077 APELLIDO2RECURSO");
		str.append(", ES_RECURSO_DISPONIBLE(t1.DNI_RECURSO_0C7, NULL, t1.ID_TAREA_0C7) AS ESRECURSODISPONIBLE ");
		str.append(", TIENE_OTRAS_TAREAS(t1.DNI_RECURSO_0C7, NULL, t1.ID_TAREA_0C7) AS TIENEOTRASTAREAS ");
		str.append(", t4.DESC_TRABAJO_0C6 DESCTRABAJO");


		return str;
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		// TareasTrabajo
		from.append(" FROM AA79BC7S01 t1 ");
		from.append(" JOIN AA79B15S01 t2 ON ID_TIPO_TAREA_0C7 = ID_015");
		from.append(" LEFT JOIN AA79B77S01 t3 ON t1.DNI_RECURSO_0C7 = t3.DNI_077");
		from.append(" JOIN AA79Bc6S01 t4 ON t1.ID_TRABAJO_0C7 = t4.ID_TRABAJO_0C6");
		return from.toString();
	}

	@Override
	protected RowMapper<TareasTrabajo> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return TareasTrabajoDaoImpl.ORDER_BY_WHITE_LIST;

	}

	@Override
	protected String getPK() {
		return "ID_TAREA_0C7";
	}

	@Override
	protected RowMapper<TareasTrabajo> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(TareasTrabajo bean, List<Object> params) {
		params.add(bean.getIdTarea());
		return " WHERE t1.ID_TAREA_0C7 = ?";
	}

	@Override
	protected String getWhere(TareasTrabajo bean, List<Object> params) {
		StringBuilder strArea = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		return strArea.toString();
	}

	@Override
	protected String getWhereLike(TareasTrabajo bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder strArea = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		if (bean.getTrabajo()!= null) {
			strArea.append(SqlUtils.generarWhereIgual("t1.ID_TRABAJO_0C7", bean.getTrabajo().getIdTrabajo(), params));
		}
		return strArea.toString();
	}

	@Override()
	public TareasTrabajo add(TareasTrabajo tareas) {
		StringBuilder queryId = new StringBuilder("SELECT AA79BC7Q00.NEXTVAL FROM dual ");
		BigDecimal id = this.getJdbcTemplate().queryForObject(queryId.toString(), BigDecimal.class);
		tareas.setIdTarea(id);
		Date fechaIni = tareas.getFechaInicio();
		String horaIni = tareas.getHoraInicio();
		Date fechaFin = tareas.getFechaFin();
		String horaFin = tareas.getHoraFin();
		String query = "INSERT INTO AA79BC7S01 ("
				+ "ID_TAREA_0C7, ID_TIPO_TAREA_0C7, ID_TRABAJO_0C7, "
				+ "FECHA_INICIO_0C7, FECHA_FIN_0C7, DNI_RECURSO_0C7, "
				+ "ESTADO_ASIGNACION_0C7, ESTADO_EJECUCION_0C7, OBSERV_0C7, ORDEN_0C7, DNI_ASIGNADOR_0C7, "
				+ "FECHA_ASIGNACION_0C7, FECHA_ACEPTACION_0C7) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, tareas.getIdTarea(), tareas.getTipoTarea().getId015(), tareas.getTrabajo().getIdTrabajo(),
				DateUtils.obtFechaHoraFormateada(fechaIni, horaIni), DateUtils.obtFechaHoraFormateada(fechaFin, horaFin),
				tareas.getPersonaAsignada().getDni(),
				Utils.getEstadoAsignacion(tareas.getEstadoAsignado()),
				Utils.getEstadoEjecucion(tareas.getEstadoEjecucion()), tareas.getObservaciones(), tareas.getOrden(),
				tareas.getPersonaAsignador().getDni(), tareas.getFechaAsignacion(), tareas.getFechaAceptacion());
		return tareas;
	}

	@Override()
	public int comprobarTareaAEliminar(BigDecimal idTarea) {
		int rst = 0;

		if (idTarea != null) {
			List<Object> params = new ArrayList<Object>();
			StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
			query.append(DaoConstants.FROM + "AA79BC7S01" + DaoConstants.WHERE);
			query.append("ID_TAREA_0C7" + DaoConstants.SIGNOIGUAL);
			query.append(idTarea);
			query.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
			query.append("ESTADO_EJECUCION_0C7" + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.OR);
			query.append("ESTADO_ASIGNACION_0C7" + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.CERRAR_PARENTESIS);
			params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			params.add(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
		}

		return rst;
	}

	/**
	 * Updates a single row in the OtrosTrabajos table.
	 *
	 * @param motivosrechazo
	 *            OtrosTrabajos
	 * @return OtrosTrabajos
	 */
	@Override()
	public void remove(TareasTrabajo tareasTrabajo) {
		String query = "DELETE FROM AA79BC7S01 WHERE ID_TAREA_0C7=?";
		this.getJdbcTemplate().update(query, tareasTrabajo.getIdTarea());
	}


	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	@Override()
	public TareasTrabajo findConfTarea(TareasTrabajo bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelectConfTareas());
		query.append(this.getFromConfTareas());
		query.append(this.getWherePK(bean, params));

		List<TareasTrabajo> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapConfTareas, params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}


	private String getSelectConfTareas() {
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);

		query.append(this.getSelect());
		// Fichero entrada de la tarea
		query.append(",t5.ID_FICHERO_088 IDFICHERO");
		query.append(",t5.TITULO_FICHERO_088 TITULOFICHERO");
		query.append(",t5.EXTENSION_FICHERO_088 EXTENSIONFICHERO");
		query.append(",t5.CONTENT_TYPE_FICHERO_088 CONTENTTYPEFICHERO");
		query.append(",t5.TAMANO_FICHERO_088 TAMANOFICHERO");
		query.append(",t5.IND_ENCRIPTADO_088 INDENCRIPTADO");
		query.append(",t5.RUTA_PIF_FICHERO_088 RUTAPIF");
		query.append(",t5.OID_FICHERO_088 OIDFICHERO");
		query.append(",t5.NOMBRE_FICHERO_088 NOMBREFICHERO");
		return query.toString();
	}

	private String getFromConfTareas() {
		StringBuilder from = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		// Tareas
		from.append(this.getFrom());
		// Documento entrada de tarea
		from.append(" LEFT JOIN AA79BC8S01 t4 ON t1.ID_TAREA_0C7 = t4.ID_TAREA_0C8");
		from.append(" LEFT JOIN AA79B88S01 t5 ON t4.ID_FICHERO_0C8 = t5.ID_FICHERO_088");
		return from.toString();
	}

	@Override
	public void reabrirTarea(BigDecimal idTarea) {

		StringBuilder query = new StringBuilder("UPDATE AA79BC7S01");
		query.append(" SET ESTADO_EJECUCION_0C7=?, FECHA_EJECUCION_0C7=?");
		query.append(" WHERE ID_TAREA_0C7=?");

		this.getJdbcTemplate().update(query.toString(), EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(), null,
				idTarea);
	}

	@Override()
	public void removeObsrvRechazo(BigDecimal idTarea) {
		List<TareasTrabajo> listTareas = new ArrayList<TareasTrabajo>();
		TareasTrabajo tarea = new TareasTrabajo();
		tarea.setIdTarea(idTarea);
		listTareas.add(tarea);

		removeObsrvRechazo(listTareas);
	}

	@Override()
	public void removeObsrvRechazo(List<TareasTrabajo> listTareas) {
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.DELETE);
		query.append(DaoConstants.FROM);
		query.append(" AA79BC9T00 ");
		query.append(DaoConstants.WHERE);
		query.append(" ID_TAREA_0C9 ");
		query.append(DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(Utils.obtenerListIdsTareasTrabajo(listTareas));
		query.append(DaoConstants.CERRAR_PARENTESIS);

		this.getJdbcTemplate().update(query.toString());
	}

	@Override()
	public TareasTrabajo update(TareasTrabajo tareas) {
		Date fechaIni = tareas.getFechaInicio();
		String horaIni = tareas.getHoraInicio();

		Date fechaFin = tareas.getFechaFin();
		String horaFin = tareas.getHoraFin();

		StringBuilder query = new StringBuilder(DaoConstants.UPDATE + " AA79BC7S01 ");
		query.append(" SET FECHA_INICIO_0C7=?");
		query.append(", FECHA_FIN_0C7=?");
		query.append(", DNI_RECURSO_0C7=?");
		query.append(", ESTADO_ASIGNACION_0C7=?");
		query.append(", ESTADO_EJECUCION_0C7=?");
		query.append(", OBSERV_0C7=?");
		query.append(", ORDEN_0C7=?");
		query.append(", DNI_ASIGNADOR_0C7=?");
		query.append(", FECHA_ASIGNACION_0C7=?");
		query.append(", FECHA_ACEPTACION_0C7=?");
		query.append(DaoConstants.WHERE + " ID_TAREA_0C7 " + DaoConstants.SIGNOIGUAL_INTERROGACION);

		this.getJdbcTemplate().update(query.toString(),
				DateUtils.obtFechaHoraFormateada(fechaIni, horaIni),
				DateUtils.obtFechaHoraFormateada(fechaFin, horaFin), tareas.getPersonaAsignada().getDni(),
				Utils.getEstadoAsignacion(tareas.getEstadoAsignado()), Utils.getEstadoEjecucion(tareas.getEstadoEjecucion()), tareas.getObservaciones(), tareas.getOrden(),
				tareas.getPersonaAsignador().getDni(), tareas.getFechaAsignacion(), tareas.getFechaAceptacion(), tareas.getIdTarea());
		return tareas;
	}

	@Override
	public TareasTrabajo getDatosTarea(TareasTrabajo tareaTrabajo) {
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append(this.getSelectConfTareas());
		query.append(" ,t6.DESC_TRABAJO_0C6 DESCTRABAJO ");
		query.append(" ,t6.FECHA_FIN_PREVISTA_0C6 FECHAFINPREVISTA ");
		query.append(", TO_CHAR(t6.FECHA_FIN_PREVISTA_0C6,'HH24:MI') HORAFINPREVISTA");
		query.append(" ,t7.NOMBRE_077 NOMBREASIGNADOR ");
		query.append(" ,t7.APEL1_077 APELLIDO1ASIGNADOR ");
		query.append(" ,t7.APEL2_077 APELLIDO2ASIGNADOR ");
		query.append(" ,t8.OBSV_RECHAZO_0C9 OBSERVRECHAZO ");
		query.append(",t9.OBSV_EJECUCION_0D0 OBSERVJECUCION");
		query.append(",t11.ID_FICHERO_088 IDFICHEROSALIDA");
		query.append(",t11.TITULO_FICHERO_088 TITULOFICHEROSALIDA");
		query.append(",t11.EXTENSION_FICHERO_088 EXTENSIONFICHEROSALIDA");
		query.append(",t11.CONTENT_TYPE_FICHERO_088 CONTENTTYPEFICHEROSALIDA");
		query.append(",t11.TAMANO_FICHERO_088 TAMANOFICHEROSALIDA");
		query.append(",t11.IND_ENCRIPTADO_088 INDENCRIPTADOSALIDA");
		query.append(",t11.RUTA_PIF_FICHERO_088 RUTAPIFSALIDA");
		query.append(",t11.OID_FICHERO_088 OIDFICHEROSALIDA");
		query.append(",t11.NOMBRE_FICHERO_088 NOMBREFICHEROSALIDA");

		query.append(this.getFromConfTareas());
		query.append(" JOIN AA79BC6S01 t6 ON t1.ID_TRABAJO_0C7 = t6.ID_TRABAJO_0C6 ");
		query.append(" LEFT JOIN AA79B77S01  t7 ON t1.DNI_ASIGNADOR_0C7 = t7.DNI_077 ");
		query.append(" LEFT JOIN AA79BC9S01  t8 ON t1.ID_TAREA_0C7 = t8.ID_TAREA_0C9 ");
		query.append(" LEFT JOIN AA79BD0S01  t9 ON t1.ID_TAREA_0C7 = t9.ID_TAREA_0D0 ");
		query.append(" LEFT JOIN AA79BD1S01  t10 ON t1.ID_TAREA_0C7 = t10.ID_TAREA_0D1 ");
		query.append(" LEFT JOIN AA79B88S01  t11 ON t10.ID_FICHERO_0D1 = t11.ID_FICHERO_088 ");

		query.append(this.getWherePK(tareaTrabajo, params));

		List<TareasTrabajo> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapDatosTarea, params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Object getTareasDependientes(TareasTrabajo tareaTrabajoFilter, JQGridRequestDto jqGridRequestDto, boolean isCount) {
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		if (isCount) {
			query.append("SELECT COUNT(1) ");
		} else {
			query.append(this.getSelectConfTareas());
			query.append(" ,t6.DESC_TRABAJO_0C6 DESCTRABAJO ");
			query.append(" ,t6.FECHA_FIN_PREVISTA_0C6 FECHAFINPREVISTA ");
			query.append(", TO_CHAR(t6.FECHA_FIN_PREVISTA_0C6,'HH24:MI') HORAFINPREVISTA");
			query.append(" ,t7.NOMBRE_077 NOMBREASIGNADOR ");
			query.append(" ,t7.APEL1_077 APELLIDO1ASIGNADOR ");
			query.append(" ,t7.APEL2_077 APELLIDO2ASIGNADOR ");
			query.append(" ,t8.OBSV_RECHAZO_0C9 OBSERVRECHAZO ");
			query.append(",t9.OBSV_EJECUCION_0D0 OBSERVJECUCION");
			query.append(",t11.ID_FICHERO_088 IDFICHEROSALIDA");
			query.append(",t11.TITULO_FICHERO_088 TITULOFICHEROSALIDA");
			query.append(",t11.EXTENSION_FICHERO_088 EXTENSIONFICHEROSALIDA");
			query.append(",t11.CONTENT_TYPE_FICHERO_088 CONTENTTYPEFICHEROSALIDA");
			query.append(",t11.TAMANO_FICHERO_088 TAMANOFICHEROSALIDA");
			query.append(",t11.IND_ENCRIPTADO_088 INDENCRIPTADOSALIDA");
			query.append(",t11.RUTA_PIF_FICHERO_088 RUTAPIFSALIDA");
			query.append(",t11.OID_FICHERO_088 OIDFICHEROSALIDA");
			query.append(",t11.NOMBRE_FICHERO_088 NOMBREFICHEROSALIDA");
		}

		query.append(this.getFromConfTareas());
		query.append(" JOIN AA79BC6S01 t6 ON t1.ID_TRABAJO_0C7 = t6.ID_TRABAJO_0C6 ");
		query.append(" LEFT JOIN AA79B77S01  t7 ON t1.DNI_ASIGNADOR_0C7 = t7.DNI_077 ");
		query.append(" LEFT JOIN AA79BC9S01  t8 ON t1.ID_TAREA_0C7 = t8.ID_TAREA_0C9 ");
		query.append(" LEFT JOIN AA79BD0S01  t9 ON t1.ID_TAREA_0C7 = t9.ID_TAREA_0D0 ");
		query.append(" LEFT JOIN AA79BD1S01  t10 ON t1.ID_TAREA_0C7 = t10.ID_TAREA_0D1 ");
		query.append(" LEFT JOIN AA79B88S01  t11 ON t10.ID_FICHERO_0D1 = t11.ID_FICHERO_088 ");

		query.append(" WHERE 1=1 ");
		query.append(SqlUtils.generarWhereIgual("t6.ID_TRABAJO_0C6", tareaTrabajoFilter.getTrabajo() != null ?
																		tareaTrabajoFilter.getTrabajo().getIdTrabajo() :
																		null, params));
		query.append(SqlUtils.generarWhereDistinto("t1.ID_TAREA_0C7", tareaTrabajoFilter.getIdTarea(), params));
		query.append(SqlUtils.generarWhereMenor("t1.ORDEN_0C7", tareaTrabajoFilter.getOrden(), params));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(query.toString(), this.rwMapDatosTarea, params.toArray());
		}
	}

	@Override
	public Tareas findConfTareaTrabajo(Tareas tareasFilter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);

		query.append("SELECT ");
		query.append("t1.ESTADO_EJECUCION_0C7 " + DBConstants.ESTADOEJECID);
		query.append(" FROM AA79BC7S01 t1 ");
		params.add(tareasFilter.getIdTarea());
		query.append(" WHERE t1.ID_TAREA_0C7 = ?");

		List<Tareas> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapConfTareasTrabajo, params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public List<TareasTrabajo> findTareasTrabajoPendientesDeEjecutar(List<String> listSelectedIds) {
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append(this.getSelect());
		// Tipos de tarea
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE_1_1);

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			query.append("AND t1.ESTADO_EJECUCION_0C7 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue()
					+ " AND t1.ID_TAREA_0C7 IN (");
			query.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			query.append(")");
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap);
	}

	@Override
	public int reasignarTareas(TareasTrabajo tarea, List<String> listSelectedIds) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append("UPDATE AA79BC7S01 SET ");
		query.append(" DNI_RECURSO_0C7=? ");
		query.append(", FECHA_ACEPTACION_0C7=?, ESTADO_ASIGNACION_0C7=? ");
		query.append(this.getWhereReasignarTareas(listSelectedIds));

		params.add(tarea.getPersonaAsignada().getDni());
		params.add(tarea.getFechaAceptacion());
		params.add(Utils.getEstadoAsignacion(tarea.getEstadoAsignado()));

		return this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	private Object getWhereReasignarTareas(List<String> listSelectedIds) {
		StringBuilder where = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);

		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			where.append("AND ESTADO_EJECUCION_0C7 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			where.append(" AND ID_TAREA_0C7 IN ( ");
			where.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			where.append(")");
		}

		return where.toString();
	}

	@Override
	public List<TareasTrabajo> findTareas(List<String> listSelectedIds) {
		List<TareasTrabajo> beanList = null;
		if (QueryUtils.isListIdsNoVacio(listSelectedIds)) {
			StringBuilder query = new StringBuilder(this.getSelect());
			query.append(this.getFrom());
			query.append(" WHERE ESTADO_EJECUCION_0C7 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			query.append(" AND ID_TAREA_0C7 IN( ");
			query.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			query.append(" )");

			beanList = this.getJdbcTemplate().query(query.toString(), this.rwMap);
		}

		return beanList;
	}

	@Override
	public TareasTrabajo comprobarEstadoEjecucionTarea(TareasTrabajo tarea) {
		List<Object> paramsCEET = new ArrayList<Object>();

		StringBuilder queryCEET = new StringBuilder(" SELECT t1.ESTADO_EJECUCION_0C7 " + DBConstants.ESTADOEJECID);
		queryCEET.append(" , t1.ID_TAREA_0C7 " + DBConstants.IDTAREA);
		queryCEET.append(" FROM AA79BC7S01 t1 ");
		queryCEET.append(DaoConstants.WHERE_1_1);
		queryCEET.append(SqlUtils.generarWhereIgual("t1.ID_TAREA_0C7", tarea.getIdTarea(), paramsCEET));
		List<TareasTrabajo> lTarea = this.getJdbcTemplate().query(queryCEET.toString(), paramsCEET.toArray(),
				this.rwMapTareaEstado);
		return DataAccessUtils.uniqueResult(lTarea);
	}

	@Override
	public int comprobarTareaAsignador(TareasTrabajo tareas) {
		int rst = 0;

		List<Object> params = new ArrayList<Object>();
		// comprobar que la el usuario que esta intentando ejecutar la tarea es el recurso asignado
		StringBuilder query = new StringBuilder(" SELECT ID_TAREA_0C7 IDTAREA ");
		query.append(" FROM AA79BC7S01 ");
		query.append(" WHERE ID_TAREA_0C7 = ? ");
		query.append(" AND DNI_RECURSO_0C7 = ? ");
		params.add(tareas.getIdTarea());
		params.add(tareas.getPersonaAsignada().getDni());
		List<TareasTrabajo> listTareas = this.getJdbcTemplate().query(query.toString(), this.rwMapPrimaryKey, params.toArray());
		TareasTrabajo rstTarea = DataAccessUtils.uniqueResult(listTareas);
		if (rstTarea == null) {
			// el usuario que esta intentando ejecutar la tarea NO es el recurso asignado
			rst = 1;
		} else {
			// comprobar que el estado de asignacion de la tarea es aceptada
			params = new ArrayList<Object>();
			query = new StringBuilder(" SELECT ID_TAREA_0C7 IDTAREA ");
			query.append(" FROM AA79BC7S01 ");
			query.append(" WHERE ID_TAREA_0C7 = ? ");
			query.append(" AND ESTADO_ASIGNACION_0C7 = ");
			query.append(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
			params.add(tareas.getIdTarea());
			listTareas = this.getJdbcTemplate().query(query.toString(), this.rwMapPrimaryKey, params.toArray());
			rstTarea = DataAccessUtils.uniqueResult(listTareas);
			if (rstTarea == null) {
				// el estado de asignacion de la tarea NO es aceptada
				rst = 4;
			}
		}

		return rst;
	}

	@Override
	public TareasTrabajo obtenerDatosEjecucionTarea(BigDecimal idTarea) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append(" SELECT t1.ID_TAREA_0C7 IDTAREA, t1.ID_TRABAJO_0C7 IDTRABAJO, t2.OBSV_EJECUCION_0D0 OBSERVACIONES, ");
		query.append(" t3.ID_FICHERO_0D1 IDFICHEROSALIDA, t4.TITULO_FICHERO_088 TITULOFICHEROSALIDA, ");
		query.append(" t4.NOMBRE_FICHERO_088 NOMBREFICHEROSALIDA, t4.IND_ENCRIPTADO_088 INDENCRIPTADO, ");
		query.append(" t4.OID_FICHERO_088 OIDFICHERO ");
		query.append(" FROM AA79BC7T00 t1 ");
		query.append(" LEFT JOIN AA79BD0S01 t2 ON T1.ID_TAREA_0C7 = t2.ID_TAREA_0D0 ");
		query.append(" LEFT JOIN AA79BD1S01  t3 ON T1.ID_TAREA_0C7 = t3.ID_TAREA_0D1 ");
		query.append(" LEFT JOIN AA79B88S01  t4 ON t3.ID_FICHERO_0D1 = t4.ID_FICHERO_088 ");
		query.append(" WHERE t1.ID_TAREA_0C7 = ? ");
		params.add(idTarea);
		List<TareasTrabajo> listTareas = this.getJdbcTemplate().query(query.toString(), this.rwMapEjecTarea, params.toArray());
		return DataAccessUtils.uniqueResult(listTareas);
	}

	@Override
	public Long comprobarConflictoFechas(String[] idTareaList) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append(" SELECT COUNT(1) FROM AA79BC7S01 t1 ");
		query.append(" JOIN AA79BC7S01 t2 ON  ");
		query.append(SqlUtils.generarWhereInWithOutAnd("t2.ID_TAREA_0C7", idTareaList, params));
		query.append(" AND t1.ID_TAREA_0C7 <> t2.ID_TAREA_0C7 ");
		query.append(" AND ((t1.FECHA_INICIO_0C7 >= t2.FECHA_INICIO_0C7 " );
		query.append(" AND t1.FECHA_INICIO_0C7 <= t2.FECHA_FIN_0C7) " );
		query.append(" OR (t1.FECHA_FIN_0C7 >= t2.FECHA_INICIO_0C7 " );
		query.append(" AND t1.FECHA_FIN_0C7 <= t2.FECHA_FIN_0C7)) " );
		query.append(" WHERE 1=1 " );
		query.append(SqlUtils.generarWhereIn("t1.ID_TAREA_0C7",
				idTareaList, params));
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Integer finalizarTarea(TareasTrabajo tareaTrabajo) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append(" UPDATE AA79BC7S01");
		query.append(" SET ESTADO_EJECUCION_0C7=?, FECHA_EJECUCION_0C7=SYSDATE, HORAS_TAREA_0C7=?");
		query.append(" WHERE ID_TAREA_0C7=?");
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(tareaTrabajo.getHorasTarea()!=null?tareaTrabajo.getHorasTarea():"");
		params.add(tareaTrabajo.getIdTarea());
		return this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override
	public int insertarObservacionesEjecucionTarea(TareasTrabajo tareaTrabajo) {
        StringBuilder insert = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
        List<Object> params = new ArrayList<Object>();
        insert.append("INSERT INTO AA79BD0S01 ");
        insert.append("VALUES (?, ?)");
        params.add(tareaTrabajo.getIdTarea());
        params.add(tareaTrabajo.getObservacionesTareas().getObsvEjecucion());
        return this.getJdbcTemplate().update(insert.toString(), params.toArray());
	}

	@Override
	public int borrarObservacionesEjecucionTarea(TareasTrabajo tareaTrabajo) {
        StringBuilder prevDelete = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
        List<Object> paramsDelete = new ArrayList<Object>();
        prevDelete.append(" DELETE FROM AA79BD0S01 ");
        prevDelete.append(" WHERE ID_TAREA_0D0 = ? ");
        paramsDelete.add(tareaTrabajo.getIdTarea());
        return this.getJdbcTemplate().update(prevDelete.toString(), paramsDelete.toArray());
	}

	@Override
	public Boolean tieneTareasPendientesDeEjecutar(TareasTrabajo tareaTrabajo) {
		StringBuilder queryTPDE = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
        List<Object> paramsTPDE = new ArrayList<Object>();
		queryTPDE.append(" SELECT COUNT(1) FROM AA79BC7S01 t1 ");
		queryTPDE.append(" where t1.ESTADO_EJECUCION_0C7 != ? ");
		queryTPDE.append(" and t1.ID_TRABAJO_0C7 = ? ");
		paramsTPDE.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		paramsTPDE.add(tareaTrabajo.getTrabajo().getIdTrabajo());
		Long tareasSinEjecutarCount = this.getJdbcTemplate().queryForObject(queryTPDE.toString(), paramsTPDE.toArray(), Long.class);
		return tareasSinEjecutarCount > 0;
	}

	@Override
	public Integer finalizaTrabajo(TareasTrabajo tareaTrabajo) {
		List<Object> paramsFT = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(TareasTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append(" UPDATE AA79BC6S01");
		query.append(" SET FECHA_FIN_0C6 = SYSDATE ");
		query.append(" WHERE ID_TRABAJO_0C6 = ?");
		paramsFT.add(tareaTrabajo.getTrabajo().getIdTrabajo());
		return this.getJdbcTemplate().update(query.toString(), paramsFT.toArray());
	}

	@Override
	public Integer comprobarTareasPendientesUsuario(TareasTrabajo tarea) {
		List<Object> params;
		StringBuilder query;
		params = new ArrayList<Object>();

		query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(" FROM AA79BC7S01 t1 ");
		query.append(" WHERE t1.ESTADO_EJECUCION_0C7 <> ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND t1.ESTADO_ASIGNACION_0C7 <> ").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		query.append(" AND t1.DNI_RECURSO_0C7 = ?");

		params.add(tarea.getPersonaAsignada().getDni());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	/**
	 * Mét odo para buscar las tareas para generar el excel del detalle de albaranes
	 * y pago a proveedor
	 */
	@Override
	public List<TareasTrabajo> getTareasTrabajosExcelDetalle(OtrosTrabajos otrosTrabajos) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder select = new StringBuilder(PagamentosDaoImpl.STRING_BUILDER_INIT);
		select.append(getSelect());
		select.append(getFrom());
		select.append(GenericoDaoImpl.DEFAULT_WHERE);
		//filtramos por
		select.append(" AND ID_TRABAJO_0C7 in (");
		select.append(this.otrosTrabajosDao.getTrabajosExcelDetalleQuery(otrosTrabajos, params));
		select.append(")");
		select.append(" ORDER BY ID_TRABAJO_0C7, ORDEN_0C7 ");

		return this.getJdbcTemplate().query(select.toString(), this.rwMap, params.toArray());

	}

	@Override
	public Integer comprobarTareasPendientesUsuarioAsignador(ExpTareasResumen expTareasResumen, String trabajador) {
		List<Object> params;
		StringBuilder query;
		params = new ArrayList<Object>();

		query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(" FROM AA79BC7S01 t1 ");
		query.append(" WHERE t1.ESTADO_EJECUCION_0C7 <> ").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND t1.ESTADO_ASIGNACION_0C7 <> ").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		query.append(" AND t1.DNI_RECURSO_0C7 = ?");
		params.add(trabajador);
		query.append(this.getWhereTareasPendientesAsignador(expTareasResumen, params));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	private String getWhereTareasPendientesAsignador(ExpTareasResumen expTareasResumen, List<Object> params) {
		StringBuilder where = new StringBuilder();
		if (expTareasResumen.getTarea() != null) {
			if(expTareasResumen.getTarea().getTipoTarea() != null && expTareasResumen.getTarea().getTipoTarea().getId015() != null) {
				where.append(" AND ID_TIPO_TAREA_0C7 = ? ");
				params.add(expTareasResumen.getTarea().getTipoTarea().getId015());
			}
			if(expTareasResumen.getTarea().getEstadoAsignado() != Constants.CERO) {
				where.append(" AND ESTADO_ASIGNACION_0C7 = ?");
				params.add(expTareasResumen.getTarea().getEstadoAsignado());
			}
			if(expTareasResumen.getTarea().getEstadoEjecucion() != Constants.CERO) {
				where.append(" AND ESTADO_EJECUCION_0C7 = ?");
				params.add(expTareasResumen.getTarea().getEstadoEjecucion());
			}
			if(expTareasResumen.getTarea().getFechaIni() != null) {
				where.append(" AND TO_DATE(FECHA_INICIO_0C7, 'DD/mm/YY') >= ? ");
				params.add(expTareasResumen.getTarea().getFechaIni());
			}
			if(expTareasResumen.getTarea().getFechaFin() != null) {
				where.append(" AND TO_DATE(FECHA_FIN_0C7, 'DD/mm/YY') <= ?");
				params.add(expTareasResumen.getTarea().getFechaFin());
			}
		}
		return where.toString();
	}

}
