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

import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.BitacoraExpedienteRowMapper;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.enums.AportaSubsanacionEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;

@Repository
@Transactional
public class BitacoraExpedienteDaoImpl extends GenericoDaoImpl<BitacoraExpediente> implements BitacoraExpedienteDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String ANYO_BITACORA_EXPEDIENTE = "ANYO_059";
	protected static final String NUM_EXP_BITACORA_EXPEDIENTE = "NUM_EXP_059";
	protected static final String ID_ESTADO_BITACORA_BITACORA_EXPEDIENTE = "ID_ESTADO_BITACORA_059";
	protected static final String ID_ESTADO_EXP_BITACORA_EXPEDIENTE = "ID_ESTADO_EXP_059";
	protected static final String ESTADO_EXPEDIENTE_DESC_ES_BITACORA_EXPEDIENTE = "ESTADOEXPEDIENTEDESCES";
	protected static final String ESTADO_EXPEDIENTE_DESC_EU_BITACORA_EXPEDIENTE = "ESTADOEXPEDIENTEDESCEU";
	protected static final String ESTADO_EXPEDIENTE_DESC_ABR_ES_BITACORA_EXPEDIENTE = "ESTADOEXPEDIENTEDESCABRES";
	protected static final String ESTADO_EXPEDIENTE_DESC_ABR_EU_BITACORA_EXPEDIENTE = "ESTADOEXPEDIENTEDESCABREU";
	protected static final String ID_FASE_EXPEDIENTE_BITACORA_EXPEDIENTE = "ID_FASE_EXPEDIENTE_059";
	protected static final String FASE_EXPEDIENTE_DESC_ES_BITACORA_EXPEDIENTE = "FASEEXPEDIENTEDESCES";
	protected static final String FASE_EXPEDIENTE_DESC_EU_BITACORA_EXPEDIENTE = "FASEEXPEDIENTEDESCEU";
	protected static final String FASE_EXPEDIENTE_DESC_ABR_ES_BITACORA_EXPEDIENTE = "FASEEXPEDIENTEDESCABRES";
	protected static final String FASE_EXPEDIENTE_DESC_ABR_EU_BITACORA_EXPEDIENTE = "FASEEXPEDIENTEDESCABREU";
	protected static final String DATO_ADIC_BITACORA_EXPEDIENTE = "DATO_ADIC_059";
	protected static final String INFO_ADIC_BITACORA_EXPEDIENTE = "INFO_ADIC_059";
	protected static final String ID_MOTIVO_RECHAZO_BITACORA_EXPEDIENTE = "ID_RECHAZO_059";
	protected static final String ID_SUBSANACION_BITACORA_EXPEDIENTE = "ID_REQUERIMIENTO_059";
	protected static final String DETALLE_SUBSANACION_BITACORA_EXPEDIENTE = "DETALLE_064";
	protected static final String FECHA_REQ_SUBSANACION_BITACORA_EXPEDIENTE = "FECHA_REQ_064";
	protected static final String FECHA_LIMITE_SUBSANACION_BITACORA_EXPEDIENTE = "FECHA_LIMITE_064";
	protected static final String IND_SUBSANACION_BITACORA_EXPEDIENTE = "IND_SUBSANADO_064";
	protected static final String ESTADO_SUBSANACION_BITACORA_EXPEDIENTE = "ESTADO_SUBSANACION_064";
	protected static final String FECHA_ACEPTACION_SUBSANACION_BITACORA_EXPEDIENTE = "FECHA_ACEPTACION_064";
	protected static final String SUBSANACION_DESC_ES_BITACORA_EXPEDIENTE = "SUBSANACIONDESCES";
	protected static final String SUBSANACION_DESC_EU_BITACORA_EXPEDIENTE = "SUBSANACIONDESCEU";

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { ANYO_BITACORA_EXPEDIENTE,
			NUM_EXP_BITACORA_EXPEDIENTE, ID_ESTADO_BITACORA_BITACORA_EXPEDIENTE, ID_ESTADO_EXP_BITACORA_EXPEDIENTE,
			ESTADO_EXPEDIENTE_DESC_ES_BITACORA_EXPEDIENTE, ESTADO_EXPEDIENTE_DESC_EU_BITACORA_EXPEDIENTE,
			ESTADO_EXPEDIENTE_DESC_ABR_ES_BITACORA_EXPEDIENTE, ESTADO_EXPEDIENTE_DESC_ABR_EU_BITACORA_EXPEDIENTE,
			ID_FASE_EXPEDIENTE_BITACORA_EXPEDIENTE, FASE_EXPEDIENTE_DESC_ES_BITACORA_EXPEDIENTE,
			FASE_EXPEDIENTE_DESC_EU_BITACORA_EXPEDIENTE, FASE_EXPEDIENTE_DESC_ABR_ES_BITACORA_EXPEDIENTE,
			FASE_EXPEDIENTE_DESC_ABR_EU_BITACORA_EXPEDIENTE, DATO_ADIC_BITACORA_EXPEDIENTE,
			INFO_ADIC_BITACORA_EXPEDIENTE, ID_MOTIVO_RECHAZO_BITACORA_EXPEDIENTE, ID_SUBSANACION_BITACORA_EXPEDIENTE,
			DETALLE_SUBSANACION_BITACORA_EXPEDIENTE, FECHA_REQ_SUBSANACION_BITACORA_EXPEDIENTE,
			FECHA_LIMITE_SUBSANACION_BITACORA_EXPEDIENTE, IND_SUBSANACION_BITACORA_EXPEDIENTE,
			ESTADO_SUBSANACION_BITACORA_EXPEDIENTE, FECHA_ACEPTACION_SUBSANACION_BITACORA_EXPEDIENTE,
			SUBSANACION_DESC_ES_BITACORA_EXPEDIENTE, SUBSANACION_DESC_EU_BITACORA_EXPEDIENTE };

	public BitacoraExpedienteDaoImpl() {
		// Constructor
		super(BitacoraExpediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<BitacoraExpediente> rwMap = new BitacoraExpedienteRowMapper();

	private RowMapper<BitacoraExpediente> rwMapPK = new RowMapper<BitacoraExpediente>() {
		@Override
		public BitacoraExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new BitacoraExpediente();
		}
	};

	/**
	 * Inserts a single row in the BitacoraExpediente table.
	 *
	 * @param bitacoraExpediente
	 *            BitacoraExpediente
	 * @return BitacoraExpediente
	 */
	@Override
	public BitacoraExpediente add(BitacoraExpediente bitacoraExpediente) {
		StringBuilder sb = new StringBuilder();
		sb.append("( SELECT nvl(max(ID_ESTADO_BITACORA_059),0) + 1");
		sb.append(this.getFrom());
		sb.append(" WHERE NUM_EXP_059 = ");
		sb.append(bitacoraExpediente.getNumExp());
		sb.append(" AND ANYO_059 = ");
		sb.append(bitacoraExpediente.getAnyo());
		sb.append(" )");

		Long idEstadoBitacora = this.getJdbcTemplate().queryForObject(sb.toString(), Long.class);
		bitacoraExpediente.setIdEstadoBitacora(idEstadoBitacora);

		String query = "INSERT INTO AA79B59S01 (ANYO_059, NUM_EXP_059, ID_ESTADO_BITACORA_059, ID_ESTADO_EXP_059, "
				+ "ID_FASE_EXPEDIENTE_059, DATO_ADIC_059, INFO_ADIC_059, ID_RECHAZO_059, "
				+ "ID_REQUERIMIENTO_059, FECHA_ALTA_059, ID_ANULACION_059) " + "VALUES (?,?,?,?,?,?,?,?,?, SYSDATE,?)";
		this.getJdbcTemplate().update(query, bitacoraExpediente.getAnyo(), bitacoraExpediente.getNumExp(),
				bitacoraExpediente.getIdEstadoBitacora(), bitacoraExpediente.getEstadoExp().getId(),
				bitacoraExpediente.getFaseExp().getId(), bitacoraExpediente.getDatoAdic(),
				bitacoraExpediente.getInfoAdic(), bitacoraExpediente.getIdMotivoRechazo(),
				bitacoraExpediente.getSubsanacionExp() != null ? bitacoraExpediente.getSubsanacionExp().getId() : null,
				bitacoraExpediente.getIdMotivoAnulacion());
		return bitacoraExpediente;
	}

	/**
	 * Updates a single row in the BitacoraExpediente table.
	 *
	 * @param bitacoraExpediente
	 *            BitacoraExpediente
	 * @return BitacoraExpediente
	 */
	@Override
	public BitacoraExpediente update(BitacoraExpediente bitacoraExpediente) {
		String query = "UPDATE AA79B59S01 SET ID_ESTADO_BITACORA_059=?, ID_ESTADO_EXP_059=?,"
				+ "ID_FASE_EXPEDIENTE_059=?, DATO_ADIC_059=?, INFO_ADIC_059=?, ID_RECHAZO_059=?,"
				+ ", ID_REQUERIMIENTO_059=?, FECHA_ALTA_059=SYSDATE  WHERE ANYO_059=? AND NUM_EXP_059=?";
		this.getJdbcTemplate().update(query, bitacoraExpediente.getIdEstadoBitacora(),
				bitacoraExpediente.getEstadoExp().getId(), bitacoraExpediente.getFaseExp().getId(),
				bitacoraExpediente.getDatoAdic(), bitacoraExpediente.getInfoAdic(),
				bitacoraExpediente.getIdMotivoRechazo(), bitacoraExpediente.getSubsanacionExp().getId(),
				bitacoraExpediente.getAnyo(), bitacoraExpediente.getNumExp());
		return bitacoraExpediente;
	}

	@Override
	protected String getSelect() {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder query = new StringBuilder();

		query.append("SELECT t1.ANYO_059 ANYO_059");
		query.append(", t1.NUM_EXP_059 NUM_EXP_059");
		query.append(", t1.ID_ESTADO_BITACORA_059 ID_ESTADO_BITACORA_059");
		query.append(", t1.ID_ESTADO_EXP_059 ID_ESTADO_EXP_059");
		query.append(", t1.FECHA_ALTA_059 FECHA_ALTA_059");
		query.append(", e1.DESC_ES_060 ESTADOEXPEDIENTEDESCES");
		query.append(", e1.DESC_EU_060 ESTADOEXPEDIENTEDESCEU");
		query.append(", e1.DESC_ABR_ES_060 ESTADOEXPEDIENTEDESCABRES");
		query.append(", e1.DESC_ABR_EU_060 ESTADOEXPEDIENTEDESCABREU");
		query.append(", e1.CLASS_060 ESTADOEXPEDIENTECLASS");
		query.append(", t1.ID_FASE_EXPEDIENTE_059 ID_FASE_EXPEDIENTE_059");
		query.append(", f1.DESC_ES_061 FASEEXPEDIENTEDESCES");
		query.append(", f1.DESC_EU_061 FASEEXPEDIENTEDESCEU");
		query.append(", f1.DESC_ABR_ES_061 FASEEXPEDIENTEDESCABRES");
		query.append(", f1.DESC_ABR_EU_061 FASEEXPEDIENTEDESCABREU");
		query.append(", t1.DATO_ADIC_059 DATO_ADIC_059");
		query.append(", t1.INFO_ADIC_059 INFO_ADIC_059");
		query.append(", t1.ID_RECHAZO_059 ID_RECHAZO_059");
		query.append(", t1.ID_REQUERIMIENTO_059 ID_REQUERIMIENTO_059");
		query.append(", s1.DETALLE_064 DETALLE_064");
		query.append(", s1.FECHA_REQ_064 FECHA_REQ_064");
		query.append(", s1.FECHA_LIMITE_064 FECHA_LIMITE_064");
		query.append(", s1.IND_SUBSANADO_064 IND_SUBSANADO_064");
		query.append(", s1.ESTADO_SUBSANACION_064 ESTADO_SUBSANACION_064");
		query.append(", s1.FECHA_ACEPTACION_064 FECHA_ACEPTACION_064");
		query.append(", DECODE(s1.IND_SUBSANADO_064, '").append(AportaSubsanacionEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.SI.getLabel(), null, es)).append("', '")
				.append(AportaSubsanacionEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO.getLabel(), null, es))
				.append(AportaSubsanacionEnum.NO_REQUERIDA.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO_REQUERIDA.getLabel(), null, es))
				.append("') AS SUBSANACIONDESCES");
		query.append(", DECODE(s1.IND_SUBSANADO_064, '").append(AportaSubsanacionEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.SI.getLabel(), null, eu)).append("', '")
				.append(AportaSubsanacionEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO.getLabel(), null, eu))
				.append(AportaSubsanacionEnum.NO_REQUERIDA.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO_REQUERIDA.getLabel(), null, eu))
				.append("') AS SUBSANACIONDESCEU");
		// número de registros de acciones
		query.append(", (SELECT  COUNT(ID_REGISTRO_ACCION_043) AS NUMACCIONES043 FROM AA79B43S01 regAc "
				+ "WHERE t1.NUM_EXP_059 = regAc.NUM_EXP_043 AND t1.ANYO_059 = regAc.ANYO_EXP_043 AND t1.ID_ESTADO_BITACORA_059 = regAc.ID_ESTADO_BITACORA_043"
				+ ") AS NUMACCIONES");

		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder fromBitacoraExpediente = new StringBuilder();
		fromBitacoraExpediente.append(" FROM AA79B59S01 t1 ");
		fromBitacoraExpediente.append(" LEFT JOIN AA79B64S01 s1 ON t1.ID_REQUERIMIENTO_059 = s1.ID_064");
		fromBitacoraExpediente.append(" LEFT JOIN AA79B60S01 e1 ON t1.ID_ESTADO_EXP_059 = e1.ID_060");
		fromBitacoraExpediente.append(" LEFT JOIN AA79B61S01 f1 ON t1.ID_FASE_EXPEDIENTE_059 = f1.ID_061");
		return fromBitacoraExpediente.toString();
	}

	@Override
	protected RowMapper<BitacoraExpediente> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return MetadatosBusquedaDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return ANYO_BITACORA_EXPEDIENTE + "|| '_' ||" + NUM_EXP_BITACORA_EXPEDIENTE;
	}

	@Override
	protected RowMapper<BitacoraExpediente> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(BitacoraExpediente bean, List<Object> params) {
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		return " WHERE t1.ANYO_059 = ? AND t1.NUM_EXP_059 = ?";
	}

	@Override
	protected String getWhere(BitacoraExpediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder(BitacoraExpedienteDaoImpl.STRING_BUILDER_INIT);

		if (bean != null) {
			where.append(SqlUtils.generarWhereIgual("t1.ANYO_059", bean.getAnyo(), params));
			where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_059", bean.getNumExp(), params));
			where.append(SqlUtils.generarWhereIgual("t1.ID_ESTADO_BITACORA_059", bean.getIdEstadoBitacora(), params));
			if (bean.getEstadoExp() != null) {
				where.append(SqlUtils.generarWhereIgual("t1.ID_ESTADO_EXP_059", bean.getEstadoExp().getId(), params));
				where.append(
						SqlUtils.generarWhereLike("e1.DESC_ES_060", bean.getEstadoExp().getDescEs(), params, false));
				where.append(
						SqlUtils.generarWhereLike("e1.DESC_EU_060", bean.getEstadoExp().getDescEu(), params, false));
				where.append(SqlUtils.generarWhereLike("e1.DESC_ABR_ES_060", bean.getEstadoExp().getDescAbrEs(), params,
						false));
				where.append(SqlUtils.generarWhereLike("e1.DESC_ABR_EU_060", bean.getEstadoExp().getDescAbrEu(), params,
						false));
			}
			if (bean.getFaseExp() != null) {
				where.append(
						SqlUtils.generarWhereIgual("t1.ID_FASE_EXPEDIENTE_059", bean.getFaseExp().getId(), params));
				where.append(SqlUtils.generarWhereLike("f1.DESC_ES_061", bean.getFaseExp().getDescEs(), params, false));
				where.append(SqlUtils.generarWhereLike("f1.DESC_EU_061", bean.getFaseExp().getDescEu(), params, false));
				where.append(SqlUtils.generarWhereLike("f1.DESC_ABR_ES_061", bean.getFaseExp().getDescAbrEs(), params,
						false));
				where.append(SqlUtils.generarWhereLike("f1.DESC_ABR_EU_061", bean.getFaseExp().getDescAbrEu(), params,
						false));
			}
			where.append(SqlUtils.generarWhereLike("t1.DATO_ADIC_059", bean.getDatoAdic(), params, false));
			where.append(SqlUtils.generarWhereLike("t1.INFO_ADIC_059", bean.getInfoAdic(), params, false));
			where.append(SqlUtils.generarWhereIgual("t1.ID_RECHAZO_059", bean.getIdMotivoRechazo(), params));
			if (bean.getSubsanacionExp() != null) {
				where.append(SqlUtils.generarWhereIgual("t1.ID_REQUERIMIENTO_059", bean.getSubsanacionExp().getId(),
						params));
				where.append(SqlUtils.generarWhereLike("s1.DETALLE_064", bean.getSubsanacionExp().getDetalle(), params,
						false));
				where.append(SqlUtils.generarWhereLike("s1.IND_SUBSANADO_064",
						bean.getSubsanacionExp().getIndSubsanado(), params, false));
				where.append(SqlUtils.generarWhereIgual("s1.ESTADO_SUBSANACION_064",
						bean.getSubsanacionExp().getEstado(), params));
			}
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(BitacoraExpediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(BitacoraExpedienteDaoImpl.STRING_BUILDER_INIT);

		if (bean != null) {
			where.append(SqlUtils.generarWhereIgual("t1.ANYO_059", bean.getAnyo(), params));
			where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_059", bean.getNumExp(), params));
			where.append(SqlUtils.generarWhereIgual("t1.ID_ESTADO_BITACORA_059", bean.getIdEstadoBitacora(), params));
			if (bean.getEstadoExp() != null) {
				where.append(SqlUtils.generarWhereIgual("t1.ID_ESTADO_EXP_059", bean.getEstadoExp().getId(), params));
				where.append(SqlUtils.generarWhereLike("e1.DESC_ES_060", bean.getEstadoExp().getDescEs(), params,
						startsWith));
				where.append(SqlUtils.generarWhereLike("e1.DESC_EU_060", bean.getEstadoExp().getDescEu(), params,
						startsWith));
				where.append(SqlUtils.generarWhereLike("e1.DESC_ABR_ES_060", bean.getEstadoExp().getDescAbrEs(), params,
						startsWith));
				where.append(SqlUtils.generarWhereLike("e1.DESC_ABR_EU_060", bean.getEstadoExp().getDescAbrEu(), params,
						startsWith));
			}
			if (bean.getFaseExp() != null) {
				where.append(
						SqlUtils.generarWhereIgual("t1.ID_FASE_EXPEDIENTE_059", bean.getFaseExp().getId(), params));
				where.append(
						SqlUtils.generarWhereLike("f1.DESC_ES_061", bean.getFaseExp().getDescEs(), params, startsWith));
				where.append(
						SqlUtils.generarWhereLike("f1.DESC_EU_061", bean.getFaseExp().getDescEu(), params, startsWith));
				where.append(SqlUtils.generarWhereLike("f1.DESC_ABR_ES_061", bean.getFaseExp().getDescAbrEs(), params,
						startsWith));
				where.append(SqlUtils.generarWhereLike("f1.DESC_ABR_EU_061", bean.getFaseExp().getDescAbrEu(), params,
						startsWith));
			}
			where.append(SqlUtils.generarWhereLike("t1.DATO_ADIC_059", bean.getDatoAdic(), params, startsWith));
			where.append(SqlUtils.generarWhereLike("t1.INFO_ADIC_059", bean.getInfoAdic(), params, startsWith));
			where.append(SqlUtils.generarWhereIgual("t1.ID_RECHAZO_059", bean.getIdMotivoRechazo(), params));
			if (bean.getSubsanacionExp() != null) {
				where.append(SqlUtils.generarWhereIgual("t1.ID_REQUERIMIENTO_059", bean.getSubsanacionExp().getId(),
						params));
				where.append(SqlUtils.generarWhereLike("s1.DETALLE_064", bean.getSubsanacionExp().getDetalle(), params,
						startsWith));
				where.append(SqlUtils.generarWhereLike("s1.IND_SUBSANADO_064",
						bean.getSubsanacionExp().getIndSubsanado(), params, startsWith));
				where.append(SqlUtils.generarWhereIgual("s1.ESTADO_SUBSANACION_064",
						bean.getSubsanacionExp().getEstado(), params));
			}
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public Long findAllCount(BitacoraExpediente bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT nvl(max(ID_ESTADO_BITACORA_059),0) + 1 ");
		query.append("FROM AA79B59S01 ");
		query.append("WHERE NUM_EXP_059 = ? and ANYO_059 = ?");
		params.add(bean.getNumExp());
		params.add(bean.getAnyo());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 * 
	 * @param bitacoraSolicitante
	 *            BitacoraSolicitante
	 * @param accionBitacora
	 *            int
	 * @return int
	 */
	@Override
	public void addBitacoraSolicitante(BitacoraSolicitante bitacoraSolicitante, int accionBitacora) {
		final Long elId = this.getNextVal("AA79B79Q00");

		String query = "INSERT INTO AA79B79S01 (ID_079, ANYO_079, NUM_EXP_079,"
				+ "ID_ACCION_BITACORA_079, FECHA_ALTA_079, USUARIO_079) " + "VALUES (?,?,?,?,SYSDATE,?)";
		this.getJdbcTemplate().update(query, elId, bitacoraSolicitante.getAnyo(), bitacoraSolicitante.getNumExp(),
				accionBitacora, bitacoraSolicitante.getUsuario());
	}

	/**
	 * 
	 * @param lista
	 *            List<Expediente>
	 */
	@Override
	public void addBitacoraListaExpedientes(List<Expediente> lista) {
		String query = "INSERT INTO AA79B59S01 (ANYO_059, NUM_EXP_059, ID_ESTADO_BITACORA_059, ID_ESTADO_EXP_059, ID_FASE_EXPEDIENTE_059, DATO_ADIC_059, INFO_ADIC_059, ID_RECHAZO_059, ID_REQUERIMIENTO_059, FECHA_ALTA_059) VALUES (?,?,?,?,?,?,?,?,?, SYSDATE)";
		final List<Object[]> params = new ArrayList<Object[]>();
		final List<Object> aux = new ArrayList<Object>();
		for (Expediente expediente : lista) {
			aux.clear();
			aux.add(expediente.getAnyo());
			aux.add(expediente.getNumExp());
			aux.add(expediente.getBitacoraExpediente().getIdEstadoBitacora());
			aux.add(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			aux.add(Long.valueOf(FaseExpedienteEnum.ESTUDIO_EXPEDIENTE.getValue()));
			aux.add(expediente.getBitacoraExpediente().getDatoAdic());
			aux.add(expediente.getBitacoraExpediente().getInfoAdic());
			aux.add(expediente.getBitacoraExpediente().getIdMotivoRechazo());
			aux.add(null);
			params.add(aux.toArray());
		}
		this.getJdbcTemplate().batchUpdate(query, params);
	}

	@Override
	public List<BitacoraExpediente> findAllOrdered(BitacoraExpediente bitacora, String campo, String orden) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom(bitacora, params));

		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhere(bitacora, params));
		query.append(DaoConstants.ORDER_BY + campo + DaoConstants.BLANK + orden);

		return this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());
	}

}