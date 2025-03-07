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
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Alumno;
import com.ejie.x21a.model.AlumnoDepartamento;
import com.ejie.x21a.model.Collection;
import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.ComarcaLocalidadDTO;
import com.ejie.x21a.model.Departamento;
import com.ejie.x21a.model.DepartamentoProvincia;
import com.ejie.x21a.model.DepartamentoProvinciaDTO;
import com.ejie.x21a.model.DivisionTerritorialDto;
import com.ejie.x21a.model.FormComarcas;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.MultiPk;
import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.ProvinciaComarcaDTO;
import com.ejie.x21a.model.ProvinciaComarcaLocalidadDTO;
import com.ejie.x21a.model.RandomForm;
import com.ejie.x21a.model.TableOptions;
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
import com.ejie.x38.json.JSONArray;
import com.ejie.x38.json.JSONObject;
import com.ejie.x38.json.JsonMixin;
import com.ejie.x38.json.MessageWriter;
import com.ejie.x38.util.DateTimeManager;
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
    
    int provincia = 0;

    @javax.annotation.Resource
    private ReloadableResourceBundleMessageSource messageSource;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
    
    // Departamentos
	private Departamento ayuntamiento = new Departamento(new BigDecimal(1), "Ayuntamiento", "Udaletxea", null);
	private Departamento diputacion = new Departamento(new BigDecimal(2), "Diputación", "Aldundia", null);
	private Departamento policia = new Departamento(new BigDecimal(3), "Policía", "Polizia", null);
	private Departamento bomberos = new Departamento(new BigDecimal(4), "Bomberos", "Suhiltzaileak", null);
	
	// Provincias
	private Provincia alava = new Provincia(new BigDecimal(1), "Álava", "Araba", null);
	private Provincia vizcaya = new Provincia(new BigDecimal(2), "Vizcaya", "Bizkaia", null);
	private Provincia gipuzcoa = new Provincia(new BigDecimal(3), "Guipúzcoa", "Gipuzkoa", null);
	
	// Comarca
	private Comarca llanadaAlavesa = new Comarca(new BigDecimal(1), "Llanada alavesa", "Arabako lautada", null, alava);
	private Comarca granBilbao = new Comarca(new BigDecimal(2), "Gran Bilbao", "Bilbo handia", null, vizcaya);
	private Comarca sanSebastian = new Comarca(new BigDecimal(3), "San Sebastián", "Donostialdea", null, gipuzcoa);
	
	// Localidad
	private Localidad vitoriaGasteiz = new Localidad(new BigDecimal(1), "Vitoria-Gasteiz", "Vitoria-Gasteiz", null, llanadaAlavesa);
	private Localidad bilbo = new Localidad(new BigDecimal(2), "Bilbao", "Bilbo", null, granBilbao);
	private Localidad donostia = new Localidad(new BigDecimal(3), "San Sebastián", "Donostia", null, sanSebastian);
    
    private List<Departamento> departamentosGenerator() {
    	List<Departamento> departamentos = new ArrayList<Departamento>();
		departamentos.add(ayuntamiento);
		departamentos.add(diputacion);
		departamentos.add(policia);
		departamentos.add(bomberos);
		
		return departamentos;
    }
    
    private List<Provincia> provinciasGenerator() {		
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(alava);
		provincias.add(vizcaya);
		provincias.add(gipuzcoa);
		
		return provincias;
    }
    
    private List<Comarca> comarcasGenerator() {		
		List<Comarca> comarcas = new ArrayList<Comarca>();
		comarcas.add(llanadaAlavesa);
		comarcas.add(granBilbao);
		comarcas.add(sanSebastian);
		
		return comarcas;
    }
    
    private List<Localidad> localidadesGenerator() {		
		List<Localidad> localidades = new ArrayList<Localidad>();
		localidades.add(vitoriaGasteiz);
		localidades.add(bilbo);
		localidades.add(donostia);
		
		return localidades;
    }
	
	// Comarca - Local
	private Comarca llanadaAlavesaLocal = new Comarca(new BigDecimal(1), new BigDecimal(1), "Llanada alavesa", "Arabako lautada", null, alava);
	private Comarca granBilbaoLocal = new Comarca(new BigDecimal(2), new BigDecimal(2), "Gran Bilbao", "Bilbo handia", null, vizcaya);
	private Comarca pruebaComarcaLocal = new Comarca(new BigDecimal(4), new BigDecimal(2), "Prueba", "Froga", null, vizcaya);
	private Comarca sanSebastianLocal = new Comarca(new BigDecimal(3), new BigDecimal(3), "San Sebastián", "Donostialdea", null, gipuzcoa);
	
	// Localidad - Local
	private Localidad vitoriaGasteizLocal = new Localidad(new BigDecimal(1), new BigDecimal(1), "Vitoria-Gasteiz", "Vitoria-Gasteiz", null, llanadaAlavesaLocal);
	private Localidad bilboLocal = new Localidad(new BigDecimal(2), new BigDecimal(2), "Bilbao", "Bilbo", null, granBilbaoLocal);
	private Localidad pruebaLocalidadLocal = new Localidad(new BigDecimal(4), new BigDecimal(2), "Prueba", "Froga", null, granBilbaoLocal);
	private Localidad donostiaLocal = new Localidad(new BigDecimal(3), new BigDecimal(3), "San Sebastián", "Donostia", null, sanSebastianLocal);
    
    private List<Comarca> comarcasGeneratorLocal() {		
		List<Comarca> comarcas = new ArrayList<Comarca>();
		comarcas.add(llanadaAlavesaLocal);
		comarcas.add(granBilbaoLocal);
		comarcas.add(pruebaComarcaLocal);
		comarcas.add(sanSebastianLocal);
		
		return comarcas;
    }
    
    private List<Localidad> localidadesGeneratorLocal() {		
		List<Localidad> localidades = new ArrayList<Localidad>();
		localidades.add(vitoriaGasteizLocal);
		localidades.add(bilboLocal);
		localidades.add(pruebaLocalidadLocal);
		localidades.add(donostiaLocal);
		
		return localidades;
    }
    
    private List<DepartamentoProvincia> departamentosProvinciasGenerator() {
    	List<DepartamentoProvincia> departamentoProvincia = new ArrayList<DepartamentoProvincia>();
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(1), "Ayuntamiento de Álava", "Arabako udaletxea", null, alava, ayuntamiento));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(2), "Diputación de Álava", "Arabako aldundia", null, alava, diputacion));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(3), "Policía de Álava", "Arabako polizia", null, alava, policia));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(4), "Bomberos de Álava", "Arabako suhiltzaileak", null, alava, bomberos));

    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(5), "Ayuntamiento de Vizcaya", "Bizkaiko udaletxea", null, vizcaya, ayuntamiento));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(6), "Diputación de Vizcaya", "Bizkaiko aldundia", null, vizcaya, diputacion));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(7), "Policía de Vizcaya", "Bizkaiko polizia", null, vizcaya, policia));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(8), "Bomberos de Vizcaya", "Bizkaiko suhiltzaileak", null, vizcaya, bomberos));

    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(9), "Ayuntamiento de Gipúzcoa", "Gipuzkoako udaletxea", null, gipuzcoa, ayuntamiento));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(10), "Diputación de Gipúzcoa", "Gipuzkoako aldundia", null, gipuzcoa, diputacion));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(11), "Policía de Gipúzcoa", "Gipuzkoako polizia", null, gipuzcoa, policia));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(12), "Bomberos de Gipúzcoa", "Gipuzkoako suhiltzaileak", null, gipuzcoa, bomberos));
    	
		return departamentoProvincia;
    }
    
    private List<DepartamentoProvincia> departamentosProvinciasGeneratorLocal() {
    	List<DepartamentoProvincia> departamentoProvincia = new ArrayList<DepartamentoProvincia>();
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(1), "Ayuntamiento de Álava", "Arabako udaletxea", null, alava, ayuntamiento, "1##1"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(2), "Diputación de Álava", "Arabako aldundia", null, alava, diputacion, "2##1"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(3), "Policía de Álava", "Arabako polizia", null, alava, policia, "3##1"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(4), "Bomberos de Álava", "Arabako suhiltzaileak", null, alava, bomberos, "4##1"));

    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(5), "Ayuntamiento de Vizcaya", "Bizkaiko udaletxea", null, vizcaya, ayuntamiento, "1##2"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(6), "Diputación de Vizcaya", "Bizkaiko aldundia", null, vizcaya, diputacion, "2##2"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(7), "Policía de Vizcaya", "Bizkaiko polizia", null, vizcaya, policia, "3##2"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(8), "Bomberos de Vizcaya", "Bizkaiko suhiltzaileak", null, vizcaya, bomberos, "4##2"));

    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(9), "Ayuntamiento de Gipúzcoa", "Gipuzkoako udaletxea", null, gipuzcoa, ayuntamiento, "1##3"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(10), "Diputación de Gipúzcoa", "Gipuzkoako aldundia", null, gipuzcoa, diputacion, "2##3"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(11), "Policía de Gipúzcoa", "Gipuzkoako polizia", null, gipuzcoa, policia, "3##3"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(12), "Bomberos de Gipúzcoa", "Gipuzkoako suhiltzaileak", null, gipuzcoa, bomberos, "4##3"));
    	
		return departamentoProvincia;
    }
    
    private List<DepartamentoProvincia> departamentosProvinciasGeneratorSelect() {
    	List<DepartamentoProvincia> departamentoProvincia = new ArrayList<DepartamentoProvincia>();
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(1), "Ayuntamiento de Álava", "Arabako udaletxea", null, alava, ayuntamiento,"1##1"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(2), "Diputación de Álava", "Arabako aldundia", null, alava, diputacion,"2##1"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(3), "Policía de Álava", "Arabako polizia", null, alava, policia,"3##1"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(4), "Bomberos de Álava", "Arabako suhiltzaileak", null, alava, bomberos,"4##1"));

    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(5), "Ayuntamiento de Vizcaya", "Bizkaiko udaletxea", null, vizcaya, ayuntamiento,"1##2"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(6), "Diputación de Vizcaya", "Bizkaiko aldundia", null, vizcaya, diputacion,"2##2"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(7), "Policía de Vizcaya", "Bizkaiko polizia", null, vizcaya, policia,"3##2"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(8), "Bomberos de Vizcaya", "Bizkaiko suhiltzaileak", null, vizcaya, bomberos,"4##2"));

    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(9), "Ayuntamiento de Gipúzcoa", "Gipuzkoako udaletxea", null, gipuzcoa, ayuntamiento,"1##3"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(10), "Diputación de Gipúzcoa", "Gipuzkoako aldundia", null, gipuzcoa, diputacion,"2##3"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(11), "Policía de Gipúzcoa", "Gipuzkoako polizia", null, gipuzcoa, policia,"3##3"));
    	departamentoProvincia.add(new DepartamentoProvincia(new BigDecimal(12), "Bomberos de Gipúzcoa", "Gipuzkoako suhiltzaileak", null, gipuzcoa, bomberos,"4##3"));
    	
		return departamentoProvincia;
    }
    
    private List<Comarca> comarcasGeneratorSelect() {		
		List<Comarca> comarcas = new ArrayList<Comarca>();
		comarcas.add(new Comarca(new BigDecimal(1),new BigDecimal(1), "Llanada alavesa", "Arabako lautada","1", null));
		comarcas.add(new Comarca(new BigDecimal(2),new BigDecimal(1), "Oyonesa", "Arabako lautada","1", null));
		comarcas.add(new Comarca(new BigDecimal(3),new BigDecimal(1), "Gamarresa", "Arabako lautada","1", null));
		
		comarcas.add(new Comarca(new BigDecimal(4),new BigDecimal(2), "Pequeño Bilbao", "Arabako lautada","2", null));
		comarcas.add(new Comarca(new BigDecimal(5),new BigDecimal(2), "Las Playas", "Arabako lautada","2", null));
		comarcas.add(new Comarca(new BigDecimal(6),new BigDecimal(2), "Gran Bilbao", "Arabako lautada","2", null));
		
		comarcas.add(new Comarca(new BigDecimal(7),new BigDecimal(3), "Donosti", "Arabako lautada","3", null));
		comarcas.add(new Comarca(new BigDecimal(8),new BigDecimal(3), "Zarautz", "Arabako lautada","3", null));
		comarcas.add(new Comarca(new BigDecimal(9),new BigDecimal(3), "Eibar", "Arabako lautada","3", null));
		
		comarcas.add(new Comarca(new BigDecimal(10),new BigDecimal(4), "Aranda de Duero", "Arabako lautada","4", null));
		comarcas.add(new Comarca(new BigDecimal(11),new BigDecimal(4), "Burgos", "Arabako lautada","4", null));
		comarcas.add(new Comarca(new BigDecimal(12),new BigDecimal(4), "Miranda de Ebro", "Arabako lautada","4", null));
		
		return comarcas;
    }

    //Sleep
    @GetMapping(value = "sleep/{ms}")
    public String getSleep(Model model, @PathVariable Integer ms) throws InterruptedException {
        Thread.sleep(ms);
        return "accordion";
    }

    //Accordion
    @GetMapping(value = "accordion")
    public String getAccordion(Model model) {
        return "accordion";
    }

    //Button (
    @GetMapping(value = "button")
    public String buttonJSP(Model model) {
        return "button";
    }

    //Date
    @GetMapping(value = "date")
    public String getDate(Model model) {
        return "date";
    }

    //Dialog
    @GetMapping(value = "dialog")
    public String getDialog(Model model) {
        return "dialog";
    }

    //Dialog (petición Ajax)
    @GetMapping(value = "dialogAjax")
    public String dialogJSP(Model model) {
        return "dialogAjax";
    }
    
    //Select Simple
    @GetMapping(value = "selectSimple")
    public String getSelectSimple(Model model) {
    	model.addAttribute("provincia", new Provincia());
    	model.addAttribute("divisionTerritorialDto", new DivisionTerritorialDto());
    	
        return "selectSimple";
    }
    
    //SelectEnlazado - simple
    @GetMapping(value = "selectEnlazadoSimple")
    public String getSelectEnlazadoSimple(Model model) {
    	model.addAttribute("provinciaComarcaLocalidadDTO", new ProvinciaComarcaLocalidadDTO());
    	model.addAttribute("provinciaComarcaDTO", new ProvinciaComarcaDTO());
    	model.addAttribute("comarcaLocalidadDTO", new ComarcaLocalidadDTO());
    	
		// Provincias
    	List<Provincia> listaProvincias = provinciasGenerator();
		model.addAttribute("comboProvincia", listaProvincias);
		
		// Comarcas
		model.addAttribute("comboComarca", comarcasGeneratorSelect());
    	
        return "selectEnlazadoSimple";
    }
    
    //selectEnlazado - multiple
    @GetMapping(value = "selectEnlazadoMultiple")
    public String getSelectEnlazadoMultiple(Model model) {
    	model.addAttribute("departamentoProvinciaDTO", new DepartamentoProvinciaDTO());
    	
    	// Departamentos
    	model.addAttribute("comboDepartamento", departamentosGenerator());
		
		// Provincias
		model.addAttribute("comboProvincia", provinciasGenerator());
    	
		// Departamentos y provincias
    	model.addAttribute("comboDepartamentoProvincia", departamentosProvinciasGeneratorSelect());
		
        return "selectEnlazadoMultiple";
    }
    
    //MultiSelect
    @GetMapping(value = "selectMultiselect")
    public String getSelectMultiSelect(Model model) {
    	model.addAttribute("provincia", new Provincia());
    	model.addAttribute("provinciaComarcaLocalidadDTO", new ProvinciaComarcaLocalidadDTO());
    	
        return "selectMultiselect";
    }

    //select en mantenimiento
    @GetMapping(value = "selectMantenimiento")
    public String getSelectMantenimiento(Model model) {
        model.addAttribute("X21aAlumno", new Alumno());
        return "selectMantenimiento";
    }
    
    // Select Autocomplete
    @GetMapping(value = "selectAutocomplete")
    public String getSelectAutocomplete(Model model) {
    	model.addAttribute("departamentoProvincia", new DepartamentoProvincia());
    	model.addAttribute("provincia", new Provincia());
    	
        return "selectAutocomplete";
    }

    // Select Autocomplete Enlazado
    @GetMapping(value = "selectAutocompleteEnlazado")
    public String getSelectAutocompleteEnlazado(Model model) {
    	
    	model.addAttribute("provinciaComarcaLocalidadDTO", new ProvinciaComarcaLocalidadDTO());
    	model.addAttribute("provinciaComarcaDTO", new ProvinciaComarcaDTO());
    	model.addAttribute("comarcaLocalidadDTO", new ComarcaLocalidadDTO());
    	
		// Provincias
		model.addAttribute("selectAutocompleteProvincia", provinciasGenerator());
		
		// Comarcas
		model.addAttribute("selectAutocompleteComarca", comarcasGeneratorSelect());
		
        return "selectAutocompleteEnlazado";
    }

    // Select Autocomplete Enlazado Multiple
    @GetMapping(value = "selectAutocompleteEnlazadoMultiple")
    public String getSelectAutocompleteEnlazadoMultiple(Model model) {
        return "selectAutocompleteEnlazadoMultiple";
    }
    
 // Select Autocomplete Enlazado Multiple
    @GetMapping(value = "selectAutocompleteMultiple")
    public String getSelectAutocompleteMultiple(Model model) {
    	model.addAttribute("departamentoProvincia", new DepartamentoProvincia());
    	model.addAttribute("provincia", new Provincia());
        return "selectAutocompleteMultiple";
    }
    //Feedback
    @GetMapping(value = "feedback")
    public String getFeedback(Model model) {
        return "feedback";
    }

    //Form
    @GetMapping(value = "form")
    public String getForm(Model model){

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
    @GetMapping(value = "grid")
    public String getGrid(Model model) {
        return "grid";
    }

    //Menu
    @GetMapping(value = "menu")
    public String getMenu(Model model) {
        return "menu";
    }

    //Menu Vertical
    @GetMapping(value = "menuVertical")
    public String getMenuVertical(Model model) {
        model.addAttribute("defaultLayout", "vertical");
        return "menuVertical";
    }

    //Menu Mixto
    @GetMapping(value = "menuMixto")
    public String getMenuMixto(Model model) {
        model.addAttribute("defaultLayout", "mixto");
        return "menuMixto";
    }

    //Message
    @GetMapping(value = "message")
    public String getMessage(Model model) {
        return "message";
    }

    //ProgressBar
    @GetMapping(value = "progressBar")
    public String getProgressBar(Model model) {
        return "progressBar";
    }

    //Slider
    @GetMapping(value = "slider")
    public String getSlider(Model model) {
        return "slider";
    }

    //Spinner
    @GetMapping(value = "spinner")
    public String getSpinner(Model model) {
        return "spinner";
    }

    //Tabs con carga de la pagina
    @GetMapping(value = "tabsStatic")
    public String getTabsStatic(Model model) {
        return "tabsStatic";
    }

    //Tabs con carga ajax
    @GetMapping(value = "tabsAjax")
    public String getTabsAjax(Model model) {
        return "tabsAjax";
    }

    //Tabs Mixto
    @GetMapping(value = "tabsMixto")
    public String getTabsMixto(Model model) {
        return "tabsMixto";
    }

    //Tabs Multiples mantenimientos
    @GetMapping(value = {"maintTab", "pruebaSub3Maint"})
    public String getMaintTab(Model model) {
        return "maintTab";
    }

    //Tabs Scrollable
    @GetMapping(value = "tabsScrollable")
    public String geTabsScrollable(Model model) {
        return "tabsScrollable";
    }

    //Time
    @GetMapping(value = "time")
    public String getTime(Model model) {
        return "time";
    }

    //Toolbar
    @GetMapping(value = "toolbar")
    public String getToolbar(Model model) {
        return "toolbar";
    }

    //Tooltip
    @GetMapping(value = "tooltip")
    public String getTooltip(Model model) {
        return "tooltip";
    }

    //Upload
    @GetMapping(value = "upload")
    public String getUpload(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("collection", new Collection());
        return "upload";
    }

    //Wizard
    @GetMapping(value = "wizard")
    public String getWizard(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard";
    }

    //Wizard_includeFile
    @GetMapping(value = "wizard_includeFile")
    public String getWizard_includeFile(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_includeFile";
    }

    //Wizard_jspInclude
    @GetMapping(value = "wizard_jspInclude")
    public String getWizard_jspInclude(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_jspInclude";
    }

    //Wizard_jstlImport
    @GetMapping(value = "wizard_jstlImport")
    public String getWizard_jstlImporte(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_jstlImport";
    }

    //Wizard dinamico
    @GetMapping(value = "wizard_dinamico")
    public String getWizard_dinamico(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "wizard_dinamico";
    }

    @GetMapping(value = "wizard_dinamico_content")
    public String getWizard_dinamico_content(Model model) {
        return "wizard_dinamico_content";
    }

    //Tree
    @GetMapping(value = "trees")
    public String getTrees(Model model) {
        return "trees";
    }

    @GetMapping(value = "treeDAD")
    public String getTreeDragAndDrop(Model model) {
        return "treeDAD";
    }
    
    @GetMapping(value = "ajaxTree")
    public Object getTreeAjax(Model model, HttpServletResponse response) {

        // S
        JSONArray root = new JSONArray();

        JSONObject padre1 = new JSONObject("{'id':'p1', 'text':'Padre1'}");

        JSONObject padre11 = new JSONObject("{'id':'p11', 'text':'Padre1.1'}");

        JSONObject hoja111 = new JSONObject("{'id':'p111', 'text':'Padre1.1.1'}");

        JSONObject padre112 = new JSONObject("{'id':'p112', 'text':'Padre1.1.2'}");

        JSONObject hoja1121 = new JSONObject("{'id':'p1121', 'text':'Padre1.1.2.1'}");

        JSONObject hoja1122 = new JSONObject("{'id':'p1122', 'text':'Padre1.1.2.2'}");

        JSONObject hoja12 = new JSONObject("{'id':'p12', 'text':'Padre1.2'}");

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

        response.setContentType("application/json;charset=UTF-8");
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
    @GetMapping(value = "validate")
    public String getValidate(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("randomForm", new RandomForm());
        return "validate";
    }

    //Validate
    @GetMapping(value = "validateRules")
    public String getValidateRules(Model model) {

        model.addAttribute("alumno", new Alumno());
        return "validateRules";
    }

    @GetMapping(value = "validateRup")
    public String getValidateRup(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("randomForm", new RandomForm());
        return "validateRup";
    }


    //All (todos los patrones en una pagina)
    @GetMapping(value = "all")
    public String getAll(Model model) {
    	model.addAttribute("comarca", new Comarca());
        return "all";
    }

    //AllDialog (todos los patrones en un dialogo)
    @GetMapping(value = "allDialog")
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
    @GetMapping(value = "contextMenu")
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
    @GetMapping(value = "autocomplete/remote")
    public @ResponseBody
    List<DepartamentoProvincia> getRemoteAutocomplete(
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
        
        return departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c);
    }
    
    
    /**
     * AUTOCOMPLETE REMOTO ENLAZADO
     */
    @GetMapping(value = "autocompleteEnlazadoSimple/provinciaComarcaLocalidadDTO")
    public @ResponseBody ProvinciaComarcaLocalidadDTO getAutocompleteProvinciaComarcaLocalidadDTO(
    		@RequestBody ProvinciaComarcaLocalidadDTO provinciaComarcaLocalidadDTO) {
        return provinciaComarcaLocalidadDTO;
    }
    
    @GetMapping(value = "autocomplete/remoteEnlazadoProvincia")
    public @ResponseBody
    List<Provincia> getProvinciaEnlazadoAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c) {
    	
    	Provincia provincia = new Provincia();
    	if(q != null) {
    		Locale locale = LocaleContextHolder.getLocale();
            if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
                provincia.setDescEu(q);
            } else {
                provincia.setDescEs(q);
            }
    	}
        return provinciaService.findAllLike(provincia, null, !c);
    }
    
    @GetMapping(value = "autocomplete/remoteEnlazadoComarca")
    public @ResponseBody
    List<Comarca> getComarcaEnlazadoAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "codeProvincia", required = false) BigDecimal codProvincia) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Provincia provincia = new Provincia();
        provincia.setCode(codProvincia);
        
        Comarca comarca = new Comarca();
        comarca.setProvincia(provincia);
    	if(q != null) {
    		Locale locale = LocaleContextHolder.getLocale();
            if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            	comarca.setDescEu(q);
            } else {
            	comarca.setDescEs(q);
            }
    	}
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return comarcaService.findAllLike(comarca, null, !c);
    }
    
    @GetMapping(value = "autocomplete/remoteEnlazadoLocalidad")
    public @ResponseBody
    List<Localidad> getLocalidadEnlazadoAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "codeComarca", required = false) BigDecimal codComarca) {
    	
    	//Convertir parámetros en entidad para búsqueda
        Comarca comarca = new Comarca();
        comarca.setCode(codComarca);
        
        Localidad localidad = new Localidad();
        localidad.setComarca(comarca);
        
    	if(q != null) {
    		Locale locale = LocaleContextHolder.getLocale();
            if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            	localidad.setDescEu(q);
            } else {
            	localidad.setDescEs(q);
            }
    	}
        
        return localidadService.findAllLike(localidad, null, !c);
    }
    
    /**
     * AUTOCOMPLETE REMOTO ENLAZADO M�LTIPLE
     */
    @GetMapping(value = "autocompleteEnlazadoMultiple/departamentoProvinciaDTO")
    public @ResponseBody DepartamentoProvinciaDTO getAutocompleteDepartamentoProvinciaDTO(
    		@RequestBody DepartamentoProvinciaDTO departamentoProvinciaDTO) {
        return departamentoProvinciaDTO;
    }
    
    @GetMapping(value = "autocomplete/departamentoRemote")
    public @ResponseBody
    List<Departamento> getDepartamentoEnlazadoMultipleAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c) {
    	
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
        return departamentoService.findAllLike(null, null, !c);
    }
    
    @GetMapping(value = "autocomplete/provinciaRemote")
    public @ResponseBody
    List<Provincia> getProvinciaEnlazadoMultipleAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c) {
    	
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return provinciaService.findAllLike(null, null, !c);
    }
    
    @GetMapping(value = "autocomplete/dptoProvRemote")
    public @ResponseBody
    List<DepartamentoProvincia> getDepartamentoProvinciaEnlazadoMultipleAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "codeDepartamento", required = false) BigDecimal departamento_code,
            @RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);
      //Idioma
        Locale locale = LocaleContextHolder.getLocale();
        
        if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            departamentoProvincia.setDescEu(q);
        } else {
            departamentoProvincia.setDescEs(q);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c);
    }
    
    @GetMapping(value = "autocomplete/dptoProvRemoteNoParam")
    public @ResponseBody
    List<DepartamentoProvincia> getDepartamentoProvinciaEnlazadoMultipleAutocompleteNoParam(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "codeDepartamento", required = false) BigDecimal departamento_code,
            @RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);
      //Idioma
        Locale locale = LocaleContextHolder.getLocale();
        
        if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            departamentoProvincia.setDescEu(q);
        } else {
            departamentoProvincia.setDescEs(q);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c);
    }
    
    @GetMapping(value = "autocomplete/dptoProvRemoteNoParamUno")
    public @ResponseBody
    List<DepartamentoProvincia> getDepartamentoProvinciaEnlazadoMultipleAutocompleteNoParamUno(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "codeDepartamento", required = false) BigDecimal departamento_code,
            @RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {
    	
    	//Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);
      //Idioma
        Locale locale = LocaleContextHolder.getLocale();
        
        if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())) {
            departamentoProvincia.setDescEu(q);
        } else {
            departamentoProvincia.setDescEs(q);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c);
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

    @Json(mixins = {@JsonMixin(target = Provincia.class, mixin = ProvinciaMixIn.class)})
    @GetMapping(value = "comboSimple/remote")
    public @ResponseBody List<Provincia> getComboRemote() {
        List<Provincia> listaProvincias = provinciaService.findAll(null, null);
        if(provincia == 0){
        	provincia = 1;
        }else{
        	provincia = 0;
        	Provincia provi = new Provincia(new BigDecimal(33), "Hugo", "Hugo", "Hugo");
        	listaProvincias.remove(0);
			listaProvincias.add(provi);
        }
        return listaProvincias;
    }
    
    @GetMapping(value = "comboSimple/remoteGroup")
    public @ResponseBody
    List<HashMap<String, List<DivisionTerritorialDto>>> getRemoteComboGrupos() {
        return this.setRemoteComboGruposEnlazado(null);
    }
    
    @GetMapping(value = "comboSimple/remoteGroupEnlazado")
    public @ResponseBody
    List<HashMap<String, List<DivisionTerritorialDto>>> getRemoteComboGruposEnlazado(
    		@RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {
    	return this.setRemoteComboGruposEnlazado(provincia_code);
    }
    
    private List<HashMap<String, List<DivisionTerritorialDto>>> setRemoteComboGruposEnlazado(BigDecimal provincia_code) {

        //Idioma
        Locale locale = LocaleContextHolder.getLocale();

        //Retorno del método
        List<HashMap<String, List<DivisionTerritorialDto>>> retorno = new ArrayList<HashMap<String, List<DivisionTerritorialDto>>>();

        //Nombres de los grupos según idioma
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

        //Provincia
        HashMap<String, List<DivisionTerritorialDto>> group = new HashMap<String, List<DivisionTerritorialDto>>();

        Provincia provinciaFilter = null;

        if (provincia_code == null) {

        	List<Provincia> lista = provinciaService.findAll(null, null);

			try {
				List<DivisionTerritorialDto> listaDivision = listToDivisionResource(lista,"Prov");
				group.put(provincia, listaDivision);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
            retorno.add(group);
        } else {
            provinciaFilter = new Provincia();
            provinciaFilter.setCode(provincia_code);
        }

        //Comarca
        group = new HashMap<String, List<DivisionTerritorialDto>>();
        List<Comarca> listaComarca = new ArrayList<Comarca>();
        if (provincia_code != null) {
            Comarca comarcaFilter = new Comarca();
            comarcaFilter.setProvincia(provinciaFilter);
            listaComarca = comarcaService.findAll(comarcaFilter, null);
            
        } else {
        	listaComarca = comarcaService.findAll(null, null);
        }
                
		try {
			List<DivisionTerritorialDto> listaDivision = listToDivisionResource(listaComarca,"Coma");
			group.put(comarca, listaDivision);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        retorno.add(group);

        //Localidad
        group = new HashMap<String, List<DivisionTerritorialDto>>();
        List<Localidad> listaLocalidad = new ArrayList<Localidad>();
        if (provincia_code != null) {
            Localidad localidadFilter = new Localidad();
            Comarca comarcaFilter = new Comarca();
            comarcaFilter.setProvincia(provinciaFilter);
            localidadFilter.setComarca(comarcaFilter);
            listaLocalidad= localidadService.findAll(localidadFilter, null);
        } else {
        	listaLocalidad= localidadService.findAll(null, null);
        }
        
		try {
			List<DivisionTerritorialDto> listaDivision = listToDivisionResource(listaLocalidad,"Loca");
			group.put(localidad, listaDivision);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        retorno.add(group);

        return retorno;
    }


    private List<DivisionTerritorialDto> listToDivisionResource(List<?> lista, String tipo) throws SecurityException, NoSuchFieldException {
    	List<DivisionTerritorialDto> listaDivision = new ArrayList<DivisionTerritorialDto>();
    	for (int i = 0; i < lista.size(); i++) {
    		
    		Object obj = lista.get(i);
    		Class<?> c = obj.getClass();
    		Field code = c.getDeclaredField("code");
    		Field descEs = c.getDeclaredField("descEs");
    		Field descEu = c.getDeclaredField("descEu");
    		Field css = c.getDeclaredField("css");
    		code.setAccessible(true);
    		descEs.setAccessible(true);
    		descEu.setAccessible(true);
    		css.setAccessible(true);
    		try {
				DivisionTerritorialDto dt = new DivisionTerritorialDto(code.get(obj) + tipo,(String) descEs.get(obj), (String) descEu.get(obj),(String) css.get(obj));
				listaDivision.add(dt );
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaDivision;
	}

	/**
     * COMBO ENLAZADO SIMPLE
     */
    @GetMapping(value = "comboEnlazadoSimple/provinciaComarcaLocalidadDTO")
    public @ResponseBody ProvinciaComarcaLocalidadDTO getProvinciaComarcaLocalidadDTO(
    		@RequestBody ProvinciaComarcaLocalidadDTO provinciaComarcaLocalidadDTO) {
        return provinciaComarcaLocalidadDTO;
    }
    
    @GetMapping(value = "comboEnlazadoSimple/provinciaComarcaDTO")
    public @ResponseBody ProvinciaComarcaDTO getProvinciaComarcaDTO(
    		@RequestBody ProvinciaComarcaDTO provinciaComarcaDTO) {
        return provinciaComarcaDTO;
    }
    
    @GetMapping(value = "comboEnlazadoSimple/comarcaLocalidadDTO")
    public @ResponseBody ProvinciaComarcaLocalidadDTO getComarcaLocalidadDTO(
    		@RequestBody ProvinciaComarcaLocalidadDTO provinciaComarcaLocalidadDTO) {
        return provinciaComarcaLocalidadDTO;
    }
    
    @GetMapping(value = "comboEnlazadoSimple/remoteEnlazadoProvincia")
    public @ResponseBody
    List<Provincia> getEnlazadoProvincia() {
        return provinciaService.findAll(null, null);
    }
    
    @GetMapping(value = "comboEnlazadoSimple/remoteEnlazadoComarca")
    public @ResponseBody
    List<Comarca> getEnlazadoComarca(
            @RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {

        //Convertir parámetros en entidad para búsqueda
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        Comarca comarca = new Comarca();
        comarca.setProvincia(provincia);
        
        return comarcaService.findAll(comarca, null);
    }

    @GetMapping(value = "comboEnlazadoSimple/remoteEnlazadoLocalidad")
    public @ResponseBody
    List<Localidad> getEnlazadoLocalidad(
            @RequestParam(value = "codeComarca", required = false) BigDecimal comarca_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Comarca comarca = new Comarca();
        comarca.setCode(comarca_code);
        Localidad localidad = new Localidad();
        localidad.setComarca(comarca);
        
        return localidadService.findAll(localidad, null);
    }
    
    @GetMapping(value = "comboEnlazadoSimple/remoteEnlazadoComarcaTable")
    public @ResponseBody
    List<Comarca> getEnlazadoComarcaTable(
            @RequestParam(value = "provincia.code", required = false) BigDecimal provincia_code) {

        //Convertir parámetros en entidad para búsqueda
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        Comarca comarca = new Comarca();
        comarca.setProvincia(provincia);
        
        return comarcaService.findAll(comarca, null);
    }
    
    @GetMapping(value = "comboEnlazadoSimple/remoteEnlazadoLocalidadMultiple")
    public @ResponseBody
    List<Localidad> getEnlazadoLocalidadMultiple(
            @RequestParam(value = "codeComarca", required = false) List<BigDecimal> listaComarcas) {
    	List<Localidad> listaLocalidades = new ArrayList<Localidad>();
    	for (BigDecimal key : listaComarcas) {
	        //Convertir parÃ¡metros en entidad para bÃºsqueda
	        Comarca comarca = new Comarca();
	        comarca.setCode(key);
	        Localidad localidad = new Localidad();
	        localidad.setComarca(comarca);
	        
	        List<Localidad> lista = localidadService.findAll(localidad, null);
	        listaLocalidades.addAll(lista);
    	}
        return listaLocalidades;
    }
    
    @GetMapping(value = "comboEnlazadoSimple/remoteGroupEnlazadoComarcaLocalidad")
    public @ResponseBody
    List<HashMap<String, List<DivisionTerritorialDto>>> getEnlazadoComarcaLocalidad(
    		@RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {
    	return this.setRemoteComboGruposEnlazado(provincia_code);
    }
    
    @GetMapping(value = "comboEnlazadoSimple/remoteEnlazadoComarcaNoParam")
    public @ResponseBody
    List<Comarca> getEnlazadoComarcaNoParam(
            @RequestParam(value = "codeProvincia", required = true) BigDecimal provincia_code) throws Exception {

    	if(provincia_code.intValueExact() < 1 || provincia_code.intValueExact() > 6){
    		throw new Exception("Identificador no valido");
    	}
        //Convertir parámetros en entidad para búsqueda
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        Comarca comarca = new Comarca();
        comarca.setProvincia(provincia);
        
        return comarcaService.findAll(comarca, null);
    }

    @GetMapping(value = "comboEnlazado/comarcaLocalidad")
    public @ResponseBody
    List<Localidad> getComarcaLocalidad(
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
        return localidadService.findAll(localidad, null);
    }


    /**
     * COMBO ENLAZADO MULTIPLE
     */
    /**
     * Combos Enlazados (múltiple)
     */
    @GetMapping(value = "comboEnlazadoMultiple/departamentoProvinciaDTO")
    public @ResponseBody DepartamentoProvinciaDTO getDepartamentoProvinciaDTO(
    		@RequestBody DepartamentoProvinciaDTO departamentoProvinciaDTO) {
        return departamentoProvinciaDTO;
    }
    
    @GetMapping(value = "comboEnlazadoMultiple/departamentoRemote")
    public @ResponseBody List<Departamento> getEnlMultDpto() {
        return departamentoService.findAll(null, null);
    }

    @GetMapping(value = "comboEnlazadoMultiple/provinciaRemote")
    public @ResponseBody List<Provincia> getEnlMultProv() {
        return provinciaService.findAll(null, null);
    }

    @GetMapping(value = "comboEnlazadoMultiple/dptoProvRemote")
    public @ResponseBody List<DepartamentoProvincia> getEnlMultDptoProv(
            @RequestParam(value = "codeDepartamento", required = false) BigDecimal departamento_code,
            @RequestParam(value = "codeProvincia", required = false) BigDecimal provincia_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        Provincia provincia = new Provincia();
        provincia.setCode(provincia_code);
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);
        
        return departamentoProvinciaService.findAll(departamentoProvincia, null);
    }
    
    @GetMapping(value = "comboEnlazadoMultiple/dptoProvRemoteNoParam")
    public @ResponseBody
    List<DepartamentoProvincia> getEnlMultDptoProvNoParam(
            @RequestParam(value = "codeDepartamento", required = false) BigDecimal departamento_code,
            @RequestParam(value = "codeProvincia", required = false) Integer provincia_code) {

        //Convertir parÃ¡metros en entidad para bÃºsqueda
        Departamento departamento = new Departamento();
        departamento.setCode(departamento_code);
        Provincia provincia = new Provincia();
        provincia.setCode(new BigDecimal(provincia_code));
        DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
        departamentoProvincia.setDepartamento(departamento);
        departamentoProvincia.setProvincia(provincia);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return departamentoProvinciaService.findAll(departamentoProvincia, null);
    }

    /**
     * TABS -> Contenidos
     */
    @GetMapping(value = {"fragmento1", "fragmento2", "fragmento3"})
    public String tabsContent(Model model) {
        return "tabsContent_1";
    }
    
    @GetMapping(value = {"tab2Fragment"})
    public String tabs2Content(Model model) {
        return "tabsContent_2";
    }

    @GetMapping(value = {"tab3Fragment"})
    public String tabs3Content(Model model) {
        return "tabsContent_3";
    }
    
	@GetMapping(value = "/tabs4Table")
	public String tabs4Table (Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("options", new TableOptions());
		
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
		comboEjie.put("1", "Sí");
		model.addAttribute("comboEjie", comboEjie);
		
		return "tabsTable_4";
	}
	
	@GetMapping(value = "/tabs5TableMultiPk")
	public String tabs5TableMultiPk (Model model) {
		model.addAttribute("multiPk", new MultiPk());
		model.addAttribute("options", new TableOptions());
		return "tabsTableMultiPk_5";
	}

    @GetMapping(value = "pruebaSub")
    public String tabSub(Model model) {
        return "tabsContent_1";
    }
    
    @GetMapping(value = "pruebaSubAna")
    public String tabSubAna(Model model) {
        return "tabsContent_2";
    }

    // rupCharts
    @GetMapping(value = "charts")
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
    @PostMapping(value = "form/ejemplo")
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
    
    /**
     * MAINT (Usuarios) [form.jsp]
     */
    //Form http submit
    @PostMapping(value = "form/ejemploModelView")
    public @ResponseBody Alumno getFormModelView(Model model, @RequestBody Alumno alumno) {

        List<NoraPais> paises = noraPaisService.findAll(null, null);
        model.addAttribute("paises", paises);

        List<NoraAutonomia> autonomias = noraAutonomiaService.findAll(null, null);
        model.addAttribute("autonomias", autonomias);

        //model.addAttribute("comarca", new Comarca());

        //model.addAttribute("formComarcas", new FormComarcas());
        alumno.setId(new BigDecimal(1300));
        model.addAttribute("alumno", alumno);

        return alumno;
    }
    
    @PostMapping(value = "form/ejemploFormNavigate")
    public String getFormNavigate(Model model, @ModelAttribute  Alumno alumno) {

        List<NoraPais> paises = noraPaisService.findAll(null, null);
        model.addAttribute("paises", paises);

        List<NoraAutonomia> autonomias = noraAutonomiaService.findAll(null, null);
        model.addAttribute("autonomias", autonomias);

        //model.addAttribute("comarca", new Comarca());

        //model.addAttribute("formComarcas", new FormComarcas());
        Usuario usuario = new Usuario();
        
        if(alumno != null){
        	alumno.setId(new BigDecimal(1300));
        }
        usuario.setId(alumno.getId()+"");
        usuario.setNombre(alumno.getNombre());
        model.addAttribute("alumno", alumno);

        model.addAttribute("usuarioDATA", usuario);

        return "formModelView";
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
    @PostMapping(value = "form/multientidades")
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
    @PostMapping(value = "form/multientidadesMismoTipo")
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
    
    @PostMapping(value = "form/subidaArchivos", produces = "application/json")
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

    @PostMapping(value = "validacion/cliente")
    public @ResponseBody
    Object validacion(Model model) {

        Map<String, Object> mapaRespuesta = new HashMap<String, Object>();

        mapaRespuesta.put("respuesta", "true");

        return mapaRespuesta;
    }


    /**
     * Validacion
     */

    @PostMapping(value = "validacion/servidor")
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

    @PostMapping(value = "validacion/servidor2", produces = "application/json")
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
    @GetMapping(value = "calendar/page")
    public String getCalendar(Model model) {
        return "calendar";
    }

    /**
     * MÃ©todo para obtener la vista con dos calendarios
     *
     * @param model el modelo.
     * @return string la view.
     */
    @GetMapping(value = "calendar/pageDouble")
    public String getDoubleCalendar(Model model) {
        return "doubleCalendar";
    }
}
