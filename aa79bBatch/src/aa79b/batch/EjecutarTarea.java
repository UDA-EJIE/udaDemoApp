package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.ConfigDireccionEmailsSolver;
import aa79b.bbdd.solvers.ConfigTextoEmailsSolver;
import aa79b.bbdd.solvers.DatosContactoSolver;
import aa79b.bbdd.solvers.ExpedientesSolver;
import aa79b.bbdd.solvers.GestorExpedientesSolver;
import aa79b.bbdd.solvers.TareasSolver;
import aa79b.util.beans.ConfigDireccionEmail;
import aa79b.util.beans.ConfigTextoEmails;
import aa79b.util.beans.DatosContacto;
import aa79b.util.beans.Expediente;
import aa79b.util.beans.GestorExpediente;
import aa79b.util.beans.Tarea;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.common.MailTextConstants;
import aa79b.util.common.TipoAvisoEnum;
import aa79b.util.common.TipoRecursoEnum;
import aa79b.util.common.TipoTareaGestionAsociadaEnum;
import aa79b.util.common.Utils;
import aa79b.util.mail.EmailMessage;
import aa79b.util.mail.EmailService;
import aa79b.util.mail.ParametrosEmail;

/**
 *
 * @author aresua
 *
 */
public class EjecutarTarea {

	private static final String ENTORNO = EmailService.obtenerProperty("app.entorno");
	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 5;

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
	 * this.idTarea: BigDecimal.
	 */
	private String idTarea = null;
	/**
	 * ConfigTextoEmailsSolver: configTextoEmailsSolver.
	 */
	private ConfigTextoEmailsSolver configTextoEmailsSolver = null;
	/**
	 * ConfigDireccionEmailsSolver: configDireccionEmailsSolver.
	 */
	private ConfigDireccionEmailsSolver configDireccionEmailsSolver = null;
	/**
	 * TareasSolver: tareasSolver.
	 */
	private TareasSolver tareasSolver = null;
	/**
	 * DatosContactoSolver: datosContactoSolver.
	 */
	private DatosContactoSolver datosContactoSolver = null;
	/**
	 * GestorExpedientesSolver: gestorExpedientesSolver.
	 */
	private GestorExpedientesSolver gestorExpedientesSolver = null;
	/**
	 * ExpedientesSolver: expedientesSolver.
	 */
	private ExpedientesSolver expedientesSolver = null;
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. main. ***********************************");

		final EjecutarTarea EjecutarTarea = new EjecutarTarea();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = EjecutarTarea.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN EjecutarTarea. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN EjecutarTarea. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "EjecutarTarea. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "EjecutarTarea - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			baseSql = BaseSql.createInstance(dbDriver, dbServer, dbUsuario, dbPassword);
			tareasSolver = new TareasSolver();
			datosContactoSolver = new DatosContactoSolver();
			configTextoEmailsSolver = new ConfigTextoEmailsSolver();
			configDireccionEmailsSolver = new ConfigDireccionEmailsSolver();
			gestorExpedientesSolver = new GestorExpedientesSolver();
			expedientesSolver = new ExpedientesSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "EjecutarTarea. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = EjecutarTarea.class.getResourceAsStream("/aa79b/aa79b.properties");
			if (in != null) {
				// Existe el fichero de propiedades
				try {
					prop.load(in);
					in.close();
				} catch (final IOException e) {
					throw new Exception("El fichero /aa79b/aa79b.properties ha dado error.", e);
				}
			} else {
				throw new Exception("El fichero /aa79b/aa79b.properties no esta en el classpath o no existe.");
			}
			in.close();
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "EjecutarTarea. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		boolean envioMailsGeneral = true;
		String dirCorreo = "";

