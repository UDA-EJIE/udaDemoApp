package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.LimitePalConcor;

@Repository()
@Transactional()
public class LimitePalConcorDaoImpl extends GenericoDaoImpl<LimitePalConcor> implements LimitePalConcorDao {

	protected static final String PK = "ID_002";
	public static final String[] ORDER_BY_WHITE_LIST = new String[] { "ID002", "LIMPALCONCOR002",
			"TIEMPO_EXTRA_GESTION_002" };

	public LimitePalConcorDaoImpl() {
		super(LimitePalConcor.class);
	}

	private RowMapper<LimitePalConcor> rwMap = new RowMapper<LimitePalConcor>() {
		@Override
		public LimitePalConcor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			LimitePalConcor limitePalConcor = new LimitePalConcor(resultSet.getInt("ID002"),
					resultSet.getLong("LIMPALCONCOR002"));
			limitePalConcor.setTiempoExtraGest(resultSet.getString("TIEMPOEXTRAGESTION002"));
			return limitePalConcor;
		}
	};

	private RowMapper<LimitePalConcor> rwMapPK = new RowMapper<LimitePalConcor>() {
		@Override
		public LimitePalConcor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new LimitePalConcor(resultSet.getInt("ID002"));
		}
	};

	@Override
	protected String getSelect() {
		StringBuilder select = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		select.append(
				" SELECT t1.ID_002 ID002, t1.LIM_PAL_CONCOR_002 LIMPALCONCOR002, t1.TIEMPO_EXTRA_GESTION_002 TIEMPOEXTRAGESTION002 ");
		return select.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		from.append("  FROM AA79B02S02 t1 ");
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
		where.append(SqlUtils.generarWhereIgual("LIM_PAL_CONCOR_002", bean.getLimPalConcor(), params));
		where.append(SqlUtils.generarWhereIgual("TIEMPO_EXTRA_GESTION_002", bean.getTiempoExtraGest(), params));
		return where.toString();
	}

	@Override
	protected String getWhereLike(LimitePalConcor bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLike = new StringBuilder(LimitePalConcorDaoImpl.STRING_BUILDER_INIT);
		whereLike.append(SqlUtils.generarWhereIgual(PK, bean.getId(), params));
		whereLike.append(SqlUtils.generarWhereIgual("LIM_PAL_CONCOR_002", bean.getLimPalConcor(), params));
		whereLike.append(SqlUtils.generarWhereIgual("TIEMPO_EXTRA_GESTION_002", bean.getTiempoExtraGest(), params));
		return whereLike.toString();
	}

	@Override
	public Boolean isLimPalConcorStored(LimitePalConcor limitePalConcor) {
		List<Object> params = new ArrayList<Object>();
		params.add(limitePalConcor.getId());
		StringBuilder queryIsLimPalConcorStored = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryIsLimPalConcorStored.append(" SELECT COUNT(1) ");
		queryIsLimPalConcorStored.append(" FROM AA79B02S02 ");
		queryIsLimPalConcorStored.append(" WHERE  ID_002 = ?");
		Integer count = this.getJdbcTemplate().queryForObject(queryIsLimPalConcorStored.toString(), params.toArray(),
				Integer.class);
		return count != null && count == 1;
	}

	@Override
	public Integer updateLimPalConcor(LimitePalConcor limitePalConcor) {
		List<Object> params = new ArrayList<Object>();
		params.add(limitePalConcor.getLimPalConcor());
		params.add(limitePalConcor.getTiempoExtraGest());
		params.add(limitePalConcor.getId());
		StringBuilder queryUpdateLimPalConcor = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryUpdateLimPalConcor.append(" UPDATE  AA79B02S02 SET ");
		queryUpdateLimPalConcor.append(" LIM_PAL_CONCOR_002 = ? ");
		queryUpdateLimPalConcor.append(" , TIEMPO_EXTRA_GESTION_002 = ? ");
		queryUpdateLimPalConcor.append(" WHERE ID_002 = ? ");
		return this.getJdbcTemplate().update(queryUpdateLimPalConcor.toString(), params.toArray());
	}

	@Override
	public Integer addLimPalConcor(LimitePalConcor limitePalConcor) {
		List<Object> params = new ArrayList<Object>();
		params.add(limitePalConcor.getId());
		params.add(limitePalConcor.getLimPalConcor());
		params.add(limitePalConcor.getTiempoExtraGest());
		StringBuilder queryAddLimPalConcor = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryAddLimPalConcor.append(" INSERT INTO AA79B02S02 ");
		queryAddLimPalConcor.append(" (ID_002, LIM_PAL_CONCOR_002, TIEMPO_EXTRA_GESTION_002) ");
		queryAddLimPalConcor.append(" VALUES (?,?,?) ");
		return this.getJdbcTemplate().update(queryAddLimPalConcor.toString(), params.toArray());
	}

	@Override
	public LimitePalConcor findLimPalConcor(Integer idDatosBasicos) {
		List<Object> params = new ArrayList<Object>();
		params.add(idDatosBasicos);
		StringBuilder queryLimPalConcor = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryLimPalConcor.append(" SELECT t1.ID_002 ID002," + "t1.LIM_PAL_CONCOR_002 LIMPALCONCOR002,"
				+ "t1.TIEMPO_EXTRA_GESTION_002 TIEMPOEXTRAGESTION002 ");
		queryLimPalConcor.append(" FROM AA79B02S02 t1");
		queryLimPalConcor.append(" WHERE  t1.ID_002 = ?");
		List<LimitePalConcor> list = this.getJdbcTemplate().query(queryLimPalConcor.toString(), this.rwMap,
				params.toArray());
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			LimitePalConcor limitePalConcor = new LimitePalConcor(idDatosBasicos, 0);
			limitePalConcor.setTiempoExtraGest(Constants.HORA_MINUTOS_CERO);
			return limitePalConcor;
		}
	}

}
