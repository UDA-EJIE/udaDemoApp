/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.model.UploadBean;
import com.ejie.x21a.service.UploadService;
import com.ejie.x21a.util.PifUtils;

/**
 * UploadController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	/**
	 * Tiempo que se mantienen, en un directorio temporal, los documentos que se
	 * quieren fusionar. En segundos.
	 */
	public static final Long TIEMPO_TTL_DOCUMENTO_TEMP = Long.valueOf(600);// 10 minutos

	@Autowired
	private Properties appConfiguration;

	@Autowired
	private UploadService uploadService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	@PostMapping(value = "/add")
	public @ResponseBody List<Map<String, Object>> add(
			@RequestParam(value = "filename", required = false) String filename,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "files", required = true) MultipartFile file,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		if (!file.isEmpty()) {
			uploadService.saveToDisk(file, appConfiguration.getProperty("fileUpload.path"));
			UploadController.logger.info("[POST - add] : Fichero guardado.");
		}

		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();
		filesMetaInfo.add(this.getFileReturnMap(file));

		return filesMetaInfo;
	}
	
	@PostMapping(value = "/addMultiple")
	public @ResponseBody List<Map<String, Object>> add(
			@RequestParam(value = "filename", required = false) String filename,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "files", required = true) MultipartFile[] files,
			HttpServletResponse response,
			HttpServletRequest request) {

		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();
		for (MultipartFile file : files) {
			uploadService.saveToDisk(file, appConfiguration.getProperty("fileUpload.path"));
			filesMetaInfo.add(this.getFileReturnMap(file));
			UploadController.logger.info("[POST - addMultiple] : {} guardado.", filename);
		}

		return filesMetaInfo;
	}
	
	@PostMapping(value = "/form")
	public @ResponseBody List<Map<String, Object>> addForm(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido1", required = false) String apellido1,
			@RequestParam(value = "apellido2", required = false) String apellido2,
			@RequestParam(value = "foto", required = false) MultipartFile file) {
		
		if (!file.isEmpty()) {
			uploadService.saveToDisk(file, appConfiguration.getProperty("fileUpload.path"));
			UploadController.logger.info("[POST - addForm] : Fichero guardado.");
		}

		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();

		filesMetaInfo.add(this.getFileReturnMap(file));

		return filesMetaInfo;
	}
	
	@PostMapping(value = "/formSimple")
	public @ResponseBody List<Map<String, Object>> addFormSimple(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "fotoPadre", required = false) MultipartFile fotoPadre,
			@RequestParam(value = "fotoMadre", required = false) MultipartFile fotoMadre,
			@ModelAttribute UploadBean uploadBean) {
		
		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();
		if (fotoPadre != null && !fotoPadre.isEmpty()) {
			uploadService.saveToDisk(fotoPadre, appConfiguration.getProperty("fileUpload.path"));
			filesMetaInfo.add(this.getFileReturnMap(fotoPadre));
			UploadController.logger.info("[POST - addFormSimple] : Fichero padre guardado.");
		}
		if (fotoMadre != null && !fotoMadre.isEmpty()) {
			uploadService.saveToDisk(fotoMadre, appConfiguration.getProperty("fileUpload.path"));
			filesMetaInfo.add(this.getFileReturnMap(fotoMadre));
			UploadController.logger.info("[POST - addFormSimple] : Fichero madre guardado.");
		}

		return filesMetaInfo;
	}

