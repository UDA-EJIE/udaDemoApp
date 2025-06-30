package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.ReceptorAutorizado;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.TipoEntidadEnum;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

@Repository()
@Transactional()
public class ReceptorAutorizadoDaoImpl extends GenericoDaoImpl<ReceptorAutorizado> implements ReceptorAutorizadoDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public ReceptorAutorizadoDaoImpl() {
		// Constructor
		super(ReceptorAutorizado.class);
	}

	public static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ANYO, DBConstants.NUMEXP,
			DBConstants.TIPIDEN, DBConstants.PREDNI, DBConstants.DNI, DBConstants.SUFDNI, DBConstants.NOMBRE,
			DBConstants.APEL1, DBConstants.APEL2, DBConstants.TIPOENTIDAD, DBConstants.CODENTIDAD, DBConstants.ESTADO,
			DBConstants.DESCES, DBConstants.DESCEU, DBConstants.DESCAMPES, DBConstants.DESCAMPEU, DBConstants.CIF,
			DBConstants.FACTURABLE, DBConstants.IVA, DBConstants.CDIRNORA, "APELLIDOS", DBConstants.TIPOENTIDADDESCEU };

	private RowMapper<ReceptorAutorizado> rwMap = new ReceptorAutorizadoRM();

	private class ReceptorAutorizadoRM implements RowMapper<ReceptorAutorizado> {
		@Override()
		public ReceptorAutorizado mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReceptorAutorizado receptor = new ReceptorAutorizado();
			receptor.setAnyo(rs.getLong(DBConstants.ANYO));
			receptor.setNumExp(rs.getInt(DBConstants.NUMEXP));
			receptor.setTipIden(rs.getInt(DBConstants.TIPIDEN));
			receptor.setPreDni(rs.getString(DBConstants.PREDNI));
			receptor.setDni(rs.getString(DBConstants.DNI));
			receptor.setSufDni(rs.getString(DBConstants.SUFDNI));
			receptor.setNombre(rs.getString(DBConstants.NOMBRE));
			receptor.setApellido1(rs.getString(DBConstants.APEL1));
			receptor.setApellido2(rs.getString(DBConstants.APEL2));

			Entidad entidad = new Entidad();
			entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
			entidad.setTipoDesc(rs.getString(DBConstants.TIPOENTIDADDESCEU));
			entidad.setCodigo(rs.getInt(DBConstants.CODENTIDAD));
			entidad.setEstado(rs.getString(DBConstants.ESTADO));
			entidad.setDescEs(rs.getString(DBConstants.DESCES));
			entidad.setDescEu(rs.getString(DBConstants.DESCEU));
			entidad.setDescAmpEs(rs.getString(DBConstants.DESCAMPES));
			entidad.setDescAmpEu(rs.getString(DBConstants.DESCAMPEU));
			entidad.setCif(rs.getString(DBConstants.CIF));
			entidad.setFacturable(rs.getString(DBConstants.FACTURABLE));
			entidad.setIva(rs.getString(DBConstants.IVA));

			DireccionNora direccionNora = new DireccionNora();
			direccionNora.setDirNora(rs.getInt(DBConstants.CDIRNORA));
			entidad.setDireccion(direccionNora);

			receptor.setEntidad(entidad);

			return receptor;
		}
	}

	private RowMapper<ReceptorAutorizado> rwMapReceptoresAutorizados = new RowMapper<ReceptorAutorizado>() {
		@Override()
		public ReceptorAutorizado mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			ReceptorAutorizado receptoresAutorizados = new ReceptorAutorizado();
			receptoresAutorizados.setPreDni(resultSet.getString(DBConstants.PREDNI));
			receptoresAutorizados.setDni(resultSet.getString(DBConstants.DNI));
			receptoresAutorizados.setSufDni(resultSet.getString(DBConstants.SUFDNI));
			receptoresAutorizados.setNombre(resultSet.getString(DBConstants.NOMBRE));
			receptoresAutorizados.setApellido1(resultSet.getString(DBConstants.APEL1));
			receptoresAutorizados.setApellido2(resultSet.getString(DBConstants.APEL2));
			receptoresAutorizados.setDniCompleto(resultSet.getString(DBConstants.DNICOMPLETO));
			receptoresAutorizados.setNombreCompleto(resultSet.getString(DBConstants.NOMBRECOMPLETO));
			Entidad entidad = new Entidad();
			entidad.setDescEs(resultSet.getString("DESCENTIDADES"));
			entidad.setDescEu(resultSet.getString("DESCENTIDADEU"));
			receptoresAutorizados.setEntidad(entidad);
			return receptoresAutorizados;
		}
	};

	private RowMapper<ReceptorAutorizado> rwMapPK = new RowMapper<ReceptorAutorizado>() {
		@Override()
		public ReceptorAutorizado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ReceptorAutorizado bean = new ReceptorAutorizado();
			bean.setDni(resultSet.getString(DBConstants.DNI));
			return bean;
		}
	};

	@Override
	public ReceptorAutorizado add(ReceptorAutorizado receptorAutorizado) {
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO AA79B63S01 ");
		insert.append("(ANYO_063, NUM_EXP_063, DNI_RECEPTOR_063, TIPO_ENTIDAD_063, ID_ENTIDAD_063) ");
		insert.append("VALUES (?,?,?,?,?)");
		this.getJdbcTemplate().update(insert.toString(), receptorAutorizado.getAnyo(), receptorAutorizado.getNumExp(),
				receptorAutorizado.getDni(), receptorAutorizado.getEntidad().getTipo(),
				receptorAutorizado.getEntidad().getCodigo());
		return receptorAutorizado;

	}

	@Override()
	public void remove(ReceptorAutorizado receptorAutorizado) {
		String query = "DELETE FROM AA79B63S01 WHERE ANYO_063  = ? AND NUM_EXP_063 = ? AND DNI_RECEPTOR_063 = ?";

		List<Object> params = new ArrayList<Object>();

		params.add(receptorAutorizado.getAnyo());
		params.add(receptorAutorizado.getNumExp());
		params.add(receptorAutorizado.getDni());

		this.getJdbcTemplate().update(query, params.toArray());
	}

	private RowMapper<Gestor> rwMapGestorRepresentante = new RowMapper<Gestor>() {
		@Override()
		public Gestor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Gestor gestor = new Gestor();
			Solicitante solicitante = new Solicitante();
			solicitante.setPreDni(resultSet.getString(DBConstants.PREDNI));
			solicitante.setDni(resultSet.getString(DBConstants.DNI));
			solicitante.setSufDni(resultSet.getString(DBConstants.SUFDNI));
			solicitante.setNombre(resultSet.getString(DBConstants.NOMBRE));
			solicitante.setApellido1(resultSet.getString(DBConstants.APEL1));
			solicitante.setApellido2(resultSet.getString(DBConstants.APEL2));
			solicitante.setGestorExpedientes(resultSet.getString(DBConstants.GESTOREXPEDIENTES));
			solicitante.setGestorExpedientesVIP(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIP));
			solicitante.setGestorExpedientesBOPV(resultSet.getString(DBConstants.GESTOREXPEDIENTESBOPV));

			gestor.setSolicitante(solicitante);

			return gestor;
		}
	};

	@Override()
	protected String getSelect() {

		Locale eu = new Locale("eu");
		StringBuilder selectReceptor = new StringBuilder();
		selectReceptor.append("SELECT");
		selectReceptor.append(" t1.ANYO_063 ANYO");
		selectReceptor.append(", t1.NUM_EXP_063 NUMEXP");
		selectReceptor.append(", t1.DNI_RECEPTOR_063 DNI ");
		selectReceptor.append(", p1.TIPIDEN_077 TIPIDEN");
		selectReceptor.append(", p1.PREDNI_077 PREDNI");
		selectReceptor.append(", p1.SUFDNI_077 SUFDNI");
		selectReceptor.append(", p1.NOMBRE_077 NOMBRE");
		selectReceptor.append(", p1.APEL1_077 APEL1");
		selectReceptor.append(", p1.APEL2_077 APEL2");
		selectReceptor.append(", p1.APEL1_077 || '/' || p1.APEL2_077 APELLIDOS");
		selectReceptor.append(", t1.TIPO_ENTIDAD_063 TIPOENTIDAD");
		selectReceptor.append(", DECODE(t1.TIPO_ENTIDAD_063");
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			selectReceptor.append(", '").append(tipo.getValue()).append("'");
			selectReceptor.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, eu)).append("'");
		}
		selectReceptor.append(") AS TIPOENTIDADDESCEU");
		selectReceptor.append(", t1.ID_ENTIDAD_063 CODENTIDAD");
		selectReceptor.append(", e1.ESTADO ESTADO");
		selectReceptor.append(", e1.DESC_ES AS DESCES");
		selectReceptor.append(", e1.DESC_EU AS DESCEU");
		selectReceptor.append(", e1.DESC_AMP_ES AS DESCAMPES");
		selectReceptor.append(", e1.DESC_AMP_EU AS DESCAMPEU");
		selectReceptor.append(", e1.CIF CIF");
		selectReceptor.append(", e1.FACTURABLE FACTURABLE");
		selectReceptor.append(", e1.IVA IVA");
		selectReceptor.append(", e1.CDIRNORA CDIRNORA");
		return selectReceptor.toString();
	}

	@Override()
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM AA79B63S01 t1 ");
		// NombreApellidosGestor
		from.append("LEFT JOIN AA79B77S01 p1 ON t1.DNI_RECEPTOR_063 = p1.DNI_077 ");
		// Entidad
		from.append(
				"LEFT JOIN X54JAPI_ENTIDADES_SOLIC e1 ON t1.ID_ENTIDAD_063 = e1.CODIGO AND t1.TIPO_ENTIDAD_063 = e1.TIPO ");
		return from.toString();
	}

	@Override()
	protected RowMapper<ReceptorAutorizado> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return ReceptorAutorizadoDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return DBConstants.DNI;
	}

	@Override()
	protected RowMapper<ReceptorAutorizado> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(ReceptorAutorizado bean, List<Object> params) {
		params.add(bean.getDni());
		return " WHERE DNI = ?";
	}

	@Override()
	protected String getWhere(ReceptorAutorizado bean, List<Object> params) {
		StringBuilder whereReceptor = new StringBuilder();

		whereReceptor.append(SqlUtils.generarWhereIgual("t1.ANYO_063", bean.getAnyo(), params));
		whereReceptor.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_063", bean.getNumExp(), params));
		whereReceptor.append(SqlUtils.generarWhereIgual("p1.TIPIDEN_077", bean.getTipIden(), params));
		whereReceptor.append(SqlUtils.generarWhereLike("p1.PREDNI_077", bean.getPreDni(), params, false));
		whereReceptor.append(SqlUtils.generarWhereIgualDni("t1.DNI_RECEPTOR_063", bean.getDni(), params));
		whereReceptor.append(SqlUtils.generarWhereLike("p1.SUFDNI_077", bean.getSufDni(), params, false));
		whereReceptor.append(SqlUtils.generarWhereLikeNormalizado("p1.NOMBRE_077", bean.getNombre(), params, false));
		whereReceptor.append(SqlUtils.generarWhereLikeNormalizado("p1.APEL1_077", bean.getApellido1(), params, false));
		whereReceptor.append(SqlUtils.generarWhereLikeNormalizado("p1.APEL2_077", bean.getApellido2(), params, false));

		if (bean.getEntidad() != null) {
			whereReceptor.append(
					SqlUtils.generarWhereLike("t1.TIPO_ENTIDAD_063", bean.getEntidad().getTipo(), params, false));
			whereReceptor
					.append(SqlUtils.generarWhereIgual("t1.ID_ENTIDAD_063", bean.getEntidad().getCodigo(), params));
			whereReceptor.append(SqlUtils.generarWhereLike("e1.ESTADO", bean.getEntidad().getEstado(), params, false));
			whereReceptor.append(
					SqlUtils.generarWhereLikeNormalizado("e1.DESC_ES", bean.getEntidad().getDescEs(), params, false));
			whereReceptor.append(
					SqlUtils.generarWhereLikeNormalizado("e1.DESC_EU", bean.getEntidad().getDescEu(), params, false));
			whereReceptor.append(SqlUtils.generarWhereLikeNormalizado("e1.DESC_AMP_ES",
					bean.getEntidad().getDescAmpEs(), params, false));
			whereReceptor.append(SqlUtils.generarWhereLikeNormalizado("e1.DESC_AMP_EU",
					bean.getEntidad().getDescAmpEu(), params, false));
			whereReceptor.append(SqlUtils.generarWhereLike("e1.CIF", bean.getEntidad().getCif(), params, false));
			whereReceptor.append(SqlUtils.generarWhereLike("e1.FACTURABLE", bean.getEntidad().getCif(), params, false));
			whereReceptor.append(SqlUtils.generarWhereLike("e1.IVA", bean.getEntidad().getCif(), params, false));
			if (bean.getEntidad().getDireccion() != null) {
				whereReceptor.append(SqlUtils.generarWhereIgual("e1.CDIRNORA",
						bean.getEntidad().getDireccion().getDirNora(), params));
			}
		}

		return whereReceptor.toString();
	}

	@Override()
	protected String getWhereLike(ReceptorAutorizado bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLikeReceptor = new StringBuilder();

		whereLikeReceptor.append(SqlUtils.generarWhereIgual("t1.ANYO_063", bean.getAnyo(), params));
		whereLikeReceptor.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_063", bean.getNumExp(), params));
		whereLikeReceptor.append(SqlUtils.generarWhereIgual("p1.TIPIDEN_077", bean.getTipIden(), params));
		whereLikeReceptor.append(SqlUtils.generarWhereLike("p1.PREDNI_077", bean.getPreDni(), params, startsWith));
		whereLikeReceptor.append(SqlUtils.generarWhereIgualDni("t1.DNI_RECEPTOR_063", bean.getDni(), params));
		whereLikeReceptor.append(SqlUtils.generarWhereLike("p1.SUFDNI_077", bean.getSufDni(), params, startsWith));
		whereLikeReceptor
				.append(SqlUtils.generarWhereLikeNormalizado("p1.NOMBRE_077", bean.getNombre(), params, startsWith));
		whereLikeReceptor
				.append(SqlUtils.generarWhereLikeNormalizado("p1.APEL1_077", bean.getApellido1(), params, startsWith));
		whereLikeReceptor
				.append(SqlUtils.generarWhereLikeNormalizado("p1.APEL2_077", bean.getApellido2(), params, startsWith));

		if (bean.getEntidad() != null) {
			whereLikeReceptor.append(
					SqlUtils.generarWhereLike("t1.TIPO_ENTIDAD_063", bean.getEntidad().getTipo(), params, startsWith));
			whereLikeReceptor
					.append(SqlUtils.generarWhereIgual("t1.ID_ENTIDAD_063", bean.getEntidad().getCodigo(), params));
			whereLikeReceptor
					.append(SqlUtils.generarWhereLike("e1.ESTADO", bean.getEntidad().getEstado(), params, startsWith));
			whereLikeReceptor.append(SqlUtils.generarWhereLikeNormalizado("e1.DESC_ES", bean.getEntidad().getDescEs(),
					params, startsWith));
			whereLikeReceptor.append(SqlUtils.generarWhereLikeNormalizado("e1.DESC_EU", bean.getEntidad().getDescEu(),
					params, startsWith));
			whereLikeReceptor.append(SqlUtils.generarWhereLikeNormalizado("e1.DESC_AMP_ES",
					bean.getEntidad().getDescAmpEs(), params, startsWith));
			whereLikeReceptor.append(SqlUtils.generarWhereLikeNormalizado("e1.DESC_AMP_EU",
					bean.getEntidad().getDescAmpEu(), params, startsWith));
			whereLikeReceptor
					.append(SqlUtils.generarWhereLike("e1.CIF", bean.getEntidad().getCif(), params, startsWith));
			whereLikeReceptor
					.append(SqlUtils.generarWhereLike("e1.FACTURABLE", bean.getEntidad().getCif(), params, startsWith));
			whereLikeReceptor
					.append(SqlUtils.generarWhereLike("e1.IVA", bean.getEntidad().getCif(), params, startsWith));
			if (bean.getEntidad().getDireccion() != null) {
				whereLikeReceptor.append(SqlUtils.generarWhereIgual("e1.CDIRNORA",
						bean.getEntidad().getDireccion().getDirNora(), params));
			}
		}

		return whereLikeReceptor.toString();
	}

	@Override
	public Long comprobarSiExiste(ReceptorAutorizado receptorAutorizado) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("SELECT COUNT(1) ");
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE_1_1);
		if (receptorAutorizado != null && receptorAutorizado.getAnyo() != null && receptorAutorizado.getNumExp() != null
				&& receptorAutorizado.getDni() != null) {
			query.append(SqlUtils.generarWhereIgual("t1.ANYO_063", receptorAutorizado.getAnyo(), params));
			query.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_063", receptorAutorizado.getNumExp(), params));
			query.append(SqlUtils.generarWhereIgualDni("t1.DNI_RECEPTOR_063", receptorAutorizado.getDni(), params));
			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
		} else {
			return -1L;
		}

	}

	@Override
	public Boolean getPermisosUsuario(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append("SELECT PREDNI, DNI, SUFDNI, NOMBRE, APEL1, APEL2, ");
		query.append(
				"GESTOR_EXPEDIENTES GESTOREXPEDIENTES, GESTOR_EXPEDIENTES_BOPV GESTOREXPEDIENTESBOPV, GESTOR_EXPEDIENTES_VIP GESTOREXPEDIENTESVIP, COORDINADOR ");
		query.append("FROM X54JAPI_SOLICITANTES ");
		query.append("WHERE DNI = ? ");
		params.add(bean.getDni());

		List<Gestor> gestorList = this.getJdbcTemplate().query(query.toString(), this.rwMapGestorRepresentante,
				params.toArray());
		if (gestorList != null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean getAccesoExpediente(Expediente bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT COUNT(1) ");
		query.append("FROM AA79B51S01, AA79B54S01 ");
		query.append("WHERE ANYO_051 =  ANYO_054 AND NUM_EXP_051 = NUM_EXP_054 AND ANYO_051 = ? AND NUM_EXP_051 = ? ");

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		GestorExpediente gestor = new GestorExpediente();
		gestor.setSolicitante(bean.getGestorExpediente().getSolicitante());

		if (esGestor(gestor)) {
			query.append("AND DNI_SOLICITANTE_054 = ? ");
			params.add(gestor.getSolicitante().getDni());
		}

		if ((Constants.SI).equals(gestor.getSolicitante().getCoordinador())) {
			query.append(
					"AND ID_ENTIDAD_054 = (SELECT COD_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE COORDINADOR = ? AND DNI = ? ) ");
			params.add(Constants.SI);
			params.add(gestor.getSolicitante().getDni());
		}

		if (((Constants.SI).equals(gestor.getSolicitante().getGestorExpedientes())
				|| (Constants.SI).equals(gestor.getSolicitante().getGestorExpedientesBOPV())
				|| (Constants.SI).equals(gestor.getSolicitante().getGestorExpedientesVIP()))
				&& (Constants.SI).equals(gestor.getSolicitante().getCoordinador())) {
			query.append(
					"AND (DNI_SOLICITANTE_054 = ? OR ID_ENTIDAD_054 = (SELECT COD_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE COORDINADOR = ? AND DNI = ? )) ");
			params.add(gestor.getSolicitante().getDni());
			params.add(Constants.SI);
			params.add(gestor.getSolicitante().getDni());
		}

		Long result = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
		if (result != null) {
			return true;
		}
		return false;
	}

	private boolean esGestor(GestorExpediente gestor) {
		if ((Constants.SI).equals(gestor.getSolicitante().getGestorExpedientes())
				|| (Constants.SI).equals(gestor.getSolicitante().getGestorExpedientesBOPV())
				|| (Constants.SI).equals(gestor.getSolicitante().getGestorExpedientesVIP())) {
			return true;
		}
		return false;
	}

	@Override
	public Object getReceptoresAutorizados(Expediente bean, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		StringBuilder paginatedQuery = new StringBuilder();

		query.append("SELECT ");
		if (isCount) {
			query.append(" COUNT(1) ");
		} else {
			query.append(
					"DNI_077 DNI, PREDNI_077 PREDNI, SUFDNI_077 SUFDNI, NOMBRE_077 NOMBRE, APEL1_077 APEL1, APEL2_077 APEL2, DESC_ES DESCENTIDADES, DESC_EU DESCENTIDADEU, ");
			query.append(
					"(PREDNI_077 || DNI_077 || SUFDNI_077) AS DNICOMPLETO, (APEL1_077 || ' ' || APEL2_077 || ', ' || NOMBRE_077) AS NOMBRECOMPLETO ");
		}
		query.append("FROM AA79B63S01, AA79B77S01, X54JAPI_ENTIDADES_SOLIC ");
		query.append(
				"WHERE ANYO_063 = ? AND NUM_EXP_063 = ? AND DNI_RECEPTOR_063 = DNI_077 AND ID_ENTIDAD_063 = CODIGO AND TIPO_ENTIDAD_063 = TIPO ");
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapReceptoresAutorizados,
					params.toArray());
		}
	}

	@Override
	public List<TableRowDto<ReceptorAutorizado>> reorderSelectionReceptoresAutorizados(Expediente bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder subQuery = new StringBuilder();
		subQuery.append(
				"SELECT DNI_077 DNI, PREDNI_077 PREDNI, SUFDNI_077 SUFDNI, NOMBRE_077 NOMBRE, APEL1_077 APEL1, APEL2_077 APEL2, DESC_ES DESCENTIDADES, DESC_EU DESCENTIDADEU, ");
		subQuery.append(
				"(PREDNI_077 || DNI_077 || SUFDNI_077) AS DNICOMPLETO, (APEL1_077 || ' ' || APEL2_077 || ', ' || NOMBRE_077) AS NOMBRECOMPLETO ");
		subQuery.append("FROM AA79B63S01, AA79B77S01, X54JAPI_ENTIDADES_SOLIC  ");
		subQuery.append(
				"WHERE ANYO_063 = ? AND NUM_EXP_063 = ? AND DNI_RECEPTOR_063 = DNI_077 AND ID_ENTIDAD_063 = CODIGO AND TIPO_ENTIDAD_063 = TIPO ");
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		StringBuilder query = DAOUtils.getReorderQuery(jqGridRequestDto, params, subQuery, this.getPK(), Constants.UNO,
				null);

		RowNumResultSetExtractor<ReceptorAutorizado> rowNumOrder = new RowNumResultSetExtractor<ReceptorAutorizado>(
				this.rwMapPK, jqGridRequestDto);

		return this.getJdbcTemplate().query(query.toString(), rowNumOrder, params.toArray());
	}

	@Override
	public void eliminarReceptorAutorizado(ReceptorAutorizado receptorAutorizado) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM AA79B63S01");
		query.append(" WHERE ANYO_063=? AND NUM_EXP_063=? AND DNI_RECEPTOR_063=?");
		this.getJdbcTemplate().update(query.toString(), receptorAutorizado.getAnyo(), receptorAutorizado.getNumExp(),
				receptorAutorizado.getDni());

	}

	@Override
	public Integer getReceptorAutorizadoCount(String dni) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.TABLA_63 + DaoConstants.BLANK
				+ DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI_RECEPTOR_063, dni,
				params, false));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Boolean esReceptorAutorizadoYAccesoAExpediente(String dni, Long anyo, Integer numExp) {
		StringBuilder queryRAC = new StringBuilder();
		List<Object> paramsRAC = new ArrayList<Object>();
		Boolean bResul = false;
		queryRAC.append(" SELECT COUNT(1) ");
		queryRAC.append(" FROM AA79B63S01 ");
		queryRAC.append(" WHERE ANYO_063 = ? ");
		queryRAC.append(" AND NUM_EXP_063 = ? ");
		queryRAC.append(" AND DNI_RECEPTOR_063 = ? ");
		paramsRAC.add(anyo);
		paramsRAC.add(numExp);
		paramsRAC.add(dni);
		Integer iResult = this.getJdbcTemplate().queryForObject(queryRAC.toString(), Integer.class,
				paramsRAC.toArray());
		if (iResult > Constants.CERO) {
			bResul = true;
		}
		return bResul;
	}
}
