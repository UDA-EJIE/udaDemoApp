package com.ejie.aa79b.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.AuditoriaCampoSeccionExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.AuditoriaDatosGeneralRowMapper;
import com.ejie.aa79b.dao.mapper.AuditoriaRowMapper;
import com.ejie.aa79b.dao.mapper.AuditoriaSeccionExpRowMapper;
import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.AuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaSeccionExpediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoCampoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum;
import com.ejie.aa79b.model.enums.EstadoAuditoriaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 *
 * @author eaguirresarobe
 *
 */
@Repository
@Transactional
public class AuditoriaDaoImpl extends GenericoDaoImpl<Auditoria> implements AuditoriaDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public AuditoriaDaoImpl() {
		super(Auditoria.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { "ANYONUMEXPCONCATENADO", "ESTADO013",
			"DESCES013", "DESCEU013", "ESTADODESCES", "ESTADODESCEU", "DESCNORMEU", "DESCNORMES" };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Auditoria> rwMap = new AuditoriaRowMapper();

	private RowMapper<Auditoria> rwMapAuditoriaDatosGenerales = new AuditoriaDatosGeneralRowMapper();

	private RowMapper<AuditoriaSeccionExpediente> rwMapAuditoriaSeccionExpediente = new AuditoriaSeccionExpRowMapper();

	private RowMapper<AuditoriaCampoSeccionExpediente> rwMapAuditoriaCampoSeccionExpediente = new AuditoriaCampoSeccionExpedienteRowMapper();

	private RowMapper<Auditoria> rwMapPK = new RowMapper<Auditoria>() {
		@Override
		public Auditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Auditoria auditoria = new Auditoria();
			auditoria.setIdTarea(resultSet.getBigDecimal("ID_TAREA"));
			return auditoria;
		}
	};

	@Override()
	protected String getSelect() {
		StringBuilder select = new StringBuilder();
		select.append("SELECT ");
		select.append(
				"tareaRevision.ID_TAREA_081 AS ID_TAREA, DESC_ES_015 AS TIPO_TAREA_ES, DESC_EU_015 AS TIPO_TAREA_EU, ");
		select.append(
				"ANYO_051 AS ANYO, NUM_EXP_051 AS NUM_EXP, SUBSTR(ANYO_051,3,4) || '/' || LPAD(NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO, ");
		select.append(
				"DNI_077 AS DNI_AUDITOR, SUFDNI_077 AS SUFDNI_AUDITOR, NOMBRE_077 AS NOMBRE_AUDITOR, APEL1_077 AS APEL1_AUDITOR, APEL2_077 AS APEL2_AUDITOR, ");
		select.append("tareaAuditar.ID_TAREA_081 AS IDTAREAAUDITAR, ");
		select.append("ID_LOTE_029 AS ID_LOTE, DESC_LOTE_ES_029 AS DESC_LOTE_ES, DESC_LOTE_EU_029 AS DESC_LOTE_EU, ");
		select.append("ID_0C2 AS ID_AUDITORIA, NVL(ESTADO_AUDITORIA_0C2, 1) AS ESTADO_AUDITORIA, ");
		select.append("FECHA_AUDITORIA_0C2 AS FECHA_ENVIO, FECHA_CONFIRMACION_0C2 AS FECHA_CONFIRMACION ");
		return select.toString();
	}

