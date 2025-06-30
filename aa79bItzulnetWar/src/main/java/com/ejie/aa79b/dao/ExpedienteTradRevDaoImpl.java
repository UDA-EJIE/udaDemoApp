package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.DocPptoTraduccionRowMapper;
import com.ejie.aa79b.dao.mapper.ExpedienteTradRevRowMapper;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.DocPresupuestoTraduccionInfoExped;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoRequerimientoEnum;
import com.ejie.aa79b.model.enums.EstadoSubsanacionEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.service.AnulacionesService;
import com.ejie.aa79b.service.BitacoraExpedienteService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.SubsanacionService;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.FinalizarTareaUtils;
import com.ejie.aa79b.utils.Utils;

@Repository
@Transactional
public class ExpedienteTradRevDaoImpl extends GenericoDaoImpl<ExpedienteTradRev> implements ExpedienteTradRevDao {

	@Autowired()
	private AnulacionesService anulacionesService;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	@Autowired()
	private BitacoraExpedienteService bitacoraExpedienteService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired()
	private SubsanacionService subsanacionService;

	protected static final String ANYO_EXPEDIENTE_TRAD_REV = "ANYO_053";
	protected static final String NUM_EXP_EXPEDIENTE_TRAD_REV = "NUM_EXP_053";
	protected static final String IND_PUBLICAR_BOPV_EXPEDIENTE_TRAD_REV = "IND_PUBLICAR_BOPV_053";
	protected static final String PUBLICAR_BOPV_DESC_ES_EXPEDIENTE_TRAD_REV = "PUBLICARBOPVDESCES";
	protected static final String PUBLICAR_BOPV_DESC_EU_EXPEDIENTE_TRAD_REV = "PUBLICARBOPVDESCEU";
	protected static final String IND_PREVISTO_BOPV_EXPEDIENTE_TRAD_REV = "IND_PREVISTO_BOPV_053";
	protected static final String ID_IDIOMA_EXPEDIENTE_TRAD_REV = "ID_IDIOMA_053";
	protected static final String IND_CONFIDENCIAL_EXPEDIENTE_TRAD_REV = "IND_CONFIDENCIAL_053";
	protected static final String IND_CORREDACCION_EXPEDIENTE_TRAD_REV = "IND_CORREDACCION_053";
	protected static final String TEXTO_EXPEDIENTE_TRAD_REV = "TEXTO_053";
	protected static final String TIPO_REDACCION_EXPEDIENTE_TRAD_REV = "TIPO_REDACCION_053";
	protected static final String PARTICIPANTES_EXPEDIENTE_TRAD_REV = "PARTICIPANTES_053";
	protected static final String REF_TRAMITAGUNE_EXPEDIENTE_TRAD_REV = "REF_TRAMITAGUNE_053";
	protected static final String NUM_TOTAL_PAL_IZO_EXPEDIENTE_TRAD_REV = "NUM_TOTAL_PAL_IZO_053";
	protected static final String FECHA_FINAL_IZO_EXPEDIENTE_TRAD_REV = "FECHA_FINAL_IZO_053";
	protected static final String HORA_FINAL_IZO_EXPEDIENTE_TRAD_REV = "HORA_FINAL_IZO_053";
	protected static final String IND_FACTURABLE_EXPEDIENTE_TRAD_REV = "IND_FACTURABLE_053";
	protected static final String ID_RELEVANCIA_EXPEDIENTE_TRAD_REV = "ID_RELEVANCIA_053";
	protected static final String IND_URGENTE_EXPEDIENTE_TRAD_REV = "IND_URGENTE_053";
	protected static final String DECODE_ID_TIPO_EXPEDIENTE_051 = "DECODE(ID_TIPO_EXPEDIENTE_051";

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { ANYO_EXPEDIENTE_TRAD_REV,
			NUM_EXP_EXPEDIENTE_TRAD_REV, IND_PUBLICAR_BOPV_EXPEDIENTE_TRAD_REV,
			PUBLICAR_BOPV_DESC_ES_EXPEDIENTE_TRAD_REV, PUBLICAR_BOPV_DESC_EU_EXPEDIENTE_TRAD_REV,
			IND_PREVISTO_BOPV_EXPEDIENTE_TRAD_REV, ID_IDIOMA_EXPEDIENTE_TRAD_REV, IND_CONFIDENCIAL_EXPEDIENTE_TRAD_REV,
			IND_CORREDACCION_EXPEDIENTE_TRAD_REV, TEXTO_EXPEDIENTE_TRAD_REV, TIPO_REDACCION_EXPEDIENTE_TRAD_REV,
			PARTICIPANTES_EXPEDIENTE_TRAD_REV, REF_TRAMITAGUNE_EXPEDIENTE_TRAD_REV,
			NUM_TOTAL_PAL_IZO_EXPEDIENTE_TRAD_REV, FECHA_FINAL_IZO_EXPEDIENTE_TRAD_REV,
			HORA_FINAL_IZO_EXPEDIENTE_TRAD_REV, IND_FACTURABLE_EXPEDIENTE_TRAD_REV, ID_RELEVANCIA_EXPEDIENTE_TRAD_REV,
			IND_URGENTE_EXPEDIENTE_TRAD_REV, "FECHA_FINAL_SOLIC_053", "FECHA_FINAL_PROP_053", "IND_OBSERVACIONES_053" };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<ExpedienteTradRev> rwMap = new ExpedienteTradRevRowMapper();

