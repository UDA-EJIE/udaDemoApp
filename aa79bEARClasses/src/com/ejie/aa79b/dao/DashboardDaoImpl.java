package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.dao.mapper.ResumenGraficas3BarrasRowMapper;
import com.ejie.aa79b.dao.mapper.ResumenGraficasRowMapper;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.enums.DashboardCargaTareasEnum;
import com.ejie.aa79b.model.enums.DashboardPlanifExpedientesEnum;
import com.ejie.aa79b.model.enums.DashboardPlanifTareasEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.utils.PlanificacionExpedienteUtils;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * DashboardDaoImpl
 *
 * @author javarona
 */

@Repository
@Transactional
public class DashboardDaoImpl implements DashboardDao {

	private JdbcTemplate jdbcTemplate;

	protected static final String T4_ID_ESTADO_EXP_059 = " t4.id_estado_exp_059 ";
	protected static final String AS_TOTAL_COMA = " AS TOTAL,";

	protected static final String FROM_81 = " FROM AA79B81S01 T1 ";
	protected static final String JOIN_15 = " JOIN AA79B15S01 T15 ON T1.ID_TIPO_TAREA_081 = T15.ID_015 ";
	protected static final String JOIN_51 = " JOIN AA79B51S01 T2 ON T1.ANYO_081 = T2.ANYO_051 AND T1.NUM_EXP_081 = T2.NUM_EXP_051 ";
	protected static final String LEFT_JOIN_52 = " LEFT JOIN AA79B52S01 T3 ON T2.ANYO_051 = T3.ANYO_052 AND T2.NUM_EXP_051 = T3.NUM_EXP_052 ";
	protected static final String LEFT_JOIN_53 = " LEFT JOIN AA79B53S01 T4 ON T2.ANYO_051 = T4.ANYO_053 AND T2.NUM_EXP_051 = T4.NUM_EXP_053 ";
	protected static final String JOIN_59_EN_CURSO_Y_CERRADO = " JOIN AA79B59S01 T5 ON T2.ANYO_051 = T5.ANYO_059 AND T2.NUM_EXP_051 = T5.NUM_EXP_059 AND T2.ESTADO_BITACORA_051 = T5.ID_ESTADO_BITACORA_059 AND T5.ID_ESTADO_EXP_059 IN  ("
			+ (EstadoExpedienteEnum.EN_CURSO.getValue() + "," + EstadoExpedienteEnum.CERRADO.getValue() + " )");

