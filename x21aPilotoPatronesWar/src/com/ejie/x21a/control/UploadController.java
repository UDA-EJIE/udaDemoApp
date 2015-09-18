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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.model.UploadBean;
import com.ejie.x21a.service.UploadService;

/**
 * UploadController
 * @author UDA
 */
@Controller
@RequestMapping (value = "/upload")
public class UploadController   {

	@Autowired
	private Properties appConfiguration;
	
	@Autowired
	private UploadService uploadService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> add(@RequestParam(value="filename", required=false) String filename ,@RequestParam(value="nombre", required=false) String nombre,@RequestParam(value="files[]", required=false) MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		
		if(!file.isEmpty()){
			uploadService.saveToDisk(file, appConfiguration.getProperty("fileUpload.path"));
		}
		
		List<Map<String,Object>> filesMetaInfo = new ArrayList<Map<String,Object>>();
		filesMetaInfo.add(this.getFileReturnMap(file));
		
		return filesMetaInfo;
	}
	
	@RequestMapping(value="form", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> addForm(@RequestParam(value="nombre", required=false) String nombre,
			@RequestParam(value="apellido1", required=false) String apellido1,
			@RequestParam(value="apellido2", required=false) String apellido2,
			@RequestParam(value="file", required=false) MultipartFile file) {
	
		if(!file.isEmpty()){
			uploadService.saveToDisk(file, appConfiguration.getProperty("fileUpload.path"));
		}
		
		List<Map<String,Object>> filesMetaInfo = new ArrayList<Map<String,Object>>();

		filesMetaInfo.add(this.getFileReturnMap(file));
		
		return filesMetaInfo;
		
		
	}
	
	@RequestMapping(value="formSimple", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> addFormSimple(@RequestParam(value="nombre", required=false) String nombre,
			@ModelAttribute UploadBean uploadBean,
			@RequestParam(value="fotoPadre", required=false) MultipartFile fotoPadre,
			@RequestParam(value="fotoMadre", required=false) MultipartFile fotoMadre) {
		
		if(fotoPadre!=null && !fotoPadre.isEmpty()){
			uploadService.saveToDisk(fotoPadre, appConfiguration.getProperty("fileUpload.path"));
		}
		if(fotoMadre!=null && !fotoMadre.isEmpty()){
			uploadService.saveToDisk(fotoMadre, appConfiguration.getProperty("fileUpload.path"));
		}
		
		return null;
	}
	 
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	 public void remove(@RequestParam(value="fileName", required=true) String fileName,
					HttpServletResponse  response) {
		
		uploadService.deleteFromDisk(appConfiguration.getProperty("fileUpload.path"), fileName);
		
		response.setContentType("text/javascript;charset=utf-8");
        response.setHeader("Pragma", "cache");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "private");
        
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.OK )
	 public ModelAndView download(@RequestParam(value="fileName", required=true) String fileName,
	        HttpServletResponse response) throws Exception {
	
		 	File file = uploadService.getFromDisk(appConfiguration.getProperty("fileUpload.path"), fileName);
	
	        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
	        byte[] fileByteArray = FileUtils.readFileToByteArray(file);
	        response.setContentLength(fileByteArray.length);
	        FileCopyUtils.copy(fileByteArray, response.getOutputStream());
	        
	        return null;
	 }
	
	
	private Map<String,Object> getFileReturnMap(MultipartFile file){
		
		Map<String,Object> mapaRetorno = new HashMap<String, Object>();
		
		mapaRetorno.put("url", "../upload?fileName="+file.getOriginalFilename());
		mapaRetorno.put("name", file.getOriginalFilename());
		mapaRetorno.put("type", file.getContentType());
		mapaRetorno.put("size", file.getSize());
		mapaRetorno.put("delete_url", "../upload?fileName="+file.getOriginalFilename());
		mapaRetorno.put("delete_type", "DELETE");
		
		return mapaRetorno;
		
	}
	
}	
	