	@Override()
	protected String getFrom() {
		StringBuilder select = new StringBuilder();
		select.append("FROM AA79B81S01 tareaRevision ");
		select.append("JOIN AA79B15S01 ON tareaRevision.ID_TIPO_TAREA_081 = ID_015 ");
		select.append(
				"JOIN AA79B51S01 ON tareaRevision.NUM_EXP_081 = NUM_EXP_051 AND tareaRevision.ANYO_081 = ANYO_051 ");
		select.append(
				"JOIN AA79B81S01 tareaAuditar ON ((tareaRevision.ID_TAREA_REL_081 = tareaAuditar.ID_TAREA_081 OR (tareaRevision.ANYO_081 = tareaAuditar.ANYO_081 ");
		select.append(
				"AND tareaRevision.NUM_EXP_081 = tareaAuditar.NUM_EXP_081 AND tareaAuditar.ID_TIPO_TAREA_081 = 4 AND tareaAuditar.ORDEN_081 < tareaRevision.ORDEN_081)) ");
		select.append("AND tareaAuditar.RECURSO_ASIGNACION_081 = 'P' AND tareaAuditar.ESTADO_EJECUCION_081 = 3) ");
		select.append("JOIN AA79B29S01 ON tareaAuditar.ID_LOTE_081 = ID_LOTE_029 ");
		select.append(
				"LEFT JOIN AA79BC2S01 ON tareaAuditar.NUM_EXP_081 = NUM_EXP_0C2 AND tareaAuditar.ANYO_081 = ANYO_0C2 ");
		select.append("AND tareaAuditar.ID_TAREA_081 = ID_TAREA_AUDITAR_0C2 ");
		select.append("JOIN AA79B77S01 ON nvl(DNI_AUDITOR_0C2, tareaRevision.DNI_RECURSO_081) = DNI_077 ");
		return select.toString();
	}

