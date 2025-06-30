package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.Lotes;

@Repository
@Transactional
public class DatosContactoDaoImpl extends GenericoDaoImpl<DatosContacto> implements DatosContactoDao {

	private static final String[] ORDER_BY_WHITE_LIST = null;

	public DatosContactoDaoImpl() {
		// Constructor
		super(DatosContacto.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<DatosContacto> rwMap = new RowMapper<DatosContacto>() {
		@Override()
		public DatosContacto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setTelfijo031(resultSet.getString(DBConstants.TELFIJO));
			datosContacto.setTelmovil031(resultSet.getString(DBConstants.TELMOVIL));
			datosContacto.setEmail031(resultSet.getString(DBConstants.EMAIL));
			datosContacto.setDni031(resultSet.getString(DBConstants.DNI));
			datosContacto.setTipiden031(resultSet.getInt(DBConstants.TIPIDEN));
			datosContacto.setPredni031(resultSet.getString(DBConstants.PREDNI));
			datosContacto.setSufdni031(resultSet.getString(DBConstants.SUFDNI));
			datosContacto.setLogin031(resultSet.getString(DBConstants.LOGIN));
			datosContacto.setOrigenlogin031(resultSet.getString(DBConstants.ORIGENLOGIN));
			datosContacto.setSms031(resultSet.getString(DBConstants.SMS));

			return datosContacto;
		}
	};

	private RowMapper<DatosContacto> rwMapPK = new RowMapper<DatosContacto>() {
		@Override()
		public DatosContacto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setDni031(resultSet.getString(DBConstants.DNI));

			return datosContacto;
		}
	};