	private static final String[] CARGA_ORDER_BY_WHITE_LIST = new String[] { "ID_FASE_EXPEDIENTE_059", "FECHA_FIN_052",
			"HORA_FIN_052", "IND_VIP_054", "IND_PUBLICAR_BOPV_053", "ID_REQUERIMIENTO_059", "FECHA_LIMITE_064",
			ExpedienteDaoImpl.ANYO_EXPEDIENTE, ExpedienteDaoImpl.NUM_EXPEDIENTE, ExpedienteDaoImpl.ID_TIPO_EXPEDIENTE,
			ExpedienteDaoImpl.TIPO_EXPEDIENTE_DESC_ES, ExpedienteDaoImpl.TIPO_EXPEDIENTE_DESC_EU,
			ExpedienteDaoImpl.ORIGEN_EXPEDIENTE, ExpedienteDaoImpl.FECHA_ALTA_EXPEDIENTE,
			ExpedienteDaoImpl.HORA_ALTA_EXPEDIENTE, ExpedienteDaoImpl.TITULO_EXPEDIENTE,
			ExpedienteDaoImpl.ESTADO_BITACORA_EXPEDIENTE, ExpedienteDaoImpl.DNI_TECNICO_EXPEDIENTE,
			DBConstants.ANYONUMEXPCONCATENADO, "NUM_TOTAL_PAL_IZO_053", "FECHA_FINAL_IZO_053", "HORA_FINAL_IZO_053",
			"IND_PREVISTO_BOPV_053", "TIPOEXPES", "TIPOEXPEU", "IND_SUBSANADO_064", "SUBSANACIONDESCES",
			"SUBSANACIONDESCEU", "IND_PRESUPUESTO_052", "PRESUPUESTODESCES", "PRESUPUESTODESCEU", "PUBLICARBOPVDESCES",
			"PUBLICARBOPVDESCEU", "GESTOREXPEDIENTESVIPDESCES", "GESTOREXPEDIENTESVIPDESCEU", "ESTADOEXPEDIENTEDESCES",
			"ESTADOEXPEDIENTEDESCEU", "ESTADOEXPEDIENTEDESCABRES", "ESTADOEXPEDIENTEDESCABREU", "FASEEXPEDIENTEDESCES",
			"FASEEXPEDIENTEDESCEU", "FASEEXPEDIENTEDESCABRES", "FASEEXPEDIENTEDESCABREU", "DNI_SOLICITANTE_054",
			"FASEEXPEDIENTEDESCABRNORMES", "FASEEXPEDIENTEDESCABRNORMEU", DBConstants.DNICOMPLETO, "FECHA_INI_052",
			"HORA_INI_052", "FECHA_FINAL_SOLIC_053", "FECHA_FINAL_PROP_053", DBConstants.PRIORITARIO,
			DBConstants.IDTIPOEXPEDIENTE, DBConstants.NOMBREGESTOR, DBConstants.FECHAFINALIZO,
			DBConstants.NUMTOTALPALIZO, DBConstants.RESPONSABLE, DBConstants.FECHAINICIOPREVISTA,
			DBConstants.FECHAFINPREVISTA, DBConstants.INDPRESUPUESTO, DBConstants.NUMTOTALPALTRADOS,
			DBConstants.IDFASEEXPEDIENTE, DBConstants.IDESTADOTAREASASIGNADAS, DBConstants.NOMBREASIGNADOR,
			DBConstants.ORDEN, DBConstants.REQUIERETRADOS, "TAREAEU", "FECHA_ASIGNACION_081", "ESTADO_ASIGNACION_081",
			"ESTADOEJECUCIONEU", "NOMBRECOMPLETO", "HORASPREVISTASNUMBER", "FECHA_FIN_081", "FECHA_FINAL" };

	/**
	 * @return JdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * Method use to set the datasource.
	 *
	 * @param dataSource
	 *            DataSource
	 * @return
	 */
	@Resource
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected String[] getCargaOrderBy() {
		return DashboardDaoImpl.CARGA_ORDER_BY_WHITE_LIST;
	}

	private RowMapper<ResumenGraficas> rwMapResumenGraficas = new ResumenGraficasRowMapper();
	private RowMapper<ResumenGraficas> rwMapResumenGraficas3Barras = new ResumenGraficas3BarrasRowMapper();

