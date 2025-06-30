package com.ejie.aa79b.dao;

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
import com.ejie.aa79b.dao.mapper.ExpedientesAAnularRowMapper;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.enums.MotivosAnulacionEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.ExpedienteDaoUtils;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class AnulacionesDaoImpl extends GenericoDaoImpl<Expediente> implements AnulacionesDao {

	protected static final String TIPO_REQUERIMIENTO_ENUM = "TipoRequerimientoEnum";
	protected static final String ESTADO_REQUERIMIENTO_ENUM = "EstadoRequerimientoEnum";

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public AnulacionesDaoImpl() {
		// Constructor
		super(Expediente.class);
	}

	private RowMapper<Expediente> rwMapBusqExpAAnular = new ExpedientesAAnularRowMapper();

	@Override
	protected String getSelect() {
		Locale es = new Locale("es");
		Locale eu = new Locale("eu");
		Locale locale = LocaleContextHolder.getLocale();
		String sqlDateFormat;
		if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
			sqlDateFormat = Constants.SQL_DATE_FORMAT_EU;
		} else {
			sqlDateFormat = Constants.SQL_DATE_FORMAT_ES;
		}
		StringBuilder select = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		select.append(DaoConstants.SELECT);

		// TABLA 51 - Datos generales del expediente
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
				.append("') AS " + DBConstants.TIPOEXPEDIENTEDESCES);
		select.append(", " + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '"
				+ TipoExpedienteEnum.INTERPRETACION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu))
				.append("') AS " + DBConstants.TIPOEXPEDIENTEDESCEU);
		select.append(", t1.TITULO_051 ").append(DBConstants.TITULO);
		select.append(", ").append(SqlUtils.normalizarCampoSql("t1.TITULO_051"))
				.append(DaoConstants.AS + DBConstants.TITULONORM);

		// TABLA 53 - Expediente de traducción/revisión
		select.append(", r1.FECHA_FINAL_SOLIC_053 " + DBConstants.FECHAFINALSOLIC);
		select.append(", TO_CHAR(r1.FECHA_FINAL_SOLIC_053,'HH24:MI') " + DBConstants.HORAFINALSOLIC);
		select.append(", r1.FECHA_FINAL_IZO_053 " + DBConstants.FECHAFINALIZO);
		select.append(", TO_CHAR(r1.FECHA_FINAL_IZO_053,'HH24:MI') " + DBConstants.HORAFINALIZO);

		// TABLA 52 - Expediente de interpretacion
		select.append(", i1.FECHA_INI_052 " + DBConstants.FECHAINICIO);
		select.append(", TO_CHAR(i1.FECHA_INI_052,'HH24:MI') " + DBConstants.HORAINICIO);
		select.append(", i1.FECHA_FIN_052 " + DBConstants.FECHAFIN);
		select.append(", TO_CHAR(i1.FECHA_FIN_052,'HH24:MI') " + DBConstants.HORAFIN);

		// TABLA 64 - Requerimientos expediente
		StringBuilder subSelect = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		subSelect.append(DaoConstants.S1_MINUSCULA);
		subSelect.append(DaoConstants.SIGNO_PUNTO);
		subSelect.append(DBConstants.TIPO_REQUERIMIENTO_064);
		// Tipo de requerimiento
		select.append(DaoConstants.SIGNO_COMA);
		select.append(subSelect.toString());
		select.append(DaoConstants.BLANK);
		select.append(DBConstants.TIPOREQUERIMIENTO);
		// Descripción tipo de requerimiento
		select.append(DAOUtils.getDecodeAcciones(subSelect.toString(), DBConstants.TIPOREQUERIMIENTODESCEU, this.msg,
				TIPO_REQUERIMIENTO_ENUM, eu));
		select.append(DAOUtils.getDecodeAcciones(subSelect.toString(), DBConstants.TIPOREQUERIMIENTODESCES, this.msg,
				TIPO_REQUERIMIENTO_ENUM, es));
		select.append(", s1.FECHA_LIMITE_064 " + DBConstants.FECHALIMITE);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ " s1.FECHA_LIMITE_064 ,'HH24:MI'" + DaoConstants.CERRAR_PARENTESIS + DBConstants.HORALIMITE);

		subSelect = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		subSelect.append(DaoConstants.S1_MINUSCULA);
		subSelect.append(DaoConstants.SIGNO_PUNTO);
		subSelect.append(DBConstants.ESTADO_SUBSANACION_064);
		// Estado subsanación/requerimiento
		select.append(DaoConstants.SIGNO_COMA);
		select.append(subSelect.toString());
		select.append(DaoConstants.BLANK);
		select.append(DBConstants.ESTADOSUBSANACION);
		// Descripción estado subsanación/requerimiento
		select.append(DAOUtils.getDecodeAcciones(subSelect.toString(), DBConstants.ESTADOSUBSANACIONDESCEU, this.msg,
				ESTADO_REQUERIMIENTO_ENUM, eu));
		select.append(DAOUtils.getDecodeAcciones(subSelect.toString(), DBConstants.ESTADOSUBSANACIONDESCES, this.msg,
				ESTADO_REQUERIMIENTO_ENUM, es));

		select.append(DaoConstants.SIGNO_COMA);
		select.append(DaoConstants.NVL);
		select.append(DaoConstants.ABRIR_PARENTESIS);
		select.append(DaoConstants.R1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FINAL_SOLIC_053);
		select.append(DaoConstants.SIGNO_COMA);
		select.append(DaoConstants.I1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_INI_052);
		select.append(DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.AS);
		select.append(DBConstants.FECHAENTREGA);
		select.append(DaoConstants.SIGNO_COMA);
		select.append(DaoConstants.CASE + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051);
		select.append(DaoConstants.WHEN + DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
				+ DaoConstants.SIGNO_APOSTROFO);
		select.append(DaoConstants.THEN);
		select.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage("label.inicio", null, locale)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO
				+ Constants.DOS_PUNTOS + DaoConstants.BLANK + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_CONCATENACION);
		select.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.TO_DATE
				+ DaoConstants.ABRIR_PARENTESIS);
		select.append(DaoConstants.I1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_INI_052);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + DaoConstants.DDMMYY
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO);
		select.append(sqlDateFormat);
		select.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + DaoConstants.BLANK
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.TO_CHAR
				+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.I1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_INI_052 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + DaoConstants.BLANK
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.SALTO_DE_LINEA
				+ DaoConstants.SIGNO_CONCATENACION);
		select.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage("label.fin", null, locale)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO
				+ Constants.DOS_PUNTOS + DaoConstants.BLANK + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_CONCATENACION);
		select.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.TO_DATE
				+ DaoConstants.ABRIR_PARENTESIS);
		select.append(DaoConstants.I1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FIN_052);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + DaoConstants.DDMMYY
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO);
		select.append(sqlDateFormat);
		select.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + DaoConstants.BLANK
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.TO_CHAR
				+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.I1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_FIN_052 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.ELSE);
		select.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage("label.solicitado", null, locale)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO
				+ Constants.DOS_PUNTOS + DaoConstants.BLANK + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_CONCATENACION);
		select.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.TO_DATE
				+ DaoConstants.ABRIR_PARENTESIS);
		select.append(DaoConstants.R1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FINAL_SOLIC_053);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + DaoConstants.DDMMYY
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO);
		select.append(sqlDateFormat);
		select.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + DaoConstants.BLANK
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.TO_CHAR
				+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.R1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_FINAL_SOLIC_053 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + DaoConstants.BLANK
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.SALTO_DE_LINEA
				+ DaoConstants.SIGNO_CONCATENACION);
		select.append(DaoConstants.SIGNO_APOSTROFO + this.msg.getMessage("label.IZO", null, locale)
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO
				+ Constants.DOS_PUNTOS + DaoConstants.BLANK + DaoConstants.SIGNO_APOSTROFO
				+ DaoConstants.SIGNO_CONCATENACION);
		select.append(DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS + DaoConstants.TO_DATE
				+ DaoConstants.ABRIR_PARENTESIS);
		select.append(DaoConstants.R1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FINAL_IZO_053);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + DaoConstants.DDMMYY
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO);
		select.append(sqlDateFormat);
		select.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + DaoConstants.BLANK
				+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION + DaoConstants.TO_CHAR
				+ DaoConstants.ABRIR_PARENTESIS + DaoConstants.R1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_FINAL_IZO_053 + DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA
				+ DaoConstants.CERRAR_PARENTESIS);
		select.append(DaoConstants.END);
		select.append(DaoConstants.AS);
		select.append(DBConstants.FECHAENTREGAFORMATEADA);

		return select.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		// TABLA 51 - Datos generales expediente
		from.append(" FROM AA79B51S01 t1 ");

		// TABLA 52 - interpretacion
		from.append("LEFT JOIN AA79B52S01 i1 ON ");
		from.append("t1.ANYO_051 = i1.ANYO_052 ");
		from.append("AND t1.NUM_EXP_051 = i1.NUM_EXP_052 ");

		// TABLA 53 - trad/rev
		from.append("LEFT JOIN AA79B53S01 r1 ");
		from.append("ON t1.ANYO_051     = r1.ANYO_053 ");
		from.append("AND t1.NUM_EXP_051 = r1.NUM_EXP_053 ");

		// TABLA 59 - Bitacora expediente
		from.append("JOIN AA79B59S01 ESTADO_ACTUAL ");
		from.append("ON t1.NUM_EXP_051 = ESTADO_ACTUAL.NUM_EXP_059 ");
		from.append("AND t1.ANYO_051 = ESTADO_ACTUAL.ANYO_059 ");
		from.append("AND t1.ESTADO_BITACORA_051 = ESTADO_ACTUAL.ID_ESTADO_BITACORA_059 ");

		// TABLA 64 - Requerimientos expediente
		from.append("JOIN AA79B64S01 s1 ");
		from.append(
				"ON (SELECT MAX(ID_064) FROM AA79B64T00 JOIN AA79B59T00 ON ID_REQUERIMIENTO_059 = ID_064 WHERE NUM_EXP_059 = t1.NUM_EXP_051 AND ANYO_059 = t1.ANYO_051) = s1.ID_064 ");

		// TABLA 54 - Gestor expedientes
		from.append("JOIN AA79B54S01 h1 ");
		from.append("ON t1.ANYO_051     = h1.ANYO_054 ");
		from.append("AND t1.NUM_EXP_051 = h1.NUM_EXP_054 ");

		return from.toString();
	}

	@Override
	protected RowMapper<Expediente> getRwMap() {
		return null;
	}

	@Override
	protected String[] getOrderBy() {
		String[] strArray = null;
		return strArray;
	}

	@Override
	protected String getPK() {
		return null;
	}

	@Override
	protected RowMapper<Expediente> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(Expediente bean, List<Object> params) {
		return null;
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
	public Object busquedaexpaanular(Expediente filter, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder select = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);

		if (isCount) {
			select.append(DaoConstants.SELECT);
			select.append(" COUNT(DISTINCT SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )) ");
		} else {
			select.append(getSelect());
		}
		select.append(getFrom());
		select.append(getWhere(filter, params, startsWith));

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, select));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMapBusqExpAAnular, params.toArray());
		}
	}

	/**
	 * @param filter
	 * @param params
	 * @param startsWith
	 * @return
	 */
	private String getWhere(Expediente filter, List<Object> params, boolean startsWith) {
		StringBuilder where = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);
		where.append(SqlUtils.generarWhereIgual("t1.ESTADO_BAJA_051", Constants.ALTA, params));

		if (filter.getTecnico() != null) {
			where.append(
					SqlUtils.generarWhereLike("t1.DNI_TECNICO_051", filter.getTecnico().getDni(), params, startsWith));
		}
		where.append(getWhereClausuleExpAAnular(filter));
		where.append(getWhereBusquedaExpAAnular(filter, params, startsWith));

		return where.toString();
	}

	/**
	 * @return
	 */
	private String getWhereClausuleExpAAnular(Expediente filter) {
		StringBuilder query = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.AND);
		// MOTIVO ANULACION
		if (filter.getMotivosAnulacion() != null && filter.getMotivosAnulacion().getId012() != null) {
			if (filter.getMotivosAnulacion().getId012() == MotivosAnulacionEnum.PLAZO_SUBS_EXPIRADO.getValue()) {
				query.append(QueryUtils.getQueryPlazoSubsanacionExpirado(filter.getMotivosAnulacion().getId012()));
			} else if (filter.getMotivosAnulacion().getId012() == MotivosAnulacionEnum.FECHA_PROP_NO_ACEPTADA_PLAZO
					.getValue()
					|| filter.getMotivosAnulacion().getId012() == MotivosAnulacionEnum.PRESUP_NO_ACEPTADO_PLAZO
							.getValue()) {
				query.append(DaoConstants.ABRIR_PARENTESIS);
				query.append(QueryUtils.getQueryExpPdteTramitacionClte());
				query.append(DaoConstants.AND);
				query.append(QueryUtils.getQueryPlazoExpirado(filter.getMotivosAnulacion().getId012()));
				query.append(DaoConstants.CERRAR_PARENTESIS);

			} else if (filter.getMotivosAnulacion().getId012() == MotivosAnulacionEnum.FECHA_PROP_RECHAZADA
					.getValue()) {
				query.append(DaoConstants.ABRIR_PARENTESIS);
				query.append(QueryUtils.getQuerySubsanacionRechazada());
				query.append(DaoConstants.CERRAR_PARENTESIS);
			} else {
				query.append(QueryUtils.getWhereExpAAnular());
			}
		} else {
			query.append(QueryUtils.getWhereExpAAnular());
		}

		return query.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param startsWith
	 * @return
	 */
	private String getWhereBusquedaExpAAnular(Expediente filter, List<Object> params, boolean startsWith) {
		// FILTROS BUSCADOR
		StringBuilder where = new StringBuilder(AnulacionesDaoImpl.STRING_BUILDER_INIT);

		// TIPO EXPEDIENTE
		where.append(
				SqlUtils.generarWhereIgual(DBConstants.T1ID_TIPO_EXPEDIENTE_051, filter.getIdTipoExpediente(), params));

		// NUM EXPEDIENTE
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));

		// TITULO
		where.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.T1TITULO_051, filter.getTitulo(), params, false));

		getWhereClausuleGestorExp(filter, params, where, startsWith);

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param where
	 */
	private void getWhereClausuleGestorExp(Expediente filter, List<Object> params, StringBuilder where,
			boolean startsWith) {
		if (filter.getGestorExpediente() != null) {
			// ENTIDAD GESTORA
			if (ExpedienteDaoUtils.isEntidadNula(filter)) {
				where.append(SqlUtils.generarWhereLike("h1.TIPO_ENTIDAD_054",
						filter.getGestorExpediente().getEntidad().getTipo(), params, startsWith));
				where.append(SqlUtils.generarWhereIgual("h1.ID_ENTIDAD_054",
						filter.getGestorExpediente().getEntidad().getCodigo(), params));
			}
			// CONTACTO GESTOR
			if (ExpedienteDaoUtils.isSolicitanteNulo(filter)) {
				where.append(SqlUtils.generarWhereLike("h1.DNI_SOLICITANTE_054",
						filter.getGestorExpediente().getSolicitante().getDni(), params, startsWith));
			}
		}

	}

}
