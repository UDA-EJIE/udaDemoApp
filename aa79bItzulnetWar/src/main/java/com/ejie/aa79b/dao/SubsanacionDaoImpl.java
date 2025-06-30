package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.dao.mapper.ReqExpRowMapper;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.utils.DAOUtils;

@Repository
@Transactional
public class SubsanacionDaoImpl extends GenericoDaoImpl<SubsanacionExpediente> implements SubsanacionDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] {};

	public SubsanacionDaoImpl() {
		// Constructor
		super(SubsanacionExpediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<SubsanacionExpediente> reqExpRwMap = new ReqExpRowMapper();

	@Override
	protected String getSelect() {
		Locale eu = new Locale("eu");
		Locale es = new Locale("es");
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT);
		query.append(" t1.ID_064 " + DBConstants.ID);
		query.append(" ,t1.TIPO_REQUERIMIENTO_064 " + DBConstants.TIPOREQUERIMIENTO);
		query.append(DAOUtils.getDecodeAcciones("t1.TIPO_REQUERIMIENTO_064", DBConstants.TIPOREQUERIMIENTODESCEU,
				this.msg, "TipoRequerimientoEnum", eu));
		query.append(DAOUtils.getDecodeAcciones("t1.TIPO_REQUERIMIENTO_064", DBConstants.TIPOREQUERIMIENTODESCES,
				this.msg, "TipoRequerimientoEnum", es));
		query.append(" ,t1.DETALLE_064 " + DBConstants.DETALLE);
		query.append(" ,t1.FECHA_REQ_064 " + DBConstants.FECHAREQ);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ " t1.FECHA_REQ_064 ,'HH24:MI'" + DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAREQ);
		query.append(" ,t1.FECHA_LIMITE_064 " + DBConstants.FECHALIMITE);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ " t1.FECHA_LIMITE_064 ,'HH24:MI'" + DaoConstants.CERRAR_PARENTESIS + DBConstants.HORALIMITE);
		query.append(" ,t1.IND_SUBSANADO_064 " + DBConstants.INDSUBSANADO);
		query.append(" ,t1.ESTADO_SUBSANACION_064 " + DBConstants.ESTADOSUBSANACION);
		query.append(DAOUtils.getDecodeAcciones("t1.ESTADO_SUBSANACION_064", DBConstants.ESTADOSUBSANACIONDESCEU,
				this.msg, "EstadoRequerimientoEnum", eu));
		query.append(DAOUtils.getDecodeAcciones("t1.TIPO_REQUERIMIENTO_064", DBConstants.ESTADOSUBSANACIONDESCES,
				this.msg, "EstadoRequerimientoEnum", es));
		query.append(" ,t1.FECHA_ACEPTACION_064 " + DBConstants.FECHAACEPTACION);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ " t1.FECHA_ACEPTACION_064 ,'HH24:MI'" + DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAACEPTACION);
		query.append(" ,t1.FECHA_SUBSANACION_064 " + DBConstants.FECHASUBSANACION);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ " t1.FECHA_SUBSANACION_064 ,'HH24:MI'" + DaoConstants.CERRAR_PARENTESIS
				+ DBConstants.HORASUBSANACION);
		query.append(" ,t1.PRESUPUESTO_064 " + DBConstants.PRESUPUESTO);
		query.append(" ,t1.FECHA_ENTREGA_064 " + DBConstants.FECHAENTREGA);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ " t1.FECHA_ENTREGA_064 ,'HH24:MI'" + DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAENTREGA);
		query.append(" ,t1.OBSRV_RECHAZO_064 " + DBConstants.OBSRVRECHAZO);
		query.append(" ,t1.DNI_RECURSO_064 " + DBConstants.DNIRECURSO);
		return query.toString();
	}

	@Override
	protected String getFrom() {
		return DaoConstants.FROM + DBConstants.TABLA_064 + " t1 ";
	}

	@Override
	protected RowMapper<SubsanacionExpediente> getRwMap() {
		return this.reqExpRwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return SubsanacionDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return DBConstants.ID;
	}

	@Override
	protected RowMapper<SubsanacionExpediente> getRwMapPK() {
		return this.reqExpRwMap;
	}

	@Override
	protected String getWherePK(SubsanacionExpediente bean, List<Object> params) {
		params.add(bean.getId());
		return DaoConstants.WHERE + "t1.ID_064" + DaoConstants.SIGNOIGUAL_INTERROGACION;
	}

	@Override
	protected String getWhere(SubsanacionExpediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder(MetadatosBusquedaDaoImpl.STRING_BUILDER_INIT);
		return where.toString();
	}

	@Override
	protected String getWhereLike(SubsanacionExpediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(MetadatosBusquedaDaoImpl.STRING_BUILDER_INIT);
		return where.toString();
	}

	@Override
	public SubsanacionExpediente getSubsanacionExpediente(Long anyo, Integer numExp, Long idTarea) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom());
		query.append(DaoConstants.JOIN + DBConstants.TABLA_081 + " t2 ");
		query.append(DaoConstants.ON + " t1.ID_064 = t2.ID_REQUERIMIENTO_081 ");
		query.append(DaoConstants.AND + " t2.ANYO_081 = ? ");
		query.append(DaoConstants.AND + " t2.NUM_EXP_081 = ? ");
		query.append(DaoConstants.AND + " t2.ID_TIPO_TAREA_081 = ?");
		query.append(DaoConstants.AND + " t2.ID_TAREA_081 = ?");
		params.add(anyo);
		params.add(numExp);
		params.add(TipoTareaGestionAsociadaEnum.ESTUDIAR_SUBSANACION.getValue());
		params.add(idTarea);
		return this.getJdbcTemplate().queryForObject(query.toString(), this.reqExpRwMap, params.toArray());
	}

	@Override
	public Integer actualizarSubsanacionConIndSubs(SubsanacionExpediente sExp) {
		String query = "UPDATE AA79B64S01 SET ESTADO_SUBSANACION_064 = ? , FECHA_ACEPTACION_064 = SYSDATE, IND_SUBSANADO_064 = ? WHERE ID_064 = ? ";
		return this.getJdbcTemplate().update(query, sExp.getEstado(), ActivoEnum.SI.getValue(), sExp.getId());
	}

	@Override
	public BigDecimal getIdTareaConRequerimiento(Long idRequerimiento) {
		String queryIdTarea = "SELECT ID_TAREA_081 FROM AA79B81S01 WHERE ID_REQUERIMIENTO_081 = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(idRequerimiento);
		return this.getJdbcTemplate().queryForObject(queryIdTarea, params.toArray(), BigDecimal.class);

	}

	@Override
	public Integer comprobarSiTieneZIP(DocumentosExpediente documentosExpediente) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.SIGNO_GUION + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.SUM_ABRIR_PARENTESIS + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append("LOWER(EXTENSION_DOC_056),'zip',1,0" + DaoConstants.CERRAR_PARENTESIS
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA);
		query.append(Constants.CERO + DaoConstants.SIGNO_COMA + "COUNT(1)" + DaoConstants.SIGNO_COMA
				+ DaoConstants.SUM_ABRIR_PARENTESIS + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append("LOWER(EXTENSION_DOC_056),'zip',1,0" + DaoConstants.SIGNO_COMA + "'zip'" + DaoConstants.SIGNO_COMA
				+ Constants.UNO + DaoConstants.SIGNO_COMA + Constants.CERO + DaoConstants.CERRAR_PARENTESIS
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.FROM + DBConstants.TABLA_56 + DaoConstants.WHERE + DBConstants.CLASE_DOCUMENTO_056
				+ DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS + Constants.UNO + DaoConstants.SIGNO_COMA + Constants.DOS
				+ DaoConstants.SIGNO_COMA + Constants.TRES + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AND + "ID_DOC_REL_056" + DaoConstants.IS_NULL);
		query.append(DaoConstants.AND + DBConstants.ANYO_056 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DBConstants.NUM_EXP_056 + DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(documentosExpediente.getAnyo());
		params.add(documentosExpediente.getNumExp());
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

}
