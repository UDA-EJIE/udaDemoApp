package aa79b.util.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import aa79b.util.beans.Expediente;
import aa79b.util.beans.Tarea;
import aa79b.util.mail.EmailService;
import aa79b.util.mail.ParametrosEmail;

public final class Utils {

	private static final String ENTORNO = EmailService.obtenerProperty("app.entorno");
	/**
	 * El metodo constructor.
	 */
	private Utils() {}

	/**
	 * Obtiene el año y el número de expediente concatenados
	 *
	 * @param anyo
	 * @param numExp
	 * @return String
	 */
	public static String getAnyoNumExpConcatenado(Long anyo, Integer numExp) {
		if (anyo != null && numExp != null) {
			if (anyo != 0 && numExp != 0) {
				return "" + String.valueOf(anyo).substring(2) + "/" + String.format("%06d", numExp);
			} else {
				return "";
			}
		}
		return null;
	}

	/**
	 *
	 * Lee a String un InputStream
	 *
	 * @param is
	 *            InputStream
	 * @return String
	 *
	 * @author xbarba
	 */
	public static String convertStreamToString(InputStream is) {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		final StringBuilder sb = new StringBuilder(50);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (final IOException e) {
			Logger.batchlog(Logger.ERROR, "Error: ", e);
		} finally {
			try {
				is.close();
			} catch (final IOException e) {
				Logger.batchlog(Logger.ERROR, "Error: ", e);
			}
		}
		return sb.toString();
	}

	public static String getMessageEmail(ParametrosEmail parametrosEmail){
		String strPlantillaHtml = StringUtils.EMPTY;
		InputStream plantillaHtml;

		String rutaPlant = Constants.RUTA_BACKEND + Constants.PLANTILLA_EMAIL;
		if ("local".equals(Utils.ENTORNO)) {
			rutaPlant = "C:\\aplic\\aa79b\\aa79bDatos\\backend\\file\\xsl\\plantillaMail.txt";
		}
		try {
			final File file = new File(rutaPlant);
			plantillaHtml = new FileInputStream(file);
			strPlantillaHtml = Utils.convertStreamToString(plantillaHtml);
			Logger.batchlog(Logger.INFO, "Plantilla: " + strPlantillaHtml);
		}catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "PlantillaHtml nula: strPlantillaHtml - " + strPlantillaHtml);
		}

		final Charset utf8charset = Charset.forName("ISO-8859-1");
		final Charset iso88591charset = Charset.forName("ISO-8859-1");
		final CharBuffer decodedMsg = iso88591charset.decode(ByteBuffer.wrap(strPlantillaHtml.getBytes()));
		final ByteBuffer encodedMsg = utf8charset.encode(decodedMsg);
		String utfMsg = new String(encodedMsg.array());

		// Definición del mensaje
		utfMsg = utfMsg.replace("{TITLE}", "");
		utfMsg = utfMsg.replace("{LEGEND}", "");

		StringBuilder messageEu = new StringBuilder();
		StringBuilder messageEs = new StringBuilder();


		if (parametrosEmail != null) {
			messageEu.append(parametrosEmail.getMensajeEu());
			messageEs.append(parametrosEmail.getMensajeEs());
			messageEu.append(Utils.ObtenerInfoAdic(parametrosEmail.getInfoEu()));
			messageEs.append(Utils.ObtenerInfoAdic(parametrosEmail.getInfoEs()));
		}

		utfMsg = utfMsg.replace("{MESSAGE_EU}", messageEu.toString());
		utfMsg = utfMsg.replace("{MESSAGE_ES}", messageEs.toString());



		/*String mensajeCharset = "";
		try {
			mensajeCharset = new String(messageEu.toString().getBytes("UTF-8"), "ISO-8859-1");
			utfMsg = utfMsg.replace("{MESSAGE_EU}", mensajeCharset);
		} catch (UnsupportedEncodingException e) {
			utfMsg = utfMsg.replace("{MESSAGE_EU}", messageEu.toString());
			Logger.batchlog(Logger.INFO, "Excepcion adaptando el charset de los parametros del email", e);
		}

		try {
			mensajeCharset = new String(messageEs.toString().getBytes("UTF-8"), "ISO-8859-1");
			utfMsg = utfMsg.replace("{MESSAGE_ES}", mensajeCharset);
		} catch (UnsupportedEncodingException e) {
			utfMsg = utfMsg.replace("{MESSAGE_ES}", messageEs.toString());
			Logger.batchlog(Logger.INFO, "Excepcion adaptando el charset de los parametros del email", e);
		}*/


		return utfMsg;
	}

	private static StringBuilder ObtenerInfoAdic(Map<String, String> info) {
		StringBuilder infoAdic = new StringBuilder();
		for (String key : info.keySet()) {
				infoAdic.append("<p class='info'><b>").append(key)
				.append("</b>");
				if (!key.equals(Constants.EMPTY)) {
					infoAdic.append(": ");
				}
				infoAdic.append(info.get(key)).append("</p>");
		}
		return infoAdic;

	}


	public static String getNumExpedienteParameter(Expediente expediente) {
		StringBuilder numExpParameter = new StringBuilder();
		numExpParameter.append("[EXP ZK: ");
		numExpParameter.append(expediente.getAnyoNumExpConcatenado());
		numExpParameter.append("] ");
		return numExpParameter.toString();
	}

	public static String getNumExpedienteParameter(Tarea tarea) {
		StringBuilder numExpParameter = new StringBuilder();
		numExpParameter.append("[EXP ZK: ");
		numExpParameter.append(tarea.getAnyoNumExpConcatenado());
		numExpParameter.append("] ");
		return numExpParameter.toString();
	}

}
