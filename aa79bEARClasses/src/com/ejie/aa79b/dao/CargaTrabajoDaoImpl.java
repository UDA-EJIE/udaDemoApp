package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.CargaTrabajoDocumentosTareaRowMapper;
import com.ejie.aa79b.dao.mapper.CargaTrabajoRowMapper;
import com.ejie.aa79b.dao.mapper.DependeTareaRowMapper;
import com.ejie.aa79b.dao.mapper.DetalleTareaRowMapper;
import com.ejie.aa79b.dao.mapper.GanttExpTrabajoRowMapper;
import com.ejie.aa79b.dao.mapper.GanttRecursoRowMapper;
import com.ejie.aa79b.dao.mapper.IcsRowMapper;
import com.ejie.aa79b.dao.mapper.IcsTareasTrabajoRowMapper;
import com.ejie.aa79b.dao.mapper.OtrosTrabajoRowMapper;
import com.ejie.aa79b.dao.mapper.TareasExpedienteRowMapper;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

@Repository()
@Transactional()
public class CargaTrabajoDaoImpl extends GenericoDaoImpl<ExpTareasResumen> implements CargaTrabajoDao {
	public static final String[] ORDER_BY_WHITE_LIST = new String[] { " IDTAREA " };
	protected static final String FECHA_INI_NOT_NULL = " AND t1.FECHA_INICIO_081 IS NOT NULL ";
	protected static final String FECHA_FIN_NOT_NULL = " AND t1.FECHA_FIN_081 IS NOT NULL ";
	protected static final String LEFTJOIN15ON81 = "LEFT JOIN AA79B15S01 a1 ON t1.ID_TIPO_TAREA_081 = a1.ID_015 ";
	protected static final String DECODE_E1TIPOEXPEDIENTE51 = "DECODE(e1.ID_TIPO_EXPEDIENTE_051";
	protected static final String A1DESC_ES_015TIPOTAREAES = "a1.DESC_ES_015 TIPOTAREAES";
	protected static final String FROM_81_T1 = "FROM AA79B81S01 t1";
	protected static final String DDMMYY = "DD/mm/YY";
	protected static final String DECODE_T1ESTADOEJECUCION_081 = "DECODE(t1.ESTADO_EJECUCION_081";
	protected static final String A1DESC_EU_015 = "a1.DESC_EU_015";
	protected static final String T1FECHA_FIN_081 = "t1.FECHA_FIN_081";
	protected static final String TOCHAR_T1FECHAFIN_081 = "TO_CHAR(t1.FECHA_FIN_081,'HH24:MI')";
	protected static final String T1FECHA_INI_081 = "t1.FECHA_INICIO_081";
	protected static final String TOCHAR_T1FECHAINI_081 = "TO_CHAR(t1.FECHA_INICIO_081,'HH24:MI')";
	protected static final String ASC = " ASC, ";

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public CargaTrabajoDaoImpl() {
		super(ExpTareasResumen.class);
	}

	private RowMapper<ExpTareasResumen> rwMap = new CargaTrabajoRowMapper();

	private RowMapper<Tareas> rwMapIcs = new IcsRowMapper();

	private RowMapper<TareasTrabajo> rwMapIcsTareasTrabajo = new IcsTareasTrabajoRowMapper();

	private RowMapper<ExpTareasResumen> rwMapDetalleTarea = new DetalleTareaRowMapper();

	private RowMapper<ExpTareasResumen> rwMapDependeTarea = new DependeTareaRowMapper();

	private RowMapper<ExpTareasResumen> rwMapTareasExpediente = new TareasExpedienteRowMapper();

	private RowMapper<DocumentosExpediente> rwMapDocumentosTarea = new CargaTrabajoDocumentosTareaRowMapper();

	private RowMapper<ExpTareasResumen> rwMapOtrosTrabajo = new OtrosTrabajoRowMapper();

	private RowMapper<ExpTareasResumen> rwMapGanttRecurso = new GanttRecursoRowMapper();

	private RowMapper<ExpTareasResumen> rwMapGanttExpTrabajo = new GanttExpTrabajoRowMapper();

	private RowMapper<ExpTareasResumen> getRwMapExpTareasPK() {
		return this.rwMapExpTareasPK;
	}

