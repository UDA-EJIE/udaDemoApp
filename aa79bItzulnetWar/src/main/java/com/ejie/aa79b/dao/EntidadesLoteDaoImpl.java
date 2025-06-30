/**
 *
 */
package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.EntidadesLote;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.TipoEntidadEnum;

/**
 * @author eaguirresarobe
 *
 */

@Repository()
@Transactional
public class EntidadesLoteDaoImpl extends GenericoDaoImpl<EntidadesLote>
								  implements EntidadesLoteDao  {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public EntidadesLoteDaoImpl() {
		super(EntidadesLote.class);
	}

	public static final String[] ORDER_BY_WHITE_LIST = new String[] { "IDLOTE", "IDENTIDAD", DBConstants.DESCES,
			DBConstants.DESCEU, "TIPODESC" };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<EntidadesLote> rwMap = new RowMapper<EntidadesLote>() {
		@Override
		public EntidadesLote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			EntidadesLote bean = new EntidadesLote();
			bean.setIdLote(resultSet.getLong("IDLOTE"));
			bean.setTipo(resultSet.getString("TIPOENTIDAD"));
			bean.setTipoDesc(resultSet.getString("TIPODESC"));
			bean.setCodigo(resultSet.getInt("IDENTIDAD"));
			bean.setDescEs(resultSet.getString(DBConstants.DESCES));
			bean.setDescEu(resultSet.getString(DBConstants.DESCEU));
			bean.setFacturableDesc(resultSet.getString("FACTURABLEDESC"));
			bean.setIvaDesc(resultSet.getString("IVADESC"));
			return bean;
		}
	};

	private RowMapper<EntidadesLote> rwMapPK = new RowMapper<EntidadesLote>() {
		@Override
		public EntidadesLote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			EntidadesLote bean = new EntidadesLote();
			bean.setCodigoCompleto(resultSet.getString(1));
			return bean;
		}
	};

	private RowMapper<EntidadesLote> rwMapComentarioEntidad = new RowMapper<EntidadesLote>() {
		@Override
		public EntidadesLote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			EntidadesLote bean = new EntidadesLote();
			bean.setTipo(resultSet.getString("TIPOENTIDAD"));
			bean.setCodigo(resultSet.getInt("IDENTIDAD"));
			bean.setComentarioEntidad(resultSet.getString("COMENTARIOENTIDAD"));
			return bean;
		}
	};

	@Override()
	public EntidadesLote add(EntidadesLote entidadesLote) {
		String query = "INSERT INTO AA79BD2S01 (ID_LOTE_0D2, TIPO_ENTIDAD_0D2, ID_ENTIDAD_0D2) VALUES (?,?,?)";
		this.getJdbcTemplate().update(query, entidadesLote.getIdLote(), entidadesLote.getTipo(),
				entidadesLote.getCodigo());
		return entidadesLote;
	}

	@Override()
	public void remove(EntidadesLote entidadesLote) {
		String query = "DELETE FROM AA79BD2S01 WHERE ID_LOTE_0D2=? AND TIPO_ENTIDAD_0D2 = ? AND ID_ENTIDAD_0D2 = ?";
		this.getJdbcTemplate().update(query, entidadesLote.getIdLote(), entidadesLote.getTipo(),
				entidadesLote.getCodigo());
	}

	@Override()
	public void removeWhereID(EntidadesLote entidadesLote) {
		String query = "DELETE FROM AA79BD2S01 WHERE ID_LOTE_0D2=?";
		this.getJdbcTemplate().update(query, entidadesLote.getIdLote());
	}

	@Override
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder selectEntLote = new StringBuilder(EntidadesLoteDaoImpl.STRING_BUILDER_INIT);
		selectEntLote.append(" SELECT t2.ID_LOTE_0D2 IDLOTE,");
		selectEntLote.append(" t1.TIPO TIPOENTIDAD,");
		selectEntLote.append(" t1.CODIGO IDENTIDAD,");
		selectEntLote.append(" t1.DESC_ES DESCES,");
		selectEntLote.append(" t1.DESC_EU DESCEU,");
		selectEntLote.append(" DECODE(t1.TIPO");
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			selectEntLote.append(", '").append(tipo.getValue()).append("'");
			selectEntLote.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		selectEntLote.append(") AS TIPODESC,");
		selectEntLote.append(" t1.FACTURABLE FACTURABLE,");
		selectEntLote.append(" DECODE(t1.FACTURABLE, '").append(ActivoEnum.SI.getValue()).append("', '")
			.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)).append("', '")
			.append(ActivoEnum.NO.getValue()).append("', '")
			.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)).append("') AS FACTURABLEDESC,");
		selectEntLote.append(" t1.IVA IVA,");
		selectEntLote.append(" DECODE(t1.IVA, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)).append("') AS IVADESC ");
		return selectEntLote.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder fromEntLote = new StringBuilder(EntidadesLoteDaoImpl.STRING_BUILDER_INIT);
		fromEntLote.append(" FROM X54JAPI_ENTIDADES_SOLIC t1");
		fromEntLote.append(" JOIN AA79BD2S01 t2 ON t1.TIPO = t2.TIPO_ENTIDAD_0D2");
		fromEntLote.append(" AND t1.CODIGO = t2.ID_ENTIDAD_0D2 ");
		return fromEntLote.toString();
	}

	@Override
	protected RowMapper<EntidadesLote> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return "IDLOTE || '_' || TIPOENTIDAD || '_' || IDENTIDAD";
	}

	@Override
	protected RowMapper<EntidadesLote> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(EntidadesLote bean, List<Object> params) {
		params.add(bean.getIdLote());
		params.add(bean.getTipo());
		params.add(bean.getCodigo());
		return " WHERE t2.ID_LOTE_0D2 = ? AND t2.TIPO_ENTIDAD_0D2 = ? AND t2.ID_ENTIDAD_0D2 = ?";
	}

	@Override
	protected String getWhere(EntidadesLote bean, List<Object> params) {
		StringBuilder where = new StringBuilder(EntidadesGruposTrabajoDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual("t2.ID_LOTE_0D2", bean.getIdLote(), params));
		where.append(SqlUtils.generarWhereIgual("t2.TIPO_ENTIDAD_0D2", bean.getTipo(), params));
		where.append(SqlUtils.generarWhereIgual("t2.ID_ENTIDAD_0D2", bean.getCodigo(), params));
		return where.toString();
	}

	@Override
	protected String getWhereLike(EntidadesLote bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLike = new StringBuilder(EntidadesGruposTrabajoDaoImpl.STRING_BUILDER_INIT);
		whereLike.append(SqlUtils.generarWhereIgual("t2.ID_LOTE_0D2", bean.getIdLote(), params));
		whereLike.append(SqlUtils.generarWhereIgual("t2.TIPO_ENTIDAD_0D2", bean.getTipo(), params));
		whereLike.append(SqlUtils.generarWhereIgual("t2.ID_ENTIDAD_0D2", bean.getCodigo(), params));
		return whereLike.toString();
	}

	@Override
	public Boolean entidadTieneComentario(EntidadesLote bean) {
		StringBuilder queryETC = new StringBuilder(EntidadesLoteDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsETC = new ArrayList<Object>();
		queryETC.append(" SELECT COUNT(1) FROM AA79BD3S01 t1");
		queryETC.append(" WHERE t1.TIPO_ENTIDAD_0D3 = ?");
		queryETC.append(" AND t1.ID_ENTIDAD_0D3 = ?");
		paramsETC.add(bean.getTipo());
		paramsETC.add(bean.getCodigo());
		return this.getJdbcTemplate().queryForObject(queryETC.toString(), Integer.class, paramsETC.toArray()) > 0;
	}

	@Override
	public Integer anyadirComentarioEntidad(EntidadesLote bean) {
		StringBuilder queryACE = new StringBuilder(EntidadesLoteDaoImpl.STRING_BUILDER_INIT);
		queryACE.append(" INSERT INTO AA79BD3S01 (TIPO_ENTIDAD_0D3,ID_ENTIDAD_0D3,OBSERV_0D3) VALUES (?,?,?)");
		return this.getJdbcTemplate().update(queryACE.toString(), bean.getTipo(), bean.getCodigo(),
				bean.getComentarioEntidad());
	}

	@Override
	public Integer modificarComentarioEntidad(EntidadesLote bean) {
		StringBuilder queryMCE = new StringBuilder(EntidadesLoteDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsMCE = new ArrayList<Object>();
		queryMCE.append(" UPDATE AA79BD3S01 SET OBSERV_0D3 = ? ");
		queryMCE.append(" WHERE TIPO_ENTIDAD_0D3 = ?");
		queryMCE.append(" AND ID_ENTIDAD_0D3 = ?");
		paramsMCE.add(bean.getComentarioEntidad());
		paramsMCE.add(bean.getTipo());
		paramsMCE.add(bean.getCodigo());
		return this.getJdbcTemplate().update(queryMCE.toString(), paramsMCE.toArray());

	}

	@Override
	public EntidadesLote findComentarioEntidad(EntidadesLote bean) {
		StringBuilder queryETC = new StringBuilder(EntidadesLoteDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsETC = new ArrayList<Object>();
		queryETC.append(" SELECT t1.ID_ENTIDAD_0D3 IDENTIDAD,");
		queryETC.append(" t1.TIPO_ENTIDAD_0D3 TIPOENTIDAD,");
		queryETC.append(" t1.OBSERV_0D3 COMENTARIOENTIDAD");
		queryETC.append(" FROM AA79BD3S01 t1 ");
		queryETC.append(" WHERE t1.TIPO_ENTIDAD_0D3 = ?");
		queryETC.append(" AND t1.ID_ENTIDAD_0D3 = ?");
		paramsETC.add(bean.getTipo());
		paramsETC.add(bean.getCodigo());
		List<EntidadesLote> list = this.getJdbcTemplate().query(queryETC.toString(),this.rwMapComentarioEntidad, paramsETC.toArray());
		if (list != null && !list.isEmpty() && list.size() == 1 && StringUtils.isNotBlank(list.get(0).getComentarioEntidad())) {
			// tiene comentario
			bean.setComentarioEntidad(list.get(0).getComentarioEntidad());
		}
		return bean;
	}

}
