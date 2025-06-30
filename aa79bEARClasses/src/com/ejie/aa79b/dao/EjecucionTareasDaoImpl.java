package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.EjecucionTareas;

@Repository()
@Transactional()
public class EjecucionTareasDaoImpl extends GenericoDaoImpl<EjecucionTareas> implements EjecucionTareasDao {

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.IDTAREA };

	public EjecucionTareasDaoImpl() {
		super(EjecucionTareas.class);
	}

	private RowMapper<EjecucionTareas> rwMapPK = new RowMapper<EjecucionTareas>() {
		@Override
		public EjecucionTareas mapRow(ResultSet rs, int rowNum) throws SQLException {
			EjecucionTareas ejecucionTareas = new EjecucionTareas();
			ejecucionTareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
			return ejecucionTareas;
		}
	};

	private RowMapper<EjecucionTareas> rwMap = new RowMapper<EjecucionTareas>() {
		@Override()
		public EjecucionTareas mapRow(ResultSet rs, int rowNum) throws SQLException {
			EjecucionTareas ejecucionTareas = new EjecucionTareas();
			ejecucionTareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
			ejecucionTareas.setDniRecurso(rs.getString("DNIRECURSO"));
			ejecucionTareas.setFechaEjecucion(rs.getDate("FECHAEJECUCION"));
			ejecucionTareas.setHorasTarea(rs.getString("HORASTAREA"));
			ejecucionTareas.setIndObservaciones(rs.getString("INDOBSERVACIONES"));
			return ejecucionTareas;
		}
	};

	@Override()
	public EjecucionTareas add(EjecucionTareas ejecucionTareas) {
		String query = "INSERT INTO AA79B82S01 (ID_TAREA_082, DNI_RECURSO_082, HORAS_TAREA_082, IND_REALIZADA_082, IND_OBSV_082, POR_USO_EUSKERA_082) VALUES (?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, ejecucionTareas.getIdTarea(), ejecucionTareas.getDniRecurso(),
				ejecucionTareas.getHorasTarea(),
				ejecucionTareas.getIndRealizada() != null ? ejecucionTareas.getIndRealizada() : Constants.NO,
				ejecucionTareas.getIndObservaciones() != null ? ejecucionTareas.getIndObservaciones() : Constants.NO,
				ejecucionTareas.getPorUsoEuskera());
		return ejecucionTareas;
	}

	@Override()
	public EjecucionTareas addConFechaEjec(EjecucionTareas ejecucionTareas) {
		String query = "INSERT INTO AA79B82S01 (ID_TAREA_082, DNI_RECURSO_082, FECHA_EJECUCION_082, HORAS_TAREA_082, IND_REALIZADA_082, IND_OBSV_082, POR_USO_EUSKERA_082) VALUES (?,?,SYSDATE,?,?,?,?)";
		this.getJdbcTemplate().update(query, ejecucionTareas.getIdTarea(), ejecucionTareas.getDniRecurso(),
				ejecucionTareas.getHorasTarea(),
				ejecucionTareas.getIndRealizada() != null ? ejecucionTareas.getIndRealizada() : Constants.NO,
				ejecucionTareas.getIndObservaciones() != null ? ejecucionTareas.getIndObservaciones() : Constants.NO,
				ejecucionTareas.getPorUsoEuskera());
		return ejecucionTareas;
	}

	@Override()
	public EjecucionTareas update(EjecucionTareas ejecucionTareas) {
		String query = "UPDATE AA79B82S01 SET DNI_RECURSO_082=?, FECHA_EJECUCION_082=SYSDATE, HORAS_TAREA_082=?, IND_REALIZADA_082=?, IND_OBSV_082=?, POR_USO_EUSKERA_082=? WHERE ID_TAREA_082=?";
		this.getJdbcTemplate().update(query, ejecucionTareas.getDniRecurso(), ejecucionTareas.getHorasTarea(),
				ejecucionTareas.getIndRealizada() != null ? ejecucionTareas.getIndRealizada() : Constants.NO,
				ejecucionTareas.getIndObservaciones() != null ? ejecucionTareas.getIndObservaciones() : Constants.NO,
				ejecucionTareas.getPorUsoEuskera(), ejecucionTareas.getIdTarea());
		return ejecucionTareas;
	}

	@Override
	protected String getSelect() {
		StringBuilder str = new StringBuilder();
		str.append("SELECT ");
		str.append("ID_TAREA_082 IDTAREA, ");
		str.append("DNI_RECURSO_082 DNIRECURSO ");
		str.append(", FECHA_EJECUCION_082 FECHAEJECUCION ");
		str.append(", HORAS_TAREA_082 HORASTAREA ");
		str.append(", IND_REALIZADA_082 INDREALIZADA ");
		str.append(", IND_OBSV_082 INDOBSERVACIONES ");
		return str.toString();
	}

	@Override
	protected String getFrom() {
		return " FROM AA79B82S01 ";
	}

	@Override
	protected RowMapper<EjecucionTareas> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return EjecucionTareasDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return " ID_TAREA_082 ";
	}

	@Override
	protected RowMapper<EjecucionTareas> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(EjecucionTareas bean, List<Object> params) {
		params.add(bean.getIdTarea());
		return " WHERE ID_TAREA_082 = ?";

	}

	@Override
	protected String getWhere(EjecucionTareas bean, List<Object> params) {
		StringBuilder strArea = new StringBuilder();
		strArea.append(SqlUtils.generarWhereIgual("ID_TAREA_082", bean.getIdTarea(), params));
		strArea.append(SqlUtils.generarWhereIgual("DNI_RECURSO_082", bean.getDniRecurso(), params));
		strArea.append(SqlUtils.generarWhereIgual("FECHA_EJECUCION_082", bean.getFechaEjecucion(), params));
		strArea.append(SqlUtils.generarWhereIgual("HORAS_TAREA_082", bean.getHorasTarea(), params));
		strArea.append(SqlUtils.generarWhereIgual("IND_REALIZADA_082", bean.getIndRealizada(), params));
		strArea.append(SqlUtils.generarWhereIgual("IND_OBSV_082", bean.getIndObservaciones(), params));
		return strArea.toString();
	}

	@Override
	protected String getWhereLike(EjecucionTareas bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder strArea = new StringBuilder();
		strArea.append(SqlUtils.generarWhereIgual("ID_TAREA_082", bean.getIdTarea(), params));
		strArea.append(SqlUtils.generarWhereIgual("DNI_RECURSO_082", bean.getDniRecurso(), params));
		strArea.append(SqlUtils.generarWhereIgual("FECHA_EJECUCION_082", bean.getFechaEjecucion(), params));
		strArea.append(SqlUtils.generarWhereIgual("HORAS_TAREA_082", bean.getHorasTarea(), params));
		strArea.append(SqlUtils.generarWhereIgual("IND_REALIZADA_082", bean.getIndRealizada(), params));
		strArea.append(SqlUtils.generarWhereIgual("IND_OBSV_082", bean.getIndObservaciones(), params));
		return strArea.toString();
	}

}