	@Override()
	protected RowMapper<Auditoria> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return AuditoriaDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return "tareaRevision.ID_TAREA_081";
	}

	@Override()
	protected RowMapper<Auditoria> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(Auditoria bean, List<Object> params) {
		params.add(bean.getIdTarea());
		return " WHERE tareaRevision.ID_TAREA_081 = ?";
	}

	@Override
	protected String getWhere(Auditoria bean, List<Object> params) {
		StringBuilder where = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_051,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));

		where.append(SqlUtils.generarWhereIgual("NUM_EXP_051", bean.getNumExp(), params));

		where.append(SqlUtils.generarWhereIgual("ID_TIPO_EXPEDIENTE_051", bean.getIdTipoExpediente(), params));

		where.append(SqlUtils.generarWhereIgual("NVL(ESTADO_AUDITORIA_0C2, 1)", bean.getEstado(), params));

		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			where.append(SqlUtils.generarWhereIn("DNI_077", bean.getFiltroDatos(), params));
		}

		if (bean.getLotes() != null) {
			where.append(SqlUtils.generarWhereIgual("ID_LOTE_029", bean.getLotes().getIdLote(), params));
		}

		if (bean.getLotes() != null && bean.getLotes().getEmpresasProveedoras() != null) {
			where.append(SqlUtils.generarWhereIgual("ID_EMPRESA_PROV_029",
					bean.getLotes().getEmpresasProveedoras().getCodigo(), params));
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();

	}

	@Override
	protected String getWhereLike(Auditoria bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		where.append(GenericoDaoImpl.DEFAULT_WHERE);
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_051,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));

		where.append(SqlUtils.generarWhereIgual("NUM_EXP_051", bean.getNumExp(), params));

		where.append(SqlUtils.generarWhereIgual("ID_TIPO_EXPEDIENTE_051", bean.getIdTipoExpediente(), params));

		where.append(SqlUtils.generarWhereIgual("NVL(ESTADO_AUDITORIA_0C2, 1)", bean.getEstado(), params));

		if (null != bean.getFiltroDatos() && Constants.CERO < bean.getFiltroDatos().length) {
			where.append(SqlUtils.generarWhereIn("DNI_077", bean.getFiltroDatos(), params));
		}

		if (bean.getLotes() != null) {
			where.append(SqlUtils.generarWhereIgual("ID_LOTE_029", bean.getLotes().getIdLote(), params));
		}

		if (bean.getLotes() != null && bean.getLotes().getEmpresasProveedoras() != null) {
			where.append(SqlUtils.generarWhereIgual("ID_EMPRESA_PROV_029",
					bean.getLotes().getEmpresasProveedoras().getCodigo(), params));
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override()
	public List<Auditoria> filterAuditoria(Auditoria auditoriaFilter, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean orderNombre) {
		StringBuilder paginatedQuery = new StringBuilder();
		StringBuilder query = new StringBuilder(this.getSelect());
		List<Object> params = new ArrayList<Object>();
		query.append(this.getFrom());
		query.append(this.getWhereLike(auditoriaFilter, startsWith, params, false));
		// tareas de tipo entrega cliente revision (20) o revisar traduccion(21)
		query.append(SqlUtils.generarWhereIn("tarearevision.id_tipo_tarea_081",
				Arrays.asList(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue(),
						TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue()),
				params));
		// estado de tarea a revisar ejecutada
		query.append(SqlUtils.generarWhereIgual("tarearevision.estado_ejecucion_081",
				EstadoEjecucionTareaEnum.EJECUTADA.getValue(), params));

		if (!orderNombre) {
			return auditoriaFiltroOrder(jqGridRequestDto, paginatedQuery, query, params);
		} else {
			query.append(" ORDER BY");
			query.append(" NOMBRE_AUDITOR ASC, APEL1_AUDITOR ASC, APEL2_AUDITOR ASC");
			return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
		}

	}

	private List<Auditoria> auditoriaFiltroOrder(JQGridRequestDto jqGridRequestDto, StringBuilder paginatedQuery,
			StringBuilder query, List<Object> params) {
		if (jqGridRequestDto != null) {
			if (jqGridRequestDto.getSidx() != null) {
				appendOrderBy(jqGridRequestDto, query);
			}

			Long rows = jqGridRequestDto.getRows();
			Long page = jqGridRequestDto.getPage();

			if ((page != null) && (rows != null)) {
				paginatedQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query + ")a) where rnum > "
						+ rows.longValue() * (page.longValue() - 1L) + " and rnum < "
						+ (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				paginatedQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query
						+ ")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			} else {
				paginatedQuery.append(query);
			}
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMap, params.toArray());
		} else {
			return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
		}
	}

	private void appendOrderBy(JQGridRequestDto jqGridRequestDto, StringBuilder query) {
		if (jqGridRequestDto.getSidx().split(",").length > 2) {
			query.append(" ORDER BY");
			query.append(" NOMBRE_AUDITOR ASC, APEL1_AUDITOR ASC, APEL2_AUDITOR ASC");
		} else {
			query.append(" ORDER BY");
			query.append(" NOMBRE_AUDITOR ASC, APEL1_AUDITOR ASC, APEL2_AUDITOR ASC");
			query.append(" , " + jqGridRequestDto.getSidx() + " ");
			query.append(jqGridRequestDto.getSord());
			if (jqGridRequestDto.getSidx().toUpperCase().contains("FECHA")) {
				// si ordenamos por fecha, los registros que tengan esa fecha a nulo iran al
				// final
				query.append(" NULLS LAST ");
			}
		}
	}

	@Override()
	public Long filterAuditoriaCount(Auditoria bean, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("" + DBConstants.SELECT + " COUNT(1)");
		query.append(this.getFrom(bean, params));
		query.append(this.getWhereLike(bean, startsWith, params, false));
		// tareas de tipo entrega cliente revision (20) o revisar traduccion(21)
		query.append(SqlUtils.generarWhereIn("tarearevision.id_tipo_tarea_081",
				Arrays.asList(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue(),
						TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue()),
				params));
		// estado de tarea a revisar ejecutada
		query.append(SqlUtils.generarWhereIgual("tarearevision.estado_ejecucion_081",
				EstadoEjecucionTareaEnum.EJECUTADA.getValue(), params));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Long llamarPLCrearEstructuraAuditoria(final Auditoria auditoria) {

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{? = call CREAR_ESTRUCTURA_AUDITORIA(?,?,?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, auditoria.getAnyo());
				callableStatement.setInt(3, auditoria.getNumExp());
				callableStatement.setBigDecimal(4, auditoria.getIdTarea());
				callableStatement.setBigDecimal(5, auditoria.getIdTareaAuditar());
				return callableStatement;
			}
		}, new CallableStatementCallback<Long>() {
			@Override
			public Long doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getLong(1);
			}
		});
	}

	@Override
	public Auditoria getDatosGeneralesAuditoria(Auditoria auditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		query.append(" SELECT ");
		query.append(" t1.ID_0C2 AS IDAUDITORIA, ");
		query.append(" t1.ANYO_0C2 AS ANYO, ");
		query.append(" t1.NUM_EXP_0C2  AS NUMEXP, ");
		query.append(" t1.IND_ENVIADO_0C2  AS INDENVIADO, ");
		query.append(" t1.ID_TAREA_AUDITAR_0C2 IDTAREAAUDITAR, ");
		query.append(" t1.ID_TAREA_REVISION_0C2 IDTAREAREVISION, ");
		query.append(" t2.DNI_077 AS DNIAUDITOR, ");
		query.append(" t2.PREDNI_077 AS PREDNIAUDITOR, ");
		query.append(" t2.SUFDNI_077 AS SUFDNIAUDITOR, ");
		query.append(" t2.TIPIDEN_077 AS TIPIDENAUDITOR, ");
		query.append(" t2.NOMBRE_077 AS NOMBREAUDITOR, ");
		query.append(" t2.APEL1_077 AS APEL1AUDITOR, ");
		query.append(" t2.APEL2_077 AS APEL2AUDITOR, ");
		query.append(" t1.ESTADO_AUDITORIA_0C2 AS ESTADOAUDITORIA ");
		query.append(DAOUtils.getDecodeAcciones("t1.ESTADO_AUDITORIA_0C2", "ESTADOAUDITORIADESCEU", this.msg,
				"EstadoAuditoriaEnum", eu));
		query.append(DAOUtils.getDecodeAcciones("t1.ESTADO_AUDITORIA_0C2", "ESTADOAUDITORIADESCES", this.msg,
				"EstadoAuditoriaEnum", es));
		query.append(" ,t1.FECHA_AUDITORIA_0C2 AS FECHAAUDITORIA, ");
		query.append(" t1.FECHA_CONFIRMACION_0C2 AS FECHACONFIRMACIONAUDITORIA, ");
		query.append(" t4.ID_LOTE_029 AS IDLOTE, ");
		query.append(" t4.DESC_LOTE_ES_029 AS DESCLOTEES, ");
		query.append(" t4.DESC_LOTE_EU_029 AS DESCLOTEEU, ");
		query.append(" t4.DNI_CONTACTO_029 AS DNICONTACTOLOTE ");
		query.append(" FROM AA79BC2S01 t1 ");
		query.append(" JOIN AA79B77S01 t2 ON t1.DNI_AUDITOR_0C2 = t2.DNI_077 ");
		query.append(" JOIN AA79B81S01 t3 ON t1.ID_TAREA_AUDITAR_0C2 = t3.ID_TAREA_081 ");
		query.append(" JOIN AA79B29S01 t4 ON t3.ID_LOTE_081 = t4.ID_LOTE_029 ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_0C2", auditoria.getIdAuditoria(), params));
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(),
				this.rwMapAuditoriaDatosGenerales);
	}

	@Override
	public List<AuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		query.append(" SELECT ");
		query.append(" t1.ID_AUDITORIA_0C3 AS IDAUDITORIA, ");
		query.append(" t1.ID_SECCION_0C3 AS IDSECCION, ");
		query.append(" t1.TIPO_SECCION_0C3 AS TIPOSECCION, ");
		query.append(" t1.IND_RESPUESTA_0C3 AS INDRESPUESTA, ");
		query.append(" t1.NOMBRE_EU_0C3 AS NOMBREEU, ");
		query.append(" t1.IND_OBSERVACIONES_0C3 AS INDOBSERVACIONES, ");
		query.append(" t1.ORDEN_0C3 AS ORDEN, ");
		query.append(" t1.VALOR_MIN_APROBADO_0C3 AS VALORMINAPROBADO, ");
		query.append(" t1.VALOR_MIN_PELIGRO_0C3 AS VALORMINPELIGRO, ");
		query.append(" t1.RESULTADO_AUDITORIA_0C3 AS RESULTADOAUDITORIA, ");
		query.append(" t1.NOTA_AUDITORIA_0C3 AS NOTAAUDITORIA, ");
		query.append(" t1.OBSERV_0C3 AS OBSERV, ");
		query.append(" COUNT(t2.ID_CAMPO_0C4) AS CAMPOSCOUNT ");
		query.append(" FROM AA79BC3S01 t1 ");
		query.append(" LEFT JOIN AA79BC4S01 t2 ");
		query.append(" ON t1.ID_AUDITORIA_0C3 = t2.ID_AUDITORIA_0C4 ");
		query.append(" AND t1.ID_SECCION_0C3 = t2.ID_SECCION_PADRE_0C4 ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C3", auditoria.getIdAuditoria(), params));
		query.append(" GROUP BY ");
		query.append(" t1.ID_AUDITORIA_0C3, ");
		query.append(" t1.ID_SECCION_0C3, ");
		query.append(" t1.TIPO_SECCION_0C3, ");
		query.append(" t1.IND_RESPUESTA_0C3, ");
		query.append(" t1.NOMBRE_EU_0C3, ");
		query.append(" t1.IND_OBSERVACIONES_0C3, ");
		query.append(" t1.ORDEN_0C3, ");
		query.append(" t1.VALOR_MIN_APROBADO_0C3, ");
		query.append(" t1.VALOR_MIN_PELIGRO_0C3, ");
		query.append(" t1.RESULTADO_AUDITORIA_0C3, ");
		query.append(" t1.NOTA_AUDITORIA_0C3, ");
		query.append(" t1.OBSERV_0C3 ");
		query.append(" ORDER BY INDRESPUESTA ASC, ORDEN ASC ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapAuditoriaSeccionExpediente, params.toArray());
	}

	@Override
	public List<AuditoriaCampoSeccionExpediente> filterCamposSeccion(AuditoriaSeccionExpediente seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		getCamposSeccionExpedienteAuditoriaQuery(seccionFilter, params, query, false);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapAuditoriaCampoSeccionExpediente,
				params.toArray());
	}

	@Override
	public Long filterCamposSeccionCount(AuditoriaSeccionExpediente seccionFilter, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		getCamposSeccionExpedienteAuditoriaQuery(seccionFilter, params, query, true);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}


	private void getCamposSeccionExpedienteAuditoriaQuery(AuditoriaSeccionExpediente auditDocSecExp, List<Object> params,
			StringBuilder query, Boolean isCount) {
		if (isCount) {
			query.append(" SELECT COUNT(1)");
		} else {
			query.append(" SELECT ");
			query.append(" t1.ID_AUDITORIA_0C4 AS IDAUDITORIA, ");
			query.append(" t1.ID_CAMPO_0C4 AS IDCAMPO, ");
			query.append(" t1.TIPO_CAMPO_0C4 AS TIPOCAMPO, ");
			query.append(" t1.NOMBRE_EU_0C4 AS NOMBREEU, ");
			query.append(" t1.IND_OBSERVACIONES_0C4 AS INDOBSERVACIONES, ");
			query.append(" t1.NOTA_OK_0C4 AS NOTAOK, ");
			query.append(" t1.NOTA_NO_OK_0C4 AS NOTANOOK, ");
			query.append(" t1.POR_NIVEL_0_0C4 AS PORNIVEL0, ");
			query.append(" t1.POR_NIVEL_1_0C4 AS PORNIVEL1, ");
			query.append(" t1.POR_NIVEL_3_0C4 AS PORNIVEL3, ");
			query.append(" t1.POR_NIVEL_5_0C4 AS PORNIVEL5, ");
			query.append(" t1.IND_OBLIGATORIO_0C4 AS INDOBLIGATORIO, ");
			query.append(" t1.ID_SECCION_PADRE_0C4 AS IDSECCIONPADRE, ");
			query.append(" t1.ORDEN_0C4 AS ORDEN, ");
			query.append(" t1.OBSERV_0C4 AS OBSERV, ");
			query.append(" t1.NIVEL_CALIDAD_0C4 AS NIVELCALIDAD, ");
			query.append(" t1.POR_NIVEL_CALIDAD_0C4 AS PORNIVELCALIDAD, ");
			query.append(" t1.IND_MARCADO_0C4 AS INDMARCADO ");
		}

		query.append(" FROM AA79BC4S01 t1 ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C4", auditDocSecExp.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_PADRE_0C4", auditDocSecExp.getIdSeccion(), params));
		if (!isCount) {
			query.append(" ORDER BY ORDEN ASC");
		}
	}

	@Override
	public Auditoria guardarDatosGeneralesAuditoria(Auditoria auditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		query.append("UPDATE AA79BC2S01 SET IND_ENVIADO_0C2 = ? ");
		params.add(auditoria.getIndEnviado());
		if (ActivoEnum.SI.getValue().equalsIgnoreCase(auditoria.getIndEnviado())) {
			// si ind enviado, el estado de la auditoria pasa a enviado e introducimos la
			// fecha actual
			auditoria.setEstado(EstadoAuditoriaEnum.ENVIADA.getValue());
			auditoria.setFechaEnvio(new Date());
			Locale es = new Locale("es");
			Locale eu = new Locale("eu");
			auditoria.setEstadoDescEs(this.msg.getMessage(EstadoAuditoriaEnum.ENVIADA.getLabel(), null, es));
			auditoria.setEstadoDescEu(this.msg.getMessage(EstadoAuditoriaEnum.ENVIADA.getLabel(), null, eu));
			query.append(", ESTADO_AUDITORIA_0C2 = ? ");
			query.append(", FECHA_AUDITORIA_0C2 = ? ");
			params.add(auditoria.getEstado());
			params.add(auditoria.getFechaEnvio());
		}
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_0C2", auditoria.getIdAuditoria(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return auditoria;
	}

	@Override
	public AuditoriaSeccionExpediente guardarDatosSeccion(AuditoriaSeccionExpediente seccion) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		boolean prevSet = false;
		query.append(" UPDATE AA79BC3S01 SET ");
		if (AuditoriaTipoSeccionEnum.VALORACION.getValue().equals(seccion.getTipoSeccion())) {
			query.append(" RESULTADO_AUDITORIA_0C3 = ? ");
			params.add(seccion.getResulAuditoria());
			prevSet = true;
		}
		if (ActivoEnum.SI.getValue().equalsIgnoreCase(seccion.getIndObservaciones())) {
			query.append(prevSet ? "," : "");
			query.append(" OBSERV_0C3 = ? ");
			params.add(seccion.getObserv());
		}
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_AUDITORIA_0C3", seccion.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("ID_SECCION_0C3", seccion.getIdSeccion(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_SECCION_0C3", seccion.getTipoSeccion(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return seccion;
	}

	@Override
	public AuditoriaCampoSeccionExpediente guardarDatosCampo(AuditoriaCampoSeccionExpediente campo) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(AuditoriaDaoImpl.STRING_BUILDER_INIT);
		boolean prevSet = false;
		query.append(" UPDATE AA79BC4S01 SET ");
		if (AuditoriaTipoCampoEnum.VALORACION.getValue().equals(campo.getTipoCampo())) {
			query.append(" POR_NIVEL_CALIDAD_0C4 = ? ");
			params.add(campo.getPorNivelCalidad());
			prevSet = true;
		} else if (AuditoriaTipoCampoEnum.CONDICION.getValue().equals(campo.getTipoCampo())) {
			query.append(" IND_MARCADO_0C4 = ? ");
			params.add(campo.getIndMarcado());
			prevSet = true;
		}
		if (ActivoEnum.SI.getValue().equalsIgnoreCase(campo.getIndObservaciones())) {
			query.append(prevSet ? "," : "");
			query.append(" OBSERV_0C4 = ? ");
			params.add(campo.getObserv());
		}
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_AUDITORIA_0C4", campo.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("ID_CAMPO_0C4", campo.getIdCampo(), params));
		query.append(SqlUtils.generarWhereIgual("ID_SECCION_PADRE_0C4", campo.getIdSeccionPadre(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_CAMPO_0C4", campo.getTipoCampo(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return campo;
	}

}