		// Inicio del proceso
		try {
 			Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. Se recupera el tipo de tarea: ");
			final List<Tarea> lista = tareasSolver.findTarea(baseSql, idTarea);
			final Tarea tarea = lista.get(Constants.MAGIC_NUMBER0);
			final String titulo = tareasSolver.obtenerTituloExpediente(baseSql, tarea);
			Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. Se recupera el tipo de tarea: " + tarea.getIdTipoTarea());

			Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. En el caso de que la tarea sea para gestionar un trabajo de interpretación, se obtiene si se requiere presupuesto: ");
			Expediente expediente = new Expediente();

			//Se obtiene el remitente
			final ConfigDireccionEmail configDireccionEmail = configDireccionEmailsSolver.find(baseSql);

			//Tratamos estos 2 casos los primeros ya que hay que comprobar a ver si hay que avisar a los asignados a tareas posteriores en este momento o no.
			if(tarea.getIdTipoTarea() == Long.valueOf(TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue())){
				//Gestión de una interpretación
				//recuperamos información del presupuesto para saber si se acaba de hacer visible el presupuesto esta pendiente de aceptar y hay que avisar al cliente.
				// O el cliente ya lo ha aceptado o no hay presupuesto que aceptar y hay que avisar a los asignados a las tareas posteriores.
				final List<Expediente> findPresupuestoExpInterpPorTarea = expedientesSolver.findPresupuestoExpInterpPorTarea(baseSql, idTarea);
				expediente = findPresupuestoExpInterpPorTarea.get(Constants.MAGIC_NUMBER0);
				if(Constants.IND_SI.equals(expediente.getIndPresupuesto())){
					envioMailsGeneral = false;
					dirCorreo = this.obtenerMailClienteReceptores(false);
					this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.REQ_PRESUPUESTO.getValue(), expediente, null);
				}
			}else if(tarea.getIdTipoTarea() == Long.valueOf(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue())){
				//Proyecto trados
				//recuperamos información del presupuesto para saber si se acaba de hacer visible el presupuesto y hay que avisar al cliente.
				// O el cliente ya lo ha aceptado y hay que avisar a los asignados a las tareas posteriores.
				final List<Expediente> listaExpedientes = expedientesSolver.findPresupuestoExpTradRevPorTarea(baseSql, idTarea);
				expediente = listaExpedientes.get(Constants.MAGIC_NUMBER0);

				final List<Tarea> listaTareasTrados = tareasSolver.findTareaTrados(baseSql, idTarea);
				final Tarea tareaTrados = listaTareasTrados.get(Constants.MAGIC_NUMBER0);

				boolean modifFechaEntrega = false;

				//comparamos si se ha modificado la fecha de entrega
				if (tareaTrados.getFechaEntrega() != null && expediente.getFechaEntrega() != null && (expediente.getFechaEntrega().getTime() != tareaTrados.getFechaEntrega().getTime())) {
					modifFechaEntrega = true;
				}

				if(Constants.IND_SI.equals(expediente.getIndPresupuesto()) || modifFechaEntrega ){
					envioMailsGeneral = false;
					//Listado de correos
					dirCorreo = this.obtenerMailClienteReceptores(false);
					int tipoAviso = Constants.MAGIC_NUMBER0;
					if(Constants.IND_SI.equals(expediente.getIndPresupuesto()) && modifFechaEntrega ){
						tipoAviso = TipoAvisoEnum.REQ_PRESUPUESTO_FECHA_ENTREGA.getValue();
					}else if(Constants.IND_SI.equals(expediente.getIndPresupuesto()) && !modifFechaEntrega){
						tipoAviso = TipoAvisoEnum.REQ_PRESUPUESTO.getValue();
					}else{
						tipoAviso = TipoAvisoEnum.REQ_FECHA_ENTREGA.getValue();
					}

					this.enviarMail(configDireccionEmail, dirCorreo, tipoAviso, expediente, null);
				}
			}



