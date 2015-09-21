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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Alumno;
import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Departamento;
import com.ejie.x21a.model.DepartamentoProvincia;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.UploadBean;
import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.ComarcaService;
import com.ejie.x21a.service.DepartamentoProvinciaService;
import com.ejie.x21a.service.DepartamentoService;
import com.ejie.x21a.service.LocalidadService;
import com.ejie.x21a.service.NoraAutonomiaService;
import com.ejie.x21a.service.NoraPaisService;
import com.ejie.x21a.service.ProvinciaService;
import com.ejie.x21a.service.UploadService;
import com.ejie.x21a.service.UsuarioService;
import com.ejie.x21a.validation.group.AlumnoEjemplo1Validation;
import com.ejie.x21a.validation.group.AlumnoEjemplo2Validation;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.json.JSONObject;
import com.ejie.x38.json.MessageWriter;
import com.ejie.x38.validation.ValidationManager;

/**
 * PatronesController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/patrones")
public class PatronesController {

	private static final Logger logger = LoggerFactory.getLogger(PatronesController.class);
	
	@Autowired
	private Properties appConfiguration;
	
	@Autowired
	private NoraPaisService noraPaisService;
	
	@Autowired
	private NoraAutonomiaService noraAutonomiaService;
	
	@Autowired
	private ValidationManager validationManager;
	
	@Autowired
	private UploadService uploadService;
	
	@Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
	}
	
	//Sleep
	@RequestMapping(value = "sleep/{ms}", method = RequestMethod.GET)
	public String getSleep(Model model, @PathVariable Integer ms) throws InterruptedException {
		Thread.sleep(ms);
		return "accordion";
	}
	
	//Accordion
	@RequestMapping(value = "accordion", method = RequestMethod.GET)
	public String getAccordion(Model model) {
		return "accordion";
	}

	//Autocomplete
	@RequestMapping(value = "autocomplete", method = RequestMethod.GET)
	public String getAutocomplete(Model model) {
		return "autocomplete";
	}
	
	//Date
	@RequestMapping(value = "date", method = RequestMethod.GET)
	public String getDate(Model model) {
		return "date";
	}
	
	//Dialog
	@RequestMapping(value = "dialog", method = RequestMethod.GET)
	public String getDialog(Model model) {
		return "dialog";
	}
	//Dialog (petición Ajax)
	@RequestMapping(value = "dialogAjax", method = RequestMethod.GET)
	public String dialogJSP(Model model) {
		return "dialogAjax";
	}	

	//Combos
	@RequestMapping(value = "comboSimple", method = RequestMethod.GET)
	public String getComboSimple(Model model) {
		return "combo";
	}
	//CombosEnlazado - simple
	@RequestMapping(value = "comboEnlazadoSimple", method = RequestMethod.GET)
	public String getComboEnlazadoSimple(Model model) {
		return "comboEnlazado";
	}
	//CombosEnlazado - multiple
	@RequestMapping(value = "comboEnlazadoMultiple", method = RequestMethod.GET)
	public String getEnlazadoMultiple(Model model) {
		return "comboEnlazadoMultiple";
	}
	//Multicombo
	@RequestMapping(value = "multicombo", method = RequestMethod.GET)
	public String getMulticombo(Model model) {
		return "multicombo";
	}

	//Feedback
	@RequestMapping(value = "feedback", method = RequestMethod.GET)
	public String getFeedback(Model model) {
		return "feedback";
	}
	
	//Form
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String getForm(Model model) {
		
		List<NoraPais> paises = noraPaisService.findAll(null, null);
		model.addAttribute("paises",paises);
		
		List<NoraAutonomia> autonomias = noraAutonomiaService.findAll(null, null);
		model.addAttribute("autonomias",autonomias);
		
		model.addAttribute("alumno",new Alumno());
		
		return "form";
	}
	
	//Grid
	@RequestMapping(value = "grid", method = RequestMethod.GET)
	public String getGrid(Model model) {
		return "grid";
	}
	
	//Menu
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String getMenu(Model model) {
		return "menu";
	}
	//Menu Vertical
	@RequestMapping(value = "menuVertical", method = RequestMethod.GET)
	public String getMenuVertical(Model model) {
		model.addAttribute("defaultLayout", "vertical");
		return "menuVertical";
	}
	//Menu Mixto
	@RequestMapping(value = "menuMixto", method = RequestMethod.GET)
	public String getMenuMixto (Model model) {
		model.addAttribute("defaultLayout", "mixto");
		return "menuMixto";
	}
	
	//Message
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public String getMessage(Model model) {
		return "message";
	}
	
	//Tabs con carga de la pagina
	@RequestMapping(value = "tabsStatic", method = RequestMethod.GET)
	public String getTabsStatic(Model model) {
		return "tabsStatic";
	}
	
	//Tabs con carga ajax
	@RequestMapping(value = "tabsAjax", method = RequestMethod.GET)
	public String getTabsAjax(Model model) {
		return "tabsAjax";
	}
	
	//Tabs Mixto
	@RequestMapping(value = "tabsMixto", method = RequestMethod.GET)
	public String getTabsMixto(Model model) {
		return "tabsMixto";
	}
	
	//Tabs Multiples mantenimientos
	@RequestMapping(value = "maintTab", method = RequestMethod.GET)
	public String getMaintTab(Model model) {
		return "maintTab";
	}
	
	//Time
	@RequestMapping(value = "time", method = RequestMethod.GET)
	public String getTime(Model model) {
		return "time";
	}
	
	//Toolbar
	@RequestMapping(value = "toolbar", method = RequestMethod.GET)
	public String getToolbar(Model model) {
		return "toolbar";
	}
	
	//Tooltip
	@RequestMapping(value = "tooltip", method = RequestMethod.GET)
	public String getTooltip(Model model) {
		return "tooltip";
	}
	
	//Upload
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String getUpload(Model model) {
		return "upload";
	}
	
	//Wizard
	@RequestMapping(value = "wizard", method = RequestMethod.GET)
	public String getWizard(Model model) {
		return "wizard";
	}
	//Wizard_includeFile
	@RequestMapping(value = "wizard_includeFile", method = RequestMethod.GET)
	public String getWizard_includeFile(Model model) {
		return "wizard_includeFile";
	}
	//Wizard_jspInclude
	@RequestMapping(value = "wizard_jspInclude", method = RequestMethod.GET)
	public String getWizard_jspInclude(Model model) {
		return "wizard_jspInclude";
	}
	//Wizard_jstlImport
	@RequestMapping(value = "wizard_jstlImport", method = RequestMethod.GET)
	public String getWizard_jstlImporte(Model model) {
		return "wizard_jstlImport";
	}
	
	//Validate
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	public String getValidate(Model model) {
		return "validate";
	}
	
	//All (todos los patrones en una pagina)
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public String getAll(Model model) {
		return "all";
	}
	//AllDialog (todos los patrones en un dialogo)
	@RequestMapping(value = "allDialog", method = RequestMethod.GET)
	public String getAllDialog(Model model) {
		return "allDialog";
	}
	
	/**
	 * SERVICIOS NECESARIOS:
	 * 		- Usuario 
	 * 		- Provincia
	 * 		- Comarca
	 * 		- Localidad
	 * 		- Departamento
	 * 		- DepartamentoProvincia
	 */
		@Autowired 
		private UsuarioService usuarioService;
		
		@Autowired 
		private ProvinciaService provinciaService;
		
		@Autowired 
		private ComarcaService comarcaService;
		
		@Autowired 
		private LocalidadService localidadService;
		
		@Autowired 
		private DepartamentoService departamentoService;
		
		@Autowired 
		private DepartamentoProvinciaService departamentoProvinciaService;
	
		
	/**
	 * AUTOCOMPLETE REMOTO
	 */
		@RequestMapping(value = "autocomplete/remote", method=RequestMethod.GET)
		public @ResponseBody List<DepartamentoProvincia> getRemoteAutocomplete(
				@RequestParam(value = "q", required = true) String q,
				@RequestParam(value = "c", required = true) Boolean c){
				//Filtro
				DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
	
				//Idioma
				Locale locale = LocaleContextHolder.getLocale();
				if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())){
					departamentoProvincia.setDescEu(q);
				}else{
					departamentoProvincia.setDescEs(q);
				}
				
				try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
				return departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c);
		}
		
		
	/**
	 * COMBO SIMPLE	
	 */
		@RequestMapping(value = "comboSimple/remote", method=RequestMethod.GET)
		public @ResponseBody List<Provincia> getComboRemote(){
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return provinciaService.findAll(null, null);
		}
		@RequestMapping(value = "comboSimple/remoteGroup", method=RequestMethod.GET)
		public @ResponseBody List<HashMap<String, List<?>>> getRemoteComboGrupos(){
			
			//Idioma
			Locale locale = LocaleContextHolder.getLocale();
			
			//Retorno del método
			List<HashMap<String, List<?>>> retorno = new ArrayList<HashMap<String, List<?>>>();
			
			//Nombres de los grupos según idioma
		   	String provincia = null, comarca = null, localidad = null;
		   	if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())){
				provincia = "Provincia_eu";
				comarca = "Comarca_eu";
				localidad = "Localidad_eu";
			} else { 
				provincia = "Provincia";
				comarca = "Comarca";
				localidad = "Localidad";
			}
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			
			//Provincia
			HashMap<String, List<?>> group = new HashMap<String, List<?>>();
			group.put(provincia, provinciaService.findAll(null, null));
			retorno.add(group);
			
			//Comarca
			group = new HashMap<String, List<?>>();
			group.put(comarca, comarcaService.findAll(null, null));
			retorno.add(group);
			
			//Localidad
			group = new HashMap<String, List<?>>();
			group.put(localidad, localidadService.findAll(null, null));
			retorno.add(group);
			
			return retorno;
		}
		
		
	/**
	 * COMBO ENLAZADO SIMPLE	
	 */	
		@RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoProvincia", method=RequestMethod.GET)
		public @ResponseBody List<Provincia> getEnlazadoProvincia() {
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return provinciaService.findAll(null, null);
		}
		
		@RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoComarca", method=RequestMethod.GET)
		public @ResponseBody List<Comarca> getEnlazadoComarca(
				@RequestParam(value = "provincia", required = false) BigDecimal provincia_code) {
			
			//Convertir parámetros en entidad para búsqueda
			Provincia provincia = new Provincia();
			provincia.setCode(provincia_code);
			Comarca comarca = new Comarca();
			comarca.setProvincia(provincia);
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return comarcaService.findAll(comarca, null);
		}
		
		@RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoLocalidad", method=RequestMethod.GET)
		public @ResponseBody List<Localidad> getEnlazadoLocalidad(
				@RequestParam(value = "comarca", required = false) BigDecimal comarca_code) {
			
			//Convertir parámetros en entidad para búsqueda
			Comarca comarca = new Comarca();
			comarca.setCode(comarca_code);
			Localidad localidad = new Localidad();
			localidad.setComarca(comarca);
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return localidadService.findAll(localidad, null);
		}
		
		
	/**
	 * COMBO ENLAZADO MULTIPLE	
	 */			
		/**
		 * Combos Enlazados (múltiple)
		 */
		@RequestMapping(value = "comboEnlazadoMultiple/departamentoRemote", method=RequestMethod.GET)
		public @ResponseBody List<Departamento> getEnlMultDpto() {
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return departamentoService.findAll(null, null);
		}
		
		@RequestMapping(value = "comboEnlazadoMultiple/provinciaRemote", method=RequestMethod.GET)
		public @ResponseBody List<Provincia> getEnlMultProv() {
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return provinciaService.findAll(null, null);
		}
		
		@RequestMapping(value = "comboEnlazadoMultiple/dptoProvRemote", method=RequestMethod.GET)
		public @ResponseBody List<DepartamentoProvincia> getEnlMultDptoProv(
				@RequestParam(value = "departamento", required = false) BigDecimal departamento_code,
				@RequestParam(value = "provincia", required = false) BigDecimal provincia_code) {
			
			//Convertir parámetros en entidad para búsqueda
			Departamento departamento = new Departamento();
			departamento.setCode(departamento_code);
			Provincia provincia = new Provincia();
			provincia.setCode(provincia_code);
			DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
			departamentoProvincia.setDepartamento(departamento);
			departamentoProvincia.setProvincia(provincia);
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return departamentoProvinciaService.findAll(departamentoProvincia, null);
		}
		
	/**
	 * TABS -> Contenidos
	 */
		@RequestMapping(value = { "fragmento1", "fragmento2", "fragmento3" }, method = RequestMethod.GET)
		public String tabsContent(Model model) {
			return "tabsContent_1";
		}
		
		@RequestMapping(value = { "tab2Fragment" }, method = RequestMethod.GET)
		public String tabs2Content(Model model) {
			return "tabsContent_2"; 
		}
		
		@RequestMapping(value = { "tab3Fragment" }, method = RequestMethod.GET)
		public String tabs3Content(Model model) {
			return "tabsContent_3";
		}
	
	/**
	 * GRID (Usuarios)
	 */
		@RequestMapping(value = "usuario", method = RequestMethod.GET)
		public @ResponseBody Object getAll(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido1", required = false) String apellido1,
			@RequestParam(value = "apellido2", required = false) String apellido2,
			@RequestParam(value = "ejie", required = false) String ejie,
			@RequestParam(value = "fechaAlta", required = false) Date fechaAlta,
			@RequestParam(value = "fechaBaja", required = false) Date fechaBaja,
			HttpServletRequest request) {
					Usuario filterUsuario = new Usuario(id, nombre, apellido1, apellido2, ejie, fechaAlta, fechaBaja);
	                Pagination pagination = null;
				    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
					    pagination = new Pagination();
					    pagination.setPage(Long.valueOf(request.getParameter("page")));
					    pagination.setRows(Long.valueOf(request.getParameter("rows")));
					    pagination.setSort(request.getParameter("sidx"));
					    pagination.setAscDsc(request.getParameter("sord"));
	                    List<Usuario> usuarios =  this.usuarioService.findAllLike(filterUsuario, pagination, false);
	
						
		        Long total = getAllCount(filterUsuario);
					    JQGridJSONModel data = new JQGridJSONModel();
					    data.setPage(request.getParameter("page"));
					    data.setRecords(total.intValue());
					    data.setTotal(total, pagination.getRows());
					    data.setRows(usuarios);
					    return data;
					}else{
			    return this.usuarioService.findAllLike(filterUsuario, pagination, false);
				}
		}
		@RequestMapping(value = "usuario/count", method = RequestMethod.GET)
		public @ResponseBody Long getAllCount(@RequestParam(value = "usuario", required = false) Usuario  filterUsuario) {
				return usuarioService.findAllLikeCount(filterUsuario != null ? filterUsuario: new Usuario (), false);
		}
		
	/**
	 * MAINT (Usuarios) [all.jsp]
	 */
		@RequestMapping(value = "usuario/{id}", method = RequestMethod.GET)
		public @ResponseBody Usuario getById(@PathVariable String id) {
	            Usuario usuario = new Usuario();
				usuario.setId(id);
	            usuario = this.usuarioService.find(usuario);
	            return usuario;
		}
		@RequestMapping(value = "usuario", method = RequestMethod.PUT)
	    public @ResponseBody Usuario edit(@RequestBody Usuario usuario, HttpServletResponse response) {		
	            Usuario usuarioAux  = this.usuarioService.update(usuario);
            logger.info("Entity correctly updated!");
	            return usuarioAux;
	    }
		@RequestMapping(value = "usuario", method = RequestMethod.POST)
		public @ResponseBody Usuario add(@RequestBody Usuario usuario) {		
	            Usuario usuarioAux = this.usuarioService.add(usuario);
	        logger.info("Entity correctly inserted!");
	        	return usuarioAux;
			}
		@RequestMapping(value = "usuario/{id}", method = RequestMethod.DELETE)
		@ResponseStatus(value=HttpStatus.OK)
	    public void remove(@PathVariable String id) {
	            Usuario usuario = new Usuario();
	            usuario.setId(id);
	            this.usuarioService.remove(usuario);
            logger.info("Entity correctly deleted!");
	    }
		
		/**
		 * MAINT (Usuarios) [form.jsp]
		 */
		//Form http submit
		@RequestMapping(value = "form/ejemplo", method = RequestMethod.POST)
		public @ResponseBody Object getFormHttp(@RequestBody Alumno alumno) {
			
			MessageWriter messageWriter = new MessageWriter();
			
			messageWriter.startMessageList();
			messageWriter.addMessage("El formulario se ha enviado correctamente.");
			messageWriter.addMessage("Esta es la representación JSON del objeto recibido:");
			messageWriter.startSubLevel();
			messageWriter.addMessage(new JSONObject(alumno).toString());
			messageWriter.endSubLevel();
			messageWriter.endMessageList();
			
			return messageWriter.toString();
		}
		
		
		@ModelAttribute(value="alumno")
		public Alumno addAlumno(@RequestParam(value="alumno.apellido1",required=false) String apellido1,
				@RequestParam(value="alumno.apellido2",required=false) String apellido2,
				@RequestParam(value="alumno.nombre",required=false) String nombre,
				HttpServletRequest request) {
			Alumno alumnoBind = new Alumno();
			
			alumnoBind.setNombre(nombre);
			alumnoBind.setApellido1(apellido1);
			alumnoBind.setApellido2(apellido2);
			
		    return alumnoBind;
		}
		
		//Form ajax submit
		@RequestMapping(value = "form/multientidades", method = RequestMethod.POST)
		public @ResponseBody Object getFormmMultientidades(@RequestBody Map<String, Object> multiModelMap) {
			
			MessageWriter messageWriter = new MessageWriter();
			
			messageWriter.startMessageList();
			messageWriter.addMessage("Las entidades se han enviado correctamente");
			messageWriter.addMessage("Esta es la representación JSON del objeto recibido:");
			messageWriter.startSubLevel();
			messageWriter.addMessage(new JSONObject(multiModelMap).toString());
			messageWriter.endSubLevel();
			messageWriter.endMessageList();
			
			return messageWriter.toString();
		}
		
		//Form ajax submit
		@RequestMapping(value = "form/multientidadesMismoTipo", method = RequestMethod.POST)
		public @ResponseBody Object getFormmMultientidadesMismoTipo(@RequestBody Map<String, Object> multiModelMap) {
			
			MessageWriter messageWriter = new MessageWriter();
			
			messageWriter.startMessageList();
			messageWriter.addMessage("Las entidades se han enviado correctamente");
			messageWriter.addMessage("Esta es la representación JSON del objeto recibido:");
			messageWriter.startSubLevel();
			messageWriter.addMessage(new JSONObject(multiModelMap).toString());
			messageWriter.endSubLevel();
			messageWriter.endMessageList();
			
			return messageWriter.toString();
			
		}
		
		@RequestMapping(value="form/subidaArchivos", method = RequestMethod.POST, produces="application/json")
		public @ResponseBody Object addFormSimple(
				@ModelAttribute UploadBean uploadBean,
				@RequestParam(value="fotoPadre", required=false) MultipartFile fotoPadre,
				@RequestParam(value="fotoMadre", required=false) MultipartFile fotoMadre,
				HttpServletResponse response) throws IOException {
			
			if(fotoPadre!=null && !fotoPadre.isEmpty()){
				uploadService.saveToDisk(fotoPadre, appConfiguration.getProperty("fileUpload.path"));
			}
			if(fotoMadre!=null && !fotoMadre.isEmpty()){
				uploadService.saveToDisk(fotoMadre, appConfiguration.getProperty("fileUpload.path"));
			}
			
			MessageWriter messageWriter = new MessageWriter();
			
			messageWriter.startMessageList();
			messageWriter.addMessage("Las entidades se han enviado correctamente");
			messageWriter.endMessageList();
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			servletOutputStream.print(messageWriter.toString());
			response.flushBuffer();
			
			return null;
		}
		
		
		/**
		 * Validacion 
		 */
		
		@RequestMapping(value = "validacion/cliente", method = RequestMethod.POST)
		public @ResponseBody Object validacion(Model model) {
			
			Map<String, Object> mapaRespuesta = new HashMap<String, Object>();
			
			mapaRespuesta.put("respuesta", "true");
			
			return mapaRespuesta;
		}
		
		
		/**
		 * Validacion 
		 */
		
		@RequestMapping(value = "validacion/servidor", method = RequestMethod.POST)
		public @ResponseBody Object validacion(@Validated(value={AlumnoEjemplo1Validation.class}) @RequestBody Alumno alumno, Model model) {
			
			Map<String, Object> mapaRespuesta = new HashMap<String, Object>();
			
			mapaRespuesta.put("respuesta", "true");
			
			return mapaRespuesta;
		}
		
		/**
		 * Validacion 
		 * @throws IOException 
		 */
		
		@RequestMapping(value = "validacion/servidor2", method = RequestMethod.POST, produces="application/json")
		public @ResponseBody Object validacion2(@RequestBody Alumno alumno, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			Errors errors = new BeanPropertyBindingResult(alumno, "alumno");
			validationManager.validate(errors, alumno, AlumnoEjemplo2Validation.class);
			
			/*
			 * Se realiza la siguiente validacion sobre el bean: 
			 * Se comprueba que el nombre de usuario introducido no 
			 */
			UserNameValidator usuarioValidator = new UserNameValidator();
			usuarioValidator.validate(alumno, errors);
			
			/*
			 * EJEMPLO DE ENVIO DE MENSAJES DESDE EL SERVIDOR 
			 */
			if (errors.hasErrors()){
				try {
					
					Map<String, List<String>> fieldErrors = validationManager.getErrorsAsMap(errors);
					
					response.sendError(406, validationManager.getMessageJSON(fieldErrors).toString());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			MessageWriter messageWriter = new MessageWriter();
			
			messageWriter.startMessageList();
			messageWriter.addMessage("Este es un ejemplo de errores enviados desde el servidor");
			messageWriter.addMessage(messageSource, "patron.validacion.required");
			messageWriter.addComplexMessage("Prueba de estilos", "rojo");
			messageWriter.startSubLevel();
			messageWriter.addMessage("Nivel 1.1","Nivel 1.2","Nivel 1.3");
			messageWriter.endSubLevel();
			messageWriter.endMessageList();
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/json") ;
			servletOutputStream.print(messageWriter.toString());
			response.flushBuffer();
			
			return null;
		}
		
		
		class UserNameValidator implements Validator {
		    
		    /**
		    * This Validator validates just Person instances
		    */
		    public boolean supports(Class<?> clazz) {
		        return Alumno.class.equals(clazz);
		    }
		    
		    public void validate(Object obj, Errors e) {
		        Alumno a = (Alumno) obj;
		        
		        if (a!=null && a.getUsuario()!=null){
		        	if ("ADMIN".equals(a.getUsuario().toUpperCase())){
		        		e.rejectValue("usuario", "patron.validacion.required");
		        	}
		        }
		    }
		}
		
		
}
