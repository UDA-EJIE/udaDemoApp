package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.LimitePalConcor;

@Repository()
@Transactional()
public class LimitePalConcorProvExtDaoImpl extends GenericoDaoImpl<LimitePalConcor>
		implements LimitePalConcorProvExtDao {

	protected static final String PK = "ID_045";
	public static final String[] ORDER_BY_WHITE_LIST = new String[] { "ID045", "LIMPALCONCOR045" };

	public LimitePalConcorProvExtDaoImpl() {
		super(LimitePalConcor.class);
	}

	private RowMapper<LimitePalConcor> rwMap = new RowMapper<LimitePalConcor>() {
		@Override
		public LimitePalConcor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new LimitePalConcor(resultSet.getInt("ID045"), resultSet.getLong("LIMPALCONCOR045"));
		}
	};

	private RowMapper<LimitePalConcor> rwMapPK = new RowMapper<LimitePalConcor>() {
		@Override
		public LimitePalConcor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new LimitePalConcor(resultSet.getInt("ID045"));
		}
	};

	@Override
	protected String getSelect() {
		StringBuilder select = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		select.append(" SELECT t1.ID_045 ID045, t1.LIM_PAL_CONCOR_045 LIMPALCONCOR045 ");
		return select.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		from.append("  FROM AA79B45S02 t1 ");
		return from.toString();
	}

	@Override
	protected RowMapper<LimitePalConcor> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return LimitePalConcorDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return PK;
	}

	@Override
	protected RowMapper<LimitePalConcor> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(LimitePalConcor bean, List<Object> params) {
		params.add(bean.getId());
		StringBuilder wherePK = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		wherePK.append(" WHERE " + PK + " = ?");
		return wherePK.toString();
	}

	@Override
	protected String getWhere(LimitePalConcor bean, List<Object> params) {
		StringBuilder where = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual(PK, bean.getId(), params));
		where.append(SqlUtils.generarWhereIgual("LIM_PAL_CONCOR_045", bean.getLimPalConcor(), params));
		return where.toString();
	}

	@Override
	protected String getWhereLike(LimitePalConcor bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLike = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		whereLike.append(SqlUtils.generarWhereIgual(PK, bean.getId(), params));
		whereLike.append(SqlUtils.generarWhereIgual("LIM_PAL_CONCOR_045", bean.getLimPalConcor(), params));
		return whereLike.toString();
	}

	@Override
	public Boolean isLimPalConcorStored(LimitePalConcor limitePalConcor) {
		List<Object> params = new ArrayList<Object>();
		params.add(limitePalConcor.getId());
		StringBuilder queryIsLimPalConcorStored = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryIsLimPalConcorStored.append(" SELECT COUNT(1) ");
		queryIsLimPalConcorStored.append(" FROM AA79B45S02 ");
		queryIsLimPalConcorStored.append(" WHERE  ID_045 = ?");
		Integer count = this.getJdbcTemplate().queryForObject(queryIsLimPalConcorStored.toString(), params.toArray(),
				Integer.class);
		return count != null && count == 1;
	}

	@Override
	public Integer updateLimPalConcor(LimitePalConcor limitePalConcor) {
		List<Object> params = new ArrayList<Object>();
		params.add(limitePalConcor.getLimPalConcor());
		params.add(limitePalConcor.getId());
		StringBuilder queryUpdateLimPalConcor = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryUpdateLimPalConcor.append(" UPDATE  AA79B45S02 SET ");
		queryUpdateLimPalConcor.append(" LIM_PAL_CONCOR_045 = ? ");
		queryUpdateLimPalConcor.append(" WHERE ID_045 = ? ");
		return this.getJdbcTemplate().update(queryUpdateLimPalConcor.toString(), params.toArray());
	}

	@Override
	public Integer addLimPalConcor(LimitePalConcor limitePalConcor) {
		List<Object> params = new ArrayList<Object>();
		params.add(limitePalConcor.getId());
		params.add(limitePalConcor.getLimPalConcor());
		StringBuilder queryAddLimPalConcor = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryAddLimPalConcor.append(" INSERT INTO AA79B45S02 ");
		queryAddLimPalConcor.append(" (ID_045, LIM_PAL_CONCOR_045) ");
		queryAddLimPalConcor.append(" VALUES (?,?) ");
		return this.getJdbcTemplate().update(queryAddLimPalConcor.toString(), params.toArray());
	}

	@Override
	public LimitePalConcor findLimPalConcor(Integer idDatosBasicos) {
		List<Object> params = new ArrayList<Object>();
		params.add(idDatosBasicos);
		StringBuilder queryLimPalConcor = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryLimPalConcor.append(" SELECT t1.ID_045 ID045," + "t1.LIM_PAL_CONCOR_045 LIMPALCONCOR045 ");
		queryLimPalConcor.append(" FROM AA79B45S02 t1");
		queryLimPalConcor.append(" WHERE  t1.ID_045 = ?");
		List<LimitePalConcor> list = this.getJdbcTemplate().query(queryLimPalConcor.toString(), this.rwMap,
				params.toArray());
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			return new LimitePalConcor(idDatosBasicos, 0);
		}
	}

}
