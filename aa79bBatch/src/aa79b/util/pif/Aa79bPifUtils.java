package aa79b.util.pif;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import aa79b.util.common.Logger;

/**
 *
 * @author caortiz
 *
 */
public class Aa79bPifUtils {

	private static final long MAX_TIME = 1296000;

	//OPERACIONES DE LAS LIBRERIAS PIF
	public static final String PIF_GET="get";
	public static final String PIF_PUT="put";
	public static final String PIF_PATH_SCRIPT = "pif.path.script";

	/**
	 * Constructor 'PIFUtil'
	 */
	private Aa79bPifUtils() {	}

	/**
	 * Metodo que se encarga de traer el fichero a una zona comun para su
	 * lectura
	 *
	 * @param srcApp
	 *            String
	 * @param pathFrom
	 *            String
	 * @param pathTo
	 *            String
	 * @throws IOException
	 *             e
	 * @throws InterruptedException
	 *             e
	 */
	public static final void getFile(String srcApp, String pathFrom, String pathTo) throws IOException, InterruptedException {


		ProcessBuilder processBuilder = new ProcessBuilder();


		List<String> parametrosComando = new ArrayList<String>();
		parametrosComando.add(obtenerProperty(Aa79bPifUtils.PIF_PATH_SCRIPT));
		parametrosComando.add(srcApp);
		parametrosComando.add(Aa79bPifUtils.PIF_GET);
		parametrosComando.add(pathFrom);
		parametrosComando.add(pathTo);

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command(parametrosComando);
        Process proc = processBuilder.start();

        Logger.batchlog(Logger.INFO,"proc", proc.toString());
        InputStream is = proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder builder = new StringBuilder();
        String line;
        Logger.batchlog(Logger.INFO, "br",br);
        while ((line = br.readLine()) != null) {
        	builder.append(line);
        	Logger.batchlog(Logger.INFO, "builder",builder);
        } //fin del while

        br.close();

        //si el valor de retorno del script no es correcto, elevamos una excepcion
        if(proc.waitFor()!=0) {
        	throw new IOException(builder.toString());
        }



	}

	/**
	 * Metodo que se llevear un fichero a una zona comun de intercambio
	 *
	 * @param dstApp
	 *            String
	 * @param pathFrom
	 *            String
	 * @param pathTo
	 *            String
	 * @param overwrite
	 *            boolean
	 *
	 * @return String 'contentType#filePath#fileName#size'
	 *
	 * @throws IOException
	 *             e
	 */
	public static final String putFile(String dstApp, String pathFrom, String pathTo, boolean overwrite) throws Exception {

		 StringBuilder salida = new StringBuilder();
		try{



			StringBuilder execString = new StringBuilder(obtenerProperty(Aa79bPifUtils.PIF_PATH_SCRIPT));
			Logger.batchlog(Logger.INFO,"execString: " + execString.toString());

			execString.append(" ").append(dstApp);
			execString.append(" ").append(Aa79bPifUtils.PIF_PUT);
			execString.append(" ").append(pathFrom);
			execString.append(" ").append(pathTo);
			execString.append(" ").append(overwrite);
			execString.append(" ").append(Aa79bPifUtils.MAX_TIME);

			Logger.batchlog(Logger.INFO,"Parametros llamada a pif en putFile: " + execString.toString());

			// Ejecutamos el script
	        Process proc = Aa79bPifUtils.execute(execString.toString());

	        InputStream is = proc.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);

	        String line;
	        while ((line = br.readLine()) != null) {
	            Logger.batchlog(Logger.INFO, line);
	        	if(line != ""){
	        		salida.append(line);
	        	}
	        } //fin del while

	        br.close();
		}catch (Exception e){
			Logger.batchlog(Logger.ERROR, "Error en putFile: " + e.getMessage());
		}

		return salida.toString();
	}

	/**
	 * Ejecuta la cadena indicada
	 *
	 * @param execString
	 *            String
	 *
	 * @return Process
	 *
	 * @throws IOException
	 *             e
	 */
	private static Process execute(String execString) throws IOException {
		try {
			Runtime rt = Runtime.getRuntime();
			return rt.exec(execString);
		} catch (IOException e){
		    Logger.batchlog(Logger.ERROR, "Llamada incorrecta a las librerias PIF", e);
			throw e;
		}
	}

	/**
	 * Ejecuta la cadena indicada
	 *
	 * @param execString
	 *            String
	 *
	 * @return Process
	 *
	 * @throws IOException
	 *             e
	 */
	private static Process execute(String[] execString) throws IOException {
		try {
			Runtime rt = Runtime.getRuntime();
			return rt.exec(execString);
		} catch (IOException e){
		    Logger.batchlog(Logger.ERROR, "Llamada incorrecta a las librerias PIF", e);
			throw e;
		}
	}

	/**
	 * Este metodo sirve para obtener una propiedad del fichero de propiedades
	 * @param propiedad
	 *            La key de la propiedad
	 * @return El valor de la propiedad
	 */
	public static String obtenerProperty(String propiedad) {
		String strResultado = "";
		final InputStream in = Aa79bPifUtils.class.getResourceAsStream("/aa79b/aa79b.properties");

		if (in != null) {
			final Properties prop = new Properties();

			try {
				prop.load(in);
			} catch (final IOException e) {
				throw new RuntimeException(
						"obtenerProperty. El fichero /aa79b/aa79b.properties ha dado error.",
						e);
			}
			strResultado = prop.getProperty(propiedad);
		} else {
			throw new RuntimeException(
					"obtenerProperty. El fichero /aa79b/aa79b.properties no esta en el classpath o no existe.");
		}
		return strResultado;
	}
}
