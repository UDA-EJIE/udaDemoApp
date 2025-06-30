package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.DocumentosAuxiliarSolver;
import aa79b.util.beans.DocumentosExpediente;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.pid.PidService;

/**
 *
 * @author aresua
 *
 */
public class BorrarOidsAuxiliares {

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
	 * DocumentosAuxiliarSolver: documentosAuxiliarSolver.
	 */
	private DocumentosAuxiliarSolver documentosAuxiliarSolver = null;

	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO BorrarOidsAuxiliares. main. ***********************************");

		final BorrarOidsAuxiliares borrarOidsAuxiliares = new BorrarOidsAuxiliares();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = borrarOidsAuxiliares.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN BorrarOidsAuxiliares. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN BorrarOidsAuxiliares. main. RESULTADO PROCESO BATCH FICHA A K81B:"+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "BorrarOidsAuxiliares. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "BorrarOidsAuxiliares - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.documentosAuxiliarSolver = new DocumentosAuxiliarSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "BorrarOidsAuxiliares. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = BorrarOidsAuxiliares.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "BorrarOidsAuxiliares. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO BorrarOidsAuxiliares. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO BorrarOidsAuxiliares. Obtener lista de oids a borrar.");
			final List<DocumentosExpediente> findABorrar = this.documentosAuxiliarSolver.findABorrar(this.baseSql);
			if(findABorrar != null && !findABorrar.isEmpty()){
				Logger.batchlog(Logger.INFO, "Se borran los oids desfasados");
				List<DocumentosExpediente> listaBorrados = new ArrayList<DocumentosExpediente>();
				for(final DocumentosExpediente doc: findABorrar){
					if (PidService.getInstance().deleteDocument(doc.getOid())) {
						listaBorrados.add(doc);
					}
				}
				Logger.batchlog(Logger.INFO, "OIDs borrados del PID");
				Logger.batchlog(Logger.INFO, "Se borran los oids desfasados de BBDD");
				this.documentosAuxiliarSolver.removeLista(this.baseSql, listaBorrados);

				Logger.batchlog(Logger.INFO, "Número de oids borrados: " + listaBorrados.size());
			}else{
				Logger.batchlog(Logger.INFO, "No existen registros para tratar");
			}
			Logger.batchlog(Logger.INFO, "INICIO BorrarOidsAuxiliares. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "BorrarOidsAuxiliares. Error.", e);
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
			throw new RuntimeException("BorrarOidsAuxiliares - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("BorrarOidsAuxiliares - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != BorrarOidsAuxiliares.numeroParametros)
			{
				throw new RuntimeException("BorrarOidsAuxiliares - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + BorrarOidsAuxiliares.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "BorrarOidsAuxiliares - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "BorrarOidsAuxiliares - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "BorrarOidsAuxiliares - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "BorrarOidsAuxiliares - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
	}

}