	private RowMapper<ExpTareasResumen> rwMapTareas = new RowMapper<ExpTareasResumen>() {
		@Override
		public ExpTareasResumen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return PlanificacionExpedienteUtils.tareaRwMap(resultSet);
		}
	};

	@Override
	public ResumenGraficas getEstudioExpedChartsData(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.SELECT_COUNT + AS_TOTAL_COMA).append(DaoConstants.NVL)
				.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS
						+ DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append("t1.DNI_TECNICO_051")
				.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNOINTERROGACION + DaoConstants.SIGNO_COMA
						+ Constants.UNO + DaoConstants.SIGNO_COMA + Constants.CERO)
				.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA);
		query.append(Constants.CERO + DBConstants.AS_MISEXP);

		query.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA
				+ DaoConstants.BLANK);

		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA
				+ DaoConstants.ON + DBConstants.T1ANYO_EXP_051 + DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_052 + DaoConstants.AND + DBConstants.T1NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_052);

		query.append(" LEFT JOIN AA79B53T00 t3	ON t1.ANYO_051 = t3.ANYO_053 AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		query.append(
				" JOIN AA79B59S01 t4 ON t1.ANYO_051 = t4.ANYO_059 AND t1.NUM_EXP_051 = t4.NUM_EXP_059 AND t1.ESTADO_BITACORA_051 = t4.ID_ESTADO_BITACORA_059 ");

		if (tipoConsulta < Constants.TRES) {
			query.append(DaoConstants.AND).append(T4_ID_ESTADO_EXP_059).append(DaoConstants.SIGNOIGUAL)
					.append(EstadoExpedienteEnum.EN_ESTUDIO.getValue());
			if (tipoConsulta == Constants.DOS) {
				query.append(" AND t4.ID_FASE_EXPEDIENTE_059 = 4 ");
			}
			query.append(" WHERE ID_TIPO_EXPEDIENTE_051 != 'I' ");
		} else {

			query.append(
					" JOIN AA79B64T00 t5 ON ( SELECT MAX(ID_064) FROM AA79B64T00 JOIN AA79B59T00 ON ID_REQUERIMIENTO_059 = ID_064 WHERE NUM_EXP_059 = T1.NUM_EXP_051 AND ANYO_059 = T1.ANYO_051 ) = t5.ID_064 ");
			query.append(" WHERE ");
			query.append(
					" ( ( ( T4.ID_ESTADO_EXP_059 = 2 AND T4.ID_FASE_EXPEDIENTE_059 = 4) AND ( IND_SUBSANADO_064 = 'N' AND ESTADO_SUBSANACION_064 = 1 AND FECHA_LIMITE_064 < SYSDATE) ) ");
			query.append(
					" OR ( ( T4.ID_ESTADO_EXP_059 = 3 AND T4.ID_FASE_EXPEDIENTE_059 = 8 ) AND ( ( IND_SUBSANADO_064 = 'N' AND ESTADO_SUBSANACION_064 = 1 AND FECHA_LIMITE_064 < SYSDATE ) OR ( IND_SUBSANADO_064 = 'S' AND ESTADO_SUBSANACION_064 = 3)))");
			query.append(
					" OR ( T4.ID_ESTADO_EXP_059 = 3 AND   TIPO_REQUERIMIENTO_064 = 3 AND   ESTADO_SUBSANACION_064 = 3 )) ");
		}
		query.append(" AND T1.ESTADO_BAJA_051 = 'A' ");

		params.add(dni);

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas, params.toArray());
	}

	@Override
	public ResumenGraficas getPlanificacionExpedChartsData(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.SELECT_COUNT + AS_TOTAL_COMA).append(DaoConstants.NVL)
				.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS
						+ DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append("t1.DNI_ASIGNADOR_051")
				.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNOINTERROGACION + DaoConstants.SIGNO_COMA
						+ Constants.UNO + DaoConstants.SIGNO_COMA + Constants.CERO)
				.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA);
		query.append(Constants.CERO + DBConstants.AS_MISEXP);

		query.append(DaoConstants.SIGNO_COMA + DaoConstants.NVL).append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SUM
				+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append("tg2" + DaoConstants.SIGNO_PUNTO + DBConstants.DNI_RESPONSABLE_026)
				.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNOINTERROGACION + DaoConstants.SIGNO_COMA
						+ Constants.UNO + DaoConstants.SIGNO_COMA + Constants.CERO)
				.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA);
		query.append(Constants.CERO + DBConstants.AS_MISGRUPOS);

		// Comun con getEstudioExpedChartsData()

		query.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA
				+ DaoConstants.BLANK);
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA
				+ DaoConstants.ON + DBConstants.T1ANYO_EXP_051 + DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_052 + DaoConstants.AND + DBConstants.T1NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_052);

		query.append(" LEFT JOIN AA79B53T00 t3	ON t1.ANYO_051 = t3.ANYO_053 AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		query.append(
				" JOIN AA79B59S01 t4 ON t1.ANYO_051 = t4.ANYO_059 AND t1.NUM_EXP_051 = t4.NUM_EXP_059 AND t1.ESTADO_BITACORA_051 = t4.ID_ESTADO_BITACORA_059 ");

		params.add(dni);
		params.add(dni);
		// FIN Comun con getEstudioExpedChartsData()

		if (tipoConsulta == DashboardPlanifExpedientesEnum.PENDIENTES_ENTREGA.getId()) {
			// Pdtes entregar que se tenían q haber entregado
			query.append(DaoConstants.AND).append(T4_ID_ESTADO_EXP_059).append(DaoConstants.SIGNOIGUAL)
					.append(EstadoExpedienteEnum.EN_CURSO.getValue());
			query.append(this.getJoinMisGrupos());
			query.append(DaoConstants.WHERE
					+ " TO_CHAR(T2.FECHA_FIN_052,'yyyy/MM/dd hh24:mi:ss') <= TO_CHAR(SYSDATE,'yyyy/MM/dd hh24:mi:ss') ");
			query.append(
					" OR TO_CHAR(T3.FECHA_FINAL_IZO_053,'yyyy/MM/dd hh24:mi:ss') <= TO_CHAR(SYSDATE,'yyyy/MM/dd hh24:mi:ss') ");
		} else if (tipoConsulta == DashboardPlanifExpedientesEnum.HOY.getId()) {

			query.append(DaoConstants.AND).append(T4_ID_ESTADO_EXP_059).append(DaoConstants.SIGNOIGUAL)
					.append(EstadoExpedienteEnum.EN_CURSO.getValue());
			query.append(this.getJoinMisGrupos());
			query.append(
					DaoConstants.WHERE + " TO_CHAR(T2.FECHA_FIN_052,'yyyy/MM/dd') = TO_CHAR(SYSDATE,'yyyy/MM/dd') ");
			query.append(" OR TO_CHAR(T3.FECHA_FINAL_IZO_053,'yyyy/MM/dd') = TO_CHAR(SYSDATE,'yyyy/MM/dd') ");

		} else if (tipoConsulta == DashboardPlanifExpedientesEnum.SIETE_DIAS.getId()) {
			query.append(DaoConstants.AND).append(T4_ID_ESTADO_EXP_059).append(DaoConstants.SIGNOIGUAL)
					.append(EstadoExpedienteEnum.EN_CURSO.getValue());
			query.append(this.getJoinMisGrupos());
			query.append(DaoConstants.WHERE
					+ " ( TO_DATE(TO_CHAR(T2.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" AND TO_DATE(TO_CHAR(T2.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(SYSDATE + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(
					" OR (TO_DATE(TO_CHAR(T3.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" AND TO_DATE(TO_CHAR(T3.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(SYSDATE + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
		} else if (tipoConsulta == DashboardPlanifExpedientesEnum.SIN_PLANIFICAR.getId()) {
			query.append(DaoConstants.AND).append(T4_ID_ESTADO_EXP_059).append(DaoConstants.SIGNOIGUAL)
					.append(EstadoExpedienteEnum.EN_CURSO.getValue());

			query.append(" AND ( ");
			query.append(
					" ( 0 < ( SELECT COUNT(1) FROM AA79B81T00 T5 WHERE T1.ANYO_051 = T5.ANYO_081 AND T1.NUM_EXP_051 = T5.NUM_EXP_081 AND ( T5.ESTADO_ASIGNACION_081 = ? OR T5.ESTADO_ASIGNACION_081 = ?)) ) ");
			query.append(
					" OR ( 0 = ( SELECT COUNT(1) FROM AA79B81T00 T5 WHERE T1.ANYO_051 = T5.ANYO_081 AND T1.NUM_EXP_051 = T5.NUM_EXP_081)) ");
			query.append(" ) ");
			params.add(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());

			query.append(this.getJoinMisGrupos());

		}

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas3Barras,
				params.toArray());
	}

	private StringBuilder getJoinMisGrupos() {
		final StringBuilder joinGrupos = new StringBuilder();
		joinGrupos
				.append(" LEFT JOIN AA79B26S01 tg2 ON tg2.ID_026 = OBTENER_GRUPO_TRABAJO(t1.ANYO_051,t1.NUM_EXP_051)");

		return joinGrupos;
	}

	@Override
	public ResumenGraficas getPlanificacionTareasChartsData(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.SELECT_COUNT + AS_TOTAL_COMA).append(DaoConstants.NVL)
				.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS
						+ DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append("t2.DNI_ASIGNADOR_051")
				.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNOINTERROGACION + DaoConstants.SIGNO_COMA
						+ Constants.UNO + DaoConstants.SIGNO_COMA + Constants.CERO)
				.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA);
		query.append(Constants.CERO + DBConstants.AS_MISEXP);

		// Comun con getEstudioExpedChartsData()
		query.append(FROM_81);
		query.append(JOIN_15);
		query.append(JOIN_51);
		query.append(LEFT_JOIN_52);
		query.append(LEFT_JOIN_53);
		query.append(JOIN_59_EN_CURSO_Y_CERRADO);

		params.add(dni);

		// FIN Comun con getEstudioExpedChartsData()
		query.append(DaoConstants.WHERE);

		if (tipoConsulta == DashboardPlanifTareasEnum.SIN_ASIGNAR_HOY.getId()) {

			query.append(
					"  (( TO_DATE(TO_CHAR(T3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') = TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(" 	  ) ");
			query.append(
					" OR (TO_DATE(TO_CHAR(T4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') = TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append("     )) ");
			query.append("  AND ( RECURSO_ASIGNACION_081 IS NULL OR ESTADO_ASIGNACION_081 = ? )");
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());

		} else if (tipoConsulta == DashboardPlanifTareasEnum.SIN_ASIGNAR.getId()) {

			query.append(
					"  (( TO_DATE(TO_CHAR(T3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" 	  AND TO_DATE(TO_CHAR(T3.FECHA_FIN_052,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(SYSDATE + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(
					" OR (TO_DATE(TO_CHAR(T4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') > TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					"     AND TO_DATE(TO_CHAR(T4.FECHA_FINAL_IZO_053,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(SYSDATE + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");
			query.append(" ) AND ( RECURSO_ASIGNACION_081 IS NULL OR ESTADO_ASIGNACION_081 = ? )");
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());

		} else if (tipoConsulta == DashboardPlanifTareasEnum.ASIGNADAS.getId()) {
			query.append(" RECURSO_ASIGNACION_081 =? ");
			query.append(" AND ESTADO_ASIGNACION_081 <> ? ");
			query.append(" AND ESTADO_EJECUCION_081 =? ");
			query.append(" AND ES_RECURSO_DISPONIBLE(DNI_RECURSO_081,ID_TAREA_081,NULL) =? ");

			params.add(TipoRecursoEnum.INTERNO.getValue());
			params.add(EstadoAceptacionTareaEnum.RECHAZADA.getValue());
			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
			params.add(Constants.NO);

		} else if (tipoConsulta == DashboardPlanifTareasEnum.FINALIZAN_HOY.getId()) {

			query.append(
					" TO_CHAR(T1.FECHA_FIN_081,'yyyy/MM/dd') = TO_CHAR(SYSDATE,'yyyy/MM/dd') AND ESTADO_EJECUCION_081 =? ");
			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());

		} else if (tipoConsulta == DashboardPlanifTareasEnum.RETRASADAS.getId()) {

			query.append(
					" TO_DATE(TO_CHAR(T1.FECHA_FIN_081,'yyyy/MM/dd hh24:mi:ss'),'yyyy/MM/dd hh24:mi:ss') < TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd hh24:mi:ss'),'yyyy/MM/dd hh24:mi:ss') AND ESTADO_EJECUCION_081 =? ");
			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		}

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas, params.toArray());
	}

	@Override
	public ResumenGraficas getCargaTareasChartsData(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.SELECT_COUNT + " AS MISEXP, 0 AS TOTAL ");

		// Comun con getEstudioExpedChartsData()
		query.append(FROM_81);
		query.append(JOIN_15);
		query.append(JOIN_51);
		query.append(LEFT_JOIN_52);
		query.append(LEFT_JOIN_53);
		query.append(JOIN_59_EN_CURSO_Y_CERRADO);

		// FIN Comun con getEstudioExpedChartsData()
		this.getWhereDatosCargaTrabajo(dni, tipoConsulta, query, params);

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas, params.toArray());
	}

	/**
	 * @param dni
	 * @param tipoConsulta
	 * @param query
	 * @param params
	 */
	private void getWhereDatosCargaTrabajo(String dni, int tipoConsulta, final StringBuilder query,
			final List<Object> params) {
		query.append(DaoConstants.WHERE);

		query.append(" ESTADO_EJECUCION_081 = ").append(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());

		if (tipoConsulta == DashboardCargaTareasEnum.PDTES_ACEPTACION.getId()) {
			query.append(DaoConstants.AND + " ESTADO_ASIGNACION_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			params.add(Constants.DOS);
		} else if (tipoConsulta == DashboardCargaTareasEnum.RETRASADAS.getId()) {
			query.append(DaoConstants.AND + " T1.FECHA_FIN_081 < SYSDATE ");
			query.append(DaoConstants.AND + " T1.RECURSO_ASIGNACION_081 = ? ");
			params.add(TipoRecursoEnum.INTERNO.getValue());
		} else if (tipoConsulta == DashboardCargaTareasEnum.FINALIZAN_HOY.getId()) {
			query.append(DaoConstants.AND + " ESTADO_ASIGNACION_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			params.add(Constants.TRES);
			query.append(DaoConstants.AND
					+ " TO_DATE(TO_CHAR(T1.FECHA_FIN_081,'yyyy/MM/dd'),'yyyy/MM/dd') = TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");

		} else if (tipoConsulta == DashboardCargaTareasEnum.FINALIZAN_7_DIAS.getId()) {
			query.append(DaoConstants.AND + " ESTADO_ASIGNACION_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			params.add(Constants.TRES);
			query.append(DaoConstants.AND
					+ "  ( TO_DATE(TO_CHAR(T1.FECHA_FIN_081,'yyyy/MM/dd'),'yyyy/MM/dd') >= TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd') ");
			query.append(
					" 	  AND TO_DATE(TO_CHAR(T1.FECHA_FIN_081,'yyyy/MM/dd'),'yyyy/MM/dd') <= TO_DATE(TO_CHAR(SYSDATE + 7,'yyyy/MM/dd'),'yyyy/MM/dd')) ");

		} else if (tipoConsulta == DashboardCargaTareasEnum.EN_DIAS_NO_DISPONIBLES.getId()) {
			query.append(DaoConstants.AND + " ESTADO_ASIGNACION_081 ").append(DaoConstants.SIGNOIGUAL_INTERROGACION);
			params.add(Constants.TRES);
			query.append(DaoConstants.AND + " RECURSO_ASIGNACION_081 =? ");
			query.append(DaoConstants.AND + " ES_RECURSO_DISPONIBLE(DNI_RECURSO_081,ID_TAREA_081,NULL) =? ");
			params.add(TipoRecursoEnum.INTERNO.getValue());
			params.add(Constants.NO);

		}
		query.append(DaoConstants.AND + " DNI_RECURSO_081 = ?");
		params.add(dni);
	}

	/**
	 * Pestaña carga de trabajo del Dashboard
	 */
	@Override
	public List<ExpTareasResumen> findAllExpTareasResumenCarga(Integer filtroDatosCarga, String dni,
			JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		final List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		// Se obtiene la select
		query.append(planificacionExpedienteUtils.getSelectResumen("tareas"));
		query.append(
				",NVL(	TRIM(t29.NOMBRE_LOTE_029),TRIM(h1.APEL1_077) || ' ' || TRIM(h1.APEL2_077) || ' ' || TRIM(h1.NOMBRE_077)) NOMBRECOMPLETO ");
		query.append(" , COALESCE(PARSE_HORA_MINUTOS(t1.HORAS_PREVISTAS_081),0) HORASPREVISTASNUMBER ");
		// Se obtienen las joins
		planificacionExpedienteUtils.getJoinsResumenCargaQuery(filtroDatosCarga, query, params, false);
		// Se obtiene la where
		this.getWhereDatosCargaTrabajo(dni, filtroDatosCarga, query, params);

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getCargaOrderBy());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapTareas, params.toArray());
	}

	@Override
	public Long findAllExpTareasResumenCargaCount(Integer filtroDatosCarga, String dni, boolean b) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT COUNT(1) ");
		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		// Se obtienen las joins
		planificacionExpedienteUtils.getJoinsResumenCargaQuery(filtroDatosCarga, query, params, true);
		// Se obtiene la where
		this.getWhereDatosCargaTrabajo(dni, filtroDatosCarga, query, params);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 * StringBuilder initilization value
	 */
	public static final int STRING_BUILDER_INIT = 4096;
}
