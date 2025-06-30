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

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.SeccionesControlDeCalidad;
import com.ejie.aa79b.model.enums.TipoSeccionAuditoriaEnum;

@Repository
@Transactional
public class SeccionesControlDeCalidadDaoImpl extends GenericoDaoImpl<SeccionesControlDeCalidad>
		implements SeccionesControlDeCalidadDao {

	protected static final String COMUN_SI = "comun.si";
	protected static final String COMUN_NO = "comun.no";
	protected static final String N = "', 'N', '";

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public SeccionesControlDeCalidadDaoImpl() {
		super(SeccionesControlDeCalidad.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { "ID", "TIPOSECCION", "TIPOSECCIONEU",
			"TIPOSECCIONES", "INDRESPUESTA", "INDRESPUESTAEU", "INDRESPUESTAES", "NOMBREEU", "INDOBSERVACIONES",
			"INDOBSERVACIONESEU", "INDOBSERVACIONESES", "INDVISIBLE", "INDVISIBLEEU", "INDVISIBLEES", "ORDEN" };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<SeccionesControlDeCalidad> rwMap = new RowMapper<SeccionesControlDeCalidad>() {
		@Override
		public SeccionesControlDeCalidad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			SeccionesControlDeCalidad seccionesControlDeCalidad = new SeccionesControlDeCalidad(resultSet.getLong("ID"),
					resultSet.getInt("TIPOSECCION"), resultSet.getString("INDRESPUESTA"),
					resultSet.getString("NOMBREEU"), resultSet.getString("INDOBSERVACIONES"),
					resultSet.getString("INDVISIBLE"), resultSet.getInt("ORDEN"));
			seccionesControlDeCalidad.setTipoSeccionEu(resultSet.getString("TIPOSECCIONEU"));
			seccionesControlDeCalidad.setTipoSeccionEs(resultSet.getString("TIPOSECCIONES"));
			seccionesControlDeCalidad.setDescRespuestaEu(resultSet.getString("INDRESPUESTAEU"));
			seccionesControlDeCalidad.setDescRespuestaEu(resultSet.getString("INDRESPUESTAES"));
			seccionesControlDeCalidad.setDescObservacionesEu(resultSet.getString("INDOBSERVACIONESEU"));
			seccionesControlDeCalidad.setDescObservacionesEs(resultSet.getString("INDOBSERVACIONESES"));
			seccionesControlDeCalidad.setDescVisibleEu(resultSet.getString("INDVISIBLEEU"));
			seccionesControlDeCalidad.setDescVisibleEs(resultSet.getString("INDVISIBLEES"));

			return seccionesControlDeCalidad;
		}
	};

	private RowMapper<SeccionesControlDeCalidad> rwMapPK = new RowMapper<SeccionesControlDeCalidad>() {
		@Override
		public SeccionesControlDeCalidad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new SeccionesControlDeCalidad(resultSet.getLong("ID"));
		}
	};

	@Override
	public SeccionesControlDeCalidad add(SeccionesControlDeCalidad seccionesControlDeCalidad) {

		String queryID = "SELECT AA79BC0Q00.NEXTVAL from dual";
		long elId = this.getJdbcTemplate().queryForObject(queryID, Long.class);

		String query = "INSERT INTO AA79BC0S01 (ID_0C0, TIPO_SECCION_0C0, IND_RESPUESTA_0C0, NOMBRE_EU_0C0, IND_OBSERVACIONES_0C0, IND_VISIBLE_0C0, ORDEN_0C0) VALUES (?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, elId, seccionesControlDeCalidad.getTipoSeccion(),
				seccionesControlDeCalidad.getIndRespuesta(), seccionesControlDeCalidad.getNombreEu(),
				seccionesControlDeCalidad.getIndObservaciones(), seccionesControlDeCalidad.getIndVisible(),
				seccionesControlDeCalidad.getOrden());
		return seccionesControlDeCalidad;
	}

	@Override
	public SeccionesControlDeCalidad update(SeccionesControlDeCalidad seccionesControlDeCalidad) {
		String query = "UPDATE AA79BC0S01 SET TIPO_SECCION_0C0=?, IND_RESPUESTA_0C0=?, NOMBRE_EU_0C0=?, IND_OBSERVACIONES_0C0=?, IND_VISIBLE_0C0=?, ORDEN_0C0=? WHERE ID_0C0=?";
		this.getJdbcTemplate().update(query, seccionesControlDeCalidad.getTipoSeccion(),
				seccionesControlDeCalidad.getIndRespuesta() != null ? seccionesControlDeCalidad.getIndRespuesta()
						: Constants.NO,
				seccionesControlDeCalidad.getNombreEu(),
				seccionesControlDeCalidad.getIndObservaciones() != null
						? seccionesControlDeCalidad.getIndObservaciones()
						: Constants.NO,
				seccionesControlDeCalidad.getIndVisible() != null ? seccionesControlDeCalidad.getIndVisible()
						: Constants.NO,
				seccionesControlDeCalidad.getOrden(), seccionesControlDeCalidad.getId());
		return seccionesControlDeCalidad;
	}

	@Override
	protected String getSelect() {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		StringBuilder select = new StringBuilder();

		select.append("SELECT t1.ID_0C0 ID");
		select.append(", t1.TIPO_SECCION_0C0 TIPOSECCION");

		select.append(", DECODE(t1.TIPO_SECCION_0C0, '1', '")
				.append(this.msg.getMessage(TipoSeccionAuditoriaEnum.SECCION_DE_VALORACION.getLabel(), null, eu))
				.append("', '2', '")
				.append(this.msg.getMessage(TipoSeccionAuditoriaEnum.SECCION_DE_INFORMACION.getLabel(), null, eu))
				.append("', '3', '")
				.append(this.msg.getMessage(TipoSeccionAuditoriaEnum.SECCION_DE_DOCUMENTOS.getLabel(), null, eu))
				.append("') AS TIPOSECCIONEU");
		select.append(", DECODE(t1.TIPO_SECCION_0C0, '1', '")
				.append(this.msg.getMessage(TipoSeccionAuditoriaEnum.SECCION_DE_VALORACION.getLabel(), null, es))
				.append("', '2', '")
				.append(this.msg.getMessage(TipoSeccionAuditoriaEnum.SECCION_DE_INFORMACION.getLabel(), null, es))
				.append("', '3', '")
				.append(this.msg.getMessage(TipoSeccionAuditoriaEnum.SECCION_DE_DOCUMENTOS.getLabel(), null, es))
				.append("') AS TIPOSECCIONES");

		select.append(", t1.IND_RESPUESTA_0C0 INDRESPUESTA");

		select.append(", DECODE(t1.IND_RESPUESTA_0C0, 'S', '").append(this.msg.getMessage(COMUN_SI, null, eu)).append(N)
				.append(this.msg.getMessage(COMUN_NO, null, eu)).append("') AS INDRESPUESTAEU");
		select.append(", DECODE(t1.IND_RESPUESTA_0C0, 'S', '").append(this.msg.getMessage(COMUN_SI, null, es)).append(N)
				.append(this.msg.getMessage(COMUN_NO, null, es)).append("') AS INDRESPUESTAES");

		select.append(", t1.NOMBRE_EU_0C0 NOMBREEU");
		select.append(", t1.IND_OBSERVACIONES_0C0 INDOBSERVACIONES");

		select.append(", DECODE(t1.IND_OBSERVACIONES_0C0, 'S', '").append(this.msg.getMessage(COMUN_SI, null, eu))
				.append(N).append(this.msg.getMessage(COMUN_NO, null, eu)).append("') AS INDOBSERVACIONESEU");
		select.append(", DECODE(t1.IND_OBSERVACIONES_0C0, 'S', '").append(this.msg.getMessage(COMUN_SI, null, es))
				.append(N).append(this.msg.getMessage(COMUN_NO, null, es)).append("') AS INDOBSERVACIONESES");

		select.append(", t1.IND_VISIBLE_0C0 INDVISIBLE");

		select.append(", DECODE(t1.IND_VISIBLE_0C0, 'S', '").append(this.msg.getMessage(COMUN_SI, null, eu)).append(N)
				.append(this.msg.getMessage(COMUN_NO, null, eu)).append("') AS INDVISIBLEEU");
		select.append(", DECODE(t1.IND_VISIBLE_0C0, 'S', '").append(this.msg.getMessage(COMUN_SI, null, es)).append(N)
				.append(this.msg.getMessage(COMUN_NO, null, es)).append("') AS INDVISIBLEES");

		select.append(", t1.ORDEN_0C0 ORDEN");
		return select.toString();
	}

	@Override()
	protected String getFrom() {
		return " FROM AA79BC0S01 t1";
	}

	@Override()
	protected RowMapper<SeccionesControlDeCalidad> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return SeccionesControlDeCalidadDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return "ID_0C0";
	}

	@Override()
	protected RowMapper<SeccionesControlDeCalidad> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(SeccionesControlDeCalidad bean, List<Object> params) {
		params.add(bean.getId());
		return " WHERE t1.ID_0C0 = ?";
	}

	@Override
	protected String getWhere(SeccionesControlDeCalidad bean, List<Object> params) {
		StringBuilder where = new StringBuilder(SeccionesControlDeCalidadDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0C0", bean.getId(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NOMBRE_EU_0C0", bean.getNombreEu(), params));
		where.append(SqlUtils.generarWhereIgual("t1.IND_VISIBLE_0C0", bean.getIndVisible(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(SeccionesControlDeCalidad bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		StringBuilder where = new StringBuilder(SeccionesControlDeCalidadDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0C0", bean.getId(), params));
		where.append(SqlUtils.generarWhereLikeNormalizado("t1.NOMBRE_EU_0C0", bean.getNombreEu(), params, startsWith));
		where.append(SqlUtils.generarWhereIgual("t1.IND_VISIBLE_0C0", bean.getIndVisible(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}
}
