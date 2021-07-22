/*
 * Copyright 2020 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 * Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito,
 * el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 * SIN GARANTÃ�AS NI CONDICIONES DE NINGÃšN TIPO, ni expresas ni implÃ­citas.
 * VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
package com.ejie.x21a.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hdiv.services.TrustAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.hateoas.Resource;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Alumno;
import com.ejie.x21a.model.AlumnoDepartamento;
import com.ejie.x21a.model.Collection;
import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Departamento;
import com.ejie.x21a.model.DepartamentoProvincia;
import com.ejie.x21a.model.FormComarcas;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.RandomForm;
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
import com.ejie.x21a.validation.group.AlumnoEjemplo1Validation;
import com.ejie.x21a.validation.group.AlumnoEjemplo2Validation;
import com.ejie.x38.control.bind.annotation.Json;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
import com.ejie.x38.json.JSONArray;
import com.ejie.x38.json.JSONObject;
import com.ejie.x38.json.JsonMixin;
import com.ejie.x38.json.MessageWriter;
import com.ejie.x38.util.DateTimeManager;
import com.ejie.x38.util.ResourceUtils;
import com.ejie.x38.validation.ValidationManager;
import com.fasterxml.jackson.annotation.JsonProperty;

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


    @javax.annotation.Resource
    private ReloadableResourceBundleMessageSource messageSource;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    //Sleep
    @RequestMapping(value = "sleep/{ms}", method = RequestMethod.GET)
    public String getSleep(Model model, @PathVariable Integer ms) throws InterruptedException {
        Thread.sleep(ms);
        return "accordion";
    }

    //Accordion
    @UDALink(name = "getAccordion", linkTo = {@UDALinkAllower(name = "getRemoteAutocomplete")})
    @RequestMapping(value = "accordion", method = RequestMethod.GET)
    public String getAccordion(Model model) {
        return "accordion";
    }

    // Autocomplete
    @UDALink(name = "getAutocomplete", linkTo = {
    		@UDALinkAllower(name = "getRemoteAutocomplete"),
    		@UDALinkAllower(name = "getComboRemote")
    })
    @RequestMapping(value = "autocomplete", method = RequestMethod.GET)
    public String getAutocomplete(Model model) {
        return "autocomplete";
    }

    // Autocomplete Enlazado
    @UDALink(name = "getAutocompleteEnlazado", linkTo = {
    		@UDALinkAllower(name = "getProvinciaEnlazadoAutocomplete"),
    		@UDALinkAllower(name = "getComarcaEnlazadoAutocomplete"),
    		@UDALinkAllower(name = "getLocalidadEnlazadoAutocomplete")
    })
    @RequestMapping(value = "autocompleteEnlazado", method = RequestMethod.GET)
    public String getAutocompleteEnlazado(Model model) {
        return "autocompleteEnlazado";
    }

    // Autocomplete Enlazado Multiple
    @UDALink(name = "getAutocompleteEnlazadoMultiple", linkTo = {
    		@UDALinkAllower(name = "getDepartamentoEnlazadoMultipleAutocomplete"),
    		@UDALinkAllower(name = "getProvinciaEnlazadoMultipleAutocomplete"),
    		@UDALinkAllower(name = "getDepartamentoProvinciaEnlazadoMultipleAutocomplete")
    })
    @RequestMapping(value = "autocompleteEnlazadoMultiple", method = RequestMethod.GET)
    public String getAutocompleteEnlazadoMultiple(Model model) {
        return "autocompleteEnlazadoMultiple";
    }

    //Button (
    @RequestMapping(value = "button", method = RequestMethod.GET)
    public String buttonJSP(Model model) {
        return "button";
    }

    //Date
    @RequestMapping(value = "date", method = RequestMethod.GET)
    public String getDate(Model model) {
        return "date";
    }

    //Dialog
    @UDALink(name = "getDialog", linkTo = {@UDALinkAllower(name = "dialogJSP")})
    @RequestMapping(value = "dialog", method = RequestMethod.GET)
    public String getDialog(Model model) {
        return "dialog";
    }

    //Dialog (peticiÃ³n Ajax)
    @UDALink(name = "dialogJSP")
    @RequestMapping(value = "dialogAjax", method = RequestMethod.GET)
    public String dialogJSP(Model model) {
        return "dialogAjax";
    }

    //Combos
    @UDALink(name = "getComboSimple", linkTo = {@UDALinkAllower(name = "getComboRemote"), @UDALinkAllower(name = "getRemoteComboGrupos"), @UDALinkAllower(name = "getRemoteComboGruposEnlazado")})
    @RequestMapping(value = "comboSimple", method = RequestMethod.GET)
    public String getComboSimple(Model model) {
        return "combo";
    }

    //CombosEnlazado - simple
    @UDALink(name = "getComboEnlazadoSimple", linkTo = {@UDALinkAllower(name = "getEnlazadoProvincia"), @UDALinkAllower(name = "getEnlazadoComarca"), @UDALinkAllower(name = "getEnlazadoLocalidad"), @UDALinkAllower(name = "getRemoteComboGruposEnlazado")})
    @RequestMapping(value = "comboEnlazadoSimple", method = RequestMethod.GET)
    public String getComboEnlazadoSimple(Model model) {
        return "comboEnlazado";
    }

    //CombosEnlazado - multiple
    @UDALink(name = "getEnlazadoMultiple", linkTo = {@UDALinkAllower(name = "getEnlMultDpto"), @UDALinkAllower(name = "getEnlMultProv"), @UDALinkAllower(name = "getEnlMultDptoProv")})
    @RequestMapping(value = "comboEnlazadoMultiple", method = RequestMethod.GET)
    public String getEnlazadoMultiple(Model model) {
        return "comboEnlazadoMultiple";
    }

    //Multicombo
    @RequestMapping(value = "multicombo", method = RequestMethod.GET)
    public String getMulticombo(Model model) {
        return "multicombo";
    }

    //Multicombo
    @RequestMapping(value = "comboMantenimiento", method = RequestMethod.GET)
    public String getComboMantenimiento(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "comboMantenimiento";
    }

    //Feedback
    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    public String getFeedback(Model model) {
        return "feedback";
    }

    //Form
    @UDALink(name = "getForm", linkTo = { @UDALinkAllower(name = "getPaises", linkClass = NoraController.class), @UDALinkAllower(name = "getAutonomias", linkClass = NoraController.class), @UDALinkAllower(name = "getProvincias", linkClass = NoraController.class), @UDALinkAllower(name = "getFormHttp" ), @UDALinkAllower(name = "getFormmMultientidades" ), @UDALinkAllower(name = "getFormmMultientidadesMismoTipo" ), @UDALinkAllower(name = "addFormSimple" )})
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String getForm(Model model) {

        List<NoraPais> paises = noraPaisService.findAll(null, null);
        model.addAttribute("paises", paises);

        List<NoraAutonomia> autonomias = noraAutonomiaService.findAll(null, null);
        model.addAttribute("autonomias", autonomias);

        model.addAttribute("comarca", new Comarca());

        model.addAttribute("formComarcas", new FormComarcas());

        model.addAttribute("alumno", new Alumno());

        model.addAttribute("alumnoDepartamento", new AlumnoDepartamento());

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
    public String getMenuMixto(Model model) {
        model.addAttribute("defaultLayout", "mixto");
        return "menuMixto";
    }

    //Message
    @RequestMapping(value = "message", method = RequestMethod.GET)
    public String getMessage(Model model) {
        return "message";
    }

    //ProgressBar
    @RequestMapping(value = "progressBar", method = RequestMethod.GET)
    public String getProgressBar(Model model) {
        return "progressBar";
    }

    //Slider
    @RequestMapping(value = "slider", method = RequestMethod.GET)
    public String getSlider(Model model) {
        return "slider";
    }

    //Spinner
    @RequestMapping(value = "spinner", method = RequestMethod.GET)
    public String getSpinner(Model model) {
        return "spinner";
    }

    //Tabs con carga de la pagina
    @RequestMapping(value = "tabsStatic", method = RequestMethod.GET)
    public String getTabsStatic(Model model) {
        return "tabsStatic";
    }

    //Tabs con carga ajax
    @UDALink(name = "getTabsAjax", linkTo = { @UDALinkAllower(name = "tabsContent"), @UDALinkAllower(name = "tabs2Content"), @UDALinkAllower(name = "tabs3Content"), @UDALinkAllower(name = "tabSub"), @UDALinkAllower(name = "tabSubAna"), @UDALinkAllower(name = "tabsMaint"), @UDALinkAllower(name = "getPageNoTemplate", linkClass = X21aCalendarController.class), @UDALinkAllower(name = "getListaNoTemplateView", linkClass = ListaController.class)})
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
    @UDALink(name = "tabsMaint", linkTo = { @UDALinkAllower(name = "getPageNoTemplate", linkClass = X21aCalendarController.class), @UDALinkAllower(name = "getListaNoTemplateView", linkClass = ListaController.class)})
    @RequestMapping(value = {"maintTab", "pruebaSub3Maint"}, method = RequestMethod.GET)
    public String getMaintTab(Model model) {
        return "maintTab";
    }

    //Tabs Scrollable
    @RequestMapping(value = "tabsScrollable", method = RequestMethod.GET)
    public String geTabsScrollable(Model model) {
        return "tabsScrollable";
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
    @UDALink(name = "getUpload", linkTo = { @UDALinkAllower(name = "addFormSimple"), @UDALinkAllower(name = "add", linkClass = UploadController.class), @UDALinkAllower(name = "addForm", linkClass = UploadController.class), @UDALinkAllower(name = "addFormSimple", linkClass = UploadController.class), @UDALinkAllower(name = "addPifFormFile", linkClass = UploadController.class), @UDALinkAllower(name = "downloadPif", linkClass = UploadController.class), @UDALinkAllower(name = "removePif", linkClass = UploadController.class), @UDALinkAllower(name = "remove", linkClass = UploadController.class), @UDALinkAllower(name = "download", linkClass = UploadController.class)})
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String getUpload(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("collection", new Collection());
        return "upload";
    }

    //Wizard
    @RequestMapping(value = "wizard", method = RequestMethod.GET)
    public String getWizard(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard";
    }

    //Wizard_includeFile
    @RequestMapping(value = "wizard_includeFile", method = RequestMethod.GET)
    public String getWizard_includeFile(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_includeFile";
    }

    //Wizard_jspInclude
    @RequestMapping(value = "wizard_jspInclude", method = RequestMethod.GET)
    public String getWizard_jspInclude(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_jspInclude";
    }

    //Wizard_jstlImport
    @RequestMapping(value = "wizard_jstlImport", method = RequestMethod.GET)
    public String getWizard_jstlImporte(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_jstlImport";
    }

    //Wizard dinamico
    @RequestMapping(value = "wizard_dinamico", method = RequestMethod.GET)
    public String getWizard_dinamico(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_dinamico";
    }

    @RequestMapping(value = "wizard_dinamico_content", method = RequestMethod.GET)
    public String getWizard_dinamico_content(Model model) {
        return "wizard_dinamico_content";
    }

    //Tree
    @UDALink(name = "getTrees", linkTo = { @UDALinkAllower(name = "getTreeAjax" )})
    @RequestMapping(value = "trees", method = RequestMethod.GET)
    public String getTrees(Model model) {
        return "trees";
    }

    @RequestMapping(value = "treeDAD", method = RequestMethod.GET)
    public String getTreeDragAndDrop(Model model) {
        return "treeDAD";
    }
    
    @UDALink(name = "getTreeAjax")
    @RequestMapping(value = "ajaxTree", method = RequestMethod.GET)
    public Object getTreeAjax(Model model, HttpServletResponse response) {

        // S
        JSONArray root = new JSONArray();

        JSONObject padre1 = new JSONObject("{'data':'Padre1', 'attr':{'id':'p1'}, 'metadata':{'nivel':1}}");

        JSONObject padre11 = new JSONObject("{'data':'Padre1.1', 'attr':{'id':'p11'}, 'metadata':{'nivel':2}}");

        JSONObject hoja111 = new JSONObject("{'data':'Padre1.1.1', 'attr':{'id':'p111'}, 'metadata':{'nivel':3}}");

        JSONObject padre112 = new JSONObject("{'data':'Padre1.1.2', 'attr':{'id':'p112'}, 'metadata':{'nivel':3}}");

        JSONObject hoja1121 = new JSONObject("{'data':'Padre1.1.2.1', 'attr':{'id':'p1121'}, 'metadata':{'nivel':4}}");

        JSONObject hoja1122 = new JSONObject("{'data':'Padre1.1.2.2', 'attr':{'id':'p1122'}, 'metadata':{'nivel':4}}");

        JSONObject hoja12 = new JSONObject("{'data':'Padre1.2', 'attr':{'id':'p12'}, 'metadata':{'nivel':2}}");

        JSONArray padre112Childs = new JSONArray();
        padre112Childs.put(hoja1121);
        padre112Childs.put(hoja1122);
        padre112.put("children", padre112Childs);

        JSONArray padre11Childs = new JSONArray();
        padre11Childs.put(hoja111);
        padre11Childs.put(padre112);
        padre11.put("children", padre11Childs);

        JSONArray padre1Childs = new JSONArray();
        padre1Childs.put(padre11);
        padre1Childs.put(hoja12);
        padre1.put("children", padre1Childs);

        root.put(padre1);

        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", DateTimeManager.getHttpExpiredDate());
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().write(root.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    //Validate
    @RequestMapping(value = "validate", method = RequestMethod.GET)
    public String getValidate(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("randomForm", new RandomForm());
        return "validate";
    }

    //Validate
    @RequestMapping(value = "validateRules", method = RequestMethod.GET)
    public String getValidateRules(Model model) {

        model.addAttribute("alumno", new Alumno());
        return "validateRules";
    }

    @RequestMapping(value = "validateRup", method = RequestMethod.GET)
    public String getValidateRup(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("randomForm", new RandomForm());
        return "validateRup";
    }


    //All (todos los patrones en una pagina)
    @UDALink(name = "getAll", linkTo = { 
    		@UDALinkAllower(name = "filter", linkClass = TableComarcaController.class),
    		@UDALinkAllower(name = "getTableComarcaEditForm", linkClass = TableComarcaController.class),
    		@UDALinkAllower(name = "getProvincias", linkClass = TableComarcaController.class),
    		@UDALinkAllower(name = "getEnlazadoProvincia"),
    		@UDALinkAllower(name = "getEnlazadoComarca"),
    		@UDALinkAllower(name = "getRemoteComboGrupos"),
    		@UDALinkAllower(name = "tabsContent"),
    		@UDALinkAllower(name = "tabs2Content"),
    		@UDALinkAllower(name = "tabs3Content"),
    		@UDALinkAllower(name = "getAllDialog") })
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String getAll(Model model) {
    	model.addAttribute("comarca", new Comarca());
        return "all";
    }

    //AllDialog (todos los patrones en un dialogo)
    @UDALink(name = "getAllDialog", linkTo = { 
    		@UDALinkAllower(name = "filter", linkClass = TableUsuarioController.class),
    		@UDALinkAllower(name = "getTableEditForm", linkClass = TableUsuarioController.class),
			@UDALinkAllower(name = "getApellidos", linkClass = TableUsuarioController.class),
			@UDALinkAllower(name = "getRoles", linkClass = TableUsuarioController.class) })
    @RequestMapping(value = "allDialog", method = RequestMethod.GET)
    public String getAllDialog(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("randomForm", new RandomForm());
		
		Map<String,String> comboRol = new LinkedHashMap<String,String>();
		comboRol.put("", "---");
		comboRol.put("Administrador", "Administrador");
		comboRol.put("Desarrollador", "Desarrollador");
		comboRol.put("Espectador", "Espectador");
		comboRol.put("Informador", "Informador");
		comboRol.put("Manager", "Manager");
		model.addAttribute("comboRol", comboRol);
		
		Map<String,String> comboEjie = new LinkedHashMap<String,String>();
		comboEjie.put("", "---");
		comboEjie.put("0", "No");
		comboEjie.put("1", "S�");
		model.addAttribute("comboEjie", comboEjie);
        
        return "allDialog";
    }

    //Context menu
    @RequestMapping(value = "contextMenu", method = RequestMethod.GET)
    public String getContextMenu(Model model) {
        return "contextMenu";
    }

    /**
     * SERVICIOS NECESARIOS:
     * - Usuario
     * - Provincia
     * - Comarca
     * - Localidad
     * - Departamento
     * - DepartamentoProvincia
     */


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
    @UDALink(name = "getRemoteAutocomplete")
    @RequestMapping(value = "autocomplete/remote", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<DepartamentoProvincia>> getRemoteAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "codProvincia", required = false) BigDecimal codProvincia) {
        //Filtro
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();

        //Idioma
        Locale locale = LocaleContextHolder.getLocale();
        if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            departamentoProvincia.setDescEu(q);
        } else {
            departamentoProvincia.setDescEs(q);
        }
        if (codProvincia != null) {
            Provincia provincia = new Provincia();
            provincia.setCode(codProvincia);
            departamentoProvincia.setProvincia(provincia);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c));
    }
    
    /**
     * AUTOCOMPLETE REMOTO ENLAZADO
     */
    @UDALink(name = "getProvinciaEnlazadoAutocomplete")
    @RequestMapping(value = "autocomplete/remoteEnlazadoProvincia", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Provincia>> getProvinciaEnlazadoAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c) {
    	
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
        return ResourceUtils.fromListToResource(provinciaService.findAllLike(null, null, !c));
    }
    
    @UDALink(name = "getComarcaEnlazadoAutocomplete")
    @RequestMapping(value = "autocomplete/remoteEnlazadoComarca", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Comarca>> getComarcaEnlazadoAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "provincia", required = false) @TrustAssertion(idFor = Provincia.class) BigDecimal codProvincia) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Provincia provincia = new Provincia();
        provincia.setCode(codProvincia);
        
        Comarca comarca = new Comarca();
        comarca.setProvincia(provincia);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return ResourceUtils.fromListToResource(comarcaService.findAllLike(comarca, null, !c));
    }
    
    @UDALink(name = "getLocalidadEnlazadoAutocomplete")
    @RequestMapping(value = "autocomplete/remoteEnlazadoLocalidad", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Localidad>> getLocalidadEnlazadoAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "comarca", required = false) @TrustAssertion(idFor = Comarca.class) BigDecimal codComarca) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Comarca comarca = new Comarca();
        comarca.setCode(codComarca);
        
        Localidad localidad = new Localidad();
        localidad.setComarca(comarca);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return ResourceUtils.fromListToResource(localidadService.findAllLike(localidad, null, !c));
    }
    
    /**
     * AUTOCOMPLETE REMOTO ENLAZADO M�LTIPLE
     */
    @UDALink(name = "getDepartamentoEnlazadoMultipleAutocomplete")
    @RequestMapping(value = "autocomplete/remoteEnlazadoMultipleDepartamento", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Departamento>> getDepartamentoEnlazadoMultipleAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c) {
    	
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
        return ResourceUtils.fromListToResource(departamentoService.findAllLike(null, null, !c));
    }
    
    @UDALink(name = "getProvinciaEnlazadoMultipleAutocomplete")
    @RequestMapping(value = "autocomplete/remoteEnlazadoMultipleProvincia", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Provincia>> getProvinciaEnlazadoMultipleAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c) {
    	
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return ResourceUtils.fromListToResource(provinciaService.findAllLike(null, null, !c));
    }
    
    @UDALink(name = "getDepartamentoProvinciaEnlazadoMultipleAutocomplete")
    @RequestMapping(value = "autocomplete/remoteEnlazadoMultipleDepartamentoProvincia", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<DepartamentoProvincia>> getDepartamentoProvinciaEnlazadoMultipleAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "departamento", required = false) @TrustAssertion(idFor = Departamento.class) BigDecimal departamento_code,
            @RequestParam(value = "provincia", required = false) @TrustAssertion(idFor = Provincia.class) BigDecimal provincia_code) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return ResourceUtils.fromListToResource(departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c));
    }

    /**
     * COMBO SIMPLE
     */
    interface ProvinciaMixIn {
        @JsonProperty("value")
        int getCode();

        @JsonProperty("label")
        int getDescEs();
    }

    @UDALink(name = "getComboRemote")
    @Json(mixins = {@JsonMixin(target = Provincia.class, mixin = ProvinciaMixIn.class)})
    @RequestMapping(value = "comboSimple/remote", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Provincia>> getComboRemote() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(provinciaService.findAll(null, null));
    }
    
    @UDALink(name = "getRemoteComboGrupos")
    @RequestMapping(value = "comboSimple/remoteGroup", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<HashMap<String, List<?>>>> getRemoteComboGrupos() {
        return this.setRemoteComboGruposEnlazado(null);
    }
    
    @UDALink(name = "getRemoteComboGruposEnlazado")
    @RequestMapping(value = "comboSimple/remoteGroupEnlazado", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<HashMap<String, List<?>>>> getRemoteComboGruposEnlazado(@RequestParam(value = "provincia", required = false) @TrustAssertion(idFor = Provincia.class) BigDecimal provincia_code) {
    	return this.setRemoteComboGruposEnlazado(provincia_code);
    }
    
    private List<Resource<HashMap<String, List<?>>>> setRemoteComboGruposEnlazado(BigDecimal provincia_code) {

        //Idioma
        Locale locale = LocaleContextHolder.getLocale();

        //Retorno del mÃ©todo
        List<HashMap<String, List<?>>> retorno = new ArrayList<HashMap<String, List<?>>>();

        //Nombres de los grupos segÃºn idioma
        String provincia = null, comarca = null, localidad = null;
        if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            provincia = "Provincia_eu";
            comarca = "Comarca_eu";
            localidad = "Localidad_eu";
        } else {
            provincia = "Provincia";
            comarca = "Comarca";
            localidad = "Localidad";
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Provincia
        HashMap<String, List<?>> group = new HashMap<String, List<?>>();

        Provincia provinciaFilter = null;

        if (provincia_code == null) {
            group.put(provincia, provinciaService.findAll(null, null));
            retorno.add(group);
        } else {
            provinciaFilter = new Provincia();
            provinciaFilter.setCode(provincia_code);
        }

        //Comarca
        group = new HashMap<String, List<?>>();
        if (provincia_code != null) {
            Comarca comarcaFilter = new Comarca();
            comarcaFilter.setProvincia(provinciaFilter);
            group.put(comarca, comarcaService.findAll(comarcaFilter, null));
        } else {
            group.put(comarca, comarcaService.findAll(null, null));
        }
        retorno.add(group);

        //Localidad
        group = new HashMap<String, List<?>>();
        if (provincia_code != null) {
            Localidad localidadFilter = new Localidad();
            Comarca comarcaFilter = new Comarca();
            comarcaFilter.setProvincia(provinciaFilter);
            localidadFilter.setComarca(comarcaFilter);
            group.put(localidad, localidadService.findAll(localidadFilter, null));
        } else {
            group.put(localidad, localidadService.findAll(null, null));
        }
        retorno.add(group);

        return ResourceUtils.fromListToResource(retorno);
    }


    /**
     * COMBO ENLAZADO SIMPLE
     */
    @UDALink(name = "getEnlazadoProvincia")
    @RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoProvincia", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Provincia>> getEnlazadoProvincia() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(provinciaService.findAll(null, null));
    }
    
    @UDALink(name = "getEnlazadoComarca")
    @RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoComarca", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Comarca>> getEnlazadoComarca(
            @RequestParam(value = "provincia", required = false) @TrustAssertion(idFor = Provincia.class) BigDecimal provincia_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        Comarca comarca = new Comarca();
        comarca.setProvincia(provincia);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(comarcaService.findAll(comarca, null));
    }

    @UDALink(name = "getEnlazadoLocalidad")
    @RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoLocalidad", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Localidad>> getEnlazadoLocalidad(
            @RequestParam(value = "comarca", required = false) @TrustAssertion(idFor = Comarca.class) BigDecimal comarca_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Comarca comarca = new Comarca();
        comarca.setCode(comarca_code);
        Localidad localidad = new Localidad();
        localidad.setComarca(comarca);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(localidadService.findAll(localidad, null));
    }


    /**
     * COMBO ENLAZADO MULTIPLE
     */
    /**
     * Combos Enlazados (mÃºltiple)
     */
    @UDALink(name = "getEnlMultDpto")
    @RequestMapping(value = "comboEnlazadoMultiple/departamentoRemote", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Departamento>> getEnlMultDpto() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(departamentoService.findAll(null, null));
    }

    @UDALink(name = "getEnlMultProv")
    @RequestMapping(value = "comboEnlazadoMultiple/provinciaRemote", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Provincia>> getEnlMultProv() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(provinciaService.findAll(null, null));
    }

    @UDALink(name = "getEnlMultDptoProv")
    @RequestMapping(value = "comboEnlazadoMultiple/dptoProvRemote", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<DepartamentoProvincia>> getEnlMultDptoProv(
            @RequestParam(value = "departamento", required = false) BigDecimal departamento_code,
            @RequestParam(value = "provincia", required = false) BigDecimal provincia_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(departamentoProvinciaService.findAll(departamentoProvincia, null));
    }

    @UDALink(name = "getComarcaLocalidad")
    @RequestMapping(value = "comboEnlazado/comarcaLocalidad", method = RequestMethod.GET)
    public @ResponseBody
    List<Resource<Localidad>> getComarcaLocalidad(
            @RequestParam(value = "comarcaId", required = false) BigDecimal comarca_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Localidad localidad = new Localidad();
        Comarca comarca = new Comarca();
        comarca.setCode(comarca_code);
        localidad.setComarca(comarca);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResourceUtils.fromListToResource(localidadService.findAll(localidad, null));
    }

    /**
     * TABS -> Contenidos
     */
    @UDALink(name = "tabsContent")
    @RequestMapping(value = {"fragmento1", "fragmento2", "fragmento3"}, method = RequestMethod.GET)
    public String tabsContent(Model model) {
        return "tabsContent_1";
    }
    
    @UDALink(name = "tabs2Content")
    @RequestMapping(value = {"tab2Fragment"}, method = RequestMethod.GET)
    public String tabs2Content(Model model) {
        return "tabsContent_2";
    }

    @UDALink(name = "tabs3Content")
    @RequestMapping(value = {"tab3Fragment"}, method = RequestMethod.GET)
    public String tabs3Content(Model model) {
        return "tabsContent_3";
    }

    @UDALink(name = "tabSub")
    @RequestMapping(value = "pruebaSub", method = RequestMethod.GET)
    public String tabSub(Model model) {
        return "tabsContent_1";
    }
    
    @UDALink(name = "tabSubAna")
    @RequestMapping(value = "pruebaSubAna", method = RequestMethod.GET)
    public String tabSubAna(Model model) {
        return "tabsContent_2";
    }

    // rupCharts
    @UDALink(name = "getCharts")
    @RequestMapping(value = "charts", method = RequestMethod.GET)
    public String getCharts(Model model) {
        return "charts";
    }

    /**
     * GRID (Usuarios)
     */
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
    private void traceRequest(HttpServletRequest request) {

        logger.info("************************************************************");
        logger.info("******************INICIO REQUEST LOG********************");
        logger.info("************************************************************");


        logger.info("-----> Request content");
        try {
            logger.info(getStringFromInputStream(request.getInputStream()));
        } catch (IOException e) {
            logger.error("ERROR " + e.getMessage());
            logger.info("ERROR " + e.getMessage());
            e.printStackTrace();
        }


        logger.info("-----> Protocol : " + request.getProtocol());
        logger.info("-----> Method : " + request.getMethod());
        logger.info("-----> RequestURL : " + request.getRequestURL());

        logger.info("******** ATRIBUTES *************");

        Enumeration attributeNames = request.getAttributeNames();


        while (attributeNames.hasMoreElements()) {
            String nextElement = (String) attributeNames.nextElement();
            logger.info("----->" + nextElement + " : " + request.getAttribute(nextElement).toString());
        }


        logger.info("******** PARAMETERS *************");

        Map parameterMap = request.getParameterMap();

        Set keySet = parameterMap.keySet();

        for (Object key : keySet) {

            logger.info("----->" + key + " : " + request.getParameter((String) key));

//				for (int i = 0; i < array.length; i++) {
//					logger.info("----->" +key +" : " + ((String[])request.getParameterValues(key).get(key))[0].toString());
//				}
//				
//				logger.info("----->" +key +" : " + ((String[])request.getParameterValues(name).get(key))[0].toString());
        }

        logger.info("******** HEADERS *************");
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String nextElement = (String) headerNames.nextElement();
            logger.info("----->" + nextElement + " : " + request.getHeader(nextElement));
        }

        logger.info("************************************************************");
        logger.info("******************END REQUEST LOG********************");
        logger.info("************************************************************");
    }


    /**
     * MAINT (Usuarios) [form.jsp]
     */
    //Form http submit
    @UDALink(name = "getFormHttp")
    @RequestMapping(value = "form/ejemplo", method = RequestMethod.POST)
    public @ResponseBody
    Object getFormHttp(@RequestBody Alumno alumno) {

        MessageWriter messageWriter = new MessageWriter();

        messageWriter.startMessageList();
        messageWriter.addMessage("El formulario se ha enviado correctamente.");
        messageWriter.addMessage("Esta es la representaciÃ³n JSON del objeto recibido:");
        messageWriter.startSubLevel();
        messageWriter.addMessage(new JSONObject(alumno).toString());
        messageWriter.endSubLevel();
        messageWriter.endMessageList();

        return messageWriter.toString();
    }


    @ModelAttribute(value = "alumno")
    public Alumno addAlumno(@RequestParam(value = "alumno.apellido1", required = false) String apellido1,
                            @RequestParam(value = "alumno.apellido2", required = false) String apellido2,
                            @RequestParam(value = "alumno.nombre", required = false) String nombre,
                            HttpServletRequest request) {
        Alumno alumnoBind = new Alumno();

        alumnoBind.setNombre(nombre);
        alumnoBind.setApellido1(apellido1);
        alumnoBind.setApellido2(apellido2);

        return alumnoBind;
    }

    //Form ajax submit
    @UDALink(name = "getFormmMultientidades")
    @RequestMapping(value = "form/multientidades", method = RequestMethod.POST)
    public @ResponseBody
    Object getFormmMultientidades(
            @RequestJsonBody(param = "alumno") Alumno alumno,
            @RequestJsonBody(param = "departamento") Departamento departamento) {

        MessageWriter messageWriter = new MessageWriter();

        messageWriter.startMessageList();
        messageWriter.addMessage("Las entidades se han enviado correctamente");
        messageWriter.addMessage("Esta es la representaciÃ³n JSON del objeto recibido:");
        messageWriter.startSubLevel();
        messageWriter.addMessage(alumno != null ? new JSONObject(alumno).toString() : "");
        messageWriter.endSubLevel();
        messageWriter.endMessageList();

        return messageWriter.toString();
    }

    //Form ajax submit
    @UDALink(name = "getFormmMultientidadesMismoTipo")
    @RequestMapping(value = "form/multientidadesMismoTipo", method = RequestMethod.POST)
    public @ResponseBody
    Object getFormmMultientidadesMismoTipo(
            @RequestJsonBody(param = "comarca1") Comarca comarca1,
            @RequestJsonBody(param = "comarca2") Comarca comarca2,
            @RequestJsonBody(param = "comarca3") Comarca comarca3
    ) {

        MessageWriter messageWriter = new MessageWriter();

        messageWriter.startMessageList();
        messageWriter.addMessage("Las entidades se han enviado correctamente");
        messageWriter.addMessage("Esta es la representaciÃ³n JSON del objeto recibido:");
        messageWriter.startSubLevel();
        messageWriter.addMessage(comarca1 != null ? new JSONObject(comarca1).toString() : "");
        messageWriter.endSubLevel();
        messageWriter.endMessageList();

        return messageWriter.toString();

    }
    
    @UDALink(name = "addFormSimple")
    @RequestMapping(value = "form/subidaArchivos", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Object addFormSimple(
            @ModelAttribute UploadBean uploadBean,
            @RequestParam(value = "fotoPadre", required = false) MultipartFile fotoPadre,
            @RequestParam(value = "fotoMadre", required = false) MultipartFile fotoMadre,
            HttpServletResponse response) throws IOException {

        if (fotoPadre != null && !fotoPadre.isEmpty()) {
            uploadService.saveToDisk(fotoPadre, appConfiguration.getProperty("fileUpload.path"));
        }
        if (fotoMadre != null && !fotoMadre.isEmpty()) {
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
    public @ResponseBody
    Object validacion(Model model) {

        Map<String, Object> mapaRespuesta = new HashMap<String, Object>();

        mapaRespuesta.put("respuesta", "true");

        return mapaRespuesta;
    }


    /**
     * Validacion
     */

    @RequestMapping(value = "validacion/servidor", method = RequestMethod.POST)
    public @ResponseBody
    Object validacion(@Validated(value = {AlumnoEjemplo1Validation.class}) @RequestBody Alumno alumno, Model model) {

        Map<String, Object> mapaRespuesta = new HashMap<String, Object>();

        mapaRespuesta.put("respuesta", "true");

        return mapaRespuesta;
    }

    /**
     * Validacion
     *
     * @throws IOException
     */

    @RequestMapping(value = "validacion/servidor2", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Object validacion2(@RequestBody Alumno alumno, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

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
        if (errors.hasErrors()) {
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
        messageWriter.addMessage("Nivel 1.1", "Nivel 1.2", "Nivel 1.3");
        messageWriter.endSubLevel();
        messageWriter.endMessageList();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setContentType("application/json");
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

            if (a != null && a.getUsuario() != null) {
                if ("ADMIN".equals(a.getUsuario().toUpperCase())) {
                    e.rejectValue("usuario", "patron.validacion.required");
                }
            }
        }
    }


    /**
     * MÃ©todo para obtener la vista el calendarios
     *
     * @param model el modelo.
     * @return string la view.
     */
    @RequestMapping(value = "calendar/page", method = RequestMethod.GET)
    public String getCalendar(Model model) {
        return "calendar";
    }

    /**
     * MÃ©todo para obtener la vista con dos calendarios
     *
     * @param model el modelo.
     * @return string la view.
     */
    @RequestMapping(value = "calendar/pageDouble", method = RequestMethod.GET)
    public String getDoubleCalendar(Model model) {
        return "doubleCalendar";
    }
}
