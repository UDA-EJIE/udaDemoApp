/**
 *
 */
package com.ejie.aa79b.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.ExpedienteDaoImpl;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.FilterTarea;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoRequerimientoEnum;
import com.ejie.aa79b.model.enums.EstadoSubsanacionEnum;
import com.ejie.aa79b.model.enums.PlanifExpedientesEnum;
import com.ejie.aa79b.model.enums.PlanifTareasEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * @author aresua
 *
 */
public class PlanificacionExpedienteUtils extends SpringBeanAutowiringSupport {

	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;

	public PlanificacionExpedienteUtils() {
		// Constructor vacío
	}

	/* Tablas */
	private static final String AA79B15S01 = " AA79B15S01 ";
	private static final String AA79B26S01 = " AA79B26S01 ";
	private static final String AA79B51S01 = " AA79B51S01 ";
	private static final String AA79B52S01 = " AA79B52S01 ";
	private static final String AA79B53S01 = " AA79B53S01 ";
	private static final String AA79B54S01 = " AA79B54S01 ";
	private static final String AA79B59S01 = " AA79B59S01 ";
	private static final String AA79B64S01 = " AA79B64S01 ";
	private static final String AA79B81S01 = " AA79B81S01 ";

	/* Sinónimos tablas */
	private static final String T1 = " T1 ";
	private static final String T2 = " T2 ";
	private static final String T3 = " T3 ";
	private static final String T4 = " T4 ";
	private static final String T5 = " T5 ";
	private static final String T6 = " T6 ";
	private static final String T15 = " T15 ";

	private static final String ESTADO_EJECUCION_WHERE = " AND ESTADO_EJECUCION_081 ";
	private static final String VIP_JOIN = ", g1.IND_VIP_054 IND_VIP_054";
	private static final String NOMBRE_JOIN = ", h1.NOMBRE_077 NOMBRE_077";
	private static final String APEL1_JOIN = ", h1.APEL1_077 APEL1_077";
	private static final String APEL2_JOIN = ", h1.APEL2_077 APEL2_077";
	private static final String FECHA_MENOR_IGUAL = " AND TO_DATE(TO_CHAR(t4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(sysdate + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ";

	protected static final String NOMBRE_077 = "NOMBRE_077";
	protected static final String APEL1_077 = "APEL1_077";
	protected static final String APEL2_077 = "APEL2_077";
	protected static final String ANYO_EXPEDIENTE = "ANYO_051";
	protected static final String NUM_EXPEDIENTE = "NUM_EXP_051";
	protected static final String ID_TIPO_EXPEDIENTE = "ID_TIPO_EXPEDIENTE_051";
	protected static final String IND_VIP_054 = "IND_VIP_054";

	/* Utilidades */
	public static final String EXPEDIENTES_FILTRO = "expedientes";
	public static final String EXPEDIENTES_SIN_FILTRO = "expedientesSin";
	public static final String TAREAS_FILTRO = "tareas";
	public static final String TAREAS_FILTRO_SIN_CONDICIONES = "tareasSinCondicioness";
	public static final String TRAMITES_FILTRO = "tramites";

	protected static final String R1IND_PUBLICAR_BOPV_053 = "r1.IND_PUBLICAR_BOPV_053";
	protected static final String R1FECHA_FINAL_IZO_053 = "r1.FECHA_FINAL_IZO_053";
	protected static final String I1IND_PRESUPUESTO_052 = "i1.IND_PRESUPUESTO_052";
	protected static final String DDMMYY = "DD/mm/YY";
	protected static final String B1ID_FASE_EXPEDIENTE_059 = "b1.ID_FASE_EXPEDIENTE_059";

