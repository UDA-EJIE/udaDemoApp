package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.AuditoriaDocumentoSeccionExpedienteRowMapper;
import com.ejie.aa79b.model.AuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class AuditoriaDocumentosDaoImpl extends GenericoDaoImpl<AuditoriaDocumentoSeccionExpediente> implements AuditoriaDocumentosDao {

	public AuditoriaDocumentosDaoImpl() {
		super(AuditoriaDocumentoSeccionExpediente.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { "IDAUDITORIA", "IDSECCION", "IDFICHERO"};

	private RowMapper<AuditoriaDocumentoSeccionExpediente> rwMap = new RowMapper<AuditoriaDocumentoSeccionExpediente>() {
		@Override
		public AuditoriaDocumentoSeccionExpediente mapRow(ResultSet rs, int arg1) throws SQLException {
			AuditoriaDocumentoSeccionExpediente docAuditoria = new AuditoriaDocumentoSeccionExpediente();
			docAuditoria.setIdAuditoria(rs.getLong("IDAUDITORIA"));
			docAuditoria.setIdSeccion(rs.getInt("IDSECCION"));
			docAuditoria.setIdFicheroInterno(rs.getBigDecimal("IDFICHERO"));
			return docAuditoria;
		}
	};

	private RowMapper<AuditoriaDocumentoSeccionExpediente> rwMapAuditoriaDocumentoSeccionExpediente = new AuditoriaDocumentoSeccionExpedienteRowMapper();

	@Override
	protected String getSelect() {
		StringBuilder select = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		select.append("SELECT t1.ID_AUDITORIA_0C5 AS IDAUDITORIA,");
		select.append(" t1.ID_SECCION_0C5 AS IDSECCION,");
		select.append(" t1.ID_FICHERO_0C5 AS IDFICHERO ");
		return select.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		from.append(" FROM AA79BC5S01 t1 ");
		return from.toString();
	}

	@Override
	protected RowMapper<AuditoriaDocumentoSeccionExpediente> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return AuditoriaDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return "ID_AUDITORIA_0C5, ID_SECCION_0C5, ID_FICHERO_0C5";
	}

	@Override
	protected RowMapper<AuditoriaDocumentoSeccionExpediente> getRwMapPK() {
		return this.rwMap;
	}

	@Override
	protected String getWherePK(AuditoriaDocumentoSeccionExpediente bean, List<Object> params) {
		params.add(bean.getIdAuditoria());
		params.add(bean.getIdSeccion());
		params.add(bean.getIdFicheroInterno());
		StringBuilder wherePK = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		wherePK.append(" WHERE t1.ID_AUDITORIA_0C5 = ? ");
		wherePK.append(" AND t1.ID_SECCION_0C5 = ?");
		wherePK.append(" AND t1.ID_FICHERO_0C5 = ?");
		return wherePK.toString();
	}

	@Override
	protected String getWhere(AuditoriaDocumentoSeccionExpediente bean, List<Object> params) {
		return this.getWhereLike(bean, false, params, false);
	}

	@Override
	protected String getWhereLike(AuditoriaDocumentoSeccionExpediente bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		StringBuilder where = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C5", bean.getIdAuditoria(), params));
		where.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_0C5", bean.getIdSeccion(), params));
		where.append(SqlUtils.generarWhereIgual("t1.ID_FICHERO_0C5", bean.getIdFicheroInterno(), params));
		return where.toString();
	}

	@Override
	public List<AuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(
			AuditoriaDocumentoSeccionExpediente seccionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		getDocumentosSeccionExpedienteAuditoriaQuery(seccionFilter, params, query, false);
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, false, query));
		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapAuditoriaDocumentoSeccionExpediente,
				params.toArray());
	}

	@Override
	public Long filterDocumentosSeccionCount(AuditoriaDocumentoSeccionExpediente seccionFilter, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		getDocumentosSeccionExpedienteAuditoriaQuery(seccionFilter, params, query, true);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Integer removeC5(BigDecimal idFicheroInterno) {
		StringBuilder deleteC5 = new StringBuilder(AuditoriaDocumentosDaoImpl.STRING_BUILDER_INIT);
		deleteC5.append("DELETE FROM AA79BC5S01 WHERE ID_FICHERO_0C5 = ?");
		return this.getJdbcTemplate().update(deleteC5.toString(), idFicheroInterno);
	}

	/**
	 *
	 * @param seccionFilter AuditoriaDocumentoSeccionExpediente
	 * @param params List<Object>
	 * @param query StringBuilder
	 * @param isCount Boolean
	 */
	private void getDocumentosSeccionExpedienteAuditoriaQuery(AuditoriaDocumentoSeccionExpediente seccionFilter,
			List<Object> params, StringBuilder query, Boolean isCount) {
		if (isCount) {
			query.append(" SELECT COUNT(1)");
		} else {
			query.append(this.getSelect());
			query.append(" ,t2.TITULO_FICHERO_088 AS TITULOFICHERO, ");
			query.append(" t2.EXTENSION_FICHERO_088 AS EXTENSIONFICHERO, ");
			query.append(" t2.CONTENT_TYPE_FICHERO_088 AS CONTENTTYPEFICHERO, ");
			query.append(" t2.TAMANO_FICHERO_088 AS TAMANOFICHERO, ");
			query.append(" t2.IND_ENCRIPTADO_088 AS INDENCRIPTADO, ");
			query.append(" t2.RUTA_PIF_FICHERO_088 AS RUTAPIFFICHERO, ");
			query.append(" t2.OID_FICHERO_088 AS OIDFICHERO, ");
			query.append(" t2.NOMBRE_FICHERO_088 AS NOMBREFICHERO ");
		}

		query.append(this.getFrom());
		query.append(" JOIN AA79B88S01 t2 ON t1.ID_FICHERO_0C5 = t2.ID_FICHERO_088");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C5", seccionFilter.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_0C5", seccionFilter.getIdSeccion(), params));
		query.append(SqlUtils.generarWhereIgual("t1.ID_FICHERO_0C5", seccionFilter.getIdFicheroInterno(), params));
	}

	@Override
	public AuditoriaDocumentoSeccionExpediente anyadirDocAuditoria(
			AuditoriaDocumentoSeccionExpediente documentoAuditoria) {
		StringBuilder insert = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		insert.append("INSERT INTO AA79BC5S01 (ID_AUDITORIA_0C5, ID_SECCION_0C5, ID_FICHERO_0C5) VALUES (?,?,?)");
		this.getJdbcTemplate().update(insert.toString(), documentoAuditoria.getIdAuditoria(),
				documentoAuditoria.getIdSeccion(), documentoAuditoria.getIdFicheroInterno());
		return documentoAuditoria;
	}

	@Override
	public AuditoriaDocumentoSeccionExpediente obtenerDatosDocumentoAuditoria(
			AuditoriaDocumentoSeccionExpediente docAuditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		getDocumentosSeccionExpedienteAuditoriaQuery(docAuditoria, params, query, false);
		List<AuditoriaDocumentoSeccionExpediente> list = this.getJdbcTemplate().query(query.toString(), this.rwMapAuditoriaDocumentoSeccionExpediente,
				params.toArray());
		return DataAccessUtils.uniqueResult(list);
	}

}
