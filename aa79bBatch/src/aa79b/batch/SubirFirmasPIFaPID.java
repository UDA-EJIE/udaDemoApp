package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.solvers.BitacoraExpedienteSolver;
import aa79b.bbdd.solvers.DocumentoTareaSolver;
import aa79b.bbdd.solvers.RegistroAccionesSolver;
import aa79b.util.beans.BitacoraExpediente;
import aa79b.util.beans.DocumentoTareaAdjunto;
import aa79b.util.beans.RegistroAcciones;
import aa79b.util.common.AccionesEnum;
import aa79b.util.common.Constants;
import aa79b.util.common.EstadoFirmaEnum;
import aa79b.util.common.Logger;
import aa79b.util.common.MailTextConstants;
import aa79b.util.common.PuntosMenuEnum;
import aa79b.util.pid.PidService;

/**
 *
 * @author javarona
 *
 */
public class SubirFirmasPIFaPID {

	
	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 12;

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
	
	//FICHERO DE FIRMA DEL DOCUMENTO ORIGINAL FINAL
	/**
	 * this.firmaOrigFinalRutaPif: String.
	 */
	private String firmaOrigFinalRutaPif = null;
	/**
	 * this.firmaOrigFinalContentType: String.
	 */
	private String firmaOrigFinalContentType = null;
	/**
	 * this.firmaOrigFinalTamano: String.
	 */
	private String firmaOrigFinalTamano = null;
	
	//FICHERO DE FIRMA DEL DOCUMENTO FINAL
	/**
	 * this.firmaFinalRutaPif: String.
	 */
	private String firmaFinalRutaPif = null;
	/**
	 * this.firmaFinalContentType: String.
	 */
	private String firmaFinalContentType = null;
	/**
	 * this.firmaFinalTamano: String.
	 */
	private String firmaFinalTamano = null;
	
	private boolean docOriginalFinalInformado = false;
	
	
	/**  
	 * DocumentoTareaSolver: documentoTareaSolver.
	 */
	private DocumentoTareaSolver documentoTareaSolver = null;
	
	/**
	 * RegistroAccionesSolver registroAccionesSolver.
	 */
	private RegistroAccionesSolver registroAccionesSolver = null;
	
	/**
	 * BitacoraExpedienteSolver bitacoraExpedienteSolver.
	 */
	private BitacoraExpedienteSolver bitacoraExpedienteSolver = null;
	
	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO SubirFirmasPIFaPID. main. ***********************************");

