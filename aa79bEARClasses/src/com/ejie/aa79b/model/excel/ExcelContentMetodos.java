package com.ejie.aa79b.model.excel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author dlopez2
 * 
 */
public abstract class ExcelContentMetodos extends ExcelContent {
    private int posicionX = 0;
    private int posicionY = 0;
    private boolean celdaActiva = true;
    private ReloadableResourceBundleMessageSource msg;

    /**
     * Method to set the msg.
     * 
     * @param msg
     *            the msg to set
     */
    public void setMsg(ReloadableResourceBundleMessageSource msg) {
        this.msg = msg;
    }

    /**
     * Method to get the msg.
     * 
     * @return the msg
     */
    public ReloadableResourceBundleMessageSource getMsg() {
        return this.msg;
    }

    /**
     * Method to get the posicionX.
     * 
     * @return the posicionX
     */
    public int getPosicionX() {
        return this.posicionX;
    }

    /**
     * Method to set the posicionX.
     * 
     * @param posicionX
     *            the posicionX to set
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    /**
     * Method to get the posicionY.
     * 
     * @return the posicionY
     */
    public int getPosicionY() {
        return this.posicionY;
    }

    /**
     * Method to set the posicionY.
     * 
     * @param posicionY
     *            the posicionY to set
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    /**
     * Method to get the celdaActiva.
     * 
     * @return the celdaActiva
     */
    public boolean isCeldaActiva() {
        return this.celdaActiva;
    }

    /**
     * Method to set the celdaActiva.
     * 
     * @param celdaActiva
     *            the celdaActiva to set
     */
    public void setCeldaActiva(boolean celdaActiva) {
        this.celdaActiva = celdaActiva;
    }

    /**
     * Crea una celda con el valor indicado.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param valor
     *            Object
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, Object valor) {
        return this.crearCelda(hoja, x, y, valor, null);
    }

    /**
     * Crea una celda con el valor indicado.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param valor
     *            Object
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, Object valor, XSSFCellStyle estilo) {
        XSSFRow fila = hoja.getRow(this.posicionY + y);
        if (fila == null) {
            fila = hoja.createRow(this.posicionY + y);
        }
        XSSFCell celda = fila.getCell(this.posicionX + x);
        if (celda == null) {
            celda = fila.createCell(this.posicionX + x);
        }
        if (valor != null) {
            if (valor instanceof Integer) {
                celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                celda.setCellValue((Integer) valor);
            } else if (valor instanceof Double) {
                celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                celda.setCellValue((Double) valor);
            } else if (valor instanceof BigDecimal) {
                celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                celda.setCellValue(((BigDecimal) valor).doubleValue());
            } else if (valor instanceof Date) {
                celda.setCellType(XSSFCell.CELL_TYPE_STRING);
                celda.setCellValue((Date) valor);
            } else {
                celda.setCellType(XSSFCell.CELL_TYPE_STRING);
                celda.setCellValue(valor.toString());
            }
        } else {
            celda.setCellType(XSSFCell.CELL_TYPE_BLANK);
        }
        if (estilo != null) {
            celda.setCellStyle(estilo);
        }
        return celda;
    }

    /**
     * Crea una celda con las dimensiones y el valor indicado.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param ancho
     *            int
     * @param alto
     *            int
     * @param valor
     *            Object
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, int ancho, Object valor, XSSFCellStyle estilo) {
        return this.crearCelda(hoja, x, y, ancho, 1, valor, estilo);
    }

    /**
     * Crea una celda con las dimensiones y el valor indicado.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param ancho
     *            int
     * @param alto
     *            int
     * @param valor
     *            Object
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, int ancho, int alto, Object valor,
            XSSFCellStyle estilo) {
        hoja.addMergedRegion(new CellRangeAddress(this.posicionY + y, this.posicionY + y + alto - 1, this.posicionX + x,
                this.posicionX + x + ancho - 1));
        for (int y2 = y; y2 < y + alto; y2++) {
            for (int x2 = x; x2 < x + ancho; x2++) {
                this.crearCelda(hoja, x2, y2, null, estilo);
            }
        }
        return this.crearCelda(hoja, x, y, valor, estilo);
    }

    /**
     * Crea una celda de las dimensiones indicadas con el literal sacado de las
     * propiedades.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param ancho
     *            int
     * @param literal
     *            String
     * @param locale
     *            Locale
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, int ancho, String literal, Locale locale,
            XSSFCellStyle estilo) {
        return this.crearCelda(hoja, x, y, ancho, 1, this.msg.getMessage(literal, null, locale), estilo);
    }

    /**
     * Crea una celda con el literal sacado de las propiedades.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param literal
     *            String
     * @param locale
     *            Locale
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, String literal, Locale locale) {
        return this.crearCelda(hoja, x, y, this.msg.getMessage(literal, null, locale));
    }

    /**
     * Crea una celda con el literal sacado de las propiedades.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param literal
     *            String
     * @param locale
     *            Locale
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, String literal, Locale locale, XSSFCellStyle estilo) {
        return this.crearCelda(hoja, x, y, this.msg.getMessage(literal, null, locale), estilo);
    }

    /**
     * Crea una celda con el literal sacado de las propiedades procesando los
     * parámetros.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param literal
     *            String
     * @param param
     *            Object[]
     * @param locale
     *            Locale
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, String literal, Object[] param, Locale locale) {
        return this.crearCelda(hoja, x, y, this.msg.getMessage(literal, param, locale));
    }

    /**
     * Crea una celda con el literal sacado de las propiedades procesando los
     * parámetros.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param literal
     *            String
     * @param param
     *            Object[]
     * @param locale
     *            Locale
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCelda(XSSFSheet hoja, int x, int y, String literal, Object[] param, Locale locale,
            XSSFCellStyle estilo) {
        return this.crearCelda(hoja, x, y, this.msg.getMessage(literal, param, locale), estilo);
    }

    /**
     * Crea una celda con el literal en mayúsculas sacado de las propiedades.
     * 
     * @param hoja
     *            XSSFSheet
     * @param x
     *            int
     * @param y
     *            int
     * @param literal
     *            String
     * @param locale
     *            Locale
     * @param estilo
     *            XSSFCellStyle
     * @return XSSFCell
     */
    protected XSSFCell crearCeldaM(XSSFSheet hoja, int x, int y, String literal, Locale locale, XSSFCellStyle estilo) {
        return this.crearCelda(hoja, x, y, this.msg.getMessage(literal, null, locale).toUpperCase(), estilo);
    }

