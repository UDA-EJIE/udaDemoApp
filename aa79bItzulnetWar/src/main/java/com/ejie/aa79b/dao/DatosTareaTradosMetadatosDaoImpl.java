package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.MetadatosBusqueda;

/**
 * 
 * @author eaguirresarobe
 *
 */
@Repository
@Transactional
public class DatosTareaTradosMetadatosDaoImpl extends GenericoDaoImpl<MetadatosBusqueda>
		implements DatosTareaTradosMetadatosDao {

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.IDMETADATO, DBConstants.DESC_ES,
			DBConstants.DESC_EU };
	protected static final String T1ID_METADATO_062 = " t1.ID_METADATO_062 ";

	public DatosTareaTradosMetadatosDaoImpl() {
		super(MetadatosBusqueda.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<MetadatosBusqueda> rwMap = new RowMapper<MetadatosBusqueda>() {
		public MetadatosBusqueda mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			MetadatosBusqueda metadatosBusqueda = new MetadatosBusqueda();
			metadatosBusqueda.setId(resultSet.getLong(DBConstants.IDMETADATO));
			metadatosBusqueda.setDescEs(resultSet.getString(DBConstants.DESC_ES));
			metadatosBusqueda.setDescEu(resultSet.getString(DBConstants.DESC_EU));
			return metadatosBusqueda;
		}
	};
	private RowMapper<MetadatosBusqueda> rwMapPK = new RowMapper<MetadatosBusqueda>() {
		@Override
		public MetadatosBusqueda mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			MetadatosBusqueda metadatosBusqueda = new MetadatosBusqueda();
			metadatosBusqueda.setId(resultSet.getLong(DBConstants.IDMETADATO));
			return metadatosBusqueda;
		}
	};

	@Override
	protected String getSelect() {
		StringBuilder query = new StringBuilder(DatosTareaTradosMetadatosDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.SELECT);
		query.append(T1ID_METADATO_062 + DBConstants.IDMETADATO + DaoConstants.SIGNO_COMA);
		query.append(" t2.DESC_ES_019 " + DBConstants.DESC_ES + DaoConstants.SIGNO_COMA);
		query.append(" t2.DESC_EU_019 " + DBConstants.DESC_EU);
		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder query = new StringBuilder(DatosTareaTradosMetadatosDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_62 + " t1 ");
		query.append(DaoConstants.JOIN);
		query.append(DBConstants.TABLA_19 + " t2 ");
		query.append(DaoConstants.ON + T1ID_METADATO_062 + DaoConstants.SIGNOIGUAL + " t2.ID_019 ");
		return query.toString();
	}

	@Override
	protected RowMapper<MetadatosBusqueda> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return DatosTareaTradosMetadatosDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return "ID_METADATO_062";
	}

	@Override
	protected RowMapper<MetadatosBusqueda> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(MetadatosBusqueda bean, List<Object> params) {
		params.add(bean.getId());
		StringBuilder where = new StringBuilder(DatosTareaTradosMetadatosDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE);
		where.append(T1ID_METADATO_062 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		return where.toString();
	}

	@Override
	protected String getWhere(MetadatosBusqueda bean, List<Object> params) {
		StringBuilder where = new StringBuilder(DatosTareaTradosMetadatosDaoImpl.STRING_BUILDER_INIT);
		if (bean != null) {
			where.append(SqlUtils.generarWhereIgual("t1.ID_METADATO_062", bean.getId(), params));
			where.append(SqlUtils.generarWhereLike("t2.DESC_ES_019", bean.getDescEs(), params, false));
			where.append(SqlUtils.generarWhereLike("t2.DESC_EU_019", bean.getDescEu(), params, false));
		}
		return where.toString();
	}

	@Override
	protected String getWhereLike(MetadatosBusqueda bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(DatosTareaTradosMetadatosDaoImpl.STRING_BUILDER_INIT);
		if (bean != null) {
			where.append(SqlUtils.generarWhereIgual("t1.ID_METADATO_062", bean.getId(), params));
			where.append(SqlUtils.generarWhereLike("t2.DESC_ES_019", bean.getDescEs(), params, false));
			where.append(SqlUtils.generarWhereLike("t2.DESC_EU_019", bean.getDescEu(), params, false));
		}
		return where.toString();
	}

	@Override
	public List<MetadatosBusqueda> getMetadatosTareaTrados(Long anyoExp, Integer numExp) {
		StringBuilder query = new StringBuilder(DatosTareaTradosMetadatosDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append(this.getSelect());
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE);
		query.append(" t1.ANYO_062 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(" t1.NUM_EXP_062 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(anyoExp);
		params.add(numExp);
		return this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());
	}

}