		final SubirFirmasPIFaPID descargaPIDaPIF = new SubirFirmasPIFaPID();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = descargaPIDaPIF.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN SubirFirmasPIFaPID. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN SubirFirmasPIFaPID. main. RESULTADO PROCESO BATCH : "+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "SubirFirmasPIFaPID. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.documentoTareaSolver = new DocumentoTareaSolver();
			this.registroAccionesSolver = new RegistroAccionesSolver();
			this.bitacoraExpedienteSolver = new BitacoraExpedienteSolver();
			
		}catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirFirmasPIFaPID. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = SubirFirmasPIFaPID.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "SubirFirmasPIFaPID. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		DocumentoTareaAdjunto infoT92 = null;
		try {
			Logger.batchlog(Logger.INFO, "INICIO SubirFirmasPIFaPID. Comienza el proceso");
			
			
			//Recupero el objeto con los datos, idDocFinal y sus datos en la T88
			final List<DocumentoTareaAdjunto> lista = this.documentoTareaSolver.leerInfoT92(this.baseSql, Long.valueOf(this.idTarea), Long.valueOf(this.idDocOriginal));
			infoT92 = lista.get(Constants.MAGIC_NUMBER0);
			
			//Subida de la firma original final
			final String nombreFirmaOriginalFinal = this.firmaOrigFinalRutaPif.substring(this.firmaOrigFinalRutaPif.lastIndexOf("/") + 1);
			final String oidFirmaOriginalFInal = PidService.getInstance().addDocument(nombreFirmaOriginalFinal, this.firmaOrigFinalRutaPif);
			Logger.batchlog(Logger.INFO, " -------> SUBIDO AL PID la firma original final. OID:  "+oidFirmaOriginalFInal);
			
			//Subida de la firma final
			final String nombreFirmaFinal = this.firmaFinalRutaPif.substring(this.firmaFinalRutaPif.lastIndexOf("/") + 1);
			final String oidFirmaFinal = PidService.getInstance().addDocument(nombreFirmaFinal, this.firmaFinalRutaPif);
			Logger.batchlog(Logger.INFO, " -------> SUBIDO AL PID la firma Final. OID:  "+oidFirmaFinal);
			
			if (oidFirmaOriginalFInal != null) {
				//Control de errores
				
				this.docOriginalFinalInformado = true;
				if ( StringUtils.isEmpty(oidFirmaOriginalFInal) || StringUtils.isEmpty(oidFirmaFinal) ){
					rdoProcesoBatch = Constants.MAGIC_NUMBER3;
				}
			} else {
				
				this.docOriginalFinalInformado = false;
				if ( StringUtils.isEmpty(oidFirmaFinal) ){
					rdoProcesoBatch = Constants.MAGIC_NUMBER3;
				}
			}
			
			// ----------------   INSERCION DE LOS FICHEROs DE FIRMA EN T88 --------------------------------------------- 
			if (rdoProcesoBatch == Constants.MAGIC_NUMBER0){
				
				DocumentoTareaAdjunto docFirmaOriginalFinal = new DocumentoTareaAdjunto();
				if (this.docOriginalFinalInformado) {
					//Leo la info del fichero original de la T56
					final List<DocumentoTareaAdjunto> listOriginalFinal = this.documentoTareaSolver.leerInfoBasicaDocFinal(this.baseSql, infoT92.getIdDocOriginalFinal() );
					final DocumentoTareaAdjunto infoOriginalFinal = listOriginalFinal.get(Constants.MAGIC_NUMBER0);
	
					// Insert del doc de firma Original Final en la T88
					docFirmaOriginalFinal.setContentType(this.firmaOrigFinalContentType);
					docFirmaOriginalFinal.setEncriptado(Constants.IND_NO);
					final String extensionOrigFinal = this.firmaOrigFinalRutaPif.substring(this.firmaOrigFinalRutaPif.lastIndexOf(".") + 1); 
					docFirmaOriginalFinal.setExtension(extensionOrigFinal);
					docFirmaOriginalFinal.setTitulo(infoOriginalFinal.getTitulo()+Constants.FIRMA_TXT);
					final String nombreOriginalFinal = infoOriginalFinal.getNombre().substring(0, infoOriginalFinal.getNombre().lastIndexOf(".") + 1) + extensionOrigFinal; 
					docFirmaOriginalFinal.setNombre(nombreOriginalFinal);
					docFirmaOriginalFinal.setOidOriginal(oidFirmaOriginalFInal);
					docFirmaOriginalFinal.setTamano(Long.valueOf(this.firmaOrigFinalTamano));
					this.documentoTareaSolver.insertTabla88(this.baseSql, docFirmaOriginalFinal);
				}
				
				//Leo la info del fichero final de la T88			
				final List<DocumentoTareaAdjunto> listaFinal = this.documentoTareaSolver.leerInfoBasicaDocFinal(this.baseSql, infoT92.getIdFichero() );
				DocumentoTareaAdjunto infoFichFinal = listaFinal.get(Constants.MAGIC_NUMBER0);
				
				// Insert del doc de firma Original en la T88
				DocumentoTareaAdjunto docFirmaFinal = new DocumentoTareaAdjunto();
				docFirmaFinal.setContentType(this.firmaFinalContentType);
				docFirmaFinal.setEncriptado(Constants.IND_NO);
				final String extensionFinal = this.firmaFinalRutaPif.substring(this.firmaFinalRutaPif.lastIndexOf(".") + 1); 
				docFirmaFinal.setExtension(extensionFinal);
				docFirmaFinal.setTitulo(infoFichFinal.getTitulo()+Constants.FIRMA_TXT);
				final String nombreFinal = infoFichFinal.getNombre().substring(0, infoFichFinal.getNombre().lastIndexOf(".") + 1) + extensionFinal; 
				docFirmaFinal.setNombre(nombreFinal);
				docFirmaFinal.setOidOriginal(oidFirmaFinal);
				docFirmaFinal.setTamano(Long.valueOf(this.firmaFinalTamano));
				this.documentoTareaSolver.insertTabla88(this.baseSql, docFirmaFinal);
				
				
				// ----------------   INSERCION DE LOS FICHEROs DE FIRMA EN T88 --------------------------------------------- 
				
				DocumentoTareaAdjunto docActualizarT92 = new DocumentoTareaAdjunto();
				
				docActualizarT92.setIdTarea( infoT92.getIdTarea() );
				docActualizarT92.setIdDocOriginal( infoT92.getIdDocOriginal() );
				docActualizarT92.setIdDocFirmaOriginal( this.docOriginalFinalInformado ? docFirmaOriginalFinal.getIdFichero():null );
				docActualizarT92.setIdDocFirmaFinal( docFirmaFinal.getIdFichero() );
				docActualizarT92.setEstadoFirma( EstadoFirmaEnum.FIRMADO.getValue() );
				
				this.documentoTareaSolver.updateEstadoTabla92( this.baseSql, docActualizarT92 );
				
				Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID ha FINALIZADO correctamente. Se ha ACTUALIZADO LA T92 OK");
				
				
				//Registro de acciones -------------------------------------------------------------------------
				
				final List<BitacoraExpediente> listaInfoExpBitacora = this.bitacoraExpedienteSolver.getInfoBitacora(this.baseSql, this.idTarea);
				final BitacoraExpediente infoExpBitacora = listaInfoExpBitacora.get(Constants.MAGIC_NUMBER0);
				
				StringBuilder observ = new StringBuilder(MailTextConstants.TEXTO_EXP_DOC_FIRMADO);
				observ.append(" "+infoExpBitacora.getAnyo()).append("/").append(infoExpBitacora.getNumExp()).append(" \n");
				observ.append(MailTextConstants.TITULO_DOC_ES+ docFirmaFinal.getTitulo());
				this.registrarAcciones(this.baseSql, infoExpBitacora.getAnyo(), infoExpBitacora.getNumExp(), infoExpBitacora.getIdEstadoBitacora(), observ.toString() );
				
				Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID registro de acciones registrado correctamente. FIN");
				
			}
			
			
			
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "SubirFirmasPIFaPID. Error.", e);
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
			throw new RuntimeException("SubirFirmasPIFaPID - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0){
				throw new RuntimeException("SubirFirmasPIFaPID - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != SubirFirmasPIFaPID.numeroParametros){
				throw new RuntimeException("SubirFirmasPIFaPID - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + SubirFirmasPIFaPID.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[4] idTarea: "+args[Constants.MAGIC_NUMBER4]);
		this.idTarea = args[Constants.MAGIC_NUMBER4].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[5] idDocOriginal: "+args[Constants.MAGIC_NUMBER5]);
		this.idDocOriginal = args[Constants.MAGIC_NUMBER5].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[6] firmaOrigFinalRutaPif: "+args[Constants.MAGIC_NUMBER6]);
		this.firmaOrigFinalRutaPif = args[Constants.MAGIC_NUMBER6].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[7] firmaOrigFinalContentType: "+args[Constants.MAGIC_NUMBER7]);
		this.firmaOrigFinalContentType = args[Constants.MAGIC_NUMBER7].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[8] firmaOrigFinalTamano: "+args[Constants.MAGIC_NUMBER8]);
		this.firmaOrigFinalTamano = args[Constants.MAGIC_NUMBER8].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[9] firmaFinalRutaPif: "+args[Constants.MAGIC_NUMBER9]);
		this.firmaFinalRutaPif = args[Constants.MAGIC_NUMBER9].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[10] firmaFinalContentType: "+args[Constants.MAGIC_NUMBER10]);
		this.firmaFinalContentType = args[Constants.MAGIC_NUMBER10].trim();
		Logger.batchlog(Logger.INFO, "SubirFirmasPIFaPID - recogerParametros. args[11] firmaFinalTamano: "+args[Constants.MAGIC_NUMBER11]);
		this.firmaFinalTamano = args[Constants.MAGIC_NUMBER11].trim();
	}
	
	private void registrarAcciones(BaseSql baseSql, Long anyo, Integer numExp, Long idBitacora, String descripcion) throws Exception {
		final RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.FIRMA_DOCUMENTOS.getValue());
		reg.setIdAccion(AccionesEnum.ALTA.getValue());
		reg.setAnyoExp(anyo);
		reg.setNumExp(numExp);
		reg.setIdEstadoBitacora(idBitacora);
		reg.setObserv(descripcion);
		this.registroAccionesSolver.add(baseSql, reg);
	}
	
}
