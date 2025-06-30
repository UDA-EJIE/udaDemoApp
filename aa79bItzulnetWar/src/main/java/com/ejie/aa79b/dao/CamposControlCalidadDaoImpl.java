package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.Aa79bCamposControlCalidadRowMapper;
import com.ejie.aa79b.model.CamposControlCalidad;
import com.ejie.aa79b.utils.DAOUtils;

/**
 * ControlCalidadDaoImpl
 * 
 */

@Repository
@Transactional
public class CamposControlCalidadDaoImpl extends GenericoDaoImpl<CamposControlCalidad>
		implements CamposControlCalidadDao {
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ID0C1, "NOMBRECAMPO",
			"TIPOCAMPOEU", "VISIBLE", "ORDEN", "SECCIONEU" };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<CamposControlCalidad> rwMap = new Aa79bCamposControlCalidadRowMapper();

	private RowMapper<CamposControlCalidad> rwMapPK = new RowMapper<CamposControlCalidad>() {
		@Override()
		public CamposControlCalidad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new CamposControlCalidad(resultSet.getLong(DBConstants.ID0C1));
		}
	};

	/**
	 * Constructor
	 */
	public CamposControlCalidadDaoImpl() {
		super(CamposControlCalidad.class);
	}

	@Override()
	public CamposControlCalidad add(CamposControlCalidad camposControlCalidad) {
		String query = "INSERT INTO AA79BC1T00 (ID_0C1, ID_SECCION_PADRE_0C1, TIPO_CAMPO_0C1, NOMBRE_EU_0C1, IND_OBSERVACIONES_0C1, IND_OBLIGATORIO_0C1, NOTA_OK_0C1, NOTA_NO_OK_0C1, ID_PESO_0C1, IND_VISIBLE_0C1, ORDEN_0C1) VALUES (AA79BC1Q00.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, camposControlCalidad.getSeccion().getId(),
				camposControlCalidad.getTipoCampo(), camposControlCalidad.getNombreCampo(),
				camposControlCalidad.getIndObservaciones() != null ? camposControlCalidad.getIndObservaciones()
						: Constants.NO,
				camposControlCalidad.getIndObligatorio() != null ? camposControlCalidad.getIndObligatorio()
						: Constants.NO,
				camposControlCalidad.getNotaOk(), camposControlCalidad.getNotaNoOk(),
				camposControlCalidad.getPeso() != null ? camposControlCalidad.getPeso().getId() : null,
				camposControlCalidad.getIndVisible() != null ? camposControlCalidad.getIndVisible() : Constants.NO,
				camposControlCalidad.getOrden());
		return camposControlCalidad;

	}

	/**
	 * Updates a single row in the ControlCalidad table.
	 *
	 * @param controlCalidad ControlCalidad
	 * @return ControlCalidad
	 */
	@Override()
	public CamposControlCalidad update(CamposControlCalidad camposControlCalidad) {
		String query = "UPDATE AA79BC1S01 SET NOMBRE_EU_0C1=?, TIPO_CAMPO_0C1=?, IND_VISIBLE_0C1=?, IND_OBSERVACIONES_0C1=?, IND_OBLIGATORIO_0C1=?, ID_PESO_0C1=?, ID_SECCION_PADRE_0C1=?, ORDEN_0C1=?, NOTA_OK_0C1=?, NOTA_NO_OK_0C1=?  WHERE ID_0C1=?";
		this.getJdbcTemplate().update(query, camposControlCalidad.getNombreCampo(), camposControlCalidad.getTipoCampo(),
				camposControlCalidad.getIndVisible() != null ? camposControlCalidad.getIndVisible() : Constants.NO,
				camposControlCalidad.getIndObservaciones() != null ? camposControlCalidad.getIndObservaciones()
						: Constants.NO,
				camposControlCalidad.getIndObligatorio() != null ? camposControlCalidad.getIndObligatorio()
						: Constants.NO,
				camposControlCalidad.getPeso().getId(), camposControlCalidad.getSeccion().getId(),
				camposControlCalidad.getOrden(), camposControlCalidad.getNotaOk(), camposControlCalidad.getNotaNoOk(),
				camposControlCalidad.getIdCampoControlCalidad());
		return camposControlCalidad;
	}

	/**
	 * The method getQuery.
	 * 
	 * @return String
	 */
	@Override
	protected String getSelect() {

		Locale eu = new Locale(Constants.LANG_EUSKERA);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.ID_0C1 ID0C1");
		sb.append(", t1.NOMBRE_EU_0C1 NOMBRECAMPO");
		sb.append(", t1.TIPO_CAMPO_0C1 TIPOCAMPO");
		sb.append(
				DAOUtils.getDecodeAcciones("t1.TIPO_CAMPO_0C1", "TIPOCAMPOEU", this.msg, "TipoCampoAuditoriaEnum", eu));
		sb.append(", t1.IND_VISIBLE_0C1 VISIBLE");
		sb.append(", t1.IND_OBSERVACIONES_0C1 INDOBSERVACIONES");
		sb.append(", t1.IND_OBLIGATORIO_0C1 OBLIGATORIO");
		sb.append(", t1.NOTA_OK_0C1 NOTAOK");
		sb.append(", t1.NOTA_NO_OK_0C1 NOTANOOK");
		sb.append(", t1.ID_PESO_0C1 IDPESO");
		sb.append(", t1.ID_SECCION_PADRE_0C1 IDSECCION");
		sb.append(", t2.nombre_eu_0c0 SECCIONEU");
		sb.append(", t1.ORDEN_0C1 ORDEN");
		return sb.toString();
	}

	@Override
	protected String getFrom() {
		return " FROM AA79BC1T00 t1 INNER JOIN AA79BC0S01 t2 on t2.id_0c0 = t1.ID_SECCION_PADRE_0C1 ";
	}

	@Override
	protected RowMapper<CamposControlCalidad> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return CamposControlCalidadDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return "ID_0C1";
	}

	@Override
	protected RowMapper<CamposControlCalidad> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(CamposControlCalidad bean, List<Object> params) {
		params.add(bean.getIdCampoControlCalidad());
		return "WHERE t1.ID_0C1 =?";
	}

	@Override
	protected String getWhere(CamposControlCalidad bean, List<Object> params) {
		StringBuilder where = new StringBuilder(TipoRelevanciaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0C1", bean.getIdCampoControlCalidad(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NOMBRE_EU_0C1", bean.getNombreCampo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.TIPO_CAMPO_0C1", bean.getTipoCampo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.IND_VISIBLE_0C1", bean.getIndVisible(), params));
		where.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_PADRE_0C1", bean.getSeccion().getId(), params));
		where.append(SqlUtils.generarWhereIgual("t1.ORDEN_0C1", bean.getOrden(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(CamposControlCalidad bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(TipoRelevanciaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0C1", bean.getIdCampoControlCalidad(), params));
		where.append(
				SqlUtils.generarWhereLikeNormalizado("t1.NOMBRE_EU_0C1", bean.getNombreCampo(), params, startsWith));
		where.append(SqlUtils.generarWhereIgual("t1.TIPO_CAMPO_0C1", bean.getTipoCampo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.IND_VISIBLE_0C1", bean.getIndVisible(), params));
		where.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_PADRE_0C1", bean.getSeccion().getId(), params));
		where.append(SqlUtils.generarWhereIgual("t1.ORDEN_0C1", bean.getOrden(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	/**
	 * @param controlCalidad ControlCalidad Bean
	 * @return List<ControlCalidad> lista de tipos.
	 */
	@Override()
	public List<CamposControlCalidad> findAllById(CamposControlCalidad controlCalidad) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom());

		if (controlCalidad.getIdCampoControlCalidad() != null) {
			query.append(" AND (t1.ID_0C1 = ?)");
			params.add(controlCalidad.getIdCampoControlCalidad());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}
}
