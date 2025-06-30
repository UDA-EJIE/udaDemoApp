package com.ejie.aa79b.control;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.SeccionesControlDeCalidad;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.service.SeccionesControlDeCalidadService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;

@Controller
@RequestMapping(value = "/administracion/configuracionauditoria/seccionescontroldecalidad")

public class SeccionesControlDeCalidadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeccionesControlDeCalidadController.class);

    @Autowired
    private SeccionesControlDeCalidadService seccionesControlDeCalidadService;
	@Autowired
	private ExcelReportService aa79bExcelReportService;
	@Autowired()
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
    
    private static final String LITERAL_LABEL = "titulo.seccionesControlDeCalidad";
      /*
     * OPERACIONES CRUD (Create, Read, Update, Delete)
     * 
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody SeccionesControlDeCalidad get(@PathVariable Long id) {
    	SeccionesControlDeCalidad seccionesControlDeCalidad = new SeccionesControlDeCalidad();
    	seccionesControlDeCalidad.setId(id);
    	seccionesControlDeCalidad = this.seccionesControlDeCalidadService.find(seccionesControlDeCalidad);
        SeccionesControlDeCalidadController.LOGGER.info("[GET - findBy_PK] : Obtener SeccionesControlDeCalidad por PK");
        return seccionesControlDeCalidad;
    }
        
    @RequestMapping(value = "/suggest", method = RequestMethod.GET)
    public @ResponseBody() List<SeccionesControlDeCalidad> getAll(
            @ModelAttribute() SeccionesControlDeCalidad filterSeccionesControlDeCalidad) {
        SeccionesControlDeCalidadController.LOGGER.info("[GET - find_ALL] : Obtener SeccionesControlDeCalidad");
        JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();
        jqGridRequestDto.setSidx(DBConstants.ORDEN);
        jqGridRequestDto.setSord("ASC");
        return this.seccionesControlDeCalidadService.findAll(filterSeccionesControlDeCalidad, jqGridRequestDto);
    }
    
    
    
//	UPDATE PESOS VALORACION AUDITORIA

    
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody SeccionesControlDeCalidad edit(@RequestBody SeccionesControlDeCalidad seccionesControlDeCalidad) {
    	SeccionesControlDeCalidad seccionesControlDeCalidadAux = this.seccionesControlDeCalidadService.update(seccionesControlDeCalidad);
        SeccionesControlDeCalidadController.LOGGER.info("[PUT] : SeccionesControlDeCalidad actualizado correctamente");
        return seccionesControlDeCalidadAux;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody SeccionesControlDeCalidad add(@RequestBody SeccionesControlDeCalidad seccionesControlDeCalidad) {
    	SeccionesControlDeCalidad seccionesControlDeCalidadAux = this.seccionesControlDeCalidadService.add(seccionesControlDeCalidad);
        SeccionesControlDeCalidadController.LOGGER.info("[POST] : SeccionesControlDeCalidad insertado correctamente");
        return seccionesControlDeCalidadAux;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody SeccionesControlDeCalidad remove(@PathVariable Long id) {
    	SeccionesControlDeCalidad seccionesControlDeCalidad = new SeccionesControlDeCalidad();
    	seccionesControlDeCalidad.setId(id);
        this.seccionesControlDeCalidadService.remove(seccionesControlDeCalidad);
        SeccionesControlDeCalidadController.LOGGER.info("[DELETE] : SeccionesControlDeCalidad borrado correctamente");
        return seccionesControlDeCalidad;
    }
    
     /*
     * METODOS COMPONENTE RUP_TABLE
     * 
     */
    
    @RequestMapping(value = "/maint", method = RequestMethod.GET)
    public String getFormEdit(Model model) {
        SeccionesControlDeCalidadController.LOGGER.info("[GET - View] : seccionesControlDeCalidad");
        return "seccionescontroldecalidad";
    }
    
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public @ResponseBody JQGridResponseDto<SeccionesControlDeCalidad> filter(
            @RequestJsonBody(param = "filter") SeccionesControlDeCalidad filterSeccionesControlDeCalidad,
            @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
    	SeccionesControlDeCalidadController.LOGGER.info("[POST - filter] : Obtener SeccionesControlDeCalidad");
        return this.seccionesControlDeCalidadService.filter(filterSeccionesControlDeCalidad, jqGridRequestDto, false);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody List<TableRowDto<SeccionesControlDeCalidad>> search(
            @RequestJsonBody(param = "filter") SeccionesControlDeCalidad filterSeccionesControlDeCalidad,
            @RequestJsonBody(param = "search") SeccionesControlDeCalidad searchSeccionesControlDeCalidad,
            @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
        SeccionesControlDeCalidadController.LOGGER.info("[POST - search] : Buscar SeccionesControlDeCalidad");
        return this.seccionesControlDeCalidadService.search(filterSeccionesControlDeCalidad, searchSeccionesControlDeCalidad,
                jqGridRequestDto, false);
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<String> removeMultiple(
            @RequestJsonBody(param = "filter") SeccionesControlDeCalidad filterSeccionesControlDeCalidad,
            @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
        SeccionesControlDeCalidadController.LOGGER
                .info("[POST - search] : [POST - removeMultiple] : Eliminar multiples SeccionesControlDeCalidad");
        this.seccionesControlDeCalidadService.removeMultiple(filterSeccionesControlDeCalidad, jqGridRequestDto, false);
        SeccionesControlDeCalidadController.LOGGER.info("All entities correctly deleted!");

        return jqGridRequestDto.getMultiselection().getSelectedIds();
    }
    
    

	@RequestMapping(value = "/pdfReport", method = RequestMethod.POST)
	public void pdfTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() SeccionesControlDeCalidad filterSeccionesControlDeCalidad, Model model) {
		SeccionesControlDeCalidadController.LOGGER.info("[POST - subm]: pdfTabla ========================================");

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<SeccionesControlDeCalidad> respuesta = this.seccionesControlDeCalidadService.filter(filterSeccionesControlDeCalidad,
				jqGridRequestDtoReport, false);
		Locale locale = LocaleContextHolder.getLocale();
		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		this.aa79bPdfReportService.generarPdf("defaultPDF", response, criterios, columns, respuesta.getRows(),
				LITERAL_LABEL, locale, label);
	}

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto, @ModelAttribute() SeccionesControlDeCalidad filterSeccionesControlDeCalidad,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		SeccionesControlDeCalidadController.LOGGER.info("[POST - subm]: xlsxTabla ========================================");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<SeccionesControlDeCalidad> respuesta = this.seccionesControlDeCalidadService.filter(filterSeccionesControlDeCalidad,
				jqGridRequestDtoReport, false);

		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		this.aa79bExcelReportService.generarExcel("printExcel", response, criterios, columns, respuesta.getRows(),
				locale, LITERAL_LABEL, label);
	}

}
