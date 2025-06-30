package com.ejie.aa79b.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.CalculoExpedienteFacturacionFilterRowMapper;
import com.ejie.aa79b.dao.mapper.DatosCalculoExpedienteFacturacionRowMapper;
import com.ejie.aa79b.dao.mapper.DatosFacturacionExpedienteImportesRowMapper;
import com.ejie.aa79b.dao.mapper.ExpFacturacionBuscadorRevisionRowMapper;
import com.ejie.aa79b.dao.mapper.ExpedienteFacturacionVariosPagadoresRowMapper;
import com.ejie.aa79b.dao.mapper.FacturacionExpedientesFilterRowMapper;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FacturasExpediente;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.RupTableMultiselectionModel;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoFacturaEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository(value = "expedienteFacturacionDao")
@Transactional
public class ExpedienteFacturacionDaoImpl extends GenericoDaoImpl<ExpedienteFacturacion>
		implements ExpedienteFacturacionDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	protected static final String DECODE_G1IND_VIP_054 = "DECODE(g1.IND_VIP_054";
	protected static final String DDMMYY = "DD/mm/YY";

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ANYONUMEXPCONCATENADO,
			"INDPUBLICADOBOE", DBConstants.IDTIPOEXPEDIENTE, DBConstants.IDRELEVANCIA, DBConstants.IDIDIOMA,
			DBConstants.TARIFAPAL, DBConstants.NUMTOTALPAL, DBConstants.INDURGENTE, DBConstants.INDDIFICIL,
			DBConstants.IMPORTEBASE, DBConstants.IMPORTEIVA, DBConstants.IMPORTETOTAL, DBConstants.FECHAALTA,
			DBConstants.TIPOACTO, DBConstants.NUMINTERPRETES, DBConstants.HORASINTERPRETACION, "IDFACTURA" };

	/*
	 * ROW_MAPPERS - INICIO
	 */
	private RowMapper<ExpedienteFacturacion> rwMap = new RowMapper<ExpedienteFacturacion>() {

		@Override
		public ExpedienteFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return new ExpedienteFacturacion();
		}
	};

	private RowMapper<ExpedienteFacturacion> rwMapPK = new RowMapper<ExpedienteFacturacion>() {
		@Override
		public ExpedienteFacturacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ExpedienteFacturacion expediente = new ExpedienteFacturacion();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			return expediente;
		}
	};

	private RowMapper<ExpedienteFacturacion> rwMapBusqRevision = new ExpFacturacionBuscadorRevisionRowMapper();

	private RowMapper<ExpedienteFacturacion> rwMapFacturacionExpedientesFilter = new FacturacionExpedientesFilterRowMapper();

	private RowMapper<ExpedienteFacturacion> rwMapCalculoExpedienteFacturacionFilter = new CalculoExpedienteFacturacionFilterRowMapper();
	private RowMapper<ExpedienteFacturacion> rwMapExpedienteFacturacionVariosPagadores = new ExpedienteFacturacionVariosPagadoresRowMapper();

	private RowMapper<ExpedienteFacturacion> getFacturacionExpedientesFilterRowMapper() {
		return this.rwMapFacturacionExpedientesFilter;
	}

	private RowMapper<ExpedienteFacturacion> rwMapDatosCalculoExpedienteFacturacion = new DatosCalculoExpedienteFacturacionRowMapper();

	private RowMapper<ExpedienteFacturacion> getDatosCalculoExpedienteFacturacion() {
		return this.rwMapDatosCalculoExpedienteFacturacion;
	}

	private RowMapper<DatosFacturacionExpediente> rwMapDatosFacturacionExpediente = new DatosFacturacionExpedienteImportesRowMapper();

	private RowMapper<DatosFacturacionExpediente> rwMapExpedientesSeleccionadosFacturacion = new RowMapper<DatosFacturacionExpediente>() {

		@Override
		public DatosFacturacionExpediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
			DatosFacturacionExpediente expediente = new DatosFacturacionExpediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			expediente.setImporteBase(resultSet.getBigDecimal("IMPORTEBASE"));
			expediente.setImporteIva(resultSet.getBigDecimal("IMPORTEIVA"));
			expediente.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
			return expediente;
		}

	};

	private RowMapper<Expediente> rwMapIndBoe = new RowMapper<Expediente>() {

		@Override
		public Expediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			ExpedienteTradRev datosTradRev = new ExpedienteTradRev();
			datosTradRev.setIndPublicadoBoe(resultSet.getString("INDBUPLICADOBOE"));
			datosTradRev.setUrlBoe(resultSet.getString("URLBOE"));
			expediente.setExpedienteTradRev(datosTradRev);
			return expediente;
		}

	};

	/*
	 * ROW_MAPPERS - FIN
	 */

	public ExpedienteFacturacionDaoImpl() {
		// Constructor
		super(ExpedienteFacturacion.class);
	}

	@Override
	protected String getSelect() {
		StringBuilder query = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder query = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		return query.toString();
	}

	@Override
	protected RowMapper<ExpedienteFacturacion> getRwMap() {
		return this.rwMap;
	}

	protected RowMapper<ExpedienteFacturacion> getRwMapBusqRevision() {
		return this.rwMapBusqRevision;
	}

	protected RowMapper<ExpedienteFacturacion> getCalculoExpedienteFacturacionTablaFilterRowMapper() {
		return this.rwMapCalculoExpedienteFacturacionFilter;
	}

	protected RowMapper<ExpedienteFacturacion> getExpedienteFacturacionVariosPagadoresRowMapper() {
		return this.rwMapExpedienteFacturacionVariosPagadores;
	}

	@Override
	protected String[] getOrderBy() {
		return ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return "ANYO" + "-" + "NUMEXP";
	}

	@Override
	protected RowMapper<ExpedienteFacturacion> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(ExpedienteFacturacion bean, List<Object> params) {
		StringBuilder wherePK = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		return wherePK.toString();
	}

	@Override
	protected String getWhere(ExpedienteFacturacion bean, List<Object> params) {
		StringBuilder where = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		return where.toString();
	}

	@Override
	protected String getWhereLike(ExpedienteFacturacion bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLike = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		return whereLike.toString();
	}

	@Override
	public List<ExpedienteFacturacion> confeccionarFacturaExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		params.add(filterExpedienteFacturacion.getEntidadContactoFactura().getEntidad().getCodigo());
		params.add(filterExpedienteFacturacion.getEntidadContactoFactura().getEntidad().getTipo());
		if (filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante() != null
				&& !"-1".equals(filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante().getDni())) {
			params.add(filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante().getDni());
		}
		StringBuilder query = new StringBuilder(
				this.getConfeccionarFacturaExpedientesFilterSelect(filterExpedienteFacturacion));
		query.append(this.getConfeccionarFacturaExpedientesFilterFrom(filterExpedienteFacturacion));
		query.append(this.getConfeccionarFacturaExpedientesFilterWhere(filterExpedienteFacturacion));
		if (jqGridRequestDto != null) {
			StringBuilder paginatedQuery = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
			if (jqGridRequestDto.getSidx() != null && !jqGridRequestDto.getSidx().contains("ANYONUMEXPCONCATENADO")) {
				// si el criterio de ordenacion no contiene
				// anyonumexpconcatenado, lo anyadimos para que anyada
				// ordenacion
				// por ese campo de manera ascendente
				jqGridRequestDto.setSidx(
						jqGridRequestDto.getSidx() + " " + jqGridRequestDto.getSord() + ", ANYONUMEXPCONCATENADO ");
				jqGridRequestDto.setSord("ASC");
			}
			paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, false, query));
			return this.getJdbcTemplate().query(paginatedQuery.toString(),
					this.getFacturacionExpedientesFilterRowMapper(), params.toArray());
		}

		return this.getJdbcTemplate().query(query.toString(), this.getFacturacionExpedientesFilterRowMapper(),
				params.toArray());
	}

	private String getConfeccionarFacturaExpedientesFilterSelect(ExpedienteFacturacion filterExpedienteFacturacion) {
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		StringBuilder select = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		select.append(DaoConstants.SELECT);
		select.append(Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO + DBConstants.ANYO
				+ DaoConstants.SIGNO_COMA);
		select.append(Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO + DBConstants.NUMEXP
				+ DaoConstants.SIGNO_COMA);
		select.append(" SUBSTR(t1.ANYO_051,2,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') "
				+ DBConstants.ANYONUMEXPCONCATENADO + DaoConstants.SIGNO_COMA);
		select.append(" t1.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE);
		select.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCES);
		select.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCEU);
		select.append(
				DaoConstants.SIGNO_COMA + " t1.FECHA_ALTA_051 " + DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
		select.append(DaoConstants.TO_CHAR + "(t1.FECHA_ALTA_051,'HH24:MI') " + DBConstants.HORAALTA
				+ DaoConstants.SIGNO_COMA);
		select.append("t1.TITULO_051 ").append(DBConstants.TITULO + DaoConstants.SIGNO_COMA);
		select.append(" T4.POR_FACTURA_058 PORFACTURA, ");
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue() == Integer
				.parseInt(filterExpedienteFacturacion.getIdTipoExpediente())) {
			// traduccion/revision
			select.append(" t3.ID_RELEVANCIA_053 " + DBConstants.IDRELEVANCIA + DaoConstants.SIGNO_COMA);
			select.append(" t3.IND_URGENTE_053 " + DBConstants.INDURGENTE + DaoConstants.SIGNO_COMA);
			select.append(" t3.IND_DIFICIL_053 " + DBConstants.INDDIFICIL + DaoConstants.SIGNO_COMA);
			select.append(" t3.ID_IDIOMA_053 " + DBConstants.IDIDIOMA + DaoConstants.SIGNO_COMA);
			select.append(" t6.TARIFA_PAL_097 " + DBConstants.TARIFAPAL + DaoConstants.SIGNO_COMA);
			select.append(" t6.POR_IVA_097 " + DBConstants.PORIVA + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_TOTAL_PAL_FACTURAR_098 " + DBConstants.NUMTOTALPAL + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_PAL_CONCOR_0_84_098 " + DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_PAL_CONCOR_85_94_098 " + DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_PAL_CONCOR_95_100_098 " + DBConstants.NUMPALCONCOR95100 + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_DIFICULTAD_098 " + DBConstants.IMPORTEDIFICULTAD + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_URGENCIA_098 " + DBConstants.IMPORTEURGENCIA + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_BASE_098 " + DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_IVA_098 " + DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_TOTAL_098 " + DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
			select.append(" t13.DESC_RELEVANCIA_EU_010 " + DBConstants.DESCRELEVANCIAEU + DaoConstants.SIGNO_COMA);
			select.append(" t13.DESC_RELEVANCIA_ES_010 " + DBConstants.DESCRELEVANCIAES + DaoConstants.SIGNO_COMA);
			select.append(" t14.DESC_IDIOMA_EU_009 " + DBConstants.DESCIDIOMAEU + DaoConstants.SIGNO_COMA);
			select.append(" t14.DESC_IDIOMA_ES_009 " + DBConstants.DESCIDIOMAES + DaoConstants.SIGNO_COMA);

			select.append(
					" CASE WHEN T54.TIPO_ENTIDAD_054 = 'B' AND T54.ID_ENTIDAD_054 = ( SELECT VALOR_000 FROM AA79B00S01 WHERE ID_000 = 1 )  ");
			select.append(" THEN DECODE(T1.ID_TIPO_EXPEDIENTE_051,'T',T3.IND_PUBLICADO_BOE_053,NULL) ");
			select.append(" ELSE NULL END AS INDPUBLICADOBOE, ");
			select.append(" '' AS INDPUBLICADOBOEDESCES, ");
			select.append(
					" CASE WHEN T54.TIPO_ENTIDAD_054 = 'B' AND T54.ID_ENTIDAD_054 = ( SELECT VALOR_000 FROM AA79B00S01 WHERE ID_000 = 1 )  ");
			select.append(
					" THEN DECODE(T1.ID_TIPO_EXPEDIENTE_051,'T',DECODE(T3.IND_PUBLICADO_BOE_053,'S','Bai','N','Ez'),NULL) ");
			select.append(" ELSE NULL END AS INDPUBLICADOBOEDESCEU ");
		} else {
			// interpretacion
			select.append(" t2.TIPO_ACTO_052 " + DBConstants.TIPOACTO + DaoConstants.SIGNO_COMA);
			select.append(" t2.FECHA_INI_052 " + DBConstants.FECHAINICIO + DaoConstants.SIGNO_COMA);
			select.append(DaoConstants.TO_CHAR + "(t2.FECHA_INI_052,'HH24:MI') " + DBConstants.HORAINICIO
					+ DaoConstants.SIGNO_COMA);
			select.append(" t2.FECHA_FIN_052 " + DBConstants.FECHAFIN + DaoConstants.SIGNO_COMA);
			select.append(DaoConstants.TO_CHAR + "(t2.FECHA_FIN_052,'HH24:MI') " + DBConstants.HORAFIN
					+ DaoConstants.SIGNO_COMA);
			select.append(" t15.DESC_ES_008 " + DBConstants.TIPOACTODESCES + DaoConstants.SIGNO_COMA);
			select.append(" t15.DESC_EU_008 " + DBConstants.TIPOACTODESCEU + DaoConstants.SIGNO_COMA);
			select.append(" t8.NUM_INTERPRETES_0A3 " + DBConstants.NUMINTERPRETES + DaoConstants.SIGNO_COMA);
			select.append(" t8.HORAS_INTERPRETACION_0A3 " + DBConstants.HORASINTERPRETACION + DaoConstants.SIGNO_COMA);
			select.append(" t11.IMPORTE_BASE_0A6 " + DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
			select.append(" t11.IMPORTE_IVA_0A6 " + DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t11.IMPORTE_TOTAL_0A6 " + DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
			select.append(" t8.POR_IVA_0A3 " + DBConstants.PORIVA);
		}
		return select.toString();
	}

	private String getConfeccionarFacturaExpedientesFilterFrom(ExpedienteFacturacion filterExpedienteFacturacion) {
		StringBuilder from = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		from.append(DaoConstants.FROM + DBConstants.TABLA_51 + " t1 ");
		from.append(DaoConstants.JOIN + DBConstants.TABLA_54 + " t54 ");
		from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t54.ANYO_054 ");
		from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t54.NUM_EXP_054 ");
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue() == Integer
				.parseInt(filterExpedienteFacturacion.getIdTipoExpediente())) {
			// traduccion/revision
			from.append(DaoConstants.JOIN + DBConstants.TABLA_53 + " t3 ");
			from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t3.ANYO_053 ");
			from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t3.NUM_EXP_053 ");
		} else {
			// interpretacion
			from.append(DaoConstants.JOIN + DBConstants.TABLA_52 + " t2 ");
			from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t2.ANYO_052 ");
			from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t2.NUM_EXP_052 ");
			from.append(DaoConstants.JOIN + DBConstants.TABLA_08).append(DaoConstants.BLANK)
					.append(DaoConstants.T15_MINUSCULA).append(DaoConstants.BLANK);
			from.append(DaoConstants.ON + " t15.ID_008 " + DaoConstants.SIGNOIGUAL + " t2.TIPO_ACTO_052 ");
			from.append(DaoConstants.AND + " t15.ESTADO_008 " + DaoConstants.SIGNOIGUAL + "'"
					+ EstadoEnum.ALTA.getValue() + "'");
		}

		from.append(DaoConstants.JOIN + DBConstants.TABLA_58 + " t4 ");
		from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t4.ANYO_058 ");
		from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t4.NUM_EXP_058 ");
		from.append(DaoConstants.JOIN + DBConstants.TABLA_59 + " t5 ");
		from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t5.ANYO_059 ");
		from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t5.NUM_EXP_059 ");
		from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1ESTADO_BITACORA_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t5.ID_ESTADO_BITACORA_059 ");
		from.append(DaoConstants.AND + " t5.ID_ESTADO_EXP_059 " + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
		// release/v3.9.40 descarte de expedientes en estado ANULADO
		from.append(EstadoExpedienteEnum.CERRADO.getValue() + DaoConstants.SIGNO_COMA
				+ EstadoExpedienteEnum.FINALIZADO.getValue() + DaoConstants.CERRAR_PARENTESIS);

		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue() == Integer
				.parseInt(filterExpedienteFacturacion.getIdTipoExpediente())) {
			// traduccion/revision
			from.append(DaoConstants.JOIN + DBConstants.TABLA_97 + " t6 ");
			from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t6.ANYO_097 ");
			from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t6.NUM_EXP_097 ");
			from.append(DaoConstants.JOIN + DBConstants.TABLA_98 + " t7 ");
			from.append(DaoConstants.ON + " t4.ID_058 " + DaoConstants.SIGNOIGUAL + " t7.ID_098 ");
			from.append(DaoConstants.JOIN + DBConstants.TABLA_10 + " t13 ");
			from.append(DaoConstants.ON + " t3.ID_RELEVANCIA_053 " + DaoConstants.SIGNOIGUAL
					+ " t13.ID_TIPO_RELEVANCIA_010 ");
			from.append(DaoConstants.AND + " t13.ESTADO_010 " + DaoConstants.SIGNOIGUAL + "'"
					+ EstadoEnum.ALTA.getValue() + "'");
			from.append(DaoConstants.JOIN + DBConstants.TABLA_09 + " t14 ");
			from.append(DaoConstants.ON + " t3.ID_IDIOMA_053 " + DaoConstants.SIGNOIGUAL + " t14.ID_IDIOMA_009 ");
			from.append(DaoConstants.AND + " t14.ESTADO_009 " + DaoConstants.SIGNOIGUAL + "'"
					+ EstadoEnum.ALTA.getValue() + "'");
		} else {
			// interpretacion
			from.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_A3 + " t8 ");
			from.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t8.ANYO_0A3 ");
			from.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t8.NUM_EXP_0A3 ");
			from.append(" LEFT JOIN AA79BA6S01 t11 ");
			from.append(" ON t11.ID_0A6 = t4.ID_058 ");
		}

		return from.toString();
	}

	private String getConfeccionarFacturaExpedientesFilterWhere(ExpedienteFacturacion filterExpedienteFacturacion) {
		StringBuilder where = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE);

		where.append(" t1.ESTADO_BAJA_051 " + DaoConstants.SIGNOIGUAL + "'" + EstadoEnum.ALTA.getValue() + "'");
		where.append(DaoConstants.AND + " t4.ID_ENTIDAD_ASOC_058 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		where.append(DaoConstants.AND + " t4.TIPO_ENTIDAD_ASOC_058 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		if (filterExpedienteFacturacion.getEntidadContactoFactura() != null
				&& filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante() != null
				&& !"-1".equals(filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante().getDni())) {
			where.append(DaoConstants.AND + " t4.DNI_CONTACTO_058 " + DaoConstants.SIGNOIGUAL_INTERROGACION);
		} else {
			where.append(DaoConstants.AND + " t4.DNI_CONTACTO_058 is null");
		}

		where.append(" AND t1.ANYO_051 >= " + Constants.ANYO_FACTURABLE);
		if (String.valueOf(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue())
				.equals(filterExpedienteFacturacion.getIdTipoExpediente())) {
			where.append(" AND t6.IND_REVISADO_097 = '").append(Constants.SI).append("'");
			where.append(" AND t3.IND_FACTURABLE_053 = '").append(Constants.SI).append("'");
			where.append(" AND CASE ");
			where.append(" WHEN T3.IND_PUBLICADO_BOE_053 = '" + Constants.SI + "'");
			where.append(" THEN 1 ");
			where.append(" WHEN t54.ID_ENTIDAD_054 <> ");
			where.append("  (SELECT VALOR_000 FROM AA79B00S01 WHERE ID_000 = 1)");
			where.append(" THEN 1 ");
			where.append(" ELSE 0 ");
			where.append("  END = 1");
		}

		where.append(" AND NOT EXISTS(SELECT t10.ANYO_0A5, t10.NUM_EXP_0A5 from AA79BA5S01 t10 JOIN AA79BA4S01 t9");
		where.append(" ON t9.ID_FACTURA_0A4         = t10.ID_FACTURA_0A5");
		where.append(" AND t4.TIPO_ENTIDAD_ASOC_058 = t9.TIPO_ENTIDAD_ASOC_0A4");
		where.append(" AND t4.ID_ENTIDAD_ASOC_058   = t9.ID_ENTIDAD_ASOC_0A4");
		where.append(" AND nvl(t4.DNI_CONTACTO_058,-1)      = nvl(t9.DNI_CONTACTO_0A4,-1)");
		where.append(" LEFT JOIN W0512S01 t11");
		where.append(" ON t9.ID_LIQUIDACION_0A4         = t11.T12_REFERENCIA");
		where.append(" WHERE t1.ANYO_051     = t10.ANYO_0A5");
		where.append(" AND t1.NUM_EXP_051 = t10.NUM_EXP_0A5");
		where.append(" AND ( ( t9.ID_LIQUIDACION_0A4 IS NOT NULL");
		where.append(" AND t11.T00_ESTADO_ID             <>" + EstadoFacturaEnum.ANULADA.getValue()
				+ " ) OR (t9.ID_LIQUIDACION_0A4 is null)))");

		return where.toString();
	}

	@Override
	public Long confeccionarFacturaExpedientesFilterCount(ExpedienteFacturacion filterExpedienteFacturacion,
			Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		params.add(filterExpedienteFacturacion.getEntidadContactoFactura().getEntidad().getCodigo());
		params.add(filterExpedienteFacturacion.getEntidadContactoFactura().getEntidad().getTipo());
		if (filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante() != null
				&& !"-1".equals(filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante().getDni())) {
			params.add(filterExpedienteFacturacion.getEntidadContactoFactura().getSolicitante().getDni());
		}
		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getConfeccionarFacturaExpedientesFilterFrom(filterExpedienteFacturacion));
		query.append(this.getConfeccionarFacturaExpedientesFilterWhere(filterExpedienteFacturacion));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Object revisionFacturacionExpedientesFilter(ExpedienteFacturacion filterExpedienteFacturacion,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, boolean isCount) {

		StringBuilder paginatedQuery = new StringBuilder();

		List<Object> params = new ArrayList<Object>();
		StringBuilder select = getQueryBuscadorRevisionFacturacion(filterExpedienteFacturacion, startsWith, isCount,
				params);

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(select.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapBusqRevision(),
					params.toArray());
		}

	}

	/**
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param startsWith                  Boolean
	 * @param isCount                     boolean
	 * @param params                      List<Object>
	 * @return StringBuilder
	 */
	private StringBuilder getQueryBuscadorRevisionFacturacion(ExpedienteFacturacion filterExpedienteFacturacion,
			Boolean startsWith, boolean isCount, List<Object> params) {
		StringBuilder select = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);

		select.append("SELECT ");

		if (isCount) {
			select.append(" COUNT(DISTINCT SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )) ");
		} else {
			select.append(getSelectRevisionFacturacion());
			select.append(
					" , NVL(N1.NUM_TOTAL_PAL_090,NVL(R1.NUM_TOTAL_PAL_IZO_053,NVL(NUM_TOTAL_PAL_SOLIC_053,0) ) ) AS NUMPALCOLORDER ");
		}
		// FROM
		select.append(getFromRevisionFacturacion());

		/* WHERE */
		select.append(DaoConstants.WHERE_1_1);
		select.append(SqlUtils.generarWhereIgual("r1.IND_FACTURABLE_053",
				filterExpedienteFacturacion.getExpedienteTradRev().getIndFacturable(), params));
		select.append(
				SqlUtils.generarWhereIgual(" b1.ID_ESTADO_EXP_059 ", EstadoExpedienteEnum.CERRADO.getValue(), params));
		select.append(SqlUtils.generarWhereIgual(" b1.ID_FASE_EXPEDIENTE_059 ",
				FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue(), params));

		// filtros formulario busqueda pantalla
		select.append(getWherebusquedarevision(filterExpedienteFacturacion, params, startsWith));

		return select;
	}

	/**
	 * @return String
	 */
	private String getFromTabla51() {
		StringBuilder query = new StringBuilder();
		query.append(" FROM AA79B51S01 t1 ");

		return query.toString();
	}

	/**
	 * @return String
	 */
	private String getFromRevisionFacturacionGenerica() {
		StringBuilder select = new StringBuilder();

		// 54 comun
		select.append(" JOIN AA79B54S01 g1 ON t1.ANYO_051 = g1.ANYO_054 AND t1.NUM_EXP_051 = g1.NUM_EXP_054 ");
		// 59 comun
		select.append(" JOIN AA79B59S01 b1 ON t1.ANYO_051 = b1.ANYO_059 AND t1.NUM_EXP_051 = b1.NUM_EXP_059 ");
		select.append(" AND t1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 ");
		// 77 comun
		select.append(" JOIN AA79B77S01 a1 ON g1.DNI_SOLICITANTE_054 = a1.DNI_077 ");
		// X54JAPI_ENTIDADES_SOLIC comun
		select.append(
				" JOIN X54JAPI_ENTIDADES_SOLIC e1 ON g1.TIPO_ENTIDAD_054 = e1.TIPO AND g1.ID_ENTIDAD_054  = e1.CODIGO ");
		// 81 comun
		select.append(" LEFT JOIN AA79B81S01 d1 ON t1.ANYO_051 = d1.ANYO_081 AND t1.NUM_EXP_051 = d1.NUM_EXP_081 ");
		select.append("AND d1.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue() + " ");
		// 90 comun
		select.append(" LEFT JOIN AA79B90S01 n1 ON d1.ID_TAREA_081  = n1.ID_TAREA_090 ");

		// Entidades y contactos de facturación.
		select.append(" LEFT JOIN ( SELECT jj1.ANYO_058, jj1.NUM_EXP_058 ");
		select.append(
				", LISTAGG( CASE WHEN jj1.DNI_CONTACTO_058 IS NULL THEN '<span class=\"nomb-entidad\">' || jj3.DESC_ES || '</span><br />' ELSE '<span class=\"nomb-entidad\">' || jj3.DESC_ES  || ' - <span class=\"nomb-gestor\">' ||  jj2.APEL1_077 || ' ' || jj2.APEL2_077 || ', ' || jj2.NOMBRE_077 || '</span></span><br />' END,'') WITHIN GROUP (ORDER BY jj3.DESC_ES) DATOSFACTURACIONCONCATENADOSES ");
		select.append(
				", LISTAGG( CASE WHEN jj1.DNI_CONTACTO_058 IS NULL THEN '<span class=\"nomb-entidad\">' || jj3.DESC_EU || '</span><br />' ELSE '<span class=\"nomb-entidad\">' || jj3.DESC_EU  || ' - <span class=\"nomb-gestor\">' ||  jj2.APEL1_077 || ' ' || jj2.APEL2_077 || ', ' || jj2.NOMBRE_077 || '</span></span><br />' END,'') WITHIN GROUP (ORDER BY jj3.DESC_EU) DATOSFACTURACIONCONCATENADOSEU ");
		select.append(" FROM AA79B58S01 jj1 ");
		select.append(" LEFT JOIN AA79B77S01 jj2 ");
		select.append(" ON jj1.DNI_CONTACTO_058 = jj2.DNI_077 ");
		select.append(" LEFT JOIN X54JAPI_ENTIDADES_SOLIC jj3 ");
		select.append(" ON jj1.TIPO_ENTIDAD_ASOC_058  = jj3.TIPO ");
		select.append(" AND jj1.ID_ENTIDAD_ASOC_058   = jj3.CODIGO ");
		select.append(" GROUP BY jj1.ANYO_058, jj1.NUM_EXP_058 ");
		select.append(" ) jj1 ");
		select.append(" ON T1.ANYO_051     = jj1.ANYO_058 ");
		select.append(" AND T1.NUM_EXP_051 = jj1.NUM_EXP_058 ");
		select.append(" JOIN AA79B00S01 g2 ");
		select.append(" ON ID_000 = 1 ");
		return select.toString();
	}

	/**
	 * @return String
	 */
	private String getFromRevisionFacturacion() {
		StringBuilder select = new StringBuilder();

		select.append(getFromTabla51());

		// 53 trad/rev
		select.append(getJoinTabla53And51());

		// comun
		select.append(getFromRevisionFacturacionGenerica());

		return select.toString();
	}

	/**
	 * @return String
	 */
	private String getJoinTabla53And51() {
		StringBuilder select = new StringBuilder();

		// 53 trad/rev
		select.append(" JOIN AA79B53S01 r1 ON t1.ANYO_051 = r1.ANYO_053 AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");

		return select.toString();
	}

	/**
	 * @return String
	 */
	private String getSelectRevisionFacturacion() {
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		StringBuilder select = new StringBuilder();

		// 51 comun
		select.append(" DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
		select.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
		select.append(
				", SUBSTR(t1.ANYO_051,2,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') " + DBConstants.ANYONUMEXPCONCATENADO);
		select.append(", t1.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE);
		select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCES);
		select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS
						+ DBConstants.TIPOEXPEDIENTEDESCEU);
		select.append(", t1.TITULO_051 ").append(DBConstants.TITULO);

		// BOPV
		select.append(", r1.IND_PUBLICAR_BOPV_053 " + DBConstants.INDPUBLICARBOPV);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS
				+ "R1.IND_PUBLICAR_BOPV_053");
		select.append(DaoConstants.SIGNO_COMA + "'" + Constants.SI + "'" + DaoConstants.SIGNO_COMA + "'"
				+ this.msg.getMessage("comun.si", null, es) + "' ");
		select.append(DaoConstants.SIGNO_COMA + "'" + Constants.NO + "'" + DaoConstants.SIGNO_COMA + "'"
				+ this.msg.getMessage("comun.no", null, es) + "' ");
		select.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + " " + DBConstants.PUBLICARBOPVDESCES);

		select.append(DaoConstants.SIGNO_COMA + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS
				+ "R1.IND_PUBLICAR_BOPV_053");
		select.append(DaoConstants.SIGNO_COMA + "'" + Constants.SI + "'" + DaoConstants.SIGNO_COMA + "'"
				+ this.msg.getMessage("comun.si", null, eu) + "' ");
		select.append(DaoConstants.SIGNO_COMA + "'" + Constants.NO + "'" + DaoConstants.SIGNO_COMA + "'"
				+ this.msg.getMessage("comun.no", null, eu) + "' ");
		select.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + " " + DBConstants.PUBLICARBOPVDESCEU);

		select.append(
				", CASE WHEN G1.TIPO_ENTIDAD_054 ='B' AND G1.ID_ENTIDAD_054 = (SELECT VALOR_000 FROM AA79B00S01 WHERE ID_000 = 1 ) THEN ");
		select.append(" DECODE(T1.ID_TIPO_EXPEDIENTE_051,'T',R1.IND_PUBLICADO_BOE_053,NULL) ");
		select.append(" ELSE  NULL ");
		select.append(" END AS INDPUBLICADOBOE ");
		select.append(", '-' AS INDPUBLICADOBOEDESCES ");
		select.append(
				" ,NVL(CASE WHEN G1.TIPO_ENTIDAD_054 ='B' AND G1.ID_ENTIDAD_054 = (SELECT VALOR_000 FROM AA79B00S01 WHERE ID_000 = 1 ) THEN ");
		select.append(
				" DECODE(T1.ID_TIPO_EXPEDIENTE_051,'T',DECODE(R1.IND_PUBLICADO_BOE_053,'S','Bai','N','Ez'),NULL) ");
		select.append(" ELSE NULL ");
		select.append(" END, '-') AS INDPUBLICADOBOEDESCEU ");

		// num total palabras izo
		select.append(", NVL(r1.NUM_TOTAL_PAL_IZO_053,r1.NUM_TOTAL_PAL_SOLIC_053) " + DBConstants.NUMTOTALPALIZO);
		select.append(", n1.NUM_TOTAL_PAL_090 " + DBConstants.NUMTOTALPALTRADOS);
		select.append(", n1.NUM_PAL_CONCOR_0_84_090 " + DBConstants.NUMPALCONCOR084090);
		select.append(", n1.NUM_PAL_CONCOR_85_94_090 " + DBConstants.NUMPALCONCOR8594090);
		select.append(", n1.NUM_PAL_CONCOR_95_100_090 " + DBConstants.NUMPALCONCOR95100090);
		select.append(", n1.NUM_PAL_CONCOR_95_99_090 " + DBConstants.NUMPALCONCOR9599090);
		select.append(", n1.NUM_PAL_CONCOR_100_090 " + DBConstants.NUMPALCONCOR100090);

		// GESTOR comun
		select.append(", g1.DNI_SOLICITANTE_054 " + DBConstants.DNIGESTOR);
		select.append(", a1.NOMBRE_077 " + DBConstants.NOMBREGESTOR);
		select.append(", a1.APEL1_077 " + DBConstants.APEL1GESTOR);
		select.append(", a1.APEL2_077 " + DBConstants.APEL2GESTOR);
		select.append(", a1.PREDNI_077 " + DBConstants.PREDNIGESTOR);
		select.append(", a1.SUFDNI_077 " + DBConstants.SUFDNIGESTOR);
		select.append(", (a1.PREDNI_077 || g1.DNI_SOLICITANTE_054 || a1.SUFDNI_077)" + DaoConstants.AS
				+ DBConstants.DNICOMPLETOGESTOR);
		select.append(", g1.TIPO_ENTIDAD_054 " + DBConstants.TIPOENTIDADGESTOR);
		select.append(", g1.ID_ENTIDAD_054 " + DBConstants.IDENTIDADGESTOR);
		select.append(", g1.IND_VIP_054 " + DBConstants.INDVIPGESTOR);
		select.append(", " + DECODE_G1IND_VIP_054 + ", NULL,'NO','N','NO','S','SI')" + DaoConstants.AS
				+ DBConstants.GESTOREXPEDIENTESVIPDESCES);
		select.append(", " + DECODE_G1IND_VIP_054 + ", NULL,'EZ','N','EZ','S','BAI')" + DaoConstants.AS
				+ DBConstants.GESTOREXPEDIENTESVIPDESCEU);

		select.append(", e1.CIF " + DBConstants.CIFENTIDADGESTOR);
		select.append(", e1.DESC_ES " + DBConstants.DESCENTIDADESGESTOR);
		select.append(", e1.DESC_EU " + DBConstants.DESCENTIDADEUGESTOR);
		select.append(", e1.DESC_AMP_ES " + DBConstants.DESCAMPENTIDADESGESTOR);
		select.append(", e1.DESC_AMP_EU " + DBConstants.DESCAMPENTIDADEUGESTOR);

		select.append(", DATOSFACTURACIONCONCATENADOSES ");
		select.append(", DATOSFACTURACIONCONCATENADOSEU ");

		// GESTORCOLORDEREU
		select.append("," + SqlUtils.normalizarCampoSql("e1.DESC_EU") + " || "
				+ SqlUtils.normalizarCampoSql("a1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("a1.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("a1.NOMBRE_077") + " GESTORCOLORDEREU");
		// GESTORCOLORDERES
		select.append("," + SqlUtils.normalizarCampoSql("e1.DESC_ES") + " || "
				+ SqlUtils.normalizarCampoSql("a1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("a1.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("a1.NOMBRE_077") + " GESTORCOLORDERES");

		return select.toString();
	}

	private String getWherebusquedarevision(ExpedienteFacturacion filter, List<Object> params, boolean startsWith) {
		// DATOS GENERALES DEL EXPEDIENTE
		StringBuilder where = getWhereBusquedaDatosExp(filter, params);

		if (filter.getExpedienteTradRev() != null) {
			// DATOS EXPEDIENTE TRADUCCIÓN/REVISIÓN
			where.append(getWhereBusquedaExpTradRev(filter, params));
		}

		// GESTOR EXPEDIENTE
		if (filter.getGestorExpediente() != null && QueryUtils.entidadSolicitanteValida(filter.getGestorExpediente())) {
			where.append(getWhereBusquedaGestorExp(filter, params));
		}

		// Entidad y contacto de facturación
		whereBusquedaRevisionFacturacion(filter, params, where);
		return where.toString();
	}

	/**
	 * @param filter ExpedienteFacturacion
	 * @param params List<Object>
	 * @return String
	 */
	private String getWhereBusquedaExpTradRev(ExpedienteFacturacion filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		// FECHA ENTREGA IZO
		where.append(SqlUtils.generarWhereMayorIgualFecha("r1.FECHA_FINAL_IZO_053", DDMMYY,
				filter.getExpedienteTradRev().getFechaEntregaIzoDesde(), params));
		where.append(SqlUtils.generarWhereMenorIgualFecha("r1.FECHA_FINAL_IZO_053", DDMMYY,
				filter.getExpedienteTradRev().getFechaEntregaIzoHasta(), params));

		where.append(getWhereComunExpTradRev(filter, params));

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @return
	 */
	private String getWhereComunExpTradRev(ExpedienteFacturacion filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		// INDICADORES:
		// PUBLICAR EN BOPV
		where.append(SqlUtils.generarWhereIgual("r1.IND_PUBLICAR_BOPV_053",
				filter.getExpedienteTradRev().getIndPublicarBopv(), params));
		// PUBLICADO EN BOE
		where.append(SqlUtils.generarWhereIgual("r1.IND_PUBLICADO_BOE_053",
				filter.getExpedienteTradRev().getIndPublicadoBoe(), params));
		// IDIOMA A REVISAR/DESTINO DE LA TRADUCCIÓN
		where.append(
				SqlUtils.generarWhereIgual("r1.ID_IDIOMA_053", filter.getExpedienteTradRev().getIdIdioma(), params));

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param where
	 */
	private void whereBusquedaRevisionFacturacion(ExpedienteFacturacion filter, List<Object> params,
			StringBuilder where) {
		boolean isWhere = false;
		if (filter.getEntidadContactoFactura() != null) {
			where.append(" AND 0 < (SELECT COUNT (1) FROM AA79B58S01 sc58 ");
			if (filter.getEntidadContactoFactura().getEntidad() != null) {
				if (filter.getEntidadContactoFactura().getEntidad().getFacturable() != null) {
					where.append(" JOIN X54JAPI_ENTIDADES_SOLIC scaps ");
					where.append(" ON sc58.TIPO_ENTIDAD_ASOC_058  = scaps.TIPO ");
					where.append(" AND sc58.ID_ENTIDAD_ASOC_058   = scaps.CODIGO ");
				}
				where.append(" WHERE sc58.ANYO_058 = t1.ANYO_051 ");
				where.append(" AND sc58.NUM_EXP_058 = t1.NUM_EXP_051 ");
				isWhere = true;
				if (filter.getEntidadContactoFactura().getEntidad().getTipo() != null && !Constants.MINUS_UNO.toString()
						.equals(filter.getEntidadContactoFactura().getEntidad().getTipo())) {
					where.append(SqlUtils.generarWhereIgual("sc58.TIPO_ENTIDAD_ASOC_058",
							filter.getEntidadContactoFactura().getEntidad().getTipo(), params));
				}
				where.append(SqlUtils.generarWhereIgual("sc58.ID_ENTIDAD_ASOC_058",
						filter.getEntidadContactoFactura().getEntidad().getCodigo(), params));
				if (filter.getEntidadContactoFactura().getEntidad().getFacturable() != null) {
					where.append(SqlUtils.generarWhereIgual("scaps.FACTURABLE",
							filter.getEntidadContactoFactura().getEntidad().getFacturable(), params));
				}

			}
			if (filter.getEntidadContactoFactura().getSolicitante() != null) {
				if (!isWhere) {
					where.append(" WHERE sc58.ANYO_058 = t1.ANYO_051 ");
					where.append(" AND sc58.NUM_EXP_058 = t1.NUM_EXP_051 ");
				}
				where.append(SqlUtils.generarWhereIgual("sc58.DNI_CONTACTO_058",
						filter.getEntidadContactoFactura().getSolicitante().getDni(), params));
			}

			where.append(" ) ");
		}
	}

	@Override
	public List<ExpedienteFacturacion> borradorFacturaTablaFilter(ExpedienteFacturacion filterBorradorFacturaTabla,
			JQGridRequestDto jqGridRequestDto, List<Expediente> expedientesList, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		params.add(filterBorradorFacturaTabla.getEntidadContactoFactura().getEntidad().getCodigo());
		params.add(filterBorradorFacturaTabla.getEntidadContactoFactura().getEntidad().getTipo());
		if (filterBorradorFacturaTabla.getEntidadContactoFactura().getSolicitante() != null
				&& !"-1".equals(filterBorradorFacturaTabla.getEntidadContactoFactura().getSolicitante().getDni())) {
			params.add(filterBorradorFacturaTabla.getEntidadContactoFactura().getSolicitante().getDni());
		}
		StringBuilder query = new StringBuilder(
				this.getConfeccionarFacturaExpedientesFilterSelect(filterBorradorFacturaTabla));
		query.append(this.getConfeccionarFacturaExpedientesFilterFrom(filterBorradorFacturaTabla));
		query.append(this.getConfeccionarFacturaExpedientesFilterWhere(filterBorradorFacturaTabla));
		query.append(this.borradorFacturaWhereExpedientes(expedientesList));
		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
		}

		return this.getJdbcTemplate().query(query.toString(), this.getFacturacionExpedientesFilterRowMapper(),
				params.toArray());
	}

	private String borradorFacturaWhereExpedientes(List<Expediente> expedientesList) {
		StringBuilder whereExpedientes = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		whereExpedientes.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
		for (Expediente expediente : expedientesList) {
			whereExpedientes.append(DaoConstants.ABRIR_PARENTESIS + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051
					+ Constants.ESPACIO + DaoConstants.SIGNOIGUAL + expediente.getAnyo() + DaoConstants.AND);
			whereExpedientes.append(Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + expediente.getNumExp() + DaoConstants.CERRAR_PARENTESIS);
			whereExpedientes.append(DaoConstants.OR);
		}
		whereExpedientes.delete(whereExpedientes.length() - Constants.TRES, whereExpedientes.length());
		whereExpedientes.append(DaoConstants.CERRAR_PARENTESIS);

		return whereExpedientes.toString();
	}

	@Override
	public Long borradorFacturaTablaFilterCount(ExpedienteFacturacion filterBorradorFacturaTabla,
			List<Expediente> expedientesList, Boolean b) {
		List<Object> params = new ArrayList<Object>();
		params.add(filterBorradorFacturaTabla.getEntidadContactoFactura().getEntidad().getCodigo());
		params.add(filterBorradorFacturaTabla.getEntidadContactoFactura().getEntidad().getTipo());
		if (filterBorradorFacturaTabla.getEntidadContactoFactura().getSolicitante() != null
				&& !"-1".equals(filterBorradorFacturaTabla.getEntidadContactoFactura().getSolicitante().getDni())) {
			params.add(filterBorradorFacturaTabla.getEntidadContactoFactura().getSolicitante().getDni());
		}
		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(this.getConfeccionarFacturaExpedientesFilterFrom(filterBorradorFacturaTabla));
		query.append(this.getConfeccionarFacturaExpedientesFilterWhere(filterBorradorFacturaTabla));
		query.append(this.borradorFacturaWhereExpedientes(expedientesList));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public void realizarCalculoInterpretacion(final ExpedienteFacturacion expedienteFacturacion) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.NUMERIC));
		paramList.add(new SqlParameter(Types.NUMERIC));

		this.getJdbcTemplate().call(new CallableStatementCreator() {
			@Override()
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call CALCULO_IMPORTES_INTER(?,?)}");
				callableStatement.setLong(1, expedienteFacturacion.getAnyo());
				callableStatement.setInt(2, expedienteFacturacion.getNumExp());
				return callableStatement;
			}
		}, paramList);

	}

	@Override
	public ExpedienteFacturacion obtenerDatosCalculoExpedienteFacturacion(ExpedienteFacturacion expedienteFacturacion,
			boolean filtroEstado) {
		StringBuilder query = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();
		query.append(this.getObtenerDatosCalculoExpedienteFacturacionSelect(expedienteFacturacion));
		query.append(this.getObtenerDatosCalculoExpedienteFacturacionFrom(expedienteFacturacion, filtroEstado));
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual("t1.ANYO_051", expedienteFacturacion.getAnyo(), params));
		query.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_051", expedienteFacturacion.getNumExp(), params));

		List<ExpedienteFacturacion> lista = this.getJdbcTemplate().query(query.toString(), params.toArray(),
				this.getDatosCalculoExpedienteFacturacion());
		if (lista != null && !lista.isEmpty()) {
			return DataAccessUtils.uniqueResult(lista);
		} else {
			return new ExpedienteFacturacion();
		}
	}

	private String getObtenerDatosCalculoExpedienteFacturacionFrom(ExpedienteFacturacion expedienteFacturacion,
			boolean filtroEstado) {
		StringBuilder query = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.FROM + DBConstants.TABLA_51 + " t1 ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_54 + " t2 ");
		query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t2.ANYO_054 ");
		query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
				+ DaoConstants.SIGNOIGUAL + " t2.NUM_EXP_054 ");
		query.append(DaoConstants.JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + " t3 ");
		query.append(DaoConstants.ON + " t2.TIPO_ENTIDAD_054 " + DaoConstants.SIGNOIGUAL + " t3.TIPO ");
		query.append(DaoConstants.AND + " t2.ID_ENTIDAD_054 " + DaoConstants.SIGNOIGUAL + " t3.CODIGO ");
		query.append(DaoConstants.JOIN + DBConstants.TABLA_77 + " t4 ");
		query.append(DaoConstants.ON + " t2.DNI_SOLICITANTE_054 " + DaoConstants.SIGNOIGUAL + " t4.DNI_077 ");
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode().equals(expedienteFacturacion.getIdTipoExpediente())) {
			// trad/rev
			query.append(DaoConstants.JOIN + DBConstants.TABLA_53 + " t5 ");
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t5.ANYO_053 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t5.NUM_EXP_053 ");
			query.append(DaoConstants.AND + " t5.IND_FACTURABLE_053 " + DaoConstants.SIGNOIGUAL + "'"
					+ ActivoEnum.SI.getValue() + "'");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_09 + " t6 ");
			query.append(DaoConstants.ON + " t5.ID_IDIOMA_053 " + DaoConstants.SIGNOIGUAL + " t6.ID_IDIOMA_009 ");
			query.append(DaoConstants.AND + " t6.ESTADO_009 " + DaoConstants.SIGNOIGUAL + "'"
					+ EstadoEnum.ALTA.getValue() + "'");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_10 + " t7 ");
			query.append(DaoConstants.ON + " t5.ID_RELEVANCIA_053 " + DaoConstants.SIGNOIGUAL
					+ " t7.ID_TIPO_RELEVANCIA_010 ");
			query.append(DaoConstants.AND + " t7.ESTADO_010 " + DaoConstants.SIGNOIGUAL + "'"
					+ EstadoEnum.ALTA.getValue() + "'");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_97 + " t9 ");
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t9.ANYO_097 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t9.NUM_EXP_097 ");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_32 + " t10 ");
			query.append(DaoConstants.ON + " t9.ID_ORDEN_097 " + DaoConstants.SIGNOIGUAL + " t10.ID_ORDEN_032 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_81 + " t11 ");
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t11.ANYO_081 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t11.NUM_EXP_081 ");
			query.append(DaoConstants.AND + " t11.ID_TIPO_TAREA_081 " + DaoConstants.SIGNOIGUAL
					+ TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
			query.append(DaoConstants.AND + " t11.ESTADO_EJECUCION_081 " + DaoConstants.SIGNOIGUAL
					+ EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_90 + " t12 ");
			query.append(DaoConstants.ON + " t11.ID_TAREA_081 " + DaoConstants.SIGNOIGUAL + " t12.ID_TAREA_090 ");
			query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_59).append(DaoConstants.BLANK)
					.append(DaoConstants.T15_MINUSCULA).append(DaoConstants.BLANK);
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t15.ANYO_059 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t15.NUM_EXP_059 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1ESTADO_BITACORA_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t15.ID_ESTADO_BITACORA_059 ");
			query.append(
					DaoConstants.AND + " t15.ID_ESTADO_EXP_059 " + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
			query.append(EstadoExpedienteEnum.ANULADO.getValue() + DaoConstants.SIGNO_COMA
					+ EstadoExpedienteEnum.CERRADO.getValue() + DaoConstants.SIGNO_COMA
					+ EstadoExpedienteEnum.FINALIZADO.getValue() + DaoConstants.CERRAR_PARENTESIS);
		} else {
			// interpretacion
			query.append(DaoConstants.JOIN + DBConstants.TABLA_52 + " t17 ");
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t17.ANYO_052 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t17.NUM_EXP_052 ");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_08 + " t18 ");
			query.append(DaoConstants.ON + " t17.TIPO_ACTO_052 " + DaoConstants.SIGNOIGUAL + " t18.ID_008 ");
			query.append(DaoConstants.AND + " t18.ESTADO_008 " + DaoConstants.SIGNOIGUAL + "'"
					+ EstadoEnum.ALTA.getValue() + "'");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_49 + " t19 ");
			query.append(DaoConstants.ON + " t17.DIR_NORA_052 " + DaoConstants.SIGNOIGUAL + " t19.CDIRNORA_049 ");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_59).append(DaoConstants.BLANK)
					.append(DaoConstants.T15_MINUSCULA).append(DaoConstants.BLANK);
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t15.ANYO_059 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t15.NUM_EXP_059 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1ESTADO_BITACORA_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t15.ID_ESTADO_BITACORA_059 ");
			if (filtroEstado) {
				query.append(
						DaoConstants.AND + " t15.ID_ESTADO_EXP_059 " + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
				query.append(EstadoExpedienteEnum.FINALIZADO.getValue() + DaoConstants.SIGNO_COMA
						+ EstadoExpedienteEnum.ANULADO.getValue());
				query.append(DaoConstants.SIGNO_COMA + EstadoExpedienteEnum.CERRADO.getValue()
						+ DaoConstants.CERRAR_PARENTESIS);
			}
			query.append(DaoConstants.JOIN + DBConstants.TABLA_A3 + " t20 ");
			query.append(DaoConstants.ON + Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t20.ANYO_0A3 ");
			query.append(DaoConstants.AND + Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO
					+ DaoConstants.SIGNOIGUAL + " t20.NUM_EXP_0A3 ");
			query.append(DaoConstants.JOIN + DBConstants.TABLA_35 + " t22 ");
			query.append(DaoConstants.ON + " t22.ID_ORDEN_035 " + DaoConstants.SIGNOIGUAL + " t20.ID_ORDEN_0A3 ");
		}
		query.append(DaoConstants.JOIN + DBConstants.TABLA_30 + " t8");

		query.append(DaoConstants.ON + " t8.ID_030 " + DaoConstants.SIGNOIGUAL);
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode().equals(expedienteFacturacion.getIdTipoExpediente())) {
			// trad/rev
			query.append(" t9.ID_ORDEN_097 ");
		} else {
			// interpretacion
			query.append(" t20.ID_ORDEN_0A3 ");
		}

		query.append(DaoConstants.JOIN + DBConstants.TABLA_31 + DaoConstants.BLANK + DaoConstants.T13_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T13_MINUSCULA_PUNTO + DBConstants.ID_ORDEN_031
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T8_MINUSCULA_PUNTO + DBConstants.ID_030);

		return query.toString();
	}

	private String getObtenerDatosCalculoExpedienteFacturacionSelect(ExpedienteFacturacion expedienteFacturacion) {
		StringBuilder select = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		select.append(DaoConstants.SELECT);
		select.append(Constants.ESPACIO + DBConstants.T1ANYO_EXP_051 + Constants.ESPACIO + DBConstants.ANYO
				+ DaoConstants.SIGNO_COMA);
		select.append(Constants.ESPACIO + DBConstants.T1NUM_EXP_051 + Constants.ESPACIO + DBConstants.NUMEXP
				+ DaoConstants.SIGNO_COMA);
		select.append(" t1.TITULO_051 " + DBConstants.TITULO + DaoConstants.SIGNO_COMA);
		select.append(" t1.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE);
		select.append(DAOUtils.getDecodeAcciones("t1.ID_TIPO_EXPEDIENTE_051", DBConstants.TIPOEXPEDIENTEDESCES,
				this.msg, "TipoExpedienteEnum", es));
		select.append(DAOUtils.getDecodeAcciones("t1.ID_TIPO_EXPEDIENTE_051", DBConstants.TIPOEXPEDIENTEDESCEU,
				this.msg, "TipoExpedienteEnum", eu));
		select.append(
				DaoConstants.SIGNO_COMA + " t1.FECHA_ALTA_051 " + DBConstants.FECHAALTA + DaoConstants.SIGNO_COMA);
		select.append(DaoConstants.TO_CHAR + "(t1.FECHA_ALTA_051,'HH24:MI') " + DBConstants.HORAALTA
				+ DaoConstants.SIGNO_COMA);
		select.append(" t1.OBSV_FACT_051 OBSV_FACT_051,");
		select.append(" t2.TIPO_ENTIDAD_054 " + DBConstants.TIPOENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t2.ID_ENTIDAD_054 " + DBConstants.IDENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t2.DNI_SOLICITANTE_054 " + DBConstants.DNISOLICITANTE + DaoConstants.SIGNO_COMA);
		select.append(" t2.IND_VIP_054 " + DBConstants.INDVIP + DaoConstants.SIGNO_COMA);
		select.append(" t3.DESC_ES " + DBConstants.DESCENTIDADES + DaoConstants.SIGNO_COMA);
		select.append(" t3.DESC_EU " + DBConstants.DESCENTIDADEU + DaoConstants.SIGNO_COMA);
		select.append(" t4.NOMBRE_077 " + DBConstants.NOMBRESOLICITANTE + DaoConstants.SIGNO_COMA);
		select.append(" t4.APEL1_077 " + DBConstants.APEL1SOLICITANTE + DaoConstants.SIGNO_COMA);
		select.append(" t4.APEL2_077 " + DBConstants.APEL2SOLICITANTE + DaoConstants.SIGNO_COMA);
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode().equals(expedienteFacturacion.getIdTipoExpediente())) {
			// trad/rev
			select.append(" t5.FECHA_FINAL_IZO_053 " + DBConstants.FECHAENTREGAIZO + DaoConstants.SIGNO_COMA);
			select.append(DaoConstants.TO_CHAR + "(t5.FECHA_FINAL_IZO_053,'HH24:MI') " + DBConstants.HORAENTREGAIZO
					+ DaoConstants.SIGNO_COMA);
			select.append(" t5.IND_PUBLICAR_BOPV_053 " + DBConstants.INDPUBLICARBOPV + DaoConstants.SIGNO_COMA);
			select.append(" t5.ID_IDIOMA_053 " + DBConstants.IDIDIOMA + DaoConstants.SIGNO_COMA);
			select.append(" t6.DESC_IDIOMA_EU_009 " + DBConstants.DESCIDIOMAEU + DaoConstants.SIGNO_COMA);
			select.append(" t6.DESC_IDIOMA_ES_009 " + DBConstants.DESCIDIOMAES + DaoConstants.SIGNO_COMA);
			select.append(" t5.ID_RELEVANCIA_053 " + DBConstants.IDRELEVANCIA + DaoConstants.SIGNO_COMA);
			select.append(" t7.DESC_RELEVANCIA_EU_010 " + DBConstants.DESCRELEVANCIAEU + DaoConstants.SIGNO_COMA);
			select.append(" t7.DESC_RELEVANCIA_ES_010 " + DBConstants.DESCRELEVANCIAES + DaoConstants.SIGNO_COMA);
			select.append(" t5.IND_URGENTE_053 " + DBConstants.INDURGENTE + DaoConstants.SIGNO_COMA);
			select.append(" t5.IND_DIFICIL_053 " + DBConstants.INDDIFICIL + DaoConstants.SIGNO_COMA);
			select.append(" t9.TARIFA_PAL_097 " + DBConstants.TARIFAPALABRA + DaoConstants.SIGNO_COMA);
			select.append(" t9.POR_IVA_097 " + DBConstants.PORCENTAJEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t10.POR_RECARGO_DIF_032 " + DBConstants.PORRECARGODIF + DaoConstants.SIGNO_COMA);
			select.append(" t10.POR_RECARGO_URG_032 " + DBConstants.PORRECARGOURG + DaoConstants.SIGNO_COMA);
			select.append(" t10.PRECIO_MINIMO_032 " + DBConstants.PRECIOMINIMO + DaoConstants.SIGNO_COMA);
			select.append(" t10.NUM_PALABRAS_TARIF_CONCOR_032 " + DBConstants.NUMPALABRASTARIFCONCOR
					+ DaoConstants.SIGNO_COMA);
			select.append(
					" t10.POR_PALABRA_CONCOR_0_84_032 " + DBConstants.PORPALABRACONCOR084 + DaoConstants.SIGNO_COMA);
			select.append(
					" t10.POR_PALABRA_CONCOR_85_94_032 " + DBConstants.PORPALABRACONCOR8594 + DaoConstants.SIGNO_COMA);
			select.append(" t10.POR_PALABRA_CONCOR_95_100_032 " + DBConstants.PORPALABRACONCOR95100
					+ DaoConstants.SIGNO_COMA);
			select.append(" t5.NUM_TOTAL_PAL_SOLIC_053 " + DBConstants.NUMPALSOLIC + DaoConstants.SIGNO_COMA);
			select.append(" t5.NUM_TOTAL_PAL_IZO_053 " + DBConstants.NUMPALIZO + DaoConstants.SIGNO_COMA);
			select.append(" t12.NUM_TOTAL_PAL_090 " + DBConstants.NUMPALXML + DaoConstants.SIGNO_COMA);
			select.append(" t12.NUM_PAL_CONCOR_0_84_090 " + DBConstants.NUMPALXMLCONCOR084 + DaoConstants.SIGNO_COMA);
			select.append(" t12.NUM_PAL_CONCOR_85_94_090 " + DBConstants.NUMPALXMLCONCOR8594 + DaoConstants.SIGNO_COMA);
			select.append(
					" t12.NUM_PAL_CONCOR_95_100_090 " + DBConstants.NUMPALXMLCONCOR95100 + DaoConstants.SIGNO_COMA);
			select.append(
					" t9.NUM_TOTAL_PAL_FACTURAR_097 " + DBConstants.NUMTOTALPALFACTCLIENTE + DaoConstants.SIGNO_COMA);
			select.append(
					" t9.NUM_PAL_CONCOR_0_84_097 " + DBConstants.NUMPALCONCOR084FACTCLIENTE + DaoConstants.SIGNO_COMA);
			select.append(" t9.NUM_PAL_CONCOR_85_94_097 " + DBConstants.NUMPALCONCOR8594FACTCLIENTE
					+ DaoConstants.SIGNO_COMA);
			select.append(" t9.NUM_PAL_CONCOR_95_100_097 " + DBConstants.NUMPALCONCOR95100FACTCLIENTE
					+ DaoConstants.SIGNO_COMA);
			select.append(" t9.IMPORTE_BASE_097 " + DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
			select.append(" t9.IMPORTE_IVA_097 " + DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t9.IMPORTE_TOTAL_097 " + DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
		} else {
			// interpretacion
			// la coma inicial la pone el decode de daoutils
			select.append(" t17.TIPO_PETICIONARIO_052 " + DBConstants.TIPOPETICIONARIO);
			select.append(DAOUtils.getDecodeAcciones("t17.TIPO_PETICIONARIO_052", DBConstants.PETICIONARIODESCES,
					this.msg, "TipoPeticionarioEnum", es));
			select.append(DAOUtils.getDecodeAcciones("t17.TIPO_PETICIONARIO_052", DBConstants.PETICIONARIODESCEU,
					this.msg, "TipoPeticionarioEnum", eu) + DaoConstants.SIGNO_COMA);
			select.append(" t17.TIPO_ACTO_052 " + DBConstants.TIPOACTO + DaoConstants.SIGNO_COMA);
			select.append(" t18.DESC_ES_008 " + DBConstants.TIPOACTODESCES + DaoConstants.SIGNO_COMA);
			select.append(" t18.DESC_EU_008 " + DBConstants.TIPOACTODESCEU + DaoConstants.SIGNO_COMA);
			select.append(" t18.TIPO_FACTURACION_008 " + DBConstants.TIPOFACTURACIONACTO + DaoConstants.SIGNO_COMA);
			select.append(" t17.IND_PROGRAMADA_052 " + DBConstants.INDPROGRAMADA + DaoConstants.SIGNO_COMA);
			select.append(" t17.IND_PRESUPUESTO_052 " + DBConstants.INDPRESUPUESTO + DaoConstants.SIGNO_COMA);
			// con este campo llamamos a service de Nora y luego a
			// obtenerDireccion de Utils para obtener el String de direccion
			select.append(" t17.DIR_NORA_052 " + DBConstants.CDIRNORA + DaoConstants.SIGNO_COMA);
			// PARA SABER SI ES CAE 48, 01, O 20
			select.append(" t19.CODPROV_049 " + DBConstants.CODPROVINCIA + DaoConstants.SIGNO_COMA);
			select.append(" t20.NUM_INTERPRETES_0A3 " + DBConstants.NUMINTERPRETES + DaoConstants.SIGNO_COMA);
			select.append(" t20.HORAS_INTERPRETACION_0A3 " + DBConstants.HORASINTERPRETACION + DaoConstants.SIGNO_COMA);
			select.append(" t20.POR_IVA_0A3 " + DBConstants.PORCENTAJEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t22.PREC_MIN_CAE_035 " + DBConstants.PRECIOMINCAE + DaoConstants.SIGNO_COMA);// condicion
																											// cae
			select.append(" t22.PREC_MIN_NO_CAE_035 " + DBConstants.PRECIOMINNOCAE + DaoConstants.SIGNO_COMA);
			// condicion tipoacto 1
			select.append(
					" t22.PREC_HORA_REUNION_CAE_035 " + DBConstants.PRECIOHORAINTERPRETECAE + DaoConstants.SIGNO_COMA);// condicion
																														// cae
			select.append(" t22.PREC_HORA_REUNION_NO_CAE_035 " + DBConstants.PRECIOHORAINTERPRETENOCAE
					+ DaoConstants.SIGNO_COMA);
			// condicion tipoacto(2,3,4,0)
			select.append(
					" t22.PREC_JORN_CONGRESO_EU_035 " + DBConstants.PRECIOJORNCONGRESOEU + DaoConstants.SIGNO_COMA);// condicion
																													// cae
			select.append(" t22.PREC_MEDIA_CONGRESO_EU_035 " + DBConstants.PRECIOMEDIAJORNCONGRESOEU
					+ DaoConstants.SIGNO_COMA);
			select.append(
					" t22.PREC_HORA_CONGRESO_EU_035 " + DBConstants.PRECIOHORAJORNCONGRESOEU + DaoConstants.SIGNO_COMA);
			select.append(" t22.PREC_JORN_CONGRESO_NO_EU_035 " + DBConstants.PRECIOJORNCONGRESONOEU
					+ DaoConstants.SIGNO_COMA);
			// condicion cae
			select.append(" t22.PREC_MEDIA_CONGRESO_NO_EU_035 " + DBConstants.PRECIOMEDIAJORNCONGRESONOEU
					+ DaoConstants.SIGNO_COMA);
			select.append(" t22.PREC_HORA_CONGRESO_NO_EU_035 " + DBConstants.PRECIOHORAJORNCONGRESONOEU
					+ DaoConstants.SIGNO_COMA);
			select.append(" t22.JORN_COMP_HORAS_DESDE_035 " + DBConstants.JORNCOMPHORASDESDE + DaoConstants.SIGNO_COMA);
			select.append(" t22.JORN_COMP_HORAS_HASTA_035 " + DBConstants.JORNCOMPHORASHASTA + DaoConstants.SIGNO_COMA);
			select.append(
					" t22.JORN_MEDIA_HORAS_DESDE_035 " + DBConstants.JORNMEDIAHORASDESDE + DaoConstants.SIGNO_COMA);
			select.append(
					" t22.JORN_MEDIA_HORAS_HASTA_035 " + DBConstants.JORNMEDIAHORASHASTA + DaoConstants.SIGNO_COMA);
			select.append(" t20.IMPORTE_BASE_0A3 " + DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
			select.append(" t20.IMPORTE_IVA_0A3 " + DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t20.IMPORTE_TOTAL_0A3 " + DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
			select.append(" t20.TIPO_FACTURACION_0A3 TIPOFACTURACION0A3" + DaoConstants.SIGNO_COMA);
		}
		select.append(" t8.TITULO_030 " + DBConstants.TITULOORDENPRECIOSPUBLICOS + DaoConstants.SIGNO_COMA);
		select.append(" t13.POR_IVA_031 " + DBConstants.PORIVAORDENPRECIOSPUBLICOS);
		return select.toString();
	}

	@Override
	public List<ExpedienteFacturacion> calculoExpedienteFacturacionTablaFilter(
			ExpedienteFacturacion expedienteFacturacionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(
				this.calculoExpedienteFacturacionTablaFilterSelect(expedienteFacturacionFilter));
		query.append(this.calculoExpedienteFacturacionTablaFilterFrom(expedienteFacturacionFilter));
		query.append(DaoConstants.WHERE_1_1);

		query.append(SqlUtils.generarWhereIgual(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.ANYO_058 + Constants.ESPACIO, expedienteFacturacionFilter.getAnyo(), params));

		query.append(SqlUtils.generarWhereIgual(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.NUM_EXP_058 + Constants.ESPACIO, expedienteFacturacionFilter.getNumExp(), params));

		return this.getJdbcTemplate().query(query.toString(),
				this.getCalculoExpedienteFacturacionTablaFilterRowMapper(), params.toArray());
	}

	private String calculoExpedienteFacturacionTablaFilterSelect(ExpedienteFacturacion expedienteFacturacionFilter) {
		StringBuilder select = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		select.append(DaoConstants.SELECT);
		select.append(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO + DBConstants.ANYO_058
				+ Constants.ESPACIO + DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		select.append(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO + DBConstants.NUM_EXP_058
				+ Constants.ESPACIO + DBConstants.NUMEXP + DaoConstants.SIGNO_COMA);
		select.append(" t1.TIPO_ENTIDAD_ASOC_058 " + DBConstants.TIPOENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t1.ID_ENTIDAD_ASOC_058 " + DBConstants.IDENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t2.DESC_ES " + DBConstants.DESCENTIDADES + DaoConstants.SIGNO_COMA);
		select.append(" t2.DESC_EU " + DBConstants.DESCENTIDADEU + DaoConstants.SIGNO_COMA);
		select.append(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO + DBConstants.DNI_CONTACTO_058
				+ Constants.ESPACIO + DBConstants.DNICONTACTO + DaoConstants.SIGNO_COMA);
		select.append(" t3.NOMBRE_077 " + DBConstants.NOMBRE + DaoConstants.SIGNO_COMA);
		select.append(" t3.APEL1_077 " + DBConstants.APEL1 + DaoConstants.SIGNO_COMA);
		select.append(" t3.APEL2_077 " + DBConstants.APEL2 + DaoConstants.SIGNO_COMA);
		select.append(" t4.PROVINCIA " + DBConstants.PROVINCIAENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t4.MUNICIPIO " + DBConstants.MUNICIPIOENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t4.CODPOSTAL " + " CODPOSTALENTIDAD " + DaoConstants.SIGNO_COMA);
		select.append(" t4.LOCALIDAD " + DBConstants.LOCALIDADENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t4.DIRECCION " + DBConstants.DIRECCIONENTIDAD + DaoConstants.SIGNO_COMA);
		select.append(" t6.PROVINCIA " + DBConstants.PROVINCIACONTACTO + DaoConstants.SIGNO_COMA);
		select.append(" t6.MUNICIPIO " + DBConstants.MUNICIPIOCONTACTO + DaoConstants.SIGNO_COMA);
		select.append(" t6.CODPOSTAL " + " CODPOSTALCONTACTO " + DaoConstants.SIGNO_COMA);
		select.append(" t6.LOCALIDAD " + DBConstants.LOCALIDADCONTACTO + DaoConstants.SIGNO_COMA);
		select.append(" t6.DIRECCION " + DBConstants.DIRECCIONCONTACTO + DaoConstants.SIGNO_COMA);
		select.append(" t2.FACTURABLE " + DBConstants.FACTURABLE + DaoConstants.SIGNO_COMA);
		select.append(" t1.POR_FACTURA_058 " + DBConstants.PORCENTAJEFACTURACION + DaoConstants.SIGNO_COMA);
		select.append(" t2.IVA " + DBConstants.IVA + DaoConstants.SIGNO_COMA);
		select.append(" t5.DNI DNIVINCULADOOBAJA " + DaoConstants.SIGNO_COMA);
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()
				.equals(expedienteFacturacionFilter.getIdTipoExpediente())) {
			// trad/rev
			select.append(" t7.NUM_TOTAL_PAL_FACTURAR_098 " + DBConstants.NUMTOTALPAL + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_PAL_CONCOR_0_84_098 " + DBConstants.NUMPALCONCOR084 + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_PAL_CONCOR_85_94_098 " + DBConstants.NUMPALCONCOR8594 + DaoConstants.SIGNO_COMA);
			select.append(" t7.NUM_PAL_CONCOR_95_100_098 " + DBConstants.NUMPALCONCOR95100 + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_BASE_098 " + DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_IVA_098 " + DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t7.IMPORTE_TOTAL_098 " + DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);
		} else {
			// interpretacion
			select.append(" t8.IMPORTE_BASE_0A6 " + DBConstants.IMPORTEBASE + DaoConstants.SIGNO_COMA);
			select.append(" t8.IMPORTE_IVA_0A6 " + DBConstants.IMPORTEIVA + DaoConstants.SIGNO_COMA);
			select.append(" t8.IMPORTE_TOTAL_0A6 " + DBConstants.IMPORTETOTAL + DaoConstants.SIGNO_COMA);

		}
		select.append(" t9.ID_TIPO_EXPEDIENTE_051 " + DBConstants.IDTIPOEXPEDIENTE);
		select.append(
				" , (SELECT CASE WHEN t12.t12_ej_contable IS NULL THEN '9999999999' ELSE substr(t12.t12_ej_contable, 3, 2) || lpad(a4.app_id_0a4, 2, '0') || substr(a4.id_liquidacion_0a4, 3, 6) END");
		select.append(" FROM aa79ba5s01 a5");
		select.append(" JOIN aa79ba4s01 a4");
		select.append(" ON a5.id_factura_0a5 = a4.id_factura_0a4");
		select.append(" AND a4.tipo_entidad_asoc_0a4 = t1.tipo_entidad_asoc_058");
		select.append(" AND a4.id_entidad_asoc_0a4 = t1.id_entidad_asoc_058");
		select.append(" AND nvl(a4.dni_contacto_0a4,-1) = nvl(t1.dni_contacto_058, -1)");
		select.append(" JOIN w0512s01 t12 ON a4.id_liquidacion_0a4 = t12.t12_referencia");
		select.append(" AND ( DECODE(t12.t00_estado_id, 1, 1, 2, 2, 4, 4, 5, 1, 6, 1, 7, 1) = 1");
		select.append(" OR DECODE(t12.t00_estado_id, 1, 1, 2, 2, 4, 4, 5, 1, 6, 1, 7, 1) = 2 )");
		select.append(" WHERE");
		select.append(" a5.anyo_0a5 = t1.anyo_058");
		select.append(" AND a5.num_exp_0a5 = t1.num_exp_058) IDFACTURA");

		return select.toString();
	}

	private Object calculoExpedienteFacturacionTablaFilterFrom(ExpedienteFacturacion expedienteFacturacionFilter) {
		StringBuilder from = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		from.append(DaoConstants.FROM + DBConstants.TABLA_58 + " t1 ");
		from.append(DaoConstants.JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + " t2 ");
		from.append(DaoConstants.ON + " t1.TIPO_ENTIDAD_ASOC_058 " + DaoConstants.SIGNOIGUAL + " t2.TIPO ");
		from.append(DaoConstants.AND + " t1.ID_ENTIDAD_ASOC_058 " + DaoConstants.SIGNOIGUAL + " t2.CODIGO ");
		from.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_77 + " t3 ");
		from.append(DaoConstants.ON + Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.DNI_CONTACTO_058 + Constants.ESPACIO + DaoConstants.SIGNOIGUAL + " t3.DNI_077 ");
		from.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JNORA + " t4 ");
		from.append(DaoConstants.ON + " t2.CDIRNORA " + DaoConstants.SIGNOIGUAL + " t4.CDIRNORA ");
		from.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JAPISOLICITANTES + " t5 ");
		from.append(DaoConstants.ON + Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.DNI_CONTACTO_058 + Constants.ESPACIO + DaoConstants.SIGNOIGUAL + " t5.DNI "
				+ DaoConstants.AND + " t5.TIPO_ENTIDAD" + DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA
				+ Constants.PUNTO + DBConstants.TIPO_ENTIDAD_ASOC_058 + DaoConstants.AND + " t5.COD_ENTIDAD "
				+ DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.ID_ENTIDAD_ASOC_058);
		from.append(DaoConstants.LEFT_JOIN + DBConstants.VISTAX54JNORA + " t6 ");
		from.append(DaoConstants.ON + " t5.CDIRNORA " + DaoConstants.SIGNOIGUAL + " t6.CDIRNORA ");
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()
				.equals(expedienteFacturacionFilter.getIdTipoExpediente())) {
			// tradRev
			from.append(DaoConstants.JOIN + DBConstants.TABLA_98 + " t7 ");
			from.append(DaoConstants.ON + " t1.ID_058 " + DaoConstants.SIGNOIGUAL + " t7.ID_098 ");
		} else {
			// interpretacion
			from.append(DaoConstants.JOIN + DBConstants.TABLA_A6 + " t8 ");
			from.append(DaoConstants.ON + " t1.ID_058 " + DaoConstants.SIGNOIGUAL + " t8.ID_0A6 ");
		}
		from.append(DaoConstants.JOIN + DBConstants.TABLA_51 + " t9 ");
		from.append(DaoConstants.ON + Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.ANYO_058 + Constants.ESPACIO + DaoConstants.SIGNOIGUAL + " t9.ANYO_051 ");
		from.append(DaoConstants.AND + Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.NUM_EXP_058 + Constants.ESPACIO + DaoConstants.SIGNOIGUAL + " t9.NUM_EXP_051 ");

		return from.toString();
	}

	@Override
	public Long calculoExpedienteFacturacionTablaFilterCount(ExpedienteFacturacion expedienteFacturacionFilter,
			Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.SELECT_COUNT);
		query.append(this.calculoExpedienteFacturacionTablaFilterFrom(expedienteFacturacionFilter));
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.ANYO_058 + Constants.ESPACIO, expedienteFacturacionFilter.getAnyo(), params));
		query.append(SqlUtils.generarWhereIgual(Constants.ESPACIO + DaoConstants.T1_MINUSCULA + Constants.PUNTO
				+ DBConstants.NUM_EXP_058 + Constants.ESPACIO, expedienteFacturacionFilter.getNumExp(), params));
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public Object actDatosFacturacionExpedientesFilter(ExpedienteFacturacion filterExpedienteFacturacion,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, boolean isCount) {

		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		StringBuilder select = getQueryBuscadorActDatosFacturacion(filterExpedienteFacturacion, startsWith, isCount,
				params);

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(select.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapBusqRevision(),
					params.toArray());
		}

	}

	/**
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param startsWith                  Boolean
	 * @param isCount                     boolean
	 * @param params                      List<Object>
	 * @return StringBuilder
	 */
	private StringBuilder getQueryBuscadorActDatosFacturacion(ExpedienteFacturacion filterExpedienteFacturacion,
			Boolean startsWith, boolean isCount, List<Object> params) {
		StringBuilder select = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		select.append(DaoConstants.SELECT);

		if (isCount) {
			select.append(DaoConstants.COUNT + DaoConstants.ABRIR_PARENTESIS);
			select.append(DaoConstants.DISTINCT);
			select.append("SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )");
			select.append(DaoConstants.CERRAR_PARENTESIS);
		} else {
			select.append(getSelectRevisionFacturacion());
			select.append(
					" , CASE WHEN T1.ID_TIPO_EXPEDIENTE_051 = 'I' THEN -1 ELSE NVL(N1.NUM_TOTAL_PAL_090,NVL(R1.NUM_TOTAL_PAL_IZO_053,NVL(NUM_TOTAL_PAL_SOLIC_053,0) ) ) END AS NUMPALCOLORDER ");

		}

		// FROM
		select.append(getFromTabla51());
		// Expediente traducción/revisión (Tabla 53)
		select.append(" LEFT JOIN AA79B53S01 r1 ON t1.ANYO_051 = r1.ANYO_053 AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");
		// Expediente Interpretación (Tabla 52)
		select.append(" LEFT JOIN AA79B52S01 s1 ON t1.ANYO_051 = s1.ANYO_052 AND t1.NUM_EXP_051 = s1.NUM_EXP_052 ");
		// comun
		select.append(getFromRevisionFacturacionGenerica());

		// WHERE
		select.append(QueryUtils.getWhereActDatosFacturacion(params));

		// filtros formulario busqueda pantalla
		select.append(getWhereBusquedaActDatosFacturacion(filterExpedienteFacturacion, params, startsWith));

		return select;
	}

	private String getWhereBusquedaActDatosFacturacion(ExpedienteFacturacion filter, List<Object> params,
			boolean startsWith) {

		// DATOS GENERALES DEL EXPEDIENTE
		StringBuilder where = getWhereBusquedaDatosExp(filter, params);

		// EXPEDIENTE DE TRADUCCIÓN/REVISIÓN
		if (filter.getExpedienteTradRev() != null) {

			if (filter.getExpedienteTradRev().getFechaEntregaIzoDesde() != null
					&& filter.getExpedienteTradRev().getFechaEntregaIzoHasta() != null) {
				// FECHA ENTREGA IZO / FECHA FIN DE LA INTERPRETACIÓN
				where.append(
						getRangoFechasActDatosFact("NVL(r1.FECHA_FINAL_IZO_053,s1.FECHA_FIN_052)", filter, params));
			}

			where.append(getWhereComunExpTradRev(filter, params));

			// INDICADOR EXPEDIENTE FACTURABLE
			where.append(SqlUtils.generarWhereIgual("r1.IND_FACTURABLE_053",
					filter.getExpedienteTradRev().getIndFacturable(), params));
		}

		// GESTOR DEL EXPEDIENTE
		if (filter.getGestorExpediente() != null && QueryUtils.entidadSolicitanteValida(filter.getGestorExpediente())) {
			// Entidad
			where.append(getWhereBusquedaGestorExp(filter, params));

			// Solicitante
			if (filter.getGestorExpediente().getSolicitante() != null
					&& filter.getGestorExpediente().getSolicitante().getDni() != null) {
				where.append(SqlUtils.generarWhereIgual("g1.DNI_SOLICITANTE_054",
						filter.getGestorExpediente().getSolicitante().getDni(), params));
			}

		}

		// Entidad y contacto de facturación
		whereBusquedaRevisionFacturacion(filter, params, where);

		return where.toString();
	}

	/**
	 * @param campo  String
	 * @param filter ExpedienteFacturacion
	 * @param params List<Object>
	 * @return String
	 */
	private String getRangoFechasActDatosFact(String campo, ExpedienteFacturacion filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		where.append(SqlUtils.generarWhereMayorIgualFecha(campo, DDMMYY,
				filter.getExpedienteTradRev().getFechaEntregaIzoDesde(), params));
		where.append(SqlUtils.generarWhereMenorIgualFecha(campo, DDMMYY,
				filter.getExpedienteTradRev().getFechaEntregaIzoHasta(), params));

		return where.toString();
	}

	/**
	 * @param filter ExpedienteFacturacion
	 * @param params List<Object>
	 * @return String
	 */
	private String getWhereBusquedaGestorExp(ExpedienteFacturacion filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		if (filter.getGestorExpediente().getEntidad().getTipo() != null) {
			where.append(SqlUtils.generarWhereIgual("g1.TIPO_ENTIDAD_054",
					filter.getGestorExpediente().getEntidad().getTipo(), params));
		}
		if (filter.getGestorExpediente().getEntidad().getCodigo() != null) {
			where.append(SqlUtils.generarWhereIgual("g1.ID_ENTIDAD_054",
					filter.getGestorExpediente().getEntidad().getCodigo(), params));
		}

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @return
	 */
	private StringBuilder getWhereBusquedaDatosExp(ExpedienteFacturacion filter, List<Object> params) {
		// FILTROS BUSCADOR
		StringBuilder where = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);

		// TIPO EXPED
		where.append(
				SqlUtils.generarWhereIgual(DBConstants.T1ID_TIPO_EXPEDIENTE_051, filter.getIdTipoExpediente(), params));

		// NUM EXPEDIENTE
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));

		// TITULO
		where.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.T1TITULO_051, filter.getTitulo(), params, false));

		// FECHA ALTA (SOLICITUD)
		where.append(
				SqlUtils.generarWhereMayorIgualFecha("t1.FECHA_ALTA_051", DDMMYY, filter.getFechaAltaDesde(), params));
		where.append(
				SqlUtils.generarWhereMenorIgualFecha("t1.FECHA_ALTA_051", DDMMYY, filter.getFechaAltaHasta(), params));
		return where;
	}

	@Override
	public long comprobarDatosFactExpInter(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM + DBConstants.TABLA_A3);
		query.append(DaoConstants.WHERE + DBConstants.ANYO_0A3 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DBConstants.NUM_EXP_0A3 + DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(anyo);
		params.add(numExp);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public long comprobarExpedienteNoFacturado(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM + DBConstants.TABLA_97);
		query.append(DaoConstants.WHERE + DBConstants.ANYO_097 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DBConstants.NUM_EXP_097 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DBConstants.IND_REVISADO_097 + DaoConstants.SIGNOIGUAL_INTERROGACION);

		params.add(anyo);
		params.add(numExp);
		params.add(Constants.SI);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override
	public DatosFacturacionExpediente obtenerImportesExpedientes(
			RupTableMultiselectionModel rupTableMultiselectionModel, Boolean esInterpretacion) {
		StringBuilder queryOIE = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsOIE = new ArrayList<Object>();
		queryOIE.append("SELECT ");
		if (esInterpretacion) {
			queryOIE.append("CASE WHEN  MAX(t11.IND_IVA_0A6) = 'S' AND MAX(t8.POR_IVA_0A3) > 0 THEN ");
			queryOIE.append("round((nvl(SUM(t11.IMPORTE_TOTAL_0A6), 0)/(1+ (MAX(t8.POR_IVA_0A3) / 100))), 2) ");
			queryOIE.append("ELSE nvl(SUM(t11.IMPORTE_TOTAL_0A6), 0) END AS IMPORTEBASE, ");
			queryOIE.append("CASE WHEN  MAX(t11.IND_IVA_0A6) = 'S' AND MAX(t8.POR_IVA_0A3) > 0 THEN ");
			queryOIE.append(
					"nvl(SUM(t11.IMPORTE_TOTAL_0A6), 0) - round((nvl(SUM(t11.IMPORTE_TOTAL_0A6), 0)/(1+ (MAX(t8.POR_IVA_0A3) / 100))), 2) ");
			queryOIE.append("ELSE 0.0 END AS IMPORTEIVA, ");
			queryOIE.append("nvl(SUM(t11.IMPORTE_TOTAL_0A6), 0) AS IMPORTETOTAL ");
		} else {
			queryOIE.append("CASE WHEN  MAX(t7.IND_IVA_098) = 'S' AND MAX(t6.por_iva_097) > 0 THEN ");
			queryOIE.append("round((nvl(SUM(t7.IMPORTE_TOTAL_098), 0)/(1+ (MAX(t6.por_iva_097) / 100))), 2) ");
			queryOIE.append("ELSE nvl(SUM(t7.IMPORTE_TOTAL_098), 0) END AS IMPORTEBASE, ");
			queryOIE.append("CASE WHEN  MAX(t7.IND_IVA_098) = 'S' AND MAX(t6.por_iva_097) > 0 THEN ");
			queryOIE.append(
					"nvl(SUM(t7.IMPORTE_TOTAL_098), 0) - round((nvl(SUM(t7.IMPORTE_TOTAL_098), 0)/(1+ (MAX(t6.por_iva_097) / 100))), 2) ");
			queryOIE.append("ELSE 0.0 END AS IMPORTEIVA, ");
			queryOIE.append("nvl(SUM(t7.IMPORTE_TOTAL_098), 0) AS IMPORTETOTAL ");
			/*
			 * nvl(SUM(t7.importe_base_098), 0) AS importebase, CASE WHEN
			 * MAX(t7.IND_IVA_098) = 'S' AND MAX(t6.por_iva_097) > 0 THEN
			 * round((nvl(SUM(t7.importe_base_098), 0) * MAX(t6.por_iva_097)) / 100, 2) ELSE
			 * 0.0 END AS importeiva, case WHEN MAX(t7.IND_IVA_098) = 'S' AND
			 * MAX(t6.por_iva_097) > 0 THEN nvl(SUM(t7.importe_base_098), 0) + round((
			 * nvl(sum(t7.importe_base_098), 0) * max(t6.por_iva_097)) / 100 ,2) ELSE
			 * nvl(SUM(t7.importe_base_098), 0) END as importetotal
			 */
		}

		getFromWhereExpedientes(rupTableMultiselectionModel, esInterpretacion, queryOIE, paramsOIE);

		return this.getJdbcTemplate().queryForObject(queryOIE.toString(), paramsOIE.toArray(),
				this.rwMapDatosFacturacionExpediente);
	}

	private void getFromWhereExpedientes(RupTableMultiselectionModel rupTableMultiselectionModel,
			Boolean esInterpretacion, StringBuilder queryOIE, List<Object> paramsOIE) {
		paramsOIE.add(rupTableMultiselectionModel.getCodEntidad());
		paramsOIE.add(rupTableMultiselectionModel.getTipoEntidad());
		ExpedienteFacturacion expedienteFacturacionFilter = new ExpedienteFacturacion();
		expedienteFacturacionFilter.setIdTipoExpediente(rupTableMultiselectionModel.getIdTipoExpediente().toString());
		if (rupTableMultiselectionModel.getDniSolicitante() != null
				&& !"-1".equals(rupTableMultiselectionModel.getDniSolicitante())) {
			paramsOIE.add(rupTableMultiselectionModel.getDniSolicitante());
			Solicitante solic = new Solicitante();
			solic.setDni(rupTableMultiselectionModel.getDniSolicitante());
			Gestor contacto = new Gestor();
			contacto.setSolicitante(solic);
			expedienteFacturacionFilter.setEntidadContactoFactura(contacto);
		}

		queryOIE.append(this.getConfeccionarFacturaExpedientesFilterFrom(expedienteFacturacionFilter));
		queryOIE.append(this.getConfeccionarFacturaExpedientesFilterWhere(expedienteFacturacionFilter));
		if (Constants.UNO == rupTableMultiselectionModel.getSelectAll() && rupTableMultiselectionModel.getIds() != null
				&& !rupTableMultiselectionModel.getIds().isEmpty()) {
			queryOIE.append(this.obtenerWhereExpedientes(rupTableMultiselectionModel.getIds(), esInterpretacion, true));
		} else if (Constants.CERO == rupTableMultiselectionModel.getSelectAll()) {
			queryOIE.append(
					this.obtenerWhereExpedientes(rupTableMultiselectionModel.getIds(), esInterpretacion, false));
		}
	}

	public String obtenerWhereExpedientes(List<String> listaIds, Boolean esInterpretacion, Boolean isSelectAll) {
		StringBuilder whereExpedientes = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		boolean esPrimero = true;
		for (String idCompuesta : listaIds) {
			String sAnyo = idCompuesta.substring(Constants.CERO, idCompuesta.indexOf("-"));
			String sNumExp = idCompuesta.substring(idCompuesta.indexOf("-") + 1, idCompuesta.length());
			if (esPrimero) {
				esPrimero = false;
				if (isSelectAll) {
					whereExpedientes.append(" AND NOT (");
				} else {
					whereExpedientes.append(" AND  (");
				}
			} else {
				whereExpedientes.append(" OR ");
			}
			whereExpedientes.append(" (t1.ANYO_051 " + " = " + sAnyo + " AND t1.NUM_EXP_051 " + " = " + sNumExp + " )");
		}
		whereExpedientes.append(" )");
		return whereExpedientes.toString();
	}

	@Override
	public List<DatosFacturacionExpediente> obtenerExpedientesSeleccionados(
			RupTableMultiselectionModel rupTableMultiselectionModel, Boolean esInterpretacion) {
		StringBuilder queryOIE = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsOIE = new ArrayList<Object>();
		queryOIE.append("SELECT ");
		queryOIE.append("t1.ANYO_051 ANYO, ");
		queryOIE.append("t1.NUM_EXP_051 NUMEXP ");
		if (esInterpretacion) {
			queryOIE.append(", t11.IMPORTE_BASE_0A6 IMPORTEBASE");
			queryOIE.append(", t11.IMPORTE_IVA_0A6 IMPORTEIVA");
			queryOIE.append(", t11.IMPORTE_TOTAL_0A6 IMPORTETOTAL");
		} else {
			queryOIE.append(", t7.IMPORTE_BASE_098 IMPORTEBASE");
			queryOIE.append(", t7.IMPORTE_IVA_098 IMPORTEIVA");
			queryOIE.append(", t7.IMPORTE_TOTAL_098 IMPORTETOTAL");
		}

		this.getFromWhereExpedientes(rupTableMultiselectionModel, esInterpretacion, queryOIE, paramsOIE);

		return this.getJdbcTemplate().query(queryOIE.toString(), paramsOIE.toArray(),
				this.rwMapExpedientesSeleccionadosFacturacion);
	}

	@Override
	public Boolean comprobarEstadoPresupuestos(Integer estado, List<Expediente> expedientesSeleccionados) {
		StringBuilder queryCEP = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsCEP = new ArrayList<Object>();

		queryCEP.append(" SELECT COUNT(1) FROM AA79B53S01 t1 ");
		queryCEP.append(" JOIN AA79B81S01 t2 ");
		queryCEP.append(" ON t1.ANYO_053 = t2.ANYO_081 ");
		queryCEP.append(" AND t1.NUM_EXP_053 = t2.NUM_EXP_081 ");
		queryCEP.append(SqlUtils.generarWhereIgual("t2.ID_TIPO_TAREA_081",
				TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue(), paramsCEP));
		queryCEP.append(" JOIN AA79B64S01 t3 ");
		queryCEP.append(" ON t2.ID_REQUERIMIENTO_081 = t3.ID_064 ");
		queryCEP.append(" AND t3.TIPO_REQUERIMIENTO_064 IN ( " + TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue()
				+ ", " + TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() + " ) ");
		queryCEP.append(SqlUtils.generarWhereIgual("t3.ESTADO_SUBSANACION_064", estado, paramsCEP));
		queryCEP.append(SqlUtils.generarWhereLike("t3.IND_SUBSANADO_064", ActivoEnum.SI.getValue(), paramsCEP, false));
		if (expedientesSeleccionados != null && !expedientesSeleccionados.isEmpty()) {
			queryCEP.append(" WHERE ( ");
			boolean esPrimero = true;
			for (Expediente expediente : expedientesSeleccionados) {
				if (esPrimero) {
					esPrimero = false;
				} else {
					queryCEP.append(" OR ");
				}
				queryCEP.append(" (t1.ANYO_053 = " + expediente.getAnyo());
				queryCEP.append(" AND t1.NUM_EXP_053 = " + expediente.getNumExp() + ") ");
			}
			queryCEP.append(" ) ");
			queryCEP.append(
					SqlUtils.generarWhereLike("t1.IND_PRESUPUESTO_053", ActivoEnum.SI.getValue(), paramsCEP, false));
		}
		Integer resulCount = this.getJdbcTemplate().queryForObject(queryCEP.toString(), paramsCEP.toArray(),
				Integer.class);
		Boolean resul = false;
		if (resulCount > Constants.CERO) {
			resul = true;
		}
		return resul;
	}

	@Override
	public List<ExpedienteFacturacion> revisionFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData) {

		List<Object> paramsRFEFGSI = new ArrayList<Object>();
		StringBuilder queryRFEFGSI = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ANYO_051");
		camposIds.add("t1.NUM_EXP_051");
		queryRFEFGSI.append("SELECT  DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
		queryRFEFGSI.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
		// FROM
		queryRFEFGSI.append(getFromRevisionFacturacion());

		/* WHERE */
		queryRFEFGSI.append(DaoConstants.WHERE_1_1);
		queryRFEFGSI.append(SqlUtils.generarWhereIgual("r1.IND_FACTURABLE_053",
				filterExpedienteFacturacion.getExpedienteTradRev().getIndFacturable(), paramsRFEFGSI));
		queryRFEFGSI.append(SqlUtils.generarWhereIgual(" b1.ID_ESTADO_EXP_059 ",
				EstadoExpedienteEnum.CERRADO.getValue(), paramsRFEFGSI));
		queryRFEFGSI.append(SqlUtils.generarWhereIgual(" b1.ID_FASE_EXPEDIENTE_059 ",
				FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue(), paramsRFEFGSI));

		// filtros formulario busqueda pantalla
		queryRFEFGSI.append(getWherebusquedarevision(filterExpedienteFacturacion, paramsRFEFGSI, false));
		queryRFEFGSI
				.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
						tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryRFEFGSI.toString(), this.getRwMapPK(), paramsRFEFGSI.toArray());
	}

	@Override
	public Expediente obtenerDatosBoeExp(Expediente expediente) {
		List<Object> paramsODBE = new ArrayList<Object>();
		StringBuilder queryODBE = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		queryODBE.append(" SELECT t1.ANYO_053 ANYO,");
		queryODBE.append(" t1.NUM_EXP_053 NUMEXP,");
		queryODBE.append(" t1.IND_PUBLICADO_BOE_053 INDBUPLICADOBOE,");
		queryODBE.append(" t1.URL_BOE_053 URLBOE ");
		queryODBE.append(" FROM AA79B53S01 t1 ");
		queryODBE.append(DaoConstants.WHERE_1_1);
		queryODBE.append(SqlUtils.generarWhereIgual("t1.ANYO_053", expediente.getAnyo(), paramsODBE));
		queryODBE.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_053", expediente.getNumExp(), paramsODBE));

		return this.getJdbcTemplate().queryForObject(queryODBE.toString(), paramsODBE.toArray(), this.rwMapIndBoe);
	}

	@Override
	public Integer guardarDatosBoe(Expediente expediente, String urlBoe, String indBoe) {
		List<Object> paramsGDBE = new ArrayList<Object>();
		StringBuilder queryGDBE = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		queryGDBE.append(this.obtenerQueryActualizarBoe(true, indBoe));
		paramsGDBE.add(urlBoe);
		paramsGDBE.add(expediente.getAnyo());
		paramsGDBE.add(expediente.getNumExp());
		return this.getJdbcTemplate().update(queryGDBE.toString(), paramsGDBE.toArray());
	}

	@Override
	public Integer guardarDatosBoe(ListaExpediente listaExpediente) {
		List<Object[]> paramsGDBEE = new ArrayList<Object[]>();
		final List<Object> aux = new ArrayList<Object>();
		StringBuilder queryGDBEE = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		queryGDBEE.append(this.obtenerQueryActualizarBoe(false, Constants.SI));
		for (Expediente expediente : listaExpediente.getListaExpediente()) {
			aux.clear();
			aux.add(expediente.getAnyo());
			aux.add(expediente.getNumExp());
			paramsGDBEE.add(aux.toArray());
		}
		int[] aInt = this.getJdbcTemplate().batchUpdate(queryGDBEE.toString(), paramsGDBEE);
		Integer iResul = Constants.CERO;
		if (aInt != null && aInt.length > Constants.CERO) {
			iResul = aInt.length;
		}
		return iResul;
	}

	public String obtenerQueryActualizarBoe(Boolean guardarUrl, String indBoe) {
		StringBuilder queryOQAB = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);
		queryOQAB.append(" UPDATE AA79B53S01 t1 ");
		queryOQAB.append(" SET t1.IND_PUBLICADO_BOE_053 = '" + indBoe + "' ");
		if (guardarUrl) {
			queryOQAB.append(" , t1.URL_BOE_053 = ? ");
		}
		queryOQAB.append(" WHERE t1.ANYO_053 = ? AND t1.NUM_EXP_053 = ?");
		return queryOQAB.toString();
	}

	@Override
	public List<ExpedienteFacturacion> actDatosFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData) {
		StringBuilder queryADFGSI = new StringBuilder();
		List<Object> paramsADFGSI = new ArrayList<Object>();
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ANYO_051");
		camposIds.add("t1.NUM_EXP_051");
		// SELECT
		queryADFGSI.append("SELECT DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
		queryADFGSI.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
		// FROM
		queryADFGSI.append(getFromTabla51());
		// Expediente traducción/revisión (Tabla 53)
		queryADFGSI
				.append(" LEFT JOIN AA79B53S01 r1 ON t1.ANYO_051 = r1.ANYO_053 AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");
		// Expediente Interpretación (Tabla 52)
		queryADFGSI
				.append(" LEFT JOIN AA79B52S01 s1 ON t1.ANYO_051 = s1.ANYO_052 AND t1.NUM_EXP_051 = s1.NUM_EXP_052 ");
		// comun
		queryADFGSI.append(getFromRevisionFacturacionGenerica());

		// WHERE
		queryADFGSI.append(QueryUtils.getWhereActDatosFacturacion(paramsADFGSI));

		// filtros formulario busqueda pantalla
		queryADFGSI.append(getWhereBusquedaActDatosFacturacion(filterExpedienteFacturacion, paramsADFGSI, false));
		queryADFGSI
				.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
						tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryADFGSI.toString(), this.rwMapPK, paramsADFGSI.toArray());
	}

	@Override
	public Integer comprobarExpSelBoeANo(ListaExpediente listaExpedientes) {
		StringBuilder queryCESBN = new StringBuilder();
		List<Object> paramsCESBN = new ArrayList<Object>();
		queryCESBN.append(" SELECT COUNT(1) ");
		queryCESBN.append(" FROM AA79B51S01 t1 ");
		queryCESBN.append(" LEFT JOIN AA79B53S01 t3 ");
		queryCESBN.append(" ON t1.ANYO_051 = t3.ANYO_053 ");
		queryCESBN.append(" AND t1.NUM_EXP_051 = t3.NUM_EXP_053 ");
		queryCESBN.append(" LEFT JOIN AA79B52S01 t2 ");
		queryCESBN.append(" ON t1.ANYO_051 = t2.ANYO_052 ");
		queryCESBN.append(" AND t1.NUM_EXP_051 = t2.NUM_EXP_052 ");
		queryCESBN.append(" JOIN AA79B54S01 G1 ON T1.ANYO_051 = G1.ANYO_054 AND T1.NUM_EXP_051 = G1.NUM_EXP_054 ");
		queryCESBN.append("WHERE (");
		boolean esPrimero = true;
		for (Expediente expediente : listaExpedientes.getListaExpediente()) {
			if (esPrimero) {
				esPrimero = false;
			} else {
				queryCESBN.append(" OR ");
			}
			queryCESBN.append(" ( t1.ANYO_051 = ? ");
			queryCESBN.append(" AND t1.NUM_EXP_051 = ? )");
			paramsCESBN.add(expediente.getAnyo());
			paramsCESBN.add(expediente.getNumExp());
		}
		queryCESBN.append(" ) ");
		queryCESBN.append(" AND ((t3.IND_PUBLICADO_BOE_053 IS NULL ");
		queryCESBN.append(" OR t3.IND_PUBLICADO_BOE_053    = '" + Constants.SI + "') ");
		queryCESBN.append(" OR t1.ID_TIPO_EXPEDIENTE_051    = '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "' ");
		queryCESBN.append(" OR t1.ID_TIPO_EXPEDIENTE_051    = '" + TipoExpedienteEnum.REVISION.getValue() + "' ");
		queryCESBN.append(
				" OR ( G1.TIPO_ENTIDAD_054 <>'B' OR G1.ID_ENTIDAD_054 <> (SELECT VALOR_000 FROM AA79B00S01 WHERE ID_000 =1))  ");
		queryCESBN.append(" ) ");

		return this.getJdbcTemplate().queryForObject(queryCESBN.toString(), Integer.class, paramsCESBN.toArray());
	}

	@Override
	public List<ExpedienteFacturacion> listadoExpedientesFacturaVariosPagadores(
			List<FacturasExpediente> listFacturasExp, String tipoExp) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ExpedienteFacturacionDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.SELECT
				+ " T1.ANYO_058 ANYO,T1.NUM_EXP_058 NUMEXP,T1.TIPO_ENTIDAD_ASOC_058 TIPOENTIDAD,T1.ID_ENTIDAD_ASOC_058 IDENTIDAD,T2.DESC_ES DESCENTIDADES, ");
		query.append(
				" T2.DESC_EU DESCENTIDADEU,T2.FACTURABLE FACTURABLE,T1.POR_FACTURA_058 PORCENTAJEFACTURACION,T2.IVA IVA, T9.ID_TIPO_EXPEDIENTE_051 TIPOEXPEDIENTE, ");

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(tipoExp)) {
			query.append(
					" T8.NUM_INTERPRETES_0A3 NUMINTERPRETES, T8.HORAS_INTERPRETACION_0A3 HORASINTERPRETACION, T11.IMPORTE_BASE_0A6 IMPORTEBASE, T11.IMPORTE_IVA_0A6 IMPORTEIVA, T11.IMPORTE_TOTAL_0A6 IMPORTETOTAL ");
		} else {
			query.append(
					" T7.NUM_TOTAL_PAL_FACTURAR_098 NUMTOTALPAL,T7.NUM_PAL_CONCOR_0_84_098 NUMPALCONCOR084,T7.NUM_PAL_CONCOR_85_94_098 NUMPALCONCOR8594,T7.NUM_PAL_CONCOR_95_100_098 NUMPALCONCOR95100, ");
			query.append(
					" T7.IMPORTE_BASE_098 IMPORTEBASE,T7.IMPORTE_IVA_098 IMPORTEIVA, T7.IMPORTE_TOTAL_098 IMPORTETOTAL ");
		}

		query.append(DaoConstants.FROM);
		query.append(" AA79B58S01 T1 ");
		query.append(
				" JOIN X54JAPI_ENTIDADES_SOLIC T2 ON T1.TIPO_ENTIDAD_ASOC_058 = T2.TIPO AND T1.ID_ENTIDAD_ASOC_058 = T2.CODIGO ");
		query.append(" JOIN AA79B51S01 T9 ON T1.ANYO_058 = T9.ANYO_051 AND T1.NUM_EXP_058 = T9.NUM_EXP_051 ");

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(tipoExp)) {
			query.append(" LEFT JOIN AA79BA3S01 T8 ON T9.ANYO_051 = T8.ANYO_0A3 AND T9.NUM_EXP_051 = T8.NUM_EXP_0A3 ");
			query.append(" LEFT JOIN AA79BA6S01 T11 ON T11.ID_0A6 = T1.ID_058 ");
		} else {
			query.append(" JOIN AA79B98S01 T7 ON T1.ID_058 = T7.ID_098 ");
		}

		query.append(DaoConstants.WHERE);
		query.append(" POR_FACTURA_058 != " + Constants.CIEN);
		query.append(" AND (T1.ANYO_058, T1.NUM_EXP_058) ").append(DaoConstants.IN)
				.append(DaoConstants.ABRIR_PARENTESIS);
		for (int x = 0; x < listFacturasExp.size(); x++) {
			if (x != 0) {
				query.append(DaoConstants.SIGNO_COMA);
			}
			query.append(DaoConstants.ABRIR_PARENTESIS).append(listFacturasExp.get(x).getAnyo())
					.append(DaoConstants.SIGNO_COMA).append(listFacturasExp.get(x).getNumExp())
					.append(DaoConstants.CERRAR_PARENTESIS);
		}
		query.append(DaoConstants.CERRAR_PARENTESIS).append(" ORDER BY ANYO ASC, NUMEXP ASC");

		return this.getJdbcTemplate().query(query.toString(), this.getExpedienteFacturacionVariosPagadoresRowMapper(),
				params.toArray());
	}

}