	/*
	 * EXPEDIENTES - INICIO
	 */
	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getExpPendienteEntregarFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getExpedientesGeneralJoins(query, params);
		query.append(DaoConstants.WHERE).append(
				" TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd hh24:mi:ss')    <= TO_CHAR(sysdate,'yyyy/MM/dd hh24:mi:ss')  ");
		query.append(
				" OR TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd hh24:mi:ss')  <= TO_CHAR(sysdate,'yyyy/MM/dd hh24:mi:ss') ");
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getExpHoyFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getExpedientesGeneralJoins(query, params);
		query.append(DaoConstants.WHERE)
				.append(" TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd') = TO_CHAR(sysdate,'yyyy/MM/dd') ");
		query.append("OR TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd') = TO_CHAR(sysdate,'yyyy/MM/dd')");
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getExpSieteDiasFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getExpedientesGeneralJoins(query, params);
		query.append(DaoConstants.WHERE);
		query.append(
				" (TO_DATE(TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd')     > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
		query.append(
				" AND TO_DATE(TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd')       <= TO_DATE(TO_CHAR(sysdate+7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
		query.append(
				" OR (TO_DATE(TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd')  > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
		query.append(
				" AND TO_DATE(TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(sysdate + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getExpNoConformidadFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getExpedientesGeneralJoins(query, params);
		query.append(DaoConstants.JOIN).append(AA79B81S01).append(T5);
		query.append(" ON t1.ANYO_051              = t5.ANYO_081 ");
		query.append(" AND t1.NUM_EXP_051          = t5.NUM_EXP_081 ");
		query.append(" AND t5.ID_TIPO_TAREA_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND t5.ESTADO_EJECUCION_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue());
		params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getExpSinPlanificarFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getExpedientesGeneralJoins(query, params);

		query.append(" LEFT JOIN (SELECT  num_exp_051 as num_exp, anyo_051 as anyo");
		query.append(" FROM aa79b51s01");
		query.append(" LEFT JOIN aa79b81t00 t5 ON anyo_051 = t5.anyo_081");
		query.append(" AND   num_exp_051 = t5.num_exp_081");
		query.append(" WHERE num_exp_081 IS NULL) t6 ON t6.num_exp = t1.num_exp_051 AND t6.anyo = t1.anyo_051");
		query.append(" LEFT JOIN (SELECT  num_exp_051 as num_exp, anyo_051 as anyo");
		query.append(" FROM aa79b51s01");
		query.append(" JOIN aa79b81t00 t5 ON anyo_051 = t5.anyo_081");
		query.append(" AND   num_exp_051 = t5.num_exp_081");
		query.append(" AND   (t5.estado_asignacion_081 = ? OR    t5.estado_asignacion_081 = ?)");
		query.append(" GROUP BY num_exp_051, anyo_051");
		query.append(" HAVING COUNT(1) > 0) t7 ON t7.num_exp = t1.num_exp_051 AND t7.anyo = t1.anyo_051");
		query.append(" WHERE (t7.num_exp IS NOT NULL OR t6.num_exp IS NOT NULL)");

		params.add(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());
		params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getExpSinAsignadorFromWhere(String dni, StringBuilder query, List<Object> params) {
		String tabla54 = "";

		query.append(DaoConstants.FROM).append(AA79B51S01).append(T1);
		query.append(DaoConstants.JOIN).append(AA79B59S01).append(T2);
		query.append(" ON t1.ANYO_051 = t2.ANYO_059 ");
		query.append(" AND t1.NUM_EXP_051 = t2.NUM_EXP_059 ");
		query.append(" AND t1.ESTADO_BITACORA_051 = t2.ID_ESTADO_BITACORA_059 ");
		query.append(getIdEstado59(T2));
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + " t15 ");
		query.append(DaoConstants.ON + " t1.ANYO_051 " + DaoConstants.SIGNOIGUAL + " t15.ANYO_052 ");
		query.append(DaoConstants.AND + " t1.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + " t15.NUM_EXP_052 ");
		if (StringUtils.isNotBlank(dni)) {
			tabla54 = T3;
		} else {
			tabla54 = T6;
			query.append(DaoConstants.LEFT_JOIN).append(AA79B53S01).append(T3);
			query.append(" ON t1.ANYO_051 = t3.ANYO_053 ");
			query.append(" AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		}
		query.append(DaoConstants.JOIN).append(AA79B54S01).append(tabla54);
		query.append(" ON t1.ANYO_051 = ").append(tabla54.trim()).append(".ANYO_054 ");
		query.append(" AND t1.NUM_EXP_051 = ").append(tabla54.trim()).append(".NUM_EXP_054 ");

		query.append(DaoConstants.LEFT_JOIN).append(AA79B26S01).append(T5).append(DaoConstants.ON);
		query.append("T5.ID_026" + DaoConstants.SIGNOIGUAL + "OBTENER_GRUPO_TRABAJO( t1.ANYO_051, t1.NUM_EXP_051) ");

		if (StringUtils.isBlank(dni)) {
			PlanificacionExpedienteUtils.getGestorExpedienteJoins(query, T1.trim());
		}
		query.append(DaoConstants.WHERE).append(" DNI_ASIGNADOR_051 IS NULL ");

		params.add(dni);
		params.add(EstadoExpedienteEnum.EN_CURSO.getValue());

	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getSelectCountCharts(String dni, StringBuilder query, List<Object> params, String tabla) {
		query.append(" SELECT COUNT(1) AS TOTAL, ").append(" NVL(SUM(DECODE(").append(tabla)
				.append(".DNI_ASIGNADOR_051, ?,1,0)),0) AS MISEXP ");

		params.add(dni);
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getSelectCountSinAsignadorCharts(String dni, StringBuilder query, List<Object> params) {
		query.append(" SELECT COUNT(DISTINCT t1.NUM_EXP_051) AS TOTAL, ")
				.append(" NVL(SUM(DECODE(t5.DNI_RESPONSABLE_026, ?,1,0)),0) AS MISEXP ");

		params.add(dni);
	}

	public static final void getSelectCountSinAsignadorChartsExped(String dni, StringBuilder query,
			List<Object> params) {
		query.append(" SELECT COUNT(DISTINCT t1.NUM_EXP_051) AS TOTAL, ");
		query.append("  NVL(SUM(DECODE(t5.DNI_RESPONSABLE_026, ?,1,0)),0) AS MISEXP ");
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	private static final void getExpedientesGeneralJoins(StringBuilder query, List<Object> params) {
		query.append(DaoConstants.FROM).append(AA79B51S01).append(T1);
		query.append(DaoConstants.LEFT_JOIN).append(AA79B52S01).append(T2);
		query.append(" ON t1.ANYO_051     = t2.ANYO_052 ");
		query.append(" AND t1.NUM_EXP_051 = t2.NUM_EXP_052 ");
		query.append(DaoConstants.LEFT_JOIN).append(AA79B53S01).append(T3);
		query.append(" ON t1.ANYO_051     = t3.ANYO_053 ");
		query.append(" AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		query.append(DaoConstants.JOIN).append(AA79B59S01).append(T4);
		query.append(" ON t1.ANYO_051             = t4.ANYO_059 ");
		query.append(" AND t1.NUM_EXP_051         = t4.NUM_EXP_059 ");
		query.append(" AND t1.ESTADO_BITACORA_051 = t4.ID_ESTADO_BITACORA_059 ");
		query.append(getIdEstado59(T4));

		params.add(EstadoExpedienteEnum.EN_CURSO.getValue());
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	private static final void getGestorExpedienteJoins(StringBuilder query, String tabla) {
		// GestorExpediente
		query.append(" JOIN AA79B54S01 g1 ON  ").append(tabla).append(".ANYO_051 = g1.ANYO_054 AND ").append(tabla)
				.append(".NUM_EXP_051 = g1.NUM_EXP_054 ");
		// NombreApellidosGestor
		query.append(" JOIN AA79B77S01 h1 ON g1.DNI_SOLICITANTE_054 = h1.DNI_077 ");
		// Entidad
		query.append(
				" JOIN X54JAPI_ENTIDADES_SOLIC n1 ON g1.ID_ENTIDAD_054 = n1.CODIGO AND g1.TIPO_ENTIDAD_054 = n1.TIPO ");
	}

	/**
	 *
	 * @param tabla String
	 * @return String
	 */
	private static final String getIdEstado59(String tabla) {
		StringBuilder str = new StringBuilder();
		str.append(DaoConstants.AND).append(Constants.ESPACIO).append(tabla.trim()).append(".id_estado_exp_059 ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		return str.toString();
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public void getSubSelectNoConformidad(StringBuilder query, List<Object> params) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);

		query.append(DaoConstants.SIGNO_COMA).append(DaoConstants.ABRIR_PARENTESIS).append(DaoConstants.SELECT)
				.append(" DECODE (COUNT(1), 0, ?, ?) ").append(DaoConstants.FROM).append(AA79B81S01).append(" r1 ")
				.append(DaoConstants.WHERE).append(" r1.ANYO_081 = t1.ANYO_051 ")
				.append(" AND r1.NUM_EXP_081 = t1.NUM_EXP_051 ").append(" AND r1.ID_TIPO_TAREA_081 ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION).append(DaoConstants.CERRAR_PARENTESIS)
				.append(" CONFORMIDAD ");

		params.add(this.messageSource.getMessage(ActivoEnum.NO.getLabel(), null, eu));
		params.add(this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu));
		params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue());
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public void getSubSelectTareasSinAsignar(StringBuilder query, List<Object> params) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);

		query.append(DaoConstants.SIGNO_COMA).append(DaoConstants.ABRIR_PARENTESIS).append(DaoConstants.SELECT)
				.append(" DECODE (COUNT(1), 0, ?, ?) ").append(DaoConstants.FROM).append(AA79B81S01).append(" r1 ")
				.append(DaoConstants.WHERE).append(" r1.ANYO_081 = t1.ANYO_051 ")
				.append(" AND r1.NUM_EXP_081 = t1.NUM_EXP_051 ").append(" AND ( r1.RECURSO_ASIGNACION_081 ")
				.append(DaoConstants.IS_NULL).append(" OR ESTADO_ASIGNACION_081 = ? ")
				.append(DaoConstants.CERRAR_PARENTESIS).append(DaoConstants.CERRAR_PARENTESIS)
				.append(" TAREASSINASIGNAR ");

		params.add(this.messageSource.getMessage(ActivoEnum.NO.getLabel(), null, eu));
		params.add(this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu));
		params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
	}

	/**
	 *
	 * @param filtro
	 * @return String
	 */
	private String getSelectExpedientes(String filtro) {
		final StringBuilder query = new StringBuilder();

		Locale es = new Locale(Constants.LANG_CASTELLANO);
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		query.append("SELECT ");
		if (EXPEDIENTES_SIN_FILTRO.equals(filtro)) {
			query.append(" DISTINCT ");
		}
		query.append(" t1.ANYO_051 ANYO_051");
		query.append(", t1.NUM_EXP_051 NUM_EXP_051");
		query.append(", SUBSTR(t1.ANYO_051,3,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO");
		query.append(", t1.ID_TIPO_EXPEDIENTE_051 ID_TIPO_EXPEDIENTE_051");
		query.append(", DECODE(t1.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.messageSource.getMessage("label.interpretacionAbr", null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.messageSource.getMessage("label.traduccionAbr", null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.messageSource.getMessage("label.revisionAbr", null, es))
				.append("') AS TIPO_EXPEDIENTE_ES");
		query.append(", DECODE(t1.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.messageSource.getMessage("label.interpretacionAbr", null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.messageSource.getMessage("label.traduccionAbr", null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.messageSource.getMessage("label.revisionAbr", null, eu))
				.append("') AS TIPO_EXPEDIENTE_EU");
		query.append(", t1.FECHA_ALTA_051 FECHA_ALTA_051");
		query.append(", TO_CHAR(t1.FECHA_ALTA_051,'HH24:MI') HORA_ALTA_051");
		if (EXPEDIENTES_SIN_FILTRO.equals(filtro)) {
			query.append(", NVL(t15.FECHA_FIN_052,NVL(t3.FECHA_FINAL_IZO_053,t3.FECHA_FINAL_SOLIC_053)) FECHA_FINAL");
			query.append(
					", TO_CHAR(NVL(t15.FECHA_FIN_052,NVL(t3.FECHA_FINAL_IZO_053,t3.FECHA_FINAL_SOLIC_053)),'HH24:MI') HORA_FINAL");
		} else {
			query.append(", NVL(t2.FECHA_FIN_052,NVL(t3.FECHA_FINAL_IZO_053,t3.FECHA_FINAL_SOLIC_053)) FECHA_FINAL");
			query.append(
					", TO_CHAR(NVL(t2.FECHA_FIN_052,NVL(t3.FECHA_FINAL_IZO_053,t3.FECHA_FINAL_SOLIC_053)),'HH24:MI') HORA_FINAL");
		}

		query.append(VIP_JOIN);
		query.append(", (h1.PREDNI_077 || g1.DNI_SOLICITANTE_054 || h1.SUFDNI_077) AS DNICOMPLETO");
		query.append(", h1.PREDNI_077 " + DBConstants.PREDNI);
		query.append(", g1.DNI_SOLICITANTE_054 " + DBConstants.DNI);
		query.append(", h1.SUFDNI_077 " + DBConstants.SUFDNI);
		query.append(NOMBRE_JOIN);
		query.append(APEL1_JOIN);
		query.append(APEL2_JOIN);
		query.append(", n1.DESC_EU ENTIDAD_DESC_EU");

		return query.toString();
	}
	/*
	 * EXPEDIENTES - FIN
	 */

	/*
	 * TAREAS - INICIO
	 */
	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTareasSinAsignarFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTareasGeneralJoins(query, params);
		query.append(DaoConstants.WHERE).append(
				" ((TO_DATE(TO_CHAR(t3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
		query.append(
				" AND TO_DATE(TO_CHAR(t3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(sysdate+7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
		query.append(
				" OR (TO_DATE(TO_CHAR(t4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
		query.append(FECHA_MENOR_IGUAL);
		query.append(") AND (RECURSO_ASIGNACION_081 IS NULL OR ESTADO_ASIGNACION_081 ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION).append(" ) ");
		params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());

	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTareasAsignadasFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTareasGeneralJoins(query, params);
		query.append(DaoConstants.WHERE).append(" RECURSO_ASIGNACION_081 ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND ESTADO_ASIGNACION_081 ").append(DaoConstants.SIGNO_DISTINTO_INTERROGACION);

		query.append(ESTADO_EJECUCION_WHERE).append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(" AND ES_RECURSO_DISPONIBLE(DNI_RECURSO_081, ID_TAREA_081, NULL) ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(TipoRecursoEnum.INTERNO.getValue());
		params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		params.add(Constants.NO);
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTareasFinalizanHoyFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTareasGeneralJoins(query, params);
		query.append(DaoConstants.WHERE)
				.append(" TO_CHAR(t1.FECHA_FIN_081,'yyyy/MM/dd') = TO_CHAR(sysdate,'yyyy/MM/dd') ");
		query.append(ESTADO_EJECUCION_WHERE).append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTareasRetrasadasFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTareasGeneralJoins(query, params);
		query.append(DaoConstants.WHERE).append(
				" TO_DATE(TO_CHAR(t1.FECHA_FIN_081,'yyyy/MM/dd hh24:mi:ss'),'yyyy/MM/dd hh24:mi:ss') < TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd hh24:mi:ss'),'yyyy/MM/dd hh24:mi:ss') ");
		query.append(ESTADO_EJECUCION_WHERE).append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	private static final void getTareasGeneralJoins(StringBuilder query, List<Object> params) {
		query.append(DaoConstants.FROM).append(AA79B81S01).append(T1);
		query.append(DaoConstants.JOIN).append(AA79B15S01).append(T15);
		query.append(" ON t1.ID_TIPO_TAREA_081     = t15.ID_015 ");
		query.append(DaoConstants.JOIN).append(AA79B51S01).append(T2);
		query.append(" ON t1.ANYO_081     = t2.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_081 = t2.NUM_EXP_051 ");
		query.append(DaoConstants.LEFT_JOIN).append(AA79B52S01).append(T3);
		query.append(" ON t2.ANYO_051     = t3.ANYO_052 ");
		query.append(" AND t2.NUM_EXP_051 = t3.NUM_EXP_052 ");
		query.append(DaoConstants.LEFT_JOIN).append(AA79B53S01).append(T4);
		query.append(" ON t2.ANYO_051     = t4.ANYO_053 ");
		query.append(" AND t2.NUM_EXP_051 = t4.NUM_EXP_053 ");
		query.append(DaoConstants.JOIN).append(AA79B59S01).append(T5);
		query.append(" ON t2.ANYO_051 = t5.ANYO_059 ");
		query.append(" AND t2.NUM_EXP_051 = t5.NUM_EXP_059 ");
		query.append(" AND t2.ESTADO_BITACORA_051 = t5.ID_ESTADO_BITACORA_059 ");
		List<Integer> listaEstados = new ArrayList<Integer>();
		listaEstados.add(EstadoExpedienteEnum.EN_CURSO.getValue());
		listaEstados.add(EstadoExpedienteEnum.CERRADO.getValue());
		query.append(getIdEstado59In(T5, listaEstados));
	}

	/**
	 *
	 * @param tabla        String
	 * @param listaEstados List<Integer>
	 * @return
	 */
	private static String getIdEstado59In(String tabla, List<Integer> listaEstados) {
		StringBuilder str = new StringBuilder();
		str.append(DaoConstants.AND).append(Constants.ESPACIO).append(tabla.trim()).append(".id_estado_exp_059 ")
				.append(DaoConstants.IN).append(DaoConstants.ABRIR_PARENTESIS);
		boolean esPrimero = true;
		for (Integer estado : listaEstados) {
			if (!esPrimero) {
				str.append(DaoConstants.SIGNO_COMA);
			} else {
				esPrimero = false;
			}
			str.append(estado);
		}
		str.append(DaoConstants.CERRAR_PARENTESIS);
		return str.toString();
	}

	/**
	 *
	 * @param query StringBuilder
	 */
	private static final void getTareasGestorJoins(StringBuilder query) {
		// GestorExpediente
		query.append(" LEFT JOIN AA79B54S01 g1 ON  ").append("t2.ANYO_051 = g1.ANYO_054 AND ")
				.append("t2.NUM_EXP_051 = g1.NUM_EXP_054 ");
	}

	/**
	 *
	 * @param query StringBuilder
	 */
	private static final void getTareasRecursoAsignadoJoins(StringBuilder query) {
		query.append(" LEFT JOIN AA79B77S01 h1 ON t1.DNI_RECURSO_081 = h1.DNI_077 ");
	}

	/**
	 *
	 * @return String
	 */
	private String getSelectTareas() {
		final StringBuilder str = new StringBuilder();
		final Locale eu = new Locale(Constants.LANG_EUSKERA);

		// Expediente
		str.append(DaoConstants.SELECT).append(" DISTINCT t2.NUM_EXP_051 NUM_EXP_051 ")
				.append(" ,t2.ANYO_051 ANYO_051 ");
		str.append(", SUBSTR(t2.ANYO_051,3,4) || '/' || LPAD(t2.NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO");
		str.append(", t2.ID_TIPO_EXPEDIENTE_051 ID_TIPO_EXPEDIENTE_051");
		str.append(", T5.ID_ESTADO_EXP_059 IDESTADOEXP059");
		str.append(", T5.ID_FASE_EXPEDIENTE_059 IDFASEEXPEDIENTE059");
		str.append(", t2.ORIGEN_051 ORIGEN051");

		// Tareas
		str.append(" ,t1.ID_TAREA_081 IDTAREA ");
		str.append(" ,t1.ID_TIPO_TAREA_081 IDTIPOTAREA ");
		str.append(" ,t15.DESC_EU_015 TAREAEU ");
		str.append(" ,t1.HORAS_PREVISTAS_081 HORASPREVISTAS ");
		str.append(" ,t1.ESTADO_EJECUCION_081 ESTADOEJECUCION ");
		/**/
		str.append(" ,CASE WHEN (" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ "=t1.ESTADO_EJECUCION_081 AND t1.FECHA_FIN_081 < SYSDATE) THEN '"
				+ this.messageSource.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, eu) + "'");
		str.append(" ELSE (DECODE(t1.ESTADO_EJECUCION_081, " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				+ ",'"
				+ this.messageSource.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, eu)
				+ "',");
		str.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ",'"
				+ this.messageSource.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, eu)
				+ "')) END AS ESTADOEJECUCIONEU");
		str.append(" ,t1.ESTADO_ASIGNACION_081 ESTADO_ASIGNACION_081 ");
		str.append(DAOUtils.getDecodeAcciones("t1.ESTADO_ASIGNACION_081", "ESTADOASIGNACIONEU", this.messageSource,
				"EstadoAceptacionTareaEnum", eu));

		str.append(", t1.FECHA_ASIGNACION_081 FECHA_ASIGNACION_081");
		str.append(", TO_CHAR(t1.FECHA_ASIGNACION_081,'HH24:MI') HORA_FECHA_ASIGNACION_081");
		str.append(", t1.FECHA_FIN_081 FECHA_FIN_081");
		str.append(", TO_CHAR(t1.FECHA_FIN_081,'HH24:MI') HORA_FECHA_FIN_081");
		str.append(", t1.ORDEN_081 ORDEN");
		str.append(", ES_RECURSO_DISPONIBLE(t1.DNI_RECURSO_081, ID_TAREA_081, NULL) ESRECURSODISPONIBLE ");

		str.append(", NVL(t3.FECHA_FIN_052,NVL(t4.FECHA_FINAL_IZO_053,t4.FECHA_FINAL_SOLIC_053))  FECHA_FINAL");
		str.append(
				", TO_CHAR(NVL(t3.FECHA_FIN_052,NVL(t4.FECHA_FINAL_IZO_053,t4.FECHA_FINAL_SOLIC_053)),'HH24:MI') HORA_FINAL");

		str.append(VIP_JOIN);
		str.append(", t2.TITULO_051 TITULO_051");

		str.append(NOMBRE_JOIN);
		str.append(APEL1_JOIN);
		str.append(APEL2_JOIN);
		str.append(" ,t29.NOMBRE_LOTE_029 NOMBRELOTE");

		return str.toString();
	}
	/*
	 * TAREAS - FIN
	 */

	/*
	 * TRAMITES - INICIO
	 */
	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTramitesPendientesAcepFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTramitesGeneralJoins(query, params, Constants.UNO);
		PlanificacionExpedienteUtils.getTramitesGeneralWhere(query, params,
				EstadoRequerimientoEnum.PENDIENTE.getValue());
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTramitesAceptadasFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTramitesGeneralJoins(query, params, Constants.DOS);
		PlanificacionExpedienteUtils.getTramitesGeneralWhere(query, params,
				EstadoRequerimientoEnum.ACEPTADA.getValue());
	}

	/**
	 *
	 * @param dni    String
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	public static final void getTramitesRechazadasFromWhere(String dni, StringBuilder query, List<Object> params) {
		PlanificacionExpedienteUtils.getTramitesGeneralJoins(query, params, Constants.TRES);
		PlanificacionExpedienteUtils.getTramitesGeneralWhere(query, params,
				EstadoRequerimientoEnum.RECHAZADA.getValue());
	}

	/**
	 *
	 * @param query            StringBuilder
	 * @param params           List<Object>
	 * @param estadoAceptacion
	 */
	private static final void getTramitesGeneralJoins(StringBuilder query, List<Object> params,
			Integer estadoAceptacion) {
		query.append(DaoConstants.FROM).append(AA79B64S01).append(T1);
		query.append(DaoConstants.JOIN + DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT + "NUM_EXP_059"
				+ DaoConstants.SIGNO_COMA + "ANYO_059" + DaoConstants.SIGNO_COMA + "ID_REQUERIMIENTO_059"
				+ DaoConstants.FROM + DBConstants.TABLA_59 + DaoConstants.WHERE + "ID_ESTADO_EXP_059"
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + "ID_REQUERIMIENTO_059");
		query.append(DaoConstants.IS_NOT_NULL);
		query.append(DaoConstants.GROUP_BY + "NUM_EXP_059" + DaoConstants.SIGNO_COMA + "ANYO_059"
				+ DaoConstants.SIGNO_COMA + "ID_REQUERIMIENTO_059" + DaoConstants.CERRAR_PARENTESIS + T2);
		query.append(DaoConstants.ON + "t2.ID_REQUERIMIENTO_059" + DaoConstants.SIGNOIGUAL + "t1.ID_064");
		query.append(DaoConstants.JOIN).append(AA79B51S01).append(T3);
		query.append(" ON t3.NUM_EXP_051 = t2.NUM_EXP_059 ");
		query.append(" AND t3.ANYO_051   = t2.ANYO_059 ");
		query.append(DaoConstants.JOIN).append(AA79B59S01).append(T4);
		query.append(" ON t4.NUM_EXP_059 = t3.NUM_EXP_051 ");
		query.append(" AND t4.ANYO_059 = t3.ANYO_051 ");
		query.append(" AND t4.ID_ESTADO_BITACORA_059 = t3.ESTADO_BITACORA_051 ");
		List<Integer> listaEstados = new ArrayList<Integer>();
		listaEstados.add(EstadoExpedienteEnum.EN_CURSO.getValue());
		if (Constants.DOS == estadoAceptacion) {
			// onartuta - anyadir estado cerrado
			listaEstados.add(EstadoExpedienteEnum.CERRADO.getValue());
		}
		query.append(getIdEstado59In(T4, listaEstados));
		params.add(EstadoExpedienteEnum.EN_CURSO.getValue());
	}

	/**
	 *
	 * @param query             StringBuilder
	 * @param params            List<Object>
	 * @param estadoSubsanacion int
	 */
	public static final void getTramitesGeneralWhere(StringBuilder query, List<Object> params, int estadoSubsanacion) {
		query.append(getEstadoSubsanacionTramites(estadoSubsanacion));
		params.add(estadoSubsanacion);
	}

	private static String getEstadoSubsanacionTramites(int estadoSubsanacion) {
		StringBuilder str = new StringBuilder();
		str.append(DaoConstants.WHERE);
		if (EstadoSubsanacionEnum.RECHAZADO.getValue() == estadoSubsanacion) {
			str.append(DaoConstants.ABRIR_PARENTESIS);
			str.append(DaoConstants.ABRIR_PARENTESIS);
		}
		str.append(Constants.ESPACIO).append(T1.trim()).append(".ESTADO_SUBSANACION_064 ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		if (EstadoSubsanacionEnum.RECHAZADO.getValue() == estadoSubsanacion) {
			// se van a incluir ademas de los tramites rechazados, los
			// pendientes de aceptacion en los que se ha superado el plazo
			// limite para la realizacion del requerimiento
			str.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.OR + DaoConstants.ABRIR_PARENTESIS + T1.trim()
					+ ".ESTADO_SUBSANACION_064 " + DaoConstants.SIGNOIGUAL + EstadoSubsanacionEnum.PENDIENTE.getValue()
					+ DaoConstants.AND + DaoConstants.TO_DATE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.TO_CHAR
					+ DaoConstants.ABRIR_PARENTESIS + T1.trim() + ".FECHA_LIMITE_064 " + DaoConstants.SIGNO_COMA
					+ DaoConstants.SIGNO_APOSTROFO + Constants.JAVA_DATE_HOUR_FORMAT_EU + DaoConstants.SIGNO_APOSTROFO
					+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
					+ Constants.JAVA_DATE_HOUR_FORMAT_EU + DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS
					+ DaoConstants.SIGNO_MENOR_IGUAL_QUE + DaoConstants.TO_DATE + DaoConstants.ABRIR_PARENTESIS
					+ DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.SYSDATE
					+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + Constants.JAVA_DATE_HOUR_FORMAT_EU
					+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA
					+ DaoConstants.SIGNO_APOSTROFO + Constants.JAVA_DATE_HOUR_FORMAT_EU + DaoConstants.SIGNO_APOSTROFO
					+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS);
		} else if (EstadoSubsanacionEnum.PENDIENTE.getValue() == estadoSubsanacion) {
			// se van a contabilizar los tramites pendientes de aceptacion pero
			// que aun estan en plazo de realizacion del requerimiento (no se
			// haya superado la fecha y hora de limite de realizacion del
			// requerimiento)
			str.append(DaoConstants.AND + DaoConstants.TO_DATE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.TO_CHAR
					+ DaoConstants.ABRIR_PARENTESIS + T1.trim() + ".FECHA_LIMITE_064 " + DaoConstants.SIGNO_COMA
					+ DaoConstants.SIGNO_APOSTROFO + Constants.JAVA_DATE_HOUR_FORMAT_EU + DaoConstants.SIGNO_APOSTROFO
					+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
					+ Constants.JAVA_DATE_HOUR_FORMAT_EU + DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS
					+ DaoConstants.SIGNO_MAYOR_QUE + DaoConstants.TO_DATE + DaoConstants.ABRIR_PARENTESIS
					+ DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.SYSDATE
					+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + Constants.JAVA_DATE_HOUR_FORMAT_EU
					+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA
					+ DaoConstants.SIGNO_APOSTROFO + Constants.JAVA_DATE_HOUR_FORMAT_EU + DaoConstants.SIGNO_APOSTROFO
					+ DaoConstants.CERRAR_PARENTESIS);
		}
		return str.toString();
	}

	/**
	 *
	 * @param tabla String
	 * @return String
	 */
	private static final String getEstadoSubsanacion64(boolean isWhere, boolean isOr) {
		StringBuilder str = new StringBuilder();
		if (isWhere) {
			str.append(DaoConstants.WHERE);
		}
		if (isOr) {
			str.append(DaoConstants.OR);
		}
		str.append(Constants.ESPACIO).append(T1.trim()).append(".ESTADO_SUBSANACION_064 ")
				.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		return str.toString();
	}

	/**
	 *
	 * @return String
	 */
	private String getSelectTramites() {
		final Locale eu = new Locale(Constants.LANG_EUSKERA);
		final StringBuilder str = new StringBuilder();
		str.append(DaoConstants.SELECT).append(" DISTINCT t3.NUM_EXP_051 NUM_EXP_051 ")
				.append(" ,t3.ANYO_051 ANYO_051 ");
		str.append(", t3.ID_TIPO_EXPEDIENTE_051 ID_TIPO_EXPEDIENTE_051");
		str.append(", SUBSTR(t3.ANYO_051,3,4) || '/' || LPAD(t3.NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO");
		str.append(" ,t1.FECHA_REQ_064 FECHA_REQ_064 ").append(" ,t1.FECHA_ACEPTACION_064 FECHA_ACEPTACION_064 ");
		str.append(DAOUtils.getDecodeAcciones("t1.ESTADO_SUBSANACION_064", "ESTADOSUBSANACIONEU", this.messageSource,
				"EstadoRequerimientoEnum", eu));
		str.append(", t1.FECHA_LIMITE_064 FECHA_LIMITE_064");
		str.append(", TO_CHAR(t1.FECHA_LIMITE_064,'HH24:MI') HORA_FECHA_LIMITE_064");
		str.append(DAOUtils.getDecodeAcciones("t1.TIPO_REQUERIMIENTO_064", "TIPOREQUERIMIENTOEU", this.messageSource,
				"TipoRequerimientoEnum", eu));

		// Falta la entidad y solicitante con indicador de VIP. Falta el
		// presupuesto.
		str.append(VIP_JOIN);
		str.append(", (h1.PREDNI_077 || g1.DNI_SOLICITANTE_054 || h1.SUFDNI_077) AS DNICOMPLETO");
		str.append(", h1.PREDNI_077 " + DBConstants.PREDNI);
		str.append(", g1.DNI_SOLICITANTE_054 " + DBConstants.DNI);
		str.append(", h1.SUFDNI_077 " + DBConstants.SUFDNI);
		str.append(NOMBRE_JOIN);
		str.append(APEL1_JOIN);
		str.append(APEL2_JOIN);
		str.append(", n1.DESC_EU ENTIDAD_DESC_EU");

		return str.toString();
	}
	/*
	 * TRAMITES - FIN
	 */

	/*
	 * UTILIDADES - INICIO
	 */
	/**
	 *
	 * @param filtro String
	 * @return String
	 */
	public String getSelectResumen(String filtro) {
		if (EXPEDIENTES_FILTRO.equals(filtro) || EXPEDIENTES_SIN_FILTRO.equals(filtro)) {
			return getSelectExpedientes(filtro);
		} else if (TAREAS_FILTRO.equals(filtro) || TAREAS_FILTRO_SIN_CONDICIONES.equals(filtro)) {
			return getSelectTareas();
		} else {
			return getSelectTramites();
		}
	}

	/**
	 *
	 * @param filtro String
	 * @return String
	 */
	public static final String getTipoFiltro(String filtro) {
		return filtro.split(Constants.GUION)[Constants.CERO];
	}

	/**
	 *
	 * @param filtro String
	 * @return int
	 */
	public static final int getValorFiltro(String filtro) {
		return Integer.valueOf(filtro.split(Constants.GUION)[Constants.UNO]);
	}
	/*
	 * UTILIDADES - FIN
	 */

	/**
	 *
	 * @param expTareasResumen ExpTareasResumen
	 * @param query            StringBuilder
	 * @param params           List<Object>
	 */
	public void getResumenQuery(ExpTareasResumen expTareasResumen, StringBuilder query, List<Object> params,
			boolean count) {
		String tabla = "";
		final String[] filtroDatos = expTareasResumen.getFiltroDatos();

		final String filtro = filtroDatos[Constants.CERO];
		// Se obtiene la select: puede ser del tipo expedientes, del tipo
		// tarea o del tipo trámites.
		final String tipoFiltro = PlanificacionExpedienteUtils.getTipoFiltro(filtro);

		boolean joinGeneral = false;
		for (String filtroItem : filtroDatos) {
			int value = PlanificacionExpedienteUtils.getValorFiltro(filtroItem);
			if (EXPEDIENTES_FILTRO.equals(tipoFiltro)) {
				if (!joinGeneral) {
					getCountJoins(query, params, count);

					// Joins
					PlanificacionExpedienteUtils.getExpedientesGeneralJoins(query, params);
					PlanificacionExpedienteUtils.getGestorExpedienteJoins(query, T1.trim());

					joinGeneral = true;

					// Where
					query.append(DaoConstants.WHERE);
					query.append(DaoConstants.ABRIR_PARENTESIS);
					tabla = T1.trim();
				} else {
					query.append(DaoConstants.OR);
				}

				query.append(DaoConstants.ABRIR_PARENTESIS);
				PlanificacionExpedienteUtils.getWhereExpedientesSelect(query, params, value);
				query.append(DaoConstants.CERRAR_PARENTESIS);
			} else if (EXPEDIENTES_SIN_FILTRO.equals(tipoFiltro)) {
				getCountJoins(query, params, count);
				PlanificacionExpedienteUtils.getExpSinAsignadorFromWhereBuscador(expTareasResumen, null, query, params);
				tabla = T1.trim();
			} else if (TAREAS_FILTRO.equals(tipoFiltro) || TAREAS_FILTRO_SIN_CONDICIONES.equals(tipoFiltro)) {
				if (!joinGeneral) {
					// Joins
					PlanificacionExpedienteUtils.getTareasGeneralJoins(query, params);
					PlanificacionExpedienteUtils.getTareasGestorJoins(query);
					PlanificacionExpedienteUtils.getTareasRecursoAsignadoJoins(query);
					query.append(" LEFT JOIN AA79B29S01 t29 ON t1.ID_LOTE_081 = t29.ID_LOTE_029");
					joinGeneral = true;

					// Where
					query.append(DaoConstants.WHERE);

					tabla = T2.trim();
				} else {
					query.append(DaoConstants.OR);
				}

				// Condiciones
				if (TAREAS_FILTRO.equals(tipoFiltro)) {
					query.append(DaoConstants.ABRIR_PARENTESIS);
					PlanificacionExpedienteUtils.getWhereTareasSelect(query, params, value);
					query.append(DaoConstants.CERRAR_PARENTESIS);
				}
			} else {
				if (!joinGeneral) {
					// Joins
					PlanificacionExpedienteUtils.getTramitesGeneralJoins(query, params, value);
					PlanificacionExpedienteUtils.getGestorExpedienteJoins(query, T3.trim());

					joinGeneral = true;

					// Se introduce la primera where
					query.append(getEstadoSubsanacionTramites(value));

					tabla = T3.trim();
				} else {
					// Se introduce el resto de condiciones
					query.append(PlanificacionExpedienteUtils.getEstadoSubsanacion64(false, true));

				}
				// El valor coincide con el tipo de parámetro enviado en el
				// filtro: 1- PENDIENTE, 2- ACEPTADO, 3- RECHAZADO
				params.add(value);
			}
		}
		parentesisAuxiliarWhere(query, tipoFiltro);

		PlanificacionExpedienteUtils.getFiltroPorAsignador(query, params, expTareasResumen, tabla);
		PlanificacionExpedienteUtils.getFiltroPorGrupoTrabajoWhere(query, params, expTareasResumen, tipoFiltro);
	}

	public void getJoinsResumenCargaQuery(Integer filtroDatosCarga, StringBuilder query, List<Object> params,
			boolean count) {

		// Joins
		PlanificacionExpedienteUtils.getTareasGeneralJoins(query, params);
		PlanificacionExpedienteUtils.getTareasGestorJoins(query);
		PlanificacionExpedienteUtils.getTareasRecursoAsignadoJoins(query);
		query.append(" LEFT JOIN AA79B29S01 t29 ON t1.ID_LOTE_081 = t29.ID_LOTE_029");

	}

	private void parentesisAuxiliarWhere(StringBuilder query, final String tipoFiltro) {
		if (EXPEDIENTES_FILTRO.equals(tipoFiltro)) {
			query.append(DaoConstants.CERRAR_PARENTESIS);
		}
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 * @param count  boolean
	 */
	private void getCountJoins(StringBuilder query, List<Object> params, boolean count) {
		if (!count) {
			getSubSelectNoConformidad(query, params);
			getSubSelectTareasSinAsignar(query, params);
		}
	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 * @param value  int
	 */
	private static void getWhereExpedientesSelect(StringBuilder query, List<Object> params, int value) {
		if (PlanifExpedientesEnum.PENDIENTES_ENTREGA.getId() == value) {
			query.append(
					" TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd hh24:mi:ss')     <= TO_CHAR(sysdate,'yyyy/MM/dd hh24:mi:ss') ");
			query.append(
					" OR TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd hh24:mi:ss')  <= TO_CHAR(sysdate,'yyyy/MM/dd hh24:mi:ss') ");
		} else if (PlanifExpedientesEnum.HOY.getId() == value) {
			query.append(" TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd') = TO_CHAR(sysdate,'yyyy/MM/dd') ");
			query.append("OR TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd') = TO_CHAR(sysdate,'yyyy/MM/dd')");
		} else if (PlanifExpedientesEnum.SIETE_DIAS.getId() == value) {
			query.append(
					" (TO_DATE(TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd')     > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" AND TO_DATE(TO_CHAR(t2.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd')       <= TO_DATE(TO_CHAR(sysdate+7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(
					" OR (TO_DATE(TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd')  > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" AND TO_DATE(TO_CHAR(t3.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(sysdate + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
		} else if (PlanifExpedientesEnum.NO_CONFORMIDAD.getId() == value) {
			query.append(" (0 < (Select count(1) from AA79B81T00 t5 where t1.ANYO_051 = t5.ANYO_081 ");
			query.append(" AND t1.NUM_EXP_051 = t5.NUM_EXP_081 ");
			query.append(" AND t5.ID_TIPO_TAREA_081 = ? AND t5.ESTADO_EJECUCION_081 = ?)) ");
			params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue());
			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		} else if (PlanifExpedientesEnum.SIN_PLANIFICAR.getId() == value) {
			query.append(" ((0 < (Select count(1) from AA79B81T00 t5 where t1.ANYO_051 = t5.ANYO_081 ");
			query.append(" AND t1.NUM_EXP_051 = t5.NUM_EXP_081 ");
			query.append(
					" AND (t5.ESTADO_ASIGNACION_081 = ? OR t5.ESTADO_ASIGNACION_081 = ?))) OR( 0 = (Select count(1) from AA79B81T00 t5 where t1.ANYO_051 = t5.ANYO_081 ");
			query.append(" AND t1.NUM_EXP_051 = t5.NUM_EXP_081))) ");
			params.add(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		}
	}

	private static void getExpSinAsignadorFromWhereBuscador(ExpTareasResumen expTareasResumen, String dni,
			StringBuilder query, List<Object> params) {

		query.append(DaoConstants.FROM).append(AA79B51S01).append(T1);
		query.append(DaoConstants.JOIN).append(AA79B59S01).append(T2);
		query.append(" ON t1.ANYO_051 = t2.ANYO_059 ");
		query.append(" AND t1.NUM_EXP_051 = t2.NUM_EXP_059 ");
		query.append(" AND t1.ESTADO_BITACORA_051 = t2.ID_ESTADO_BITACORA_059 ");
		query.append(getIdEstado59(T2));
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + " t15 ");
		query.append(DaoConstants.ON + " t1.ANYO_051 " + DaoConstants.SIGNOIGUAL + " t15.ANYO_052 ");
		query.append(DaoConstants.AND + " t1.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + " t15.NUM_EXP_052 ");
		if (StringUtils.isBlank(dni)) {
			query.append(DaoConstants.LEFT_JOIN).append(AA79B53S01).append(T3);
			query.append(" ON t1.ANYO_051 = t3.ANYO_053 ");
			query.append(" AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		}

		if (StringUtils.isBlank(dni)) {
			PlanificacionExpedienteUtils.getGestorExpedienteJoins(query, T1.trim());
		}

		if (StringUtils.isNotBlank(expTareasResumen.getIdsGrupoTrabajo())) {
			query.append(DaoConstants.AND + " OBTENER_GRUPO_TRABAJO( T1.ANYO_051, t1.NUM_EXP_051) " + DaoConstants.IN
					+ DaoConstants.ABRIR_PARENTESIS + expTareasResumen.getIdsGrupoTrabajo()
					+ DaoConstants.CERRAR_PARENTESIS);
		}

		query.append(DaoConstants.WHERE).append(" DNI_ASIGNADOR_051 IS NULL ");

		params.add(EstadoExpedienteEnum.EN_CURSO.getValue());

	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 * @param value  int
	 */
	private static void getWhereTareasSelect(StringBuilder query, List<Object> params, int value) {
		// Este primer caso es específico para el dashboard,
		// un filtro que en planificación no existe.
		// no lo puedo meter en el enum porque saldría también en la
		// planificación
		if (Constants.CERO == value) {
			query.append(
					" ((TO_DATE(TO_CHAR(t3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') = TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(
					" OR (TO_DATE(TO_CHAR(t4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') = TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(") AND (RECURSO_ASIGNACION_081 IS NULL OR ESTADO_ASIGNACION_081 ")
					.append(DaoConstants.SIGNOIGUAL_INTERROGACION).append(" ) ");
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
		} else if (PlanifTareasEnum.SIN_ASIGNAR.getId() == value) {
			query.append(
					" ((TO_DATE(TO_CHAR(t3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" AND TO_DATE(TO_CHAR(t3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(sysdate+7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(
					" OR (TO_DATE(TO_CHAR(t4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(FECHA_MENOR_IGUAL);
			query.append(") AND (RECURSO_ASIGNACION_081 IS NULL OR ESTADO_ASIGNACION_081 ")
					.append(DaoConstants.SIGNOIGUAL_INTERROGACION).append(" ) ");
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());

		} else if (PlanifTareasEnum.ASIGNADAS.getId() == value) {
			query.append(" RECURSO_ASIGNACION_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(" AND ESTADO_ASIGNACION_081 ").append(DaoConstants.SIGNO_DISTINTO_INTERROGACION);
			query.append(ESTADO_EJECUCION_WHERE).append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(" AND ES_RECURSO_DISPONIBLE(DNI_RECURSO_081, ID_TAREA_081, NULL) ")
					.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

			params.add(TipoRecursoEnum.INTERNO.getValue());
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
			params.add(Constants.NO);
		} else if (PlanifTareasEnum.FINALIZAN_HOY.getId() == value) {
			query.append(" TO_CHAR(t1.FECHA_FIN_081,'yyyy/MM/dd') = TO_CHAR(sysdate,'yyyy/MM/dd') ");
			query.append(ESTADO_EJECUCION_WHERE).append(DaoConstants.SIGNOIGUAL_INTERROGACION);

			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		} else {
			query.append(
					" TO_DATE(TO_CHAR(t1.FECHA_FIN_081,'yyyy/MM/dd hh24:mi:ss'),'yyyy/MM/dd hh24:mi:ss') < TO_DATE(TO_CHAR(sysdate,'yyyy/MM/dd hh24:mi:ss'),'yyyy/MM/dd hh24:mi:ss') ");
			query.append(ESTADO_EJECUCION_WHERE).append(DaoConstants.SIGNOIGUAL_INTERROGACION);

			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		}
	}

	/*
	 * Filtros generales
	 */
	/**
	 *
	 * @param query
	 * @param params
	 * @param dni
	 * @param tabla
	 */
	public static void getFiltroPorAsignador(StringBuilder query, List<Object> params, ExpTareasResumen expediente,
			String tabla) {
		if (expediente != null && expediente.getTecnico() != null
				&& StringUtils.isNotBlank(expediente.getTecnico().getDni())) {
			query.append(DaoConstants.AND).append(tabla).append(".DNI_ASIGNADOR_051")
					.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			params.add(expediente.getTecnico().getDni());
		}
	}

	/**
	 *
	 * @param query
	 * @param params
	 * @param dni
	 * @param tabla
	 */
	public static void getFiltroPorGrupoTrabajoWhere(StringBuilder query, List<Object> params,
			ExpTareasResumen expediente, String tipoFiltro) {
		if (expediente != null && StringUtils.isNotBlank(expediente.getIdsGrupoTrabajo())) {
			String tablaAux51 = "";
			if (TRAMITES_FILTRO.equals(tipoFiltro)) {
				tablaAux51 = "t3";
			} else if (TAREAS_FILTRO.equals(tipoFiltro) || TAREAS_FILTRO_SIN_CONDICIONES.equals(tipoFiltro)) {
				tablaAux51 = "t2";
			} else {
				tablaAux51 = "t1";
			}
			query.append(DaoConstants.AND + " OBTENER_GRUPO_TRABAJO( " + tablaAux51 + ".ANYO_051, " + tablaAux51
					+ ".NUM_EXP_051) " + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
					+ expediente.getIdsGrupoTrabajo() + DaoConstants.CERRAR_PARENTESIS);

		}
	}

	/*
	 * ROWMAPPERS INICIO
	 */
	/**
	 *
	 * @param resultSet ResultSet
	 * @return ExpTareasResumen
	 * @throws SQLException e
	 */
	public static final ExpTareasResumen expedienteRwMap(ResultSet resultSet) throws SQLException {
		final ExpTareasResumen expTareasResumen = new ExpTareasResumen();

		expTareasResumen.setAnyo(resultSet.getLong(ANYO_EXPEDIENTE));
		expTareasResumen.setNumExp(resultSet.getInt(NUM_EXPEDIENTE));
		expTareasResumen.setIdTipoExpediente(resultSet.getString("ID_TIPO_EXPEDIENTE_051"));
		expTareasResumen.setTipoExpedienteDescEs(resultSet.getString("TIPO_EXPEDIENTE_ES"));
		expTareasResumen.setTipoExpedienteDescEu(resultSet.getString("TIPO_EXPEDIENTE_EU"));
		expTareasResumen.setFechaAlta(resultSet.getDate("FECHA_ALTA_051"));
		expTareasResumen.setHoraAlta(resultSet.getString("HORA_ALTA_051"));

		final ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expTareasResumen.setExpedienteTradRev(expedienteTradRev);

		final Gestor gestorExpediente = new Gestor();
		final Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString(IND_VIP_054));
		solicitanteAux.setPreDni(resultSet.getString(DBConstants.PREDNI));
		solicitanteAux.setDni(resultSet.getString(DBConstants.DNI));
		solicitanteAux.setSufDni(resultSet.getString(DBConstants.SUFDNI));
		solicitanteAux.setDniCompleto(resultSet.getString("DNICOMPLETO"));
		solicitanteAux.setNombre(resultSet.getString(NOMBRE_077));
		solicitanteAux.setApellido1(resultSet.getString(APEL1_077));
		solicitanteAux.setApellido2(resultSet.getString(APEL2_077));
		gestorExpediente.setSolicitante(solicitanteAux);

		final Entidad entidadAux = new Entidad();
		entidadAux.setDescEu(resultSet.getString("ENTIDAD_DESC_EU"));
		gestorExpediente.setEntidad(entidadAux);
		expTareasResumen.setGestorExpediente(gestorExpediente);

		expTareasResumen.setConformidad(resultSet.getString("CONFORMIDAD"));
		expTareasResumen.setSinAsignar(resultSet.getString("TAREASSINASIGNAR"));

		expTareasResumen.setFechaFin(resultSet.getDate("FECHA_FINAL"));
		expTareasResumen.setHoraFin(resultSet.getString("HORA_FINAL"));

		expTareasResumen.setIndUrgente(resultSet.getString(DBConstants.ESURGENTE));
		return expTareasResumen;
	}

	/**
	 *
	 * @param resultSet ResultSet
	 * @return ExpTareasResumen
	 * @throws SQLException e
	 */
	public static final ExpTareasResumen tareaRwMap(ResultSet resultSet) throws SQLException {
		final ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		expTareasResumen.setAnyo(resultSet.getLong(ANYO_EXPEDIENTE));
		expTareasResumen.setNumExp(resultSet.getInt(NUM_EXPEDIENTE));
		expTareasResumen.setIdTipoExpediente(resultSet.getString(ID_TIPO_EXPEDIENTE));

		final Tareas tarea = new Tareas();
		final TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(resultSet.getLong("IDTIPOTAREA"));
		tiposTarea.setDescEu015(resultSet.getString("TAREAEU"));
		tarea.setTipoTarea(tiposTarea);
		tarea.setEstadoEjecucion(resultSet.getInt("ESTADOEJECUCION"));
		tarea.setEstadoEjecucionDesc(resultSet.getString("ESTADOEJECUCIONEU"));
		tarea.setEstadoAsignado(resultSet.getInt("ESTADO_ASIGNACION_081"));
		tarea.setEstadoAsignadoDesc(resultSet.getString("ESTADOASIGNACIONEU"));
		tarea.setFechaFin(resultSet.getDate("FECHA_FIN_081"));
		tarea.setHoraFin(resultSet.getString("HORA_FECHA_FIN_081"));
		tarea.setHorasPrevistas(resultSet.getString("HORASPREVISTAS"));
		tarea.setFechaAsignacion(resultSet.getDate("FECHA_ASIGNACION_081"));
		tarea.setHoraAsignacion(resultSet.getString("HORA_FECHA_ASIGNACION_081"));
		tarea.setRecursoDisponible(resultSet.getString("ESRECURSODISPONIBLE"));
		tarea.setIdTarea(resultSet.getBigDecimal("IDTAREA"));

		expTareasResumen.setTarea(tarea);

		final Gestor gestorExpediente = new Gestor();
		final Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString(IND_VIP_054));
		solicitanteAux.setNombre(resultSet.getString(NOMBRE_077));
		solicitanteAux.setApellido1(resultSet.getString(APEL1_077));
		solicitanteAux.setApellido2(resultSet.getString(APEL2_077));
		Lotes lotes = new Lotes();
		lotes.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));
		if (!StringUtils.isEmpty(lotes.getNombreLote())) {
			solicitanteAux.setNombre(lotes.getNombreLote());
		}
		gestorExpediente.setSolicitante(solicitanteAux);
		final Entidad entidadAux = new Entidad();
		gestorExpediente.setEntidad(entidadAux);
		expTareasResumen.setGestorExpediente(gestorExpediente);
		expTareasResumen.setTitulo(resultSet.getString("TITULO_051"));

		expTareasResumen.setFechaFin(resultSet.getDate("FECHA_FINAL"));
		expTareasResumen.setHoraFin(resultSet.getString("HORA_FINAL"));

		return expTareasResumen;
	}

	/**
	 *
	 * @param resultSet ResultSet
	 * @return ExpTareasResumen
	 * @throws SQLException e
	 */
	public static final ExpTareasResumen tramiteRwMap(ResultSet resultSet) throws SQLException {
		final ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		expTareasResumen.setAnyo(resultSet.getLong(ANYO_EXPEDIENTE));
		expTareasResumen.setNumExp(resultSet.getInt(NUM_EXPEDIENTE));
		expTareasResumen.setIdTipoExpediente(resultSet.getString(ID_TIPO_EXPEDIENTE));

		final Gestor gestorExpediente = new Gestor();
		final Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString(IND_VIP_054));

		solicitanteAux.setPreDni(resultSet.getString(DBConstants.PREDNI));
		solicitanteAux.setDni(resultSet.getString(DBConstants.DNI));
		solicitanteAux.setSufDni(resultSet.getString(DBConstants.SUFDNI));
		solicitanteAux.setDniCompleto(resultSet.getString("DNICOMPLETO"));

		solicitanteAux.setNombre(resultSet.getString(NOMBRE_077));
		solicitanteAux.setApellido1(resultSet.getString(APEL1_077));
		solicitanteAux.setApellido2(resultSet.getString(APEL2_077));
		gestorExpediente.setSolicitante(solicitanteAux);
		final Entidad entidadAux = new Entidad();
		entidadAux.setDescEu(resultSet.getString("ENTIDAD_DESC_EU"));
		gestorExpediente.setEntidad(entidadAux);
		expTareasResumen.setGestorExpediente(gestorExpediente);

		final SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
		subsanacionExpediente.setEstadoDescEu(resultSet.getString("ESTADOSUBSANACIONEU"));
		subsanacionExpediente.setFechaLimite(resultSet.getDate("FECHA_LIMITE_064"));
		subsanacionExpediente.setHoraLimite(resultSet.getString("HORA_FECHA_LIMITE_064"));
		subsanacionExpediente.setTipoRequerimientoDescEu(resultSet.getString("TIPOREQUERIMIENTOEU"));
		final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		bitacoraExpediente.setSubsanacionExp(subsanacionExpediente);
		expTareasResumen.setBitacoraExpediente(bitacoraExpediente);

		return expTareasResumen;
	}

	/**
	 * obtenemos la query para obetener los datos necesarios para generar ICS de
	 * expedientes
	 *
	 * @param query                    StringBuilder
	 * @param expedientesSeleccionados String
	 * @param iTipoExp                 Integer
	 */
	public static void getQueryDatosExpIcs(StringBuilder query, String expedientesSeleccionados, Integer iTipoExp) {
		List<Expediente> listaExp = new ArrayList<Expediente>();
		String[] aIds = expedientesSeleccionados.split(",");
		Expediente expediente = new Expediente();
		for (String idExpediente : aIds) {
			String[] id = idExpediente.split("-");
			expediente = new Expediente();
			expediente.setAnyo(Long.parseLong(id[0]));
			expediente.setNumExp(Integer.parseInt(id[1]));
			listaExp.add(expediente);
		}
		int count = 0;
		query.append(DaoConstants.SELECT + DaoConstants.DISTINCT);
		query.append(" t1.ANYO_051 " + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		query.append(" t1.NUM_EXP_051 " + DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		query.append(" t1.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE + DaoConstants.SIGNO_COMA);
		query.append(" t1.TITULO_051 " + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
		if (TipoExpedienteGrupoEnum.INTERPRETACION.getValue() == iTipoExp) {
			// interpretacion query
			query.append(" t2.FECHA_INI_052 " + DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
			query.append(" TO_CHAR(t2.FECHA_INI_052,'HH24:MI') " + DBConstants.HORAALTA + DaoConstants.SIGNO_COMA);
			query.append(" t2.FECHA_FIN_052 " + DBConstants.FECHAFINAL + DaoConstants.SIGNO_COMA);
			query.append(" TO_CHAR(t2.FECHA_FIN_052,'HH24:MI') " + DBConstants.HORAFINAL + DaoConstants.SIGNO_COMA);
			query.append(" t2.DIR_NORA_052 " + DBConstants.CDIRNORA);
			query.append(DaoConstants.FROM + DBConstants.TABLA_51 + " t1 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + " t2 ");
			query.append(DaoConstants.ON + " t1.ANYO_051 " + DaoConstants.SIGNOIGUAL + " t2.ANYO_052 ");
			query.append(DaoConstants.AND + " t1.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + " t2.NUM_EXP_052 ");
			query.append(DaoConstants.WHERE_1_1);
			// where
			for (Expediente exp : listaExp) {
				query.append((0 == count ? DaoConstants.AND : DaoConstants.OR) + DaoConstants.ABRIR_PARENTESIS
						+ " t1.ANYO_051 " + DaoConstants.SIGNOIGUAL + exp.getAnyo());
				query.append(DaoConstants.AND + " t1.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + exp.getNumExp()
						+ DaoConstants.CERRAR_PARENTESIS);
				count++;
			}
		} else {
			// tradRev query
			query.append(DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS + " t3.FECHA_INICIO_081,t1.FECHA_ALTA_051 "
					+ DaoConstants.CERRAR_PARENTESIS + DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
			query.append(" TO_CHAR(NVL(t3.FECHA_INICIO_081,t1.FECHA_ALTA_051),'HH24:MI') " + DBConstants.HORAALTA
					+ DaoConstants.SIGNO_COMA);
			query.append(" t2.FECHA_FINAL_IZO_053 " + DBConstants.FECHAFINAL + DaoConstants.SIGNO_COMA);
			query.append(" TO_CHAR(t2.FECHA_FINAL_IZO_053, 'HH24:MI') " + DBConstants.HORAFINAL);
			query.append(DaoConstants.FROM + DBConstants.TABLA_51 + " t1 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_53 + " t2 ");
			query.append(DaoConstants.ON + " t1.ANYO_051 " + DaoConstants.SIGNOIGUAL + " t2.ANYO_053 ");
			query.append(DaoConstants.AND + " t1.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + " t2.NUM_EXP_053 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_81 + " t3 ");
			// left join 81
			for (Expediente expAux81 : listaExp) {
				query.append((0 == count ? DaoConstants.ON : DaoConstants.OR) + DaoConstants.ABRIR_PARENTESIS
						+ " t3.ANYO_081 " + DaoConstants.SIGNOIGUAL + expAux81.getAnyo());
				query.append(DaoConstants.AND + " t3.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL + expAux81.getNumExp());
				query.append(DaoConstants.AND + " t3.FECHA_INICIO_081 " + DaoConstants.SIGNOIGUAL + DaoConstants.CASE);
				query.append(
						DaoConstants.WHEN + DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT + DaoConstants.MIN);
				query.append(DaoConstants.ABRIR_PARENTESIS + " FECHA_INICIO_081 " + DaoConstants.CERRAR_PARENTESIS);
				query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " f0 ");
				query.append(DaoConstants.WHERE + " f0.ANYO_081 " + DaoConstants.SIGNOIGUAL + expAux81.getAnyo());
				query.append(DaoConstants.AND + " f0.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL + expAux81.getNumExp());
				query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.IS_NOT_NULL + DaoConstants.THEN);
				query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT + DaoConstants.MIN);
				query.append(DaoConstants.ABRIR_PARENTESIS + " FECHA_INICIO_081 " + DaoConstants.CERRAR_PARENTESIS);
				query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " f0 ");
				query.append(DaoConstants.WHERE + " f0.ANYO_081 " + DaoConstants.SIGNOIGUAL + expAux81.getAnyo());
				query.append(DaoConstants.AND + " f0.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL + expAux81.getNumExp());
				query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.ELSE + DaoConstants.NULL + DaoConstants.END
						+ DaoConstants.CERRAR_PARENTESIS);
				count++;
			}
			// where
			count = 0;
			for (Expediente expWhere : listaExp) {
				query.append((0 == count ? DaoConstants.WHERE : DaoConstants.OR) + DaoConstants.ABRIR_PARENTESIS
						+ " t1.ANYO_051 " + DaoConstants.SIGNOIGUAL + expWhere.getAnyo());
				query.append(DaoConstants.AND + " t1.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + expWhere.getNumExp()
						+ DaoConstants.CERRAR_PARENTESIS);
				count++;
			}
		}
	}

	public void getExcelPlanifQuery(ExpTareasResumen filter, StringBuilder query, List<Object> params,
			boolean joinLote) {
		PlanificacionExpedienteUtils.getTareasGeneralJoinsInterOTradRev(query, params);
		PlanificacionExpedienteUtils.getTareasGestorJoins(query);
		PlanificacionExpedienteUtils.getTareasRecursoAsignadoJoins(query);
		if (joinLote) {
			query.append(" LEFT JOIN AA79B29S01 t29 ON t1.ID_LOTE_081 = t29.ID_LOTE_029");
		}
		// Where
		query.append(DaoConstants.WHERE);

	}

	/**
	 *
	 * @param query  StringBuilder
	 * @param params List<Object>
	 */
	private static final void getTareasGeneralJoinsInterOTradRev(StringBuilder query, List<Object> params) {
		query.append(DaoConstants.FROM).append(AA79B81S01).append(T1);
		query.append(DaoConstants.JOIN).append(AA79B15S01).append(T15);
		query.append(" ON t1.ID_TIPO_TAREA_081     = t15.ID_015 ");
		query.append(DaoConstants.JOIN).append(AA79B51S01).append(T2);
		query.append(" ON t1.ANYO_081     = t2.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_081 = t2.NUM_EXP_051 ");
		query.append(DaoConstants.LEFT_JOIN).append(AA79B52S01).append(T3);
		query.append(" ON t2.ANYO_051     = t3.ANYO_052 ");
		query.append(" AND t2.NUM_EXP_051 = t3.NUM_EXP_052 ");
		query.append(DaoConstants.LEFT_JOIN).append(AA79B53S01).append(T4);
		query.append(" ON t2.ANYO_051     = t4.ANYO_053 ");
		query.append(" AND t2.NUM_EXP_051 = t4.NUM_EXP_053 ");
		query.append(DaoConstants.JOIN).append(AA79B59S01).append(T5);
		query.append(" ON t2.ANYO_051 = t5.ANYO_059 ");
		query.append(" AND t2.NUM_EXP_051 = t5.NUM_EXP_059 ");
		query.append(" AND t2.ESTADO_BITACORA_051 = t5.ID_ESTADO_BITACORA_059 ");
	}

	public static final ExpTareasResumen tareaDesgloseRwMap(ResultSet resultSetDesglose) throws SQLException {
		final ExpTareasResumen expTareasDesgloseResumen = new ExpTareasResumen();
		expTareasDesgloseResumen.setAnyo(resultSetDesglose.getLong(ANYO_EXPEDIENTE));
		expTareasDesgloseResumen.setNumExp(resultSetDesglose.getInt(NUM_EXPEDIENTE));
		expTareasDesgloseResumen.setIdTipoExpediente(resultSetDesglose.getString(ID_TIPO_EXPEDIENTE));
		expTareasDesgloseResumen.setOrigen(resultSetDesglose.getString("ORIGEN051"));

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		EstadosExpediente estadoExp = new EstadosExpediente();
		estadoExp.setId(resultSetDesglose.getLong("IDESTADOEXP059"));
		bitacoraExpediente.setEstadoExp(estadoExp);

		FasesExpediente faseExp = new FasesExpediente();
		faseExp.setId(resultSetDesglose.getLong("IDFASEEXPEDIENTE059"));
		bitacoraExpediente.setFaseExp(faseExp);
		expTareasDesgloseResumen.setBitacoraExpediente(bitacoraExpediente);

		final Tareas tareaDesglose = new Tareas();
		final TiposTarea tiposTareaDesglose = new TiposTarea();
		tiposTareaDesglose.setId015(resultSetDesglose.getLong("IDTIPOTAREA"));
		tiposTareaDesglose.setDescEu015(resultSetDesglose.getString("TAREAEU"));
		tareaDesglose.setIdTarea(resultSetDesglose.getBigDecimal("IDTAREA"));
		tareaDesglose.setTipoTarea(tiposTareaDesglose);
		tareaDesglose.setEstadoEjecucion(resultSetDesglose.getInt("ESTADOEJECUCION"));
		tareaDesglose.setEstadoEjecucionDesc(resultSetDesglose.getString("ESTADOEJECUCIONEU"));
		tareaDesglose.setEstadoAsignado(resultSetDesglose.getInt("ESTADO_ASIGNACION_081"));
		tareaDesglose.setEstadoAsignadoDesc(resultSetDesglose.getString("ESTADOASIGNACIONEU"));
		tareaDesglose.setFechaFin(resultSetDesglose.getDate("FECHA_FIN_081"));
		tareaDesglose.setHoraFin(resultSetDesglose.getString("HORA_FECHA_FIN_081"));
		tareaDesglose.setHorasPrevistas(resultSetDesglose.getString("HORASPREVISTAS"));
		tareaDesglose.setFechaAsignacion(resultSetDesglose.getDate("FECHA_ASIGNACION_081"));
		tareaDesglose.setHoraAsignacion(resultSetDesglose.getString("HORA_FECHA_ASIGNACION_081"));
		tareaDesglose.setRecursoDisponible(resultSetDesglose.getString("ESRECURSODISPONIBLE"));
		expTareasDesgloseResumen.setTarea(tareaDesglose);

		final Gestor gestorExpedienteDesglose = new Gestor();
		final Solicitante solicitanteAuxDesglose = new Solicitante();
		solicitanteAuxDesglose.setGestorExpedientesVIP(resultSetDesglose.getString(IND_VIP_054));
		solicitanteAuxDesglose.setNombre(resultSetDesglose.getString(NOMBRE_077));
		solicitanteAuxDesglose.setApellido1(resultSetDesglose.getString(APEL1_077));
		solicitanteAuxDesglose.setApellido2(resultSetDesglose.getString(APEL2_077));
		final Entidad entidadAuxDesglose = new Entidad();
		gestorExpedienteDesglose.setEntidad(entidadAuxDesglose);
		expTareasDesgloseResumen.setGestorExpediente(gestorExpedienteDesglose);
		expTareasDesgloseResumen.setTitulo(resultSetDesglose.getString("TITULO_051"));
		Lotes lotes = new Lotes();
		lotes.setNombreLote(resultSetDesglose.getString(DBConstants.NOMBRELOTE));
		// interpretacion
		expTareasDesgloseResumen.setFechaFin(resultSetDesglose.getDate("FECHA_FINAL"));
		expTareasDesgloseResumen.setHoraFin(resultSetDesglose.getString("HORA_FINAL"));

		if (!StringUtils.isEmpty(lotes.getNombreLote())) {
			solicitanteAuxDesglose.setNombre(lotes.getNombreLote());
		}

		gestorExpedienteDesglose.setSolicitante(solicitanteAuxDesglose);
		return expTareasDesgloseResumen;
	}

	public StringBuilder whereDeAnyoNumExp(JQGridRequestDto jqGridRequestDtoReport, boolean isCount,
			List<Object> params, StringBuilder query, String[] aIds) {
		query.append(DaoConstants.ABRIR_PARENTESIS);
		for (String id : aIds) {
			Long anyo = Long.parseLong(id.substring(Constants.CERO, id.indexOf(Constants.GUION)));
			Integer numExp = Integer.parseInt(id.substring(id.indexOf(Constants.GUION) + Constants.UNO));
			query.append("( t1.ANYO_081 = ? AND t1.NUM_EXP_081 = ? )" + DaoConstants.OR);
			params.add(anyo);
			params.add(numExp);
		}

		StringBuilder updatedQuery = new StringBuilder();
		StringBuilder paginatedQuery = new StringBuilder();
		updatedQuery.append(query.replace(query.toString().lastIndexOf("OR"),
				query.toString().lastIndexOf("OR") + Constants.DOS, DaoConstants.CERRAR_PARENTESIS).toString());

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDtoReport, isCount, updatedQuery));
		return paginatedQuery;
	}

	private void whereIndicesBopv(StringBuilder where, ExpedientePlanificacion filter, List<Object> params) {
		if (filter.getIndicesBopv() != null) {
			boolean bOr = false;
			if (filter.getExpedienteTradRev() == null) {
				ExpedienteTradRev tradRev = new ExpedienteTradRev();
				filter.setExpedienteTradRev(tradRev);
			}
			if (Constants.SI.equals(filter.getIndicesBopv())) {
				filter.getExpedienteTradRev().setIndPublicarBopv(Constants.SI);
				filter.getExpedienteTradRev().setIndPrevistoBopv(Constants.SI);
				bOr = true;
			} else if (Constants.NO.equals(filter.getIndicesBopv())) {
				filter.getExpedienteTradRev().setIndPublicarBopv(Constants.NO);
				filter.getExpedienteTradRev().setIndPrevistoBopv(Constants.NO);
			}

			where.append(DaoConstants.AND);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(SqlUtils.generarWhereLikeWithoutAnd(R1IND_PUBLICAR_BOPV_053,
					filter.getExpedienteTradRev().getIndPublicarBopv(), params, false));
			if (bOr) {
				where.append(DaoConstants.OR);
			} else {
				where.append(DaoConstants.AND);
			}
			where.append(SqlUtils.generarWhereLikeWithoutAnd("r1.IND_PREVISTO_BOPV_053",
					filter.getExpedienteTradRev().getIndPrevistoBopv(), params, false));
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}
	}

	/**
	 * @param filter
	 * @param params
	 * @param esInterpretacion
	 * @param where
	 */
	public void getWherebusquedageneralContinuacion(ExpedientePlanificacion filter, List<Object> params,
			boolean esInterpretacion, StringBuilder where) {
		if (!esInterpretacion) {
			// BOPV
			whereIndicesBopv(where, filter, params);

			if (filter.getExpedienteTradRev() != null) {
				// PALABRAS IZO
				where.append(SqlUtils.generarWhereMayorIgual("r1.NUM_TOTAL_PAL_IZO_053",
						filter.getExpedienteTradRev().getNumPalIzoDesde(), params));
				where.append(SqlUtils.generarWhereMenorIgual("r1.NUM_TOTAL_PAL_IZO_053",
						filter.getExpedienteTradRev().getNumPalIzoHasta(), params));
				// FECHA ENTREGA IZO
				where.append(SqlUtils.generarWhereMayorIgualFecha(R1FECHA_FINAL_IZO_053, DDMMYY,
						filter.getExpedienteTradRev().getFechaEntregaIzoDesde(), params));
				where.append(SqlUtils.generarWhereMenorIgualFecha(R1FECHA_FINAL_IZO_053, DDMMYY,
						filter.getExpedienteTradRev().getFechaEntregaIzoHasta(), params));
			}
			// NEGOCIACION PRESUPUESTO y NEGOCIACION FECHA Y HORA
			where.append(SqlUtils.generarWhereIgual("r1.IND_PRESUPUESTO_053", filter.getRequierePresupuesto(), params));
			ExpedienteDaoUtils.generarWherePresupuestoONegociacionSi(filter.getRequierePresupuesto(), filter, where,
					params, true);

			if (StringUtils.isNotBlank(filter.getRequiereNegociacion())) {
				where.append(SqlUtils.generarWhereIgual("TIENE_NEGOCIACION_FECHA(t1.ANYO_051,t1.NUM_EXP_051)",
						filter.getRequiereNegociacion(), params));
				ExpedienteDaoUtils.generarWhereNegociacionSi(filter.getRequiereNegociacion(), filter, where, params,
						false);
			}
		} else {
			// NEGOCIACION PRESUPUESTO
			where.append(SqlUtils.generarWhereIgual(I1IND_PRESUPUESTO_052, filter.getRequierePresupuesto(), params));
			ExpedienteDaoUtils.generarWherePresupuestoONegociacionSi(filter.getRequierePresupuesto(), filter, where,
					params, true);
			where.append(
					SqlUtils.generarWhereIgual("i1.IND_PRESUPUESTO_052 ", filter.getRequiereNegociacion(), params));
			ExpedienteDaoUtils.generarWherePresupuestoONegociacionSi(filter.getRequiereNegociacion(), filter, where,
					params, false);
		}
		// TIPO TAREA
		if (filter.getFilterTarea() != null) {
			if (filter.getFilterTarea().getEstadoAceptTarea() == Constants.CERO) {
				where.append(
						" AND NOT exists (SELECT 1 FROM AA79B81T00 WHERE ANYO_081= t1.anyo_051 AND NUM_EXP_081  = t1.num_exp_051) ");
			} else {
				where.append(DaoConstants.AND);
				where.append(Constants.CERO + DaoConstants.SIGNO_MENOR_QUE + DaoConstants.ABRIR_PARENTESIS);
				where.append(DaoConstants.SELECT_COUNT);
				where.append(DaoConstants.FROM);
				where.append(DBConstants.TABLA_81 + " z1 ");
				where.append(DaoConstants.WHERE);
				where.append(" z1.ANYO_081 " + DaoConstants.SIGNOIGUAL + " t1.ANYO_051 ");
				where.append(DaoConstants.AND);
				where.append(" z1.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL + " t1.NUM_EXP_051 ");
				where.append(SqlUtils.generarWhereIgual(" z1.ID_TIPO_TAREA_081 ",
						filter.getFilterTarea().getTipoTarea(), params));
				// ESTADO ACEPTACION TAREAS PLANIFICADAS
				where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_ASIGNACION_081 ",
						filter.getFilterTarea().getEstadoAceptTarea(), params));

				// ESTADO EJECUCION TAREAS PLANIFICADAS
				if (filter.getFilterTarea().getEstadoEjecTarea() != null && EstadoEjecucionTareaEnum.RETRASADA
						.getValue() == filter.getFilterTarea().getEstadoEjecTarea()) {
					where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_EJECUCION_081 ",
							EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(), params));
					where.append(DaoConstants.AND + " z1.FECHA_FIN_081 " + DaoConstants.SIGNO_MENOR_QUE
							+ DaoConstants.SYSDATE);
				} else {
					where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_EJECUCION_081 ",
							filter.getFilterTarea().getEstadoEjecTarea(), params));
				}

				// FECHA PLANIFICACION TAREAS
				where.append(SqlUtils.generarWhereMayorIgualFecha("z1.FECHA_ASIGNACION_081", DDMMYY,
						filter.getFilterTarea().getFechaDesdePlanifTarea(), params));
				where.append(SqlUtils.generarWhereMenorIgualFecha("z1.FECHA_ASIGNACION_081", DDMMYY,
						filter.getFilterTarea().getFechaHastaPlanifTarea(), params));
				// PERSONAL IZO ASIGNADO A TAREA
				where.append(SqlUtils.generarWhereLike(" z1.DNI_RECURSO_081",
						filter.getFilterTarea().getPersIzoAsignTarea(), params, false));
				// LOTE ASIGNADO A TAREA
				where.append(SqlUtils.generarWhereIgual(" z1.ID_LOTE_081",
						filter.getFilterTarea().getIdLoteAsignTarea(), params));
				where.append(DaoConstants.CERRAR_PARENTESIS);
			}
		}
	}

	public StringBuilder getSelectConsultaPlanificacionExpediente(StringBuilder select, boolean isCount,
			boolean esInterpretacion) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		select.append("SELECT ");

		if (isCount) {
			select.append(" COUNT(DISTINCT t1.NUM_EXP_051) ");
		} else {
			select.append(" DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
			select.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
			select.append(", SUBSTR(t1.ANYO_051,2,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') "
					+ DBConstants.ANYONUMEXPCONCATENADO);
			select.append(", t1.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE);
			select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
					+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
					.append(this.messageSource.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.messageSource.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.messageSource.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
					.append("') AS " + DBConstants.TIPOEXPEDIENTEDESCES);
			select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
					+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
					.append(this.messageSource.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.messageSource.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.messageSource.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
					.append("') AS " + DBConstants.TIPOEXPEDIENTEDESCEU);
			select.append(", b1." + DBConstants.ID_ESTADO_EXP_059 + " " + DBConstants.IDESTADOEXP);
			// ESTADOEXPEDIENTEDESCEU
			select.append(", e1." + DBConstants.DESC_EU_060 + " " + DBConstants.ESTADOEXPEDIENTEDESCEU);
			// ESTADOEXPEDIENTEDESCES
			select.append(", e1." + DBConstants.DESC_ES_060 + " " + DBConstants.ESTADOEXPEDIENTEDESCES);
			// ESTADOEXPEDIENTEDESCABREU
			select.append(", e1." + DBConstants.DESC_ABR_EU_060 + " " + DBConstants.ESTADOEXPEDIENTEDESCABREU);
			// ESTADOEXPEDIENTEDESCABRES
			select.append(", e1." + DBConstants.DESC_ABR_ES_060 + " " + DBConstants.ESTADOEXPEDIENTEDESCABRES);
			select.append(", b1." + DBConstants.ID_FASE_EXPEDIENTE_059 + " " + DBConstants.IDFASEEXPEDIENTE);
			select.append(", t1.TITULO_051 ").append(DBConstants.TITULO);
			select.append(", t1.FECHA_ALTA_051 ").append(DBConstants.FECHAALTA);
			select.append(", TO_CHAR(t1.FECHA_ALTA_051,'HH24:MI') " + DBConstants.HORAALTA);
			if (!esInterpretacion) {
				select.append(", NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053) " + DBConstants.FECHAFINALIZO);
				select.append(", TO_CHAR(NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053),'HH24:MI') "
						+ DBConstants.HORAFINALIZO);
				select.append(", NVL(r1.NUM_TOTAL_PAL_IZO_053, NVL(r1.NUM_TOTAL_PAL_SOLIC_053,0)) "
						+ DBConstants.NUMTOTALPALIZO);
				select.append(", n1.NUM_TOTAL_PAL_090 " + DBConstants.NUMTOTALPALTRADOS);
				select.append(", n1.NUM_PAL_CONCOR_0_84_090 " + DBConstants.NUMPALCONCOR084090);
				select.append(", n1.NUM_PAL_CONCOR_85_94_090 " + DBConstants.NUMPALCONCOR8594090);
				select.append(", n1.NUM_PAL_CONCOR_95_100_090 " + DBConstants.NUMPALCONCOR95100090);
				select.append(", n1.NUM_PAL_CONCOR_95_99_090 " + DBConstants.NUMPALCONCOR9599090);
				select.append(", n1.NUM_PAL_CONCOR_100_090 " + DBConstants.NUMPALCONCOR100090);
				select.append(", " + DaoConstants.NULL + DBConstants.FECHAINICIOPREVISTA);
				select.append(", " + DaoConstants.NULL + DBConstants.FECHAFINPREVISTA);
				select.append(
						", NVL(n1.NUM_TOTAL_PAL_090,NVL(r1.NUM_TOTAL_PAL_IZO_053,NVL(r1.NUM_TOTAL_PAL_SOLIC_053,0))) AS NUMPALCOLORDER ");
				// FECHA ENTREGA REAL
				select.append(", r1.").append(DBConstants.FECHA_ENTREGA_REAL_053).append(" ")
						.append(DBConstants.FECHAENTREGAREAL);
				// HORA ENTREGA REAL
				select.append(", TO_CHAR( r1.").append(DBConstants.FECHA_ENTREGA_REAL_053).append(",")
						.append(DaoConstants.FORMATO_HORA).append(") ").append(DBConstants.HORAENTREGAREAL);
			} else {
				select.append(", " + DaoConstants.NULL + DBConstants.FECHAFINALIZO);
				select.append(", " + DaoConstants.NULL + DBConstants.HORAFINALIZO);
				select.append(", " + DaoConstants.NULL + DBConstants.NUMTOTALPALIZO);
				select.append(", -1 NUMPALCOLORDER");
				select.append(", i1.FECHA_INI_052 " + DBConstants.FECHAINICIOPREVISTA);
				select.append(", TO_CHAR(i1.FECHA_INI_052,'HH24:MI') " + DBConstants.HORAINICIOPREVISTA);
				select.append(", i1.FECHA_FIN_052 " + DBConstants.FECHAFINPREVISTA);
				select.append(", TO_CHAR(i1.FECHA_FIN_052,'HH24:MI') " + DBConstants.HORAFINPREVISTA);
			}
		}

		this.getFromConsultaPlanificacionExpediente(select, esInterpretacion);
		return select;
	}

	public void getFromConsultaPlanificacionExpediente(StringBuilder select, boolean esInterpretacion) {
		select.append(" FROM AA79B51S01 t1 ");

		if (!esInterpretacion) {
			select.append("JOIN AA79B53S01 r1 ");
			select.append("ON t1.ANYO_051 = r1.ANYO_053 ");
			select.append("AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");
		} else {
			select.append("JOIN AA79B52S01 i1 ON ");
			select.append("t1.ANYO_051 = i1.ANYO_052 ");
			select.append("AND t1.NUM_EXP_051 = i1.NUM_EXP_052 ");
		}

		select.append("JOIN AA79B54S01 g1 ");
		select.append("ON t1.ANYO_051     = g1.ANYO_054 ");
		select.append("AND t1.NUM_EXP_051 = g1.NUM_EXP_054 ");
		select.append("JOIN AA79B59S01 b1 ");
		select.append("ON t1.ANYO_051 = b1.ANYO_059 ");
		select.append("AND t1.NUM_EXP_051 = b1.NUM_EXP_059 ");
		select.append("AND t1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 ");
		// 60 DESC ESTADO EXPEDIENTE
		// 61 DESC FASE EXPEDIENTE
		select.append("JOIN AA79B60S01 e1 ");
		select.append("ON b1.ID_ESTADO_EXP_059 = e1.ID_060 ");
		select.append("JOIN AA79B61S01 f1 ");
		select.append("ON b1.ID_FASE_EXPEDIENTE_059 = f1.ID_061 ");
		select.append("LEFT JOIN AA79B81S01 d1 ");
		select.append("ON t1.ANYO_051     = d1.ANYO_081 ");
		select.append("AND t1.NUM_EXP_051 = d1.NUM_EXP_081 ");
		select.append("AND d1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue() + " ");

		select.append("LEFT JOIN AA79B81S01 v1 ");
		select.append("ON t1.ANYO_051     = v1.ANYO_081 ");
		select.append("AND t1.NUM_EXP_051 = v1.NUM_EXP_081 ");
		select.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
		select.append(" v1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue());
		select.append(DaoConstants.OR);
		select.append(" v1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue());
		select.append(DaoConstants.OR);
		select.append(
				" v1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue());
		select.append(DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.AND + " v1.IND_NO_CONFORMIDAD_081 = " + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.NO.getValue() + DaoConstants.SIGNO_APOSTROFO);

		select.append("LEFT JOIN AA79B82S01 d2 ");
		select.append("ON d2.ID_TAREA_082 = d1.ID_TAREA_081 ");

		select.append("LEFT JOIN AA79B90S01 n1 ");
		select.append("ON d1.ID_TAREA_081  = n1.ID_TAREA_090 ");
		select.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_62 + DaoConstants.BLANK + DaoConstants.T14_MINUSCULA);
		select.append(DaoConstants.ON + DaoConstants.T14_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_062);
		select.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		select.append(
				DaoConstants.AND + DaoConstants.T14_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_062);
		select.append(DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_051);
		select.append(DaoConstants.WHERE_1_1);
	}

	public String getWhereConsultaPlanificacionExpediente(ExpedientePlanificacion filter, List<Object> params,
			boolean startsWith, boolean esInterpretacion) {
		// FILTROS BUSCADOR
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		// NUM EXPEDIENTE
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));

		if (null != filter.getIdTipoExpediente()) {
			if (filter.getIdTipoExpediente().equals(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode())) {
				List<String> listaExpedientes = new ArrayList<String>();
				listaExpedientes.add(TipoExpedienteEnum.REVISION.getValue());
				listaExpedientes.add(TipoExpedienteEnum.TRADUCCION.getValue());
				where.append(SqlUtils.generarWhereIn(ID_TIPO_EXPEDIENTE, listaExpedientes, params));
			} else {
				where.append(SqlUtils.generarWhereIgual(ID_TIPO_EXPEDIENTE, filter.getIdTipoExpediente(), params));
			}
		}

		// FASE EXPEDIENTE
		if (filter != null) {
			ConsultaExpedienteUtils.filtroEstado(where, filter.getBitacoraExpediente(), params, "b1", "t1");
		}
		// ASIGNADOR
		if (filter.getAsignador() != null) {
			where.append(
					SqlUtils.generarWhereLike("t1.DNI_ASIGNADOR_051", filter.getAsignador().getDni(), params, false));
		}

		this.getFiltrosTareasWherePlanifConsulta(filter, params, where);

		if (StringUtils.isNotBlank(filter.getIdsMetadatosBusqueda())) {
			getWhereConsPlanifMetadatos(filter, where);
		}

		if (filter.getTipoDocumento() != null) {
			where.append(" AND ( SELECT COUNT(1) FROM AA79B56S01 t56 WHERE t56.ANYO_056 = t1.ANYO_051 ");
			where.append(" AND t56.NUM_EXP_056 = t1.NUM_EXP_051 AND t56.TIPO_DOCUMENTO_056 = ?) > 0 ");
			params.add(filter.getTipoDocumento());
		}

		return where.toString();
	}

	public void getFiltrosTareasWherePlanifConsulta(ExpedientePlanificacion filter, List<Object> params,
			StringBuilder where) {
		if (tieneFiltrosTarea(filter.getFilterTarea()) || tieneGestor(filter)) {
			where.append(DaoConstants.AND);
			where.append(Constants.CERO + DaoConstants.SIGNO_MENOR_QUE + DaoConstants.ABRIR_PARENTESIS);
			where.append(DaoConstants.SELECT_COUNT);
			where.append(DaoConstants.FROM);
			where.append(DBConstants.TABLA_81 + " z1 ");
			if (filter.getFilterTarea().getFechaDesdeRealEjecucion() != null
					|| filter.getFilterTarea().getFechaHastaRealEjecucion() != null) {
				// FECHA EJECUCION TAREA
				where.append(" JOIN AA79B82S01 z2 ");
				where.append(" ON z1.ID_TAREA_081 = z2.ID_TAREA_082 ");
				where.append(" WHERE 1 = 1 ");
				where.append(SqlUtils.generarWhereMayorIgualFecha("z2.FECHA_EJECUCION_082", DDMMYY,
						filter.getFilterTarea().getFechaDesdeRealEjecucion(), params));
				where.append(SqlUtils.generarWhereMenorIgualFecha("z2.FECHA_EJECUCION_082", DDMMYY,
						filter.getFilterTarea().getFechaHastaRealEjecucion(), params));
			} else {
				where.append(" WHERE 1 = 1 ");
			}

			where.append(" AND z1.ANYO_081 " + DaoConstants.SIGNOIGUAL + " t1.ANYO_051 ");
			where.append(DaoConstants.AND);
			where.append(" z1.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL + " t1.NUM_EXP_051 ");
			// TIPO TAREA
			where.append(SqlUtils.generarWhereIgual(" z1.ID_TIPO_TAREA_081 ", filter.getFilterTarea().getTipoTarea(),
					params));
			// ESTADO ACEPTACION TAREAS PLANIFICADAS
			where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_ASIGNACION_081 ",
					filter.getFilterTarea().getEstadoAceptTarea(), params));
			// ESTADO EJECUCION TAREAS PLANIFICADAS
			if (filter.getFilterTarea().getEstadoEjecTarea() != null
					&& EstadoEjecucionTareaEnum.RETRASADA.getValue() == filter.getFilterTarea().getEstadoEjecTarea()) {
				where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_EJECUCION_081 ",
						EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(), params));
				where.append(
						DaoConstants.AND + " z1.FECHA_FIN_081 " + DaoConstants.SIGNO_MENOR_QUE + DaoConstants.SYSDATE);
			} else {
				where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_EJECUCION_081 ",
						filter.getFilterTarea().getEstadoEjecTarea(), params));
			}

			// TIPO RECURSO ASIGNADA A TAREA
			where.append(SqlUtils.generarWhereIgual(" z1.RECURSO_ASIGNACION_081",
					filter.getFilterTarea().getRecursoAsignacion(), params));
			// ENTIDAD DE LOTE ASIGNADA A TAREA
			if (filter.getGestorExpediente() != null) {
				where.append(" AND 0 < (SELECT COUNT(1) ");
				where.append(" FROM AA79B29S01 k1 ");
				where.append(" WHERE z1.ID_LOTE_081 = k1.ID_LOTE_029 ");
				where.append(SqlUtils.generarWhereLike("k1.TIPO_ENTIDAD_029",
						filter.getGestorExpediente().getEntidad().getTipo(), params, false));
				where.append(SqlUtils.generarWhereIgual("k1.ID_EMPRESA_PROV_029",
						filter.getGestorExpediente().getEntidad().getCodigo(), params));
				where.append(" )");
			}
			// FECHA ASIGNACION TAREA
			where.append(SqlUtils.generarWhereMayorIgualFecha("z1.FECHA_ASIGNACION_081", DDMMYY,
					filter.getFilterTarea().getFechaAsignacionDesde(), params));
			where.append(SqlUtils.generarWhereMenorIgualFecha("z1.FECHA_ASIGNACION_081", DDMMYY,
					filter.getFilterTarea().getFechaAsignacionHasta(), params));
			// FECHA FIN TAREA
			where.append(SqlUtils.generarWhereMayorIgualFecha("z1.FECHA_FIN_081", DDMMYY,
					filter.getFilterTarea().getFechaDesdePrevistaEjecucion(), params));
			where.append(SqlUtils.generarWhereMenorIgualFecha("z1.FECHA_FIN_081", DDMMYY,
					filter.getFilterTarea().getFechaHastaPrevistaEjecucion(), params));

			// DNI PERSONA ASIGNADA A TAREA
			where.append(SqlUtils.generarWhereLike(" z1.DNI_RECURSO_081",
					filter.getFilterTarea().getPersIzoAsignTarea(), params, false));
			// LOTE ASIGNADO A TAREA
			where.append(SqlUtils.generarWhereIgual(" z1.ID_LOTE_081", filter.getFilterTarea().getIdLoteAsignTarea(),
					params));
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}
	}

	public boolean tieneFiltrosTarea(FilterTarea filtroTarea) {
		boolean tieneFiltrosTarea = false;
		if (filtroTarea != null) {
			if (filtroTarea.getTipoTarea() != null || filtroTarea.getEstadoAceptTarea() != null
					|| filtroTarea.getEstadoEjecTarea() != null) {
				tieneFiltrosTarea = true;
			}
			if (filtroTarea.getPersIzoAsignTarea() != null || filtroTarea.getIdLoteAsignTarea() != null
					|| filtroTarea.getRecursoAsignacion() != null) {
				tieneFiltrosTarea = true;
			}
			if (tieneFiltrosFecha(filtroTarea)) {
				tieneFiltrosTarea = true;
			}
		}

		return tieneFiltrosTarea;
	}

	private boolean tieneGestor(ExpedientePlanificacion filtro) {
		return filtro.getGestorExpediente() != null && filtro.getGestorExpediente().getEntidad() != null
				&& (filtro.getGestorExpediente().getEntidad().getTipo() != null
						|| filtro.getGestorExpediente().getEntidad().getCodigo() != null);
	}

	public boolean tieneFiltrosFecha(FilterTarea filtroTarea) {
		boolean tieneFiltrosFecha = false;
		if (filtroTarea.getFechaDesdeRealEjecucion() != null || filtroTarea.getFechaHastaRealEjecucion() != null
				|| filtroTarea.getFechaAsignacionDesde() != null || filtroTarea.getFechaAsignacionHasta() != null) {
			tieneFiltrosFecha = true;
		}
		if (filtroTarea.getFechaDesdePrevistaEjecucion() != null
				|| filtroTarea.getFechaDesdePrevistaEjecucion() != null) {
			tieneFiltrosFecha = true;
		}
		return tieneFiltrosFecha;
	}

	/**
	 * @param filter
	 * @param where
	 */
	private void getWhereConsPlanifMetadatos(ExpedientePlanificacion filter, StringBuilder where) {
		final String[] idsMetadatosBusqueda = filter.getIdsMetadatosBusqueda().split(Constants.COMA);
		where.append(DaoConstants.AND + DaoConstants.T14_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_METADATO_062 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
		for (int i = Constants.CERO; i < idsMetadatosBusqueda.length; i++) {
			where.append(idsMetadatosBusqueda[i]);
			if (i != idsMetadatosBusqueda.length - 1) {
				where.append(DaoConstants.SIGNO_COMA);
			}
		}
		where.append(DaoConstants.CERRAR_PARENTESIS);
	}

	public String getWhereConsultaPlanificacionExpedienteIds(ExpedientePlanificacion filterExpedientePlanificacion,
			List<Object> params, boolean esInterpretacion, JQGridRequestDto tableData) {
		StringBuilder whereCPEI = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ANYO_051");
		camposIds.add("t1.NUM_EXP_051");
		whereCPEI.append(this.getWhereConsultaPlanificacionExpediente(filterExpedientePlanificacion, params, false,
				esInterpretacion));
		whereCPEI.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
				tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));
		return whereCPEI.toString();
	}

	public String dashboardTableReorderQueryAux(StringBuilder query, String tipoFiltro) {
		StringBuilder queryAux = new StringBuilder();
		if (PlanificacionExpedienteUtils.EXPEDIENTES_FILTRO.equals(tipoFiltro)
				|| PlanificacionExpedienteUtils.EXPEDIENTES_SIN_FILTRO.equals(tipoFiltro)) {
			// Añado a la select el ES_EXPEDIENTE_URGENTE
			// ind urgente
			queryAux.append(", ES_EXPEDIENTE_URGENTE(t1.ANYO_051,t1.NUM_EXP_051, t1.ID_TIPO_EXPEDIENTE_051,"
					+ " t3.NUM_TOTAL_PAL_IZO_053, t3.FECHA_FINAL_IZO_053, g1.IND_VIP_054) " + DBConstants.ESURGENTE);
			// GESTORCOLORDEREU
			queryAux.append("," + SqlUtils.normalizarCampoSql("n1.DESC_EU") + " || "
					+ SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
					+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORCOLORDEREU");
			// GESTORCOLORDERES
			queryAux.append("," + SqlUtils.normalizarCampoSql("n1.DESC_ES") + " || "
					+ SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
					+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORCOLORDERES");

		} else if (PlanificacionExpedienteUtils.TAREAS_FILTRO.equals(tipoFiltro)) {
			queryAux.append(
					" ,NVL(	TRIM(t29.NOMBRE_LOTE_029),TRIM(h1.APEL1_077) || ' ' || TRIM(h1.APEL2_077) || ' ' || TRIM(h1.NOMBRE_077)) NOMBRECOMPLETO ");
			queryAux.append(" , COALESCE(PARSE_HORA_MINUTOS(t1.HORAS_PREVISTAS_081),0) HORASPREVISTASNUMBER ");
		} else {
			// GESTORCOLORDEREU
			queryAux.append("," + SqlUtils.normalizarCampoSql("n1.DESC_EU") + " || "
					+ SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
					+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORCOLORDEREU");
			// GESTORCOLORDERES
			queryAux.append("," + SqlUtils.normalizarCampoSql("n1.DESC_ES") + " || "
					+ SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
					+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORCOLORDERES");
		}
		return queryAux.toString();
	}

}
