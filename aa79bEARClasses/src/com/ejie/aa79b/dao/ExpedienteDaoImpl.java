package com.ejie.aa79b.dao;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.Aa79bSolicitudRowMapper;
import com.ejie.aa79b.dao.mapper.ConsultaPlanifExpRowMapper;
import com.ejie.aa79b.dao.mapper.DocusSelecRowMapper;
import com.ejie.aa79b.dao.mapper.EstadoSubRowMapper;
import com.ejie.aa79b.dao.mapper.ExpPlanifRowMapper;
import com.ejie.aa79b.dao.mapper.ExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.ExpedienteTareasEntregaRowMapper;
import com.ejie.aa79b.dao.mapper.JustiSolicitudRowMapper;
import com.ejie.aa79b.dao.mapper.ResumenGraficasRowMapper;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.CalendarioIcsModel;
import com.ejie.aa79b.model.CamposSelecSub;
import com.ejie.aa79b.model.CamposSubsanacion;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.DocusSelecSub;
import com.ejie.aa79b.model.EntradaFechaFinalizacion;
import com.ejie.aa79b.model.EntradaGestoresRepresentante;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AportaSubsanacionEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoRequerimientoEnum;
import com.ejie.aa79b.model.enums.EstadoSubsanacionEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.OrigenExpedienteEnum;
import com.ejie.aa79b.model.enums.PlanifExpedientesEnum;
import com.ejie.aa79b.model.enums.PlanifTramitesEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.enums.TipoPeticionarioEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.ExpedienteDaoUtils;
import com.ejie.aa79b.utils.PLConnectionUtils;
import com.ejie.aa79b.utils.PlanificacionExpedienteUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.security.Credentials;

