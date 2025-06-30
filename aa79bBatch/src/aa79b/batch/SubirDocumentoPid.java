package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.DocumentosExpedienteSolver;
import aa79b.util.beans.DocumentosExpediente;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.pid.PidService;

/**
 *
 * @author aresua
 *
 */
public class SubirDocumentoPid {

	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long NUMERO_PARAMS = 5;

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
	private String idDoc = null;
	/**
	 * DocumentosExpedienteSolver: documentosExpedienteSolver.
	 */
	private DocumentosExpedienteSolver documentosExpedienteSolver = null;
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. main. ***********************************");

		final SubirDocumentoPid subirDocumentoPid = new SubirDocumentoPid();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = subirDocumentoPid.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN SubirDocumentoPid. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN SubirDocumentoPid. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
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
			this.documentosExpedienteSolver = new DocumentosExpedienteSolver();
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirDocumentoPid. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "SubirDocumentoPid - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirDocumentoPid. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = SubirDocumentoPid.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "SubirDocumentoPid. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. Se recupera el documento del expediente: ");
			final List<DocumentosExpediente> list = this.documentosExpedienteSolver.find(this.baseSql, this.idDoc);
			if(list != null
					&& !list.isEmpty()){
				final DocumentosExpediente documentosExpediente = list.get(Constants.MAGIC_NUMBER0);
				if(StringUtils.isNotEmpty(documentosExpediente.getOid())){
					//Se debe realizar una modificación del documento en el PID
					Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. MODIFICACIÓN. Se realiza una modificación del archivo en el PID: ");
					PidService.getInstance().modifyDocument(documentosExpediente.getNombre(), documentosExpediente.getRutaPif(), documentosExpediente.getOid());
					Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. Se borra la ruta del PIF de la BD: ");
					this.documentosExpedienteSolver.deleteRutaPif(baseSql, documentosExpediente);
				}else{
					Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. NUEVO DOCUMENTO. Se realiza la subida al PID con la ruta del pif y el nombre del documento: ");
					final String oid = PidService.getInstance().addDocument(documentosExpediente.getNombre(), documentosExpediente.getRutaPif());
					Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. Se guarda en el documento del expediente el oid: ");
					documentosExpediente.setOid(oid);
					this.documentosExpedienteSolver.updateOid(baseSql, documentosExpediente);
				}
			}else{
				Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. El iddocumento no se corresponde con ningún documento.");
			}
			Logger.batchlog(Logger.INFO, "INICIO SubirDocumentoPid. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirDocumentoPid. Error.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			System.exit(rdoProcesoBatch);
		}

		return rdoProcesoBatch;
	}

	/**
	 *
	 * @param args a
	 * @throws RuntimeException e
	 */
	private void recogerParametros(String[] args) throws RuntimeException{

		if (args == null) {
			throw new RuntimeException("SubirDocumentoPid - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("SubirDocumentoPid - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != SubirDocumentoPid.NUMERO_PARAMS)
			{
				throw new RuntimeException("SubirDocumentoPid - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + SubirDocumentoPid.NUMERO_PARAMS + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "SubirDocumentoPid - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "SubirDocumentoPid - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "SubirDocumentoPid - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "SubirDocumentoPid - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "SubirDocumentoPid - recogerParametros. args[4] idDoc: "+args[Constants.MAGIC_NUMBER4]);
		this.idDoc = args[Constants.MAGIC_NUMBER4];
	}

}
