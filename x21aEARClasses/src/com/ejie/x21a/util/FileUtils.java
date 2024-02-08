package com.ejie.x21a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

public class FileUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUtils.class);

	/**
	 * Función que convierte un fichero en un string en base64 del mismo
	 * 
	 * @param file
	 *            el fichero a convertir
	 * @return String el fichero convertido en un string base64
	 */
	public static String encode64File(File file) throws IOException {
		logger.info("encode64File-start");
		String file64 = null;

		logger.info("lectura de fichero");
		byte[] bytes = readFile(file);
		logger.info("codificacion base64 -start");
		byte[] encoded = Base64.encode(bytes);
		logger.info("codificacion base64 -end");

		logger.info("parseo a String-start");
		file64 = new String(encoded);
		logger.info("parseo a String-end");
		logger.info("encode64File-end");
		return file64;
	}

	/**
	 * Función que lee un fichero y lo convierte en un array de bytes
	 * 
	 * @param file
	 *            el fichero a convertir
	 * @return byte el fichero convertido en un array de bytes
	 */
	public static byte[] readFile(File file) {
		logger.info("readFile-start");
		byte[] bytes = null;
		try (InputStream is = new FileInputStream(file)) {
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// File is too large
			}
			bytes = new byte[(int) length];

			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			if (offset < bytes.length) {
				throw new IOException("No se pudo leer el fichero  "
						+ file.getName());
			}
		} catch (Exception ex) {

			logger.error(ex.getCause().toString());
		}
		logger.info("readFile-end");
		return bytes;
	}

	/**
	 * Función que descomprime un fichero zip y lo guarda en la carpeta definida
	 * 
	 * @param file
	 *            file el fichero a descomprimir
	 * @param outputFolder
	 *            path de la carpeta donde guardar los ficheros descomprimidos
	 * 
	 */
	public static void unzipFile(File file, String outputFolder) {
		logger.info("unzipFile-start");

		byte[] buffer = new byte[1024];

		// get the zip file content
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(file))) {
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator
						+ fileName);

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
			logger.error(ex.getCause().toString());
			logger.error(ex.getStackTrace().toString());
		}
		logger.info("unzipFile-end");
	}

	/**
	 * Función que convierte un String base64 en un fichero y lo guarda
	 * 
	 * @param file64
	 *            string en base64 a convertir a fichero
	 * @param outputFolder
	 *            path de la carpeta donde guardar el fichero
	 * 
	 */
	public static void saveFile(String file64, String outputFolder) {
		logger.info("saveFile-start");
		FileOutputStream outputStream;
		// decode base64
		byte[] fileTemp = Base64.decode(file64.getBytes());

		try {
			outputStream = new FileOutputStream(outputFolder);
			outputStream.write(fileTemp);
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getStackTrace().toString());
		} catch (IOException e) {
			logger.error(e.getStackTrace().toString());
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
		logger.info("saveFile-end");

	}

}
