package aa79b.batch;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.DocumentoTareaSolver;
import aa79b.util.beans.DocumentoTareaAdjunto;
import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.pid.PidService;

/**
 *
 * @author javarona
 *
 */
public class DescargaPIDaPIF {

	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 8;

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
	 * this.idTarea: String.
	 */
	private String idTarea = null;
	
	/**
	 * this.idDocOriginal: String.
	 */
	private String idDocOriginal = null;
	/**
	 * this.nombreFicheroRutaPif: String.
	 */
	private String nombreFicheroRutaPif = null;
	/**
	 * this.dirFilesTmp: String.
	 */
	private String dirFilesTmp = null;
	/**  
	 * DocumentoTareaSolver: documentoTareaSolver.
	 */
	private DocumentoTareaSolver documentoTareaSolver = null;

	
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO DescargaPIDaPIF. main. ***********************************");

		final DescargaPIDaPIF descargaPIDaPIF = new DescargaPIDaPIF();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = descargaPIDaPIF.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN DescargaPIDaPIF. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN DescargaPIDaPIF. main. RESULTADO PROCESO BATCH Descargar doc del pid al pif: "+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "DescargaPIDaPIF. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.documentoTareaSolver = new DocumentoTareaSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "DescargaPIDaPIF. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = DescargaPIDaPIF.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "DescargaPIDaPIF. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		DocumentoTareaAdjunto documentoFinal = null;
		try {
			Logger.batchlog(Logger.INFO, "INICIO DescargaPIDaPIF. Comienza el proceso");
			
			//Recupero el objeto con los datos, idDocFinal y sus datos en la T88
			final List<DocumentoTareaAdjunto> lista = this.documentoTareaSolver.leerInfoT92(this.baseSql, Long.valueOf(this.idTarea), Long.valueOf(this.idDocOriginal));
			documentoFinal = lista.get(Constants.MAGIC_NUMBER0);
			
			String rutaPifOriginalFinal = PidService.getInstance().getRutaPIF(documentoFinal.getOidOriginalFinal());
			Logger.batchlog(Logger.INFO, "BAJADO FICH Original Final con oid: "+documentoFinal.getOidOriginalFinal() +" AL PIF a la ruta  "+rutaPifOriginalFinal);	
			
			String rutaPifFinal = PidService.getInstance().getRutaPIF(documentoFinal.getOidFinal());
			Logger.batchlog(Logger.INFO, "BAJADO FICH FINAL con oid: "+documentoFinal.getOidFinal()+" AL PIF a la ruta  "+rutaPifFinal);		
			
			//Control de errores en la bajada al PIF
			if ( (documentoFinal.getOidOriginalFinal() != null && StringUtils.isEmpty(rutaPifOriginalFinal)) || StringUtils.isEmpty(rutaPifFinal)){
				rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			}else{
				//si no hay error guardo el fichero de txt con las dos rutas
				final String rutaCompletaArchivo =  this.dirFilesTmp+"/"+this.nombreFicheroRutaPif;
				String rutaArchivosPIF = rutaPifOriginalFinal+"$"+rutaPifFinal;
				this.guardaFichero(rutaCompletaArchivo, rutaArchivosPIF);
			}
			
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "DescargaPIDaPIF. Error en la firma.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			try {
				//Actualizar T92 con estado 2 de error
				Logger.batchlog(Logger.INFO, "Actualizada tabla A8 con estado ERROR.");
			}catch (final Exception e2) {
				Logger.batchlog(Logger.ERROR, "DescargaPIDaPIF. Error actualizando tabla A8.", e2);
			}
			System.exit(rdoProcesoBatch);
		}	
		return rdoProcesoBatch;
	}

	/**
	 *
	 * @param args a
	 * @throws RuntimeException e
	 */
	private void recogerParametros(String[] args){

		if (args == null) {
			throw new RuntimeException("DescargaPIDaPIF - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0){
				throw new RuntimeException("DescargaPIDaPIF - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != DescargaPIDaPIF.numeroParametros){
				throw new RuntimeException("DescargaPIDaPIF - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + DescargaPIDaPIF.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[4] idTarea: "+args[Constants.MAGIC_NUMBER4]);
		this.idTarea = args[Constants.MAGIC_NUMBER4].trim();
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[5] idDocOriginal: "+args[Constants.MAGIC_NUMBER5]);
		this.idDocOriginal = args[Constants.MAGIC_NUMBER5].trim();
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[6] nombreFicheroRutaPif: "+args[Constants.MAGIC_NUMBER6]);
		this.nombreFicheroRutaPif = args[Constants.MAGIC_NUMBER6].trim();
		Logger.batchlog(Logger.INFO, "DescargaPIDaPIF - recogerParametros. args[7] dirFilesTmp: "+args[Constants.MAGIC_NUMBER7]);
		this.dirFilesTmp = args[Constants.MAGIC_NUMBER7].trim();
	}
	
	private void guardaFichero( String rutaCompletaArchivo, String rutaPif) throws Exception {
		
        FileWriter fichero = null;
        PrintWriter pw = null;
    	Logger.batchlog(Logger.INFO, "Comienza la creación del archivo. rutaCompletaArchivo: "+ rutaCompletaArchivo+ "  /// rutaPif: "+rutaPif);
        fichero = new FileWriter(rutaCompletaArchivo);
        pw = new PrintWriter(fichero);
		pw.println(rutaPif);
		if (null != fichero) {
			fichero.close();
        }
    }
}