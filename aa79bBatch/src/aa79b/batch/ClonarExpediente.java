package aa79b.batch;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.BaseSqlTransaccional;
import aa79b.bbdd.solvers.BitacoraSolicitanteSolver;
import aa79b.bbdd.solvers.ClonacionExpedienteSolver;
import aa79b.bbdd.solvers.ConfigDireccionEmailsSolver;
import aa79b.bbdd.solvers.ConfigTextoEmailsSolver;
import aa79b.bbdd.solvers.DocumentosExpedienteSolver;
import aa79b.bbdd.solvers.ExpedientesSolver;
import aa79b.bbdd.solvers.GestorExpedientesSolver;
import aa79b.bbdd.solvers.LibroRegistroSolver;
import aa79b.bbdd.solvers.RegistroAccionesSolver;
import aa79b.util.beans.BitacoraSolicitante;
import aa79b.util.beans.Clonacion;
import aa79b.util.beans.ConfigDireccionEmail;
import aa79b.util.beans.ConfigTextoEmails;
import aa79b.util.beans.DatosContacto;
import aa79b.util.beans.DocumentosExpediente;
import aa79b.util.beans.Expediente;
import aa79b.util.beans.GestorExpediente;
import aa79b.util.beans.LibroRegistro;
import aa79b.util.beans.LibroRegistroEntrada;
import aa79b.util.beans.RegistroAcciones;
import aa79b.util.common.AccionBitacoraEnum;
import aa79b.util.common.AccionesEnum;
import aa79b.util.common.Constants;
import aa79b.util.common.EstadoClonacionEnum;
import aa79b.util.common.Logger;
import aa79b.util.common.MailTextConstants;
import aa79b.util.common.PuntosMenuEnum;
import aa79b.util.common.TipoAvisoEnum;
import aa79b.util.common.Utils;
import aa79b.util.libroregistro.LibroRegistroService;
import aa79b.util.mail.EmailMessage;
import aa79b.util.mail.EmailService;
import aa79b.util.mail.ParametrosEmail;
import aa79b.util.pid.PidService;

/**
 *
 * @author aresua
 *
 */
public class ClonarExpediente {

	private static final String ENTORNO = EmailService.obtenerProperty("app.entorno");

	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 5;

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
	 * this.baseSql: BaseSqlTransaccional.
	 */
	private BaseSqlTransaccional baseSqlTransaccional = null;

	/**
	 * this.idClonacion: String.
	 */
	private String idClonacion = null;


	/**
	 * ClonacionExpedienteSolver: clonacionExpedienteSolver.
	 */
	private ClonacionExpedienteSolver clonacionExpedienteSolver = null;


	/**
	 * RegistroAccionesSolver registroAccionesSolver.
	 */
	private RegistroAccionesSolver registroAccionesSolver = null;

	/**
	 * DocumentosExpedienteSolver: documentosExpedienteSolver.
	 */
	private DocumentosExpedienteSolver documentosExpedienteSolver = null;

	/**
	 * GestorExpedientesSolver gestorExpedientesSolver.
	 */
	private GestorExpedientesSolver gestorExpedientesSolver = null;

	/**
	 * ConfigTextoEmailsSolver: configTextoEmailsSolver.
	 */
	private ConfigTextoEmailsSolver configTextoEmailsSolver = null;

	/**
	 * ConfigDireccionEmailsSolver: configDireccionEmailsSolver.
	 */
	private ConfigDireccionEmailsSolver configDireccionEmailsSolver = null;

	/**
	 * ExpedientesSolver: expedientesSolver.
	 */
	private ExpedientesSolver expedientesSolver = null;

	/**
	 * BitacoraSolicitanteSolver: bitacoraSolicitanteSolver.
	 */
	private BitacoraSolicitanteSolver bitacoraSolicitanteSolver = null;
	/**
	 * LibroRegistroSolver: libroRegistroSolver.
	 */
	private LibroRegistroSolver libroRegistroSolver = null;

	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO ClonarExpediente. main. ***********************************");

