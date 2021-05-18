package com.ejie.x21a.control;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x21a.model.RandomForm;
import com.ejie.x21a.service.IberdokFileService;
import com.ejie.x21a.util.FileUtils;
import com.ejie.x21a.util.ResourceUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;

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
	 * Operaciï¿½n CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea recuperar.
	 * @return Objeto correspondiente al identificador indicado.
	 */
	@UDALink(name = "get", linkTo = { @UDALinkAllower(name = "edit"), @UDALinkAllower(name = "remove"), @UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resource<IberdokFile> get(@PathVariable String id) {
		IberdokFile file = new IberdokFile();
		file.setId(id);
		file = this.iberdokFileService.find(file);
		IberdokController.logger.info("[GET - findBy_PK] : Obtener ficheros de iberdok por PK");
		return new Resource<IberdokFile>(file);
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parï¿½metro.
	 * 
	 * @param IberdokFile
	 *            Objeto que contiene los parï¿½metros de filtrado utilizados en
	 *            la bï¿½squeda.
	 * @return Lista de objetos correspondientes a la bï¿½squeda realizada.
	 */
	@UDALink(name = "getall", linkTo = { @UDALinkAllower(name = "edit" ), @UDALinkAllower(name = "remove" ), @UDALinkAllower(name = "get" )})
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Resource<IberdokFile>> getAll(
			@ModelAttribute() IberdokFile fileFilter) {
		IberdokController.logger.info("[GET - find_ALL] : Obtener ficheros de iberdok por filtro");
		return ResourceUtils.fromListToResource(this.iberdokFileService.findAllLike(fileFilter, null, false));
	}

	/**
	 * Operaciï¿½n CRUD Edit. Modificaciï¿½n del bean indicado.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene la informaciï¿½n a modificar.
	 * @return Bean resultante de la modificaciï¿½n.
	 */
	@UDALink(name = "edit", linkTo = { @UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public @ResponseBody Resource<IberdokFile> edit(
			@Validated @RequestBody IberdokFile file) {

		IberdokFile fileAux = this.iberdokFileService.update(file);
		logger.info("Entity correctly updated!");
		return new Resource<IberdokFile>(fileAux);
	}

	/**
	 * Operaciï¿½n CRUD Create. Creaciï¿½n de un nuevo registro a partir del bean
	 * indicado.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene la informaciï¿½n con la que se va a crear el
	 *            nuevo registro.
	 * @return Bean resultante del proceso de creaciï¿½n.
	 */
	@UDALink(name = "add", linkTo = { @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Resource<IberdokFile> add(
			@Validated @RequestBody IberdokFile file) {

		IberdokFile fileAux = this.iberdokFileService.add(file);
		logger.info("Entity correctly inserted!");
		return new Resource<IberdokFile>(fileAux);
	}

	/**
	 * Operaciï¿½n CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea eliminar.
	 * @return Bean eliminado.
	 */
	@UDALink(name = "remove")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Resource<IberdokFile> remove(
			@PathVariable(value = "id") String id, HttpServletResponse response) {
		IberdokFile file = new IberdokFile();
		file.setId(id);
		this.iberdokFileService.remove(file);
		logger.info("Entity correctly deleted!");
		return new Resource<IberdokFile>(file);
	}

	/*
	 * METODOS COMPONENTE RUP_TABLE
	 */

	/**
	 * Operaciï¿½n de filtrado del componente RUP_TABLE.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene los parï¿½metros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parï¿½mtros de configuraciï¿½n propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	// @Json(mixins={@JsonMixin(target=IberdokFile.class,
	// mixin=IberdokFileMixIn.class)})
	@UDALink(name = "filter", linkTo = { @UDALinkAllower(name = "get"), @UDALinkAllower(name = "remove"), @UDALinkAllower(name = "filter"), @UDALinkAllower(name = "deleteAll")})
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResourceResponseDto<IberdokFile> filter(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		IberdokController.logger.info("[POST - table] : Obtener IberdokFiles");
		return iberdokFileService.filter(filterIberdokFile, tableRequestDto, false);
	}

	/**
	 * Operaciï¿½n de bï¿½squeda del componente RUP_TABLE.
	 * 
	 * @param filterIberdokFile
	 *            Bean que contiene los parï¿½metros de filtrado a emplear.
	 * @param searchIberdokFile
	 *            Bean que contiene los parï¿½metros de bï¿½squeda a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parï¿½mtros de configuraciï¿½n propios del
	 *            RUP_TABLE a aplicar en la bï¿½squeda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los parï¿½metros de bï¿½squeda.
	 * 
	 */
	@UDALink(name = "search", linkTo = { @UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<IberdokFile>> search(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody(param = "search") IberdokFile searchIberdokFile,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		IberdokController.logger.info("[POST - search] : Buscar IberdokFiles");
		return iberdokFileService.search(filterIberdokFile, searchIberdokFile, tableRequestDto, false);
	}

	/**
	 * Borrado mï¿½ltiple de registros
	 * 
	 * @param filterIberdokFile
	 *            Bean que contiene los parï¿½metros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parï¿½mtros de configuraciï¿½n propios del
	 *            RUP_TABLE a aplicar en la bï¿½squeda.
	 * @return Lista de los identificadores de los registros eliminados.
	 */
	@UDALink(name = "deleteAll")
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
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
	@UDALink(name = "getFiltroSimple", linkTo = {
			@UDALinkAllower(name = "getTableEditForm"),
			@UDALinkAllower(name = "deleteAll"),
			@UDALinkAllower(name = "getMultiFilterForm"),
			@UDALinkAllower(name = "filter"),
			@UDALinkAllower(name = "getXhtml"),
			@UDALinkAllower(name = "getPDF") })
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String getIberdok(Model model) {

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
		return "iberdok";
	}
	
	@UDALink(name = "getTableEditForm", linkTo = {
			@UDALinkAllower(name = "get"),
			@UDALinkAllower(name = "add"),
			@UDALinkAllower(name = "edit"),
			@UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/editForm", method = RequestMethod.POST)
	public String getTableEditForm (
			@RequestParam(required = true) String actionType,
			@RequestParam(required = false) String fixedMessage,
			Model model) {
		model.addAttribute("randomForm", new RandomForm());
		model.addAttribute("actionType", actionType);
		if (fixedMessage != null) {
			model.addAttribute("fixedMessage", fixedMessage);
		}
		
		Map<String,String> lang = new LinkedHashMap<String,String>();
		lang.put("es", "Castellano");
		lang.put("eu", "Euskera");
		lang.put("en", "Inglés");
		model.addAttribute("lang", lang);
		
		Map<String,String> modo = new LinkedHashMap<String,String>();
		modo.put("1", "Crear documento");
		modo.put("2", "Editar documento finalizado");
		modo.put("7", "Editar documento no finalizado");
		modo.put("8", "Copiar documento");
		model.addAttribute("modo", modo);
		
		return "iberdokEditForm";
	}

	/** url demo para probar en local la url de finalizacion */
	// @RequestMapping(value = "/urlDemo", method = RequestMethod.POST)
	// public String urlDemo(@RequestBody IberdokFile file) {
	// logger.info("urlFinalizacion-start");
	// logger.info("urlFinalizacion-idDOcumento: " + file.getIdDocumento());
	// logger.info("urlFinalizacion-file: " + file.getFile());
	// logger.info("urlFinalizacion-modelo: " + file.getIdModelo());
	//
	// // path de la carpeta donde se guardan los ficheros
	// String iberdokFolderPath = appConfiguration
	// .getProperty("iberdokFolder");
	// // path del fichero
	// String zipPath = null;
	//
	// IberdokFile auxFile = new IberdokFile();
	// auxFile.setIdDocumento(file.getIdDocumento());
	// // auxFile.setIdModelo(idModelo);
	//
	// if (file.getFile() != "") {
	// logger.info("documento Finalizado");
	// // documento finalizado
	// auxFile.setEstado("2");
	//
	// zipPath = iberdokFolderPath + "/" + file.getIdDocumento() + ".zip";
	// // guardar el zip de iberdok en el path del properties iberdokFolder
	// FileUtils.saveFile(file.getFile(), zipPath);
	// // crear carpeta si no existe
	// String outputUnzipFolder = createFolderIfNotExists(
	// file.getIdDocumento(), iberdokFolderPath);
	//
	// // descomprimir el zip
	// File unzipFile = new File(zipPath);
	// FileUtils.unzipFile(unzipFile, outputUnzipFolder);
	//
	// } else {
	// // documento en elaboracion
	// logger.info("documento Finalizado");
	// auxFile.setEstado("1");
	// }
	//
	// // check if the file already exists
	// if (this.iberdokFileService.existsFile(auxFile)) {
	// logger.info("documento existente, actualizar datos en bd");
	// // if exists, update estado
	// this.iberdokFileService.updateIdDocumento(auxFile);
	// } else {
	// logger.info("documento NO existente, nuevo registro en bd");
	// // else update the last record created
	// this.iberdokFileService.updateLastRecord(auxFile);
	// }
	//
	// logger.info("urlFinalizacion-end");
	// return "iberdok";
	// }

	/**
	 * Dado un idDocumento obtiene el xhtml alojado en weblogic
	 * 
	 *
	 */
	@UDALink(name = "getXhtml")
	@RequestMapping(value = "/getXhtml", method = RequestMethod.GET)
	@ResponseBody
	public String getXhtml(
			@RequestParam(value = "idDocumento", required = true) String idDocumento) {
		logger.info("getXtml-start");
		String fichero64 = null;
		String folderIberdokPath = appConfiguration
				.getProperty("iberdokFolder") + "/" + idDocumento;
		String filePath = folderIberdokPath + "/" + idDocumento + ".xhtml";

		File file = new File(filePath);

		try {
			fichero64 = FileUtils.encode64File(file);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.info("getXtml-end");
		return fichero64;
	}

	/**
	 * Dado un idDocumento obtiene el pdf alojado en weblogic
	 * 
	 *
	 */
	@UDALink(name = "getPDF")
	@RequestMapping(value = "/getPdf", method = RequestMethod.GET)
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
	
//	@RequestMapping(value = "/urlFinalizacion")
//	public String urlFinalizacion(@RequestBody IberdokFile file,
////			@RequestParam(value = "idModelo") String idModelo) {
//		 @RequestParam(value = "idModelo") String idModelo,
//		 @RequestParam(value = "nombre") String nombre) {
//		logger.info("urlFinalizacion-start");
//		logger.info("urlFinalizacion-idDOcumento: " + file.getIdDocumento());
//		logger.info("urlFinalizacion-file: " + file.getFile());
//		logger.info("urlFinalizacion-modelo: " + file.getIdModelo());
//		logger.info("urlFinalizacion-idModelo: " + idModelo);
//
//		// path de la carpeta donde se guardan los ficheros
//		String iberdokFolderPath = appConfiguration
//				.getProperty("iberdokFolder");
//		// path del fichero
//		String zipPath = null;
//
//		IberdokFile auxFile = new IberdokFile();
//		auxFile.setIdDocumento(file.getIdDocumento());
//		auxFile.setIdModelo(idModelo);
//		auxFile.setNombre(nombre);
//
//		if (file.getFile() != "") {
//			logger.info("documento Finalizado");
//			// documento finalizado
//			auxFile.setEstado("2");
//
//			zipPath = iberdokFolderPath + "/" + file.getIdDocumento() + ".zip";
//			// guardar el zip de iberdok en el path del properties iberdokFolder
//			FileUtils.saveFile(file.getFile(), zipPath);
//			// crear carpeta si no existe
//			String outputUnzipFolder = createFolderIfNotExists(
//					file.getIdDocumento(), iberdokFolderPath);
//
//			// descomprimir el zip
//			File unzipFile = new File(zipPath);
//			FileUtils.unzipFile(unzipFile, outputUnzipFolder);
//
//		} else {
//			// documento en elaboracion
//			logger.info("documento Finalizado");
//			auxFile.setEstado("1");
//		}
//
//		// check if the file already exists
//		if (this.iberdokFileService.existsFile(auxFile)) {
//			logger.info("documento existente, actualizar datos en bd");
//			// if exists, update estado
//			this.iberdokFileService.updateIdDocumento(auxFile);
//		} else {
//			logger.info("documento NO existente, nuevo registro en bd");
//			// else update the last record created
//			this.iberdokFileService.add(auxFile);
//		}
//
//		logger.info("urlFinalizacion-end");
//		return "iberdok";
//	}

//	/**
//	 * Funciï¿½n comprueba si existe una carpeta con el nombre del fichero y en
//	 * caso contrario la crea, deuelve el path de la carpeta
//	 * 
//	 * @param idDocumento
//	 *            idDocumento del fichero
//	 * @param fileFolder
//	 *            path de la carpeta donde deberia estar el fichero
//	 * 
//	 * @return String path of the folder
//	 */
//	private String createFolderIfNotExists(String idDocumento, String fileFolder) {
//		logger.info("createFolderIfNotExists-start");
//
//		// create output directory is not exists
//		File folderIdDocumento = new File(fileFolder + "/" + idDocumento);
//		if (!folderIdDocumento.exists()) {
//			folderIdDocumento.mkdir();
//		}
//
//		logger.info("createFolderIfNotExists-end");
//		return folderIdDocumento.getPath();
//	}


}
