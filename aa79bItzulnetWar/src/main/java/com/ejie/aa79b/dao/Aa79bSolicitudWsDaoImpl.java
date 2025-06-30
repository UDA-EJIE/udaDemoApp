package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.ExcepcionFacturacion;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.Aa06aAccionSolicitudEnum;
import com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum;
import com.ejie.aa79b.model.enums.OrigenExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoExcepcionEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoOrigenFicheroEnum;
import com.ejie.aa79b.model.webservices.Aa79bContactoDoc;
import com.ejie.aa79b.model.webservices.Aa79bDireccionNora;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;
import com.ejie.aa79b.model.webservices.Aa79bFicheroObservaciones;
import com.ejie.aa79b.model.webservices.Aa79bRegistroExpediente;
import com.ejie.aa79b.model.webservices.Aa79bSolicitante;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;
import com.ejie.aa79b.service.ExpedienteTradRevService;
import com.ejie.aa79b.service.SolicitanteService;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.PLConnectionUtils;
import com.ejie.aa79b.utils.Utils;

@Repository
@Transactional
public class Aa79bSolicitudWsDaoImpl extends GenericoDaoImpl<Aa79bSolicitud> implements Aa79bSolicitudWsDao {

	private static final String AND_NUM_EXP_0 = "=? AND NUM_EXP_0";
	private static final String NO_SE_PUEDE_ELIMINAR = "No se puede eliminar el fichero. No existe.";
	protected static final String ID036 = "ID036";
	private static final String DNICONTACTO036 = "DNICONTACTO036";
	private static final String PORFACTURA036 = "PORFACTURA036";

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired()
	private SolicitanteService solicitanteService;

	@Autowired()
	private ExpedienteTradRevService expedienteTradRevService;

	@Autowired()
	private ObservacionesExpedienteDao observacionesExpedienteDao;

	@Autowired()
	private DireccionNoraDao direccionNoraDao;

	@Autowired()
	private OidsAuxiliarDao oidsAuxiliarDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(Aa79bSolicitudWsDaoImpl.class);