		final ClonarExpediente clonarExpediente = new ClonarExpediente();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = clonarExpediente.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN ClonarExpediente. main. ERROR grave.",t);
		}

		Logger.batchlog(Logger.INFO, "FIN ClonarExpediente. main. RESULTADO PROCESO BATCH CLONACIÓN DE EXPEDIENTES: "+ rdoProcesoBatch +" ***********************************");
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
			Logger.batchlog(Logger.ERROR, "ClonarExpediente. main. recogerParametros", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "ClonarExpediente - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------
		final String dbCharSetOra = "UTF-8";
		try{
			this.baseSql = BaseSql.createInstance(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword);
			this.baseSqlTransaccional =  new BaseSqlTransaccional(this.dbDriver, this.dbServer, this.dbUsuario, this.dbPassword,
                    dbCharSetOra);
			this.clonacionExpedienteSolver = new ClonacionExpedienteSolver();
			this.registroAccionesSolver = new RegistroAccionesSolver();
			this.documentosExpedienteSolver = new DocumentosExpedienteSolver();
			this.gestorExpedientesSolver = new GestorExpedientesSolver();
			this.configTextoEmailsSolver = new ConfigTextoEmailsSolver();
			this.configDireccionEmailsSolver = new ConfigDireccionEmailsSolver();
			this.expedientesSolver = new ExpedientesSolver();
			this.bitacoraSolicitanteSolver = new BitacoraSolicitanteSolver();
			this.libroRegistroSolver = new LibroRegistroSolver();

		}catch (final Exception e) {
			if (this.baseSqlTransaccional != null) {
                this.baseSqlTransaccional.closeConection();
            }
			Logger.batchlog(Logger.ERROR, "ClonarExpediente. main. inicializar", e);
			rdoProcesoBatch =  Constants.MAGIC_NUMBER2;
			System.exit(rdoProcesoBatch);
		}

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = ClonarExpediente.class.getResourceAsStream("/aa79b/aa79b.properties");
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
			Logger.batchlog(Logger.ERROR, "ClonarExpediente. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		final Expediente expedienteClon = new Expediente();
		Clonacion clonacion = null;
		List<String> listaNuevosOids = new ArrayList<String>();
		try {
			Logger.batchlog(Logger.INFO, "INICIO ClonarExpediente. Comienza el proceso");


			//Recupero el objeto con los datos, id tabla A8, exp original y exp clonado
			final List<Clonacion> lista = this.clonacionExpedienteSolver.leerInfoClonacion(this.baseSqlTransaccional, Long.valueOf(this.idClonacion));
			clonacion = lista.get(Constants.MAGIC_NUMBER0);

			DocumentosExpediente docClon = new DocumentosExpediente();
			//key: idOriginal / valor: idClonado
			HashMap<BigDecimal,BigDecimal> listaRelacionIds = new HashMap<BigDecimal,BigDecimal>();

			expedienteClon.setAnyo(clonacion.getAnyoClon());
			expedienteClon.setNumExp(clonacion.getNumExpClon());
			BitacoraSolicitante bitacoraSolicitante;

			//Transaccionalidad a partir de aquí...

			Logger.batchlog(Logger.INFO, "ClonarExpediente. Original: "+clonacion.getAnyoNumExpOrigConcatenado()+ " - Exp. a clonar: "+clonacion.getAnyoNumExpClonConcatenado());

			//Primera inserción, tabla 51
			this.clonacionExpedienteSolver.copiaTabla51(this.baseSqlTransaccional, clonacion);
			// copia  tabla 53
			this.clonacionExpedienteSolver.copiaTabla53(this.baseSqlTransaccional, clonacion);

			Logger.batchlog(Logger.INFO, "ClonarExpediente. Copiadas tablas 51 y 53");

			// copia  tabla 54
			this.clonacionExpedienteSolver.copiaTabla54(this.baseSqlTransaccional, clonacion);

			// copia  tabla 55
			int hayRegistro55 = 0;
			hayRegistro55 = this.clonacionExpedienteSolver.copiaTabla55(this.baseSqlTransaccional, clonacion);
			if ( hayRegistro55 > 0){
				final String elOidOriginal = this.expedientesSolver.hayFicheroObservaciones( this.baseSqlTransaccional, expedienteClon );
				if ( StringUtils.isNotEmpty(elOidOriginal)){
					//Proceso de copia del fichero por su oid
					if ("local".equals(ClonarExpediente.ENTORNO)) {
						docClon.setOid( elOidOriginal+"-CLONADO");
					}else{
						docClon.setOid( PidService.getInstance().copyDocument(elOidOriginal));
						//añado a lista por si hay error en la clonación y tengo que borrar los docs del PID...
						listaNuevosOids.add(docClon.getOid());
					}
					//Actualizar en la tabla 55 el oid nuevo
					this.expedientesSolver.updateOidObservaciones( this.baseSqlTransaccional, expedienteClon, docClon.getOid() );
				}
				Logger.batchlog(Logger.INFO, "ClonarExpediente. Hay fichero de observaciones en la T55 --> clonado");
			}
			Logger.batchlog(Logger.INFO, "ClonarExpediente. Copiadas tablas 54, 55. Empezamos con los documentos");

			// copia  tabla 56 uno a uno
			List<DocumentosExpediente> listaDocs = this.documentosExpedienteSolver.findDatosCompletos(this.baseSqlTransaccional, clonacion.getAnyoOrig(), clonacion.getNumExpOrig());
			if(listaDocs != null && !listaDocs.isEmpty()){
				for (final DocumentosExpediente docActual : listaDocs){
					docClon = docActual.clone();
					docClon.setAnyo(clonacion.getAnyoClon());
					docClon.setNumExp(clonacion.getNumExpClon());
					//Duplico el archivo del PID y asigno el nuevo Oid
					if (StringUtils.isNotEmpty(docActual.getOid())){
						if ("local".equals(ClonarExpediente.ENTORNO)) {
							docClon.setOid( docActual.getOid()+"-CLONADO");
						}else{
							docClon.setOid( PidService.getInstance().copyDocument(docActual.getOid()));
							//añado a lista por si hay error en la clonación y tengo que borrar los docs del PID...
							listaNuevosOids.add(docClon.getOid());
						}
					}
					//Si hay documento relacionado, busco en la Hash la equivalencia con el IdDoc clonado correspondiente
					if (docActual.getIdDocRel()!=null){
						if (listaRelacionIds.containsKey(docActual.getIdDocRel())) {
							docClon.setIdDocRel(listaRelacionIds.get(docActual.getIdDocRel()));
						}else{
							Logger.batchlog(Logger.INFO, "ClonarExpediente. No se ha encontrado en el Hash el key: "+docActual.getIdDocRel() );
						}
					}
					//Inserto el doc clonado en bbdd
					docClon = this.documentosExpedienteSolver.add(this.baseSqlTransaccional, docClon );

					//Añado el nuevo idDoc al Hash
					if (!listaRelacionIds.containsKey(docActual.getIdDoc())) {
						listaRelacionIds.put( docActual.getIdDoc(),docClon.getIdDoc() );
					}else{
						Logger.batchlog(Logger.INFO, "ClonarExpediente. Clave duplicada en el Hash de relación de Ids");
					}
				}
			}
			Logger.batchlog(Logger.INFO, "ClonarExpediente. Clonados los documentos");

			// copia  tabla 57
			this.clonacionExpedienteSolver.copiaTabla57(this.baseSqlTransaccional, clonacion);
			Logger.batchlog(Logger.INFO, "ClonarExpediente. Copiada tabla 57");

			//relacionar los expedientes entre ellos
			this.clonacionExpedienteSolver.relacionarExpedientes57(this.baseSqlTransaccional, clonacion);
			Logger.batchlog(Logger.INFO, "ClonarExpediente. relacionados los expedientes original y clon (57)");

			// copia  tabla 58
			this.clonacionExpedienteSolver.copiaTabla58(this.baseSqlTransaccional, clonacion);
			Logger.batchlog(Logger.INFO, "ClonarExpediente. Copiada tabla 58");

			// copia  tabla 62
			this.clonacionExpedienteSolver.copiaTabla62(this.baseSqlTransaccional, clonacion);
			Logger.batchlog(Logger.INFO, "ClonarExpediente. Copiada tabla 62");

			// copia  tabla 63
			this.clonacionExpedienteSolver.copiaTabla63(this.baseSqlTransaccional, clonacion);
			Logger.batchlog(Logger.INFO, "ClonarExpediente. Copiada tabla 63");


			// Crear la bitácora del expediente clonado y la asociarla al expediente
			this.clonacionExpedienteSolver.creacionBitacoraClonado(this.baseSqlTransaccional, clonacion);
			// Actualizar el estado de la bitacora del expediente (Tabla 51)
			expedienteClon.setEstadoBitacora(1L);
			this.expedientesSolver.updateIdEstadoBitacoraTransaccional(this.baseSqlTransaccional, expedienteClon);

			// Actualizar bitacora solicitante (Tabla 79)
			bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(expedienteClon.getAnyo());
			bitacoraSolicitante.setNumExp(expedienteClon.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);
			bitacoraSolicitante.setIdAccion((long) AccionBitacoraEnum.ALTA_EXP.getValue());
			this.bitacoraSolicitanteSolver.add(this.baseSqlTransaccional, bitacoraSolicitante);
			Logger.batchlog(Logger.INFO, "Creada la bitacora del expediente y del solicitante para el expediente clonado");

			//Registro de acciones expediente original
			final Long idBitacoraOrig = this.clonacionExpedienteSolver.getIdEstadoBitacoraOriginal(this.baseSqlTransaccional, clonacion);
			StringBuilder observ = new StringBuilder(MailTextConstants.TEXTO_EXP_CLONADO_ORIG);
			observ.append(" "+clonacion.getAnyoClon()).append("/").append(clonacion.getNumExpClon()).append(" \n");
			this.registrarAcciones(this.baseSqlTransaccional, clonacion.getAnyoOrig(), clonacion.getNumExpOrig(), idBitacoraOrig, observ.toString() );

			//Registro de acciones expediente clonado
			observ = new StringBuilder(MailTextConstants.TEXTO_EXP_CLONADO_CLON);
			observ.append(" "+clonacion.getAnyoOrig()).append("/").append(clonacion.getNumExpOrig()).append(" \n");
			this.registrarAcciones(this.baseSqlTransaccional, clonacion.getAnyoClon(), clonacion.getNumExpClon(), Long.valueOf(Constants.MAGIC_NUMBER1), observ.toString() );

			//Actualizar tabla 88 ->estado finalizado
			this.clonacionExpedienteSolver.updateEstadoTablaA8(this.baseSqlTransaccional, Long.valueOf(this.idClonacion), EstadoClonacionEnum.FINALIZADO.getValue());
			Logger.batchlog(Logger.INFO, "Actualizada tabla A8 con estado FINALIZADO.");
			Logger.batchlog(Logger.INFO, "FIN CORRECTO DEL PROCESO DE CLONACIÓN.");

			// TRANSACCIONALIDAD HASTA AQUí....
			this.baseSqlTransaccional.getDbConnection().commit();



		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "ClonarExpediente. Error en la clonacion.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			try {
				this.baseSqlTransaccional.getDbConnection().rollback();
				Logger.batchlog(Logger.INFO, "ROLLBACK BBDD REALIZADO.");
				//Actualizar T0A8 con estado 1 y fecha ejecución (new date())
				this.clonacionExpedienteSolver.updateEstadoTablaA8(this.baseSql, Long.valueOf(this.idClonacion), EstadoClonacionEnum.ERROR.getValue());
				Logger.batchlog(Logger.INFO, "Actualizada tabla A8 con estado ERROR.");
				//eliminamos los ficheros del PID
				if (!listaNuevosOids.isEmpty()){
					PidService.getInstance().deleteDocuments(listaNuevosOids);
				}
			}catch (final Exception e2) {
				Logger.batchlog(Logger.ERROR, "ClonarExpediente. Error actualizando tabla A8.", e2);
			}finally {
	            this.baseSqlTransaccional.closeConection();
	        }
			System.exit(rdoProcesoBatch);
		}

		try{
			if (clonacion!=null && rdoProcesoBatch == Constants.MAGIC_NUMBER0){
				// Enviar email al gestor del expediente
				Logger.batchlog(Logger.INFO, "Se envía el email al gestor del expediente");
				this.enviarEmail(this.baseSql, expedienteClon);

				// Libro de registro
				Logger.batchlog(Logger.INFO, "Intentamos anotar en el libro de registro.");
				StringBuilder sb = new StringBuilder();
				sb.append(MailTextConstants.TEXTO_NUM_EXP_EU+ clonacion.getAnyoNumExpOrigConcatenado() + Constants.GUION + MailTextConstants.TEXTO_EXP_CLONADO_ORIG).append(clonacion.getAnyoNumExpClonConcatenado());
				LibroRegistroEntrada libroRegistroEntrada = new LibroRegistroEntrada();
				libroRegistroEntrada.setMatter(sb.toString());

				LibroRegistroEntrada libroRegistroRst = LibroRegistroService.getInstance().altaRegistroEntrada(libroRegistroEntrada);

				Logger.batchlog(Logger.INFO, "Libro de registro. Vuelta de altaRegistroEntrada OK.");

				if (libroRegistroRst != null && libroRegistroRst.getFechaRegistro() != null ) {
					LibroRegistro libroRegistro = new LibroRegistro();
					libroRegistro.setAnyo(clonacion.getAnyoOrig());
					libroRegistro.setNumExp(clonacion.getNumExpOrig());
					libroRegistro.setMatter(sb.toString());
					libroRegistro.setTelematico(Constants.IND_NO);
					libroRegistro.setFechaRegistro(libroRegistroRst.getFechaRegistro());
					libroRegistro.setNumRegistro(libroRegistroRst.getNumRegistro());

					this.libroRegistroSolver.add(this.baseSql, libroRegistro);

					Logger.batchlog(Logger.INFO, "Proceso de libro de registro guardado en T80");
				}else{
					Logger.batchlog(Logger.INFO, "Proceso de libro de registro: altaRegistroEntrada ha devuelto NULL, no guardamos en T80");
				}
			}else{
				Logger.batchlog(Logger.INFO, "ERROR en el proceso Batch, no se envía email ni se apunta en el libro de registro");
			}




		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "ClonarExpediente. Error en la clonacion, al enviar el email o en el libro de registro.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			try {
				//Actualizar T0A8 con estado 1 y fecha ejecución (new date())
				this.clonacionExpedienteSolver.updateEstadoTablaA8(this.baseSql, Long.valueOf(this.idClonacion), EstadoClonacionEnum.ERROR.getValue());
				Logger.batchlog(Logger.INFO, "Actualizada tabla A8 con estado ERROR.");
			}catch (final Exception e2) {
				Logger.batchlog(Logger.ERROR, "ClonarExpediente. Error actualizando tabla A8.", e2);
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
			throw new RuntimeException("ClonarExpediente - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0){
				throw new RuntimeException("ClonarExpediente - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != ClonarExpediente.numeroParametros){
				throw new RuntimeException("ClonarExpediente - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length +
						", NECESARIOS:" + ClonarExpediente.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "ClonarExpediente - recogerParametros. args[0] dbUsuario: "+args[Constants.MAGIC_NUMBER0]);
		this.dbUsuario= args[Constants.MAGIC_NUMBER0];
		Logger.batchlog(Logger.INFO, "ClonarExpediente - recogerParametros. args[1] dbPassword: ");
		this.dbPassword = args[Constants.MAGIC_NUMBER1];
		Logger.batchlog(Logger.INFO, "ClonarExpediente - recogerParametros. args[2] dbDriver: "+args[Constants.MAGIC_NUMBER2]);
		this.dbDriver = args[Constants.MAGIC_NUMBER2];
		Logger.batchlog(Logger.INFO, "ClonarExpediente - recogerParametros. args[3] dbServer: "+args[Constants.MAGIC_NUMBER3]);
		this.dbServer = args[Constants.MAGIC_NUMBER3];
		Logger.batchlog(Logger.INFO, "ClonarExpediente - recogerParametros. args[4] idClonacion: "+args[Constants.MAGIC_NUMBER4]);
		this.idClonacion = args[Constants.MAGIC_NUMBER4].trim();
	}




	private void registrarAcciones(BaseSqlTransaccional baseSql, Long anyo, Integer numExp, Long idBitacora, String descripcion) throws Exception {
		final RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.CLONACION_EXPEDIENTES.getValue());
		reg.setIdAccion(AccionesEnum.ALTA.getValue());
		reg.setAnyoExp(anyo);
		reg.setNumExp(numExp);
		reg.setIdEstadoBitacora(idBitacora);
		reg.setObserv(descripcion);
		this.registroAccionesSolver.add(baseSql, reg);
	}


	private void enviarEmail(BaseSql baseSql, Expediente expediente) throws Exception {
		GestorExpediente gestor = null;

		if ("local".equals(ClonarExpediente.ENTORNO)) {
			gestor = new GestorExpediente();
			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031("aa79b@eurohelp.local");
			gestor.setDatosContacto(datosContacto);
		} else {
			gestor = this.gestorExpedientesSolver.getGestorExpediente(baseSql, expediente);
		}

		if (gestor != null && gestor.getDatosContacto() != null && gestor.getDatosContacto().getEmail031() != null){

			//Recoge parámetros para enviar el mail
			Logger.batchlog(Logger.INFO, "Recoger el texto del envío de mail");

			//Se obtiene el remitente
			final ConfigDireccionEmail configDireccionEmail = this.configDireccionEmailsSolver.find(baseSql);

			// Se obtiene el mensaje.
			final ConfigTextoEmails configTextoEmails = this.configTextoEmailsSolver.findAviso(TipoAvisoEnum.APERTURA_OFICIO.getValue(), this.baseSql);

			//Enviar el mail
			Logger.batchlog(Logger.INFO, "Enviar el email al gestor");
			final EmailMessage message = new EmailMessage();

			message.setContentType("text/html");
			message.setSubject("");
			message.setBody("");
			//recupero el título del exp
			expediente = this.expedientesSolver.findTitulo( baseSql, expediente).get(Constants.MAGIC_NUMBER0);
			if(configTextoEmails != null){

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

			//Se obtiene la dirección de correo
			final String dirCorreo = gestor.getDatosContacto().getEmail031();
			message.setToAddress(dirCorreo);

			EmailService.getInstance().sendEmail(message);
			Logger.batchlog(Logger.INFO, "Mail enviado");

		}

	}
}
