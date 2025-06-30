package com.ejie.aa79b.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.ComunicacionesRowMapper;
import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.security.Usuario;

@Repository
@Transactional
public class ComunicacionesDaoImpl extends GenericoDaoImpl<Comunicaciones> implements ComunicacionesDao {

	public ComunicacionesDaoImpl() {
		super(Comunicaciones.class);
	}

	private static final String ID_0D4 = "ID_0D4";
	private static final String TIPO_0D4 = "TIPO_0D4";

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { ID_0D4,"FECHA_0D4",TIPO_0D4,"REMITENTE_0D4","ASUNTO_0D4"};

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Comunicaciones> rwMap = new ComunicacionesRowMapper();

	private RowMapper<Comunicaciones> rwMapPK = new ComunicacionesRowMapper();

	/*
	 * OPERACIONES CRUD
	 */

	@Override
	public Comunicaciones add(Comunicaciones comunicaciones) {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			comunicaciones.setRemitente(credentials.getFullName());
		}

		final Long elId = this.getNextVal("AA79BD4Q00");
		StringBuilder queryAP = new StringBuilder(GenericoDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsAP = new ArrayList<Object>();
		paramsAP.add(elId);
		paramsAP.add(comunicaciones.getAnyo());
		paramsAP.add(comunicaciones.getNumExp());
		paramsAP.add(comunicaciones.getRefTramitagune());
		paramsAP.add(comunicaciones.getTipo());
		paramsAP.add(comunicaciones.getRemitente());
		paramsAP.add(comunicaciones.getAsunto());
		paramsAP.add(comunicaciones.getMensaje());
		paramsAP.add(comunicaciones.getIdFichero0D4());
		queryAP.append(
				"INSERT INTO AA79BD4S01 (ID_0D4, ANYO_0D4, NUM_EXP_0D4, REF_TRAMITAGUNE_0D4, FECHA_0D4, TIPO_0D4, REMITENTE_0D4, ASUNTO_0D4, MENSAJE_0D4, ID_FICHERO_0D4) VALUES (?,?,?,?,SYSDATE,?,?,?,?,?)");
		this.getJdbcTemplate().update(queryAP.toString(), paramsAP.toArray());
		comunicaciones.setId0D4(elId);
		return comunicaciones;

	}

	/**
	 * Updates a single row in the Comunicaciones table.S
	 *
	 * @param motivosrechazo Comunicaciones
	 * @return Comunicaciones
	 */
	@Override
	public Comunicaciones update(Comunicaciones comunicaciones) {
		List<Object> paramsAP = new ArrayList<Object>();

		paramsAP.add(comunicaciones.getAnyo());
		paramsAP.add(comunicaciones.getNumExp());
		paramsAP.add(comunicaciones.getRefTramitagune());
		paramsAP.add(comunicaciones.getFecha());
		paramsAP.add(comunicaciones.getTipo());
		paramsAP.add(comunicaciones.getRemitente());
		paramsAP.add(comunicaciones.getAsunto());
		paramsAP.add(comunicaciones.getMensaje());
		paramsAP.add(comunicaciones.getIdFichero0D4());
		paramsAP.add(comunicaciones.getId0D4());
		String query = "UPDATE AA79BD4S01 SET ANYO_0D4 = ?, NUM_EXP_0D4 = ?, REF_TRAMITAGUNE_0D4 = ?, FECHA_0D4 = ?, TIPO_0D4 = ?, REMITENTE_0D4 = ?, ASUNTO_0D4 = ?, MENSAJE_0D4 = ?, ID_FICHERO_0D4 = ? WHERE ID_0D4 = ?";
		this.getJdbcTemplate().update(query, paramsAP.toArray());
		return comunicaciones;
	}

	/**
	 * Finds a single row in the Comunicaciones table.
	 *
	 * @param motivosrechazo Comunicaciones
	 * @return Comunicaciones
	 */
	@Override()
	protected String getSelect() {

		StringBuilder selectComunicaciones = new StringBuilder();

		selectComunicaciones.append("SELECT ID_0D4, ANYO_0D4, NUM_EXP_0D4, REF_TRAMITAGUNE_0D4, FECHA_0D4, TIPO_0D4, REMITENTE_0D4, ASUNTO_0D4, MENSAJE_0D4, ID_FICHERO_0D4");
		selectComunicaciones.append(", t2.ID_FICHERO_088 IDFICHERO088");
		selectComunicaciones.append(", t2.TITULO_FICHERO_088 TITULOFICHERO088");
		selectComunicaciones.append(", t2.EXTENSION_FICHERO_088 EXTENSIONFICHERO088");
		selectComunicaciones.append(", t2.CONTENT_TYPE_FICHERO_088 CONTENTTYPEFICHERO088");
		selectComunicaciones.append(", t2.TAMANO_FICHERO_088 TAMANOFICHERO088");
		selectComunicaciones.append(", t2.IND_ENCRIPTADO_088 INDENCRIPTADO088");
		selectComunicaciones.append(", t2.RUTA_PIF_FICHERO_088 RUTAPIFFICHERO088");
		selectComunicaciones.append(", t2.OID_FICHERO_088 OIDFICHERO088");
		selectComunicaciones.append(", t2.NOMBRE_FICHERO_088 NOMBREFICHERO088");

		return selectComunicaciones.toString();
	}

	@Override()
	protected String getFrom() {
		StringBuilder query = new StringBuilder();
		query.append(" FROM AA79BD4S01 ");
		query.append(" LEFT JOIN AA79B88S01 t2 ON ID_FICHERO_0D4 = t2.ID_FICHERO_088 ");
		return query.toString();
	}

	@Override()
	protected RowMapper<Comunicaciones> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return ComunicacionesDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return ID_0D4;
	}

	@Override()
	protected RowMapper<Comunicaciones> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(Comunicaciones bean, List<Object> params) {
		params.add(bean.getId0D4());
		return " WHERE ID_0D4 = ?";
	}

	@Override
	protected String getWhere(Comunicaciones bean, List<Object> params) {
		return getWhereLike(bean, false, params, false);

	}

	@Override
	protected String getWhereLike(Comunicaciones bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(GenericoDaoImpl.STRING_BUILDER_INIT);

		if ( "R".contentEquals( bean.getTipoBusqueda()) && StringUtils.isNotBlank(bean.getRefTramitagune()) ) {

			where.append(SqlUtils.generarWhereIgual("REF_TRAMITAGUNE_0D4", bean.getRefTramitagune(), params));
		}else {
			where.append(SqlUtils.generarWhereIgual(ID_0D4, bean.getId0D4(), params));
			where.append(SqlUtils.generarWhereIgual("ANYO_0D4", bean.getAnyo(), params));
			where.append(SqlUtils.generarWhereIgual("NUM_EXP_0D4", bean.getNumExp(), params));
			where.append(SqlUtils.generarWhereIgual(TIPO_0D4, bean.getTipo(), params));
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		return where.toString();
	}

}
