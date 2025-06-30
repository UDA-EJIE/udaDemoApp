package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
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
import com.ejie.aa79b.dao.mapper.AA79bFindTareasAlbaranRowMapper;
import com.ejie.aa79b.dao.mapper.AA79bObtenerDetalleAlbaranRowMapper;
import com.ejie.aa79b.dao.mapper.Aa79bAlbaranRowMapper;
import com.ejie.aa79b.model.Albaran;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.EntradaAlbaranTareas;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bEntradaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository()
@Transactional()
public class Aa79bAlbaranDaoImpl extends GenericoDaoImpl<Aa79bAlbaran> implements Aa79bAlbaranDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	protected static final String TAREAFACT = "tareaFact";
	protected static final String TAREAREVPAGO = "tareaRevPago";
	protected static final String EJECUCIONTAREA = "ejecucionTarea";
	protected static final String PAGOPROVEEDOR = "pagoProveedor";
	protected static final String TIPOSTAREA = "tiposTarea";
	protected static final String ALBARAN = "albaran";
	protected static final String PUNTO_IND_ALBARAN_094_IGUAL = ".IND_ALBARAN_094 = '";
	protected static final String PUNTO_ESTADO_EJECUCION_081_IGUAL = ".ESTADO_EJECUCION_081 = ";
	protected static final String PUNTO_ID_TAREA_081_IGUAL = ".ID_TAREA_081 = ";
	protected static final String DDMMYY = "DD/mm/YY";

	public Aa79bAlbaranDaoImpl() {
		super(Aa79bAlbaran.class);
	}

	private RowMapper<Aa79bAlbaran> rwMap = new Aa79bAlbaranRowMapper();
	private RowMapper<Aa79bSalidaTarea> rwMapObtenerDetalleAlbaran = new AA79bObtenerDetalleAlbaranRowMapper();
	private RowMapper<Aa79bSalidaTarea> rwMapFindTareasAlbaran = new AA79bFindTareasAlbaranRowMapper();

	private RowMapper<Aa79bSalidaTarea> getrwMapObtenerDetalleAlbaran() {
		return this.rwMapObtenerDetalleAlbaran;
	}

	private RowMapper<Aa79bSalidaTarea> getrwMapFindTareasAlbaran() {
		return this.rwMapFindTareasAlbaran;
	}

	private RowMapper<Aa79bAlbaran> rwMapObtenerBuscadorAlbaran = new RowMapper<Aa79bAlbaran>() {
		@Override
		public Aa79bAlbaran mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Aa79bAlbaran aa79bAlbaran = new Aa79bAlbaran();
			aa79bAlbaran.setIdAlbaran(resultSet.getBigDecimal(DBConstants.IDLOTE));
			aa79bAlbaran.setRefAlbaran(resultSet.getString(DBConstants.REFALBARAN));
			return aa79bAlbaran;

		}
	};

	private RowMapper<Tareas> rwMapDatosTarea = new RowMapper<Tareas>() {
		@Override
		public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tareas tareas = new Tareas();
			tareas.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
			tareas.setAnyo(resultSet.getLong(DBConstants.ANYO));
			tareas.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return tareas;

		}
	};

	private String getWhereTareasAlbaran(Aa79bEntradaTarea tareas, List<Object> params, boolean conIdLote) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual(TAREAFACT + ".ID_TAREA_081", tareas.getIdTarea(), params));
		where.append(SqlUtils.generarWhereIgual("SUBSTR(" + TAREAFACT + ".ANYO_081,3,4)", tareas.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(TAREAFACT + ".NUM_EXP_081", tareas.getNumExp(), params));
		if (conIdLote) {
			where.append(SqlUtils.generarWhereIgual(ALBARAN + ".ID_LOTE_099", tareas.getIdLote(), params));
		}

		where.append(SqlUtils.generarWhereMayorIgualFecha(EJECUCIONTAREA + ".FECHA_EJECUCION_082", DDMMYY,
				DateUtils.obtFechaHoraFormateada(tareas.getFechaIniEjecucion(), Constants.HORA_MINUTOS_CERO), params));

		where.append(SqlUtils.generarWhereMenorIgualFecha(EJECUCIONTAREA + ".FECHA_EJECUCION_082", DDMMYY,
				DateUtils.obtFechaHoraFormateada(tareas.getFechaFinEjecucion(), Constants.HORA_MINUTOS_CERO), params));

		if (StringUtils.isNotBlank(tareas.getEstadoListStr())) {
			String[] aEstados = tareas.getEstadoListStr().split(Constants.COMA);
			where.append(SqlUtils.generarWhereIn("NVL(" + ALBARAN + ".ESTADO_099,1)", aEstados, params));
		}

		if (tareas.getDatosPagoProveedores() != null && tareas.getDatosPagoProveedores().getAlbaran() != null) {

			where.append(SqlUtils.generarWhereIgual(ALBARAN + ".ID_099",
					tareas.getDatosPagoProveedores().getAlbaran().getIdAlbaran(), params));

			where.append(SqlUtils.generarWhereIgual(ALBARAN + ".REF_ALBARAN_099",
					tareas.getDatosPagoProveedores().getAlbaran().getRefAlbaran(), params));

			where.append(SqlUtils.generarWhereMayorIgualFecha(ALBARAN + ".FECHA_ALTA_099", DDMMYY,
					DateUtils.obtFechaHoraFormateada(tareas.getDatosPagoProveedores().getAlbaran().getFechaAltaDesde(),
							Constants.HORA_MINUTOS_CERO),
					params));

			where.append(SqlUtils.generarWhereMenorIgualFecha(ALBARAN + ".FECHA_ALTA_099", DDMMYY,
					DateUtils.obtFechaHoraFormateada(tareas.getDatosPagoProveedores().getAlbaran().getFechaAltaHasta(),
							Constants.HORA_MINUTOS_CERO),
					params));
		}
		if (tareas.getTipoTarea() != null) {
			where.append(SqlUtils.generarWhereIgual(TAREAFACT + ".ID_TIPO_TAREA_081", tareas.getTipoTarea().getId(),
					params));
		}
		return where.toString();
	}

	private String getWhereGeneral(boolean indAlbaranable) {
		StringBuilder where = new StringBuilder(TareasDaoImpl.STRING_BUILDER_INIT);
		where.append(TAREAFACT + ".IND_FACTURABLE_081 = '" + Constants.SI + "'");
		where.append(DaoConstants.BLANK);
		if (indAlbaranable) {
			where.append(DaoConstants.AND);
			where.append(TAREAFACT + PUNTO_ESTADO_EJECUCION_081_IGUAL + Constants.TRES + "");
			where.append(DaoConstants.BLANK);
			where.append(DaoConstants.AND);
			where.append(DaoConstants.BLANK);
			where.append(PAGOPROVEEDOR + PUNTO_IND_ALBARAN_094_IGUAL + Constants.SI + "'");
			where.append(DaoConstants.BLANK);
		}
		return where.toString();
	}

	@Override
	public List<Aa79bAlbaran> obtenerBuscadorAlbaran(Albaran bean) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.SELECT);
		query.append("ID_099 " + DBConstants.IDLOTE + ", ");
		query.append("REF_ALBARAN_099 " + DBConstants.REFALBARAN + " ");
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_99);
		query.append(DaoConstants.WHERE_1_1);
		query.append(DaoConstants.AND);
		query.append("ID_LOTE_099 = ?");
		params.add(bean.getIdLote());

		if (bean.getEstado() != null) {
			query.append(DaoConstants.AND);
			query.append("ESTADO_099 = ?");
			params.add(bean.getEstado());
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMapObtenerBuscadorAlbaran, params.toArray());
	}

	@Override
	public Object findTareasAlbaran(Aa79bEntradaTarea tareas, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount, Boolean pagiantion) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);
		if (pagiantion) {

			if (isCount) {
				query.append(DaoConstants.SELECT + " COUNT " + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ TAREAFACT + ".ID_TAREA_081" + DaoConstants.CERRAR_PARENTESIS);
				query.append(this.getQueryConsultaAlbaran(tareas, params, true));
			} else {
				query.append(this.getQueryConsultaAlbaran(tareas, params, false));
				paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));
			}

			if (isCount) {
				return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
			} else {
				return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getrwMapFindTareasAlbaran(),
						params.toArray());
			}
		} else {
			if (isCount) {
				query.append(DaoConstants.SELECT + " COUNT " + DaoConstants.ABRIR_PARENTESIS + DaoConstants.DISTINCT
						+ TAREAFACT + ".ID_TAREA_081" + DaoConstants.CERRAR_PARENTESIS);
				query.append(this.getQueryConsultaAlbaran(tareas, params, true));
			} else {
				query.append(this.getQueryConsultaAlbaran(tareas, params, false));
				paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

			}

			if (isCount) {
				return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
			} else {
				return this.getJdbcTemplate().query(query.toString(), this.getrwMapFindTareasAlbaran(),
						params.toArray());
			}
		}
	}

	@Override
	public void crearAlbaran(Albaran bean) {
		final Long elId = this.getNextVal("AA79B99Q00");
		String query = "INSERT INTO " + DBConstants.TABLA_99
				+ " (ID_099,REF_ALBARAN_099,ID_LOTE_099,FECHA_ALTA_099,ESTADO_099,REF_FACTURA_099,IMPORTE_FACTURA_099) VALUES (?,?,?,SYSDATE,?,?,?)";
		this.getJdbcTemplate().update(query, elId, bean.getRefAlbaran(), bean.getIdLote(), bean.getEstado(),
				bean.getRefFactura(), bean.getImporteFactura());
	}

	@Override
	public Aa79bSalidaTarea detalleTareaAlbaran(Aa79bEntradaTarea bean) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.SELECT);
		query.append(DBConstants.TIPOSTAREA_ID_015 + " " + DBConstants.TIPOTAREAID + ", ");
		query.append(DBConstants.TIPOSTAREA_DESC_ES_015 + " " + DBConstants.TIPOTAREADESCES + ", ");
		query.append(DBConstants.TIPOSTAREA_DESC_EU_015 + " " + DBConstants.TIPOTAREADESCEU + ", ");
		query.append(TAREAFACT + ".ANYO_081 " + DBConstants.ANYO + ", ");
		query.append(TAREAFACT + ".NUM_EXP_081 " + DBConstants.NUMEXP + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".NUM_TOTAL_PAL_094, 0) as " + DBConstants.NUMTOTALPAL + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".NUM_PAL_CONCOR_0_84_094, 0) as " + DBConstants.NUMPALCONCOR084 + ", ");
		query.append(
				"NVL(" + PAGOPROVEEDOR + ".NUM_PAL_CONCOR_85_94_094, 0) as " + DBConstants.NUMPALCONCOR8594 + ", ");
		query.append(
				"NVL(" + PAGOPROVEEDOR + ".NUM_PAL_CONCOR_95_100_094, 0) as " + DBConstants.NUMPALCONCOR95100 + ", ");
		query.append(PAGOPROVEEDOR + ".IND_RECARGO_FORMATO_094 " + DBConstants.INDRECARGOFORMATO + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".NUM_PAL_RECARGO_FORMATO_094, 0) as " + DBConstants.NUMPALRECARGOFORMATO
				+ ", ");
		query.append(PAGOPROVEEDOR + ".IND_RECARGO_APREMIO_094 " + DBConstants.INDRECARGOAPREMIO + ", ");
		query.append(
				"NVL(" + PAGOPROVEEDOR + ".POR_RECARGO_APREMIO_094, 0) as " + DBConstants.PORRECARGOAPREMIO + ", ");
		query.append(PAGOPROVEEDOR + ".IND_PENALIZACION_094 " + DBConstants.INDPENALIZACION + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".POR_PENALIZACION_094, 0) as " + DBConstants.PORPENALIZACION + ", ");
		query.append(PAGOPROVEEDOR + ".IVA_APLIC_094 " + DBConstants.IVAAPLIC + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_PAL_APLIC_094, 0) as " + DBConstants.IMPORTEPALAPLIC + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".POR_REVISION_APLIC_094, 0) as " + DBConstants.PORREVISIONAPLIC + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_TAREA_094, 0) as " + DBConstants.IMPORTETAREA + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_RECARGO_FORMATO_094, 0) as " + DBConstants.IMPORTERECARGOFORMATO
				+ ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_RECARGO_APREMIO_094, 0) as " + DBConstants.IMPORTERECARGOAPREMIO
				+ ", ");
		query.append(
				"NVL(" + PAGOPROVEEDOR + ".IMPORTE_PENALIZACION_094, 0) as " + DBConstants.IMPORTEPENALIZACION + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMP_PENAL_CALIDAD_094, 0) as IMPORTEPENALCALIDAD, ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_BASE_094, 0) as " + DBConstants.IMPORTEBASE + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_IVA_094, 0) as " + DBConstants.IMPORTEIVA + ", ");
		query.append("NVL(" + PAGOPROVEEDOR + ".IMPORTE_TOTAL_094, 0) as " + DBConstants.IMPORTETOTAL);
		query.append(this.getFromDetalleGeneral());
		query.append(DaoConstants.WHERE_1_1);
		query.append(DaoConstants.AND);
		query.append(this.getWhereGeneral(false));
		query.append(DaoConstants.AND);
		query.append(TAREAFACT + ".ID_TAREA_081 = ?");
		params.add(bean.getIdTarea());

		List<Aa79bSalidaTarea> listAlbaran = this.getJdbcTemplate().query(query.toString(),
				this.getrwMapObtenerDetalleAlbaran(), params.toArray());
		if (!listAlbaran.isEmpty()) {
			return DataAccessUtils.uniqueResult(listAlbaran);
		} else {
			return new Aa79bSalidaTarea();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long existeReferencia(Albaran bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(bean.getRefAlbaran());
		StringBuilder query = new StringBuilder("SELECT COUNT(1)");
		query.append(this.getFrom());
		query.append(DaoConstants.WHERE_1_1);
		query.append(" AND REF_ALBARAN_099 = ?");

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Albaran updateEstadoAlbaran(Albaran bean) {
		String query = "UPDATE " + DBConstants.TABLA_99 + " SET ESTADO_099 = ? WHERE ID_099 = ? AND ID_LOTE_099 = ? ";
		this.getJdbcTemplate().update(query, bean.getEstado(), bean.getIdAlbaran(), bean.getIdLote());
		return bean;
	}

	@Override
	public Tareas obtenerIdTareaPagoProveedorRel(Tareas bean) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(Aa79bTareaWsDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.SELECT);
		query.append("ID_TAREA_081 IDTAREA, ");
		query.append("NUM_EXP_081 NUMEXP, ");
		query.append("ANYO_081 ANYO");
		query.append(DaoConstants.FROM);
		query.append(DBConstants.TABLA_81);
		query.append(DaoConstants.WHERE);
		query.append("ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.REVISION_PAGO_PROVEEDOR.getValue() + " ");
		query.append("AND ID_TAREA_REL_081 = ?");
		params.add(bean.getIdTarea());

		List<Tareas> listTareas = this.getJdbcTemplate().query(query.toString(), this.rwMapDatosTarea,
				params.toArray());
		return DataAccessUtils.uniqueResult(listTareas);
	}

	@Override
	public DatosPagoProveedores asociarAlbaran(DatosPagoProveedores bean) {
		String query = "UPDATE " + DBConstants.TABLA_94 + " SET COD_ALBARAN_094 = ? WHERE ID_TAREA_094 = ? ";
		this.getJdbcTemplate().update(query, bean.getAlbaran().getIdAlbaran(), bean.getIdTarea());
		return bean;
	}

	@Override
	public DatosPagoProveedores desasociarAlbaran(DatosPagoProveedores bean) {
		String query = "UPDATE " + DBConstants.TABLA_94 + " SET COD_ALBARAN_094 = NULL WHERE ID_TAREA_094 = ? ";
		this.getJdbcTemplate().update(query, bean.getIdTarea());
		return bean;
	}

	@Override
	@Transactional(readOnly = true)
	public Long comprobarAsociacionAlbaranPorTarea(EntradaAlbaranTareas bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(bean.getAlbaran().getEstado());
		params.add(bean.getTareas().getIdTarea());
		StringBuilder query = new StringBuilder("SELECT COUNT(1)");
		query.append(this.getFromDetalleGeneral());
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_99);
		query.append(DaoConstants.BLANK);
		query.append(ALBARAN + DaoConstants.ON);
		query.append(PAGOPROVEEDOR + ".COD_ALBARAN_094 = " + ALBARAN + ".ID_099");
		query.append(DaoConstants.WHERE_1_1);
		query.append(DaoConstants.AND);
		query.append(this.getWhereGeneral(true));
		query.append(DaoConstants.AND);
		query.append(" (" + ALBARAN + ".ESTADO_099 <> ? ");
		if (Constants.UNO == bean.getAccionComprobarAsoc()) {
			query.append(" OR " + ALBARAN + ".ESTADO_099 IS NULL");
		}
		query.append(") ");
		query.append(DaoConstants.AND);
		query.append(TAREAFACT + ".ID_TAREA_081 = ?");

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	protected String getSelect() {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT);
		query.append("ID_099 " + DBConstants.IDALBARAN);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("REF_ALBARAN_099 " + DBConstants.REFALBARAN);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("ID_LOTE_099 " + DBConstants.IDLOTE);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("FECHA_ALTA_099 " + DBConstants.FECHAALTA);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("ESTADO_099 " + DBConstants.ESTADO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("REF_FACTURA_099 " + DBConstants.REFFACTURA);
		query.append(DaoConstants.SIGNO_COMA);
		query.append("IMPORTE_FACTURA_099 " + DBConstants.IMPORTEFACTURA);

		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(DaoConstants.FROM);
		from.append(DBConstants.TABLA_99);
		from.append(DaoConstants.BLANK);
		return from.toString();

	}

	private String getFromDetalleGeneral() {
		StringBuilder from = new StringBuilder();
		from.append(DaoConstants.FROM);
		from.append(DBConstants.TABLA_81);
		from.append(DaoConstants.BLANK);
		from.append(TAREAFACT);
		from.append(DaoConstants.JOIN + DBConstants.TABLA_82);
		from.append(DaoConstants.BLANK);
		from.append(EJECUCIONTAREA + DaoConstants.ON);
		from.append(EJECUCIONTAREA + ".ID_TAREA_082 = ");
		from.append(TAREAFACT + ".ID_TAREA_081");
		from.append(DaoConstants.JOIN + DBConstants.TABLA_81);
		from.append(DaoConstants.BLANK);
		from.append(TAREAREVPAGO + DaoConstants.ON);
		from.append(TAREAREVPAGO + ".ID_TIPO_TAREA_081 = " + Constants.ONCE + "");
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.AND);
		from.append(DaoConstants.BLANK);
		from.append(TAREAFACT + PUNTO_ID_TAREA_081_IGUAL + TAREAREVPAGO + ".ID_TAREA_REL_081");

		from.append(DaoConstants.JOIN + DBConstants.TABLA_94);
		from.append(DaoConstants.BLANK);
		from.append(PAGOPROVEEDOR + DaoConstants.ON);
		from.append(TAREAREVPAGO + PUNTO_ID_TAREA_081_IGUAL + PAGOPROVEEDOR + ".ID_TAREA_094");

		from.append(DaoConstants.JOIN + DBConstants.TABLA_15);
		from.append(DaoConstants.BLANK);
		from.append(TIPOSTAREA + DaoConstants.ON);
		from.append(TAREAFACT + ".ID_TIPO_TAREA_081 = " + TIPOSTAREA + ".ID_015");
		return from.toString();

	}

	private String getQueryConsultaAlbaran(Aa79bEntradaTarea tareas, List<Object> params, boolean isCount) {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		StringBuilder query = new StringBuilder();
		if (!isCount) {
			query.append(DaoConstants.SELECT);
			query.append(TAREAFACT + ".ID_TAREA_081 IDTAREAFACT, ");
			query.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DBConstants.TIPOSTAREA_ID_015
					+ DaoConstants.SIGNO_COMA + TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()
					+ DaoConstants.SIGNO_COMA + "'" + this.msg.getMessage("label.traduccionAbr", null, es) + "'"
					+ DaoConstants.SIGNO_COMA + TipoTareaGestionAsociadaEnum.REVISAR.getValue()
					+ DaoConstants.SIGNO_COMA + "'" + this.msg.getMessage("label.revisionAbr", null, eu) + "'"
					+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_CONCATENACION + "'" + DaoConstants.SIGNO_GUION
					+ "'" + DaoConstants.SIGNO_CONCATENACION + TAREAFACT + ".ID_TAREA_081" + DaoConstants.AS
					+ DBConstants.TIPOTAREADESCES + DaoConstants.SIGNO_COMA);
			query.append(DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS + DBConstants.TIPOSTAREA_ID_015
					+ DaoConstants.SIGNO_COMA + TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()
					+ DaoConstants.SIGNO_COMA + "'" + this.msg.getMessage("label.traduccionAbr", null, eu) + "'"
					+ DaoConstants.SIGNO_COMA + TipoTareaGestionAsociadaEnum.REVISAR.getValue()
					+ DaoConstants.SIGNO_COMA + "'" + this.msg.getMessage("label.revisionAbr", null, eu) + "'"
					+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.SIGNO_CONCATENACION + "'" + DaoConstants.SIGNO_GUION
					+ "'" + DaoConstants.SIGNO_CONCATENACION + TAREAFACT + ".ID_TAREA_081" + DaoConstants.AS
					+ DBConstants.TIPOTAREADESCEU + DaoConstants.SIGNO_COMA);
			query.append(TAREAFACT + ".ANYO_081 " + DBConstants.ANYO + ", ");
			query.append(TAREAFACT + ".NUM_EXP_081 " + DBConstants.NUMEXP + ", ");
			query.append(" SUBSTR(" + TAREAFACT + ".ANYO_081,3,4) || '/' || LPAD(" + TAREAFACT + ".NUM_EXP_081,6,'0')"
					+ DBConstants.ANYONUMEXPCONCATENADO + DaoConstants.SIGNO_COMA);
			query.append(TAREAREVPAGO + ".ID_TAREA_081 IDTAREAREVPAGO, ");
			query.append(PAGOPROVEEDOR + ".NUM_TOTAL_PAL_094 NUMTOTALPAL, ");
			query.append(PAGOPROVEEDOR + ".NUM_PAL_CONCOR_0_84_094 NUMPALCONCOR084, ");
			query.append(PAGOPROVEEDOR + ".NUM_PAL_CONCOR_85_94_094 NUMPALCONCOR8594, ");
			query.append(PAGOPROVEEDOR + ".NUM_PAL_CONCOR_95_100_094 NUMPALCONCOR95100, ");
			query.append(
					"NVL(" + PAGOPROVEEDOR + ".IMPORTE_TOTAL_094" + ", 0) as IMPORTETOTAL" + DaoConstants.SIGNO_COMA);
			query.append(ALBARAN + ".REF_ALBARAN_099 REFALBARAN, ");

			query.append("NVL(albaran.ESTADO_099,1)" + DBConstants.ESTADO);
			query.append(DAOUtils.getDecodeAcciones("NVL(albaran.ESTADO_099,1)", DBConstants.ESTADOALBARANDESCES,
					this.msg, "EstadoAlbaranEnum", es));
			query.append(DAOUtils.getDecodeAcciones("NVL(albaran.ESTADO_099,1)", DBConstants.ESTADOALBARANDESCEU,
					this.msg, "EstadoAlbaranEnum", eu));
			query.append(", lote.ID_LOTE_029 IDLOTE, ");
			query.append(" lote.DESC_LOTE_EU_029 DESCLOTEEU,");
			query.append(" lote.DESC_LOTE_ES_029 DESCLOTEES");
		}
		query.append(this.getFromDetalleGeneral());
		query.append(" JOIN AA79B29S01 lote ON tareafact.id_lote_081 = lote.ID_LOTE_029 ");
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_99);
		query.append(DaoConstants.BLANK);
		query.append(ALBARAN + DaoConstants.ON);
		query.append(PAGOPROVEEDOR + ".COD_ALBARAN_094 = " + ALBARAN + ".ID_099 ");

		query.append(DaoConstants.WHERE);
		query.append(this.getWhereGeneral(true));
		query.append(this.getWhereTareasAlbaran(tareas, params, false));

		query.append(DaoConstants.AND + TAREAFACT + ".ID_LOTE_081 = ? ");
		params.add(tareas.getIdLote());
		return query.toString();
	}

	@Override
	public Long comprobarSiTareasAsociadas(BigDecimal idAlbaran) {

		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM + DBConstants.TABLA_94);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_99);
		query.append(DaoConstants.ON + DBConstants.COD_ALBARAN_094 + DaoConstants.SIGNOIGUAL + DBConstants.ID_099);
		query.append(DaoConstants.WHERE + DBConstants.ID_099 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		List<Object> params = new ArrayList<Object>();
		params.add(idAlbaran);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	protected RowMapper<Aa79bAlbaran> getRwMap() {
		return this.rwMap;
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
	protected RowMapper<Aa79bAlbaran> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(Aa79bAlbaran bean, List<Object> params) {
		params.add(bean.getIdAlbaran());
		return " WHERE ID_099 = ? ";
	}

	@Override
	protected String getWhere(Aa79bAlbaran bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(Aa79bAlbaran bean, Boolean startsWith, List<Object> params, Boolean search) {
		return null;
	}

}