package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.AnulacionExpedienteSolver;
import aa79b.bbdd.solvers.BitacoraExpedienteSolver;
import aa79b.bbdd.solvers.BitacoraSolicitanteSolver;
import aa79b.bbdd.solvers.ConfigDireccionEmailsSolver;
import aa79b.bbdd.solvers.ConfigTextoEmailsSolver;
import aa79b.bbdd.solvers.ExpedientesSolver;
import aa79b.bbdd.solvers.GestorExpedientesSolver;
import aa79b.bbdd.solvers.MotivosAnulacionSolver;
import aa79b.bbdd.solvers.PropiedadSolver;
import aa79b.bbdd.solvers.RegistroAccionesSolver;
import aa79b.util.beans.AnulacionExpediente;
import aa79b.util.beans.BitacoraExpediente;
import aa79b.util.beans.BitacoraSolicitante;
import aa79b.util.beans.ConfigDireccionEmail;
import aa79b.util.beans.ConfigTextoEmails;
import aa79b.util.beans.Expediente;
import aa79b.util.beans.GestorExpediente;
import aa79b.util.beans.MotivosAnulacion;
import aa79b.util.beans.Propiedad;
import aa79b.util.beans.RegistroAcciones;
import aa79b.util.common.AccionBitacoraEnum;
import aa79b.util.common.AccionesEnum;
import aa79b.util.common.Constants;
import aa79b.util.common.EstadoExpedienteEnum;
import aa79b.util.common.EstadoSubsanacionEnum;
import aa79b.util.common.FaseExpedienteEnum;
import aa79b.util.common.Logger;
import aa79b.util.common.MailTextConstants;
import aa79b.util.common.MotivosAnulacionEnum;
import aa79b.util.common.PuntosMenuEnum;
import aa79b.util.common.TipoAvisoEnum;
import aa79b.util.common.TipoRequerimientoEnum;
import aa79b.util.common.Utils;
import aa79b.util.mail.EmailMessage;
import aa79b.util.mail.EmailService;
import aa79b.util.mail.ParametrosEmail;

/**
 * @author mrodriguezç
 *
 */
public class AnulacionExpedientes {

	private static final String ENTORNO = EmailService.obtenerProperty("app.entorno");
	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 4;

	// =======================================================================
	// PROPIEDADES DE INSTANCIA
	// =======================================================================
	/**
	 * dbServer: String.
	 */
	private String dbServer = "";
	/**
	 * dbUsuario: String.
	 */
	private String dbUsuario = "";
	/**
	 * dbPassword: String.
	 */
	private String dbPassword = "";
	/**
	 * dbDriver: String.
	 */
	private String dbDriver = "";


	/**
	 * this.baseSql: BaseSql.
	 */
	private BaseSql baseSql = null;

	/**
	 * ExpedientesSolver: expedientesSolver.
	 */
	private ExpedientesSolver expedientesSolver = null;

	/**
	 * BitacoraExpedienteSolver: bitacoraExpedienteSolver.
	 */
	private BitacoraExpedienteSolver bitacoraExpedienteSolver = null;

	/**
	 * BitacoraSolicitanteSolver: bitacoraSolicitanteSolver.
	 */
	private BitacoraSolicitanteSolver bitacoraSolicitanteSolver = null;

	/**
	 * AnulacionExpedienteSolver: anulacionExpedienteSolver.
	 */
	private AnulacionExpedienteSolver anulacionExpedienteSolver = null;

	/**
	 * RegistroAccionesSolver registroAccionesSolver.
	 */
	private RegistroAccionesSolver registroAccionesSolver = null;

	/**
	 * PropiedadSolver propiedadSolver.
	 */
	private PropiedadSolver propiedadSolver = null;

	/**
	 * GestorExpedientesSolver gestorExpedientesSolver.
	 */
	private GestorExpedientesSolver gestorExpedientesSolver = null;

	/**
	 * ConfigTextoEmailsSolver: configTextoEmailsSolver.
	 */
	private ConfigTextoEmailsSolver configTextoEmailsSolver = null;

	/**
	 * ConfigDireccionEmailsSolver: configDireccionEmailsSolver.
	 */
	private ConfigDireccionEmailsSolver configDireccionEmailsSolver = null;

