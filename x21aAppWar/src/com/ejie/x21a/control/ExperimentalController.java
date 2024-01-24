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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ejie.x21a.model.Buzones;
import com.ejie.x21a.model.IberdokFile;
import com.ejie.x21a.model.RandomForm;
import com.ejie.x21a.util.Constants;
import com.ejie.x21a.util.FileUtils;
import com.ejie.x21a.util.JmsUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.generic.model.SelectGeneric;
import com.ejie.x38.log.LoggingEditor;
import com.ejie.x38.log.model.LogModel;

import n38c.exe.N38API;

/**
 * ExperimentalController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/experimental")
public class ExperimentalController {

	private static final Logger logger = LoggerFactory.getLogger(ExperimentalController.class);

	@Autowired
	private Properties appConfiguration;
	
	//tabsPaging
	@GetMapping(value = "tabsPaging")
	public String getTabsPaging(Model model) {
		return "tabsPaging";
	}
	
	//logLevel
	@GetMapping(value = "logLevel")
	public String getLogLevel(Model model) {
		model.addAttribute("randomForm", new RandomForm());
		
		Map<String,String> comboLevel = new LinkedHashMap<String,String>();
		comboLevel.put("", "---");
		comboLevel.put("TRACE", "TRACE");
		comboLevel.put("DEBUG", "DEBUG");
		comboLevel.put("INFO", "INFO");
		comboLevel.put("WARN", "WARN");
		comboLevel.put("ERROR", "ERROR");
		model.addAttribute("comboLevel", comboLevel);
		
		logger.info("[GET - View] : logLevel");
		return "logLevel";
	}
	
	@PostMapping(value = "/inlineEdit")
	public String getTableInlineEdit (
			@RequestParam(required = false) String pkValue,
			Model model) {
		model.addAttribute(Constants.MODEL_LOGMODEL, new LogModel());
		model.addAttribute(Constants.MODEL_ACTIONTYPE, "PUT");
		model.addAttribute(Constants.MODEL_ENCTYPE, Constants.APPLICATION_URLENCODED);
		
		if (pkValue != null) {
			model.addAttribute(Constants.MODEL_PKVALUE, pkValue);
		}
		
		model.addAttribute(Constants.MODEL_ENDPOINT, "edit");
		
		Map<String,String> comboLevel = new LinkedHashMap<String,String>();
		comboLevel.put("", "---");
		comboLevel.put("TRACE", "TRACE");
		comboLevel.put("DEBUG", "DEBUG");
		comboLevel.put("INFO", "INFO");
		comboLevel.put("WARN", "WARN");
		comboLevel.put("ERROR", "ERROR");
		model.addAttribute("comboLevel", comboLevel);
		
		logger.info("[POST - View] : tableExperimentalInlineEditAuxForm");
		return "tableExperimentalInlineEditAuxForm";
	}
	
	@GetMapping(value = "/name")
	public @ResponseBody List<SelectGeneric> getName(
			@RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "c", required = false) Boolean c) {
		return LoggingEditor.getNames(q);
	}
	
	@GetMapping(value = "/level")
	public @ResponseBody List<SelectGeneric> getLevel() {	
		return LoggingEditor.getLevels();
	}

	//@Json(mixins={@JsonMixin(target=Usuario.class, mixin=UsuarioMixIn.class)})
	@PostMapping(value = "/filter")
	public @ResponseBody TableResponseDto<LogModel> filter(
			@RequestJsonBody(param="filter") LogModel filterLogModel,
			@RequestJsonBody TableRequestDto tableRequestDto) {	
		return LoggingEditor.getLoggersFiltered(filterLogModel, tableRequestDto);
	}
	
	@GetMapping(value = "/{nameLog}")
	public @ResponseBody LogModel get(@PathVariable String nameLog) {
        return LoggingEditor.getLogger(nameLog);
	}
	
	@PutMapping(value = "/edit")
    public @ResponseBody LogModel edit(@Validated @RequestBody LogModel log) {		
		logger.info("logModel edit-init");
		JmsUtils.enviarJMS(log, "x21a.x21aConnectionFactory", "x21a.x21aJMSCacheTopic", null);
//		LoggingEditor.setLogLevel(log.getNameLog(), log.getLevelLog());
//		logger.info("Entity correctly updated!");
//			
//		logger.trace("logLevel trace");
//		logger.debug("logLevel debug");
//		logger.info("logLevel info");
//		logger.warn("logLevel warn");
//		logger.error("logLevel error");
		logger.info("logModel edit-end");

        return log;
    }
		
	//xlnetsTest
	@GetMapping(value = "xlnetsTest")
	public String getXlnetsTest(Model model) {
		return "xlnetsTest";
	}
	
	/**
	 * Obtener buzones personas nivel 3 indicado. Todos los buzones de personas
	 * cuyo nombre contenga el parametro de entrada
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param nombre
	 *            String
	 * @throws Exception
	 *             Exception
	 * @return Buzones Objeto correspondiente al identificador indicado.
	 */
	@GetMapping(value = "/getBuzonesNivel3")
	public @ResponseBody
	List<Buzones> getBuzonesNivel3(HttpServletRequest request,
			@RequestParam(value = "q", required = true) String nombre)
			throws Exception {

		 logger.info("[GET - getBuzonesNivel3] start ");

		List<Buzones> buzones =  new ArrayList<Buzones>();

		// Busco las personas cuyo displayname contenga el string introducido
		buzones = getPersonasNombre(request, nombre);

		 logger.info("[GET - getBuzonesNivel3] end ");
		return buzones;

	}
	
	/**
	 * Devuelve todos los displayname que contengan parte del nombre
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 * @param nombre
	 *            String
	 * 
	 * @return List<Y68bDepartamentos>
	 * 
	 * @throws Exception
	 *             e
	 */
	public  List<Buzones> getPersonasNombre(HttpServletRequest request,
			String nombre) throws Exception {

		logger.info("getPersonasNombre-start:");
		List<Buzones> buzones=null;
		try{
			Document sesion = null;
			N38API miApi = new N38API(request);
			buzones = new ArrayList<Buzones>();

			sesion = miApi.n38ItemObtenerPersonas("displayname=*" + nombre + "*");// uid	
			buzones = obtenerBuzonesPersonasUT(sesion);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getPersonasNombre-end:");
		return buzones;

	}
	
	/**
	 * Obtiene los buzones personales para el filtro aplicado previamente
	 * 
	 * @param sesion
	 *            Document con los buzones personales para el filtro aplicado
	 * 
	 * @return List<Buzon>
	 */
	private List<Buzones> obtenerBuzonesPersonasUT(Document sesion) {

		List<Buzones> buzones = new ArrayList<Buzones>();

		try {
			String lFiltro = "//elementos[@tipo='n38ItemObtenerPersonas']/elemento[@subtipo='n38persona']";
			NodeList nodeList = XPathAPI.selectNodeList(sesion, lFiltro);
			Element element = null;
			NodeList objsegList = null;
			NodeList cadenaEsList = null;
			Buzones buzon = null;
			String nameEs = null;
			String idbuzon = null;
			String nameCapsEs = null;
			for (int j = 0; j < nodeList.getLength(); j++) {
				element = (Element) nodeList.item(j);
				lFiltro = "parametro[@id='n38puestouid']/valor/text()";
				objsegList = XPathAPI.selectNodeList(element, lFiltro);
				lFiltro = "parametro[@id='displayname']/valor/text()";
				cadenaEsList = XPathAPI.selectNodeList(element, lFiltro);
				if (cadenaEsList.getLength() > 0 /* && cadenaEuList.getLength()>0 */) {
					nameEs = cadenaEsList.item(0).getNodeValue();
					idbuzon = objsegList.item(0).getNodeValue();
					nameCapsEs = nameEs.toUpperCase();
					buzon = new Buzones(); // NOPMD
					buzon.setIdbuzonT28(idbuzon);
					buzon.setNomBuzonCastT28(nameCapsEs);
					buzon.setNomBuzonEuskT28(nameCapsEs);
					buzones.add(buzon);
				}
			}
		} catch (Exception e) {
			StringBuffer stbTraza = new StringBuffer("30");
			stbTraza.append("obtenerBuzonesPersonas: ERROR:");
			stbTraza.append(e.getMessage());
		}
		return buzones;
	}

/***********************************************IBERDOK*************************************/

		
	@RequestMapping(value = "/urlFinalizacion")
	public String urlFinalizacion(@RequestBody IberdokFile file,
		 //@RequestParam(value = "idModelo") String idModelo) {
		 @RequestParam(value = "idModelo") String idModelo,
		 @RequestParam(value = "idCorrelacion") String nombre) {
		logger.info("urlFinalizacion-start");
		logger.info("urlFinalizacion-idDOcumento: " + file.getIdDocumento());
		logger.info("urlFinalizacion-file: " + file.getFile());
		logger.info("urlFinalizacion-modelo: " + file.getIdModelo());
		logger.info("urlFinalizacion-idModelo: " + idModelo);

		// path de la carpeta donde se guardan los ficheros
		String iberdokFolderPath = appConfiguration
				.getProperty("iberdokFolder");
		// path del fichero
		String zipPath = null;

		IberdokFile auxFile = new IberdokFile();
		auxFile.setIdDocumento(file.getIdDocumento());
		auxFile.setIdModelo(idModelo);
		auxFile.setNombre(nombre);

		if (file.getFile() != "") {
			logger.info("documento Finalizado");
			// documento finalizado
			auxFile.setDocFinalizado(1);

			zipPath = iberdokFolderPath + "/" + file.getIdDocumento() + ".zip";
			// guardar el zip de iberdok en el path del properties iberdokFolder
			FileUtils.saveFile(file.getFile(), zipPath);
			// crear carpeta si no existe
			String outputUnzipFolder = createFolderIfNotExists(
					file.getIdDocumento(), iberdokFolderPath);

			// descomprimir el zip
			File unzipFile = new File(zipPath);
			FileUtils.unzipFile(unzipFile, outputUnzipFolder);

		} else {
			// documento en elaboracion
			logger.info("documento Finalizado");
			auxFile.setDocFinalizado(0);
		}

		// check if the file already exists
	/*	if (this.iberdokFileService.existsFile(auxFile)) {
			logger.info("documento existente, actualizar datos en bd");
			// if exists, update estado
			this.iberdokFileService.updateIdDocumento(auxFile);
		} else {
			logger.info("documento NO existente, nuevo registro en bd");
			// else update the last record created
			this.iberdokFileService.add(auxFile);
		}*/

		logger.info("urlFinalizacion-end");
		return "iberdok";
	}
	
	/**
	 * Funci�n comprueba si existe una carpeta con el nombre del fichero y en
	 * caso contrario la crea, deuelve el path de la carpeta
	 * 
	 * @param idDocumento
	 *            idDocumento del fichero
	 * @param fileFolder
	 *            path de la carpeta donde deberia estar el fichero
	 * 
	 * @return String path of the folder
	 */
	private String createFolderIfNotExists(String idDocumento, String fileFolder) {
		logger.info("createFolderIfNotExists-start");

		// create output directory is not exists
		File folderIdDocumento = new File(fileFolder + "/" + idDocumento);
		if (!folderIdDocumento.exists()) {
			folderIdDocumento.mkdir();
		}

		logger.info("createFolderIfNotExists-end");
		return folderIdDocumento.getPath();
	}

		
/***********************************************************PRUEBAS URL FINALIZACION*******************************/
/**							                                                                                   ***/
/*****************************************************************************************************************/
	@RequestMapping(value = "/urlPruebaConDosParamatros")
	public String urlFinalizacion(@RequestBody String file ,@RequestBody String idModelo){
		logger.info("urlUrko-start");
		//logger.info("urlUrko-idDOcumento: " + file.getIdDocumento());
		logger.info("urlUrko-file: " + file);
		logger.info("urlUrko-modelo: " + idModelo);
//		logger.info("urlUrko-idModelo: " + idModelo);

		// path de la carpeta donde se guardan los ficheros
		String iberdokFolderPath = appConfiguration
				.getProperty("iberdokFolder");
		// path del fichero
		String zipPath = null;

		IberdokFile auxFile = new IberdokFile();
		//auxFile.setIdDocumento(file.getIdDocumento());
		auxFile.setIdModelo(idModelo);
//			auxFile.setNombre(nombre);

//			if (file != "") {
//				logger.info("documento Finalizado");
//				// documento finalizado
//				auxFile.setEstado("2");

//				zipPath = iberdokFolderPath + "/" + file.getIdDocumento() + ".zip";
			// guardar el zip de iberdok en el path del properties iberdokFolder
//				FileUtils.saveFile(file.getFile(), zipPath);
			// crear carpeta si no existe
//				String outputUnzipFolder = createFolderIfNotExists(
//						file.getIdDocumento(), iberdokFolderPath);

//				// descomprimir el zip
//				File unzipFile = new File(zipPath);
//				FileUtils.unzipFile(unzipFile, outputUnzipFolder);
//
//			} else {
//				// documento en elaboracion
//				logger.info("documento Finalizado");
//				auxFile.setEstado("1");
//			}
//
//			// check if the file already exists
//			if (this.iberdokFileService.existsFile(auxFile)) {
//				logger.info("documento existente, actualizar datos en bd");
//				// if exists, update estado
//				this.iberdokFileService.updateIdDocumento(auxFile);
//			} else {
//				logger.info("documento NO existente, nuevo registro en bd");
//				// else update the last record created
//				this.iberdokFileService.add(auxFile);
//			}

		logger.info("urlUrko-end");
		return "iberdok";
	}
	
	@RequestMapping(value = "/urlDemo")
	public String urlDemo(@RequestBody IberdokFile file,
			@RequestParam(value = "idModelo") String idModelo) {
		logger.info("urlDemo-start");
		logger.info("urlDemo-idDOcumento: " + file.getIdDocumento());
		logger.info("urlDemo-file: " + file.getFile());
		logger.info("urlDemo-modelo: " + file.getIdModelo());
		logger.info("urlDemo-idModelo: " + idModelo);

		// path de la carpeta donde se guardan los ficheros
		String iberdokFolderPath = appConfiguration
				.getProperty("iberdokFolder");
		// path del fichero
		String zipPath = null;

		IberdokFile auxFile = new IberdokFile();
		auxFile.setIdDocumento(file.getIdDocumento());
		auxFile.setIdModelo(idModelo);
//		auxFile.setNombre(nombre);

		if (file.getFile() != "") {
			logger.info("documento Finalizado");
			// documento finalizado
			auxFile.setDocFinalizado(1);

			zipPath = iberdokFolderPath + "/" + file.getIdDocumento() + ".zip";
			// guardar el zip de iberdok en el path del properties iberdokFolder
			FileUtils.saveFile(file.getFile(), zipPath);
			// crear carpeta si no existe
			String outputUnzipFolder = createFolderIfNotExists(
					file.getIdDocumento(), iberdokFolderPath);

			// descomprimir el zip
			File unzipFile = new File(zipPath);
			FileUtils.unzipFile(unzipFile, outputUnzipFolder);

		} else {
			// documento en elaboracion
			logger.info("documento Finalizado");
			auxFile.setDocFinalizado(0);
		}

		// check if the file already exists
/*		if (this.iberdokFileService.existsFile(auxFile)) {
			logger.info("documento existente, actualizar datos en bd");
			// if exists, update estado
			this.iberdokFileService.updateIdDocumento(auxFile);
		} else {
			logger.info("documento NO existente, nuevo registro en bd");
			// else update the last record created
			this.iberdokFileService.add(auxFile);
		}
*/
		logger.info("urlDemo-end");
		return "iberdok";
	}
	
	@RequestMapping(value = "/urlTest")
	public String urlTest(@RequestBody IberdokFile file) {
		logger.info("urlTest-start");
		logger.info("urlTest-idDOcumento: " + file.getIdDocumento());
		logger.info("urlTest-file: " + file.getFile());
		logger.info("urlTest-modelo: " + file.getIdModelo());
//		logger.info("urlDemo-idModelo: " + idModelo);

		// path de la carpeta donde se guardan los ficheros
		String iberdokFolderPath = appConfiguration
				.getProperty("iberdokFolder");
		// path del fichero
		String zipPath = null;

		IberdokFile auxFile = new IberdokFile();
		auxFile.setIdDocumento(file.getIdDocumento());
//			auxFile.setIdModelo(idModelo);
//			auxFile.setNombre(nombre);

		if (file.getFile() != "") {
			logger.info("documento Finalizado");
			// documento finalizado
			auxFile.setDocFinalizado(1);

			zipPath = iberdokFolderPath + "/" + file.getIdDocumento() + ".zip";
			// guardar el zip de iberdok en el path del properties iberdokFolder
			FileUtils.saveFile(file.getFile(), zipPath);
			// crear carpeta si no existe
			String outputUnzipFolder = createFolderIfNotExists(
					file.getIdDocumento(), iberdokFolderPath);

			// descomprimir el zip
			File unzipFile = new File(zipPath);
			FileUtils.unzipFile(unzipFile, outputUnzipFolder);

		} else {
			// documento en elaboracion
			logger.info("documento Finalizado");
			auxFile.setDocFinalizado(0);
		}

		// check if the file already exists
/*		if (this.iberdokFileService.existsFile(auxFile)) {
			logger.info("documento existente, actualizar datos en bd");
			// if exists, update estado
			this.iberdokFileService.updateIdDocumento(auxFile);
		} else {
			logger.info("documento NO existente, nuevo registro en bd");
			// else update the last record created
			this.iberdokFileService.add(auxFile);
		}
*/
		logger.info("urlTest-end");
		return "iberdok";
	}
}
