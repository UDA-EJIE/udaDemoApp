package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.ConfigHorasPrevistasProvExtDatosBasicosFilterRowMapper;
import com.ejie.aa79b.model.ConfigHorasPrevistasProveedorExt;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class ConfigHorasPrevistasProveedorExtDaoImpl extends GenericoDaoImpl<ConfigHorasPrevistasProveedorExt>
		implements ConfigHorasPrevistasProveedorExtDao {
	public static final String[] ORDER_BY_WHITE_LIST = new String[] { "ID045", "PALTRADHORA045",
			"PALHORACONCOR100045", "PALHORACONCOR9599045", "PALHORACONCOR8594045", "PALHORACONCOR084045", "PALSEGCONCOR100045",
			"PALSEGCONCOR9599045","PALSEGCONCOR8594045", "PALSEGCONCOR084045", "PALREVHORA045", "PALTRADSEG045", "PALREVSEG045",
			"NIVELUSUARIO045" };

	public ConfigHorasPrevistasProveedorExtDaoImpl() {
		super(ConfigHorasPrevistasProveedorExt.class);
	}

	private RowMapper<ConfigHorasPrevistasProveedorExt> rwMap = new RowMapper<ConfigHorasPrevistasProveedorExt>() {
		@Override
		public ConfigHorasPrevistasProveedorExt mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ConfigHorasPrevistasProveedorExt bean = new ConfigHorasPrevistasProveedorExt(resultSet.getInt("ID045"));
			bean.setNumPalTradHora(resultSet.getBigDecimal("PALTRADHORA045"));
			bean.setPalHoraConcor8594(resultSet.getBigDecimal("PALHORACONCOR8594045"));
			bean.setPalHoraConcor084(resultSet.getBigDecimal("PALHORACONCOR084045"));
			bean.setPalHoraConcor9599(resultSet.getBigDecimal("PALHORACONCOR9599045"));
			bean.setPalHoraConcor100(resultSet.getBigDecimal("PALHORACONCOR100045"));
			bean.setPalSegConcor9599(resultSet.getBigDecimal("PALSEGCONCOR9599045"));
			bean.setPalSegConcor100(resultSet.getBigDecimal("PALSEGCONCOR100045"));
			bean.setPalSegConcor8594(resultSet.getBigDecimal("PALSEGCONCOR8594045"));
			bean.setPalSegConcor084(resultSet.getBigDecimal("PALSEGCONCOR084045"));
			bean.setPalReExtHora(resultSet.getBigDecimal("PALREVHORA045"));
			bean.setPalTradSeg(resultSet.getBigDecimal("PALTRADSEG045"));
			bean.setPalRevSeg(resultSet.getBigDecimal("PALREVSEG045"));
			bean.setNivUsuario(resultSet.getString("NIVELUSUARIO045"));
			return bean;
		}
	};

	private RowMapper<ConfigHorasPrevistasProveedorExt> rwMapPK = new RowMapper<ConfigHorasPrevistasProveedorExt>() {
		@Override
		public ConfigHorasPrevistasProveedorExt mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ConfigHorasPrevistasProveedorExt bean = new ConfigHorasPrevistasProveedorExt(resultSet.getInt("ID045"));
			return bean;
		}
	};

	private RowMapper<ConfigHorasPrevistasProveedorExt> rwMapDatosBasicosFilter = new ConfigHorasPrevistasProvExtDatosBasicosFilterRowMapper();

	@Override()
	public ConfigHorasPrevistasProveedorExt add(ConfigHorasPrevistasProveedorExt confighorasprevistas) {
		StringBuilder query = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		query.append("INSERT INTO AA79B45S01 ");
		query.append(" (ID_045, PAL_TRAD_HORA_045, PAL_HORA_CONCOR_100_045, PAL_HORA_CONCOR_95_99_045, PAL_HORA_CONCOR_85_94_045,"
				+ " PAL_HORA_CONCOR_0_84_045,PAL_SEG_CONCOR_100_045,PAL_SEG_CONCOR_95_99_045,PAL_SEG_CONCOR_85_94_045,PAL_SEG_CONCOR_0_84_045, PAL_REV_HORA_045, ");
		query.append(" PAL_TRAD_SEG_045, PAL_REV_SEG_045, NIVEL_USUARIO_045) ");
		query.append("  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		this.getJdbcTemplate().update(query.toString(), confighorasprevistas.getId(),
				confighorasprevistas.getNumPalTradHora(), confighorasprevistas.getPalHoraConcor100(), confighorasprevistas.getPalHoraConcor9599(),
				confighorasprevistas.getPalHoraConcor8594(), confighorasprevistas.getPalHoraConcor084(),
				confighorasprevistas.getPalSegConcor100(), confighorasprevistas.getPalSegConcor9599(),confighorasprevistas.getPalSegConcor8594(),
				confighorasprevistas.getPalSegConcor084(), confighorasprevistas.getPalReExtHora(),
				confighorasprevistas.getPalTradSeg(), confighorasprevistas.getPalRevSeg(),
				confighorasprevistas.getNivUsuario());
		return confighorasprevistas;
	}

	@Override()
	public ConfigHorasPrevistasProveedorExt update(ConfigHorasPrevistasProveedorExt confighorasprevistas) {

		String query = "UPDATE AA79B45S01 SET PAL_TRAD_HORA_045=?, PAL_HORA_CONCOR_100_045=?, PAL_HORA_CONCOR_95_99_045=?,PAL_HORA_CONCOR_85_94_045=?, PAL_HORA_CONCOR_0_84_045=?,"
				+ "PAL_SEG_CONCOR_100_045=?,PAL_SEG_CONCOR_95_99_045=?,PAL_SEG_CONCOR_85_94_045=?,PAL_SEG_CONCOR_0_84_045=?, PAL_REV_HORA_045=?"
				+ ", PAL_TRAD_SEG_045=?, PAL_REV_SEG_045=? WHERE ID_045=? AND NIVEL_USUARIO_045=?";
		this.getJdbcTemplate().update(query, confighorasprevistas.getNumPalTradHora(),
				confighorasprevistas.getPalHoraConcor100(), confighorasprevistas.getPalHoraConcor9599(),confighorasprevistas.getPalHoraConcor8594(),
				confighorasprevistas.getPalHoraConcor084(), confighorasprevistas.getPalSegConcor100(),confighorasprevistas.getPalSegConcor9599(),
				confighorasprevistas.getPalSegConcor8594(), confighorasprevistas.getPalSegConcor084(),
				confighorasprevistas.getPalReExtHora(), confighorasprevistas.getPalTradSeg(),
				confighorasprevistas.getPalRevSeg(), confighorasprevistas.getId(),
				confighorasprevistas.getNivUsuario());
		return confighorasprevistas;
	}

	@Override()
	public void remove(ConfigHorasPrevistasProveedorExt confighorasprevistas) {
		StringBuilder query = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append("DELETE FROM AA79B45S01 WHERE ID_045=?");
		params.add(confighorasprevistas.getId());
		query.append(SqlUtils.generarWhereIgual("NIVEL_USUARIO_045", confighorasprevistas.getNivUsuario(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override
	protected String getSelect() {
		return "SELECT t1.ID_045 ID045," + " t1.PAL_TRAD_HORA_045 PALTRADHORA045,"
				+ " t1.PAL_HORA_CONCOR_100_045 PALHORACONCOR100045,"
				+ " t1.PAL_HORA_CONCOR_95_99_045 PALHORACONCOR9599045,"
				+ " t1.PAL_HORA_CONCOR_85_94_045 PALHORACONCOR8594045,"
				+ " t1.PAL_HORA_CONCOR_0_84_045 PALHORACONCOR084045,"
				+ " t1.PAL_SEG_CONCOR_100_045 PALSEGCONCOR100045,"
				+ " t1.PAL_SEG_CONCOR_95_99_045 PALSEGCONCOR9599045,"
				+ " t1.PAL_SEG_CONCOR_85_94_045 PALSEGCONCOR8594045,"
				+ " t1.PAL_SEG_CONCOR_0_84_045 PALSEGCONCOR084045," + " t1.PAL_REV_HORA_045 PALREVHORA045,"
				+ " t1.PAL_TRAD_SEG_045 PALTRADSEG045," + " t1,PAL_REV_SEG_045 PALREVSEG045,"
				+ " t1.NIVEL_USUARIO_045 NIVELUSUARIO045";
	}

	@Override
	protected String getFrom() {
		return " FROM AA79B45S01 t1";
	}

	@Override
	protected RowMapper<ConfigHorasPrevistasProveedorExt> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ConfigHorasPrevistasProveedorExtDaoImpl.ORDER_BY_WHITE_LIST;

	}

	@Override
	protected String getPK() {
		return "ID_045";
	}

	@Override
	protected RowMapper<ConfigHorasPrevistasProveedorExt> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(ConfigHorasPrevistasProveedorExt bean, List<Object> params) {
		params.add(bean.getId());
		return " WHERE ID_045 = ?";
	}

	@Override
	protected String getWhere(ConfigHorasPrevistasProveedorExt bean, List<Object> params) {
		StringBuilder where = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual("ID_045", bean.getId(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_TRAD_HORA_002", bean.getNumPalTradHora(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_100_045", bean.getPalHoraConcor100(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_95_99_045", bean.getPalHoraConcor9599(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_85_94_045", bean.getPalHoraConcor8594(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_0_84_045", bean.getPalHoraConcor084(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_100_045", bean.getPalSegConcor100(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_95_99_045", bean.getPalSegConcor9599(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_85_94_045", bean.getPalSegConcor8594(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_0_84_045", bean.getPalSegConcor084(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_REV_HORA_045", bean.getPalReExtHora(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_TRAD_SEG_045", bean.getPalTradSeg(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_REV_SEG_045", bean.getPalRevSeg(), params));
		where.append(SqlUtils.generarWhereIgual("NIVEL_USUARIO_045", bean.getNivUsuario(), params));
		return where.toString();
	}

	@Override
	protected String getWhereLike(ConfigHorasPrevistasProveedorExt bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		StringBuilder where = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual("ID_045", bean.getId(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_TRAD_HORA_002", bean.getNumPalTradHora(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_100_045", bean.getPalHoraConcor100(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_95_99_045", bean.getPalHoraConcor9599(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_85_94_045", bean.getPalHoraConcor8594(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_HORA_CONCOR_0_84_045", bean.getPalHoraConcor084(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_100_045", bean.getPalSegConcor100(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_95_99_045", bean.getPalSegConcor9599(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_85_94_045", bean.getPalSegConcor8594(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_SEG_CONCOR_0_84_045", bean.getPalSegConcor084(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_REV_HORA_045", bean.getPalReExtHora(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_TRAD_SEG_045", bean.getPalTradSeg(), params));
		where.append(SqlUtils.generarWhereIgual("PAL_REV_SEG_045", bean.getPalRevSeg(), params));
		where.append(SqlUtils.generarWhereIgual("NIVEL_USUARIO_045", bean.getNivUsuario(), params));
		return where.toString();
	}

	@Override
	public Object datosBasicosFilter(ConfigHorasPrevistasProveedorExt filterConfigHorasPrevistas,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, Boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		if (isCount) {
			query.append(" SELECT COUNT(1) ");
		} else {
			query.append(this.getFilterSelect());
		}
		query.append(this.getFilterFrom());
		query.append(this.getWhere(filterConfigHorasPrevistas, params));

		StringBuilder paginatedQuery = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));
		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapDatosBasicosFilter,
					params.toArray());
		}
	}

	private String getFilterSelect() {
		StringBuilder queryFilterSelect = new StringBuilder(
				ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		queryFilterSelect.append(" SELECT usuario.nivel_usuario NIVELUSUARIO045,"
				+ " PAL_TRAD_HORA_045 PALTRADHORA045, " + " PAL_HORA_CONCOR_100_045 PALHORACONCOR100045, "
				+ " PAL_HORA_CONCOR_95_99_045 PALHORACONCOR9599045, "+ " PAL_HORA_CONCOR_85_94_045 PALHORACONCOR8594045, "
				+ " PAL_HORA_CONCOR_0_84_045 PALHORACONCOR084045, " + " PAL_REV_HORA_045 PALREVHORA045, "
				+ " PAL_SEG_CONCOR_100_045 PALSEGCONCOR100045, " + " PAL_SEG_CONCOR_95_99_045 PALSEGCONCOR9599045, "
				+ " PAL_SEG_CONCOR_85_94_045 PALSEGCONCOR8594045, " + " PAL_SEG_CONCOR_0_84_045 PALSEGCONCOR084045, "
				+ " PAL_TRAD_SEG_045 PALTRADSEG045, " + " PAL_REV_SEG_045 PALREVSEG045 ");
		return queryFilterSelect.toString();
	}

	private String getFilterFrom() {
		StringBuilder queryFilterFrom = new StringBuilder(ConfigHorasPrevistasProveedorExtDaoImpl.STRING_BUILDER_INIT);
		queryFilterFrom
				.append(" from  (SELECT 'N' nivel_usuario from dual UNION select 'V' nivel_usuario from dual) usuario "
						+ " LEFT JOIN AA79B45T00 ON " + " NIVEL_USUARIO_045 = usuario.nivel_usuario AND ID_045 = 0");
		return queryFilterFrom.toString();
	}

	@Override
	public Integer isConfigHorasPrevistasAlreadyStored(ConfigHorasPrevistasProveedorExt configHorasPrevistas) {
		List<Object> params = new ArrayList<Object>();
		params.add(configHorasPrevistas.getId());
		params.add(configHorasPrevistas.getNivUsuario());
		StringBuilder queryIsConfigStored = new StringBuilder(ConfigHorasPrevistasDaoImpl.STRING_BUILDER_INIT);
		queryIsConfigStored.append(" SELECT COUNT(1) ");
		queryIsConfigStored.append(" FROM AA79B45S01 ");
		queryIsConfigStored.append(" WHERE ID_045 = ? AND NIVEL_USUARIO_045 = ? ");
		return this.getJdbcTemplate().queryForObject(queryIsConfigStored.toString(), params.toArray(), Integer.class);
	}

}
