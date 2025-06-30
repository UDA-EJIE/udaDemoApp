package com.ejie.aa79b.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.InformesDao;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.Informe;
import com.ejie.aa79b.model.InformeInterpretacion;
import com.ejie.aa79b.model.InformeTradRev;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.excel.ExcelEstilo;
import com.ejie.aa79b.model.excel.ExcelHoja;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.model.excel.content.ExcelTable;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.utils.Utils;

@Service(value = "informesService")
public class InformesServiceImpl implements InformesService {

	@Autowired()
	private InformesDao informesDao;
	@Autowired()
	private ExcelReportService excelReportService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	@Override
	public Fichero exportarResultadoCurso(Informe bean, Locale locale) throws Exception {
		List<String> columnas = this.obtenerColumnasPlantilla(bean, locale);

		final ExcelModel excelModel = new ExcelModel();

		Integer posX = Constants.CERO;
		final ExcelTable tabla = new ExcelTable(posX, 0, false);
		tabla.setEstiloCabecera(ExcelEstilo.HEADER);
		for (String columna : columnas) {
			tabla.addlabel(columna, columna);
		}

		// Pasar las columnas a las celdas

		posX = Constants.CERO;
		String literal = "label.informeTradRev.file";

		if (TipoExpedienteGrupoEnum.INTERPRETACION.getCode().equals(bean.getTipoInforme())) {
			literal = "label.informeInterpretacion.file";

			List<InformeInterpretacion> lista = this.informesDao.informeInterpretacion(bean);

			for (InformeInterpretacion informe : lista) {
				ArrayList<String> array = this.newArrayListString();
				ArrayList<Boolean> arrayIsNumeric = new ArrayList<Boolean>();
				ArrayList<Boolean> arrayIsDate = new ArrayList<Boolean>();
				array.add(informe.getAnyo().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getNumExp().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(Utils.getAnyoNumExpConcatenado(informe.getAnyo(), informe.getNumExp()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTipoExpediente());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTitulo());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getIndPublicarBopvIdioma(LocaleContextHolder.getLocale().getLanguage()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getSolicitante());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTipoSolicitante());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getContacto());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getContactoDni());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTarea());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getCodAsignadoA());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getAsignadoA());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(
						DateUtils.obtFechaHoraFormateada(informe.getFechaSolicitud(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(DateUtils.obtFechaHoraFormateada(informe.getFechaFin(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(DateUtils.obtFechaHoraFormateada(informe.getFechaIni(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(informe.getNumInterpretesFact());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getHorasFacturadas());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(
						DateUtils.obtFechaHoraFormateada(informe.getFechaPrevista(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(
						DateUtils.obtFechaHoraFormateada(informe.getFechaEjecTarea(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(informe.getTerminado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTipoActoDesc());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(GeneralUtils.format2Decimals(informe.getIngreso()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getIva());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(GeneralUtils.format2Decimals(informe.getImporteConIVA()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getCodTarea() != null ? informe.getCodTarea().toString() : "");
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getRechazado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getMotivoRechazo());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getAnulado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getMotivoAnulado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				tabla.addFilaCasera(posX++, array, arrayIsNumeric, arrayIsDate);
			}
		} else {
			List<InformeTradRev> lista = this.informesDao.informeTradRev(bean);

			for (InformeTradRev informe : lista) {
				ArrayList<String> array = this.newArrayListString();
				ArrayList<Boolean> arrayIsNumeric = new ArrayList<Boolean>();
				ArrayList<Boolean> arrayIsDate = new ArrayList<Boolean>();
				array.add(informe.getAnyo().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getNumExp().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(Utils.getAnyoNumExpConcatenado(informe.getAnyo(), informe.getNumExp()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTipoExpediente());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTitulo());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getIndPublicarBopvIdioma(LocaleContextHolder.getLocale().getLanguage()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getSolicitante());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTipoSolicitante());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getContacto());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getContactoDni());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTarea());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getCodAsignadoA());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getAsignadoA());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(
						DateUtils.obtFechaHoraFormateada(informe.getFechaSolicitud(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(DateUtils.obtFechaHoraFormateada(informe.getFechaFin(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(informe.getNumPalabrasSolic().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getNumPalabrasIzo().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getSede());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
//				array.add(informe.getTradosExp().getNumTotalPalConTramosPerfectMatchExcel());
//				arrayIsNumeric.add(false);
//				arrayIsDate.add(false);
				array.add(informe.getTradosExp().getNumPalConcor084090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTradosExp().getNumPalConcor8594090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTradosExp().getNumPalConcor9599090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTradosExp().getNumPalConcor100090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
//				array.add(informe.getTradosTarea().getNumTotalPalConTramosPerfectMatchExcel());
//				arrayIsNumeric.add(false);
//				arrayIsDate.add(false);
				array.add(informe.getTradosTarea().getNumPalConcor084090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTradosTarea().getNumPalConcor8594090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTradosTarea().getNumPalConcor9599090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getTradosTarea().getNumPalConcor100090().toString());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(GeneralUtils.format4Decimals(informe.getTarifaPalabras()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add("0");
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(
						DateUtils.obtFechaHoraFormateada(informe.getFechaPrevista(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(
						DateUtils.obtFechaHoraFormateada(informe.getFechaEjecTarea(), LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(DateUtils.obtFechaHoraFormateada(informe.getFechaEntregaReal(),
						LocaleContextHolder.getLocale()));
				arrayIsNumeric.add(false);
				arrayIsDate.add(true);
				array.add(informe.getTipoTraduccion());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTipoTraduccion0());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getTerminado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getPenalizacionCalidad());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(GeneralUtils.format2Decimals(informe.getImportePenalizacionCalidad()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(GeneralUtils.format2Decimals(informe.getIngreso()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getIva());
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(GeneralUtils.format2Decimals(informe.getImporteConIVA()));
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getCodTarea() != null ? informe.getCodTarea().toString() : "");
				arrayIsNumeric.add(true);
				arrayIsDate.add(false);
				array.add(informe.getRechazado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getMotivoRechazo());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getAnulado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				array.add(informe.getMotivoAnulado());
				arrayIsNumeric.add(false);
				arrayIsDate.add(false);
				tabla.addFilaCasera(posX++, array, arrayIsNumeric, arrayIsDate);
			}
		}

		final String titulo = this.appMessageSource.getMessage(literal, new Object[] {}, locale);
		final ExcelHoja excelHoja = new ExcelHoja(titulo, 0);
		excelModel.setNombre(titulo);

		excelHoja.addContenido(tabla);
		excelModel.getListaHojas().add(excelHoja);

		byte[] excel = this.excelReportService.generarExcelTrados("printExcel", excelModel);
		Fichero fichero = new Fichero();
		fichero.setBytes(excel);
		fichero.setNombre(excelModel.getNombre());
		fichero.setContentType(Constants.CONTENT_TYPE_EXCEL);
		return fichero;
	}

	public List<String> obtenerColumnasPlantilla(Informe bean, Locale locale) {
		List<String> listaColumnas = new ArrayList<String>();
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.anyo", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.expediente", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("aa79b.tabla.numExpediente", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.tipoExpediente", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.titulo", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.ehaa", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.solicitante", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.tipoSolicitante", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.nombreSolicitante", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.dniSolicitante", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.tarea", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.numIdentifiacion", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.personaAsignada", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaSolicitud", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaEntrega", null, locale));

		if (TipoExpedienteGrupoEnum.INTERPRETACION.getCode().equals(bean.getTipoInforme())) {
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaInicio", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.numInterpretesFact", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.horasFacturadas", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaPrevista", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaEjecTarea", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.terminado", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.tipoActo", null, locale));

		} else {
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.numPalSolicitadas", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.numPalIzo", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.sede", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.tradosExpediente", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor084", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor8594", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor9599", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor100", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.tradosTarea", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor084", null, locale) + " ");
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor8594", null, locale) + " ");
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor9599", null, locale) + " ");
			listaColumnas.add(this.appMessageSource.getMessage("comun.tramosConcor100", null, locale) + " ");
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.tarifaPorPal", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.horas", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaPrevista", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaEjecTarea", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.fechaEntregaReal", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.tipoTraduccion", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.tipoTraduccion0", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.terminado", null, locale));
			listaColumnas.add(this.appMessageSource.getMessage("label.informe.penalizacionCalidad", null, locale));
			listaColumnas
					.add(this.appMessageSource.getMessage("label.informe.importePenalizacionCalidad", null, locale));

		}

		listaColumnas.add(this.appMessageSource.getMessage("label.informe.ingresos", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.iva", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.importeIVA", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.codTarea", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.rechazado", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.rechazadoMotivo", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.anulado", null, locale));
		listaColumnas.add(this.appMessageSource.getMessage("label.informe.anuladoMotivo", null, locale));
		return listaColumnas;
	}

	private ArrayList<String> newArrayListString() {
		return new ArrayList<String>();
	}

}