	/**
	 * MotivosAnulacionSolver: motivosAnulacionSolver.
	 */
	private MotivosAnulacionSolver motivosAnulacionSolver = null;

	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO AnulacionExpedientes. main. ***********************************");

		final AnulacionExpedientes anulacionExpedientes = new AnulacionExpedientes();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = anulacionExpedientes.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN AnulacionExpedientes. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN AnulacionExpedientes. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
		System.exit(rdoProcesoBatch);
	}
	/**
	 *
	 * @param args a
	 * @return int
	 * @throws IOException
	 */
	protected int doMain(String[] args) throws IOException {

		int rdoProcesoBatch = Constants.MAGIC_NUMBER0;

		try {
			this.recogerParametros(args);
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AnulacionExpedientes. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "AnulacionExpedientes - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.expedientesSolver = new ExpedientesSolver();
			this.bitacoraExpedienteSolver = new BitacoraExpedienteSolver();
			this.bitacoraSolicitanteSolver = new BitacoraSolicitanteSolver();
			this.propiedadSolver = new PropiedadSolver();
			this.anulacionExpedienteSolver = new AnulacionExpedienteSolver();
			this.registroAccionesSolver = new RegistroAccionesSolver();
			this.gestorExpedientesSolver = new GestorExpedientesSolver();
			this.configTextoEmailsSolver = new ConfigTextoEmailsSolver();
			this.configDireccionEmailsSolver = new ConfigDireccionEmailsSolver();
			this.motivosAnulacionSolver = new MotivosAnulacionSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AnulacionExpedientes. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = AnulacionExpedientes.class.getResourceAsStream("/aa79b/aa79b.properties");
			if (in != null) {
				// Existe el fichero de propiedades
				try {
					this.prop.load(in);
					in.close();
				} catch (final IOException e) {
					throw new Exception("El fichero /aa79b/aa79b.properties ha dado error.", e);
				}
			} else {
				throw new Exception("El fichero /aa79b/aa79b.properties no esta en el classpath o no existe.");
			}
			in.close();
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AnulacionExpedientes. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO AnulacionExpedientes. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO AnulacionExpedientes. Obtener expedientes susceptibles de ser anulados.");
			Logger.batchlog(Logger.INFO, "INICIO AnulacionExpedientes. ENTORNO: " + AnulacionExpedientes.ENTORNO);

			Logger.batchlog(Logger.INFO, "Recoger el valor de la propiedad que indica el plazo de espera");

			// Se recoge el valor de la propiedad que indica el plazo de espera
			final Propiedad propiedad = this.propiedadSolver.find(this.baseSql, Constants.MAGIC_NUMBER2);
			final Double plazoEntrega = Double.valueOf(propiedad.getValor());
			final List<Expediente> listaExpedientes = this.expedientesSolver.findExpAAnular(this.baseSql, plazoEntrega);
			final List<MotivosAnulacion> listaMotivosAnul = this.motivosAnulacionSolver.findAll(this.baseSql);
			if(listaExpedientes!=null && !listaExpedientes.isEmpty()){
				BitacoraExpediente bitacoraExpediente;
				BitacoraSolicitante bitacoraSolicitante;
				AnulacionExpediente anulacionExpediente;

				for (final Expediente expediente : listaExpedientes){

					final Long idMotivoAnulacion = this.obtenerMotivoAnulacion(plazoEntrega, expediente);

					Logger.batchlog(Logger.INFO, "Se guarda el motivo de anulación");

					final Long idAnulExp = this.anulacionExpedienteSolver.getNextVal(this.baseSql);
					anulacionExpediente = new AnulacionExpediente();
					anulacionExpediente.setId(idAnulExp);
					anulacionExpediente.setIdMotivoAnulacion(idMotivoAnulacion);
					this.anulacionExpedienteSolver.add(this.baseSql, anulacionExpediente);

					Logger.batchlog(Logger.INFO, "Se actualiza la bitacora expediente");

					// Actualizar la bitacora (Tabla 59)
					bitacoraExpediente = new BitacoraExpediente();
					bitacoraExpediente.setAnyo(expediente.getAnyo());
					bitacoraExpediente.setNumExp(expediente.getNumExp());
					bitacoraExpediente.setIdEstadoExp((long) EstadoExpedienteEnum.ANULADO.getValue());
					bitacoraExpediente.setIdFaseExp((long) FaseExpedienteEnum.ANULADO.getValue());
					bitacoraExpediente.setIdMotivoAnulacion(idAnulExp);

					final Long idEstadoBitacora = this.bitacoraExpedienteSolver.getNextVal(this.baseSql, bitacoraExpediente);
					bitacoraExpediente.setIdEstadoBitacora(idEstadoBitacora);

					this.bitacoraExpedienteSolver.add(this.baseSql, bitacoraExpediente);

					Logger.batchlog(Logger.INFO, "Se actualiza el estado de la bitacora del expediente");

					// Actualizar el estado de la bitacora del expediente (Tabla 51)
					expediente.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());
					this.expedientesSolver.updateIdEstadoBitacora(this.baseSql, expediente);

					Logger.batchlog(Logger.INFO, "Se actualiza la bitacora del solicitante");

					// Actualizar bitacora solicitante (Tabla 79)
					bitacoraSolicitante = new BitacoraSolicitante();
					bitacoraSolicitante.setAnyo(expediente.getAnyo());
					bitacoraSolicitante.setNumExp(expediente.getNumExp());
					bitacoraSolicitante.setUsuario(Constants.IZO);
					bitacoraSolicitante.setIdAccion((long) AccionBitacoraEnum.EXP_ANULADO.getValue());
					this.bitacoraSolicitanteSolver.add(this.baseSql, bitacoraSolicitante);

					Logger.batchlog(Logger.INFO, "Se actualiza el registro de acciones");

					// Obtener descripción del motivo de anulación para el registro de acciones
					final String descMotivoAnulacion = this.obtenerDescMotivoAnulacion(idMotivoAnulacion, listaMotivosAnul);

					// Registro de acciones (Tabla 43)
					this.registrarAcciones(this.baseSql, expediente, descMotivoAnulacion);

					Logger.batchlog(Logger.INFO, "Se envía el email al gestor del expediente");

					// Enviar email al gestor del expediente
					this.enviarEmail(this.baseSql, expediente);
				}

			}else{
				Logger.batchlog(Logger.INFO, "No existen registros para tratar");
			}
			Logger.batchlog(Logger.INFO, "INICIO AnulacionExpedientes. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AnulacionExpedientes. Error.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			System.exit(rdoProcesoBatch);
		}

		return rdoProcesoBatch;
	}

	/**
	 * @param plazoEntrega
	 * @param expediente
	 * @return Long, idMotivoAnulacion
	 */
	private Long obtenerMotivoAnulacion(final Double plazoEntrega, Expediente expediente) {
		Long idMotivoAnulacion = (long) MotivosAnulacionEnum.OTROS.getValue();

		// Se asigna el motivo de anulacion para cada expediente
		if (this.isPlazoSubsanacionExpirado(plazoEntrega, expediente)) {
			// Plazo de subsanación expirado
			// Motivo anulación = 2
			idMotivoAnulacion = (long) MotivosAnulacionEnum.PLAZO_SUBS_EXPIRADO.getValue();
		} else if (EstadoExpedienteEnum.EN_CURSO.getValue() == expediente.getBitacoraExpediente().getIdEstadoExp() &&
				FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue() == expediente.getBitacoraExpediente().getIdFaseExp()) {
			idMotivoAnulacion = this.obtenerMotivoAnulXIndSubsanacion(expediente, plazoEntrega);
		}

		return idMotivoAnulacion;
	}

	/**
	 * @param expediente
	 * @param plazoEntrega
	 * @return Long, idMotivoAnulacion
	 */
	private Long obtenerMotivoAnulXIndSubsanacion(Expediente expediente, final Double plazoEntrega) {
		Long idMotivoAnulacion = (long) MotivosAnulacionEnum.OTROS.getValue();

		if (EstadoSubsanacionEnum.PENDIENTE.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getEstado() &&
				Constants.IND_NO.contentEquals(expediente.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado()) &&
				expediente.getBitacoraExpediente().getSubsanacionExp().getDifPlazoEntrega() > plazoEntrega) {
			idMotivoAnulacion = this.obtenerMotivoAnulXRequerimiento(expediente);
		} else if (EstadoSubsanacionEnum.RECHAZADO.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getEstado() &&
				Constants.IND_SI.contentEquals(expediente.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado())){
			// Fecha propuesta por el IZO rechazada
			// Motivo anulación = 6
			idMotivoAnulacion = (long) MotivosAnulacionEnum.FECHA_PROP_RECHAZADA.getValue();
		}

		return idMotivoAnulacion;
	}

	/**
	 * @param expediente
	 * @return Long, idMotivoAnulacion
	 */
	private Long obtenerMotivoAnulXRequerimiento(Expediente expediente) {
		Long idMotivoAnulacion = (long) MotivosAnulacionEnum.OTROS.getValue();

		if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getTipoRequerimiento() ||
				TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getTipoRequerimiento()){
			// Presupuesto sin aceptar en plazo
			// Motivo anulación = 4
			idMotivoAnulacion = (long) MotivosAnulacionEnum.PRESUP_NO_ACEPTADO_PLAZO.getValue();

		} else if (TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getTipoRequerimiento()){
			// Fecha propuesta por el IZO sin aceptar en plazo
			// Motivo anulación = 5
			idMotivoAnulacion = (long) MotivosAnulacionEnum.FECHA_PROP_NO_ACEPTADA_PLAZO.getValue();
		} else if (TipoRequerimientoEnum.SUBSANACION.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getTipoRequerimiento()){
			// Subsanación sin realizar en el plazo
			// Motivo anulación = 5
			idMotivoAnulacion = (long) MotivosAnulacionEnum.PLAZO_SUBS_EXPIRADO.getValue();
		}
		return idMotivoAnulacion;
	}

	/**
	 * @param plazoEntrega
	 * @param expediente
	 * @return boolean
	 */
	private boolean isPlazoSubsanacionExpirado(final Double plazoEntrega, Expediente expediente) {
		return EstadoExpedienteEnum.EN_ESTUDIO.getValue() == expediente.getBitacoraExpediente().getIdEstadoExp() &&
				FaseExpedienteEnum.PDTE_TRAM_SUBSANACION.getValue() == expediente.getBitacoraExpediente().getIdFaseExp() &&
				Constants.IND_NO.equals(expediente.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado()) &&
				EstadoSubsanacionEnum.PENDIENTE.getValue() == expediente.getBitacoraExpediente().getSubsanacionExp().getEstado() &&
				expediente.getBitacoraExpediente().getSubsanacionExp().getDifPlazoEntrega() > plazoEntrega;
	}

	/**
	 * Obtiene la descripción del motivo de anuluación
	 *
	 * @param idMotivoAnulacion
	 * 			long
	 * @param listMotivos
	 * 			List<MotivosAnulacion>
	 * @return String, descMotivoAnulacion
	 */
	private String obtenerDescMotivoAnulacion(final long idMotivoAnulacion, List<MotivosAnulacion> listMotivos) {
		String descMotivoAnulacion = StringUtils.EMPTY;

		if (listMotivos != null && !listMotivos.isEmpty()){
			for (int i = 0; i < listMotivos.size(); i++) {
				final MotivosAnulacion motivoAnulacion = listMotivos.get(i);

				if (motivoAnulacion.getId012() == idMotivoAnulacion){
					descMotivoAnulacion = motivoAnulacion.getDescEu012();
				}
			}
		}

		return descMotivoAnulacion;
	}

	/**
	 *
	 * @param args a
	 * @throws RuntimeException e
	 */
	private void recogerParametros(String[] args) throws RuntimeException{

		if (args == null) {
			throw new RuntimeException("AnulacionExpedientes - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0) {
				throw new RuntimeException("AnulacionExpedientes - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != AnulacionExpedientes.numeroParametros) {
				throw new RuntimeException("AnulacionExpedientes - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + AnulacionExpedientes.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "AnulacionExpedientes - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "AnulacionExpedientes - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "AnulacionExpedientes - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "AnulacionExpedientes - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
	}

	/**
	 * Se registra una nueva accion en la tabla 43 (Registro de acciones)
	 *
	 * @param baseSql
	 * 			BaseSql
	 * @param bean
	 *            Expediente
	 * @throws Exception
	 */
	private void registrarAcciones(BaseSql baseSql, Expediente bean, String descMotivoAnulacion) throws Exception {
		final RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(AccionesEnum.MODIFICACION.getValue());

		final Locale locale = new Locale(Constants.LANG_EUSKERA);
		final StringBuilder observ = new StringBuilder();
		String textoObs = StringUtils.EMPTY;
		if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
			textoObs = MailTextConstants.TEXTO_MOTIVO_ANULACION_EU;
		} else {
			textoObs = MailTextConstants.TEXTO_MOTIVO_ANULACION_ES;
		}
		observ.append(textoObs).append(" ");
		observ.append(descMotivoAnulacion).append(" \n");
		reg.setAnyoExp(bean.getAnyo());
		reg.setNumExp(bean.getNumExp());
		reg.setIdEstadoBitacora(bean.getEstadoBitacora());
		reg.setObserv(observ.toString());
		this.registroAccionesSolver.add(baseSql, reg);
	}

	/**
	 * Se realiza el envio del email
	 *
	 * @param baseSql
	 * 			BaseSql
	 * @param expediente
	 *            Expediente
	 * @throws Exception
	 */
	private void enviarEmail(BaseSql baseSql, Expediente expediente) throws Exception {
		final GestorExpediente gestor = this.gestorExpedientesSolver.getGestorExpediente(baseSql, expediente);

		if (gestor != null && gestor.getDatosContacto() != null && gestor.getDatosContacto().getEmail031() != null){

			//Recoge parámetros para enviar el mail
			Logger.batchlog(Logger.INFO, "Recoger el texto del envío de mail");

			//Se obtiene el remitente
			final ConfigDireccionEmail configDireccionEmail = this.configDireccionEmailsSolver.find(baseSql);

			// Se obtiene el mensaje.
			final ConfigTextoEmails configTextoEmails = this.configTextoEmailsSolver.findAviso(TipoAvisoEnum.ANULADO_EXP.getValue(), this.baseSql);

			//Enviar el mail
			Logger.batchlog(Logger.INFO, "Enviar el  mail");
			final EmailMessage message = new EmailMessage();

			message.setContentType("text/html");
			message.setSubject("");
			message.setBody("");

			if(configTextoEmails != null){



				message.setSubject(Utils.getNumExpedienteParameter(expediente) + configTextoEmails.getAsuntoEu().toUpperCase() + " / " + configTextoEmails.getAsuntoEs());

				ParametrosEmail parametrosEmail = new ParametrosEmail();

				parametrosEmail.setMensajeEu(configTextoEmails.getTextoMailEu());
				parametrosEmail.setMensajeEs(configTextoEmails.getTextoMailEs());

				Map<String, String> infoEu = new LinkedHashMap<String, String>();
				Map<String, String> infoEs = new LinkedHashMap<String, String>();

				infoEu.put(MailTextConstants.TEXTO_NUM_EXP_EU, expediente.getAnyoNumExpConcatenado()
						+ Constants.ESPACIO + Constants.GUION + Constants.ESPACIO + expediente.getTitulo());
				infoEs.put(MailTextConstants.TEXTO_NUM_EXP_ES, expediente.getAnyoNumExpConcatenado()
						+ Constants.ESPACIO + Constants.GUION + Constants.ESPACIO + expediente.getTitulo());

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				message.setSubject(Utils.getNumExpedienteParameter(expediente) + configTextoEmails.getAsuntoEu().toUpperCase() + " / " + configTextoEmails.getAsuntoEs());
				message.setBody(Utils.getMessageEmail(parametrosEmail));


			}

			message.setFromAddress(configDireccionEmail.getDirEmail());

			//Se obtiene la dirección de correo
			final String dirCorreo = gestor.getDatosContacto().getEmail031();
			message.setToAddress(dirCorreo);

			EmailService.getInstance().sendEmail(message);
			Logger.batchlog(Logger.INFO, "Mail enviado");

		}

	}


}
