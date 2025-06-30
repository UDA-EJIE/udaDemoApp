/**
 *
 */
package com.ejie.aa79b.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DatosTareaTradosDocs;
import com.ejie.aa79b.model.MetadatosBusqueda;
import com.ejie.aa79b.model.trados.Analyse;
import com.ejie.aa79b.model.trados.Task;
import com.ejie.aa79b.model.trados.Task.TaskInfo;

/**
 * @author aresua
 *
 */
public class TradosUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradosUtils.class);

	public static final String TIPO_ARCHIVO = ".sdlxliff";

	/**
	 *
	 */
	private TradosUtils() {

	}

	/**
	 *
	 * @param task
	 *            Task
	 * @return List<MetadatosBusqueda>
	 */
	public static final List<MetadatosBusqueda> getListMetadatos(Task task) {
		final List<MetadatosBusqueda> lista = new ArrayList<MetadatosBusqueda>();
		MetadatosBusqueda metadatosBusqueda = new MetadatosBusqueda();
		for (TaskInfo.Tm tm : task.getTaskInfo().getTm()) {
			metadatosBusqueda = TradosUtils.getMetadatosBusqueda();
			metadatosBusqueda.setDescEu(tm.getName());
			metadatosBusqueda.setDescEs(tm.getName());
			lista.add(metadatosBusqueda);
		}
		return lista;
	}

	/**
	 *
	 * @return MetadatosBusqueda
	 */
	private static final MetadatosBusqueda getMetadatosBusqueda() {
		return new MetadatosBusqueda();
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @param datosTareaTrados
	 *            DatosTareaTrados
	 */
	public static final void getDatosTareaTrados(Analyse analyse, DatosTareaTrados datosTareaTrados) {
		datosTareaTrados.setNumPalConcor084(TradosUtils.analyseFrom0To84Data(analyse));
		datosTareaTrados.setNumPalConcor8594(TradosUtils.analyseFrom85To94Data(analyse));
		datosTareaTrados.setNumPalConcor95100(TradosUtils.analyseFrom95To100Data(analyse));
		datosTareaTrados.setNumPalConcor9599(TradosUtils.analyseFrom95To99Data(analyse));
		datosTareaTrados.setNumPalConcor100(TradosUtils.analyseFrom100Data(analyse));
		datosTareaTrados.setNumTotalPal(TradosUtils.analyseTotal(analyse));
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @param datosTareaTradosDocs
	 *            DatosTareaTradosDocs
	 */
	public static final void getDatosTareaTradosDocs(Analyse analyse, DatosTareaTradosDocs datosTareaTradosDocs) {
		datosTareaTradosDocs.setNumPalConcor084(TradosUtils.analyseFrom0To84Data(analyse));
		datosTareaTradosDocs.setNumPalConcor8594(TradosUtils.analyseFrom85To94Data(analyse));
		datosTareaTradosDocs.setNumPalConcor95100(TradosUtils.analyseFrom95To100Data(analyse));
		datosTareaTradosDocs.setNumPalConcor9599(TradosUtils.analyseFrom95To99Data(analyse));
		datosTareaTradosDocs.setNumPalConcor100(TradosUtils.analyseFrom100Data(analyse));
		datosTareaTradosDocs.setNumTotalPal(TradosUtils.analyseTotal(analyse));
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @return Long
	 */
	private static final Integer analyseFrom0To84Data(Analyse analyse) {
		TradosUtils.LOGGER.debug("******************************************** EXPEDIENTE: 0-84%");

		return analyse.getNew().getWords().intValue() + analyse.getFuzzy().get(Constants.CERO).getWords().intValue()
				+ analyse.getFuzzy().get(Constants.UNO).getWords().intValue();
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @return Long
	 */
	private static final Integer analyseFrom85To94Data(Analyse analyse) {
		TradosUtils.LOGGER.debug("******************************************** EXPEDIENTE: 85-94%");
		return analyse.getFuzzy().get(2).getWords().intValue();
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @return Long
	 */
	private static final Integer analyseFrom95To99Data(Analyse analyse) {
		TradosUtils.LOGGER.debug("******************************************** EXPEDIENTE: 95-100%");
		return analyse.getInContextExact().getWords().intValue() + analyse.getExact().getWords().intValue()
				+ analyse.getRepeated().getWords().intValue()
				+ analyse.getCrossFileRepeated().getWords().intValue() + analyse.getFuzzy().get(Constants.TRES).getWords().intValue();
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @return Long
	 */
	private static final Integer analyseFrom100Data(Analyse analyse) {
		TradosUtils.LOGGER.debug("******************************************** EXPEDIENTE: 95-100%");
		return analyse.getPerfect().getWords().intValue();
	}



	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @return Long
	 */
	private static final Integer analyseFrom95To100Data(Analyse analyse) {
		TradosUtils.LOGGER.debug("******************************************** EXPEDIENTE: 95-100%");
		return analyse.getInContextExact().getWords().intValue() + analyse.getExact().getWords().intValue()
				+ analyse.getRepeated().getWords().intValue() + analyse.getPerfect().getWords().intValue()
				+ analyse.getCrossFileRepeated().getWords().intValue() + analyse.getFuzzy().get(Constants.TRES).getWords().intValue();
	}

	/**
	 *
	 * @param analyse
	 *            Analyse
	 * @return Long
	 */
	private static final Integer analyseTotal(Analyse analyse) {
		TradosUtils.LOGGER.debug("******************************************** EXPEDIENTE: Total");
		return analyse.getTotal().getWords().intValue();
	}

	/**
	 *
	 * @param anyo
	 *            String
	 * @param numExp
	 *            String
	 * @return String
	 */
	private static final String getAnyoNumExpTrados(String anyo, String numExp) {
		return "" + anyo + Constants.CONTRABARRA + String.format("%06d", Integer.valueOf(numExp));
	}

	/**
	 *
	 * @param nombre
	 *            String
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return boolean
	 */
	public static final boolean esProyectoTradosValido(String nombre, Long anyo, Integer numExp) {
		boolean esValid = false;
		final String nombreFicheroTradosSplit = nombre.split(Constants.GUION_BAJO)[Constants.CERO];
		final String[] split = nombreFicheroTradosSplit.split(Constants.GUION);
		if (TradosUtils.getAnyoNumExpTrados(split[Constants.CERO], split[Constants.UNO])
				.equals(Utils.getAnyoNumExpConcatenado(anyo, numExp))) {
			esValid = true;
		}
		return esValid;
	}

}
