package com.ejie.x21a.control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.rup.table.filter.model.Filter;
import com.ejie.x38.rup.table.filter.service.FilterService;

@Controller
@RequestMapping(value = "/iberdok")
public class IberdokController {

	@Autowired
	private Properties appConfiguration;

	private static final Logger logger = LoggerFactory
			.getLogger(IberdokController.class);

	@Autowired
	private IberdokFileService iberdokFileService;

	@Autowired
	private FilterService filterService;

	/*
	 * OPERACIONES CRUD
	 * 
	 * Metodos correspondientes a las operaciones CRUD (Create, Read, Update,
	 * Delete).
	 */

	/**
	 * Operaci�n CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea recuperar.
	 * @return Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody IberdokFile get(@PathVariable String id) {
		IberdokFile file = new IberdokFile();
		file.setId(id);
		file = this.iberdokFileService.find(file);

		return file;
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como par�metro.
	 * 
	 * @param IberdokFile
	 *            Objeto que contiene los par�metros de filtrado utilizados en
	 *            la b�squeda.
	 * @return Lista de objetos correspondientes a la b�squeda realizada.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<IberdokFile> getAll(
			@ModelAttribute() IberdokFile fileFilter) {
		IberdokController.logger
				.info("[GET - find_ALL] : Obtener ficheros de iberdok por filtro");
		return this.iberdokFileService.findAllLike(fileFilter, null, false);
	}

	/**
	 * Operaci�n CRUD Edit. Modificaci�n del bean indicado.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene la informaci�n a modificar.
	 * @return Bean resultante de la modificaci�n.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public @ResponseBody IberdokFile edit(
			@Validated @RequestBody IberdokFile file) {

		IberdokFile fileAux = this.iberdokFileService.update(file);
		logger.info("Entity correctly updated!");
		return fileAux;
	}

	/**
	 * Operaci�n CRUD Create. Creaci�n de un nuevo registro a partir del bean
	 * indicado.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene la informaci�n con la que se va a crear el
	 *            nuevo registro.
	 * @return Bean resultante del proceso de creaci�n.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody IberdokFile add(
			@Validated @RequestBody IberdokFile file) {

		IberdokFile fileAux = this.iberdokFileService.add(file);
		logger.info("Entity correctly inserted!");
		return fileAux;
	}

	/**
	 * Operaci�n CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea eliminar.
	 * @return Bean eliminado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
	 * Operaci�n de filtrado del componente RUP_TABLE.
	 * 
	 * @param IberdokFile
	 *            Bean que contiene los par�metros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los par�mtros de configuraci�n propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	// @Json(mixins={@JsonMixin(target=IberdokFile.class,
	// mixin=IberdokFileMixIn.class)})
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<IberdokFile> filter(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		IberdokController.logger.info("[POST - jqGrid] : Obtener IberdokFiles");
		return iberdokFileService.filter(filterIberdokFile, jqGridRequestDto,
				false);
	}

	@RequestMapping(value = "/multiFilter/add", method = RequestMethod.POST)
	public @ResponseBody Filter filterAdd(
			@RequestJsonBody(param = "filtro") Filter filtro) {
		IberdokController.logger.info("[POST - jqGrid] : add filter");

		return filterService.insert(filtro);
	}

	@RequestMapping(value = "/multiFilter/delete", method = RequestMethod.POST)
	public @ResponseBody Filter filterDelete(
			@RequestJsonBody(param = "filtro") Filter filtro) {
		IberdokController.logger.info("[POST - jqGrid] : delete filter");
		return filterService.delete(filtro);
	}

	@RequestMapping(value = "/multiFilter/getDefault", method = RequestMethod.GET)
	public @ResponseBody Filter filterGetDefault(
			@RequestParam(value = "filterSelector", required = true) String filterSelector,
			@RequestParam(value = "user", required = true) String filterDocument) {
		IberdokController.logger.info("[get - jqGrid] : getDefault filter");
		return filterService.getDefault(filterSelector, filterDocument);
	}

	@RequestMapping(value = "/multiFilter/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Filter> filterGetAll(
			@RequestParam(value = "filterSelector", required = true) String filterSelector,
			@RequestParam(value = "user", required = true) String filterDocument) {
		IberdokController.logger.info("[get - jqGrid] : GetAll filter");
		return filterService.getAllFilters(filterSelector, filterDocument);
	}

	/**
	 * Operaci�n de b�squeda del componente RUP_TABLE.
	 * 
	 * @param filterIberdokFile
	 *            Bean que contiene los par�metros de filtrado a emplear.
	 * @param searchIberdokFile
	 *            Bean que contiene los par�metros de b�squeda a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los par�mtros de configuraci�n propios del
	 *            RUP_TABLE a aplicar en la b�squeda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los par�metros de b�squeda.
	 * 
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<IberdokFile>> search(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody(param = "search") IberdokFile searchIberdokFile,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		IberdokController.logger.info("[POST - search] : Buscar IberdokFiles");
		return iberdokFileService.search(filterIberdokFile, searchIberdokFile,
				jqGridRequestDto, false);
	}

	/**
	 * Borrado m�ltiple de registros
	 * 
	 * @param filterIberdokFile
	 *            Bean que contiene los par�metros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los par�mtros de configuraci�n propios del
	 *            RUP_TABLE a aplicar en la b�squeda.
	 * @return Lista de los identificadores de los registros eliminados.
	 */
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param = "filter") IberdokFile filterIberdokFile,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		IberdokController.logger
				.info("[POST - removeMultiple] : Eliminar multiples documentos iberdok");
		this.iberdokFileService.removeMultiple(filterIberdokFile,
				jqGridRequestDto, false);
		IberdokController.logger.info("All entities correctly deleted!");

		return jqGridRequestDto.getMultiselection().getSelectedIds();
	}

	// Iberdok
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
//	 * Funci�n comprueba si existe una carpeta con el nombre del fichero y en
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
