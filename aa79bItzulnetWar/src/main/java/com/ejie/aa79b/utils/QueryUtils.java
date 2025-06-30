package com.ejie.aa79b.utils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.common.formatter.Aa79BFormatMoneda;
import com.ejie.aa79b.dao.AnulacionesDaoImpl;
import com.ejie.aa79b.dao.TareasDaoImpl;
import com.ejie.aa79b.model.Albaran;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoAlbaranEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoFacturaEnum;
import com.ejie.aa79b.model.enums.EstadoRequerimientoEnum;
import com.ejie.aa79b.model.enums.EstadoTareasAsignadasEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.MotivosAnulacionEnum;
import com.ejie.aa79b.model.enums.RequiereTradosEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;

public final class QueryUtils extends SpringBeanAutowiringSupport {

	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;

	private NumberFormat formatMoneda = new Aa79BFormatMoneda();

	/**
	 * Constructor
	 */
	public QueryUtils() {
		// Constructor
	}

	/**
	 */
	public static String getWhereExpAAnular() {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(getQueryPlazoSubsanacionExpirado(new Long(-1)));
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.OR);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(getQueryExpPdteTramitacionClte());
		query.append(DaoConstants.AND);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(getQueryPlazoExpirado());
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.OR);
		query.append(getQuerySubsanacionRechazada());
		query.append(DaoConstants.CERRAR_PARENTESIS);

		return query.toString();
	}

	/**
	 */
	public static String getQuerySubsanacionRechazada() {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(" ESTADO_ACTUAL.ID_ESTADO_EXP_059 ");
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(EstadoExpedienteEnum.EN_CURSO.getValue());
		query.append(DaoConstants.AND);
		query.append(DBConstants.TIPO_REQUERIMIENTO_064);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue());
		query.append(DaoConstants.AND);
		query.append(DBConstants.ESTADO_SUBSANACION_064);
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(EstadoRequerimientoEnum.RECHAZADA.getValue());
		query.append(DaoConstants.CERRAR_PARENTESIS);

		return query.toString();
	}

	/**
	 */
	public static String getQueryExpPdteTramitacionClte() {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append("ESTADO_ACTUAL.ID_ESTADO_EXP_059");
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(EstadoExpedienteEnum.EN_CURSO.getValue());
		query.append(DaoConstants.AND);
		query.append("ESTADO_ACTUAL.ID_FASE_EXPEDIENTE_059");
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue());
		query.append(DaoConstants.CERRAR_PARENTESIS);

		return query.toString();
	}

	/**
	 * @param motivoAnulacion
	 */
	public static String getQueryPlazoSubsanacionExpirado(Long motivoAnulacion) {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append("ESTADO_ACTUAL.ID_ESTADO_EXP_059");
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(EstadoExpedienteEnum.EN_ESTUDIO.getValue());
		query.append(DaoConstants.AND);
		query.append("ESTADO_ACTUAL.ID_FASE_EXPEDIENTE_059");
		query.append(DaoConstants.SIGNOIGUAL);
		query.append(FaseExpedienteEnum.PDTE_TRAM_SUBSANACION.getValue());
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AND);
		query.append(getQueryPlazoExpirado(motivoAnulacion));

		return query.toString();
	}

	/**
	 *
	 */
	public static String getQueryPlazoExpirado() {
		StringBuilder subQuery = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		subQuery.append(DaoConstants.ABRIR_PARENTESIS);
		subQuery.append(DBConstants.IND_SUBSANADO_064);
		subQuery.append(DaoConstants.SIGNOIGUAL);
		subQuery.append(DaoConstants.SIGNO_APOSTROFO);
		subQuery.append(Constants.NO);
		subQuery.append(DaoConstants.SIGNO_APOSTROFO);
		subQuery.append(DaoConstants.AND);
		subQuery.append(DBConstants.ESTADO_SUBSANACION_064);
		subQuery.append(DaoConstants.SIGNOIGUAL);
		subQuery.append(EstadoRequerimientoEnum.PENDIENTE.getValue());
		subQuery.append(DaoConstants.AND);
		subQuery.append(DBConstants.FECHA_LIMITE_064);
		subQuery.append(DaoConstants.SIGNO_MENOR_QUE);
		subQuery.append(DaoConstants.SYSDATE);
		subQuery.append(DaoConstants.CERRAR_PARENTESIS);

		return subQuery.toString();
	}

	/**
	 *
	 */
	public static String getQueryPlazoExpirado(Long motivoAnulacion) {
		StringBuilder subQuery = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		subQuery.append(DaoConstants.ABRIR_PARENTESIS);
		subQuery.append(DBConstants.IND_SUBSANADO_064);
		subQuery.append(DaoConstants.SIGNOIGUAL);
		subQuery.append(DaoConstants.SIGNO_APOSTROFO);
		subQuery.append(Constants.NO);
		subQuery.append(DaoConstants.SIGNO_APOSTROFO);
		subQuery.append(DaoConstants.AND);
		subQuery.append(DBConstants.ESTADO_SUBSANACION_064);
		subQuery.append(DaoConstants.SIGNOIGUAL);
		subQuery.append(EstadoRequerimientoEnum.PENDIENTE.getValue());
		// si viene a -1 no introducimos el tipo de requerimiento --
		if (motivoAnulacion != -1) {
			subQuery.append(DaoConstants.AND);
			subQuery.append(" TIPO_REQUERIMIENTO_064 IN ( ");
			subQuery.append(getTiposReqConMotivoAnulacion(motivoAnulacion));
			subQuery.append(" ) ");
		}
		subQuery.append(DaoConstants.AND);
		subQuery.append(DBConstants.FECHA_LIMITE_064);
		subQuery.append(DaoConstants.SIGNO_MENOR_QUE);
		subQuery.append(DaoConstants.SYSDATE);
		subQuery.append(DaoConstants.CERRAR_PARENTESIS);

		return subQuery.toString();
	}

	public static String getTiposReqConMotivoAnulacion(Long motivoAnulacion) {
		StringBuilder tiposReq = new StringBuilder();
		if (MotivosAnulacionEnum.PLAZO_SUBS_EXPIRADO.getValue() == motivoAnulacion) {
			tiposReq.append(TipoRequerimientoEnum.SUBSANACION.getValue());
		} else if (MotivosAnulacionEnum.PRESUP_NO_ACEPTADO_PLAZO.getValue() == motivoAnulacion) {
			tiposReq.append(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() + " , "
					+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue());
		} else if (MotivosAnulacionEnum.FECHA_PROP_NO_ACEPTADA_PLAZO.getValue() == motivoAnulacion) {
			tiposReq.append(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue());
		}
		return tiposReq.toString();
	}

	/**
	 *
	 * @param resultSet    ResultSet
	 * @param tieneGroupBy boolean
	 * @return DocumentoTarea
	 * @throws SQLException e
	 */
	public DocumentoTarea getDocumentoTareaXliffRwMp(ResultSet resultSet, boolean tieneGroupBy) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();
		if (tieneGroupBy) {
			documentoTarea.setGroupBy(resultSet.getString(DBConstants.GROUPBYTEXT));
			documentoTarea.setIdTareaAgrupacion(resultSet.getBigDecimal(DBConstants.IDTAREAAGRUPACION));
			documentoTarea.setFechaEjecucionTarea(resultSet.getDate(DBConstants.FECHAEJECUCIONTAREA));
		}
		documentoTarea.setIdTarea(resultSet.getBigDecimal("ID_TAREA_096"));
		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal("ID_FICHERO_XLIFF_096"));
		documentoTareaAdjunto.setEncriptado(resultSet.getString("IND_ENCRIPTADO_088"));
		documentoTareaAdjunto.setOid(resultSet.getString("OID_FICHERO_088"));
		documentoTareaAdjunto.setTitulo(resultSet.getString("TITULO_FICHERO_088"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);
		return documentoTarea;
	}

	public void obtenerEstadoTareasDesc(ExpedientePlanificacion expPlanificacion) {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		String labelEstado = "";
		if (EstadoTareasAsignadasEnum.ACEPTADAS.getValue() == expPlanificacion.getIdEstadoTareasAsignadas()) {
			labelEstado = EstadoTareasAsignadasEnum.ACEPTADAS.getLabel();
		} else if (EstadoTareasAsignadasEnum.PENDIENTE_ACEPTACION.getValue() == expPlanificacion
				.getIdEstadoTareasAsignadas()) {
			labelEstado = EstadoTareasAsignadasEnum.PENDIENTE_ACEPTACION.getLabel();
		} else if (EstadoTareasAsignadasEnum.RECHAZADAS.getValue() == expPlanificacion.getIdEstadoTareasAsignadas()) {
			labelEstado = EstadoTareasAsignadasEnum.RECHAZADAS.getLabel();
		} else if (EstadoTareasAsignadasEnum.PENDIENTE_ASIGNACION.getValue() == expPlanificacion
				.getIdEstadoTareasAsignadas()) {
			labelEstado = EstadoTareasAsignadasEnum.PENDIENTE_ASIGNACION.getLabel();
		} else {
			// sin tareas asignadas
			labelEstado = EstadoTareasAsignadasEnum.SIN_TAREAS.getLabel();
		}
		expPlanificacion.setEstadoTareasAsignadasEs(this.messageSource.getMessage(labelEstado, null, es));
		expPlanificacion.setEstadoTareasAsignadasEu(this.messageSource.getMessage(labelEstado, null, eu));

	}

	public void obtenerRequiereProyectoTradosDesc(ExpedientePlanificacion expPlanificacion) {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		String labelTradosEs = "";
		String labelTradosEu = "";
		if (RequiereTradosEnum.NO_REQUIERE_TRADOS.getValue() == expPlanificacion.getExpedienteTradRev()
				.getReqProyectoTrados()) {
			labelTradosEs = this.messageSource.getMessage(RequiereTradosEnum.NO_REQUIERE_TRADOS.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(RequiereTradosEnum.NO_REQUIERE_TRADOS.getLabel(), null, eu);
		} else if (RequiereTradosEnum.SIN_PROYECTO_TRADOS.getValue() == expPlanificacion.getExpedienteTradRev()
				.getReqProyectoTrados()) {
			labelTradosEs = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, es) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.SIN_PROYECTO_TRADOS.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.SIN_PROYECTO_TRADOS.getLabel(), null, eu);
		} else if (RequiereTradosEnum.PDTE_ASIGNACION.getValue() == expPlanificacion.getExpedienteTradRev()
				.getReqProyectoTrados()) {
			labelTradosEs = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, es) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.PDTE_ASIGNACION.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.PDTE_ASIGNACION.getLabel(), null, eu);
		} else if (RequiereTradosEnum.PDTE_EJECUCION.getValue() == expPlanificacion.getExpedienteTradRev()
				.getReqProyectoTrados()) {
			labelTradosEs = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, es) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.PDTE_EJECUCION.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.PDTE_EJECUCION.getLabel(), null, eu);
		} else if (RequiereTradosEnum.PDTE_RETRASADA.getValue() == expPlanificacion.getExpedienteTradRev()
				.getReqProyectoTrados()) {
			labelTradosEs = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, es) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.PDTE_RETRASADA.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.PDTE_RETRASADA.getLabel(), null, eu);
		} else if (RequiereTradosEnum.TRADOS_EJECUTADA.getValue() == expPlanificacion.getExpedienteTradRev()
				.getReqProyectoTrados()) {
			labelTradosEs = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, es) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.TRADOS_EJECUTADA.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu) + " - "
					+ this.messageSource.getMessage(RequiereTradosEnum.TRADOS_EJECUTADA.getLabel(), null, eu);
		} else {
			labelTradosEs = this.messageSource.getMessage(RequiereTradosEnum.NO_REQUIERE_TRADOS.getLabel(), null, es);
			labelTradosEu = this.messageSource.getMessage(RequiereTradosEnum.NO_REQUIERE_TRADOS.getLabel(), null, eu);
		}
		expPlanificacion.getExpedienteTradRev().setReqProyectoTradosDescEs(labelTradosEs);
		expPlanificacion.getExpedienteTradRev().setReqProyectoTradosDescEu(labelTradosEu);
	}

	public void obtenerRequierePresupuestoDesc(ExpedientePlanificacion expPlanificacion) {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		String labelPresupuestoEs = "";
		String labelPresupuestoEu = "";
		if (ActivoEnum.SI.getValue().equals(expPlanificacion.getExpedienteTradRev().getIndPresupuesto())) {
			labelPresupuestoEs = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, es);
			labelPresupuestoEu = this.messageSource.getMessage(ActivoEnum.SI.getLabel(), null, eu);
			if (expPlanificacion.getExpedienteTradRev().getEstadoPresupuestoDescEu() != null) {
				labelPresupuestoEu += Constants.COMA_ESPACIO
						+ expPlanificacion.getExpedienteTradRev().getEstadoPresupuestoDescEu();
				labelPresupuestoEs += Constants.COMA_ESPACIO
						+ expPlanificacion.getExpedienteTradRev().getEstadopresupuestoDescEs();
			}
			if (expPlanificacion.getExpedienteTradRev().getFechaLimitePresupuesto() != null) {
				labelPresupuestoEu += Constants.ESPACIO + DateUtils.obtFechaHoraFormateada(
						expPlanificacion.getExpedienteTradRev().getFechaLimitePresupuesto(), "", eu);
				labelPresupuestoEs += Constants.ESPACIO + DateUtils.obtFechaHoraFormateada(
						expPlanificacion.getExpedienteTradRev().getFechaLimitePresupuesto(), "", es);
			}
		} else {
			labelPresupuestoEs = this.messageSource.getMessage(ActivoEnum.NO.getLabel(), null, es);
			labelPresupuestoEu = this.messageSource.getMessage(ActivoEnum.NO.getLabel(), null, eu);
		}
		expPlanificacion.getExpedienteTradRev().setReqPresupuestoDescEs(labelPresupuestoEs);
		expPlanificacion.getExpedienteTradRev().setReqPresupuestoDescEu(labelPresupuestoEu);
	}

	/**
	 * @return
	 */
	public static String getJoinTabla51And59() {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		query.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_059);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_059);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ESTADO_BITACORA_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_ESTADO_BITACORA_059);

		return query.toString();
	}

	/**
	 * @return
	 */
	public static String getJoinTabla59And60() {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.JOIN + DBConstants.TABLA_60 + DaoConstants.BLANK + DaoConstants.T5_MINUSCULA);
		query.append(
				DaoConstants.ON + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ESTADO_EXP_059);
		query.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_060);

		return query.toString();
	}

	public DatosPagoProveedores getDatosPagoProveedorTareas(ResultSet resultSet, boolean esExcel) throws SQLException {
		DatosPagoProveedores datosPagoProveedores = new DatosPagoProveedores();

		Locale locale = LocaleContextHolder.getLocale();

		datosPagoProveedores.setAnyo(resultSet.getLong("ANYO"));
		datosPagoProveedores.setNumExpediente(resultSet.getInt("NUMEXP"));
		datosPagoProveedores.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		datosPagoProveedores.setIdTipTarea(resultSet.getInt("ID_TIPO_TAREA_081"));
		datosPagoProveedores.setNumTotalPal(resultSet.getInt("numTotalPal"));
		datosPagoProveedores.setNumPalConcor084(resultSet.getInt("NUM_PAL_CONCOR_0_84_094"));
		datosPagoProveedores.setNumPalConcor8594(resultSet.getInt("NUM_PAL_CONCOR_85_94_094"));
		datosPagoProveedores.setNumPalConcor95100(resultSet.getInt("NUM_PAL_CONCOR_95_100_094"));
		datosPagoProveedores.setIndRecargoFormato(resultSet.getString("IND_RECARGO_FORMATO_094"));
		datosPagoProveedores.setNumPalRecargoFormato(resultSet.getInt("NUM_PAL_RECARGO_FORMATO_094"));
		datosPagoProveedores.setIndRecargoApremio(resultSet.getString("IND_RECARGO_APREMIO_094"));
		datosPagoProveedores.setPorRecargoApremio(resultSet.getLong("POR_RECARGO_APREMIO_094"));
		datosPagoProveedores.setIndPenalizacion(resultSet.getString("IND_PENALIZACION_094"));
		datosPagoProveedores.setPorPenalizacion(resultSet.getLong("POR_PENALIZACION_094"));
		datosPagoProveedores.setPorPenalizacionCalidad(resultSet.getLong("PORPENALCALIDAD"));
		datosPagoProveedores.setIvaAplic(resultSet.getInt("IVAAPLIC"));
		datosPagoProveedores.setImportePalAplic(resultSet.getBigDecimal("IMPORTEPALAPLIC"));
		datosPagoProveedores.setImporteTarea(resultSet.getBigDecimal("IMPORTETAREA"));
		datosPagoProveedores.setImporteRecargoFormato(resultSet.getBigDecimal("IMPORTERECARGOFORMATO"));
		// Formateo aquí para no usar resourcebundle en el get del modelo
		StringBuilder sb = new StringBuilder(4096);
		if (datosPagoProveedores.getImporteRecargoFormato().compareTo(BigDecimal.ZERO) != 0) {
			sb.append(GeneralUtils.getFormateoMoneda(datosPagoProveedores.getImporteRecargoFormato()) + " ("
					+ datosPagoProveedores.getNumPalRecargoFormato() + " "
					+ this.messageSource.getMessage("label.palabras", null, locale) + ")");
		} else {
			sb.append(GeneralUtils.getFormateoMoneda(datosPagoProveedores.getImporteRecargoFormato()));
		}
		datosPagoProveedores.setDescImporteRecargoFormato(sb.toString());

		datosPagoProveedores.setImporteRecargoApremio(resultSet.getBigDecimal("IMPORTERECARGOAPREMIO"));
		datosPagoProveedores.setImportePenalizacion(resultSet.getBigDecimal("IMPORTEPENALIZACION"));
		datosPagoProveedores.setImportePenalCalidad(resultSet.getBigDecimal("IMPPENALCALIDAD"));
		datosPagoProveedores.setImporteBase(resultSet.getBigDecimal("IMPORTEBASE"));
		datosPagoProveedores.setImporteIva(resultSet.getBigDecimal("IMPORTEIVA"));
		datosPagoProveedores.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
		String label = "";
		if (datosPagoProveedores.getIdTipTarea() == TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()) {
			label = TipoTareaGestionAsociadaEnum.TRADUCIR.getLabel();
		} else if (datosPagoProveedores.getIdTipTarea() == TipoTareaGestionAsociadaEnum.REVISAR.getValue()) {
			label = TipoTareaGestionAsociadaEnum.REVISAR.getLabel();
		}

		datosPagoProveedores.setIndFacturable(resultSet.getString("INDFACTURABLE"));
		datosPagoProveedores.setFacturableDescEu(resultSet.getString("FACTURABLEDESCEU"));
		datosPagoProveedores.setFacturableDescEs(resultSet.getString("FACTURABLEDESCES"));

		datosPagoProveedores.setIndAlbaran(resultSet.getString("INDALBARAN"));
		datosPagoProveedores.setDescIndAlbaran(resultSet.getString("INDALBARANDESCEU"));

		String desc = this.messageSource.getMessage(label, null, locale);
		datosPagoProveedores.setDescTipTarea(desc);

		if (esExcel) {
			this.getDatosPagoProveedorTareasDatosExcel(resultSet, datosPagoProveedores, locale);

		}

		return datosPagoProveedores;
	}

	public DatosPagoProveedores getDatosPagoProveedorConsultaTradRev(ResultSet resultSet) throws SQLException {

		Locale locale = LocaleContextHolder.getLocale();

		DatosPagoProveedores datosPagoProveedoresConsultaTradRev = new DatosPagoProveedores();
		datosPagoProveedoresConsultaTradRev.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		datosPagoProveedoresConsultaTradRev.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosPagoProveedoresConsultaTradRev.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosPagoProveedoresConsultaTradRev.setPorIva(resultSet.getLong(DBConstants.PORIVA));
		datosPagoProveedoresConsultaTradRev.setImportePalAplic(resultSet.getBigDecimal(DBConstants.IMPORTEPALAPLIC));
		datosPagoProveedoresConsultaTradRev
				.setImportePenalizacion(resultSet.getBigDecimal(DBConstants.IMPORTEPENALIZACION));
		datosPagoProveedoresConsultaTradRev
				.setImporteRecargoApremio(resultSet.getBigDecimal(DBConstants.IMPORTERECARGOAPREMIO));
		datosPagoProveedoresConsultaTradRev
				.setImporteRecargoFormato(resultSet.getBigDecimal(DBConstants.IMPORTERECARGOFORMATO));

		datosPagoProveedoresConsultaTradRev.setImporteTarea(resultSet.getBigDecimal(DBConstants.IMPORTETAREA));
		datosPagoProveedoresConsultaTradRev.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		datosPagoProveedoresConsultaTradRev.setIndPenalizacion(resultSet.getString(DBConstants.INDPENALIZACION));
		datosPagoProveedoresConsultaTradRev.setIndRecargoApremio(resultSet.getString(DBConstants.INDRECARGOAPREMIO));
		datosPagoProveedoresConsultaTradRev.setIndRecargoFormato(resultSet.getString(DBConstants.INDRECARGOFORMATO));
		datosPagoProveedoresConsultaTradRev.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosPagoProveedoresConsultaTradRev.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosPagoProveedoresConsultaTradRev.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosPagoProveedoresConsultaTradRev.setNumPalRecargoFormato(resultSet.getInt(DBConstants.NUMPALRECARGOFORMATO));
		datosPagoProveedoresConsultaTradRev.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPAL));
		datosPagoProveedoresConsultaTradRev.setPorPenalizacion(resultSet.getLong(DBConstants.PORPENALIZACION));
		datosPagoProveedoresConsultaTradRev.setPorRecargoApremio(resultSet.getLong(DBConstants.PORRECARGOAPREMIO));
		datosPagoProveedoresConsultaTradRev.setIndAlbaran(resultSet.getString(DBConstants.INDALBARAN));
		datosPagoProveedoresConsultaTradRev.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		datosPagoProveedoresConsultaTradRev
				.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		datosPagoProveedoresConsultaTradRev
				.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		// Formateo aquí para no usar resourcebundle en el get del modelo
		StringBuilder sb = new StringBuilder(4096);
		if (datosPagoProveedoresConsultaTradRev.getImporteRecargoFormato().compareTo(BigDecimal.ZERO) != 0) {
			sb.append(GeneralUtils.getFormateoMoneda(datosPagoProveedoresConsultaTradRev.getImporteRecargoFormato())
					+ " (" + datosPagoProveedoresConsultaTradRev.getNumPalRecargoFormato() + " "
					+ this.messageSource.getMessage("label.palabras", null, locale) + ")");
		} else {
			sb.append(GeneralUtils.getFormateoMoneda(datosPagoProveedoresConsultaTradRev.getImporteRecargoFormato()));
		}
		datosPagoProveedoresConsultaTradRev.setDescImporteRecargoFormato(sb.toString());
		return datosPagoProveedoresConsultaTradRev;
	}

	/**
	 * @param resultSet
	 * @param datosPagoProveedores
	 * @param locale
	 * @throws SQLException
	 */
	private void getDatosPagoProveedorTareasDatosExcel(ResultSet resultSet, DatosPagoProveedores datosPagoProveedores,
			Locale locale) throws SQLException {
		String label;
		String desc;

		if (locale.getLanguage().equalsIgnoreCase(Constants.LANG_CASTELLANO)) {
			datosPagoProveedores.setEmpresaProvedora(resultSet.getString("DESC_ES"));
		} else {
			datosPagoProveedores.setEmpresaProvedora(resultSet.getString("DESC_EU"));
		}

		Albaran alb = new Albaran();
		alb.setIdAlbaran(resultSet.getBigDecimal("ID_099"));
		alb.setRefAlbaran(resultSet.getString("REF_ALBARAN_099"));
		alb.setFechaAlta(resultSet.getDate("FECHA_ALTA_099"));
		alb.setEstado(resultSet.getInt("ESTADO_099"));

		label = "";
		if (alb.getEstado() == EstadoAlbaranEnum.PENDIENTE_ASOCIAR_ALBARAN.getValue()) {
			label = EstadoAlbaranEnum.PENDIENTE_ASOCIAR_ALBARAN.getLabel();
		} else if (alb.getEstado() == EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getValue()) {
			label = EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getLabel();
		} else if (alb.getEstado() == EstadoAlbaranEnum.ENVIADO_IZO.getValue()) {
			label = EstadoAlbaranEnum.ENVIADO_IZO.getLabel();
		} else if (alb.getEstado() == EstadoAlbaranEnum.PAGADO.getValue()) {
			label = EstadoAlbaranEnum.PAGADO.getLabel();
		}

		desc = this.messageSource.getMessage(label, null, locale);
		alb.setDescEstado(desc);

		alb.setDescLote(resultSet.getString("NOMBRE_LOTE_029"));

		datosPagoProveedores.setBopv(resultSet.getString("IND_PUBLICAR_BOPV_053"));
		if (datosPagoProveedores.getBopv().equalsIgnoreCase(Constants.SI)) {
			datosPagoProveedores.setDescBOPV(this.messageSource.getMessage("comun.si", null, locale));
		} else {
			datosPagoProveedores.setDescBOPV(this.messageSource.getMessage("comun.no", null, locale));
		}
		if (locale.getLanguage().equalsIgnoreCase(Constants.LANG_CASTELLANO)) {
			datosPagoProveedores.setIdiomaDestino(resultSet.getString("DESC_IDIOMA_ES_009"));
		} else {
			datosPagoProveedores.setIdiomaDestino(resultSet.getString("DESC_IDIOMA_EU_009"));
		}

		datosPagoProveedores.setTarifa(resultSet.getBigDecimal("IMPORTEPALAPLIC"));

		datosPagoProveedores.setIndAlbaran(resultSet.getString("IND_ALBARAN_094"));
		if (datosPagoProveedores.getIndAlbaran().equals(Constants.SI)) {
			datosPagoProveedores.setDescIndAlbaran(this.messageSource.getMessage("comun.si", null, locale));
		} else {
			datosPagoProveedores.setDescIndAlbaran(this.messageSource.getMessage("comun.no", null, locale));
		}

		datosPagoProveedores.setAlbaran(alb);
	}

	public static String getWhereActDatosFacturacion(List<Object> params) {
		return getWhereActDatosFacturacion(params, DaoConstants.T1_MINUSCULA);
	}

	public static String getWhereActDatosFactEntidades(List<Object> params) {
		return getWhereActDatosFacturacion(params, DaoConstants.S1_MINUSCULA);
	}

	public static String getWhereActDatosFacturacion(List<Object> params, String idTabla51) {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.WHERE_1_1);
		query.append(DaoConstants.AND + DaoConstants.NOT + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.BLANK + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059
				+ DaoConstants.BLANK + DaoConstants.SIGNOIGUAL + EstadoExpedienteEnum.CERRADO.getValue());
		query.append(SqlUtils.generarWhereIgual(DaoConstants.BLANK + DaoConstants.B1_MINUSCULA_PUNTO
				+ DBConstants.ID_FASE_EXPEDIENTE_059 + DaoConstants.BLANK,
				FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue(), params));
		query.append(DaoConstants.CERRAR_PARENTESIS);
		// release/v3.9.40 descarte de expedientes en estado ANULADO
		query.append(" AND NOT b1.ID_ESTADO_EXP_059 = " + EstadoExpedienteEnum.ANULADO.getValue() + " ");
		query.append(DaoConstants.AND + DaoConstants.NOT + DaoConstants.EXISTS + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.SELECT + DBConstants.ID_FACTURA_0A4 + DaoConstants.FROM + DBConstants.TABLA_A4);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_A5 + DaoConstants.ON);
		query.append(DBConstants.ANYO_0A5 + DaoConstants.SIGNOIGUAL + idTabla51 + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ANYO_051);
		query.append(DaoConstants.AND + DBConstants.NUM_EXP_0A5 + DaoConstants.SIGNOIGUAL + idTabla51
				+ DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		query.append(
				DaoConstants.AND + DBConstants.ID_FACTURA_0A5 + DaoConstants.SIGNOIGUAL + DBConstants.ID_FACTURA_0A4);
		query.append(DaoConstants.JOIN + DBConstants.VISTAW05B12 + DaoConstants.BLANK + DaoConstants.T12_MINUSCULA
				+ DaoConstants.ON);
		query.append(DBConstants.ID_LIQUIDACION_0A4 + DaoConstants.SIGNOIGUAL + DaoConstants.T12_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.T12_REFERENCIA);
		query.append(DaoConstants.WHERE_1_1);
		query.append(
				SqlUtils.generarWhereDistinto(" T12.T00_ESTADO_ID ", EstadoFacturaEnum.ANULADA.getValue(), params));
		query.append(DaoConstants.CERRAR_PARENTESIS);

		return query.toString();
	}

	/**
	 * @return String
	 */
	public static String getJoinTabla59and51() {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.B1_MINUSCULA);
		query.append(
				DaoConstants.ON + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.ANYO_051 + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.ANYO_059);
		query.append(
				DaoConstants.AND + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_059);
		query.append(DaoConstants.AND + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.ESTADO_BITACORA_051
				+ DaoConstants.SIGNOIGUAL);
		query.append(DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_BITACORA_059);

		return query.toString();
	}

	public Expediente getExpedienteRwMp(ResultSet resultSet, boolean tieneTareasEntrega) throws SQLException {
		Expediente expediente = new Expediente();
		expediente.setAnyo(resultSet.getLong("ANYO_051"));
		expediente.setNumExp(resultSet.getInt("NUM_EXP_051"));
		expediente.setIdTipoExpediente(resultSet.getString("ID_TIPO_EXPEDIENTE_051"));
		expediente.setTipoExpedienteDescEs(resultSet.getString("TIPO_EXPEDIENTE_ES"));
		expediente.setTipoExpedienteDescEu(resultSet.getString("TIPO_EXPEDIENTE_EU"));
		expediente.setTipoExpedienteDescEsPDF(resultSet.getString("TIPOEXPEDIENTEDESCES"));
		expediente.setTipoExpedienteDescEuPDF(resultSet.getString("TIPOEXPEDIENTEDESCEU"));
		expediente.setOrigen(resultSet.getString("ORIGEN_051"));
		expediente.setAplicacionOrigen(resultSet.getString("APLIC_ORIGEN_051"));
		expediente.setFechaAlta(resultSet.getDate("FECHA_ALTA_051"));
		expediente.setHoraAlta(resultSet.getString("HORA_ALTA_051"));
		expediente.setTitulo(resultSet.getString("TITULO_051"));
		expediente.setEstadoBitacora(resultSet.getLong("ESTADO_BITACORA_051"));
		Persona tecnico = new Persona();
		tecnico.setDni(resultSet.getString("DNI_TECNICO_051"));
		tecnico.setNombre(resultSet.getString("NOMBRE_TECNICO_077"));
		tecnico.setApellido1(resultSet.getString("APEL1_TECNICO_077"));
		tecnico.setApellido2(resultSet.getString("APEL2_TECNICO_077"));
		expediente.setTecnico(tecnico);
		Persona asignador = new Persona();
		asignador.setDni(resultSet.getString("DNI_ASIGNADOR_051"));
		expediente.setAsignador(asignador);

		// ExpedienteInterpretacion
		ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();
		expedienteInterpretacion.setModoInterpretacion(resultSet.getLong("MODO_INTERPRETACION_052"));
		expedienteInterpretacion.setModoInterpretacionDescEs(resultSet.getString("DESC_ES_014"));
		expedienteInterpretacion.setModoInterpretacionDescEu(resultSet.getString("DESC_EU_014"));
		expedienteInterpretacion.setTipoActo(resultSet.getLong("TIPO_ACTO_052"));
		expedienteInterpretacion.setTipoActoDescEs(resultSet.getString("DESC_ES_008"));
		expedienteInterpretacion.setTipoActoDescEu(resultSet.getString("DESC_EU_008"));
		expedienteInterpretacion.setTipoPeticionario(resultSet.getString("TIPO_PETICIONARIO_052"));
		expedienteInterpretacion.setTipoPeticionarioDescEs(resultSet.getString("TIPOPETICIONARIODESCES"));
		expedienteInterpretacion.setTipoPeticionarioDescEu(resultSet.getString("TIPOPETICIONARIODESCEU"));
		expedienteInterpretacion.setIndProgramada(resultSet.getString("IND_PROGRAMADA_052"));
		expedienteInterpretacion.setProgramadaDescEs(resultSet.getString("INDPROGRAMADADESCES"));
		expedienteInterpretacion.setProgramadaDescEu(resultSet.getString("INDPROGRAMADADESCEU"));
		expedienteInterpretacion.setFechaIni(resultSet.getDate("FECHA_INI_052"));
		expedienteInterpretacion.setHoraIni(resultSet.getString("HORA_INI_052"));
		expedienteInterpretacion.setFechaFin(resultSet.getDate("FECHA_FIN_052"));
		expedienteInterpretacion.setHoraFin(resultSet.getString("HORA_FIN_052"));

		DireccionNora direccionNora = new DireccionNora();
		direccionNora.setDirNora(resultSet.getInt("CDIRNORA_049"));
		expedienteInterpretacion.setDirNora(direccionNora);

		expedienteInterpretacion.setIndPresupuesto(resultSet.getString("IND_PRESUPUESTO_052"));
		expedienteInterpretacion.setPresupuestoDescEs(resultSet.getString("PRESUPUESTODESCES"));
		expedienteInterpretacion.setPresupuestoDescEu(resultSet.getString("PRESUPUESTODESCEU"));
		expedienteInterpretacion.setPersonaContacto(resultSet.getString("PERSONA_CONTACTO_052"));
		expedienteInterpretacion.setEmailContacto(resultSet.getString("EMAIL_CONTACTO_052"));
		expedienteInterpretacion.setTelefonoContacto(resultSet.getString("TELEFONO_CONTACTO_052"));
		expedienteInterpretacion.setIndObservaciones(resultSet.getString("IND_OBSERVACIONES_052"));

		expediente.setExpedienteInterpretacion(expedienteInterpretacion);

		// ExpedienteTradRev
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setAnyo(expediente.getAnyo());
		expedienteTradRev.setNumExp(expediente.getNumExp());
		expedienteTradRev.setIndPublicarBopv(resultSet.getString("IND_PUBLICAR_BOPV_053"));
		expedienteTradRev.setPublicarBopvDescEs(resultSet.getString("PUBLICARBOPVDESCES"));
		expedienteTradRev.setPublicarBopvDescEu(resultSet.getString("PUBLICARBOPVDESCEU"));
		expedienteTradRev.setIndPrevistoBopv(resultSet.getString("IND_PREVISTO_BOPV_053"));
		expedienteTradRev.setIdIdioma(resultSet.getLong("ID_IDIOMA_053"));
		expedienteTradRev.setIndDificil(resultSet.getString("INDDIFICIL"));
		expedienteTradRev.setIdIdiomaDescEs(resultSet.getString("DESC_IDIOMA_ES_009"));
		expedienteTradRev.setIdIdiomaDescEu(resultSet.getString("DESC_IDIOMA_EU_009"));
		expedienteTradRev.setIndConfidencial(resultSet.getString("IND_CONFIDENCIAL_053"));
		expedienteTradRev.setIndCorredaccion(resultSet.getString("IND_CORREDACCION_053"));
		expedienteTradRev.setTexto(resultSet.getString("TEXTO_053"));
		expedienteTradRev.setTipoRedaccion(resultSet.getString("TIPO_REDACCION_053"));
		expedienteTradRev.setParticipantes(resultSet.getString("PARTICIPANTES_053"));
		expedienteTradRev.setRefTramitagune(resultSet.getString("REF_TRAMITAGUNE_053"));
		expedienteTradRev.setNumTotalPalIzo(resultSet.getInt("NUM_TOTAL_PAL_IZO_053"));
		expedienteTradRev.setNumTotalPalSolic(resultSet.getInt("NUM_TOTAL_PAL_SOLIC_053"));
		expedienteTradRev.setFechaFinalIzo(resultSet.getDate("FECHA_FINAL_IZO_053"));
		expedienteTradRev.setHoraFinalIzo(resultSet.getString("HORA_FINAL_IZO_053"));
		expedienteTradRev.setIndFacturable(resultSet.getString("IND_FACTURABLE_053"));
		expedienteTradRev.setIdRelevancia(resultSet.getLong("ID_RELEVANCIA_053"));
		expedienteTradRev.setIndUrgente(resultSet.getString("IND_URGENTE_053"));
		expedienteTradRev.setFechaFinalSolic(resultSet.getDate("FECHA_FINAL_SOLIC_053"));
		expedienteTradRev.setHoraFinalSolic(resultSet.getString("HORAFINALSOLIC"));
		expedienteTradRev.setFechaFinalProp(resultSet.getDate("FECHA_FINAL_PROP_053"));
		expedienteTradRev.setIndObservaciones(resultSet.getString("IND_OBSERVACIONES_053"));
		expedienteTradRev.setIndPresupuesto(resultSet.getString("IND_PRESUPUESTO_053"));
		expedienteTradRev.setEstadopresupuestoDescEs(resultSet.getString("PRESUPUESTOTRDESCES"));
		expedienteTradRev.setEstadoPresupuestoDescEu(resultSet.getString("PRESUPUESTOTRDESCEU"));
		if (StringUtils.isNotBlank(resultSet.getString("IND_PUBLICADO_BOE_053"))) {
			expedienteTradRev.setIndPublicadoBoe(resultSet.getString("IND_PUBLICADO_BOE_053"));
			expedienteTradRev.setUrlBoe(resultSet.getString("URL_BOE_053"));
		}
		expedienteTradRev.setEsExpedienteBoe(resultSet.getString("IND_BOE"));
		if (tieneTareasEntrega) {
			expedienteTradRev.setIndTareasEntrega(resultSet.getString(DBConstants.TIENETAREASENTREGA));
		} else {
			expediente.setObsvFacturacion(resultSet.getString("OBSV_FACT_051"));
		}

		expediente.setExpedienteTradRev(expedienteTradRev);

		// GestorExpediente
		Gestor gestorExpediente = new Gestor();
		Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setDni(resultSet.getString("DNI_SOLICITANTE_054"));
		solicitanteAux.setPreDni(resultSet.getString("PREDNI_077"));
		solicitanteAux.setSufDni(resultSet.getString("SUFDNI_077"));
		solicitanteAux.setDniCompleto(resultSet.getString("DNICOMPLETO"));
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString("IND_VIP_054"));
		solicitanteAux.setGestorExpedientesVIPDescEs(resultSet.getString("GESTOREXPEDIENTESVIPDESCES"));
		solicitanteAux.setGestorExpedientesVIPDescEu(resultSet.getString("GESTOREXPEDIENTESVIPDESCEU"));
		solicitanteAux.setNombre(resultSet.getString("NOMBRE_077"));
		solicitanteAux.setApellido1(resultSet.getString("APEL1_077"));
		solicitanteAux.setApellido2(resultSet.getString("APEL2_077"));
		solicitanteAux.setGrupoBoletin(resultSet.getString("ES_GRUPO_BOLETIN_054"));

		String dniVinculado = resultSet.getString("DNIVINCULADOOBAJA");
		solicitanteAux.setSolicitanteVinculado(StringUtils.isNotBlank(dniVinculado));

		gestorExpediente.setSolicitante(solicitanteAux);
		Entidad entidadAux = new Entidad();
		entidadAux.setTipo(resultSet.getString("TIPO_ENTIDAD_054"));
		entidadAux.setTipoDesc(Utils.getTipoEntidadDescLabel(entidadAux.getTipo()));
		entidadAux.setCodigo(resultSet.getInt("ID_ENTIDAD_054"));
		entidadAux.setCif(resultSet.getString("ENTIDAD_CIF"));
		entidadAux.setEstado(resultSet.getString("ENTIDAD_ESTADO"));
		entidadAux.setDescEs(resultSet.getString("ENTIDAD_DESC_ES"));
		entidadAux.setDescEu(resultSet.getString("ENTIDAD_DESC_EU"));
		entidadAux.setDescAmpEs(resultSet.getString("ENTIDAD_DESC_AMP_ES"));
		entidadAux.setDescAmpEu(resultSet.getString("ENTIDAD_DESC_AMP_EU"));
		gestorExpediente.setEntidad(entidadAux);
		expediente.setGestorExpediente(gestorExpediente);

		// BitacoraExpediente
		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		bitacoraExpediente.setIdEstadoBitacora(resultSet.getLong("ID_ESTADO_BITACORA_059"));
		EstadosExpediente estadoExp = new EstadosExpediente();
		estadoExp.setId(resultSet.getLong("ID_ESTADO_EXP_059"));
		estadoExp.setDescEs(resultSet.getString("ESTADOEXPEDIENTEDESCES"));
		estadoExp.setDescEu(resultSet.getString("ESTADOEXPEDIENTEDESCEU"));
		estadoExp.setDescAbrEs(resultSet.getString("ESTADOEXPEDIENTEDESCABRES"));
		estadoExp.setDescAbrEu(resultSet.getString("ESTADOEXPEDIENTEDESCABREU"));
		estadoExp.setClassStyle(resultSet.getString("ESTADOEXPEDIENTECLASS"));
		bitacoraExpediente.setEstadoExp(estadoExp);
		FasesExpediente faseExp = new FasesExpediente();
		faseExp.setId(resultSet.getLong("ID_FASE_EXPEDIENTE_059"));
		faseExp.setDescEs(resultSet.getString("FASEEXPEDIENTEDESCES"));
		faseExp.setDescEu(resultSet.getString("FASEEXPEDIENTEDESCEU"));
		faseExp.setDescAbrEs(resultSet.getString("FASEEXPEDIENTEDESCABRES"));
		faseExp.setDescAbrEu(resultSet.getString("FASEEXPEDIENTEDESCABREU"));
		bitacoraExpediente.setFaseExp(faseExp);
		bitacoraExpediente.setDatoAdic(resultSet.getString("DATO_ADIC_059"));
		bitacoraExpediente.setInfoAdic(resultSet.getString("INFO_ADIC_059"));
		bitacoraExpediente.setIdMotivoRechazo(resultSet.getLong("ID_RECHAZO_059"));
		SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
		subsanacionExpediente.setId(resultSet.getLong("ID_REQUERIMIENTO_059"));
		subsanacionExpediente.setDetalle(resultSet.getString("DETALLE_064"));
		subsanacionExpediente.setFechaReq(resultSet.getDate("FECHA_REQ_064"));
		subsanacionExpediente.setFechaLimite(resultSet.getDate("FECHA_LIMITE_064"));
		subsanacionExpediente.setIndSubsanado(resultSet.getString("IND_SUBSANADO_064"));
		subsanacionExpediente.setEstado(resultSet.getInt("ESTADO_SUBSANACION_064"));
		subsanacionExpediente.setFechaAceptacion(resultSet.getDate("FECHA_ACEPTACION_064"));
		subsanacionExpediente.setSubsanacionDescEs(resultSet.getString("SUBSANACIONDESCES"));
		subsanacionExpediente.setSubsanacionDescEu(resultSet.getString("SUBSANACIONDESCEU"));
		bitacoraExpediente.setSubsanacionExp(subsanacionExpediente);
		expediente.setBitacoraExpediente(bitacoraExpediente);

		expediente.setGrupoTrabajo(resultSet.getString("GRUPO_TRABAJO"));
		return expediente;
	}

	public static boolean entidadSolicitanteValida(Gestor gestorExpediente) {
		if (gestorExpediente.getEntidad() != null && !"null".equals(gestorExpediente.getEntidad().getTipo())) {
			return true;
		}
		return false;
	}

	/**
	 * Obtiene la parte where asociada a la eliminación de tareas
	 *
	 * @param listSelectedIds
	 * @return String
	 */
	public static String getWhereTareasAEliminar(List<String> listSelectedIds) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);

		if (isListIdsNoVacio(listSelectedIds)) {
			where.append("AND (( RECURSO_ASIGNACION_081 is null) OR (RECURSO_ASIGNACION_081 = '"
					+ TipoRecursoEnum.EXTERNO.getValue() + "'" + "AND ESTADO_EJECUCION_081 <> "
					+ EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ")" + "OR (RECURSO_ASIGNACION_081 = '"
					+ TipoRecursoEnum.INTERNO.getValue() + "'" + "AND ESTADO_ASIGNACION_081 <> "
					+ EstadoAceptacionTareaEnum.ACEPTADA.getValue() + "))" + DaoConstants.AND
					+ DaoConstants.ABRIR_PARENTESIS + DBConstants.ID_TIPO_TAREA_081 + DaoConstants.SIGNO_DISTINTO
					+ TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue() + DaoConstants.CERRAR_PARENTESIS
					+ " AND ID_TAREA_081 IN (");
			where.append(Utils.obtenerIdsConcatenados(listSelectedIds));
			where.append(")");
		}

		return where.toString();
	}

	/**
	 * Obtiene la parte where asociada a la eliminación de tareas
	 *
	 * @param listSelectedIds
	 * @return String
	 */
	public static String getWhereTareasDependientesAEliminar(List<Tareas> listTareas) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);
		where.append(" AND ID_TAREA_081 ");
		where.append(DaoConstants.IN);
		where.append(DaoConstants.ABRIR_PARENTESIS);
		where.append(getQueryTareasDependientes(listTareas));
		where.append(DaoConstants.CERRAR_PARENTESIS);

		return where.toString();
	}

	public static String getQueryTareasDependientes(List<Tareas> listTareas) {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT);
		query.append(DBConstants.ID_TAREA_081);
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_081);
		query.append(DaoConstants.WHERE_1_1);
		query.append(DaoConstants.AND);
		query.append(DBConstants.ID_TAREA_REL_081);
		query.append(DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(Utils.obtenerListIds(listTareas));
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AND);
		query.append(DBConstants.ID_TIPO_TAREA_081);
		query.append(DaoConstants.SIGNO_DISTINTO);
		query.append(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue());

		return query.toString();
	}

	/**
	 * Comprueba si el listado de ids seleccionados está vacío
	 *
	 * @param listSelectedIds List<String>
	 * @return boolean
	 */
	public static boolean isListIdsNoVacio(List<String> listSelectedIds) {
		return listSelectedIds != null && !listSelectedIds.isEmpty();
	}

	/**
	 * Comprueba si es posible actualizar la fecha de aceptación de la tarea
	 *
	 * @param tareas            Tareas
	 * @param cambioTipoRecurso boolean
	 * @return boolean
	 */
	public static boolean isActualizarFechaAceptacion(Tareas tareas, boolean cambioTipoRecurso) {
		return EstadoAceptacionTareaEnum.ACEPTADA.getValue() != tareas.getEstadoAsignado()
				|| (EstadoAceptacionTareaEnum.ACEPTADA.getValue() == tareas.getEstadoAsignado() && cambioTipoRecurso);
	}

	public static String getWhereLikeTareasConf(Tareas bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);
		where.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_081, bean.getAnyo(),
				params));
		where.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_081,
				bean.getNumExp(), params));

		return where.toString();
	}

	public String getLabelConImporte(BigDecimal importeDificultad, String lang) {
		Locale locale = new Locale(lang);
		StringBuilder sbImpDifLab = new StringBuilder();
		sbImpDifLab.append("<div class=\"ta_left\">");
		if (importeDificultad != null && importeDificultad.compareTo(BigDecimal.ZERO) > Constants.CERO) {
			sbImpDifLab.append(this.messageSource.getMessage("comun.si", null, locale) + " ( "
					+ this.formatMoneda.format(importeDificultad) + " )");
		} else {
			sbImpDifLab.append(this.messageSource.getMessage("comun.no", null, locale));
		}
		sbImpDifLab.append(" </div> ");
		return sbImpDifLab.toString();
	}

	/**
	 * @return the formatMoneda
	 */
	public NumberFormat getFormatMoneda() {
		return this.formatMoneda;
	}

	/**
	 * @param formatMoneda the formatMoneda to set
	 */
	public void setFormatMoneda(NumberFormat formatMoneda) {
		this.formatMoneda = formatMoneda;
	}

}
