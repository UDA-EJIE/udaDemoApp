package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.PropertiesConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.GruposTrabajo;
import com.ejie.aa79b.model.Propiedad;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.utils.Utils;

@Repository
@Transactional
public class GestorExpedienteDaoImpl extends GenericoDaoImpl<Expediente> implements GestorExpedienteDao {

	@Autowired()
	private PropiedadDao propiedadDao;

	protected static final String ANYO_GESTOR_EXPEDIENTE = "ANYO_054";
	protected static final String NUM_EXP_GESTOR_EXPEDIENTE = "NUM_EXP_054";
	protected static final String DNI_SOLICITANTE_GESTOR_EXPEDIENTE = "DNI_SOLICITANTE_054";
	protected static final String TIPO_ENTIDAD_GESTOR_EXPEDIENTE = "TIPO_ENTIDAD_054";
	protected static final String ID_ENTIDAD_GESTOR_EXPEDIENTE = "ID_ENTIDAD_054";
	protected static final String IND_VIP_GESTOR_EXPEDIENTE = "IND_VIP_054";

	private static final String[] ORDER_BY_WHITE_LIST = null;

	public GestorExpedienteDaoImpl() {
		// Constructor
		super(Expediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Expediente> rwMap = new RowMapper<Expediente>() {
		@Override()
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong(ANYO_GESTOR_EXPEDIENTE));
			expediente.setNumExp(resultSet.getInt(NUM_EXP_GESTOR_EXPEDIENTE));
			Gestor gestorExpediente = new Gestor();
			Solicitante solicitanteAux = new Solicitante();
			solicitanteAux.setDni(resultSet.getString(DNI_SOLICITANTE_GESTOR_EXPEDIENTE));
			solicitanteAux.setGestorExpedientesVIP(resultSet.getString(IND_VIP_GESTOR_EXPEDIENTE));
			gestorExpediente.setSolicitante(solicitanteAux);
			Entidad entidadAux = new Entidad();
			entidadAux.setTipo(resultSet.getString(TIPO_ENTIDAD_GESTOR_EXPEDIENTE));
			entidadAux.setTipoDesc(Utils.getTipoEntidadDescLabel(entidadAux.getTipo()));
			entidadAux.setCodigo(resultSet.getInt(ID_ENTIDAD_GESTOR_EXPEDIENTE));
			gestorExpediente.setEntidad(entidadAux);
			expediente.setGestorExpediente(gestorExpediente);
			return expediente;
		}
	};

	private RowMapper<Expediente> rwMapPK = new RowMapper<Expediente>() {
		@Override()
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong(ANYO_GESTOR_EXPEDIENTE));
			expediente.setNumExp(resultSet.getInt(NUM_EXP_GESTOR_EXPEDIENTE));

