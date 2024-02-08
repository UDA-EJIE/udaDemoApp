package com.ejie.x21a.control;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x21a.model.RandomForm;
import com.ejie.x21a.service.IberdokFileService;
import com.ejie.x21a.util.FileUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.security.XlnetCore;

import n38c.exe.N38API;

@Controller
@RequestMapping(value = "/iberdok")
public class IberdokController {

	@Autowired
	private Properties appConfiguration;

	private static final Logger logger = LoggerFactory.getLogger(IberdokController.class);

	@Autowired
	private IberdokFileService iberdokFileService;

	/*
	 * OPERACIONES CRUD
	 * 
	 * Metodos correspondientes a las operaciones CRUD (Create, Read, Update, Delete).
	 */

	/**
	 * Operación CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea recuperar.
	 * @return Objeto correspondiente al identificador indicado.
	 */
	@GetMapping(value = "/{id}")
	public @ResponseBody IberdokFile get(@PathVariable String id,HttpServletResponse response) {
		IberdokFile file = new IberdokFile();
		file.setId(id);
		file = this.iberdokFileService.find(file);
		IberdokController.logger.info("[GET - findBy_PK] : Obtener ficheros de iberdok por PK");
		
		return file;
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parámetro.
	 * 
	 * @param IberdokFile
	 *            Objeto que contiene los parï¿½metros de filtrado utilizados en
	 *            la búsqueda.
	 * @return Lista de objetos correspondientes a la búsqueda realizada.
	 */
	@GetMapping
	public @ResponseBody List<IberdokFile> getAll(
			@ModelAttribute() IberdokFile fileFilter) {
		IberdokController.logger.info("[GET - find_ALL] : Obtener ficheros de iberdok por filtro");
		return this.iberdokFileService.findAllLike(fileFilter, null, false);
	}

	/**
	 * Operación CRUD Edit. Modificación del bean indicado.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene la información a modificar.
	 * @return Bean resultante de la modificación.
	 */
	@PutMapping(value = "/edit")
	public @ResponseBody IberdokFile edit(
			@Validated @RequestBody IberdokFile file, HttpServletRequest request) {
		try{
			N38API miApi = new N38API(request);
			Document miDoc = miApi.n38ItemSesion();
			String sUsu = DatoSesion(miDoc, "n38login");
			file.setUsuario(sUsu);
		}catch(Exception e){
			e.printStackTrace();
		}

		IberdokFile fileAux = this.iberdokFileService.update(file);
		logger.info("Entity correctly updated!");
		return fileAux;
	}

	/**
	 * Operación CRUD Create. Creación de un nuevo registro a partir del bean
	 * indicado.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene la información con la que se va a crear el
	 *            nuevo registro.
	 * @return Bean resultante del proceso de creación.
	 */
	@PostMapping(value = "/add")
	public @ResponseBody IberdokFile add(
			@Validated @RequestBody IberdokFile file) {

		IberdokFile fileAux = this.iberdokFileService.add(file);
		logger.info("Entity correctly inserted!");
		return fileAux;
	}

	/**
	 * Operación CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea eliminar.
	 * @return Bean eliminado.
	 */
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody IberdokFile remove(
			@PathVariable(value = "id") String id, HttpServletResponse response) {
		IberdokFile file = new IberdokFile();
		file.setId(id);
		this.iberdokFileService.remove(file);
		logger.info("Entity correctly deleted!");
		return file;
	}

	/*
	 * METODOS COMPONENTE RUP_TABLE
	 */

	/**
	 * Operaciï¿½n de filtrado del componente RUP_TABLE.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	// @Json(mixins={@JsonMixin(target=IberdokFile.class,
	// mixin=IberdokFileMixIn.class)})
	@PostMapping(value = "/filter")
	public @ResponseBody TableResponseDto<IberdokFile> filter(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		IberdokController.logger.info("[POST - table] : Obtener IberdokFiles");
		return iberdokFileService.filter(filterIberdokFile, tableRequestDto, false);
	}

	/**
	 * Operación de búsqueda del componente RUP_TABLE.
	 * 
	 * @param filterIberdokFile
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param searchIberdokFile
	 *            Bean que contiene los parámetros de bï¿½squeda a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en la búqueda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los parámetros de búsqueda.
	 * 
	 */
	@PostMapping(value = "/search")
	public @ResponseBody List<TableRowDto<IberdokFile>> search(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody(param = "search") IberdokFile searchIberdokFile,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		IberdokController.logger.info("[POST - search] : Buscar IberdokFiles");
		return iberdokFileService.search(filterIberdokFile, searchIberdokFile, tableRequestDto, false);
	}

	/**
	 * Borrado múltiple de registros
	 * 
	 * @param filterIberdokFile
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return Lista de los identificadores de los registros eliminados.
	 */
	@PostMapping(value = "/deleteAll")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		IberdokController.logger
				.info("[POST - removeMultiple] : Eliminar multiples documentos iberdok");
		this.iberdokFileService.removeMultiple(filterIberdokFile, tableRequestDto, false);
		IberdokController.logger.info("All entities correctly deleted!");

		return tableRequestDto.getMultiselection().getSelectedIds();
	}

