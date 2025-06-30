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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.EntidadExtendRowMapper;
import com.ejie.aa79b.dao.mapper.EntidadReceptorRowMapper;
import com.ejie.aa79b.dao.mapper.EntidadSolicitanteRowMapper;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoEnum;
import com.ejie.aa79b.model.enums.TipoEntidadEnum;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository(value = "entidadDao")
@Transactional()
public class EntidadDaoImpl extends GenericoDaoImpl<Entidad> implements EntidadDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String DECODE_T1_TIPO = ", DECODE(t1.TIPO";
	protected static final String AS_TIPODESC = ") AS TIPODESC";
	protected static final String SELECT_DATOS_ENTIDAD = "SELECT DISTINCT t1.CODIGO CODIGO, t1.TIPO TIPO, TRIM(t1.DESC_ES) DESCES, TRIM(NVL(t1.DESC_EU,t1.DESC_ES)) DESCEU, TRIM(t1.DESC_AMP_ES) DESCAMPES";
	protected static final String SELECT_DATOS_ENTIDAD_2 = ", TRIM(NVL(t1.DESC_AMP_EU,t1.DESC_AMP_ES)) DESCAMPEU, t1.CIF CIF, t1.ESTADO ESTADO ";
	protected static final String SELECT_DATOS_ENTIDAD_3 = ", t1.CDIRNORA CDIRNORA ";
	protected static final String FROM_VISTAENTIDADESSOLIC = " FROM X54JAPI_ENTIDADES_SOLIC t1 JOIN AA79B54S01 e1 ON t1.TIPO = e1.TIPO_ENTIDAD_054";
	protected static final String IGUALDAD_IDENTIDAD_VISTAENTIDADESSOLIC_54 = " AND t1.CODIGO = e1.ID_ENTIDAD_054";

	public EntidadDaoImpl() {
		super(Entidad.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.TIPO, DBConstants.CODIGO,
			DBConstants.DESCAMPES, DBConstants.DESCAMPEU, DBConstants.DESCES, DBConstants.DESCEU, DBConstants.CIF,
			DBConstants.ESTADO, DBConstants.CDIRNORA };

	private RowMapper<Entidad> rwMap = new EntidadSolicitanteRowMapper();

	private RowMapper<Entidad> rwMapReceptor = new EntidadReceptorRowMapper();

	private RowMapper<Entidad> rwMapPK = new RowMapper<Entidad>() {
		@Override()
		public Entidad mapRow(ResultSet rs, int rowNum) throws SQLException {
			Entidad bean = new Entidad();
			bean.setTipo(rs.getString(DBConstants.TIPO));
			bean.setCodigo(rs.getInt(DBConstants.CODIGO));
			return bean;
		}
	};

	private RowMapper<Entidad> rwMapFindOne = new EntidadExtendRowMapper();

	private RowMapper<Entidad> rwMapConfeccionArFactura = new RowMapper<Entidad>() {
		@Override()
		public Entidad mapRow(ResultSet rs, int rowNum) throws SQLException {
			Entidad bean = new Entidad();
			bean.setCif(rs.getString(DBConstants.CIF));
			bean.setDescEu(rs.getString(DBConstants.DESC_EU));
			Direccion direccion = new Direccion();
			direccion.setCodProvincia(rs.getString(DBConstants.PROVINCIA));
			direccion.setCodMunicipio(rs.getString(DBConstants.MUNICIPIO));
			direccion.setTxtLocalidad(rs.getString(DBConstants.LOCALIDAD));
			direccion.setTxtCalle(rs.getString(DBConstants.DIRECCION));
			direccion.setCodPostal(rs.getString(DBConstants.CODPOSTAL));
			bean.setDireccion(direccion);
			bean.setIva(rs.getString(DBConstants.IVA));
			return bean;
		}
	};

	private RowMapper<Entidad> getRwMapConfeccionArFactura() {
		return this.rwMapConfeccionArFactura;
	}

	@Override()
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder select = new StringBuilder();
		select.append("SELECT t1.TIPO TIPO");
		select.append(", t1.CODIGO CODIGO");
		select.append(", t1.DESC_AMP_ES DESCAMPES");
		select.append(", t1.DESC_AMP_EU DESCAMPEU");
		select.append(", t1.DESC_ES DESCES");
		select.append(", t1.DESC_EU DESCEU");
		select.append(", t1.CIF CIF");
		select.append(", t1.ESTADO ESTADO");
		select.append(", t1.CDIRNORA CDIRNORA");
		select.append(DECODE_T1_TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			select.append(", '").append(tipo.getValue()).append("'");
			select.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		select.append(AS_TIPODESC);
		return select.toString();
	}

	@Override()
	public Entidad find(Entidad bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(", t2.TIPONORA TIPONORA");
		query.append(", t2.CALLE CALLE");
		query.append(", t2.PORTAL PORTAL");
		query.append(", t2.ESCALERA ESCALERA");
		query.append(", t2.PISO PISO");
		query.append(", t2.MANO MANO");
		query.append(", t2.PUERTA PUERTA");
		query.append(", t2.CODPOSTAL CODPOSTAL");
		query.append(", t2.LOCALIDAD LOCALIDAD");
		query.append(", t2.CODMUNICIPIO CODMUNICIPIO");
		query.append(", t2.MUNICIPIO MUNICIPIO");
		query.append(", t2.CODPROVINCIA CODPROVINCIA");
		query.append(", t2.PROVINCIA PROVINCIA");
		query.append(", t2.PAIS PAIS");
		query.append(", t2.DIRECCION DIRECCION");
		query.append(this.getFrom(bean, params));
		query.append(" JOIN X54JNORA t2 ON t1.CDIRNORA = t2.CDIRNORA ");
		query.append(this.getWherePK(bean, params));

		List<Entidad> beanList = this.getJdbcTemplate().query(query.toString(), this.getRwMapFindOne(),
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(DaoConstants.FROM);
		from.append(DBConstants.VISTAX54JAPIENTIDADESSOLIC);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.T1_MINUSCULA);
		return from.toString();
	}

	protected String getFromGrupoTrabajo() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM X54JAPI_ENTIDADES_SOLIC t1");
		from.append(" JOIN AA79B27S01 g1");
		from.append(" ON t1.CODIGO = g1.ID_ENTIDAD_027");
		from.append(" AND t1.TIPO = g1.TIPO_ENTIDAD_027");
		return from.toString();
	}

	@Override()
	protected RowMapper<Entidad> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return "TIPO, CODIGO";
	}

	@Override()
	protected RowMapper<Entidad> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(Entidad bean, List<Object> params) {
		params.add(bean.getTipo());
		params.add(bean.getCodigo());
		return " WHERE t1.TIPO = ? AND t1.CODIGO = ?";
	}

	@Override()
	protected String getWhere(Entidad bean, List<Object> params) {
		StringBuilder where = new StringBuilder();
		where.append(SqlUtils.generarWhereIgual(DBConstants.TIPO, bean.getTipo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.CODIGO, bean.getCodigo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.DESC_AMP_ES, bean.getDescAmpEs(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.DESC_AMP_EU, bean.getDescAmpEu(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.DESC_ES, bean.getDescEs(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.DESC_EU, bean.getDescEu(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.CIF, bean.getCif(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.ESTADO, bean.getEstado(), params));
		if (bean.getDireccion() != null) {
			where.append(SqlUtils.generarWhereIgual(DBConstants.CDIRNORA, bean.getDireccion().getDirNora(), params));
		}
		return where.toString();
	}

	@Override()
	protected String getWhereLike(Entidad bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder();
		where.append(SqlUtils.generarWhereIgual(DBConstants.TIPO, bean.getTipo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.CODIGO, bean.getCodigo(), params));
		where.append(
				SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_AMP_ES, bean.getDescAmpEs(), params, startsWith));
		where.append(
				SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_AMP_EU, bean.getDescAmpEu(), params, startsWith));
		where.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_ES, bean.getDescEs(), params, startsWith));
		where.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_EU, bean.getDescEu(), params, startsWith));
		where.append(SqlUtils.generarWhereIgual(DBConstants.CIF, bean.getCif(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.ESTADO, bean.getEstado(), params));
		if (bean.getDireccion() != null) {
			where.append(SqlUtils.generarWhereIgual(DBConstants.CDIRNORA, bean.getDireccion().getDirNora(), params));
		}
		return where.toString();
	}

	protected RowMapper<Entidad> getRwMapFindOne() {
		return this.rwMapFindOne;
	}

	/**
	 * Funcion que devuelve una lista filtrada por el tipo, el cual se le pasa como
	 * parametro en un objeto Entidad
	 */

	@Override
	public List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp) {
		return this.getEntidadesGestorasConExpEnEstado(entidad, idEstadoExp, null, null);
	}

	@Override
	public List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp, Integer idFaseExp,
			String entidadAFacturar) {
		StringBuilder query = new StringBuilder();

		List<Object> params = new ArrayList<Object>();

		Locale locale = LocaleContextHolder.getLocale();
		query.append(SELECT_DATOS_ENTIDAD);
		query.append(SELECT_DATOS_ENTIDAD_2);
		query.append(SELECT_DATOS_ENTIDAD_3);
		query.append(DECODE_T1_TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			query.append(", '").append(tipo.getValue()).append("'");
			query.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		query.append(AS_TIPODESC);

		if (StringUtils.isNotEmpty(entidadAFacturar) && entidadAFacturar.equals(Constants.FACTURACION)) {
			// Se devolverán entidades que tengan registros en la 58 (entidad a
			// facturar)
			query.append(" FROM X54JAPI_ENTIDADES_SOLIC t1 JOIN AA79B58S01 e1 ON t1.TIPO = e1.TIPO_ENTIDAD_ASOC_058");
			query.append(" AND t1.CODIGO = e1.ID_ENTIDAD_ASOC_058");
		} else {
			query.append(FROM_VISTAENTIDADESSOLIC);
			query.append(IGUALDAD_IDENTIDAD_VISTAENTIDADESSOLIC_54);
		}

		comprobarEstadoyFase(idEstadoExp, idFaseExp, entidadAFacturar, query, params);
		if (entidad != null && entidad.getTipo() != null) {
			query.append(" WHERE t1.TIPO = ?");
			params.add(entidad.getTipo());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	/**
	 * @param idEstadoExp
	 * @param idFaseExp
	 * @param entidadAFacturar
	 * @param query
	 * @param params
	 */
	private void comprobarEstadoyFase(Integer idEstadoExp, Integer idFaseExp, String entidadAFacturar,
			StringBuilder query, List<Object> params) {
		if (idEstadoExp != null || idFaseExp != null) {
			if (StringUtils.isNotEmpty(entidadAFacturar) && entidadAFacturar.equals(Constants.FACTURACION)) {
				query.append(" JOIN AA79B51S01 e2 ON e2.ANYO_051 = e1.ANYO_058 AND e2.NUM_EXP_051 = e1.NUM_EXP_058");
			} else {
				query.append(" JOIN AA79B51S01 e2 ON e2.ANYO_051 = e1.ANYO_054 AND e2.NUM_EXP_051 = e1.NUM_EXP_054");
			}

			query.append(" JOIN AA79B59S01 e3 ON e2.ANYO_051 = e3.ANYO_059 AND e2.NUM_EXP_051 = e3.NUM_EXP_059");
			query.append(" AND e2.ESTADO_BITACORA_051 = e3.ID_ESTADO_BITACORA_059");
			if (idEstadoExp != null) {
				query.append(" AND e3.ID_ESTADO_EXP_059 = ?");
				params.add(idEstadoExp);
			}
			if (idFaseExp != null) {
				query.append(" AND e3.ID_FASE_EXPEDIENTE_059 = ?");
				params.add(idFaseExp);
			}
		}
	}

	/**
	 * Funcion que devuelve una lista filtrada por el tipo, el cual se le pasa como
	 * parametro en un objeto Entidad
	 */
	@Override
	public List<Entidad> getEntidadesConExpARelacionar(Entidad entidad) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Locale locale = LocaleContextHolder.getLocale();
		query.append(SELECT_DATOS_ENTIDAD);
		query.append(SELECT_DATOS_ENTIDAD_2);
		query.append(SELECT_DATOS_ENTIDAD_3);
		query.append(DECODE_T1_TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			query.append(", '").append(tipo.getValue()).append("'");
			query.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		query.append(AS_TIPODESC);
		query.append(FROM_VISTAENTIDADESSOLIC);
		query.append(IGUALDAD_IDENTIDAD_VISTAENTIDADESSOLIC_54);
		query.append(" JOIN AA79B51S01 e2 ON e1.ANYO_054 = e2.ANYO_051");
		query.append(" AND e1.NUM_EXP_054 = e2.NUM_EXP_051");
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(
				DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ESTADO_BAJA_051, Constants.ALTA,
				params));
		query.append(SqlUtils.generarWhereLike("t1.ESTADO", EstadoEnum.ALTA.getValue(), params, false));

		if (entidad != null && entidad.getTipo() != null) {
			query.append(DaoConstants.AND + " t1.TIPO = ?");
			params.add(entidad.getTipo());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	/**
	 * Funcion que devuelve una lista filtrada por el tipo, el cual se le pasa como
	 * parametro en un objeto Entidad
	 */
	@Override
	public List<Entidad> getEntidadesSolicitantes(Entidad entidad) {
		StringBuilder query = new StringBuilder();

		Locale locale = LocaleContextHolder.getLocale();
		query.append(
				"SELECT DISTINCT t1.CODIGO CODIGO, t1.TIPO TIPO, t1.DESC_ES DESCES, TRIM(NVL(t1.DESC_EU,t1.DESC_ES)) DESCEU, t1.DESC_AMP_ES DESCAMPES");
		query.append(", TRIM(NVL(t1.DESC_AMP_EU,t1.DESC_AMP_ES)) DESCAMPEU, t1.CIF CIF, t1.ESTADO ESTADO ");
		query.append(SELECT_DATOS_ENTIDAD_3);
		query.append(DECODE_T1_TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			query.append(", '").append(tipo.getValue()).append("'");
			query.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		query.append(AS_TIPODESC);
		query.append(" FROM X54JAPI_ENTIDADES_SOLIC t1");

		query.append(DaoConstants.WHERE_1_1);

		List<Object> params = new ArrayList<Object>();
		query.append(SqlUtils.generarWhereLike("t1.ESTADO", EstadoEnum.ALTA.getValue(), params, false));
		if (entidad != null) {
			query.append(SqlUtils.generarWhereLike("t1.DESC_AMP_EU", entidad.getDescAmpEu(), params, false));
			if (entidad.getTipo() != null) {
				query.append(" AND t1.TIPO = ?");
				params.add(entidad.getTipo());
			}
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	/**
	 * Funcion que devuelve una lista filtrada por el tipo, el cual se le pasa como
	 * parametro en un objeto Entidad
	 */
	@Override
	public List<Entidad> getEntidadesConContactosSolicitantes(Entidad entidad) {
		return this.getEntidadesConContactosSolicitantes(entidad, true);
	}

	/**
	 * Funcion que devuelve una lista filtrada por el tipo, el cual se le pasa como
	 * parametro en un objeto Entidad
	 */
	@Override
	public List<Entidad> getEntidadesConContactosSolicitantes(Entidad entidad, boolean mostrarSoloAltas) {

		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(
				"SELECT TIPO,TIPODESC, CODIGO, DESCAMPES, DESCAMPEU, DESCES, DESCEU, CIF, ESTADO, CDIRNORA FROM ( (");

		// Primera parte de la UNION
		query.append(this.getSelect()).append(this.getFrom(entidad, params));
		query.append(
				" RIGHT JOIN X54JAPI_SOLICITANTES t2 ON t1.TIPO = t2.TIPO_ENTIDAD AND t1.CODIGO = t2.COD_ENTIDAD ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhereLike(entidad, false, params, false));
		query.append(" AND t1.ESTADO = ").append("'" + Constants.ALTA + "'");
		query.append(" AND ( t2.GESTOR_EXPEDIENTES = '" + Constants.SI + "' OR t2.GESTOR_EXPEDIENTES_VIP = '"
				+ Constants.SI + "' OR t2.GESTOR_EXPEDIENTES_BOPV = '" + Constants.SI + "') ");

		query.append(" ) UNION ( ");

		query.append(this.getSelect());
		query.append(
				" FROM AA79B36S01 t2 LEFT JOIN X54JAPI_ENTIDADES_SOLIC t1 ON (t1.TIPO = t2.TIPO_ENTIDAD_036 AND t1.CODIGO = t2.ID_ENTIDAD_036 ) ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhereLike(entidad, false, params, false));
		// para poder buscar también entidades de baja...
		if (mostrarSoloAltas) {
			query.append(" AND t1.ESTADO = ").append("'" + Constants.ALTA + "'");
		}

		query.append(
				" ) ) GROUP BY TIPO,TIPODESC, CODIGO, DESCAMPES, DESCAMPEU, DESCES, DESCEU, CIF, ESTADO, CDIRNORA");

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override
	public List<Entidad> findAllLike(Entidad bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom());

		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhereLike(bean, startsWith, params, false));

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override
	public List<Entidad> findAllLikeGrupoTrabajo(Entidad bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFromGrupoTrabajo());

		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhereLike(bean, startsWith, params, false));

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override
	public List<Entidad> getEntidadesReceptores(Entidad entidad) {
		StringBuilder query = new StringBuilder();

		Locale locale = LocaleContextHolder.getLocale();
		query.append(
				"SELECT DISTINCT t1.CODIGO CODIGO, t1.TIPO_ENTIDAD TIPO, t1.DESC_CR DESCES, TRIM(NVL(t1.DESC_ER,t1.DESC_CR)) DESCEU, t1.DESC_CA DESCAMPES");
		query.append(", TRIM(NVL(t1.DESC_EA,t1.DESC_CA)) DESCAMPEU, t1.CIF CIF ");
		query.append(", DECODE(t1.TIPO_ENTIDAD");
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			query.append(", '").append(tipo.getValue()).append("'");
			query.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		query.append(AS_TIPODESC);
		query.append(" FROM X54JAPI_ENTDPTOS t1 ");

		List<Object> params = new ArrayList<Object>();

		query.append(" WHERE t1.TIPO_ENTIDAD LIKE '%" + TipoEntidadEnum.DEPARTAMENTO.getValue() + "%' ");
		query.append(SqlUtils.generarWhereLike("t1.DESC_EA", entidad.getDescAmpEu(), params, false));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapReceptor, params.toArray());
	}

	@Override
	public Entidad obtenerDatosConfeccionarFactura(Entidad entidad) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(DaoConstants.SELECT + " t1.CIF " + DBConstants.CIF + DaoConstants.SIGNO_COMA);
		query.append(" t1.DESC_EU " + DBConstants.DESC_EU + DaoConstants.SIGNO_COMA);
		query.append(" t1.DESC_ES " + DBConstants.DESC_ES + DaoConstants.SIGNO_COMA);
		query.append(" t2.PROVINCIA " + DBConstants.PROVINCIA + DaoConstants.SIGNO_COMA);
		query.append(" t2.MUNICIPIO " + DBConstants.MUNICIPIO + DaoConstants.SIGNO_COMA);
		query.append(" t2.LOCALIDAD " + DBConstants.LOCALIDAD + DaoConstants.SIGNO_COMA);
		query.append(" t2.DIRECCION " + DBConstants.DIRECCION + DaoConstants.SIGNO_COMA);
		query.append(
				" t2." + DBConstants.CODPOSTAL + DaoConstants.BLANK + DBConstants.CODPOSTAL + DaoConstants.SIGNO_COMA);
		query.append(" t1.IVA " + DBConstants.IVA);
		query.append(DaoConstants.FROM + DBConstants.VISTAX54JAPIENTIDADESSOLIC + " t1 ");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JNORA + " t2 ");
		query.append(DaoConstants.ON + " t1.CDIRNORA " + DaoConstants.SIGNOIGUAL + " t2.CDIRNORA ");
		query.append(DaoConstants.WHERE_1_1);
		if (entidad != null) {
			query.append(SqlUtils.generarWhereIgual("t1.TIPO", entidad.getTipo(), params));
			query.append(SqlUtils.generarWhereIgual("t1.CODIGO", entidad.getCodigo(), params));
		}
		List<Entidad> beanList = this.getJdbcTemplate().query(query.toString(), this.getRwMapConfeccionArFactura(),
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public List<Entidad> getEntidadesGestAnulacion(Entidad entidad) {
		StringBuilder query = new StringBuilder();

		List<Object> params = new ArrayList<Object>();

		Locale locale = LocaleContextHolder.getLocale();
		query.append(SELECT_DATOS_ENTIDAD);
		query.append(SELECT_DATOS_ENTIDAD_2);
		query.append(SELECT_DATOS_ENTIDAD_3);
		query.append(DECODE_T1_TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			query.append(", '").append(tipo.getValue()).append("'");
			query.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		query.append(AS_TIPODESC);

		query.append(FROM_VISTAENTIDADESSOLIC);
		query.append(IGUALDAD_IDENTIDAD_VISTAENTIDADESSOLIC_54);

		// TABLA 51 - Expediente
		query.append(" JOIN AA79B51S01 e2 ON e2.ANYO_051 = e1.ANYO_054 AND e2.NUM_EXP_051 = e1.NUM_EXP_054");

		// TABLA 59 - Bitacora expediente
		query.append(DaoConstants.BLANK);
		query.append("JOIN AA79B59S01 ESTADO_ACTUAL ");
		query.append("ON e2.NUM_EXP_051 = ESTADO_ACTUAL.NUM_EXP_059 ");
		query.append("AND e2.ANYO_051 = ESTADO_ACTUAL.ANYO_059 ");
		query.append("AND e2.ESTADO_BITACORA_051 = ESTADO_ACTUAL.ID_ESTADO_BITACORA_059 ");

		// TABLA 64 - Requerimientos expediente
		query.append("JOIN AA79B64S01 s1 ");
		query.append("ON ESTADO_ACTUAL.ID_REQUERIMIENTO_059 = s1.ID_064 ");

		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual("e2.ESTADO_BAJA_051", Constants.ALTA, params));
		query.append(DaoConstants.AND);
		query.append(QueryUtils.getWhereExpAAnular());

		if (entidad != null && entidad.getTipo() != null) {
			query.append(DaoConstants.AND);
			query.append("t1.TIPO = ?");
			params.add(entidad.getTipo());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override
	public boolean buscarEntidadAlta(Entidad entidad) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		params.add(entidad.getTipo());
		params.add(entidad.getCodigo());
		query.append(
				"SELECT COUNT(1) FROM X54JAPI_ENTIDADES_SOLIC T1 WHERE T1.TIPO =? AND T1.CODIGO =? AND T1.ESTADO = '")
				.append(Constants.ALTA).append("'");
		if (this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Entidad> getEntidadesGestorasConExpConfidenciales(Entidad entidad, String dni) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(getSelectEntidadesGestoras());
		query.append(getFromEntidadesGestoras());
		query.append(getJoinTabla51And54());
		// Expediente traducción/revisión
		query.append(DaoConstants.JOIN + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_053);
		query.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		query.append(DaoConstants.AND + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_053);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_051);
		query.append(" LEFT JOIN AA79B81S01 t3 ON t3.ANYO_081     = s1.ANYO_051 AND t3.NUM_EXP_081 = s1.NUM_EXP_051 ");

		// Where
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_CONFIDENCIAL_053,
				Constants.SI, params));

		if (entidad != null && entidad.getTipo() != null) {
			query.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO,
					entidad.getTipo(), params));
		}
		query.append(" AND ( s1.DNI_TECNICO_051        = ? OR t3.DNI_RECURSO_081 = ? ) ");
		params.add(dni);
		params.add(dni);

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	/**
	 * @return String
	 */
	private String getJoinTabla51And54() {
		StringBuilder query = new StringBuilder();

		// Expediente
		query.append(DaoConstants.JOIN + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.S1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_054);
		query.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		query.append(DaoConstants.AND + DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_054);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_051);

		return query.toString();
	}

	/**
	 * @return String
	 */
	private String getJoinTabla51And58() {
		StringBuilder query = new StringBuilder();

		// Expediente
		query.append(DaoConstants.JOIN + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.S1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_058);
		query.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		query.append(DaoConstants.AND + DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_058);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_051);

		return query.toString();
	}

	/**
	 * @return String
	 */
	private String getFromEntidadesGestoras() {
		StringBuilder query = getFromEntidades();
		// Gestor de expediente
		query.append(DaoConstants.JOIN + DBConstants.TABLA_54 + DaoConstants.BLANK + DaoConstants.E1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.CODIGO + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054);

		return query.toString();
	}

	/**
	 * @return String
	 */
	private StringBuilder getFromEntidades() {
		StringBuilder query = new StringBuilder();

		// From
		query.append(DaoConstants.FROM + DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK
				+ DaoConstants.T1_MINUSCULA);

		return query;
	}

	/**
	 * @param query
	 * @param locale
	 * @return
	 */
	private String getSelectEntidadesGestoras() {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT + DaoConstants.DISTINCT);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.CODIGO + DaoConstants.BLANK + DBConstants.CODIGO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO + DaoConstants.BLANK
				+ DBConstants.TIPO);
		query.append(DaoConstants.SIGNO_COMA + "TRIM(" + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DESC_ES + ") "
				+ DaoConstants.BLANK + DBConstants.DESCES);
		query.append(DaoConstants.SIGNO_COMA + "TRIM(" + DaoConstants.NVL);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DESC_EU);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DESC_ES);
		query.append(DaoConstants.CERRAR_PARENTESIS + ") " + DBConstants.DESCEU);
		query.append(DaoConstants.SIGNO_COMA + "TRIM(" + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES
				+ ") " + DaoConstants.BLANK + DBConstants.DESCAMPES);
		query.append(DaoConstants.SIGNO_COMA + "TRIM(" + DaoConstants.NVL);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_EU);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES);
		query.append(DaoConstants.CERRAR_PARENTESIS + ") " + DBConstants.DESCAMPEU);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.CIF + DaoConstants.BLANK
				+ DBConstants.CIF);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO + DaoConstants.BLANK
				+ DBConstants.ESTADO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.CDIRNORA
				+ DaoConstants.BLANK + DBConstants.CDIRNORA);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.DECODE);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			query.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO);
			query.append(tipo.getValue());
			query.append(DaoConstants.SIGNO_APOSTROFO);
			query.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO);
			query.append(this.msg.getMessage(tipo.getLabel(), null, locale));
			query.append(DaoConstants.SIGNO_APOSTROFO);
		}
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPODESC);

		return query.toString();
	}

	@Override
	public List<Entidad> getEntidadesGestorasActDatosFacturacion(Entidad entidad, String entidadAFacturar) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(getSelectEntidadesGestoras());

		if (StringUtils.isNotEmpty(entidadAFacturar) && entidadAFacturar.equals(Constants.FACTURACION)) {
			// Se devolverán entidades que tengan registros en la 58 (entidad a
			// facturar)
			query.append(getFromEntidadesAFacturar());
		} else {
			query.append(getFromEntidadesGestoras());
		}

		if (StringUtils.isNotEmpty(entidadAFacturar) && entidadAFacturar.equals(Constants.FACTURACION)) {
			query.append(getJoinTabla51And58());
		} else {
			query.append(getJoinTabla51And54());
		}

		query.append(QueryUtils.getJoinTabla59and51());
		query.append(QueryUtils.getWhereActDatosFactEntidades(params));

		if (entidad != null && entidad.getTipo() != null) {
			query.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO,
					entidad.getTipo(), params));
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	/**
	 * @return String
	 */
	private String getFromEntidadesAFacturar() {
		StringBuilder query = new StringBuilder();

		query.append(getFromEntidades());
		query.append(DaoConstants.JOIN + DBConstants.TABLA_58 + DaoConstants.BLANK + DaoConstants.E1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_ASOC_058);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.CODIGO + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_ASOC_058);

		return query.toString();
	}

	@Override
	public List<Entidad> getEntidadesConGestorActivo(Entidad entidad) {
		StringBuilder queryGECGA = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Locale locale = LocaleContextHolder.getLocale();
		queryGECGA.append(SELECT_DATOS_ENTIDAD);
		queryGECGA.append(SELECT_DATOS_ENTIDAD_2);
		queryGECGA.append(SELECT_DATOS_ENTIDAD_3);
		queryGECGA.append(DECODE_T1_TIPO);
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			queryGECGA.append(", '").append(tipo.getValue()).append("'");
			queryGECGA.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		queryGECGA.append(AS_TIPODESC);
		queryGECGA.append(" FROM X54JAPI_ENTIDADES_SOLIC t1 ");
		queryGECGA.append(" JOIN X54JAPI_SOLICITANTES t2 ");
		queryGECGA.append(" ON t2.COD_ENTIDAD = t1.CODIGO ");
		queryGECGA.append(" AND t2.TIPO_ENTIDAD = t1.TIPO ");
		queryGECGA.append(" AND t2.ESTADO = 'V' ");
		queryGECGA.append(" WHERE 1 = 1 ");
		queryGECGA.append(SqlUtils.generarWhereLike("t1.ESTADO", EstadoEnum.ALTA.getValue(), params, false));
		queryGECGA.append(" AND ( ( 1           =1 ");
		queryGECGA.append(SqlUtils.generarWhereLike("t2.GESTOR_EXPEDIENTES", ActivoEnum.SI.getValue(), params, false));
		queryGECGA.append(" ) ");
		queryGECGA.append(" OR ( 1                     =1 ");
		queryGECGA.append(
				SqlUtils.generarWhereLike("t2.GESTOR_EXPEDIENTES_VIP", ActivoEnum.SI.getValue(), params, false));
		queryGECGA.append(" ) ");
		queryGECGA.append(" OR ( 1                         =1 ");
		queryGECGA.append(
				SqlUtils.generarWhereLike("t2.GESTOR_EXPEDIENTES_BOPV", ActivoEnum.SI.getValue(), params, false));
		queryGECGA.append(" ) ) ");
		if (entidad != null && entidad.getTipo() != null) {
			queryGECGA.append(DaoConstants.AND + " t1.TIPO = ?");
			params.add(entidad.getTipo());
		}

		return this.getJdbcTemplate().query(queryGECGA.toString(), this.rwMap, params.toArray());
	}

}
