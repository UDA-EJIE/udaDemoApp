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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.UsuarioService;


/**
 * UploadController
 * @author UDA
 */
@Controller
@RequestMapping (value = "/patrones/usuario")
public class UsuarioController   {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	/**
	 * Tiempo que se mantienen, en un directorio temporal, los documentos que se
	 * quieren fusionar. En segundos.
	 */
	public static final Long TIEMPO_TTL_DOCUMENTO_TEMP = Long.valueOf(600);// 10 minutos
	
	@Autowired
	private Properties appConfiguration;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Usuario> get(HttpServletRequest request) {
		logger.debug("Entrando en método: get");
		this.traceRequest(request);
        List<Usuario> listaUsuarios = this.usuarioService.findAll(new Usuario(), null);
        logger.debug("Usuarios recuperados correctamente");
        return listaUsuarios;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Usuario getById(@PathVariable String id, HttpServletRequest request) {
		logger.debug("Entrando en método: getById");
		logger.debug("Parametro de url (id): "+id);
		this.traceRequest(request);
        Usuario usuario = new Usuario(id);
        logger.debug("Usuario recuperado correctamente");
        return  this.usuarioService.find(usuario);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Usuario put(@RequestBody Usuario usuario, HttpServletRequest request) {
		logger.debug("Entrando en método: put");
		logger.debug("Usuario: "+usuario.toString());
		this.traceRequest(request);
        Usuario updatedUsuario = this.usuarioService.update(usuario);
        logger.debug("Usuario actualizado correctamente");
        return updatedUsuario;
         
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Usuario post(@RequestBody Usuario usuario, HttpServletRequest request) {
		logger.debug("Entrando en método: post");
		logger.debug("Usuario: "+usuario.toString());
		this.traceRequest(request);
        Usuario addedUsuario = this.usuarioService.add(usuario);
        logger.debug("Usuario insertado correctamente");
        return addedUsuario;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Usuario delete(@PathVariable String id, HttpServletRequest request) {
		logger.debug("Entrando en método: delete");
		logger.debug("Parametro de url (id): "+id);
		this.traceRequest(request);
        Usuario usuario = new Usuario(id);
        this.usuarioService.remove(usuario);
        logger.debug("Usuario eliminado correctamente");
        
        return usuario;
	}
	
	
	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {
 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
	
	@SuppressWarnings("rawtypes")
	private void traceRequest (HttpServletRequest request){
		
		logger.info("************************************************************");
		logger.info("******************INICIO REQUEST LOG********************");
		logger.info("************************************************************");
		
			
		logger.info("-----> Request content");
		try {
			logger.info(getStringFromInputStream(request.getInputStream()));
		} catch (IOException e) {
			logger.error("ERROR "+e.getMessage());
			logger.info("ERROR "+e.getMessage());
			e.printStackTrace();
		}
			
	
		logger.info("-----> Protocol : " + request.getProtocol());
		logger.info("-----> Method : " + request.getMethod());
		logger.info("-----> RequestURL : " + request.getRequestURL());
		
		logger.info("******** ATRIBUTES *************");
		
		Enumeration attributeNames = request.getAttributeNames();
		
		
		while(attributeNames.hasMoreElements()){
			String nextElement = (String) attributeNames.nextElement();
			try{
				logger.info("----->" +nextElement +" : " + request.getAttribute(nextElement).toString());
			}catch(Exception e){
				logger.info("**ERROR** al escribir el atributo "+nextElement);
			}
		}

		
		logger.info("******** PARAMETERS *************");
		
		Map parameterMap = request.getParameterMap();
		
		Set keySet = parameterMap.keySet();
		
		for (Object key : keySet) {
			try{
				logger.info("----->" +key +" : " + request.getParameter((String)key));
			}catch(Exception e){
				logger.info("**ERROR** al escribir el parámetro "+key);
			}
//			for (int i = 0; i < array.length; i++) {
//				logger.info("----->" +key +" : " + ((String[])request.getParameterValues(key).get(key))[0].toString());
//			}
//			
//			logger.info("----->" +key +" : " + ((String[])request.getParameterValues(name).get(key))[0].toString());
		}
		
		logger.info("******** HEADERS *************");
		Enumeration headerNames = request.getHeaderNames();
		
		while(headerNames.hasMoreElements()){
			String nextElement = (String) headerNames.nextElement();
			try{
				logger.info("----->" +nextElement +" : " + request.getHeader(nextElement));
			}catch(Exception e){
				logger.info("**ERROR** al escribir el header "+nextElement);
			}
		}

		logger.info("************************************************************");
		logger.info("******************END REQUEST LOG********************");
		logger.info("************************************************************");
	}
	
}	
	
