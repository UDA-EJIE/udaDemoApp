package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.RangoAprobacionAuditoria;

@Repository
@Transactional
public class RangoAprobacionAuditoriaDaoImpl extends GenericoDaoImpl<RangoAprobacionAuditoria>
		implements RangoAprobacionAuditoriaDao {

	private static final String ID = "ID";

	private static final String VALMINAPROBADO = "VALMINAPROBADO";

	private static final String VALMINPELIGRO = "VALMINPELIGRO";

	public RangoAprobacionAuditoriaDaoImpl() {
		super(RangoAprobacionAuditoria.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { ID, VALMINAPROBADO, VALMINPELIGRO };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<RangoAprobacionAuditoria> rwMap = new RowMapper<RangoAprobacionAuditoria>() {
		@Override
		public RangoAprobacionAuditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new RangoAprobacionAuditoria(resultSet.getLong(ID), resultSet.getInt(VALMINAPROBADO),
					resultSet.getInt(VALMINPELIGRO));
		}
	};

	private RowMapper<RangoAprobacionAuditoria> rwMapPK = new RowMapper<RangoAprobacionAuditoria>() {
		@Override
		public RangoAprobacionAuditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new RangoAprobacionAuditoria(resultSet.getLong(ID), resultSet.getInt(VALMINAPROBADO),
					resultSet.getInt(VALMINPELIGRO));
		}
	};

	@Override
	public RangoAprobacionAuditoria add(RangoAprobacionAuditoria rangoAprobacionAuditoria) {

		String query = "INSERT INTO AA79BB8S01 (ID_0B8, VAL_MIN_APROBADO_0B8, VAL_MIN_PELIGRO_0B8) VALUES (?,?,?)";
		this.getJdbcTemplate().update(query, rangoAprobacionAuditoria.getId(),
				rangoAprobacionAuditoria.getValMinAprobado(), rangoAprobacionAuditoria.getValMinPeligro());
		return rangoAprobacionAuditoria;
	}

	@Override
	public RangoAprobacionAuditoria update(RangoAprobacionAuditoria rangoAprobacionAuditoria) {
		String query = "UPDATE AA79BB8S01 SET VAL_MIN_APROBADO_0B8=?, VAL_MIN_PELIGRO_0B8=? WHERE ID_0B8=?";
		this.getJdbcTemplate().update(query, rangoAprobacionAuditoria.getValMinAprobado(),
				rangoAprobacionAuditoria.getValMinPeligro(), rangoAprobacionAuditoria.getId());
		return rangoAprobacionAuditoria;
	}

	@Override
	protected String getSelect() {
		StringBuilder select = new StringBuilder();

		select.append("SELECT t1.ID_0B8 ID");
		select.append(", t1.VAL_MIN_APROBADO_0B8 VALMINAPROBADO");
		select.append(", t1.VAL_MIN_PELIGRO_0B8 VALMINPELIGRO");
		return select.toString();
	}

	@Override()
	protected String getFrom() {
		return " FROM AA79BB8S01 t1";
	}

	@Override()
	protected RowMapper<RangoAprobacionAuditoria> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return RangoAprobacionAuditoriaDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return "ID_0B8";
	}

	@Override()
	protected RowMapper<RangoAprobacionAuditoria> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(RangoAprobacionAuditoria bean, List<Object> params) {
		params.add(bean.getId());
		return " WHERE t1.ID_0B8 = ?";
	}

	@Override
	protected String getWhere(RangoAprobacionAuditoria bean, List<Object> params) {
		StringBuilder where = new StringBuilder(RangoAprobacionAuditoriaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0B8", bean.getId(), params));

		where.append(SqlUtils.generarWhereIgual("t1.VAL_MIN_APROBADO_0B8", bean.getValMinAprobado(), params));

		where.append(SqlUtils.generarWhereIgual("t1.VAL_MIN_PELIGRO_0B8", bean.getValMinPeligro(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(RangoAprobacionAuditoria bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		StringBuilder where = new StringBuilder(RangoAprobacionAuditoriaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0B8", bean.getId(), params));

		where.append(SqlUtils.generarWhereIgual("t1.VAL_MIN_APROBADO_0B8", bean.getValMinAprobado(), params));

		where.append(SqlUtils.generarWhereIgual("t1.VAL_MIN_PELIGRO_0B8", bean.getValMinPeligro(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public RangoAprobacionAuditoria find() {
		String query = "SELECT t1.ID_0B8 ID, t1.VAL_MIN_APROBADO_0B8 VALMINAPROBADO, t1.VAL_MIN_PELIGRO_0B8 VALMINPELIGRO FROM AA79BB8S01 t1";

		List<RangoAprobacionAuditoria> rangoAprobacionAuditoria = this.getJdbcTemplate().query(query, this.rwMap);
		return DataAccessUtils.uniqueResult(rangoAprobacionAuditoria);
	}
}