	private RowMapper<ExpTareasResumen> rwMapExpTareasPK = new RowMapper<ExpTareasResumen>() {
		@Override
		public ExpTareasResumen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ExpTareasResumen expediente = new ExpTareasResumen();
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			expediente.setTarea(tareas);
			return expediente;
		}
	};

	@Override
	protected String getSelect() {

		Locale locale = LocaleContextHolder.getLocale();
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder str = new StringBuilder();
		str.append("" + DBConstants.SELECT + " ");

		// Tareas
		str.append("t51.ANYO_051 ANYO ");
		str.append(", t51.NUM_EXP_051 NUMEXP ");
		str.append(", SUBSTR(t51.ANYO_051,3,4) || '/' || LPAD(t51.NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO");
		str.append(", t1.ID_TAREA_081 IDTAREA ");
		str.append(", t1.ID_TIPO_TAREA_081 IDTIPOTAREA ");
		str.append(", t15.DESC_ES_015 TIPOTAREAES ");
		str.append(", t15.DESC_EU_015 TIPOTAREAEU ");
		str.append(", t1.IND_FACTURABLE_081 INDFACTURABLE ");
		str.append(", " + T1FECHA_INI_081 + " FECHAINI ");
		str.append(", " + TOCHAR_T1FECHAINI_081 + " " + DBConstants.HORAINI + " ");
		str.append(", " + T1FECHA_FIN_081 + " " + DBConstants.FECHAFIN + " ");
		str.append(", " + TOCHAR_T1FECHAFIN_081 + " " + DBConstants.HORAFIN + " ");
		str.append(", t1.HORAS_PREVISTAS_081 HORASPREVISTAS ");
		str.append(", t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION ");
		str.append(", p1.DNI DNIRECURSO ");
		str.append(", p1.SUFDNI SUFDNIRECURSO ");
		str.append(", p1.NOMBRE NOMBRERECURSO ");
		str.append(", p1.APEL1 APEL1RECURSO ");
		str.append(", p1.APEL2 APEL2RECURSO ");
		str.append(", t1.DNI_ASIGNADOR_081 DNIASIGNADOR ");
		str.append(", t77.SUFDNI_077 SUFDNIASIGNADOR ");
		str.append(", t77.NOMBRE_077 NOMBREASIGNADOR ");
		str.append(", t77.APEL1_077 APEL1ASIGNADOR ");
		str.append(", t77.APEL2_077 APEL2ASIGNADOR ");
		str.append(", t1.ESTADO_ASIGNACION_081 ESTADOASIGID ");
		str.append(",DECODE(t1.ESTADO_ASIGNACION_081, '")
				.append(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel(), null, locale));
		str.append("', '").append(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel(), null, locale));
		str.append("', '").append(EstadoAceptacionTareaEnum.ACEPTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.ACEPTADA.getLabel(), null, locale));
		str.append("', '").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.RECHAZADA.getLabel(), null, locale))
				.append("') AS ESTADOASIGNACIONDESC");
		str.append(", CASE WHEN t1.ESTADO_EJECUCION_081 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and " + T1FECHA_FIN_081 + " < SYSDATE then " + EstadoEjecucionTareaEnum.RETRASADA.getValue()
				+ " else t1.ESTADO_EJECUCION_081 END AS ESTADOEJECID ");
		str.append(", CASE WHEN t1.ESTADO_EJECUCION_081 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and " + T1FECHA_FIN_081 + " < SYSDATE then '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "' ELSE ");
		str.append(DECODE_T1ESTADOEJECUCION_081 + ", '").append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue())
				.append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale));
		str.append("', '").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale))
				.append("') END AS ESTADOEJECUCIONDESC");
		str.append(", t1.OBSERV_081 OBSERV ");
		str.append(", t1.ORDEN_081 ORDEN ");
		str.append(", t1.FECHA_ASIGNACION_081 FECHAASIGNACION ");
		str.append(", TO_CHAR(t1.FECHA_ASIGNACION_081,'HH24:MI') HORAASIGNACION ");
		str.append(", t1.FECHA_ACEPTACION_081 FECHAACEPTACION ");
		// Expediente
		str.append(", t51.TITULO_051 TITULO ");
		str.append(", t51.ID_TIPO_EXPEDIENTE_051 IDTIPOEXPEDIENTE ");
		str.append(", DECODE(t51.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage("label.interpretacionAbr", null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage("label.traduccionAbr", null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage("label.revisionAbr", null, es)).append("') AS TIPOEXPEDIENTEDESCES");
		str.append(", DECODE(t51.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage("label.interpretacionAbr", null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage("label.traduccionAbr", null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage("label.revisionAbr", null, eu)).append("') AS TIPOEXPEDIENTEDESCEU");
		str.append(", t51.IND_PRIORITARIO_051 INDPRIORITARIO ");
		str.append(
				", ESTADO_TAREAS_DEPENDIENTES(t1.ID_TAREA_081,t1.ANYO_081,t1.NUM_EXP_081) AS ESTADOTAREASDEPENDIENTES ");
		str.append(", ES_RECURSO_DISPONIBLE(t1.DNI_RECURSO_081, t1.ID_TAREA_081, NULL) AS ESRECURSODISPONIBLE ");
		str.append(", t1.ID_TAREA_REL_081 IDTAREAREL ");
		str.append(", t26.DESC_EU_026 GRUPOTRABAJO ");
		str.append(", t53.FECHA_FINAL_IZO_053 FECHAFINALIZO ");
		str.append(", TO_CHAR(t53.FECHA_FINAL_IZO_053,'HH24:MI') HORAFINALIZO ");

		str.append(", t53.NUM_TOTAL_PAL_IZO_053 NUMTOTALPALIZO");
		str.append(", t1.NUM_TOTAL_PAL_091 NUMTOTALPAL");
		str.append(", t1.NUM_PAL_CONCOR_0_84_091 NUMPALCONCOR084091");
		str.append(", t1.NUM_PAL_CONCOR_85_94_091 NUMPALCONCOR8594091");
		str.append(", t1.NUM_PAL_CONCOR_95_100_091 NUMPALCONCOR95100091");
		str.append(", t1.NUM_PAL_CONCOR_95_99_091 NUMPALCONCOR9599091");
		str.append(", t1.NUM_PAL_CONCOR_100_091 NUMPALCONCOR100091");
		str.append(", NVL(t1.NUM_TOTAL_PAL_091,NVL(t53.NUM_TOTAL_PAL_IZO_053, 0)) AS NUMPALCOLORDER ");
		str.append(", DECODE(t1.ID_TAREA_081, null, 1,0) AS TIENETAREA ");
		str.append(", t1.FECHA_INICIO_081 " + DBConstants.FECHAINICIOPREVISTA);
		str.append(", TO_CHAR(t1.FECHA_INICIO_081,'HH24:MI') " + DBConstants.HORAINICIOPREVISTA);
		str.append(", t1.FECHA_FIN_081 " + DBConstants.FECHAFINPREVISTA);
		str.append(", TO_CHAR(t1.FECHA_FIN_081,'HH24:MI') " + DBConstants.HORAFINPREVISTA);
		str.append(", t53.IND_PUBLICAR_BOPV_053 INDPUBLICARBOPV ");
		str.append(", t53.IND_PREVISTO_BOPV_053 INDPREVISTOBOPV ");

		return str.toString();
	}

	@Override
	protected String getFrom(ExpTareasResumen bean, List<Object> params) {

		StringBuilder from = new StringBuilder();

		from.append(" FROM X54JAPI_PERSONAL_IZO p1 ");
		if (null != bean.getTarea() && Constants.SI.equals(bean.getTarea().getRecursoAsignacion())) {
			from.append(" LEFT ");
		}
		from.append(" JOIN (SELECT ");

		from.append(" t81.id_tarea_081, ");
		from.append(" t81.num_exp_081, ");
		from.append(" t81.anyo_081, ");
		from.append(" t81.id_tipo_tarea_081, ");
		from.append(" t81.ind_facturable_081, ");
		from.append(" t81.dni_recurso_081, ");
		from.append(" t81.fecha_inicio_081 , ");
		from.append(" t81.fecha_fin_081, ");
		from.append(" t81.horas_previstas_081, ");
		from.append(" t81.recurso_asignacion_081, ");
		from.append(" t81.dni_asignador_081, ");
		from.append(" t81.estado_asignacion_081, ");
		from.append(" t81.estado_ejecucion_081, ");
		from.append(" t81.observ_081, ");
		from.append(" t81.orden_081, ");
		from.append(" t81.fecha_asignacion_081, ");
		from.append(" t81.fecha_aceptacion_081, ");
		from.append(" t81.id_tarea_rel_081,  ");
		from.append(" sum(tr2.num_total_pal_091)           num_total_pal_091, ");
		from.append(" sum(tr2.num_pal_concor_0_84_091)     num_pal_concor_0_84_091, ");
		from.append(" sum(tr2.num_pal_concor_85_94_091)    num_pal_concor_85_94_091, ");
		from.append(" sum(tr2.num_pal_concor_95_100_091)   num_pal_concor_95_100_091, ");
		from.append(" sum(tr2.num_pal_concor_95_99_091)   num_pal_concor_95_99_091, ");
		from.append(" sum(tr2.num_pal_concor_100_091)   num_pal_concor_100_091 ");
		from.append(" FROM AA79B81S01 t81 ");
		from.append(" LEFT JOIN aa79b83t00 t83 ON t81.id_tarea_081 = t83.id_tarea_083 ");
		// descartamos tareas de expedientes rechazado o anulados
		from.append(" JOIN aa79b51t00   t51aux ON t81.anyo_081 = t51aux.anyo_051 ");
		from.append(" AND t81.num_exp_081 = t51aux.num_exp_051 ");
		from.append(" JOIN aa79b59s01   t59 ON t51aux.anyo_051 = t59.anyo_059 ");
		from.append(" AND t51aux.num_exp_051 = t59.num_exp_059  ");
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(bean.getIdTipoExpediente())) {
			from.append(DaoConstants.AND + "t51aux.ID_TIPO_EXPEDIENTE_051 = ? ");
			params.add(TipoExpedienteEnum.INTERPRETACION.getValue());
		} else if (TipoExpedienteEnum.TRADUCCION.getValue().equals(bean.getIdTipoExpediente())) {
			from.append(DaoConstants.AND + "t51aux.ID_TIPO_EXPEDIENTE_051 IN (?,?) ");
			params.add(TipoExpedienteEnum.TRADUCCION.getValue());
			params.add(TipoExpedienteEnum.REVISION.getValue());
		}

		from.append(" AND t51aux.estado_bitacora_051 = t59.id_estado_bitacora_059 ");
		from.append(" AND t59.id_estado_exp_059 IN ( ");
		from.append(EstadoExpedienteEnum.EN_CURSO.getValue()).append(",");
		from.append(EstadoExpedienteEnum.CERRADO.getValue()).append(")");

		from.append(
				"LEFT JOIN AA79B81S01 tr1 ON tr1.ANYO_081 = t81.ANYO_081 AND tr1.NUM_EXP_081 = t81.NUM_EXP_081 AND tr1.ID_TIPO_TAREA_081 =");
		from.append(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		from.append(
				" LEFT JOIN AA79B91S01 tr2 ON tr1.ID_TAREA_081 = tr2.ID_TAREA_091 AND tr2.id_doc_orig_091 = t83.id_doc_083");
		from.append(
				" WHERE t81.ESTADO_EJECUCION_081 <> 3 AND t81.ESTADO_ASIGNACION_081 <> 1 AND t81.RECURSO_ASIGNACION_081 = 'I' ");

		if (null != bean.getTarea()) {

			from.append(SqlUtils.generarWhereIgual("t81.ID_TIPO_TAREA_081", bean.getTarea().getTipoTarea().getId015(),
					params));
			if (bean.getTarea().getEstadoAsignado() != Constants.CERO) {
				from.append(SqlUtils.generarWhereIgual("t81.ESTADO_ASIGNACION_081", bean.getTarea().getEstadoAsignado(),
						params));
			}
			if (bean.getTarea().getEstadoEjecucion() != Constants.CERO) {
				from.append(" AND " + bean.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN t81.ESTADO_EJECUCION_081 = '1' AND t81.FECHA_FIN_081 < SYSDATE THEN 2 ELSE t81.ESTADO_EJECUCION_081 END ");
			}
			if (null != bean.getTarea().getFechaIni()) {
				from.append(SqlUtils.generarWhereMayorIgualFecha("t81.FECHA_INICIO_081", DDMMYY,
						bean.getTarea().getFechaIni(), params));
			}
			if (null != bean.getTarea().getFechaFin()) {
				from.append(SqlUtils.generarWhereMenorIgualFecha("t81.FECHA_FIN_081", DDMMYY,
						bean.getTarea().getFechaFin(), params));
			}
		}

		from.append("GROUP BY t81.id_tarea_081,  ");
		from.append("t81.num_exp_081, ");
		from.append("t81.anyo_081, ");
		from.append("t81.id_tipo_tarea_081, ");
		from.append("t81.ind_facturable_081, ");
		from.append(" t81.dni_recurso_081, ");
		from.append(" t81.fecha_inicio_081 , ");
		from.append("t81.fecha_fin_081, ");
		from.append("t81.horas_previstas_081, ");
		from.append("t81.recurso_asignacion_081, ");
		from.append("t81.dni_asignador_081, ");
		from.append("t81.estado_asignacion_081, ");
		from.append("t81.estado_ejecucion_081, ");
		from.append("t81.observ_081                  , ");
		from.append("t81.orden_081                   , ");
		from.append("t81.fecha_asignacion_081        , ");
		from.append(" t81.fecha_aceptacion_081 , ");
		from.append("t81.id_tarea_rel_081");

		from.append(") t1 ON p1.DNI = t1.DNI_RECURSO_081 ");

		from.append(" LEFT JOIN aa79b51t00   t51 ON t1.anyo_081 = t51.anyo_051 ");
		from.append(" AND t1.num_exp_081 = t51.num_exp_051 ");

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(bean.getIdTipoExpediente())) {
			from.append(DaoConstants.AND + "t51.ID_TIPO_EXPEDIENTE_051 = ? ");
			params.add(TipoExpedienteEnum.INTERPRETACION.getValue());
		} else if (TipoExpedienteEnum.TRADUCCION.getValue().equals(bean.getIdTipoExpediente())) {
			from.append(DaoConstants.AND + "t51.ID_TIPO_EXPEDIENTE_051 IN (?,?) ");
			params.add(TipoExpedienteEnum.TRADUCCION.getValue());
			params.add(TipoExpedienteEnum.REVISION.getValue());
		}

		from.append("LEFT JOIN aa79b53t00   t53 ON t53.anyo_053 = t51.anyo_051 ");
		from.append(" AND t53.num_exp_053 = t51.num_exp_051 ");
		from.append("LEFT JOIN aa79b15s01   t15 ON t1.id_tipo_tarea_081 = t15.id_015 ");
		from.append("LEFT JOIN aa79b77s01   t77 ON t1.dni_asignador_081 = t77.dni_077 ");
		from.append("LEFT JOIN aa79b26s01 t26 ON t26.id_026 = obtener_grupo_trabajo(t51.anyo_051, t51.num_exp_051) ");

		if (null != bean.getIdGrupo()) {
			// GruposTrabajo
			from.append("JOIN AA79B28S01 g1 ON p1.DNI = g1.DNI_TRABAJADOR_028 ");
		}

		return from.toString();
	}

	protected String getSelectDetalleTarea() {

		Locale locale = LocaleContextHolder.getLocale();
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder query = new StringBuilder();
		query.append("" + DBConstants.SELECT + " ");
		query.append("t1.ANYO_081 ANYO ");
		query.append(", t1.NUM_EXP_081 NUMEXP ");
		query.append(", t1.ID_TAREA_081 IDTAREA ");
		query.append(", t1.ID_TIPO_TAREA_081 IDTIPOTAREA ");
		query.append(", " + A1DESC_ES_015TIPOTAREAES + " ");
		query.append(", " + A1DESC_EU_015 + " " + DBConstants.TIPOTAREAEU + " ");
		query.append(", DECODE(t1.IND_FACTURABLE_081, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS INDFACTURABLE");
		query.append(", " + T1FECHA_INI_081 + " FECHAINI ");
		query.append(", " + TOCHAR_T1FECHAINI_081 + " " + DBConstants.HORAINI + " ");
		query.append(", " + T1FECHA_FIN_081 + " " + DBConstants.FECHAFIN + " ");
		query.append(", " + TOCHAR_T1FECHAFIN_081 + " " + DBConstants.HORAFIN + " ");
		query.append(", t1.HORAS_PREVISTAS_081 HORASPREVISTAS ");
		query.append(", t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION ");
		query.append(", t1.DNI_RECURSO_081 DNIRECURSO ");
		query.append(", p1.SUFDNI_077 SUFDNIRECURSO ");
		query.append(", p1.NOMBRE_077 NOMBRERECURSO ");
		query.append(", p1.APEL1_077 APEL1RECURSO ");
		query.append(", p1.APEL2_077 APEL2RECURSO ");
		query.append(", t1.DNI_ASIGNADOR_081 DNIASIGNADOR ");
		query.append(", p2.SUFDNI_077 SUFDNIASIGNADOR ");
		query.append(", p2.NOMBRE_077 NOMBREASIGNADOR ");
		query.append(", p2.APEL1_077 APEL1ASIGNADOR ");
		query.append(", p2.APEL2_077 APEL2ASIGNADOR ");
		query.append(", t1.ESTADO_ASIGNACION_081 ESTADOASIGID ");
		query.append(",DECODE(t1.ESTADO_ASIGNACION_081, '")
				.append(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel(), null, locale));
		query.append("', '").append(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel(), null, locale));
		query.append("', '").append(EstadoAceptacionTareaEnum.ACEPTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.ACEPTADA.getLabel(), null, locale));
		query.append("', '").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.RECHAZADA.getLabel(), null, locale))
				.append("') AS ESTADOASIGNACIONDESC");
		query.append(", CASE WHEN t1.ESTADO_EJECUCION_081 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and " + T1FECHA_FIN_081 + " < SYSDATE then " + EstadoEjecucionTareaEnum.RETRASADA.getValue()
				+ " else t1.ESTADO_EJECUCION_081 END AS ESTADOEJECID ");
		query.append(", CASE WHEN t1.ESTADO_EJECUCION_081 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and " + T1FECHA_FIN_081 + " < SYSDATE then '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "' ELSE ");
		query.append(DECODE_T1ESTADOEJECUCION_081 + ", '")
				.append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale));
		query.append("', '").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale))
				.append("') END AS ESTADOEJECUCIONDESC");
		query.append(", ejecucionTarea.FECHA_EJECUCION_082 FECHAEJECUCION");
		query.append(", TO_CHAR(ejecucionTarea.FECHA_EJECUCION_082,'HH24:MI') HORAEJECUCION ");
		query.append(", t1.OBSERV_081 OBSERV ");
		query.append(", t1.ORDEN_081 ORDEN ");
		query.append(", t1.FECHA_ASIGNACION_081 FECHAASIGNACION ");
		query.append(", TO_CHAR(t1.FECHA_ASIGNACION_081,'HH24:MI') HORAASIGNACION ");
		query.append(", t1.FECHA_ACEPTACION_081 FECHAACEPTACION ");
		query.append(", TO_CHAR(t1.FECHA_ACEPTACION_081,'HH24:MI') HORAACEPTACION ");
		query.append(", e2.DESC_ES DESCES ");
		query.append(", e2.DESC_EU DESCEU ");
		query.append(", e2.CIF CIF ");
		query.append(", e1.ID_TIPO_EXPEDIENTE_051 IDTIPOEXPEDIENTE ");
		query.append(", " + DECODE_E1TIPOEXPEDIENTE51 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage("label.interpretacion", null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage("label.traduccion", null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage("label.revision", null, es)).append("') AS TIPOEXPEDIENTEDESCES");
		query.append(", " + DECODE_E1TIPOEXPEDIENTE51 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage("label.interpretacion", null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage("label.traduccion", null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage("label.revision", null, eu)).append("') AS TIPOEXPEDIENTEDESCEU");
		query.append(", i2.MODO_INTERPRETACION_052 MODOINTERPRETACION ");
		query.append(", i3.DESC_ES_014 MODOINTERPRETACIONDESCES ");
		query.append(", i3.DESC_EU_014 MODOINTERPRETACIONDESCEU ");
		query.append(", t2.IND_PUBLICAR_BOPV_053 BOPV ");
		query.append(", t2.FECHA_FINAL_IZO_053 FECHAFINALIZO ");
		query.append(", t2.NUM_TOTAL_PAL_SOLIC_053 NUMTOTALPALSOLIC ");
		query.append(", t2.NUM_TOTAL_PAL_IZO_053 NUMTOTALPALIZO ");
		query.append(", t2.ID_IDIOMA_053 IDIDIOMA ");
		query.append(", t77.NOMBRE_077 NOMBRESOLIC ");
		query.append(", t77.APEL1_077 APEL1SOLIC ");
		query.append(", t77.APEL2_077 APEL2SOLIC ");
		query.append(", NVL(c1.TELMOVIL_031,'-') TELSOLIC ");
		query.append(", NVL(c1.EMAIL_031,'-') EMAILSOLIC ");
		query.append(", i1.DESC_IDIOMA_EU_009 DESCIDIOMAEU ");
		query.append(", i1.DESC_IDIOMA_ES_009 DESCIDIOMAES ");
		query.append(", m1.OBSV_RECHAZO_084 OBSRVRECHAZO ");
		query.append(", t1.ID_TAREA_REL_081 IDTAREAREL ");
		query.append(", t3.ID_LOTE_081 IDLOTE ");
		query.append(", l1.NOMBRE_LOTE_029 NOMBRELOTE ");
		query.append(", l1.FECHA_INICIO_029 FECHAINICIO ");
		query.append(", l1.FECHA_FIN_029 " + DBConstants.FECHAFIN + " ");
		query.append(", l1.IMPORTE_PALABRA_029 IMPORTEPALABRA ");
		query.append(", l1.POR_PAGO_PAL_CONCOR_85_94_029 PORPAGOPALCONCOR8594 ");
		query.append(", l1.POR_PAGO_PAL_CONCOR_95_100_029 PORPAGOPALCONCOR95100 ");
		query.append(", l1.POR_REVISION_029 PORREVISION ");
		query.append(", l1.POR_RECARGO_FORMATO_029 PORRECARGOFORMATO ");
		query.append(", l1.POR_RECARGO_APREMIO_029 PORRECARGOAPREMIO ");
		query.append(", l1.POR_PENALIZACION_029 PORPENALIZACION ");
		query.append(", NVL(l1.IMPORTE_ASIGNADO_029, 0) as IMPORTEASIGNADO ");
		query.append(", NVL(l1.IMPORTE_CONSUMIDO_029, 0) as IMPORTECONSUMIDO ");
		query.append(", t3.ID_TIPO_TAREA_081 IDTIPOTAREAREL ");
		query.append(", a2.DESC_ES_015 TIPOTAREAESREL ");
		query.append(", a2.DESC_EU_015 TIPOTAREAEUREL ");
		query.append(", t2.NUM_TOTAL_PAL_IZO_053 NUMTOTALPALIZO");
		query.append(", t1.NUM_TOTAL_PAL_091 NUMTOTALPAL");
		query.append(", t1.NUM_PAL_CONCOR_0_84_091 NUMPALCONCOR084091");
		query.append(", t1.NUM_PAL_CONCOR_85_94_091 NUMPALCONCOR8594091");
		query.append(", t1.NUM_PAL_CONCOR_95_100_091 NUMPALCONCOR95100091");
		query.append(", t1.NUM_PAL_CONCOR_95_99_091 NUMPALCONCOR9599091");
		query.append(", t1.NUM_PAL_CONCOR_100_091 NUMPALCONCOR100091");
		query.append(", NVL(t1.NUM_TOTAL_PAL_091,NVL(t2.NUM_TOTAL_PAL_IZO_053, 0)) AS NUMPALCOLORDER ");
		query.append(", tg2.DESC_EU_026 GRUPOTRABAJO ");
		query.append(", t1.ID_TIPO_REVISION_081 IDTIPOREVISION ");
		query.append(", DECODE(t1.DESC_ES_018,null,' ',t1.DESC_ES_018) AS TIPOREVISIONDESCES ");
		query.append(", DECODE(t1.DESC_EU_018,null,' ',t1.DESC_EU_018) AS TIPOREVISIONDESCEU ");
		query.append(", t1.IND_MOSTRAR_NOTAS_A_TRAD_081 INDMOSTRARNOTASATRAD ");
		query.append(", t1.IND_OBLIGAR_XLIFF_081 INDOBLIGARXLIFF ");
		return query.toString();
	}

	protected String getSelectDocumentosTarea() {
		StringBuilder query = new StringBuilder();
		query.append("" + DBConstants.SELECT + " ");
		query.append("d2.ID_DOC_056 IDDOC ");
		query.append(", d2.ANYO_056 ANYO ");
		query.append(", d2.NUM_EXP_056 NUMEXP ");
		query.append(", d2.NUM_PAL_SOLIC_056 NUMPALSOLIC ");
		query.append(", d2.NUM_PAL_IZO_056 NUMPALIZO ");
		query.append(", d2.TIPO_DOCUMENTO_056 TIPODOCUMENTO ");
		query.append(", d3.DESC_ES_042 TIPODOCUMENTODESCES ");
		query.append(", d3.DESC_EU_042 TIPODOCUMENTODESCEU ");
		query.append(", d2.NOMBRE_FICHERO_056 NOMBREFICHERO ");
		query.append(", d2.OID_FICHERO_056 OIDFICHERO ");
		return query.toString();
	}

	protected String getFromDetalleTarea() {

		StringBuilder from = new StringBuilder();
		from.append("FROM (SELECT ");
		from.append(" t81.id_tarea_081, ");
		from.append(" t81.num_exp_081, ");
		from.append(" t81.anyo_081, ");
		from.append(" t81.id_tipo_tarea_081, ");
		from.append(" t81.ind_facturable_081, ");
		from.append(" t81.dni_recurso_081, ");
		from.append(" t81.fecha_inicio_081 , ");
		from.append(" t81.fecha_fin_081, ");
		from.append(" t81.horas_previstas_081, ");
		from.append(" t81.recurso_asignacion_081, ");
		from.append(" t81.dni_asignador_081, ");
		from.append(" t81.estado_asignacion_081, ");
		from.append(" t81.estado_ejecucion_081, ");
		from.append(" t81.observ_081, ");
		from.append(" t81.orden_081, ");
		from.append(" t81.fecha_asignacion_081, ");
		from.append(" t81.fecha_aceptacion_081, ");
		from.append(" t81.id_tarea_rel_081,  ");
		from.append(" sum(tr2.num_total_pal_091)           num_total_pal_091, ");
		from.append(" sum(tr2.num_pal_concor_0_84_091)     num_pal_concor_0_84_091, ");
		from.append(" sum(tr2.num_pal_concor_85_94_091)    num_pal_concor_85_94_091, ");
		from.append(" sum(tr2.num_pal_concor_95_100_091)   num_pal_concor_95_100_091, ");
		from.append(" sum(tr2.num_pal_concor_95_99_091)   num_pal_concor_95_99_091, ");
		from.append(" sum(tr2.num_pal_concor_100_091)   num_pal_concor_100_091, ");
		from.append(" t81.ID_TIPO_REVISION_081 ID_TIPO_REVISION_081, ");
		from.append(" trev1.DESC_ES_018 DESC_ES_018, ");
		from.append(" trev1.DESC_EU_018 DESC_EU_018, ");
		from.append(" t81.IND_MOSTRAR_NOTAS_A_TRAD_081, ");
		from.append(" t81.IND_OBLIGAR_XLIFF_081 ");
		from.append(" FROM AA79B81S01 t81 ");
		from.append(" LEFT JOIN aa79b83t00 t83 ON t81.id_tarea_081 = t83.id_tarea_083 ");
		from.append(
				"LEFT JOIN AA79B81S01 tr1 ON tr1.ANYO_081 = t81.ANYO_081 AND tr1.NUM_EXP_081 = t81.NUM_EXP_081 AND tr1.ID_TIPO_TAREA_081 =");
		from.append(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		from.append(
				"LEFT JOIN AA79B91S01 tr2 ON tr1.ID_TAREA_081 = tr2.ID_TAREA_091 AND tr2.id_doc_orig_091 = t83.id_doc_083 ");
		from.append("LEFT JOIN AA79B18S01 trev1 ON t81.ID_TIPO_REVISION_081 = trev1.ID_018 ");
		from.append(" GROUP BY t81.id_tarea_081,  ");
		from.append("t81.num_exp_081, ");
		from.append("t81.anyo_081, ");
		from.append("t81.id_tipo_tarea_081, ");
		from.append("t81.ind_facturable_081, ");
		from.append(" t81.dni_recurso_081, ");
		from.append(" t81.fecha_inicio_081 , ");
		from.append("t81.fecha_fin_081, ");
		from.append("t81.horas_previstas_081, ");
		from.append("t81.recurso_asignacion_081, ");
		from.append("t81.dni_asignador_081, ");
		from.append("t81.estado_asignacion_081, ");
		from.append("t81.estado_ejecucion_081, ");
		from.append("t81.observ_081                  , ");
		from.append("t81.orden_081                   , ");
		from.append("t81.fecha_asignacion_081        , ");
		from.append(" t81.fecha_aceptacion_081 , ");
		from.append("t81.id_tarea_rel_081, ");
		from.append("t81.ID_TIPO_REVISION_081, ");
		from.append("trev1.DESC_ES_018, ");
		from.append("trev1.DESC_EU_018, ");
		from.append("t81.IND_MOSTRAR_NOTAS_A_TRAD_081, ");
		from.append("t81.IND_OBLIGAR_XLIFF_081  ");
		from.append(") t1 ");
		from.append("LEFT JOIN AA79B82S01 ejecucionTarea on t1.ID_TAREA_081 = ejecucionTarea.ID_TAREA_082 ");
		from.append(LEFTJOIN15ON81);
		from.append("LEFT JOIN AA79B51S01 e1 ON t1.ANYO_081 = e1.ANYO_051 AND t1.NUM_EXP_081 = e1.NUM_EXP_051 ");
		from.append("LEFT JOIN AA79B52S01 i2 ON t1.ANYO_081 = i2.ANYO_052 AND t1.NUM_EXP_081 = i2.NUM_EXP_052 ");
		from.append("LEFT JOIN AA79B14S01 i3 ON i2.MODO_INTERPRETACION_052 = i3.ID_014 ");
		from.append("LEFT JOIN AA79B53S01 t2 ON t1.ANYO_081 = t2.ANYO_053 AND t1.NUM_EXP_081 = t2.NUM_EXP_053 ");
		from.append("LEFT JOIN AA79B54S01 t54 ON t1.ANYO_081 = t54.ANYO_054 AND t1.NUM_EXP_081 = t54.NUM_EXP_054 ");
		from.append("JOIN AA79B77S01 t77 ON t54.DNI_SOLICITANTE_054 = t77.DNI_077 ");
		from.append("LEFT JOIN X54JAPI_DATOS_CONTACTO c1 ON t77.DNI_077 = c1.DNI_031 ");
		from.append("LEFT JOIN AA79B09S01 i1 ON t2.ID_IDIOMA_053 = i1.ID_IDIOMA_009 ");
		from.append("LEFT JOIN AA79B77S01 p1 ON t1.DNI_RECURSO_081 = p1.DNI_077 ");
		from.append("LEFT JOIN AA79B77S01 p2 ON t1.DNI_ASIGNADOR_081 = p2.DNI_077 ");
		from.append("LEFT JOIN AA79B84S01 m1 on t1.ID_TAREA_081 = m1.ID_TAREA_084 ");
		from.append("LEFT JOIN AA79B81S01 t3 on t1.ID_TAREA_REL_081 = t3.ID_TAREA_081 ");
		from.append("LEFT JOIN AA79B29S01 l1 on t3.ID_LOTE_081 = l1.ID_LOTE_029 ");
		from.append("LEFT JOIN X54JAPI_EMPRESAS_PROV e2 on l1.ID_EMPRESA_PROV_029 = e2.CODIGO ");
		from.append("LEFT JOIN AA79B15S01 a2 ON t3.ID_TIPO_TAREA_081 = a2.ID_015 ");
		from.append("LEFT JOIN AA79B26S01 tg2 ON tg2.ID_026 = OBTENER_GRUPO_TRABAJO(e1.ANYO_051,e1.NUM_EXP_051) ");

		return from.toString();
	}

	protected String getFromDocumentosTarea() {

		StringBuilder from = new StringBuilder();
		from.append(" FROM AA79B81S01 t1 ");
		// Documentos tarea
		from.append("LEFT JOIN AA79B83S01 d1 on t1.ID_TAREA_081 = d1.ID_TAREA_083 ");
		// Documentos
		from.append("LEFT JOIN AA79B56S01 d2 on d1.ID_DOC_083 = d2.ID_DOC_056 ");
		// Tipo Documento
		from.append("LEFT JOIN AA79B42S01 d3 on d2.TIPO_DOCUMENTO_056 = d3.ID_042 ");

		return from.toString();
	}

	@Override
	protected RowMapper<ExpTareasResumen> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return CargaTrabajoDaoImpl.ORDER_BY_WHITE_LIST;

	}

	@Override
	protected String getPK() {
		return "IDTAREA";
	}

	@Override
	protected RowMapper<ExpTareasResumen> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(ExpTareasResumen bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(ExpTareasResumen bean, Boolean startsWith, List<Object> params,
			Boolean expedienteGantt) {
		StringBuilder strArea = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		strArea.append("WHERE 1 = 1 ");
		if (null != bean.getIdGrupo()) {
			strArea.append(SqlUtils.generarWhereIgual("g1.ID_GRUPO_028", bean.getIdGrupo(), params));
		}
		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			if (!expedienteGantt) {
				strArea.append(SqlUtils.generarWhereIn("p1.DNI", bean.getFiltroDatos(), params));
			} else {
				strArea.append(SqlUtils.generarWhereIn("p1.DNI_077", bean.getFiltroDatos(), params));
			}
		}
		if (null != bean.getTarea()) {
			strArea.append(SqlUtils.generarWhereIgual("t1.ID_TIPO_TAREA_081", bean.getTarea().getTipoTarea().getId015(),
					params));
			if (bean.getTarea().getEstadoAsignado() != Constants.CERO) {
				strArea.append(SqlUtils.generarWhereIgual("t1.ESTADO_ASIGNACION_081",
						bean.getTarea().getEstadoAsignado(), params));
			}
			if (bean.getTarea().getEstadoEjecucion() != Constants.CERO) {
				strArea.append(" AND " + bean.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN t1.ESTADO_EJECUCION_081 = '1' AND t1.FECHA_FIN_081 < SYSDATE THEN 2 ELSE t1.ESTADO_EJECUCION_081 END ");
			}
			if (null != bean.getTarea().getFechaIni()) {
				strArea.append(SqlUtils.generarWhereMayorIgualFecha(T1FECHA_INI_081, DDMMYY,
						bean.getTarea().getFechaIni(), params));
			}
			if (null != bean.getTarea().getFechaFin()) {
				strArea.append(SqlUtils.generarWhereMenorIgualFecha(T1FECHA_FIN_081, DDMMYY,
						bean.getTarea().getFechaFin(), params));
			}
		}
		return strArea.toString();
	}

	protected String getWhereLikeTareas(ExpTareasResumen bean, Boolean startsWith, List<Object> params,
			Boolean expedienteGantt) {
		StringBuilder strArea = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		strArea.append("WHERE 1 = 1 ");
		if (null != bean.getIdGrupo()) {
			strArea.append(SqlUtils.generarWhereIgual("g1.ID_GRUPO_028", bean.getIdGrupo(), params));
		}
		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			if (!expedienteGantt) {
				strArea.append(SqlUtils.generarWhereIn("p1.DNI", bean.getFiltroDatos(), params));
			} else {
				strArea.append(SqlUtils.generarWhereIn("t77.DNI_077", bean.getFiltroDatos(), params));
			}
		}
		return strArea.toString();
	}

	@Override
	public ExpTareasResumen findTarea(BigDecimal idTarea) {
		StringBuilder query = new StringBuilder(this.getSelectDetalleTarea());
		List<Object> params = new ArrayList<Object>();

		query.append(this.getFromDetalleTarea());
		query.append(" WHERE t1.ID_TAREA_081 = ? ");
		params.add(idTarea);
		List<ExpTareasResumen> tareaList = this.getJdbcTemplate().query(query.toString(), this.rwMapDetalleTarea,
				params.toArray());
		return DataAccessUtils.uniqueResult(tareaList);

	}

	@Override
	public List<DocumentosExpediente> findDocumentosTarea(BigDecimal idTarea) {
		StringBuilder query = new StringBuilder(this.getSelectDocumentosTarea());
		List<Object> params = new ArrayList<Object>();

		query.append(this.getFromDocumentosTarea());
		query.append(" WHERE t1.ID_TAREA_081 = ? ");
		params.add(idTarea);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapDocumentosTarea, params.toArray());
	}

	@Override
	public List<Tareas> obtenerDatosTareas(String tareasSeleccionados) {
		StringBuilder query = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		this.getQueryDatosExpIcs(query, tareasSeleccionados);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapIcs);
	}

	@Override()
	public List<ExpTareasResumen> filtroExpTareas(ExpTareasResumen bean, JQGridRequestDto jqGridRequestDto,
			boolean startsWith, boolean orderNombre) {
		StringBuilder paginatedQuery = new StringBuilder();
		StringBuilder query = new StringBuilder(this.getSelect());
		List<Object> params = new ArrayList<Object>();
		query.append(this.getFrom(bean, params));
		query.append(this.getWhereLikeTareas(bean, startsWith, params, false));
		query.append(DaoConstants.AND + "p1.NOMBRE" + DaoConstants.IS_NOT_NULL);

		if (!orderNombre) {
			return expTareasFiltroOrder(jqGridRequestDto, paginatedQuery, query, params, this.rwMap);
		} else {
			query.append(" ORDER BY TIENETAREA DESC,");
			query.append(" NOMBRERECURSO ASC, " + DBConstants.APEL1RECURSO + ASC + DBConstants.APEL2RECURSO
					+ " ASC, FECHAFIN ASC");
			return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
		}

	}


	private List<ExpTareasResumen> expTareasFiltroOrder(JQGridRequestDto jqGridRequestDto, StringBuilder paginatedQuery,
			StringBuilder query, List<Object> params, RowMapper<ExpTareasResumen> rwMap) {
		if (jqGridRequestDto != null) {
			if (jqGridRequestDto.getSidx() != null) {
				appendOrderBy(jqGridRequestDto, query);
			}

			Long rows = jqGridRequestDto.getRows();
			Long page = jqGridRequestDto.getPage();

			if ((page != null) && (rows != null)) {
				paginatedQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query + ")a) where rnum > "
						+ rows.longValue() * (page.longValue() - 1L) + " and rnum < "
						+ (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				paginatedQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query
						+ ")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			} else {
				paginatedQuery.append(query);
			}
			return this.getJdbcTemplate().query(paginatedQuery.toString(), rwMap, params.toArray());
		} else {
			return this.getJdbcTemplate().query(query.toString(), rwMap, params.toArray());
		}
	}

	private void appendOrderBy(JQGridRequestDto jqGridRequestDto, StringBuilder query) {
		if (jqGridRequestDto.getSidx().split(",").length > 2) {
			query.append(" ORDER BY TIENETAREA DESC");
			query.append(" , NOMBRERECURSO ASC, " + DBConstants.APEL1RECURSO + ASC + DBConstants.APEL2RECURSO + " ASC");
		} else {
			query.append(" ORDER BY TIENETAREA DESC");
			query.append(" , NOMBRERECURSO ASC, " + DBConstants.APEL1RECURSO + ASC + DBConstants.APEL2RECURSO + " ASC");
			query.append(" , " + jqGridRequestDto.getSidx() + " ");
			query.append(jqGridRequestDto.getSord());
		}
	}

	@Override()
	public Long filtroTareasCount(ExpTareasResumen bean, boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("" + DBConstants.SELECT + " COUNT(1)");
		query.append(this.getFrom(bean, params));
		query.append(this.getWhereLikeTareas(bean, startsWith, params, false));
		query.append(DaoConstants.AND + "p1.NOMBRE" + DaoConstants.IS_NOT_NULL);
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(bean.getIdTipoExpediente())) {
			query.append(DaoConstants.AND + "t51.ID_TIPO_EXPEDIENTE_051 = ? ");
			params.add(TipoExpedienteEnum.INTERPRETACION.getValue());
		} else if (TipoExpedienteEnum.TRADUCCION.getValue().equals(bean.getIdTipoExpediente())) {
			query.append(DaoConstants.AND + "t51.ID_TIPO_EXPEDIENTE_051 IN (?,?) ");
			params.add(TipoExpedienteEnum.TRADUCCION.getValue());
			params.add(TipoExpedienteEnum.REVISION.getValue());
		}
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override()
	public List<TableRowDto<ExpTareasResumen>> reorderSelection(ExpTareasResumen bean,
			JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		// SELECT
		List<Object> params = new ArrayList<Object>();
		StringBuilder subQuery = new StringBuilder(this.getSelect());
		// FROM
		subQuery.append(this.getFrom(bean, params));
		// FILTRADO
		subQuery.append(this.getWhereLikeTareas(bean, startsWith, params, false));

		StringBuilder query = DAOUtils.getReorderQuery(jqGridRequestDto, params, subQuery, this.getPK(), Constants.CERO,
				null);

		RowNumResultSetExtractor<ExpTareasResumen> rowNumOrder = new RowNumResultSetExtractor<ExpTareasResumen>(
				this.getRwMapExpTareasPK(), jqGridRequestDto);

		return this.getJdbcTemplate().query(query.toString(), rowNumOrder, params.toArray());

	}

	@Override()
	public List<ExpTareasResumen> getExpedientesTarea(ExpTareasResumen bean) {

		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("" + DBConstants.SELECT + " ");
		query.append("e1.ANYO_051 ANYO, ");
		query.append("e1.NUM_EXP_051 NUMEXP, ");
		query.append("e1.TITULO_051 TITULO, ");
		query.append("NVL(i1.FECHA_FIN_052, t2.FECHA_FINAL_IZO_053) " + DBConstants.FECHAFIN + ", ");
		query.append("NVL(i1.FECHA_INI_052,e1.FECHA_ALTA_051) FECHAINICIO, ");
		query.append("MIN(" + T1FECHA_INI_081 + ") FECHAINICIOTAREA, ");
		query.append(" NULL IDTRABAJO, ");
		query.append(" NULL DESCTRABAJO, ");
		query.append(" NULL FECHAFINTRABAJO, ");
		query.append(" NULL FECHAINICIOTRABAJO, ");
		query.append(" NULL FECHAINICIOTAREATRABAJO ");
		query.append("FROM AA79B51S01 e1 ");
		query.append("LEFT JOIN AA79B52S01 i1 ");
		query.append("ON e1.ANYO_051 = i1.ANYO_052 AND e1.NUM_EXP_051 = i1.NUM_EXP_052 ");
		query.append("LEFT JOIN AA79B53S01 t2 ");
		query.append("ON e1.ANYO_051 = t2.ANYO_053 AND e1.NUM_EXP_051 = t2.NUM_EXP_053 ");
		query.append(
				"JOIN AA79B59S01  ON ANYO_059 = e1.ANYO_051  AND NUM_EXP_059 = e1.NUM_EXP_051 AND ID_ESTADO_BITACORA_059 = e1.ESTADO_BITACORA_051 ");
		query.append("AND AA79B59S01.ID_ESTADO_EXP_059 = " + EstadoExpedienteEnum.EN_CURSO.getValue() + " ");
		query.append("LEFT JOIN AA79B81S01 t1 ON t1.ANYO_081 = e1.ANYO_051 and t1.NUM_EXP_081 = e1.NUM_EXP_051 ");
		query.append(
				"AND t1.ESTADO_EJECUCION_081 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue() + DaoConstants.BLANK);
		query.append("LEFT JOIN AA79B77S01 p1 ");
		query.append("ON t1.DNI_RECURSO_081 = p1.DNI_077 ");
		if (null != bean.getIdGrupo()) {
			query.append("JOIN AA79B28S01 g1 ON t1.DNI_RECURSO_081 = g1.DNI_TRABAJADOR_028 ");
		}
		query.append(this.getWhereLike(bean, false, params, true));
		query.append("GROUP BY e1.ANYO_051, ");
		query.append("e1.NUM_EXP_051, ");
		query.append("e1.ID_TIPO_EXPEDIENTE_051, ");
		query.append(
				"e1.TITULO_051, NVL(i1.FECHA_INI_052,e1.FECHA_ALTA_051),NVL(i1.FECHA_FIN_052, t2.FECHA_FINAL_IZO_053) ");
		// query trabajo
		query.append(" UNION SELECT NULL,NULL,NULL,NULL,NULL,NULL,");
		query.append(" c6.ID_TRABAJO_0C6 IDTRABAJO,");
		query.append(" c6.DESC_TRABAJO_0C6 DESCTRABAJO,");
		query.append(" NVL(c6.FECHA_FIN_0C6, NVL(c6.FECHA_FIN_PREVISTA_0C6, NULL)) FECHAFINTRABAJO,");
		query.append(" c6.FECHA_INICIO_0C6 FECHAINICIOTRABAJO,");
		query.append(" MIN(c7.FECHA_INICIO_0C7) FECHAINICIOTAREATRABAJO");
		query.append(" FROM AA79BC6S01 c6");
		query.append(" LEFT JOIN AA79BC7S01  c7 ON c6.ID_TRABAJO_0C6 = c7.ID_TRABAJO_0C7 ");
		query.append(" AND c7.ESTADO_EJECUCION_0C7 <>" + EstadoEjecucionTareaEnum.EJECUTADA.getValue() + DaoConstants.BLANK);
		query.append(" LEFT JOIN AA79B77S01  p2 ON c7.DNI_RECURSO_0C7 = p2.DNI_077 ");
		if (null != bean.getIdGrupo() && bean.getIdGrupo() != 0) {
			query.append("JOIN AA79B28S01 gc1 ON c7.DNI_RECURSO_0C7 = gc1.DNI_TRABAJADOR_028 ");
		}
		query.append("WHERE 1 = 1 ");
		if (null != bean.getIdGrupo()) {
			// GruposTrabajo
			if (bean.getIdGrupo() == 0) {
				query.append(" AND c7.DNI_RECURSO_0C7 NOT IN (SELECT taux28.DNI_TRABAJADOR_028 FROM AA79B28S01 taux28) ");
			} else {
				query.append(SqlUtils.generarWhereIgual("gc1.ID_GRUPO_028", bean.getIdGrupo(), params));
			}
		}
		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			query.append(SqlUtils.generarWhereIn("p2.DNI_077", bean.getFiltroDatos(), params));
		}
		if (null != bean.getTarea()) {
			query.append(SqlUtils.generarWhereIgual("c7.ID_TIPO_TAREA_0C7", bean.getTarea().getTipoTarea().getId015(),
					params));
			if (bean.getTarea().getEstadoAsignado() != Constants.CERO) {
				query.append(SqlUtils.generarWhereIgual("c7.ESTADO_ASIGNACION_0C7",
						bean.getTarea().getEstadoAsignado(), params));
			}
			if (bean.getTarea().getEstadoEjecucion() != Constants.CERO) {
				query.append(" AND " + bean.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN c7.ESTADO_EJECUCION_0C7 = '1' AND c7.FECHA_INICIO_0C7 < SYSDATE THEN 2 ELSE c7.ESTADO_EJECUCION_0C7 END ");
			}
			if (null != bean.getTarea().getFechaIni()) {
				query.append(SqlUtils.generarWhereMayorIgualFecha("c7.FECHA_INICIO_0C7", DDMMYY,
						bean.getTarea().getFechaIni(), params));
			}
			if (null != bean.getTarea().getFechaFin()) {
				query.append(SqlUtils.generarWhereMenorIgualFecha("c7.FECHA_FIN_0C7", DDMMYY,
						bean.getTarea().getFechaFin(), params));
			}
		}
		query.append(" GROUP BY c6.ID_TRABAJO_0C6,");
		query.append(" c6.DESC_TRABAJO_0C6, ");
		query.append(" c6.FECHA_INICIO_0C6, ");
		query.append(" NVL(c6.FECHA_FIN_0C6, NVL(c6.FECHA_FIN_PREVISTA_0C6, NULL)) ");

		query.append("ORDER BY ANYO ASC, NUMEXP ASC, IDTRABAJO ASC ");
		return this.getJdbcTemplate().query(query.toString(), this.rwMapGanttExpTrabajo, params.toArray());
	}

	@Override()
	public List<ExpTareasResumen> getTareasExpediente(ExpTareasResumen bean) {

		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("" + DBConstants.SELECT + " ");
		query.append("t1.ANYO_081 ANYO, ");
		query.append("t1.NUM_EXP_081 NUMEXP, ");
		query.append("t1.ID_TAREA_081 IDTAREA, ");
		query.append("t1.ESTADO_ASIGNACION_081 ESTADOASIGID, ");
		query.append("CASE WHEN t1.ESTADO_EJECUCION_081 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and FECHA_FIN_081 < SYSDATE then " + EstadoEjecucionTareaEnum.RETRASADA.getValue()
				+ " else t1.ESTADO_EJECUCION_081 END AS ESTADOEJECID, ");
		query.append("t1.ID_TIPO_TAREA_081 IDTIPOTAREA, ");
		query.append("" + T1FECHA_INI_081 + " FECHAINI,  ");
		query.append("" + T1FECHA_FIN_081 + " " + DBConstants.FECHAFIN + ", ");
		query.append("t1.DNI_RECURSO_081 DNIRECURSO, ");
		query.append("p1.NOMBRE_077 NOMBRE, ");
		query.append("p1.APEL1_077 APE1, ");
		query.append("p1.APEL2_077 APE2, ");
		query.append("t2.DESC_ES_015 DESCES, ");
		query.append("t2.DESC_EU_015 DESCEU, ");
		query.append("NULL IDTRABAJO, NULL IDTAREATRABAJO, NULL ESTADOASIGIDTRABAJO, NULL ESTADOEJECIDTRABAJO, ");
		query.append("NULL IDTIPOTAREATRABAJO, NULL FECHAINITRABAJO, NULL FECHAFINTRABAJO, NULL DNIRECURSOTRABAJO, ");
		query.append("NULL NOMBRETRABAJO, NULL APE1TRABAJO, NULL APE2TRABAJO, NULL DESCESTRABAJO, ");
		query.append("NULL DESCEUTRABAJO ");
		query.append("" + FROM_81_T1 + " ");
		if (null != bean.getTarea() && Constants.SI.equals(bean.getTarea().getRecursoAsignacion())) {
			query.append(" LEFT ");
		}
		query.append(" JOIN AA79B77S01 p1 ");
		query.append("ON t1.DNI_RECURSO_081 = p1.DNI_077 ");
		query.append("LEFT JOIN AA79B15S01 t2 ");
		query.append("ON t1.ID_TIPO_TAREA_081 = t2.ID_015 ");
		query.append("LEFT JOIN AA79B51S01 e1 ON t1.ANYO_081 = e1.ANYO_051 and t1.NUM_EXP_081 = e1.NUM_EXP_051 ");
		if (null != bean.getIdGrupo()) {
			query.append("JOIN AA79B28S01 g1 ON t1.DNI_RECURSO_081 = g1.DNI_TRABAJADOR_028 ");
		}
		query.append(
				"JOIN AA79B59S01 b1 ON e1.ANYO_051 = b1.ANYO_059 AND e1.NUM_EXP_051 = b1.NUM_EXP_059 AND e1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 ");
		query.append("AND b1.ID_ESTADO_EXP_059 = " + EstadoExpedienteEnum.EN_CURSO.getValue() + " ");
		query.append(this.getWhereLike(bean, false, params, true));
		query.append(" AND t1.RECURSO_ASIGNACION_081 = '");
		query.append(TipoRecursoEnum.INTERNO.getValue());
		query.append("'");
		// query tareas de trabajo
		query.append(" UNION SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,");
		query.append(" c7.ID_TRABAJO_0C7    IDTRABAJO,");
		query.append(" c7.ID_TAREA_0C7      IDTAREATRABAJO, ");
		query.append(" c7.ESTADO_ASIGNACION_0C7    ESTADOASIGIDTRABAJO,  ");
		query.append(" CASE WHEN c7.ESTADO_EJECUCION_0C7 = '1'");
		query.append(" AND c7.FECHA_FIN_0C7 < SYSDATE THEN 2 ELSE c7.ESTADO_EJECUCION_0C7 END AS ESTADOEJECIDTRABAJO,");
		query.append(" c7.ID_TIPO_TAREA_0C7        IDTIPOTAREATRABAJO, ");
		query.append(" c7.FECHA_INICIO_0C7         FECHAINITRABAJO, ");
		query.append(" c7.FECHA_FIN_0C7            FECHAFINTRABAJO, ");
		query.append(" c7.DNI_RECURSO_0C7          DNIRECURSOTRABAJO, ");
		query.append(" p2.NOMBRE_077               NOMBRETRABAJO, ");
		query.append(" p2.APEL1_077                APE1TRABAJO, ");
		query.append(" p2.APEL2_077                APE2TRABAJO, ");
		query.append(" ct2.DESC_ES_015              DESCESTRABAJO, ");
		query.append(" ct2.DESC_EU_015              DESCEUTRABAJO ");
		query.append(" FROM AA79BC7S01 c7 ");
		if (null != bean.getTarea() && Constants.SI.equals(bean.getTarea().getRecursoAsignacion())) {
			query.append(" LEFT ");
		}
		query.append(" JOIN aa79b77s01  p2 ON c7.DNI_RECURSO_0C7 = p2.DNI_077  ");
		query.append(" LEFT JOIN aa79b15s01  ct2 ON c7.ID_TIPO_TAREA_0C7 = ct2.ID_015 ");
		if (null != bean.getIdGrupo() && bean.getIdGrupo() != 0) {
			query.append("JOIN AA79B28S01 gc1 ON c7.DNI_RECURSO_0C7 = gc1.DNI_TRABAJADOR_028 ");
		}
		query.append("WHERE 1 = 1 ");
		if (null != bean.getIdGrupo()) {
			// GruposTrabajo
			if (bean.getIdGrupo() == 0) {
				query.append(" AND c7.DNI_RECURSO_0C7 NOT IN (SELECT taux28.DNI_TRABAJADOR_028 FROM AA79B28S01 taux28) ");
			} else {
				query.append(SqlUtils.generarWhereIgual("gc1.ID_GRUPO_028", bean.getIdGrupo(), params));
			}
		}
		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			query.append(SqlUtils.generarWhereIn("p2.DNI_077", bean.getFiltroDatos(), params));
		}
		if (null != bean.getTarea()) {
			query.append(SqlUtils.generarWhereIgual("c7.ID_TIPO_TAREA_0C7", bean.getTarea().getTipoTarea().getId015(),
					params));
			if (bean.getTarea().getEstadoAsignado() != Constants.CERO) {
				query.append(SqlUtils.generarWhereIgual("c7.ESTADO_ASIGNACION_0C7",
						bean.getTarea().getEstadoAsignado(), params));
			}
			if (bean.getTarea().getEstadoEjecucion() != Constants.CERO) {
				query.append(" AND " + bean.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN c7.ESTADO_EJECUCION_0C7 = '1' AND c7.FECHA_INICIO_0C7 < SYSDATE THEN 2 ELSE c7.ESTADO_EJECUCION_0C7 END ");
			}
			if (null != bean.getTarea().getFechaIni()) {
				query.append(SqlUtils.generarWhereMayorIgualFecha("c7.FECHA_INICIO_0C7", DDMMYY,
						bean.getTarea().getFechaIni(), params));
			}
			if (null != bean.getTarea().getFechaFin()) {
				query.append(SqlUtils.generarWhereMenorIgualFecha("c7.FECHA_FIN_0C7", DDMMYY,
						bean.getTarea().getFechaFin(), params));
			}
		}

		query.append("ORDER BY ANYO,NUMEXP,FECHAINI,");
		query.append(" IDTRABAJO, FECHAINITRABAJO ");
		return this.getJdbcTemplate().query(query.toString(), this.rwMapTareasExpediente, params.toArray());
	}

	@Override()
	public List<ExpTareasResumen> filtroTareasDependiente(ExpTareasResumen bean, JQGridRequestDto jqGridRequestDto,
			boolean startsWith) {

		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("" + DBConstants.SELECT + " ");
		query.append("t1.ID_TAREA_081 IDTAREA ");
		query.append(", t1.ORDEN_081 ORDEN ");
		query.append(", t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION ");
		query.append(", " + A1DESC_ES_015TIPOTAREAES + " ");
		query.append(", " + A1DESC_EU_015 + " " + DBConstants.TIPOTAREAEU + " ");
		query.append(", p1.NOMBRE_077 NOMBRERECURSO ");
		query.append(", p1.APEL1_077 APEL1RECURSO ");
		query.append(", p1.APEL2_077 APEL2RECURSO ");
		query.append(", t1.ID_LOTE_081 LOTEID ");
		query.append(", l1.NOMBRE_LOTE_029 NOMBRELOTE ");
		query.append(", e1.TITULO_051 TITULO ");
		query.append(", " + T1FECHA_FIN_081 + " " + DBConstants.FECHAFIN + " ");
		query.append(", " + TOCHAR_T1FECHAFIN_081 + " " + DBConstants.HORAFIN + " ");
		query.append("," + DECODE_T1ESTADOEJECUCION_081 + ", '")
				.append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale));
		query.append("', '").append(EstadoEjecucionTareaEnum.RETRASADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale));
		query.append("', '").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale))
				.append("') AS ESTADOEJECUCIONDESC ");
		query.append("" + FROM_81_T1 + " ");
		query.append(LEFTJOIN15ON81);
		query.append("LEFT JOIN AA79B77S01 p1 ON t1.DNI_RECURSO_081 = p1.DNI_077 ");
		query.append("JOIN AA79B51S01 e1 ON t1.ANYO_081 = e1.ANYO_051 AND t1.NUM_EXP_081 = e1.NUM_EXP_051 ");
		query.append("LEFT JOIN AA79B29S01 l1 ON t1.ID_LOTE_081 = l1.ID_LOTE_029 ");
		query.append(DaoConstants.WHERE);
		query.append("t1.ANYO_081 = ?");
		query.append(DaoConstants.AND);
		query.append("t1.NUM_EXP_081 = ?");
		query.append(DaoConstants.AND);
		query.append("t1.ORDEN_081 <  ");
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT + "t2.ORDEN_081");
		query.append(DaoConstants.FROM + DBConstants.TABLA_081 + DaoConstants.BLANK + "t2");
		query.append(DaoConstants.WHERE + "t2" + DaoConstants.SIGNO_PUNTO + DBConstants.ID_TAREA_081
				+ DaoConstants.BLANK + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		params.add(bean.getTarea().getAnyo());
		params.add(bean.getTarea().getNumExp());
		params.add(bean.getTarea().getIdTarea());

		query.append(DaoConstants.ORDER_BY + DBConstants.ORDEN + DaoConstants.SIGNO_COMA_SIN_ESPACIOS
				+ DBConstants.IDTAREA + DaoConstants.BLANK + DaoConstants.ASC);

		return this.getJdbcTemplate().query(query.toString(), this.rwMapDependeTarea, params.toArray());
	}

	@Override()
	public Long filtroTareasDependienteCount(ExpTareasResumen bean, boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("" + DBConstants.SELECT + " COUNT(1)");
		query.append("" + FROM_81_T1 + " ");
		query.append(LEFTJOIN15ON81);
		query.append("LEFT JOIN AA79B77S01 p1 ON t1.DNI_RECURSO_081 = p1.DNI_077 ");
		query.append("WHERE t1.ANYO_081 = ? AND t1.NUM_EXP_081 = ? AND t1.ORDEN_081 = ? ");
		params.add(bean.getTarea().getAnyo());
		params.add(bean.getTarea().getNumExp());
		params.add(bean.getTarea().getOrden() - Constants.UNO);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public ExpTareasResumen updateEstadoAceptacion(ExpTareasResumen expTareasResumen) {
		String query = "UPDATE AA79B81S01 SET ESTADO_ASIGNACION_081 = ?, FECHA_ACEPTACION_081 = SYSDATE WHERE ANYO_081 = ? AND NUM_EXP_081 = ? AND ID_TAREA_081 = ?";
		this.getJdbcTemplate().update(query, expTareasResumen.getTarea().getEstadoAsignado(),
				expTareasResumen.getAnyo(), expTareasResumen.getNumExp(), expTareasResumen.getTarea().getIdTarea());
		return expTareasResumen;
	}

	@Override
	public Integer insertObsrvRechazo(BigDecimal idTarea, String motivoRechazo) {
		StringBuilder insert = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		insert.append("INSERT INTO AA79B84S01 ");
		insert.append("VALUES (?, ?)");
		params.add(idTarea);
		params.add(motivoRechazo);
		return this.getJdbcTemplate().update(insert.toString(), params.toArray());
	}

	@Override()
	public Integer procComprobarEstadoTareasDependientes(final BigDecimal idTarea, final Long anyo,
			final Integer numExp) {

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{?= call ESTADO_TAREAS_DEPENDIENTES (?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setBigDecimal(2, idTarea);
				callableStatement.setLong(3, anyo);
				callableStatement.setInt(4, numExp);
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

	@Override
	protected String getFrom() {
		return null;
	}

	public void getQueryDatosExpIcs(StringBuilder query, String tareasSeleccionados) {
		List<Tareas> listaTareas = new ArrayList<Tareas>();
		String[] aIds = tareasSeleccionados.split(",");
		Tareas tarea = new Tareas();
		for (String idTarea : aIds) {
			String[] id = idTarea.split("-");
			tarea = new Tareas();
			tarea.setIdTarea(new BigDecimal(id[0]));
			listaTareas.add(tarea);
		}
		int count = 0;
		query.append(DaoConstants.SELECT + DaoConstants.DISTINCT);
		// Tareas
		query.append("t1.ANYO_081 " + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		query.append("t1.NUM_EXP_081 " + DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		query.append("t1.ID_TAREA_081 " + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
		query.append("t1.ID_TIPO_TAREA_081 " + DBConstants.IDTIPOTAREA + DaoConstants.SIGNO_COMA);
		query.append("a1.DESC_ES_015 " + DBConstants.TIPOTAREADESCES + DaoConstants.SIGNO_COMA);
		query.append("" + A1DESC_EU_015 + " " + DBConstants.TIPOTAREADESCEU + DaoConstants.SIGNO_COMA);
		query.append("" + T1FECHA_INI_081 + " " + DBConstants.FECHAINI + DaoConstants.SIGNO_COMA);
		query.append("" + TOCHAR_T1FECHAINI_081 + " " + DBConstants.HORAINI + DaoConstants.SIGNO_COMA);
		query.append("" + T1FECHA_FIN_081 + " " + DBConstants.FECHAFIN + DaoConstants.SIGNO_COMA);
		query.append("" + TOCHAR_T1FECHAFIN_081 + " " + DBConstants.HORAFIN);
		query.append(" FROM AA79B81S01 t1");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_15 + " a1 ");
		query.append(DaoConstants.ON + " t1.ID_TIPO_TAREA_081 " + DaoConstants.SIGNOIGUAL + " a1.ID_015 ");
		query.append(DaoConstants.WHERE_1_1);
		// where
		for (Tareas tareas : listaTareas) {
			query.append((0 == count ? DaoConstants.AND : DaoConstants.OR) + DaoConstants.ABRIR_PARENTESIS
					+ " t1.ID_TAREA_081 " + DaoConstants.SIGNOIGUAL + tareas.getIdTarea()
					+ DaoConstants.CERRAR_PARENTESIS);
			count++;
		}
	}

	@Override
	public Long comprobarConflictoFechas(String[] listaIdTareas) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_081 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_081 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA
				+ DaoConstants.ON);
		query.append(SqlUtils.generarWhereInWithOutAnd(
				DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + "ID_TAREA_081", listaIdTareas, params));
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "ID_TAREA_081"
				+ DaoConstants.SIGNO_DISTINTO + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + "ID_TAREA_081");
		query.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "FECHA_INICIO_081");
		query.append(DaoConstants.SIGNO_MAYOR_IGUAL_QUE + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ "FECHA_INICIO_081");
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "FECHA_INICIO_081"
				+ DaoConstants.SIGNO_MENOR_IGUAL_QUE + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ "FECHA_FIN_081");
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.OR + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "FECHA_FIN_081"
				+ DaoConstants.SIGNO_MAYOR_IGUAL_QUE + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ "FECHA_INICIO_081");
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "FECHA_FIN_081"
				+ DaoConstants.SIGNO_MENOR_IGUAL_QUE + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ "FECHA_FIN_081");
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.WHERE);
		query.append(DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "ID_TIPO_TAREA_081"
				+ DaoConstants.SIGNOIGUAL + TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue());
		query.append(SqlUtils.generarWhereIn(DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "ID_TAREA_081",
				listaIdTareas, params));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	private void setVariablesEstadoRecurso(StringBuilder from) {
		from.append(
				"AND t1.ESTADO_EJECUCION_081 <> " + EstadoEjecucionTareaEnum.EJECUTADA.getValue() + DaoConstants.BLANK);
		from.append("AND t1.ESTADO_ASIGNACION_081 <> " + EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()
				+ DaoConstants.BLANK);
		from.append(" AND t1.RECURSO_ASIGNACION_081 = '");
		from.append(TipoRecursoEnum.INTERNO.getValue());
		from.append("'");
	}

	@Override
	protected String getWhere(ExpTareasResumen bean, List<Object> params) {
		return null;
	}

	@Override
	public List<ExpTareasResumen> filtroExpTareasGetSelectedIds(ExpTareasResumen expTareasResumenFilter,
			JQGridRequestDto tableData) {
		StringBuilder queryFETGSI = new StringBuilder(CargaTrabajoDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsFETGSI = new ArrayList<Object>();
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ID_TAREA_081");
		// SELECT
		queryFETGSI.append("SELECT t1.ID_TAREA_081 IDTAREA ");
		// FROM
		queryFETGSI.append(this.getFrom(expTareasResumenFilter, paramsFETGSI));
		// WHERE
		queryFETGSI.append(this.getWhereLikeTareas(expTareasResumenFilter, false, paramsFETGSI, false));
		queryFETGSI.append(DaoConstants.AND + "p1.NOMBRE" + DaoConstants.IS_NOT_NULL);

		queryFETGSI
				.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
						tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryFETGSI.toString(), this.rwMapExpTareasPK, paramsFETGSI.toArray());
	}

	@Override
	public List<ExpTareasResumen> filtroExpTareasTrabajo(ExpTareasResumen bean,
			JQGridRequestDto jqGridRequestDto, boolean startsWith, boolean orderNombre) {
		StringBuilder paginatedQuery = new StringBuilder();
		StringBuilder queryTT = new StringBuilder();

		List<Object> params = new ArrayList<Object>();
		Locale locale = LocaleContextHolder.getLocale();
		queryTT.append(" SELECT tc6.ID_TRABAJO_0C6 IDTRABAJO, ");
		queryTT.append(" tc6.DESC_TRABAJO_0C6 DESCTRABAJO, ");
		queryTT.append(" tc6.FECHA_INICIO_0C6 FECHAINICIO, ");
		queryTT.append(" tc6.FECHA_FIN_PREVISTA_0C6 FECHAFINPREVISTA, ");
		queryTT.append(" tc6.FECHA_FIN_0C6 FECHAFIN, ");
		queryTT.append(" tc7.ID_TAREA_0C7 IDTAREA, ");
		queryTT.append(" t15.DESC_ES_015 TIPOTAREAES, ");
		queryTT.append(" t15.DESC_EU_015 TIPOTAREAEU, ");
		queryTT.append(" tc7.ID_TIPO_TAREA_0C7 IDTIPOTAREA, ");
		queryTT.append(" tc7.FECHA_INICIO_0C7 FECHAINICIOTAREA, ");
		queryTT.append(" TO_CHAR(tc7.FECHA_INICIO_0C7,'HH24:MI') HORAINICIOTAREA,");
		queryTT.append(" tc7.FECHA_FIN_0C7 FECHAFINTAREA, ");
		queryTT.append(" TO_CHAR(tc7.FECHA_FIN_0C7,'HH24:MI') HORAFINTAREA,");
		queryTT.append(" p1.DNI DNIRECURSO, ");
		queryTT.append(" p1.SUFDNI SUFDNIRECURSO, ");
		queryTT.append(" p1.NOMBRE NOMBRERECURSO, ");
		queryTT.append(" p1.APEL1 APEL1RECURSO, ");
		queryTT.append(" p1.APEL2 APEL2RECURSO, ");
		queryTT.append(" ES_RECURSO_DISPONIBLE(p1.DNI, tc7.ID_TAREA_0C7, NULL) AS ESRECURSODISPONIBLE, ");
		queryTT.append(" tc7.ESTADO_ASIGNACION_0C7 ESTADOASIGNACION, ");
		queryTT.append(" tc7.ESTADO_EJECUCION_0C7 ESTADOEJECUCION, ");
		queryTT.append(" DECODE(tc7.ESTADO_ASIGNACION_0C7, '")
				.append(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoAceptacionTareaEnum.ACEPTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.ACEPTADA.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.RECHAZADA.getLabel(), null, locale))
				.append("') AS ESTADOASIGNACIONDESC");
		queryTT.append(", CASE WHEN tc7.ESTADO_EJECUCION_0C7 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and tc7.FECHA_FIN_0C7 < SYSDATE then " + EstadoEjecucionTareaEnum.RETRASADA.getValue()
				+ " else tc7.ESTADO_EJECUCION_0C7 END AS ESTADOEJECID ");
		queryTT.append(", CASE WHEN tc7.ESTADO_EJECUCION_0C7 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and tc7.FECHA_FIN_0C7 < SYSDATE then '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "' ELSE ");
		queryTT.append("decode(tc7.ESTADO_EJECUCION_0C7, '").append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue())
				.append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale))
				.append("') END AS ESTADOEJECUCIONDESC, ");
		queryTT.append(" tc7.OBSERV_0C7 OBSERVACIONES, ");
		queryTT.append(" tc7.ORDEN_0C7 ORDEN, ");
		queryTT.append(" tc7.DNI_ASIGNADOR_0C7 DNIASIGNADOR, ");
		queryTT.append(" p2.SUFDNI_077 SUFDNIASIGNADOR, ");
		queryTT.append(" p2.NOMBRE_077 NOMBREASIGNADOR, ");
		queryTT.append(" p2.APEL1_077 APEL1ASIGNADOR, ");
		queryTT.append(" p2.APEL2_077 APEL2ASIGNADOR, ");
		queryTT.append(" tc7.FECHA_ASIGNACION_0C7 FECHAASIGNACION, ");
		queryTT.append(" TO_CHAR(tc7.FECHA_ASIGNACION_0C7,'HH24:MI') HORAASIGNACION, ");
		queryTT.append(" tc7.FECHA_ACEPTACION_0C7 FECHAACEPTACION, ");
		queryTT.append(" tc7.FECHA_EJECUCION_0C7 FECHAEJECUCION, ");
		queryTT.append(" tc7.HORAS_TAREA_0C7 HORASTAREA, ");
		queryTT.append(" tc7.IND_REALIZADA_0C7 INDREALIZADA, ");
		queryTT.append(" tc7.IND_OBSV_EJEC_0C7 INDOBSEJEC, ");
		queryTT.append(" decode(tc7.ID_TAREA_0C7, NULL, 1, 0) AS tienetarea ");
		queryTT.append(" FROM x54japi_personal_izo p1 ");
		if (null != bean.getTarea() && Constants.SI.equals(bean.getTarea().getRecursoAsignacion())) {
			queryTT.append(" LEFT ");
		}
		queryTT.append(" JOIN ( SELECT ");
		queryTT.append(" t1.ID_TAREA_0C7, t1.ID_TRABAJO_0C7, t1.ID_TIPO_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_INICIO_0C7, t1.FECHA_FIN_0C7, t1.DNI_RECURSO_0C7,  ");
		queryTT.append(" t1.ESTADO_ASIGNACION_0C7, t1.ESTADO_EJECUCION_0C7, t1.DNI_ASIGNADOR_0C7,");
		queryTT.append(" t1.IND_OBSV_EJEC_0C7, t1.IND_REALIZADA_0C7, t1.HORAS_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_EJECUCION_0C7, t1.FECHA_ACEPTACION_0C7, t1.FECHA_ASIGNACION_0C7, ");
		queryTT.append(" t1.ORDEN_0C7, t1.OBSERV_0C7 FROM AA79BC7S01 t1");
		queryTT.append(" JOIN AA79BC6S01 t2 ON t2.ID_TRABAJO_0C6 = t1.ID_TRABAJO_0C7 WHERE ");
		queryTT.append(" t1.ESTADO_EJECUCION_0C7 <> 3 ");
		queryTT.append(" AND t1.ESTADO_ASIGNACION_0C7 <> 1 ");
		queryTT.append(" GROUP BY ");
		queryTT.append(" t1.ID_TAREA_0C7, t1.ID_TRABAJO_0C7, t1.ID_TIPO_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_INICIO_0C7, t1.FECHA_FIN_0C7, t1.DNI_RECURSO_0C7,  ");
		queryTT.append(" t1.ESTADO_ASIGNACION_0C7, t1.ESTADO_EJECUCION_0C7, t1.DNI_ASIGNADOR_0C7,");
		queryTT.append(" t1.IND_OBSV_EJEC_0C7, t1.IND_REALIZADA_0C7, t1.HORAS_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_EJECUCION_0C7, t1.FECHA_ACEPTACION_0C7, t1.FECHA_ASIGNACION_0C7, ");
		queryTT.append(" t1.ORDEN_0C7, t1.OBSERV_0C7) tc7 ON p1.dni = tc7.DNI_RECURSO_0C7 ");
		queryTT.append(" LEFT JOIN AA79BC6S01 tc6 ON tc6.ID_TRABAJO_0C6 = tc7.ID_TRABAJO_0C7 ");
		queryTT.append(" LEFT JOIN aa79b15s01 t15 ON tc7.ID_TIPO_TAREA_0C7 = t15.id_015 ");
		queryTT.append(" LEFT JOIN aa79b77s01 p2 ON tc7.DNI_ASIGNADOR_0C7 = p2.dni_077 ");
		if (null != bean.getIdGrupo() && bean.getIdGrupo() != 0) {
				queryTT.append(" JOIN aa79b28s01 t28 ON tc7.DNI_RECURSO_0C7 = t28.DNI_TRABAJADOR_028 ");
		}

		queryTT.append(" WHERE 1 = 1 ");
		if (null != bean.getTarea()) {

			queryTT.append(SqlUtils.generarWhereIgual("tc7.ID_TIPO_TAREA_0C7", bean.getTarea().getTipoTarea().getId015(),
					params));
			if (bean.getTarea().getEstadoAsignado() != Constants.CERO) {
				queryTT.append(SqlUtils.generarWhereIgual("tc7.ESTADO_ASIGNACION_0C7", bean.getTarea().getEstadoAsignado(),
						params));
			}
			if (bean.getTarea().getEstadoEjecucion() != Constants.CERO) {
				queryTT.append(" AND " + bean.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN tc7.ESTADO_EJECUCION_0C7 = '1' AND tc7.FECHA_FIN_0C7 < SYSDATE THEN 2 ELSE tc7.ESTADO_EJECUCION_0C7 END ");
			}
			if (null != bean.getTarea().getFechaIni()) {
				queryTT.append(SqlUtils.generarWhereMayorIgualFecha("tc7.FECHA_INICIO_0C7", DDMMYY,
						bean.getTarea().getFechaIni(), params));
			}
			if (null != bean.getTarea().getFechaFin()) {
				queryTT.append(SqlUtils.generarWhereMenorIgualFecha("tc7.FECHA_FIN_0C7", DDMMYY,
						bean.getTarea().getFechaFin(), params));
			}
		}
		if (null != bean.getIdGrupo()) {
			// GruposTrabajo
			if (bean.getIdGrupo() == 0) {
				queryTT.append(" AND tc7.DNI_RECURSO_0C7 NOT IN (SELECT taux28.DNI_TRABAJADOR_028 FROM aa79b28s01 taux28) ");
			} else {
				queryTT.append(SqlUtils.generarWhereIgual("t28.ID_GRUPO_028", bean.getIdGrupo(), params));
			}
		}
		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			queryTT.append(SqlUtils.generarWhereIn("tc7.DNI_RECURSO_0C7", bean.getFiltroDatos(), params));
		}

		if (!orderNombre) {
			return expTareasFiltroOrder(jqGridRequestDto, paginatedQuery, queryTT, params, this.rwMapOtrosTrabajo);
		} else {
			queryTT.append(" ORDER BY TIENETAREA DESC");
			queryTT.append(" , NOMBRERECURSO ASC, " + DBConstants.APEL1RECURSO + ASC + DBConstants.APEL2RECURSO
					+ " ASC, FECHAFIN ASC");
			return this.getJdbcTemplate().query(queryTT.toString(), this.rwMapOtrosTrabajo, params.toArray());
		}
	}

	@Override
	public Long filtroTareasTrabajoCount(ExpTareasResumen bean, boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder queryTTC = new StringBuilder("" + DBConstants.SELECT + " COUNT(1)");
		queryTTC.append(" FROM x54japi_personal_izo p1 ");
		if (null != bean.getTarea() && Constants.SI.equals(bean.getTarea().getRecursoAsignacion())) {
			queryTTC.append(" LEFT ");
		}
		queryTTC.append(" JOIN ( SELECT ");
		queryTTC.append(" t1.ID_TAREA_0C7, t1.ID_TRABAJO_0C7, t1.ID_TIPO_TAREA_0C7, ");
		queryTTC.append(" t1.FECHA_INICIO_0C7, t1.FECHA_FIN_0C7, t1.DNI_RECURSO_0C7,  ");
		queryTTC.append(" t1.ESTADO_ASIGNACION_0C7, t1.ESTADO_EJECUCION_0C7, t1.DNI_ASIGNADOR_0C7,");
		queryTTC.append(" t1.IND_OBSV_EJEC_0C7, t1.IND_REALIZADA_0C7, t1.HORAS_TAREA_0C7, ");
		queryTTC.append(" t1.FECHA_EJECUCION_0C7, t1.FECHA_ACEPTACION_0C7, t1.FECHA_ASIGNACION_0C7, ");
		queryTTC.append(" t1.ORDEN_0C7, t1.OBSERV_0C7 FROM AA79BC7S01 t1");
		queryTTC.append(" JOIN AA79BC6S01 t2 ON t2.ID_TRABAJO_0C6 = t1.ID_TRABAJO_0C7 WHERE ");
		queryTTC.append(" t1.ESTADO_EJECUCION_0C7 <> 3 ");
		queryTTC.append(" AND t1.ESTADO_ASIGNACION_0C7 <> 1 ");
		queryTTC.append(" GROUP BY ");
		queryTTC.append(" t1.ID_TAREA_0C7, t1.ID_TRABAJO_0C7, t1.ID_TIPO_TAREA_0C7, ");
		queryTTC.append(" t1.FECHA_INICIO_0C7, t1.FECHA_FIN_0C7, t1.DNI_RECURSO_0C7,  ");
		queryTTC.append(" t1.ESTADO_ASIGNACION_0C7, t1.ESTADO_EJECUCION_0C7, t1.DNI_ASIGNADOR_0C7,");
		queryTTC.append(" t1.IND_OBSV_EJEC_0C7, t1.IND_REALIZADA_0C7, t1.HORAS_TAREA_0C7, ");
		queryTTC.append(" t1.FECHA_EJECUCION_0C7, t1.FECHA_ACEPTACION_0C7, t1.FECHA_ASIGNACION_0C7, ");
		queryTTC.append(" t1.ORDEN_0C7, t1.OBSERV_0C7) tc7 ON p1.dni = tc7.DNI_RECURSO_0C7 ");
		queryTTC.append(" LEFT JOIN AA79BC6S01 tc6 ON tc6.ID_TRABAJO_0C6 = tc7.ID_TRABAJO_0C7 ");
		queryTTC.append(" LEFT JOIN aa79b15s01 t15 ON tc7.ID_TIPO_TAREA_0C7 = t15.id_015 ");
		queryTTC.append(" LEFT JOIN aa79b77s01 p2 ON tc7.DNI_ASIGNADOR_0C7 = p2.dni_077 ");
		return this.getJdbcTemplate().queryForObject(queryTTC.toString(), params.toArray(), Long.class);
	}

	@Override
	public List<ExpTareasResumen> filtroTareasTrabajoGetSelectedIds(ExpTareasResumen bean,
			JQGridRequestDto tableData) {
		// TODO
		StringBuilder queryTT = new StringBuilder();
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("tc7.ID_TAREA_0C7");
		List<Object> params = new ArrayList<Object>();
		Locale locale = LocaleContextHolder.getLocale();
		queryTT.append(" SELECT tc6.ID_TRABAJO_0C6 IDTRABAJO, ");
		queryTT.append(" tc6.DESC_TRABAJO_0C6 DESCTRABAJO, ");
		queryTT.append(" tc6.FECHA_INICIO_0C6 FECHAINICIO, ");
		queryTT.append(" tc6.FECHA_FIN_PREVISTA_0C6 FECHAFINPREVISTA, ");
		queryTT.append(" tc6.FECHA_FIN_0C6 FECHAFIN, ");
		queryTT.append(" tc7.ID_TAREA_0C7 IDTAREA, ");
		queryTT.append(" t15.DESC_ES_015 TIPOTAREAES, ");
		queryTT.append(" t15.DESC_EU_015 TIPOTAREAEU, ");
		queryTT.append(" tc7.ID_TIPO_TAREA_0C7 IDTIPOTAREA, ");
		queryTT.append(" tc7.FECHA_INICIO_0C7 FECHAINICIOTAREA, ");
		queryTT.append(" TO_CHAR(tc7.FECHA_INICIO_0C7,'HH24:MI') HORAINICIOTAREA,");
		queryTT.append(" tc7.FECHA_FIN_0C7 FECHAFINTAREA, ");
		queryTT.append(" TO_CHAR(tc7.FECHA_FIN_0C7,'HH24:MI') HORAFINTAREA,");
		queryTT.append(" p1.DNI DNIRECURSO, ");
		queryTT.append(" p1.SUFDNI SUFDNIRECURSO, ");
		queryTT.append(" p1.NOMBRE NOMBRERECURSO, ");
		queryTT.append(" p1.APEL1 APEL1RECURSO, ");
		queryTT.append(" p1.APEL2 APEL2RECURSO, ");
		queryTT.append(" ES_RECURSO_DISPONIBLE(p1.DNI, tc7.ID_TAREA_0C7, NULL) AS ESRECURSODISPONIBLE, ");
		queryTT.append(" tc7.ESTADO_ASIGNACION_0C7 ESTADOASIGNACION, ");
		queryTT.append(" tc7.ESTADO_EJECUCION_0C7 ESTADOEJECUCION, ");
		queryTT.append(" DECODE(tc7.ESTADO_ASIGNACION_0C7, '")
				.append(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoAceptacionTareaEnum.ACEPTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.ACEPTADA.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.RECHAZADA.getLabel(), null, locale))
				.append("') AS ESTADOASIGNACIONDESC");
		queryTT.append(", CASE WHEN tc7.ESTADO_EJECUCION_0C7 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and tc7.FECHA_FIN_0C7 < SYSDATE then " + EstadoEjecucionTareaEnum.RETRASADA.getValue()
				+ " else tc7.ESTADO_EJECUCION_0C7 END AS ESTADOEJECID ");
		queryTT.append(", CASE WHEN tc7.ESTADO_EJECUCION_0C7 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' and tc7.FECHA_FIN_0C7 < SYSDATE then '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "' ELSE ");
		queryTT.append("decode(tc7.ESTADO_EJECUCION_0C7, '").append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue())
				.append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale));
		queryTT.append("', '").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale))
				.append("') END AS ESTADOEJECUCIONDESC, ");
		queryTT.append(" tc7.OBSERV_0C7 OBSERVACIONES, ");
		queryTT.append(" tc7.ORDEN_0C7 ORDEN, ");
		queryTT.append(" tc7.DNI_ASIGNADOR_0C7 DNIASIGNADOR, ");
		queryTT.append(" p2.SUFDNI_077 SUFDNIASIGNADOR, ");
		queryTT.append(" p2.NOMBRE_077 NOMBREASIGNADOR, ");
		queryTT.append(" p2.APEL1_077 APEL1ASIGNADOR, ");
		queryTT.append(" p2.APEL2_077 APEL2ASIGNADOR, ");
		queryTT.append(" tc7.FECHA_ASIGNACION_0C7 FECHAASIGNACION, ");
		queryTT.append(" TO_CHAR(tc7.FECHA_ASIGNACION_0C7,'HH24:MI') HORAASIGNACION, ");
		queryTT.append(" tc7.FECHA_ACEPTACION_0C7 FECHAACEPTACION, ");
		queryTT.append(" tc7.FECHA_EJECUCION_0C7 FECHAEJECUCION, ");
		queryTT.append(" tc7.HORAS_TAREA_0C7 HORASTAREA, ");
		queryTT.append(" tc7.IND_REALIZADA_0C7 INDREALIZADA, ");
		queryTT.append(" tc7.IND_OBSV_EJEC_0C7 INDOBSEJEC, ");
		queryTT.append(" decode(tc7.ID_TAREA_0C7, NULL, 1, 0) AS tienetarea ");
		queryTT.append(" FROM x54japi_personal_izo p1 ");
		queryTT.append(" LEFT JOIN ( SELECT ");
		queryTT.append(" t1.ID_TAREA_0C7, t1.ID_TRABAJO_0C7, t1.ID_TIPO_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_INICIO_0C7, t1.FECHA_FIN_0C7, t1.DNI_RECURSO_0C7,  ");
		queryTT.append(" t1.ESTADO_ASIGNACION_0C7, t1.ESTADO_EJECUCION_0C7, t1.DNI_ASIGNADOR_0C7,");
		queryTT.append(" t1.IND_OBSV_EJEC_0C7, t1.IND_REALIZADA_0C7, t1.HORAS_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_EJECUCION_0C7, t1.FECHA_ACEPTACION_0C7, t1.FECHA_ASIGNACION_0C7, ");
		queryTT.append(" t1.ORDEN_0C7, t1.OBSERV_0C7 FROM AA79BC7S01 t1");
		queryTT.append(" JOIN AA79BC6S01 t2 ON t2.ID_TRABAJO_0C6 = t1.ID_TRABAJO_0C7 WHERE ");
		queryTT.append(" t1.ESTADO_EJECUCION_0C7 <> 3 ");
		queryTT.append(" AND t1.ESTADO_ASIGNACION_0C7 <> 1 ");
		queryTT.append(" GROUP BY ");
		queryTT.append(" t1.ID_TAREA_0C7, t1.ID_TRABAJO_0C7, t1.ID_TIPO_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_INICIO_0C7, t1.FECHA_FIN_0C7, t1.DNI_RECURSO_0C7,  ");
		queryTT.append(" t1.ESTADO_ASIGNACION_0C7, t1.ESTADO_EJECUCION_0C7, t1.DNI_ASIGNADOR_0C7,");
		queryTT.append(" t1.IND_OBSV_EJEC_0C7, t1.IND_REALIZADA_0C7, t1.HORAS_TAREA_0C7, ");
		queryTT.append(" t1.FECHA_EJECUCION_0C7, t1.FECHA_ACEPTACION_0C7, t1.FECHA_ASIGNACION_0C7, ");
		queryTT.append(" t1.ORDEN_0C7, t1.OBSERV_0C7) tc7 ON p1.dni = tc7.DNI_RECURSO_0C7 ");
		queryTT.append(" LEFT JOIN AA79BC6S01 tc6 ON tc6.ID_TRABAJO_0C6 = tc7.ID_TRABAJO_0C7 ");
		queryTT.append(" LEFT JOIN aa79b15s01 t15 ON tc7.ID_TIPO_TAREA_0C7 = t15.id_015 ");
		queryTT.append(" LEFT JOIN aa79b77s01 p2 ON tc7.DNI_ASIGNADOR_0C7 = p2.dni_077 ");
		if (null != bean.getIdGrupo() && bean.getIdGrupo() != 0) {
				queryTT.append(" JOIN aa79b28s01 t28 ON tc7.DNI_RECURSO_0C7 = t28.DNI_TRABAJADOR_028 ");
		}

		queryTT.append(" WHERE 1 = 1 ");
		if (null != bean.getTarea()) {

			queryTT.append(SqlUtils.generarWhereIgual("tc7.ID_TIPO_TAREA_0C7", bean.getTarea().getTipoTarea().getId015(),
					params));
			if (bean.getTarea().getEstadoAsignado() != Constants.CERO) {
				queryTT.append(SqlUtils.generarWhereIgual("tc7.ESTADO_ASIGNACION_0C7", bean.getTarea().getEstadoAsignado(),
						params));
			}
			if (bean.getTarea().getEstadoEjecucion() != Constants.CERO) {
				queryTT.append(" AND " + bean.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN tc7.ESTADO_EJECUCION_0C7 = '1' AND tc7.FECHA_FIN_0C7 < SYSDATE THEN 2 ELSE tc7.ESTADO_EJECUCION_0C7 END ");
			}
			if (null != bean.getTarea().getFechaIni()) {
				queryTT.append(SqlUtils.generarWhereMayorIgualFecha("tc7.FECHA_INICIO_0C7", DDMMYY,
						bean.getTarea().getFechaIni(), params));
			}
			if (null != bean.getTarea().getFechaFin()) {
				queryTT.append(SqlUtils.generarWhereMenorIgualFecha("tc7.FECHA_FIN_0C7", DDMMYY,
						bean.getTarea().getFechaFin(), params));
			}
		}
		if (null != bean.getIdGrupo()) {
			// GruposTrabajo
			if (bean.getIdGrupo() == 0) {
				queryTT.append(" AND tc7.DNI_RECURSO_0C7 NOT IN (SELECT taux28.DNI_TRABAJADOR_028 FROM aa79b28s01 taux28) ");
			} else {
				queryTT.append(SqlUtils.generarWhereIgual("t28.ID_GRUPO_028", bean.getIdGrupo(), params));
			}
		}
		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			queryTT.append(SqlUtils.generarWhereIn("tc7.DNI_RECURSO_0C7", bean.getFiltroDatos(), params));
		}

		queryTT.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
						tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryTT.toString(), this.rwMapExpTareasPK, params.toArray());
	}

	@Override
	public Integer updateEstadoAceptacionTareaTrabajo(TareasTrabajo tareas) {
		StringBuilder query = new StringBuilder(CargaTrabajoDaoImpl.STRING_BUILDER_INIT);
		query.append(" UPDATE AA79BC7S01 SET ESTADO_ASIGNACION_0C7 = ?, FECHA_ACEPTACION_0C7 = SYSDATE WHERE ID_TAREA_0C7 = ? AND ID_TRABAJO_0C7 = ?");
		return this.getJdbcTemplate().update(query.toString(), tareas.getEstadoAsignado(),
				tareas.getIdTarea(), tareas.getTrabajo().getIdTrabajo());
	}

	@Override
	public Integer insertObsrvRechazoTareaTrabajo(BigDecimal idTarea, String motivoRechazo) {
		// lanzamos un borrado previo por si ha sido rechazada antes
		StringBuilder prevDelete = new StringBuilder();
		List<Object> paramsDelete = new ArrayList<Object>();
		prevDelete.append(" DELETE FROM AA79BC9S01 ");
		prevDelete.append(" WHERE ID_TAREA_0C9 = ? ");
		paramsDelete.add(idTarea);
		this.getJdbcTemplate().update(prevDelete.toString(), paramsDelete.toArray());

		StringBuilder insert = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		insert.append("INSERT INTO AA79BC9S01 ");
		insert.append("VALUES (?, ?)");
		params.add(idTarea);
		params.add(motivoRechazo);
		return this.getJdbcTemplate().update(insert.toString(), params.toArray());
	}

	@Override
	public List<TareasTrabajo> obtenerDatosTareasTrabajo(String tareasSeleccionados) {
		StringBuilder query = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		List<TareasTrabajo> listaTareas = new ArrayList<TareasTrabajo>();
		String[] aIds = tareasSeleccionados.split(",");
		TareasTrabajo tarea = new TareasTrabajo();
		for (String idTarea : aIds) {
			String[] id = idTarea.split("-");
			tarea = new TareasTrabajo();
			tarea.setIdTarea(new BigDecimal(id[0]));
			listaTareas.add(tarea);
		}
		int count = 0;
		query.append(DaoConstants.SELECT + DaoConstants.DISTINCT);
		// Tareas
		query.append("t1.ID_TAREA_0C7 " + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
		query.append("t1.ID_TIPO_TAREA_0C7 " + DBConstants.IDTIPOTAREA + DaoConstants.SIGNO_COMA);
		query.append("t1.ID_TRABAJO_0C7 IDTRABAJO" + DaoConstants.SIGNO_COMA);
		query.append("a1.DESC_ES_015 " + DBConstants.TIPOTAREADESCES + DaoConstants.SIGNO_COMA);
		query.append("" + A1DESC_EU_015 + " " + DBConstants.TIPOTAREADESCEU + DaoConstants.SIGNO_COMA);
		query.append("t1.FECHA_INICIO_0C7" + " " + DBConstants.FECHAINI + DaoConstants.SIGNO_COMA);
		query.append("TO_CHAR(t1.FECHA_INICIO_0C7,'HH24:MI')" + " " + DBConstants.HORAINI + DaoConstants.SIGNO_COMA);
		query.append("t1.FECHA_FIN_0C7" + " " + DBConstants.FECHAFIN + DaoConstants.SIGNO_COMA);
		query.append("TO_CHAR(t1.FECHA_FIN_0C7,'HH24:MI')" + " " + DBConstants.HORAFIN);
		query.append(" FROM AA79BC7S01 t1");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_15 + " a1 ");
		query.append(DaoConstants.ON + " t1.ID_TIPO_TAREA_0C7 " + DaoConstants.SIGNOIGUAL + " a1.ID_015 ");
		query.append(DaoConstants.WHERE_1_1);
		// where
		for (TareasTrabajo tareas : listaTareas) {
			query.append((0 == count ? DaoConstants.AND : DaoConstants.OR) + DaoConstants.ABRIR_PARENTESIS
					+ " t1.ID_TAREA_0C7 " + DaoConstants.SIGNOIGUAL + tareas.getIdTarea()
					+ DaoConstants.CERRAR_PARENTESIS);
			count++;
		}
		return this.getJdbcTemplate().query(query.toString(), this.rwMapIcsTareasTrabajo);
	}

	@Override
	public List<ExpTareasResumen> getExpTareasGantt(ExpTareasResumen expTareasResumen, boolean startsWith) {

		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(" ,NULL IDTRABAJO, NULL DESCTRABAJO");
		List<Object> params = new ArrayList<Object>();
		query.append(this.getFrom(expTareasResumen, params));
		query.append(this.getWhereLikeTareas(expTareasResumen, startsWith, params, false));
		query.append(DaoConstants.AND + "p1.NOMBRE" + DaoConstants.IS_NOT_NULL);

		// union tareas trabajo
		query.append(" UNION SELECT ");
		query.append(" NULL,NULL,NULL, ");
		query.append(" c1.ID_TAREA_0C7 IDTAREA, ");
		query.append(" c1.ID_TIPO_TAREA_0C7 IDTIPOTAREA, ");
		query.append(" c15.DESC_ES_015 TIPOTAREAES, ");
		query.append(" c15.DESC_EU_015 TIPOTAREAEU, ");
		query.append(" NULL, ");
		query.append(" c1.FECHA_INICIO_0C7 FECHAINI, ");
		query.append(" TO_CHAR(c1.FECHA_INICIO_0C7, 'HH24:MI') HORAINI, ");
		query.append(" c1.FECHA_FIN_0C7 FECHAFIN, ");
		query.append(" TO_CHAR(c1.FECHA_FIN_0C7, 'HH24:MI') HORAFIN, ");
		query.append(" NULL,NULL, ");
		query.append(" p2.DNI DNIRECURSO, ");
		query.append(" p2.SUFDNI SUFDNIRECURSO, ");
		query.append(" p2.NOMBRE NOMBRERECURSO, ");
		query.append(" p2.APEL1 APEL1RECURSO, ");
		query.append(" p2.APEL2 APEL2RECURSO, ");
		query.append(" c1.DNI_ASIGNADOR_0C7 DNIASIGNADOR, ");
		query.append(" c77.SUFDNI_077 SUFDNIASIGNADOR, ");
		query.append(" c77.NOMBRE_077 NOMBREASIGNADOR, ");
		query.append(" c77.APEL1_077 APEL1ASIGNADOR, ");
		query.append(" c77.APEL2_077 APEL2ASIGNADOR, ");
		query.append(" c1.ESTADO_ASIGNACION_0C7 ESTADOASIGID ");
		query.append(",DECODE(c1.ESTADO_ASIGNACION_0C7, '")
				.append(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel(), null, locale));
		query.append("', '").append(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel(), null, locale));
		query.append("', '").append(EstadoAceptacionTareaEnum.ACEPTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.ACEPTADA.getLabel(), null, locale));
		query.append("', '").append(EstadoAceptacionTareaEnum.RECHAZADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoAceptacionTareaEnum.RECHAZADA.getLabel(), null, locale))
				.append("') AS ESTADOASIGNACIONDESC");
		query.append(", CASE WHEN c1.ESTADO_EJECUCION_0C7 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' AND c1.FECHA_FIN_0C7 < SYSDATE THEN " + EstadoEjecucionTareaEnum.RETRASADA.getValue()
				+ " ELSE c1.ESTADO_EJECUCION_0C7 END AS ESTADOEJECID ");
		query.append(", CASE WHEN c1.ESTADO_EJECUCION_0C7 = '" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "' AND c1.FECHA_FIN_0C7 < SYSDATE THEN '"
				+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, locale) + "' ELSE ");
		query.append("DECODE(c1.ESTADO_EJECUCION_0C7, '").append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue())
				.append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, locale));
		query.append("', '").append(EstadoEjecucionTareaEnum.EJECUTADA.getValue()).append("', '")
				.append(this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, locale))
				.append("') END AS ESTADOEJECUCIONDESC,");
		query.append(" c1.OBSERV_0C7 OBSERV, ");
		query.append(" c1.ORDEN_0C7 ORDEN, ");
		query.append(" c1.FECHA_ASIGNACION_0C7 FECHAASIGNACION, ");
		query.append(" TO_CHAR(c1.FECHA_ASIGNACION_0C7, 'HH24:MI') HORAASIGNACION, ");
		query.append(" c1.FECHA_ACEPTACION_0C7 FECHAACEPTACION, ");
		query.append(" null,null,null,null,null,null, ");
		query.append(" es_recurso_disponible(c1.DNI_RECURSO_0C7, c1.ID_TAREA_0C7, NULL)                     AS ESRECURSODISPONIBLE, ");
		query.append(" NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL, ");
		query.append(" c1.ID_TRABAJO_0C7 IDTRABAJO, c6.DESC_TRABAJO_0C6 DESCTRABAJO ");
		query.append(" FROM X54JAPI_PERSONAL_IZO  p2 ");
		if (null != expTareasResumen.getTarea() && Constants.SI.equals(expTareasResumen.getTarea().getRecursoAsignacion())) {
			query.append(" LEFT ");
		}
		query.append(" JOIN ( SELECT tC7.ID_TAREA_0C7,");
		query.append(" tC7.ID_TIPO_TAREA_0C7, ");
		query.append(" tC7.ID_TRABAJO_0C7, ");
		query.append(" TC7.FECHA_INICIO_0C7, ");
		query.append(" TC7.FECHA_FIN_0C7, ");
		query.append(" TC7.DNI_RECURSO_0C7, ");
		query.append(" TC7.ESTADO_ASIGNACION_0C7, ");
		query.append(" TC7.ESTADO_EJECUCION_0C7, ");
		query.append(" TC7.OBSERV_0C7, ");
		query.append(" TC7.ORDEN_0C7, ");
		query.append(" TC7.DNI_ASIGNADOR_0C7, ");
		query.append(" TC7.FECHA_ASIGNACION_0C7, ");
		query.append(" TC7.FECHA_ACEPTACION_0C7, ");
		query.append(" TC7.FECHA_EJECUCION_0C7, ");
		query.append(" TC7.HORAS_TAREA_0C7, ");
		query.append(" TC7.IND_REALIZADA_0C7, ");
		query.append(" TC7.IND_OBSV_EJEC_0C7  ");
		query.append(" FROM aa79bC7s01  tC7 ");
		query.append(" WHERE tC7.ESTADO_EJECUCION_0C7 <> 3");
		query.append(" AND tC7.ESTADO_ASIGNACION_0C7 <> 1 ");
		if (null != expTareasResumen.getTarea()) {

			query.append(SqlUtils.generarWhereIgual("tC7.ID_TIPO_TAREA_0C7", expTareasResumen.getTarea().getTipoTarea().getId015(),
					params));
			if (expTareasResumen.getTarea().getEstadoAsignado() != Constants.CERO) {
				query.append(SqlUtils.generarWhereIgual("tC7.ESTADO_ASIGNACION_0C7", expTareasResumen.getTarea().getEstadoAsignado(),
						params));
			}
			if (expTareasResumen.getTarea().getEstadoEjecucion() != Constants.CERO) {
				query.append(" AND " + expTareasResumen.getTarea().getEstadoEjecucion()
						+ " = CASE WHEN tC7.ESTADO_EJECUCION_0C7 = '1' AND tC7.FECHA_FIN_0C7 < SYSDATE THEN 2 ELSE tC7.ESTADO_EJECUCION_0C7 END ");
			}
			if (null != expTareasResumen.getTarea().getFechaIni()) {
				query.append(SqlUtils.generarWhereMayorIgualFecha("tC7.FECHA_INICIO_0C7", DDMMYY,
						expTareasResumen.getTarea().getFechaIni(), params));
			}
			if (null != expTareasResumen.getTarea().getFechaFin()) {
				query.append(SqlUtils.generarWhereMenorIgualFecha("tC7.FECHA_FIN_0C7", DDMMYY,
						expTareasResumen.getTarea().getFechaFin(), params));
			}
		}
		query.append(" GROUP BY tC7.ID_TAREA_0C7,");
		query.append(" tC7.ID_TIPO_TAREA_0C7, ");
		query.append(" tC7.ID_TRABAJO_0C7, ");
		query.append(" TC7.FECHA_INICIO_0C7, TC7.FECHA_FIN_0C7,");
		query.append(" TC7.DNI_RECURSO_0C7, TC7.ESTADO_ASIGNACION_0C7,");
		query.append(" TC7.ESTADO_EJECUCION_0C7, TC7.OBSERV_0C7,");
		query.append(" TC7.ORDEN_0C7, TC7.DNI_ASIGNADOR_0C7,");
		query.append(" TC7.FECHA_ASIGNACION_0C7, TC7.FECHA_ACEPTACION_0C7,");
		query.append(" TC7.FECHA_EJECUCION_0C7, TC7.HORAS_TAREA_0C7,");
		query.append(" TC7.IND_REALIZADA_0C7, TC7.IND_OBSV_EJEC_0C7 ");
		query.append(" ) C1 ON p2.DNI = C1.DNI_RECURSO_0C7 ");
		query.append(" JOIN AA79BC6S01 C6 ON C1.ID_TRABAJO_0C7 = C6.ID_TRABAJO_0C6 ");
		query.append(" LEFT JOIN AA79B77S01 c77 ON c1.DNI_ASIGNADOR_0C7 = c77.DNI_077 ");
		query.append(" LEFT JOIN AA79B15S01 c15 ON c1.ID_TIPO_TAREA_0C7 = c15.ID_015 ");
		if (null != expTareasResumen.getIdGrupo() && expTareasResumen.getIdGrupo() != 0) {
			query.append(" JOIN aa79b28s01 c28 ON C1.DNI_RECURSO_0C7 = c28.DNI_TRABAJADOR_028 ");
	}
		query.append(this.getWhereLikeTareasTrabajo(expTareasResumen, startsWith, params, true));
		query.append(" AND p2.NOMBRE IS NOT NULL ");

		query.append(" ORDER BY ");
		query.append(" NOMBRERECURSO ASC, " + DBConstants.APEL1RECURSO + ASC + DBConstants.APEL2RECURSO
				+ " ASC, TIENETAREA DESC, FECHAFIN ASC");
		return this.getJdbcTemplate().query(query.toString(), this.rwMapGanttRecurso, params.toArray());
	}

	private Object getWhereLikeTareasTrabajo(ExpTareasResumen expTareasResumen, boolean startsWith, List<Object> params,
			boolean expedienteGantt) {
		StringBuilder strArea = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		strArea.append("WHERE 1 = 1 ");
		if (null != expTareasResumen.getIdGrupo()) {
			// GruposTrabajo
			if (expTareasResumen.getIdGrupo() == 0) {
				strArea.append(" AND p2.dni NOT IN (SELECT taux28.DNI_TRABAJADOR_028 FROM aa79b28s01 taux28) ");
			} else {
				strArea.append(SqlUtils.generarWhereIgual("c28.ID_GRUPO_028", expTareasResumen.getIdGrupo(), params));
			}
		}
		if (null != expTareasResumen.getFiltroDatos() && Constants.CERO < expTareasResumen.getFiltroDatos().length) {
			if (!expedienteGantt) {
				strArea.append(SqlUtils.generarWhereIn("p2.DNI", expTareasResumen.getFiltroDatos(), params));
			} else {
				strArea.append(SqlUtils.generarWhereIn("c77.DNI_077", expTareasResumen.getFiltroDatos(), params));
			}
		}
		return strArea.toString();
	}

}
