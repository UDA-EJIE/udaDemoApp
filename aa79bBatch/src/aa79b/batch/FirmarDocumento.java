package aa79b.batch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import aa79b.util.common.Constants;
import aa79b.util.common.Logger;
import aa79b.util.firma.CoreFirmaDelegate;
import aa79b.util.firma.FirmaService;
import aa79b.util.pif.Aa79bPifUtils;
import aa79b.webservices.x43f.EjgvDocument;

/**
 *
 * @author aresua
 *
 */
public class FirmarDocumento {

	// =======================================================================
	// PROPIEDADES ESTATICAS
	// =======================================================================
	private static final long numeroParametros = 3;

	// =======================================================================
	// PROPIEDADES DE INSTANCIA
	// =======================================================================

	/**
	 * this.rutaArchivoOriginal: String.
	 */
	private String rutaArchivoOriginal = null;
	/**
	 * this.rutaArchivoFinal: String.
	 */
	private String rutaArchivoFinal = null;

	/**
	 * this.directorioPifR02gTmp: String.
	 */
	private String directorioPifR02gTmp = null;

	/**
	 * prop: Properties
	 */
	private final Properties prop = new Properties();

	/**
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		Logger.batchlog(Logger.INFO, "INICIO FirmarDocumento. main. ***********************************");

		final FirmarDocumento firmarDocumento = new FirmarDocumento();

		int rdoProcesoBatch;
		try {
			rdoProcesoBatch = firmarDocumento.doMain(args);
		} catch (final Throwable t) {
			rdoProcesoBatch = Constants.MAGIC_NUMBER99;
			Logger.batchlog(Logger.ERROR, "FIN FirmarDocumento. main. ERROR grave.", t);
		}

		Logger.batchlog(Logger.INFO, "FIN FirmarDocumento. main. RESULTADO PROCESO BATCH FIRMA DE DOCUMENTOS: "
				+ rdoProcesoBatch + " ***********************************");
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
			Logger.batchlog(Logger.ERROR, "FirmarDocumento. main. recogerParametros", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER1;
			System.exit(rdoProcesoBatch);
		}

		Logger.batchlog(Logger.INFO, "FirmarDocumento - MAIN. ");

		// -----------------------------------------------------------------
		// Inicializamos objetos de conexion
		// -----------------------------------------------------------------

		// Inicializar las propiedades
		try {
			Logger.batchlog(Logger.INFO, "Inicializar las propiedades.");
			final InputStream in = FirmarDocumento.class.getResourceAsStream("/aa79b/aa79b.properties");
			if (in != null) {
				// Existe el fichero de propiedades
				try {
					prop.load(in);
					in.close();
				} catch (final IOException e) {
					throw new Exception("El fichero /aa79b/aa79b.properties ha dado error.", e);
				}
			} else {
				throw new Exception("El fichero /aa79b/aa79b.properties no esta en el classpath o no existe.");
			}
			in.close();
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "FirmarDocumento. main. inicializar las propiedades", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER4;
			System.exit(rdoProcesoBatch);
		}

		// Inicio del proceso
		try {
			Logger.batchlog(Logger.INFO, "INICIO FirmarDocumento. Comienza el proceso");

			// ARCHIVOS ORIGINALES
			Logger.batchlog(Logger.INFO, "Carpeta de ficheros ORIGINALES : " + this.rutaArchivoOriginal);
			File carpetaOriginales = new File(this.rutaArchivoOriginal);
			File[] ficheros = carpetaOriginales.listFiles();
			Logger.batchlog(Logger.INFO, "Número de ficheros ORIGINALES encontrados para firmar: " + ficheros.length);
			for (int i = 0; i < ficheros.length; i++) {
				Logger.batchlog(Logger.INFO, "Nombre del fichero ORIGINAL "+i+": " + ficheros[i].getName());
				this.firmarArchivoYGuardar( this.rutaArchivoOriginal + ficheros[i].getName(),"_ORIG/" );
			}
						
			// ARCHIVOS FINALES
			Logger.batchlog(Logger.INFO, "Carpeta de ficheros FINALES : " + this.rutaArchivoFinal);
			File carpetaFinales = new File(this.rutaArchivoFinal);
			File[] ficherosFin = carpetaFinales.listFiles();
			Logger.batchlog(Logger.INFO, "Número de ficheros FINALES encontrados para firmar: " + ficherosFin.length);
			for (int i = 0; i < ficherosFin.length; i++) {
				Logger.batchlog(Logger.INFO, "Nombre del fichero FINAL " + i + ": " + ficherosFin[i].getName());
				this.firmarArchivoYGuardar( this.rutaArchivoFinal + ficherosFin[i].getName(), "_FIN/" );
			}

			Logger.batchlog(Logger.INFO, "ARCHIVOS FINALES FIRMADOS!!! ****************************** ");

		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "FirmarDocumento. Error en la firma.", e);
			rdoProcesoBatch = Constants.MAGIC_NUMBER3;
			System.exit(rdoProcesoBatch);
		}

		return rdoProcesoBatch;
	}

	/**
	 *
	 * @param args
	 */
	private void recogerParametros(String[] args) {

		if (args == null) {
			throw new RuntimeException("FirmarDocumento - recogerParametros. La clase no ha recibido parametros.");
		} else {
			if (args.length == 0) {
				throw new RuntimeException("FirmarDocumento - recogerParametros. La clase ha recibido 0 parametros.");
			}
			if (args.length != FirmarDocumento.numeroParametros) {
				throw new RuntimeException(
						"FirmarDocumento - recogerParametros. Numero de argumentos incorrecto. RECBIDOS:" + args.length
								+ ", NECESARIOS:" + FirmarDocumento.numeroParametros + ".");
			}
		}
		// Si NO hubo excepciones, se cargaran los parametros
		Logger.batchlog(Logger.INFO, "FirmarDocumento - recogerParametros. args[0] rutaArchivoOriginal: "+args[Constants.MAGIC_NUMBER0]);
		this.rutaArchivoOriginal = args[Constants.MAGIC_NUMBER0].trim();
		Logger.batchlog(Logger.INFO, "FirmarDocumento - recogerParametros. args[1] rutaArchivoFinal: "+args[Constants.MAGIC_NUMBER1]);
		this.rutaArchivoFinal = args[Constants.MAGIC_NUMBER1].trim();
		Logger.batchlog(Logger.INFO, "FirmarDocumento - recogerParametros. args[2] directorioPifR02gTmp: "+args[Constants.MAGIC_NUMBER2]);
		this.directorioPifR02gTmp = args[Constants.MAGIC_NUMBER2].trim();
	}

