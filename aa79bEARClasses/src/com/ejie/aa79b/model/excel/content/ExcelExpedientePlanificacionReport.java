package com.ejie.aa79b.model.excel.content;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.model.ExpedientePlanificacionReport;
import com.ejie.aa79b.model.excel.ExcelContentMetodos;
import com.ejie.aa79b.model.excel.ExcelEstilo;

public class ExcelExpedientePlanificacionReport extends ExcelContentMetodos {

	private List<Criterio> criterios = new ArrayList<Criterio>();
	private List<Columna> columnas = new ArrayList<Columna>();
	private List<ExpedientePlanificacionReport> datos;
	private Locale locale;
	private Object criterio;

	public void setDatos(List<ExpedientePlanificacionReport> datos) {
		this.datos = datos;
	}

	public List<ExpedientePlanificacionReport> getDatos() {
		return this.datos;
	}

	public void addCriterio(String literal, String propiedad, Integer anchoLabel, Integer anchoValor) {
		this.addCriterio(literal, propiedad, anchoLabel, anchoValor, false);
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void addCriterio(String literal, String propiedad, Integer anchoLabel, Integer anchoValor,
			Boolean saltoLinea) {
		Criterio criterioAux = new Criterio();
		criterioAux.setLiteral(literal);
		criterioAux.setPropiedad(propiedad);
		criterioAux.setAnchoLabel(anchoLabel);
		criterioAux.setAnchoValor(anchoValor);
		criterioAux.setSaltoLinea(saltoLinea);
		this.criterios.add(criterioAux);
	}

	@Override
	public void procesarContenido(XSSFWorkbook libro, XSSFSheet hoja) {
		int posX = Constants.CERO;
		int posY = Constants.CERO;

		if (this.locale == null) {
			this.locale = LocaleContextHolder.getLocale();
		}

		if (this.isCeldaActiva()) {
			posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + Constants.DOS;
		}
		int x = posX;
		int y = posY;

		final XSSFFont negrita = libro.createFont();
		negrita.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

		// estilo de titulo de titulos de campo de expediente en negrita y con
		// background en gris
		final XSSFCellStyle celdaNegrita = libro.createCellStyle();
		celdaNegrita.setFont(negrita);
		XSSFColor myColor = new XSSFColor(new Color(Constants.DOSCIENTOSCUARENTAYDOS, Constants.DOSCIENTOSCUARENTAYDOS,
				Constants.DOSCIENTOSCUARENTAYDOS));
		celdaNegrita.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		celdaNegrita.setFillForegroundColor(myColor);

		// estilo de celda de datos de expediente con background en gris
		final XSSFCellStyle celdaBackground = libro.createCellStyle();
		celdaBackground.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		celdaBackground.setFillForegroundColor(myColor);

		// estilo de celda estandar
		XSSFCellStyle celdaTabla = ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT, null);

		// estilo de celda ajustada
		final XSSFCellStyle celdaTablaAjustada = ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT, null);
		celdaTablaAjustada.setWrapText(true);

		final XSSFCellStyle cabecera = ExcelEstilo.getEstilo(libro, ExcelEstilo.HEADER, null);

		XSSFCell celda = null;
		XSSFCell celdaSubTabla = null;

