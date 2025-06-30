package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.PesosValoracionAuditoria;

@Repository
@Transactional
public class PesosValoracionAuditoriaDaoImpl extends GenericoDaoImpl<PesosValoracionAuditoria>
		implements PesosValoracionAuditoriaDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public PesosValoracionAuditoriaDaoImpl() {
		super(PesosValoracionAuditoria.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { "ID", "DESCEU", "PORNIVEL0", "PORNIVEL1",
			"PORNIVEL3", "PORNIVEL5", "ESTADO", "ESTADODESCES", "ESTADODESCEU" };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<PesosValoracionAuditoria> rwMap = new RowMapper<PesosValoracionAuditoria>() {
		@Override
		public PesosValoracionAuditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			PesosValoracionAuditoria pesosValoracionAuditoria = new PesosValoracionAuditoria(resultSet.getLong("ID"),
					resultSet.getString("DESCEU"), resultSet.getBigDecimal("PORNIVEL0"),
					resultSet.getBigDecimal("PORNIVEL1"), resultSet.getBigDecimal("PORNIVEL3"),
					resultSet.getBigDecimal("PORNIVEL5"), resultSet.getString("ESTADO"));
			pesosValoracionAuditoria.setDescEstadoEs(resultSet.getString("ESTADODESCES"));
			pesosValoracionAuditoria.setDescEstadoEu(resultSet.getString("ESTADODESCEU"));
			return pesosValoracionAuditoria;
		}
	};

	private RowMapper<PesosValoracionAuditoria> rwMapPK = new RowMapper<PesosValoracionAuditoria>() {
		@Override
		public PesosValoracionAuditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new PesosValoracionAuditoria(resultSet.getLong("ID"), resultSet.getString("DESCEU"),
					resultSet.getBigDecimal("PORNIVEL0"), resultSet.getBigDecimal("PORNIVEL1"),
					resultSet.getBigDecimal("PORNIVEL3"), resultSet.getBigDecimal("PORNIVEL5"),
					resultSet.getString("ESTADO"));
		}
	};

	@Override
	public PesosValoracionAuditoria add(PesosValoracionAuditoria pesosValoracionAuditoria) {

		String queryID = "SELECT AA79BB9Q00.NEXTVAL from dual";
		long elId = this.getJdbcTemplate().queryForObject(queryID, Long.class);

		String query = "INSERT INTO AA79BB9S01 (ID_0B9, DESC_EU_0B9, POR_NIVEL_0_0B9, POR_NIVEL_1_0B9, POR_NIVEL_3_0B9, POR_NIVEL_5_0B9, ESTADO_0B9) VALUES (?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, elId, pesosValoracionAuditoria.getDescEu(),
				pesosValoracionAuditoria.getPorNivel0(), pesosValoracionAuditoria.getPorNivel1(),
				pesosValoracionAuditoria.getPorNivel3(), pesosValoracionAuditoria.getPorNivel5(),
				pesosValoracionAuditoria.getEstado());
		return pesosValoracionAuditoria;
	}

	@Override
	public PesosValoracionAuditoria update(PesosValoracionAuditoria pesosValoracionAuditoria) {
		String query = "UPDATE AA79BB9S01 SET DESC_EU_0B9=?, POR_NIVEL_0_0B9=?, POR_NIVEL_1_0B9=?, POR_NIVEL_3_0B9=?, POR_NIVEL_5_0B9=?, ESTADO_0B9=? WHERE ID_0B9=?";
		this.getJdbcTemplate().update(query, pesosValoracionAuditoria.getDescEu(),
				pesosValoracionAuditoria.getPorNivel0(), pesosValoracionAuditoria.getPorNivel1(),
				pesosValoracionAuditoria.getPorNivel3(), pesosValoracionAuditoria.getPorNivel5(),
				pesosValoracionAuditoria.getEstado(), pesosValoracionAuditoria.getId());
		return pesosValoracionAuditoria;
	}

	@Override
	protected String getSelect() {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		StringBuilder select = new StringBuilder();

		select.append("SELECT t1.ID_0B9 ID");
		select.append(", t1.DESC_EU_0B9 DESCEU");
		select.append(", t1.POR_NIVEL_0_0B9 PORNIVEL0");
		select.append(", t1.POR_NIVEL_1_0B9 PORNIVEL1");
		select.append(", t1.POR_NIVEL_3_0B9 PORNIVEL3");
		select.append(", t1.POR_NIVEL_5_0B9 PORNIVEL5");
		select.append(", t1.ESTADO_0B9 ESTADO");
		select.append(", DECODE(t1.ESTADO_0B9, 'A', '").append(this.msg.getMessage("estado.alta", null, es))
				.append("', 'B', '").append(this.msg.getMessage("estado.baja", null, es)).append("') AS ESTADODESCES");
		select.append(", DECODE(t1.ESTADO_0B9, 'A', '").append(this.msg.getMessage("estado.alta", null, eu))
				.append("', 'B', '").append(this.msg.getMessage("estado.baja", null, eu)).append("') AS ESTADODESCEU");
		return select.toString();
	}

	@Override()
	protected String getFrom() {
		return " FROM AA79BB9S01 t1";
	}

	@Override()
	protected RowMapper<PesosValoracionAuditoria> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return PesosValoracionAuditoriaDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return "ID_0B9";
	}

	@Override()
	protected RowMapper<PesosValoracionAuditoria> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(PesosValoracionAuditoria bean, List<Object> params) {
		params.add(bean.getId());
		return " WHERE t1.ID_0B9 = ?";
	}

	@Override
	protected String getWhere(PesosValoracionAuditoria bean, List<Object> params) {
		StringBuilder where = new StringBuilder(PesosValoracionAuditoriaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0B9", bean.getId(), params));

		where.append(SqlUtils.generarWhereIgual("t1.DESC_EU_0B9", bean.getDescEu(), params));

		where.append(SqlUtils.generarWhereIgual("t1.ESTADO_0B9", bean.getEstado(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(PesosValoracionAuditoria bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		StringBuilder where = new StringBuilder(PesosValoracionAuditoriaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0B9", bean.getId(), params));

		where.append(SqlUtils.generarWhereIgual("t1.DESC_EU_0B9", bean.getDescEu(), params));

		where.append(SqlUtils.generarWhereIgual("t1.ESTADO_0B9", bean.getEstado(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}
}