	/**
	 *
	 * @param rutaCompletaArchivo
	 * @param ejgvDocument
	 * @throws Exception
	 */
	private void guardaFicheroXMLEjgvDocument(String rutaCompletaArchivo, EjgvDocument ejgvDocument) throws Exception {

		Logger.batchlog(Logger.INFO, "CoreFirmaDelegate.guardaFicheroXMLEjgvDocument ");
		File file = new File(rutaCompletaArchivo);
		final JAXBContext jaxbContext = JAXBContext.newInstance();

		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(ejgvDocument, file);

	}

	private void firmarArchivoYGuardar(String rutaArchivo, String fin_ruta) throws Exception {
		final String rutaCompletaArchivoPIFR02g = directorioPifR02gTmp + fin_ruta
				+ rutaArchivo.substring(rutaArchivo.lastIndexOf("/") + 1, rutaArchivo.length());

		Logger.batchlog(Logger.INFO, "archivo a firmar en:  " + rutaCompletaArchivoPIFR02g);

		final String rutaDestinoArchivoFirma = FirmaService.getInstance()
				.getServerSignatureLocationDetached(rutaCompletaArchivoPIFR02g);

		/*JAXBContext context = JAXBContext.newInstance(EjgvDocumentType.class);
		Unmarshaller un = context.createUnmarshaller();*/

		Logger.batchlog(Logger.INFO, "archivo firmado en:  " + rutaDestinoArchivoFirma);
		// convert to desired object
		final String rutaDestinoArchivoFirmaFinal = rutaArchivo.substring(0, rutaArchivo.lastIndexOf("."))
				+ Constants.FIRMA_EXTENSION;

		Logger.batchlog(Logger.INFO, "archivo firmado en:  " + rutaDestinoArchivoFirma
				+ " ....... Vamos a guardar el fichero de firma en:  " + rutaDestinoArchivoFirmaFinal);


		Logger.batchlog(Logger.INFO, "rutaDestinoArchivoFirma",rutaDestinoArchivoFirma);
		Logger.batchlog(Logger.INFO, "rutaDestinoArchivoFirma",rutaDestinoArchivoFirmaFinal);
		Aa79bPifUtils.getFile("aa79b", rutaDestinoArchivoFirma,rutaDestinoArchivoFirmaFinal);


	}

	public static Document recogerToken()
			throws IOException, ParserConfigurationException, SAXException, TransformerException {

		try {
			URL url = new URL(CoreFirmaDelegate.obtenerProperty("ruta.WSToken"));
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String token = IOUtils.toString(in, encoding);
			Logger.batchlog(Logger.INFO, "token . " + token);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = docFactory.newDocumentBuilder();


			Logger.batchlog(Logger.INFO, "StringReader(token) . " + new StringReader(token));
			Logger.batchlog(Logger.INFO, "new InputSource(new StringReader(token)) . " + new InputSource(new StringReader(token)));
			Document document = builder.parse(new InputSource(new StringReader(token)));
			Logger.batchlog(Logger.INFO, "token document. " + document.toString());
			return document;
		} catch (MalformedURLException e) {
			Logger.batchlog(Logger.ERROR, "W22ClsUtilidades. recogerToken() MalformedURLException ", e);
			throw e;
		} catch (IOException e) {
			Logger.batchlog(Logger.ERROR, "W22ClsUtilidades. recogerToken() IOException ", e);
			throw e;
		} catch (ParserConfigurationException e) {
			Logger.batchlog(Logger.ERROR, "W22ClsUtilidades. recogerToken() ParserConfigurationException ", e);
			throw e;
		} catch (SAXException e) {
			Logger.batchlog(Logger.ERROR, "W22ClsUtilidades. recogerToken() SAXException ", e);
			throw e;
		}
	}
}