    /**
     * The method aplicarBorde.
     * 
     * @param estilo
     *            XSSFCellStyle
     * @param borde
     *            short
     */
    protected void aplicarBorde(XSSFCellStyle estilo, short borde) {
        estilo.setBorderBottom(borde);
        estilo.setBorderTop(borde);
        estilo.setBorderLeft(borde);
        estilo.setBorderRight(borde);
    }

    /**
     * The method centrar.
     * 
     * @param estilo
     *            XSSFCellStyle
     */
    protected void centrar(XSSFCellStyle estilo) {
        estilo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        estilo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    }

    /**
     * The method centrar.
     * 
     * @param estilo
     *            XSSFCellStyle
     */
    protected void alinearDcha(XSSFCellStyle estilo) {
        estilo.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        estilo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    }

    /**
     * The method aplicarColorFondo.
     * 
     * @param estilo
     *            XSSFCellStyle
     * @param color
     *            short
     */
    protected void aplicarColorFondo(XSSFCellStyle estilo, short color) {
        estilo.setFillForegroundColor(color);
        estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    }

    /**
     * The method crearEstiloFondo.
     * 
     * @param libro
     *            XSSFWorkbook
     * @param borde
     *            short
     * @param fondo
     *            short
     * @return XSSFCellStyle
     */
    protected XSSFCellStyle crearEstiloFondo(XSSFWorkbook libro, short borde, short fondo) {
        final XSSFCellStyle celdaFichaA = libro.createCellStyle();
        celdaFichaA.setFillForegroundColor(fondo);
        celdaFichaA.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        celdaFichaA.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        celdaFichaA.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        celdaFichaA.setBorderBottom(borde);
        celdaFichaA.setBorderTop(borde);
        celdaFichaA.setBorderLeft(borde);
        celdaFichaA.setBorderRight(borde);
        return celdaFichaA;
    }

    /**
     * The method crearEstilo.
     * 
     * @param libro
     *            XSSFWorkbook
     * @param borde
     *            short
     * @return XSSFCellStyle
     */
    protected XSSFCellStyle crearEstilo(XSSFWorkbook libro, short borde) {
        final XSSFCellStyle celdaCentrada = libro.createCellStyle();
        celdaCentrada.setBorderBottom(borde);
        celdaCentrada.setBorderTop(borde);
        celdaCentrada.setBorderLeft(borde);
        celdaCentrada.setBorderRight(borde);
        return celdaCentrada;
    }

    /**
     * The method crearEstiloCentrado.
     * 
     * @param libro
     *            XSSFWorkbook
     * @param borde
     *            short
     * @return XSSFCellStyle
     */
    protected XSSFCellStyle crearEstiloCentrado(XSSFWorkbook libro, short borde) {
        final XSSFCellStyle celdaCentrada = libro.createCellStyle();
        celdaCentrada.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        celdaCentrada.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        celdaCentrada.setBorderBottom(borde);
        celdaCentrada.setBorderTop(borde);
        celdaCentrada.setBorderLeft(borde);
        celdaCentrada.setBorderRight(borde);
        return celdaCentrada;
    }

    /**
     * The method crearBorde.
     * 
     * @param libro
     *            XSSFWorkbook
     * @param hoja
     *            XSSFSheet
     * @param x1
     *            int
     * @param y1
     *            int
     * @param x2
     *            int
     * @param y2
     *            int
     * @param border
     *            short
     */
    protected void crearBorde(XSSFWorkbook libro, XSSFSheet hoja, int x1, int y1, int x2, int y2, short border) {
        RegionUtil.setBorderTop(border, new CellRangeAddress(y1, y1, x1, x2), hoja, libro);
        RegionUtil.setBorderLeft(border, new CellRangeAddress(y1, y2, x1, x1), hoja, libro);
        RegionUtil.setBorderBottom(border, new CellRangeAddress(y2, y2, x1, x2), hoja, libro);
        RegionUtil.setBorderRight(border, new CellRangeAddress(y1, y2, x2, x2), hoja, libro);
    }

    /**
     * @param libro
     *            XSSFWorkbook
     * @param hoja
     *            XSSFSheet
     */
    @Override
    public abstract void procesarContenido(XSSFWorkbook libro, XSSFSheet hoja);
}
