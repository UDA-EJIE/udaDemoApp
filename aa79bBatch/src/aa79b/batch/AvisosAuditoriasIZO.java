package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.AvisosAuditoriasSolver;
import aa79b.bbdd.solvers.ConfigDireccionEmailsSolver;
import aa79b.bbdd.solvers.ConfigTextoEmailsSolver;
import aa79b.bbdd.solvers.DatosContactoSolver;
import aa79b.util.beans.ConfigDireccionEmail;
import aa79b.util.beans.ConfigTextoEmails;
import aa79b.util.beans.DatosContacto;
import aa79b.util.beans.TareaAuditoria;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.common.MailTextConstants;
import aa79b.util.common.PropertiesConstants;
import aa79b.util.common.TipoAvisoEnum;
import aa79b.util.common.Utils;
import aa79b.util.mail.EmailMessage;
import aa79b.util.mail.EmailService;
import aa79b.util.mail.ParametrosEmail;

/**
 *
 * @author eaguirresarobe
 *
 */
public class AvisosAuditoriasIZO {

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
	 * baseSql: BaseSql.
	 */
	private BaseSql baseSql = null;
	/**
	 * avisosAuditoriasSolver: AvisosAuditoriasSolver
	 */
	private AvisosAuditoriasSolver avisosAuditoriasSolver = null;
	/**
	 * ConfigDireccionEmailsSolver: configDireccionEmailsSolver.
	 */
	private ConfigDireccionEmailsSolver configDireccionEmailsSolver = null;
	/**
	 * ConfigTextoEmailsSolver: configTextoEmailsSolver.
	 */
	private ConfigTextoEmailsSolver configTextoEmailsSolver = null;
	/**
	 * ConfigTextoEmailsSolver: configTextoEmailsSolver.
	 */
	private DatosContactoSolver datosContactoSolver = null;
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO AvisosAuditorias. main. ***********************************");

