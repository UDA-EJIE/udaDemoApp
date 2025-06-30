package com.ejie.aa79b.model.excel.content;

import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.model.GruposTrabajo;
import com.ejie.aa79b.model.excel.ExcelContentMetodos;
import com.ejie.aa79b.utils.Utils;

/**
 * The type ExcelInformeResponsable.
 *
 */
public class ExcelInformeResponsable extends ExcelContentMetodos {

    private ReloadableResourceBundleMessageSource msg;

    private List<GruposTrabajo> listaResultados;

    public void setListaResultados(List<GruposTrabajo> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public List<GruposTrabajo> getListaResultados() {
        return this.listaResultados;
    }

    /**
     * Method to set the msg.
     *
     * @param msg
     *            the msg to set
     */
    @Override
	public void setMsg(ReloadableResourceBundleMessageSource msg) {
        this.msg = msg;
    }

    /**
     * Method to get the msg.
     *
     * @return the msg
     */
    @Override
	public ReloadableResourceBundleMessageSource getMsg() {
        return this.msg;
    }

    @Override()
    public void procesarContenido(XSSFWorkbook libro, XSSFSheet hoja) {
        Locale locale = new Locale("eu");
        int posX = 0;
        int posY = 0;

        if (this.isCeldaActiva()) {
            posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + 2;
        }
        int x = posX;
        int y = posY;

        final XSSFFont negrita = libro.createFont();
        negrita.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

        final XSSFCellStyle celdaNegrita = libro.createCellStyle();
        celdaNegrita.setFont(negrita);

        final XSSFCellStyle celdaTabla = libro.createCellStyle();
        celdaTabla.setWrapText(true);

        final XSSFCellStyle celdaCabeceraResumen = libro.createCellStyle();
        this.centrar(celdaCabeceraResumen);
        celdaCabeceraResumen.setFont(negrita);
        celdaCabeceraResumen.setWrapText(true);

        GruposTrabajo gruposTrabajo = this.listaResultados.get(0);

        Integer col = x;
        this.crearCelda(hoja, col++, y, 1, 1, this.msg.getMessage("label.responsable", null, locale),
                celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, 1, 1, gruposTrabajo.getResponsable().getNombreCompleto(), celdaCabeceraResumen);
        y += 2;
        col = x;
        this.crearCelda(hoja, col++, y, "label.grupoTrabajo", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, "label.estado", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, "label.tipoExpediente", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, "label.bopv", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, "label.previstoBopv", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, 5, "label.entidadesSolicitantes", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, 5, "label.traductoresInterpretes", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, "label.fechaAlta", locale, celdaCabeceraResumen);
        this.crearCelda(hoja, col++, y, "label.fechaBaja", locale, celdaCabeceraResumen);
        y += 2;

        for (GruposTrabajo grupo : this.listaResultados) {
            col = x;
            this.crearCelda(hoja, col++, y, grupo.getDescEu(), celdaTabla);
            this.crearCelda(hoja, col++, y, grupo.getEstadoDesc(), celdaTabla);
            this.crearCelda(hoja, col++, y, grupo.getTipoExpedienteDesc(), celdaTabla);
            this.crearCelda(hoja, col++, y, grupo.getBopvDesc(), celdaTabla);
            this.crearCelda(hoja, col++, y, grupo.getPrevistoBopvDesc(), celdaTabla);
            this.crearCelda(hoja, col, y, Utils.concatenarListaBean(grupo.getEntidades(), "descEu", "\n"), celdaTabla);
            col += 5;
            this.crearCelda(hoja, col++, y, Utils.concatenarListaBean(grupo.getTrabajadores(), "nombreCompleto", "\n"),
                    celdaTabla);
            col += 5;
            this.crearCelda(hoja, col++, y, grupo.getFechaAlta(), celdaTabla);
            this.crearCelda(hoja, col++, y, grupo.getFechaBaja(), celdaTabla).setAsActiveCell();
            y++;
        }
    }

    @Override()
    public void procesarContenidoTrados(XSSFWorkbook libro, XSSFSheet hoja) {
    	Locale locale = new Locale("eu");
    	int posX = 0;
    	int posY = 0;

    	if (this.isCeldaActiva()) {
    		posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + 2;
    	}
    	int x = posX;
    	int y = posY;

    	final XSSFFont negrita = libro.createFont();
    	negrita.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

    	final XSSFCellStyle celdaNegrita = libro.createCellStyle();
    	celdaNegrita.setFont(negrita);

    	final XSSFCellStyle celdaTabla = libro.createCellStyle();
    	celdaTabla.setWrapText(true);

    	final XSSFCellStyle celdaCabeceraResumen = libro.createCellStyle();
    	this.centrar(celdaCabeceraResumen);
    	celdaCabeceraResumen.setFont(negrita);
    	celdaCabeceraResumen.setWrapText(true);

    	GruposTrabajo gruposTrabajo = this.listaResultados.get(0);

    	Integer col = x;
    	this.crearCelda(hoja, col++, y, 1, 1, this.msg.getMessage("label.responsable", null, locale),
    			celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, 1, 1, gruposTrabajo.getResponsable().getNombreCompleto(), celdaCabeceraResumen);
    	y += 2;
    	col = x;
    	this.crearCelda(hoja, col++, y, "label.grupoTrabajo", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, "label.estado", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, "label.tipoExpediente", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, "label.bopv", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, "label.previstoBopv", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, 5, "label.entidadesSolicitantes", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, 5, "label.traductoresInterpretes", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, "label.fechaAlta", locale, celdaCabeceraResumen);
    	this.crearCelda(hoja, col++, y, "label.fechaBaja", locale, celdaCabeceraResumen);
    	y += 2;

    	for (GruposTrabajo grupo : this.listaResultados) {
    		col = x;
    		this.crearCelda(hoja, col++, y, grupo.getDescEu(), celdaTabla);
    		this.crearCelda(hoja, col++, y, grupo.getEstadoDesc(), celdaTabla);
    		this.crearCelda(hoja, col++, y, grupo.getTipoExpedienteDesc(), celdaTabla);
    		this.crearCelda(hoja, col++, y, grupo.getBopvDesc(), celdaTabla);
    		this.crearCelda(hoja, col++, y, grupo.getPrevistoBopvDesc(), celdaTabla);
    		this.crearCelda(hoja, col, y, Utils.concatenarListaBean(grupo.getEntidades(), "descEu", "\n"), celdaTabla);
    		col += 5;
    		this.crearCelda(hoja, col++, y, Utils.concatenarListaBean(grupo.getTrabajadores(), "nombreCompleto", "\n"),
    				celdaTabla);
    		col += 5;
    		this.crearCelda(hoja, col++, y, grupo.getFechaAlta(), celdaTabla);
    		this.crearCelda(hoja, col++, y, grupo.getFechaBaja(), celdaTabla).setAsActiveCell();
    		y++;
    	}
    }

}