			if(envioMailsGeneral){
				Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. Obtener número de tareas configuradas con el mismo orden y ejecutadas.");
				Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. ENTORNO: " + EjecutarTarea.ENTORNO);
				final Long countTareasMismoOrdenEjecutadas = tareasSolver.findAllCountMismoOrden(baseSql, idTarea);


				if(countTareasMismoOrdenEjecutadas == Constants.MAGIC_NUMBER0){
					//Si todas las tareas configuradas con el mismo orden están ejecutadas se obtienen las tareas con un orden inmediatamente posterior
					Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. Obtener tareas con un orden inmediatamente posterior a la tarea ejecutada.");


					final List<Tarea> listaTareas = tareasSolver.findAllTareasPosteriores(baseSql, idTarea);

					//Listado de correos
					//Enviar un mail al recurso asociado
					for(final Tarea tareaItem : listaTareas){
						dirCorreo = this.obtenerDestinatarios(tareaItem);
						this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.FINALIZACION_TAREAS_DEPENDIENTES.getValue(), tareaItem, titulo);
					}
				}else{
					Logger.batchlog(Logger.INFO, "No existen registros para tratar");
				}
			}

			if(tarea.getIdTipoTarea() == Long.valueOf(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue())
					|| tarea.getIdTipoTarea() == Long.valueOf(TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue())){
				//Entrega a cliente (Traduccion) - Envio de mail al cliente y receptores autorizados

				//Listado de correos
				dirCorreo = this.obtenerMailClienteReceptores(true);
				List<Tarea> listaTarea = new ArrayList<Tarea>();
				listaTarea = tareasSolver.getInfoTarea(baseSql, idTarea);
				if(listaTarea != null && !listaTarea.isEmpty()){
					Tarea tareaInformada = listaTarea.get(Constants.MAGIC_NUMBER0);
					Logger.batchlog(Logger.INFO, "obtener informacion de tarea para descripcion OK: ");
					this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.ENTREGA_DOCUMENTOS_FINALES.getValue(), tareaInformada, titulo);
				}else{
					Logger.batchlog(Logger.ERROR, "No se ha podido enviar el correo - no se ha podido obtener la informacion de la tarea");
				}
			}else if(tarea.getIdTipoTarea() == Long.valueOf(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue())){
				String indAlbaran = tareasSolver.findRevisionPagoProveedores(baseSql, idTarea);
				if (Constants.IND_NO.equals(indAlbaran)) {
					Expediente expARevisar = new Expediente();
					expARevisar.setAnyo(tarea.getAnyo());
					expARevisar.setNumExp(tarea.getNumExp());
					expARevisar.setTitulo(titulo);
					//obtener destinatario
					dirCorreo = this.obtenerMailAsignadorExpediente(expARevisar);

					this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.REVISION_DATOS_PAGO.getValue(), expARevisar, null);
				}

				//Revision con entrega a cliente - Envio de mail al cliente y receptores autorizados
				//Listado de correos
				dirCorreo = this.obtenerMailClienteReceptores(true);
				List<Tarea> listaTarea = new ArrayList<Tarea>();
				listaTarea = tareasSolver.getInfoTarea(baseSql, idTarea);
				if(listaTarea != null && !listaTarea.isEmpty()){
					Tarea tareaInformada = listaTarea.get(Constants.MAGIC_NUMBER0);
					Logger.batchlog(Logger.INFO, "obtener informacion de tarea para descripcion OK: ");
					this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.ENTREGA_DOCUMENTOS_FINALES.getValue(), tareaInformada, titulo);
				}else{
					Logger.batchlog(Logger.ERROR, "No se ha podido enviar el correo - no se ha podido obtener la informacion de la tarea");
				}
			}else if(tarea.getIdTipoTarea() == Long.valueOf(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue())){
				//Gestionar una no conformidad por parte del cliente

				//Se comprueba la NoConformidad de la tarea relacionada
				final String indNoConformidadTareaRel = tareasSolver.findIndNoConformidadTareaRel(baseSql, idTarea);

				//Si se rechaza la no conformidad -> Mail al cliente
				if(Constants.IND_NO.equals(indNoConformidadTareaRel)){
					//Listado de correos
					dirCorreo = this.obtenerMailClienteReceptores(false);

					final String observEjec = tareasSolver.findObservacionesEjecucion(baseSql, idTarea);

					Expediente expNoConformidad = new Expediente();
					expNoConformidad.setAnyo(tarea.getAnyo());
					expNoConformidad.setNumExp(tarea.getNumExp());
					expNoConformidad.setTitulo(titulo);

					this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.NO_CONFORMIDAD_RECHAZADA.getValue(), expNoConformidad, observEjec);
				}

			}else if((Long.valueOf(TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()) == tarea.getIdTipoTarea()
					|| Long.valueOf(TipoTareaGestionAsociadaEnum.REVISAR.getValue()) == tarea.getIdTipoTarea()
							|| Long.valueOf(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue()) == tarea.getIdTipoTarea()
					|| Long.valueOf(TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue()) == tarea.getIdTipoTarea())
					&& TipoRecursoEnum.EXTERNO.getValue().equals(tarea.getRecursoAsignacion())){
				Logger.batchlog(Logger.INFO, "INICIO conformar email de tarea ejecutada  desde aa06a de tipo trad/rev/no conformidad prov - id tipo:"+ tarea.getIdTipoTarea());
				//ejecutar tarea traduccion/revision/no conformidad con el trabajo entregado por el proveedor
				Logger.batchlog(Logger.INFO, "obtener direccion de correo de asignador de tarea...");
					dirCorreo = this.obtenerEmailContacto(tarea.getDniAsignador());
					if(StringUtils.isNotBlank(dirCorreo)){
						Logger.batchlog(Logger.INFO, "obtener direccion de correo de asignador de tarea OK: " + dirCorreo);
						List<Tarea> listaTareaInfo = new ArrayList<Tarea>();
						Logger.batchlog(Logger.INFO, "obtener informacion de tarea...");
						listaTareaInfo = tareasSolver.getInfoTarea(baseSql, idTarea);
						Tarea tareaInfo = new Tarea();
						if(listaTareaInfo != null && !listaTareaInfo.isEmpty()){
							tareaInfo = listaTareaInfo.get(Constants.MAGIC_NUMBER0);
							Logger.batchlog(Logger.INFO, "obtener informacion de tarea OK: ");
							this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.FINALIZACION_TAREA_PROVEEDOR.getValue(), tareaInfo, titulo);
						}else{
							Logger.batchlog(Logger.ERROR, "No se ha podido enviar el correo - no se ha podido obtener la informacion de la tarea");
						}
					}else{
						Logger.batchlog(Logger.ERROR, "No se ha podido enviar el correo - no se ha podido obtener el email del destinatario");
					}

					Logger.batchlog(Logger.INFO, "FIN conformar email de tarea ejecutada  desde aa06a de tipo trad/rev/no conformidad prov - id tipo:"+ tarea.getIdTipoTarea());
			}

			Logger.batchlog(Logger.INFO, "INICIO EjecutarTarea. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "EjecutarTarea. Error.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			System.exit(rdoProcesoBatch);
		}

		return rdoProcesoBatch;
	}

	/**
	 *
	 * @param configDireccionEmail ConfigDireccionEmail
	 * @param dirCorreo String
	 * @param idTipoAviso int
	 * @param tarea Tarea
	 * @param titulo String
	 * @throws Exception
	 */
	private void enviarMail(ConfigDireccionEmail configDireccionEmail, String dirCorreo, int idTipoAviso, Tarea tarea,
			String titulo) throws Exception {

		// Se obtiene el mensaje.
		final ConfigTextoEmails configTextoEmails = configTextoEmailsSolver.findAviso(idTipoAviso, baseSql);

		//Enviar el mail
		Logger.batchlog(Logger.INFO, "Enviar el  mail");
		final EmailMessage message = new EmailMessage();

		message.setContentType("text/html");
		message.setSubject("");
		message.setBody("");

		if(configTextoEmails != null){

			message.setSubject(Utils.getNumExpedienteParameter(tarea) +configTextoEmails.getAsuntoEu().toUpperCase() + " / " + configTextoEmails.getAsuntoEs());

			ParametrosEmail parametrosEmail = new ParametrosEmail();

			parametrosEmail.setMensajeEu(configTextoEmails.getTextoMailEu());
			parametrosEmail.setMensajeEs(configTextoEmails.getTextoMailEs());

			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(MailTextConstants.TEXTO_TAREA_EU, tarea.getIdTarea() + Constants.ESPACIO + Constants.GUION + Constants.ESPACIO
					+ tarea.getTipoTarea().getDescEu() + Constants.ESPACIO + Constants.ABRIR_PARENTESIS + MailTextConstants.TEXTO_NUM_EXP_EU
					+ Constants.ESPACIO + tarea.getAnyoNumExpConcatenado() + Constants.ESPACIO + Constants.CERRAR_PARENTESIS );

			infoEs.put(MailTextConstants.TEXTO_TAREA_ES, tarea.getIdTarea() + Constants.ESPACIO + Constants.GUION + Constants.ESPACIO
					+ tarea.getTipoTarea().getDescEs() + Constants.ESPACIO + Constants.ABRIR_PARENTESIS + MailTextConstants.TEXTO_NUM_EXP_ES
					+ Constants.ESPACIO + tarea.getAnyoNumExpConcatenado() + Constants.ESPACIO + Constants.CERRAR_PARENTESIS );

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);

			message.setBody(Utils.getMessageEmail(parametrosEmail));

		}

		message.setFromAddress(configDireccionEmail.getDirEmail());
		message.setToAddress(dirCorreo);

		Logger.batchlog(Logger.ERROR, "Enviar el  mail a:" + dirCorreo);
		Logger.batchlog(Logger.ERROR, "tipo de aviso:" + idTipoAviso);
		Logger.batchlog(Logger.ERROR, "cuerpo:" + message.getBody());

		EmailService.getInstance().sendEmail(message);
		Logger.batchlog(Logger.INFO, "Mail enviado");



	}


	/**
	 *
	 * @param configDireccionEmail ConfigDireccionEmail
	 * @param dirCorreo String
	 * @param idTipoAviso int
	 * @throws Exception e
	 */
	private void enviarMail(ConfigDireccionEmail configDireccionEmail, String dirCorreo, int idTipoAviso, Expediente expediente, String observaciones) throws Exception{

		// Se obtiene el mensaje.
		final ConfigTextoEmails configTextoEmails = configTextoEmailsSolver.findAviso(idTipoAviso, baseSql);

		//Enviar el mail
		Logger.batchlog(Logger.INFO, "Enviar el  mail");
		final EmailMessage message = new EmailMessage();

		message.setContentType("text/html");
		message.setSubject("");
		message.setBody("");

		if(configTextoEmails != null){

			message.setSubject(configTextoEmails.getAsuntoEu().toUpperCase() + " / " + configTextoEmails.getAsuntoEs());

			ParametrosEmail parametrosEmail = new ParametrosEmail();

			parametrosEmail.setMensajeEu(configTextoEmails.getTextoMailEu());
			parametrosEmail.setMensajeEs(configTextoEmails.getTextoMailEs());

			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(MailTextConstants.TEXTO_NUM_EXP_EU, expediente.getAnyoNumExpConcatenado()
					+ Constants.ESPACIO + Constants.GUION + Constants.ESPACIO + expediente.getTitulo());
			infoEs.put(MailTextConstants.TEXTO_NUM_EXP_ES, expediente.getAnyoNumExpConcatenado()
					+ Constants.ESPACIO + Constants.GUION + Constants.ESPACIO + expediente.getTitulo());

			if (observaciones != null) {
				infoEu.put("", observaciones);
				infoEs.put("", observaciones);
			}

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);

			message.setSubject(Utils.getNumExpedienteParameter(expediente) + configTextoEmails.getAsuntoEu().toUpperCase() + " / " + configTextoEmails.getAsuntoEs());
			message.setBody(Utils.getMessageEmail(parametrosEmail));

		}

		message.setFromAddress(configDireccionEmail.getDirEmail());
		message.setToAddress(dirCorreo);

		//ponemos la traza como error para que se vea en producción
		Logger.batchlog(Logger.ERROR, "Enviar el  mail a:" + dirCorreo);
		Logger.batchlog(Logger.ERROR, "tipo de aviso:" + idTipoAviso);
		Logger.batchlog(Logger.ERROR, "cuerpo:" + message.getBody());

		EmailService.getInstance().sendEmail(message);
		Logger.batchlog(Logger.INFO, "Mail enviado");

	}

	/**
	 *
	 * @return String
	 * @throws NumberFormatException e
	 * @throws Exception e
	 */
	private String obtenerMailClienteReceptores(boolean conReceptores) throws NumberFormatException, Exception{
		String dirCorreo = "";
		Logger.batchlog(Logger.INFO, "Se obtiene el mail del cliente final");
		final List<GestorExpediente> gestorExpedientesPorTarea = gestorExpedientesSolver.getGestorExpedientesPorTarea(baseSql, idTarea);
		if(gestorExpedientesPorTarea != null
				&& !gestorExpedientesPorTarea.isEmpty()
				&& gestorExpedientesPorTarea.get(Constants.MAGIC_NUMBER0).getDatosContacto() != null
				&& StringUtils.isNotEmpty(gestorExpedientesPorTarea.get(Constants.MAGIC_NUMBER0).getDatosContacto().getEmail031())){

			if(StringUtils.isNotEmpty(dirCorreo)){
				dirCorreo += ";";
			}
			dirCorreo += gestorExpedientesPorTarea.get(Constants.MAGIC_NUMBER0).getDatosContacto().getEmail031();
			Logger.batchlog(Logger.INFO, "gestorExpedientesPorTarea devuelve datos gestor: "+dirCorreo);
		}
		if (conReceptores){
			Logger.batchlog(Logger.INFO, "Se obtienen los receptores autorizados");
			final List<DatosContacto> receptoresPorTarea = datosContactoSolver.getReceptoresPorTarea(baseSql, idTarea);
			for(final DatosContacto contacto : receptoresPorTarea){
				if(StringUtils.isNotEmpty(dirCorreo)){
					dirCorreo += ";";
				}
				dirCorreo += contacto.getEmail031();
			}
		}
		Logger.batchlog(Logger.INFO, "Se recuperan los siguientes mails " + dirCorreo);
		return dirCorreo;
	}


	/**
	 *
	 * @return String
	 * @throws NumberFormatException e
	 * @throws Exception e
	 */
	private String obtenerMailAsignadorExpediente(Expediente expediente) throws NumberFormatException, Exception{
		StringBuilder dirCorreo = new StringBuilder();
		Logger.batchlog(Logger.INFO, "Se obtiene el mail del asignador del expediente");
		final List<DatosContacto> asignadorExpediente = datosContactoSolver.getMailAsignadorExpediente(baseSql, expediente);
		for(final DatosContacto contacto : asignadorExpediente){
			if(StringUtils.isNotEmpty(dirCorreo)){
				dirCorreo.append(";");
			}
			dirCorreo.append(contacto.getEmail031());
		}
		Logger.batchlog(Logger.INFO, "Se recuperan los siguientes mails " + dirCorreo);
		return dirCorreo.toString();
	}

	/**
	 *
	 * @param args a
	 * @throws RuntimeException e
	 */
	private void recogerParametros(String[] args) throws RuntimeException{

		if (args == null) {
			throw new RuntimeException("EjecutarTarea - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("EjecutarTarea - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != EjecutarTarea.numeroParametros)
			{
				throw new RuntimeException("EjecutarTarea - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + EjecutarTarea.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "EjecutarTarea - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "EjecutarTarea - recogerParametros. args[1] dbPassword: ");
		dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "EjecutarTarea - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "EjecutarTarea - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "EjecutarTarea - recogerParametros. args[4] idTarea: "+args[Constants.MAGIC_NUMBER4]);
		idTarea = args[Constants.MAGIC_NUMBER4];
	}

	/**
	 * @param email
	 * @param tareas
	 * @return
	 * @throws Exception
	 */
	private String obtenerDestinatarios(Tarea tareas) throws Exception {
		String email = "";
		if (StringUtils.isNotBlank(tareas.getDniRecurso())) {
			// La tarea tiene asignado dni de recurso. El recurso de asignación
			// puede ser tanto interno como externo
			email = this.obtenerEmailContacto(tareas.getDniRecurso());
		} else if (TipoRecursoEnum.EXTERNO.getValue().equals(tareas.getRecursoAsignacion()) && tareas.getIdLote() != null) {
			// El recurso de asignación es un proveedor y la tarea tiene idLote
			// asignado
			final List<DatosContacto> contactosPorLotePorTarea = datosContactoSolver.getContactosPorLotePorTarea(baseSql, tareas.getIdTarea());
			for(final DatosContacto contacto: contactosPorLotePorTarea){
				if(StringUtils.isNotEmpty(email)){
					email += ";";
				}
				email += contacto.getEmail031();
			}
		}
		return email;
	}

	/**
	 * @param dni
	 * @return
	 * @throws Exception e
	 */
	private String obtenerEmailContacto(String dni) throws Exception {
		String mail = "";
		final List<DatosContacto> contacto = datosContactoSolver.getMailRecurso(baseSql, dni);
		if(contacto != null
				&& !contacto.isEmpty()
				&& StringUtils.isNotBlank(contacto.get(Constants.MAGIC_NUMBER0).getEmail031())){
			mail = contacto.get(Constants.MAGIC_NUMBER0).getEmail031();
		}
		return mail;
	}
}
