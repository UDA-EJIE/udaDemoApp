package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.DocumentosExpedienteSolver;
import aa79b.util.beans.DocumentosExpediente;
import aa79b.util.beans.Expediente;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.pid.PidService;

/**
 *
 * @author aresua
 *
 */
public class SubirDocumentosAa06aPid {

	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long NUMERO_PARAMS = 6;

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
	 * this.anyo: String.
	 */
	private String anyo = null;
	/**
	 * this.numExp: String.
	 */
	private String numExp = null;
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

		Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. main. ***********************************");

		final SubirDocumentosAa06aPid subirDocumentosAa06aPid = new SubirDocumentosAa06aPid();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = subirDocumentosAa06aPid.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN SubirDocumentosAa06aPid. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN SubirDocumentosAa06aPid. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "SubirDocumentosAa06aPid. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirDocumentosAa06aPid. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = SubirDocumentosAa06aPid.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "SubirDocumentosAa06aPid. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. Se recupera el documento del expediente: ");
			final Expediente exp = new Expediente(Long.valueOf(this.anyo), Integer.valueOf(this.numExp));
			final List<DocumentosExpediente> list = this.documentosExpedienteSolver.find(this.baseSql, exp);
			if(list != null
					&& !list.isEmpty()){
				for(final DocumentosExpediente doc : list){
					if(StringUtils.isNotEmpty(doc.getOid()) && StringUtils.isNotEmpty(doc.getRutaPif())){
						Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. MODIFICACIÓN. Se realiza la subida al PID con la ruta del pif y el nombre del documento: ");
						PidService.getInstance().modifyDocument(doc.getNombre(), doc.getRutaPif(), doc.getOid());
						Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. Se elimina la ruta del PIF de la 56: ");
						this.documentosExpedienteSolver.deleteRutaPif(baseSql, doc);
					}else if(StringUtils.isEmpty(doc.getOid()) && StringUtils.isNotEmpty(doc.getRutaPif())){
						Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. ALTA. Se realiza la subida al PID con la ruta del pif y el nombre del documento: ");
						final String oid = PidService.getInstance().addDocument(doc.getNombre(), doc.getRutaPif());
						Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. Se guarda en el documento del expediente (TABLA 56) el oid: ");
						doc.setOid(oid);
						this.documentosExpedienteSolver.updateOid(baseSql, doc);
						Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. Se guarda en el documento del expediente (TABLA 73) el oid: ");
						this.documentosExpedienteSolver.updateOid73(baseSql, doc);
					}
				}
			}else{
				Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. El iddocumento no se corresponde con ningún documento.");
			}
			Logger.batchlog(Logger.INFO, "INICIO SubirDocumentosAa06aPid. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirDocumentosAa06aPid. Error.", e);
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
			throw new RuntimeException("SubirDocumentosAa06aPid - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("SubirDocumentosAa06aPid - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != SubirDocumentosAa06aPid.NUMERO_PARAMS)
			{
				throw new RuntimeException("SubirDocumentosAa06aPid - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + SubirDocumentosAa06aPid.NUMERO_PARAMS + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - recogerParametros. args[4] anyo: "+args[Constants.MAGIC_NUMBER4]);
		this.anyo = args[Constants.MAGIC_NUMBER4];
		Logger.batchlog(Logger.INFO, "SubirDocumentosAa06aPid - recogerParametros. args[5] numExp: "+args[Constants.MAGIC_NUMBER5]);
		this.numExp = args[Constants.MAGIC_NUMBER5];
	}

}