//	@PostMapping(value="pifForm")
//	public @ResponseBody List<Map<String,Object>> addPifForm(@RequestParam(value="nombre", required=false) String nombre,
//			@RequestParam(value="apellido1", required=false) String apellido1,
//			@RequestParam(value="apellido2", required=false) String apellido2,
//			@RequestParam(value="file", required=false) MultipartFile file,
//			HttpServletRequest httpRequest) {
//	
//		
//		PifUtils.put(file);
//		
//		List<Map<String,Object>> filesMetaInfo = new ArrayList<Map<String,Object>>();
//
//		filesMetaInfo.add(this.getFileReturnMap(file));
//		
//		return filesMetaInfo;
//		
//	}

	@PostMapping(value = "/pifForm22")
	public @ResponseBody List<Map<String, Object>> addPifForm(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido1", required = false) String apellido1,
			@RequestParam(value = "apellido2", required = false) String apellido2,
			@RequestParam(value = "foto", required = false) MultipartFile file,
			HttpServletRequest httpRequest) {
		
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;
		try {
			iter = upload.getItemIterator(httpRequest);
			while (iter.hasNext()) {

				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					System.out.println("Form field " + name + " with value " + Streams.asString(stream) + " detected.");
				} else {
					System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
					// Process the input stream
					PifUtils.put(item.getName(), stream);

				}

			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		PifUtils.put(file);

		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();
//		filesMetaInfo.add(this.getFileReturnMap(file));

		return filesMetaInfo;
	}

	@PostMapping(value = "/aportarDocumento")
	public @ResponseBody List<Map<String, Object>> aportarDocumento(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido1", required = false) String apellido1,
			@RequestParam(value = "foto", required = false) MultipartFile file,
			HttpServletRequest request) {
		
		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();

		return filesMetaInfo;
	}

	@PostMapping(value = "/pifForm")
	public @ResponseBody List<Map<String, Object>> addPifFormFile(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido1", required = false) String apellido1,
			@RequestParam(value = "apellido2", required = false) String apellido2,
			@RequestParam(value = "foto", required = false) MultipartFile file,
			HttpServletRequest httpRequest) {
		
		PifUtils.put(file);

		List<Map<String, Object>> filesMetaInfo = new ArrayList<Map<String, Object>>();

		filesMetaInfo.add(this.getFileReturnMap(file));

		return filesMetaInfo;
	}

	@GetMapping(value = "/pifForm")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView downloadPif(
			@RequestParam(value = "fileName", required = true) String fileName,
			HttpServletResponse response) throws Exception {
		
		//File file = uploadService.getFromDisk(appConfiguration.getProperty("fileUpload.path"), fileName);
		InputStream inputStream = PifUtils.get(fileName);
		byte[] fileByteArray = IOUtils.toByteArray(inputStream);

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		response.setContentLength(fileByteArray.length);
		FileCopyUtils.copy(fileByteArray, response.getOutputStream());

		return null;
	}

	@DeleteMapping(value = "/pifForm")
	@ResponseStatus(HttpStatus.OK)
	public void removePif(
			@RequestParam(value = "fileName", required = true) String fileName,
			HttpServletResponse response) {
		
		PifUtils.delete(fileName);

		//uploadService.deleteFromDisk(appConfiguration.getProperty("fileUpload.path"), fileName);

		response.setContentType("text/javascript;charset=utf-8");
		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> remove(
			@RequestParam(value = "fileName", required = true) String fileName,
			HttpServletResponse response) {
		
		uploadService.deleteFromDisk(appConfiguration.getProperty("fileUpload.path"), fileName);

		response.setContentType("text/javascript;charset=utf-8");
		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");

		return this.getDeleteFileReturnMap(fileName);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView download(
			@RequestParam(value = "fileName", required = true) String fileName,
			HttpServletResponse response) throws Exception {
		
		File file = uploadService.getFromDisk(appConfiguration.getProperty("fileUpload.path"), fileName);
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		byte[] fileByteArray = FileUtils.readFileToByteArray(file);
		response.setContentLength(fileByteArray.length);
		FileCopyUtils.copy(fileByteArray, response.getOutputStream());

		return null;
	}

	private Map<String, Object> getFileReturnMap(MultipartFile file) {
		Map<String, Object> mapaRetorno = new HashMap<String, Object>();
		mapaRetorno.put("url", "../upload?fileName=" + file.getOriginalFilename());
		mapaRetorno.put("name", file.getOriginalFilename());
		mapaRetorno.put("type", file.getContentType());
		mapaRetorno.put("size", file.getSize());
		mapaRetorno.put("delete_url", "../upload?fileName=" + file.getOriginalFilename());
		mapaRetorno.put("delete_type", "DELETE");

		return mapaRetorno;
	}

	private Map<String, Object> getDeleteFileReturnMap(String fileName) {
		Map<String, Object> mapaRetorno = new HashMap<String, Object>();
		mapaRetorno.put("fileName", Boolean.TRUE);
		
		return mapaRetorno;
	}
}
