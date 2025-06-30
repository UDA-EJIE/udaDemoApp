package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.ConfigDireccionEmailsSolver;
import aa79b.bbdd.solvers.ConfigTextoEmailsSolver;
import aa79b.bbdd.solvers.ExpedientesSolver;
import aa79b.util.beans.ConfigDireccionEmail;
import aa79b.util.beans.ConfigTextoEmails;
import aa79b.util.beans.Expediente;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.common.MailTextConstants;
import aa79b.util.common.TipoAvisoEnum;
import aa79b.util.common.Utils;
import aa79b.util.mail.EmailMessage;
import aa79b.util.mail.EmailService;
import aa79b.util.mail.ParametrosEmail;

/**
 *
 * @author aresua
 *
 */
public class AvisoExpiracionPlazos {

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
	 * ConfigTextoEmailsSolver: configTextoEmailsSolver.
	 */
	private ConfigTextoEmailsSolver configTextoEmailsSolver = null;
	/**
	 * ConfigDireccionEmailsSolver: configDireccionEmailsSolver.
	 */
	private ConfigDireccionEmailsSolver configDireccionEmailsSolver = null;
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO AvisoExpiracionPlazos. main. ***********************************");

		final AvisoExpiracionPlazos avisoExpiracionPlazos = new AvisoExpiracionPlazos();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = avisoExpiracionPlazos.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN AvisoExpiracionPlazos. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN AvisoExpiracionPlazos. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "AvisoExpiracionPlazos. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "AvisoExpiracionPlazos - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.expedientesSolver = new ExpedientesSolver();
			this.configTextoEmailsSolver = new ConfigTextoEmailsSolver();
			this.configDireccionEmailsSolver = new ConfigDireccionEmailsSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AvisoExpiracionPlazos. main. inicializar", e);
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
			Logger.batchlog(Logger.ERROR, "AvisoExpiracionPlazos. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO AvisoExpiracionPlazos. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO AvisoExpiracionPlazos. Obtener expedientes en estado 'En estudio' y en fase 'pendiente tramitación por parte del cliente' sin subsanación.");
			Logger.batchlog(Logger.INFO, "INICIO AvisoExpiracionPlazos. ENTORNO: " + AvisoExpiracionPlazos.ENTORNO);
			final List<Expediente> listaExpedientes = this.expedientesSolver.findAvisoPlazoExpiracion(this.baseSql);
			if(listaExpedientes!=null && !listaExpedientes.isEmpty()){
				//Recoge parámetros para enviar el mail
				Logger.batchlog(Logger.INFO, "Recoger el texto del envío de mail");

				//Se obtiene el remitente
				final ConfigDireccionEmail configDireccionEmail = this.configDireccionEmailsSolver.find(this.baseSql);

				for(final Expediente expedientes:listaExpedientes){
					String dirCorreo = expedientes.getGestorExpediente().getDatosContacto().getEmail031();
					this.enviarMail(configDireccionEmail, dirCorreo, TipoAvisoEnum.EXP_PLAZO_REQ.getValue(), expedientes);
				}
			}else{
				Logger.batchlog(Logger.INFO, "No existen registros para tratar");
			}
			Logger.batchlog(Logger.INFO, "INICIO AvisoExpiracionPlazos. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "AvisoExpiracionPlazos. Error.", e);
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
		 * @throws Exception e
		 */
		private void enviarMail(ConfigDireccionEmail configDireccionEmail, String dirCorreo, int idTipoAviso, Expediente expediente) throws Exception{
			// Se obtiene el mensaje.
						final ConfigTextoEmails configTextoEmails = this.configTextoEmailsSolver.findAviso(idTipoAviso, this.baseSql);

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
						message.setToAddress(dirCorreo);

						Logger.batchlog(Logger.INFO, "Enviar el  mail a:" + dirCorreo);

						EmailService.getInstance().sendEmail(message);
						Logger.batchlog(Logger.INFO, "Mail enviado");
		}




	/**
	 *
	 * @param args a
	 * @throws RuntimeException e
	 */
	private void recogerParametros(String[] args) throws RuntimeException{

		if (args == null) {
			throw new RuntimeException("AvisoExpiracionPlazos - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("AvisoExpiracionPlazos - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != AvisoExpiracionPlazos.numeroParametros)
			{
				throw new RuntimeException("AvisoExpiracionPlazos - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + AvisoExpiracionPlazos.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "AvisoExpiracionPlazos - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "AvisoExpiracionPlazos - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "AvisoExpiracionPlazos - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "AvisoExpiracionPlazos - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
	}

}
