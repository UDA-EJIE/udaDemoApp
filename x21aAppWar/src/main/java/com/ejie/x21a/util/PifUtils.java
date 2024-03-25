package com.ejie.x21a.util;

import java.io.IOException;
import java.io.InputStream;

import n38a.exe.N38APISesion;
import n38c.exe.N38API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.ejie.y31.factory.Y31JanoServiceAbstractFactory;
import com.ejie.y31.service.Y31JanoService;
import com.ejie.y31.vo.Y31AttachmentBean;

public class PifUtils {

	private static final Logger logger = LoggerFactory.getLogger(PifUtils.class);
	
	private static final String APP = "X21A";
	private static final Long TIEMPO_TTL_DOCUMENTO_TEMP  = Long.valueOf(600);
	private static final String PATH = "/x21a/";
	
	public static void put (MultipartFile multipartFile){
		
		try {
			PifUtils.put(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void put (String filename, InputStream inputStream){
	
		Y31JanoService service = null;
		try {
			service = Y31JanoServiceAbstractFactory.getInstance();
			StringBuilder rutaFichTmp = new StringBuilder(PATH).append(filename);
	
			Y31AttachmentBean result = service.put(PifUtils.getTokenAppDocument(), inputStream, rutaFichTmp.toString(),
					false, PifUtils.TIEMPO_TTL_DOCUMENTO_TEMP);
			
			PifUtils.logger.debug("guardaDocTmpPif result: " + result);
			
		} catch (Exception e) {
			PifUtils.logger.error("Se ha producido un error al guardar el temporal en PIF:",e);
			throw new RuntimeException("ERR!! Servicio PIF no disponible", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					PifUtils.logger
							.warn("Se ha producido un error al cerrar el stream de lectura: "
									+ e.getMessage());
				}
			}
		}
	}
	
	public static Y31AttachmentBean putPif (String filename, InputStream inputStream){
		
		Y31JanoService service = null;
		try {
			service = Y31JanoServiceAbstractFactory.getInstance();
			StringBuilder rutaFichTmp = new StringBuilder(PATH).append(filename);
	
			Y31AttachmentBean result = service.put(PifUtils.getTokenAppDocument(), inputStream, rutaFichTmp.toString(),
					false, PifUtils.TIEMPO_TTL_DOCUMENTO_TEMP);
			
			PifUtils.logger.debug("guardaDocTmpPif result: " + result);
			
			return result;
			
		} catch (Exception e) {
			PifUtils.logger.error("Se ha producido un error al guardar el temporal en PIF:",e);
			throw new RuntimeException("ERR!! Servicio PIF no disponible", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					PifUtils.logger
							.warn("Se ha producido un error al cerrar el stream de lectura: "
									+ e.getMessage());
				}
			}
		}
	}
	
	public static InputStream get (String filename){
		
		Y31JanoService service = null;
		try {
			service = Y31JanoServiceAbstractFactory.getInstance();
			StringBuilder rutaFichTmp = new StringBuilder(PATH).append(filename);
	
			return service.get(PifUtils.getTokenAppDocument(), rutaFichTmp.toString());
			
		} catch (Exception e) {
			PifUtils.logger.error("Se ha producido un error al guardar el temporal en PIF:",e);
			throw new RuntimeException("ERR!! Servicio PIF no disponible", e);
		} 
	}
	
	public static void delete (String filename){
		
		Y31JanoService service = null;
		try {
			service = Y31JanoServiceAbstractFactory.getInstance();
			StringBuilder rutaFichTmp = new StringBuilder(PATH).append(filename);
	
			service.delete(PifUtils.getTokenAppDocument(), rutaFichTmp.toString());
			
		} catch (Exception e) {
			PifUtils.logger.error("Se ha producido un error al guardar el temporal en PIF:",e);
			throw new RuntimeException("ERR!! Servicio PIF no disponible", e);
		} 
	}
	
	

	public static Document getTokenAppDocument() {
		final N38APISesion miAPISesion = new N38APISesion();
		final Document docAPISesionApp = miAPISesion.n38APISesionCrearApp(PifUtils.APP);
		
		PifUtils.logger.trace("INI - getTokenAppDocument");
		Document doc = null;
			
		PifUtils.logger.info(PifUtils.APP + " logueandose en XLNets ...");

		N38API n38api = new N38API(docAPISesionApp);
		PifUtils.logger.info(PifUtils.APP + " logueada en XLNets.");
		
		doc = n38api.n38ItemSesion();

		PifUtils.logger.trace("FIN - getTokenAppDocument");
		return doc;
	}
	
	
}
