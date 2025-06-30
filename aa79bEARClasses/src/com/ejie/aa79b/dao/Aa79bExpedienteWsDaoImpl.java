package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.Aa79bBitacoraSolicitanteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bCabeceraExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bContactoDatosFacturacionRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bDatosExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bDatosFacturacionInterpretacionRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bDatosFacturacionTraduccionRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bExpedienteRelacionadoRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bExpedientesRelacionadosRowMapper;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.CabeceraExpediente;
import com.ejie.aa79b.model.EntradaDatosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoFacturaEnum;
import com.ejie.aa79b.model.enums.EstadoSubsanacionEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoPeticionarioEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bAnulacionRechazo;
import com.ejie.aa79b.model.webservices.Aa79bContacto;
import com.ejie.aa79b.model.webservices.Aa79bEntidad;
import com.ejie.aa79b.model.webservices.Aa79bEntidadContacto;
import com.ejie.aa79b.model.webservices.Aa79bEstadoFactura;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bFacturaExpediente;
import com.ejie.aa79b.model.webservices.Aa79bInformeSolicitudes;
import com.ejie.aa79b.model.webservices.Aa79bInformes;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;
import com.ejie.aa79b.model.webservices.Aa79bSolaskides;
import com.ejie.aa79b.model.webservices.Aa79bTradosExp;
import com.ejie.aa79b.utils.Aa79bExpedienteWSUtils;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

