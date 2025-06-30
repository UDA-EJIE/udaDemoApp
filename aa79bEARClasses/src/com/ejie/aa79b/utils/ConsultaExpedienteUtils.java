package com.ejie.aa79b.utils;

import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.ConsultasDaoImpl;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ListaCategExp;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;

public class ConsultaExpedienteUtils {

	protected static final String ACTIVO_ENUM = "ActivoEnum";
	protected static final String TIPOEXPEDIENTE_ENUM = "TipoExpedienteEnum";
	protected static final String TIPOPETICIONARIO_ENUM = "TipoPeticionarioEnum";
	protected static final String APOST_COMA_ESPACIO = "', '";
	protected static final String COMA_ESPACIO_APOST = ", '";

	private ConsultaExpedienteUtils() {
		// Constructor
	}

	public static boolean listaExpYGestorValidos(ListaExpediente listaExpedientes) {
		return listaExpedientes != null && !listaExpedientes.getListaExpediente().isEmpty()
				&& listaExpedientes.getListaExpediente().get(0).getGestorExpediente() != null
				&& listaExpedientes.getListaExpediente().get(0).getGestorExpediente().getSolicitante() != null;
	}

	public static boolean listaExpedientesValida(ListaExpediente listaExpedientes) {
		return listaExpedientes != null && listaExpedientes.getListaExpediente() != null
				&& !listaExpedientes.getListaExpediente().isEmpty();
	}

	public static boolean listaMetadatosValida(ListaCategExp listaMetadatos) {
		return listaMetadatos != null && listaMetadatos.getListaCategExp() != null
				&& !listaMetadatos.getListaCategExp().isEmpty();
	}

	public static boolean gestorValido(Expediente expediente) {
		return expediente != null && expediente.getGestorExpediente() != null
				&& expediente.getGestorExpediente().getSolicitante() != null;
	}