	private RowMapper<DatosContacto> rwMapMail = new RowMapper<DatosContacto>() {
		@Override()
		public DatosContacto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031(resultSet.getString(DBConstants.EMAIL));
			return datosContacto;
		}
	};

	private RowMapper<DatosContacto> rwMapDatosContacto = new RowMapper<DatosContacto>() {
		@Override()
		public DatosContacto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setTelfijo031(resultSet.getString(DBConstants.TELFIJO));
			datosContacto.setTelmovil031(resultSet.getString(DBConstants.TELMOVIL));
			datosContacto.setEmail031(resultSet.getString(DBConstants.EMAIL));
			datosContacto.setDni031(resultSet.getString(DBConstants.DNI));
			datosContacto.setPredni031(resultSet.getString(DBConstants.PREDNI));
			datosContacto.setSufdni031(resultSet.getString(DBConstants.SUFDNI));
			datosContacto.setNombreApellidos(resultSet.getString(DBConstants.NOMBRECOMPLETO));

			return datosContacto;
		}
	};

	private RowMapper<String> rwMapSolicitanteExp = new RowMapper<String>() {
		@Override()
		public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return resultSet.getString(DBConstants.NOMBRECOMPLETO);
		}
	};

	@Override()
	protected String getSelect() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.TELFIJO_031 TELFIJO");
		query.append(", t1.TELMOVIL_031 TELMOVIL ");
		query.append(", t1.EMAIL_031 EMAIL ");
		query.append(", t1.DNI_031 DNI ");
		query.append(", t1.TIPIDEN_031 TIPIDEN ");
		query.append(", t1.PREDNI_031 PREDNI ");
		query.append(", t1.SUFDNI_031 SUFDNI ");
		query.append(", t1.LOGIN_031 LOGIN ");
		query.append(", t1.ORIGEN_LOGIN_031 ORIGENLOGIN ");
		query.append(", t1.SMS_031 SMS ");

		return query.toString();
	}

	@Override()
	protected String getFrom() {
		return " FROM X54JAPI_DATOS_CONTACTO t1 ";
	}

	@Override()
	protected RowMapper<DatosContacto> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return DatosContactoDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return "DNI_031";
	}

	@Override()
	protected RowMapper<DatosContacto> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(DatosContacto bean, List<Object> params) {
		params.add(bean.getDni031());
		return " WHERE t1.DNI_031 = ?";
	}

	@Override()
	protected String getWhere(DatosContacto bean, List<Object> params) {
		return this.getWhereClause(bean, params);
	}

	@Override()
	protected String getWhereLike(DatosContacto bean, Boolean startsWith, List<Object> params, Boolean search) {
		return this.getWhereClause(bean, params);
	}

	/**
	 * @param bean
	 * @param params
	 * @return
	 */
	private String getWhereClause(DatosContacto bean, List<Object> params) {
		StringBuilder where = new StringBuilder(DatosContactoDaoImpl.STRING_BUILDER_INIT);

		if (bean != null) {
			where.append(SqlUtils.generarWhereIgual("TELFIJO_031", bean.getTelfijo031(), params));
			where.append(SqlUtils.generarWhereIgual("TELMOVIL_031", bean.getTelmovil031(), params));
			where.append(SqlUtils.generarWhereIgual("EMAIL_031", bean.getEmail031(), params));
			where.append(SqlUtils.generarWhereIgual("DNI_031", bean.getDni031(), params));
			where.append(SqlUtils.generarWhereIgual("TIPIDEN_031", bean.getTipiden031(), params));
			where.append(SqlUtils.generarWhereIgual("PREDNI_031", bean.getPredni031(), params));
			where.append(SqlUtils.generarWhereIgual("SUFDNI_031", bean.getSufdni031(), params));
			where.append(SqlUtils.generarWhereIgual("LOGIN_031", bean.getLogin031(), params));
			where.append(SqlUtils.generarWhereIgual("ORIGEN_LOGIN_031", bean.getOrigenlogin031(), params));
			where.append(SqlUtils.generarWhereIgual("SMS_031", bean.getSms031(), params));
		}

		return where.toString();
	}

	/**
	 * 
	 * @param idTarea Long
	 * @return List<DatosContacto>
	 */
	@Override()
	public List<DatosContacto> getMailsProveedoresPorIdTarea(BigDecimal idTarea) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT T4.EMAIL_031 EMAIL ");
		query.append(" FROM AA79B81T00 T1 ");
		query.append(" JOIN AA79B29T00 T2 ");
		query.append(" ON T1.ID_LOTE_081 = T2.ID_LOTE_029 ");
		query.append(" JOIN X54JAPI_PROVEEDORES T3 ");
		query.append(" ON T2.ID_EMPRESA_PROV_029 = T3.COD_ENTIDAD ");
		query.append(" AND T2.TIPO_ENTIDAD_029 = T3.TIPO_ENTIDAD ");
		query.append(" JOIN X54JAPI_DATOS_CONTACTO T4 ");
		query.append(" ON T3.DNI = T4.DNI_031 ");
		query.append(" WHERE T1.ID_TAREA_081 = ? ");

		params.add(idTarea);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapMail, params.toArray());
	}

	@Override()
	public List<DatosContacto> getMailRecursoTareaAuditoria(BigDecimal idTarea) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT T4.EMAIL_031 EMAIL ");
		query.append(" FROM AA79BC2T00 T1 ");
		query.append(" JOIN AA79B81T00 T2 ON T1.ID_TAREA_REVISION_0C2 = T2.ID_TAREA_081");
		query.append(" JOIN AA79B77T00 T3 ");
		query.append(" ON T2.DNI_RECURSO_081 = T3.DNI_077 ");
		query.append(" JOIN X54JAPI_DATOS_CONTACTO T4 ");
		query.append(" ON T3.DNI_077 = T4.DNI_031 ");
		query.append(" WHERE T1.ID_0C2 = ? ");

		params.add(idTarea);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapMail, params.toArray());
	}

	@Override()
	public DatosContacto findDatosContacto(DatosContacto bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());

		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append("datosPersona.APEL1_077");
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append("datosPersona.APEL2_077");
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append("datosPersona.NOMBRE_077");
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AS);
		query.append(DBConstants.NOMBRECOMPLETO);
		query.append(DaoConstants.BLANK);
		query.append(this.getFrom());
		query.append(DaoConstants.JOIN);
		query.append("AA79B77S01 datosPersona");
		query.append(DaoConstants.ON);
		query.append("t1.DNI_031 = datosPersona.DNI_077");
		query.append(DaoConstants.BLANK);
		query.append(this.getWherePK(bean, params));

		List<DatosContacto> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapDatosContacto,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public String findSolicitanteExp(ExpedienteFacturacion expedienteFacturacion) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(DaoConstants.SELECT);

		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append("d.APEL1_077");
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append("d.APEL2_077");
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append("d.NOMBRE_077");
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AS);
		query.append(DBConstants.NOMBRECOMPLETO);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.FROM);
		query.append("AA79B51S01 e");
		query.append(DaoConstants.JOIN);
		query.append("AA79B54S01 f");
		query.append(DaoConstants.ON);
		query.append("e.ANYO_051 = f.ANYO_054");
		query.append(DaoConstants.AND);
		query.append("e.NUM_EXP_051 = f.NUM_EXP_054");
		query.append(DaoConstants.JOIN);
		query.append("AA79B77T00 d");
		query.append(DaoConstants.ON);
		query.append("f.DNI_SOLICITANTE_054 = d.DNI_077");
		query.append(DaoConstants.WHERE);
		query.append("e.ANYO_051 = ?");
		query.append(DaoConstants.AND);
		query.append("e.NUM_EXP_051 = ?");
		params.add(expedienteFacturacion.getAnyo());
		params.add(expedienteFacturacion.getNumExp());

		List<String> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitanteExp,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public List<DatosContacto> getMailsProveedoresPorIdLote(Lotes lotes) {

		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT T3.EMAIL_031 EMAIL ");
		query.append(" FROM AA79B29T00 T1 ");
		query.append(" JOIN X54JAPI_PROVEEDORES T2 ");
		query.append(" ON T1.ID_EMPRESA_PROV_029 = T2.COD_ENTIDAD ");
		query.append(" AND T1.TIPO_ENTIDAD_029   = T2.TIPO_ENTIDAD ");
		query.append(" JOIN X54JAPI_DATOS_CONTACTO T3 ");
		query.append(" ON T2.DNI             = T3.DNI_031 ");
		query.append(" WHERE T1.ID_LOTE_029 = ? ");

		params.add(lotes.getIdLote());
		return this.getJdbcTemplate().query(query.toString(), this.rwMapMail, params.toArray());
	}

}