@Repository
@Transactional
public class ExpedienteDaoImpl extends GenericoDaoImpl<Expediente> implements ExpedienteDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteDaoImpl.class);

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	protected static final String ANYO_EXPEDIENTE = "ANYO_051";
	protected static final String NUM_EXPEDIENTE = "NUM_EXP_051";
	protected static final String ID_TIPO_EXPEDIENTE = "ID_TIPO_EXPEDIENTE_051";
	protected static final String TIPO_EXPEDIENTE_DESC_ES = "TIPO_EXPEDIENTE_ES";
	protected static final String TIPO_EXPEDIENTE_DESC_EU = "TIPO_EXPEDIENTE_EU";
	protected static final String ORIGEN_EXPEDIENTE = "ORIGEN_051";
	protected static final String APLICACION_ORIGEN = "APLIC_ORIGEN_051";
	protected static final String FECHA_ALTA_EXPEDIENTE = "FECHA_ALTA_051";
	protected static final String HORA_ALTA_EXPEDIENTE = "HORA_ALTA_051";
	protected static final String TITULO_EXPEDIENTE = "TITULO_051";
	protected static final String ESTADO_BITACORA_EXPEDIENTE = "ESTADO_BITACORA_051";
	protected static final String DNI_TECNICO_EXPEDIENTE = "DNI_TECNICO_051";
	protected static final String NOMBRE_077 = "NOMBRE_077";
	protected static final String APEL1_077 = "APEL1_077";
	protected static final String APEL2_077 = "APEL2_077";
	protected static final String DECODE_T1ID_TIPO_EXPEDIENTE_069 = "DECODE(t1.ID_TIPO_EXPEDIENTE_069";
	protected static final String DECODE_G1IND_VIP_054 = "DECODE(g1.IND_VIP_054";
	protected static final String LABEL_INTERPRETACION = "label.interpretacion";
	protected static final String LABEL_TRADUCCION = "label.traduccion";
	protected static final String LABEL_REVISION = "label.revision";

	protected static final String I1IND_PRESUPUESTO_052 = "i1.IND_PRESUPUESTO_052";
	protected static final String S1TIPO_REQUERIMIENTO_064 = " s1.TIPO_REQUERIMIENTO_064 ";
	protected static final String R1IND_PUBLICAR_BOPV_053 = "r1.IND_PUBLICAR_BOPV_053";
	protected static final String G1DNI_SOLICITANTE_054 = "g1.DNI_SOLICITANTE_054";
	protected static final String G1TIPO_ENTIDAD_054 = "g1.TIPO_ENTIDAD_054";
	protected static final String G1ID_ENTIDAD_054 = "g1.ID_ENTIDAD_054";
	protected static final String QUERY = "query";
	protected static final String PARAMS = "params";
	protected static final String DDMMYY = "DD/mm/YY";
	protected static final String SELECTALLFROMROW = "SELECT * FROM (SELECT rownum rnum, a.*  FROM (";
	protected static final String B1ID_FASE_EXPEDIENTE_059 = "b1.ID_FASE_EXPEDIENTE_059";
	protected static final String UPDATEAA79B51S01 = "UPDATE AA79B51S01 ";
	protected static final String R1FECHA_FINAL_IZO_053 = "r1.FECHA_FINAL_IZO_053";
	protected static final String ANYO_051_EQUALS_ANYO_059AND = "ANYO_051 = ANYO_059 AND ";

	protected static final String FROMAA79B51S01ELEFTJOINAA79B59S01B = "FROM AA79B51S01 e LEFT JOIN AA79B59S01 b ";
	protected static final String ONEANYO_051_EQUALS_BANYO_059AND = "ON e.ANYO_051 = b.ANYO_059 AND ";
	protected static final String ENUM_EXP_051_EQUALS_BNUM_EXP_059AND = "e.NUM_EXP_051 = b.NUM_EXP_059 AND ";
	protected static final String EESTADO_BITACORA_051_EQUALS_ID_ESTADO_BITACORA_059 = "e.ESTADO_BITACORA_051 = b.ID_ESTADO_BITACORA_059 ";
	protected static final String LEFTJOINAA79B64S01S = "LEFT JOIN AA79B64S01 s ";
	protected static final String ONBID_REQUERIMIENTO_059_EQUALS_SID_064 = "ON b.ID_REQUERIMIENTO_059 = s.ID_064 ";
	protected static final String ANYO_051_AND_NUMEXP_051EQUALS = "ANYO_051 = ? and NUM_EXP_051 = ? ";

	protected static final String PLANIFICACION_UTILS = "com.ejie.aa79b.utils.PlanificacionExpedienteUtils";

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { "ID_FASE_EXPEDIENTE_059", "FECHA_FIN_052",
			"HORA_FIN_052", "IND_VIP_054", "IND_PUBLICAR_BOPV_053", "ID_REQUERIMIENTO_059", "FECHA_LIMITE_064",
			ANYO_EXPEDIENTE, NUM_EXPEDIENTE, ID_TIPO_EXPEDIENTE, TIPO_EXPEDIENTE_DESC_ES, TIPO_EXPEDIENTE_DESC_EU,
			ORIGEN_EXPEDIENTE, APLICACION_ORIGEN, FECHA_ALTA_EXPEDIENTE, HORA_ALTA_EXPEDIENTE, TITULO_EXPEDIENTE,
			ESTADO_BITACORA_EXPEDIENTE, DNI_TECNICO_EXPEDIENTE, DBConstants.ANYONUMEXPCONCATENADO,
			"NUM_TOTAL_PAL_IZO_053", "FECHA_FINAL_IZO_053", "HORA_FINAL_IZO_053", "IND_PREVISTO_BOPV_053", "TIPOEXPES",
			"TIPOEXPEU", "IND_SUBSANADO_064", "SUBSANACIONDESCES", "SUBSANACIONDESCEU", "IND_PRESUPUESTO_052",
			"PRESUPUESTODESCES", "PRESUPUESTODESCEU", "PUBLICARBOPVDESCES", "PUBLICARBOPVDESCEU",
			"GESTOREXPEDIENTESVIPDESCES", "GESTOREXPEDIENTESVIPDESCEU", "ESTADOEXPEDIENTEDESCES",
			"ESTADOEXPEDIENTEDESCEU", "ESTADOEXPEDIENTEDESCABRES", "ESTADOEXPEDIENTEDESCABREU", "FASEEXPEDIENTEDESCES",
			"FASEEXPEDIENTEDESCEU", "FASEEXPEDIENTEDESCABRES", "FASEEXPEDIENTEDESCABREU", "DNI_SOLICITANTE_054",
			"FASEEXPEDIENTEDESCABRNORMES", "FASEEXPEDIENTEDESCABRNORMEU", DBConstants.DNICOMPLETO, "FECHA_INI_052",
			"HORA_INI_052", "FECHA_FINAL_SOLIC_053", "FECHA_FINAL_PROP_053", DBConstants.PRIORITARIO,
			DBConstants.IDTIPOEXPEDIENTE, DBConstants.NOMBREGESTOR, DBConstants.FECHAFINALIZO,
			DBConstants.NUMTOTALPALIZO, DBConstants.RESPONSABLE, DBConstants.FECHAINICIOPREVISTA,
			DBConstants.FECHAFINPREVISTA, DBConstants.INDPRESUPUESTO, DBConstants.NUMTOTALPALTRADOS,
			DBConstants.IDFASEEXPEDIENTE, DBConstants.IDESTADOTAREASASIGNADAS, DBConstants.NOMBREASIGNADOR,
			DBConstants.ORDEN, DBConstants.REQUIERETRADOS, "GESTORCOLORDEREU", "GESTORCOLORDERES", "CONFORMIDAD",
			"TAREASSINASIGNAR", "FECHA_FINAL", "TAREAEU", "NOMBRECOMPLETO", "FECHA_ASIGNACION_081",
			"ESTADO_ASIGNACION_081", "ESTADOEJECUCIONEU", "HORASPREVISTASNUMBER", "FECHA_FIN_081",
			"TIPOREQUERIMIENTOEU", "FECHA_LIMITE_064", "ESTADOSUBSANACIONEU" };

	public ExpedienteDaoImpl() {
		// Constructor
		super(Expediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Expediente> rwMap = new ExpedienteRowMapper();
	private RowMapper<Expediente> rwMapJustiSolicitud = new JustiSolicitudRowMapper();

	private RowMapper<Expediente> rwMapPK = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong(ANYO_EXPEDIENTE));
			expediente.setNumExp(resultSet.getInt(NUM_EXPEDIENTE));
			return expediente;
		}
	};

	private RowMapper<Aa79bSolicitud> rwMapSolicitud = new Aa79bSolicitudRowMapper();

	private RowMapper<Aa79bExpedienteRelacionado> rwMapExpRelacionadas = new RowMapper<Aa79bExpedienteRelacionado>() {
		@Override
		public Aa79bExpedienteRelacionado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Aa79bExpedienteRelacionado expRel = new Aa79bExpedienteRelacionado();
			expRel.setAnyo(resultSet.getLong("ANYOEXPREL74"));
			expRel.setNumExp(resultSet.getInt("NUMEXPREL74"));
			return expRel;
		}
	};

	private RowMapper<DocumentosExpediente> rwMapDocumentosRelacionados = new RowMapper<DocumentosExpediente>() {
		@Override
		public DocumentosExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			DocumentosExpediente expRel = new DocumentosExpediente();
			expRel.setClaseDocumento(resultSet.getLong("CLASEDOCUMENTO"));
			expRel.setTipoDocumento(resultSet.getLong("TIPODOCUMENTO"));
			expRel.setTitulo(resultSet.getString("TITULO"));
			expRel.setNumPalSolic(resultSet.getInt("NUMPALSOLIC"));
			expRel.setClaseDocumentoDesc(resultSet.getString("CLASEDOCUMENTODESC"));
			expRel.setTipoDocumentoDesc(resultSet.getString("TIPODOCUMENTODESC"));
			return expRel;
		}
	};

	private RowMapper<SubsanacionExpediente> rwMapDetalleSub = new RowMapper<SubsanacionExpediente>() {
		@Override
		public SubsanacionExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
			subsanacionExpediente.setId(resultSet.getLong("ID_REQUERIMIENTO_059"));
			subsanacionExpediente.setDetalle(resultSet.getString("DETALLE_064"));
			subsanacionExpediente.setIndDocNuevos(resultSet.getString("IND_DOC_NUEVOS_064"));
			subsanacionExpediente.setFechaLimite(resultSet.getTimestamp("FECHA_LIMITE_064"));
			return subsanacionExpediente;

		}
	};

	private RowMapper<CamposSubsanacion> rwMapCampoSub = new RowMapper<CamposSubsanacion>() {
		@Override
		public CamposSubsanacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			CamposSubsanacion camposSubsanacion = new CamposSubsanacion();

			camposSubsanacion.setId078(resultSet.getLong("ID078"));
			camposSubsanacion.setDesces078(resultSet.getString("DESCES078"));
			camposSubsanacion.setDesceu078(resultSet.getString("DESCEU078"));
			camposSubsanacion.setNameaa79078(resultSet.getString("NAMEAA79078"));
			camposSubsanacion.setNameaa06078(resultSet.getString("NAMEAA06078"));
			return camposSubsanacion;

		}
	};

	private RowMapper<CamposSelecSub> rwMapCamposSelecSub = new RowMapper<CamposSelecSub>() {
		@Override
		public CamposSelecSub mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			CamposSelecSub camposSelecSub = new CamposSelecSub();
			camposSelecSub.setId065(resultSet.getLong("ID_065"));
			camposSelecSub.setIdcampo065(resultSet.getLong("ID_CAMPO_065"));

			CamposSubsanacion camposSubsanacion = new CamposSubsanacion();
			camposSubsanacion.setNameaa06078(resultSet.getString("NAME_AA06_078"));

			camposSelecSub.setCamposSubsanacion(camposSubsanacion);

			return camposSelecSub;

		}
	};

	private RowMapper<DocusSelecSub> rwMapDocusSelecSub = new RowMapper<DocusSelecSub>() {
		@Override
		public DocusSelecSub mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			DocusSelecSub docusSelecSub = new DocusSelecSub();

			docusSelecSub.setId066(resultSet.getLong("ID_066"));
			docusSelecSub.setIddoc066(resultSet.getLong("ID_DOC_066"));
			return docusSelecSub;

		}
	};

	private RowMapper<Expediente> rwMapEstadoSub = new EstadoSubRowMapper();

	private RowMapper<Expediente> rwMapCamposSelec = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			CamposSubsanacion camposSub = new CamposSubsanacion();
			camposSub.setNameaa79078(resultSet.getString("NAMEAA79078"));
			CamposSelecSub camposSelSub = new CamposSelecSub();
			camposSelSub.setId065(resultSet.getLong("ID065"));
			camposSelSub.setCamposSubsanacion(camposSub);
			SubsanacionExpediente subExp = new SubsanacionExpediente();
			subExp.setId(resultSet.getLong("ID064"));
			subExp.setCamposSelecSub(camposSelSub);
			BitacoraExpediente bitacora = new BitacoraExpediente();
			bitacora.setSubsanacionExp(subExp);
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO051"));
			expediente.setNumExp(resultSet.getInt("NUMEXP051"));
			expediente.setBitacoraExpediente(bitacora);
			return expediente;
		}
	};

	private RowMapper<Expediente> rwMapDocusSelec = new DocusSelecRowMapper();
	private RowMapper<ResumenGraficas> rwMapResumenGraficas = new ResumenGraficasRowMapper();

	private RowMapper<ExpTareasResumen> rwMapExpedientes = new RowMapper<ExpTareasResumen>() {
		@Override
		public ExpTareasResumen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return PlanificacionExpedienteUtils.expedienteRwMap(resultSet);
		}
	};

	private RowMapper<ExpTareasResumen> rwMapTareas = new RowMapper<ExpTareasResumen>() {
		@Override
		public ExpTareasResumen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return PlanificacionExpedienteUtils.tareaRwMap(resultSet);
		}
	};

	private RowMapper<ExpTareasResumen> rwMapTareasDesglose = new RowMapper<ExpTareasResumen>() {
		@Override
		public ExpTareasResumen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return PlanificacionExpedienteUtils.tareaDesgloseRwMap(resultSet);
		}
	};

	private RowMapper<ExpTareasResumen> rwMapTramites = new RowMapper<ExpTareasResumen>() {
		@Override
		public ExpTareasResumen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return PlanificacionExpedienteUtils.tramiteRwMap(resultSet);
		}
	};

	private RowMapper<ExpedientePlanificacion> rwMapBusqGen = new ExpPlanifRowMapper();

	private RowMapper<ExpedientePlanificacion> rwMapConsultaPlanifExp = new ConsultaPlanifExpRowMapper();

	private RowMapper<ExpedientePlanificacion> rwMapConsultaPlanifExpIds = new RowMapper<ExpedientePlanificacion>() {
		@Override
		public ExpedientePlanificacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
			ExpedientePlanificacion expPlanificacion = new ExpedientePlanificacion();
			expPlanificacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
			expPlanificacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return expPlanificacion;
		}
	};

	private RowMapper<CalendarioIcsModel> rwMapCalendarioIcs = new RowMapper<CalendarioIcsModel>() {
		@Override
		public CalendarioIcsModel mapRow(ResultSet resultSet, int arg1) throws SQLException {
			CalendarioIcsModel calendarioIcsModel = new CalendarioIcsModel();
			calendarioIcsModel.setAnyo(resultSet.getLong(DBConstants.ANYO));
			calendarioIcsModel.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			calendarioIcsModel.setTipoExp(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
			calendarioIcsModel.setTitulo(resultSet.getString(DBConstants.TITULO));
			calendarioIcsModel.setFechaInicio(resultSet.getDate(DBConstants.FECHAALTA));
			calendarioIcsModel.setHoraInicio(resultSet.getString(DBConstants.HORAALTA));
			calendarioIcsModel.setFechaFin(resultSet.getDate(DBConstants.FECHAFINAL));
			calendarioIcsModel.setHoraFin(resultSet.getString(DBConstants.HORAFINAL));
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equalsIgnoreCase(calendarioIcsModel.getTipoExp())) {
				DireccionNora dirNora = new DireccionNora();
				dirNora.setDirNora(resultSet.getInt(DBConstants.CDIRNORA));
				calendarioIcsModel.setDirNora(dirNora);
			}
			return calendarioIcsModel;
		}
	};

	private RowMapper<Expediente> rwMapTareasEntrega = new ExpedienteTareasEntregaRowMapper();

	public RowMapper<CalendarioIcsModel> getRwMapCalendarioIcs() {
		return this.rwMapCalendarioIcs;
	}

	private RowMapper<Expediente> rwMapBitacoras = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setIdEstadoBitacora(resultSet.getLong("IDESTADOBITACORA"));
			expediente.setBitacoraExpediente(bitacoraExpediente);
			return expediente;
		}
	};

	private RowMapper<BitacoraExpediente> rwMapBitacora = new RowMapper<BitacoraExpediente>() {
		@Override
		public BitacoraExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			BitacoraExpediente bitacora = new BitacoraExpediente();
			EstadosExpediente estadoExp = new EstadosExpediente();
			estadoExp.setId(resultSet.getLong(DBConstants.IDESTADOEXP));
			bitacora.setEstadoExp(estadoExp);
			FasesExpediente faseExp = new FasesExpediente();
			faseExp.setId(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
			bitacora.setFaseExp(faseExp);
			return bitacora;
		}
	};

	private RowMapper<BitacoraExpediente> rwMapBitacoraExp = new RowMapper<BitacoraExpediente>() {
		@Override
		public BitacoraExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			BitacoraExpediente bitacora = new BitacoraExpediente();
			bitacora.setIdEstadoBitacora(resultSet.getLong(DBConstants.ESTADOBITACORA));
			return bitacora;
		}
	};

	private RowMapper<ExpedienteTradRev> rwMapFechaIzo = new RowMapper<ExpedienteTradRev>() {
		@Override
		public ExpedienteTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
			expedienteTradRev.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
			expedienteTradRev.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
			return expedienteTradRev;
		}
	};

	private RowMapper<Expediente> rwMapOrigenExpediente = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente exp = new Expediente();
			exp.setAnyo(resultSet.getLong("ANYO"));
			exp.setNumExp(resultSet.getInt("NUMEXP"));
			exp.setOrigen(resultSet.getString("ORIGEN"));
			exp.setAplicacionOrigen(resultSet.getString("APLICORIGEN"));

			return exp;
		}
	};

	/**
	 * Inserts a single row in the Expediente table.
	 *
	 * @param expediente Expediente
	 * @return Expediente
	 */
	@Override
	public Expediente add(Expediente expediente) {
		long numExp = PLConnectionUtils.crearNumExp(expediente.getAnyo(), this.getJdbcTemplate());
		if (numExp > 0) {
			expediente.setNumExp(Integer.valueOf((int) numExp));

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			expediente.setAnyo((long) year);

			Date fechaAlta = expediente.getFechaAlta();
			String horaAlta = expediente.getHoraAlta();

			String query = "INSERT INTO AA79B51S01 (ANYO_051, NUM_EXP_051, ID_TIPO_EXPEDIENTE_051, ORIGEN_051, "
					+ "FECHA_ALTA_051, TITULO_051, ESTADO_BITACORA_051, DNI_TECNICO_051) " + "VALUES (?,?,?,?,?,?,?,?)";
			this.getJdbcTemplate().update(query, expediente.getAnyo(), expediente.getNumExp(),
					expediente.getIdTipoExpediente(), expediente.getOrigen(),
					DateUtils.obtFechaHoraFormateada(fechaAlta, horaAlta), expediente.getTitulo(),
					expediente.getEstadoBitacora(), expediente.getTecnico().getDni());
			return expediente;
		} else {
			return null;
		}
	}

	/**
	 * Updates a single row in the Expediente table.
	 *
	 * @param expediente Expediente
	 * @return Expediente
	 */
	@Override
	public Expediente update(Expediente expediente) {
		Date fechaAlta = expediente.getFechaAlta();
		String horaAlta = expediente.getHoraAlta();

		String query = "UPDATE AA79B51S01 SET ID_TIPO_EXPEDIENTE_051=?, "
				+ "FECHA_ALTA_051=?, TITULO_051=?, DNI_TECNICO_051=? WHERE ANYO_051=? AND NUM_EXP_051=?";
		this.getJdbcTemplate().update(query, expediente.getIdTipoExpediente(),
				DateUtils.obtFechaHoraFormateada(fechaAlta, horaAlta), expediente.getTitulo(),
				expediente.getTecnico() != null ? expediente.getTecnico().getDni() : null, expediente.getAnyo(),
				expediente.getNumExp());
		return expediente;
	}

	@Override
	public Expediente updateIdEstadoBitacora(Expediente expediente) {
		String query = "UPDATE AA79B51S01 SET ESTADO_BITACORA_051=? WHERE ANYO_051=? AND NUM_EXP_051=?";
		this.getJdbcTemplate().update(query, expediente.getEstadoBitacora(), expediente.getAnyo(),
				expediente.getNumExp());
		return expediente;
	}

	@Override
	public Expediente updateAsignador(Expediente expediente) {
		String query = "UPDATE AA79B51S01 SET DNI_ASIGNADOR_051=? WHERE ANYO_051=? AND NUM_EXP_051=? AND DNI_ASIGNADOR_051 IS NULL";
		this.getJdbcTemplate().update(query, expediente.getAsignador().getDni(), expediente.getAnyo(),
				expediente.getNumExp());
		return expediente;
	}

	protected String getSelectExpRelacionadas() {

		StringBuilder query = new StringBuilder();
		query.append("SELECT ANYO_EXP_REL_074 ANYOEXPREL74, ");
		query.append("NUM_EXP_REL_074 NUMEXPREL74 ");

		return query.toString();
	}

	protected String getSelectDocumentosSolicitud() {

		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.CLASE_DOCUMENTO_073 CLASEDOCUMENTO, ");
		query.append("t1.TIPO_DOCUMENTO_073 TIPODOCUMENTO, ");
		query.append("t1.TITULO_073 TITULO, ");
		query.append("t1.NUM_PAL_SOLIC_073 NUMPALSOLIC, ");
		query.append("t2.DESC_EU_075 AS CLASEDOCUMENTODESC, ");
		query.append("t3.DESC_EU_042 AS TIPODOCUMENTODESC ");

		return query.toString();
	}

	protected String getSelectSolicitud() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT a.ANYO_069 ANYO69, ");
		query.append("a.NUM_EXP_069 NUMEXP69, ");
		query.append("a.ID_TIPO_EXPEDIENTE_069 IDTIPOEXPEDIENTE69, ");
		query.append("a.FECHA_ALTA_069 FECHAALTA69, ");
		query.append("a.TITULO_069 TITULO69, ");
		query.append("b.MODO_INTERPRETACION_070 MODOINTERPRETACION70, ");
		query.append("b.TIPO_ACTO_070 TIPOACTO70, ");
		query.append("b.TIPO_PETICIONARIO_070 TIPOPETICIONARIO70, ");
		query.append("b.IND_PROGRAMADA_070 INDPROGRAMADA70, ");
		query.append("b.FECHA_INI_070 FECHAINI70, ");
		query.append("b.FECHA_FIN_070 FECHAFIN70, ");
		query.append("b.DIR_NORA_070 DIRNORA70, ");
		query.append("b.IND_PRESUPUESTO_070 INDPRESUPUESTO70, ");
		query.append("b.PERSONA_CONTACTO_070 PERSONACONTACTO70, ");
		query.append("b.EMAIL_CONTACTO_070 EMAILCONTACTO70, ");
		query.append("b.TELEFONO_CONTACTO_070 TELEFONOCONTACTO70, ");
		query.append("c.IND_PUBLICAR_BOPV_071 INDPUBLICARBOPV71, ");
		query.append("c.ID_IDIOMA_071 IDIDIOMA71, ");
		query.append("c.IND_CONFIDENCIAL_071 INDCONFIDENCIAL71, ");
		query.append("c.IND_CORREDACCION_071 INDCORREDACCION71, ");
		query.append("c.TEXTO_071 TEXTO71, ");
		query.append("c.TIPO_REDACCION_071 TIPOREDACCION71, ");
		query.append("c.PARTICIPANTES_071 PARTICIPANTES71, ");
		query.append("c.REF_TRAMITAGUNE_071 REFTRAMITAGUNE71, ");
		query.append("c.NUM_TOTAL_PAL_SOLIC_071 NUMTOTALPALSOLIC71, ");
		query.append("c.FECHA_FINAL_SOLIC_071 FECHAFINALSOLIC71, ");
		query.append("c.IND_FACTURABLE_071 INDFACTURABLE71, ");
		query.append("c.ID_RELEVANCIA_071 IDRELEVANCIA71, ");
		query.append("c.IND_URGENTE_071 INDURGENTE71, ");
		query.append("c.IND_PRESUPUESTO_071 INDPRESUPUESTO71 ");

		return query.toString();
	}

	@Override
	protected String getSelect() {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.ANYO_051 ANYO_051");
		query.append(", t1.NUM_EXP_051 NUM_EXP_051");
		query.append(", SUBSTR(t1.ANYO_051,3,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO");
		query.append(", t1.ID_TIPO_EXPEDIENTE_051 ID_TIPO_EXPEDIENTE_051");
		query.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
				.append("') AS TIPO_EXPEDIENTE_ES");
		query.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append("') AS TIPO_EXPEDIENTE_EU");
		query.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_INTERPRETACION, null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_TRADUCCION, null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_REVISION, null, es)).append("') AS TIPOEXPEDIENTEDESCES");
		query.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_INTERPRETACION, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_TRADUCCION, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_REVISION, null, eu)).append("') AS TIPOEXPEDIENTEDESCEU");
		query.append(", t1.ORIGEN_051 ORIGEN_051");
		query.append(", t1.APLIC_ORIGEN_051 APLIC_ORIGEN_051");
		query.append(", t1.FECHA_ALTA_051 FECHA_ALTA_051");
		query.append(", TO_CHAR(t1.FECHA_ALTA_051,'HH24:MI') HORA_ALTA_051");
		query.append(", t1.TITULO_051 TITULO_051");
		query.append(", t1.ESTADO_BITACORA_051 ESTADO_BITACORA_051");
		query.append(", t1.DNI_TECNICO_051 DNI_TECNICO_051");
		query.append(", t1.OBSV_FACT_051 OBSV_FACT_051");

		// NombreApellidosTecnico
		query.append(", k1.NOMBRE_077 NOMBRE_TECNICO_077");
		query.append(", k1.APEL1_077 APEL1_TECNICO_077");
		query.append(", k1.APEL2_077 APEL2_TECNICO_077");

		// Asignador
		query.append(", t1.DNI_ASIGNADOR_051 DNI_ASIGNADOR_051");

		// ExpedienteInterpretacion
		query.append(", i1.MODO_INTERPRETACION_052 MODO_INTERPRETACION_052");
		query.append(", m1.DESC_ES_014 DESC_ES_014");
		query.append(", m1.DESC_EU_014 DESC_EU_014");
		query.append(", i1.TIPO_ACTO_052 TIPO_ACTO_052");
		query.append(", a1.DESC_ES_008 DESC_ES_008");
		query.append(", a1.DESC_EU_008 DESC_EU_008");
		query.append(", i1.TIPO_PETICIONARIO_052 TIPO_PETICIONARIO_052");
		query.append(", DECODE(i1.TIPO_PETICIONARIO_052, '" + TipoPeticionarioEnum.ADMIN_PUBLICA.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel(), null, es))
				.append("', '" + TipoPeticionarioEnum.PARTICULAR.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.PARTICULAR.getLabel(), null, es))
				.append("') AS TIPOPETICIONARIODESCES");
		query.append(", DECODE(i1.TIPO_PETICIONARIO_052, '" + TipoPeticionarioEnum.ADMIN_PUBLICA.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel(), null, eu))
				.append("', '" + TipoPeticionarioEnum.PARTICULAR.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.PARTICULAR.getLabel(), null, eu))
				.append("') AS TIPOPETICIONARIODESCEU");
		query.append(", i1.IND_PROGRAMADA_052 IND_PROGRAMADA_052");
		query.append(", DECODE(i1.IND_PROGRAMADA_052, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS INDPROGRAMADADESCES");
		query.append(", DECODE(i1.IND_PROGRAMADA_052, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS INDPROGRAMADADESCEU");
		query.append(", i1.FECHA_INI_052 FECHA_INI_052");
		query.append(", TO_CHAR(i1.FECHA_INI_052,'HH24:MI') HORA_INI_052");
		query.append(", i1.FECHA_FIN_052 FECHA_FIN_052");
		query.append(", TO_CHAR(i1.FECHA_FIN_052,'HH24:MI') HORA_FIN_052");
		query.append(", i1.DIR_NORA_052 DIR_NORA_052");
		query.append(", i1.IND_PRESUPUESTO_052 IND_PRESUPUESTO_052");
		query.append(", DECODE(i1.IND_PRESUPUESTO_052, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PRESUPUESTODESCES");
		query.append(", DECODE(i1.IND_PRESUPUESTO_052, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PRESUPUESTODESCEU");
		query.append(", i1.PERSONA_CONTACTO_052 PERSONA_CONTACTO_052");
		query.append(", i1.EMAIL_CONTACTO_052 EMAIL_CONTACTO_052");
		query.append(", i1.TELEFONO_CONTACTO_052 TELEFONO_CONTACTO_052");
		query.append(", i1.IND_OBSERVACIONES_052 IND_OBSERVACIONES_052");
		query.append(", d1.CDIRNORA_049 CDIRNORA_049");

		// ExpedienteTradRev
		query.append(", r1.IND_PUBLICAR_BOPV_053 IND_PUBLICAR_BOPV_053");
		query.append(", DECODE(r1.IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PUBLICARBOPVDESCES");
		query.append(", DECODE(r1.IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PUBLICARBOPVDESCEU");
		query.append(", r1.IND_PREVISTO_BOPV_053 IND_PREVISTO_BOPV_053");
		query.append(", r1.ID_IDIOMA_053 ID_IDIOMA_053");
		query.append(", l1.DESC_IDIOMA_ES_009 DESC_IDIOMA_ES_009");
		query.append(", l1.DESC_IDIOMA_EU_009 DESC_IDIOMA_EU_009");
		query.append(", r1.IND_CONFIDENCIAL_053 IND_CONFIDENCIAL_053");
		query.append(", r1.IND_CORREDACCION_053 IND_CORREDACCION_053");
		query.append(", r1.TEXTO_053 TEXTO_053");
		query.append(", r1.IND_DIFICIL_053 INDDIFICIL");
		query.append(", r1.TIPO_REDACCION_053 TIPO_REDACCION_053");
		query.append(", r1.PARTICIPANTES_053 PARTICIPANTES_053");
		query.append(", r1.REF_TRAMITAGUNE_053 REF_TRAMITAGUNE_053");
		query.append(", NVL(r1.NUM_TOTAL_PAL_IZO_053,0) NUM_TOTAL_PAL_IZO_053");
		query.append(", r1.NUM_TOTAL_PAL_SOLIC_053 NUM_TOTAL_PAL_SOLIC_053");

		query.append(", NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053) FECHA_FINAL_IZO_053");
		query.append(", TO_CHAR(NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053),'HH24:MI') HORA_FINAL_IZO_053");

		query.append(", r1.IND_FACTURABLE_053 IND_FACTURABLE_053");
		query.append(", r1.ID_RELEVANCIA_053 ID_RELEVANCIA_053");
		query.append(", r1.IND_URGENTE_053 IND_URGENTE_053");
		query.append(", r1.FECHA_FINAL_SOLIC_053 FECHA_FINAL_SOLIC_053");
		query.append(", TO_CHAR(r1.FECHA_FINAL_SOLIC_053,'HH24:MI') HORAFINALSOLIC");
		query.append(", r1.FECHA_FINAL_PROP_053 FECHA_FINAL_PROP_053");
		query.append(", r1.IND_OBSERVACIONES_053 IND_OBSERVACIONES_053");
		query.append(", r1.IND_PRESUPUESTO_053 IND_PRESUPUESTO_053");
		query.append(", DECODE(r1.IND_PRESUPUESTO_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PRESUPUESTOTRDESCES");
		query.append(", DECODE(r1.IND_PRESUPUESTO_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PRESUPUESTOTRDESCEU");
		// Nuevos para planificacion
		query.append(", r1.IND_PUBLICADO_BOE_053 IND_PUBLICADO_BOE_053");
		query.append(", r1.URL_BOE_053 URL_BOE_053");
		query.append(
				", CASE WHEN (g1.TIPO_ENTIDAD_054, g1.ID_ENTIDAD_054) = (SELECT 'B', VALOR_000 FROM AA79B00T00 WHERE ID_000 = 1) THEN ");
		query.append("'S' ELSE 'N' END AS IND_BOE ");
		// GestorExpediente
		query.append(", t4.DNI DNIVINCULADOOBAJA ");
		query.append(", g1.DNI_SOLICITANTE_054 DNI_SOLICITANTE_054");
		query.append(", g1.TIPO_ENTIDAD_054 TIPO_ENTIDAD_054");
		query.append(", g1.ID_ENTIDAD_054 ID_ENTIDAD_054");
		query.append(", g1.ES_GRUPO_BOLETIN_054 ES_GRUPO_BOLETIN_054");
		query.append(", n1.CDIRNORA ENTIDAD_CDIRNORA");
		query.append(", n1.IVA ENTIDAD_IVA");
		query.append(", n1.FACTURABLE ENTIDAD_FACTURABLE");
		query.append(", n1.ESTADO ENTIDAD_ESTADO");
		query.append(", n1.CIF ENTIDAD_CIF");
		query.append(", n1.DESC_AMP_EU ENTIDAD_DESC_AMP_EU");
		query.append(", n1.DESC_AMP_ES ENTIDAD_DESC_AMP_ES");
		query.append(", n1.DESC_EU ENTIDAD_DESC_EU");
		query.append(", n1.DESC_ES ENTIDAD_DESC_ES");
		query.append(", g1.IND_VIP_054 IND_VIP_054");
		query.append(", " + DECODE_G1IND_VIP_054 + ", '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es))
				.append("') AS GESTOREXPEDIENTESVIPDESCES");
		query.append(", " + DECODE_G1IND_VIP_054 + ", '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu))
				.append("') AS GESTOREXPEDIENTESVIPDESCEU");
		// NombreApellidosGestor
		query.append(", h1.NOMBRE_077 NOMBRE_077");
		query.append(", h1.APEL1_077 APEL1_077");
		query.append(", h1.APEL2_077 APEL2_077");
		query.append(", h1.PREDNI_077 PREDNI_077");
		query.append(", h1.SUFDNI_077 SUFDNI_077");
		query.append(", (h1.PREDNI_077 || g1.DNI_SOLICITANTE_054 || h1.SUFDNI_077) AS DNICOMPLETO");

		// Bitacora
		query.append(", b1.ID_ESTADO_BITACORA_059 ID_ESTADO_BITACORA_059");
		query.append(", b1.ID_ESTADO_EXP_059 ID_ESTADO_EXP_059");
		query.append(", e1.DESC_ES_060 ESTADOEXPEDIENTEDESCES");
		query.append(", e1.DESC_EU_060 ESTADOEXPEDIENTEDESCEU");
		query.append(", e1.DESC_ABR_ES_060 ESTADOEXPEDIENTEDESCABRES");
		query.append(", e1.DESC_ABR_EU_060 ESTADOEXPEDIENTEDESCABREU");
		query.append(", e1.CLASS_060 ESTADOEXPEDIENTECLASS");
		query.append(", b1.ID_FASE_EXPEDIENTE_059 ID_FASE_EXPEDIENTE_059");
		query.append(", f1.DESC_ES_061 FASEEXPEDIENTEDESCES");
		query.append(", f1.DESC_EU_061 FASEEXPEDIENTEDESCEU");
		query.append(", f1.DESC_ABR_ES_061 FASEEXPEDIENTEDESCABRES");
		query.append(", f1.DESC_ABR_EU_061 FASEEXPEDIENTEDESCABREU");
		query.append(", ").append(SqlUtils.normalizarCampoSql("f1.DESC_ABR_ES_061"))
				.append(" AS FASEEXPEDIENTEDESCABRNORMES");
		query.append(", ").append(SqlUtils.normalizarCampoSql("f1.DESC_ABR_EU_061"))
				.append(" AS FASEEXPEDIENTEDESCABRNORMEU");
		query.append(", b1.DATO_ADIC_059 DATO_ADIC_059");
		query.append(", b1.INFO_ADIC_059 INFO_ADIC_059");
		query.append(", b1.ID_RECHAZO_059 ID_RECHAZO_059");
		query.append(", b1.ID_REQUERIMIENTO_059 ID_REQUERIMIENTO_059");
		query.append(", s1.DETALLE_064 DETALLE_064");
		query.append(", s1.FECHA_REQ_064 FECHA_REQ_064");
		query.append(", s1.FECHA_LIMITE_064 FECHA_LIMITE_064");
		query.append(", NVL(s1.IND_SUBSANADO_064,'" + AportaSubsanacionEnum.NO_REQUERIDA.getValue()
				+ "') IND_SUBSANADO_064");
		query.append(", s1.ESTADO_SUBSANACION_064 ESTADO_SUBSANACION_064");
		query.append(", s1.FECHA_ACEPTACION_064 FECHA_ACEPTACION_064");
		query.append(", DECODE(s1.IND_SUBSANADO_064, '").append(AportaSubsanacionEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.SI.getLabel(), null, es)).append("', '")
				.append(AportaSubsanacionEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO.getLabel(), null, es)).append("', '")
				.append(AportaSubsanacionEnum.NO_REQUERIDA.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO_REQUERIDA.getLabel(), null, es)).append("NULL")
				.append("', '").append(this.msg.getMessage(AportaSubsanacionEnum.NO_REQUERIDA.getLabel(), null, es))
				.append("') AS SUBSANACIONDESCES");
		query.append(", DECODE(s1.IND_SUBSANADO_064, '").append(AportaSubsanacionEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.SI.getLabel(), null, eu)).append("', '")
				.append(AportaSubsanacionEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO.getLabel(), null, eu)).append("', '")
				.append(AportaSubsanacionEnum.NO_REQUERIDA.getValue()).append("', '")
				.append(this.msg.getMessage(AportaSubsanacionEnum.NO_REQUERIDA.getLabel(), null, eu)).append("NULL")
				.append("', '").append(this.msg.getMessage(AportaSubsanacionEnum.NO_REQUERIDA.getLabel(), null, eu))
				.append("') AS SUBSANACIONDESCEU");
		// GESTORCOLORDEREU
		query.append("," + SqlUtils.normalizarCampoSql("n1.DESC_EU") + " || "
				+ SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORCOLORDEREU");
		// GESTORCOLORDERES
		query.append("," + SqlUtils.normalizarCampoSql("n1.DESC_ES") + " || "
				+ SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORCOLORDERES");
		// GESTORVIPCOLORDER
		query.append(
				"," + SqlUtils.normalizarCampoSql("h1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("h1.APEL2_077")
						+ " || " + SqlUtils.normalizarCampoSql("h1.NOMBRE_077") + " GESTORVIPCOLORDER");

		query.append(" ,tg2.DESC_EU_026 GRUPO_TRABAJO");
		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder();

		from.append(" FROM AA79B51S01 t1 ");
		// ExpedienteInterpretacion
		from.append("LEFT JOIN AA79B52S01 i1 ON t1.ANYO_051 = i1.ANYO_052 AND t1.NUM_EXP_051 = i1.NUM_EXP_052 ");
		// ExpedienteTradRev
		from.append("LEFT JOIN AA79B53S01 r1 ON t1.ANYO_051 = r1.ANYO_053 AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");
		// GestorExpediente
		from.append("LEFT JOIN AA79B54S01 g1 ON t1.ANYO_051 = g1.ANYO_054 AND t1.NUM_EXP_051 = g1.NUM_EXP_054 ");
		// NombreApellidosGestor
		from.append("LEFT JOIN AA79B77S01 h1 ON g1.DNI_SOLICITANTE_054 = h1.DNI_077 ");
		// Gestor activo
		from.append(
				"LEFT JOIN X54JAPI_SOLICITANTES t4 ON t4.DNI = g1.DNI_SOLICITANTE_054 AND t4.TIPO_ENTIDAD =  g1.TIPO_ENTIDAD_054 AND t4.COD_ENTIDAD = g1.ID_ENTIDAD_054 ");
		// Entidad
		from.append(
				"LEFT JOIN X54JAPI_ENTIDADES_SOLIC n1 ON g1.ID_ENTIDAD_054 = n1.CODIGO AND g1.TIPO_ENTIDAD_054 = n1.TIPO ");
		// BitacoraExpediente
		from.append(
				"LEFT JOIN AA79B59S01 b1 ON t1.ANYO_051 = b1.ANYO_059 AND t1.NUM_EXP_051 = b1.NUM_EXP_059 AND t1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 ");
		// subsanacion
		from.append("LEFT JOIN AA79B64S01 s1 ON b1.ID_REQUERIMIENTO_059 = s1.ID_064 ");
		// EstadosExpediente
		from.append("LEFT JOIN AA79B60S01 e1 ON b1.ID_ESTADO_EXP_059 = e1.ID_060 ");
		// FasesExpediente
		from.append("LEFT JOIN AA79B61S01 f1 ON b1.ID_FASE_EXPEDIENTE_059 = f1.ID_061 ");
		// NombreApellidosTecnico
		from.append("LEFT JOIN AA79B77S01 k1 ON t1.DNI_TECNICO_051 = k1.DNI_077 ");
		// ModoInterpretacion
		from.append("LEFT JOIN AA79B14S01 m1 ON i1.MODO_INTERPRETACION_052 = m1.ID_014 ");
		// TipoActo
		from.append("LEFT JOIN AA79B08S01 a1 ON i1.TIPO_ACTO_052 = a1.ID_008 ");
		// Idioma
		from.append("LEFT JOIN AA79B09S01 l1 ON r1.ID_IDIOMA_053 = l1.ID_IDIOMA_009 ");
		// DirNora
		from.append("LEFT JOIN AA79B49S01 d1 ON i1.DIR_NORA_052 = d1.CDIRNORA_049 ");

		from.append(" LEFT JOIN AA79B26S01 tg2 ON tg2.ID_026 = OBTENER_GRUPO_TRABAJO(t1.ANYO_051,t1.NUM_EXP_051) ");

		return from.toString();

	}

	private String getSelectJustificanteSolicitud() {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.ANYO_069 ANYO_069");
		query.append(", t1.NUM_EXP_069 NUM_EXP_069");
		query.append(", SUBSTR(t1.ANYO_069,3,4) || '/' || LPAD(t1.NUM_EXP_069,6,'0') ANYONUMEXPCONCATENADO");
		query.append(", t1.ID_TIPO_EXPEDIENTE_069 ID_TIPO_EXPEDIENTE_069");
		query.append(
				", " + DECODE_T1ID_TIPO_EXPEDIENTE_069 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
				.append("') AS TIPO_EXPEDIENTE_ES");
		query.append(
				", " + DECODE_T1ID_TIPO_EXPEDIENTE_069 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append("') AS TIPO_EXPEDIENTE_EU");
		query.append(
				", " + DECODE_T1ID_TIPO_EXPEDIENTE_069 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_INTERPRETACION, null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_TRADUCCION, null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_REVISION, null, es)).append("') AS TIPOEXPEDIENTEDESCES");
		query.append(
				", " + DECODE_T1ID_TIPO_EXPEDIENTE_069 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_INTERPRETACION, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_TRADUCCION, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(LABEL_REVISION, null, eu)).append("') AS TIPOEXPEDIENTEDESCEU");
		query.append(", t1.TITULO_069 TITULO_069");
		query.append(", t1.FECHA_ALTA_069 FECHAALTA069");
		query.append(", TO_CHAR(t1.FECHA_ALTA_069,'HH24:MI') HORAALTA069");

		// ExpedienteInterpretacion
		query.append(", i1.MODO_INTERPRETACION_070 MODO_INTERPRETACION_070");
		query.append(", m1.DESC_ES_014 DESC_ES_014");
		query.append(", m1.DESC_EU_014 DESC_EU_014");
		query.append(", i1.TIPO_ACTO_070 TIPO_ACTO_070");
		query.append(", a1.DESC_ES_008 DESC_ES_008");
		query.append(", a1.DESC_EU_008 DESC_EU_008");
		query.append(", i1.TIPO_PETICIONARIO_070 TIPO_PETICIONARIO_070");
		query.append(", DECODE(i1.TIPO_PETICIONARIO_070, '" + TipoPeticionarioEnum.ADMIN_PUBLICA.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel(), null, es))
				.append("', '" + TipoPeticionarioEnum.PARTICULAR.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.PARTICULAR.getLabel(), null, es))
				.append("') AS TIPOPETICIONARIODESCES");
		query.append(", DECODE(i1.TIPO_PETICIONARIO_070, '" + TipoPeticionarioEnum.ADMIN_PUBLICA.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel(), null, eu))
				.append("', '" + TipoPeticionarioEnum.PARTICULAR.getValue() + "', '")
				.append(this.msg.getMessage(TipoPeticionarioEnum.PARTICULAR.getLabel(), null, eu))
				.append("') AS TIPOPETICIONARIODESCEU");
		query.append(", i1.IND_PROGRAMADA_070 IND_PROGRAMADA_070");
		query.append(", DECODE(i1.IND_PROGRAMADA_070, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS INDPROGRAMADADESCES");
		query.append(", DECODE(i1.IND_PROGRAMADA_070, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS INDPROGRAMADADESCEU");
		query.append(", i1.FECHA_INI_070 FECHA_INI_070");
		query.append(", TO_CHAR(i1.FECHA_INI_070,'HH24:MI') HORA_INI_070");
		query.append(", i1.FECHA_FIN_070 FECHA_FIN_070");
		query.append(", TO_CHAR(i1.FECHA_FIN_070,'HH24:MI') HORA_FIN_070");
		query.append(", i1.DIR_NORA_070 DIR_NORA_070");
		query.append(", i1.IND_PRESUPUESTO_070 IND_PRESUPUESTO_070");
		query.append(", DECODE(i1.IND_PRESUPUESTO_070, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PRESUPUESTODESCES");
		query.append(", DECODE(i1.IND_PRESUPUESTO_070, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PRESUPUESTODESCEU");
		query.append(", i1.PERSONA_CONTACTO_070 PERSONA_CONTACTO_070");
		query.append(", i1.EMAIL_CONTACTO_070 EMAIL_CONTACTO_070");
		query.append(", i1.TELEFONO_CONTACTO_070 TELEFONO_CONTACTO_070");
		query.append(", i1.IND_OBSERVACIONES_070 IND_OBSERVACIONES_070");
		query.append(", d1.CDIRNORA_049 CDIRNORA_049");

		// ExpedienteTradRev
		query.append(", r1.IND_PUBLICAR_BOPV_071 IND_PUBLICAR_BOPV_071");
		query.append(", DECODE(r1.IND_PUBLICAR_BOPV_071, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PUBLICARBOPVDESCES");
		query.append(", DECODE(r1.IND_PUBLICAR_BOPV_071, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PUBLICARBOPVDESCEU");
		query.append(", r1.ID_IDIOMA_071 ID_IDIOMA_071");
		query.append(", l1.DESC_IDIOMA_ES_009 DESC_IDIOMA_ES_009");
		query.append(", l1.DESC_IDIOMA_EU_009 DESC_IDIOMA_EU_009");
		query.append(", r1.IND_CONFIDENCIAL_071 IND_CONFIDENCIAL_071");
		query.append(", r1.IND_CORREDACCION_071 IND_CORREDACCION_071");
		query.append(", r1.TEXTO_071 TEXTO_071");
		query.append(", r1.TIPO_REDACCION_071 TIPO_REDACCION_071");
		query.append(", r1.PARTICIPANTES_071 PARTICIPANTES_071");
		query.append(", r1.REF_TRAMITAGUNE_071 REF_TRAMITAGUNE_071");
		query.append(", r1.NUM_TOTAL_PAL_SOLIC_071 NUM_TOTAL_PAL_SOLIC_071");
		query.append(", r1.IND_FACTURABLE_071 IND_FACTURABLE_071");
		query.append(", r1.ID_RELEVANCIA_071 ID_RELEVANCIA_071");
		query.append(", r1.IND_URGENTE_071 IND_URGENTE_071");
		query.append(", r1.FECHA_FINAL_SOLIC_071 FECHA_FINAL_SOLIC_071");
		query.append(", TO_CHAR(r1.FECHA_FINAL_SOLIC_071,'HH24:MI') HORAFINALSOLIC");
		query.append(", r1.IND_OBSERVACIONES_071 IND_OBSERVACIONES_071");
		query.append(", r1.IND_PRESUPUESTO_071 IND_PRESUPUESTO_071");

		// Observaciones
		query.append(", o1.OBSERVACIONES_072 OBSERVACIONES_072");
		query.append(", o1.NOMBRE_FICHERO_072 NOMBRE_FICHERO_072");

		// GestorExpediente
		query.append(", g1.DNI_SOLICITANTE_054 DNI_SOLICITANTE_054");
		query.append(", g1.TIPO_ENTIDAD_054 TIPO_ENTIDAD_054");
		query.append(", g1.ID_ENTIDAD_054 ID_ENTIDAD_054");
		query.append(", n1.CDIRNORA ENTIDAD_CDIRNORA");
		query.append(", n1.IVA ENTIDAD_IVA");
		query.append(", n1.FACTURABLE ENTIDAD_FACTURABLE");
		query.append(", n1.ESTADO ENTIDAD_ESTADO");
		query.append(", n1.CIF ENTIDAD_CIF");
		query.append(", n1.DESC_AMP_EU ENTIDAD_DESC_AMP_EU");
		query.append(", n1.DESC_AMP_ES ENTIDAD_DESC_AMP_ES");
		query.append(", n1.DESC_EU ENTIDAD_DESC_EU");
		query.append(", n1.DESC_ES ENTIDAD_DESC_ES");
		query.append(", g1.IND_VIP_054 IND_VIP_054");
		query.append(", " + DECODE_G1IND_VIP_054 + ", '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es))
				.append("') AS GESTOREXPEDIENTESVIPDESCES");
		query.append(", " + DECODE_G1IND_VIP_054 + ", '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu))
				.append("') AS GESTOREXPEDIENTESVIPDESCEU");
		// NombreApellidosGestor
		query.append(", h1.NOMBRE_077 NOMBRE_077");
		query.append(", h1.APEL1_077 APEL1_077");
		query.append(", h1.APEL2_077 APEL2_077");
		query.append(", h1.PREDNI_077 PREDNI_077");
		query.append(", h1.SUFDNI_077 SUFDNI_077");
		query.append(", (h1.PREDNI_077 || g1.DNI_SOLICITANTE_054 || h1.SUFDNI_077) AS DNICOMPLETO");

		return query.toString();
	}

	private String getFromJustificanteSolicitud() {
		StringBuilder from = new StringBuilder();

		from.append(" FROM AA79B69S01 t1 ");
		// ExpedienteInterpretacion
		from.append("LEFT JOIN AA79B70S01 i1 ON t1.ANYO_069 = i1.ANYO_070 AND t1.NUM_EXP_069 = i1.NUM_EXP_070 ");
		// ExpedienteTradRev
		from.append("LEFT JOIN AA79B71S01 r1 ON t1.ANYO_069 = r1.ANYO_071 AND t1.NUM_EXP_069 = r1.NUM_EXP_071 ");
		// Observaciones
		from.append("LEFT JOIN AA79B72S01 o1 ON t1.ANYO_069 = o1.ANYO_072 AND t1.NUM_EXP_069 = o1.NUM_EXP_072 ");
		// GestorExpediente
		from.append("LEFT JOIN AA79B54S01 g1 ON t1.ANYO_069 = g1.ANYO_054 AND t1.NUM_EXP_069 = g1.NUM_EXP_054 ");
		// NombreApellidosGestor
		from.append("LEFT JOIN AA79B77S01 h1 ON g1.DNI_SOLICITANTE_054 = h1.DNI_077 ");
		// Entidad
		from.append(
				"LEFT JOIN X54JAPI_ENTIDADES_SOLIC n1 ON g1.ID_ENTIDAD_054 = n1.CODIGO AND g1.TIPO_ENTIDAD_054 = n1.TIPO ");
		// ModoInterpretacion
		from.append("LEFT JOIN AA79B14S01 m1 ON i1.MODO_INTERPRETACION_070 = m1.ID_014 ");
		// TipoActo
		from.append("LEFT JOIN AA79B08S01 a1 ON i1.TIPO_ACTO_070 = a1.ID_008 ");
		// Idioma
		from.append("LEFT JOIN AA79B09S01 l1 ON r1.ID_IDIOMA_071 = l1.ID_IDIOMA_009 ");
		// DirNora
		from.append("LEFT JOIN AA79B49S01 d1 ON i1.DIR_NORA_070 = d1.CDIRNORA_049 ");

		return from.toString();

	}

	@Override
	protected RowMapper<Expediente> getRwMap() {
		return this.rwMap;
	}

	protected RowMapper<ExpedientePlanificacion> getRwMapBusqGen(boolean esInterpretacion) {
		return this.rwMapBusqGen;
	}

	protected RowMapper<ExpedientePlanificacion> getRwMapConsultaPlanifExp() {
		return this.rwMapConsultaPlanifExp;
	}

	protected RowMapper<ExpedientePlanificacion> getRwMapConsultaPlanifExpIds() {
		return this.rwMapConsultaPlanifExpIds;
	}

	@Override
	protected String[] getOrderBy() {
		return ExpedienteDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return ANYO_EXPEDIENTE + "," + NUM_EXPEDIENTE;
	}

	@Override
	protected RowMapper<Expediente> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(Expediente bean, List<Object> params) {
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		return "WHERE t1.ANYO_051 = ? AND t1.NUM_EXP_051 = ?";
	}

	@Override
	protected String getWhere(Expediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		ExpedienteDaoUtils.getWhereAux(bean, params, where);
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put(QUERY, where);
		mapWhere.put(PARAMS, params);

		return where.toString();
	}

	private String getWhereJustificanteSolicitud(Expediente bean, List<Object> params) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		ExpedienteDaoUtils.getWhereJustificanteSolicitudAux(bean, params, where);
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put(QUERY, where);
		mapWhere.put(PARAMS, params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(Expediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		ExpedienteDaoUtils.getWhereLikeAux(bean, startsWith, params, where);

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put(QUERY, where);
		mapWhere.put(PARAMS, params);

		return where.toString();
	}

	/**
	 * Funciones para evitar Nullpointers - FIN
	 *
	 */

	/**
	 * Funciones de carga de tabla en estudioExpedientes. Filtro de estado de
	 * expediente 'en estudio' 2
	 *
	 * INICIO
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Expediente> filtroExpedienteEstadoEnEstudio(Expediente bean, JQGridRequestDto jqGridRequestDto,
			boolean startsWith) {

		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		if (bean.getBitacoraExpediente() != null && bean.getBitacoraExpediente().getEstadoExp() != null) {
			bean.getBitacoraExpediente().getEstadoExp().setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		} else {
			BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			EstadosExpediente estadoExpediente = new EstadosExpediente();
			estadoExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			bitacoraExpediente.setEstadoExp(estadoExpediente);
			bean.setBitacoraExpediente(bitacoraExpediente);

		}
		query.append(this.getFrom(bean, params));
		query.append(DaoConstants.WHERE_1_1);
		query.append(this.getWhereLike(bean, startsWith, params, false));
		if (bean.getIdTipoExpediente() == null) {
			query.append(this.getWhereTipoTradRev(bean, startsWith, params, false));
		}
		ExpedienteDaoUtils.paginatedQueryAux(jqGridRequestDto, paginatedQuery, query);

		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMap, params.toArray());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Expediente> filtroExpedienteEstadoEnCurso(Expediente bean, JQGridRequestDto jqGridRequestDto,
			boolean startsWith) {

		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		query.append(this.getFrom(bean, params));
		query.append(DaoConstants.WHERE_1_1);
		query.append(this.getWhereLike(bean, startsWith, params, false));
		if (bean.getIdTipoExpediente() == null) {
			query.append(this.getWhereTipoTradRev(bean, startsWith, params, false));
		}
		ExpedienteDaoUtils.filtroExpedienteEstadoEnCursoAux(jqGridRequestDto, paginatedQuery, query);

		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMap, params.toArray());
	}

	/**
	 *
	 * Funcion que si no se especifica el tipo de expediente que se desea
	 * visualizar, los busca de tipo traduccion o revision
	 */
	private String getWhereTipoTradRev(Expediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.AND);
		where.append(DaoConstants.ABRIR_PARENTESIS);
		where.append(DaoConstants.ABRIR_PARENTESIS);
		where.append(SqlUtils.generarWhereLikeWithoutAnd(DBConstants.T1ID_TIPO_EXPEDIENTE_051,
				TipoExpedienteEnum.TRADUCCION.getValue(), params, false));
		where.append(DaoConstants.CERRAR_PARENTESIS);
		where.append(DaoConstants.OR);
		where.append(DaoConstants.ABRIR_PARENTESIS);
		where.append(SqlUtils.generarWhereLikeWithoutAnd(DBConstants.T1ID_TIPO_EXPEDIENTE_051,
				TipoExpedienteEnum.REVISION.getValue(), params, false));
		where.append(DaoConstants.CERRAR_PARENTESIS);
		where.append(DaoConstants.CERRAR_PARENTESIS);

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put(QUERY, where);
		mapWhere.put(PARAMS, params);

		return where.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public Long filtroExpedienteEstadoEnEstudioCount(Expediente bean, boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		params.add(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		StringBuilder query = new StringBuilder("SELECT COUNT(1)");
		query.append(this.getFrom(bean, params));
		query.append(DaoConstants.WHERE_1_1);
		query.append(" AND b1.ID_ESTADO_EXP_059 = ?");
		query.append(this.getWhereLike(bean, startsWith, params, false));
		if (bean.getIdTipoExpediente() == null) {
			query.append(this.getWhereTipoTradRev(bean, startsWith, params, false));
		}

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Long filtroExpedienteEstadoEnCursoCount(Expediente bean, boolean startsWith) {
		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT COUNT(1)");
		query.append(this.getFrom(bean, params));
		query.append(DaoConstants.WHERE_1_1);
		query.append(this.getWhereLike(bean, startsWith, params, false));
		if (bean.getIdTipoExpediente() == null) {
			query.append(this.getWhereTipoTradRev(bean, startsWith, params, false));
		}
		ExpedienteDaoUtils.filtroExpedienteEstadoEnCursoAux(null, paginatedQuery, query);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public List<TableRowDto<Expediente>> reorderSelection(Expediente filterExpediente,
			JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		// SELECT
		List<Object> params = new ArrayList<Object>();
		StringBuilder subQuery = new StringBuilder(this.getSelect());
		// FROM
		subQuery.append(this.getFrom(filterExpediente, params));
		// FILTRADO
		subQuery.append(DaoConstants.WHERE_1_1);
		subQuery.append(this.getWhereLike(filterExpediente, startsWith, params, false));
		if (filterExpediente.getIdTipoExpediente() == null) {
			subQuery.append(this.getWhereTipoTradRev(filterExpediente, startsWith, params, false));
		}

		StringBuilder query = new StringBuilder();
		query.append(" select " + this.getPK() + ", page, pageLine, tableLine from" + " ( select " + this.getPK()
				+ " , ceil(rownum/" + jqGridRequestDto.getRows() + ") page, case when (mod(rownum,"
				+ jqGridRequestDto.getRows() + ")=0) " + "then '" + jqGridRequestDto.getRows()
				+ "' else TO_CHAR(mod(rownum," + jqGridRequestDto.getRows()
				+ ")) end as pageLine, rownum as tableLine from (");
		query.append(subQuery.toString());
		query.append(")" + DaoConstants.WHERE_1_1);
		List<String> listSelectedIds = jqGridRequestDto.getMultiselection().getSelectedIds();
		ExpedienteDaoUtils.getSelectedIdsAux(params, query, listSelectedIds);
		query.append(")");

		RowNumResultSetExtractor<Expediente> rowNumOrder = new RowNumResultSetExtractor<Expediente>(this.getRwMapPK(),
				jqGridRequestDto);

		return this.getJdbcTemplate().query(query.toString(), rowNumOrder, params.toArray());
	}

	/**
	 * Funciones de carga de tabla en estudioExpedientes. Filtro de estado de
	 * expediente 'en estudio' 2
	 *
	 * FIN
	 */

	@Override
	public Integer asignarTecnicoAExpedientes(ListaExpediente listaExpedientes) {
		StringBuilder query = new StringBuilder();
		if (listaExpedientes != null && listaExpedientes.getListaExpediente() != null
				&& !listaExpedientes.getListaExpediente().isEmpty()
				&& listaExpedientes.getListaExpediente().get(0).getTecnico() != null) {
			List<Object> params = new ArrayList<Object>();
			query.append(UPDATEAA79B51S01);
			query.append("SET DNI_TECNICO_051 = ?");
			query.append(DaoConstants.WHERE);
			params.add(listaExpedientes.getListaExpediente().get(0).getTecnico().getDni());
			for (Expediente expediente : listaExpedientes.getListaExpediente()) {
				query.append(ANYO_051_AND_NUMEXP_051EQUALS);
				params.add(expediente.getAnyo());
				params.add(expediente.getNumExp());
				query.append(" OR ");
			}
			query.delete(query.length() - 3, query.length());
			return this.getJdbcTemplate().update(query.toString(), params.toArray());

		}

		return 0;
	}

	// EXPEDIENTES RELACIONADOS - INICIO
	@Override
	public List<Expediente> obtenerExpedientesRelacionados(Expediente filterExpediente,
			JQGridRequestDto jqGridRequestDto, boolean startsWith, boolean noRelacionados) {
		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		if (!noRelacionados) {
			filterExpediente.setEstadoBitacora(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		}
		query.append(this.getFrom(filterExpediente, params));
		query.append(this.getWhereLikeRelacionados(filterExpediente, startsWith, params, false, noRelacionados));
		ExpedienteDaoUtils.filtroExpedienteEstadoEnCursoAux(jqGridRequestDto, paginatedQuery, query);
		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMap, params.toArray());
	}

	@Override
	public Long obtenerExpedientesRelacionadosCount(Expediente filterExpediente, boolean startsWith,
			boolean noRelacionados) {
		List<Object> params = new ArrayList<Object>();
		if (noRelacionados) {
			filterExpediente.setEstadoBitacora(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		}
		StringBuilder query = new StringBuilder("SELECT COUNT(1)");
		query.append(this.getFrom(filterExpediente, params));
		query.append(this.getWhereLikeRelacionados(filterExpediente, startsWith, params, false, noRelacionados));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	private String getWhereLikeRelacionados(Expediente filterExpediente, boolean startsWith, List<Object> params,
			boolean b, boolean noRelacionados) {
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		ExpedienteDaoUtils.getWhereLikeRelacionadosAux(filterExpediente, startsWith, params, noRelacionados, where);

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put(QUERY, where);
		mapWhere.put(PARAMS, params);

		return where.toString();
	}

	@Override
	public Integer guardarRelacionExpediente(Expediente expedienteARelacionar) {
		StringBuilder insert = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		insert.append("INSERT INTO AA79B57S01 ");
		insert.append("VALUES (?, ?, ?, ?)");
		params.add(expedienteARelacionar.getAnyo());
		params.add(expedienteARelacionar.getNumExp());
		if (expedienteARelacionar.getExpedienteRelacionado() != null) {
			params.add(expedienteARelacionar.getExpedienteRelacionado().getAnyoExpRel());
			params.add(expedienteARelacionar.getExpedienteRelacionado().getNumExpRel());
		} else {
			return -1;
		}
		return this.getJdbcTemplate().update(insert.toString(), params.toArray());
	}

	@Override
	public List<CamposSubsanacion> findCamposSub(String tipoExp) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(
				"SELECT ID_078 ID078,DESC_ES_078 DESCES078, DESC_EU_078 DESCEU078, NAME_AA79_078 NAMEAA79078, NAME_AA06_078 NAMEAA06078");
		query.append(" FROM AA79B78S01 WHERE ID_TIPO_EXPEDIENTE_078 = ? ");
		params.add(tipoExp);

		return this.getJdbcTemplate().query(query.toString(), this.rwMapCampoSub, params.toArray());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override()
	public Integer comprobarRequisitosDocu(final Expediente expediente) {

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));

		return (Integer) this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{? = call COMPROBAR_FINALIZACION_ESTUDIO(?,?)}");
				callableStatement.registerOutParameter(1, Types.NUMERIC);
				callableStatement.setLong(2, expediente.getAnyo());
				callableStatement.setInt(3, expediente.getNumExp());
				return callableStatement;
			}
		}, new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getInt(1);
			}
		});

	}

	@Override
	public SubsanacionExpediente registrarSubsanacion(SubsanacionExpediente subsanacionExpediente) {

		String query = "INSERT INTO AA79B64S01 (ID_064,DETALLE_064,FECHA_REQ_064,FECHA_LIMITE_064,IND_SUBSANADO_064,ESTADO_SUBSANACION_064,FECHA_ACEPTACION_064, PRESUPUESTO_064,FECHA_ENTREGA_064,OBSRV_RECHAZO_064,DNI_RECURSO_064,TIPO_REQUERIMIENTO_064,IND_DOC_NUEVOS_064) VALUES (?,?,SYSDATE,?,'N',?,NULL,NULL,?,NULL,NULL,?,?)";
		this.getJdbcTemplate().update(query, subsanacionExpediente.getId(), subsanacionExpediente.getDetalle(),
				DateUtils.obtFechaHoraFormateada(
						subsanacionExpediente.getFechaLimite(), subsanacionExpediente.getHoraLimite()),
				EstadoRequerimientoEnum.PENDIENTE.getValue(),
				DateUtils.obtFechaHoraFormateada(subsanacionExpediente.getFechaEntrega(),
						subsanacionExpediente.getHoraEntrega()),
				subsanacionExpediente.getTipoRequerimiento() == null
						|| subsanacionExpediente.getTipoRequerimiento().equals(Long.valueOf(Constants.CERO))
								? TipoRequerimientoEnum.SUBSANACION.getValue()
								: subsanacionExpediente.getTipoRequerimiento(),
				subsanacionExpediente.getIndDocNuevos());
		return subsanacionExpediente;

	}

	/**
	 *
	 * @param subsanacionExpediente SubsanacionExpediente
	 * @return SubsanacionExpediente
	 */
	@Override
	public SubsanacionExpediente registrarSubsanacionTrados(SubsanacionExpediente subsanacionExpediente) {

		String query = "INSERT INTO AA79B64S01 (ID_064,DETALLE_064,FECHA_REQ_064,FECHA_LIMITE_064,IND_SUBSANADO_064,ESTADO_SUBSANACION_064,"
				+ "FECHA_ACEPTACION_064, PRESUPUESTO_064,FECHA_ENTREGA_064,OBSRV_RECHAZO_064,DNI_RECURSO_064, TIPO_REQUERIMIENTO_064) "
				+ "VALUES (?,?,SYSDATE,?,'N',?,NULL,?,?,NULL,NULL,?)";

		this.getJdbcTemplate().update(query, subsanacionExpediente.getId(), subsanacionExpediente.getDetalle(),
				subsanacionExpediente.getFechaLimite(), EstadoRequerimientoEnum.PENDIENTE.getValue(),
				subsanacionExpediente.getPresupuesto(), subsanacionExpediente.getFechaEntrega(),
				subsanacionExpediente.getTipoRequerimiento() == null
						|| subsanacionExpediente.getTipoRequerimiento().equals(Long.valueOf(Constants.CERO))
								? TipoRequerimientoEnum.SUBSANACION.getValue()
								: subsanacionExpediente.getTipoRequerimiento());
		return subsanacionExpediente;

	}

	@Override
	public void actualizarSubsanacion(SubsanacionExpediente subsanacionExpediente) {

		String query = "UPDATE AA79B64S01 SET ESTADO_SUBSANACION_064 = ? , FECHA_ACEPTACION_064 = SYSDATE WHERE ID_064 = ? ";
		this.getJdbcTemplate().update(query, subsanacionExpediente.getEstado(), subsanacionExpediente.getId());

	}

	@Override
	public void actualizarSubsanacionConObsvRechazo(SubsanacionExpediente subsanacionExpediente) {

		String query = "UPDATE AA79B64S01 SET ESTADO_SUBSANACION_064 = ? , FECHA_ACEPTACION_064 = SYSDATE , OBSRV_RECHAZO_064 = ? WHERE ID_064 = ? ";
		this.getJdbcTemplate().update(query, subsanacionExpediente.getEstado(), subsanacionExpediente.getId());

	}

	@Override
	public void updateFechaLimite(SubsanacionExpediente subsanacionExpediente) {

		String query = "UPDATE AA79B64S01 SET FECHA_LIMITE_064 = ? WHERE ID_064 = ? ";
		this.getJdbcTemplate().update(query, DateUtils.obtFechaHoraFormateada(subsanacionExpediente.getFechaLimite(),
				subsanacionExpediente.getHoraLimite()), subsanacionExpediente.getId());

	}

	@Override
	public List<CamposSelecSub> getCampoSubsanacion(SubsanacionExpediente bean) {

		List<Object> params = new ArrayList<Object>();
		String query = "SELECT ID_065, ID_CAMPO_065, NAME_AA06_078 FROM AA79B65S01 LEFT JOIN AA79B78S01 ON ID_CAMPO_065 = ID_078 WHERE ID_065 = ? ";
		params.add(bean.getId());

		return this.getJdbcTemplate().query(query.toString(), this.rwMapCamposSelecSub, params.toArray());
	}

	@Override
	public List<DocusSelecSub> getDocuSubsanacion(SubsanacionExpediente bean) {

		List<Object> params = new ArrayList<Object>();
		String query = "SELECT ID_066, ID_DOC_066 FROM AA79B66S01 WHERE ID_066 = ? ";
		params.add(bean.getId());

		return this.getJdbcTemplate().query(query.toString(), this.rwMapDocusSelecSub, params.toArray());
	}

	@Override
	public void putCampoSubsanacion(CamposSelecSub camposSelecSub) {

		String query = "INSERT INTO AA79B65S01 (ID_065,ID_CAMPO_065) VALUES (?,?)";
		this.getJdbcTemplate().update(query, camposSelecSub.getId065(), camposSelecSub.getIdcampo065());
	}

	@Override
	public void putDocuSubsanacion(DocusSelecSub docuSelecSub) {

		String query = "INSERT INTO AA79B66S01 (ID_066,ID_DOC_066) VALUES (?,?)";
		this.getJdbcTemplate().update(query, docuSelecSub.getId066(), docuSelecSub.getIddoc066());
	}

	@Override
	public Expediente comprobarRequisitosSub(Expediente expedientes) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("SELECT e.ANYO_051 ANYO051, ");
		query.append("e.NUM_EXP_051 NUMEXP051, ");
		query.append("b.ID_FASE_EXPEDIENTE_059 IDFASEEXPEDIENTE059, ");
		query.append("b.ID_ESTADO_EXP_059 IDESTADOEXP059, ");
		query.append("s.DETALLE_064 DETALLE064, ");
		query.append("s.ID_064 ID064, ");
		query.append("s.IND_SUBSANADO_064 INDSUBSANADO064, ");
		query.append("TO_DATE(TO_CHAR(s.FECHA_LIMITE_064,'YYYY/MM/DD'),'YYYY/MM/DD') FECHALIMITE064, ");
		query.append("TO_CHAR(s.FECHA_LIMITE_064,'HH24:MI') HORALIMITE064, ");
		query.append("s.ESTADO_SUBSANACION_064 ESTADOSUBSANACION064 ");
		query.append(FROMAA79B51S01ELEFTJOINAA79B59S01B);
		query.append(ONEANYO_051_EQUALS_BANYO_059AND);
		query.append(ENUM_EXP_051_EQUALS_BNUM_EXP_059AND);
		query.append(EESTADO_BITACORA_051_EQUALS_ID_ESTADO_BITACORA_059);
		query.append(LEFTJOINAA79B64S01S);
		query.append(ONBID_REQUERIMIENTO_059_EQUALS_SID_064);
		query.append("WHERE e.ANYO_051 = ? AND e.NUM_EXP_051 = ? ");

		params.add(expedientes.getAnyo());
		params.add(expedientes.getNumExp());
		List<Expediente> listExp = this.getJdbcTemplate().query(query.toString(), this.rwMapEstadoSub,
				params.toArray());
		return DataAccessUtils.uniqueResult(listExp);

	}

	@Override
	public List<Expediente> comprobarCamposSeleccionados(Expediente expedientes) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(
				"SELECT e.ANYO_051 ANYO051, e.NUM_EXP_051 NUMEXP051, b.ID_REQUERIMIENTO_059 IDREQUERIMIENTO059, s.ID_064 ID064, p.ID_065 ID065, z.NAME_AA79_078 NAMEAA79078 ");
		query.append(FROMAA79B51S01ELEFTJOINAA79B59S01B);
		query.append(ONEANYO_051_EQUALS_BANYO_059AND);
		query.append(ENUM_EXP_051_EQUALS_BNUM_EXP_059AND);
		query.append(EESTADO_BITACORA_051_EQUALS_ID_ESTADO_BITACORA_059);
		query.append(LEFTJOINAA79B64S01S);
		query.append(ONBID_REQUERIMIENTO_059_EQUALS_SID_064);
		query.append("LEFT JOIN AA79B65S01 p ");
		query.append("ON s.ID_064 = p.ID_065 ");
		query.append("LEFT JOIN AA79B78S01 z ");
		query.append("ON p.ID_CAMPO_065 = z.ID_078 ");
		query.append("WHERE e.ANYO_051 = ? ");
		query.append("AND e.NUM_EXP_051 = ? ");
		query.append("AND b.ID_ESTADO_BITACORA_059 = ? ");

		params.add(expedientes.getAnyo());
		params.add(expedientes.getNumExp());
		params.add(expedientes.getBitacoraExpediente().getIdEstadoBitacora());
		return this.getJdbcTemplate().query(query.toString(), this.rwMapCamposSelec, params.toArray());
	}

	@Override
	public List<Expediente> comprobarDocumentosSeleccionados(Expediente expedientes) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(
				"SELECT e.ANYO_051 ANYO051, e.NUM_EXP_051 NUMEXP051, b.ID_REQUERIMIENTO_059 IDREQUERIMIENTO059, s.ID_064 ID064, p.ID_066 ID066, p.ID_DOC_066 IDDOC066, z.ID_DOC_056 IDDOC056 ");
		query.append(FROMAA79B51S01ELEFTJOINAA79B59S01B);
		query.append(ONEANYO_051_EQUALS_BANYO_059AND);
		query.append(ENUM_EXP_051_EQUALS_BNUM_EXP_059AND);
		query.append(EESTADO_BITACORA_051_EQUALS_ID_ESTADO_BITACORA_059);
		query.append(LEFTJOINAA79B64S01S);
		query.append(ONBID_REQUERIMIENTO_059_EQUALS_SID_064);
		query.append("LEFT JOIN AA79B66S01 p ");
		query.append("ON s.ID_064 = p.ID_066 ");
		query.append("LEFT JOIN AA79B56S01 z ");
		query.append("ON p.ID_DOC_066 = z.ID_DOC_056 ");
		query.append("WHERE e.ANYO_051 = ? ");
		query.append("AND e.NUM_EXP_051 = ? ");
		query.append("AND b.ID_ESTADO_BITACORA_059 = ? ");

		params.add(expedientes.getAnyo());
		params.add(expedientes.getNumExp());
		params.add(expedientes.getBitacoraExpediente().getIdEstadoBitacora());
		return this.getJdbcTemplate().query(query.toString(), this.rwMapDocusSelec, params.toArray());
	}

	@Override
	public Integer modificarEstado(Expediente expedientes) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(UPDATEAA79B51S01);
		query.append("SET ESTADO_BITACORA_051 = ?");
		query.append(DaoConstants.WHERE);
		query.append(ANYO_051_AND_NUMEXP_051EQUALS);
		params.add(expedientes.getBitacoraExpediente().getIdEstadoBitacora());
		params.add(expedientes.getAnyo());
		params.add(expedientes.getNumExp());

		return this.getJdbcTemplate().update(query.toString(), params.toArray());

	}
	// EXPEDIENTES RELACIONADOS - FIN

	@Override
	public List<String> getTitulosExpediente(EntradaGestoresRepresentante bean) {

		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT TITULO_051 ");
		query.append("FROM AA79B51S01 JOIN AA79B54S01 ");
		query.append("ON ANYO_051 = ANYO_054 AND ");
		query.append("NUM_EXP_051 = NUM_EXP_054 ");
		query.append(
				"LEFT JOIN X54JAPI_SOLICITANTES ON COD_ENTIDAD = ID_ENTIDAD_054 AND TIPO_ENTIDAD = TIPO_ENTIDAD_054 AND DNI = DNI_REPRESENTANTE_054 ");
		query.append("WHERE (DNI_SOLICITANTE_054 = ? OR (DNI_REPRESENTANTE_054 = ? AND ");
		query.append(" COORDINADOR = ?)) ");
		params.add(bean.getDni());
		params.add(bean.getDni());
		params.add(Constants.SI);
		if (bean.getAnyo() != null) {
			query.append(" AND SUBSTR(ANYO_051,3,4) = ? ");
			params.add(bean.getAnyo());
		}
		query.append(" ORDER BY TITULO_051 ASC");

		return this.getJdbcTemplate().queryForList(query.toString(), params.toArray(), String.class);
	}

	@Override
	public SubsanacionExpediente getDetalleSubsanacion(Expediente bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append("SELECT ID_REQUERIMIENTO_059, DETALLE_064, FECHA_LIMITE_064, IND_DOC_NUEVOS_064 ");
		query.append("FROM AA79B51S01, AA79B59S01, AA79B64S01 ");
		query.append("WHERE ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059 AND ");
		query.append(ANYO_051_EQUALS_ANYO_059AND);
		query.append("NUM_EXP_051 = NUM_EXP_059 AND ");
		query.append("ID_REQUERIMIENTO_059 = ID_064 AND ");
		query.append("ANYO_051 = ? AND ");
		query.append("NUM_EXP_051 = ? AND ");
		query.append("ESTADO_BITACORA_051 = ? AND ");
		query.append("IND_SUBSANADO_064 = ? AND ");
		query.append("ESTADO_SUBSANACION_064 = ? ");

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		params.add(bean.getEstadoBitacora());
		params.add(Constants.NO);
		params.add(EstadoRequerimientoEnum.PENDIENTE.getValue());

		List<SubsanacionExpediente> listaSubsanacionExpediente = this.getJdbcTemplate().query(query.toString(),
				this.rwMapDetalleSub, params.toArray());
		return DataAccessUtils.uniqueResult(listaSubsanacionExpediente);
	}

	@Override
	@Transactional(readOnly = true)
	public Long countGestorExpPdteTramCliente(EntradaGestoresRepresentante bean) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT COUNT(1) ");
		query.append("FROM AA79B51T00, AA79B54T00, AA79B59T00 ");
		query.append("WHERE ANYO_051 = ANYO_054 AND ");
		query.append("NUM_EXP_051 = NUM_EXP_054 AND ");
		query.append("DNI_SOLICITANTE_054 = ? AND ");
		query.append(ANYO_051_EQUALS_ANYO_059AND);
		query.append("NUM_EXP_051 = NUM_EXP_059 AND ");
		query.append(ANYO_051_EQUALS_ANYO_059AND);
		query.append("ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059 ");
		query.append(" AND ESTADO_BAJA_051 = 'A' AND");
		query.append(
				"((ID_ESTADO_EXP_059 = ? AND ID_FASE_EXPEDIENTE_059 = ? ) OR (ID_ESTADO_EXP_059 = ? AND ID_FASE_EXPEDIENTE_059 = ? ))");

		params.add(bean.getDni());
		params.add(EstadoExpedienteEnum.EN_ESTUDIO.getValue());
		params.add(FaseExpedienteEnum.PDTE_TRAM_SUBSANACION.getValue());
		params.add(EstadoExpedienteEnum.EN_CURSO.getValue());
		params.add(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue());

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Integer guardarPersona(PersonalIZO persona) {
		StringBuilder query = new StringBuilder();
		query.append(
				"INSERT INTO AA79B77S01 (DNI_077, TIPIDEN_077 , PREDNI_077, SUFDNI_077 , NOMBRE_077 , APEL1_077 , APEL2_077 ) "
						+ "VALUES (?,?,?,?,?,?,?)");
		return this.getJdbcTemplate().update(query.toString(), persona.getDni(), persona.getTipIden(),
				persona.getPreDni(), persona.getSufDni(), persona.getNombre(), persona.getApellido1(),
				persona.getApellido2());
	}

	/**
	 * Función para invocar desde el WebService aa79bExpedienteWS (métod
	 * obtenerDatosFechaFinalizacionWS) que obtiene el plazo mínimo de entrega a
	 * partir de los params de entrada
	 *
	 * @author javarona
	 *
	 * @param bean   EntradaFechaFinalizacion
	 * @param String gestorExpedientesVIP
	 * @return Date
	 */
	@Override
	public Date llamadaFncObtenerPlazoMinEntrega(final EntradaFechaFinalizacion bean,
			final String gestorExpedientesVIP) {

		return this.getJdbcTemplate().execute(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{?= call OBTENER_PLAZO_MINIMO_ENTREGA(?,?,?,?)}");
				callableStatement.registerOutParameter(1, Types.DATE);

				callableStatement.setString(2, bean.getTipoExpediente());
				callableStatement.setLong(3, bean.getNumPalabras());
				callableStatement.setString(4, bean.getIndPresupuesto());
				callableStatement.setString(5, gestorExpedientesVIP);

				return callableStatement;
			}
		}, new CallableStatementCallback<Date>() {

			@Override
			public Date doInCallableStatement(CallableStatement cs) throws SQLException {
				cs.execute();
				return cs.getTimestamp(1);
			}
		});

	}

	@Override
	public Long leerIdEstadoBitacora(Expediente bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		StringBuilder query = new StringBuilder(
				"SELECT ESTADO_BITACORA_051 IDESTADOBITACORA FROM AA79B51S01 WHERE ANYO_051 = ? AND NUM_EXP_051 = ?");
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public List<Aa79bSolicitud> findSolicitud(Expediente bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		StringBuilder query = new StringBuilder(this.getSelectSolicitud());
		query.append("FROM AA79B69S01 a ");
		query.append("LEFT JOIN AA79B70S01 b ON ");
		query.append("a.ANYO_069 = b.ANYO_070 AND ");
		query.append("a.NUM_EXP_069 = b.NUM_EXP_070 ");
		query.append("LEFT JOIN AA79B71S01 c ON ");
		query.append("a.ANYO_069 = c.ANYO_071 AND ");
		query.append("a.NUM_EXP_069 = c.NUM_EXP_071 ");
		query.append("WHERE ANYO_069 = ? ");
		query.append("AND NUM_EXP_069 = ? ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapSolicitud, params.toArray());
	}

	@Override
	public List<DocumentosExpediente> documentosRelacionados(Expediente expediente) {
		List<Object> params = new ArrayList<Object>();
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		StringBuilder query = new StringBuilder(this.getSelectDocumentosSolicitud());
		query.append("FROM AA79B73S01 t1 ");
		query.append("LEFT JOIN AA79B75S01 t2 ON t1.CLASE_DOCUMENTO_073 = t2.ID_075 ");
		query.append("LEFT JOIN AA79B42S01 t3 ON t1.TIPO_DOCUMENTO_073 = t3.ID_042 ");
		query.append("WHERE 1=1 AND t1.ANYO_073 = ? AND t1.NUM_EXP_073 = ? ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapDocumentosRelacionados, params.toArray());

	}

	@Override
	public Integer updateIndSubsanacion(SubsanacionExpediente bean) {
		StringBuilder update = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		update.append(
				" UPDATE AA79B64S01 SET IND_SUBSANADO_064 = ? , FECHA_SUBSANACION_064 = SYSDATE WHERE ID_064 = ? ");

		return this.getJdbcTemplate().update(update.toString(), Constants.SI, bean.getId());
	}

	@Override
	public List<Aa79bExpedienteRelacionado> expRelacionadasFind(Expediente bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		StringBuilder query = new StringBuilder(this.getSelectExpRelacionadas());
		query.append("FROM AA79B74T00 ");
		query.append("WHERE ANYO_074 = ? AND NUM_EXP_074 = ? ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapExpRelacionadas, params.toArray());
	}

	@Override
	public List<Expediente> getJustificanteSolicitud(Expediente bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelectJustificanteSolicitud());
		query.append(this.getFromJustificanteSolicitud());

		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(this.getWhereJustificanteSolicitud(bean, params));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapJustiSolicitud, params.toArray());
	}

	@Override
	public Integer countGruposTrabajoInterpretacion(String dni) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		params.add(dni);
		params.add(TipoExpedienteGrupoEnum.INTERPRETACION.getValue());
		params.add(dni);
		params.add(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue());
		query.append(DaoConstants.SELECT + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.TABLA_26 + DaoConstants.WHERE);
		query.append(" DNI_RESPONSABLE_026 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + " ID_TIPO_EXPEDIENTE_026 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_RESTA + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.TABLA_26 + DaoConstants.WHERE);
		query.append(" DNI_RESPONSABLE_026 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + " ID_TIPO_EXPEDIENTE_026 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + " SUMCOUNT " + DaoConstants.FROM
				+ DaoConstants.DUAL);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Object busquedageneral(ExpedientePlanificacion filter, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount) {
		boolean esInterpretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(filter.getIdTipoExpediente())) {
			esInterpretacion = true;
		}
		List<Object> params = new ArrayList<Object>();
		StringBuilder select = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		this.getSelectBusquedaGeneral(filter, select, isCount, esInterpretacion);

		if (!isCount && !esInterpretacion) {
			params.add(filter.getPalTrados()); // para REQUIERE_TRADOS
		}

		select.append(SqlUtils.generarWhereIgual("t1.ESTADO_BAJA_051", Constants.ALTA, params));

		// MODIFICADO PARA VER LOS EXPEDIENTES CERRADOS en el buscador de
		// planificación
		Object[] arrayEstados = { EstadoExpedienteEnum.EN_CURSO.getValue(), EstadoExpedienteEnum.CERRADO.getValue() };
		select.append(SqlUtils.generarWhereIn(" b1.ID_ESTADO_EXP_059 ", arrayEstados, params));

		// filtros formulario busqueda pantalla
		select.append(getWherebusquedageneral(filter, params, startsWith, esInterpretacion));

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapBusqGen(esInterpretacion),
					params.toArray());
		}
	}

	@Override
	public Object consultaPlanificacionExpediente(ExpedientePlanificacion filter, JQGridRequestDto jqGridRequestDto,
			boolean startsWith, boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder select = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		boolean esInterpretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(filter.getIdTipoExpediente())) {
			esInterpretacion = true;
		}

		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		planificacionExpedienteUtils.getSelectConsultaPlanificacionExpediente(select, isCount, esInterpretacion);

		select.append(planificacionExpedienteUtils.getWhereConsultaPlanificacionExpediente(filter, params, startsWith,
				esInterpretacion));

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapConsultaPlanifExp(),
					params.toArray());
		}
	}

	private String getWherebusquedageneral(ExpedientePlanificacion filter, List<Object> params, boolean startsWith,
			boolean esInterpretacion) {
		// FILTROS BUSCADOR
		StringBuilder where = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		// NUM EXPEDIENTE
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));
		// FASE EXPEDIENTE
		ExpedienteDaoUtils.getWhereFasesExpedientes(filter, where);
		// PRIORITARIO
		where.append(SqlUtils.generarWhereLike("t1.IND_PRIORITARIO_051", filter.getPrioridad(), params, false));
		// GESTOR
		if (filter.getGestorExpediente() != null
				&& ExpedienteDaoUtils.solicitanteValido(filter.getGestorExpediente())) {
			where.append(SqlUtils.generarWhereLike("g1.IND_VIP_054",
					filter.getGestorExpediente().getSolicitante().getGestorExpedientesVIP(), params, false));
		}

		// TIPO DE EXPEDIENTE
		if (null != filter.getIdTipoExpediente()) {
			if (filter.getIdTipoExpediente().equals(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode())) {
				List<String> listaExpedientes = new ArrayList<String>();
				listaExpedientes.add(TipoExpedienteEnum.REVISION.getValue());
				listaExpedientes.add(TipoExpedienteEnum.TRADUCCION.getValue());
				where.append(SqlUtils.generarWhereIn(ID_TIPO_EXPEDIENTE, listaExpedientes, params));
			} else {
				where.append(SqlUtils.generarWhereIgual(ID_TIPO_EXPEDIENTE, filter.getIdTipoExpediente(), params));
			}
		}
		// GRUPO TRABAJO - INICIO
		if ("S".equals(filter.getIndGruposBOE())) {
			ExpedienteDaoUtils.getWhereGruposTrabajoBOE(filter, where, params);
		} else {
			ExpedienteDaoUtils.getWhereGruposTrabajo(filter, where, params, true);
		}
		// GRUPO TRABAJO - FIN
		// ASIGNADOR
		if (filter.getAsignador() != null) {
			where.append(
					SqlUtils.generarWhereLike("t1.DNI_ASIGNADOR_051", filter.getAsignador().getDni(), params, false));
		}
		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		planificacionExpedienteUtils.getWherebusquedageneralContinuacion(filter, params, esInterpretacion, where);

		return where.toString();
	}

	@Override
	public ResumenGraficas getExpPlanificacionCount(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		if (tipoConsulta == PlanifExpedientesEnum.SIN_ASIGNADOR.getId()) {
			PlanificacionExpedienteUtils.getSelectCountSinAsignadorChartsExped(dni, query, params);
		} else {
			PlanificacionExpedienteUtils.getSelectCountCharts(dni, query, params, "t1");
		}

		this.getPlanificacionWhere(dni, tipoConsulta, query, params, PlanificacionExpedienteUtils.EXPEDIENTES_FILTRO);

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas, params.toArray());
	}

	@Override
	public ResumenGraficas getTramitesPlanificacionCount(String dni, int tipoConsulta) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		PlanificacionExpedienteUtils.getSelectCountCharts(dni, query, params, "t3");

		this.getPlanificacionWhere(dni, tipoConsulta, query, params, PlanificacionExpedienteUtils.TRAMITES_FILTRO);

		return this.getJdbcTemplate().queryForObject(query.toString(), this.rwMapResumenGraficas, params.toArray());
	}

	/**
	 *
	 * @param dni          String
	 * @param tipoConsulta int
	 * @param query        StringBuilder
	 * @param params       List<Object>
	 * @param tipoFiltro   String
	 */
	public void getPlanificacionWhere(String dni, int tipoConsulta, StringBuilder query, List<Object> params,
			String tipoFiltro) {
		try {
			// Reflexión
			final String classPackage = PLANIFICACION_UTILS;

			if (PlanificacionExpedienteUtils.EXPEDIENTES_FILTRO.equals(tipoFiltro)) {
				final PlanifExpedientesEnum planifExpedientesEnum = PlanifExpedientesEnum.getById(tipoConsulta);

				final Class<?> c = Class.forName(classPackage);
				Class<?>[] paramTypes = { String.class, StringBuilder.class, List.class };
				Method method = c.getDeclaredMethod(planifExpedientesEnum.getMethod(), paramTypes);
				method.invoke(c, dni, query, params);
			} else {
				final PlanifTramitesEnum planifTramitesEnum = PlanifTramitesEnum.getById(tipoConsulta);

				final Class<?> c = Class.forName(classPackage);
				Class<?>[] paramTypes = { String.class, StringBuilder.class, List.class };
				Method method = c.getDeclaredMethod(planifTramitesEnum.getMethod(), paramTypes);
				method.invoke(c, dni, query, params);
			}
		} catch (Exception e) {
			ExpedienteDaoImpl.LOGGER.info("Error: ", e);
		}
	}

	@Override
	public List<ExpTareasResumen> findAllExpTareasResumen(ExpTareasResumen expTareasResumen,
			JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		final List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		final String[] filtroDatos = expTareasResumen.getFiltroDatos();
		final String filtro = filtroDatos[Constants.CERO];
		final String tipoFiltro = PlanificacionExpedienteUtils.getTipoFiltro(filtro);

		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		// Se obtiene la select
		query.append(planificacionExpedienteUtils.getSelectResumen(tipoFiltro));
		// se anaden campos para reorder de columnas de tablas de dashboard
		query.append(planificacionExpedienteUtils.dashboardTableReorderQueryAux(query, tipoFiltro));
		// Se obtiene la where
		planificacionExpedienteUtils.getResumenQuery(expTareasResumen, query, params, false);

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
		}

		// Obtener el rowMapper
		if (PlanificacionExpedienteUtils.EXPEDIENTES_FILTRO.equals(tipoFiltro)
				|| PlanificacionExpedienteUtils.EXPEDIENTES_SIN_FILTRO.equals(tipoFiltro)) {
			return this.getJdbcTemplate().query(query.toString(), this.rwMapExpedientes, params.toArray());
		} else if (PlanificacionExpedienteUtils.TAREAS_FILTRO.equals(tipoFiltro)) {
			return this.getJdbcTemplate().query(query.toString(), this.rwMapTareas, params.toArray());
		} else {
			return this.getJdbcTemplate().query(query.toString(), this.rwMapTramites, params.toArray());
		}
	}

	@Override
	public Long findAllExpTareasResumenCount(ExpTareasResumen expTareasResumen, boolean b) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT COUNT(1) ");
		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();

		// Se obtiene la where
		planificacionExpedienteUtils.getResumenQuery(expTareasResumen, query, params, true);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public String cambiarPrioridadExpediente(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder select = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		select.append("SELECT IND_PRIORITARIO_051 FROM AA79B51S01 WHERE ANYO_051=? AND NUM_EXP_051=?");

		String inPrioritario = this.getJdbcTemplate().queryForObject(select.toString(), String.class, anyo, numExp);

		StringBuilder update = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		update.append(UPDATEAA79B51S01);
		update.append(" SET IND_PRIORITARIO_051=? ");
		update.append(" WHERE ANYO_051=? AND NUM_EXP_051=? ");
		if (Constants.SI.equals(inPrioritario)) {
			params.add(Constants.NO);
			inPrioritario = Constants.NO;
		} else {
			params.add(Constants.SI);
			inPrioritario = Constants.SI;
		}
		params.add(anyo);
		params.add(numExp);
		this.getJdbcTemplate().update(update.toString(), params.toArray());
		return inPrioritario;
	}

	@SuppressWarnings("static-access")
	@Override
	public Object findAllTareasExpedientes(String idsExpedientes, JQGridRequestDto jqGridRequestDto, Boolean b,
			Boolean isCount) {
		final List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		ExpTareasResumen filter = new ExpTareasResumen();
		String[] aFiltroDatos = new String[Constants.UNO];
		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		aFiltroDatos[Constants.CERO] = PlanificacionExpedienteUtils.TAREAS_FILTRO_SIN_CONDICIONES + "-"
				+ Constants.CUATRO;
		filter.setFiltroDatos(aFiltroDatos);
		// Se obtiene la select
		if (isCount) {
			query.append(DaoConstants.SELECT_COUNT);
		} else {
			query.append(planificacionExpedienteUtils
					.getSelectResumen(PlanificacionExpedienteUtils.TAREAS_FILTRO_SIN_CONDICIONES));
		}

		// Se obtiene la where
		planificacionExpedienteUtils.getExcelPlanifQuery(filter, query, params, true);
		// Where de anyo numexp
		String[] aIds = idsExpedientes.split(Constants.GUION_BAJO);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		for (String id : aIds) {
			Long anyo = Long.parseLong(id.substring(Constants.CERO, id.indexOf(Constants.GUION)));
			Integer numExp = Integer.parseInt(id.substring(id.indexOf(Constants.GUION) + Constants.UNO));
			query.append("( t1.ANYO_081 = ? AND t1.NUM_EXP_081 = ? )" + DaoConstants.OR);
			params.add(anyo);
			params.add(numExp);
		}

		StringBuilder updatedQuery = new StringBuilder();
		StringBuilder paginatedQuery = new StringBuilder();
		updatedQuery.append(query.replace(query.toString().lastIndexOf("OR"),
				query.toString().lastIndexOf("OR") + Constants.DOS, DaoConstants.CERRAR_PARENTESIS).toString());

		if (!isCount) {
			if (jqGridRequestDto != null) {
				paginatedQuery
						.append(JQGridManager.getPaginationQuery(jqGridRequestDto, updatedQuery, this.getOrderBy()));
			}
		} else {
			paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, updatedQuery));
		}

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapTareasDesglose, params.toArray());
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public Long findAllTareasExpedientesCount(String idsExpedientes, boolean b) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		ExpTareasResumen filter = new ExpTareasResumen();
		String[] aFiltroDatos = new String[Constants.UNO];
		aFiltroDatos[Constants.CERO] = PlanificacionExpedienteUtils.TAREAS_FILTRO + "-" + Constants.CUATRO;
		filter.setFiltroDatos(aFiltroDatos);
		// Se obtiene la where
		planificacionExpedienteUtils.getResumenQuery(filter, query, params, true);
		// Where de anyo y numexp

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Integer updateIndAndFechaSubsanacion(SubsanacionExpediente bean) {
		StringBuilder update = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		// dnirecurso usuario del sistema
		final Credentials credentials = (Credentials) SecurityContextHolder.getContext().getAuthentication()
				.getCredentials();
		String dni = credentials.getNif();
		update.append(
				" UPDATE AA79B64S01 SET ESTADO_SUBSANACION_064 = ?, FECHA_ACEPTACION_064 = SYSDATE, OBSRV_RECHAZO_064 = ?"
						+ ", DNI_RECURSO_064 = ? WHERE ID_064 = ? ");

		return this.getJdbcTemplate().update(update.toString(), bean.getEstado(), bean.getObservRechazo(), dni,
				bean.getId());
	}

	@Override
	public List<CalendarioIcsModel> obtenerDatosExpedientes(String expedientesSeleccionados, Integer iTipoExp) {
		StringBuilder query = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		PlanificacionExpedienteUtils.getQueryDatosExpIcs(query, expedientesSeleccionados, iTipoExp);
		return this.getJdbcTemplate().query(query.toString(), getRwMapCalendarioIcs());
	}

	@Override
	public List<Expediente> obtenerNuevasBitacoras(ListaExpediente listaExpedientes) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.SELECT).append(
				" t1.NUM_EXP_059 NUMEXP, t1.ANYO_059 ANYO, (NVL(MAX(t1.ID_ESTADO_BITACORA_059),0) + 1) as IDESTADOBITACORA ")
				.append(" FROM AA79B59S01 t1 ").append(" LEFT JOIN AA79B51S01 t2 ")
				.append(" ON t1.NUM_EXP_059=t2.NUM_EXP_051 AND t1.ANYO_059=t2.ANYO_051 ").append(DaoConstants.WHERE_1_1)
				.append(DaoConstants.AND).append(" t2.DNI_TECNICO_051 IS NULL ");

		if (ExpedienteDaoUtils.listaExpedientesValido(listaExpedientes)) {
			query.append(DaoConstants.AND);
			query.append(DaoConstants.ABRIR_PARENTESIS);
			for (int i = 0; i < listaExpedientes.getListaExpediente().size(); i++) {
				if (i != Constants.CERO) {
					query.append(DaoConstants.OR);
				}
				Expediente expediente = listaExpedientes.getListaExpediente().get(i);
				query.append(DaoConstants.ABRIR_PARENTESIS);
				query.append(SqlUtils.generarWhereIgualSinAnd(" t1.NUM_EXP_059 ", expediente.getNumExp(), params));
				query.append(DaoConstants.AND);
				query.append(SqlUtils.generarWhereIgualSinAnd(" t1.ANYO_059 ", expediente.getAnyo(), params));
				query.append(DaoConstants.CERRAR_PARENTESIS);
			}
			query.append(DaoConstants.CERRAR_PARENTESIS);
		}
		query.append(DaoConstants.GROUP_BY).append(" t1.NUM_EXP_059, t1.ANYO_059 ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapBitacoras, params.toArray());
	}

	/**
	 *
	 * @param lista List<Expediente>
	 */
	@Override
	public void modificarEstadoListaExpedientes(List<Expediente> lista) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE AA79B51S01 SET ESTADO_BITACORA_051 = ? WHERE ANYO_051 = ? AND NUM_EXP_051 = ? ");

		final List<Object[]> params = new ArrayList<Object[]>();
		final List<Object> aux = new ArrayList<Object>();
		for (Expediente expediente : lista) {
			aux.clear();
			aux.add(expediente.getBitacoraExpediente().getIdEstadoBitacora());
			aux.add(expediente.getAnyo());
			aux.add(expediente.getNumExp());
			params.add(aux.toArray());
		}
		this.getJdbcTemplate().batchUpdate(query.toString(), params);
	}

	@Override()
	public BitacoraExpediente findEstadoFaseExpediente(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT);
		query.append("b1.ID_ESTADO_EXP_059");
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDESTADOEXP);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(B1ID_FASE_EXPEDIENTE_059);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDFASEEXPEDIENTE);
		query.append(DaoConstants.FROM);
		query.append("AA79B51S01 t1 ");
		query.append(DaoConstants.LEFT_JOIN);
		// BitacoraExpediente
		query.append(
				"AA79B59S01 b1 ON t1.ANYO_051 = b1.ANYO_059 AND t1.NUM_EXP_051 = b1.NUM_EXP_059 AND t1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 ");
		query.append(DaoConstants.WHERE);
		query.append("t1.ANYO_051");
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(DBConstants.T1NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(anyo);
		params.add(numExp);

		List<BitacoraExpediente> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapBitacora,
				params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override()
	public Long findEstadoBitacoraExp(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT);
		query.append(ESTADO_BITACORA_EXPEDIENTE);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.ESTADOBITACORA);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_51);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.WHERE);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(DBConstants.T1NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(anyo);
		params.add(numExp);

		List<BitacoraExpediente> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapBitacoraExp,
				params.toArray());

		BitacoraExpediente bitacora = DataAccessUtils.uniqueResult(beanList);

		return bitacora.getIdEstadoBitacora();
	}

	@Override
	public Integer asignarAsignadorAExpedientes(ListaExpediente listaExpedientes) {
		StringBuilder query = new StringBuilder();
		if (listaExpedientes != null && listaExpedientes.getListaExpediente() != null
				&& !listaExpedientes.getListaExpediente().isEmpty()
				&& listaExpedientes.getListaExpediente().get(0).getTecnico() != null) {
			List<Object> params = new ArrayList<Object>();
			query.append(UPDATEAA79B51S01);
			query.append("SET DNI_ASIGNADOR_051 = ?");
			query.append(DaoConstants.WHERE);
			params.add(listaExpedientes.getListaExpediente().get(0).getTecnico().getDni());
			for (Expediente expediente : listaExpedientes.getListaExpediente()) {
				query.append(ANYO_051_AND_NUMEXP_051EQUALS);
				params.add(expediente.getAnyo());
				params.add(expediente.getNumExp());
				query.append(" OR ");
			}
			query.delete(query.length() - 3, query.length());
			return this.getJdbcTemplate().update(query.toString(), params.toArray());

		}

		return 0;
	}

	private StringBuilder getSelectBusquedaGeneral(ExpedientePlanificacion filter, StringBuilder select,
			boolean isCount, boolean esInterpretacion) {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		select.append("SELECT ");

		if (isCount) {
			select.append(" COUNT(DISTINCT SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )) ");
		} else {
			// 51 comun
			select.append(" DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
			select.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
			select.append(", SUBSTR(t1.ANYO_051,2,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') "
					+ DBConstants.ANYONUMEXPCONCATENADO);
			select.append(", t1.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE);
			select.append(", t1.IND_PRIORITARIO_051 " + DBConstants.PRIORITARIO);
			select.append(DAOUtils.getDecodeAcciones("t1.IND_PRIORITARIO_051", DBConstants.PRIORITARIODESCES, this.msg,
					"ActivoEnum", es));
			select.append(DAOUtils.getDecodeAcciones("t1.IND_PRIORITARIO_051", DBConstants.PRIORITARIODESCEU, this.msg,
					"ActivoEnum", eu));
			select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
					+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
					.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
					.append("') AS " + DBConstants.TIPOEXPEDIENTEDESCES);
			select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
					+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
					.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
					.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
					.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
					.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
					.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
					.append("') AS " + DBConstants.TIPOEXPEDIENTEDESCEU);
			select.append(", t1.TITULO_051 ").append(DBConstants.TITULO);
			// 59 comun
			select.append(", b1.ID_ESTADO_EXP_059 " + DBConstants.IDESTADOEXP);
			select.append(", b1.ID_FASE_EXPEDIENTE_059 " + DBConstants.IDFASEEXPEDIENTE);
			select.append(", f1.DESC_ES_061 " + DBConstants.FASEEXPEDIENTEDESCES);
			select.append(", f1.DESC_EU_061 " + DBConstants.FASEEXPEDIENTEDESCEU);
			select.append(", f1.DESC_ABR_ES_061 " + DBConstants.FASEEXPEDIENTEDESCABRES);
			select.append(", f1.DESC_ABR_EU_061 " + DBConstants.FASEEXPEDIENTEDESCABREU);
			// 53 - 81 - 90 tradRev
			if (!esInterpretacion) {
				// tiene tareas entrega
				select.append(", TIENE_TAREAS_ENTREGA(t1.ANYO_051,t1.NUM_EXP_051) " + DBConstants.TIENETAREASENTREGA);
				// ind urgente
				select.append(", ES_EXPEDIENTE_URGENTE(t1.ANYO_051,t1.NUM_EXP_051, t1.ID_TIPO_EXPEDIENTE_051,"
						+ " r1.NUM_TOTAL_PAL_IZO_053, r1.FECHA_FINAL_IZO_053, g1.IND_VIP_054) "
						+ DBConstants.ESURGENTE);
				// fecha final izo
				select.append(", NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053) " + DBConstants.FECHAFINALIZO);
				select.append(", TO_CHAR(NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053),'HH24:MI') "
						+ DBConstants.HORAFINALIZO);
				// num total palabras izo
				select.append(
						", NVL(r1.NUM_TOTAL_PAL_IZO_053,r1.NUM_TOTAL_PAL_SOLIC_053) " + DBConstants.NUMTOTALPALIZO);
				select.append(", n1.NUM_TOTAL_PAL_090 " + DBConstants.NUMTOTALPALTRADOS);
				select.append(", n1.NUM_PAL_CONCOR_0_84_090 " + DBConstants.NUMPALCONCOR084090);
				select.append(", n1.NUM_PAL_CONCOR_85_94_090 " + DBConstants.NUMPALCONCOR8594090);
				select.append(", n1.NUM_PAL_CONCOR_95_100_090 " + DBConstants.NUMPALCONCOR95100090);
				select.append(", n1.NUM_PAL_CONCOR_95_99_090 " + DBConstants.NUMPALCONCOR9599090);
				select.append(", n1.NUM_PAL_CONCOR_100_090 " + DBConstants.NUMPALCONCOR100090);

				// responsable trabajo
				select.append(", NVL(OBTENER_RESPONSABLE_TRABAJO(t1.ANYO_051, t1.NUM_EXP_051),'-')  "
						+ DBConstants.RESPONSABLE);
				// presupuesto
				select.append(", r1.IND_PRESUPUESTO_053 " + DBConstants.INDPRESUPUESTO);
				select.append(", s1.ESTADO_SUBSANACION_064 " + DBConstants.IDESTADOPRESUPUESTO);
				select.append(", DECODE(s1.ESTADO_SUBSANACION_064," + EstadoRequerimientoEnum.PENDIENTE.getValue() + ","
						+ "'" + this.msg.getMessage(EstadoRequerimientoEnum.PENDIENTE.getLabel(), null, es) + "',"
						+ EstadoRequerimientoEnum.ACEPTADA.getValue() + ",'" + ""
						+ this.msg.getMessage(EstadoRequerimientoEnum.ACEPTADA.getLabel(), null, es) + "',"
						+ EstadoRequerimientoEnum.RECHAZADA.getValue() + ",'" + ""
						+ this.msg.getMessage(EstadoRequerimientoEnum.RECHAZADA.getLabel(), null, es) + "') AS "
						+ DBConstants.ESTADOPRESUPUESTODESCES);
				select.append(", DECODE(s1.ESTADO_SUBSANACION_064," + EstadoRequerimientoEnum.PENDIENTE.getValue()
						+ ",'" + "" + this.msg.getMessage(EstadoRequerimientoEnum.PENDIENTE.getLabel(), null, eu) + "',"
						+ EstadoRequerimientoEnum.ACEPTADA.getValue() + ",'" + ""
						+ this.msg.getMessage(EstadoRequerimientoEnum.ACEPTADA.getLabel(), null, eu) + "',"
						+ EstadoRequerimientoEnum.RECHAZADA.getValue() + ",'" + ""
						+ this.msg.getMessage(EstadoRequerimientoEnum.RECHAZADA.getLabel(), null, eu) + "') AS "
						+ DBConstants.ESTADOPRESUPUESTODESCEU);
				select.append(", s1.FECHA_LIMITE_064 " + DBConstants.FECHALIMITEPRESUPUESTO);
				// fecha fin prevista
				select.append(", (SELECT FECHA_FIN_081 ");
				select.append("FROM AA79B81T00 ");
				select.append("WHERE ANYO_081 = t1.ANYO_051 AND NUM_EXP_081 = t1.NUM_EXP_051 ");
				select.append(
						"AND ESTADO_ASIGNACION_081 <> " + EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());
				select.append(" AND ESTADO_EJECUCION_081 = " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());

				select.append(
						" ORDER BY ORDEN_081 ASC, FECHA_INICIO_081 DESC FETCH FIRST 1 ROW ONLY) FECHAFINPREVISTA ");
				select.append(", (SELECT TO_CHAR(FECHA_FIN_081,'HH24:MI') ");
				select.append("FROM AA79B81T00 ");
				select.append("WHERE ANYO_081 = t1.ANYO_051 AND NUM_EXP_081 = t1.NUM_EXP_051 ");
				select.append(
						"AND ESTADO_ASIGNACION_081 <> " + EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());
				select.append(" AND ESTADO_EJECUCION_081 = " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
				select.append(
						" ORDER BY ORDEN_081 ASC, FECHA_INICIO_081 DESC FETCH FIRST 1 ROW ONLY) HORAFINPREVISTA ");
				// requiere proyecto trados
				select.append(", d1.ESTADO_ASIGNACION_081 " + DBConstants.ESTADOASIGID);
				select.append(", d1.ESTADO_EJECUCION_081 " + DBConstants.ESTADOEJECID);
				select.append(
						", REQUIERE_TRADOS(t1.ID_TIPO_EXPEDIENTE_051,t1.ANYO_051,t1.NUM_EXP_051,NVL(r1.NUM_TOTAL_PAL_IZO_053,r1.NUM_TOTAL_PAL_SOLIC_053),?) "
								+ DBConstants.REQUIERETRADOS);
				// campo para reordenacion si esta seleccionado tipo de
				// expediente diferente
				select.append(" , NVL(r1.FECHA_FINAL_IZO_053,r1.FECHA_FINAL_SOLIC_053) FECHAINICIOPREVISTA");

				select.append(
						" , NVL(n1.NUM_TOTAL_PAL_090,NVL(r1.NUM_TOTAL_PAL_IZO_053,NVL(NUM_TOTAL_PAL_SOLIC_053,0))) NUMPALCOLORDER");

				select.append(" , OBTENER_ESTADO_NEGOCIACION(t1.ANYO_051,t1.NUM_EXP_051) INDESTADONEGOCIACION");
				select.append(" , NVL(lote.NOMBRE_LOTE_029,'-') NOMBRELOTE");
				select.append(", " + R1IND_PUBLICAR_BOPV_053 + " INDPUBLICARBOPV");
				select.append(", r1.IND_PREVISTO_BOPV_053 INDPREVISTOBOPV ");

			} else {
				select.append(", i1.FECHA_INI_052 " + DBConstants.FECHAINICIOPREVISTA);
				select.append(", TO_CHAR(i1.FECHA_INI_052,'HH24:MI') " + DBConstants.HORAINICIOPREVISTA);
				select.append(", i1.FECHA_FIN_052 " + DBConstants.FECHAFINPREVISTA);
				select.append(", TO_CHAR(i1.FECHA_FIN_052,'HH24:MI') " + DBConstants.HORAFINPREVISTA);
				// campos para reordenacion si esta seleccionado tipo de
				// expediente diferente
				select.append(", i1.FECHA_INI_052 " + DBConstants.FECHAFINALIZO);
				select.append(", i1.FECHA_INI_052 " + DBConstants.NUMTOTALPALIZO);
				select.append(", i1.FECHA_INI_052 " + DBConstants.RESPONSABLE);
				select.append(", i1.FECHA_INI_052 " + DBConstants.INDPRESUPUESTO);
				select.append(", i1.FECHA_INI_052 " + DBConstants.REQUIERETRADOS);
				select.append(", i1.FECHA_INI_052 NUMPALCOLORDER");
			}

			// GESTOR comun
			select.append(", g1.DNI_SOLICITANTE_054 " + DBConstants.DNIGESTOR);
			select.append(", a1.NOMBRE_077 " + DBConstants.NOMBREGESTOR);
			select.append(", a1.APEL1_077 " + DBConstants.APEL1GESTOR);
			select.append(", a1.APEL2_077 " + DBConstants.APEL2GESTOR);
			select.append(", a1.PREDNI_077 " + DBConstants.PREDNIGESTOR);
			select.append(", a1.SUFDNI_077 " + DBConstants.SUFDNIGESTOR);
			select.append(
					", (a1.PREDNI_077 || g1.DNI_SOLICITANTE_054 || a1.SUFDNI_077) AS " + DBConstants.DNICOMPLETOGESTOR);
			select.append(", g1.TIPO_ENTIDAD_054 " + DBConstants.TIPOENTIDADGESTOR);
			select.append(", g1.ID_ENTIDAD_054 " + DBConstants.IDENTIDADGESTOR);
			select.append(", g1.IND_VIP_054 " + DBConstants.INDVIPGESTOR);
			select.append(", " + DECODE_G1IND_VIP_054 + ", NULL,'NO','N','NO','S','SI') AS "
					+ DBConstants.GESTOREXPEDIENTESVIPDESCES);
			select.append(", " + DECODE_G1IND_VIP_054 + ", NULL,'EZ','N','EZ','S','BAI') AS "
					+ DBConstants.GESTOREXPEDIENTESVIPDESCEU);
			select.append(", e1.CDIRNORA " + DBConstants.ENTIDADCDIRNORAGESTOR);
			select.append(", e1.IVA " + DBConstants.ENTIDADIVAGESTOR);
			select.append(", e1.FACTURABLE " + DBConstants.ENTIDADFACTURABLEGESTOR);
			select.append(", e1.ESTADO " + DBConstants.ESTADOENTIDADGESTOR);
			select.append(", e1.CIF " + DBConstants.CIFENTIDADGESTOR);
			select.append(", e1.DESC_ES " + DBConstants.DESCENTIDADESGESTOR);
			select.append(", e1.DESC_EU " + DBConstants.DESCENTIDADEUGESTOR);
			select.append(", e1.DESC_AMP_ES " + DBConstants.DESCAMPENTIDADESGESTOR);
			select.append(", e1.DESC_AMP_EU " + DBConstants.DESCAMPENTIDADEUGESTOR);

			// estadoTareas comun
			select.append(
					", ESTADO_TAREAS_ASIGNADAS(t1.ANYO_051, t1.NUM_EXP_051) " + DBConstants.IDESTADOTAREASASIGNADAS);

			select.append(
					", (SELECT DESC_EU_026 FROM AA79B26T00 WHERE ID_026 = OBTENER_GRUPO_TRABAJO(T1.ANYO_051, T1.NUM_EXP_051)) GRUPOTRABAJO");

			// asignador comun
			select.append(", t1.DNI_ASIGNADOR_051 " + DBConstants.DNIASIGNADOR);
			select.append(", x1.NOMBRE_077 " + DBConstants.NOMBREASIGNADOR);
			select.append(", x1.APEL1_077 " + DBConstants.APEL1ASIGNADOR);
			select.append(", x1.APEL2_077 " + DBConstants.APEL2ASIGNADOR);

			select.append(", t1.DNI_TECNICO_051 " + DBConstants.DNITECNICO);
			select.append(", x2.NOMBRE_077 NOMBRETECNICO");
			select.append(", x2.APEL1_077 APEL1TECNICO");
			select.append(", x2.APEL2_077 APEL2TECNICO");

			// GESTORCOLORDEREU
			select.append("," + SqlUtils.normalizarCampoSql("e1.DESC_EU") + " || "
					+ SqlUtils.normalizarCampoSql("a1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("a1.APEL2_077")
					+ " || " + SqlUtils.normalizarCampoSql("a1.NOMBRE_077") + " GESTORCOLORDEREU");
			// GESTORCOLORDERES
			select.append("," + SqlUtils.normalizarCampoSql("e1.DESC_ES") + " || "
					+ SqlUtils.normalizarCampoSql("a1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("a1.APEL2_077")
					+ " || " + SqlUtils.normalizarCampoSql("a1.NOMBRE_077") + " GESTORCOLORDERES");
		}
		select.append(" FROM AA79B51S01 t1 ");
		// 52 interpretacion
		if (esInterpretacion) {
			select.append("JOIN AA79B52S01 i1 ON ");
			select.append("t1.ANYO_051 = i1.ANYO_052 ");
			select.append("AND t1.NUM_EXP_051 = i1.NUM_EXP_052 ");
		} else {
			// 53 trad/rev
			select.append("JOIN AA79B53S01 r1 ");
			select.append("ON t1.ANYO_051     = r1.ANYO_053 ");
			select.append("AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");

			select.append("LEFT JOIN AA79B81S01 tarea ");
			select.append("ON r1.ANYO_053     = tarea.ANYO_081 ");
			select.append("AND r1.NUM_EXP_053 = tarea.NUM_EXP_081 ");
			select.append("AND tarea.id_tipo_tarea_081 in (").append(TipoTareaGestionAsociadaEnum.TRADUCIR.getValue())
					.append(",").append(TipoTareaGestionAsociadaEnum.REVISAR.getValue()).append(")");
			select.append("AND tarea.RECURSO_ASIGNACION_081 = '").append(TipoRecursoEnum.EXTERNO.getValue())
					.append("'");
			select.append("LEFT JOIN AA79B29S01 lote ");
			select.append("ON tarea.ID_LOTE_081 = lote.ID_LOTE_029 ");
		}
		// 54 comun
		select.append("JOIN AA79B54S01 g1 ");
		select.append("ON t1.ANYO_051     = g1.ANYO_054 ");
		select.append("AND t1.NUM_EXP_051 = g1.NUM_EXP_054 ");

		// 59 comun
		select.append("JOIN AA79B59S01 b1 ");
		select.append("ON t1.ANYO_051 = b1.ANYO_059 ");
		select.append("AND t1.NUM_EXP_051 = b1.NUM_EXP_059 ");
		select.append("AND t1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 ");
		// 61 Fases
		select.append("JOIN AA79B61S01 f1 ");
		select.append("ON b1.ID_FASE_EXPEDIENTE_059 = f1.ID_061 ");
		// 77 comun
		select.append("JOIN AA79B77S01 a1 ");
		select.append("ON g1.DNI_SOLICITANTE_054 = a1.DNI_077 ");
		// X54JAPI_ENTIDADES_SOLIC comun
		select.append("JOIN X54JAPI_ENTIDADES_SOLIC e1 ");
		select.append("ON g1.TIPO_ENTIDAD_054 = e1.TIPO ");
		select.append("AND g1.ID_ENTIDAD_054  = e1.CODIGO ");
		// 81 comun
		select.append("LEFT JOIN AA79B81S01 d1 ");
		select.append("ON t1.ANYO_051     = d1.ANYO_081 ");
		select.append("AND t1.NUM_EXP_051 = d1.NUM_EXP_081 ");
		if (esInterpretacion) {
			select.append("AND d1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue()
					+ " ");
		} else {
			select.append(
					"AND d1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue() + " ");
			// 90 comun
			select.append("LEFT JOIN AA79B90S01 n1 ");
			select.append("ON d1.ID_TAREA_081  = n1.ID_TAREA_090 ");
		}

		// 77 comun
		select.append("LEFT JOIN AA79B77S01 c1 ");
		select.append("ON d1.DNI_RECURSO_081 = c1.DNI_077 ");
		// 77 comun
		select.append("LEFT JOIN AA79B77S01 x1 ");
		select.append("ON t1.DNI_ASIGNADOR_051 = x1.DNI_077 ");
		// 77 tecnico
		select.append("LEFT JOIN AA79B77S01 x2 ");
		select.append("ON t1.DNI_TECNICO_051 = x2.DNI_077 ");
		// 64 comun
		select.append("LEFT JOIN AA79B64S01 s1 ");
		select.append("ON d1.ID_REQUERIMIENTO_081 = s1.ID_064 ");
		// 81 comun para fecha fin prevista
		select.append(" LEFT JOIN AA79B81S01 v1 ");
		select.append("ON t1.ANYO_051     = v1.ANYO_081 ");
		select.append("AND t1.NUM_EXP_051 = v1.NUM_EXP_081 ");
		select.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
		select.append(" v1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue());
		select.append(DaoConstants.OR);
		select.append(" v1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue());
		select.append(DaoConstants.OR);
		select.append(
				" v1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue());
		select.append(DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.AND + " v1.IND_NO_CONFORMIDAD_081 = " + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.NO.getValue() + DaoConstants.SIGNO_APOSTROFO);
		select.append(DaoConstants.WHERE_1_1);
		// necesario para llamar al pl requiere trados
		return select;
	}

	@Override
	public Object getTareasExpedientes(String string, JQGridRequestDto jqGridRequestDtoReport, boolean startsWith,
			boolean isCount) {
		final List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		ExpTareasResumen filter = new ExpTareasResumen();
		String[] aFiltroDatos = new String[Constants.UNO];
		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		aFiltroDatos[Constants.CERO] = PlanificacionExpedienteUtils.TAREAS_FILTRO_SIN_CONDICIONES + "-"
				+ Constants.CUATRO;
		filter.setFiltroDatos(aFiltroDatos);
		// Se obtiene la select
		ExpedienteDaoUtils.obtenerSelectTareasExpedientesAux(isCount, query, planificacionExpedienteUtils);

		// Se obtiene la where
		planificacionExpedienteUtils.getExcelPlanifQuery(filter, query, params, true);

		// Where de anyo numexp
		String[] aIds = string.split(Constants.GUION_BAJO);
		StringBuilder paginatedQuery = planificacionExpedienteUtils.whereDeAnyoNumExp(jqGridRequestDtoReport, isCount,
				params, query, aIds);

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapTareasDesglose, params.toArray());
		}
	}

	@Override
	public boolean isEstadoSubDocPdte(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.TABLA_51);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.LEFT_JOIN);
		// BitacoraExpediente
		query.append(DBConstants.TABLA_59);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_059);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_059);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ESTADO_BITACORA_051);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_ESTADO_BITACORA_059);
		query.append(DaoConstants.LEFT_JOIN);
		// Requerimiento expediente
		query.append(DBConstants.TABLA_064);
		query.append(DaoConstants.BLANK);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.ON);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_REQUERIMIENTO_059);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_064);
		query.append(DaoConstants.WHERE);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.IND_SUBSANADO_064);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(Constants.SI);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ESTADO_SUBSANACION_064);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(EstadoSubsanacionEnum.PENDIENTE.getValue());

		params.add(anyo);
		params.add(numExp);

		Long rst = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);

		return rst > 0;

	}

	@Override
	public ExpedienteTradRev getFechaFinalIzo(Expediente bean) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT);
		query.append("NVL(FECHA_FINAL_IZO_053,FECHA_FINAL_SOLIC_053) " + DBConstants.FECHAFINALIZO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("TO_CHAR(NVL(FECHA_FINAL_IZO_053,FECHA_FINAL_SOLIC_053),'HH24:MI') " + DBConstants.HORAFINALIZO);
		query.append(DaoConstants.FROM + DBConstants.TABLA_51);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_53);
		query.append(DaoConstants.ON);
		query.append("ANYO_051 = ANYO_053 AND NUM_EXP_051 = NUM_EXP_053");
		query.append(DaoConstants.WHERE);
		query.append("ANYO_051 = ? AND NUM_EXP_051 = ?");
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		List<ExpedienteTradRev> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapFechaIzo,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Expediente findConTareasEntrega(Expediente bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelect());
		// tiene tareas entrega
		query.append(", TIENE_TAREAS_ENTREGA(t1.ANYO_051,t1.NUM_EXP_051) " + DBConstants.TIENETAREASENTREGA);
		query.append(this.getFrom(bean, params));
		query.append(this.getWherePK(bean, params));

		List<Expediente> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMapTareasEntrega,
				params.toArray());
		return DataAccessUtils.uniqueResult(beanList);
	}

	@Override
	public Boolean getComprobarEstadoExpedientesEnCurso(List<String> expedientesSeleccionados) {
		List<Object> paramsCEEEC = new ArrayList<Object>();
		StringBuilder queryCEEEC = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		queryCEEEC.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK
				+ DaoConstants.T1_MINUSCULA);
		queryCEEEC.append(DaoConstants.JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		queryCEEEC.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_059);
		queryCEEEC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_059);
		queryCEEEC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_BITACORA_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_BITACORA_059);
		queryCEEEC.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059,
				EstadoExpedienteEnum.CERRADO.getValue(), paramsCEEEC));
		queryCEEEC.append(DaoConstants.WHERE_1_1);
		boolean esPrimero = true;
		for (String anyoNumExpConcatenado : expedientesSeleccionados) {
			String[] idConcatenadoExp = anyoNumExpConcatenado.split(Constants.GUION);
			if (esPrimero) {
				esPrimero = false;
				queryCEEEC.append(DaoConstants.AND);
			} else {
				queryCEEEC.append(DaoConstants.OR);
			}
			queryCEEEC.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
					+ DaoConstants.SIGNOIGUAL_INTERROGACION);
			queryCEEEC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
					+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
			paramsCEEEC.add(idConcatenadoExp[0]);
			paramsCEEEC.add(idConcatenadoExp[1]);
		}
		Boolean bEnEstadoEnCurso = false;
		Integer iEnEstadoEnCurso = this.getJdbcTemplate().queryForObject(queryCEEEC.toString(), paramsCEEEC.toArray(),
				Integer.class);
		if (Constants.CERO < iEnEstadoEnCurso) {
			bEnEstadoEnCurso = true;
		}
		return bEnEstadoEnCurso;
	}

	/**
	 *
	 * @param expediente
	 * @return Devuelve 0 -> No hace falta firmar los docs del expediente Devuelve 1
	 *         -> Es necesario firmar los docs del expediente Devuelve 2 -> Es
	 *         necesario firmar los docs del expediente Devuelve 3 -> Es necesario
	 *         firmar los docs del expediente, y hacer las comprobaciones
	 *         posteriores y llamar al WS de IzoBerri
	 */
	@Override
	public Integer comprobarNecesidadFirmaDocs(Expediente expediente) {
		List<Object> paramsCNFD = new ArrayList<Object>();
		StringBuilder queryCNFD = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		queryCNFD.append(DaoConstants.SELECT
				+ " NVL( SUM(  DECODE(APLIC_ORIGEN_051,?,1,0)+DECODE(ORIGEN_051,?,1,0) + DECODE(NUM_EXP_051,?,1,0)), 0)");
		queryCNFD.append(DaoConstants.FROM + DBConstants.TABLA_51);
		queryCNFD.append(
				DaoConstants.JOIN + DBConstants.TABLA_53 + " ON ANYO_053 = ANYO_051 AND NUM_EXP_053 = NUM_EXP_051 ");

		queryCNFD.append(DaoConstants.WHERE + " ANYO_051 = ? AND NUM_EXP_051 = ? ");
		queryCNFD.append(" AND (IND_PUBLICAR_BOPV_053 = ? OR IND_PREVISTO_BOPV_053 = ? OR (ORIGEN_051 = ? AND (APLIC_ORIGEN_051 = ? OR APLIC_ORIGEN_051 = LOWER(?) ))) ");

		paramsCNFD.add(Constants.CONSTANTE_APLICACION_BOLETIN);
		paramsCNFD.add(OrigenExpedienteEnum.WEB_SERVICE.getValue());
		paramsCNFD.add(expediente.getNumExp());
		paramsCNFD.add(expediente.getAnyo());
		paramsCNFD.add(expediente.getNumExp());
		paramsCNFD.add(Constants.SI);
		paramsCNFD.add(Constants.SI);
		paramsCNFD.add(OrigenExpedienteEnum.WEB_SERVICE.getValue());
		paramsCNFD.add(Constants.CONSTANTE_APLICACION_TRAMITAGUNE);
		paramsCNFD.add(Constants.CONSTANTE_APLICACION_TRAMITAGUNE);

		return this.getJdbcTemplate().queryForObject(queryCNFD.toString(), paramsCNFD.toArray(), Integer.class);
	}

	@Override
	public List<ExpedientePlanificacion> consultaPlanificacionExpedienteGetSelectedIds(
			ExpedientePlanificacion filterExpedientePlanificacion, JQGridRequestDto tableData) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder queryCPEGSI = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);

		boolean esInterpretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(filterExpedientePlanificacion.getIdTipoExpediente())) {
			esInterpretacion = true;
		}

		final PlanificacionExpedienteUtils planificacionExpedienteUtils = new PlanificacionExpedienteUtils();
		// SELECT
		queryCPEGSI.append("SELECT DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
		queryCPEGSI.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
		// FROM
		planificacionExpedienteUtils.getFromConsultaPlanificacionExpediente(queryCPEGSI, esInterpretacion);
		// WHERE
		queryCPEGSI.append(planificacionExpedienteUtils.getWhereConsultaPlanificacionExpedienteIds(
				filterExpedientePlanificacion, params, esInterpretacion, tableData));

		return this.getJdbcTemplate().query(queryCPEGSI.toString(), this.getRwMapConsultaPlanifExpIds(),
				params.toArray());
	}

	@Override
	public List<ExpedientePlanificacion> busquedageneralGetSelectedIds(ExpedientePlanificacion filter,
			JQGridRequestDto tableData) {
		boolean esInterpretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(filter.getIdTipoExpediente())) {
			esInterpretacion = true;
		}
		List<Object> paramsBGGSI = new ArrayList<Object>();
		StringBuilder queryBGGSI = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ANYO_051");
		camposIds.add("t1.NUM_EXP_051");

		this.getSelectBusquedaGeneral(filter, queryBGGSI, false, esInterpretacion);

		if (!esInterpretacion) {
			paramsBGGSI.add(filter.getPalTrados()); // para REQUIERE_TRADOS
		}

		queryBGGSI.append(SqlUtils.generarWhereIgual("t1.ESTADO_BAJA_051", Constants.ALTA, paramsBGGSI));

		// MODIFICADO PARA VER LOS EXPEDIENTES CERRADOS en el buscador de
		// planificación
		Object[] arrayEstados = { EstadoExpedienteEnum.EN_CURSO.getValue(), EstadoExpedienteEnum.CERRADO.getValue() };
		queryBGGSI.append(SqlUtils.generarWhereIn(" b1.ID_ESTADO_EXP_059 ", arrayEstados, paramsBGGSI));

		// filtros formulario busqueda pantalla
		queryBGGSI.append(getWherebusquedageneral(filter, paramsBGGSI, false, esInterpretacion));
		queryBGGSI.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
				tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryBGGSI.toString(), this.getRwMapConsultaPlanifExpIds(),
				paramsBGGSI.toArray());
	}

	@Override()
	public void procCambioTipoExpedientePL(final Long anyo, final Integer numExp, final String tipoExpediente) {

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.VARCHAR));

		this.getJdbcTemplate().call(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call CAMBIO_TIPO_EXPEDIENTE (?,?,?)}");
				callableStatement.setLong(1, anyo);
				callableStatement.setInt(2, numExp);
				callableStatement.setString(3, tipoExpediente);
				return callableStatement;
			}
		}, paramList);
	}

	@Override
	public List<Expediente> filtroExpedienteEstadoEnEstudioGetSelectedIds(Expediente filterExpediente,
			JQGridRequestDto tableData) {

		StringBuilder queryFEEGSI = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsFEEGSI = new ArrayList<Object>();
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ANYO_051");
		camposIds.add("t1.NUM_EXP_051");
		queryFEEGSI.append("SELECT t1.ANYO_051 ANYO_051");
		queryFEEGSI.append(", t1.NUM_EXP_051 NUM_EXP_051");
		if (filterExpediente.getBitacoraExpediente() != null
				&& filterExpediente.getBitacoraExpediente().getEstadoExp() != null) {
			filterExpediente.getBitacoraExpediente().getEstadoExp()
					.setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		} else {
			BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			EstadosExpediente estadoExpediente = new EstadosExpediente();
			estadoExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			bitacoraExpediente.setEstadoExp(estadoExpediente);
			filterExpediente.setBitacoraExpediente(bitacoraExpediente);

		}
		queryFEEGSI.append(this.getFrom(filterExpediente, paramsFEEGSI));
		queryFEEGSI.append(DaoConstants.WHERE_1_1);
		queryFEEGSI.append(this.getWhereLike(filterExpediente, false, paramsFEEGSI, false));
		if (filterExpediente.getIdTipoExpediente() == null) {
			queryFEEGSI.append(this.getWhereTipoTradRev(filterExpediente, false, paramsFEEGSI, false));
		}
		queryFEEGSI
				.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
						tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryFEEGSI.toString(), this.rwMapPK, paramsFEEGSI.toArray());
	}

	@Override
	public Expediente getOrigenExpediente(Expediente exp) {
		StringBuilder queryOE = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsOE = new ArrayList<Object>();
		queryOE.append(" SELECT t1." + DBConstants.ORIGEN_051 + " ORIGEN ");
		queryOE.append(" ,t1." + DBConstants.APLIC_ORIGEN + " APLICORIGEN ");
		queryOE.append(" ,t1." + DBConstants.ANYO_051 + " ANYO ");
		queryOE.append(" ,t1." + DBConstants.NUM_EXP_051 + " NUMEXP ");
		queryOE.append(" FROM AA79B51S01 t1 ");
		queryOE.append(DaoConstants.WHERE_1_1);
		queryOE.append(SqlUtils.generarWhereIgual("t1." + DBConstants.ANYO_051, exp.getAnyo(), paramsOE));
		queryOE.append(SqlUtils.generarWhereIgual("t1." + DBConstants.NUM_EXP_051, exp.getNumExp(), paramsOE));

		return this.getJdbcTemplate().queryForObject(queryOE.toString(), this.rwMapOrigenExpediente,
				paramsOE.toArray());
	}

}