		// expediente
		Integer col = x;
		Integer countSaltoDeLinea = Constants.CERO;
		if (!this.criterios.isEmpty()) {
			for (Criterio crit : this.criterios) {
				// titulo de campo de expediente
				celda = this.crearCelda(hoja, col, y, crit.getAnchoLabel(), crit.getLiteral(), this.locale,
						celdaNegrita);
				// dato de campo de expediente
				celda = this.crearCelda(hoja, col, y + Constants.UNO, crit.getAnchoValor(),
						Reports.retrieveObjectValue(this.criterio, crit.getPropiedad()), celdaBackground);

				// cada 7 datos de expediente hacemos un salto de linea
				col += crit.getAnchoLabel();
				if (countSaltoDeLinea == Constants.SEIS) {
					y = y + Constants.DOS;
					col = x;
					countSaltoDeLinea = Constants.CERO;
				} else {
					countSaltoDeLinea++;
				}
			}
			y += Constants.UNO;
		}
		// para que las tareas vayan en la siguiente fila de los datos del
		// expediente
		y--;
		y--;
		// tareas
		// titulos de campos de tareas
		col = x;
		for (Columna columna : this.columnas) {
			celda = this.crearCelda(hoja, col, y, columna.getAncho(), columna.getLiteral(), this.locale, cabecera);
			col += columna.getAncho();
		}
		y++;
		// datos de campos de tareas - se van anyadiendo las filas
		if (this.datos != null) {
			for (Object grupo : this.datos) {
				col = x;
				Integer yMax = Constants.UNO;
				for (Columna columna : this.columnas) {
					if (StringUtils.isNotBlank(columna.getLista())) {
						Integer yAux = Constants.CERO;
						List<?> lista = (List<?>) Reports.retrieveObjectValue(grupo, columna.getLista());
						if (lista != null) {
							for (Object registro : lista) {
								celda = this.crearCelda(hoja, col, y + yAux, columna.getAncho(),
										Reports.retrieveObjectValue(registro, columna.getPropiedad()),
										columna.isAjustar() ? celdaTablaAjustada : celdaTabla);
								yAux++;
							}
							if (yAux > yMax) {
								yMax = yAux;
								celdaSubTabla = celda;
							}
						} else {
							celda = this.crearCelda(hoja, col, y + yAux, columna.getAncho(), Constants.EMPTY,
									columna.isAjustar() ? celdaTablaAjustada : celdaTabla);
							yAux++;
						}
					} else {
						celda = this.crearCelda(hoja, col, y, columna.getAncho(),
								Reports.retrieveObjectValue(grupo, columna.getPropiedad()),
								columna.isAjustar() ? celdaTablaAjustada : celdaTabla);
					}
					col += columna.getAncho();
				}
				y += yMax;
			}
		}
		if (celda != null) {
			celda.setAsActiveCell();
			if (celdaSubTabla != null && celdaSubTabla.getRowIndex() > celda.getRowIndex()) {
				celdaSubTabla.setAsActiveCell();
			}
		}

	}


	@Override
	public void procesarContenidoTrados(XSSFWorkbook libro, XSSFSheet hoja) {
		int posX = Constants.CERO;
		int posY = Constants.CERO;

		if (this.locale == null) {
			this.locale = LocaleContextHolder.getLocale();
		}

		if (this.isCeldaActiva()) {
			posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + Constants.DOS;
		}
		int x = posX;
		int y = posY;

		final XSSFFont negrita = libro.createFont();
		negrita.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

		// estilo de titulo de titulos de campo de expediente en negrita y con
		// background en gris
		final XSSFCellStyle celdaNegrita = libro.createCellStyle();
		celdaNegrita.setFont(negrita);
		XSSFColor myColor = new XSSFColor(new Color(Constants.DOSCIENTOSCUARENTAYDOS, Constants.DOSCIENTOSCUARENTAYDOS,
				Constants.DOSCIENTOSCUARENTAYDOS));
		celdaNegrita.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		celdaNegrita.setFillForegroundColor(myColor);

		// estilo de celda de datos de expediente con background en gris
		final XSSFCellStyle celdaBackground = libro.createCellStyle();
		celdaBackground.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		celdaBackground.setFillForegroundColor(myColor);

		// estilo de celda estandar
		XSSFCellStyle celdaTabla = ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT, null);

		// estilo de celda ajustada
		final XSSFCellStyle celdaTablaAjustada = ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT, null);
		celdaTablaAjustada.setWrapText(true);

		final XSSFCellStyle cabecera = ExcelEstilo.getEstilo(libro, ExcelEstilo.HEADER, null);

		XSSFCell celda = null;
		XSSFCell celdaSubTabla = null;

		// expediente
		Integer col = x;
		Integer countSaltoDeLinea = Constants.CERO;
		if (!this.criterios.isEmpty()) {
			for (Criterio crit : this.criterios) {
				// titulo de campo de expediente
				celda = this.crearCelda(hoja, col, y, crit.getAnchoLabel(), crit.getLiteral(), this.locale,
						celdaNegrita);
				// dato de campo de expediente
				celda = this.crearCelda(hoja, col, y + Constants.UNO, crit.getAnchoValor(),
						Reports.retrieveObjectValue(this.criterio, crit.getPropiedad()), celdaBackground);

				// cada 7 datos de expediente hacemos un salto de linea
				col += crit.getAnchoLabel();
				if (countSaltoDeLinea == Constants.SEIS) {
					y = y + Constants.DOS;
					col = x;
					countSaltoDeLinea = Constants.CERO;
				} else {
					countSaltoDeLinea++;
				}
			}
			y += Constants.UNO;
		}
		// para que las tareas vayan en la siguiente fila de los datos del
		// expediente
		y--;
		y--;
		// tareas
		// titulos de campos de tareas
		col = x;
		for (Columna columna : this.columnas) {
			celda = this.crearCelda(hoja, col, y, columna.getAncho(), columna.getLiteral(), this.locale, cabecera);
			col += columna.getAncho();
		}
		y++;
		// datos de campos de tareas - se van anyadiendo las filas
		if (this.datos != null) {
			for (Object grupo : this.datos) {
				col = x;
				Integer yMax = Constants.UNO;
				for (Columna columna : this.columnas) {
					if (StringUtils.isNotBlank(columna.getLista())) {
						Integer yAux = Constants.CERO;
						List<?> lista = (List<?>) Reports.retrieveObjectValue(grupo, columna.getLista());
						if (lista != null) {
							for (Object registro : lista) {
								celda = this.crearCelda(hoja, col, y + yAux, columna.getAncho(),
										Reports.retrieveObjectValue(registro, columna.getPropiedad()),
										columna.isAjustar() ? celdaTablaAjustada : celdaTabla);
								yAux++;
							}
							if (yAux > yMax) {
								yMax = yAux;
								celdaSubTabla = celda;
							}
						} else {
							celda = this.crearCelda(hoja, col, y + yAux, columna.getAncho(), Constants.EMPTY,
									columna.isAjustar() ? celdaTablaAjustada : celdaTabla);
							yAux++;
						}
					} else {
						celda = this.crearCelda(hoja, col, y, columna.getAncho(),
								Reports.retrieveObjectValue(grupo, columna.getPropiedad()),
								columna.isAjustar() ? celdaTablaAjustada : celdaTabla);
					}
					col += columna.getAncho();
				}
				y += yMax;
			}
		}
		if (celda != null) {
			celda.setAsActiveCell();
			if (celdaSubTabla != null && celdaSubTabla.getRowIndex() > celda.getRowIndex()) {
				celdaSubTabla.setAsActiveCell();
			}
		}

	}

	public Object getCriterio() {
		return this.criterio;
	}

	public void setCriterio(Object criterio) {
		this.criterio = criterio;
	}

	private class Columna {
		private String propiedad;
		private String literal;
		private Integer ancho;
		private Boolean ajustar = true;
		private String lista;

		public String getPropiedad() {
			return this.propiedad;
		}

		public void setPropiedad(String propiedad) {
			this.propiedad = propiedad;
		}

		public String getLiteral() {
			return this.literal;
		}

		public void setLiteral(String literal) {
			this.literal = literal;
		}

		public Integer getAncho() {
			return this.ancho;
		}

		public void setAncho(Integer ancho) {
			this.ancho = ancho;
		}

		public boolean isAjustar() {
			return this.ajustar;
		}

		public void setAjustar(boolean ajustar) {
			this.ajustar = ajustar;
		}

		public String getLista() {
			return this.lista;
		}

		public void setLista(String lista) {
			this.lista = lista;
		}
	}

	private class Criterio {
		private String propiedad;
		private String literal;
		private Integer anchoLabel;
		private Integer anchoValor;
		private Boolean saltoLinea;

		public String getPropiedad() {
			return this.propiedad;
		}

		public void setPropiedad(String propiedad) {
			this.propiedad = propiedad;
		}

		public String getLiteral() {
			return this.literal;
		}

		public void setLiteral(String literal) {
			this.literal = literal;
		}

		public Integer getAnchoLabel() {
			return this.anchoLabel;
		}

		public void setAnchoLabel(Integer anchoLabel) {
			this.anchoLabel = anchoLabel;
		}

		public Integer getAnchoValor() {
			return this.anchoValor;
		}

		public void setAnchoValor(Integer anchoValor) {
			this.anchoValor = anchoValor;
		}

		public Boolean getSaltoLinea() {
			return this.saltoLinea;
		}

		public void setSaltoLinea(Boolean saltoLinea) {
			this.saltoLinea = saltoLinea;
		}
	}

	public void addColumna(String literal, String lista, String propiedad, Integer ancho, Boolean ajustar) {
		Columna col = new Columna();
		col.setLiteral(literal);
		col.setPropiedad(propiedad);
		col.setAncho(ancho);
		col.setAjustar(ajustar);
		col.setLista(lista);
		this.columnas.add(col);
	}

}
