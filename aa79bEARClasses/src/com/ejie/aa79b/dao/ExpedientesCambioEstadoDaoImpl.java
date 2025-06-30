package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.ExpedienteRowMapperConfLeyenda;
import com.ejie.aa79b.dao.mapper.ExpedientesCambioEstadoRowMapper;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Leyenda;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoFacturaEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class ExpedientesCambioEstadoDaoImpl extends GenericoDaoImpl<Expediente> implements ExpedientesCambioEstadoDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ANYO, DBConstants.NUMEXP,
			DBConstants.ANYONUMEXPCONCATENADO, DBConstants.IDTIPOEXPEDIENTE, DBConstants.TIPOEXPEDIENTE,
			DBConstants.TITULO, DBConstants.FECHAFINALIZO, DBConstants.HORAFINALIZO, DBConstants.IDESTADOEXP,
			DBConstants.ESTADOEXPEDIENTEDESCEU, DBConstants.ESTADOEXPEDIENTEDESCABREU, DBConstants.DESCAMPENTIDADEU,
			DBConstants.DESCENTIDADEU, DBConstants.NOMBRECOMPLETO };

	public ExpedientesCambioEstadoDaoImpl() {
		// Constructor
		super(Expediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Expediente> rwMap = new ExpedientesCambioEstadoRowMapper();

	private RowMapper<Expediente> rwMapPK = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
			expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return expediente;
		}
	};

	private RowMapper<Leyenda> rwMapConfLeyenda = new ExpedienteRowMapperConfLeyenda();

	@Override
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();

		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051 + DaoConstants.BLANK
				+ DBConstants.ANYO + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.BLANK + DBConstants.NUMEXP
				+ DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SUBSTR + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051 + DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(Constants.STRING_DOS + DaoConstants.SIGNO_COMA_SIN_ESPACIOS + Constants.STRING_CUATRO
				+ DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + Constants.CONTRABARRA);
		query.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.LPAD + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(Constants.STRING_SEIS + DaoConstants.SIGNO_COMA_SIN_ESPACIOS + DaoConstants.SIGNO_APOSTROFO
				+ Constants.STRING_CERO + DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.CERRAR_PARENTESIS + DBConstants.ANYONUMEXPCONCATENADO + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051 + DaoConstants.BLANK
				+ DBConstants.IDTIPOEXPEDIENTE + DaoConstants.SIGNO_COMA);
		query.append(DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue()
				+ "', '").append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, locale))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, locale))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, locale)).append("'");
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPOEXPEDIENTE
				+ DaoConstants.SIGNO_COMA);
		query.append(
				DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TITULO_051 + DaoConstants.BLANK + DBConstants.TITULO);
		// Expediente traducción/revisión
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T3_MINUSCULA_PUNTO
				+ DBConstants.FECHA_FINAL_IZO_053 + DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.FECHA_FIN_052 + DaoConstants.CERRAR_PARENTESIS);
		query.append(DBConstants.FECHAFINAL + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.FECHA_FINAL_IZO_053
				+ DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.FECHA_FIN_052 + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS + DaoConstants.FORMATO_HORA);
		query.append(DaoConstants.CERRAR_PARENTESIS + DBConstants.HORAFINAL);
		// Bitácora
		// IDESTADOEXP
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059
				+ DaoConstants.BLANK + DBConstants.IDESTADOEXP);
		// ESTADOEXPEDIENTEDESCEU
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_EU_060 + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCEU);
		// ESTADOEXPEDIENTEDESCABREU
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_EU_060 + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCABREU);
		// IDFASEEXPEDIENTE
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059
				+ DaoConstants.BLANK + DBConstants.IDFASEEXPEDIENTE);
		// FASEEXPEDIENTEDESCEU
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_EU_061 + DaoConstants.BLANK + DBConstants.FASEEXPEDIENTEDESCEU);
		// FASEEXPEDIENTEDESCABREU
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_EU_061 + DaoConstants.BLANK + DBConstants.FASEEXPEDIENTEDESCABREU);
		// Nombre y apellidos del contacto gestor
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054
				+ DaoConstants.BLANK + DBConstants.DNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.PREDNI_077
				+ DaoConstants.BLANK + DBConstants.PREDNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.SUFDNI_077
				+ DaoConstants.BLANK + DBConstants.SUFDNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.PREDNI_077 + DaoConstants.SIGNO_CONCATENACION);
		query.append(
				DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054 + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.SUFDNI_077 + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AS + DBConstants.DNICOMPLETO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.NOMBRE_077
				+ DaoConstants.BLANK + DBConstants.NOMBRE);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.APEL1_077
				+ DaoConstants.BLANK + DBConstants.APEL1);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.APEL2_077
				+ DaoConstants.BLANK + DBConstants.APEL2);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.APEL1_077
				+ DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.BLANK + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.APEL2_077 + DaoConstants.SIGNO_CONCATENACION
				+ DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.NOMBRE_077 + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AS + DBConstants.NOMBRECOMPLETO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_054
				+ DaoConstants.BLANK + DBConstants.TIPOENTIDAD);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_054
				+ DaoConstants.BLANK + DBConstants.IDENTIDAD);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.IND_VIP_054
				+ DaoConstants.BLANK + DBConstants.INDVIP);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.DECODE + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.IND_VIP_054 + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.NULL + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO + ActivoEnum.NO.getValue() + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage(ActivoEnum.NO.getLabel(), null, locale)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO + ActivoEnum.SI.getValue() + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage(ActivoEnum.SI.getLabel(), null, locale)
				+ DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.GESTOREXPEDIENTESVIPDESCEU);
		// GestorExpediente
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.CIF + DaoConstants.BLANK
				+ DBConstants.CIF);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_EU
				+ DaoConstants.BLANK + DBConstants.DESCAMPENTIDADEU);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES
				+ DaoConstants.BLANK + DBConstants.DESCAMPENTIDADES);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.DESC_EU
				+ DaoConstants.BLANK + DBConstants.DESCENTIDADEU);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.DESC_ES
				+ DaoConstants.BLANK + DBConstants.DESCENTIDADES);
		// GESTORCOLORDEREU
		query.append("," + SqlUtils.normalizarCampoSql("b1.DESC_EU") + " || "
				+ SqlUtils.normalizarCampoSql("s1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("s1.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("s1.NOMBRE_077") + " GESTORCOLORDEREU");
		// GESTORCOLORDERES
		query.append("," + SqlUtils.normalizarCampoSql("b1.DESC_ES") + " || "
				+ SqlUtils.normalizarCampoSql("s1.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("s1.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("s1.NOMBRE_077") + " GESTORCOLORDERES");
		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// Expediente interpretación (Tabla 52)
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.ANYO_052);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.NUM_EXP_052);
		// Expediente traducción/revisión (Tabla 53)
		query.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.ANYO_053);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA_PUNTO + DBConstants.NUM_EXP_053);
		// Bitácora (Tabla 59)
		query.append(QueryUtils.getJoinTabla51And59());
		// Descripción del estado del expediente (Tabla 60)
		query.append(QueryUtils.getJoinTabla59And60());
		// Descripción de la fase del expediente (Tabla 61)
		query.append(DaoConstants.JOIN + DBConstants.TABLA_61 + DaoConstants.BLANK + DaoConstants.T6_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059);
		query.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_061);
		// Gestor expedientes (Tabla 54)
		query.append(DaoConstants.JOIN + DBConstants.TABLA_54 + DaoConstants.BLANK + DaoConstants.E1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.ANYO_054);
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_054);
		// Nombre y apellidos del solicitante (Tabla 77)
		query.append(DaoConstants.JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + DaoConstants.S1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.DNI_077);
		// Descripción de la entidad gestora (Vista X54JAPI_ENTIDADES_SOLIC)
		query.append(DaoConstants.JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK
				+ DaoConstants.B1_MINUSCULA);
		query.append(DaoConstants.ON + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_054);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.CODIGO);
		query.append(DaoConstants.AND + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_054);
		query.append(DaoConstants.SIGNOIGUAL + DaoConstants.B1_MINUSCULA_PUNTO + DBConstants.TIPO);

		return query.toString();
	}

	@Override
	protected RowMapper<Expediente> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ExpedientesCambioEstadoDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return DBConstants.ANYO_051 + "," + DBConstants.NUM_EXP_051;
	}

	@Override
	protected RowMapper<Expediente> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(Expediente bean, List<Object> params) {
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());

		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.WHERE);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051 + DaoConstants.SIGNOIGUAL_INTERROGACION);

		return query.toString();
	}

	@Override
	protected String getWhere(Expediente bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(Expediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		return null;
	}

	@Override
	public Object findExpedientesCambioEstado(Expediente bean, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount) {
		StringBuilder paginatedQuery = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		StringBuilder select = getQueryBuscadorExpedientes(bean, startsWith, isCount, params);

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(select.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMap, params.toArray());
		}
	}

	/**
	 * @param filterExpedienteFacturacion
	 *            ExpedienteFacturacion
	 * @param startsWith
	 *            Boolean
	 * @param isCount
	 *            boolean
	 * @param params
	 *            List<Object>
	 * @return StringBuilder
	 */
	private StringBuilder getQueryBuscadorExpedientes(Expediente bean, Boolean startsWith, boolean isCount,
			List<Object> params) {
		StringBuilder query = new StringBuilder(ExpedientesCambioEstadoDaoImpl.STRING_BUILDER_INIT);

		if (isCount) {
			query.append(DaoConstants.SELECT + DaoConstants.COUNT + DaoConstants.ABRIR_PARENTESIS);
			query.append(DaoConstants.DISTINCT);
			query.append("SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )");
			query.append(DaoConstants.CERRAR_PARENTESIS);
		} else {
			query.append(getSelect());

		}
		// FROM
		query.append(getFrom());

		// WHERE
		// filtros formulario busqueda pantalla
		query.append(getWhereBusquedaExpedientes(bean, params, startsWith));

		return query;
	}

	private String getWhereBusquedaExpedientes(Expediente filter, List<Object> params, boolean startsWith) {

		// DATOS GENERALES DEL EXPEDIENTE
		StringBuilder where = getWhereBusquedaDatosExp(filter, params);

		where.append(getWhereBusquedaDatosBitacora(filter, params));

		// EXPEDIENTE DE TRADUCCIÓN/REVISIÓN
		where.append(getWhereBusquedaExpTradRev(filter, params));

		// GESTOR DEL EXPEDIENTE
		if (filter.getGestorExpediente() != null && QueryUtils.entidadSolicitanteValida(filter.getGestorExpediente())) {
			// Entidad
			where.append(getWhereBusquedaGestorExp(filter, params));

			// Solicitante
			if (filter.getGestorExpediente().getSolicitante() != null
					&& filter.getGestorExpediente().getSolicitante().getDni() != null) {
				where.append(
						SqlUtils.generarWhereIgual(DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054,
								filter.getGestorExpediente().getSolicitante().getDni(), params));
			}

		}

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param where
	 * @return String
	 */
	private String getWhereBusquedaExpTradRev(Expediente filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		if (filter.getExpedienteTradRev() != null && filter.getExpedienteTradRev().getFechaEntregaIzoDesde() != null
				&& filter.getExpedienteTradRev().getFechaEntregaIzoHasta() != null) {
			// FECHA ENTREGA IZO / FECHA FIN DE LA INTERPRETACIÓN
			String fecha = DaoConstants.NVL + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T3_MINUSCULA_PUNTO
					+ DBConstants.FECHA_FINAL_IZO_053 + DaoConstants.SIGNO_COMA_SIN_ESPACIOS
					+ DaoConstants.T2_MINUSCULA_PUNTO + DBConstants.FECHA_FIN_052 + DaoConstants.CERRAR_PARENTESIS;
			where.append(getRangoFechasExpTradRev(fecha, filter, params));
		}

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param where
	 * @return String
	 */
	private String getWhereBusquedaDatosBitacora(Expediente filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		if (filter.getBitacoraExpediente() != null) {
			// ESTADO
			if (filter.getBitacoraExpediente().getEstadoExp() != null
					&& filter.getBitacoraExpediente().getEstadoExp().getId() != null) {
				where.append(SqlUtils.generarWhereIgual(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_059,
						filter.getBitacoraExpediente().getEstadoExp().getId(), params));
			}

			// FASE
			if (filter.getBitacoraExpediente().getFaseExp() != null
					&& filter.getBitacoraExpediente().getFaseExp().getId() != null) {
				where.append(
						SqlUtils.generarWhereIgual(DaoConstants.T4_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059,
								filter.getBitacoraExpediente().getFaseExp().getId(), params));
			}

		}

		return where.toString();
	}

	/**
	 * @param filter
	 *            Expediente
	 * @param params
	 *            List<Object>
	 * @return StringBuilder
	 */
	private StringBuilder getWhereBusquedaDatosExp(Expediente filter, List<Object> params) {
		// FILTROS BUSCADOR
		StringBuilder where = new StringBuilder(ExpedientesCambioEstadoDaoImpl.STRING_BUILDER_INIT);

		// TIPO EXPED
		where.append(
				SqlUtils.generarWhereIgual(DBConstants.T1ID_TIPO_EXPEDIENTE_051, filter.getIdTipoExpediente(), params));

		// NUM EXPEDIENTE
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));

		// TITULO
		where.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.T1TITULO_051, filter.getTitulo(), params, false));

		return where;
	}

	/**
	 * @param filter
	 *            ExpedienteFacturacion
	 * @param params
	 *            List<Object>
	 * @return String
	 */
	private String getWhereBusquedaGestorExp(Expediente filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		if (filter.getGestorExpediente().getEntidad().getTipo() != null) {
			where.append(SqlUtils.generarWhereIgual(DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.TIPO_ENTIDAD_054,
					filter.getGestorExpediente().getEntidad().getTipo(), params));
		}
		if (filter.getGestorExpediente().getEntidad().getCodigo() != null) {
			where.append(SqlUtils.generarWhereIgual(DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.ID_ENTIDAD_054,
					filter.getGestorExpediente().getEntidad().getCodigo(), params));
		}

		return where.toString();
	}

	/**
	 * @param campo
	 *            String
	 * @param filter
	 *            Expediente
	 * @param params
	 *            List<Object>
	 * @return String
	 */
	private String getRangoFechasExpTradRev(String campo, Expediente filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		where.append(SqlUtils.generarWhereMayorIgualFecha(campo, DaoConstants.DDMMYY,
				filter.getExpedienteTradRev().getFechaEntregaIzoDesde(), params));
		where.append(SqlUtils.generarWhereMenorIgualFecha(campo, DaoConstants.DDMMYY,
				filter.getExpedienteTradRev().getFechaEntregaIzoHasta(), params));

		return where.toString();
	}

	@Override
	public long comprobarExpFacturado(Long anyo, Integer numExp) {
		List<Object> params = new ArrayList<Object>();
		params.add(anyo);
		params.add(numExp);

		StringBuilder query = new StringBuilder(DaoConstants.SELECT + DaoConstants.COUNT);
		query.append(DaoConstants.ABRIR_PARENTESIS + DBConstants.ID_FACTURA_0A4 + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.FROM + DBConstants.TABLA_A4);
		query.append(DaoConstants.JOIN + DBConstants.TABLA_A5);
		query.append(DaoConstants.ON + DBConstants.ANYO_0A5 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND + DBConstants.ID_FACTURA_0A4 + DaoConstants.SIGNOIGUAL + " ID_FACTURA_0A5 ");
		query.append(DaoConstants.AND + DBConstants.NUM_EXP_0A5 + DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.JOIN + DBConstants.VISTAW05B12 + DaoConstants.BLANK + DaoConstants.T12_MINUSCULA);
		query.append(DaoConstants.ON + DBConstants.ID_LIQUIDACION_0A4 + DaoConstants.SIGNOIGUAL
				+ DaoConstants.T12_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.T12_REFERENCIA);
		query.append(SqlUtils.generarWhereDistinto(
				DaoConstants.BLANK + DaoConstants.T12_MINUSCULA_PUNTO + DBConstants.T00_ESTADO_ID + DaoConstants.BLANK,
				EstadoFacturaEnum.ANULADA.getValue(), params));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	private String getSelectConfLeyenda() {
		StringBuilder query = new StringBuilder();

		query.append(DaoConstants.SELECT + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_0A9
				+ DaoConstants.BLANK + DBConstants.IDESTADOEXP + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXP_0A9 + DaoConstants.BLANK
				+ DBConstants.IDFASEEXPEDIENTE + DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ORDEN_0A9 + DaoConstants.BLANK + DBConstants.ORDEN
				+ DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NIVEL_0A9 + DaoConstants.BLANK + DBConstants.NIVEL
				+ DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.TEXTO_0A9 + DaoConstants.BLANK + DBConstants.TEXTO);

		return query.toString();
	}

	private String getFromConfLeyenda() {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.FROM + DBConstants.TABLA_A9 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		return query.toString();
	}

	private String getWhereConfLeyenda(Leyenda bean, List<Object> params) {
		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_ESTADO_EXP_0A9,
				bean.getIdEstadosExp(), params));
		query.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_FASE_EXP_0A9,
				bean.getIdFaseExp(), params));

		return query.toString();
	}

	@Override()
	public List<Leyenda> findConfLeyenda(Leyenda bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(this.getSelectConfLeyenda());
		query.append(this.getFromConfLeyenda());
		query.append(this.getWhereConfLeyenda(bean, params));

		return this.getJdbcTemplate().query(query.toString(), this.rwMapConfLeyenda, params.toArray());
	}

}