@Repository
@Transactional
public class Aa79bExpedienteWsDaoImpl extends GenericoDaoImpl<Aa79bExpediente> implements Aa79bExpedienteWsDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	private static final String T1 = ", t1.";
	private static final String T1ORIGEN_051 = ", t1.ORIGEN_051 ORIGEN";
	private static final String T1FECHA_ALTA_051 = ", t1.FECHA_ALTA_051 FECHAALTA";
	private static final String COUNT1 = " COUNT(1) ";
	private static final String ANYO_051_ANYO = " ANYO_051 ANYO";
	private static final String NUM_EXP051_NUMEXP = ", NUM_EXP_051 NUMEXP ";
	private static final String SUBSTRANYO_NUMEXPCONCATENADO = ", SUBSTR(ANYO_051,3,4) || '/' || LPAD(NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO";
	private static final String IDTIPOEXPEDIENTE051_IDTIPOEXPEDIENTE = ", ID_TIPO_EXPEDIENTE_051 IDTIPOEXPEDIENTE ";
	private static final String DECODE_ID_TIPO_EXPEDIENTE_051 = "DECODE(ID_TIPO_EXPEDIENTE_051";
	private static final String TABLA_TRAD_REV = "97";
	private static final String TABLA_INTER = "A3";
	private static final String TABLA_INICIO = "AA79B";
	private static final String TABLA_FIN = "S01";
	private static final String ANYO_INICIO = "ANYO_0";
	private static final String NUM_EXP_INICIO = "NUM_EXP_0";
	private static final String IND_REVISADO_INICIO = "IND_REVISADO_0";
	protected static final String ACTIVO_ENUM = "ActivoEnum";

	private static final Logger LOGGER = LoggerFactory.getLogger(Aa79bExpedienteWsDaoImpl.class);

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ANYO, DBConstants.NUMEXP,
			DBConstants.IDTIPOEXPEDIENTE, "ORIGEN", DBConstants.FECHAALTA, DBConstants.TITULO, "ESTADOBITACORA",
			DBConstants.DNI, DBConstants.DNIGESTOR, DBConstants.ANYONUMEXPCONCATENADO };

	public Aa79bExpedienteWsDaoImpl() {
		// Constructor
		super(Aa79bExpediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<Aa79bExpediente> rwMapExpediente = new Aa79bExpedienteRowMapper();

	private RowMapper<Aa79bExpediente> rwMapPK = new RowMapper<Aa79bExpediente>() {
		@Override
		public Aa79bExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Aa79bExpediente aa79bExpediente = new Aa79bExpediente();
			aa79bExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
			aa79bExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return aa79bExpediente;
		}
	};

	private RowMapper<Aa79bExpediente> rwMapExpedienteRelacionado = new Aa79bExpedienteRelacionadoRowMapper();

	private RowMapper<CabeceraExpediente> rwMapCabeceraExpediente = new Aa79bCabeceraExpedienteRowMapper();

	private RowMapper<BitacoraSolicitante> rwMapInfoBitacora = new Aa79bBitacoraSolicitanteRowMapper();

	private RowMapper<Aa79bExpedienteRelacionado> rwMapExpedientesRelacionados = new Aa79bExpedientesRelacionadosRowMapper();

	private RowMapper<Aa79bExpediente> rwMapDatosExpediente = new Aa79bDatosExpedienteRowMapper();

	private RowMapper<Aa79bSalidaDatosPresupuestoFacturacion> rwMapDatosFacturacionInterpretacion = new Aa79bDatosFacturacionInterpretacionRowMapper();

	private RowMapper<Aa79bEntidadContacto> rwMapContactoDatosFacturacion = new Aa79bContactoDatosFacturacionRowMapper();

	private RowMapper<Aa79bSalidaDatosPresupuestoFacturacion> rwMapDatosFacturacionTraduccion = new Aa79bDatosFacturacionTraduccionRowMapper();

	private RowMapper<Persona> rwMapTrabajadorGIP = new RowMapper<Persona>() {
		@Override
		public Persona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Persona persona = new Persona();
			persona.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
			persona.setPreDni(resultSet.getString(DBConstants.PREDNI));
			persona.setDni(resultSet.getString(DBConstants.DNI));
			persona.setSufDni(resultSet.getString(DBConstants.SUFDNI));
			persona.setNombre(resultSet.getString(DBConstants.NOMBRE));
			persona.setApellido1(resultSet.getString(DBConstants.APE1));
			persona.setApellido2(resultSet.getString(DBConstants.APE2));

			return persona;
		}
	};

	private RowMapper<Aa79bExpedienteRelacionado> rwMapExpedienteRelacionadoPK = new RowMapper<Aa79bExpedienteRelacionado>() {
		@Override
		public Aa79bExpedienteRelacionado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Aa79bExpedienteRelacionado aa79bExpedienteRelacionado = new Aa79bExpedienteRelacionado();
			aa79bExpedienteRelacionado.setAnyo(resultSet.getLong(DBConstants.ANYO));
			aa79bExpedienteRelacionado.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return aa79bExpedienteRelacionado;
		}
	};

	private RowMapper<Aa79bTradosExp> rwMapDatosTrados = new RowMapper<Aa79bTradosExp>() {
		@Override
		public Aa79bTradosExp mapRow(ResultSet resultSet, int arg1) throws SQLException {
			Aa79bTradosExp trados = new Aa79bTradosExp();
			trados.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPAL));
			trados.setNumPalConcor084090(resultSet.getInt(DBConstants.NUMPALCONCOR084));
			trados.setNumPalConcor8594090(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
			trados.setNumPalConcor95100090(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
			trados.setNumPalConcor9599090(resultSet.getInt(DBConstants.NUMPALCONCOR9599));
			trados.setNumPalConcor100090(resultSet.getInt(DBConstants.NUMPALCONCOR100));
			return trados;
		}
	};

	private RowMapper<Aa79bFacturaExpediente> rwFacturasExpediente = new RowMapper<Aa79bFacturaExpediente>() {
		@Override
		public Aa79bFacturaExpediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
			Aa79bFacturaExpediente aa79bFacturaExpediente = new Aa79bFacturaExpediente();
			aa79bFacturaExpediente.setIdFactura(resultSet.getLong("IDFACTURA"));
			aa79bFacturaExpediente.setCodConcatenado(resultSet.getLong("CODCONCATENADO"));
			final Date fechaEmision = resultSet.getTimestamp("FECHAEMISION");
			if (fechaEmision != null) {
				aa79bFacturaExpediente.setFechaEmision(fechaEmision.getTime());
			}
			Aa79bEstadoFactura aa79bEstadoFactura = new Aa79bEstadoFactura();
			aa79bEstadoFactura.setIndEstadoFactura(resultSet.getLong("ESTFACTURA"));
			aa79bEstadoFactura.setEstadoFacturaDescEs(resultSet.getString("DESCES"));
			aa79bEstadoFactura.setEstadoFacturaDescEu(resultSet.getString("DESCEU"));
			aa79bFacturaExpediente.setEstadoFactura(aa79bEstadoFactura);
			return aa79bFacturaExpediente;
		}
	};
	private RowMapper<Aa79bAnulacionRechazo> rwAnulacionRechazo = new RowMapper<Aa79bAnulacionRechazo>() {
		@Override
		public Aa79bAnulacionRechazo mapRow(ResultSet resultSet, int arg1) throws SQLException {
			Aa79bAnulacionRechazo aa79bAnulacionRechazo = new Aa79bAnulacionRechazo();
			aa79bAnulacionRechazo.setObservaciones(resultSet.getString("OBSERVACIONES"));
			aa79bAnulacionRechazo.setMotivoDescEs(resultSet.getString("DESCES"));
			aa79bAnulacionRechazo.setMotivoDescEu(resultSet.getString("DESCEU"));
			return aa79bAnulacionRechazo;
		}
	};

	private RowMapper<Aa79bSolaskides> rwMapSolaskides = new RowMapper<Aa79bSolaskides>() {
		@Override
		public Aa79bSolaskides mapRow(ResultSet resultSet, int arg1) throws SQLException {
			Aa79bSolaskides aa79bSolaskides = new Aa79bSolaskides();

			Aa79bContacto contacto = new Aa79bContacto();
			contacto.setDni(resultSet.getString("DNICOMPLETO"));
			contacto.setNombre(resultSet.getString("NOMBRE"));
			contacto.setApellido1(resultSet.getString("APEL1"));
			contacto.setApellido2(resultSet.getString("APEL2"));
			aa79bSolaskides.setContacto(contacto);

			Aa79bEntidad entidad = new Aa79bEntidad();
			entidad.setTipo(resultSet.getString("TIPO_ENTIDAD"));
			entidad.setEntidadDescAmpEs(resultSet.getString("DESC_ES"));
			entidad.setEntidadDescAmpEu(resultSet.getString("DESC_EU"));
			aa79bSolaskides.setEntidad(entidad);
			return aa79bSolaskides;
		}
	};

	private RowMapper<Aa79bInformeSolicitudes> rwMapInformeSolicitudes = new RowMapper<Aa79bInformeSolicitudes>() {
		@Override
		public Aa79bInformeSolicitudes mapRow(ResultSet resultSet, int arg1) throws SQLException {
			Aa79bInformeSolicitudes aa79bInformeSolicitudes = new Aa79bInformeSolicitudes();
			aa79bInformeSolicitudes.setNumExp(resultSet.getInt("ESP_ZK"));
			aa79bInformeSolicitudes.setAnyo(resultSet.getLong("URTEA"));
			aa79bInformeSolicitudes.setAnyoNumExp(resultSet.getString("ESPEDIENTEA"));
			aa79bInformeSolicitudes.setBopvDesc(resultSet.getString("EHAA"));
			aa79bInformeSolicitudes.setTipoExpDesc(resultSet.getString("ESPEDIENTE_MOTA"));
			aa79bInformeSolicitudes.setSolicitante(resultSet.getString("ESKATZAILEA"));
			aa79bInformeSolicitudes.setNumPalIzo(resultSet.getInt("HITZ_KOPURUA_IZO"));
			aa79bInformeSolicitudes.setTarifaPal(resultSet.getBigDecimal("TARIFA_HITZAK"));
			aa79bInformeSolicitudes.setFinalizado(resultSet.getString("AMAITUTA"));
			aa79bInformeSolicitudes.setTipoDocumento(resultSet.getString("DOCUMENTU_MOTA"));
			aa79bInformeSolicitudes.setTipoTraduccion(resultSet.getString("ITZULPEN_MOTA"));
			aa79bInformeSolicitudes.setImporteTotal(resultSet.getBigDecimal("ZENBATEKOA_BEZ_BARNE"));
			aa79bInformeSolicitudes.setDescripcion(resultSet.getString("DESKRIBAPENA"));
			aa79bInformeSolicitudes.setSolaskidea(resultSet.getString("SOLASKIDEA"));
			aa79bInformeSolicitudes.setIdioma(resultSet.getString("HIZKUNTZA"));
			aa79bInformeSolicitudes.setFechaAlta(resultSet.getTimestamp("ALTA_DATA"));
			aa79bInformeSolicitudes.setFechaFinIzo(resultSet.getTimestamp("IZO_AMAIERA_DATA"));
			aa79bInformeSolicitudes.setFechaEntregaReal(resultSet.getTimestamp("BENETAKO_ENTREGA_DATA"));
			aa79bInformeSolicitudes.setlIdFactura(resultSet.getString("LIDFAKTURA"));

			return aa79bInformeSolicitudes;
		}
	};

	@Override
	protected String getSelect() {
		StringBuilder query = new StringBuilder();
		query.append(DBConstants.SELECT).append(" t1.").append(DBConstants.ANYO_051).append(" ")
				.append(DBConstants.ANYO);
		query.append(T1).append(DBConstants.NUM_EXP_051).append(" ").append(DBConstants.NUMEXP);
		query.append(T1).append(DBConstants.ID_TIPO_EXPEDIENTE_051).append(" ").append(DBConstants.IDTIPOEXPEDIENTE);
		query.append(T1ORIGEN_051);
		query.append(T1FECHA_ALTA_051);
		query.append(T1).append(DBConstants.TITULO_051).append(" ").append(DBConstants.TITULO);
		query.append(", t1.ESTADO_BITACORA_051 ESTADOBITACORA");
		query.append(", t1.DNI_TECNICO_051 DNI");
		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM AA79B51S01 t1 ");
		return from.toString();
	}

	@Override
	protected RowMapper<Aa79bExpediente> getRwMap() {
		return this.rwMapExpediente;
	}

	@Override
	protected String[] getOrderBy() {
		return ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return DBConstants.ANYO + "," + DBConstants.NUMEXP;
	}

	@Override
	protected RowMapper<Aa79bExpediente> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(Aa79bExpediente bean, List<Object> params) {
		return "WHERE ANYO_051 = ? AND NUM_EXP_051 = ?";
	}

	@Override
	protected String getWhere(Aa79bExpediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);
		where.append(" ");
		return where.toString();
	}

	@Override
	protected String getWhereLike(Aa79bExpediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);
		return where.toString();
	}

	@Override()
	public boolean tieneAccesoExpediente(Expediente bean, String numeroTabla) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		params.add(bean.getGestorExpediente().getSolicitante().getDni());

		query.append(" (SELECT COUNT(NUM_EXP_0").append(numeroTabla).append(")");
		query.append(" FROM AA79B").append(numeroTabla).append("S01 t1");
		query.append(" WHERE t1.ANYO_0").append(numeroTabla).append("  = ?");
		query.append(" AND t1.NUM_EXP_0").append(numeroTabla).append(" = ?");
		if (Utils.esGestorCoordinador(bean.getGestorExpediente().getSolicitante())) {
			// Es gestor y coordinador
			params.add(bean.getGestorExpediente().getSolicitante().getDni());

			query.append(" AND (DNI_SOLICITANTE_0").append(numeroTabla).append(" = ? OR (TIPO_ENTIDAD_0")
					.append(numeroTabla).append(", ID_ENTIDAD_0").append(numeroTabla).append(") = ");
			query.append(" (SELECT TIPO_ENTIDAD, COD_ENTIDAD");
			query.append(" FROM X54JAPI_SOLICITANTES");
			query.append(" WHERE COORDINADOR='S'");
			query.append(" AND DNI = ?");
			query.append(" ))");
		} else if (Utils.esGestor(bean.getGestorExpediente().getSolicitante())) {
			// Es gestor
			query.append(" AND DNI_SOLICITANTE_0").append(numeroTabla).append(" = ?");
		} else if (Utils.esCoordinador(bean.getGestorExpediente().getSolicitante())) {
			// Es coordinador
			query.append(" AND (TIPO_ENTIDAD_0").append(numeroTabla).append(", ID_ENTIDAD_0").append(numeroTabla)
					.append(") = ");
			query.append(" (SELECT TIPO_ENTIDAD, COD_ENTIDAD");
			query.append(" FROM X54JAPI_SOLICITANTES");
			query.append(" WHERE COORDINADOR = 'S'");
			query.append(" AND DNI = ? )");
		}

		query.append(" )");

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class) > 0;
	}

	@Override
	public Object obtenerExpedientes(Solicitante solicitante, Aa79bExpediente bean, JQGridRequestDto jQGridRequestDto,
			Boolean startsWith, Boolean isCount) {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		List<Object> paramsOE = new ArrayList<Object>();
		StringBuilder selectOE = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQueryOE = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		this.getSelectExpedientes(isCount, es, eu, paramsOE, selectOE);

		this.getFromExpedientes(selectOE);
		this.getWhereExpedientes(paramsOE, selectOE);
		if (bean != null) {
			selectOE.append(this.getWhereObtenerExpedientes(solicitante, bean, paramsOE, startsWith));
		}
		selectOE.append(this.getGestorSolicitanteQuery(solicitante, bean, paramsOE));
		paginatedQueryOE.append(Utils.getPaginationQuery(jQGridRequestDto, isCount, selectOE));
		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQueryOE.toString(), paramsOE.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQueryOE.toString(), this.rwMapExpediente, paramsOE.toArray());
		}
	}

	private void getSelectExpedientes(Boolean isCount, Locale es, Locale eu, List<Object> paramsOE,
			StringBuilder selectOE) {
		selectOE.append("SELECT ");

		if (isCount) {
			selectOE.append(COUNT1);
		} else {
			selectOE.append(ANYO_051_ANYO);
			selectOE.append(NUM_EXP051_NUMEXP);
			selectOE.append(SUBSTRANYO_NUMEXPCONCATENADO);
			selectOE.append(IDTIPOEXPEDIENTE051_IDTIPOEXPEDIENTE);
			selectOE.append(", " + DECODE_ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue()
					+ "', '").append(this.msg.getMessage("label.interpretacionAbr", null, es))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.msg.getMessage("label.traduccionAbr", null, es))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.msg.getMessage("label.revisionAbr", null, es)).append("') AS TIPOEXPEDIENTEDESCES");
			selectOE.append(", " + DECODE_ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue()
					+ "', '").append(this.msg.getMessage("label.interpretacionAbr", null, eu))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.msg.getMessage("label.traduccionAbr", null, eu))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.msg.getMessage("label.revisionAbr", null, eu)).append("') AS TIPOEXPEDIENTEDESCEU");
			selectOE.append(", TITULO_051 ").append(DBConstants.TITULO);
			selectOE.append(", ID_ESTADO_EXP_059 IDESTADOEXP");
			selectOE.append(", ID_FASE_EXPEDIENTE_059 IDFASEEXPEDIENTE");
			selectOE.append(", OBTENER_ESTADO_SOLICITANTE(ID_ESTADO_EXP_059,ID_FASE_EXPEDIENTE_059) ESTADOFASEAA06A");
			selectOE.append(
					Utils.obtenerAa06aEstadoFaseDesc("ID_ESTADO_EXP_059", "ID_FASE_EXPEDIENTE_059", this.msg, es));
			selectOE.append(
					Utils.obtenerAa06aEstadoFaseDesc("ID_ESTADO_EXP_059", "ID_FASE_EXPEDIENTE_059", this.msg, eu));
			selectOE.append(", FECHA_ALTA_051 FECHAALTA");
			selectOE.append(", TO_CHAR(FECHA_ALTA_051,'HH24:MI') HORAALTA");
			selectOE.append(", COALESCE (NVL(FECHA_FINAL_IZO_053,FECHA_FINAL_SOLIC_053),FECHA_FIN_052) FECHAFINALIZO");
			selectOE.append(
					", TO_CHAR(COALESCE (NVL(FECHA_FINAL_IZO_053,FECHA_FINAL_SOLIC_053),FECHA_FIN_052),'HH24:MI') HORAFINALIZO");
			selectOE.append(", IND_PUBLICAR_BOPV_053 INDPUBLICARBOPV");
			selectOE.append(", DECODE(IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
					.append(ActivoEnum.NO.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PUBLICARBOPVDESCES");
			selectOE.append(", DECODE(IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
					.append(ActivoEnum.NO.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PUBLICARBOPVDESCEU");
			selectOE.append(", NVL(IND_FACTURABLE_053, '" + ActivoEnum.NO.getValue() + "') INDFACTURABLE");
			selectOE.append(", DECODE(IND_FACTURABLE_053, '").append(ActivoEnum.SI.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
					.append(ActivoEnum.NO.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS FACTURABLEDESCES");
			selectOE.append(", DECODE(IND_FACTURABLE_053, '").append(ActivoEnum.SI.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
					.append(ActivoEnum.NO.getValue()).append("', '")
					.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS FACTURABLEDESCEU");
			selectOE.append(", REF_TRAMITAGUNE_053 REF_TRAMITAGUNE");
			selectOE.append(", DNI_SOLICITANTE_054 DNIGESTOR");
			selectOE.append(", NOMBRE_077 NOMBREGESTOR");
			selectOE.append(", APEL1_077 APEL1GESTOR");
			selectOE.append(", APEL2_077 APEL2GESTOR");
			selectOE.append(", TIPO_ENTIDAD_054 TIPOENTIDAD");
			selectOE.append(", ID_ENTIDAD_054 IDENTIDAD");
			selectOE.append(", IND_VIP_054 INDVIP");
			selectOE.append(", e1.DESC_ES DESCENTIDADES");
			selectOE.append(", e1.DESC_EU DESCENTIDADEU");
			selectOE.append(", e1.DESC_AMP_ES DESCAMPENTIDADES");
			selectOE.append(", e1.DESC_AMP_EU DESCAMPENTIDADEU");
			selectOE.append(", COMPROBAR_PAGADO(ANYO_051,NUM_EXP_051) INDPAGADO");

		}
	}

	private void getWhereExpedientes(List<Object> paramsOE, StringBuilder selectOE) {
		selectOE.append(DaoConstants.WHERE_1_1);
		selectOE.append(SqlUtils.generarWhereIgual("ESTADO_BAJA_051", Constants.ALTA, paramsOE));
		selectOE.append(" AND ANYO_051 =  ANYO_054");
		selectOE.append(" AND NUM_EXP_051 = NUM_EXP_054");
		selectOE.append(" AND ANYO_051 =  ANYO_052 (+) ");
		selectOE.append(" AND NUM_EXP_051 = NUM_EXP_052 (+) ");
		selectOE.append(" AND ANYO_051 =  ANYO_053 (+) ");
		selectOE.append(" AND NUM_EXP_051 = NUM_EXP_053 (+) ");
		selectOE.append(" AND ANYO_051 =  ANYO_059");
		selectOE.append(" AND NUM_EXP_051 = NUM_EXP_059");
		selectOE.append(" AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059");
		selectOE.append(" AND DNI_SOLICITANTE_054 = DNI_077");
		selectOE.append(" AND TIPO_ENTIDAD_054 = e1.TIPO");
		selectOE.append(" AND ID_ENTIDAD_054 = e1.CODIGO");
	}

	private void getFromExpedientes(StringBuilder selectOE) {
		selectOE.append(
				" FROM AA79B51S01, AA79B54S01, AA79B52S01, AA79B53S01 , AA79B59S01 , AA79B77S01 , X54JAPI_ENTIDADES_SOLIC e1 ");
	}

	public String getWhereObtenerExpedientes(Solicitante solicitante, Aa79bExpediente bean, List<Object> params,
			boolean startsWith) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		// Estado/Fase*
		where.append(Utils.getAa79baEstadoFaseQuery(bean.getEstadoFase()));
		// tipoExpediente*
		if (bean.getTipoExpediente() != null) {
			where.append(
					SqlUtils.generarWhereIgual("ID_TIPO_EXPEDIENTE_051", bean.getTipoExpediente().getCodigo(), params));
		}
		// AÑO*
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_051,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		// NUM_EXP*
		where.append(SqlUtils.generarWhereIgual("NUM_EXP_051", bean.getNumExp(), params));
		// Titulo*

		where.append(SqlUtils.generarWhereLikeNormalizado("TITULO_051",
				bean.getTitulo() == null ? null : bean.getTitulo().trim(), params, startsWith));

		// fechas
		this.getWhereFechas(where, bean, params);
		// IndFacturable*
		where.append(SqlUtils.generarWhereIgual("IND_FACTURABLE_053", bean.getIndFacturable(), params));
		// IndPagado*
		if (bean.getIndPagado() != null) {
			where.append(
					SqlUtils.generarWhereIgual("COMPROBAR_PAGADO(ANYO_051,NUM_EXP_051)", bean.getIndPagado(), params));
		}
		// IndPublicarBOPV*
		where.append(SqlUtils.generarWhereIgual("IND_PUBLICAR_BOPV_053", bean.getIndPublicarBOPV(), params));
		if (bean.getDatosTradRev() != null) {
			// RefTramitagune*
			where.append(SqlUtils.generarWhereLike("REF_TRAMITAGUNE_053",
					bean.getDatosTradRev().getRefTramitagune().trim(), params, startsWith));
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	private void getWhereFechas(StringBuilder where, Aa79bExpediente bean, List<Object> params) {
		// FechaDesdeSolicitud*
		where.append(SqlUtils.generarWhereMayorIgualFecha("FECHA_ALTA_051", "DD/mm/YY",
				DateUtils.obtFechaHoraFormateada(bean.getFechaDesdeSolicitud(), Constants.HORA_MINUTOS_CERO), params));
		// FechaHastaSolicitud*
		where.append(SqlUtils.generarWhereMenorIgualFecha("FECHA_ALTA_051", "DD/mm/YY",
				DateUtils.obtFechaHoraFormateada(bean.getFechaHastaSolicitud(), Constants.HORA_MINUTOS_CERO), params));
		// FechaDesdeEntrega* FechaHastaEntrega*
		if (bean.getFechaDesdeEntrega() != null || bean.getFechaHastaEntrega() != null) {
			where.append(DaoConstants.AND);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(DaoConstants.IGUAL_1_1);
			where.append(SqlUtils.generarWhereMayorIgualFecha("FECHA_FIN_052", "DD/mm/YY",
					DateUtils.obtFechaHoraFormateada(bean.getFechaDesdeEntrega(), Constants.HORA_MINUTOS_CERO),
					params));
			where.append(SqlUtils.generarWhereMenorIgualFecha("FECHA_FIN_052", "DD/mm/YY",
					DateUtils.obtFechaHoraFormateada(bean.getFechaHastaEntrega(), Constants.HORA_MINUTOS_CERO),
					params));
			where.append(DaoConstants.CERRAR_PARENTESIS);
			where.append(DaoConstants.OR);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(DaoConstants.IGUAL_1_1);
			where.append(SqlUtils.generarWhereMayorIgualFecha("FECHA_FINAL_IZO_053", "DD/mm/YY",
					DateUtils.obtFechaHoraFormateada(bean.getFechaDesdeEntrega(), Constants.HORA_MINUTOS_CERO),
					params));
			where.append(SqlUtils.generarWhereMenorIgualFecha("FECHA_FINAL_IZO_053", "DD/mm/YY",
					DateUtils.obtFechaHoraFormateada(bean.getFechaHastaEntrega(), Constants.HORA_MINUTOS_CERO),
					params));
			where.append(DaoConstants.CERRAR_PARENTESIS);
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}
	}

	@Override
	public Integer eliminarExpediente(Aa79bExpediente bean) {

		StringBuilder update = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		update.append(
				" UPDATE AA79B51S01 SET FECHA_BAJA_051=?, ESTADO_BAJA_051=?, OBSV_BAJA_051=? WHERE ANYO_051=? AND NUM_EXP_051=?"
						+ " AND FECHA_BAJA_051 IS NULL AND ESTADO_BAJA_051 LIKE 'A'");

		return this.getJdbcTemplate().update(update.toString(), new Date(), Constants.BAJA, bean.getTitulo(),
				bean.getAnyo(), bean.getNumExp());

	}

	@Override
	public Object buscarExpedientesRelacionados(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jQGridRequestDto, Boolean startsWith, Boolean isCount) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder select = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);

		select.append("SELECT ");
		if (isCount) {
			select.append(COUNT1);
		} else {
			select.append(ANYO_051_ANYO);
			select.append(NUM_EXP051_NUMEXP);
			select.append(SUBSTRANYO_NUMEXPCONCATENADO);
			select.append(", TITULO_051 ").append(DBConstants.TITULO);
			select.append(", FECHA_ALTA_051 FECHAALTA");
			select.append(", TO_CHAR(FECHA_ALTA_051,'HH24:MI') HORAALTA");
			select.append(", NVL(FECHA_FINAL_IZO_053,FECHA_FINAL_SOLIC_053) FECHAFINALIZO");
			select.append(", TO_CHAR(NVL(FECHA_FINAL_IZO_053,FECHA_FINAL_SOLIC_053),'HH24:MI') HORAFINALIZO");
		}

		select.append(" FROM AA79B51S01, AA79B54S01, AA79B53S01, AA79B52S01 ");
		select.append(DaoConstants.WHERE_1_1);
		select.append(DaoConstants.AND);
		select.append(DaoConstants.ABRIR_PARENTESIS);
		select.append(" ID_TIPO_EXPEDIENTE_051 = '" + TipoExpedienteEnum.REVISION.getValue()
				+ "' OR ID_TIPO_EXPEDIENTE_051 = '" + TipoExpedienteEnum.TRADUCCION.getValue() + "'");
		select.append(DaoConstants.CERRAR_PARENTESIS);
		select.append(SqlUtils.generarWhereIgual("ESTADO_BAJA_051", Constants.ALTA, params));
		select.append(" AND ANYO_051 =  ANYO_054");
		select.append(" AND NUM_EXP_051 = NUM_EXP_054");
		select.append(" AND ANYO_051 =  ANYO_053 (+)");
		select.append(" AND NUM_EXP_051 = NUM_EXP_053 (+)");
		select.append(" AND ANYO_051 =  ANYO_052 (+)");
		select.append(" AND NUM_EXP_051 = NUM_EXP_052 (+)");
		if (bean != null) {
			select.append(this.getWhereBuscarExpedientesRelacionados(solicitante, bean, params));
		}
		if (bean.getGestor() != null && !bean.getGestor().getDniGestor().equals(solicitante.getDni())) {
			select.append(" AND DNI_SOLICITANTE_054 = ?");// dni*
			params.add(bean.getGestor().getDniGestor());
		} else {
			params.add(solicitante.getDni());
			if (Utils.esGestorCoordinador(solicitante)) {
				// Si no lo seleccionan y el usuario es gestor y coordinador
				params.add(solicitante.getDni());
				select.append(" AND (DNI_SOLICITANTE_054 = ? OR (TIPO_ENTIDAD_054,ID_ENTIDAD_054) ="
						+ "(SELECT TIPO_ENTIDAD, COD_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE coordinador='S' AND DNI=?))");
			} else if (Utils.esCoordinador(solicitante)) {
				// Si no lo seleccionan y el usuario es coordinador
				select.append(
						" AND (TIPO_ENTIDAD_054,ID_ENTIDAD_054) = (SELECT TIPO_ENTIDAD, COD_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE coordinador='S' AND DNI=?)");// DNI*
			} else if (Utils.esGestor(solicitante)) {
				// Si no lo seleccionan y el usuario es gestor
				select.append(" AND DNI_SOLICITANTE_054 = ?");// dni*
			}
		}

		paginatedQuery.append(Utils.getPaginationQuery(jQGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapExpedienteRelacionado,
					params.toArray());
		}
	}

	public String getWhereBuscarExpedientesRelacionados(Solicitante solicitante, Aa79bExpediente bean,
			List<Object> params) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		// AÑO*
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_051,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		// NUM_EXP*
		where.append(SqlUtils.generarWhereIgual("NUM_EXP_051", bean.getNumExp(), params));
		// Titulo*
		where.append(SqlUtils.generarWhereLike("TITULO_051", bean.getTitulo() == null ? null : bean.getTitulo().trim(),
				params, false));
		// fechas
		this.getWhereFechas(where, bean, params);
		// IndPublicarBOPV*
		where.append(SqlUtils.generarWhereIgual("IND_PUBLICAR_BOPV_053", bean.getIndPublicarBOPV(), params));

		if (bean.getAnyoExpRel() != null && bean.getNumExpExpRel() != null) {
			// excluir receptores ya autorizados
			params.add(bean.getAnyoExpRel());
			params.add(bean.getNumExpExpRel());
			where.append(" AND ");
			where.append(" NOT (");
			where.append(" ANYO_051 = ? ");
			where.append(" AND ");
			where.append(" NUM_EXP_051 = ?)");
		}

		return where.toString();
	}

	private Object getGestorSolicitanteQuery(Solicitante solicitante, Aa79bExpediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder();
		// Si nos seleccionan un gestor
		if (bean != null && bean.getGestor() != null) {
			// dniGestor*
			where.append(SqlUtils.generarWhereIgual("DNI_SOLICITANTE_054", bean.getGestor().getDniGestor(), params));
		} else {
			params.add(solicitante.getDni());
			if (Utils.esGestorCoordinador(solicitante)) {
				// Si no lo seleccionan y el usuario es gestor y coordinador
				params.add(solicitante.getDni());
				where.append(" AND (DNI_SOLICITANTE_054 = ? OR (TIPO_ENTIDAD_054,ID_ENTIDAD_054) ="
						+ "(SELECT TIPO_ENTIDAD, COD_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE coordinador='S' AND DNI=?))");
			} else if (Utils.esCoordinador(solicitante)) {
				// Si no lo seleccionan y el usuario es coordinador
				where.append(
						" AND (TIPO_ENTIDAD_054,ID_ENTIDAD_054) = (SELECT TIPO_ENTIDAD, COD_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE coordinador='S' AND DNI=?)");// DNI*
			} else if (Utils.esGestor(solicitante)) {
				// Si no lo seleccionan y el usuario es gestor
				where.append(" AND DNI_SOLICITANTE_054 = ?");// dni*
			}
		}
		return where.toString();
	}

	@Override
	public CabeceraExpediente findCabeceraExpediente(Expediente bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		query.append(DBConstants.SELECT).append(" t1.").append(DBConstants.ANYO_051).append(" ")
				.append(DBConstants.ANYO);
		query.append(T1).append(DBConstants.NUM_EXP_051).append(" ").append(DBConstants.NUMEXP);
		query.append(T1).append(DBConstants.ID_TIPO_EXPEDIENTE_051).append(" ").append(DBConstants.IDTIPOEXPEDIENTE);
		query.append(T1).append(DBConstants.TITULO_051).append(" ").append(DBConstants.TITULO);
		query.append(", l1.DESC_IDIOMA_ES_009 DESC_IDIOMA_ES_009");
		query.append(", l1.DESC_IDIOMA_EU_009 DESC_IDIOMA_EU_009");
		query.append(", t2.DNI_SOLICITANTE_054 DNISOLICITANTE");
		query.append(", t2.IND_VIP_054 INDVIPSOLICITANTE");
		query.append(", t2.TIPO_ENTIDAD_054 TIPOENTIDAD");
		query.append(", t2.ID_ENTIDAD_054 IDENTIDAD");
		query.append(", t3.NOMBRE_077 NOMBRESOLICITANTE");
		query.append(", t3.APEL1_077 APEL1SOLICITANTE");
		query.append(", t3.APEL2_077 APEL2SOLICITANTE");
		query.append(", t4.CIF CIFENTIDAD");
		query.append(", t4.DESC_AMP_EU DESCAMPENTIDADEU");
		query.append(", t4.DESC_AMP_ES DESCAMPENTIDADES");
		query.append(", t4.DESC_EU DESCENTIDADEU");
		query.append(", t4.DESC_ES DESCENTIDADES");
		query.append(this.getFrom());
		query.append(" LEFT JOIN AA79B53S01 t53 ON t1.ANYO_051 = t53.ANYO_053 AND t1.NUM_EXP_051 = t53.NUM_EXP_053 ");
		query.append(" LEFT JOIN AA79B09S01 l1 ON t53.ID_IDIOMA_053 = l1.ID_IDIOMA_009 ");
		// GestorExpediente
		query.append(" LEFT JOIN AA79B54S01 t2 ON t1.ANYO_051 = t2.ANYO_054 AND t1.NUM_EXP_051 = t2.NUM_EXP_054 ");
		// NombreApellidosGestor
		query.append(" LEFT JOIN AA79B77S01 t3 ON t2.DNI_SOLICITANTE_054 = t3.DNI_077 ");
		// Entidad
		query.append(
				" LEFT JOIN X54JAPI_ENTIDADES_SOLIC t4 ON t2.ID_ENTIDAD_054 = t4.CODIGO AND t2.TIPO_ENTIDAD_054 = t4.TIPO ");
		query.append(" WHERE t1.ANYO_051 = ? AND t1.NUM_EXP_051 = ?");

		List<CabeceraExpediente> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapCabeceraExpediente,
				params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public List<BitacoraSolicitante> findInfoBitacora(Expediente bean) {
		List<BitacoraSolicitante> listaBitacoraExpediente = new ArrayList<BitacoraSolicitante>();

		if (bean != null) {
			List<Object> params = new ArrayList<Object>();
			StringBuilder query = new StringBuilder();

			params.add(bean.getAnyo());
			params.add(bean.getNumExp());

			query.append("SELECT t1.ID_079 ID");
			query.append(", t1.ANYO_079 ANYO");
			query.append(", t1.NUM_EXP_079 NUMEXP");
			query.append(", t1.ID_ACCION_BITACORA_079 IDACCIONBITACORA");
			query.append(", t1.FECHA_ALTA_079 FECHAALTA");
			query.append(", TO_CHAR(t1.FECHA_ALTA_079,'HH24:MI') HORAALTA");
			query.append(", t1.USUARIO_079 USUARIO");
			query.append(", t2.DESC_EU_048 DESCEU");
			query.append(", t2.DESC_ES_048 DESCES");
			query.append(", t2.CLASS_048 CLASS");
			query.append(" FROM AA79B79S01 t1, AA79B48S01 t2");
			query.append(" WHERE t1.ID_ACCION_BITACORA_079 = t2.ID_048");
			query.append(" AND t1.ANYO_079 = ? AND t1.NUM_EXP_079 = ?");
			query.append(" ORDER BY FECHAALTA, HORAALTA,ID");

			listaBitacoraExpediente = this.getJdbcTemplate().query(query.toString(), this.rwMapInfoBitacora,
					params.toArray());
		}

		return listaBitacoraExpediente;
	}

	@Override
	public Aa79bExpediente findDatosExpediente(Expediente bean) {
		List<Object> paramsFDE = new ArrayList<Object>();
		StringBuilder queryFDE = new StringBuilder();
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		paramsFDE.add(bean.getAnyo());
		paramsFDE.add(bean.getNumExp());

		queryFDE.append(DBConstants.SELECT).append(" t1.").append(DBConstants.ANYO_051).append(" ")
				.append(DBConstants.ANYO);
		queryFDE.append(T1).append(DBConstants.NUM_EXP_051).append(" ").append(DBConstants.NUMEXP);
		queryFDE.append(T1).append(DBConstants.ID_TIPO_EXPEDIENTE_051).append(" ").append(DBConstants.IDTIPOEXPEDIENTE);
		queryFDE.append(", DECODE(t1.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, es))
				.append("') AS TIPOEXPEDIENTEDESCES");
		queryFDE.append(", DECODE(t1.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, eu))
				.append("') AS TIPOEXPEDIENTEDESCEU");
		queryFDE.append(T1).append(DBConstants.TITULO_051).append(" ").append(DBConstants.TITULO);
		queryFDE.append(", t5.ID_ESTADO_EXP_059 IDESTADOEXP");
		queryFDE.append(", t5.ID_FASE_EXPEDIENTE_059 IDFASEEXPEDIENTE");
		queryFDE.append(", OBTENER_ESTADO_SOLICITANTE(t5.ID_ESTADO_EXP_059,t5.ID_FASE_EXPEDIENTE_059) ESTADOFASEAA06A");
		queryFDE.append(
				Utils.obtenerAa06aEstadoFaseDesc("t5.ID_ESTADO_EXP_059", "t5.ID_FASE_EXPEDIENTE_059", this.msg, es));
		queryFDE.append(
				Utils.obtenerAa06aEstadoFaseDesc("t5.ID_ESTADO_EXP_059", "t5.ID_FASE_EXPEDIENTE_059", this.msg, eu));
		queryFDE.append(T1FECHA_ALTA_051);
		queryFDE.append(T1ORIGEN_051);
		// Datos interpretación
		queryFDE.append(", t2.MODO_INTERPRETACION_052 MODOINTERPRETACION");
		queryFDE.append(", t9.DESC_EU_014 MODOINTERPRETACIONDESCEU");
		queryFDE.append(", t9.DESC_ES_014 MODOINTERPRETACIONDESCES");
		queryFDE.append(", t2.TIPO_ACTO_052 TIPOACTO");
		queryFDE.append(", t11.DESC_EU_008 TIPOACTODESCEU");
		queryFDE.append(", t11.DESC_ES_008 TIPOACTODESCES");
		queryFDE.append(", t2.TIPO_PETICIONARIO_052 TIPOPETICIONARIO");
		queryFDE.append(", DECODE(t2.TIPO_PETICIONARIO_052, '" + TipoPeticionarioEnum.ADMIN_PUBLICA.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel(), null, es))
				.append("', '" + TipoPeticionarioEnum.PARTICULAR.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.PARTICULAR.getLabel(), null, es))
				.append("') AS TIPOPETICIONARIODESCES");
		queryFDE.append(", DECODE(t2.TIPO_PETICIONARIO_052, '" + TipoPeticionarioEnum.ADMIN_PUBLICA.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel(), null, eu))
				.append("', '" + TipoPeticionarioEnum.PARTICULAR.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.PARTICULAR.getLabel(), null, eu))
				.append("') AS TIPOPETICIONARIODESCEU");
		queryFDE.append(", t2.IND_PROGRAMADA_052 INDPROGRAMADA");
		queryFDE.append(", t2.FECHA_INI_052 FECHAINI");
		queryFDE.append(", t2.FECHA_FIN_052 FECHAFIN");
		queryFDE.append(", t2.DIR_NORA_052 DIRNORA");
		queryFDE.append(", t2.IND_PRESUPUESTO_052 INDPRESUPUESTOINTER");
		queryFDE.append(", t2.IND_OBSERVACIONES_052 INDOBSERVACIONESINTER");
		// Gestor expediente
		queryFDE.append(", t4.DNI_SOLICITANTE_054 DNISOLICITANTE");
		queryFDE.append(", t4.IND_VIP_054 INDVIPSOLICITANTE");
		queryFDE.append(", t4.TIPO_ENTIDAD_054 TIPOENTIDAD");
		queryFDE.append(", t4.ID_ENTIDAD_054 IDENTIDAD");
		queryFDE.append(", t6.PREDNI_077 PREDNISOLICITANTE");
		queryFDE.append(", t6.SUFDNI_077 SUFDNISOLICITANTE");
		queryFDE.append(", t6.NOMBRE_077 NOMBRESOLICITANTE");
		queryFDE.append(", t6.APEL1_077 APEL1SOLICITANTE");
		queryFDE.append(", t6.APEL2_077 APEL2SOLICITANTE");
		queryFDE.append(", t7.CIF CIFENTIDAD");
		queryFDE.append(", t7.DESC_AMP_EU DESCAMPENTIDADEU");
		queryFDE.append(", t7.DESC_AMP_ES DESCAMPENTIDADES");
		queryFDE.append(", t7.DESC_EU DESCENTIDADEU");
		queryFDE.append(", t7.DESC_ES DESCENTIDADES");
		// Representante gestor
		queryFDE.append(", t4.DNI_REPRESENTANTE_054 DNIREPRESENTANTE");
		queryFDE.append(", t8.PREDNI_077 PREDNIREPRESENTANTE");
		queryFDE.append(", t8.SUFDNI_077 SUFDNIREPRESENTANTE");
		queryFDE.append(", t8.NOMBRE_077 NOMBREREPRESENTANTE");
		queryFDE.append(", t8.APEL1_077 APEL1REPRESENTANTE");
		queryFDE.append(", t8.APEL2_077 APEL2REPRESENTANTE");
		// Datos traducción/revisión
		queryFDE.append(", t3.NUM_TOTAL_PAL_SOLIC_053 NUMTOTALPALSOLIC");

		queryFDE.append(", NVL(t3.IND_FACTURABLE_053,t2.IND_FACTURABLE_052) INDFACTURABLE");

		queryFDE.append(", t3.ID_RELEVANCIA_053 IDRELEVANCIA");
		queryFDE.append(", TO_CHAR(t3.FECHA_FINAL_IZO_053,'HH24:MI') HORAFINALIZO");

		queryFDE.append(", t3.IND_URGENTE_053 AS INDURGENTE");

		queryFDE.append(", t3.FECHA_FINAL_PROP_053 FECHAFINALPROP");
		queryFDE.append(", t3.FECHA_FINAL_SOLIC_053 FECHAFINALSOLIC");
		queryFDE.append(", TO_CHAR(t3.FECHA_FINAL_SOLIC_053,'HH24:MI') HORAFINALSOLIC");
		queryFDE.append(", t3.IND_PUBLICAR_BOPV_053 INDPUBLICARBOPV");
		queryFDE.append(", t3.IND_PRESUPUESTO_053 INDPRESUPUESTOTRADREV");
		queryFDE.append(", t3.REF_TRAMITAGUNE_053 REFTRAMITAGUNE");
		queryFDE.append(", t3.IND_CORREDACCION_053 INDCORREDACCION");
		queryFDE.append(", t3.ID_IDIOMA_053 IDIDIOMA");
		queryFDE.append(", t12.DESC_IDIOMA_EU_009 IDIOMADESCEU");
		queryFDE.append(", t12.DESC_IDIOMA_ES_009 IDIOMADESCES");
		queryFDE.append(", t3.IND_CONFIDENCIAL_053 INDCONFIDENCIAL");
		queryFDE.append(", t3.NUM_TOTAL_PAL_IZO_053 NUMTOTALPALIZO");
		queryFDE.append(", t3.FECHA_FINAL_IZO_053 FECHAFINALIZO");
		queryFDE.append(", t3.IND_OBSERVACIONES_053 INDOBSERVACIONESTRADREV");
		queryFDE.append(", t3.TEXTO_053 TEXTO53");
		queryFDE.append(", t3.TIPO_REDACCION_053 TIPOREDACCION53");
		queryFDE.append(", t3.PARTICIPANTES_053 PARTICIPANTES53");
		queryFDE.append(", t3.FECHA_ENTREGA_REAL_053 FECHAENTREGA");
		queryFDE.append(", t13.FECHA_LIMITE_064 FECHALIMITE");
		queryFDE.append(", t13.ESTADO_SUBSANACION_064 ESTADOSUBSANACION");
		queryFDE.append(", t13.IND_SUBSANADO_064 INDSUBSANADO");
		// Fichero Observaciones
		queryFDE.append(", t14.OBSERVACIONES_055 OBSERVACIONES55");
		queryFDE.append(", t14.OID_FICHERO_055 OIDFICHERO55");
		queryFDE.append(", t14.EXTENSION_DOC_055 EXTENSIONDOC55");
		queryFDE.append(", t14.CONTENT_TYPE_055 CONTENTTYPE55");
		queryFDE.append(", t14.NOMBRE_FICHERO_055 NOMBREFICHERO55");
		queryFDE.append(", NVL(t5.ID_REQUERIMIENTO_059," + Constants.MINUS_UNO + ") IDREQUERIMIENTO");
		queryFDE.append(", NVL(t15.TIPO_REQUERIMIENTO_064," + Constants.MINUS_UNO + ") TIPOREQUERIMIENTO");

		queryFDE.append(this.getFrom());
		// Datos Interpretación
		queryFDE.append(" LEFT JOIN AA79B52S01 t2 ON t1.ANYO_051 = t2.ANYO_052 AND t1.NUM_EXP_051 = t2.NUM_EXP_052 ");
		// Datos Traducción/Revisión
		queryFDE.append(" LEFT JOIN AA79B53S01 t3 ON t1.ANYO_051 = t3.ANYO_053 AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		// Gestor Expediente
		queryFDE.append(" LEFT JOIN AA79B54S01 t4 ON t1.ANYO_051 = t4.ANYO_054 AND t1.NUM_EXP_051 = t4.NUM_EXP_054 ");
		// Datos subsanación
		queryFDE.append(" LEFT JOIN AA79B59S01 t5 ON t1.ANYO_051 = t5.ANYO_059 AND t1.NUM_EXP_051 = t5.NUM_EXP_059 ");
		queryFDE.append(" AND t1.ESTADO_BITACORA_051 = t5.ID_ESTADO_BITACORA_059");
		// NombreApellidosGestor
		queryFDE.append(" LEFT JOIN AA79B77S01 t6 ON t4.DNI_SOLICITANTE_054 = t6.DNI_077 ");
		// Entidad
		queryFDE.append(
				" LEFT JOIN X54JAPI_ENTIDADES_SOLIC t7 ON t4.ID_ENTIDAD_054 = t7.CODIGO AND t4.TIPO_ENTIDAD_054 = t7.TIPO ");
		// NombreApellidosRepresentante
		queryFDE.append(" LEFT JOIN AA79B77S01 t8 ON t4.DNI_REPRESENTANTE_054 = t8.DNI_077 ");
		// Modo interpretación
		queryFDE.append(" LEFT JOIN AA79B14S01 t9 ON t2.MODO_INTERPRETACION_052 = t9.ID_014 ");
		// Tipos interpretación
		queryFDE.append(" LEFT JOIN AA79B08S01 t11 ON t2.TIPO_ACTO_052 = t11.ID_008 ");
		// Idioma
		queryFDE.append(" LEFT JOIN AA79B09S01 t12 ON t3.ID_IDIOMA_053 = t12.ID_IDIOMA_009 ");
		// Fecha y hora límite para el requerimiento de tramitación
		queryFDE.append(" LEFT JOIN AA79B64S01 t13 ON t5.ID_REQUERIMIENTO_059 = t13.ID_064 ");
		// Fichero observaciones
		queryFDE.append(
				" LEFT JOIN AA79B55S01 t14 ON t1.ANYO_051 = t14.ANYO_055 AND t1.NUM_EXP_051 = t14.NUM_EXP_055 ");
		queryFDE.append(" LEFT JOIN AA79B64S01 t15 ON t5.ID_REQUERIMIENTO_059 = t15.ID_064 ");
		queryFDE.append(" WHERE t1.ANYO_051 = ? AND t1.NUM_EXP_051 = ?");

		List<Aa79bExpediente> beanList = this.getJdbcTemplate().query(queryFDE.toString(), this.rwMapDatosExpediente,
				paramsFDE.toArray());

		Aa79bExpediente exp = DataAccessUtils.uniqueResult(beanList);

		// determinar si el expediente ha requerido presupuesto y este está ya
		// aceptado
		exp.setPresupuestoAceptado(this.comprobarPresupuestoReqYAceptado(exp));

		if (!TipoExpedienteEnum.INTERPRETACION.getValue().equals(exp.getTipoExpediente().getCodigo())) {
			// tradRev
			exp.getDatosTradRev().setEnPlazoParaLanzarNoConformidad(this.comprobarPlazoNoConformidad(exp));

			exp.getDatosTradRev().setDatosTrados(this.comprobarReqAnalisisTrados(exp));
			// recuperar los motivos y obsv de anulación o rechazo del exp
			if (EstadoExpedienteEnum.ANULADO.getValue() == exp.getEstadoFaseExpediente().getIdEstado()
					|| EstadoExpedienteEnum.RECHAZADO.getValue() == exp.getEstadoFaseExpediente().getIdEstado()) {
				exp.setAnulacionRechazo(this.recuperarRechazoAnulacion(exp));
			}
			exp.setTieneDatosFacturacion(this.comprobarFacturacionExpediente(exp, TABLA_TRAD_REV));
		} else {
			// interpretacion
			if (EstadoExpedienteEnum.ANULADO.getValue() == exp.getEstadoFaseExpediente().getIdEstado()
					|| EstadoExpedienteEnum.RECHAZADO.getValue() == exp.getEstadoFaseExpediente().getIdEstado()) {
				exp.setAnulacionRechazo(this.recuperarRechazoAnulacion(exp));
			}
			exp.setTieneDatosFacturacion(this.comprobarFacturacionExpediente(exp, TABLA_INTER));
		}

		// recuperar las facturas del expediente
		exp.setFacturaExpediente(this.recuperarFacturasExpediente(exp));

		return exp;
	}

	private String comprobarFacturacionExpediente(Aa79bExpediente exp, String tabla) {
		List<Object> paramsCFE = new ArrayList<Object>();
		StringBuilder queryCFE = new StringBuilder();
		String bPresupuesto = ActivoEnum.NO.getValue();
		queryCFE.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + TABLA_INICIO + tabla + TABLA_FIN
				+ DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		queryCFE.append(DaoConstants.WHERE_1_1);
		queryCFE.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + ANYO_INICIO + tabla, exp.getAnyo(),
				paramsCFE));
		queryCFE.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + NUM_EXP_INICIO + tabla,
				exp.getNumExp(), paramsCFE));
		queryCFE.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + IND_REVISADO_INICIO + tabla,
				ActivoEnum.SI.getValue(), paramsCFE, false));
		Integer iResul = this.getJdbcTemplate().queryForObject(queryCFE.toString(), paramsCFE.toArray(), Integer.class);
		if (iResul > Constants.CERO) {
			bPresupuesto = ActivoEnum.SI.getValue();
		}
		return bPresupuesto;
	}

	private List<Aa79bFacturaExpediente> recuperarFacturasExpediente(Aa79bExpediente exp) {
		List<Object> paramsFact = new ArrayList<Object>();
		StringBuilder queryFact = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		List<Aa79bFacturaExpediente> listaFacturas;

		queryFact.append(DaoConstants.SELECT
				+ " T12_FECHA_EMISION FECHAEMISION, T12_EST_FACTURA ESTFACTURA, ID_FACTURA_0A4 IDFACTURA, T00_DESC_ES DESCES, T00_DESC_EU DESCEU, "
				+ " CASE WHEN T12_EJ_CONTABLE is null THEN '9999999999' ELSE SUBSTR(T12_EJ_CONTABLE,3,2) || LPAD(APP_ID_0A4,2,'0') || SUBSTR(ID_LIQUIDACION_0A4,3,6) END CODCONCATENADO "
				+ DaoConstants.FROM + " AA79B58S01 ");
		queryFact.append(
				" LEFT JOIN AA79BA4T00 ON TIPO_ENTIDAD_ASOC_0A4 = TIPO_ENTIDAD_ASOC_058 AND ID_ENTIDAD_ASOC_0A4 = ID_ENTIDAD_ASOC_058 AND DNI_CONTACTO_0A4 = DNI_CONTACTO_058 ");
		queryFact.append(
				" JOIN AA79BA5T00 ON ID_FACTURA_0A4 = ID_FACTURA_0A5 AND ANYO_058 = ANYO_0A5 AND NUM_EXP_058 = NUM_EXP_0A5 ");
		queryFact.append(
				" LEFT JOIN W0512S01 ON ID_LIQUIDACION_0A4 = T12_REFERENCIA AND T12_EST_FACTURA NOT IN ( ?, ?, ? ) ");

		queryFact.append(" JOIN W05B_ESTADOS_FACTURA ON T12_EST_FACTURA = T00_REG_ID ");
		queryFact.append(DaoConstants.WHERE + " ANYO_058 = ? AND NUM_EXP_058 = ?");
		paramsFact.add(EstadoFacturaEnum.ANULADA.getValue());
		paramsFact.add(EstadoFacturaEnum.DEVOLUCION_INGRESO.getValue());
		paramsFact.add(EstadoFacturaEnum.ERRONEO.getValue());
		paramsFact.add(exp.getAnyo());
		paramsFact.add(exp.getNumExp());

		listaFacturas = this.getJdbcTemplate().query(queryFact.toString(), this.rwFacturasExpediente,
				paramsFact.toArray());

		return listaFacturas;
	}

	/**
	 * Devuelve el motivo y observaciones de la anulacion o el rechazo del exp
	 *
	 * @param exp
	 * @return
	 */
	private Aa79bAnulacionRechazo recuperarRechazoAnulacion(Aa79bExpediente exp) {
		List<Object> paramsAnul = new ArrayList<Object>();
		StringBuilder queryAnul = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);

		queryAnul.append(DaoConstants.SELECT
				+ " NVL(OBSV_RECHAZO_068,OBSV_ANULACION_0A2) OBSERVACIONES, NVL(DESC_ES_013,DESC_ES_012) DESCES, NVL(DESC_EU_013,DESC_EU_012) DESCEU "
				+ DaoConstants.FROM + " AA79B59S01 ");
		queryAnul.append(" LEFT JOIN AA79B67S01 ON ID_067 = ID_RECHAZO_059 ");
		queryAnul.append(" LEFT JOIN AA79B13S01 ON ID_013 = ID_MOTIVO_RECHAZO_067 ");
		queryAnul.append(" LEFT JOIN AA79B68S01 ON ID_068 = ID_RECHAZO_059 ");

		queryAnul.append(" LEFT JOIN AA79BA1S01 ON ID_0A1 = ID_ANULACION_059 ");
		queryAnul.append(" LEFT JOIN AA79B12S01 ON ID_012 = ID_MOTIVO_ANULACION_0A1 ");
		queryAnul.append(" LEFT JOIN AA79BA2S01 ON ID_0A2 = ID_ANULACION_059 ");

		queryAnul.append(DaoConstants.WHERE
				+ " ANYO_059 = ? AND NUM_EXP_059 = ? AND (ID_RECHAZO_059 IS NOT NULL OR ID_ANULACION_059 IS NOT NULL)");

		paramsAnul.add(exp.getAnyo());
		paramsAnul.add(exp.getNumExp());

		List<Aa79bAnulacionRechazo> listaAnulacionRechazo = this.getJdbcTemplate().query(queryAnul.toString(),
				paramsAnul.toArray(), this.rwAnulacionRechazo);
		return DataAccessUtils.uniqueResult(listaAnulacionRechazo);

	}

	private String comprobarPresupuestoReqYAceptado(Aa79bExpediente exp) {
		List<Object> paramsCPRA = new ArrayList<Object>();
		StringBuilder queryCPRA = new StringBuilder();
		String bPresupuesto = ActivoEnum.NO.getValue();
		queryCPRA.append(" SELECT COUNT(1) ");
		queryCPRA.append(" FROM AA79B64S01 t1 ");
		queryCPRA.append(" JOIN AA79B81S01 t2 ");
		queryCPRA.append(" ON t1.ID_064 = t2.ID_REQUERIMIENTO_081 ");
		queryCPRA.append(" AND t2.ID_TIPO_TAREA_081 IN ( " + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue()
				+ " , " + TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue() + " ) ");
		queryCPRA.append(" AND t2.ESTADO_EJECUCION_081 = " + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		queryCPRA.append(DaoConstants.WHERE_1_1);
		queryCPRA.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.IND_SUBSANADO_064,
				ActivoEnum.SI.getValue(), paramsCPRA, false));
		queryCPRA
				.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_SUBSANACION_064,
						EstadoSubsanacionEnum.ACEPTADO.getValue(), paramsCPRA));
		queryCPRA.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO_REQUERIMIENTO_064
				+ DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
				+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() + DaoConstants.SIGNO_COMA
				+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() + DaoConstants.CERRAR_PARENTESIS);
		queryCPRA.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_081,
				exp.getAnyo(), paramsCPRA));
		queryCPRA.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081,
				exp.getNumExp(), paramsCPRA));
		Integer iResul = this.getJdbcTemplate().queryForObject(queryCPRA.toString(), paramsCPRA.toArray(),
				Integer.class);
		if (iResul > Constants.CERO) {
			bPresupuesto = ActivoEnum.SI.getValue();
		}
		return bPresupuesto;
	}

	/**
	 * comprobaremos si se esta en plazo para lanzar una no conformidad
	 *
	 * @param bean Aa79bExpediente
	 * @return Integer
	 */
	private Integer comprobarPlazoNoConformidad(Aa79bExpediente bean) {
		Integer iEnPlazo = -1;
		if (this.hayQueComprobarPlazoNoConformidad(bean)) {
			// Recuperamos el plazo para lanzar una no conformidad
			List<Object> params = new ArrayList<Object>();
			StringBuilder query = new StringBuilder();
			query.append(DaoConstants.SELECT_COUNT);
			query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " t1 ");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_82 + " t2 ");
			query.append(DaoConstants.ON + " t2.ID_TAREA_082 " + DaoConstants.SIGNOIGUAL + " t1.ID_TAREA_081 ");
			query.append(DaoConstants.WHERE + " t1.ID_TIPO_TAREA_081 " + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
					+ TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue() + DaoConstants.SIGNO_COMA
					+ TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue() + DaoConstants.SIGNO_COMA
					+ TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue()
					+ DaoConstants.CERRAR_PARENTESIS);
			query.append(DaoConstants.AND + " t1.ANYO_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + " t1.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + "( t1.ESTADO_EJECUCION_081 " + DaoConstants.SIGNO_DISTINTO_INTERROGACION
					+ DaoConstants.OR + " t2.FECHA_EJECUCION_082 " + DaoConstants.SIGNO_SUMA);
			query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT + " t3.DIAS_NO_CONFORMIDAD_004 ");
			query.append(DaoConstants.FROM + DBConstants.TABLA_04 + " t3 ");
			query.append(DaoConstants.WHERE + " t3.ID_004 " + DaoConstants.SIGNOIGUAL + Constants.CERO
					+ DaoConstants.CERRAR_PARENTESIS);
			query.append(DaoConstants.SIGNO_MENOR_QUE + DaoConstants.SYSDATE);
			query.append(
					DaoConstants.OR + Constants.CERO + DaoConstants.SIGNO_MENOR_QUE + DaoConstants.ABRIR_PARENTESIS);
			query.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.TABLA_81 + " t4 ");
			query.append(DaoConstants.WHERE + " t4.ANYO_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + " t4.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + " t4.ID_TIPO_TAREA_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + " t4.ESTADO_EJECUCION_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION
					+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS);
			params.add(bean.getAnyo());
			params.add(bean.getNumExp());
			params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			params.add(bean.getAnyo());
			params.add(bean.getNumExp());
			params.add(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue());
			params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());

			iEnPlazo = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
		}

		return iEnPlazo;

	}

	private Boolean hayQueComprobarPlazoNoConformidad(Aa79bExpediente bean) {
		return (this.comprobarTipoExpedientePlazoNoConformidad(bean)
				&& comprobarEstadoExpedientePlazoNoConformidad(bean));
	}

	private Boolean comprobarTipoExpedientePlazoNoConformidad(Aa79bExpediente bean) {
		return (TipoExpedienteEnum.REVISION.getValue().equals(bean.getTipoExpediente().getCodigo())
				|| (TipoExpedienteEnum.TRADUCCION.getValue().equals(bean.getTipoExpediente().getCodigo())
						&& Constants.NO.equals(bean.getDatosTradRev().getIndCorredaccion())));
	}

	private Boolean comprobarEstadoExpedientePlazoNoConformidad(Aa79bExpediente bean) {
		return (EstadoExpedienteEnum.CERRADO.getValue() == bean.getEstadoFaseExpediente().getIdEstado()
				|| EstadoExpedienteEnum.FINALIZADO.getValue() == bean.getEstadoFaseExpediente().getIdEstado());
	}

	private Aa79bTradosExp comprobarReqAnalisisTrados(Aa79bExpediente bean) {
		if (TipoExpedienteEnum.REVISION.getValue().equals(bean.getTipoExpediente().getCodigo())
				|| TipoExpedienteEnum.TRADUCCION.getValue().equals(bean.getTipoExpediente().getCodigo())) {
			Integer numPalConfigServicio = this.obtenerNumeroDePalabrasConfiguradoDelServicio();
			if (bean.getDatosTradRev().getNumTotalPalIzo() > numPalConfigServicio) {
				return this.recuperarInfoTrados(bean);
			}
		}
		return null;
	}

	private Aa79bTradosExp recuperarInfoTrados(Aa79bExpediente bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(
				DaoConstants.SELECT + " t2.NUM_TOTAL_PAL_090 " + DBConstants.NUMTOTALPAL + DaoConstants.SIGNO_COMA);
		query.append(" t2.NUM_PAL_CONCOR_0_84_090 " + DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
		query.append(" t2.NUM_PAL_CONCOR_85_94_090 " + DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
		query.append(" t2.NUM_PAL_CONCOR_95_100_090 " + DBConstants.NUMPALCONCOR95100 + DaoConstants.SIGNO_COMA);
		query.append(" t2.NUM_PAL_CONCOR_95_99_090 " + DBConstants.NUMPALCONCOR9599 + DaoConstants.SIGNO_COMA);
		query.append(" t2.NUM_PAL_CONCOR_100_090 " + DBConstants.NUMPALCONCOR100);
		query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_90 + " t2 ");
		query.append(DaoConstants.ON + " t2.ID_TAREA_090 " + DaoConstants.SIGNOIGUAL + " t1.ID_TAREA_081 ");
		query.append(DaoConstants.WHERE + " t1.ID_TIPO_TAREA_081 " + DaoConstants.SIGNOIGUAL
				+ TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		query.append(DaoConstants.AND + " t1.ESTADO_EJECUCION_081 " + DaoConstants.SIGNOIGUAL
				+ EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		query.append(DaoConstants.AND + " t1.ANYO_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + " t1.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		List<Aa79bTradosExp> beanList = this.getJdbcTemplate().query(query.toString(), params.toArray(),
				this.rwMapDatosTrados);
		return DataAccessUtils.uniqueResult(beanList);

	}

	private Integer obtenerNumeroDePalabrasConfiguradoDelServicio() {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT + " t1.PAL_PRESUPUESTO_003 " + DaoConstants.FROM + DBConstants.TABLA_03 // cambiado
																													// por
																													// palPresupuesto
				+ " t1 ");
		query.append(DaoConstants.WHERE + " t1.ID_003 " + DaoConstants.SIGNOIGUAL + Constants.CERO);
		return this.getJdbcTemplate().queryForObject(query.toString(), Integer.class);
	}

	@Override
	public Object findExpedientesRelacionados(Aa79bExpediente bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		StringBuilder paginatedQuery = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		query.append(DBConstants.SELECT);
		if (isCount) {
			query.append(COUNT1);
		} else {

			query.append(" t1.").append(DBConstants.ANYO_051).append(" ").append(DBConstants.ANYO);
			query.append(T1).append(DBConstants.NUM_EXP_051).append(" ").append(DBConstants.NUMEXP);
			query.append(", SUBSTR(").append(DBConstants.ANYO_051).append(",3,4) || '/' || LPAD(")
					.append(DBConstants.NUM_EXP_051).append(",6,'0')").append(DBConstants.ANYONUMEXPCONCATENADO);
			query.append(T1).append(DBConstants.ID_TIPO_EXPEDIENTE_051).append(" ")
					.append(DBConstants.IDTIPOEXPEDIENTE);
			query.append(
					", DECODE(t1.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
					.append(this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, es))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, es))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, es))
					.append("') AS TIPOEXPEDIENTEDESCES");
			query.append(
					", DECODE(t1.ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
					.append(this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, eu))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, eu))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, eu))
					.append("') AS TIPOEXPEDIENTEDESCEU");
			query.append(T1).append(DBConstants.TITULO_051).append(" ").append(DBConstants.TITULO);
			query.append(T1FECHA_ALTA_051);
			query.append(", NVL(t2.FECHA_FINAL_IZO_053,t2.FECHA_FINAL_SOLIC_053) FECHAFINALIZO");
		}

		query.append(this.getFrom());

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		// Expediente Traducción/Revisión
		query.append(" LEFT JOIN AA79B53S01 t2 ON t1.ANYO_051 = t2.ANYO_053 AND t1.NUM_EXP_051 = t2.NUM_EXP_053 ");
		// Expedientes relacionados
		query.append(
				" LEFT JOIN AA79B57S01 t3 ON t1.ANYO_051 = t3.ANYO_EXP_REL_057 AND t1.NUM_EXP_051 = t3.NUM_EXP_REL_057 ");
		query.append(" WHERE t3.ANYO_057 = ? AND t3.NUM_EXP_057 = ? ");
		if (bean.getGestor() != null) {
			query.append("AND t1.NUM_EXP_051 IN ");
			query.append("(SELECT x1.NUM_EXP_054");
			query.append(" FROM AA79B54S01 x1 ");
			query.append("WHERE x1.DNI_SOLICITANTE_054 = ?)");
			params.add(bean.getGestor().getDniGestor());
		}
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));
		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapExpedientesRelacionados,
					params.toArray());
		}
	}

	/**
	 *
	 */
	@Override
	public List<TableRowDto<Aa79bExpedienteRelacionado>> reorderSelectionFindExpedientesRelacionados(
			JQGridRequestDto jQGridRequestDto, boolean startsWith) {
		List<Object> paramsRSFER = new ArrayList<Object>();
		StringBuilder queryRSFER = new StringBuilder();
		queryRSFER.append(" select " + this.getPK() + ", page, pageLine, tableLine from" + " ( select " + this.getPK()
				+ " , ceil(rownum/" + jQGridRequestDto.getRows() + ") page, case when (mod(rownum,"
				+ jQGridRequestDto.getRows() + ")=0) " + "then '" + jQGridRequestDto.getRows()
				+ "' else TO_CHAR(mod(rownum," + jQGridRequestDto.getRows()
				+ ")) end as pageLine, rownum as tableLine from (");
		queryRSFER.append(this.getFrom());
		queryRSFER.append(")" + DaoConstants.WHERE_1_1);
		List<String> listSelectedIds = jQGridRequestDto.getMultiselection().getSelectedIds();
		for (int i = 0; i < listSelectedIds.size(); i++) {
			if (i >= 1) {
				queryRSFER.append(DaoConstants.OR + " ");
			}
			if (i == 0) {
				queryRSFER.append(DaoConstants.AND + " ");
			}
			String idCombinada = listSelectedIds.get(i);
			String[] parts = idCombinada.split(",");
			queryRSFER.append("ANYO_051 = ? and NUM_EXP_051 = ? ");
			paramsRSFER.add(Long.parseLong(parts[0], 10));
			paramsRSFER.add(Integer.parseInt(parts[1]));
		}
		queryRSFER.append(")");

		RowNumResultSetExtractor<Aa79bExpedienteRelacionado> rowNumOrder = new RowNumResultSetExtractor<Aa79bExpedienteRelacionado>(
				this.rwMapExpedienteRelacionadoPK, jQGridRequestDto);

		return this.getJdbcTemplate().query(queryRSFER.toString(), rowNumOrder, paramsRSFER.toArray());
	}

	@Override
	public List<Aa79bExpedienteRelacionado> obtenerExpedientesSeleccionados(Solicitante solicitante,
			String selectedIds) {
		List<Aa79bExpediente> listaExp = new ArrayList<Aa79bExpediente>();
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		if (selectedIds != null && !"".equals(selectedIds)) {
			String[] aIds = selectedIds.split("-");
			Aa79bExpediente aa79bExpedienteAux;
			for (String idExpediente : aIds) {
				String[] id = idExpediente.split(",");
				aa79bExpedienteAux = new Aa79bExpediente();
				aa79bExpedienteAux.setAnyo(Long.parseLong(id[0]));
				aa79bExpedienteAux.setNumExp(Integer.parseInt(id[1]));
				listaExp.add(aa79bExpedienteAux);
			}
		}
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(DBConstants.SELECT).append(" DISTINCT ");
		query.append(DBConstants.ANYO_051).append(" ").append(DBConstants.ANYO);
		query.append(", ").append(DBConstants.NUM_EXP_051).append(" ").append(DBConstants.NUMEXP);
		query.append(SUBSTRANYO_NUMEXPCONCATENADO);
		query.append(", ").append(DBConstants.TITULO_051).append(" ").append(DBConstants.TITULO);
		query.append(", ").append(" FECHA_ALTA_051").append(" ").append(DBConstants.FECHAALTA);
		query.append(", ").append(" t3.FECHA_FINAL_IZO_053").append(" ").append(DBConstants.FECHAFINALIZO);
		query.append(", ").append(DBConstants.ID_TIPO_EXPEDIENTE_051).append(" ").append(DBConstants.IDTIPOEXPEDIENTE);
		query.append(
				", " + DECODE_ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, es))
				.append("') AS TIPOEXPEDIENTEDESCES");
		query.append(
				", " + DECODE_ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, eu))
				.append("') AS TIPOEXPEDIENTEDESCEU");
		query.append(" FROM AA79B51S01, AA79B53S01 t3, AA79B54S01 ");
		query.append(DaoConstants.WHERE);
		query.append(" ANYO_051 = t3.ANYO_053 (+) ");
		query.append(DaoConstants.AND);
		query.append(" NUM_EXP_051 = t3.NUM_EXP_053 (+) ");
		if (listaExp != null && !listaExp.isEmpty()) {
			query.append(DaoConstants.AND);
			query.append(DaoConstants.ABRIR_PARENTESIS);
			for (Aa79bExpediente expediente : listaExp) {
				query.append(DaoConstants.ABRIR_PARENTESIS);
				query.append(DaoConstants.IGUAL_1_1);
				query.append(SqlUtils.generarWhereIgual(DBConstants.ANYO_051, expediente.getAnyo(), params));
				query.append(SqlUtils.generarWhereIgual(DBConstants.NUM_EXP_051, expediente.getNumExp(), params));
				query.append(DaoConstants.CERRAR_PARENTESIS);
				query.append(DaoConstants.OR);
			}
			query.delete(query.length() - 3, query.length());
			query.append(DaoConstants.CERRAR_PARENTESIS);
		}
		query.append(this.getGestorSolicitanteQuery(solicitante, null, params));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapExpedientesRelacionados, params.toArray());
	}

	@Override
	public List<TableRowDto<Aa79bExpediente>> reorderBuscarSelectionExpedientesRelacionados(String dni,
			Aa79bExpediente bean, JQGridRequestDto jQGridRequestDto, boolean b) {
		// SELECT
		List<Object> params = new ArrayList<Object>();
		StringBuilder subQuery = new StringBuilder(" SELECT ");
		subQuery.append(ANYO_051_ANYO);
		subQuery.append(NUM_EXP051_NUMEXP);
		subQuery.append(SUBSTRANYO_NUMEXPCONCATENADO);
		subQuery.append(", TITULO_051 ").append(DBConstants.TITULO);
		subQuery.append(", FECHA_ALTA_051 FECHAALTA");
		subQuery.append(", TO_CHAR(FECHA_ALTA_051,'HH24:MI') HORAALTA");
		subQuery.append(", FECHA_FINAL_IZO_053 FECHAFINALIZO");
		subQuery.append(", TO_CHAR(FECHA_FINAL_IZO_053,'HH24:MI') HORAFINALIZO");
		// FROM
		subQuery.append(" FROM AA79B51S01, AA79B54S01, AA79B52S01, AA79B53S01");
		// FILTRADO
		subQuery.append(DaoConstants.WHERE_1_1);
		subQuery.append(SqlUtils.generarWhereIgual("ESTADO_BAJA_051", Constants.ALTA, params));
		subQuery.append(" AND ANYO_051 =  ANYO_054");
		subQuery.append(" AND NUM_EXP_051 = NUM_EXP_054");
		subQuery.append(" AND ANYO_051 =  ANYO_052 (+)");
		subQuery.append(" AND NUM_EXP_051 = NUM_EXP_052 (+)");
		subQuery.append(" AND ANYO_051 =  ANYO_053 (+)");
		subQuery.append(" AND NUM_EXP_051 = NUM_EXP_053 (+)");
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(dni);
		if (bean != null) {
			subQuery.append(this.getWhereBuscarExpedientesRelacionados(solicitante, bean, params));
		}

		subQuery.append(this.getGestorSolicitanteQuery(solicitante, bean, params));
		StringBuilder queryRBSER = new StringBuilder();
		queryRBSER.append(" select " + this.getPK() + ", page, pageLine, tableLine from" + " ( select " + this.getPK()
				+ " , ceil(rownum/" + jQGridRequestDto.getRows() + ") page, case when (mod(rownum,"
				+ jQGridRequestDto.getRows() + ")=0) " + "then '" + jQGridRequestDto.getRows()
				+ "' else TO_CHAR(mod(rownum," + jQGridRequestDto.getRows()
				+ ")) end as pageLine, rownum as tableLine from (");
		queryRBSER.append(subQuery.toString());
		queryRBSER.append(")" + DaoConstants.WHERE_1_1);
		List<String> listSelectedIds = jQGridRequestDto.getMultiselection().getSelectedIds();
		for (int i = 0; i < listSelectedIds.size(); i++) {
			if (i >= 1) {
				queryRBSER.append(DaoConstants.OR + " ");
			}
			if (i == 0) {
				queryRBSER.append(DaoConstants.AND + " ");
			}
			String idCombinada = listSelectedIds.get(i);
			String[] parts = idCombinada.split(",");
			queryRBSER.append("ANYO_051 = ? and NUM_EXP_051 = ? ");
			params.add(Long.parseLong(parts[0], 10));
			params.add(Integer.parseInt(parts[1]));
		}
		queryRBSER.append(")");

		RowNumResultSetExtractor<Aa79bExpediente> rowNumOrder = new RowNumResultSetExtractor<Aa79bExpediente>(
				this.getRwMapPK(), jQGridRequestDto);

		return this.getJdbcTemplate().query(queryRBSER.toString(), rowNumOrder, params.toArray());
	}

	@Override
	public Persona findTrabajadorGIP(Persona persona) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		params.add(persona.getDni());

		query.append("SELECT TIPIDEN");
		query.append(", PREDNI");
		query.append(", DNI");
		query.append(", SUFDNI");
		query.append(", NOMBRE");
		query.append(", APE1");
		query.append(", APE2");
		query.append(" FROM X54JAPI_TRABAJADORES_GIP");
		query.append(" WHERE DNI = ?");

		List<Persona> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTrabajadorGIP,
				params.toArray());

		return DataAccessUtils.uniqueResult(beanList);

	}

	@Override
	public Integer guardarPersona(Persona persona) {
		StringBuilder query = new StringBuilder();
		query.append(
				"INSERT INTO AA79B77S01 (DNI_077, TIPIDEN_077 , PREDNI_077, SUFDNI_077 , NOMBRE_077 , APEL1_077 , APEL2_077 ) "
						+ "VALUES (?,?,?,?,?,?,?)");
		return this.getJdbcTemplate().update(query.toString(), persona.getDni(), persona.getTipIden(),
				persona.getPreDni(), persona.getSufDni(), persona.getNombre(), persona.getApellido1(),
				persona.getApellido2());
	}

	@Override
	public Object consultarExpReceptores(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, Boolean isCount) {
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		Locale eu = new Locale(Constants.LANG_EUSKERA);

		List<Object> paramsCER = new ArrayList<Object>();
		StringBuilder selectCER = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQueryOE = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		this.getSelectExpedientes(isCount, es, eu, paramsCER, selectCER);
		this.getFromExpedientes(selectCER);
		selectCER.append(
				DaoConstants.SIGNO_COMA + DBConstants.TABLA_63 + DaoConstants.BLANK + DaoConstants.R1_MINUSCULA);
		this.getWhereExpedientes(paramsCER, selectCER);
		selectCER.append(DaoConstants.AND + DaoConstants.R1_MINUSCULA_PUNTO + DBConstants.ANYO_063
				+ DaoConstants.SIGNOIGUAL + DBConstants.ANYO_051);
		selectCER.append(DaoConstants.AND + DaoConstants.R1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_063
				+ DaoConstants.SIGNOIGUAL + DBConstants.NUM_EXP_051);
		selectCER.append(SqlUtils.generarWhereLike(DaoConstants.R1_MINUSCULA_PUNTO + DBConstants.DNI_RECEPTOR_063,
				solicitante.getDni(), paramsCER, startsWith));
		paginatedQueryOE.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, selectCER));
		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQueryOE.toString(), paramsCER.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQueryOE.toString(), this.rwMapExpediente, paramsCER.toArray());
		}
	}

	@Override
	public Integer obtenerIdRequerimiento(EntradaDatosExpediente datosExpediente) {
		List<Object> paramsOIR = new ArrayList<Object>();
		StringBuilder selectOIR = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		selectOIR.append(" SELECT t1.ID_064 IDREQUERIMIENTO ");
		selectOIR.append(" FROM AA79B64S01 t1 ");
		selectOIR.append(" JOIN AA79B81S01 t2 ");
		selectOIR.append(" ON t1.ID_064 = t2.ID_REQUERIMIENTO_081 ");
		selectOIR.append(" AND t2.ID_TIPO_TAREA_081 IN ( " + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue()
				+ " , " + TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue() + " )");
		selectOIR.append(" AND t2.ESTADO_EJECUCION_081 =  " + EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		selectOIR.append(DaoConstants.WHERE_1_1);
		selectOIR.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.IND_SUBSANADO_064,
				ActivoEnum.SI.getValue(), paramsOIR, false));
		selectOIR
				.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_SUBSANACION_064,
						EstadoSubsanacionEnum.ACEPTADO.getValue(), paramsOIR));
		selectOIR.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO_REQUERIMIENTO_064
				+ DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
				+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() + DaoConstants.SIGNO_COMA_SIN_ESPACIOS
				+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() + DaoConstants.CERRAR_PARENTESIS);
		selectOIR.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_081,
				datosExpediente.getAnyo(), paramsOIR));
		selectOIR.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081,
				datosExpediente.getNumExp(), paramsOIR));

		try {
			return this.getJdbcTemplate().queryForObject(selectOIR.toString(), paramsOIR.toArray(), Integer.class);

		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			Aa79bExpedienteWsDaoImpl.LOGGER.error(erdae.getMessage(), erdae);
			return Constants.CERO;
		}
	}

	@Override
	public Aa79bSalidaDatosPresupuestoFacturacion datosFacturacionInterpretacion(
			EntradaDatosExpediente datosExpediente) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);

		List<Object> paramsDPFI = new ArrayList<Object>();
		StringBuilder queryDPFI = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		Aa79bExpedienteWSUtils.getSelectDatosFacturacionInterpretacion(eu, es, queryDPFI, this.msg);
		Aa79bExpedienteWSUtils.getFromDatosFacturacionInterpretacion(queryDPFI);
		Aa79bExpedienteWSUtils.getWhereDatosFacturacionInterpretacion(datosExpediente, paramsDPFI, queryDPFI);
		return this.getJdbcTemplate().queryForObject(queryDPFI.toString(), this.rwMapDatosFacturacionInterpretacion,
				paramsDPFI.toArray());
	}

	@Override
	public List<Aa79bEntidadContacto> obtenerDatosEntidadContactoFacturacionInterpretacion(
			EntradaDatosExpediente datosExpediente) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);

		List<Object> paramsDECF = new ArrayList<Object>();
		StringBuilder queryDECF = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		Aa79bExpedienteWSUtils.getSelectDatosEntidadContactoFacturacionInterpretacion(eu, es, queryDECF, this.msg);
		Aa79bExpedienteWSUtils.getFromDatosEntidadContactoFacturacionInterpretacion(queryDECF);
		Aa79bExpedienteWSUtils.getWhereDatosEntidadContactoFacturacionInterpretacion(datosExpediente, paramsDECF,
				queryDECF);
		return this.getJdbcTemplate().query(queryDECF.toString(), paramsDECF.toArray(),
				this.rwMapContactoDatosFacturacion);
	}

	@Override
	public Aa79bSalidaDatosPresupuestoFacturacion datosFacturacionTraduccion(EntradaDatosExpediente datosExpediente) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		List<Object> paramsDPFI = new ArrayList<Object>();
		StringBuilder queryDPFI = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		Aa79bExpedienteWSUtils.getSelectDatosFacturacionTraduccion(eu, es, queryDPFI, this.msg);
		Aa79bExpedienteWSUtils.getFromDatosFacturacionTraduccion(queryDPFI);
		Aa79bExpedienteWSUtils.getWhereDatosFacturacionTraduccion(datosExpediente, paramsDPFI, queryDPFI);
		return this.getJdbcTemplate().queryForObject(queryDPFI.toString(), this.rwMapDatosFacturacionTraduccion,
				paramsDPFI.toArray());
	}

	@Override
	public List<Aa79bEntidadContacto> obtenerDatosEntidadContactoFacturacionTraduccion(
			EntradaDatosExpediente datosExpediente) {
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);

		List<Object> paramsDECF = new ArrayList<Object>();
		StringBuilder queryDECF = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		Aa79bExpedienteWSUtils.getSelectDatosEntidadContactoFacturacionTraduccion(eu, es, queryDECF, this.msg);
		Aa79bExpedienteWSUtils.getFromDatosEntidadContactoFacturacionTraduccion(queryDECF);
		Aa79bExpedienteWSUtils.getWhereDatosEntidadContactoFacturacionTraduccion(datosExpediente, paramsDECF,
				queryDECF);
		return this.getJdbcTemplate().query(queryDECF.toString(), paramsDECF.toArray(),
				this.rwMapContactoDatosFacturacion);
	}

	@Override
	public List<Aa79bSolaskides> findSolaskides(Aa79bInformes aa79bInformes, Locale locale) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		params.add(aa79bInformes.getDni());

		query.append(DBConstants.SELECT);
		query.append(" DNICOMPLETO,");
		query.append(" NOMBRE, APEL1, APEL2 ");
		query.append(DAOUtils.getDecodeAcciones("TIPO_ENTIDAD", "TIPO_ENTIDAD", this.msg, "TipoEntidadEnum", locale));
		query.append(", DESC_ES, DESC_EU");
		query.append(" FROM INF_SOLASKIDEAK ");
		query.append(
				" WHERE (COD_ENTIDAD, TIPO_ENTIDAD) IN (SELECT COD_ENTIDAD, TIPO_ENTIDAD FROM X54JAPI_SOLICITANTES WHERE COORDINADOR = 'S' AND DNI = ?) ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolaskides, params.toArray());
	}

	@Override
	public List<Aa79bInformeSolicitudes> findInformeSolicitudes(Aa79bInformes aa79bInformes, Locale locale) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		params.add(aa79bInformes.getDni());
		query.append(DBConstants.SELECT);
		query.append(
				" A.ESP_ZK ESP_ZK, A.URTEA URTEA, A.ESPEDIENTEA ESPEDIENTEA, A.EHAA EHAA, A.ESPEDIENTE_MOTA ESPEDIENTE_MOTA, A.ESKATZAILEA ESKATZAILEA, ");
		query.append(
				" A.HITZ_KOPURUA_IZO HITZ_KOPURUA_IZO, A.TARIFA_HITZAK TARIFA_HITZAK, A.AMAITUTA AMAITUTA, A.DOCUMENTU_MOTA DOCUMENTU_MOTA, A.ITZULPEN_MOTA ITZULPEN_MOTA,");
		query.append(
				" A.ZENBATEKOA_BEZ_BARNE ZENBATEKOA_BEZ_BARNE, A.DESKRIBAPENA DESKRIBAPENA, A.SOLASKIDEA SOLASKIDEA");
		query.append(SqlUtils.generarDecodeIdioma("A.HIZKUNTZA", "HIZKUNTZA", this.msg, locale));
		query.append(
				" , A.ALTA_DATA ALTA_DATA, A.IZO_AMAIERA_DATA IZO_AMAIERA_DATA, A.BENETAKO_ENTREGA_DATA BENETAKO_ENTREGA_DATA, A.LIDFAKTURA LIDFAKTURA");
		query.append(" FROM INF_EXPEDIENTES A");
		query.append(" JOIN X54JAPI_SOLICITANTES B ");
		query.append(" ON A.COD_ENTIDAD = B.COD_ENTIDAD ");
		query.append(" AND A.TIPO = B.TIPO_ENTIDAD ");
		query.append(" WHERE B.DNI = ? ");
		query.append(" ORDER BY A.URTEA ASC, A.ESP_ZK ASC ");
		return this.getJdbcTemplate().query(query.toString(), this.rwMapInformeSolicitudes, params.toArray());
	}

}
