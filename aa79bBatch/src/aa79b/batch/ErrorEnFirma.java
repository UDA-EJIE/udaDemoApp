package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.DocumentoTareaSolver;
import aa79b.util.beans.DocumentoTareaAdjunto;
import aa79b.util.common.Constants;
import aa79b.util.common.EstadoFirmaEnum;
import aa79b.util.common.Logger;

/**
 *
 * @author javarona
 *
 */
public class ErrorEnFirma {

	
	
	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 6;

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

		Logger.batchlog(Logger.INFO, "INICIO ErrorEnFirma. main. ***********************************");

		final ErrorEnFirma descargaPIDaPIF = new ErrorEnFirma();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = descargaPIDaPIF.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN ErrorEnFirma. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN ErrorEnFirma. main. RESULTADO PROCESO BATCH : "+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "ErrorEnFirma. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "ErrorEnFirma - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.documentoTareaSolver = new DocumentoTareaSolver();
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "ErrorEnFirma. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = ErrorEnFirma.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "ErrorEnFirma. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO ErrorEnFirma. Comienza el proceso para actualizar la T92 con eeror en la firma");
			
			// ----------------   INSERCION DE LOS FICHEROs DE FIRMA EN T88 --------------------------------------------- 
			
			DocumentoTareaAdjunto docActualizarT92 = new DocumentoTareaAdjunto();
			BigDecimal idTareaBig = new BigDecimal(this.idTarea);
			BigDecimal idDocOrigBig = new BigDecimal(this.idDocOriginal);
			
			docActualizarT92.setIdTarea( idTareaBig );
			docActualizarT92.setIdDocOriginal( idDocOrigBig );
			docActualizarT92.setIdDocFirmaOriginal(null);
			docActualizarT92.setIdDocFirmaFinal(null);
			docActualizarT92.setEstadoFirma( EstadoFirmaEnum.ERROR.getValue() );
			
			this.documentoTareaSolver.updateEstadoTabla92( this.baseSql, docActualizarT92 );
			
			Logger.batchlog(Logger.INFO, "ErrorEnFirma ha FINALIZADO correctamente. Se ha ACTUALIZADO LA T92 CON ESTADO ERROR en la firma");
			
			
			
			
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "ErrorEnFirma. Error.", e);
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
	private void recogerParametros(String[] args){

		if (args == null) {
			throw new RuntimeException("ErrorEnFirma - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0){
				throw new RuntimeException("ErrorEnFirma - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != ErrorEnFirma.numeroParametros){
				throw new RuntimeException("ErrorEnFirma - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + ErrorEnFirma.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "ErrorEnFirma - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "ErrorEnFirma - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "ErrorEnFirma - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "ErrorEnFirma - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "ErrorEnFirma - recogerParametros. args[4] idTarea: "+args[Constants.MAGIC_NUMBER4]);
		this.idTarea = args[Constants.MAGIC_NUMBER4].trim();
		Logger.batchlog(Logger.INFO, "ErrorEnFirma - recogerParametros. args[5] idDocOriginal: "+args[Constants.MAGIC_NUMBER5]);
		this.idDocOriginal = args[Constants.MAGIC_NUMBER5].trim();
	}
	
}