			return expediente;
		}
	};

	private RowMapper<GestorExpediente> rwMapDatosContacto = new RowMapper<GestorExpediente>() {
		@Override()
		public GestorExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			GestorExpediente gestorExpediente = new GestorExpediente();
			gestorExpediente.setAnyo054(resultSet.getString("ANYO54"));
			gestorExpediente.setNumexp054(resultSet.getString("NUMEXP054"));

			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031(resultSet.getString("EMAIL031"));
			datosContacto.setTelmovil031(resultSet.getString("TELMOVIL031"));
			datosContacto.setTelfijo031(resultSet.getString("TELFIJO031"));

			Solicitante solicitante = new Solicitante();
			solicitante.setDatosContacto(datosContacto);

			gestorExpediente.setSolicitante(solicitante);

			return gestorExpediente;
		}
	};

	private RowMapper<GestorExpediente> rwMapDni = new RowMapper<GestorExpediente>() {
		@Override()
		public GestorExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			GestorExpediente gestorExpediente = new GestorExpediente();

			Solicitante solicitante = new Solicitante();
			solicitante.setDni(resultSet.getString("DNISOLICITANTE054"));

			gestorExpediente.setSolicitante(solicitante);

			return gestorExpediente;
		}
	};

	private RowMapper<GruposTrabajo> rwMapGrupoTrabajoGestor = new RowMapper<GruposTrabajo>() {
		@Override()
		public GruposTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			GruposTrabajo gruposTrabajo = new GruposTrabajo();
			gruposTrabajo.setId(resultSet.getLong("IDGRUPO"));

			return gruposTrabajo;
		}
	};

	/**
	 * Inserts a single row in the GestorExpediente table.
	 *
	 * @param gestorExpediente
	 *            GestorExpediente
	 * @return GestorExpediente
	 */
	@Override
	public Expediente add(Expediente expedienteGestor) {
		String query = "INSERT INTO AA79B54S01 (ANYO_054, NUM_EXP_054, DNI_SOLICITANTE_054, TIPO_ENTIDAD_054, "
				+ "ID_ENTIDAD_054, IND_VIP_054) " + "VALUES (?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, expedienteGestor.getAnyo(), expedienteGestor.getNumExp(),
				expedienteGestor.getGestorExpediente().getSolicitante().getDni(),
				expedienteGestor.getGestorExpediente().getEntidad().getTipo(),
				expedienteGestor.getGestorExpediente().getEntidad().getCodigo(),
				expedienteGestor.getGestorExpediente().getSolicitante().getGestorExpedientesVIP());

		return expedienteGestor;
	}

	/**
	 * Updates a single row in the GestorExpediente table.
	 *
	 * @param gestorExpediente
	 *            GestorExpediente
	 * @return GestorExpediente
	 */
	@Override
	public Expediente update(Expediente expedienteGestor) {
		String query = "UPDATE AA79B54S01 SET DNI_SOLICITANTE_054=?, TIPO_ENTIDAD_054=?,"
				+ "ID_ENTIDAD_054=?, IND_VIP_054=? WHERE ANYO_054=? AND NUM_EXP_054=?";
		this.getJdbcTemplate().update(query, expedienteGestor.getGestorExpediente().getSolicitante().getDni(),
				expedienteGestor.getGestorExpediente().getEntidad().getTipo(),
				expedienteGestor.getGestorExpediente().getEntidad().getCodigo(),
				expedienteGestor.getGestorExpediente().getSolicitante().getGestorExpedientesVIP(),
				expedienteGestor.getAnyo(), expedienteGestor.getNumExp());

		return expedienteGestor;
	}

	@Override
	protected String getSelect() {

		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.ANYO_054 ANYO_054");
		query.append(", t1.NUM_EXP_054 NUM_EXP_054");
		query.append(", t1.DNI_SOLICITANTE_054 DNI_SOLICITANTE_054");
		query.append(", t1.TIPO_ENTIDAD_054 TIPO_ENTIDAD_054");
		query.append(", t1.ID_ENTIDAD_054 ID_ENTIDAD_054");
		query.append(", t1.IND_VIP_054 IND_VIP_054");

		return query.toString();
	}

	@Override
	protected String getFrom() {
		return " FROM AA79B54S01 t1 ";
	}

	@Override
	protected RowMapper<Expediente> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return GestorExpedienteDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return ANYO_GESTOR_EXPEDIENTE + "|| '_' ||" + NUM_EXP_GESTOR_EXPEDIENTE;
	}

	@Override
	protected RowMapper<Expediente> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(Expediente bean, List<Object> params) {
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		return " WHERE t1.ANYO_054 = ? AND t1.NUM_EXP_054 = ?";
	}

	@Override
	protected String getWhere(Expediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder(GestorExpedienteDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ANYO_054", bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_054", bean.getNumExp(), params));
		if (bean.getGestorExpediente() != null) {
			where.append(SqlUtils.generarWhereLike("t1.DNI_SOLICITANTE_054",
					bean.getGestorExpediente().getSolicitante().getDni(), params, false));
			where.append(SqlUtils.generarWhereLike("t1.TIPO_ENTIDAD_054",
					bean.getGestorExpediente().getEntidad().getTipo(), params, false));
			where.append(SqlUtils.generarWhereIgual("t1.ID_ENTIDAD_054",
					bean.getGestorExpediente().getEntidad().getCodigo(), params));
			where.append(SqlUtils.generarWhereLike("t1.IND_VIP_054",
					bean.getGestorExpediente().getSolicitante().getGestorExpedientesVIP(), params, false));

		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(Expediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(GestorExpedienteDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ANYO_054", bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_054", bean.getNumExp(), params));
		if (bean.getGestorExpediente() != null) {
			where.append(SqlUtils.generarWhereLike("t1.DNI_SOLICITANTE_054",
					bean.getGestorExpediente().getSolicitante().getDni(), params, startsWith));
			where.append(SqlUtils.generarWhereLike("t1.TIPO_ENTIDAD_054",
					bean.getGestorExpediente().getEntidad().getTipo(), params, startsWith));
			where.append(SqlUtils.generarWhereIgual("t1.ID_ENTIDAD_054",
					bean.getGestorExpediente().getEntidad().getCodigo(), params));
			where.append(SqlUtils.generarWhereLike("t1.IND_VIP_054",
					bean.getGestorExpediente().getSolicitante().getGestorExpedientesVIP(), params, startsWith));
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	public GestorExpediente getDatosContacto(Expediente bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT t1.ANYO_054 ANYO54");
		query.append(", t1.NUM_EXP_054 NUMEXP054");
		query.append(", d1.EMAIL_031 EMAIL031");
		query.append(", d1.TELMOVIL_031 TELMOVIL031");
		query.append(", d1.TELFIJO_031 TELFIJO031 ");
		query.append("FROM AA79B54S01 t1 ");
		query.append("LEFT JOIN X54JAPI_DATOS_CONTACTO d1 ");
		query.append("ON t1.DNI_SOLICITANTE_054 = d1.DNI_031 ");
		query.append("WHERE t1.ANYO_054 = ? AND t1.NUM_EXP_054 = ? ");

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		List<GestorExpediente> gestorList = this.getJdbcTemplate().query(query.toString(), this.rwMapDatosContacto,
				params.toArray());
		return DataAccessUtils.uniqueResult(gestorList);
	}

	@Override()
	public GruposTrabajo getGrupoTrabajoGestor(Expediente bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT t2.ID_GRUPO_027 IDGRUPO");
		query.append(" FROM AA79B54S01 t1 ");
		query.append(" LEFT JOIN AA79B27S01 t2 ");
		query.append(" ON t1.ID_ENTIDAD_054 = t2.ID_ENTIDAD_027 ");
		query.append(" AND t1.TIPO_ENTIDAD_054 = t2.TIPO_ENTIDAD_027 ");
		query.append(" WHERE t1.ANYO_054 = ? AND t1.NUM_EXP_054 = ? ");

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		List<GruposTrabajo> grupoTrabajoList = this.getJdbcTemplate().query(query.toString(),
				this.rwMapGrupoTrabajoGestor, params.toArray());
		return DataAccessUtils.uniqueResult(grupoTrabajoList);
	}

	/**
	 * @param expedienteRst
	 * @return boolean
	 */
	private boolean isCodigoEntidadValido(Expediente expedienteRst) {
		return expedienteRst != null && expedienteRst.getGestorExpediente() != null
				&& expedienteRst.getGestorExpediente().getEntidad() != null
				&& expedienteRst.getGestorExpediente().getEntidad().getCodigo() != null;
	}

	@Override()
	public boolean isGestorBoe(Expediente bean) {

		Propiedad propiedad = new Propiedad();
		propiedad.setId(PropertiesConstants.COD_ENTIDAD_BOE);
		Propiedad propiedadRst = this.propiedadDao.find(propiedad);

		Expediente expedienteRst = this.find(bean);

		return propiedadRst != null && propiedadRst.getValor() != null && this.isCodigoEntidadValido(expedienteRst)
				&& propiedadRst.getValor()
						.equals(expedienteRst.getGestorExpediente().getEntidad().getCodigo().toString());
	}

	@Override()
	public GestorExpediente getDniSolicitante(ExpedienteTradRev bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT DNI_SOLICITANTE_054 DNISOLICITANTE054 ");
		query.append("FROM AA79B54T00 t1 ");
		query.append("WHERE t1.ANYO_054 = ? AND t1.NUM_EXP_054 = ? ");

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		List<GestorExpediente> gestorList = this.getJdbcTemplate().query(query.toString(), this.rwMapDni,
				params.toArray());
		return DataAccessUtils.uniqueResult(gestorList);
	}

}
