package com.ejie.aa79b.utils;

import java.io.ByteArrayOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.DatosDocumentoSalida;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EntradaDatosDocumento;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;
import com.ejie.aa79b.service.DocumentosExpedienteService;
import com.ejie.aa79b.webservices.PIDService;

public class DocumentoUtils extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoUtils.class);

	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private DocumentosExpedienteService documentosExpedienteService;
	@Autowired()
	private PIDService pidService;

	/**
	 * Constructor
	 */
	public DocumentoUtils() {
		// Constructor vacío
	}

	public DatosDocumentoSalida obtenerDatosDocumentoSalida(EntradaDatosDocumento entradaDatosDocumento)
			throws Exception {

		DatosDocumentoSalida datosDocumentoSalida = new DatosDocumentoSalida();
		Locale locale = new Locale(entradaDatosDocumento.getIdioma());
		int x = 0;

		if (entradaDatosDocumento.getListaFicheros() != null && !entradaDatosDocumento.getListaFicheros().isEmpty()
				&& entradaDatosDocumento.getListaFicheros().size() > Constants.UNO) {

			// Se declara el fichero ZIP de salida
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final ZipOutputStream zos = new ZipOutputStream(baos);
			zos.setLevel(ZipOutputStream.DEFLATED);

			ZipEntry entry = null;
			byte[] documento = null;
			for (Aa79bFicheroDocExp fichero : entradaDatosDocumento.getListaFicheros()) {
				// Se crea una nueva entrada con el nombre del archivo
				x++;
				entry = getZipEntryInstance(fichero.getNombre(), x);
				try {
					documento = this.pidService.getDocument(fichero.getOid());
					entry.setSize(documento.length);
					zos.putNextEntry(entry);
					// Se escriben los bytes (input = byte[])
					zos.write(documento);
					zos.closeEntry();
				} catch (Exception e) {
					DocumentoUtils.LOGGER.error("DocumentoUtils - obtenerDatosDocumentoSalida ", e);
					zos.closeEntry();
				}

			}
			zos.close();
			byte[] documentoFinal = new byte[baos.size()];
			documentoFinal = baos.toByteArray();
			baos.close();
			final String message = this.messageSource.getMessage("label.nombreDocZIPgenerado", null, locale);
			StringBuilder nombreZIP = new StringBuilder();
			nombreZIP.append(message);
			nombreZIP.append(entradaDatosDocumento.getAnyo()).append(Constants.GUION_BAJO)
					.append(entradaDatosDocumento.getNumExp()).append(Constants.PUNTO).append(Constants.ZIP);
			datosDocumentoSalida.setRutaPif(this.pidService.subidaPif(nombreZIP.toString(), documentoFinal,
					this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} else {
			DocumentosExpediente documentosExpediente = new DocumentosExpediente();
			documentosExpediente
					.setIdDoc(entradaDatosDocumento.getListaFicheros().get(Constants.CERO).getIdDocInsertado());
			documentosExpediente = this.documentosExpedienteService.find(documentosExpediente);

			String rutaPif = "";

			if (Utils.comprobarRutaPifFichero(documentosExpediente)) {
				rutaPif = documentosExpediente.getFicheros().get(Constants.CERO).getRutaPif();
			} else {
				rutaPif = this.pidService
						.getRutaPIF(entradaDatosDocumento.getListaFicheros().get(Constants.CERO).getOid());
			}

			datosDocumentoSalida.setRutaPif(rutaPif);
			if (StringUtils.isNotEmpty(entradaDatosDocumento.getListaFicheros().get(Constants.CERO).getNombre())) {
				datosDocumentoSalida
						.setNombre(entradaDatosDocumento.getListaFicheros().get(Constants.CERO).getNombre());
			}
		}

		return datosDocumentoSalida;
	}

	public static ZipEntry getZipEntryInstance(String filename, int num) {
		int elPunto = filename.lastIndexOf(".");
		return new ZipEntry(
				filename.substring(0, elPunto) + "-" + num + filename.substring(elPunto, filename.length()));
	}

}