	// Iberdok
	@GetMapping(value = "view")
	public String getIberdok(Model model, HttpServletRequest request, HttpServletResponse response) {

		// anadimos los datos de configuracion de iberdok al cliente desde el
		// properties

		model.addAttribute("idUsuario",
				appConfiguration.getProperty("iberdok.idUsuario"));
		model.addAttribute("urlFinalizacion",
				appConfiguration.getProperty("iberdok.urlFinalizacion"));
		model.addAttribute("urlRetorno",
				appConfiguration.getProperty("iberdok.urlRetorno"));
		model.addAttribute("idModelo",
				appConfiguration.getProperty("iberdok.idModelo"));
		model.addAttribute("token",
				appConfiguration.getProperty("iberdok.token"));
		model.addAttribute("lang", appConfiguration.getProperty("iberdok.lang"));
		model.addAttribute("urlNuevoDocumento",
				appConfiguration.getProperty("iberdok.urlNuevoDocumento"));
		model.addAttribute("urlEditarDocumento",
				appConfiguration.getProperty("iberdok.urlEditarDocumento"));
		model.addAttribute("urlEditorDocumentos",
				appConfiguration.getProperty("iberdok.urlEditorDocumentos"));

		model.addAttribute("randomForm", new RandomForm());
		
		response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
		
		return "iberdok";
	}
	
