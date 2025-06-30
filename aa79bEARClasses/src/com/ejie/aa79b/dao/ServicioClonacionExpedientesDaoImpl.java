package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.Clonacion;
import com.ejie.aa79b.model.enums.EstadoClonacionEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.PLConnectionUtils;

/**
 * ClonacionDaoImpl
 * 
 * @author javarona
 */

@Repository
@Transactional
public class ServicioClonacionExpedientesDaoImpl extends GenericoDaoImpl<Clonacion>
		implements ServicioClonacionExpedientesDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String ID_0A8 = "ID_0A8";
	protected static final String ID0A8 = "ID";
	protected static final String WHERE_1 = " WHERE 1=1 ";
	protected static final String NUM_EXP_CLON_0A8 = "NUM_EXP_CLON_0A8";
	protected static final String NUM_EXP_ORIG_0A8 = "NUM_EXP_ORIG_0A8";

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { ID0A8, "ANYO_ORIG_0A8", NUM_EXP_ORIG_0A8,
			"ANYO_CLON_0A8", NUM_EXP_CLON_0A8, "FECHA_CLON_0A8", "ESTADO_CLON_0A8" };

	private RowMapper<Clonacion> rwMap = new RowMapper<Clonacion>() {
		@Override()
		public Clonacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Clonacion clonacion = new Clonacion(resultSet.getLong(ID0A8));
			clonacion.setAnyoOrig(resultSet.getLong("ANYOORIG"));
			clonacion.setNumExpOrig(resultSet.getInt("NUMEXPORIG"));
			clonacion.setAnyoClon(resultSet.getLong("ANYOCLON"));
			clonacion.setNumExpClon(resultSet.getInt("NUMEXPCLON"));
			clonacion.setFecha(resultSet.getTimestamp("FECHACLON"));
			clonacion.setIdTipoExpediente(resultSet.getString("IDTIPOEXPEDIENTE"));
			clonacion.setTipoExpedienteDesc(resultSet.getString("TIPOEXPEDIENTEDESC"));
			clonacion.setEstado(resultSet.getInt("ESTADOCLON"));
			clonacion.setEstadoDesc(resultSet.getString("ESTADOCLONDESC"));

			return clonacion;
		}
	};

	private RowMapper<Clonacion> rwMapPK = new RowMapper<Clonacion>() {
		@Override()
		public Clonacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Clonacion(resultSet.getLong(ID0A8));
		}
	};

	/**
	 * Constructor de la clase.
	 */
	public ServicioClonacionExpedientesDaoImpl() {
		/* Constructor */
		super(Clonacion.class);
	}
	/*
	 * OPERACIONES CRUD
	 */

	@Override()
	public Clonacion add(Clonacion clonacion) {
		final Long elId = this.getNextVal("AA79BA8Q00");
		String query = "INSERT INTO AA79BA8S01 (ID_0A8, ANYO_ORIG_0A8, NUM_EXP_ORIG_0A8, ANYO_CLON_0A8, NUM_EXP_CLON_0A8, FECHA_CLON_0A8) VALUES (?,?,?,?,?,?)";

		this.getJdbcTemplate().update(query, elId, clonacion.getAnyoOrig(), clonacion.getNumExpOrig(),
				clonacion.getAnyoClon(), clonacion.getNumExpClon(), new Date());
		clonacion.setId(elId);
		return clonacion;
	}

	@Override()
	public Clonacion update(Clonacion clonacion) {
		String query = "UPDATE AA79BA8S01 SET ANYO_CLON_0A8=?, NUM_EXP_CLON_0A8=?, ESTADO_CLON_0A8=? WHERE ID_0A8=?";
		this.getJdbcTemplate().update(query, clonacion.getAnyoClon(), clonacion.getNumExpClon(), clonacion.getEstado(),
				clonacion.getId());
		return clonacion;
	}

	/**
	 * The method getQuery.
	 * 
	 * @return String OJO CON ESTE MÉTOD
	 */
	@Override()
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder sb = new StringBuilder();

		sb.append(DaoConstants.SELECT
				+ "ID_0A8 ID, ANYO_ORIG_0A8 ANYOORIG, NUM_EXP_ORIG_0A8 NUMEXPORIG, ANYO_CLON_0A8 ANYOCLON, NUM_EXP_CLON_0A8 NUMEXPCLON,  FECHA_CLON_0A8 FECHACLON,t2.ID_TIPO_EXPEDIENTE_051 IDTIPOEXPEDIENTE,");

		sb.append(" DECODE( t2.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, locale))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, locale))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, locale))
				.append("') AS TIPOEXPEDIENTEDESC,");

		sb.append(" ESTADO_CLON_0A8 ESTADOCLON, ");
		sb.append(" DECODE( t1.ESTADO_CLON_0A8, '" + EstadoClonacionEnum.FINALIZADO.getValue() + "', '")
				.append(this.msg.getMessage(EstadoClonacionEnum.FINALIZADO.getLabel(), null, locale))
				.append("', '" + EstadoClonacionEnum.ERROR.getValue() + "', '")
				.append(this.msg.getMessage(EstadoClonacionEnum.ERROR.getLabel(), null, locale))
				.append("', '" + EstadoClonacionEnum.PENDIENTE.getValue() + "', '")
				.append(this.msg.getMessage(EstadoClonacionEnum.PENDIENTE.getLabel(), null, locale))
				.append("') AS ESTADOCLONDESC");

		return sb.toString();
	}

	@Override()
	protected String getFrom() {
		return " FROM AA79BA8S01 t1 JOIN AA79B51S01 t2 ON t2.ANYO_051 = t1.ANYO_ORIG_0A8 AND t2.NUM_EXP_051 = t1.NUM_EXP_ORIG_0A8 ";
	}

	@Override()
	protected RowMapper<Clonacion> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return ServicioClonacionExpedientesDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return ID_0A8;
	}

	@Override()
	protected RowMapper<Clonacion> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(Clonacion bean, List<Object> params) {
		params.add(bean.getId());
		return " WHERE t1.ID_0A8 = ? ";
	}

	@Override
	protected String getWhere(Clonacion bean, List<Object> params) {
		StringBuilder where = new StringBuilder(ServicioClonacionExpedientesDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual(ID_0A8, bean.getId(), params));
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_ORIG_0A8,3,4)",
				bean.getAnyoOrig() != null ? bean.getAnyoOrig().toString() : bean.getAnyoOrig(), params));
		where.append(SqlUtils.generarWhereIgual(NUM_EXP_ORIG_0A8, bean.getNumExpOrig(), params));
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_CLON_0A8,3,4)",
				bean.getAnyoClon() != null ? bean.getAnyoClon().toString() : bean.getAnyoClon(), params));
		where.append(SqlUtils.generarWhereIgual(NUM_EXP_CLON_0A8, bean.getNumExpClon(), params));
		where.append(SqlUtils.generarWhereLikeNormalizado("t2.TITULO_051", bean.getTituloExpediente(), params, false));
		where.append(SqlUtils.generarWhereIgual("t2.ID_TIPO_EXPEDIENTE_051", bean.getIdTipoExpediente(), params));
		return where.toString();
	}

	@Override
	protected String getWhereLike(Clonacion bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(ServicioClonacionExpedientesDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual(ID_0A8, bean.getId(), params));
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_ORIG_0A8,3,4)",
				bean.getAnyoOrig() != null ? bean.getAnyoOrig().toString() : bean.getAnyoOrig(), params));
		where.append(SqlUtils.generarWhereIgual(NUM_EXP_ORIG_0A8, bean.getNumExpOrig(), params));
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_CLON_0A8,3,4)",
				bean.getAnyoClon() != null ? bean.getAnyoClon().toString() : bean.getAnyoClon(), params));
		where.append(SqlUtils.generarWhereIgual(NUM_EXP_CLON_0A8, bean.getNumExpClon(), params));
		where.append(SqlUtils.generarWhereLikeNormalizado("t2.TITULO_051", bean.getTituloExpediente(), params, false));
		where.append(SqlUtils.generarWhereIgual("t2.ID_TIPO_EXPEDIENTE_051", bean.getIdTipoExpediente(), params));
		return where.toString();
	}

	@Override()
	public Clonacion getNuevoNumExp(Clonacion clonacion) {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		long numExp = PLConnectionUtils.crearNumExp(year, this.getJdbcTemplate());
		if (numExp > 0) {
			clonacion.setNumExpClon(Integer.valueOf((int) numExp));
			clonacion.setAnyoClon((long) year);
		}
		return clonacion;
	}
}
