package aa79b.batch;

import java.util.Calendar;
import java.util.Properties;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.CrearCalendarioAnyoSigSolver;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.mail.EmailService;

/**
 *
 * @author eaguirresarobe
 *
 */
public class CrearCalendarioAnyoSig {

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
	 * crearCalendarioAnyoSigSolver: CrearCalendarioAnyoSigSolver.
	 */
	private CrearCalendarioAnyoSigSolver crearCalendarioAnyoSigSolver = null;
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO CrearCalendarioAnyoSig. main. ***********************************");

		final CrearCalendarioAnyoSig crearCalendarioAnyoSig = new CrearCalendarioAnyoSig();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = crearCalendarioAnyoSig.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN CrearCalendarioAnyoSig. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN CrearCalendarioAnyoSig. main. RESULTADO PROCESO BATCH FICHA A AA79B:"+ rdoProcesoBatch +" ***********************************");
		System.exit(rdoProcesoBatch);
	}

	/**
	 *
	 * @param args String[]
	 * @return int
	 */
	private int doMain(String[] args) {

		int rdoProcesoBatch = Constants.MAGIC_NUMBER0;
		try {
			this.recogerParametros(args);
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "CrearCalendarioAnyoSig. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig - MAIN. ");

		// inicializar objetos de conexion
		try {
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.crearCalendarioAnyoSigSolver = new CrearCalendarioAnyoSigSolver();
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "CrearCalendarioAnyoSig. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO CrearCalendarioAnyoSig. Comienza el proceso");
			Logger.batchlog(Logger.INFO, "INICIO CrearCalendarioAnyoSig. obtenemos el anyo siguiente");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 1);
			int nextYear = cal.get(Calendar.YEAR);
			Logger.batchlog(Logger.INFO, "INICIO CrearCalendarioAnyoSig. anyo siguiente: " + nextYear);
			Long nextYearCalendarCount = this.crearCalendarioAnyoSigSolver.findNextYearCount(this.baseSql, nextYear);
			if (nextYearCalendarCount == 0) {
				Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig. - NO HAY CALENDARIO - lo creamos:");
				Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig. - llamamos a PL CARGAR_CALENDARIO_SERVICIO - INICIO");
				this.crearCalendarioAnyoSigSolver.crearCalendario(this.baseSql, nextYear);
				Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig. - llamamos a PL CARGAR_CALENDARIO_SERVICIO - FINAL");
				Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig. - calendario creado para el anyo: " + nextYear);
			} else {
				Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig. YA HAY CALENDARIO CREADO PARA EL ANYO: " + nextYear);
			}
		} catch (Exception e) {
			Logger.batchlog(Logger.ERROR, "CrearCalendarioAnyoSig. Error.", e);
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
			throw new RuntimeException("CrearCalendarioAnyoSig - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0)
			{
				throw new RuntimeException("CrearCalendarioAnyoSig - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != CrearCalendarioAnyoSig.numeroParametros)
			{
				throw new RuntimeException("CrearCalendarioAnyoSig - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + CrearCalendarioAnyoSig.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "CrearCalendarioAnyoSig - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];

	}

}
