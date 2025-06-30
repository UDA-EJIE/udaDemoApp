package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.GestorRowMapper;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;

@Repository(value = "personalIZODao")
@Transactional()
public class PersonalIZODaoImpl extends GenericoDaoImpl<PersonalIZO> implements PersonalIZODao {

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.TIPIDEN, DBConstants.PREDNI,
			DBConstants.DNI, DBConstants.SUFDNI, DBConstants.NOMBRE, DBConstants.APEL1, DBConstants.APEL2,
			DBConstants.ESTADO, DBConstants.TIPOENTIDAD, DBConstants.CODENTIDAD, DBConstants.DESCES, DBConstants.DESCEU,
			DBConstants.CIF, "TECNICOGESTOR", "ASIGNADOR", "TRADUCTOR", "INTERPRETE", DBConstants.NOMBRECOMPLETO,
			DBConstants.GESTORBOPV };

	private RowMapper<PersonalIZO> rwMap = new PersonalIZORM();

	private RowMapper<PersonalIZO> rwMapGestor = new GestorRowMapper();

	private class PersonalIZORM implements RowMapper<PersonalIZO> {
		@Override()
		public PersonalIZO mapRow(ResultSet rs, int rowNum) throws SQLException {
			PersonalIZO bean = new PersonalIZO();
			bean.setTipIden(rs.getInt(DBConstants.TIPIDEN));
			bean.setPreDni(rs.getString(DBConstants.PREDNI));
			bean.setDni(rs.getString(DBConstants.DNI));
			bean.setSufDni(rs.getString(DBConstants.SUFDNI));
			bean.setNombre(rs.getString(DBConstants.NOMBRE));
			bean.setApellido1(rs.getString(DBConstants.APEL1));
			bean.setApellido2(rs.getString(DBConstants.APEL2));
			bean.setEstado(rs.getString(DBConstants.ESTADO));

			Entidad entidad = bean.getEntidad();
			entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
			entidad.setCodigo(rs.getInt(DBConstants.CODENTIDAD));
			entidad.setDescEs(rs.getString(DBConstants.DESCES));
			entidad.setDescEu(rs.getString(DBConstants.DESCEU));
			entidad.setCif(rs.getString(DBConstants.CIF));

			bean.setTecnicoGestor(rs.getString("TECNICOGESTOR"));
			bean.setAsignador(rs.getString("ASIGNADOR"));
			bean.setTraductor(rs.getString("TRADUCTOR"));
			bean.setInterprete(rs.getString("INTERPRETE"));
			bean.setGestorBopv(rs.getString(DBConstants.GESTORBOPV));
			return bean;
		}
	}

	private RowMapper<PersonalIZO> rwMapPK = new RowMapper<PersonalIZO>() {
		@Override()
		public PersonalIZO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			PersonalIZO bean = new PersonalIZO();
			bean.setDni(resultSet.getString(DBConstants.DNI));
			return bean;
		}
	};

	private RowMapper<PersonalIZO> rwMapFindAsignadores = new RowMapper<PersonalIZO>() {
		@Override()
		public PersonalIZO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			PersonalIZO bean = new PersonalIZO();
			bean.setDni(resultSet.getString(DBConstants.DNI));
			bean.setNombre(resultSet.getString(DBConstants.NOMBRE));
			bean.setApellido1(resultSet.getString(DBConstants.APEL1));
			bean.setApellido2(resultSet.getString(DBConstants.APEL2));
			return bean;
		}
	};

	private RowMapper<PersonalIZO> rwMapTecnicoAsignado = new RowMapper<PersonalIZO>() {
		@Override
		public PersonalIZO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			PersonalIZO personalIzo = new PersonalIZO();
			personalIzo.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
			personalIzo.setPreDni(resultSet.getString(DBConstants.PREDNI));
			personalIzo.setDni(resultSet.getString(DBConstants.DNI));
			personalIzo.setSufDni(resultSet.getString(DBConstants.SUFDNI));
			personalIzo.setNombre(resultSet.getString(DBConstants.NOMBRE));
			personalIzo.setApellido1(resultSet.getString(DBConstants.APEL1));
			personalIzo.setApellido2(resultSet.getString(DBConstants.APEL2));

			return personalIzo;
		}
	};

	/**
	 * Constructor
	 */
	public PersonalIZODaoImpl() {
		super(PersonalIZO.class);
	}

	@Override()
	protected String getSelect() {
		StringBuilder selectPer = new StringBuilder();
		selectPer.append("SELECT t1.TIPIDEN TIPIDEN");
		selectPer.append(", t1.PREDNI PREDNI");
		selectPer.append(", t1.DNI DNI");
		selectPer.append(", t1.SUFDNI SUFDNI");
		selectPer.append(", TRIM(t1.NOMBRE) NOMBRE");
		selectPer.append(", TRIM(t1.APEL1) APEL1");
		selectPer.append(", TRIM(t1.APEL2) APEL2");
		selectPer.append(", t1.ESTADO ESTADO");
		selectPer.append(", t1.TIPO_ENTIDAD TIPOENTIDAD");
		selectPer.append(", t1.COD_ENTIDAD CODENTIDAD");
		selectPer.append(", t1.DESC_ES DESCES");
		selectPer.append(", t1.DESC_EU DESCEU");
		selectPer.append(", t1.CIF CIF");
		selectPer.append(", t1.TECNICO_GESTOR TECNICOGESTOR");
		selectPer.append(", t1.ASIGNADOR ASIGNADOR");
		selectPer.append(", t1.TRADUCTOR TRADUCTOR");
		selectPer.append(", t1.INTERPRETE INTERPRETE");
		selectPer.append(", t1.APEL1 || ' ' || t1.APEL2 || ', ' || t1.NOMBRE AS NOMBRECOMPLETO");
		selectPer.append(", t2.GESTOR_EXPEDIENTES_BOPV GESTORBOPV ");
		return selectPer.toString();
	}

	@Override()
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM X54JAPI_PERSONAL_IZO t1");
		from.append(" LEFT JOIN X54JAPI_SOLICITANTES t2");
		from.append(" ON t2.DNI = t1.DNI ");
		return from.toString();
	}

	@Override()
	protected RowMapper<PersonalIZO> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return PersonalIZODaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return DBConstants.DNI;
	}

	@Override()
	protected RowMapper<PersonalIZO> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(PersonalIZO bean, List<Object> params) {
		params.add(bean.getDni());
		return " WHERE t1.DNI = ?";
	}

	@Override()
	protected String getWhere(PersonalIZO bean, List<Object> params) {
		StringBuilder wherePer = new StringBuilder();
		wherePer.append(SqlUtils.generarWhereIgual("t1.TIPIDEN", bean.getTipIden(), params));
		wherePer.append(SqlUtils.generarWhereIgual("t1.PREDNI", bean.getPreDni(), params));
		wherePer.append(SqlUtils.generarWhereIgualDni("t1.DNI", bean.getDni(), params));
		wherePer.append(SqlUtils.generarWhereIgual("t1.SUFDNI", bean.getSufDni(), params));
		wherePer.append(SqlUtils.generarWhereIgual("t1.NOMBRE", bean.getNombre(), params));
		wherePer.append(SqlUtils.generarWhereIgual("t1.APEL1", bean.getApellido1(), params));
		wherePer.append(SqlUtils.generarWhereIgual("t1.APEL2", bean.getApellido2(), params));
		wherePer.append(SqlUtils.generarWhereIgual("t1.ESTADO", bean.getEstado(), params));

		Entidad entidad = bean.getEntidad();
		if (entidad != null) {
			wherePer.append(SqlUtils.generarWhereIgual("t1.TIPO_ENTIDAD", entidad.getTipo(), params));
			wherePer.append(SqlUtils.generarWhereIgual("t1.COD_ENTIDAD", entidad.getCodigo(), params));
			wherePer.append(SqlUtils.generarWhereIgual("t1.DESC_ES", entidad.getDescEs(), params));
			wherePer.append(SqlUtils.generarWhereIgual("t1.DESC_EU", entidad.getDescEu(), params));
			wherePer.append(SqlUtils.generarWhereIgual("t1.CIF", entidad.getCif(), params));
		}

		List<String> permisos = new ArrayList<String>();
		SqlUtils.comprobarPermisos(bean.getTecnicoGestor(), "TECNICO_GESTOR", permisos);
		SqlUtils.comprobarPermisos(bean.getAsignador(), "ASIGNADOR", permisos);
		SqlUtils.comprobarPermisos(bean.getTraductor(), "TRADUCTOR", permisos);
		SqlUtils.comprobarPermisos(bean.getInterprete(), "INTERPRETE", permisos);
		SqlUtils.comprobarPermisos(bean.getGestorBopv(), "GESTORBOPV", permisos);
		wherePer.append(SqlUtils.generarWherePermisos(permisos, params));

		return wherePer.toString();
	}

	@Override()
	protected String getWhereLike(PersonalIZO bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLikePer = new StringBuilder();
		whereLikePer.append(SqlUtils.generarWhereIgual("t1.TIPIDEN", bean.getTipIden(), params));
		whereLikePer.append(SqlUtils.generarWhereIgual("t1.PREDNI", bean.getPreDni(), params));
		whereLikePer.append(SqlUtils.generarWhereIgualDni("t1.DNI", bean.getDni(), params));
		whereLikePer.append(SqlUtils.generarWhereIgual("t1.SUFDNI", bean.getSufDni(), params));
		whereLikePer.append(SqlUtils.generarWhereLikeNormalizado("t1.NOMBRE", bean.getNombre(), params, startsWith));
		whereLikePer.append(SqlUtils.generarWhereLikeNormalizado("t1.APEL1", bean.getApellido1(), params, startsWith));
		whereLikePer.append(SqlUtils.generarWhereLikeNormalizado("t1.APEL2", bean.getApellido2(), params, startsWith));
		whereLikePer.append(SqlUtils.generarWhereIgual("t1.ESTADO", bean.getEstado(), params));

		Entidad entidad = bean.getEntidad();
		if (entidad != null) {
			whereLikePer.append(SqlUtils.generarWhereIgual("t1.TIPO_ENTIDAD", entidad.getTipo(), params));
			whereLikePer.append(SqlUtils.generarWhereIgual("t1.COD_ENTIDAD", entidad.getCodigo(), params));
			whereLikePer.append(
					SqlUtils.generarWhereLikeNormalizado("t1.DESC_ES", entidad.getDescEs(), params, startsWith));
			whereLikePer.append(
					SqlUtils.generarWhereLikeNormalizado("t1.DESC_EU", entidad.getDescEu(), params, startsWith));
			whereLikePer.append(SqlUtils.generarWhereIgual("t1.CIF", entidad.getCif(), params));
		}
		if (bean.getNombreFiltro() != null) {
			whereLikePer.append(SqlUtils.generarWhereLikeNormalizado(
					"(t1.NOMBRE || ' ' || t1.APEL1 || ' ' || t1.APEL2)", bean.getNombreFiltro(), params, startsWith));
		}
		List<String> permisos = new ArrayList<String>();
		SqlUtils.comprobarPermisos(bean.getTecnicoGestor(), "TECNICO_GESTOR", permisos);
		SqlUtils.comprobarPermisos(bean.getAsignador(), "ASIGNADOR", permisos);
		SqlUtils.comprobarPermisos(bean.getTraductor(), "TRADUCTOR", permisos);
		SqlUtils.comprobarPermisos(bean.getInterprete(), "INTERPRETE", permisos);
		SqlUtils.comprobarPermisos(bean.getGestorBopv(), "GESTORBOPV", permisos);
		whereLikePer.append(SqlUtils.generarWherePermisos(permisos, params));

		return whereLikePer.toString();
	}

	@Override()
	public void guardarFoto(PersonalIZO bean) {
		if (bean != null && StringUtils.isNotBlank(bean.getDni())) {
			StringBuilder update = new StringBuilder();
			update.append("UPDATE AA79B77S01");
			update.append(
					" SET (TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077) = (SELECT TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2 FROM X54JAPI_PERSONAL_IZO WHERE DNI = DNI_077)");
			update.append(" WHERE DNI_077 = ?");
			Integer rdo = this.getJdbcTemplate().update(update.toString(), bean.getDni());
			if (rdo == 0) {
				StringBuilder insert = new StringBuilder();
				insert.append(
						"INSERT INTO AA79B77S01 (DNI_077, TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077)");
				insert.append(
						" SELECT DNI, TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2 FROM X54JAPI_PERSONAL_IZO WHERE DNI = ?");
				this.getJdbcTemplate().update(insert.toString(), bean.getDni());
			}
		}
	}

	@Override()
	public List<PersonalIZO> findAsignadoresSinGrupo(PersonalIZO bean) {
		bean.setAsignador(Constants.SI);
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom(bean, params));
		query.append(" LEFT JOIN AA79B26S01 t3 ON t1.DNI = t3.DNI_RESPONSABLE_026");

		query.append(" WHERE t3.DNI_RESPONSABLE_026 IS NULL");
		query.append(this.getWhereLike(bean, false, params, false));

		query.append(" ORDER BY NOMBRECOMPLETO");

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override()
	public List<PersonalIZO> findTraductoresSinGrupo(PersonalIZO bean) {
		bean.setTraductor(Constants.SI);
		bean.setInterprete(Constants.SI);
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom(bean, params));
		query.append(" LEFT JOIN AA79B28S01 t3 ON t1.DNI = t3.DNI_TRABAJADOR_028");

		query.append(" WHERE t3.DNI_TRABAJADOR_028 IS NULL");
		query.append(this.getWhereLike(bean, false, params, false));

		query.append(" ORDER BY NOMBRECOMPLETO");

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override
	public List<PersonalIZO> filterTecnicoAsignadoAEstudio(PersonalIZO filterPersonalIZO) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("SELECT DISTINCT t1.DNI_077 DNI, t1.TIPIDEN_077 TIPIDEN, t1.PREDNI_077 PREDNI");
		query.append(", t1.SUFDNI_077 SUFDNI, t1.NOMBRE_077 NOMBRE, t1.APEL1_077 APEL1, t1.APEL2_077 APEL2 ");
		query.append("FROM AA79B77S01 t1 ");
		query.append("LEFT JOIN AA79B51S01 e1 ON t1.DNI_077 = e1.DNI_TECNICO_051");
		query.append(" LEFT JOIN AA79B59S01 b1 ON e1.ANYO_051 = b1.ANYO_059 AND e1.NUM_EXP_051 = b1.NUM_EXP_059");
		query.append(
				" WHERE b1.ID_ESTADO_EXP_059 = 2 AND (b1.ID_FASE_EXPEDIENTE_059 = 3 OR b1.ID_FASE_EXPEDIENTE_059 = 4)");
		if (filterPersonalIZO.getNombreFiltro() != null) {
			query.append(SqlUtils.generarWhereLikeNormalizado(
					"(t1.NOMBRE_077 || ' ' || t1.APEL1_077 || ' ' || t1.APEL2_077)",
					filterPersonalIZO.getNombreFiltro(), params, false));
		}
		return this.getJdbcTemplate().query(query.toString(), this.rwMapTecnicoAsignado, params.toArray());
	}

	@Override()
	public List<PersonalIZO> findAsignadores(PersonalIZO bean, String alta) {
		bean.setAsignador(Constants.SI);
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		if (alta.equals(Constants.ALTA)) {
			query.append("SELECT DNI, NOMBRE, APEL1, APEL2 ");
			query.append("FROM X54JAPI_PERSONAL_IZO WHERE ASIGNADOR = 'S' ");
		} else if (alta.equals(Constants.DETALLE)) {
			query.append("SELECT DNI, NOMBRE, APEL1, APEL2 ");
			query.append("FROM X54JAPI_PERSONAL_IZO WHERE ASIGNADOR = 'S' ");
			query.append("UNION ALL ");
			query.append("SELECT DNI_077 DNI ");
			query.append(", NOMBRE_077 NOMBRE ");
			query.append(", APEL1_077 APEL1 ");
			query.append(", APEL2_077 APEL2 ");
			query.append("FROM AA79B77S01 ");
			query.append("JOIN (SELECT DNI_RESPONSABLE_026 ");
			query.append("FROM AA79B26S01 ");
			query.append("LEFT JOIN X54JAPI_PERSONAL_IZO ON DNI_RESPONSABLE_026 = DNI AND ASIGNADOR = 'S' ");
			query.append("WHERE DNI IS NULL ");
			query.append("GROUP BY DNI_RESPONSABLE_026) ");
			query.append("ON DNI_RESPONSABLE_026 = DNI_077 ");
			query.append("WHERE 1=1 ");
			query.append("ORDER BY APEL1, APEL2, NOMBRE, DNI ");
		} else if (alta.equals(Constants.MAINT)) {
			query.append("SELECT DNI_077 DNI, NOMBRE_077 NOMBRE, APEL1_077 APEL1, APEL2_077 APEL2 ");
			query.append("FROM AA79B77S01 ");
			query.append("JOIN (");
			query.append("SELECT DNI_RESPONSABLE_026 ");
			query.append("FROM AA79B26S01 ");
			query.append("GROUP BY DNI_RESPONSABLE_026) ");
			query.append("ON DNI_RESPONSABLE_026 = DNI_077 ");
			query.append("WHERE 1=1 ");
			query.append("ORDER BY APEL1, APEL2, NOMBRE, DNI ");
		}
		return this.getJdbcTemplate().query(query.toString(), this.rwMapFindAsignadores, params.toArray());
	}

	@Override()
	public List<PersonalIZO> findTraductores(PersonalIZO bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append("SELECT DNI_077 DNI , ");
		query.append("NOMBRE_077 NOMBRE , ");
		query.append("APEL1_077 APEL1 , ");
		query.append("APEL2_077 APEL2 ");
		query.append("FROM AA79B77S01 ");
		query.append("JOIN ");
		query.append("(SELECT DNI_TRABAJADOR_028 ");
		query.append("FROM AA79B28S01 ");
		query.append("GROUP BY DNI_TRABAJADOR_028 ");
		query.append(") ON DNI_TRABAJADOR_028 = DNI_077 ");
		query.append("WHERE 1=1 ");
		query.append("ORDER BY APEL1, ");
		query.append("APEL2, ");
		query.append("NOMBRE, ");
		query.append("DNI ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapFindAsignadores, params.toArray());
	}

	@Override
	public List<PersonalIZO> getAsignadores(List<Long> gruposTrabajo) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT ");
		query.append("DNI_077 DNI,  ");
		query.append("NOMBRE_077 NOMBRE, APEL1_077 APEL1, APEL2_077 APEL2 ");
		query.append("FROM AA79B81S01 JOIN AA79B77S01 ");
		query.append("ON DNI_RECURSO_081 = DNI_077 JOIN AA79B51S01 ");
		query.append("ON ANYO_081     = ANYO_051 ");
		query.append("AND NUM_EXP_081 = NUM_EXP_051 ");
		query.append("AND RECURSO_ASIGNACION_081 = '").append(TipoRecursoEnum.INTERNO.getValue()).append("' ");
		query.append("JOIN AA79B59S01 ");
		query.append("ON ANYO_051 = ANYO_059 ");
		query.append("AND NUM_EXP_051 = NUM_EXP_059 ");
		query.append("AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059 ");
		Object[] listaEstados = { EstadoExpedienteEnum.EN_CURSO.getValue(), EstadoExpedienteEnum.CERRADO.getValue() };
		query.append(SqlUtils.generarWhereIn("ID_ESTADO_EXP_059", listaEstados, params));
		if (gruposTrabajo != null && !gruposTrabajo.isEmpty()) {
			query.append(" JOIN AA79B28S01 ON DNI_TRABAJADOR_028 = DNI_RECURSO_081 AND (");
			for (Long id : gruposTrabajo) {
				params.add(id);
				query.append(" ID_GRUPO_028 = ? OR");
			}
			StringBuilder queryWithGruposTrabajo = new StringBuilder(query.toString());
			queryWithGruposTrabajo.replace(query.toString().lastIndexOf("OR"),
					query.toString().lastIndexOf("OR") + Constants.DOS, ")");
			return this.getJdbcTemplate().query(queryWithGruposTrabajo.toString(), this.rwMapFindAsignadores,
					params.toArray());
		}
		return this.getJdbcTemplate().query(query.toString(), this.rwMapFindAsignadores, params.toArray());
	}

	@Override
	public List<PersonalIZO> findTradInterpAsign(PersonalIZO filterPersonalIZO) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		Object[] arrayEstados = { EstadoExpedienteEnum.EN_CURSO.getValue() };
		this.getQueryAsignadorDeExpedientesEstado(filterPersonalIZO, params, query, arrayEstados);
		params.add(filterPersonalIZO.getAsignador());

		return this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());
	}

	public void getQueryAsignadorDeExpedientesEstado(PersonalIZO filterPersonalIZO, List<Object> params,
			StringBuilder query, Object[] arrayEstados) {
		query.replace(query.indexOf(DaoConstants.SELECT.trim()), Constants.SEIS,
				DaoConstants.SELECT + DaoConstants.DISTINCT);
		query.append(this.getFrom(filterPersonalIZO, params));
		query.append(DaoConstants.JOIN + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.ON + " t1.DNI " + DaoConstants.SIGNOIGUAL + " t3.DNI_ASIGNADOR_051 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		query.append(DaoConstants.ON + " t3.ANYO_051 " + DaoConstants.SIGNOIGUAL + " t4.ANYO_059 ");
		query.append(DaoConstants.AND + " t3.NUM_EXP_051 " + DaoConstants.SIGNOIGUAL + " t4.NUM_EXP_059 ");
		query.append(DaoConstants.AND + " t3.ESTADO_BITACORA_051 " + DaoConstants.SIGNOIGUAL
				+ " t4.ID_ESTADO_BITACORA_059 ");
		query.append(SqlUtils.generarWhereIn(" t4.ID_ESTADO_EXP_059 ", arrayEstados, params));
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(DaoConstants.AND + " t1.ASIGNADOR " + DaoConstants.SIGNOIGUAL_INTERROGACION);
	}

	@Override
	public List<PersonalIZO> getGestoresActivos(Solicitante gestorFilter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(PersonalIZODaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.SELECT + DaoConstants.DISTINCT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPIDEN
				+ DaoConstants.BLANK + DBConstants.TIPIDEN);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.PREDNI + DaoConstants.BLANK
				+ DBConstants.PREDNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI + DaoConstants.BLANK
				+ DBConstants.DNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.SUFDNI + DaoConstants.BLANK
				+ DBConstants.SUFDNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NOMBRE + DaoConstants.BLANK
				+ DBConstants.NOMBRE);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.APEL1 + DaoConstants.BLANK
				+ DBConstants.APEL1);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.APEL2 + DaoConstants.BLANK
				+ DBConstants.APEL2);
		query.append(" FROM X54JAPI_SOLICITANTES t1 ");
		query.append(" LEFT JOIN X54JAPI_ENTIDADES_SOLIC e1 ");
		query.append(" ON t1.COD_ENTIDAD = e1.CODIGO ");
		query.append(" AND t1.TIPO_ENTIDAD = e1.TIPO ");
		query.append(" AND e1.ESTADO = 'A' ");
		query.append(" WHERE 1 = 1 ");
		query.append(SqlUtils.generarWhereLike("t1.ESTADO", "V", params, false));
		query.append(" AND ( ( 1           =1 ");
		query.append(SqlUtils.generarWhereLike("t1.GESTOR_EXPEDIENTES", ActivoEnum.SI.getValue(), params, false));
		query.append(" ) ");
		query.append(" OR ( 1                     =1 ");
		query.append(SqlUtils.generarWhereLike("t1.GESTOR_EXPEDIENTES_VIP", ActivoEnum.SI.getValue(), params, false));
		query.append(" ) ");
		query.append(" OR ( 1                         =1 ");
		query.append(SqlUtils.generarWhereLike("t1.GESTOR_EXPEDIENTES_BOPV", ActivoEnum.SI.getValue(), params, false));
		query.append(" ) ) ");
		// generar where con filtros de entidad
		query.append(SqlUtils.generarWhereIgual("e1.TIPO", gestorFilter.getEntidad().getTipo(), params));
		query.append(SqlUtils.generarWhereIgual("e1.CODIGO", gestorFilter.getEntidad().getCodigo(), params));

		if (gestorFilter.getNombreFiltro() != null) {
			query.append(SqlUtils.generarWhereLikeNormalizado("(t1.NOMBRE || ' ' || t1.APEL1 || ' ' || t1.APEL2)",
					gestorFilter.getNombreFiltro(), params, false));
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapTecnicoAsignado, params.toArray());
	}

	@Override
	public PersonalIZO getDatosGestor(PersonalIZO personalIZO) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.GESTOR_EXPEDIENTES_VIP
				+ DaoConstants.BLANK + DBConstants.GESTOR_EXPEDIENTES_VIP);
		query.append(this.getFrom(personalIZO, params));
		query.append(this.getWherePK(personalIZO, params));

		List<PersonalIZO> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapGestor, params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public List<PersonalIZO> getAsignadoresPlanificacion(PersonalIZO filterPersonalIZO) {
		List<Object> paramsAP = new ArrayList<Object>();
		StringBuilder queryAP = new StringBuilder(this.getSelect());
		Object[] arrayEstados = { EstadoExpedienteEnum.EN_CURSO.getValue(), EstadoExpedienteEnum.CERRADO.getValue() };
		this.getQueryAsignadorDeExpedientesEstado(filterPersonalIZO, paramsAP, queryAP, arrayEstados);
		paramsAP.add(filterPersonalIZO.getAsignador());

		return this.getJdbcTemplate().query(queryAP.toString(), this.getRwMap(), paramsAP.toArray());
	}

}
