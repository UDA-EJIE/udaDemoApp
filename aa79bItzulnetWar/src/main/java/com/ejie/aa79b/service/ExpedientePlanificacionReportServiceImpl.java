package com.ejie.aa79b.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedientePlanificacionReportDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedientePlanificacionReport;
import com.ejie.aa79b.model.excel.ExcelCelda;
import com.ejie.aa79b.model.excel.ExcelEstilo;
import com.ejie.aa79b.model.excel.ExcelHoja;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.model.excel.content.ExcelCriterios;
import com.ejie.aa79b.model.excel.content.ExcelExpedientePlanificacionReport;
import com.ejie.aa79b.utils.ExcelUtils;
import com.ejie.x38.dto.JQGridRequestDto;

@Service(value = "expedientePlanificacionReportService")
public class ExpedientePlanificacionReportServiceImpl extends GenericoServiceImpl<ExpedientePlanificacionReport>
		implements ExpedientePlanificacionReportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedientePlanificacionReportServiceImpl.class);
	@Autowired()
	private ExpedientePlanificacionReportDao expedientePlanificacionReportDao;
	@Autowired()
	ExpedientePlanificacionService expedientePlanificacionService;
	@Autowired()
	ExpedienteDao expedienteDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String LABEL_TAREA_TIPOTAREA = "comun.tipoDeTarea";
	protected static final String LABEL_TAREA_RECURSOASIGNADO = "label.recursoAsignado";
	protected static final String LABEL_TAREA_FECHAHORAASIGNACION = "label.fechaHoraAsignacion";
	protected static final String LABEL_TAREA_ESTADOACEPTACION = "label.estadoAceptacion";
	protected static final String LABEL_TAREA_ESTADOEJECUCION = "label.estadoEjecucion";
	protected static final String LABEL_TAREA_HORASPREVISTAS = "label.horasPrevistas";
	protected static final String LABEL_TAREA_FECHAHORAFINALIZACION = "label.fechaHoraFin";
	protected static final String LABEL_TAREA_FECHAHORAENTREGA = "label.fechaHoraEntrega";
	protected static final String NAME_TAREA_TIPOTAREA = "tarea.tipoTarea.descEu015";
	protected static final String NAME_TAREA_RECURSOASIGNADO = "gestorExpediente.solicitante.nombreCompleto";
	protected static final String NAME_TAREA_FECHAHORAASIGNACION = "tarea.fechaHoraAsignacion";
	protected static final String NAME_TAREA_ESTADOACEPTACION = "tarea.estadoAsignadoDesc";
	protected static final String NAME_TAREA_ESTADOEJECUCION = "tarea.estadoEjecucionDesc";
	protected static final String NAME_TAREA_HORASPREVISTAS = "tarea.horasPrevistas";
	protected static final String NAME_TAREA_FECHAHORAFINALIZACION = "tarea.fechaHoraFin";
	protected static final String NAME_TAREA_FECHAHORAENTREGA = "fechaHoraFin";
	protected static final String LISTA_TAREAS = "listaTareas";
	protected static final String TITULO_EXPEDIENTE = "titulo.expediente";
	protected static final String IMAGEN = "imagen";
	protected static final String TITULO = "titulo";

	@SuppressWarnings("unchecked")
	@Override
	public ExcelModel getInformePlanificacion(ExpedientePlanificacion filter, String columns,
			JQGridRequestDto jqGridRequestDto) {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		final String titulo = this.msg.getMessage(TITULO_EXPEDIENTE, null, locale);
		final ExcelModel excelModel = new ExcelModel();
		excelModel.setNombre(titulo);
		final ExcelHoja excelHoja = new ExcelHoja(titulo, Constants.CERO);
		// creamos la cabecera del excel
		this.crearCabecera(titulo, locale, excelHoja);
		// obtenemos los expedientes
		final List<ExpedientePlanificacion> rdo = (List<ExpedientePlanificacion>) this.expedienteDao
				.busquedageneral(filter, jqGridRequestDto, false, false);
		// obtenemos los anyos y numExp de expedientes y los metemos en un
		// string en el formato adecuado para su busqueda de tareas
		// correspondientes
		StringBuilder idsExpedientes = new StringBuilder();
		// obtenemos las tareas
		List<ExpTareasResumen> listaTareas = new ArrayList<ExpTareasResumen>();
		if (rdo != null && !rdo.isEmpty()) {
			for (ExpedientePlanificacion expPlanif : rdo) {
				idsExpedientes
						.append(expPlanif.getAnyo() + Constants.GUION + expPlanif.getNumExp() + Constants.GUION_BAJO);
			}
			idsExpedientes.deleteCharAt(idsExpedientes.lastIndexOf(Constants.GUION_BAJO));
			listaTareas = (List<ExpTareasResumen>) expedienteDao.getTareasExpedientes(idsExpedientes.toString(), null,
					false, false);
		}

		final List<ExpedientePlanificacionReport> listaExpReport = new ArrayList<ExpedientePlanificacionReport>();

		// montamos la lista de objetos con expedientes y tareas
		for (ExpedientePlanificacion expPlanif : rdo) {
			ExpedientePlanificacionReport expReport = new ExpedientePlanificacionReport();
			expReport.setExpedientePlanificacion(expPlanif);
			for (ExpTareasResumen tarea : listaTareas) {
				if (expPlanif.getAnyo().equals(tarea.getAnyo()) && expPlanif.getNumExp().equals(tarea.getNumExp())) {
					expReport.getListaTareas().add(tarea);
				}
			}
			listaExpReport.add(expReport);
		}

		// obtenemos las columnas seleccionadas para visualizar en el excel
		List<ExcelCriterios> lCriterios = ExcelUtils.obtenerCriterios(columns);

		// anyadimos los campos que queremos mostrar en el excel
		for (ExpedientePlanificacionReport expediente : listaExpReport) {
			ExcelExpedientePlanificacionReport tabla = new ExcelExpedientePlanificacionReport();
			tabla.setCriterio(expediente.getExpedientePlanificacion());
			for (ExcelCriterios criterios : lCriterios) {
				tabla.addCriterio(criterios.getCriterios().get(Constants.LABEL),
						criterios.getCriterios().get(Constants.NAME), criterios.getPosicionX(),
						criterios.getPosicionY());
			}
			tabla.setMsg(this.msg);
			tabla.setLocale(locale);
			tabla.setPosicionX(Constants.UNO);
			excelHoja.addContenido(tabla);

			// anyadimos los campos que queremos visualizar de las tareas y
			// sus
			// valores
			ExcelExpedientePlanificacionReport subTabla = new ExcelExpedientePlanificacionReport();
			subTabla.setCriterio(expediente.getExpedientePlanificacion());
			subTabla.addColumna(LABEL_TAREA_TIPOTAREA, LISTA_TAREAS, NAME_TAREA_TIPOTAREA, Constants.DOS, true);
			subTabla.addColumna(LABEL_TAREA_RECURSOASIGNADO, LISTA_TAREAS, NAME_TAREA_RECURSOASIGNADO, Constants.TRES,
					true);
			subTabla.addColumna(LABEL_TAREA_FECHAHORAASIGNACION, LISTA_TAREAS, NAME_TAREA_FECHAHORAASIGNACION,
					Constants.TRES, true);
			subTabla.addColumna(LABEL_TAREA_ESTADOACEPTACION, LISTA_TAREAS, NAME_TAREA_ESTADOACEPTACION, Constants.DOS,
					true);
			subTabla.addColumna(LABEL_TAREA_ESTADOEJECUCION, LISTA_TAREAS, NAME_TAREA_ESTADOEJECUCION, Constants.DOS,
					true);
			subTabla.addColumna(LABEL_TAREA_HORASPREVISTAS, LISTA_TAREAS, NAME_TAREA_HORASPREVISTAS, Constants.TRES,
					true);
			subTabla.addColumna(LABEL_TAREA_FECHAHORAFINALIZACION, LISTA_TAREAS, NAME_TAREA_FECHAHORAFINALIZACION,
					Constants.TRES, true);
			subTabla.addColumna(LABEL_TAREA_FECHAHORAENTREGA, LISTA_TAREAS, NAME_TAREA_FECHAHORAENTREGA, Constants.DOS,
					true);

			List<ExpedientePlanificacionReport> aux = new ArrayList<ExpedientePlanificacionReport>();
			aux.add(expediente);
			subTabla.setDatos(aux);
			subTabla.setMsg(this.msg);
			subTabla.setLocale(locale);
			subTabla.setPosicionX(Constants.DOS);
			excelHoja.addContenido(subTabla);
		}
		excelModel.getListaHojas().add(excelHoja);
		return excelModel;
	}

	private void crearCabecera(String titulo, Locale locale, ExcelHoja excelHoja) {
		// Parametros
		ExcelCelda imagenCelda = new ExcelCelda();
		imagenCelda.setTipoCelda(Reports.TIPO_CELDA_IMAGEN);
		imagenCelda.setRutaImagen(Reports.RUTA_IMAGEN);
		imagenCelda.setPosicionX(0);
		imagenCelda.setPosicionX(0);
		excelHoja.addParametro(IMAGEN, imagenCelda);
		ExcelCelda tituloCelda = new ExcelCelda(titulo, Constants.TRES, Constants.DOS, ExcelEstilo.TITLE);
		excelHoja.addParametro(TITULO, tituloCelda);

	}

	@Override
	protected GenericoDao<ExpedientePlanificacionReport> getDao() {
		return this.expedientePlanificacionReportDao;
	}

}