	public Aa79bSolicitudWsDaoImpl() {
		// Constructor
		super(Aa79bSolicitud.class);
	}

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<ExcepcionFacturacion> rwMapExcepcionFacturacion = new RowMapper<ExcepcionFacturacion>() {
		@Override()
		public ExcepcionFacturacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ExcepcionFacturacion excepcionFacturacionAux = new ExcepcionFacturacion(resultSet.getLong(ID036));
			excepcionFacturacionAux.setTipoEntidad036(resultSet.getString("TIPOENTIDAD036"));
			excepcionFacturacionAux.setIdEntidad036(resultSet.getInt("IDENTIDAD036"));
			excepcionFacturacionAux.setTipoExcepcion036(resultSet.getInt("TIPOEXCEPCION036"));
			excepcionFacturacionAux.setTipoEntidadAsoc036(resultSet.getString("TIPOENTIDADASOC036"));
			excepcionFacturacionAux.setIdEntidadAsoc036(resultSet.getInt("IDENTIDADASOC036"));
			excepcionFacturacionAux.setDniContacto036(resultSet.getString(DNICONTACTO036));
			excepcionFacturacionAux.setPorFactura036(resultSet.getLong(PORFACTURA036));
			return excepcionFacturacionAux;
		}
	};

	private RowMapper<ExcepcionFacturacion> getrwMapExcepcionFacturacion() {
		return this.rwMapExcepcionFacturacion;
	}

	private RowMapper<Expediente> rwMapRelaciones = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			return expediente;
		}
	};

	@Override
	public Aa79bRegistroExpediente altaSolicitud(Solicitante solicitante, Aa79bSolicitud solicitud) {
		return null;
	}

	@Override
	protected String getSelect() {
		return null;
	}

	@Override
	protected String getFrom() {
		return null;
	}

	@Override
	protected RowMapper<Aa79bSolicitud> getRwMap() {
		return null;
	}

	@Override
	protected String[] getOrderBy() {
		return new String[0];
	}

	@Override
	protected String getPK() {
		return null;
	}

	@Override
	protected RowMapper<Aa79bSolicitud> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(Aa79bSolicitud bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhere(Aa79bSolicitud bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(Aa79bSolicitud bean, Boolean startsWith, List<Object> params, Boolean search) {
		return null;
	}

	/**
	 *
	 */
	@Override
	public Aa79bSolicitud add(Aa79bSolicitud solicitud) {
		long numExp = PLConnectionUtils.crearNumExp(solicitud.getAnyo(), this.getJdbcTemplate());
		if (numExp > 0) {
			solicitud.setNumExp(Integer.valueOf((int) numExp));

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			solicitud.setAnyo((long) year);

			String query = "INSERT INTO AA79B51S01 (ANYO_051, NUM_EXP_051, ID_TIPO_EXPEDIENTE_051, ORIGEN_051, "
					+ "FECHA_ALTA_051, TITULO_051, ESTADO_BITACORA_051, DNI_TECNICO_051) " + "VALUES (?,?,?,?,?,?,?,?)";
			this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
					solicitud.getTipoExpediente().getCodigo(), OrigenExpedienteEnum.SOLICITANTE.getValue(),
					DateUtils.obtFechaHoraFormateada(solicitud.getFechaAlta(), null), solicitud.getTitulo(), null,
					null);
			return solicitud;
		} else {
			return null;
		}
	}

	@Override
	public Aa79bSolicitud update(Aa79bSolicitud solicitud) {
		String query = "UPDATE AA79B51S01 SET " + " TITULO_051=?  WHERE ANYO_051=? AND NUM_EXP_051=?";
		this.getJdbcTemplate().update(query, solicitud.getTitulo(), solicitud.getAnyo(), solicitud.getNumExp());
		return solicitud;

	}

	/**
	 *
	 */
	@Override
	public int addSolicitud69(Aa79bSolicitud solicitud) {
		String query = "INSERT INTO AA79B69S01 (ANYO_069, NUM_EXP_069, ID_TIPO_EXPEDIENTE_069, FECHA_ALTA_069, TITULO_069) "
				+ "VALUES (?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
				solicitud.getTipoExpediente().getCodigo(),
				DateUtils.obtFechaHoraFormateada(solicitud.getFechaAlta(), null), solicitud.getTitulo());

	}

	/**
	 *
	 */
	@Override
	public long addBitacoraExpediente(BitacoraExpediente bitacoraExpediente) {
		StringBuilder sb = new StringBuilder();
		sb.append("( SELECT nvl(max(ID_ESTADO_BITACORA_059),0) + 1");
		sb.append(" FROM AA79B59S01 t1 ");
		sb.append(" LEFT JOIN AA79B64S01 s1 ON t1.ID_REQUERIMIENTO_059 = s1.ID_064");
		sb.append(" LEFT JOIN AA79B60S01 e1 ON t1.ID_ESTADO_EXP_059 = e1.ID_060");
		sb.append(" LEFT JOIN AA79B61S01 f1 ON t1.ID_FASE_EXPEDIENTE_059 = f1.ID_061");
		sb.append(" WHERE NUM_EXP_059 = ");
		sb.append(bitacoraExpediente.getNumExp());
		sb.append(" AND ANYO_059 = ");
		sb.append(bitacoraExpediente.getAnyo());
		sb.append(" )");

		Long idEstadoBitacora = this.getJdbcTemplate().queryForObject(sb.toString(), Long.class);

		String query = "INSERT INTO AA79B59S01 (ANYO_059, NUM_EXP_059, ID_ESTADO_BITACORA_059, ID_ESTADO_EXP_059, "
				+ "ID_FASE_EXPEDIENTE_059, DATO_ADIC_059, INFO_ADIC_059, ID_RECHAZO_059, "
				+ "ID_REQUERIMIENTO_059, FECHA_ALTA_059) " + "VALUES (?,?,?,?,?,?,?,?,?, SYSDATE)";
		this.getJdbcTemplate().update(query, bitacoraExpediente.getAnyo(), bitacoraExpediente.getNumExp(),
				idEstadoBitacora, bitacoraExpediente.getEstadoExp().getId(), bitacoraExpediente.getFaseExp().getId(),
				bitacoraExpediente.getDatoAdic(), bitacoraExpediente.getInfoAdic(),
				bitacoraExpediente.getIdMotivoRechazo(),
				bitacoraExpediente.getSubsanacionExp() != null ? bitacoraExpediente.getSubsanacionExp().getId() : null);

		return idEstadoBitacora;

	}

	/**
	 *
	 */
	@Override
	public int updateIdEstadoBitacora(Aa79bSolicitud solicitud) {
		String query = "UPDATE AA79B51S01 SET ESTADO_BITACORA_051=? WHERE ANYO_051=? AND NUM_EXP_051=?";
		return this.getJdbcTemplate().update(query, solicitud.getIdEstadoBitacora(), solicitud.getAnyo(),
				solicitud.getNumExp());

	}

	/**
	 *
	 */
	@Override
	public int addGestorExpediente(Aa79bSolicitud solicitud, Map<String, String> parametros) {
		if (solicitud.getGestor() != null) {
			// obtenemos el codigo y el tipo de entidad
			Solicitante solicitante = new Solicitante();
			solicitante.setDni(solicitud.getGestor().getDniGestor());
			Solicitante datosGestor = this.solicitanteService.find(solicitante);
			solicitud.getGestor().setTipoEntidadGestor(datosGestor.getEntidad().getTipo());
			solicitud.getGestor().setCodigoEntidadGestor(datosGestor.getEntidad().getCodigo());
			solicitud.getGestor().setIndVIPGestor(datosGestor.getGestorExpedientesVIP());
			solicitud.getGestor().setPreDniGestor(datosGestor.getPreDni());
			solicitud.getGestor().setSufDniGestor(datosGestor.getSufDni());
			this.insertGestorExpediente(solicitud);

			// Información de la facturación
			ExcepcionFacturacion excepcionFacturacion = new ExcepcionFacturacion();
			excepcionFacturacion.setIdEntidad036(solicitud.getGestor().getCodigoEntidadGestor());
			excepcionFacturacion.setTipoEntidad036(solicitud.getGestor().getTipoEntidadGestor());
			excepcionFacturacion.setTipoExcepcion036(Integer.valueOf(TipoExcepcionEnum.PORSOLICITANTE.getValue()));

			List<ExcepcionFacturacion> excepcionesFacturacion = this.findExcepcionesSolicitante(excepcionFacturacion,
					solicitud.getGestor().getDniGestor());

			if (excepcionesFacturacion.isEmpty()) {
				excepcionFacturacion.setTipoExcepcion036(Integer.valueOf(TipoExcepcionEnum.PORENTIDAD.getValue()));
				excepcionesFacturacion = this.findExcepcionesFacturacion(excepcionFacturacion);
			}

			ContactoFacturacionExpediente contactoFacturacionExpediente;

			if (!excepcionesFacturacion.isEmpty()) {

				for (ExcepcionFacturacion excepcionFacturacionAux : excepcionesFacturacion) {

					contactoFacturacionExpediente = new ContactoFacturacionExpediente();
					contactoFacturacionExpediente.setAnyo(solicitud.getAnyo());
					contactoFacturacionExpediente.setNumExp(solicitud.getNumExp());
					contactoFacturacionExpediente.setIdEntidadAsoc058(excepcionFacturacionAux.getIdEntidadAsoc036());
					contactoFacturacionExpediente
							.setTipoEntidadAsoc058(excepcionFacturacionAux.getTipoEntidadAsoc036());
					contactoFacturacionExpediente.setDniContacto058(excepcionFacturacionAux.getDniContacto036());
					contactoFacturacionExpediente.setPorFactura058(excepcionFacturacionAux.getPorFactura036());

					this.addContactoFacturacionExpediente(contactoFacturacionExpediente);

				}
			} else {
				contactoFacturacionExpediente = new ContactoFacturacionExpediente();
				contactoFacturacionExpediente.setAnyo(solicitud.getAnyo());
				contactoFacturacionExpediente.setNumExp(solicitud.getNumExp());
				contactoFacturacionExpediente.setIdEntidadAsoc058(solicitud.getGestor().getCodigoEntidadGestor());
				contactoFacturacionExpediente.setTipoEntidadAsoc058(solicitud.getGestor().getTipoEntidadGestor());
				contactoFacturacionExpediente.setDniContacto058(solicitud.getGestor().getDniGestor());
				contactoFacturacionExpediente.setPorFactura058((long) 100);

				this.addContactoFacturacionExpediente(contactoFacturacionExpediente);
			}

			parametros.put("gestor.dniCompletoGestor", "label.dniGestor");
			parametros.put("gestor.codigoEntidadGestor", "label.codigoEntidad");
			parametros.put("gestor.tipoEntidadGestor", "label.tipoEntidad");
		}
		return 0;
	}

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	private int insertGestorExpediente(Aa79bSolicitud solicitud) {
		String query = "INSERT INTO AA79B54S01 (ANYO_054, NUM_EXP_054, DNI_SOLICITANTE_054, TIPO_ENTIDAD_054, "
				+ "ID_ENTIDAD_054, IND_VIP_054, DNI_REPRESENTANTE_054) " + "VALUES (?,?,?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
				solicitud.getGestor().getDniGestor(), solicitud.getGestor().getTipoEntidadGestor(),
				solicitud.getGestor().getCodigoEntidadGestor(),
				solicitud.getGestor().getIndVIPGestor() != null ? solicitud.getGestor().getIndVIPGestor()
						: Constants.NO,
				solicitud.getGestor().getDniRepresentante());
	}

	/**
	 *
	 * @param excepcionFacturacion
	 * @param dniGestor
	 * @return
	 */
	public List<ExcepcionFacturacion> findExcepcionesSolicitante(ExcepcionFacturacion excepcionFacturacion,
			String dniGestor) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT t1.ID_036 ID036, t1.TIPO_ENTIDAD_036 TIPOENTIDAD036,"
				+ " t1.ID_ENTIDAD_036 IDENTIDAD036, t1.TIPO_EXCEPCION_036 TIPOEXCEPCION036,"
				+ " t1.TIPO_ENTIDAD_ASOC_036 TIPOENTIDADASOC036, t1.ID_ENTIDAD_ASOC_036 IDENTIDADASOC036,"
				+ " t1.DNI_CONTACTO_036 DNICONTACTO036, t1.POR_FACTURA_036 PORFACTURA036 ");

		params.add(dniGestor);

		query.append(" FROM AA79B36S01 t1  ");
		query.append(" JOIN AA79B37S01 t2 ON t1.ID_036 = t2.ID_EXCEPCION_037 ");
		query.append(" AND t2.DNI_TRABAJADOR_037 = ? ");

		query.append(this.excepcionesWhere(excepcionFacturacion, params));

		return this.getJdbcTemplate().query(query.toString(), this.getrwMapExcepcionFacturacion(), params.toArray());
	}

	/**
	 *
	 * @param excepcionFacturacion
	 * @return
	 */
	private List<ExcepcionFacturacion> findExcepcionesFacturacion(ExcepcionFacturacion excepcionFacturacion) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT t1.ID_036 ID036, t1.TIPO_ENTIDAD_036 TIPOENTIDAD036,"
				+ " t1.ID_ENTIDAD_036 IDENTIDAD036, t1.TIPO_EXCEPCION_036 TIPOEXCEPCION036,"
				+ " t1.TIPO_ENTIDAD_ASOC_036 TIPOENTIDADASOC036, t1.ID_ENTIDAD_ASOC_036 IDENTIDADASOC036,"
				+ " t1.DNI_CONTACTO_036 DNICONTACTO036, t1.POR_FACTURA_036 PORFACTURA036 ");
		query.append(" FROM AA79B36S01 t1  ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_036", excepcionFacturacion.getId036(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_036", excepcionFacturacion.getTipoEntidad036(), params));
		query.append(SqlUtils.generarWhereIgual("ID_ENTIDAD_036", excepcionFacturacion.getIdEntidad036(), params));
		query.append(
				SqlUtils.generarWhereIgual("TIPO_EXCEPCION_036", excepcionFacturacion.getTipoExcepcion036(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_ASOC_036", excepcionFacturacion.getTipoEntidadAsoc036(),
				params));
		query.append(
				SqlUtils.generarWhereIgual("ID_ENTIDAD_ASOC_036", excepcionFacturacion.getIdEntidadAsoc036(), params));
		query.append(SqlUtils.generarWhereIgual("DNI_CONTACTO_036", excepcionFacturacion.getDniContacto036(), params));
		query.append(SqlUtils.generarWhereIgual("POR_FACTURA_036", excepcionFacturacion.getPorFactura036(), params));

		return this.getJdbcTemplate().query(query.toString(), this.getrwMapExcepcionFacturacion(), params.toArray());
	}

	/**
	 *
	 * @param contactoFacturacionExpediente
	 * @return
	 */
	private ContactoFacturacionExpediente addContactoFacturacionExpediente(
			ContactoFacturacionExpediente contactoFacturacionExpediente) {
		final Long elId = this.getNextVal("AA79B58Q00");
		String query = "INSERT INTO AA79B58S01 (ID_058,ANYO_058, NUM_EXP_058, TIPO_ENTIDAD_ASOC_058,"
				+ " ID_ENTIDAD_ASOC_058, DNI_CONTACTO_058, POR_FACTURA_058) VALUES (?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, elId, contactoFacturacionExpediente.getAnyo(),
				contactoFacturacionExpediente.getNumExp(), contactoFacturacionExpediente.getTipoEntidadAsoc058(),
				contactoFacturacionExpediente.getIdEntidadAsoc058(), contactoFacturacionExpediente.getDniContacto058(),
				contactoFacturacionExpediente.getPorFactura058());
		contactoFacturacionExpediente.setId058(elId);
		return contactoFacturacionExpediente;

	}

	/**
	 *
	 */
	@Override
	public void addExpedienteInterpretacion(Aa79bSolicitud solicitud) {
		if (solicitud.getDatosInterpretacion() != null) {
			Aa79bDireccionNora direccionNora = new Aa79bDireccionNora();
			if (solicitud.getDatosInterpretacion().getDirNora() != null) {
				direccionNora = this.addDireccionNora(solicitud.getDatosInterpretacion().getDirNora());
			}
			solicitud.getDatosInterpretacion().setDirNora(direccionNora);
			solicitud.getDatosInterpretacion().setIndObservaciones(
					this.obtenerIndObservaciones(solicitud.getDatosInterpretacion().getObservaciones(), null));
			this.insertarExpedienteInterpretacion(solicitud);
			if (solicitud.getDatosInterpretacion().getIndObservaciones() != null
					&& Constants.SI.equalsIgnoreCase(solicitud.getDatosInterpretacion().getIndObservaciones())) {
				this.addObservaciones(solicitud);
				this.insertObservacionesExpediente(solicitud, DBConstants.TABLA_072, false);
			}
		}
	}

	private void addObservaciones(Aa79bSolicitud solicitud) {
		if (TipoExpedienteEnum.TRADUCCION.getValue().equals(solicitud.getTipoExpediente().getCodigo())
				|| TipoExpedienteEnum.REVISION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {

			if (solicitud.getDatosTradRev().getFicheroObservaciones() != null) {
				this.oidsAuxiliarDao.remove(solicitud.getDatosTradRev().getFicheroObservaciones().getOidFichero());
			} else {
				Aa79bFicheroObservaciones ficheroObservaciones = new Aa79bFicheroObservaciones();
				solicitud.getDatosTradRev().setFicheroObservaciones(ficheroObservaciones);
			}
		}

		this.insertObservacionesExpediente(solicitud, DBConstants.TABLA_055, false);
	}

	public int insertObservacionesExpediente(Aa79bSolicitud solicitud, String tabla, boolean subsanar) {
		if (!subsanar) {
			String insert = "INSERT INTO AA79B" + tabla + "S01  VALUES (?,?,?,?,?,?,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(solicitud.getAnyo());
			params.add(solicitud.getNumExp());
			if (TipoExpedienteEnum.TRADUCCION.getValue().equals(solicitud.getTipoExpediente().getCodigo())
					|| TipoExpedienteEnum.REVISION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
				params.add(solicitud.getDatosTradRev().getObservaciones());
				if (solicitud.getDatosTradRev().getFicheroObservaciones() != null) {
					params.add(solicitud.getDatosTradRev().getFicheroObservaciones().getOidFichero());
					params.add(solicitud.getDatosTradRev().getFicheroObservaciones().getExtension());
					params.add(solicitud.getDatosTradRev().getFicheroObservaciones().getContentType());
					params.add(solicitud.getDatosTradRev().getFicheroObservaciones().getNombre());
					params.add(solicitud.getDatosTradRev().getFicheroObservaciones().getTamano());
				} else {
					params.add(null);
					params.add(null);
					params.add(null);
					params.add(null);
					params.add(null);
				}
			} else if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
				params.add(solicitud.getDatosInterpretacion().getObservaciones());
				params.add(null);
				params.add(null);
				params.add(null);
				params.add(null);
				params.add(null);
			}
			return this.getJdbcTemplate().update(insert.toString(), params.toArray());
		}
		return 0;

	}

	private Aa79bSolicitud insertarExpedienteInterpretacion(Aa79bSolicitud solicitud) {
		Date fechaIni = DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaIni(),
				solicitud.getDatosInterpretacion().getHoraIni());

		Date fechaFin = DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaFin(),
				solicitud.getDatosInterpretacion().getHoraFin());
		if (solicitud.getDatosInterpretacion().getDirNora() == null
				|| solicitud.getDatosInterpretacion().getDirNora().getDireccion() == null) {
			Aa79bDireccionNora direccionNora = new Aa79bDireccionNora();
			direccionNora.setDireccion(new DireccionNora());
			solicitud.getDatosInterpretacion().setDirNora(direccionNora);
		}
		// Comprobar si existe ANYO_052 + NUM_EXP_052
		String query = "INSERT INTO AA79B52S01 (ANYO_052, NUM_EXP_052, MODO_INTERPRETACION_052, TIPO_ACTO_052, "
				+ "TIPO_PETICIONARIO_052, IND_PROGRAMADA_052, FECHA_INI_052, FECHA_FIN_052, DIR_NORA_052, "
				+ "IND_PRESUPUESTO_052, PERSONA_CONTACTO_052, EMAIL_CONTACTO_052, TELEFONO_CONTACTO_052, IND_OBSERVACIONES_052) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
				solicitud.getDatosInterpretacion().getModoInterpretacion().getId(),
				solicitud.getDatosInterpretacion().getTipoActo().getId(),
				solicitud.getDatosInterpretacion().getTipoPeticionario().getCodigo(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndProgramada()), fechaIni, fechaFin,
				solicitud.getDatosInterpretacion().getDirNora().getDireccion().getDirNora(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndPresupuesto()),
				solicitud.getDatosInterpretacion().getPersonaContacto(),
				solicitud.getDatosInterpretacion().getEmailContacto(),
				solicitud.getDatosInterpretacion().getTelefonoContacto(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndObservaciones()));

		return solicitud;

	}

	public String obtenerIndObservaciones(String observaciones, Aa79bFicheroObservaciones ficheroObservaciones) {
		String indObservaciones = Constants.NO;
		if (observaciones != null || ficheroObservaciones != null) {
			indObservaciones = Constants.SI;
		}

		return indObservaciones;
	}

	private Aa79bDireccionNora addDireccionNora(Aa79bDireccionNora dirNora) {
		DireccionNora dirActualizada = this.direccionNoraDao.add(dirNora.getDireccion());
		dirNora.setDireccion(dirActualizada);
		return dirNora;
	}

	/**
	 *
	 */
	@Override
	public void addExpedienteTradRev(Aa79bSolicitud solicitud) {
		if (solicitud.getDatosTradRev() != null) {
			solicitud.getDatosTradRev()
					.setIndObservaciones(this.obtenerIndObservaciones(solicitud.getDatosTradRev().getObservaciones(),
							solicitud.getDatosTradRev().getFicheroObservaciones()));
			this.addExpedienteTradRevDao(solicitud);
			if (solicitud.getDatosTradRev().getIndObservaciones() != null
					&& Constants.SI.equalsIgnoreCase(solicitud.getDatosTradRev().getIndObservaciones())) {
				this.addObservaciones(solicitud);
				this.insertObservacionesExpediente(solicitud, DBConstants.TABLA_072, false);
			}
		}
	}

	private int addExpedienteTradRevDao(Aa79bSolicitud solicitud) {
		Long fecha = solicitud.getDatosTradRev().getFechaFinalIzo();
		String hora = solicitud.getDatosTradRev().getHoraFinalIzo();
		String query = "INSERT INTO AA79B53S01 (ANYO_053, NUM_EXP_053, IND_PUBLICAR_BOPV_053, IND_PREVISTO_BOPV_053, "
				+ "ID_IDIOMA_053, IND_CONFIDENCIAL_053, IND_CORREDACCION_053, TEXTO_053, TIPO_REDACCION_053, "
				+ "PARTICIPANTES_053, REF_TRAMITAGUNE_053, NUM_TOTAL_PAL_IZO_053, FECHA_FINAL_IZO_053, "
				+ "IND_FACTURABLE_053, ID_RELEVANCIA_053, IND_URGENTE_053,FECHA_FINAL_SOLIC_053,FECHA_FINAL_PROP_053,"
				+ "IND_PRESUPUESTO_053, NUM_TOTAL_PAL_SOLIC_053, IND_OBSERVACIONES_053) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndPublicarBopv()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndPrevistoBopv()),
				solicitud.getDatosTradRev().getIdioma().getId(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndConfidencial()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndCorredaccion()),
				solicitud.getDatosTradRev().getTexto(), solicitud.getDatosTradRev().getTipoRedaccion(),
				solicitud.getDatosTradRev().getParticipantes(), solicitud.getDatosTradRev().getRefTramitagune(),
				solicitud.getDatosTradRev().getNumTotalPalIzo(), DateUtils.obtFechaHoraFormateada(fecha, hora),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndFacturable()),
				solicitud.getDatosTradRev().getIdRelevancia(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndUrgente()),
				DateUtils.obtFechaHoraFormateada(solicitud.getDatosTradRev().getFechaFinalSolic(),
						solicitud.getDatosTradRev().getHoraFinalSolic()),
				DateUtils.obtFechaHoraFormateada(solicitud.getDatosTradRev().getFechaFinalProp(), null),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndPresupuesto()),
				solicitud.getDatosTradRev().getNumTotalPalSolic(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndObservaciones()));

	}

	@Override
	public void addDocumentosExpediente(Aa79bSolicitud solicitud) {
		if (this.contieneDocumentos(solicitud)) {
			boolean tieneDocTradRev = false;
			for (Aa79bDocumentoExpediente beanDocExpediente : solicitud.getDocumentosExpediente()) {
				if (this.isClasificacionTradRev(beanDocExpediente)) {
					tieneDocTradRev = true;
				}
				beanDocExpediente.setAnyo(solicitud.getAnyo());
				beanDocExpediente.setNumExp(solicitud.getNumExp());
				this.addDocumentosExp(beanDocExpediente, solicitud);
			}
			// Actualiz datos T53
			if (tieneDocTradRev) {
				this.expedienteTradRevService.procRecalcularCamposDocumentacionPL(solicitud.getAnyo(),
						solicitud.getNumExp());
			}
		}

	}

	private void updateDatosExpedienteTradRev(Aa79bDocumentoExpediente beanDocExpediente) {
		if (this.isClasificacionTradRev(beanDocExpediente)) {
			this.expedienteTradRevService.procRecalcularCamposDocumentacionPL(beanDocExpediente.getAnyo(),
					beanDocExpediente.getNumExp());
			beanDocExpediente.setLlamadaPL(true);
		} else {
			beanDocExpediente.setLlamadaPL(false);
		}

	}

	private boolean isClasificacionTradRev(Aa79bDocumentoExpediente beanDocExpediente) {
		return ClasificacionDocumentosEnum.TRADUCCION.getValue() == beanDocExpediente.getClaseDocumento()
				|| (ClasificacionDocumentosEnum.REVISION.getValue() == beanDocExpediente.getClaseDocumento());
	}

	private BigDecimal addDocumentosExpediente(Aa79bSolicitante solicitante, Aa79bDocumentoExpediente beanDocExpediente,
			int x) {
		final Long elIdLong = this.getNextVal("AA79B56Q00");
		final BigDecimal elId = new BigDecimal(elIdLong.longValue());

		if (StringUtils.isBlank(beanDocExpediente.getTitulo())) {
			String elTitulo = beanDocExpediente.getFicheros().get(x).getNombre();
			elTitulo = elTitulo.substring(Constants.CERO, elTitulo.lastIndexOf('.'));
			beanDocExpediente.setTitulo(elTitulo);
		}

		List<Object> params = new ArrayList<Object>();
		params.add(elId);
		params.add(beanDocExpediente.getAnyo());
		params.add(beanDocExpediente.getNumExp());
		params.add(beanDocExpediente.getFicheros().get(x).getOid());
		params.add(beanDocExpediente.getFicheros().get(x).getExtension());
		params.add(beanDocExpediente.getFicheros().get(x).getTamano());
		params.add(Utils.getValueInd(beanDocExpediente.getFicheros().get(x).getEncriptado()));
		if (x == 0) {
			params.add(null);
		} else {
			params.add(beanDocExpediente.getFicheros().get(0).getIdDocInsertado());
			beanDocExpediente.getFicheros().get(1)
					.setIdDocRel(beanDocExpediente.getFicheros().get(0).getIdDocInsertado());
		}
		params = this.masParams(params, beanDocExpediente, x);

		params.add(new Date());
		// dni usuario conectado
		params.add(solicitante.getDni());
		if (beanDocExpediente.getFicheros().get(x).getContacto() != null) {
			params.add(beanDocExpediente.getFicheros().get(x).getContacto().getDireccion());
			params.add(beanDocExpediente.getFicheros().get(x).getContacto().getEntidad());
		} else {
			params.add(null);
			params.add(null);
		}
		params.add(beanDocExpediente.getFicheros().get(x).getContentType());
		params.add(beanDocExpediente.getFicheros().get(x).getNombre());
		params.add(beanDocExpediente.getFicheros().get(x).getRutaPif());

		String query = "INSERT INTO AA79B56S01 (ID_DOC_056, ANYO_056, NUM_EXP_056, "
				+ "OID_FICHERO_056, EXTENSION_DOC_056, TAMANO_DOC_056, IND_ENCRIPTADO_056,"
				+ " ID_DOC_REL_056, CLASE_DOCUMENTO_056, TIPO_DOCUMENTO_056, TITULO_056,"
				+ " NUM_PAL_SOLIC_056, NUM_PAL_IZO_056, PERSONA_CONTACTO_056, EMAIL_CONTACTO_056, "
				+ "TELEFONO_CONTACTO_056, FECHA_ALTA_056, DNI_ALTA_056, "
				+ "DIRECCION_CONTACTO_056,ENTIDAD_CONTACTO_056, CONTENT_TYPE_056, "
				+ "NOMBRE_FICHERO_056, RUTA_PIF_FICHERO_056) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, params.toArray());

		beanDocExpediente.getFicheros().get(x).setIdDocInsertado(elId);
		beanDocExpediente.setIdDoc(elId);

		return elId;
	}

	private List<Object> masParams(List<Object> params, Aa79bDocumentoExpediente beanDocExpediente, int x) {
		params.add(beanDocExpediente.getClaseDocumento());
		params.add(beanDocExpediente.getTipoDocumento());
		params.add(beanDocExpediente.getTitulo());
		params.add(beanDocExpediente.getNumPalSolic());
		params.add(beanDocExpediente.getNumPalIzo());
		if (beanDocExpediente.getFicheros().get(x).getContacto() != null) {
			params.add(beanDocExpediente.getFicheros().get(x).getContacto().getPersona());
			params.add(beanDocExpediente.getFicheros().get(x).getContacto().getEmail());
			params.add(beanDocExpediente.getFicheros().get(x).getContacto().getTelefono());
		} else {
			params.add(null);
			params.add(null);
			params.add(null);
		}
		return params;
	}

	@Override
	public void registrarAcciones(Aa79bSolicitud bean, Aa79bSolicitud original, Long accionAlta,
			Map<String, String> parametros, String mensaje) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(Constants.PUNTO_MENU_TRAMITACION_EXPEDIENTES);
		reg.setIdAccion(accionAlta);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		String aux = Utils.observacionesRegistro(bean, original, parametros, this.msg, locale);
		if (StringUtils.isNotBlank(aux)) {
			StringBuilder observ = new StringBuilder();
			observ.append(this.msg.getMessage(mensaje, null, locale)).append(" \n");
			observ.append(aux);
			observ.append(this.obtenerMensajeObservaciones(bean, original, locale));
			reg.setAnyo(bean.getAnyo());
			reg.setNumExp(bean.getNumExp());
			reg.setIdEstadoBitacora(bean.getIdEstadoBitacora());
			reg.setObserv(observ.toString());
			this.addRegistroAcciones(bean.getSolicitante(), reg);
		}

	}

	private RegistroAcciones addRegistroAcciones(Aa79bSolicitante solicitante, RegistroAcciones registroAcciones) {
		registroAcciones.setIp(null);
		// usuario conectado
		registroAcciones.setUsuarioRegistro(solicitante.getDni());
		String query = "INSERT INTO AA79B43S01 (ID_REGISTRO_ACCION_043, ID_PUNTO_MENU_043, ID_ACCION_043, IP_043, FECHA_REGISTRO_043, USUARIO_REGISTRO_043, ANYO_EXP_043, NUM_EXP_043,  OBSERV_043, ID_ESTADO_BITACORA_043) VALUES (AA79B43Q00.NEXTVAL,?,?,?,SYSDATE,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, registroAcciones.getIdPuntoMenu(), registroAcciones.getIdAccion(),
				registroAcciones.getIp(), registroAcciones.getUsuarioRegistro(), registroAcciones.getAnyo(),
				registroAcciones.getNumExp(), registroAcciones.getObserv(), registroAcciones.getIdEstadoBitacora());
		return registroAcciones;

	}

	private Object obtenerMensajeObservaciones(Aa79bSolicitud bean, Aa79bSolicitud original, Locale locale) {
		StringBuilder strObservaciones = new StringBuilder();
		if (original != null) {
			String strActual = this.obtenerObservaciones(bean);
			String strOriginal = this.obtenerObservaciones(original);
			if (!StringUtils.equals(strOriginal, strActual)) {
				strObservaciones.append(this.msg.getMessage(Constants.LABEL_OBSERVACIONES_MODIFICADAS, null, locale));
			}
		}

		return strObservaciones.toString();
	}

	private String obtenerObservaciones(Aa79bSolicitud bean) {
		String observaciones = "";
		if (bean != null) {
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(bean.getTipoExpediente().getId())
					&& bean.getDatosInterpretacion() != null
					&& bean.getDatosInterpretacion().getObservaciones() != null) {
				observaciones = bean.getDatosInterpretacion().getObservaciones();
			} else if (bean.getDatosTradRev() != null && bean.getDatosTradRev().getObservaciones() != null) {
				observaciones = bean.getDatosTradRev().getObservaciones();
			}
		}
		return observaciones;
	}

	@Override
	public int addExpedienteTradRev71(Aa79bSolicitud solicitud) {
		Long fecha = solicitud.getDatosTradRev().getFechaFinalSolic();
		String hora = solicitud.getDatosTradRev().getHoraFinalSolic();

		String query = "INSERT INTO AA79B71S01 (ANYO_071, NUM_EXP_071, IND_PUBLICAR_BOPV_071,"
				+ "ID_IDIOMA_071, IND_CONFIDENCIAL_071, IND_CORREDACCION_071, TEXTO_071, TIPO_REDACCION_071, "
				+ "PARTICIPANTES_071, REF_TRAMITAGUNE_071, NUM_TOTAL_PAL_SOLIC_071, FECHA_FINAL_SOLIC_071, "
				+ "IND_FACTURABLE_071, ID_RELEVANCIA_071, IND_URGENTE_071,IND_PRESUPUESTO_071,IND_OBSERVACIONES_071) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndPublicarBopv()),
				solicitud.getDatosTradRev().getIdioma().getId(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndConfidencial()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndCorredaccion()),
				solicitud.getDatosTradRev().getTexto(), solicitud.getDatosTradRev().getTipoRedaccion(),
				solicitud.getDatosTradRev().getParticipantes(), solicitud.getDatosTradRev().getRefTramitagune(),
				solicitud.getDatosTradRev().getNumTotalPalSolic(), DateUtils.obtFechaHoraFormateada(fecha, hora),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndFacturable()),
				solicitud.getDatosTradRev().getIdRelevancia(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndUrgente()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndPresupuesto()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndObservaciones()));

	}

	@Override
	public int addExpedienteInterpretacion70(Aa79bSolicitud solicitud) {
		Date fechaIni = DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaIni(),
				solicitud.getDatosInterpretacion().getHoraIni());

		Date fechaFin = DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaFin(),
				solicitud.getDatosInterpretacion().getHoraFin());
		if (solicitud.getDatosInterpretacion().getDirNora() == null
				|| solicitud.getDatosInterpretacion().getDirNora().getDireccion() == null) {
			Aa79bDireccionNora direccionNora = new Aa79bDireccionNora();
			direccionNora.setDireccion(new DireccionNora());
			solicitud.getDatosInterpretacion().setDirNora(direccionNora);
		}
		// Comprobar si existe ANYO_052 + NUM_EXP_052
		String query = "INSERT INTO AA79B70S01 (ANYO_070, NUM_EXP_070, MODO_INTERPRETACION_070, TIPO_ACTO_070, "
				+ "TIPO_PETICIONARIO_070, IND_PROGRAMADA_070, FECHA_INI_070, FECHA_FIN_070, DIR_NORA_070, "
				+ "IND_PRESUPUESTO_070, PERSONA_CONTACTO_070, EMAIL_CONTACTO_070, TELEFONO_CONTACTO_070, IND_OBSERVACIONES_070) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp(),
				solicitud.getDatosInterpretacion().getModoInterpretacion().getId(),
				solicitud.getDatosInterpretacion().getTipoActo().getId(),
				solicitud.getDatosInterpretacion().getTipoPeticionario().getCodigo(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndProgramada()), fechaIni, fechaFin,
				solicitud.getDatosInterpretacion().getDirNora().getDireccion().getDirNora(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndPresupuesto()),
				solicitud.getDatosInterpretacion().getPersonaContacto(),
				solicitud.getDatosInterpretacion().getEmailContacto(),
				solicitud.getDatosInterpretacion().getTelefonoContacto(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndObservaciones()));
	}

	@Override
	public int relacionarExpedientes(Aa79bSolicitud solicitud, String tabla) {
		Integer guardado = 0;
		if (solicitud.getExpedientesRelacionados() != null) {
			for (Aa79bExpedienteRelacionado expedienteARelacionar : solicitud.getExpedientesRelacionados()) {
				guardado += this.guardarRelacionExpediente(solicitud, expedienteARelacionar, tabla);
			}
		}

		return guardado;
	}

	private Integer guardarRelacionExpediente(Aa79bSolicitud solicitud,
			Aa79bExpedienteRelacionado expedienteARelacionar, String tabla) {
		StringBuilder insert = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		insert.append("INSERT INTO AA79B" + tabla + "S01 ");
		insert.append("VALUES (?, ?, ?, ?)");
		params.add(solicitud.getAnyo());
		params.add(solicitud.getNumExp());
		params.add(expedienteARelacionar.getAnyo());
		params.add(expedienteARelacionar.getNumExp());

		return this.getJdbcTemplate().update(insert.toString(), params.toArray());
	}

	@Override
	public int addBitacoraSolicitud(Aa79bSolicitud solicitud, int accionBitacora) {
		final Long elId = this.getNextVal("AA79B79Q00");
		if (solicitud.getFechaAlta() == null) {
			solicitud.setFechaAlta(new Date().getTime());
		}
		String query = "INSERT INTO AA79B79S01 (ID_079, ANYO_079, NUM_EXP_079,"
				+ "ID_ACCION_BITACORA_079, FECHA_ALTA_079, USUARIO_079) " + "VALUES (?,?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, elId, solicitud.getAnyo(), solicitud.getNumExp(), accionBitacora,
				DateUtils.obtFechaHoraFormateada(solicitud.getFechaAlta(), null),
				solicitud.getSolicitante().getNombre());
	}

	@Override
	public int addBitacoraSolicitante(BitacoraSolicitante bitacoraSolicitante) {
		final Long elId = this.getNextVal("AA79B79Q00");
		String query = "INSERT INTO AA79B79S01 (ID_079, ANYO_079, NUM_EXP_079,"
				+ "ID_ACCION_BITACORA_079, FECHA_ALTA_079, USUARIO_079) " + "VALUES (?,?,?,?,SYSDATE,?)";
		return this.getJdbcTemplate().update(query, elId, bitacoraSolicitante.getAnyo(),
				bitacoraSolicitante.getNumExp(), bitacoraSolicitante.getIdAccionBitacora(),
				bitacoraSolicitante.getUsuario());
	}

	@Override
	public int addDocumentosExpediente73(Aa79bSolicitud solicitud) {
		int documentosGuardados = 0;
		if (this.contieneDocumentos(solicitud)) {
			for (Aa79bDocumentoExpediente documentoExpediente : solicitud.getDocumentosExpediente()) {
				documentosGuardados += this.addDocumentoExpediente73(documentoExpediente);
			}
		}
		return documentosGuardados;
	}

	private boolean contieneDocumentos(Aa79bSolicitud solicitud) {
		return (solicitud.getDocumentosExpediente() != null && !solicitud.getDocumentosExpediente().isEmpty());
	}

	public int addDocumentoExpediente73(Aa79bDocumentoExpediente documento) {
		int documentosGuardados = 0;
		for (Aa79bFicheroDocExp fichero : documento.getFicheros()) {
			if (fichero.getContacto() == null) {
				Aa79bContactoDoc contactoAux = new Aa79bContactoDoc();
				fichero.setContacto(contactoAux);
			}
			if (fichero.getFechaAlta() == null) {
				fichero.setFechaAlta(new Date().getTime());
			}
			String query = "INSERT INTO AA79B73S01 (ID_DOC_073, ANYO_073, NUM_EXP_073,"
					+ "OID_FICHERO_073, EXTENSION_DOC_073, TAMANO_DOC_073, IND_ENCRIPTADO_073,"
					+ "CONTENT_TYPE_073, ID_DOC_REL_073, CLASE_DOCUMENTO_073, TIPO_DOCUMENTO_073, "
					+ "TITULO_073, NUM_PAL_SOLIC_073, PERSONA_CONTACTO_073, EMAIL_CONTACTO_073, "
					+ "TELEFONO_CONTACTO_073, DIRECCION_CONTACTO_073, ENTIDAD_CONTACTO_073, "
					+ "FECHA_ALTA_073, NOMBRE_FICHERO_073) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			documentosGuardados += this.getJdbcTemplate().update(query, fichero.getIdDocInsertado(),
					documento.getAnyo(), documento.getNumExp(), fichero.getOid(), fichero.getExtension(),
					fichero.getTamano(), Utils.getValueInd(fichero.getEncriptado()), fichero.getContentType(),
					fichero.getIdDocRel(), documento.getClaseDocumento(), documento.getTipoDocumento(),
					documento.getTitulo(), documento.getNumPalSolic(), fichero.getContacto().getPersona(),
					fichero.getContacto().getEmail(), fichero.getContacto().getTelefono(),
					fichero.getContacto().getDireccion(), fichero.getContacto().getEntidad(),
					DateUtils.obtFechaHoraFormateada(fichero.getFechaAlta(), null), fichero.getNombre());
		}
		return documentosGuardados;
	}

	@Override
	public void addRegistroAcciones(Aa79bSolicitud solicitud, Aa79bSolicitud original, Long accionAlta,
			Map<String, String> parametros, String mensaje) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.SOLICITANTES_ALTA.getValue());
		reg.setIdAccion(accionAlta);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		String aux = Utils.observacionesRegistro(solicitud, original, parametros, this.msg, locale);
		if (StringUtils.isNotBlank(aux)) {
			StringBuilder observ = new StringBuilder();
			observ.append(this.msg.getMessage(mensaje, null, locale)).append(" \n");
			observ.append(aux);
			observ.append(this.obtenerMensajeObservaciones(solicitud, original, locale));
			reg.setAnyo(solicitud.getAnyo());
			reg.setNumExp(solicitud.getNumExp());
			reg.setIdEstadoBitacora(solicitud.getIdEstadoBitacora());
			reg.setObserv(observ.toString());
			reg.setUsuarioRegistro(solicitud.getSolicitante().getDni());
			this.addRegistroAccionesDao(reg);
		}
	}

	public RegistroAcciones addRegistroAccionesDao(RegistroAcciones registroAcciones) {
		String query = "INSERT INTO AA79B43S01 (ID_REGISTRO_ACCION_043, ID_PUNTO_MENU_043, ID_ACCION_043, IP_043, FECHA_REGISTRO_043, USUARIO_REGISTRO_043, ANYO_EXP_043, NUM_EXP_043,  OBSERV_043, ID_ESTADO_BITACORA_043) VALUES (AA79B43Q00.NEXTVAL,?,?,?,SYSDATE,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, registroAcciones.getIdPuntoMenu(), registroAcciones.getIdAccion(),
				registroAcciones.getIp(), registroAcciones.getUsuarioRegistro(), registroAcciones.getAnyo(),
				registroAcciones.getNumExp(), registroAcciones.getObserv(), registroAcciones.getIdEstadoBitacora());
		return registroAcciones;
	}

	@Override
	public int updateSolicitud69(Aa79bSolicitud solicitud, boolean subsanar) {
		if (!subsanar) {
			String query = "UPDATE AA79B69S01 SET " + " TITULO_069=? WHERE ANYO_069=? AND NUM_EXP_069=?";

			return this.getJdbcTemplate().update(query, solicitud.getTitulo(), solicitud.getAnyo(),
					solicitud.getNumExp());
		}
		return 0;
	}

	@Override
	public int updateExpedienteTradRev(Aa79bSolicitud solicitud, boolean subsanar) {

		solicitud.getDatosTradRev().setIndObservaciones(this.obtenerIndObservaciones(
				solicitud.getDatosTradRev().getObservaciones(), solicitud.getDatosTradRev().getFicheroObservaciones()));
		this.updateObservaciones(solicitud.getDatosTradRev().getFicheroObservaciones(),
				solicitud.getDatosTradRev().getObservaciones(), solicitud, subsanar);
		Long fechaFinalSolic = solicitud.getDatosTradRev().getFechaFinalSolic();
		String horaFinalSolic = solicitud.getDatosTradRev().getHoraFinalSolic();
		String query = "UPDATE AA79B53S01 SET  IND_PUBLICAR_BOPV_053=?,"
				+ "ID_IDIOMA_053=?, IND_CONFIDENCIAL_053=?, IND_CORREDACCION_053=?, TEXTO_053=?, "
				+ "TIPO_REDACCION_053=?, PARTICIPANTES_053=?, REF_TRAMITAGUNE_053=?,"
				+ "NUM_TOTAL_PAL_SOLIC_053=?,FECHA_FINAL_SOLIC_053=?,"
				+ "IND_PRESUPUESTO_053=?, IND_OBSERVACIONES_053=? WHERE ANYO_053=? AND NUM_EXP_053=?";

		return this.getJdbcTemplate().update(query, Utils.getValueInd(solicitud.getDatosTradRev().getIndPublicarBopv()),
				solicitud.getDatosTradRev().getIdioma().getId(),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndConfidencial()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndCorredaccion()),
				solicitud.getDatosTradRev().getTexto(), solicitud.getDatosTradRev().getTipoRedaccion(),
				solicitud.getDatosTradRev().getParticipantes(), solicitud.getDatosTradRev().getRefTramitagune(),
				solicitud.getDatosTradRev().getNumTotalPalSolic(),
				DateUtils.obtFechaHoraFormateada(fechaFinalSolic, horaFinalSolic),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndPresupuesto()),
				Utils.getValueInd(solicitud.getDatosTradRev().getIndObservaciones()), solicitud.getAnyo(),
				solicitud.getNumExp());

	}

	@Override
	public int updateExpedienteTradRevNumTotalPalSolic(Aa79bSolicitud solicitud) {

		String query = "UPDATE AA79B53S01 SET NUM_TOTAL_PAL_SOLIC_053=? WHERE ANYO_053=? AND NUM_EXP_053=?";

		return this.getJdbcTemplate().update(query, solicitud.getDatosTradRev().getNumTotalPalSolic(),
				solicitud.getAnyo(), solicitud.getNumExp());

	}

	@Override
	public int updateExpedienteTradRev71(Aa79bSolicitud solicitud, boolean subsanar) {
		if (!subsanar) {
			String query = "UPDATE AA79B71S01 SET  IND_PUBLICAR_BOPV_071=?,"
					+ "ID_IDIOMA_071=?, IND_CONFIDENCIAL_071=?, IND_CORREDACCION_071=?, TEXTO_071=?, TIPO_REDACCION_071=?, "
					+ "PARTICIPANTES_071=?, REF_TRAMITAGUNE_071=?, NUM_TOTAL_PAL_SOLIC_071=?, FECHA_FINAL_SOLIC_071=?, "
					+ "IND_FACTURABLE_071=?, ID_RELEVANCIA_071=?, IND_URGENTE_071=?, IND_PRESUPUESTO_071=?, IND_OBSERVACIONES_071=? "
					+ "WHERE ANYO_071=? AND NUM_EXP_071=?";
			return this.getJdbcTemplate().update(query,
					Utils.getValueInd(solicitud.getDatosTradRev().getIndPublicarBopv()),
					solicitud.getDatosTradRev().getIdioma().getId(),
					Utils.getValueInd(solicitud.getDatosTradRev().getIndConfidencial()),
					Utils.getValueInd(solicitud.getDatosTradRev().getIndCorredaccion()),
					solicitud.getDatosTradRev().getTexto(), solicitud.getDatosTradRev().getTipoRedaccion(),
					solicitud.getDatosTradRev().getParticipantes(), solicitud.getDatosTradRev().getRefTramitagune(),
					solicitud.getDatosTradRev().getNumTotalPalSolic(),
					DateUtils.obtFechaHoraFormateada(solicitud.getDatosTradRev().getFechaFinalSolic(),
							solicitud.getDatosTradRev().getHoraFinalSolic()),
					Utils.getValueInd(solicitud.getDatosTradRev().getIndFacturable()),
					solicitud.getDatosTradRev().getIdRelevancia(),
					Utils.getValueInd(solicitud.getDatosTradRev().getIndUrgente()),
					Utils.getValueInd(solicitud.getDatosTradRev().getIndPresupuesto()),
					Utils.getValueInd(solicitud.getDatosTradRev().getIndObservaciones()), solicitud.getAnyo(),
					solicitud.getNumExp());
		}
		return 0;
	}

	@Override
	public int updateExpedienteInterpretacion(Aa79bSolicitud solicitud, boolean subsanar) {

		// Actualizar dirNora
		Aa79bDireccionNora direccionNora = new Aa79bDireccionNora();
		if (solicitud.getDatosInterpretacion().getDirNora() != null) {
			direccionNora = this.updateDireccionNora(solicitud.getDatosInterpretacion().getDirNora());

		}
		solicitud.getDatosInterpretacion().setDirNora(direccionNora);
		solicitud.getDatosInterpretacion().setIndObservaciones(
				this.obtenerIndObservaciones(solicitud.getDatosInterpretacion().getObservaciones(), null));

		this.updateObservaciones(null, solicitud.getDatosInterpretacion().getObservaciones(), solicitud, subsanar);

		String query = "UPDATE AA79B52S01 SET  MODO_INTERPRETACION_052=?, TIPO_ACTO_052=?, TIPO_PETICIONARIO_052=?, "
				+ "IND_PROGRAMADA_052=?, FECHA_INI_052=?, FECHA_FIN_052=?, DIR_NORA_052=?, IND_PRESUPUESTO_052=?, "
				+ "PERSONA_CONTACTO_052=?, EMAIL_CONTACTO_052=?, TELEFONO_CONTACTO_052=?, IND_OBSERVACIONES_052=?  "
				+ "WHERE ANYO_052=? AND NUM_EXP_052=?";
		return this.getJdbcTemplate().update(query, solicitud.getDatosInterpretacion().getModoInterpretacion().getId(),
				solicitud.getDatosInterpretacion().getTipoActo().getId(),
				solicitud.getDatosInterpretacion().getTipoPeticionario().getCodigo(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndProgramada()),
				DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaIni(), null),
				DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaFin(), null),
				solicitud.getDatosInterpretacion().getDirNora().getDireccion().getDirNora(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndPresupuesto()),
				solicitud.getDatosInterpretacion().getPersonaContacto(),
				solicitud.getDatosInterpretacion().getEmailContacto(),
				solicitud.getDatosInterpretacion().getTelefonoContacto(),
				Utils.getValueInd(solicitud.getDatosInterpretacion().getIndObservaciones()), solicitud.getAnyo(),
				solicitud.getNumExp());

	}

	private Aa79bDireccionNora updateDireccionNora(Aa79bDireccionNora dirNora) {
		DireccionNora dirActualizada = this.direccionNoraDao.update(dirNora.getDireccion());
		dirNora.setDireccion(dirActualizada);
		return dirNora;
	}

	@Override
	public int updateExpedienteInterpretacion70(Aa79bSolicitud solicitud, boolean subsanar) {
		if (!subsanar) {
			String query = "UPDATE AA79B70S01 SET  MODO_INTERPRETACION_070=?, TIPO_ACTO_070=?, TIPO_PETICIONARIO_070=?, "
					+ "IND_PROGRAMADA_070=?, FECHA_INI_070=?, FECHA_FIN_070=?, DIR_NORA_070=?, IND_PRESUPUESTO_070=?, "
					+ "PERSONA_CONTACTO_070=?, EMAIL_CONTACTO_070=?, TELEFONO_CONTACTO_070=?, IND_OBSERVACIONES_070=?  "
					+ "WHERE ANYO_070=? AND NUM_EXP_070=?";
			return this.getJdbcTemplate().update(query,
					solicitud.getDatosInterpretacion().getModoInterpretacion().getId(),
					solicitud.getDatosInterpretacion().getTipoActo().getId(),
					solicitud.getDatosInterpretacion().getTipoPeticionario().getCodigo(),
					Utils.getValueInd(solicitud.getDatosInterpretacion().getIndProgramada()),
					DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaIni(), null),
					DateUtils.obtFechaHoraFormateada(solicitud.getDatosInterpretacion().getFechaFin(), null),
					solicitud.getDatosInterpretacion().getDirNora().getDireccion().getDirNora(),
					Utils.getValueInd(solicitud.getDatosInterpretacion().getIndPresupuesto()),
					solicitud.getDatosInterpretacion().getPersonaContacto(),
					solicitud.getDatosInterpretacion().getEmailContacto(),
					solicitud.getDatosInterpretacion().getTelefonoContacto(),
					Utils.getValueInd(solicitud.getDatosInterpretacion().getIndObservaciones()), solicitud.getAnyo(),
					solicitud.getNumExp());
		}
		return 0;
	}

	@Override
	public int updateExpedientesRelacionados(Aa79bSolicitud solicitud, String tabla, boolean subsanar) {
		if (!subsanar) {
			// eliminar los expedientes relacionados
			String query = "DELETE FROM AA79B" + tabla + "S01 WHERE ANYO_0" + tabla + AND_NUM_EXP_0 + tabla + "=?";
			this.getJdbcTemplate().update(query, solicitud.getAnyo(), solicitud.getNumExp());

			// insertar los expedientes relacionados
			return this.relacionarExpedientes(solicitud, tabla);
		}
		return 0;

	}

	@Override
	public List<BigDecimal> updateDocumentosExpediente(Aa79bSolicitud solicitud, boolean subsanar) {
		List<BigDecimal> listaIdDocsNuevos = new ArrayList<BigDecimal>();
		try {
			for (Aa79bDocumentoExpediente documento : solicitud.getDocumentosExpediente()) {
				String estadoDocumento = documento.getEstado();

				if ("N".equalsIgnoreCase(estadoDocumento)) {
					// nuevo documento
					this.nuevoDocumento(solicitud, documento, subsanar);
					// añado el idDoc a la lista para insertar en T83 en caso de
					// subsanación
					listaIdDocsNuevos.add(documento.getFicheros().get(0).getIdDocInsertado());
				} else if ("M".equalsIgnoreCase(estadoDocumento)) {
					// modificar documento - VERSIONES ELIMINADAS!
					this.modificarDocumento(solicitud, documento, subsanar);
				} else if ("E".equalsIgnoreCase(estadoDocumento)) {
					// eliminar documento
					this.eliminarDocumento(solicitud, documento, subsanar);
				}

			}
		} catch (Exception e) {
			LOGGER.info("Error modificando documentos ", e);
		}
		return listaIdDocsNuevos;
	}

	private void eliminarDocumento(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, boolean subsanar) {

		List<String> listaOids = this.getOidsABorrar(documento.getIdDoc());

		try {
			for (String unOid : listaOids) {
				this.oidsAuxiliarDao.add(unOid);
			}
			this.removeDocYRelacionado(documento);

			if (!subsanar && !Utils.comprobarAccion(solicitud.getAccionSolicitud(),
					Aa06aAccionSolicitudEnum.DOCUMENTOS_CON_VERSION.getLabel())) {
				// eliminar de 73
				this.removeDoc73(documento);

			}
			// Actualiz datos T53
			this.updateDatosExpedienteTradRev(documento);
		} catch (Exception e) {
			LOGGER.info(NO_SE_PUEDE_ELIMINAR + e);
		}
	}

	private void nuevoDocumento(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, boolean subsanar) {
		documento.setAnyo(solicitud.getAnyo());
		documento.setNumExp(solicitud.getNumExp());
		for (int x = 0; x < documento.getFicheros().size()
				&& StringUtils.isNotBlank(documento.getFicheros().get(x).getContentType()); x++) {

			documento.getFicheros().get(x)
					.setIdDocInsertado(this.addDocumentosExpediente(solicitud.getSolicitante(), documento, x));
			this.oidsAuxiliarDao.remove(documento.getFicheros().get(x).getOid());

		}
		// Actualiz datos T53
		this.updateDatosExpedienteTradRev(documento);
		if (!subsanar && !Utils.comprobarAccion(solicitud.getAccionSolicitud(),
				Aa06aAccionSolicitudEnum.DOCUMENTOS_CON_VERSION.getLabel())) {
			// anyadir a 73
			this.addDocumentoExpediente73(documento);
		}
		if (Utils.comprobarAccion(solicitud.getAccionSolicitud(),
				Aa06aAccionSolicitudEnum.DOCUMENTOS_CON_VERSION.getLabel())
				&& (documento.getClaseDocumento() == 1 || documento.getClaseDocumento() == 2)) {
			// anyadir a 83 si existe tarea 3,19 o 20
			this.expedienteTradRevService.procAsociarDocAEntregaPL(solicitud.getAnyo(), solicitud.getNumExp(),
					documento.getFicheros().get(0).getIdDocInsertado());
		}

	}

	public void modificarDocumento(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, boolean subsanar) {

		String oidOriginal = "";
		for (int x = 0; x < documento.getFicheros().size()
				&& StringUtils.isNotBlank(documento.getFicheros().get(x).getContentType()); x++) {

			oidOriginal = this.getOidBBDD(documento.getFicheros().get(x).getIdDocInsertado());

			// Si cambia el OID hay que eliminar el viejo del pid y guardar en
			// bbdd tb los datos del fichero
			if (!documento.getFicheros().get(x).getOid().equals(oidOriginal) && StringUtils.isNotEmpty(oidOriginal)) {
				this.updateFichero56ConFichero(solicitud, documento, x);
				this.oidsAuxiliarDao.add(oidOriginal);
				this.oidsAuxiliarDao.remove(documento.getFicheros().get(x).getOid());
				// meter solic gestor de la solicitud
			} else {
				// modificar solo datos doc, sin fichero ni dni_alta
				this.updateFichero56SinFichero(solicitud, documento, x);
			}

			// modificar fichero en 73
			if (!subsanar && !Utils.comprobarAccion(solicitud.getAccionSolicitud(),
					Aa06aAccionSolicitudEnum.DOCUMENTOS_CON_VERSION.getLabel())) {
				this.updateDocumentosExpediente73(solicitud, documento, x);
			}

			// Si es doc de revisión y el primer fichero es un zip ... hay q
			// eliminar el segundo
			if (ClasificacionDocumentosEnum.REVISION.getValue() == documento.getClaseDocumento()
					&& Constants.ZIP.equals(documento.getFicheros().get(0).getExtension())) {
				List<String> oidRel = this.getOidFichRelacionado(documento.getFicheros().get(0).getIdDocInsertado());
				if (!oidRel.isEmpty()) {
					this.oidsAuxiliarDao.add(oidRel.get(0));
					this.removeDocRelacionado(documento);
				}

			}

		}
	}

	public int removeDoc73(Aa79bDocumentoExpediente documento) {
		String query = "DELETE FROM AA79B73S01 WHERE ID_DOC_073=? OR ID_DOC_REL_073=? ";
		return this.getJdbcTemplate().update(query, documento.getIdDoc(), documento.getIdDoc());
	}

	@Override()
	public void removeDocYRelacionado(Aa79bDocumentoExpediente documento) {
		List<Object> params = new ArrayList<Object>();
		String query = "DELETE FROM AA79B56S01 WHERE (ID_DOC_056=?) OR (ID_DOC_REL_056=?)";
		params.add(documento.getIdDoc());
		params.add(documento.getIdDoc());
		this.getJdbcTemplate().update(query, params.toArray());

	}

	@Override()
	public String getOidBBDD(BigDecimal elId) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT OID_FICHERO_056 FROM AA79B56S01 WHERE ID_DOC_056=? ");
		params.add(elId);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), String.class);
	}

	@Override()
	public List<String> getOidsABorrar(BigDecimal elId) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT OID_FICHERO_056 FROM AA79B56S01 WHERE (ID_DOC_056=?) OR (ID_DOC_REL_056=?) ");
		params.add(elId);
		params.add(elId);
		return this.getJdbcTemplate().queryForList(query.toString(), params.toArray(), String.class);
	}

	@Override()
	public List<String> getOidFichRelacionado(BigDecimal elId) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT OID_FICHERO_056 FROM AA79B56S01 WHERE ID_DOC_REL_056=? ");
		params.add(elId);
		return this.getJdbcTemplate().queryForList(query.toString(), params.toArray(), String.class);
	}

	@Override()
	public void removeDocRelacionado(Aa79bDocumentoExpediente documento) {
		List<Object> params = new ArrayList<Object>();
		String query = "DELETE FROM AA79B56S01 WHERE ID_DOC_REL_056=?";
		params.add(documento.getIdDoc());
		this.getJdbcTemplate().update(query, params.toArray());

	}

	public int updateFichero56ConFichero(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, int x) {
		if (documento.getFicheros().get(x).getContacto() == null) {
			Aa79bContactoDoc contacto = new Aa79bContactoDoc();
			documento.getFicheros().get(x).setContacto(contacto);
		}
		String query = "UPDATE AA79B56S01 SET OID_FICHERO_056=?, EXTENSION_DOC_056=?, TAMANO_DOC_056=?,"
				+ "IND_ENCRIPTADO_056=?, CONTENT_TYPE_056=?, ID_DOC_REL_056=?, "
				+ "CLASE_DOCUMENTO_056=?, TIPO_DOCUMENTO_056=?, TITULO_056=?, "
				+ "NUM_PAL_SOLIC_056=?, NUM_PAL_IZO_056=?, PERSONA_CONTACTO_056=?, "
				+ "EMAIL_CONTACTO_056=?, TELEFONO_CONTACTO_056=?, DIRECCION_CONTACTO_056=?, "
				+ "ENTIDAD_CONTACTO_056=?, FECHA_ALTA_056=?, DNI_ALTA_056=?, "
				+ "ID_DOC_VERSION_056=?, NOMBRE_FICHERO_056=? "
				// Incluyo el origen para forzar "E" (Externo)
				+ ",IND_ORIGEN_056 = ?" + " WHERE ID_DOC_056=? AND ANYO_056=? AND NUM_EXP_056=?";

		return this.getJdbcTemplate().update(query, documento.getFicheros().get(x).getOid(),
				documento.getFicheros().get(x).getExtension(), documento.getFicheros().get(x).getTamano(),
				Utils.getValueInd(documento.getFicheros().get(x).getEncriptado()),
				documento.getFicheros().get(x).getContentType(), documento.getFicheros().get(x).getIdDocRel(),
				documento.getClaseDocumento(), documento.getTipoDocumento(), documento.getTitulo(),
				documento.getNumPalSolic(), documento.getNumPalIzo(),
				documento.getFicheros().get(x).getContacto().getPersona(),
				documento.getFicheros().get(x).getContacto().getEmail(),
				documento.getFicheros().get(x).getContacto().getTelefono(),
				documento.getFicheros().get(x).getContacto().getDireccion(),
				documento.getFicheros().get(x).getContacto().getEntidad(),
				DateUtils.obtFechaHoraFormateada(documento.getFicheros().get(x).getFechaAlta(), null),
				solicitud.getSolicitante().getDni(), null, documento.getFicheros().get(x).getNombre(),
				// Incluyo el origen para forzar "E" (Externo)
				TipoOrigenFicheroEnum.EXTERNO.getValue(), documento.getFicheros().get(x).getIdDocInsertado(),
				solicitud.getAnyo(), solicitud.getNumExp());
	}

	public int updateFichero56SinFichero(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, int x) {
		if (documento.getFicheros().get(x).getContacto() == null) {
			Aa79bContactoDoc contacto = new Aa79bContactoDoc();
			documento.getFicheros().get(x).setContacto(contacto);
		}
		String query = "UPDATE AA79B56S01 SET " + " ID_DOC_REL_056=?, "
				+ "CLASE_DOCUMENTO_056=?, TIPO_DOCUMENTO_056=?, TITULO_056=?, "
				+ "NUM_PAL_SOLIC_056=?, NUM_PAL_IZO_056=?, PERSONA_CONTACTO_056=?, "
				+ "EMAIL_CONTACTO_056=?, TELEFONO_CONTACTO_056=?, DIRECCION_CONTACTO_056=?, "
				+ "ENTIDAD_CONTACTO_056=? " + " WHERE ID_DOC_056=? AND ANYO_056=? AND NUM_EXP_056=?";

		return this.getJdbcTemplate().update(query, documento.getFicheros().get(x).getIdDocRel(),
				documento.getClaseDocumento(), documento.getTipoDocumento(), documento.getTitulo(),
				documento.getNumPalSolic(), documento.getNumPalIzo(),
				documento.getFicheros().get(x).getContacto().getPersona(),
				documento.getFicheros().get(x).getContacto().getEmail(),
				documento.getFicheros().get(x).getContacto().getTelefono(),
				documento.getFicheros().get(x).getContacto().getDireccion(),
				documento.getFicheros().get(x).getContacto().getEntidad(),
				documento.getFicheros().get(x).getIdDocInsertado(), solicitud.getAnyo(), solicitud.getNumExp());
	}

	@Override
	public int updateDocumentosExpediente73(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, int x) {
		if (documento.getFicheros().get(x).getContacto() == null) {
			Aa79bContactoDoc contacto = new Aa79bContactoDoc();
			documento.getFicheros().get(x).setContacto(contacto);
		}

		String query73 = "UPDATE AA79B73S01 SET " + " OID_FICHERO_073=?, EXTENSION_DOC_073=?, TAMANO_DOC_073=?,"
				+ "IND_ENCRIPTADO_073=?, CONTENT_TYPE_073=?, ID_DOC_REL_073=?, "
				+ "CLASE_DOCUMENTO_073=?, TIPO_DOCUMENTO_073=?, TITULO_073=?, "
				+ "NUM_PAL_SOLIC_073=?, PERSONA_CONTACTO_073=?, "
				+ "EMAIL_CONTACTO_073=?, TELEFONO_CONTACTO_073=?, DIRECCION_CONTACTO_073=?, "
				+ "ENTIDAD_CONTACTO_073=?, FECHA_ALTA_073=?, NOMBRE_FICHERO_073=? "
				+ " WHERE ID_DOC_073=? AND ANYO_073=? AND NUM_EXP_073=?";

		return this.getJdbcTemplate().update(query73, documento.getFicheros().get(x).getOid(),
				documento.getFicheros().get(x).getExtension(), documento.getFicheros().get(x).getTamano(),
				Utils.getValueInd(documento.getFicheros().get(x).getEncriptado()),
				documento.getFicheros().get(x).getContentType(), documento.getFicheros().get(x).getIdDocRel(),
				documento.getClaseDocumento(), documento.getTipoDocumento(), documento.getTitulo(),
				documento.getNumPalSolic(), documento.getFicheros().get(x).getContacto().getPersona(),
				documento.getFicheros().get(x).getContacto().getEmail(),
				documento.getFicheros().get(x).getContacto().getTelefono(),
				documento.getFicheros().get(x).getContacto().getDireccion(),
				documento.getFicheros().get(x).getContacto().getEntidad(),
				DateUtils.obtFechaHoraFormateada(documento.getFicheros().get(x).getFechaAlta(), null),
				documento.getFicheros().get(x).getNombre(), documento.getFicheros().get(x).getIdDocInsertado(),
				solicitud.getAnyo(), solicitud.getNumExp());
	}

	public void updateObservaciones(Aa79bFicheroObservaciones ficheroObservaciones, String observaciones,
			Aa79bSolicitud solicitud, boolean subsanar) {

		Expediente expediente = new Expediente();
		expediente.setAnyo(solicitud.getAnyo());
		expediente.setNumExp(solicitud.getNumExp());
		ObservacionesExpediente observacionesOriginales = this.observacionesExpedienteDao.observacionesFind(expediente);
		// comprobar si nuevo o modificar
		if (observaciones != null || ficheroObservaciones != null) {

			if (ficheroObservaciones != null) {

				if (observacionesOriginales != null
						&& !ficheroObservaciones.getOidFichero().equals(observacionesOriginales.getOidFichero())
						&& StringUtils.isNotEmpty(observacionesOriginales.getOidFichero())) {
					// si cambia el oid, tenemos que eliminarlo
					this.oidsAuxiliarDao.add(observacionesOriginales.getOidFichero());
				}
				this.oidsAuxiliarDao.remove(ficheroObservaciones.getOidFichero());
			} else {
				if (observacionesOriginales != null
						&& StringUtils.isNotEmpty(observacionesOriginales.getOidFichero())) {
					this.oidsAuxiliarDao.add(observacionesOriginales.getOidFichero());
				}
			}

			if (observacionesOriginales != null) {
				// update observaciones
				this.updateObservacionesExpediente(solicitud, DBConstants.TABLA_055, false);
				this.updateObservacionesExpediente(solicitud, DBConstants.TABLA_072, subsanar);

			} else {
				// add observaciones
				this.insertObservacionesExpediente(solicitud, DBConstants.TABLA_055, false);
				this.insertObservacionesExpediente(solicitud, DBConstants.TABLA_072, subsanar);
			}

		} else {
			this.eliminarObservaciones(observacionesOriginales, ficheroObservaciones, solicitud, subsanar);
		}
	}

	private void eliminarObservaciones(ObservacionesExpediente observacionesOriginales,
			Aa79bFicheroObservaciones ficheroObservaciones, Aa79bSolicitud solicitud, boolean subsanar) {
		if (observacionesOriginales != null) {
			if (observacionesOriginales.getOidFichero() != null && ficheroObservaciones == null) {
				this.oidsAuxiliarDao.add(observacionesOriginales.getOidFichero());
			}
			// delete observaciones
			this.deleteObservacionesExpediente(solicitud, DBConstants.TABLA_055, false);
			this.deleteObservacionesExpediente(solicitud, DBConstants.TABLA_072, subsanar);
			if (solicitud.getDatosInterpretacion() != null) {
				solicitud.getDatosInterpretacion().setIndObservaciones(Constants.NO);
			} else {
				solicitud.getDatosTradRev().setIndObservaciones(Constants.NO);
			}
		}
	}

	public void updateObservacionesExpediente(Aa79bSolicitud solicitud, String tabla, boolean subsanar) {
		if (!subsanar) {
			String query = "UPDATE AA79B" + tabla + "S01 SET OBSERVACIONES_0" + tabla + "=?, OID_FICHERO_0" + tabla
					+ "=?, " + "EXTENSION_DOC_0" + tabla + "=?, CONTENT_TYPE_0" + tabla + "=?, NOMBRE_FICHERO_0" + tabla
					+ "=?, TAMANO_DOC_0" + tabla + "=?" + " WHERE ANYO_0" + tabla + AND_NUM_EXP_0 + tabla + "=?";

			if (solicitud.getDatosInterpretacion() != null) {
				this.getJdbcTemplate().update(query, solicitud.getDatosInterpretacion().getObservaciones(), null, null,
						null, null, null, solicitud.getAnyo(), solicitud.getNumExp());
			} else {
				if (solicitud.getDatosTradRev().getFicheroObservaciones() != null) {
					this.getJdbcTemplate().update(query, solicitud.getDatosTradRev().getObservaciones(),
							solicitud.getDatosTradRev().getFicheroObservaciones().getOidFichero(),
							solicitud.getDatosTradRev().getFicheroObservaciones().getExtension(),
							solicitud.getDatosTradRev().getFicheroObservaciones().getContentType(),
							solicitud.getDatosTradRev().getFicheroObservaciones().getNombre(),
							solicitud.getDatosTradRev().getFicheroObservaciones().getTamano(), solicitud.getAnyo(),
							solicitud.getNumExp());
				} else {
					this.getJdbcTemplate().update(query, solicitud.getDatosTradRev().getObservaciones(), null, null,
							null, null, null, solicitud.getAnyo(), solicitud.getNumExp());
				}

			}
		}
	}

	public void deleteObservacionesExpediente(Aa79bSolicitud solicitud, String tabla, boolean subsanar) {
		if (!subsanar) {
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM AA79B" + tabla + "S01 ");
			query.append(" WHERE ANYO_0" + tabla + AND_NUM_EXP_0 + tabla + "=?");
			this.getJdbcTemplate().update(query.toString(), solicitud.getAnyo(), solicitud.getNumExp());
		}
	}

	@Override
	public int guardarTecnico(Solicitante solicitanteAInsertar) {
		StringBuilder query = new StringBuilder();
		query.append(
				"INSERT INTO AA79B77S01 (DNI_077, TIPIDEN_077 , PREDNI_077, SUFDNI_077 , NOMBRE_077 , APEL1_077 , APEL2_077 ) "
						+ "VALUES (?,?,?,?,?,?,?)");
		return this.getJdbcTemplate().update(query.toString(), solicitanteAInsertar.getDni(),
				solicitanteAInsertar.getTipIden(), solicitanteAInsertar.getPreDni(), solicitanteAInsertar.getSufDni(),
				solicitanteAInsertar.getNombre(), solicitanteAInsertar.getApellido1(),
				solicitanteAInsertar.getApellido2());

	}

	public void addDocumentosExp(Aa79bDocumentoExpediente beanDocExpediente, Aa79bSolicitud solicitud) {
		for (int x = 0; x < beanDocExpediente.getFicheros().size(); x++) {
			try {
				beanDocExpediente.getFicheros().get(x).setIdDocInsertado(
						this.addDocumentosExpediente(solicitud.getSolicitante(), beanDocExpediente, x));
				// Elimino de la tabla auxiliar de OIDs
				this.oidsAuxiliarDao.remove(beanDocExpediente.getFicheros().get(x).getOid());
			} catch (Exception e) {
				LOGGER.info("Error anyadiendo documentos ", e);
				beanDocExpediente.getFicheros().get(x).setError(e.getLocalizedMessage());
			}
		}
	}

	private StringBuilder excepcionesWhere(ExcepcionFacturacion excepcionFacturacion, List<Object> params) {
		StringBuilder query = new StringBuilder();
		query.append(GenericoDaoImpl.DEFAULT_WHERE);

		query.append(SqlUtils.generarWhereIgual("ID_036", excepcionFacturacion.getId036(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_036", excepcionFacturacion.getTipoEntidad036(), params));
		query.append(SqlUtils.generarWhereIgual("ID_ENTIDAD_036", excepcionFacturacion.getIdEntidad036(), params));
		query.append(
				SqlUtils.generarWhereIgual("TIPO_EXCEPCION_036", excepcionFacturacion.getTipoExcepcion036(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_ASOC_036", excepcionFacturacion.getTipoEntidadAsoc036(),
				params));
		query.append(
				SqlUtils.generarWhereIgual("ID_ENTIDAD_ASOC_036", excepcionFacturacion.getIdEntidadAsoc036(), params));
		query.append(SqlUtils.generarWhereIgual("DNI_CONTACTO_036", excepcionFacturacion.getDniContacto036(), params));
		query.append(SqlUtils.generarWhereIgual("POR_FACTURA_036", excepcionFacturacion.getPorFactura036(), params));

		return query;
	}

	@Override
	public List<Expediente> getExpedientesRefAplic(Expediente expediente){
		String query = "SELECT ANYO_053 ANYO, NUM_EXP_053 NUMEXP FROM AA79B53T00 JOIN AA79B51T00 ON ANYO_053 = ANYO_051 AND NUM_EXP_053 = NUM_EXP_051 JOIN AA79B54T00 ON "
				+ "ANYO_053 = ANYO_054 AND NUM_EXP_053 = NUM_EXP_054 WHERE REF_TRAMITAGUNE_053 = ? AND DNI_SOLICITANTE_054 = ?";

		return this.getJdbcTemplate().query(query, this.rwMapRelaciones, expediente.getExpedienteTradRev().getRefTramitagune(),
				expediente.getGestorExpediente().getSolicitante().getDni());
	}

}
