package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.ExpedientesRelacionadosSolver;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;

/**
 *
 * @author aresua
 *
 */
public class ActualizarExpRelacionados {

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
	private ExpedientesRelacionadosSolver expedientesRelacionadosSolver = null;

	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO ActualizarExpRelacionados. main. ***********************************");

		final ActualizarExpRelacionados actualizarExpRelacionados = new ActualizarExpRelacionados();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = actualizarExpRelacionados.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN ActualizarExpRelacionados. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN ActualizarExpRelacionados. main. RESULTADO PROCESO BATCH :"+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "ActualizarExpRelacionados. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "ActualizarExpRelacionados - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.expedientesRelacionadosSolver = new ExpedientesRelacionadosSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "ActualizarExpRelacionados. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = ActualizarExpRelacionados.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "ActualizarExpRelacionados. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO ActualizarExpRelacionados. Comienza el proceso");
			this.expedientesRelacionadosSolver.addRelaciones(this.baseSql);
			Logger.batchlog(Logger.INFO, "INICIO ActualizarExpRelacionados. Fin del proceso");
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "ActualizarExpRelacionados. Error.", e);
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
			throw new RuntimeException("ActualizarExpRelacionados - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("ActualizarExpRelacionados - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != ActualizarExpRelacionados.numeroParametros)
			{
				throw new RuntimeException("ActualizarExpRelacionados - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + ActualizarExpRelacionados.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "ActualizarExpRelacionados - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "ActualizarExpRelacionados - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "ActualizarExpRelacionados - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "ActualizarExpRelacionados - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
	}

}