		final AvisosAuditoriasIZO avisosAuditorias = new AvisosAuditoriasIZO();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = avisosAuditorias.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN AvisosAuditorias. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN AvisosAuditorias. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
		System.exit(rdoProcesoBatch);
	}

	/**
	 *
	 * @param args String[]
	 * @return int
	 */
	protected int doMain(String[] args) {

		int rdoProcesoBatch = Constants.MAGIC_NUMBER0;
		try {
			this.recogerParametros(args);
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AvisosAuditorias. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "AvisosAuditorias - MAIN. ");

		// inicializar objetos de conexion
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.avisosAuditoriasSolver = new AvisosAuditoriasSolver();
			this.configDireccionEmailsSolver = new ConfigDireccionEmailsSolver();
			this.configTextoEmailsSolver = new ConfigTextoEmailsSolver();
			this.datosContactoSolver = new DatosContactoSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AvisosAuditorias. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = AvisoExpiracionPlazos.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "AvisosAuditorias. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO AvisosAuditorias. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO AvisosAuditorias. ENTORNO: " + AvisosAuditoriasIZO.ENTORNO);
			Long numMaxDiasAvisoAuditoria = this.avisosAuditoriasSolver.findNumMaxDiasAvisoAuditoria(this.baseSql);
			if (numMaxDiasAvisoAuditoria == null) {
				Logger.batchlog(Logger.ERROR, "AvisosAuditorias. Campo de tabla AA79B00T00 con id " + PropertiesConstants.DIAS_PLAZO_AVISO_AUDITORIA + " no informado!!!!!");
				Logger.batchlog(Logger.INFO, "AvisosAuditorias. No se sigue con el proceso");
				rdoProcesoBatch = Constants.MAGIC_NUMBER3;
				System.exit(rdoProcesoBatch);
			}
			Logger.batchlog(Logger.INFO, "INICIO AvisosAuditorias. DIAS PLAZO AVISO AUDITORIA: " + numMaxDiasAvisoAuditoria);
			//Se obtiene el remitente
			final ConfigDireccionEmail configDireccionEmail = this.configDireccionEmailsSolver.find(this.baseSql);
			Logger.batchlog(Logger.INFO, "AvisosAuditorias. REMITENTE: " +  (configDireccionEmail.getDirEmail()));


			Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - AUDITORIAS PENDIENTES DE CONFIRMAR - INICIO***********************************");
			// PARTE 1
			// avisar al IZO de las auditorias pendiente de confirmar que tienen los proveedores
			// Se buscaran las tareas a auditar de traduccion o revision asignadas al lote y que tengan auditoria enviada hace cinco dias y
			// que aun no se hayan confirmado
			final List<TareaAuditoria> lTareaAuditoriaSinConfirmar = this.avisosAuditoriasSolver.findAuditoriasSinConfirmar(this.baseSql, numMaxDiasAvisoAuditoria);
			Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - TAREAS SIN CONFIRMAR: " +  (lTareaAuditoriaSinConfirmar != null ? lTareaAuditoriaSinConfirmar.size() : 0));
			Map<String, List<TareaAuditoria>> mContactoTarea = new HashMap<String, List<TareaAuditoria>>();
			Map<String, String> mIZOEmail = new HashMap<String, String>();
			for (TareaAuditoria tareaAuditoria: lTareaAuditoriaSinConfirmar) {

				// Por cada lote se enviara un email (por lo que a cada recurso se le mandara un unico email por lote
				// mostrando una lista de expedientes pendientes de confirmar). El tipo de aviso a enviar se correspondera
				// con el id=34, definido nuevo en esta version


				Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - LOTES A ENVIAR EMAIL - INICIO ");
				if (mContactoTarea.containsKey(tareaAuditoria.getAuditor().getDni())) {
					// idlote ya esta en el mapa, anyadimos tarea a su lista de tareas
					mContactoTarea.get(tareaAuditoria.getAuditor().getDni()).add(tareaAuditoria);
				} else {
					// anyadimos al mapa el idlote y la tarea a su lista de tareas
					List<TareaAuditoria> lTareas = new ArrayList<TareaAuditoria>();
					lTareas.add(tareaAuditoria);
					mContactoTarea.put(tareaAuditoria.getAuditor().getDni(), lTareas);
					//añadimos el email asociado al usuario del IZO
					List<DatosContacto> dirCorreo = this.datosContactoSolver.getMailRecurso(this.baseSql, tareaAuditoria.getAuditor().getDni());
					mIZOEmail.put(tareaAuditoria.getAuditor().getDni(), dirCorreo.get(Constants.MAGIC_NUMBER0).getEmail031());
					Logger.batchlog(Logger.INFO, "dni:  " + tareaAuditoria.getAuditor().getDni() + " - email(s): " + dirCorreo.get(Constants.MAGIC_NUMBER0).getEmail031());
				}
			}
			Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - LOTES A ENVIAR EMAIL - FIN ");
			// Se obtiene el mensaje.
			final ConfigTextoEmails configTextoEmails = this.configTextoEmailsSolver.findAviso(TipoAvisoEnum.AUDITORIAS_PENDIENTES_DE_CONFIRMAR.getValue(), this.baseSql);
			if(configTextoEmails != null){
				Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - existen textos configurados para tipo de aviso " + TipoAvisoEnum.AUDITORIAS_PENDIENTES_DE_CONFIRMAR.getValue() + " --> seguimos" );
				// por cada lote enviamos un email
				for (String dni : mContactoTarea.keySet()) {
					Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - ENVIAR EMAIL A: " + dni  + " INICIO**");
					// comprobamos que haya emails asociados al lote
					if(mIZOEmail != null && mIZOEmail.containsKey(dni) && !mIZOEmail.get(dni).isEmpty()) {
						Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - direccion(es) email asociada(s) a: " + dni  + " -- email(s): " + mIZOEmail.get(dni));
						// por cada usuario de IZO se envia un email con datos de expediente y tarea a confirmar a las direcciones de email que tenga
						final EmailMessage message = new EmailMessage();
						message.setContentType("text/html");
						message.setSubject("");
						message.setBody("");

						ParametrosEmail parametrosEmail = new ParametrosEmail();

						// texto del tipo de aviso del email
						parametrosEmail.setMensajeEu(configTextoEmails.getTextoMailEu());
						parametrosEmail.setMensajeEs(configTextoEmails.getTextoMailEs());


						Map<String, String> infoEu = new LinkedHashMap<String, String>();
						Map<String, String> infoEs = new LinkedHashMap<String, String>();

						infoEu.put(MailTextConstants.TEXTO_TAREA_EU, "");
						infoEs.put(MailTextConstants.TEXTO_TAREA_ES, "");

						StringBuilder descTareasEs = new StringBuilder();
						StringBuilder descTareasEu = new StringBuilder();

						int cont = 0;


						// el body del email con los numeros de expediente y las tareas
						Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - expedientes-tareas asociadas a: " + dni  + " INICIO");
						for (int i = 0; i < mContactoTarea.get(dni).size(); i++) {
							TareaAuditoria tarea = mContactoTarea.get(dni).get(i);

							if (cont != 0) {
								descTareasEu.append(Constants.SALTO_DE_LINEA);
								descTareasEs.append(Constants.SALTO_DE_LINEA);
							}

							descTareasEu.append(MailTextConstants.TEXTO_TAREA_EU + Constants.ESPACIO+  tarea.getIdTarea() + Constants.ESPACIO + Constants.GUION + Constants.ESPACIO
									+ tarea.getTipoTarea().getDescEu() + Constants.ESPACIO + Constants.ABRIR_PARENTESIS + MailTextConstants.TEXTO_NUM_EXP_EU
									+ Constants.ESPACIO + tarea.getAnyoNumExpConcatenado() + Constants.COMA + Constants.ESPACIO
									+ MailTextConstants.TEXTO_LOTE_EU + Constants.ESPACIO + tarea.getLote().getDescLoteEu() + Constants.CERRAR_PARENTESIS);

							descTareasEs.append(MailTextConstants.TEXTO_TAREA_ES + Constants.ESPACIO+ tarea.getIdTarea() + Constants.ESPACIO + Constants.GUION + Constants.ESPACIO
									+ tarea.getTipoTarea().getDescEs() + Constants.ESPACIO + Constants.ABRIR_PARENTESIS + MailTextConstants.TEXTO_NUM_EXP_ES
									+ Constants.ESPACIO + tarea.getAnyoNumExpConcatenado() + Constants.COMA + Constants.ESPACIO
									+ MailTextConstants.TEXTO_LOTE_ES + Constants.ESPACIO + tarea.getLote().getDescLoteEu() + Constants.CERRAR_PARENTESIS);

							cont++;




							Logger.batchlog(Logger.INFO, " - anyoNumExp: " + tarea.getAnyoNumExpConcatenado() + " - idTarea: " + tarea.getIdTarea());
						}
						Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - expedientes-tareas asociadas a: " + dni  + " FIN");

						infoEu.put("", descTareasEu.toString());
						infoEs.put("", descTareasEs.toString());

						parametrosEmail.setInfoEu(infoEu);
						parametrosEmail.setInfoEs(infoEs);
						// asunto del tipo de aviso
						message.setSubject(configTextoEmails.getAsuntoEu().toUpperCase() + " / " + configTextoEmails.getAsuntoEs());
						message.setBody(Utils.getMessageEmail(parametrosEmail));
						message.setFromAddress(configDireccionEmail.getDirEmail());

						message.setToAddress(mIZOEmail.get(dni));

						Logger.batchlog(Logger.ERROR, "Enviar el  mail a:" + mIZOEmail.get(dni));
						Logger.batchlog(Logger.ERROR, "cuerpo:" + message.getBody());
						// enviar email
						EmailService.getInstance().sendEmail(message);

						Logger.batchlog(Logger.INFO, "Mail enviado");
					} else {
						Logger.batchlog(Logger.ERROR,
								"AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - NO HAY EMAIL ASOCIADO A: " + dni );
						Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - No se enviara email a : " + dni);
					}
					Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - ENVIAR EMAIL A DIRECCION(ES) ASOCIADA(S) DE: " + dni  + " FIN**");
				}
			} else {
				Logger.batchlog(Logger.ERROR,
						"AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar al IZO - NO HAY TEXTO CONFIGURADO PARA EL ID: " + TipoAvisoEnum.AUDITORIAS_PENDIENTES_DE_CONFIRMAR.getValue() + " EN LA TABLA AA79B06S01");
				Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - auditorias pendientes de confirmar - No se enviaran emails de auditorias pendientes de confirmar");
			}

			Logger.batchlog(Logger.INFO, "AvisosAuditorias. PARTE 1 - AUDITORIAS PENDIENTES DE CONFIRMAR - FIN ***********************************");
			Logger.batchlog(Logger.INFO, "AvisosAuditorias. FIN DEL PROCESO");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AvisosAuditorias. Error.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			System.exit(rdoProcesoBatch);
		}

		return rdoProcesoBatch;
	}


	/**
	 *
	 * @param args String[]
	 */
	private void recogerParametros(String[] args) {
		if (args == null) {
			throw new RuntimeException("AvisosAuditorias - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("AvisosAuditorias - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != AvisosAuditoriasIZO.numeroParametros)
			{
				throw new RuntimeException("AvisosAuditorias - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + AvisosAuditoriasIZO.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "AvisosAuditorias - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "AvisosAuditorias - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "AvisosAuditorias - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "AvisosAuditorias - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];

	}
}
