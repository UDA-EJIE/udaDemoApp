package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.ejie.aa79b.dao.mapper.TareasGestionInterpretacionRowMapper;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasGestionInterpretacion;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;

/**
 * 
 * @author dmuchuari
 *
 */
@Repository
@Transactional
public class TareasGestionInterpretacionDaoImpl extends GenericoDaoImpl<TareasGestionInterpretacion>
		implements TareasGestionInterpretacionDao {
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public TareasGestionInterpretacionDaoImpl() {
		super(TareasGestionInterpretacion.class);
	}

	@Override()
	public TareasGestionInterpretacion add(TareasGestionInterpretacion tareasGestionInterpretacion) {
		String query = "INSERT INTO AA79B89S01 (ID_TAREA_089, NUM_INTERPRETES_089, HORAS_INTERPRETACION_089, IND_VISIBLE_089, PRESUPUESTO_089) VALUES (?,?,?,?,?)";

		this.getJdbcTemplate().update(query, tareasGestionInterpretacion.getIdTarea(),
				tareasGestionInterpretacion.getNumInterpretes(),
				tareasGestionInterpretacion.getHorasPrevistasInterpretacion(),
				(tareasGestionInterpretacion.getIndVisible() != null
						&& tareasGestionInterpretacion.getIndVisible().equalsIgnoreCase(Constants.SI)) ? "S" : "N",
				tareasGestionInterpretacion.getPresupuesto());
		return tareasGestionInterpretacion;
	}

	@Override()
	public TareasGestionInterpretacion update(TareasGestionInterpretacion tareasGestionInterpretacion) {
		String query = "UPDATE AA79B89S01 SET NUM_INTERPRETES_089=?, HORAS_INTERPRETACION_089=?, IND_VISIBLE_089=?, PRESUPUESTO_089=? WHERE ID_TAREA_089=?";
		this.getJdbcTemplate().update(query, tareasGestionInterpretacion.getNumInterpretes(),
				tareasGestionInterpretacion.getHorasPrevistasInterpretacion(),
				(tareasGestionInterpretacion.getIndVisible() != null
						&& tareasGestionInterpretacion.getIndVisible().equalsIgnoreCase(Constants.SI)) ? "S" : "N",
				tareasGestionInterpretacion.getPresupuesto(), tareasGestionInterpretacion.getIdTarea());
		return tareasGestionInterpretacion;
	}

	@Override()
	public int updateInt(TareasGestionInterpretacion tareasGestionInterpretacion) {
		String query = "UPDATE AA79B89S01 SET NUM_INTERPRETES_089=?, HORAS_INTERPRETACION_089=?, IND_VISIBLE_089=?, PRESUPUESTO_089=? WHERE ID_TAREA_089=?";
		return this.getJdbcTemplate().update(query, tareasGestionInterpretacion.getNumInterpretes(),
				tareasGestionInterpretacion.getHorasPrevistasInterpretacion(),
				(tareasGestionInterpretacion.getIndVisible() != null
						&& tareasGestionInterpretacion.getIndVisible().equalsIgnoreCase(Constants.SI)) ? "S" : "N",
				tareasGestionInterpretacion.getPresupuesto(), tareasGestionInterpretacion.getIdTarea());
	}

	@Override
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.ID_TAREA_089 IDTAREA");
		query.append(", t1.NUM_INTERPRETES_089 NUMINTERPRETES");
		query.append(", t1.HORAS_INTERPRETACION_089 HORASINTERPRETACION");
		query.append(", DECODE(t1.IND_VISIBLE_089, '").append(ActivoEnum.SI.getValue().toLowerCase()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)).append("', '")
				.append(ActivoEnum.NO.getValue().toLowerCase()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)).append("') AS INDVISIBLE");
		query.append(", t1.PRESUPUESTO_089 PRESUPUESTO");
		return query.toString();
	}

	@Override
	protected String getFrom() {
		return " FROM AA79B89S01 t1 LEFT JOIN AA79B81S01 t2 ON t1.ID_TAREA_089 = t2.ID_TAREA_081 ";
	}

	@Override
	protected RowMapper<TareasGestionInterpretacion> getRwMap() {
		return null;
	}

	private RowMapper<TareasGestionInterpretacion> rwMapTareasInterpretacion = new TareasGestionInterpretacionRowMapper();

	private RowMapper<TareasGestionInterpretacion> rwMapInfoDocumentoPresupuestoInterpretacion = new RowMapper<TareasGestionInterpretacion>() {
		@Override()
		public TareasGestionInterpretacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasGestionInterpretacion tareasGestionInterpretacion = new TareasGestionInterpretacion();
			tareasGestionInterpretacion.setNumInterpretes(resultSet.getLong("NUM_INTERPRETES_089"));
			tareasGestionInterpretacion.setPresupuesto(resultSet.getBigDecimal("PRESUPUESTO_089"));
			tareasGestionInterpretacion.setFechaLimite(resultSet.getDate("FECHA_LIMITE_064"));
			tareasGestionInterpretacion.setHoraLimite(resultSet.getString(DBConstants.HORALIMITE));
			tareasGestionInterpretacion.setUrlOrdenPrecios(resultSet.getString("URL_ORDEN_030"));
			return tareasGestionInterpretacion;
		}
	};

	private RowMapper<TareasGestionInterpretacion> rwMapInfoDocumentoPresupuestoInterpretacionSinEjecutar = new RowMapper<TareasGestionInterpretacion>() {
		@Override()
		public TareasGestionInterpretacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			TareasGestionInterpretacion tareasGestionInterpretacion = new TareasGestionInterpretacion();
			tareasGestionInterpretacion.setNumInterpretes(resultSet.getLong("NUM_INTERPRETES_089"));
			tareasGestionInterpretacion.setPresupuesto(resultSet.getBigDecimal("PRESUPUESTO_089"));
			tareasGestionInterpretacion.setUrlOrdenPrecios(resultSet.getString("URL_ORDEN_030"));
			return tareasGestionInterpretacion;
		}
	};

	private RowMapper<Tareas> rwMapTareasPrevInterpretacion = new RowMapper<Tareas>() {

		@Override
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tarea = new Tareas();
			EjecucionTareas ejecucionTareas = new EjecucionTareas();
			ejecucionTareas.setHorasTarea(resultSet.getString("HORAS_TAREA_082"));
			ejecucionTareas.setPorUsoEuskera(resultSet.getLong("POR_USO_EUSKERA_082"));
			ejecucionTareas.setUsuariosPrevEjecTarea(resultSet.getString("NOMBRES"));
			tarea.setEjecucionTareas(ejecucionTareas);
			return tarea;
		}
	};

	protected RowMapper<TareasGestionInterpretacion> getRwMapInfoDocumentoPresupuestoInterpretacion() {
		return this.rwMapInfoDocumentoPresupuestoInterpretacion;
	}

	protected RowMapper<TareasGestionInterpretacion> getRwMapInfoDocumentoPresupuestoInterpretacionSinEjecutar() {
		return this.rwMapInfoDocumentoPresupuestoInterpretacionSinEjecutar;
	}

	@Override
	protected String[] getOrderBy() {
		return new String[0];
	}

	@Override
	protected String getPK() {
		return null;
	}

	@Override
	protected RowMapper<TareasGestionInterpretacion> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(TareasGestionInterpretacion bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhere(TareasGestionInterpretacion bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(TareasGestionInterpretacion bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		return null;
	}

	public String getSelectTareasInterpretacion() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT t2.ID_TAREA_081 IDTAREA");
		query.append(", t2.ANYO_081 ANYO");
		query.append(", t2.NUM_EXP_081 NUMEXP");
		query.append(", t1.NUM_INTERPRETES_089 NUMINTERPRETES");
		query.append(", t1.HORAS_INTERPRETACION_089 HORASINTERPRETACION");
		query.append(", t1.IND_VISIBLE_089 INDVISIBLE");
		query.append(", t1.PRESUPUESTO_089 PRESUPUESTO");
		query.append(", t2.ID_TIPO_TAREA_081 TIPOTAREA");
		query.append(", t2.FECHA_INICIO_081 FECHAINI");
		query.append(", TO_CHAR(t2.FECHA_INICIO_081,'HH24:MI') HORAINI");
		query.append(", t2.FECHA_FIN_081 FECHAFIN");
		query.append(", TO_CHAR(t2.FECHA_FIN_081,'HH24:MI') HORAFIN");
		query.append(", t3.HORAS_TAREA_082 HORASTAREA");
		query.append(", t4.IND_PRESUPUESTO_052 INDPRESU");
		query.append(", t5.FECHA_LIMITE_064 FECHALIMITE");
		query.append(", TO_CHAR(t5.FECHA_LIMITE_064,'HH24:MI') HORALIMITE ");
		query.append(", NVL(t2.ID_REQUERIMIENTO_081," + Constants.MINUS_UNO + ") IDREQUERIMIENTO ");
		query.append(", t4.FECHA_INI_052 FECHAINI52 ");
		query.append(", TO_CHAR(t4.FECHA_INI_052, 'HH24:MI') HORAINI52 ");
		query.append(", t4.FECHA_FIN_052 FECHAFIN52 ");
		query.append(", TO_CHAR(t4.FECHA_FIN_052, 'HH24:MI') HORAFIN52 ");

		return query.toString();
	}

	public RowMapper<TareasGestionInterpretacion> getRwMapTareasInterpretacion() {
		return rwMapTareasInterpretacion;
	}

	private String getFromTareaInterpretacion() {

		return " FROM AA79B52S01 t4 LEFT JOIN AA79B81S01 t2 on t4.NUM_EXP_052=t2.NUM_EXP_081 AND t4.ANYO_052=t2.ANYO_081 LEFT JOIN AA79B89S01 t1 ON t1.ID_TAREA_089 = t2.ID_TAREA_081 LEFT JOIN AA79B82S01 t3 on t2.ID_TAREA_081=t3.ID_TAREA_082 LEFT JOIN aa79b64s01 t5 ON t2.ID_REQUERIMIENTO_081 = t5.ID_064 ";
	}

	@Override
	public TareasGestionInterpretacion findTareaInterpretacion(BigDecimal idTarea) {
		StringBuilder query = new StringBuilder(this.getSelectTareasInterpretacion());
		List<Object> params = new ArrayList<Object>();
		query.append(this.getFromTareaInterpretacion());
		query.append(" WHERE t2.ID_TAREA_081 = ? ");
		params.add(idTarea);
		List<TareasGestionInterpretacion> tareaList = this.getJdbcTemplate().query(query.toString(),
				this.getRwMapTareasInterpretacion(), params.toArray());
		return DataAccessUtils.uniqueResult(tareaList);
	}

	@Override
	public TareasGestionInterpretacion getInfoDocumentoPresupuestoInterpretacion(Long anyo, Integer numExp) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(
				"SELECT NUM_INTERPRETES_089, NVL(PRESUPUESTO_089, 0) as PRESUPUESTO_089, FECHA_LIMITE_064, URL_ORDEN_030");
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + "FECHA_LIMITE_064" + DaoConstants.SIGNO_COMA
				+ DaoConstants.FORMATO_HORA + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.BLANK + DBConstants.HORALIMITE);
		query.append(" FROM AA79B81S01 JOIN AA79B89S01 ON ID_TAREA_089 = ID_TAREA_081 ");
		query.append(" JOIN AA79B64S01 ON ID_064 = ID_REQUERIMIENTO_081");
		query.append(" AND TIPO_REQUERIMIENTO_064 = " + TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue());
		query.append(" JOIN  AA79B30S01 ON ID_ORDEN_089 = ID_030 ");
		query.append(" WHERE ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue());
		query.append(" AND ESTADO_EJECUCION_081 = " + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(" AND IND_VISIBLE_089 = '" + ActivoEnum.SI.getValue() + "' ");
		query.append(" AND ANYO_081 = ? AND NUM_EXP_081 = ? ");
		params.add(anyo);
		params.add(numExp);

		List<TareasGestionInterpretacion> tareaList = this.getJdbcTemplate().query(query.toString(),
				this.getRwMapInfoDocumentoPresupuestoInterpretacion(), params.toArray());
		return DataAccessUtils.uniqueResult(tareaList);
	}

	@Override
	public TareasGestionInterpretacion getInfoDocumentoPresupuestoInterpretacionSinEjecutar(Long anyo, Integer numExp) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("SELECT NUM_INTERPRETES_089, PRESUPUESTO_089, URL_ORDEN_030");
		query.append(" FROM AA79B81S01 JOIN AA79B89S01 ON ID_TAREA_089 = ID_TAREA_081 ");
		query.append(" JOIN  AA79B30S01 ON ID_ORDEN_089 = ID_030 ");
		query.append(" WHERE ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue());
		query.append(" AND ANYO_081 = ? AND NUM_EXP_081 = ? ");
		params.add(anyo);
		params.add(numExp);

		List<TareasGestionInterpretacion> tareaList = this.getJdbcTemplate().query(query.toString(),
				this.getRwMapInfoDocumentoPresupuestoInterpretacionSinEjecutar(), params.toArray());
		return DataAccessUtils.uniqueResult(tareaList);
	}

	@Override
	public Tareas findEjecTareasPrevInterpretacion(Tareas tarea) {
		StringBuilder queryETPI = new StringBuilder(TareasGestionInterpretacionDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsETPI = new ArrayList<Object>();
		queryETPI.append(" SELECT MAX(HORAS_TAREA_082) AS HORAS_TAREA_082, ");
		queryETPI.append(" MAX(POR_USO_EUSKERA_082) AS POR_USO_EUSKERA_082, ");
		queryETPI.append(
				" LISTAGG( NOMBRE_077 || ' ' || APEL1_077 || ' ' || APEL2_077, ', ') WITHIN GROUP(ORDER BY NOMBRE_077, APEL1_077, APEL2_077) AS NOMBRES ");
		queryETPI.append(" FROM AA79B81T00 ");
		queryETPI.append(" JOIN AA79B82T00 ON ID_TAREA_081 = ID_TAREA_082 ");
		queryETPI.append(" JOIN AA79B77T00 ON DNI_RECURSO_081 = DNI_077 ");
		queryETPI.append(" WHERE 1=1 ");
		queryETPI.append(SqlUtils.generarWhereIgual("ESTADO_EJECUCION_081",
				EstadoEjecucionTareaEnum.EJECUTADA.getValue(), paramsETPI));
		queryETPI.append(SqlUtils.generarWhereIgual("ID_TIPO_TAREA_081",
				TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue(), paramsETPI));
		queryETPI.append(SqlUtils.generarWhereIgual("NUM_EXP_081", tarea.getNumExp(), paramsETPI));
		queryETPI.append(SqlUtils.generarWhereIgual("ANYO_081", tarea.getAnyo(), paramsETPI));
		queryETPI.append(SqlUtils.generarWhereDistinto("ID_TAREA_081", tarea.getIdTarea(), paramsETPI));
		queryETPI.append(" GROUP BY ANYO_081, NUM_EXP_081 ");
		List<Tareas> beanList = this.getJdbcTemplate().query(queryETPI.toString(), this.rwMapTareasPrevInterpretacion,
				paramsETPI.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Integer actualizarTareasPrevInterpretacion(Tareas tarea) {
		StringBuilder queryATPI = new StringBuilder(TareasGestionInterpretacionDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsATPI = new ArrayList<Object>();
		paramsATPI.add(tarea.getEjecucionTareas().getHorasTarea());
		paramsATPI.add(tarea.getEjecucionTareas().getPorUsoEuskera());
		queryATPI.append(" UPDATE AA79B82T00 SET HORAS_TAREA_082 = ? ,");
		queryATPI.append(" POR_USO_EUSKERA_082 = ? ");
		queryATPI.append(" WHERE ID_TAREA_082 IN  ");
		queryATPI.append(" (SELECT ID_TAREA_081  ");
		queryATPI.append(" FROM AA79B81T00 WHERE 1 = 1 ");
		queryATPI.append(SqlUtils.generarWhereIgual("ESTADO_EJECUCION_081",
				EstadoEjecucionTareaEnum.EJECUTADA.getValue(), paramsATPI));
		queryATPI.append(SqlUtils.generarWhereIgual("ID_TIPO_TAREA_081",
				TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue(), paramsATPI));
		queryATPI.append(SqlUtils.generarWhereIgual("NUM_EXP_081", tarea.getNumExp(), paramsATPI));
		queryATPI.append(SqlUtils.generarWhereIgual("ANYO_081", tarea.getAnyo(), paramsATPI));
		queryATPI.append(SqlUtils.generarWhereDistinto("ID_TAREA_081", tarea.getIdTarea(), paramsATPI));
		queryATPI.append(")");
		return this.getJdbcTemplate().update(queryATPI.toString(), paramsATPI.toArray());
	}

}