	private RowMapper<DocPresupuestoTraduccionInfoExped> rwMapDocPptoTraduccion = new DocPptoTraduccionRowMapper();

	private RowMapper<ExpedienteTradRev> rwMapPK = new RowMapper<ExpedienteTradRev>() {
		@Override
		public ExpedienteTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			return new ExpedienteTradRev(resultSet.getLong(ANYO_EXPEDIENTE_TRAD_REV),
					resultSet.getInt(NUM_EXP_EXPEDIENTE_TRAD_REV));
		}
	};

	private RowMapper<SubsanacionExpediente> rwMapDatosEstadoPpto = new RowMapper<SubsanacionExpediente>() {
		@Override
		public SubsanacionExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
			subsanacionExpediente.setId(resultSet.getLong("ID_064"));
			subsanacionExpediente.setEstado(resultSet.getInt("ESTADO_SUBSANACION_064"));
			subsanacionExpediente.setFechaLimite(resultSet.getDate("FECHA_LIMITE_064"));
			subsanacionExpediente.setHoraLimite(resultSet.getString("HORA_LIMITE_064"));
			return subsanacionExpediente;

		}
	};
	private RowMapper<SubsanacionExpediente> rwMapNegociacionFecha = new RowMapper<SubsanacionExpediente>() {
		@Override
		public SubsanacionExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
			subsanacionExpediente.setId(resultSet.getLong("ID_064"));
			subsanacionExpediente.setEstado(resultSet.getInt("ESTADO_SUBSANACION_064"));
			subsanacionExpediente.setFechaLimite(resultSet.getDate("FECHA_LIMITE_064"));
			subsanacionExpediente.setHoraLimite(resultSet.getString("HORA_LIMITE_064"));
			subsanacionExpediente.setFechaEntrega(resultSet.getDate("FECHA_ENTREGA_064"));
			subsanacionExpediente.setHoraEntrega(resultSet.getString("HORA_ENTREGA_064"));
			return subsanacionExpediente;

		}
	};

    private RowMapper<ExpedienteTradRev> rwMapFechaIzo = new RowMapper<ExpedienteTradRev>() {
        @Override
        public ExpedienteTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
        	expedienteTradRev.setFechaFinalIzo(resultSet.getDate(FECHA_FINAL_IZO_EXPEDIENTE_TRAD_REV));
        	expedienteTradRev.setHoraFinalIzo(DateUtils.obtHoraFormateada(resultSet.getTime(FECHA_FINAL_IZO_EXPEDIENTE_TRAD_REV)).toString());

        	return expedienteTradRev;

//            return new ExpedienteTradRev(resultSet.getDate(FECHA_FINAL_IZO_EXPEDIENTE_TRAD_REV), resultSet.getTime(FECHA_FINAL_IZO_EXPEDIENTE_TRAD_REV));
        }
    };

	/**
	 * Constructor de la clase.
	 */
	public ExpedienteTradRevDaoImpl() {
		/* Constructor */
		super(ExpedienteTradRev.class);
	}

	/**
	 * Inserts a single row in the ExpedienteTradRev table.
	 *
	 * @param expedienteTradRev ExpedienteTradRev
	 * @return ExpedienteTradRev
	 */
	@Override
	public ExpedienteTradRev add(ExpedienteTradRev expedienteTradRev) {
		Date fecha = expedienteTradRev.getFechaFinalIzo();
		String hora = expedienteTradRev.getHoraFinalIzo();

		String query = "INSERT INTO AA79B53S01 (ANYO_053, NUM_EXP_053, IND_PUBLICAR_BOPV_053, IND_PREVISTO_BOPV_053, "
				+ ID_IDIOMA_EXPEDIENTE_TRAD_REV
				+ ", IND_CONFIDENCIAL_053, IND_CORREDACCION_053, TEXTO_053, TIPO_REDACCION_053, "
				+ "PARTICIPANTES_053, REF_TRAMITAGUNE_053, NUM_TOTAL_PAL_IZO_053, FECHA_FINAL_IZO_053, "
				+ "IND_FACTURABLE_053, ID_RELEVANCIA_053, IND_URGENTE_053,FECHA_FINAL_SOLIC_053,FECHA_FINAL_PROP_053,"
				+ "IND_PRESUPUESTO_053, NUM_TOTAL_PAL_SOLIC_053, IND_OBSERVACIONES_053) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, expedienteTradRev.getAnyo(), expedienteTradRev.getNumExp(),
				Utils.getValueInd(expedienteTradRev.getIndPublicarBopv()),
				Utils.getValueInd(expedienteTradRev.getIndPrevistoBopv()), expedienteTradRev.getIdIdioma(),
				Utils.getValueInd(expedienteTradRev.getIndConfidencial()),
				Utils.getValueInd(expedienteTradRev.getIndCorredaccion()), expedienteTradRev.getTexto(),
				expedienteTradRev.getTipoRedaccion(), expedienteTradRev.getParticipantes(),
				expedienteTradRev.getRefTramitagune(), expedienteTradRev.getNumTotalPalIzo(),
				DateUtils.obtFechaHoraFormateada(fecha, hora), Utils.getValueInd(expedienteTradRev.getIndFacturable()),
				expedienteTradRev.getIdRelevancia(), Utils.getValueInd(expedienteTradRev.getIndUrgente()),
				expedienteTradRev.getFechaFinalSolic(), expedienteTradRev.getFechaFinalProp(),
				Utils.getValueInd(expedienteTradRev.getIndPresupuesto()), expedienteTradRev.getNumTotalPalSolic(),
				Utils.getValueInd(expedienteTradRev.getIndObservaciones()));

		return expedienteTradRev;
	}

	/**
	 * Updates a single row in the ExpedienteTradRev table.
	 *
	 * @param expedienteTradRev ExpedienteTradRev
	 * @return ExpedienteTradRev
	 */

	@Override
	public ExpedienteTradRev update(ExpedienteTradRev expedienteTradRev) {
		String query = "UPDATE AA79B53S01 SET  IND_PUBLICAR_BOPV_053=?, IND_PREVISTO_BOPV_053=?,"
				+ ID_IDIOMA_EXPEDIENTE_TRAD_REV + "=?, IND_CONFIDENCIAL_053=?, IND_CORREDACCION_053=?, TEXTO_053=?, "
				+ "TIPO_REDACCION_053=?, PARTICIPANTES_053=?, REF_TRAMITAGUNE_053=?, IND_OBSERVACIONES_053=?, IND_PUBLICADO_BOE_053=?, URL_BOE_053=?, IND_DIFICIL_053=? "
				+ "WHERE ANYO_053=? AND NUM_EXP_053=?";

		this.getJdbcTemplate().update(query, Utils.getValueInd(expedienteTradRev.getIndPublicarBopv()),
				Utils.getValueInd(expedienteTradRev.getIndPrevistoBopv()), expedienteTradRev.getIdIdioma(),
				Utils.getValueInd(expedienteTradRev.getIndConfidencial()),
				Utils.getValueInd(expedienteTradRev.getIndCorredaccion()), expedienteTradRev.getTexto(),
				expedienteTradRev.getTipoRedaccion(), expedienteTradRev.getParticipantes(),
				expedienteTradRev.getRefTramitagune(), Utils.getValueInd(expedienteTradRev.getIndObservaciones()),
				Utils.getValueInd(expedienteTradRev.getIndPublicadoBoe()), expedienteTradRev.getUrlBoe(),
				Utils.getValueInd(expedienteTradRev.getIndDificil()), expedienteTradRev.getAnyo(),
				expedienteTradRev.getNumExp());

		return expedienteTradRev;
	}

	/**
	 * Mét odo específico para AA79B - Facturacion y pagos > Revisión de datos de
	 * facturacion > Detalle revisión datos de facturación
	 *
	 * @param expedienteTradRev
	 * @return
	 */
	@Override
	public ExpedienteTradRev updateRevisionDatosFacturacion(ExpedienteTradRev expedienteTradRev) {
		String query = "UPDATE AA79B53S01 SET IND_URGENTE_053=?, IND_DIFICIL_053=?, ID_RELEVANCIA_053=?, IND_FACTURABLE_053=?, ID_IDIOMA_053=? "
				+ "WHERE ANYO_053=? AND NUM_EXP_053=?";

		this.getJdbcTemplate().update(query, Utils.getValueInd(expedienteTradRev.getIndUrgente()),
				Utils.getValueInd(expedienteTradRev.getIndDificil()), expedienteTradRev.getIdRelevancia(),
				Utils.getValueInd(expedienteTradRev.getIndFacturable()), expedienteTradRev.getIdIdioma(),
				expedienteTradRev.getAnyo(), expedienteTradRev.getNumExp());

		return expedienteTradRev;
	}

	/**
	 * Updates a single row in the ExpedienteTradRev table.
	 *
	 * @param expedienteTradRev ExpedienteTradRev
	 * @return ExpedienteTradRev
	 */
	@Override
	public ExpedienteTradRev guardarParcial(ExpedienteTradRev expedienteTradRev) {
		Date fecha = expedienteTradRev.getFechaFinalIzo();
		String hora = expedienteTradRev.getHoraFinalIzo();

		String query = "UPDATE AA79B53S01 SET NUM_TOTAL_PAL_IZO_053=?, FECHA_FINAL_IZO_053=?, "
				+ "IND_FACTURABLE_053=?, ID_RELEVANCIA_053=?, IND_URGENTE_053=?,IND_PRESUPUESTO_053=?,IND_DIFICIL_053=?"
				+ " WHERE ANYO_053=? AND NUM_EXP_053=?";
		this.getJdbcTemplate().update(query, expedienteTradRev.getNumTotalPalIzo(),
				DateUtils.obtFechaHoraFormateada(fecha, hora), Utils.getValueInd(expedienteTradRev.getIndFacturable()),
				expedienteTradRev.getIdRelevancia(), Utils.getValueInd(expedienteTradRev.getIndUrgente()),
				Utils.getValueInd(expedienteTradRev.getIndPresupuesto()),
				Utils.getValueInd(expedienteTradRev.getIndDificil()), expedienteTradRev.getAnyo(),
				expedienteTradRev.getNumExp());

		return expedienteTradRev;
	}

	/**
	 * guardarFFinalIzo: Modificacion de la fecha y hora final izo
	 *
	 * @param expedienteTradRev ExpedienteTradRev
	 * @return ExpedienteTradRev
	 */
	@Override
	public ExpedienteTradRev guardarFFinalIzo(ExpedienteTradRev expedienteTradRev) {
		Date fecha = expedienteTradRev.getFechaFinalIzo();
		String hora = expedienteTradRev.getHoraFinalIzo();

		String query = "UPDATE AA79B53S01 SET FECHA_FINAL_IZO_053=? WHERE ANYO_053=? AND NUM_EXP_053=?";
		this.getJdbcTemplate().update(query, DateUtils.obtFechaHoraFormateada(fecha, hora), expedienteTradRev.getAnyo(),
				expedienteTradRev.getNumExp());
		return expedienteTradRev;
	}

	@Override
	public ExpedienteTradRev updateDatosAceptacionPpto(ExpedienteTradRev expedienteTradRev, String idioma) {
		return this.updateDatosAceptacionPpto(expedienteTradRev, idioma, TipoExpedienteEnum.TRADUCCION.getValue());
	}

	@Override
	public ExpedienteTradRev updateDatosAceptacionPpto(ExpedienteTradRev expedienteTradRev, String idioma,
			String tipoExp) {

		SubsanacionExpediente subsanacionExpedienteOriginal;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().contentEquals(tipoExp)) {
			subsanacionExpedienteOriginal = this.findDatosEstadoPresupuesto(expedienteTradRev,
					TipoExpedienteEnum.INTERPRETACION.getValue());
		} else {
			subsanacionExpedienteOriginal = this.findDatosEstadoPresupuesto(expedienteTradRev);
		}

		// el original para comparar el cambio de estado para acciones
		// posteriores...
		Date fecha = expedienteTradRev.getAceptacionPresupuesto().getFechaLimite();
		String hora = expedienteTradRev.getAceptacionPresupuesto().getHoraLimite();

		StringBuilder query = new StringBuilder("UPDATE AA79B64S01 SET FECHA_LIMITE_064=?, ESTADO_SUBSANACION_064=? ");
		// Si ha cambiado el estado de la subsanacion...
		if (expedienteTradRev.getAceptacionPresupuesto().getEstado() != subsanacionExpedienteOriginal.getEstado()) {
			query.append(",IND_SUBSANADO_064 = '");
			query.append(Constants.SI);
			query.append("', FECHA_ACEPTACION_064 = SYSDATE ");
		}
		query.append(" WHERE ID_064 = ? ");

		this.getJdbcTemplate().update(query.toString(), DateUtils.obtFechaHoraFormateada(fecha, hora),
				expedienteTradRev.getAceptacionPresupuesto().getEstado(), subsanacionExpedienteOriginal.getId());

		// El tipo de requerimiento, aunque no llega, es 2 (ACEPTACION
		// PRESUPUESTO)

		// Inserción de estados en la bitácora...
		// Si ha cambiado el estado de la subsanacion...
		if (expedienteTradRev.getAceptacionPresupuesto().getEstado() != subsanacionExpedienteOriginal.getEstado()) {

			Locale locale = new Locale(idioma);

			BitacoraExpediente bExp = new BitacoraExpediente();
			bExp.setAnyo(expedienteTradRev.getAnyo());
			bExp.setNumExp(expedienteTradRev.getNumExp());

			EstadosExpediente estado = new EstadosExpediente();
			estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bExp.setEstadoExp(estado);

			FasesExpediente fases = new FasesExpediente();
			fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bExp.setFaseExp(fases);

			// Si ha sido ACEPTADO...
			if (EstadoRequerimientoEnum.ACEPTADA.getValue() == expedienteTradRev.getAceptacionPresupuesto()
					.getEstado()) {
				bExp.setDatoAdic(this.msg.getMessage("comun.aceptado", null, locale));
			} else {
				// Si ha sido RECHAZADO...
				bExp.setDatoAdic(this.msg.getMessage("comun.estadoRechazada", null, locale));
			}
			this.bitacoraExpedienteService.add(bExp);

			Expediente exp = new Expediente();
			exp.setAnyo(expedienteTradRev.getAnyo());
			exp.setNumExp(expedienteTradRev.getNumExp());
			exp = this.expedienteService.find(exp);
			exp.setBitacoraExpediente(bExp);
			this.expedienteService.modificarEstado(exp);

			BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(expedienteTradRev.getAnyo());
			bitacoraSolicitante.setNumExp(expedienteTradRev.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);
			if (EstadoRequerimientoEnum.ACEPTADA.getValue() == expedienteTradRev.getAceptacionPresupuesto()
					.getEstado()) {
				this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
						AccionBitacoraEnum.ACEPT_PRESUP.getValue());
				// segundo estado de bitácora en caso de aceptación
				// pasa a fase "pendiente de ejecutar tareas". En caso de
				// rechazo no se añade
				fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
				bExp.setFaseExp(fases);
				bExp.setIdMotivoRechazo(null);
				bExp.setDatoAdic("");
				bExp.setInfoAdic("");
				exp.setBitacoraExpediente(bExp);

				this.bitacoraExpedienteService.add(bExp);
				this.expedienteService.modificarEstado(exp);

				// se lanza de nuevo la ejecución de tarea
				BigDecimal elIdTarea = this.subsanacionService
						.getIdTareaConRequerimiento(subsanacionExpedienteOriginal.getId());

				Tareas laTarea = new Tareas();
				laTarea.setIdTarea(elIdTarea);

				FinalizarTareaUtils finalizarTareaUtils = new FinalizarTareaUtils();
				finalizarTareaUtils.procesoBatchEnvioMails(laTarea, null);

			} else {
				this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
						AccionBitacoraEnum.RECHAZ_PRESUP.getValue());
				// Se anula el expediente
				this.anulacionesService.anularExpAutomaticamente(expedienteTradRev.getAnyo(), expedienteTradRev.getNumExp(),
						null);
			}
		}
		return expedienteTradRev;
	}

	@Override
	public ExpedienteTradRev updateDatosAceptacionFechaHora(ExpedienteTradRev expedienteTradRev, String idioma) {

		SubsanacionExpediente subsanacionExpedienteOriginal;
		subsanacionExpedienteOriginal = this.findDatosNegociacionFecha(expedienteTradRev);

		// el original para comparar el cambio de estado para acciones
		// posteriores...
		Date fecha = expedienteTradRev.getAceptacionFechaHora().getFechaLimite();
		String hora = expedienteTradRev.getAceptacionFechaHora().getHoraLimite();

		StringBuilder query = new StringBuilder("UPDATE AA79B64S01 SET FECHA_LIMITE_064=?, ESTADO_SUBSANACION_064=? ");
		// Si ha cambiado el estado de la subsanacion...
		if (expedienteTradRev.getAceptacionFechaHora().getEstado() != subsanacionExpedienteOriginal.getEstado()) {
			query.append(",IND_SUBSANADO_064 = '");
			query.append(Constants.SI);
			query.append("', FECHA_ACEPTACION_064 = SYSDATE ");
		}
		query.append(" WHERE ID_064 = ? ");

		this.getJdbcTemplate().update(query.toString(), DateUtils.obtFechaHoraFormateada(fecha, hora),
				expedienteTradRev.getAceptacionFechaHora().getEstado(), subsanacionExpedienteOriginal.getId());
		// Inserción de estados en la bitácora...
		// Si ha cambiado el estado de la subsanacion...
		if (expedienteTradRev.getAceptacionFechaHora().getEstado() != subsanacionExpedienteOriginal.getEstado()) {

			Locale locale = new Locale(idioma);

			BitacoraExpediente bExp = new BitacoraExpediente();
			bExp.setAnyo(expedienteTradRev.getAnyo());
			bExp.setNumExp(expedienteTradRev.getNumExp());

			EstadosExpediente estado = new EstadosExpediente();
			estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bExp.setEstadoExp(estado);

			FasesExpediente fases = new FasesExpediente();
			fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bExp.setFaseExp(fases);

			// Si ha sido ACEPTADO...
			if (EstadoRequerimientoEnum.ACEPTADA.getValue() == expedienteTradRev.getAceptacionFechaHora().getEstado()) {
				bExp.setDatoAdic(this.msg.getMessage("comun.aceptado", null, locale));
			} else {
				// Si ha sido RECHAZADO...
				bExp.setDatoAdic(this.msg.getMessage("comun.estadoRechazada", null, locale));
			}
			this.bitacoraExpedienteService.add(bExp);

			Expediente exp = new Expediente();
			exp.setAnyo(expedienteTradRev.getAnyo());
			exp.setNumExp(expedienteTradRev.getNumExp());
			exp = this.expedienteService.find(exp);
			exp.setBitacoraExpediente(bExp);
			this.expedienteService.modificarEstado(exp);

			BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(expedienteTradRev.getAnyo());
			bitacoraSolicitante.setNumExp(expedienteTradRev.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);
			if (EstadoRequerimientoEnum.ACEPTADA.getValue() == expedienteTradRev.getAceptacionFechaHora().getEstado()) {
				this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
						AccionBitacoraEnum.ACEPT_FECHA_PROP_IZO.getValue());

				// se actualiza la nueva fecha de entrega
				this.updateFechaEntregaIZO(expedienteTradRev, subsanacionExpedienteOriginal.getFechaEntrega(),
						subsanacionExpedienteOriginal.getHoraEntrega());

			} else {
				this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
						AccionBitacoraEnum.RECHAZ_FECHA_PROP_IZO.getValue());
			}
			// segundo estado de bitácora en caso de aceptación
			// pasa a fase "pendiente de ejecutar tareas". En caso de
			// rechazo no se añade
			fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
			bExp.setFaseExp(fases);
			bExp.setIdMotivoRechazo(null);
			bExp.setDatoAdic("");
			bExp.setInfoAdic("");
			exp.setBitacoraExpediente(bExp);

			this.bitacoraExpedienteService.add(bExp);
			this.expedienteService.modificarEstado(exp);
		}
		return expedienteTradRev;
	}

	@Override
	public ExpedienteTradRev updateIndFacturable(ExpedienteTradRev expedienteTradRev) {
		String query = "UPDATE AA79B53S01 SET IND_FACTURABLE_053=? WHERE ANYO_053=? AND NUM_EXP_053=?";

		this.getJdbcTemplate().update(query, Utils.getValueInd(expedienteTradRev.getIndFacturable()),
				expedienteTradRev.getAnyo(), expedienteTradRev.getNumExp());

		return expedienteTradRev;
	}

	/**
	 *
	 * @param expedienteTradRev
	 * @return
	 */
	@Override
	public void actualizarNumPalSolicConIzo(Long anyo, Integer numExp) {
		String query = "UPDATE AA79B53S01 SET NUM_TOTAL_PAL_SOLIC_053 = NUM_TOTAL_PAL_IZO_053 WHERE ANYO_053=? AND NUM_EXP_053=?";
		this.getJdbcTemplate().update(query, anyo, numExp);
	}

	/**
	 * actualizarSumaNumTotalPalIzo
	 *
	 * @param expedienteTradRev
	 * @return
	 */
	@Override
	public void actualizarSumaNumTotalPalIzo(Long anyo, Integer numExp) {
		StringBuilder query = new StringBuilder("UPDATE AA79B53S01 t1 SET NUM_TOTAL_PAL_IZO_053 =");
		query.append(
				" (SELECT SUM(NUM_PAL_IZO_056) FROM AA79B56S01 t2 WHERE t2.ANYO_056 = ? AND t2.NUM_EXP_056 = ? AND (t2.CLASE_DOCUMENTO_056 = 1 OR t2.CLASE_DOCUMENTO_056 = 2))");
		query.append(" WHERE t1.ANYO_053 = ? AND t1.NUM_EXP_053 = ?");
		this.getJdbcTemplate().update(query.toString(), anyo, numExp, anyo, numExp);
	}

	/**
	 *
	 * @param anyo   Long
	 * @param numExp Integer
	 */
	@Override
	public void actualizarT53ConfidencialSI(Long anyo, Integer numExp) {
		StringBuilder query = new StringBuilder("UPDATE AA79B53S01 SET IND_CONFIDENCIAL_053 = '").append(Constants.SI)
				.append("' WHERE ANYO_053=? AND NUM_EXP_053=?");
		this.getJdbcTemplate().update(query.toString(), anyo, numExp);
	}

	@Override()
	public void procRecalcularCamposDocumentacionPL(final Long anyo, final Integer numExp) {

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));

		this.getJdbcTemplate().call(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call ACT_DATOS_DOCUMENTACION (?,?)}");
				callableStatement.setLong(1, anyo);
				callableStatement.setInt(2, numExp);
				return callableStatement;
			}
		}, paramList);
	}

	@Override()
	public void procAsociarDocAEntregaPL(final Long anyo, final Integer numExp, final BigDecimal idDoc) {

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));

		this.getJdbcTemplate().call(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call ASOCIAR_DOC_A_ENTREGA (?,?,?)}");
				callableStatement.setLong(1, anyo);
				callableStatement.setInt(2, numExp);
				callableStatement.setBigDecimal(3, idDoc);
				return callableStatement;
			}
		}, paramList);
	}

	@Override
	protected String getSelect() {

		Locale es = new Locale("es");
		Locale eu = new Locale("eu");

		StringBuilder query = new StringBuilder();
		query.append("SELECT t1.ANYO_053 ANYO_053");
		query.append(", t1.NUM_EXP_053 NUM_EXP_053");
		query.append(", t1.IND_PUBLICAR_BOPV_053 IND_PUBLICAR_BOPV_053");
		query.append(", DECODE(t1.IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PUBLICARBOPVDESCES");
		query.append(", DECODE(t1.IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PUBLICARBOPVDESCEU");
		query.append(", t1.IND_PREVISTO_BOPV_053 IND_PREVISTO_BOPV_053");
		query.append(", t1." + ID_IDIOMA_EXPEDIENTE_TRAD_REV + " " + ID_IDIOMA_EXPEDIENTE_TRAD_REV + "");
		query.append(", t1.IND_CONFIDENCIAL_053 IND_CONFIDENCIAL_053");
		query.append(", t1.IND_CORREDACCION_053 IND_CORREDACCION_053");
		query.append(", t1.TEXTO_053 TEXTO_053");
		query.append(", t1.TIPO_REDACCION_053 TIPO_REDACCION_053");
		query.append(", t1.PARTICIPANTES_053 PARTICIPANTES_053");
		query.append(", t1.REF_TRAMITAGUNE_053 REF_TRAMITAGUNE_053");
		query.append(", t1.NUM_TOTAL_PAL_IZO_053 NUM_TOTAL_PAL_IZO_053");
		query.append(", t1.FECHA_FINAL_IZO_053 FECHA_FINAL_IZO_053");
		query.append(", TO_CHAR(t1.FECHA_FINAL_IZO_053,'HH24:MI') HORA_FINAL_IZO_053");
		query.append(", t1.IND_FACTURABLE_053 IND_FACTURABLE_053");
		query.append(", t1.ID_RELEVANCIA_053 ID_RELEVANCIA_053");
		query.append(", t1.IND_URGENTE_053 IND_URGENTE_053");
		query.append(", t1.FECHA_FINAL_SOLIC_053 FECHA_FINAL_SOLIC_053");
		query.append(", t1.FECHA_FINAL_PROP_053 FECHA_FINAL_PROP_053");
		query.append(", t1.IND_PRESUPUESTO_053 IND_PRESUPUESTO_053");
		query.append(", t1.NUM_TOTAL_PAL_SOLIC_053 NUM_TOTAL_PAL_SOLIC_053");
		query.append(", t1.IND_OBSERVACIONES_053 IND_OBSERVACIONES_053");
		query.append(", t1.IND_DIFICIL_053 IND_DIFICIL_053");

		return query.toString();
	}

	@Override
	protected String getFrom() {
		return " FROM AA79B53S01 t1 ";
	}

	@Override
	protected RowMapper<ExpedienteTradRev> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ExpedienteTradRevDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return ANYO_EXPEDIENTE_TRAD_REV + "|| '_' ||" + NUM_EXP_EXPEDIENTE_TRAD_REV;
	}

	@Override
	protected RowMapper<ExpedienteTradRev> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(ExpedienteTradRev bean, List<Object> params) {
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		return " WHERE t1.ANYO_053 = ? AND t1.NUM_EXP_053 = ?";
	}

	@Override
	protected String getWhere(ExpedienteTradRev bean, List<Object> params) {
		StringBuilder where = new StringBuilder(ExpedienteTradRevDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ANYO_053", bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_053", bean.getNumExp(), params));
		where.append(SqlUtils.generarWhereIgual("t1.IND_PUBLICAR_BOPV_053", bean.getIndPublicarBopv(), params));
		where.append(SqlUtils.generarWhereIgual("t1.IND_PUBLICAR_BOPV_053", bean.getIndPublicarBopv(), params));

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	protected String getWhereLike(ExpedienteTradRev bean, Boolean startsWith, List<Object> params, Boolean search) {
		return "";
	}

	@Override
	public SubsanacionExpediente findDatosEstadoPresupuesto(ExpedienteTradRev bean) {
		return findDatosEstadoPresupuesto(bean, TipoExpedienteEnum.TRADUCCION.getValue());
	}

	@Override
	public SubsanacionExpediente findDatosNegociacionFecha(ExpedienteTradRev bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(
				"SELECT t2.ID_064 ID_064, t2.FECHA_LIMITE_064 FECHA_LIMITE_064, TO_CHAR(t2.FECHA_LIMITE_064,'HH24:MI') HORA_LIMITE_064, t2.ESTADO_SUBSANACION_064 ESTADO_SUBSANACION_064, T2.FECHA_ENTREGA_064 FECHA_ENTREGA_064, TO_CHAR(T2.FECHA_ENTREGA_064,'HH24:MI') HORA_ENTREGA_064 FROM AA79B59S01 t1 JOIN AA79B64S01 t2 ON t2.ID_064 = t1.ID_REQUERIMIENTO_059 WHERE t1.ANYO_059 =? AND t1.NUM_EXP_059 =? AND t2.TIPO_REQUERIMIENTO_064 = ? ORDER BY ID_064 DESC");
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		params.add(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue());

		List<SubsanacionExpediente> listaSubsanacionExpediente = this.getJdbcTemplate().query(query.toString(),
				this.rwMapNegociacionFecha, params.toArray());
		if (!listaSubsanacionExpediente.isEmpty()) {
			return listaSubsanacionExpediente.get(0);
		} else {
			return null;
		}
	}

	@Override
	public SubsanacionExpediente findDatosEstadoPresupuesto(ExpedienteTradRev bean, String tipoExp) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(
				"SELECT t2.ID_064 ID_064, t2.FECHA_LIMITE_064 FECHA_LIMITE_064, TO_CHAR(t2.FECHA_LIMITE_064,'HH24:MI') HORA_LIMITE_064, t2.ESTADO_SUBSANACION_064 ESTADO_SUBSANACION_064 FROM AA79B81S01 t1 JOIN AA79B64S01 t2 ON t1.ID_REQUERIMIENTO_081 = t2.ID_064 WHERE t1.ANYO_081 = ? AND t1.NUM_EXP_081 = ? AND t1.ID_TIPO_TAREA_081 = ");

		if (TipoExpedienteEnum.INTERPRETACION.getValue().contentEquals(tipoExp)) {
			query.append(TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue());
		} else {
			query.append(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		}

		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		List<SubsanacionExpediente> listaSubsanacionExpediente = this.getJdbcTemplate().query(query.toString(),
				this.rwMapDatosEstadoPpto, params.toArray());
		return DataAccessUtils.uniqueResult(listaSubsanacionExpediente);
	}

	@Override
	public DocPresupuestoTraduccionInfoExped getInfoDocumentoPresupuestoTraduccion(Long anyo, Integer numExp) {
		return this.getInfoDocumentoPresupuestoTraduccionLang(anyo, numExp, Constants.LANG_CASTELLANO);
	}

	@Override
	public DocPresupuestoTraduccionInfoExped getInfoDocumentoPresupuestoTraduccionLang(Long anyo, Integer numExp,
			String idioma) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		Locale locale = new Locale(idioma);
		query.append(
				"SELECT ANYO_051 ANYO_051, NUM_EXP_051 NUM_EXP_051, ID_TIPO_EXPEDIENTE_051 ID_TIPO_EXPEDIENTE_051, TITULO_051 TITULO_051, ");
		query.append("DECODE(ID_TIPO_EXPEDIENTE_051, '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage("label.interpretacionAbr", null, locale))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage("label.traduccionAbr", null, locale))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage("label.revisionAbr", null, locale)).append("') AS TIPO_EXP_DESC, ");
		query.append(DECODE_ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACION, null, locale))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCION, null, locale))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISION, null, locale))
				.append("') AS TIPO_EXPEDIENTE_DESCRIPCION, ");
		query.append(
				"FECHA_ALTA_051 FECHA_ALTA_051, TO_CHAR(FECHA_ALTA_051,'HH24:MI') HORA_ALTA_051, FECHA_FINAL_IZO_053 FECHA_FINAL_IZO_053, TO_CHAR(FECHA_FINAL_IZO_053,'HH24:MI') HORA_FINAL_IZO_053, ");
		query.append("IND_PUBLICAR_BOPV_053 IND_PUBLICAR_BOPV_053, IND_PREVISTO_BOPV_053 IND_PREVISTO_BOPV_053, "
				+ ID_IDIOMA_EXPEDIENTE_TRAD_REV + " " + ID_IDIOMA_EXPEDIENTE_TRAD_REV + ", ");
		query.append("DESC_IDIOMA_ES_009 DESC_IDIOMA_ES_009, DESC_IDIOMA_EU_009 DESC_IDIOMA_EU_009, ");
		query.append("DECODE(IND_PUBLICAR_BOPV_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale))
				.append("') AS PUBLICAR_BOPV_DESC_053, ");
		query.append(
				"ID_RELEVANCIA_053, IND_URGENTE_053 IND_URGENTE_053, IND_DIFICIL_053 IND_DIFICIL_053, DESC_RELEVANCIA_ES_010, DESC_RELEVANCIA_EU_010, ");
		query.append("DECODE(IND_URGENTE_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)).append("') AS URGENTE_DESC_053, ");
		query.append("DECODE(IND_DIFICIL_053, '").append(ActivoEnum.SI.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)).append("', '")
				.append(ActivoEnum.NO.getValue()).append("', '")
				.append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)).append("') AS DIFICIL_DESC_053 ");
		query.append("FROM AA79B51S01 JOIN AA79B53S01 ON ANYO_051 = ANYO_053 AND NUM_EXP_051 = NUM_EXP_053 ");
		query.append("LEFT JOIN AA79B10S01 ON ID_TIPO_RELEVANCIA_010 = ID_RELEVANCIA_053 ");
		query.append("LEFT JOIN AA79B09S01 ON ID_IDIOMA_009 = " + ID_IDIOMA_EXPEDIENTE_TRAD_REV + " ");
		query.append(DaoConstants.WHERE);
		query.append("ANYO_051 = ? AND NUM_EXP_051 = ? ");

		params.add(anyo);
		params.add(numExp);

		List<DocPresupuestoTraduccionInfoExped> listaDocPresupuestoTraduccionInfoExped = this.getJdbcTemplate()
				.query(query.toString(), this.rwMapDocPptoTraduccion, params.toArray());
		return DataAccessUtils.uniqueResult(listaDocPresupuestoTraduccionInfoExped);
	}

	@Override
	public int isExpConPptoAceptado(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		int rst = 0;

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(getFromExpConPptoAceptado());
		query.append(getWhereExpConPptoAceptado());

		params.add(anyo);
		params.add(numExp);

		Long cont = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);

		if (cont > 0) {
			rst = 1;
		}

		return rst;
	}

	/**
	 * @return String
	 */
	private String getFromExpConPptoAceptado() {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.FROM + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_81 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_081 + DaoConstants.SIGNOIGUAL
				+ DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_053);
		query.append(DaoConstants.AND + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_053);
		query.append(DaoConstants.AND + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081
				+ DaoConstants.SIGNOIGUAL + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		query.append(DaoConstants.JOIN + DBConstants.TABLA_064 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_REQUERIMIENTO_081
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_064);
		query.append(DaoConstants.AND + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.TIPO_REQUERIMIENTO_064);
		query.append(DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
				+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() + DaoConstants.SIGNO_COMA
				+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AND + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_SUBSANADO_064
				+ DaoConstants.SIGNOIGUAL + DaoConstants.SIGNO_APOSTROFO + Constants.SI + DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.AND + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ESTADO_SUBSANACION_064
				+ DaoConstants.SIGNOIGUAL + EstadoSubsanacionEnum.ACEPTADO.getValue());

		return query.toString();
	}

	/**
	 * @return String
	 */
	private String getWhereExpConPptoAceptado() {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_053
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_053
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_053
				+ DaoConstants.SIGNOIGUAL + DaoConstants.SIGNO_APOSTROFO + Constants.SI + DaoConstants.SIGNO_APOSTROFO);

		return query.toString();
	}

	@Override
	public ExpedienteTradRev updateFechaEntregaReal(ExpedienteTradRev expedienteTradRev) {
		String query = "UPDATE AA79B53S01 SET FECHA_ENTREGA_REAL_053 = SYSDATE WHERE ANYO_053 = ? AND NUM_EXP_053 = ?";

		this.getJdbcTemplate().update(query, expedienteTradRev.getAnyo(), expedienteTradRev.getNumExp());

		return expedienteTradRev;
	}

	@Override
	public ExpedienteTradRev updateFechaEntregaIZO(ExpedienteTradRev expedienteTradRev, Date fechaEntrega,
			String horaEntrega) {
		String query = "UPDATE AA79B53S01 SET FECHA_FINAL_IZO_053 = ? WHERE ANYO_053 = ? AND NUM_EXP_053 = ?";

		this.getJdbcTemplate().update(query, DateUtils.obtFechaHoraFormateada(fechaEntrega, horaEntrega),
				expedienteTradRev.getAnyo(), expedienteTradRev.getNumExp());

		return expedienteTradRev;
	}

    @Override
    public ExpedienteTradRev getFechaIzo(ExpedienteTradRev expedienteTradRev) {
        String query = "SELECT FECHA_FINAL_IZO_053 FROM AA79B53T00 WHERE NUM_EXP_053 = ? AND ANYO_053 = ?";

        return DataAccessUtils.uniqueResult(this.getJdbcTemplate().query(query, this.rwMapFechaIzo, expedienteTradRev.getNumExp(), expedienteTradRev.getAnyo()));
    }

}
