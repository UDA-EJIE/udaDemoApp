package com.ejie.aa79b.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.EntradaDatosExpediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.CodProvinciaEnum;
import com.ejie.aa79b.model.enums.EstadoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.Aa79bDireccionNora;

public class Aa79bExpedienteWSUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	protected static final String ACTIVO_ENUM = "ActivoEnum";

	public Aa79bExpedienteWSUtils() {
		// Constructor
	}

	public static void getSelectDatosFacturacionInterpretacion(Locale eu, Locale es, StringBuilder selectDPFI,
			ReloadableResourceBundleMessageSource msg) {
		/* ANYO */
		selectDPFI.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.BLANK + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		/* NUMEXP */
		selectDPFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.BLANK
				+ DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		/* ANYONUMEXPCONCATENADO */
		selectDPFI.append(DaoConstants.SUBSTR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.ANYO_051);
		selectDPFI.append(DaoConstants.SIGNO_COMA + Constants.DOS + DaoConstants.SIGNO_COMA + Constants.CUATRO
				+ DaoConstants.CERRAR_PARENTESIS);
		selectDPFI.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + Constants.CONTRABARRA
				+ DaoConstants.SIGNO_APOSTROFO);
		selectDPFI.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.LPAD + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051);
		selectDPFI.append(DaoConstants.SIGNO_COMA + Constants.SEIS + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + Constants.CERO + DaoConstants.SIGNO_APOSTROFO);
		selectDPFI.append(DaoConstants.CERRAR_PARENTESIS + DBConstants.ANYONUMEXPCONCATENADO + DaoConstants.SIGNO_COMA);
		/* IDTIPOEXPEDIENTE */
		selectDPFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051 + DaoConstants.BLANK
				+ DBConstants.IDTIPOEXPEDIENTE);
		// TIPOEXPEDIENTEDESCES
		selectDPFI
				.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + DaoConstants.SIGNO_COMA
						+ DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
						+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.TRADUCCION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.REVISION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es)).append(DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPOEXPEDIENTEDESCES);
		// TIPOEXPEDIENTEDESCEU
		selectDPFI
				.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + DaoConstants.SIGNO_COMA
						+ DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
						+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.TRADUCCION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.REVISION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCEU + DaoConstants.SIGNO_COMA);
		// FECHAALTA
		selectDPFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.FECHA_ALTA_051 + DaoConstants.BLANK
				+ DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
		// HORAALTA
		selectDPFI.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.FECHA_ALTA_051 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAALTA + DaoConstants.SIGNO_COMA);
		// TIPOACTO
		selectDPFI.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_ACTO_052 + DaoConstants.BLANK
				+ DBConstants.TIPOACTO + DaoConstants.SIGNO_COMA);
		// TIPOACTODESCES
		selectDPFI.append(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_ES_008 + DaoConstants.BLANK
				+ DBConstants.TIPOACTODESCES + DaoConstants.SIGNO_COMA);
		// TIPOACTODESCEU
		selectDPFI.append(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_EU_008 + DaoConstants.BLANK
				+ DBConstants.TIPOACTODESCEU + DaoConstants.SIGNO_COMA);
		// INDPROGRAMADA
		selectDPFI.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PROGRAMADA_052 + DaoConstants.BLANK
				+ DBConstants.INDPROGRAMADA);
		// INDPROGRAMADADESCES
		selectDPFI.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PROGRAMADA_052,
				DBConstants.INDPROGRAMADADESCES, msg, ACTIVO_ENUM, es));
		// INDPROGRAMADADESCEU
		selectDPFI.append(DAOUtils.getDecodeAcciones(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PROGRAMADA_052,
				DBConstants.INDPROGRAMADADESCEU, msg, ACTIVO_ENUM, eu) + DaoConstants.SIGNO_COMA);
		// ESCAEDESCES
		selectDPFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T4_MINUSCULA_PUNTO
				+ DBConstants.CODPROV_049 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ CodProvinciaEnum.ARABA.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ CodProvinciaEnum.BIZKAIA.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ CodProvinciaEnum.GIPUZKOA.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.ESCAEDESCES + DaoConstants.SIGNO_COMA);
		// ESCAEDESCEU
		selectDPFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T4_MINUSCULA_PUNTO
				+ DBConstants.CODPROV_049 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ CodProvinciaEnum.ARABA.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ CodProvinciaEnum.BIZKAIA.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ CodProvinciaEnum.GIPUZKOA.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.ESCAEDESCEU + DaoConstants.SIGNO_COMA);
		// NUMINTERPRETES
		selectDPFI.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_INTERPRETES_0A3 + DaoConstants.BLANK
				+ DBConstants.NUMINTERPRETES + DaoConstants.SIGNO_COMA);
		// HORASINTERPRETACION
		selectDPFI.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.HORAS_INTERPRETACION_0A3 + DaoConstants.BLANK
				+ DBConstants.HORASINTERPRETACION + DaoConstants.SIGNO_COMA);
		// PORIVA
		selectDPFI.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.POR_IVA_0A3 + DaoConstants.BLANK
				+ DBConstants.PORIVA + DaoConstants.SIGNO_COMA);
		// IMPORTEBASE
		selectDPFI.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_BASE_0A3 + DaoConstants.BLANK
				+ DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
		// IMPORTEIVA
		selectDPFI.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_IVA_0A3 + DaoConstants.BLANK
				+ DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
		// IMPORTETOTAL
		selectDPFI.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_TOTAL_0A3 + DaoConstants.BLANK
				+ DBConstants.IMPORTETOTAL);
	}

	public static void getFromDatosFacturacionInterpretacion(StringBuilder fromDPFI) {
		// 51
		fromDPFI.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// 52
		fromDPFI.append(DaoConstants.JOIN + DBConstants.TABLA_52 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		fromDPFI.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_052);
		fromDPFI.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_052);
		// A3
		fromDPFI.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_A3 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		fromDPFI.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ANYO_0A3);
		fromDPFI.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_EXP_0A3);
		fromDPFI.append(DaoConstants.AND + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_REVISADO_0A3
				+ DaoConstants.SIGNOIGUAL + DaoConstants.SIGNO_APOSTROFO + ActivoEnum.SI.getValue()
				+ DaoConstants.SIGNO_APOSTROFO);
		// 49
		fromDPFI.append(DaoConstants.JOIN + DBConstants.TABLA_49 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		fromDPFI.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.DIR_NORA_052
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.CDIRNORA_049);
		// 08
		fromDPFI.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_08 + DaoConstants.BLANK + DaoConstants.T5_MINUSCULA);
		fromDPFI.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.TIPO_ACTO_052
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_008);
		fromDPFI.append(
				DaoConstants.AND + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ESTADO_008 + DaoConstants.SIGNOIGUAL
						+ DaoConstants.SIGNO_APOSTROFO + EstadoEnum.ALTA.getValue() + DaoConstants.SIGNO_APOSTROFO);
	}

	public static void getWhereDatosFacturacionInterpretacion(EntradaDatosExpediente datosExpediente,
			List<Object> paramsDPFI, StringBuilder whereDPFI) {
		whereDPFI.append(DaoConstants.WHERE_1_1);
		whereDPFI.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051,
				datosExpediente.getAnyo(), paramsDPFI));
		whereDPFI.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051,
				datosExpediente.getNumExp(), paramsDPFI));

	}

	public static void getSelectDatosEntidadContactoFacturacionInterpretacion(Locale eu, Locale es,
			StringBuilder selectDECFI, ReloadableResourceBundleMessageSource msg) {
		/* ID058 */
		selectDECFI.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_058
				+ DaoConstants.BLANK + DBConstants.ID_058 + DaoConstants.SIGNO_COMA);
		/* ANYO */
		selectDECFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_058 + DaoConstants.BLANK
				+ DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		/* NUMEXP */
		selectDECFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_058 + DaoConstants.BLANK
				+ DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		/* TIPOENTIDADASOCIADA */
		selectDECFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_ASOC_058 + DaoConstants.BLANK
				+ DBConstants.TIPOENTIDADASOCIADA + DaoConstants.SIGNO_COMA);
		/* IDENTIDADASOCIADA */
		selectDECFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_ASOC_058 + DaoConstants.BLANK
				+ DBConstants.IDENTIDADASOCIADA + DaoConstants.SIGNO_COMA);
		/* DNICONTACTO */
		selectDECFI.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI_CONTACTO_058 + DaoConstants.BLANK
				+ DBConstants.DNICONTACTO + DaoConstants.SIGNO_COMA);
		/* ENTIDADDESCAMPES */
		selectDECFI.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES + DaoConstants.BLANK
				+ DBConstants.DESCENTIDADES + DaoConstants.SIGNO_COMA);
		/* ENTIDADDESCAMPEU */
		selectDECFI.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.DESC_AMP_EU + DaoConstants.BLANK
				+ DBConstants.DESCENTIDADEU + DaoConstants.SIGNO_COMA);
		/* NOMBRE */
		selectDECFI.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.NOMBRE_077 + DaoConstants.BLANK
				+ DBConstants.NOMBRE + DaoConstants.SIGNO_COMA);
		/* APEL1 */
		selectDECFI.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.APEL1_077 + DaoConstants.BLANK
				+ DBConstants.APEL1 + DaoConstants.SIGNO_COMA);
		/* APEL2 */
		selectDECFI.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.APEL2_077 + DaoConstants.BLANK
				+ DBConstants.APEL2 + DaoConstants.SIGNO_COMA);
		/* FACTURABLE */
		selectDECFI.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.IND_FACTURABLE_0A6 + DaoConstants.BLANK
				+ DBConstants.FACTURABLE + DaoConstants.SIGNO_COMA);
		/* IVA */
		selectDECFI.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.IND_IVA_0A6 + DaoConstants.BLANK
				+ DBConstants.IVA + DaoConstants.SIGNO_COMA);
		/* FACTURABLEDESCES */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_FACTURABLE_0A6 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURABLEDESCES
				+ DaoConstants.SIGNO_COMA);
		/* FACTURABLEDESCEU */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_FACTURABLE_0A6 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURABLEDESCEU
				+ DaoConstants.SIGNO_COMA);
		/* IVADESCES */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_IVA_0A6 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.IVADESCES + DaoConstants.SIGNO_COMA);
		/* IVADESCEU */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_IVA_0A6 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.IVADESCEU + DaoConstants.SIGNO_COMA);
		/* CDIRNORA FACTURACION */
		selectDECFI.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.DIR_NORA_0A6 + DaoConstants.BLANK
				+ DBConstants.CDIRNORA + DaoConstants.SIGNO_COMA);
		/* NUMFACTURA */
		selectDECFI.append(" f2." + DBConstants.ID_FACTURA_0A4 + DaoConstants.BLANK + DBConstants.NUMFACTURA
				+ DaoConstants.SIGNO_COMA);
		selectDECFI.append(" f2.CODCONCATENADO CODCONCATENADO" + DaoConstants.SIGNO_COMA);
		/* FACTURADODESCES */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.ID_FACTURA_0A4
				+ DaoConstants.SIGNO_COMA + DaoConstants.NULL + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURADODESCES
				+ DaoConstants.SIGNO_COMA);
		/* FACTURADODESCEU */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.ID_FACTURA_0A4
				+ DaoConstants.SIGNO_COMA + DaoConstants.NULL + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURADODESCEU
				+ DaoConstants.SIGNO_COMA);
		/* PAGADODESCES */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL
				+ DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.T00_ESTADO_ID + DaoConstants.SIGNO_COMA
				+ Constants.CERO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA + Constants.DOS
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.PAGADODESCES + DaoConstants.SIGNO_COMA);
		/* PAGADODESCEU */
		selectDECFI.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL
				+ DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.T00_ESTADO_ID + DaoConstants.SIGNO_COMA
				+ Constants.CERO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA + Constants.DOS
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.PAGADODESCEU);

	}

	public static void getFromDatosEntidadContactoFacturacionInterpretacion(StringBuilder fromDECFI) {
		// 58
		fromDECFI.append(DaoConstants.FROM + DBConstants.TABLA_58 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// X54JAPI_ENTIDADES_SOLIC
		fromDECFI.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK
				+ DaoConstants.T2_MINUSCULA);
		fromDECFI.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.CODIGO + DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.ID_ENTIDAD_ASOC_058 + DaoConstants.AND + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.TIPO + DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.TIPO_ENTIDAD_ASOC_058 + DaoConstants.CERRAR_PARENTESIS);
		// A6
		fromDECFI
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_A6 + DaoConstants.BLANK + DaoConstants.T6_MINUSCULA);
		fromDECFI.append(DaoConstants.ON + DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.ID_0A6
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_058);
		// 77
		fromDECFI
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		fromDECFI.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DNI_077
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI_CONTACTO_058);
		//
		fromDECFI.append(DaoConstants.LEFT_JOIN).append(" ( ");
		fromDECFI.append(" SELECT t10.ANYO_0A5, ");
		fromDECFI.append(" t10.NUM_EXP_0A5, ");
		fromDECFI.append(" t9.TIPO_ENTIDAD_ASOC_0A4, ");
		fromDECFI.append(" t9.ID_ENTIDAD_ASOC_0A4, ");
		fromDECFI.append(" t9.DNI_CONTACTO_0A4, ");
		fromDECFI.append(" t9.ID_FACTURA_0A4, ");
		fromDECFI.append(" w2.T00_ESTADO_ID, ");
		fromDECFI.append(
				" CASE WHEN W2.T12_EJ_CONTABLE is null THEN '9999999999' ELSE SUBSTR(W2.T12_EJ_CONTABLE,3,2) || LPAD(t9.APP_ID_0A4,2,'0') || SUBSTR(t9.ID_LIQUIDACION_0A4,3,6) END CODCONCATENADO ");
		fromDECFI.append(" FROM AA79BA5S01 t10 ");
		fromDECFI.append(" JOIN AA79BA4S01 t9 ");
		fromDECFI.append(" ON t9.ID_FACTURA_0A4 = t10.ID_FACTURA_0A5 ");
		fromDECFI.append(" LEFT JOIN W0512S01 w2 ");
		fromDECFI.append(" ON t9.ID_LIQUIDACION_0A4 = w2.T12_REFERENCIA ");

	}

	public static void getWhereDatosEntidadContactoFacturacionInterpretacion(EntradaDatosExpediente datosExpediente,
			List<Object> paramsDECFI, StringBuilder whereDECFI) {
		whereDECFI.append(DaoConstants.WHERE);
		whereDECFI.append(" ( ( t9.ID_LIQUIDACION_0A4 IS NOT NULL ");
		whereDECFI.append(" AND w2.T00_ESTADO_ID <>4 ) ");
		whereDECFI.append(" OR (t9.ID_LIQUIDACION_0A4 IS NULL)) ");
		whereDECFI.append(" OR (t9.ID_LIQUIDACION_0A4 IS NULL) ");
		whereDECFI.append(SqlUtils.generarWhereIgual(DaoConstants.T10_MINUSCULA_PUNTO + DBConstants.ANYO_0A5,
				datosExpediente.getAnyo(), paramsDECFI));
		whereDECFI.append(SqlUtils.generarWhereIgual(DaoConstants.T10_MINUSCULA_PUNTO + DBConstants.NUM_EXP_0A5,
				datosExpediente.getNumExp(), paramsDECFI));
		whereDECFI.append(" ) f2 ");
		whereDECFI.append(" ON t1.ANYO_058 = f2.ANYO_0A5 ");
		whereDECFI.append(" AND t1.NUM_EXP_058 = f2.NUM_EXP_0A5 ");
		whereDECFI.append(" AND f2.TIPO_ENTIDAD_ASOC_0A4 = t1.TIPO_ENTIDAD_ASOC_058 ");
		whereDECFI.append(" AND f2.ID_ENTIDAD_ASOC_0A4 = t1.ID_ENTIDAD_ASOC_058 ");
		whereDECFI.append(" AND nvl(f2.DNI_CONTACTO_0A4,-1) = nvl(t1.DNI_CONTACTO_058,-1) ");
		whereDECFI.append(" WHERE 1 =1 ");
		whereDECFI.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_058,
				datosExpediente.getAnyo(), paramsDECFI));
		whereDECFI.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_058,
				datosExpediente.getNumExp(), paramsDECFI));
		whereDECFI.append(DaoConstants.ORDER_BY + DBConstants.ID_058 + DaoConstants.ASC);
	}

	public static boolean tieneDireccionNora(Aa79bDireccionNora dirNoraEntidad) {
		boolean direccionValida = false;
		if (dirNoraEntidad != null && dirNoraEntidad.getDireccion() != null
				&& dirNoraEntidad.getDireccion().getDirNora() > 0) {
			direccionValida = true;
		}
		return direccionValida;
	}

	public static void getSelectDatosFacturacionTraduccion(Locale eu, Locale es, StringBuilder selectDFT,
			ReloadableResourceBundleMessageSource msg) {
		/* ANYO */
		selectDFT.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.BLANK + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		/* NUMEXP */
		selectDFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.BLANK
				+ DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		/* ANYONUMEXPCONCATENADO */
		selectDFT.append(DaoConstants.SUBSTR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.ANYO_051);
		selectDFT.append(DaoConstants.SIGNO_COMA + Constants.DOS + DaoConstants.SIGNO_COMA + Constants.CUATRO
				+ DaoConstants.CERRAR_PARENTESIS);
		selectDFT.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + Constants.CONTRABARRA
				+ DaoConstants.SIGNO_APOSTROFO);
		selectDFT.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.LPAD + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051);
		selectDFT.append(DaoConstants.SIGNO_COMA + Constants.SEIS + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + Constants.CERO + DaoConstants.SIGNO_APOSTROFO);
		selectDFT.append(DaoConstants.CERRAR_PARENTESIS + DBConstants.ANYONUMEXPCONCATENADO + DaoConstants.SIGNO_COMA);
		/* IDTIPOEXPEDIENTE */
		selectDFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051 + DaoConstants.BLANK
				+ DBConstants.IDTIPOEXPEDIENTE);
		// TIPOEXPEDIENTEDESCES
		selectDFT
				.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + DaoConstants.SIGNO_COMA
						+ DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
						+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.TRADUCCION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.REVISION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es)).append(DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPOEXPEDIENTEDESCES);
		// TIPOEXPEDIENTEDESCEU
		selectDFT
				.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + DaoConstants.SIGNO_COMA
						+ DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
						+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.TRADUCCION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.REVISION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCEU + DaoConstants.SIGNO_COMA);
		// FECHAALTA
		selectDFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.FECHA_ALTA_051 + DaoConstants.BLANK
				+ DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
		// HORAALTA
		selectDFT.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.FECHA_ALTA_051 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAALTA + DaoConstants.SIGNO_COMA);
		// IDRELEVANCIA
		selectDFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_RELEVANCIA_053 + DaoConstants.BLANK
				+ DBConstants.IDRELEVANCIA + DaoConstants.SIGNO_COMA);
		// RELEVANCIADESCES
		selectDFT.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DESC_RELEVANCIA_ES_010 + DaoConstants.BLANK
				+ DBConstants.RELEVANCIADESCES + DaoConstants.SIGNO_COMA);
		// RELEVANCIADESCEU
		selectDFT.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DESC_RELEVANCIA_EU_010 + DaoConstants.BLANK
				+ DBConstants.RELEVANCIADESCEU + DaoConstants.SIGNO_COMA);
		// IDIDIOMA
		selectDFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_IDIOMA_053 + DaoConstants.BLANK
				+ DBConstants.IDIDIOMA + DaoConstants.SIGNO_COMA);
		// IDIOMADESCES
		selectDFT.append(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_IDIOMA_ES_009 + DaoConstants.BLANK
				+ DBConstants.IDIOMADESCES + DaoConstants.SIGNO_COMA);
		// IDIOMADESCEU
		selectDFT.append(DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.DESC_IDIOMA_EU_009 + DaoConstants.BLANK
				+ DBConstants.IDIOMADESCEU + DaoConstants.SIGNO_COMA);
		// INDPUBLICARBOPV
		selectDFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053 + DaoConstants.BLANK
				+ DBConstants.INDPUBLICARBOPV + DaoConstants.SIGNO_COMA);
		/* PUBLICARBOPVDESCES */
		selectDFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.IND_PUBLICAR_BOPV_053 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.PUBLICARBOPVDESCES
				+ DaoConstants.SIGNO_COMA);
		/* PUBLICARBOPVDESCEU */
		selectDFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.IND_PUBLICAR_BOPV_053 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.PUBLICARBOPVDESCEU
				+ DaoConstants.SIGNO_COMA);
		// INDURGENTE
		selectDFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_URGENTE_053 + DaoConstants.BLANK
				+ DBConstants.INDURGENTE + DaoConstants.SIGNO_COMA);
		/* APREMIODESCES */
		selectDFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.IND_URGENTE_053 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.APREMIODESCES
				+ DaoConstants.SIGNO_COMA);
		/* APREMIODESCEU */
		selectDFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.IND_URGENTE_053 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.APREMIODESCEU
				+ DaoConstants.SIGNO_COMA);
		// INDDIFICIL
		selectDFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.IND_DIFICIL_053 + DaoConstants.BLANK
				+ DBConstants.INDDIFICIL + DaoConstants.SIGNO_COMA);
		/* DIFICULTADDESCES */
		selectDFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.IND_DIFICIL_053 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.DIFICULTADDESCES
				+ DaoConstants.SIGNO_COMA);
		/* DIFICULTADDESCEU */
		selectDFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.IND_DIFICIL_053 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.DIFICULTADDESCEU
				+ DaoConstants.SIGNO_COMA);
		/* TARIFAPAL */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.TARIFA_PAL_097 + DaoConstants.BLANK
				+ DBConstants.TARIFAPAL + DaoConstants.SIGNO_COMA);
		/* NUMTOTALPALFACT */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_TOTAL_PAL_FACTURAR_097 + DaoConstants.BLANK
				+ DBConstants.NUMTOTALPALFACT + DaoConstants.SIGNO_COMA);
		/* NUMPALCONCOR084 */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_0_84_097 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
		/* NUMPALCONCOR8594 */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_85_94_097 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
		/* NUMPALCONCOR95100 */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_PAL_CONCOR_95_100_097 + DaoConstants.BLANK
				+ DBConstants.NUMPALCONCOR95100 + DaoConstants.SIGNO_COMA);
		/* IMPORTEDIFICULTAD */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_DIFICULTAD_097 + DaoConstants.BLANK
				+ DBConstants.IMPORTEDIFICULTAD + DaoConstants.SIGNO_COMA);
		/* IMPORTEURGENCIA */
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_URGENCIA_097 + DaoConstants.BLANK
				+ DBConstants.IMPORTEURGENCIA + DaoConstants.SIGNO_COMA);
		// PORIVA
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.POR_IVA_097 + DaoConstants.BLANK
				+ DBConstants.PORIVA + DaoConstants.SIGNO_COMA);
		// IMPORTEBASE
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_BASE_097 + DaoConstants.BLANK
				+ DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
		// IMPORTEIVA
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_IVA_097 + DaoConstants.BLANK
				+ DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
		// IMPORTETOTAL
		selectDFT.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IMPORTE_TOTAL_097 + DaoConstants.BLANK
				+ DBConstants.IMPORTETOTAL);
	}

	public static void getFromDatosFacturacionTraduccion(StringBuilder fromDFT) {
		// 51
		fromDFT.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// 53
		fromDFT.append(DaoConstants.JOIN + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		fromDFT.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_053);
		fromDFT.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_053);
		// 97
		fromDFT.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_97 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		fromDFT.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ANYO_097);
		fromDFT.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_EXP_097);
		fromDFT.append(DaoConstants.AND + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.IND_REVISADO_097
				+ DaoConstants.SIGNOIGUAL + DaoConstants.SIGNO_APOSTROFO + ActivoEnum.SI.getValue()
				+ DaoConstants.SIGNO_APOSTROFO);
		// 10
		fromDFT.append(DaoConstants.JOIN + DBConstants.TABLA_10 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		fromDFT.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_RELEVANCIA_053
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_TIPO_RELEVANCIA_010);
		fromDFT.append(
				DaoConstants.AND + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ESTADO_010 + DaoConstants.SIGNOIGUAL
						+ DaoConstants.SIGNO_APOSTROFO + EstadoEnum.ALTA.getValue() + DaoConstants.SIGNO_APOSTROFO);
		// 09
		fromDFT.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_09 + DaoConstants.BLANK + DaoConstants.T5_MINUSCULA);
		fromDFT.append(DaoConstants.ON + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ID_IDIOMA_053
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ID_IDIOMA_009);
		fromDFT.append(
				DaoConstants.AND + DaoConstants.T5_MINUSCULA_PUNTO + DBConstants.ESTADO_009 + DaoConstants.SIGNOIGUAL
						+ DaoConstants.SIGNO_APOSTROFO + EstadoEnum.ALTA.getValue() + DaoConstants.SIGNO_APOSTROFO);
	}

	public static void getWhereDatosFacturacionTraduccion(EntradaDatosExpediente datosExpediente,
			List<Object> paramsDFT, StringBuilder whereDFT) {
		whereDFT.append(DaoConstants.WHERE_1_1);
		whereDFT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051,
				datosExpediente.getAnyo(), paramsDFT));
		whereDFT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051,
				datosExpediente.getNumExp(), paramsDFT));

	}

	public static void getSelectDatosEntidadContactoFacturacionTraduccion(Locale eu, Locale es,
			StringBuilder selectDECFT, ReloadableResourceBundleMessageSource msg) {
		/* ID058 */
		selectDECFT.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_058
				+ DaoConstants.BLANK + DBConstants.ID_058 + DaoConstants.SIGNO_COMA);
		/* ANYO */
		selectDECFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_058 + DaoConstants.BLANK
				+ DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		/* NUMEXP */
		selectDECFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_058 + DaoConstants.BLANK
				+ DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		/* TIPOENTIDADASOCIADA */
		selectDECFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_ASOC_058 + DaoConstants.BLANK
				+ DBConstants.TIPOENTIDADASOCIADA + DaoConstants.SIGNO_COMA);
		/* IDENTIDADASOCIADA */
		selectDECFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_ASOC_058 + DaoConstants.BLANK
				+ DBConstants.IDENTIDADASOCIADA + DaoConstants.SIGNO_COMA);
		/* DNICONTACTO */
		selectDECFT.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI_CONTACTO_058 + DaoConstants.BLANK
				+ DBConstants.DNICONTACTO + DaoConstants.SIGNO_COMA);
		/* ENTIDADDESCAMPES */
		selectDECFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES + DaoConstants.BLANK
				+ DBConstants.DESCENTIDADES + DaoConstants.SIGNO_COMA);
		/* ENTIDADDESCAMPEU */
		selectDECFT.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.DESC_AMP_EU + DaoConstants.BLANK
				+ DBConstants.DESCENTIDADEU + DaoConstants.SIGNO_COMA);
		/* NOMBRE */
		selectDECFT.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.NOMBRE_077 + DaoConstants.BLANK
				+ DBConstants.NOMBRE + DaoConstants.SIGNO_COMA);
		/* APEL1 */
		selectDECFT.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.APEL1_077 + DaoConstants.BLANK
				+ DBConstants.APEL1 + DaoConstants.SIGNO_COMA);
		/* APEL2 */
		selectDECFT.append(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.APEL2_077 + DaoConstants.BLANK
				+ DBConstants.APEL2 + DaoConstants.SIGNO_COMA);
		/* FACTURABLE */
		selectDECFT.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.IND_FACTURABLE_098 + DaoConstants.BLANK
				+ DBConstants.FACTURABLE + DaoConstants.SIGNO_COMA);
		/* IVA */
		selectDECFT.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.IND_IVA_098 + DaoConstants.BLANK
				+ DBConstants.IVA + DaoConstants.SIGNO_COMA);
		/* FACTURABLEDESCES */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_FACTURABLE_098 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURABLEDESCES
				+ DaoConstants.SIGNO_COMA);
		/* FACTURABLEDESCEU */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_FACTURABLE_098 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURABLEDESCEU
				+ DaoConstants.SIGNO_COMA);
		/* IVADESCES */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_IVA_098 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, es)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.IVADESCES + DaoConstants.SIGNO_COMA);
		/* IVADESCEU */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T6_MINUSCULA_PUNTO
				+ DBConstants.IND_IVA_098 + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.IVADESCEU + DaoConstants.SIGNO_COMA);
		/* CDIRNORA FACTURACION */
		selectDECFT.append(DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.DIR_NORA_098 + DaoConstants.BLANK
				+ DBConstants.CDIRNORA + DaoConstants.SIGNO_COMA);
		/* NUMFACTURA */
		selectDECFT.append(" f2." + DBConstants.ID_FACTURA_0A4 + DaoConstants.BLANK + DBConstants.NUMFACTURA
				+ DaoConstants.SIGNO_COMA);
		selectDECFT.append(" f2." + DBConstants.ID_FACTURA_0A4 + DaoConstants.BLANK + DBConstants.NUMFACTURA
				+ DaoConstants.SIGNO_COMA);
		/* CODCONCATENADO */
		selectDECFT.append("CASE WHEN  f2.T12_EJ_CONTABLE is null THEN '9999999999' ELSE ");
		selectDECFT.append(
				"SUBSTR(f2.T12_EJ_CONTABLE,3,2) || LPAD(f2.APP_ID_0A4,2,'0') || SUBSTR(f2.ID_LIQUIDACION_0A4,3,6) END CODCONCATENADO ");
		selectDECFT.append(DaoConstants.SIGNO_COMA);
		/* FACTURADODESCES */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.ID_FACTURA_0A4
				+ DaoConstants.SIGNO_COMA + DaoConstants.NULL + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURADODESCES
				+ DaoConstants.SIGNO_COMA);
		/* FACTURADODESCEU */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.ID_FACTURA_0A4
				+ DaoConstants.SIGNO_COMA + DaoConstants.NULL + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.FACTURADODESCEU
				+ DaoConstants.SIGNO_COMA);
		/* PAGADODESCES */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL
				+ DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.T00_ESTADO_ID + DaoConstants.SIGNO_COMA
				+ Constants.CERO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA + Constants.DOS
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, es) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.PAGADODESCES + DaoConstants.SIGNO_COMA);
		/* PAGADODESCEU */
		selectDECFT.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL
				+ DaoConstants.ABRIR_PARENTESIS + " f2." + DBConstants.T00_ESTADO_ID + DaoConstants.SIGNO_COMA
				+ Constants.CERO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_COMA + Constants.DOS
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.SI.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
				+ msg.getMessage(ActivoEnum.NO.getLabel(), null, eu) + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.CERRAR_PARENTESIS + DBConstants.PAGADODESCEU);

	}

	public static void getFromDatosEntidadContactoFacturacionTraduccion(StringBuilder fromDECFT) {
		// 58
		fromDECFT.append(DaoConstants.FROM + DBConstants.TABLA_58 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// X54JAPI_ENTIDADES_SOLIC
		fromDECFT.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK
				+ DaoConstants.T2_MINUSCULA);
		fromDECFT.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.CODIGO + DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.ID_ENTIDAD_ASOC_058 + DaoConstants.AND + DaoConstants.T2_MINUSCULA_PUNTO
				+ DBConstants.TIPO + DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO
				+ DBConstants.TIPO_ENTIDAD_ASOC_058 + DaoConstants.CERRAR_PARENTESIS);
		// 98
		fromDECFT
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_98 + DaoConstants.BLANK + DaoConstants.T6_MINUSCULA);
		fromDECFT.append(DaoConstants.ON + DaoConstants.T6_MINUSCULA_PUNTO + DBConstants.ID_098
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_058);
		// 77
		fromDECFT
				.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		fromDECFT.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.DNI_077
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.DNI_CONTACTO_058);
		// A5
		fromDECFT.append(DaoConstants.LEFT_JOIN).append(" ( ");
		fromDECFT.append(" SELECT t10.ANYO_0A5, ");
		fromDECFT.append(" t10.NUM_EXP_0A5, ");
		fromDECFT.append(" t9.TIPO_ENTIDAD_ASOC_0A4, ");
		fromDECFT.append(" t9.ID_ENTIDAD_ASOC_0A4, ");
		fromDECFT.append(" t9.DNI_CONTACTO_0A4, ");
		fromDECFT.append(" t9.ID_FACTURA_0A4, ");
		fromDECFT.append(" t9.app_id_0a4, ");
		fromDECFT.append(" t9.id_liquidacion_0a4, ");
		fromDECFT.append(" w2.t12_ej_contable, ");
		fromDECFT.append(" w2.T00_ESTADO_ID ");
		fromDECFT.append(" FROM AA79BA5S01 t10 ");
		fromDECFT.append(" JOIN AA79BA4S01 t9 ");
		fromDECFT.append(" ON t9.ID_FACTURA_0A4 = t10.ID_FACTURA_0A5 ");
		fromDECFT.append(" LEFT JOIN W0512S01 w2 ");
		fromDECFT.append(" ON t9.ID_LIQUIDACION_0A4 = w2.T12_REFERENCIA ");

	}

	public static void getWhereDatosEntidadContactoFacturacionTraduccion(EntradaDatosExpediente datosExpediente,
			List<Object> paramsDECFT, StringBuilder whereDECFT) {
		whereDECFT.append(DaoConstants.WHERE);
		whereDECFT.append(" ( ( t9.ID_LIQUIDACION_0A4 IS NOT NULL ");
		whereDECFT.append(" AND w2.T00_ESTADO_ID <>4 ) ");
		whereDECFT.append(" OR (t9.ID_LIQUIDACION_0A4 IS NULL)) ");
		whereDECFT.append(" OR (t9.ID_LIQUIDACION_0A4 IS NULL) ");
		whereDECFT.append(SqlUtils.generarWhereIgual(DaoConstants.T10_MINUSCULA_PUNTO + DBConstants.ANYO_0A5,
				datosExpediente.getAnyo(), paramsDECFT));
		whereDECFT.append(SqlUtils.generarWhereIgual(DaoConstants.T10_MINUSCULA_PUNTO + DBConstants.NUM_EXP_0A5,
				datosExpediente.getNumExp(), paramsDECFT));
		whereDECFT.append(" ) f2 ");
		whereDECFT.append(" ON t1.ANYO_058 = f2.ANYO_0A5 ");
		whereDECFT.append(" AND t1.NUM_EXP_058 = f2.NUM_EXP_0A5 ");
		whereDECFT.append(" AND f2.TIPO_ENTIDAD_ASOC_0A4 = t1.TIPO_ENTIDAD_ASOC_058 ");
		whereDECFT.append(" AND f2.ID_ENTIDAD_ASOC_0A4 = t1.ID_ENTIDAD_ASOC_058 ");
		whereDECFT.append(" AND nvl(f2.DNI_CONTACTO_0A4,-1) = nvl(t1.DNI_CONTACTO_058,-1) ");
		whereDECFT.append(" WHERE 1 =1 ");
		whereDECFT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_058,
				datosExpediente.getAnyo(), paramsDECFT));
		whereDECFT.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_058,
				datosExpediente.getNumExp(), paramsDECFT));
		whereDECFT.append(DaoConstants.ORDER_BY + DBConstants.ID_058 + DaoConstants.ASC);
	}

}