	// Iberdok
	@GetMapping(value = "iberdokWelcome")
	public String getIberdokWelcome(Model model, HttpServletRequest request, HttpServletResponse response) {
		String udaXLNetsSessionId = XlnetCore.getN38ItemSesion(XlnetCore.getN38API(request), "n38UidSesion");
		// anadimos los datos de configuracion de iberdok al cliente desde el
		// properties

		model.addAttribute("idUsuario",
				appConfiguration.getProperty("iberdok.idUsuario"));
		model.addAttribute("urlFinalizacion",
				appConfiguration.getProperty("iberdok.urlFinalizacion"));
		model.addAttribute("urlRetorno",
				appConfiguration.getProperty("iberdok.urlRetorno"));
		model.addAttribute("idModelo",
				appConfiguration.getProperty("iberdok.idModelo"));
		model.addAttribute("token",
				appConfiguration.getProperty("iberdok.token"));
		model.addAttribute("lang", appConfiguration.getProperty("iberdok.lang"));
		model.addAttribute("urlNuevoDocumento",
				appConfiguration.getProperty("iberdok.urlNuevoDocumento"));
		model.addAttribute("urlEditarDocumento",
				appConfiguration.getProperty("iberdok.urlEditarDocumento"));
		model.addAttribute("urlEditorDocumentos",
				appConfiguration.getProperty("iberdok.urlEditorDocumentos"));

		model.addAttribute("randomForm", new RandomForm());
		
		response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
		if(udaXLNetsSessionId != null) {
			try {
				response.sendRedirect("view");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "iberdokWelcome";
	}
	
	// Iberdok
	@GetMapping(value = "viewIberdok")
	public String viewIberdok(Model model,HttpServletResponse response,@RequestParam(required = false) String idCorrelacion,@RequestParam(required = false) String idModelo) {

		// anadimos los datos de configuracion de iberdok al cliente desde el
		// properties

		model.addAttribute("idUsuario",
				appConfiguration.getProperty("iberdok.idUsuario"));
		model.addAttribute("urlFinalizacion",
				appConfiguration.getProperty("iberdok.urlFinalizacion"));
		model.addAttribute("urlRetorno",
				appConfiguration.getProperty("iberdok.urlRetorno"));
		model.addAttribute("idModelo",
				appConfiguration.getProperty("iberdok.idModelo"));
		model.addAttribute("token",
				appConfiguration.getProperty("iberdok.token"));
		model.addAttribute("lang", appConfiguration.getProperty("iberdok.lang"));
		model.addAttribute("urlNuevoDocumento",
				appConfiguration.getProperty("iberdok.urlNuevoDocumento"));
		model.addAttribute("urlEditarDocumento",
				appConfiguration.getProperty("iberdok.urlEditarDocumento"));
		model.addAttribute("urlEditorDocumentos",
				appConfiguration.getProperty("iberdok.urlEditorDocumentos"));
		RandomForm random = new RandomForm();
		String idDocumento = "";
		try{
			if(idModelo != null || idCorrelacion != null){
				IberdokFile file = iberdokFileService.findLastByIdCorrelacion(idModelo,idCorrelacion);
				idDocumento = file.getIdDocumento();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		random.setIdDocumento(idDocumento);
		model.addAttribute("randomForm", random);
		model.addAttribute("idDocumento", idDocumento);
		response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
		return "iberdok";
	}
	
	@RequestMapping(value="/urlFinalizacion",consumes = MediaType.APPLICATION_JSON_VALUE)  //this url should map which you configured in step 5
    public void success(@RequestBody(required = false) IberdokFile bean, HttpServletRequest request) throws Exception{
		
		IberdokController.logger.info("[POST - iber/succes] : ENTRO AL SUCCESS3 con paramas");
       
        if(bean != null){
        	
        	IberdokController.logger.info("[POST - iber/succes] : ENTRO AL SUCCESS3 con paramas");
        	IberdokController.logger.info("[POST - iber/succes] : " + bean.toString());
  
        	//FAlta semilla e idModelo
        	
        	bean.setNombre(bean.getIdCorrelacion());
			if(bean.getIdDocumento() != null){
				IberdokFile fileSearch = new IberdokFile();
				fileSearch.setIdDocumento(bean.getIdDocumento());
				String idRecibido = iberdokFileService.existsFile(fileSearch );
				IberdokController.logger.info("[POST - iber/ADDoUPDATE] : " + idRecibido);
				try{
					N38API miApi = new N38API(request);
					Document miDoc = miApi.n38ItemSesion();
					String sUsu = DatoSesion(miDoc, "n38login");
					bean.setUsuario(sUsu);
				}catch(Exception e){
					e.printStackTrace();
				}
				Timestamp ts=new Timestamp(new Date().getTime());  
				bean.setFechaIberdok(ts);
				if(idRecibido != null){
					bean.setId(idRecibido);
					iberdokFileService.update(bean );
				}else{
					iberdokFileService.add(bean );
				}
				IberdokController.logger.info("[POST - iber/GUARDO] : ");
				//Guardar elzip
				if(bean.getFile() != null){
					IberdokController.logger.info("[POST - iber/GUARDOZIP : ");
					String iberdokFolderPath = appConfiguration.getProperty("iberdokFolder");
					String zipPath = iberdokFolderPath + "/" + bean.getIdDocumento() + ".zip";
					FileUtils.saveFile(bean.getFile(), zipPath);
					
					// crear carpeta si no existe
					String outputUnzipFolder = createFolderIfNotExists(
							bean.getIdDocumento(), iberdokFolderPath);

					// descomprimir el zip
					File unzipFile = new File(zipPath);
					FileUtils.unzipFile(unzipFile, outputUnzipFolder);
					IberdokController.logger.info("[POST - iber/FIN_GUARDOZIP : ");
				}
			}
        }
        
        
	}
	
	private String DatoSesion(Document miDoc, String tag) {
		NodeList elemento = null;
		try {
			elemento = XPathAPI.selectNodeList(miDoc,
					"//elementos/elemento/parametro[@id=\"" + tag + "\"]/valor/text()");
		} catch (Exception e) {
			logger.error("DatoSesion: "+tag);
			return null;
		}
		if (null == elemento) { 
								
								
			return null;
		}
		if (1 != elemento.getLength()) { 
											
			return null;
		}
		return elemento.item(0).getNodeValue();
	}
	
	@PostMapping(value = "/editForm")
	public String getTableEditForm (
			@RequestParam(required = true) String actionType,
			Model model, HttpServletResponse response) {
		model.addAttribute("randomForm", new RandomForm());
		model.addAttribute("actionType", actionType);
		
		Map<String,String> lang = new LinkedHashMap<String,String>();
		lang.put("es", "Castellano");
		lang.put("eu", "Euskera");
		lang.put("en", "Inglés");
		model.addAttribute("lang", lang);
		
		Map<String,String> modo = new LinkedHashMap<String,String>();
		modo.put("1", "Crear documento");
		modo.put("2", "Editar documento en elaboración con xhtml");
		modo.put("7", "Editar documento no finalizado");
		modo.put("8", "Copiar documento");
		model.addAttribute("modo", modo);
		
		return "iberdokEditForm";
	}

	/**
	 * Dado un idDocumento obtiene el xhtml alojado en weblogic
	 * 
	 *
	 */
	@GetMapping(value = "/getXhtml")
	@ResponseBody
	public String getXhtml(
			@RequestParam(value = "idDocumento", required = true) String idDocumento) throws IOException {
		logger.info("getXtml-start");
		String fichero64 = null;
		String folderIberdokPath = appConfiguration
				.getProperty("iberdokFolder") + "/" + idDocumento;
		String filePath = folderIberdokPath + "/" + idDocumento + ".xhtml";

		File file = new File(filePath);

		fichero64 = FileUtils.encode64File(file);
		logger.info("getXtml-end");
		return fichero64;
	}

	/**
	 * Dado un idDocumento obtiene el pdf alojado en weblogic
	 * 
	 *
	 */
	@GetMapping(value = "/getPdf")
	@ResponseBody
	public void getPDF(
			@RequestParam(value = "idDocumento", required = true) String idDocumento,
			HttpServletResponse response) {

		logger.info("getPdf-start");

		String folderIberdokPath = appConfiguration
				.getProperty("iberdokFolder") + "/" + idDocumento;
		String filePath = folderIberdokPath + "/" + idDocumento + ".pdf";

		File file = new File(filePath);

		byte[] bytes = FileUtils.readFile(file);

		response.setContentType("application/pdf");

		response.setContentLength(bytes.length);

		try {
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error(e.getCause().toString());
		}

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


}
