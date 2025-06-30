package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.AA79bDocumentoTareaOrigenNoConformidadRevisionRowMapper;
import com.ejie.aa79b.dao.mapper.AA79bDocumentoTareaOrigenNoConformidadTraduccionRowMapper;
import com.ejie.aa79b.dao.mapper.AA79bDocumentoTareaRevisionRowMapper;
import com.ejie.aa79b.dao.mapper.AA79bSalidaOrigenNoConformidadRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bAuditoriaCampoSeccionExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bAuditoriaDatosGeneralesRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bAuditoriaDocumentoSeccionExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bAuditoriaRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bAuditoriaSeccionExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bConsultaTareasReportRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bDatosTareaEjecutadaRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bDocumentoTareaTraduccionRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bDocumentoTareaXliffRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bSalidaConsultaTareaRowMapper;
import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.EntradaDatosDocumento;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoCampoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum;
import com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum;
import com.ejie.aa79b.model.enums.EstadoAuditoriaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bConsultaTareasReport;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaCamposSeccion;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaValidarDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaOrigenNoConformidad;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTarea;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class Aa79bTareaWsDaoImpl extends GenericoDaoImpl<Aa79bTarea> implements Aa79bTareaWsDao {

	private static final String T1ID_TAREA_081 = " t1.ID_TAREA_081 ";
	private static final String T1ID_TIPO_TAREA_081 = " t1.ID_TIPO_TAREA_081 ";
	private static final String T1ESTADO_EJECUCION_081 = " t1.ESTADO_EJECUCION_081 ";
	private static final String T1ANYO_081 = " t1.ANYO_081 ";
	private static final String T1NUM_EXP_081 = " t1.NUM_EXP_081 ";
	private static final String T7ID_LOTE_029 = " t7.ID_LOTE_029 ";
	private static final String DDMMYY = "DD/mm/YY";
	private static final String T1ID_TAREA_083 = " t1.ID_TAREA_083 ";
	private static final String T2ID_DOC_056 = " t2.ID_DOC_056 ";
	private static final String T2TITULO_056 = " t2.TITULO_056 ";
	private static final String T2OID_FICHERO_056 = " t2.OID_FICHERO_056 ";
	private static final String T2EXTENSION_DOC_056 = " t2.EXTENSION_DOC_056 ";
	private static final String T2CLASE_DOCUMENTO_056 = " t2.CLASE_DOCUMENTO_056 ";
	private static final String T2IND_ENCRIPTADO_056 = " t2.IND_ENCRIPTADO_056 ";
	private static final String T3ID_FICHERO_TRADUCIDO_087 = " t3.ID_FICHERO_TRADUCIDO_087 ";
	private static final String T1ID_DOC_083 = " t1.ID_DOC_083 ";
	private static final String NUM_EXP_081 = " NUM_EXP_081 ";
	private static final String ANYO_081 = " ANYO_081 ";
	private static final String T4ID_FICHERO_TRADUCIDO_087 = " t4.ID_FICHERO_TRADUCIDO_087 ";
	private static final String T6ID_FICHERO_JUSTIFICANTE_093 = " t6.ID_FICHERO_JUSTIFICANTE_093 ";
	private static final String T7ID_FICHERO_088 = " t7.ID_FICHERO_088 ";
	private static final String T1_FECHA_FIN_081 = DaoConstants.BLANK + DaoConstants.T1_MINUSCULA_PUNTO
			+ DBConstants.FECHA_FIN_081 + DaoConstants.BLANK;

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	private static final Logger LOGGER = LoggerFactory.getLogger(Aa79bTareaWsDaoImpl.class);

	public Aa79bTareaWsDaoImpl() {
		// Constructor
		super(Aa79bTarea.class);
	}

	/*
	 * ROW_MAPPERS - INICIO
	 */
	private RowMapper<Aa79bLoteCombo> rwMapLoteCombo = new RowMapper<Aa79bLoteCombo>() {
		@Override
		public Aa79bLoteCombo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Aa79bLoteCombo loteCombo = new Aa79bLoteCombo();
			loteCombo.setId(resultSet.getInt(DBConstants.IDLOTE));
			loteCombo.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));
			loteCombo.setEstado(resultSet.getString(DBConstants.ACTIVO));
			return loteCombo;

		}
	};

	private RowMapper<Tareas> rwMapTareasEntrega = new RowMapper<Tareas>() {
		@Override
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setOrden(resultSet.getInt(DBConstants.ORDEN));
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			return tareas;

		}
	};

	private RowMapper<Aa79bSalidaConsultaTarea> rwMapAa79bSalidaConsultaTarea = new Aa79bSalidaConsultaTareaRowMapper();
	private RowMapper<Aa79bDocumentoTarea> rwMapAa79bDocumentoTareaTraduccion = new Aa79bDocumentoTareaTraduccionRowMapper();
	private RowMapper<Aa79bDocumentoTarea> rwMapAa79bDocXliffTareaTraduccion = new Aa79bDocumentoTareaXliffRowMapper();
	private RowMapper<Aa79bDocumentoTarea> rwMapAa79bDocumentoTareaRevision = new AA79bDocumentoTareaRevisionRowMapper();
	private RowMapper<Aa79bSalidaOrigenNoConformidad> rwMapAa79bSalidaOrigenNoConformidad = new AA79bSalidaOrigenNoConformidadRowMapper();
	private RowMapper<Aa79bDocumentoTarea> rwMapAa79bDocumentoTareaOrigenNoConformidadTraduccion = new AA79bDocumentoTareaOrigenNoConformidadTraduccionRowMapper();
	private RowMapper<Aa79bDocumentoTarea> rwMapAa79bDocumentoTareaOrigenNoConformidadRevision = new AA79bDocumentoTareaOrigenNoConformidadRevisionRowMapper();
	private RowMapper<Aa79bConsultaTareasReport> rwMapAa79bConsultaTareasReport = new Aa79bConsultaTareasReportRowMapper();
	private RowMapper<Aa79bSalidaTarea> rWMapDatosTareaEjecutada = new Aa79bDatosTareaEjecutadaRowMapper();
	private RowMapper<Aa79bAuditoria> rwMapAa79bAuditoria = new Aa79bAuditoriaRowMapper();
	private RowMapper<Aa79bAuditoria> rwMapAuditoriaDatosGenerales = new Aa79bAuditoriaDatosGeneralesRowMapper();
	private RowMapper<Aa79bAuditoriaSeccionExpediente> rwMapAuditoriaSeccionExpediente = new Aa79bAuditoriaSeccionExpedienteRowMapper();
	private RowMapper<Aa79bAuditoriaCampoSeccionExpediente> rwMapAuditoriaCampoSeccionExpediente = new Aa79bAuditoriaCampoSeccionExpedienteRowMapper();
	private RowMapper<Aa79bAuditoriaDocumentoSeccionExpediente> rwMapAuditoriaDocumentoSeccionExpediente = new Aa79bAuditoriaDocumentoSeccionExpedienteRowMapper();

	private RowMapper<Aa79bLoteCombo> getrwMapLoteCombo() {
		return this.rwMapLoteCombo;
	}

	private RowMapper<Aa79bSalidaConsultaTarea> getRwMapAa79bSalidaConsultaTarea() {
		return this.rwMapAa79bSalidaConsultaTarea;
	}

	private RowMapper<Aa79bDocumentoTarea> getRwMapAa79bDocumentoTareaTraduccion() {
		return this.rwMapAa79bDocumentoTareaTraduccion;
	}

	private RowMapper<Aa79bDocumentoTarea> getRwMapAa79bDocXliffTareaTraduccion() {
		return this.rwMapAa79bDocXliffTareaTraduccion;
	}

	private RowMapper<Aa79bDocumentoTarea> getRwMapAa79bDocumentoTareaRevision() {
		return this.rwMapAa79bDocumentoTareaRevision;
	}

	private RowMapper<Aa79bSalidaOrigenNoConformidad> getRwMappOrigenNoConformidad() {
		return this.rwMapAa79bSalidaOrigenNoConformidad;
	}

	private RowMapper<Aa79bDocumentoTarea> getRwMapAa79bDocumentoOrigenNoConformidadTraduccion() {
		return this.rwMapAa79bDocumentoTareaOrigenNoConformidadTraduccion;
	}

	private RowMapper<Aa79bDocumentoTarea> getRwMapAa79bDocumentoOrigenNoConformidadRevision() {
		return this.rwMapAa79bDocumentoTareaOrigenNoConformidadRevision;
	}

	private RowMapper<Aa79bConsultaTareasReport> getRwMapAa79bConsultaTareasReport() {
		return this.rwMapAa79bConsultaTareasReport;
	}

	private RowMapper<Aa79bSalidaTarea> getRWMapDatosTareaEjecutada() {
		return this.rWMapDatosTareaEjecutada;
	}

	private RowMapper<Aa79bAuditoria> getRwMapAa79bAuditorias() {
		return this.rwMapAa79bAuditoria;
	}

	/*
	 * ROW_MAPPERS - FIN
	 */

	@Override
	protected String getSelect() {
		return null;
	}

	@Override
	protected String getFrom() {
		return null;
	}

	@Override
	protected RowMapper<Aa79bTarea> getRwMap() {
		return null;
	}

	@Override
	protected String[] getOrderBy() {
		return new String[0];
	}

	@Override
	protected String getPK() {
		return null;
	}

	@Override
	protected RowMapper<Aa79bTarea> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(Aa79bTarea bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhere(Aa79bTarea bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(Aa79bTarea bean, Boolean startsWith, List<Object> params, Boolean search) {
		return null;
	}

	@Override
	public List<Aa79bLoteCombo> obtenerBuscadorProveedor(String dni) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(DaoConstants.SELECT);
		query.append(" t1.ID_LOTE_029 " + DBConstants.IDLOTE + DaoConstants.SIGNO_COMA);
		query.append(" t1.NOMBRE_LOTE_029 " + DBConstants.NOMBRELOTE + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.CASE + DaoConstants.WHEN);
		query.append(DaoConstants.SYSDATE + DaoConstants.SIGNO_MENOR_IGUAL_QUE + " t1.FECHA_FIN_029 ");
		query.append(DaoConstants.AND);
		query.append(DaoConstants.SYSDATE + DaoConstants.SIGNO_MAYOR_IGUAL_QUE + " t1.FECHA_INICIO_029 ");
		query.append(DaoConstants.THEN + DaoConstants.SIGNOINTERROGACION);
		query.append(DaoConstants.ELSE + DaoConstants.SIGNOINTERROGACION);
		query.append(DaoConstants.END + DaoConstants.AS + DBConstants.ACTIVO);
		query.append(DaoConstants.FROM + DBConstants.TABLA_29 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.VISTAX54JAPIPROVEEDORES + " t2 ");
		query.append(DaoConstants.ON + " t1.TIPO_ENTIDAD_029 " + DaoConstants.SIGNOIGUAL + " t2.TIPO_ENTIDAD ");
		query.append(DaoConstants.AND + " t1.ID_EMPRESA_PROV_029 " + DaoConstants.SIGNOIGUAL + " t2.COD_ENTIDAD ");
		query.append(DaoConstants.WHERE + " t2.DNI " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(ActivoEnum.SI.getValue());
		params.add(ActivoEnum.NO.getValue());
		params.add(dni);
		return this.getJdbcTemplate().query(query.toString(), params.toArray(), getrwMapLoteCombo());
	}

	@Override
	public Object consultaTareasProveedor(Aa79bEntradaConsultaTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount) {
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		Locale eu = new Locale("eu");
		Locale es = new Locale("es");
		boolean esInterpretacion = false;
		if (bean.getIdTipoTarea() != null
				&& TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == bean.getIdTipoTarea()) {
			esInterpretacion = true;
		}
		// comun

		this.getSelectConsultaTareasProveedor(isCount, query, eu, es, esInterpretacion);

		// comun
		query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_15 + " t2 ");
		query.append(DaoConstants.ON + T1ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL + " t2.ID_015 ");
		this.consultaTareasProveedorJoinTablas(query, esInterpretacion);

		params.add(bean.getDni());

		query.append(this.getWhereConsultaTareasProveedor(bean, params, startsWith));

		this.getWhereAuxConsultaTareasProveedor(isCount, query, esInterpretacion);

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapAa79bSalidaConsultaTarea(),
					params.toArray());
		}
	}

	private void getWhereAuxConsultaTareasProveedor(Boolean isCount, StringBuilder query, boolean esInterpretacion) {
		Locale eu = new Locale("eu");
		Locale es = new Locale("es");
		if (!isCount && !esInterpretacion) {
			query.append(DaoConstants.GROUP_BY);
			query.append(T1ID_TAREA_081 + DaoConstants.SIGNO_COMA);
			query.append(T1ID_TIPO_TAREA_081 + DaoConstants.SIGNO_COMA);
			query.append(T1ESTADO_EJECUCION_081 + DaoConstants.SIGNO_COMA);
			query.append("DECODE(t1.ESTADO_EJECUCION_081, " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
					+ ",'" + this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, es)
					+ "'," + EstadoEjecucionTareaEnum.RETRASADA.getValue() + ", '"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, es) + "', "
					+ EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ", '"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, es) + "')"
					+ DaoConstants.SIGNO_COMA);
			query.append("DECODE(t1.ESTADO_EJECUCION_081, " + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
					+ ",'" + this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, eu)
					+ "'," + EstadoEjecucionTareaEnum.RETRASADA.getValue() + ", '"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, eu) + "', "
					+ EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ", '"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, eu) + "')"
					+ DaoConstants.SIGNO_COMA);
			query.append(T1ANYO_081 + DaoConstants.SIGNO_COMA);
			query.append(T1NUM_EXP_081 + DaoConstants.SIGNO_COMA);
			query.append("  SUBSTR(t1.ANYO_081,3,4) || '/' || LPAD(t1.NUM_EXP_081,6,'0')  " + DaoConstants.SIGNO_COMA);
			query.append(" t2.DESC_ES_015 " + DaoConstants.SIGNO_COMA);
			query.append(" t2.DESC_EU_015 " + DaoConstants.SIGNO_COMA);
			// fecha prevista
			query.append(T1_FECHA_FIN_081 + DaoConstants.SIGNO_COMA);
			query.append(" TO_CHAR(t1.FECHA_FIN_081,'HH24:MI') " + DaoConstants.SIGNO_COMA);
			// fecha real
			query.append(" t3.FECHA_EJECUCION_082 " + DaoConstants.SIGNO_COMA);
			query.append(" TO_CHAR(t3.FECHA_EJECUCION_082,'HH24:MI') " + DaoConstants.SIGNO_COMA);
			query.append(T7ID_LOTE_029 + DaoConstants.SIGNO_COMA);
			query.append(" t7.NOMBRE_LOTE_029 ");
		}
	}

	private void getSelectConsultaTareasProveedor(Boolean isCount, StringBuilder query, Locale eu, Locale es,
			boolean esInterpretacion) {
		if (isCount) {
			query.append(DaoConstants.SELECT + " COUNT " + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
					+ T1ID_TAREA_081 + DaoConstants.CERRAR_PARENTESIS);
		} else {
			query.append(DaoConstants.SELECT + DaoConstants.DISTINCT);
			query.append(T1ID_TAREA_081 + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
			query.append(T1ID_TIPO_TAREA_081 + DBConstants.IDTIPOTAREA + DaoConstants.SIGNO_COMA);
			query.append(T1ESTADO_EJECUCION_081 + DBConstants.ESTADOEJECID + DaoConstants.SIGNO_COMA);
			query.append(" CASE WHEN (" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
					+ "=t1.ESTADO_EJECUCION_081 AND t1.FECHA_FIN_081 < SYSDATE) THEN '"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, es) + "'");
			query.append(" ELSE (DECODE(t1.ESTADO_EJECUCION_081, "
					+ EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue() + ",'"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, es) + "',");
			query.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ",'"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, es) + "')) END AS "
					+ DBConstants.ESTADOEJECUCIONDESCES + DaoConstants.SIGNO_COMA);
			query.append(" CASE WHEN (" + EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
					+ "=t1.ESTADO_EJECUCION_081 AND t1.FECHA_FIN_081 < SYSDATE) THEN '"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.RETRASADA.getLabel(), null, eu) + "'");
			query.append(" ELSE (DECODE(t1.ESTADO_EJECUCION_081, "
					+ EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue() + ",'"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel(), null, eu) + "',");
			query.append(EstadoEjecucionTareaEnum.EJECUTADA.getValue() + ",'"
					+ this.msg.getMessage(EstadoEjecucionTareaEnum.EJECUTADA.getLabel(), null, eu) + "')) END AS "
					+ DBConstants.ESTADOEJECUCIONDESCEU + DaoConstants.SIGNO_COMA);
			query.append(T1ANYO_081 + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
			query.append(T1NUM_EXP_081 + DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
			query.append(" SUBSTR(t1.ANYO_081,2,4) || '/' || LPAD(t1.NUM_EXP_081,6,'0') "
					+ DBConstants.ANYONUMEXPCONCATENADO + DaoConstants.SIGNO_COMA);
			query.append(" t2.DESC_ES_015 " + DBConstants.TIPOTAREADESCES + DaoConstants.SIGNO_COMA);
			query.append(" t2.DESC_EU_015 " + DBConstants.TIPOTAREADESCEU + DaoConstants.SIGNO_COMA);

			if (esInterpretacion) {
				// interpretacion
				query.append(" t1.FECHA_INICIO_081 " + DBConstants.FECHAINICIO + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + " t1.FECHA_INICIO_081,'HH24:MI' "
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAINICIO + DaoConstants.SIGNO_COMA);
				query.append(T1_FECHA_FIN_081 + DBConstants.FECHAFIN + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + " t1.FECHA_FIN_081,'HH24:MI' "
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAFIN);

			} else {
				// tradRev
				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_TOTAL_PAL_091 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMTOTALPAL + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_PAL_CONCOR_0_84_091 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_PAL_CONCOR_85_94_091 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_PAL_CONCOR_95_100_091 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMPALCONCOR95100 + DaoConstants.SIGNO_COMA);

				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_PAL_CONCOR_95_99_091 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMPALCONCOR9599 + DaoConstants.SIGNO_COMA);

				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_PAL_CONCOR_100_091 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMPALCONCOR100 + DaoConstants.SIGNO_COMA);

				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t6.NUM_PAL_IZO_056 " + DaoConstants.SIGNO_COMA + Constants.CERO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.NUMPALIZO + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.SUM + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS
						+ " t5.NUM_TOTAL_PAL_091 ");
				query.append(DaoConstants.SIGNO_COMA + " t6.NUM_PAL_IZO_056 " + DaoConstants.CERRAR_PARENTESIS
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS
						+ DBConstants.NUMPALXMLONUMPALIZO + DaoConstants.SIGNO_COMA);
				query.append(T1_FECHA_FIN_081 + DBConstants.FECHAPREVISTAEJECUCION + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + " t1.FECHA_FIN_081,'HH24:MI' "
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAPREVISTAEJECUCION + DaoConstants.SIGNO_COMA);
				query.append(" t3.FECHA_EJECUCION_082 " + DBConstants.FECHAREALEJECUCION + DaoConstants.SIGNO_COMA);
				query.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + " t3.FECHA_EJECUCION_082,'HH24:MI' "
						+ DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAREALEJECUCION + DaoConstants.SIGNO_COMA);
				query.append(T7ID_LOTE_029 + DBConstants.IDLOTE + DaoConstants.SIGNO_COMA);
				query.append(" t7.NOMBRE_LOTE_029 " + DBConstants.NOMBRELOTE);

			}
		}
	}

	private void consultaTareasProveedorJoinTablas(StringBuilder query, boolean esInterpretacion) {
		if (esInterpretacion) {
			// interpretacion
			// where
			query.append(DaoConstants.WHERE + " t1.DNI_RECURSO_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		} else {
			// traduRev
			query.append(DaoConstants.JOIN + DBConstants.TABLA_82 + " t3 ");
			query.append(DaoConstants.ON + T1ID_TAREA_081 + DaoConstants.SIGNOIGUAL + " t3.ID_TAREA_082 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_83 + " t4 ");
			query.append(DaoConstants.ON + T1ID_TAREA_081 + DaoConstants.SIGNOIGUAL + " t4.ID_TAREA_083 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_91 + " t5 ");
			query.append(DaoConstants.ON + " t4.ID_DOC_083 " + DaoConstants.SIGNOIGUAL + " t5.ID_DOC_ORIG_091 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t6 ");
			query.append(DaoConstants.ON + " t4.ID_DOC_083 " + DaoConstants.SIGNOIGUAL + " t6.ID_DOC_056 ");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_29 + " t7 ");
			query.append(DaoConstants.ON + " t1.ID_LOTE_081 " + DaoConstants.SIGNOIGUAL + T7ID_LOTE_029);
			query.append(DaoConstants.AND + DaoConstants.SYSDATE + DaoConstants.SIGNO_MENOR_IGUAL_QUE
					+ " t7.FECHA_FIN_029 ");
			query.append(DaoConstants.AND + DaoConstants.SYSDATE + DaoConstants.SIGNO_MAYOR_IGUAL_QUE
					+ " t7.FECHA_INICIO_029 ");
			query.append(DaoConstants.JOIN + DBConstants.VISTAX54JAPIPROVEEDORES + " t8 ");
			query.append(DaoConstants.ON + " t7.TIPO_ENTIDAD_029 " + DaoConstants.SIGNOIGUAL + " t8.TIPO_ENTIDAD ");
			query.append(DaoConstants.AND + " t7.ID_EMPRESA_PROV_029 " + DaoConstants.SIGNOIGUAL + " t8.COD_ENTIDAD ");
			// where
			query.append(DaoConstants.WHERE + " t8.DNI " + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS + T1ID_TIPO_TAREA_081
					+ DaoConstants.SIGNOIGUAL + TipoTareaGestionAsociadaEnum.TRADUCIR.getValue());
			query.append(DaoConstants.OR + T1ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL
					+ TipoTareaGestionAsociadaEnum.REVISAR.getValue());
			query.append(DaoConstants.OR + T1ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL
					+ TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue());
			query.append(DaoConstants.OR + T1ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL
					+ TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue()
					+ DaoConstants.CERRAR_PARENTESIS);
		}
		query.append(DaoConstants.AND + " t1.RECURSO_ASIGNACION_081 " + DaoConstants.SIGNOIGUAL + "'"
				+ TipoRecursoEnum.EXTERNO.getValue() + "' ");
	}

	private String getWhereConsultaTareasProveedor(Aa79bEntradaConsultaTarea bean, List<Object> params,
			Boolean startsWith) {
		StringBuilder where = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);

		// tipo de tarea
		where.append(SqlUtils.generarWhereIgual("t1.ID_TIPO_TAREA_081", bean.getIdTipoTarea(), params));

		// estado ejecucion tarea
		if (StringUtils.isNotBlank(bean.getIdsEstadoEjecTarea())) {
			String[] aEstados = bean.getIdsEstadoEjecTarea().split(Constants.COMA);
			where.append(DaoConstants.AND);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			boolean esPrimero = true;
			for (String sId : aEstados) {
				Integer idEstado = Integer.parseInt(sId);
				if (esPrimero) {
					esPrimero = false;
				} else {
					where.append(DaoConstants.OR);
				}
				if (idEstado == EstadoEjecucionTareaEnum.RETRASADA.getValue()) {
					where.append("( t1.ESTADO_EJECUCION_081 = 1 AND t1.FECHA_FIN_081 < SYSDATE ) ");
				} else {
					where.append(T1ESTADO_EJECUCION_081 + DaoConstants.SIGNOIGUAL + idEstado);
				}
			}
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}

		// fecha prevista ejecucion tarea
		where.append(SqlUtils.generarWhereMayorIgualFecha("t1.FECHA_FIN_081", DDMMYY,
				DateUtils.obtFechaHoraFormateada(bean.getFechaDesdeEjecPrevTarea(), Constants.HORA_MINUTOS_CERO),
				params));
		where.append(SqlUtils.generarWhereMenorIgualFecha("t1.FECHA_FIN_081", DDMMYY,
				DateUtils.obtFechaHoraFormateada(bean.getFechaHastaEjecPrevTarea(), Constants.HORA_MINUTOS_CERO),
				params));
		// fecha real ejecucion tarea
		where.append(SqlUtils.generarWhereMayorIgualFecha("t3.FECHA_EJECUCION_082", DDMMYY,
				DateUtils.obtFechaHoraFormateada(bean.getFechaDesdeEjecRealTarea(), Constants.HORA_MINUTOS_CERO),
				params));
		where.append(SqlUtils.generarWhereMenorIgualFecha("t3.FECHA_EJECUCION_082", DDMMYY,
				DateUtils.obtFechaHoraFormateada(bean.getFechaHastaEjecRealTarea(), Constants.HORA_MINUTOS_CERO),
				params));
		// anyo num expediente
		where.append(SqlUtils.generarWhereIgual("SUBSTR(t1.ANYO_081,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_081", bean.getNumExp(), params));
		// id lotes
		where.append(SqlUtils.generarWhereIgual("t7.ID_LOTE_029", bean.getIdLote(), params));
		return where.toString();
	}

	@Override
	public Object obtenerDocumentosTraduccion(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount) {
		StringBuilder queryODT = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQueryODT = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsODT = new ArrayList<Object>();
		if (isCount) {
			queryODT.append(DaoConstants.SELECT_COUNT);
		} else {
			queryODT.append(DaoConstants.SELECT);
			queryODT.append(T1ID_TAREA_083 + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
			queryODT.append(T2ID_DOC_056 + DBConstants.IDDOC + DaoConstants.SIGNO_COMA);
			queryODT.append(T2TITULO_056 + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
			queryODT.append(T2OID_FICHERO_056 + DBConstants.OIDFICHERO + DaoConstants.SIGNO_COMA);
			queryODT.append(T2EXTENSION_DOC_056 + DBConstants.EXTENSIONDOC + DaoConstants.SIGNO_COMA);
			queryODT.append(T2CLASE_DOCUMENTO_056 + DBConstants.CLASEDOCUMENTO + DaoConstants.SIGNO_COMA);
			queryODT.append(T2IND_ENCRIPTADO_056 + DBConstants.INDENCRIPTADO + DaoConstants.SIGNO_COMA);
			queryODT.append(" t2.NOMBRE_FICHERO_056 NOMBREDOC, ");
			queryODT.append(T3ID_FICHERO_TRADUCIDO_087 + DBConstants.IDFICHEROTRADUCIDO + DaoConstants.SIGNO_COMA);
			queryODT.append(" t4.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADODOCTRAD + DaoConstants.SIGNO_COMA);
			queryODT.append(" t4.OID_FICHERO_088 " + DBConstants.OIDFICHERODOCTRAD + DaoConstants.SIGNO_COMA);
			queryODT.append(" t4.NOMBRE_FICHERO_088 NOMBREDOCTRADUCIDO, ");
			queryODT.append(" t4.TITULO_FICHERO_088 " + DBConstants.TITULODOCTRADUCIDO);
		}

		queryODT.append(DaoConstants.FROM + DBConstants.TABLA_83 + " t1 ");
		queryODT.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t2 ");
		queryODT.append(DaoConstants.ON + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL + T2ID_DOC_056);
		queryODT.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_87 + " t3 ");
		queryODT.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + T2ID_DOC_056 + DaoConstants.SIGNOIGUAL
				+ " t3.ID_DOC_ORIG_087 ");
		queryODT.append(DaoConstants.AND + " t3.ID_TAREA_087 " + DaoConstants.SIGNOIGUAL + T1ID_TAREA_083
				+ DaoConstants.CERRAR_PARENTESIS);
		queryODT.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t4 ");
		queryODT.append(DaoConstants.ON + T3ID_FICHERO_TRADUCIDO_087 + DaoConstants.SIGNOIGUAL + " t4.ID_FICHERO_088 ");
		queryODT.append(DaoConstants.WHERE + T1ID_TAREA_083 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		queryODT.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS + T2CLASE_DOCUMENTO_056
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.OR + T2CLASE_DOCUMENTO_056
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		paramsODT.add(bean.getIdTarea());
		paramsODT.add(ClasificacionDocumentosEnum.TRADUCCION.getValue());
		paramsODT.add(ClasificacionDocumentosEnum.TRABAJO.getValue());

		paginatedQueryODT.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, queryODT));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQueryODT.toString(), paramsODT.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQueryODT.toString(),
					this.getRwMapAa79bDocumentoTareaTraduccion(), paramsODT.toArray());
		}
	}

	@Override
	public Boolean getAccesoTarea(Aa79bEntradaEjecutarTarea bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.TABLA_81 + " t1 ");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_29 + " t2 ");
		query.append(DaoConstants.ON + " t1.ID_LOTE_081 " + DaoConstants.SIGNOIGUAL + " t2.ID_LOTE_029 ");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JAPIPROVEEDORES + " t3 ");
		query.append(DaoConstants.ON + " t2.TIPO_ENTIDAD_029 " + DaoConstants.SIGNOIGUAL + " t3.TIPO_ENTIDAD ");
		query.append(DaoConstants.AND + " t2.ID_EMPRESA_PROV_029 " + DaoConstants.SIGNOIGUAL + " t3.COD_ENTIDAD ");
		query.append(DaoConstants.WHERE + DaoConstants.ABRIR_PARENTESIS + " t1.DNI_RECURSO_081 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(
				DaoConstants.OR + " t3.DNI " + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AND + T1ID_TAREA_081 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(bean.getDni());
		params.add(bean.getDni());
		params.add(bean.getIdTarea());

		Long resul = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
		return (resul != null && resul > 0);
	}

	@Override
	public Tareas findTareaEntregaCliente(Tareas tarea) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT * ");
		query.append("FROM (SELECT ORDEN_081 ORDEN, ID_TAREA_081 IDTAREA FROM AA79B81T00 ");
		query.append("WHERE ID_TIPO_TAREA_081 IN (");
		query.append(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue()).append(",");
		query.append(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue()).append(",");
		query.append(TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue());
		query.append(") AND ANYO_081 = ? ");
		query.append("AND NUM_EXP_081 = ? ");
		query.append("ORDER BY ID_TAREA_081 DESC) WHERE ROWNUM = 1 ");
		params.add(tarea.getAnyo());
		params.add(tarea.getNumExp());
		List<Tareas> tareaList = this.getJdbcTemplate().query(query.toString(), this.rwMapTareasEntrega,
				params.toArray());
		return DataAccessUtils.uniqueResult(tareaList);

	}

	@Override
	public Boolean getAccesoFicheroTarea(EntradaDatosDocumento bean) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(DaoConstants.SELECT + " case when ");
		// ficheros trad/rev tarea
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT_COUNT + DaoConstants.FROM
				+ DBConstants.TABLA_87 + DaoConstants.SIGNO_COMA + DBConstants.TABLA_81);
		query.append(DaoConstants.WHERE + " ID_TAREA_087 = ID_TAREA_081 " + DaoConstants.AND + NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + " ID_FICHERO_TRADUCIDO_087 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_MAYOR_QUE + Constants.CERO + DaoConstants.THEN + Constants.UNO);
		query.append(DaoConstants.ELSE + DaoConstants.CASE + DaoConstants.WHEN);
		// ficheros documentos finales
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT_COUNT + DaoConstants.FROM
				+ DBConstants.TABLA_92 + DaoConstants.SIGNO_COMA + DBConstants.TABLA_81);
		query.append(DaoConstants.WHERE + " ID_TAREA_092 = ID_TAREA_081 " + DaoConstants.AND + NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + " ID_FICHERO_FINAL_092 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_MAYOR_QUE + Constants.CERO + DaoConstants.THEN + Constants.UNO);

		query.append(DaoConstants.ELSE + DaoConstants.CASE + DaoConstants.WHEN);
		// ficheros justificantes de revision
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT_COUNT + DaoConstants.FROM
				+ DBConstants.TABLA_93 + DaoConstants.SIGNO_COMA + DBConstants.TABLA_81);
		query.append(DaoConstants.WHERE + " ID_TAREA_093 = ID_TAREA_081 " + DaoConstants.AND + NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + " ID_FICHERO_JUSTIFICANTE_093 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_MAYOR_QUE + Constants.CERO + DaoConstants.THEN + Constants.UNO);

		query.append(DaoConstants.ELSE + DaoConstants.CASE + DaoConstants.WHEN);
		// ficheros xliff/tmx
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT_COUNT + DaoConstants.FROM
				+ DBConstants.TABLA_96 + DaoConstants.SIGNO_COMA + DBConstants.TABLA_81);
		query.append(DaoConstants.WHERE + " ID_TAREA_096 = ID_TAREA_081 " + DaoConstants.AND + NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + " ID_FICHERO_XLIFF_096 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_MAYOR_QUE + Constants.CERO + DaoConstants.THEN + Constants.UNO);
		// ficheros correcciones
		query.append(DaoConstants.ELSE + DaoConstants.CASE + DaoConstants.WHEN);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT_COUNT + DaoConstants.FROM
				+ DBConstants.TABLA_B1 + DaoConstants.SIGNO_COMA + DBConstants.TABLA_81);
		query.append(DaoConstants.WHERE + " ID_TAREA_0B1 = ID_TAREA_081 " + DaoConstants.AND + NUM_EXP_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + ANYO_081
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.AND + " ID_FICHERO_0B1 "
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_MAYOR_QUE + Constants.CERO + DaoConstants.THEN + Constants.UNO);

		query.append(DaoConstants.ELSE + Constants.CERO + DaoConstants.END + DaoConstants.END + DaoConstants.END
				+ DaoConstants.END + DaoConstants.END + DaoConstants.AS + " FLAG FROM DUAL");

		for (int i = Constants.CERO; i < Constants.CINCO; i++) {
			params.add(bean.getNumExp());
			params.add(bean.getAnyo());
			params.add(bean.getIdsFichero());
		}

		Long resul = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
		return (resul != null && resul > 0);
	}

	@Override
	public Object obtenerDocumentosXliffWS(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount) {
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		if (isCount) {
			query.append(DaoConstants.SELECT_COUNT);
		} else {
			query.append(DaoConstants.SELECT);
			query.append(" t1.ID_TAREA_096 " + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
			query.append(" t1.ID_FICHERO_XLIFF_096 " + DBConstants.IDFICHEROXLIFF + DaoConstants.SIGNO_COMA);
			query.append(" t2.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADO + DaoConstants.SIGNO_COMA);
			query.append(" t2.OID_FICHERO_088 " + DBConstants.OIDFICHERO + DaoConstants.SIGNO_COMA);
			query.append(" t2.TITULO_FICHERO_088 " + DBConstants.TITULO);
		}

		query.append(DaoConstants.FROM + DBConstants.TABLA_96 + " t1 ");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t2 ");
		query.append(DaoConstants.ON + " t1.ID_FICHERO_XLIFF_096 " + DaoConstants.SIGNOIGUAL + " t2.ID_FICHERO_088 ");
		query.append(DaoConstants.WHERE + " t1.ID_TAREA_096 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(bean.getIdTarea());

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapAa79bDocXliffTareaTraduccion(),
					params.toArray());
		}
	}

	@Override
	public Object obtenerDocumentosRevision(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount) {
		StringBuilder queryODR = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQueryODR = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsODR = new ArrayList<Object>();
		if (isCount) {
			queryODR.append(DaoConstants.SELECT_COUNT);
		} else {
			queryODR.append(DaoConstants.SELECT);
			queryODR.append(T1ID_TAREA_083 + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
			queryODR.append(T2ID_DOC_056 + DBConstants.IDDOC + DaoConstants.SIGNO_COMA);
			queryODR.append(" t3.ID_DOC_056 " + DBConstants.IDDOCREL + DaoConstants.SIGNO_COMA);
			queryODR.append(T2TITULO_056 + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
			queryODR.append(T2OID_FICHERO_056 + DBConstants.OIDFICHERO + DaoConstants.SIGNO_COMA);
			queryODR.append(T2EXTENSION_DOC_056 + DBConstants.EXTENSIONDOC + DaoConstants.SIGNO_COMA);
			queryODR.append(T2CLASE_DOCUMENTO_056 + DBConstants.CLASEDOCUMENTO + DaoConstants.SIGNO_COMA);
			queryODR.append(T2IND_ENCRIPTADO_056 + DBConstants.INDENCRIPTADO + DaoConstants.SIGNO_COMA);
			queryODR.append(" t2.NOMBRE_FICHERO_056 NOMBREDOC, ");

			queryODR.append(" t3.TITULO_056 " + DBConstants.TITULOREL + DaoConstants.SIGNO_COMA);
			queryODR.append(" t3.OID_FICHERO_056 " + DBConstants.OIDFICHEROREL + DaoConstants.SIGNO_COMA);
			queryODR.append(" t3.CLASE_DOCUMENTO_056 " + DBConstants.CLASEDOCUMENTOREL + DaoConstants.SIGNO_COMA);
			queryODR.append(" t3.IND_ENCRIPTADO_056 " + DBConstants.INDENCRIPTADOREL + DaoConstants.SIGNO_COMA);
			queryODR.append(" t3.NOMBRE_FICHERO_056 NOMBREDOCREL, ");
			queryODR.append(T4ID_FICHERO_TRADUCIDO_087 + DBConstants.IDFICHEROREVISADO + DaoConstants.SIGNO_COMA);
			queryODR.append(" t5.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADOFICHREV + DaoConstants.SIGNO_COMA);
			queryODR.append(" t5.TITULO_FICHERO_088 " + DBConstants.TITULOFICHREV + DaoConstants.SIGNO_COMA);
			queryODR.append(" t5.OID_FICHERO_088 " + DBConstants.OIDFICHEROREV + DaoConstants.SIGNO_COMA);
			queryODR.append(" t5.NOMBRE_FICHERO_088 NOMBREFICHEROREV,");
			queryODR.append(T6ID_FICHERO_JUSTIFICANTE_093 + DBConstants.IDFICHJUST + DaoConstants.SIGNO_COMA);
			queryODR.append(" t6.IND_VISIBLE_093 " + DBConstants.INDVISIBLEFICHJUST + DaoConstants.SIGNO_COMA);
			queryODR.append(" t7.NOMBRE_FICHERO_088 " + DBConstants.NOMBREFICHJUST + DaoConstants.SIGNO_COMA);
			queryODR.append(" t7.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADOJUST);
		}

		queryODR.append(DaoConstants.FROM + DBConstants.TABLA_83 + " t1 ");
		queryODR.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t2 ");
		queryODR.append(DaoConstants.ON + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL + T2ID_DOC_056);
		queryODR.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t3 ");
		queryODR.append(DaoConstants.ON + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL + " t3.ID_DOC_REL_056 ");
		queryODR.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_87 + " t4 ");
		queryODR.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL
				+ " t4.ID_DOC_ORIG_087 ");
		queryODR.append(DaoConstants.AND + " t4.ID_TAREA_087 " + DaoConstants.SIGNOIGUAL + T1ID_TAREA_083
				+ DaoConstants.CERRAR_PARENTESIS);
		queryODR.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t5 ");
		queryODR.append(DaoConstants.ON + T4ID_FICHERO_TRADUCIDO_087 + DaoConstants.SIGNOIGUAL + " t5.ID_FICHERO_088 ");
		queryODR.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_93 + " t6 ");
		queryODR.append(DaoConstants.ON + " t6.ID_TAREA_093 " + DaoConstants.SIGNOIGUAL + T1ID_TAREA_083);
		queryODR.append(DaoConstants.AND + " t6.ID_DOC_ORIG_093 " + DaoConstants.SIGNOIGUAL + T2ID_DOC_056);
		queryODR.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t7 ");
		queryODR.append(DaoConstants.ON + T6ID_FICHERO_JUSTIFICANTE_093 + DaoConstants.SIGNOIGUAL + T7ID_FICHERO_088);
		queryODR.append(DaoConstants.WHERE + T1ID_TAREA_083 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		queryODR.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS + T2CLASE_DOCUMENTO_056
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.OR + T2CLASE_DOCUMENTO_056
				+ DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.CERRAR_PARENTESIS);
		paramsODR.add(bean.getIdTarea());
		paramsODR.add(ClasificacionDocumentosEnum.REVISION.getValue());
		paramsODR.add(ClasificacionDocumentosEnum.TRABAJO.getValue());

		paginatedQueryODR.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, queryODR));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQueryODR.toString(), paramsODR.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQueryODR.toString(),
					this.getRwMapAa79bDocumentoTareaRevision(), paramsODR.toArray());
		}
	}

	@Override
	public Aa79bSalidaOrigenNoConformidad obtenerOrigenNoConformidad(Aa79bEntradaEjecutarTarea bean) {
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append(DaoConstants.SELECT + T1ID_TIPO_TAREA_081 + DBConstants.IDTIPOTAREA + DaoConstants.SIGNO_COMA);
		query.append(T1ID_TAREA_081 + DBConstants.IDTAREA);
		query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_81 + " t2 ");
		query.append(DaoConstants.ON + T1ID_TAREA_081 + DaoConstants.SIGNOIGUAL + " t2.ID_TAREA_REL_081 ");
		query.append(DaoConstants.AND + " t2.ID_TAREA_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		params.add(bean.getIdTarea());
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(),
				this.getRwMappOrigenNoConformidad());
	}

	@Override
	public Object obtenerDocumentosTraduccionNoConformidad(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, Boolean isCount) {
		StringBuilder queryODTNC = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQueryODTNC = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsODTNC = new ArrayList<Object>();
		if (isCount) {
			queryODTNC.append(DaoConstants.SELECT_COUNT);
		} else {
			queryODTNC.append(DaoConstants.SELECT);
			queryODTNC.append(T1ID_TAREA_083 + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
			queryODTNC.append(T2ID_DOC_056 + DBConstants.IDDOC + DaoConstants.SIGNO_COMA);
			queryODTNC.append(T2TITULO_056 + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(T2OID_FICHERO_056 + DBConstants.OIDFICHERO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(T2EXTENSION_DOC_056 + DBConstants.EXTENSIONDOC + DaoConstants.SIGNO_COMA);
			queryODTNC.append(T2CLASE_DOCUMENTO_056 + DBConstants.CLASEDOCUMENTO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(T2IND_ENCRIPTADO_056 + DBConstants.INDENCRIPTADO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t2.NOMBRE_FICHERO_056 NOMBREDOC, ");

			queryODTNC.append(T3ID_FICHERO_TRADUCIDO_087 + DBConstants.IDFICHEROTRADUCIDO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t4.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADODOCTRAD + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t4.OID_FICHERO_088 " + DBConstants.OIDFICHERODOCTRAD + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t4.TITULO_FICHERO_088 " + DBConstants.TITULODOCTRADUCIDO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t4.NOMBRE_FICHERO_088 NOMBREDOCTRADUCIDO, ");
			queryODTNC
					.append(" t6.ID_FICHERO_TRADUCIDO_087 " + DBConstants.IDFICHEROSUBSANADO + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t7.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADODOCSUBS + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t7.OID_FICHERO_088 " + DBConstants.OIDFICHERODOCSUBS + DaoConstants.SIGNO_COMA);
			queryODTNC.append(" t7.NOMBRE_FICHERO_088 NOMBREDOCSUBSANADO, ");
			queryODTNC.append(" t7.TITULO_FICHERO_088 " + DBConstants.TITULODOCSUBSANADO);
		}

		queryODTNC.append(DaoConstants.FROM + DBConstants.TABLA_83 + " t1 ");
		queryODTNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t2 ");
		queryODTNC.append(DaoConstants.ON + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL + T2ID_DOC_056);
		queryODTNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_87 + " t3 ");
		queryODTNC.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + T2ID_DOC_056 + DaoConstants.SIGNOIGUAL
				+ " t3.ID_DOC_ORIG_087 ");
		queryODTNC.append(DaoConstants.AND + " t3.ID_TAREA_087 " + DaoConstants.SIGNOIGUAL + T1ID_TAREA_083
				+ DaoConstants.CERRAR_PARENTESIS);
		queryODTNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t4 ");
		queryODTNC
				.append(DaoConstants.ON + T3ID_FICHERO_TRADUCIDO_087 + DaoConstants.SIGNOIGUAL + " t4.ID_FICHERO_088 ");
		queryODTNC.append(DaoConstants.JOIN + DBConstants.TABLA_83 + " t5 ");
		queryODTNC.append(DaoConstants.ON + " t1.ID_DOC_083 " + DaoConstants.SIGNOIGUAL + " t5.ID_DOC_083 "
				+ DaoConstants.AND + " t5.ID_TAREA_083 " + DaoConstants.SIGNOIGUAL_INTERROGACION); // idTareaNoConfTrad
		queryODTNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_87 + " t6 ");
		queryODTNC.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + T2ID_DOC_056 + DaoConstants.SIGNOIGUAL
				+ " t6.ID_DOC_ORIG_087 ");
		queryODTNC.append(DaoConstants.AND + " t6.ID_TAREA_087 " + DaoConstants.SIGNOIGUAL_INTERROGACION
				+ DaoConstants.CERRAR_PARENTESIS);
		queryODTNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t7 ");
		queryODTNC
				.append(DaoConstants.ON + " t6.ID_FICHERO_TRADUCIDO_087 " + DaoConstants.SIGNOIGUAL + T7ID_FICHERO_088);
		queryODTNC.append(DaoConstants.WHERE + DBConstants.CLASE_DOCUMENTO_056 + DaoConstants.IN);
		queryODTNC.append(DaoConstants.ABRIR_PARENTESIS + ClasificacionDocumentosEnum.TRADUCCION.getValue()
				+ DaoConstants.SIGNO_COMA + ClasificacionDocumentosEnum.REVISION.getValue() + DaoConstants.SIGNO_COMA
				+ ClasificacionDocumentosEnum.TRABAJO.getValue() + DaoConstants.CERRAR_PARENTESIS);
		queryODTNC.append(DaoConstants.AND + T1ID_TAREA_083 + DaoConstants.SIGNOIGUAL + DaoConstants.ABRIR_PARENTESIS);
		queryODTNC.append(DaoConstants.SELECT + " ID_TAREA_REL_081 " + DaoConstants.FROM + DBConstants.TABLA_81);
		queryODTNC.append(DaoConstants.WHERE + " ID_TAREA_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION
				+ DaoConstants.CERRAR_PARENTESIS);
		paramsODTNC.add(bean.getIdTarea());
		paramsODTNC.add(bean.getIdTarea());
		paramsODTNC.add(bean.getIdTarea());

		paginatedQueryODTNC.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, queryODTNC));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQueryODTNC.toString(), paramsODTNC.toArray(),
					Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQueryODTNC.toString(),
					this.getRwMapAa79bDocumentoOrigenNoConformidadTraduccion(), paramsODTNC.toArray());
		}
	}

	@Override
	public Object obtenerDocumentosRevisionNoConformidad(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, Boolean b, Boolean isCount) {
		StringBuilder queryODRNC = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQueryODRNC = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsODRNC = new ArrayList<Object>();
		if (isCount) {
			queryODRNC.append(DaoConstants.SELECT_COUNT);
		} else {
			queryODRNC.append(DaoConstants.SELECT);
			queryODRNC.append(T1ID_TAREA_083 + DBConstants.IDTAREA + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T2ID_DOC_056 + DBConstants.IDDOC + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T2TITULO_056 + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t2.NOMBRE_FICHERO_056 NOMBREDOC, ");
			queryODRNC.append(T2OID_FICHERO_056 + DBConstants.OIDFICHERO + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T2EXTENSION_DOC_056 + DBConstants.EXTENSIONDOC + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T2CLASE_DOCUMENTO_056 + DBConstants.CLASEDOCUMENTO + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T2IND_ENCRIPTADO_056 + DBConstants.INDENCRIPTADO + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t3.ID_DOC_056 " + DBConstants.IDDOCREL + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t3.TITULO_056 " + DBConstants.TITULOREL + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t3.NOMBRE_FICHERO_056 NOMBREDOCREL, ");
			queryODRNC.append(" t3.OID_FICHERO_056 " + DBConstants.OIDFICHEROREL + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t3.CLASE_DOCUMENTO_056 " + DBConstants.CLASEDOCUMENTOREL + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t3.IND_ENCRIPTADO_056 " + DBConstants.INDENCRIPTADOREL + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T4ID_FICHERO_TRADUCIDO_087 + DBConstants.IDFICHEROREVISADO + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t5.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADOFICHREV + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t5.TITULO_FICHERO_088 " + DBConstants.TITULOFICHREV + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t5.NOMBRE_FICHERO_088 NOMBREFICHREV, ");
			queryODRNC.append(" t5.OID_FICHERO_088 " + DBConstants.OIDFICHEROREV + DaoConstants.SIGNO_COMA);
			queryODRNC.append(T6ID_FICHERO_JUSTIFICANTE_093 + DBConstants.IDFICHJUST + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t6.IND_VISIBLE_093 " + DBConstants.INDVISIBLEFICHJUST + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t7.NOMBRE_FICHERO_088 " + DBConstants.NOMBREFICHJUST + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t7.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADOJUST + DaoConstants.SIGNO_COMA);
			queryODRNC
					.append(" t8.ID_FICHERO_TRADUCIDO_087 " + DBConstants.IDFICHEROSUBSANADO + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t9.IND_ENCRIPTADO_088 " + DBConstants.INDENCRIPTADODOCSUBS + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t9.OID_FICHERO_088 " + DBConstants.OIDFICHERODOCSUBS + DaoConstants.SIGNO_COMA);
			queryODRNC.append(" t9.NOMBRE_FICHERO_088 NOMBREDOCSUBSANADO, ");
			queryODRNC.append(" t9.TITULO_FICHERO_088 " + DBConstants.TITULODOCSUBSANADO);
		}
		queryODRNC.append(DaoConstants.FROM + DBConstants.TABLA_83 + " t1 ");
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t2 ");
		queryODRNC.append(DaoConstants.ON + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL + T2ID_DOC_056);
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_56 + " t3 ");
		queryODRNC.append(DaoConstants.ON + " t2.ID_DOC_056 " + DaoConstants.SIGNOIGUAL + " t3.ID_DOC_REL_056 ");
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_87 + " t4 ");
		queryODRNC.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + T1ID_DOC_083 + DaoConstants.SIGNOIGUAL
				+ " t4.ID_DOC_ORIG_087 ");
		queryODRNC.append(DaoConstants.AND + " t4.ID_TAREA_087 " + DaoConstants.SIGNOIGUAL + T1ID_TAREA_083
				+ DaoConstants.CERRAR_PARENTESIS);
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t5 ");
		queryODRNC
				.append(DaoConstants.ON + T4ID_FICHERO_TRADUCIDO_087 + DaoConstants.SIGNOIGUAL + " t5.ID_FICHERO_088 ");
		queryODRNC.append(DaoConstants.JOIN + DBConstants.TABLA_83 + " t10 ");
		queryODRNC.append(DaoConstants.ON + " t1.ID_DOC_083 " + DaoConstants.SIGNOIGUAL + " t10.ID_DOC_083 "
				+ DaoConstants.AND + " t10.ID_TAREA_083 " + DaoConstants.SIGNOIGUAL_INTERROGACION); // idTareaNoConfTrad
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_93 + " t6 ");
		queryODRNC.append(DaoConstants.ON + " t6.ID_TAREA_093 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		queryODRNC.append(DaoConstants.AND + " t6.ID_DOC_ORIG_093 " + DaoConstants.SIGNOIGUAL + T2ID_DOC_056);
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t7 ");
		queryODRNC.append(DaoConstants.ON + T6ID_FICHERO_JUSTIFICANTE_093 + DaoConstants.SIGNOIGUAL + T7ID_FICHERO_088);
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_87 + " t8 ");
		queryODRNC.append(DaoConstants.ON + DaoConstants.ABRIR_PARENTESIS + T2ID_DOC_056 + DaoConstants.SIGNOIGUAL
				+ " t8.ID_DOC_ORIG_087 ");
		queryODRNC.append(DaoConstants.AND + " t8.ID_TAREA_087 " + DaoConstants.SIGNOIGUAL_INTERROGACION
				+ DaoConstants.CERRAR_PARENTESIS);
		queryODRNC.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_88 + " t9 ");
		queryODRNC.append(
				DaoConstants.ON + " t8.ID_FICHERO_TRADUCIDO_087 " + DaoConstants.SIGNOIGUAL + " t9.ID_FICHERO_088 ");
		queryODRNC.append(DaoConstants.WHERE + "t2." + DBConstants.CLASE_DOCUMENTO_056 + DaoConstants.IN);
		queryODRNC.append(DaoConstants.ABRIR_PARENTESIS + ClasificacionDocumentosEnum.TRADUCCION.getValue()
				+ DaoConstants.SIGNO_COMA + ClasificacionDocumentosEnum.REVISION.getValue() + DaoConstants.SIGNO_COMA
				+ ClasificacionDocumentosEnum.TRABAJO.getValue() + DaoConstants.CERRAR_PARENTESIS);
		queryODRNC.append(DaoConstants.AND + T1ID_TAREA_083 + DaoConstants.SIGNOIGUAL + DaoConstants.ABRIR_PARENTESIS);
		queryODRNC.append(DaoConstants.SELECT + " ID_TAREA_REL_081 " + DaoConstants.FROM + DBConstants.TABLA_81);
		queryODRNC.append(DaoConstants.WHERE + " ID_TAREA_081 " + DaoConstants.SIGNOIGUAL_INTERROGACION
				+ DaoConstants.CERRAR_PARENTESIS);
		paramsODRNC.add(bean.getIdTarea());
		paramsODRNC.add(bean.getIdTarea());
		paramsODRNC.add(bean.getIdTarea());
		paramsODRNC.add(bean.getIdTarea());

		paginatedQueryODRNC.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, queryODRNC));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQueryODRNC.toString(), paramsODRNC.toArray(),
					Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQueryODRNC.toString(),
					this.getRwMapAa79bDocumentoOrigenNoConformidadRevision(), paramsODRNC.toArray());
		}
	}

	@Override
	public Integer validarDocumentosTareaFinalizar(Aa79bEntradaValidarDocumentosTarea bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		Integer resul = 0;
		params.add(bean.getIdTarea());
		query.append(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		if (Long.valueOf(TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()) == bean.getIdTipoTarea() || ((Long
				.valueOf(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue()) == bean.getIdTipoTarea())
				&& (Long.valueOf(TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()) == bean.getIdTipoTareaRel()))) {
			// es de traduccion o de no conformidad proveedor con origen de
			// tarea de traduccion
			this.validarDocsTareaFinPorIdTipoTarea(bean.getIdTarea(), DBConstants.PREFIX_087, DBConstants.TABLA_87,
					DBConstants.ID_FICHERO_TRADUCIDO_087, query);
			query.append(DaoConstants.CERRAR_PARENTESIS);
			resul = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
		} else if (Long.valueOf(TipoTareaGestionAsociadaEnum.REVISAR.getValue()) == bean.getIdTipoTarea() || ((Long
				.valueOf(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue()) == bean.getIdTipoTarea())
				&& (Long.valueOf(TipoTareaGestionAsociadaEnum.REVISAR.getValue()) == bean.getIdTipoTareaRel()))) {
			// es de revision de no conformidad proveedor con origen de tarea de
			// revision
			StringBuilder queryAux = new StringBuilder();
			queryAux.append(query.toString());
			this.validarDocsTareaFinPorIdTipoTarea(bean.getIdTarea(), DBConstants.PREFIX_087, DBConstants.TABLA_87,
					DBConstants.ID_FICHERO_TRADUCIDO_087, query);
			query.append(DaoConstants.CERRAR_PARENTESIS);
			resul = this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
			// el justificante deja de ser obligatorio
			/*
			 * this.validarDocsTareaFinPorIdTipoTarea(bean.getIdTarea(),
			 * DBConstants.PREFIX_093, DBConstants.TABLA_93,
			 * DBConstants.ID_FICHERO_JUSTIFICANTE_093, queryAux);
			 * queryAux.append(DaoConstants.CERRAR_PARENTESIS); resul +=
			 * this.getJdbcTemplate().queryForObject(queryAux.toString(), params.toArray(),
			 * Integer.class);
			 */
		}
		return resul;
	}

	public void validarDocsTareaFinPorIdTipoTarea(BigDecimal idTarea, String prefix, String tabla, String campo,
			StringBuilder query) {
		query.append(DaoConstants.SELECT);
		query.append(" t1." + DBConstants.ID_TAREA_083 + " " + DBConstants.ID_TAREA_083 + DaoConstants.SIGNO_COMA);
		query.append(" t1." + DBConstants.ID_DOC_083 + " " + DBConstants.ID_DOC_083 + DaoConstants.SIGNO_COMA);
		query.append(" t2." + campo + " " + campo + " ");
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_83 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_56 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_DOC_083
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ID_DOC_056);
		query.append(
				DaoConstants.AND + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.CLASE_DOCUMENTO_056 + DaoConstants.IN);
		query.append(DaoConstants.ABRIR_PARENTESIS + ClasificacionDocumentosEnum.TRADUCCION.getValue()
				+ DaoConstants.SIGNO_COMA + ClasificacionDocumentosEnum.REVISION.getValue()
				+ DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.LEFT_JOIN);
		query.append(tabla + " t2 ");
		query.append(DaoConstants.ON);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append("t1." + DBConstants.ID_DOC_083 + " = t2.ID_DOC_ORIG_" + prefix + " ");
		query.append(DaoConstants.AND);
		query.append("t2.ID_TAREA_" + prefix + " = t1." + DBConstants.ID_TAREA_083 + " ");
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.WHERE);
		query.append("t1." + DBConstants.ID_TAREA_083 + " ");
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append("t2." + campo);
		query.append(DaoConstants.IS_NULL);
	}

	@Override
	public List<Aa79bConsultaTareasReport> consultaTareasInformeProveedor(Aa79bEntradaConsultaTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		boolean esInterpretacion = false;
		Locale eu = new Locale("eu");
		Locale es = new Locale("es");
		List<Object> params = new ArrayList<Object>();
		StringBuilder paginatedQuery = new StringBuilder();
		if (bean.getIdTipoTarea() != null
				&& TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == bean.getIdTipoTarea()) {
			esInterpretacion = true;
		}
		StringBuilder query = new StringBuilder();
		this.getSelectConsultaTareasProveedor(false, query, eu, es, esInterpretacion);
		query.append(DaoConstants.FROM + DBConstants.TABLA_81 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_15 + " t2 ");
		query.append(DaoConstants.ON + T1ID_TIPO_TAREA_081 + DaoConstants.SIGNOIGUAL + " t2.ID_015 ");
		this.consultaTareasProveedorJoinTablas(query, esInterpretacion);
		params.add(bean.getDni());
		query.append(this.getWhereConsultaTareasProveedor(bean, params, false));
		this.getWhereAuxConsultaTareasProveedor(false, query, esInterpretacion);
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, false, query));
		return this.getJdbcTemplate().query(query.toString(), this.getRwMapAa79bConsultaTareasReport(),
				params.toArray());
	}

	@Override
	public Aa79bSalidaTarea getDatosTareaEjecutada(BigDecimal idTarea) {
		StringBuilder queryDTE = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		queryDTE.append("SELECT t2." + DBConstants.OBSV_EJECUCION_085 + " " + DBConstants.OBSERVACIONES + ", ");
		queryDTE.append("t3." + DBConstants.HORAS_TAREA_082 + " " + DBConstants.HORASTAREA + ", ");
		queryDTE.append("t3." + DBConstants.POR_USO_EUSKERA_082 + " " + DBConstants.PORUSOEUSKERA);
		queryDTE.append(" FROM " + DBConstants.TABLA_81 + " t1 ");
		queryDTE.append("LEFT JOIN " + DBConstants.TABLA_85 + " t2 ");
		queryDTE.append("ON t1." + DBConstants.ID_TAREA_081 + " = t2." + DBConstants.ID_TAREA_085);
		queryDTE.append(" LEFT JOIN " + DBConstants.TABLA_82 + " t3 ");
		queryDTE.append("ON t1." + DBConstants.ID_TAREA_081 + " = t3." + DBConstants.ID_TAREA_082);
		queryDTE.append(DaoConstants.WHERE_1_1);
		queryDTE.append(SqlUtils.generarWhereIgual("t1." + DBConstants.ID_TAREA_081, idTarea, params));

		Aa79bSalidaTarea salidaTarea = null;
		try {
			salidaTarea = this.getJdbcTemplate().queryForObject(queryDTE.toString(), params.toArray(),
					this.getRWMapDatosTareaEjecutada());
			return salidaTarea;
		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			Aa79bTareaWsDaoImpl.LOGGER.error(erdae.getMessage(), erdae);
			salidaTarea = new Aa79bSalidaTarea();
			return salidaTarea;
		}
	}

	@Override
	public Object obtenerAuditorias(Aa79bEntradaAuditoria bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount) {
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (isCount) {
			query.append(DaoConstants.SELECT_COUNT);
		} else {
			query.append("SELECT tareaRevision.ID_TAREA_081 AS ID_TAREA, NUM_EXP_051 AS NUM_EXP, ANYO_051 AS ANYO, ");
			query.append("SUBSTR(ANYO_051,3,4) || '/' || LPAD(NUM_EXP_051,6,'0') AS ANYONUMEXPCONCATENADO, ");
			query.append("ID_015 AS ID_TIPO_TAREA, DESC_ES_015 AS TIPO_TAREA_ES, DESC_EU_015 AS TIPO_TAREA_EU, ");
			query.append(
					"DNI_077 AS DNI_AUDITOR, SUFDNI_077 AS SUFDNI_AUDITOR, NOMBRE_077 AS NOMBRE_AUDITOR, APEL1_077 AS APEL1_AUDITOR, APEL2_077 AS APEL2_AUDITOR, ");
			query.append(
					"tareaAuditar.ID_TAREA_081 AS ID_TAREA_AUDITAR, ID_LOTE_029 AS ID_LOTE, DESC_LOTE_ES_029 AS DESC_LOTE_ES, DESC_LOTE_EU_029 AS DESC_LOTE_EU, ");
			query.append("ID_0C2 AS ID_AUDITORIA, NVL(ESTADO_AUDITORIA_0C2, 1) ESTADO_AUDITORIA, ");
			query.append("FECHA_AUDITORIA_0C2 AS FECHA_ENVIO, FECHA_CONFIRMACION_0C2 AS FECHA_CONFIRMACION ");
		}
		query.append("FROM AA79B81S01 tareaRevision ");

		query.append(
				"JOIN AA79B51S01 ON tareaRevision.NUM_EXP_081 = NUM_EXP_051 AND tareaRevision.ANYO_081 = ANYO_051 ");
		query.append(
				"JOIN AA79B81S01 tareaAuditar ON ((tareaRevision.ID_TAREA_REL_081 = tareaAuditar.ID_TAREA_081 OR (tareaRevision.ANYO_081 = tareaAuditar.ANYO_081 ");
		query.append(
				"AND tareaRevision.NUM_EXP_081 = tareaAuditar.NUM_EXP_081 AND tareaAuditar.ID_TIPO_TAREA_081 = 4 AND tareaAuditar.ORDEN_081 < tareaRevision.ORDEN_081)) ");
		query.append("AND tareaAuditar.RECURSO_ASIGNACION_081 = 'P' AND tareaAuditar.ESTADO_EJECUCION_081 = 3) ");
		query.append("JOIN AA79B15S01 ON tareaRevision.ID_TIPO_TAREA_081 = ID_015 ");
		query.append("JOIN AA79B29S01 ON tareaAuditar.ID_LOTE_081 = ID_LOTE_029 ");
		query.append(
				"LEFT JOIN AA79BC2S01 ON tareaAuditar.NUM_EXP_081 = NUM_EXP_0C2 AND tareaAuditar.ANYO_081 = ANYO_0C2 AND tareaAuditar.ID_TAREA_081 = ID_TAREA_AUDITAR_0C2 ");
		query.append("JOIN AA79B77S01 ON nvl(DNI_AUDITOR_0C2, tareaRevision.DNI_RECURSO_081) = DNI_077 ");

		query.append("WHERE tareaRevision.ID_TIPO_TAREA_081 in (?,?) ");
		query.append("AND tareaRevision.ESTADO_EJECUCION_081 = ? ");
		query.append("AND IND_ENVIADO_0C2 = ? ");
		params.add(TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue());
		params.add(TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue());
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		params.add(Constants.SI);

		query.append(this.getWhereAuditorias(bean, params));

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapAa79bAuditorias(),
					params.toArray());
		}
	}

	private String getWhereAuditorias(Aa79bEntradaAuditoria bean, List<Object> params) {
		StringBuilder where = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		where.append(SqlUtils.generarWhereIgual("SUBSTR(ANYO_051,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual("NUM_EXP_051", bean.getNumExp(), params));

		if (bean.getEstado() != null) {
			where.append(SqlUtils.generarWhereIgual("NVL(ESTADO_AUDITORIA_0C2, 1)", bean.getEstado(), params));
		} else {
			ArrayList<Integer> estados = new ArrayList<Integer>();
			estados.add(EstadoAuditoriaEnum.ENVIADA.getValue());
			estados.add(EstadoAuditoriaEnum.CONFIRMADA.getValue());
			where.append(SqlUtils.generarWhereIn("NVL(ESTADO_AUDITORIA_0C2, 1)", estados, params));
		}

		return where.toString();
	}

	@Override
	public Aa79bAuditoria getDatosGeneralesAuditoria(Auditoria auditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		query.append(" SELECT ");
		query.append(" t1.ID_0C2 AS IDAUDITORIA, ");
		query.append(" t1.ANYO_0C2 AS ANYO, ");
		query.append(" t1.NUM_EXP_0C2  AS NUMEXP, ");
		query.append(" t1.IND_ENVIADO_0C2  AS INDENVIADO, ");
		query.append(" t1.ID_TAREA_AUDITAR_0C2  AS IDTAREAAUDITAR, ");
		query.append(" t2.DNI_077 AS DNIAUDITOR, ");
		query.append(" t2.PREDNI_077 AS PREDNIAUDITOR, ");
		query.append(" t2.SUFDNI_077 AS SUFDNIAUDITOR, ");
		query.append(" t2.TIPIDEN_077 AS TIPIDENAUDITOR, ");
		query.append(" t2.NOMBRE_077 AS NOMBREAUDITOR, ");
		query.append(" t2.APEL1_077 AS APEL1AUDITOR, ");
		query.append(" t2.APEL2_077 AS APEL2AUDITOR, ");
		query.append(" t1.ESTADO_AUDITORIA_0C2 AS ESTADOAUDITORIA ");
		query.append(DAOUtils.getDecodeAcciones("t1.ESTADO_AUDITORIA_0C2", "ESTADOAUDITORIADESCEU", this.msg,
				"EstadoAuditoriaEnum", eu));
		query.append(DAOUtils.getDecodeAcciones("t1.ESTADO_AUDITORIA_0C2", "ESTADOAUDITORIADESCES", this.msg,
				"EstadoAuditoriaEnum", es));
		query.append(" ,t1.FECHA_AUDITORIA_0C2 AS FECHAAUDITORIA, ");
		query.append(" t1.FECHA_CONFIRMACION_0C2 AS FECHACONFIRMACIONAUDITORIA, ");
		query.append(" t4.ID_LOTE_029 AS IDLOTE, ");
		query.append(" t4.DESC_LOTE_ES_029 AS DESCLOTEES, ");
		query.append(" t4.DESC_LOTE_EU_029 AS DESCLOTEEU, ");
		query.append(" t4.DNI_CONTACTO_029 AS DNICONTACTOLOTE ");
		query.append(" FROM AA79BC2S01 t1 ");
		query.append(" JOIN AA79B77S01 t2 ON t1.DNI_AUDITOR_0C2 = t2.DNI_077 ");
		query.append(" JOIN AA79B81S01 t3 ON t1.ID_TAREA_AUDITAR_0C2 = t3.ID_TAREA_081 ");
		query.append(" JOIN AA79B29S01 t4 ON t3.ID_LOTE_081 = t4.ID_LOTE_029 ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_0C2", auditoria.getIdAuditoria(), params));
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(),
				this.rwMapAuditoriaDatosGenerales);
	}

	@Override
	public Aa79bAuditoria confirmarAuditoria(Aa79bAuditoria auditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		auditoria.setEstado(EstadoAuditoriaEnum.CONFIRMADA.getValue());
		query.append("UPDATE AA79BC2S01 SET ESTADO_AUDITORIA_0C2 = ?, FECHA_CONFIRMACION_0C2 = ?");
		params.add(auditoria.getEstado());
		Date fechaConfirmacion = new Date();
		params.add(fechaConfirmacion);
		auditoria.setFechaConfirmacion(fechaConfirmacion.getTime());
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_0C2", auditoria.getId(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return auditoria;
	}

	@Override
	public Aa79bAuditoriaSeccionExpediente guardarDatosSeccion(Aa79bAuditoriaSeccionExpediente seccion) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		boolean prevSet = false;
		query.append(" UPDATE AA79BC3S01 SET ");
		if (AuditoriaTipoSeccionEnum.VALORACION.getValue().equals(seccion.getTipoSeccion())) {
			query.append(" RESULTADO_AUDITORIA_0C3 = ? ");
			params.add(seccion.getResulAuditoria());
			prevSet = true;
		}
		if (ActivoEnum.SI.getValue().equalsIgnoreCase(seccion.getIndObservaciones())) {
			query.append(prevSet ? "," : "");
			query.append(" OBSERV_0C3 = ? ");
			params.add(seccion.getObserv());
		}
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_AUDITORIA_0C3", seccion.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("ID_SECCION_0C3", seccion.getIdSeccion(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_SECCION_0C3", seccion.getTipoSeccion(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return seccion;
	}

	@Override
	public Aa79bAuditoriaCampoSeccionExpediente guardarDatosCampo(Aa79bAuditoriaCampoSeccionExpediente campo) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		boolean prevSet = false;
		query.append(" UPDATE AA79BC4S01 SET ");
		if (AuditoriaTipoCampoEnum.VALORACION.getValue().equals(campo.getTipoCampo())) {
			query.append(" POR_NIVEL_CALIDAD_0C4 = ? ");
			params.add(campo.getPorNivelCalidad());
			prevSet = true;
		} else if (AuditoriaTipoCampoEnum.CONDICION.getValue().equals(campo.getTipoCampo())) {
			query.append(" IND_MARCADO_0C4 = ? ");
			params.add(campo.getIndMarcado());
			prevSet = true;
		}
		if (ActivoEnum.SI.getValue().equalsIgnoreCase(campo.getIndObservaciones())) {
			query.append(prevSet ? "," : "");
			query.append(" OBSERV_0C4 = ? ");
			params.add(campo.getObserv());
		}
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("ID_AUDITORIA_0C4", campo.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("ID_CAMPO_0C4", campo.getIdCampo(), params));
		query.append(SqlUtils.generarWhereIgual("ID_SECCION_PADRE_0C4", campo.getIdSeccionPadre(), params));
		query.append(SqlUtils.generarWhereIgual("TIPO_CAMPO_0C4", campo.getTipoCampo(), params));
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return campo;
	}

	@Override
	public List<Aa79bAuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		query.append(" SELECT ");
		query.append(" t1.ID_AUDITORIA_0C3 AS IDAUDITORIA, ");
		query.append(" t1.ID_SECCION_0C3 AS IDSECCION, ");
		query.append(" t1.TIPO_SECCION_0C3 AS TIPOSECCION, ");
		query.append(" t1.IND_RESPUESTA_0C3 AS INDRESPUESTA, ");
		query.append(" t1.NOMBRE_EU_0C3 AS NOMBREEU, ");
		query.append(" t1.IND_OBSERVACIONES_0C3 AS INDOBSERVACIONES, ");
		query.append(" t1.ORDEN_0C3 AS ORDEN, ");
		query.append(" t1.VALOR_MIN_APROBADO_0C3 AS VALORMINAPROBADO, ");
		query.append(" t1.VALOR_MIN_PELIGRO_0C3 AS VALORMINPELIGRO, ");
		query.append(" t1.RESULTADO_AUDITORIA_0C3 AS RESULTADOAUDITORIA, ");
		query.append(" t1.NOTA_AUDITORIA_0C3 AS NOTAAUDITORIA, ");
		query.append(" t1.OBSERV_0C3 AS OBSERV, ");
		query.append(" COUNT(t2.ID_CAMPO_0C4) AS CAMPOSCOUNT ");
		query.append(" FROM AA79BC3S01 t1 ");
		query.append(" LEFT JOIN AA79BC4S01 t2 ");
		query.append(" ON t1.ID_AUDITORIA_0C3 = t2.ID_AUDITORIA_0C4 ");
		query.append(" AND t1.ID_SECCION_0C3 = t2.ID_SECCION_PADRE_0C4 ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C3", auditoria.getIdAuditoria(), params));
		query.append(" GROUP BY ");
		query.append(" t1.ID_AUDITORIA_0C3, ");
		query.append(" t1.ID_SECCION_0C3, ");
		query.append(" t1.TIPO_SECCION_0C3, ");
		query.append(" t1.IND_RESPUESTA_0C3, ");
		query.append(" t1.NOMBRE_EU_0C3, ");
		query.append(" t1.IND_OBSERVACIONES_0C3, ");
		query.append(" t1.ORDEN_0C3, ");
		query.append(" t1.VALOR_MIN_APROBADO_0C3, ");
		query.append(" t1.VALOR_MIN_PELIGRO_0C3, ");
		query.append(" t1.RESULTADO_AUDITORIA_0C3, ");
		query.append(" t1.NOTA_AUDITORIA_0C3, ");
		query.append(" t1.OBSERV_0C3 ");
		query.append(" ORDER BY INDRESPUESTA ASC, ORDEN ASC ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapAuditoriaSeccionExpediente, params.toArray());
	}

	@Override
	public List<Aa79bAuditoriaCampoSeccionExpediente> filterCamposSeccion(Aa79bEntradaCamposSeccion seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		getCamposSeccionExpedienteAuditoriaQuery(seccionFilter, params, query, false);
		return this.getJdbcTemplate().query(query.toString(), this.rwMapAuditoriaCampoSeccionExpediente,
				params.toArray());
	}

	@Override
	public Long filterCamposSeccionCount(Aa79bEntradaCamposSeccion seccionFilter, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		getCamposSeccionExpedienteAuditoriaQuery(seccionFilter, params, query, true);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	private void getCamposSeccionExpedienteAuditoriaQuery(Aa79bEntradaCamposSeccion auditSecExp, List<Object> params,
			StringBuilder query, Boolean isCount) {
		if (isCount) {
			query.append(" SELECT COUNT(1)");
		} else {
			query.append(" SELECT ");
			query.append(" t1.ID_AUDITORIA_0C4 AS IDAUDITORIA, ");
			query.append(" t1.ID_CAMPO_0C4 AS IDCAMPO, ");
			query.append(" t1.TIPO_CAMPO_0C4 AS TIPOCAMPO, ");
			query.append(" t1.NOMBRE_EU_0C4 AS NOMBREEU, ");
			query.append(" t1.IND_OBSERVACIONES_0C4 AS INDOBSERVACIONES, ");
			query.append(" t1.NOTA_OK_0C4 AS NOTAOK, ");
			query.append(" t1.NOTA_NO_OK_0C4 AS NOTANOOK, ");
			query.append(" t1.POR_NIVEL_0_0C4 AS PORNIVEL0, ");
			query.append(" t1.POR_NIVEL_1_0C4 AS PORNIVEL1, ");
			query.append(" t1.POR_NIVEL_3_0C4 AS PORNIVEL3, ");
			query.append(" t1.POR_NIVEL_5_0C4 AS PORNIVEL5, ");
			query.append(" t1.IND_OBLIGATORIO_0C4 AS INDOBLIGATORIO, ");
			query.append(" t1.ID_SECCION_PADRE_0C4 AS IDSECCIONPADRE, ");
			query.append(" t1.ORDEN_0C4 AS ORDEN, ");
			query.append(" t1.OBSERV_0C4 AS OBSERV, ");
			query.append(" t1.NIVEL_CALIDAD_0C4 AS NIVELCALIDAD, ");
			query.append(" t1.POR_NIVEL_CALIDAD_0C4 AS PORNIVELCALIDAD, ");
			query.append(" t1.IND_MARCADO_0C4 AS INDMARCADO ");
		}

		query.append(" FROM AA79BC4S01 t1 ");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C4", auditSecExp.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_PADRE_0C4", auditSecExp.getIdSeccion(), params));
		if (!isCount) {
			query.append(" ORDER BY ORDEN ASC");
		}
	}

	@Override
	public List<Aa79bAuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(Aa79bEntradaCamposSeccion filter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		getDocumentosSeccionExpedienteAuditoriaQuery(filter, params, query, false);
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, false, query));
		return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapAuditoriaDocumentoSeccionExpediente,
				params.toArray());
	}

	@Override
	public Long filterDocumentosSeccionCount(Aa79bEntradaCamposSeccion filter, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		getDocumentosSeccionExpedienteAuditoriaQuery(filter, params, query, true);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 *
	 * @param filter Aa79bEntradaCamposSeccion
	 * @param params List<Object>
	 * @param query StringBuilder
	 * @param isCount Boolean
	 */
	private void getDocumentosSeccionExpedienteAuditoriaQuery(Aa79bEntradaCamposSeccion filter, List<Object> params,
			StringBuilder query, Boolean isCount) {
		if (isCount) {
			query.append(" SELECT COUNT(1)");
		} else {
			query.append("SELECT t1.ID_AUDITORIA_0C5 AS IDAUDITORIA,");
			query.append(" t1.ID_SECCION_0C5 AS IDSECCION,");
			query.append(" t1.ID_FICHERO_0C5 AS IDFICHERO, ");
			query.append(" t2.TITULO_FICHERO_088 AS TITULOFICHERO, ");
			query.append(" t2.EXTENSION_FICHERO_088 AS EXTENSIONFICHERO, ");
			query.append(" t2.CONTENT_TYPE_FICHERO_088 AS CONTENTTYPEFICHERO, ");
			query.append(" t2.TAMANO_FICHERO_088 AS TAMANOFICHERO, ");
			query.append(" t2.IND_ENCRIPTADO_088 AS INDENCRIPTADO, ");
			query.append(" t2.RUTA_PIF_FICHERO_088 AS RUTAPIFFICHERO, ");
			query.append(" t2.OID_FICHERO_088 AS OIDFICHERO, ");
			query.append(" t2.NOMBRE_FICHERO_088 AS NOMBREFICHERO ");
		}
		query.append(" FROM AA79BC5S01 t1 ");
		query.append(" JOIN AA79B88S01 t2 ON t1.ID_FICHERO_0C5 = t2.ID_FICHERO_088");
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		query.append(SqlUtils.generarWhereIgual("t1.ID_AUDITORIA_0C5", filter.getIdAuditoria(), params));
		query.append(SqlUtils.generarWhereIgual("t1.ID_SECCION_0C5", filter.getIdSeccion(), params));
	}
}