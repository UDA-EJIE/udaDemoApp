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
import com.ejie.aa79b.model.CentroOrganico;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository()
@Transactional()
public class SolicitanteDaoImpl extends GenericoDaoImpl<Solicitante> implements SolicitanteDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String SELECT77 = "SELECT DISTINCT t1.DNI_077 DNI, t1.TIPIDEN_077 TIPIDEN, t1.PREDNI_077 PREDNI, t1.SUFDNI_077 SUFDNI, TRIM(t1.NOMBRE_077) NOMBRE, TRIM(t1.APEL1_077) APEL1, TRIM(t1.APEL2_077) APEL2 FROM AA79B77S01 t1";
	protected static final String JOIN_51_59 = " JOIN AA79B59S01 b1 ON s1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 AND b1.ID_ESTADO_EXP_059 = ";
	protected static final String JOIN_51 = " JOIN AA79B51S01 s1";
	protected static final String IGUALDAD_ANYO_NUMEXP_51_59 = " AND b1.ANYO_059 = s1.ANYO_051 AND b1.NUM_EXP_059 = s1.NUM_EXP_051";
	protected static final String IGUALDAD_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_58 = DaoConstants.AND
			+ DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.CODIGO + DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA
			+ DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_ASOC_058;
	protected static final String JOIN_VISTAX54JAPIENTIDADESSOLIC_A1 = DaoConstants.JOIN
			+ DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK + DaoConstants.A1_MINUSCULA;
	protected static final String ON_TIPO_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_58 = DaoConstants.ON
			+ DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.TIPO + DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA
			+ DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_ASOC_058;
	protected static final String JOIN_54_E1 = " JOIN AA79B54S01 e1";
	protected static final String ON_ANYO_NUMEXP_54_51 = " ON e1.ANYO_054 = s1.ANYO_051 AND e1.NUM_EXP_054 = s1.NUM_EXP_051";
	protected static final String ON_DNI_77_54 = " ON t1.DNI_077 = e1.DNI_SOLICITANTE_054";
	protected static final String NOMBRE_APELLIDOS_77 = "(t1.NOMBRE_077 || ' ' || t1.APEL1_077 || ' ' || t1.APEL2_077)";
	protected static final String ON_TIPO_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_54 = DaoConstants.ON
			+ DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.TIPO + DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA
			+ DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054;
	protected static final String IGUALDAD_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_54 = DaoConstants.AND
			+ DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.CODIGO + DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA
			+ DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054;

	public SolicitanteDaoImpl() {
		super(Solicitante.class);
	}

	public static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.TIPIDEN, DBConstants.PREDNI,
			DBConstants.DNI, DBConstants.SUFDNI, DBConstants.NOMBRE, DBConstants.APEL1, DBConstants.APEL2,
			DBConstants.ESTADO, DBConstants.TIPOENTIDAD, DBConstants.CODENTIDAD, DBConstants.DESCES, DBConstants.DESCEU,
			DBConstants.CIF, DBConstants.GESTOREXPEDIENTES, DBConstants.GESTORFACTURAS, DBConstants.COORDINADOR,
			DBConstants.GESTOREXPEDIENTESVIP, "GESTOREXPEDIENTESVIPDESCES", "GESTOREXPEDIENTESVIPDESCEU",
			DBConstants.GESTOREXPEDIENTESBOPV, "APELLIDOS", DBConstants.CENTRO_ORGANICO_ES, DBConstants.CENTRO_ORGANICO_EU };
	private RowMapper<Solicitante> rwMap = new SolicitanteRM();

	private class SolicitanteRM implements RowMapper<Solicitante> {
		@Override()
		public Solicitante mapRow(ResultSet rs, int rowNum) throws SQLException {
			Solicitante solicitante = new Solicitante();
			solicitante.setTipIden(rs.getInt(DBConstants.TIPIDEN));
			solicitante.setPreDni(rs.getString(DBConstants.PREDNI));
			solicitante.setDni(rs.getString(DBConstants.DNI));
			solicitante.setSufDni(rs.getString(DBConstants.SUFDNI));
			solicitante.setNombre(rs.getString(DBConstants.NOMBRE));
			solicitante.setApellido1(rs.getString(DBConstants.APEL1));
			solicitante.setApellido2(rs.getString(DBConstants.APEL2));
			solicitante.setEstado(rs.getString(DBConstants.ESTADO));

			Entidad entidad = solicitante.getEntidad();
			entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
			entidad.setCodigo(rs.getInt(DBConstants.CODENTIDAD));
			entidad.setDescEs(rs.getString(DBConstants.DESCES));
			entidad.setDescEu(rs.getString(DBConstants.DESCEU));
			entidad.setCif(rs.getString(DBConstants.CIF));

			solicitante.setGestorExpedientes(rs.getString(DBConstants.GESTOREXPEDIENTES));
			solicitante.setGestorFacturas(rs.getString(DBConstants.GESTORFACTURAS));
			solicitante.setCoordinador(rs.getString(DBConstants.COORDINADOR));
			solicitante.setGestorExpedientesVIP(rs.getString(DBConstants.GESTOREXPEDIENTESVIP));
			solicitante.setGestorExpedientesVIPDescEs(rs.getString("GESTOREXPEDIENTESVIPDESCES"));
			solicitante.setGestorExpedientesVIPDescEu(rs.getString("GESTOREXPEDIENTESVIPDESCEU"));
			solicitante.setGestorExpedientesBOPV(rs.getString(DBConstants.GESTOREXPEDIENTESBOPV));

			return solicitante;
		}
	}

	private RowMapper<Solicitante> rwMapPermisosBOPV = new SolicitanteBOPV();

	private class SolicitanteBOPV implements RowMapper<Solicitante> {
		@Override()
		public Solicitante mapRow(ResultSet rs, int rowNum) throws SQLException {
			Solicitante solicitante = new Solicitante();
			solicitante.setGestorExpedientesBOPV(rs.getString(DBConstants.GESTOREXPEDIENTESBOPV));
			return solicitante;
		}
	}

	private RowMapper<Solicitante> rwMapSolicitanteGestor = new RowMapper<Solicitante>() {
		@Override()
		public Solicitante mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Solicitante solicitante = new Solicitante();
			solicitante.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
			solicitante.setPreDni(resultSet.getString(DBConstants.PREDNI));
			solicitante.setDni(resultSet.getString(DBConstants.DNI));
			solicitante.setSufDni(resultSet.getString(DBConstants.SUFDNI));
			solicitante.setNombre(resultSet.getString(DBConstants.NOMBRE));
			solicitante.setApellido1(resultSet.getString(DBConstants.APEL1));
			solicitante.setApellido2(resultSet.getString(DBConstants.APEL2));

			return solicitante;
		}
	};

	private RowMapper<Solicitante> rwMapPK = new RowMapper<Solicitante>() {
		@Override()
		public Solicitante mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Solicitante bean = new Solicitante();
			bean.setDni(resultSet.getString(DBConstants.DNI));
			return bean;
		}
	};

	private RowMapper<Gestor> rwMapGestorConsulta = new RowMapper<Gestor>() {
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
			gestor.setSolicitante(solicitante);

			return gestor;
		}
	};

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

	private RowMapper<Solicitante> rwMapGestorCoordinador = new RowMapper<Solicitante>() {
		@Override()
		public Solicitante mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
			solicitante.setCoordinador(resultSet.getString(DBConstants.COORDINADOR));

			return solicitante;
		}
	};

	private RowMapper<Solicitante> rwMapSolicitanteDireccion = new SolicitanteDireccion();

	private class SolicitanteDireccion implements RowMapper<Solicitante> {
		@Override()
		public Solicitante mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
			solicitante.setCoordinador(resultSet.getString(DBConstants.COORDINADOR));

			Entidad entidad = solicitante.getEntidad();

			Direccion direccion = entidad.getDireccion();
			direccion.setDireccion(resultSet.getString(DBConstants.DIRECCION));
			direccion.setTxtMunicipio(resultSet.getString(DBConstants.MUNICIPIO));
			direccion.setTxtLocalidad(resultSet.getString(DBConstants.LOCALIDAD));
			direccion.setTxtProvincia(resultSet.getString(DBConstants.PROVINCIA));
			direccion.setCodPostal(resultSet.getString(DBConstants.CODPOSTAL));

			entidad.setDireccion(direccion);
			solicitante.setEntidad(entidad);
			return solicitante;
		}
	}

	private RowMapper<PersonalIZO> rwMapGestor = new RowMapper<PersonalIZO>() {
		@Override
		public PersonalIZO mapRow(ResultSet resultSet, int arg1) throws SQLException {
			PersonalIZO bean = new PersonalIZO();
			bean.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
			Entidad entidad = new Entidad();
			entidad.setTipo(resultSet.getString("TIPOENTIDAD"));
			entidad.setCodigo(resultSet.getInt("IDENTIDAD"));
			bean.setEntidad(entidad);
			bean.setGestorExpedientesVIP(resultSet.getString("INDVIP"));
			bean.setPreDni(resultSet.getString(DBConstants.PREDNI));
			bean.setDni(resultSet.getString(DBConstants.DNI));
			bean.setSufDni(resultSet.getString(DBConstants.SUFDNI));
			bean.setNombre(resultSet.getString(DBConstants.NOMBRE));
			bean.setApellido1(resultSet.getString(DBConstants.APEL1));
			bean.setApellido2(resultSet.getString(DBConstants.APEL2));
			bean.setNombreCompleto(resultSet.getString("NOMBRECOMPLETO"));
			return bean;
		}
	};

	private RowMapper<Solicitante> rwMapEntidadCentroOrganico = new RowMapper<Solicitante>() {
		@Override()
		public Solicitante mapRow(ResultSet rs, int rowNum) throws SQLException {
			Locale locale = LocaleContextHolder.getLocale();

			Solicitante solicitante = new Solicitante();
			solicitante.setTipIden(rs.getInt(DBConstants.TIPIDEN));
			solicitante.setPreDni(rs.getString(DBConstants.PREDNI));
			solicitante.setDni(rs.getString(DBConstants.DNI));
			solicitante.setSufDni(rs.getString(DBConstants.SUFDNI));
			solicitante.setNombre(rs.getString(DBConstants.NOMBRE));
			solicitante.setApellido1(rs.getString(DBConstants.APEL1));
			solicitante.setApellido2(rs.getString(DBConstants.APEL2));
			solicitante.setEstado(rs.getString(DBConstants.ESTADO));

			Entidad entidad = solicitante.getEntidad();
			entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
			entidad.setCodigo(rs.getInt(DBConstants.CODENTIDAD));
			entidad.setDescEs(rs.getString(DBConstants.DESCES));
			entidad.setDescEu(rs.getString(DBConstants.DESCEU));
			entidad.setCif(rs.getString(DBConstants.CIF));

			solicitante.setGestorExpedientes(rs.getString(DBConstants.GESTOREXPEDIENTES));
			solicitante.setGestorFacturas(rs.getString(DBConstants.GESTORFACTURAS));
			solicitante.setCoordinador(rs.getString(DBConstants.COORDINADOR));
			solicitante.setGestorExpedientesVIP(rs.getString(DBConstants.GESTOREXPEDIENTESVIP));
			solicitante.setGestorExpedientesVIPDescEs(rs.getString("GESTOREXPEDIENTESVIPDESCES"));
			solicitante.setGestorExpedientesVIPDescEu(rs.getString("GESTOREXPEDIENTESVIPDESCEU"));
			solicitante.setGestorExpedientesBOPV(rs.getString(DBConstants.GESTOREXPEDIENTESBOPV));

			CentroOrganico centroOrganico = new CentroOrganico();
			if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
				centroOrganico.setDescAmp(rs.getString(DBConstants.CENTRO_ORGANICO_EU));
			} else {
				centroOrganico.setDescAmp(rs.getString(DBConstants.CENTRO_ORGANICO_ES));
			}
			solicitante.setCentroOrganico(centroOrganico);

			return solicitante;
		}
	};

	@Override()
	protected String getSelect() {
		return getSelect(false);
	}

	private String getSelect(boolean isDistinct) {

		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder selectSolic = new StringBuilder();
		selectSolic.append(DaoConstants.SELECT);
		if (isDistinct) {
			selectSolic.append(DaoConstants.DISTINCT);
		}
		selectSolic.append("TIPIDEN");
		selectSolic.append(", PREDNI");
		selectSolic.append(", DNI");
		selectSolic.append(", SUFDNI");
		selectSolic.append(", TRIM(NOMBRE) NOMBRE");
		selectSolic.append(", TRIM(APEL1) APEL1");
		selectSolic.append(", TRIM(APEL2) APEL2");
		selectSolic.append(", APEL1 || ' ' || APEL2  AS APELLIDOS");
		selectSolic.append(", ESTADO");
		selectSolic.append(", TIPO_ENTIDAD AS TIPOENTIDAD");
		selectSolic.append(", COD_ENTIDAD AS CODENTIDAD");
		selectSolic.append(", DESC_ES AS DESCES");
		selectSolic.append(", DESC_EU AS DESCEU");
		selectSolic.append(", CIF");
		selectSolic.append(", GESTOR_EXPEDIENTES AS GESTOREXPEDIENTES");
		selectSolic.append(", GESTOR_FACTURAS AS GESTORFACTURAS");
		selectSolic.append(", COORDINADOR");
		selectSolic.append(", GESTOR_EXPEDIENTES_VIP AS GESTOREXPEDIENTESVIP");
		selectSolic.append(", DECODE(GESTOR_EXPEDIENTES_VIP, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es))
				.append("') AS GESTOREXPEDIENTESVIPDESCES");
		selectSolic.append(", DECODE(GESTOR_EXPEDIENTES_VIP, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu))
				.append("') AS GESTOREXPEDIENTESVIPDESCEU");
		selectSolic.append(", GESTOR_EXPEDIENTES_BOPV AS GESTOREXPEDIENTESBOPV");
		return selectSolic.toString();
	}

	@Override()
	protected String getFrom() {
		return " FROM X54JAPI_SOLICITANTES";
	}

	@Override()
	protected RowMapper<Solicitante> getRwMap() {
		return this.rwMap;
	}

	protected RowMapper<Gestor> getRwMapGestorRepresentante() {
		return this.rwMapGestorRepresentante;
	}

	@Override()
	protected String[] getOrderBy() {
		return SolicitanteDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return DBConstants.DNI;
	}

	@Override()
	protected RowMapper<Solicitante> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(Solicitante bean, List<Object> params) {
		params.add(bean.getDni());
		return " WHERE DNI = ?";
	}

	@Override()
	protected String getWhere(Solicitante bean, List<Object> params) {
		StringBuilder whereSolic = new StringBuilder();
		whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.TIPIDEN, bean.getTipIden(), params));
		whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.PREDNI, bean.getPreDni(), params));
		whereSolic.append(SqlUtils.generarWhereIgualDni(DBConstants.DNI, bean.getDni(), params));
		whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.SUFDNI, bean.getSufDni(), params));
		whereSolic.append(SqlUtils.generarWhereLike(DBConstants.NOMBRE, bean.getNombre(), params, false));
		whereSolic.append(SqlUtils.generarWhereLike(DBConstants.APEL1, bean.getApellido1(), params, false));
		whereSolic.append(SqlUtils.generarWhereLike(DBConstants.APEL2, bean.getApellido2(), params, false));
		whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.ESTADO, bean.getEstado(), params));

		Entidad entidad = bean.getEntidad();
		if (entidad != null) {
			whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.TIPO_ENTIDAD, entidad.getTipo(), params));
			whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.COD_ENTIDAD, entidad.getCodigo(), params));
			whereSolic.append(SqlUtils.generarWhereLike(DBConstants.DESC_ES, entidad.getDescEs(), params, false));
			whereSolic.append(SqlUtils.generarWhereLike(DBConstants.DESC_EU, entidad.getDescEu(), params, false));
			whereSolic.append(SqlUtils.generarWhereIgual(DBConstants.CIF, entidad.getCif(), params));
		}

		List<String> permisos = new ArrayList<String>();
		SqlUtils.comprobarPermisos(bean.getGestorExpedientes(), "GESTOR_EXPEDIENTES", permisos);
		SqlUtils.comprobarPermisos(bean.getGestorFacturas(), "GESTOR_FACTURAS", permisos);
		SqlUtils.comprobarPermisos(bean.getCoordinador(), DBConstants.COORDINADOR, permisos);
		SqlUtils.comprobarPermisos(bean.getGestorExpedientesVIP(), "GESTOR_EXPEDIENTES_VIP", permisos);
		SqlUtils.comprobarPermisos(bean.getGestorExpedientesBOPV(), "GESTOR_EXPEDIENTES_BOPV", permisos);
		whereSolic.append(SqlUtils.generarWherePermisos(permisos, params));

		return whereSolic.toString();
	}

	@Override()
	protected String getWhereLike(Solicitante bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLikeSolic = new StringBuilder();
		whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.TIPIDEN, bean.getTipIden(), params));
		whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.PREDNI, bean.getPreDni(), params));
		whereLikeSolic.append(SqlUtils.generarWhereIgualDni(DBConstants.DNI, bean.getDni(), params));
		whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.SUFDNI, bean.getSufDni(), params));
		whereLikeSolic
				.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.NOMBRE, bean.getNombre(), params, startsWith));
		whereLikeSolic.append(
				SqlUtils.generarWhereLikeNormalizado(DBConstants.APEL1, bean.getApellido1(), params, startsWith));
		whereLikeSolic.append(
				SqlUtils.generarWhereLikeNormalizado(DBConstants.APEL2, bean.getApellido2(), params, startsWith));
		whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.ESTADO, bean.getEstado(), params));

		Entidad entidad = bean.getEntidad();
		if (entidad != null) {
			whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.TIPO_ENTIDAD, entidad.getTipo(), params));
			whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.COD_ENTIDAD, entidad.getCodigo(), params));
			whereLikeSolic.append(
					SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_ES, entidad.getDescEs(), params, startsWith));
			whereLikeSolic.append(
					SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_EU, entidad.getDescEu(), params, startsWith));
			whereLikeSolic.append(SqlUtils.generarWhereIgual(DBConstants.CIF, entidad.getCif(), params));
		}

		if (bean.getNombreFiltro() != null) {
			whereLikeSolic.append(SqlUtils.generarWhereLikeNormalizado("(NOMBRE || ' ' || APEL1 || ' ' || APEL2)",
					bean.getNombreFiltro(), params, startsWith));
		}

		List<String> permisos = new ArrayList<String>();
		SqlUtils.comprobarPermisos(bean.getGestorExpedientes(), "GESTOR_EXPEDIENTES", permisos);
		SqlUtils.comprobarPermisos(bean.getGestorFacturas(), "GESTOR_FACTURAS", permisos);
		SqlUtils.comprobarPermisos(bean.getCoordinador(), DBConstants.COORDINADOR, permisos);
		SqlUtils.comprobarPermisos(bean.getGestorExpedientesVIP(), "GESTOR_EXPEDIENTES_VIP", permisos);
		SqlUtils.comprobarPermisos(bean.getGestorExpedientesBOPV(), "GESTOR_EXPEDIENTES_BOPV", permisos);
		whereLikeSolic.append(SqlUtils.generarWherePermisos(permisos, params));

		return whereLikeSolic.toString();
	}

	@Override()
	public void guardarFoto(Solicitante bean) {
		if (bean != null && StringUtils.isNotBlank(bean.getDni())) {
			StringBuilder update = new StringBuilder();
			update.append("UPDATE AA79B77S01");
			update.append(
					" SET (TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077) = (SELECT TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2 FROM X54JAPI_SOLICITANTES WHERE DNI = DNI_077)");
			update.append(" WHERE DNI_077 = ?");
			Integer rdo = this.getJdbcTemplate().update(update.toString(), bean.getDni());
			if (rdo == 0) {
				StringBuilder insert = new StringBuilder();
				insert.append(
						"INSERT INTO AA79B77S01 (DNI_077, TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077)");
				insert.append(
						" SELECT DNI, TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2 FROM X54JAPI_SOLICITANTES WHERE DNI = ?");
				this.getJdbcTemplate().update(insert.toString(), bean.getDni());
			}
		}
	}

	@Override
	public List<Solicitante> findGestoresDeExp(Solicitante solicitanteFilter) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(SELECT77);
		query.append(JOIN_54_E1);
		query.append(ON_DNI_77_54);
		query.append(JOIN_51);
		query.append(ON_ANYO_NUMEXP_54_51);
		query.append(JOIN_51_59 + EstadoExpedienteEnum.EN_ESTUDIO.getValue());

		if (solicitanteFilter.getNombreFiltro() != null) {
			query.append(SqlUtils.generarWhereLikeNormalizado(NOMBRE_APELLIDOS_77, solicitanteFilter.getNombreFiltro(),
					params, false));
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());
	}

	@Override()
	public List<Gestor> findGestoresRespresentante(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder();
		query.append("SELECT PREDNI, DNI, SUFDNI, NOMBRE, APEL1, APEL2");
		query.append(", GESTOR_EXPEDIENTES GESTOREXPEDIENTES");
		query.append(", GESTOR_EXPEDIENTES_BOPV GESTOREXPEDIENTESBOPV");
		query.append(", GESTOR_EXPEDIENTES_VIP GESTOREXPEDIENTESVIP");
		query.append(this.getFrom());
		query.append(
				" WHERE (GESTOR_EXPEDIENTES = 'S' OR GESTOR_EXPEDIENTES_BOPV = 'S' OR GESTOR_EXPEDIENTES_VIP = 'S') AND");
		query.append(
				" COD_ENTIDAD = (SELECT COD_ENTIDAD FROM X54J.X54JAPI_SOLICITANTES WHERE COORDINADOR = 'S' AND DNI = ?)");

		params.add(bean.getDni());

		return this.getJdbcTemplate().query(query.toString(), this.getRwMapGestorRepresentante(), params.toArray());
	}

	@Override()
	public Solicitante findSolicitanteConDatosDireccion(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(
				" , t2.DIRECCION DIRECCION, t2.MUNICIPIO MUNICIPIO, t2.LOCALIDAD LOCALIDAD, t2.PROVINCIA PROVINCIA, t2.CODPOSTAL CODPOSTAL ");
		query.append(this.getFrom(bean, params));
		query.append(" LEFT JOIN X54JNORA t2 ON X54JAPI_SOLICITANTES.CDIRNORA = t2.CDIRNORA ");
		query.append(this.getWherePK(bean, params));

		List<Solicitante> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteDireccion,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public Solicitante findGestorCoordinador(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom(bean, params));
		query.append(this.getWherePK(bean, params));
		query.append(
				" AND (GESTOR_EXPEDIENTES = 'S' OR GESTOR_EXPEDIENTES_BOPV = 'S' OR GESTOR_EXPEDIENTES_VIP = 'S' OR COORDINADOR = 'S')");

		List<Solicitante> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapGestorCoordinador,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public List<Gestor> findGestoresConsulta(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT");
		query.append(" DNI_077 " + DBConstants.DNI + ",");
		query.append(" PREDNI_077 " + DBConstants.PREDNI + ",");
		query.append(" SUFDNI_077 " + DBConstants.SUFDNI + ",");
		query.append(" NOMBRE_077 " + DBConstants.NOMBRE + ",");
		query.append(" APEL1_077 " + DBConstants.APEL1 + ",");
		query.append(" APEL2_077 " + DBConstants.APEL2 + "");
		query.append(" FROM  AA79B54S01, AA79B77S01");
		query.append(" WHERE (DNI_REPRESENTANTE_054 = ? OR DNI_SOLICITANTE_054= ?)");
		query.append(" AND DNI_SOLICITANTE_054 = DNI_077");

		params.add(bean.getDni());
		params.add(bean.getDni());

		return this.getJdbcTemplate().query(query.toString(), this.rwMapGestorConsulta, params.toArray());
	}

	@Override
	public List<Solicitante> findGestoresDeExpCEntidad(Solicitante solicitanteFilter) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(SELECT77);
		query.append(JOIN_54_E1);
		query.append(ON_DNI_77_54);
		query.append(JOIN_51);
		query.append(ON_ANYO_NUMEXP_54_51);
		query.append(JOIN_51_59 + EstadoExpedienteEnum.EN_ESTUDIO.getValue());
		query.append(JOIN_VISTAX54JAPIENTIDADESSOLIC_A1);
		query.append(" ON a1.TIPO = e1.TIPO_ENTIDAD_054 ");
		query.append(" AND a1.CODIGO = e1.ID_ENTIDAD_054 ");

		if (solicitanteFilter.getNombreFiltro() != null) {
			query.append(SqlUtils.generarWhereLikeNormalizado(NOMBRE_APELLIDOS_77, solicitanteFilter.getNombreFiltro(),
					params, false));
		}
		query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.TIPO,
				solicitanteFilter.getEntidad().getTipo(), params));
		query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.CODIGO,
				solicitanteFilter.getEntidad().getCodigo(), params));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());
	}

	@Override
	public List<Solicitante> findRevisionContactoFacturarConExpedientes(Solicitante solicitanteFilter) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(getSelectSolicitanteAFacturar());
		query.append(JOIN_51_59 + EstadoExpedienteEnum.CERRADO.getValue());
		query.append(" AND b1.ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue());
		query.append(IGUALDAD_ANYO_NUMEXP_51_59);

		if (StringUtils.isNotBlank(solicitanteFilter.getEntidad().getTipo())
				|| (solicitanteFilter.getEntidad().getCodigo() != null
						&& solicitanteFilter.getEntidad().getCodigo() != -1)) {
			query.append(JOIN_VISTAX54JAPIENTIDADESSOLIC_A1);
			query.append(ON_TIPO_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_58);
			if (solicitanteFilter.getEntidad().getCodigo() != null
					&& solicitanteFilter.getEntidad().getCodigo() != -1) {
				query.append(IGUALDAD_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_58);
				query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.CODIGO,
						solicitanteFilter.getEntidad().getCodigo(), params));
			}

			query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.TIPO,
					solicitanteFilter.getEntidad().getTipo(), params));

		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());
	}

	@Override()
	public Solicitante getPermisosBopv(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT);
		query.append("g1.GESTOR_EXPEDIENTES_BOPV GESTOREXPEDIENTESBOPV");
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.FROM);
		query.append("AA79B54S01 t1 ");
		query.append(DaoConstants.LEFT_JOIN);
		query.append("X54JAPI_SOLICITANTES g1 ON t1.DNI_SOLICITANTE_054 = g1.DNI");
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.WHERE);
		query.append("t1.ANYO_054");
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append("t1.NUM_EXP_054");
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(anyo);
		params.add(numExp);

		List<Solicitante> listSol = this.getJdbcTemplate().query(query.toString(), this.rwMapPermisosBOPV,
				params.toArray());
		return DataAccessUtils.uniqueResult(listSol);
	}

	@Override()
	public Solicitante find77(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(SELECT77);
		query.append(" WHERE DNI_077 = ? ");
		params.add(bean.getDni());
		List<Solicitante> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public int comprobarEntidadYSolicitanteValidos(String tipoEnt, int idEntidad, String dniContacto) {
		List<Object> params = new ArrayList<Object>();
		params.add(tipoEnt);
		params.add(idEntidad);
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(1) ");
		if (StringUtils.isNotEmpty(dniContacto)) {
			query.append(
					" FROM X54JAPI_SOLICITANTES T1 LEFT JOIN X54JAPI_ENTIDADES_SOLIC T2 ON T1.TIPO_ENTIDAD = T2.TIPO AND T1.COD_ENTIDAD = T2.CODIGO ");
			query.append(" WHERE T1.TIPO_ENTIDAD =? AND T1.COD_ENTIDAD = ? AND T1.DNI = ? AND T2.ESTADO = '"
					+ Constants.ALTA + "' ");
			params.add(dniContacto);
		} else {
			query.append(" FROM X54JAPI_ENTIDADES_SOLIC T2 WHERE T2.TIPO =? AND T2.CODIGO = ? AND T2.ESTADO = '"
					+ Constants.ALTA + "' ");
		}
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public List<Solicitante> findGestoresExpConfidenciales(Solicitante solicitante) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(SELECT77);
		query.append(JOIN_54_E1);
		query.append(ON_DNI_77_54);
		query.append(JOIN_51);
		query.append(ON_ANYO_NUMEXP_54_51);
		// Expediente traducción/revisión
		query.append(DaoConstants.JOIN);
		query.append(DBConstants.TABLA_53);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_053);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.S1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_053);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.S1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		// Where
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(
				DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_CONFIDENCIAL_053, Constants.SI,
				params));

		if (solicitante.getNombreFiltro() != null) {
			query.append(SqlUtils.generarWhereLikeNormalizado(NOMBRE_APELLIDOS_77, solicitante.getNombreFiltro(),
					params, false));
		}
		query.append(SqlUtils.generarWhereIgual(DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_054,
				solicitante.getEntidad().getTipo(), params));
		query.append(SqlUtils.generarWhereIgual(DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_054,
				solicitante.getEntidad().getCodigo(), params));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());
	}

	@Override
	public List<Solicitante> findSolicitantesGestAnul(Solicitante filterSolicitante) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect(true));
		query.append(getFrom() + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA + DaoConstants.BLANK);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_54 + DaoConstants.BLANK + DaoConstants.E1_MINUSCULA
				+ DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD + DaoConstants.SIGNOIGUAL
				+ DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054);
		query.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.COD_ENTIDAD + DaoConstants.SIGNOIGUAL
						+ DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI + DaoConstants.SIGNOIGUAL
				+ DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.DNI_SOLICITANTE_054);

		// TABLA 51 - Expediente
		query.append(DaoConstants.JOIN + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.E2_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_054
				+ DaoConstants.AND + DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_054);

		// TABLA 59 - Bitacora expediente
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DBConstants.ESTADO_ACTUAL);
		query.append(DaoConstants.ON + DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DBConstants.ESTADO_ACTUAL + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_059);
		query.append(DaoConstants.AND + DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DBConstants.ESTADO_ACTUAL + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ANYO_059);
		query.append(DaoConstants.AND + DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ESTADO_BITACORA_051 + DaoConstants.SIGNOIGUAL + DBConstants.ESTADO_ACTUAL
				+ DaoConstants.SIGNO_PUNTO + DBConstants.ID_ESTADO_BITACORA_059);

		// TABLA 64 - Requerimientos expediente
		query.append(DaoConstants.JOIN + DBConstants.TABLA_064 + DaoConstants.BLANK + DaoConstants.S1_MINUSCULA);
		query.append(DaoConstants.ON + DBConstants.ESTADO_ACTUAL + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_REQUERIMIENTO_059 + DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.ID_064);

		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(
				DaoConstants.E2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ESTADO_BAJA_051, Constants.ALTA,
				params));
		query.append(DaoConstants.AND);
		query.append(QueryUtils.getWhereExpAAnular());

		Entidad entidad = filterSolicitante.getEntidad();
		if (entidad != null) {
			query.append(SqlUtils.generarWhereIgual(DBConstants.TIPO_ENTIDAD, entidad.getTipo(), params));
			query.append(SqlUtils.generarWhereIgual(DBConstants.COD_ENTIDAD, entidad.getCodigo(), params));
		}

		return this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());

	}

	@Override
	public List<Solicitante> findSolicitanteAFacturarActDatosFact(Solicitante solicitanteFilter) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(getSelectSolicitanteAFacturar());
		query.append(QueryUtils.getJoinTabla59and51());

		if (StringUtils.isNotBlank(solicitanteFilter.getEntidad().getTipo())
				|| (solicitanteFilter.getEntidad().getCodigo() != null
						&& solicitanteFilter.getEntidad().getCodigo() != -1)) {
			query.append(JOIN_VISTAX54JAPIENTIDADESSOLIC_A1);
			query.append(ON_TIPO_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_58);
			query.append(QueryUtils.getWhereActDatosFactEntidades(params));

			if (solicitanteFilter.getEntidad().getCodigo() != null
					&& solicitanteFilter.getEntidad().getCodigo() != -1) {
				query.append(IGUALDAD_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_58);
				query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.CODIGO,
						solicitanteFilter.getEntidad().getCodigo(), params));
			}

			query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.TIPO,
					solicitanteFilter.getEntidad().getTipo(), params));

		} else {
			query.append(QueryUtils.getWhereActDatosFactEntidades(params));
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());
	}

	/**
	 * @return String
	 */
	private String getSelectSolicitanteAFacturar() {
		StringBuilder query = new StringBuilder();

		query.append(SELECT77);
		query.append(" JOIN AA79B58S01 e1");
		query.append(" ON t1.DNI_077 = e1.DNI_CONTACTO_058");
		query.append(JOIN_51);
		query.append(" ON e1.ANYO_058 = s1.ANYO_051 AND e1.NUM_EXP_058 = s1.NUM_EXP_051");

		return query.toString();
	}

	@Override
	public List<Solicitante> findSolicitanteActDatosFact(Solicitante solicitanteFilter) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(SELECT77);
		query.append(JOIN_54_E1);
		query.append(ON_DNI_77_54);
		query.append(JOIN_51);
		query.append(ON_ANYO_NUMEXP_54_51);
		query.append(QueryUtils.getJoinTabla59and51());

		if (StringUtils.isNotBlank(solicitanteFilter.getEntidad().getTipo())
				|| (solicitanteFilter.getEntidad().getCodigo() != null
						&& solicitanteFilter.getEntidad().getCodigo() != -1)) {
			query.append(JOIN_VISTAX54JAPIENTIDADESSOLIC_A1);
			query.append(ON_TIPO_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_54);
			query.append(QueryUtils.getWhereActDatosFactEntidades(params));

			if (solicitanteFilter.getEntidad().getCodigo() != null
					&& solicitanteFilter.getEntidad().getCodigo() != -1) {
				query.append(IGUALDAD_ENTIDAD_VISTAX54JAPIENTIDADESSOLIC_54);
				query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.CODIGO,
						solicitanteFilter.getEntidad().getCodigo(), params));
			}

			query.append(SqlUtils.generarWhereIgual(DaoConstants.A1_MINUSCULA_PUNTO + DBConstants.TIPO,
					solicitanteFilter.getEntidad().getTipo(), params));

		} else {
			query.append(QueryUtils.getWhereActDatosFactEntidades(params));
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());
	}

	@Override()
	public List<Solicitante> findGestores(Solicitante bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(SELECT77);
		query.append(JOIN_54_E1);
		query.append(ON_DNI_77_54);
		query.append(JOIN_51);
		query.append(ON_ANYO_NUMEXP_54_51);
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(
				DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ESTADO_BAJA_051, Constants.ALTA,
				params));
		query.append(SqlUtils.generarWhereIgual(
				DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054,
				bean.getEntidad().getTipo(), params));
		query.append(SqlUtils.generarWhereIgual(
				DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054,
				bean.getEntidad().getCodigo(), params));

		if (bean.getNombreFiltro() != null) {
			query.append(
					SqlUtils.generarWhereLikeNormalizado(NOMBRE_APELLIDOS_77, bean.getNombreFiltro(), params, false));
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteGestor, params.toArray());

	}

	@Override
	public PersonalIZO getDatosGestor(PersonalIZO personalIZO) {
		List<Object> paramsGDG = new ArrayList<Object>();
		StringBuilder queryGDG = new StringBuilder();
		queryGDG.append("SELECT t1.TIPIDEN TIPIDEN, ");
		queryGDG.append("t1.TIPO_ENTIDAD TIPOENTIDAD, ");
		queryGDG.append("t1.COD_ENTIDAD IDENTIDAD, ");
		queryGDG.append("t1.GESTOR_EXPEDIENTES_VIP INDVIP, ");
		queryGDG.append("t1.PREDNI PREDNI, ");
		queryGDG.append("t1.DNI DNI, ");
		queryGDG.append("t1.SUFDNI SUFDNI, ");
		queryGDG.append("t1.NOMBRE NOMBRE, ");
		queryGDG.append("t1.APEL1 APEL1, ");
		queryGDG.append("t1.APEL2 APEL2, ");
		queryGDG.append("t1.APEL1 || ' ' || t1.APEL2  || ', ' || t1.NOMBRE AS NOMBRECOMPLETO ");
		queryGDG.append(" FROM X54JAPI_SOLICITANTES t1");
		queryGDG.append(" WHERE t1.DNI = ?");
		paramsGDG.add(personalIZO.getDni());
		List<PersonalIZO> beanList = this.getJdbcTemplate().query(queryGDG.toString(), this.rwMapGestor,
				paramsGDG.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public List<Solicitante> findEntidadCentroOrganico(Solicitante bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		query.append(DaoConstants.SELECT);
		query.append("TIPIDEN");
		query.append(", PREDNI");
		query.append(", SOL.DNI");
		query.append(", SUFDNI");
		query.append(", TRIM(NOMBRE) NOMBRE");
		query.append(", TRIM(APEL1) APEL1");
		query.append(", TRIM(APEL2) APEL2");
		query.append(", APEL1 || ' ' || APEL2  AS APELLIDOS");
		query.append(", ESTADO");
		query.append(", TIPO_ENTIDAD AS TIPOENTIDAD");
		query.append(", COD_ENTIDAD AS CODENTIDAD");
		query.append(", DESC_ES AS DESCES");
		query.append(", DESC_EU AS DESCEU");
		query.append(", CIF");
		query.append(", GESTOR_EXPEDIENTES AS GESTOREXPEDIENTES");
		query.append(", GESTOR_FACTURAS AS GESTORFACTURAS");
		query.append(", COORDINADOR");
		query.append(", GESTOR_EXPEDIENTES_VIP AS GESTOREXPEDIENTESVIP");
		query.append(", DECODE(GESTOR_EXPEDIENTES_VIP, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es))
				.append("') AS GESTOREXPEDIENTESVIPDESCES");
		query.append(", DECODE(GESTOR_EXPEDIENTES_VIP, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu))
				.append("') AS GESTOREXPEDIENTESVIPDESCEU");
		query.append(", GESTOR_EXPEDIENTES_BOPV AS GESTOREXPEDIENTESBOPV");
		query.append(", DESCRIPCION_CASTELLANO_AMP AS CENTROORGANICOES");
		query.append(", DESCRIPCION_EUSKERA_AMP AS CENTROORGANICOEU");

        query.append(" FROM X54JAPI_SOLICITANTES SOL LEFT JOIN X54JAPI_CENORGS_PERSONA CP ON SOL.DNI = CP.DNI");

        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(bean, startsWith, params, false));

        if (jqGridRequestDto != null) {
            query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
        }

        return this.getJdbcTemplate().query(query.toString(), this.rwMapEntidadCentroOrganico, params.toArray());
	}

}