	/**
	 * 
	 * @param filter
	 *            Expediente
	 * @param params
	 *            List<Object>
	 * @param msg
	 *            ReloadableResourceBundleMessageSource
	 * @param esIntepretacion
	 * @return
	 */
	public static String getDatosPagoProveedoresConsultaSelect(Expediente filter, List<Object> params,
			ReloadableResourceBundleMessageSource msg, Boolean esIntepretacion) {
		StringBuilder selectDPPC = new StringBuilder();
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		selectDPPC.append(DaoConstants.SELECT);
		if (esIntepretacion) {
			selectDPPC.append(getCamposInterDatosPagoProveedoresConsultaSelect());
		} else {
			selectDPPC.append(getCamposTradRevDatosPagoProveedoresConsultaSelect());
		}
		selectDPPC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051 + DaoConstants.BLANK
				+ DBConstants.IDTIPOEXPEDIENTE + DaoConstants.SIGNO_COMA);
		selectDPPC
				.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T4_MINUSCULA_PUNTO
						+ DBConstants.ID_TIPO_EXPEDIENTE_051 + COMA_ESPACIO_APOST
						+ TipoExpedienteEnum.INTERPRETACION.getValue() + APOST_COMA_ESPACIO)
				.append(msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append(APOST_COMA_ESPACIO + TipoExpedienteEnum.TRADUCCION.getValue() + APOST_COMA_ESPACIO)
				.append(msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append(APOST_COMA_ESPACIO + TipoExpedienteEnum.REVISION.getValue() + APOST_COMA_ESPACIO)
				.append(msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCES + DaoConstants.SIGNO_COMA);
		selectDPPC
				.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T4_MINUSCULA_PUNTO
						+ DBConstants.ID_TIPO_EXPEDIENTE_051 + COMA_ESPACIO_APOST
						+ TipoExpedienteEnum.INTERPRETACION.getValue() + APOST_COMA_ESPACIO)
				.append(msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append(APOST_COMA_ESPACIO + TipoExpedienteEnum.TRADUCCION.getValue() + APOST_COMA_ESPACIO)
				.append(msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append(APOST_COMA_ESPACIO + TipoExpedienteEnum.REVISION.getValue() + APOST_COMA_ESPACIO)
				.append(msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu)).append(DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPOEXPEDIENTEDESCEU);
		return selectDPPC.toString();
	}

	private static String getCamposTradRevDatosPagoProveedoresConsultaSelect() {
		StringBuilder selectTRDPPC = new StringBuilder();
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_TAREA_094 + DaoConstants.BLANK
				+ DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_BASE_094 + DaoConstants.BLANK
				+ DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_IVA_094 + DaoConstants.BLANK
				+ DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IVA_APLIC_094 + DaoConstants.BLANK
				+ DBConstants.PORIVA + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_PENALIZACION_094 + DaoConstants.BLANK
				+ DBConstants.IMPORTEPENALIZACION + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_PAL_APLIC_094 + DaoConstants.BLANK
				+ DBConstants.IMPORTEPALAPLIC + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_RECARGO_APREMIO_094
				+ DaoConstants.BLANK + DBConstants.IMPORTERECARGOAPREMIO + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_RECARGO_FORMATO_094
				+ DaoConstants.BLANK + DBConstants.IMPORTERECARGOFORMATO + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_TAREA_094 + DaoConstants.BLANK
				+ DBConstants.IMPORTETAREA + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_TOTAL_094 + DaoConstants.BLANK
				+ DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PENALIZACION_094 + DaoConstants.BLANK
				+ DBConstants.INDPENALIZACION + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_RECARGO_APREMIO_094 + DaoConstants.BLANK
				+ DBConstants.INDRECARGOAPREMIO + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_RECARGO_FORMATO_094 + DaoConstants.BLANK
				+ DBConstants.INDRECARGOFORMATO + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_0_84_094 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_85_94_094 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_95_100_094 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR95100 + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_RECARGO_FORMATO_094
				+ DaoConstants.BLANK + DBConstants.NUMPALRECARGOFORMATO + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_TOTAL_PAL_094 + DaoConstants.BLANK
				+ DBConstants.NUMTOTALPAL + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.POR_PENALIZACION_094 + DaoConstants.BLANK
				+ DBConstants.PORPENALIZACION + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.POR_RECARGO_APREMIO_094 + DaoConstants.BLANK
				+ DBConstants.PORRECARGOAPREMIO + DaoConstants.SIGNO_COMA);
		selectTRDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_ALBARAN_094 + DaoConstants.BLANK
				+ DBConstants.INDALBARAN + DaoConstants.SIGNO_COMA);
		return selectTRDPPC.toString();
	}

	private static String getCamposInterDatosPagoProveedoresConsultaSelect() {
		StringBuilder selectIDPPC = new StringBuilder();
		selectIDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_TAREA_095 + DaoConstants.BLANK
				+ DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
		selectIDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_BASE_095 + DaoConstants.BLANK
				+ DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
		selectIDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.POR_IVA_095 + DaoConstants.BLANK
				+ DBConstants.PORIVA + DaoConstants.SIGNO_COMA);
		selectIDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_IVA_095 + DaoConstants.BLANK
				+ DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
		selectIDPPC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_TOTAL_095 + DaoConstants.BLANK
				+ DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
		return selectIDPPC.toString();
	}

	public static String getDatosPagoProveedoresConsultaFrom(Expediente filter, Boolean esTabla,
			Boolean esIntepretacion) {
		StringBuilder fromDPPC = new StringBuilder();
		fromDPPC.append(DaoConstants.FROM + DBConstants.TABLA_81 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		fromDPPC.append(DaoConstants.JOIN + DBConstants.TABLA_81 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		fromDPPC.append(
				DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TAREA_081 + DaoConstants.SIGNOIGUAL);
		fromDPPC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TAREA_REL_081);
		if (esTabla) {
			if (esIntepretacion) {
				fromDPPC.append(
						DaoConstants.JOIN + DBConstants.TABLA_95 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
				fromDPPC.append(DaoConstants.ON + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_TAREA_095
						+ DaoConstants.SIGNOIGUAL);
				fromDPPC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TAREA_081);
			} else {
				fromDPPC.append(
						DaoConstants.JOIN + DBConstants.TABLA_94 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
				fromDPPC.append(DaoConstants.ON + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_TAREA_094
						+ DaoConstants.SIGNOIGUAL);
				fromDPPC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TAREA_081);
			}
			fromDPPC.append(DaoConstants.JOIN + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
			fromDPPC.append(
					DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081 + DaoConstants.SIGNOIGUAL);
			fromDPPC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ANYO_051);
			fromDPPC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081
					+ DaoConstants.SIGNOIGUAL);
			fromDPPC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051);

		}
		return fromDPPC.toString();
	}

	public static String getDatosPagoProveedoresConsultaWhere(Expediente filter, List<Object> params, Boolean esTabla,
			Boolean esIntepretacion) {
		StringBuilder whereDPPC = new StringBuilder();
		whereDPPC.append(DaoConstants.WHERE_1_1);
		whereDPPC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081,
				filter.getAnyo(), params));
		whereDPPC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081,
				filter.getNumExp(), params));
		whereDPPC.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.RECURSO_ASIGNACION_081,
				TipoRecursoEnum.EXTERNO.getValue(), params, false));
		whereDPPC.append(obtenerTiposDeTareaABuscarDatosPagoAProveedores(esIntepretacion, params));
		return whereDPPC.toString();
	}

	public static String obtenerTiposDeTareaABuscarDatosPagoAProveedores(Boolean esIntepretacion, List<Object> params) {
		StringBuilder sToAppend = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		int tipoTareaABuscar = 0;
		sToAppend.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081
				+ DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
		if (esIntepretacion) {
			sToAppend.append(TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue());
			tipoTareaABuscar = TipoTareaGestionAsociadaEnum.INTRODUCCION_DATOS_PAGO_PROVEEDORES.getValue();
		} else {
			sToAppend.append(TipoTareaGestionAsociadaEnum.REVISAR.getValue());
			sToAppend.append(DaoConstants.SIGNO_COMA + TipoTareaGestionAsociadaEnum.TRADUCIR.getValue());
			tipoTareaABuscar = TipoTareaGestionAsociadaEnum.REVISION_PAGO_PROVEEDOR.getValue();
		}
		sToAppend.append(DaoConstants.CERRAR_PARENTESIS);
		sToAppend.append(SqlUtils.generarWhereIgual(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081,
				tipoTareaABuscar, params));
		return sToAppend.toString();
	}

	public static String getDatosFacturacionExpedienteConsultaFrom(Expediente expediente, Boolean esInterpretacion) {
		StringBuilder fromDFEC = new StringBuilder();
		if (esInterpretacion) {
			fromDFEC.append(DaoConstants.FROM + DBConstants.TABLA_A3 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		} else {
			fromDFEC.append(DaoConstants.FROM + DBConstants.TABLA_97 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		}
		return fromDFEC.toString();
	}

	public static String getDatosFacturacionExpedienteConsultaWhere(Expediente expediente, List<Object> params,
			Boolean esIntepretacion) {
		StringBuilder whereDFEC = new StringBuilder();
		whereDFEC.append(DaoConstants.WHERE_1_1);
		if (esIntepretacion) {
			whereDFEC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_0A3,
					expediente.getAnyo(), params));
			whereDFEC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_0A3,
					expediente.getNumExp(), params));
			whereDFEC.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.IND_REVISADO_0A3,
					ActivoEnum.SI.getValue(), params, false));
		} else {
			whereDFEC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_097,
					expediente.getAnyo(), params));
			whereDFEC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_097,
					expediente.getNumExp(), params));
			whereDFEC.append(SqlUtils.generarWhereLike(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.IND_REVISADO_097,
					ActivoEnum.SI.getValue(), params, false));
		}
		return whereDFEC.toString();
	}

	public static String selectDatosDetalleExpedienteDesdeClienteConsulta(Locale es, Locale eu,
			ReloadableResourceBundleMessageSource msg) {
		StringBuilder selectDDEDCC = new StringBuilder();
		selectDDEDCC.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.BLANK + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.BLANK
				+ DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051 + DaoConstants.BLANK
				+ DBConstants.IDTIPOEXPEDIENTE);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051,
						DBConstants.TIPOEXPEDIENTEDESCES, msg, TIPOEXPEDIENTE_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051,
						DBConstants.TIPOEXPEDIENTEDESCEU, msg, TIPOEXPEDIENTE_ENUM, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TITULO_051
				+ DaoConstants.BLANK + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059 + DaoConstants.BLANK
				+ DBConstants.IDESTADOEXP + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059 + DaoConstants.BLANK
				+ DBConstants.IDFASEEXPEDIENTE);
		selectDDEDCC.append(
				Utils.obtenerAa06aEstadoFaseDesc(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059,
						DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059, msg, es));
		selectDDEDCC.append(
				Utils.obtenerAa06aEstadoFaseDesc(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059,
						DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059, msg, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.FECHA_ALTA_051
				+ DaoConstants.BLANK + DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.FECHA_ALTA_051 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAALTA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ORIGEN_051 + DaoConstants.BLANK
				+ DBConstants.ORIGEN + DaoConstants.SIGNO_COMA);
		// Datos interpretacion - INICIO
		selectDDEDCC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.MODO_INTERPRETACION_052 + DaoConstants.BLANK
				+ DBConstants.MODOINTERPRETACION + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T9_MINUSCULA_PUNTO + DBConstants.DESC_EU_014 + DaoConstants.BLANK
				+ DBConstants.MODOINTERPRETACIONDESCEU + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T9_MINUSCULA_PUNTO + DBConstants.DESC_ES_014 + DaoConstants.BLANK
				+ DBConstants.MODOINTERPRETACIONDESCES + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_ACTO_052 + DaoConstants.BLANK
				+ DBConstants.TIPOACTO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T11_MINUSCULA_PUNTO + DBConstants.DESC_EU_008 + DaoConstants.BLANK
				+ DBConstants.TIPOACTODESCEU + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T11_MINUSCULA_PUNTO + DBConstants.DESC_ES_008 + DaoConstants.BLANK
				+ DBConstants.TIPOACTODESCES + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_PETICIONARIO_052 + DaoConstants.BLANK
				+ DBConstants.TIPOPETICIONARIO);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_PETICIONARIO_052,
						DBConstants.TIPOPETICIONARIODESCES, msg, TIPOPETICIONARIO_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_PETICIONARIO_052,
						DBConstants.TIPOPETICIONARIODESCEU, msg, TIPOPETICIONARIO_ENUM, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PROGRAMADA_052
				+ DaoConstants.BLANK + DBConstants.INDPROGRAMADA);
		selectDDEDCC.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PROGRAMADA_052,
				DBConstants.PROGRAMADADESCES, msg, ACTIVO_ENUM, es));
		selectDDEDCC.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PROGRAMADA_052,
				DBConstants.PROGRAMADADESCEU, msg, ACTIVO_ENUM, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.FECHA_INI_052
				+ DaoConstants.BLANK + DBConstants.FECHAINI + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.FECHA_INI_052 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAINI + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.FECHA_FIN_052 + DaoConstants.BLANK
				+ DBConstants.FECHAFIN + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.FECHA_FIN_052 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAFIN + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.DIR_NORA_052 + DaoConstants.BLANK
				+ DBConstants.DIRNORA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_052 + DaoConstants.BLANK
				+ DBConstants.INDPRESUPUESTOINTER);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_052,
						DBConstants.DESCPRESUPUESTOINTERES, msg, ACTIVO_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_052,
						DBConstants.DESCPRESUPUESTOINTEREU, msg, ACTIVO_ENUM, eu));
		selectDDEDCC
				.append(DaoConstants.SIGNO_COMA + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_OBSERVACIONES_052
						+ DaoConstants.BLANK + DBConstants.INDOBSERVACIONESINTER + DaoConstants.SIGNO_COMA);
		// Datos interpretacion - FIN
		// Gestor expediente - INICIO
		selectDDEDCC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054 + DaoConstants.BLANK
				+ DBConstants.DNISOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.IND_VIP_054 + DaoConstants.BLANK
				+ DBConstants.INDVIPSOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_054 + DaoConstants.BLANK
				+ DBConstants.TIPOENTIDAD + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_054 + DaoConstants.BLANK
				+ DBConstants.IDENTIDAD + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.PREDNI_077 + DaoConstants.BLANK
				+ DBConstants.PREDNISOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.SUFDNI_077 + DaoConstants.BLANK
				+ DBConstants.SUFDNISOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.NOMBRE_077 + DaoConstants.BLANK
				+ DBConstants.NOMBRESOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.APEL1_077 + DaoConstants.BLANK
				+ DBConstants.APEL1SOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.APEL2_077 + DaoConstants.BLANK
				+ DBConstants.APEL2SOLICITANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.CIF + DaoConstants.BLANK
				+ DBConstants.CIFENTIDAD + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_EU + DaoConstants.BLANK
				+ DBConstants.DESCAMPENTIDADEU + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES + DaoConstants.BLANK
				+ DBConstants.DESCAMPENTIDADES + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.DESC_EU + DaoConstants.BLANK
				+ DBConstants.DESCENTIDADEU + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.DESC_ES + DaoConstants.BLANK
				+ DBConstants.DESCENTIDADES + DaoConstants.SIGNO_COMA);
		// Gestor expediente - FIN
		// Representante gestor - INICIO
		selectDDEDCC.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DNI_REPRESENTANTE_054 + DaoConstants.BLANK
				+ DBConstants.DNIREPRESENTANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.PREDNI_077 + DaoConstants.BLANK
				+ DBConstants.PREDNIREPRESENTANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.SUFDNI_077 + DaoConstants.BLANK
				+ DBConstants.SUFDNIREPRESENTANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.NOMBRE_077 + DaoConstants.BLANK
				+ DBConstants.NOMBREREPRESENTANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.APEL1_077 + DaoConstants.BLANK
				+ DBConstants.APEL1REPRESENTANTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.APEL2_077 + DaoConstants.BLANK
				+ DBConstants.APEL2REPRESENTANTE + DaoConstants.SIGNO_COMA);
		// Representante gestor - FIN
		// Datos traduccion/revision - INICIO
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_TOTAL_PAL_SOLIC_053 + DaoConstants.BLANK
				+ DBConstants.NUMTOTALPALSOLIC + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_FACTURABLE_053 + DaoConstants.BLANK
				+ DBConstants.INDFACTURABLE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_RELEVANCIA_053 + DaoConstants.BLANK
				+ DBConstants.IDRELEVANCIA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T3_MINUSCULA_PUNTO
				+ DBConstants.FECHA_FINAL_IZO_053 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAFINALIZO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_URGENTE_053 + DaoConstants.BLANK
				+ DBConstants.INDURGENTE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.FECHA_FINAL_PROP_053 + DaoConstants.BLANK
				+ DBConstants.FECHAFINALPROP + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.FECHA_FINAL_SOLIC_053 + DaoConstants.BLANK
				+ DBConstants.FECHAFINALSOLIC + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T3_MINUSCULA_PUNTO
				+ DBConstants.FECHA_FINAL_SOLIC_053 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAFINALSOLIC + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053 + DaoConstants.BLANK
				+ DBConstants.INDPUBLICARBOPV);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053,
						DBConstants.PUBLICARBOPVDESCES, msg, ACTIVO_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053,
						DBConstants.PUBLICARBOPVDESCEU, msg, ACTIVO_ENUM, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_053
				+ DaoConstants.BLANK + DBConstants.INDPRESUPUESTOTRADREV);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_053,
						DBConstants.DESCPRESUPUESTOTRADREVES, msg, ACTIVO_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_PRESUPUESTO_053,
						DBConstants.DESCPRESUPUESTOTRADREVEU, msg, ACTIVO_ENUM, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.REF_TRAMITAGUNE_053
				+ DaoConstants.BLANK + DBConstants.REFTRAMITAGUNE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_CORREDACCION_053 + DaoConstants.BLANK
				+ DBConstants.INDCORREDACCION);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_CORREDACCION_053,
						DBConstants.DESCCORREDACCIONES, msg, ACTIVO_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_CORREDACCION_053,
						DBConstants.DESCCORREDACCIONEU, msg, ACTIVO_ENUM, eu));
		selectDDEDCC.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_IDIOMA_053
				+ DaoConstants.BLANK + DBConstants.IDIDIOMA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T12_MINUSCULA_PUNTO + DBConstants.DESC_IDIOMA_EU_009 + DaoConstants.BLANK
				+ DBConstants.IDIOMADESCEU + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T12_MINUSCULA_PUNTO + DBConstants.DESC_IDIOMA_ES_009 + DaoConstants.BLANK
				+ DBConstants.IDIOMADESCES + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_CONFIDENCIAL_053 + DaoConstants.BLANK
				+ DBConstants.INDCONFIDENCIAL);
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_CONFIDENCIAL_053,
						DBConstants.INDCONFIDENCIALDESCES, msg, ACTIVO_ENUM, es));
		selectDDEDCC
				.append(DAOUtils.getDecodeAcciones(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_CONFIDENCIAL_053,
						DBConstants.INDCONFIDENCIALDESCEU, msg, ACTIVO_ENUM, eu));
		selectDDEDCC
				.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_TOTAL_PAL_IZO_053
						+ DaoConstants.BLANK + DBConstants.NUMTOTALPALIZO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.FECHA_FINAL_IZO_053 + DaoConstants.BLANK
				+ DBConstants.FECHAFINALIZO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_OBSERVACIONES_053 + DaoConstants.BLANK
				+ DBConstants.INDOBSERVACIONESTRADREV + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.TEXTO_053 + DaoConstants.BLANK
				+ DBConstants.TEXTO53 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.TIPO_REDACCION_053 + DaoConstants.BLANK
				+ DBConstants.TIPOREDACCION53 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.PARTICIPANTES_053 + DaoConstants.BLANK
				+ DBConstants.PARTICIPANTES53 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.FECHA_ENTREGA_REAL_053 + DaoConstants.BLANK
				+ DBConstants.FECHAENTREGA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T3_MINUSCULA_PUNTO
				+ DBConstants.FECHA_ENTREGA_REAL_053 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAENTREGA + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T13_MINUSCULA_PUNTO + DBConstants.FECHA_LIMITE_064 + DaoConstants.BLANK
				+ DBConstants.FECHALIMITE + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T13_MINUSCULA_PUNTO + DBConstants.ESTADO_SUBSANACION_064 + DaoConstants.BLANK
				+ DBConstants.ESTADOSUBSANACION + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T13_MINUSCULA_PUNTO + DBConstants.IND_SUBSANADO_064 + DaoConstants.BLANK
				+ DBConstants.INDSUBSANADO + DaoConstants.SIGNO_COMA);
		// Datos traduccion/revision - FIN
		// Fichero Observaciones - INICIO
		selectDDEDCC.append(DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.OBSERVACIONES_055 + DaoConstants.BLANK
				+ DBConstants.OBSERVACIONES55 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.OID_FICHERO_055 + DaoConstants.BLANK
				+ DBConstants.OIDFICHERO55 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.EXTENSION_DOC_055 + DaoConstants.BLANK
				+ DBConstants.EXTENSIONDOC55 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.CONTENT_TYPE_055 + DaoConstants.BLANK
				+ DBConstants.CONTENTTYPE55 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.NOMBRE_FICHERO_055 + DaoConstants.BLANK
				+ DBConstants.NOMBREFICHERO55 + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T5_MINUSCULA_PUNTO
				+ DBConstants.ID_REQUERIMIENTO_059 + DaoConstants.SIGNO_COMA_SIN_ESPACIOS + Constants.MINUS_UNO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.IDREQUERIMIENTO + DaoConstants.SIGNO_COMA);
		selectDDEDCC.append(DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T15_MINUSCULA_PUNTO
				+ DBConstants.TIPO_REQUERIMIENTO_064 + DaoConstants.SIGNO_COMA_SIN_ESPACIOS + Constants.MINUS_UNO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.TIPOREQUERIMIENTO);
		// Fichero Observaciones - FIN
		return selectDDEDCC.toString();
	}

	public static String fromDatosDetalleExpedienteDesdeClienteConsulta() {
		StringBuilder fromDDEDCC = new StringBuilder();
		fromDDEDCC.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// Datos Interpretación
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_052);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_052);
		// Datos Traducción/Revisión
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ANYO_053);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_EXP_053);
		// Gestor Expediente
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_54 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ANYO_054);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.NUM_EXP_054);
		// bitacora
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.T5_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ANYO_059);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.NUM_EXP_059);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_BITACORA_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_BITACORA_059);
		// NombreApellidosGestor
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + DaoConstants.T6_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.DNI_077);
		// Entidad
		fromDDEDCC.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK
				+ DaoConstants.G1_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_054
				+ DaoConstants.SIGNOIGUAL + DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.CODIGO);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_054
				+ DaoConstants.SIGNOIGUAL + DaoConstants.G1_MINUSCULA_PUNTO + DBConstants.TIPO);
		// NombreApellidosRepresentante
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + DaoConstants.T8_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DNI_REPRESENTANTE_054
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.DNI_077);
		// Modo interpretacion
		fromDDEDCC
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_14 + DaoConstants.BLANK + DaoConstants.T9_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.MODO_INTERPRETACION_052
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T9_MINUSCULA_PUNTO + DBConstants.ID_014);
		// Tipos interpretacion
		fromDDEDCC.append(
				DaoConstants.LEFT_JOIN + DBConstants.TABLA_08 + DaoConstants.BLANK + DaoConstants.T11_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_ACTO_052
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T11_MINUSCULA_PUNTO + DBConstants.ID_008);
		// Idioma
		fromDDEDCC.append(
				DaoConstants.LEFT_JOIN + DBConstants.TABLA_09 + DaoConstants.BLANK + DaoConstants.T12_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_IDIOMA_053
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T12_MINUSCULA_PUNTO + DBConstants.ID_IDIOMA_009);
		// Fecha y hora límite para el requerimiento de tramitación
		fromDDEDCC.append(
				DaoConstants.LEFT_JOIN + DBConstants.TABLA_64 + DaoConstants.BLANK + DaoConstants.T13_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_REQUERIMIENTO_059
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T13_MINUSCULA_PUNTO + DBConstants.ID_064);
		// Fichero observaciones
		fromDDEDCC.append(
				DaoConstants.LEFT_JOIN + DBConstants.TABLA_55 + DaoConstants.BLANK + DaoConstants.T14_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.ANYO_055);
		fromDDEDCC.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T14_MINUSCULA_PUNTO + DBConstants.NUM_EXP_055);
		fromDDEDCC.append(
				DaoConstants.LEFT_JOIN + DBConstants.TABLA_64 + DaoConstants.BLANK + DaoConstants.T15_MINUSCULA);
		fromDDEDCC.append(DaoConstants.ON + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_REQUERIMIENTO_059
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T15_MINUSCULA_PUNTO + DBConstants.ID_064);
		// FROM - FIN
		return fromDDEDCC.toString();
	}

	public static String getDatosTareaTradosQuery(Expediente expediente, List<Object> paramsDTT) {
		StringBuilder queryDTT = new StringBuilder();
		queryDTT.append(DaoConstants.SELECT + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_TOTAL_PAL_090
				+ DaoConstants.BLANK + DBConstants.NUM_TOTAL_PAL_090 + DaoConstants.SIGNO_COMA);
		queryDTT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_0_84_090 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
		queryDTT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_85_94_090 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
		queryDTT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_95_100_090 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR95100);
		queryDTT.append(DaoConstants.FROM + DBConstants.TABLA_81 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		queryDTT.append(DaoConstants.JOIN + DBConstants.TABLA_90 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		queryDTT.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_TAREA_090
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TAREA_081);
		queryDTT.append(DaoConstants.WHERE_1_1);
		queryDTT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_TAREA_081,
				TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue(), paramsDTT));
		queryDTT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ESTADO_EJECUCION_081,
				EstadoEjecucionTareaEnum.EJECUTADA.getValue(), paramsDTT));
		queryDTT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081,
				expediente.getAnyo(), paramsDTT));
		queryDTT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081,
				expediente.getNumExp(), paramsDTT));
		return queryDTT.toString();
	}

	public static void filtroEstado(StringBuilder whereCEW, BitacoraExpediente bitacora, List<Object> params,
			String nomTabla59, String nomTabla51) {
		String estadoABuscar = EstadoEnum.ALTA.getValue();
		if (bitacora != null && bitacora.getEstadoExp() != null && bitacora.getEstadoExp().getId() != null) {
			if (Constants.CEROLONG != bitacora.getEstadoExp().getId()) {
				whereCEW.append(SqlUtils.generarWhereIgual(
						nomTabla59 + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ESTADO_EXP_059,
						bitacora.getEstadoExp().getId(), params));
				if (bitacora.getFaseExp() != null && bitacora.getFaseExp().getId() != null) {
					whereCEW.append(SqlUtils.generarWhereIgual(
							nomTabla59 + DaoConstants.SIGNO_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059,
							bitacora.getFaseExp().getId(), params));
				}
			} else {
				estadoABuscar = EstadoEnum.BAJA.getValue();
			}
			// ESTADO BAJA
			whereCEW.append(DaoConstants.AND + nomTabla51 + DaoConstants.SIGNO_PUNTO + DBConstants.ESTADO_BAJA_051
					+ DaoConstants.SIGNOIGUAL + DaoConstants.SIGNO_APOSTROFO + estadoABuscar
					+ DaoConstants.SIGNO_APOSTROFO);
		}

	}

}
