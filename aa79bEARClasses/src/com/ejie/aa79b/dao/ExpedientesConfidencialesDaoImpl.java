package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
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
import com.ejie.aa79b.dao.mapper.ExpedientesConfidencialesRowMapper;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.ExpedienteDaoUtils;
import com.ejie.aa79b.utils.QueryUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class ExpedientesConfidencialesDaoImpl extends GenericoDaoImpl<Expediente>
		implements ExpedientesConfidencialesDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ANYO, DBConstants.NUMEXP,
			DBConstants.ANYONUMEXPCONCATENADO, DBConstants.IDTIPOEXPEDIENTE, DBConstants.TIPOEXPEDIENTE,
			DBConstants.TITULO, DBConstants.FECHAFINALIZO, DBConstants.HORAFINALIZO, DBConstants.IDESTADOEXP,
			DBConstants.ESTADOEXPEDIENTEDESCEU, DBConstants.ESTADOEXPEDIENTEDESCABREU, DBConstants.DESCAMPENTIDADEU,
			DBConstants.DESCENTIDADEU, DBConstants.NOMBRECOMPLETO };

	public ExpedientesConfidencialesDaoImpl() {
		// Constructor
		super(Expediente.class);
	}

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Expediente> rwMap = new ExpedientesConfidencialesRowMapper();

	private RowMapper<Expediente> rwMapPK = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
			expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return expediente;
		}
	};

	@Override
	protected String getSelect() {
		Locale locale = LocaleContextHolder.getLocale();

		StringBuilder query = new StringBuilder();
		query.append(DaoConstants.SELECT);
		query.append(DaoConstants.DISTINCT);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.ANYO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.NUMEXP);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.SUBSTR);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(Constants.STRING_DOS);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(Constants.STRING_CUATRO);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(Constants.CONTRABARRA);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.LPAD);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(Constants.STRING_SEIS);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(Constants.STRING_CERO);
		query.append(DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DBConstants.ANYONUMEXPCONCATENADO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ID_TIPO_EXPEDIENTE_051);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.IDTIPOEXPEDIENTE);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + ", '" + TipoExpedienteEnum.INTERPRETACION.getValue()
				+ "', '").append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, locale))
				.append("', '" + TipoExpedienteEnum.TRADUCCION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, locale))
				.append("', '" + TipoExpedienteEnum.REVISION.getValue() + "', '")
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, locale)).append("'");
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AS);
		query.append(DBConstants.TIPOEXPEDIENTE);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.TITULO_051);
		query.append(DaoConstants.BLANK);
		query.append(DBConstants.TITULO);
		// Expediente traducción/revisión
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.NVL);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.FECHA_FINAL_IZO_053);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.FECHA_FINAL_SOLIC_053);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DBConstants.FECHAFINALIZO);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.TO_CHAR);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.NVL);
		query.append(DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.FECHA_FINAL_IZO_053);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(DaoConstants.T2_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.FECHA_FINAL_SOLIC_053);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
		query.append(DaoConstants.FORMATO_HORA);
		query.append(DaoConstants.CERRAR_PARENTESIS);
		query.append(DBConstants.HORAFINALIZO);
		// Bitácora
		// IDESTADOEXP
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_ESTADO_EXP_059 + DaoConstants.BLANK + DBConstants.IDESTADOEXP);
		// ESTADOEXPEDIENTEDESCEU
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_EU_060 + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCEU);
		// ESTADOEXPEDIENTEDESCABREU
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_EU_060 + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCABREU);
		// Nombre y apellidos del contacto gestor
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054
				+ DaoConstants.BLANK + DBConstants.DNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.PREDNI_077
				+ DaoConstants.BLANK + DBConstants.PREDNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.SUFDNI_077
				+ DaoConstants.BLANK + DBConstants.SUFDNI);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.ABRIR_PARENTESIS);
		query.append(DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.PREDNI_077 + DaoConstants.SIGNO_CONCATENACION);
		query.append(
				DaoConstants.E1_MINUSCULA_PUNTO + DBConstants.DNI_SOLICITANTE_054 + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.SUFDNI_077 + DaoConstants.CERRAR_PARENTESIS);
		query.append(DaoConstants.AS + DBConstants.DNICOMPLETO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.NOMBRE_077
				+ DaoConstants.BLANK + DBConstants.NOMBRE);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.APEL1_077
				+ DaoConstants.BLANK + DBConstants.APEL1);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.APEL2_077
				+ DaoConstants.BLANK + DBConstants.APEL2);
		query.append(DaoConstants.SIGNO_COMA);
		query.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.APEL1_077
				+ DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.BLANK + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.APEL2_077 + DaoConstants.SIGNO_CONCATENACION
				+ DaoConstants.SIGNO_APOSTROFO);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_CONCATENACION);
		query.append(DaoConstants.S2_MINUSCULA_PUNTO + DBConstants.NOMBRE_077 + DaoConstants.CERRAR_PARENTESIS);
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
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.CIF + DaoConstants.BLANK
				+ DBConstants.CIF);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_EU
				+ DaoConstants.BLANK + DBConstants.DESCAMPENTIDADEU);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.DESC_AMP_ES
				+ DaoConstants.BLANK + DBConstants.DESCAMPENTIDADES);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.DESC_EU
				+ DaoConstants.BLANK + DBConstants.DESCENTIDADEU);
		query.append(DaoConstants.SIGNO_COMA + DaoConstants.S1_MINUSCULA_PUNTO + DBConstants.DESC_ES
				+ DaoConstants.BLANK + DBConstants.DESCENTIDADES);
		// GESTORCOLORDEREU
		query.append("," + SqlUtils.normalizarCampoSql("s1.DESC_EU") + " || "
				+ SqlUtils.normalizarCampoSql("s2.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("s2.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("s2.NOMBRE_077") + " GESTORCOLORDEREU");
		// GESTORCOLORDERES
		query.append("," + SqlUtils.normalizarCampoSql("s1.DESC_ES") + " || "
				+ SqlUtils.normalizarCampoSql("s2.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("s2.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("s2.NOMBRE_077") + " GESTORCOLORDERES");

		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder();

		from.append(DaoConstants.FROM);
		from.append(DBConstants.TABLA_51);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.T1_MINUSCULA);
		// Expediente traducción/revisión (Tabla 53)
		from.append(DaoConstants.JOIN);
		from.append(DBConstants.TABLA_53);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.T2_MINUSCULA);
		from.append(DaoConstants.ON);
		from.append(DaoConstants.T2_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.ANYO_053);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.T1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.ANYO_051);
		from.append(DaoConstants.AND);
		from.append(DaoConstants.T2_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.NUM_EXP_053);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.T1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.NUM_EXP_051);
		// Tareas (Tabla 81)
		from.append(DaoConstants.LEFT_JOIN);
		from.append(DBConstants.TABLA_81);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.T3_MINUSCULA);
		from.append(DaoConstants.ON);
		from.append(DaoConstants.T3_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.ANYO_081);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.T1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.ANYO_051);
		from.append(DaoConstants.AND);
		from.append(DaoConstants.T3_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.NUM_EXP_081);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.T1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.NUM_EXP_051);
		// Bitácora (Tabla 59)
		from.append(QueryUtils.getJoinTabla51And59());
		// Descripción del estado del expediente (Tabla 60)
		from.append(QueryUtils.getJoinTabla59And60());
		// Gestor expedientes (Tabla 54)
		from.append(DaoConstants.JOIN);
		from.append(DBConstants.TABLA_54);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.E1_MINUSCULA);
		from.append(DaoConstants.ON);
		from.append(DaoConstants.T1_MINUSCULA_PUNTO);
		from.append(DBConstants.ANYO_051);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.E1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.ANYO_054);
		from.append(DaoConstants.AND);
		from.append(DaoConstants.T1_MINUSCULA_PUNTO);
		from.append(DBConstants.NUM_EXP_051);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.E1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.NUM_EXP_054);
		// Descripción de la entidad gestora (Vista X54JAPI_ENTIDADES_SOLIC)
		from.append(DaoConstants.JOIN);
		from.append(DBConstants.VISTAX54JAPIENTIDADESSOLIC);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.S1_MINUSCULA);
		from.append(DaoConstants.ON);
		from.append(DaoConstants.S1_MINUSCULA_PUNTO);
		from.append(DBConstants.TIPO);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.E1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.TIPO_ENTIDAD_054);
		from.append(DaoConstants.AND);
		from.append(DaoConstants.S1_MINUSCULA_PUNTO);
		from.append(DBConstants.CODIGO);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.E1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.ID_ENTIDAD_054);
		// Nombre y apellidos del solicitante (Tabla 77)
		from.append(DaoConstants.JOIN);
		from.append(DBConstants.TABLA_77);
		from.append(DaoConstants.BLANK);
		from.append(DaoConstants.S2_MINUSCULA);
		from.append(DaoConstants.ON);
		from.append(DaoConstants.S2_MINUSCULA_PUNTO);
		from.append(DBConstants.DNI_077);
		from.append(DaoConstants.SIGNOIGUAL);
		from.append(DaoConstants.E1_MINUSCULA);
		from.append(DaoConstants.SIGNO_PUNTO);
		from.append(DBConstants.DNI_SOLICITANTE_054);

		return from.toString();
	}

	@Override
	protected RowMapper<Expediente> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ExpedientesConfidencialesDaoImpl.ORDER_BY_WHITE_LIST;
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
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.ANYO_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);
		query.append(DaoConstants.AND);
		query.append(DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.SIGNO_PUNTO);
		query.append(DBConstants.NUM_EXP_051);
		query.append(DaoConstants.SIGNOIGUAL_INTERROGACION);

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

	/**
	 * @param filter
	 * @param params
	 * @param startsWith
	 * @return
	 */
	private String getWhere(Expediente filter, String dni, List<Object> params, boolean startsWith) {
		StringBuilder where = new StringBuilder(ExpedientesConfidencialesDaoImpl.STRING_BUILDER_INIT);
		where.append(DaoConstants.WHERE_1_1);
		where.append(SqlUtils.generarWhereIgual(
				DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + "ESTADO_BAJA_051", Constants.ALTA, params));
		where.append(SqlUtils.generarWhereIgual(
				DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_CONFIDENCIAL_053, Constants.SI,
				params));
		if (StringUtils.isNotBlank(dni)) {
			where.append(DaoConstants.AND);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(DaoConstants.T1_MINUSCULA);
			where.append(DaoConstants.SIGNO_PUNTO);
			where.append(DBConstants.DNI_TECNICO_051);
			where.append(DaoConstants.SIGNOIGUAL);
			where.append(DaoConstants.SIGNO_APOSTROFO);
			where.append(dni);
			where.append(DaoConstants.SIGNO_APOSTROFO);
			where.append(DaoConstants.OR);
			where.append(DaoConstants.T3_MINUSCULA);
			where.append(DaoConstants.SIGNO_PUNTO);
			where.append(DBConstants.DNI_RECURSO_081);
			where.append(DaoConstants.SIGNOIGUAL);
			where.append(DaoConstants.SIGNO_APOSTROFO);
			where.append(dni);
			where.append(DaoConstants.SIGNO_APOSTROFO);
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}

		where.append(getWhereClausuleExpConfi(filter));
		where.append(getWhereBusquedaExpConfi(filter, params, startsWith));

		return where.toString();
	}

	private String getWhereClausuleExpConfi(Expediente filter) {
		StringBuilder query = new StringBuilder(ExpedientesConfidencialesDaoImpl.STRING_BUILDER_INIT);

		query.append(DaoConstants.AND);
		// Tipo de expediente
		if (StringUtils.isNotBlank(filter.getIdTipoExpediente())) {
			query.append(DaoConstants.T1_MINUSCULA);
			query.append(DaoConstants.SIGNO_PUNTO);
			query.append(DBConstants.ID_TIPO_EXPEDIENTE_051);
			query.append(DaoConstants.SIGNOIGUAL);
			query.append(DaoConstants.SIGNO_APOSTROFO);
			query.append(filter.getIdTipoExpediente());
			query.append(DaoConstants.SIGNO_APOSTROFO);
		} else {
			query.append(DaoConstants.ABRIR_PARENTESIS);
			query.append(DaoConstants.T1_MINUSCULA);
			query.append(DaoConstants.SIGNO_PUNTO);
			query.append(DBConstants.ID_TIPO_EXPEDIENTE_051);
			query.append(DaoConstants.SIGNOIGUAL);
			query.append(DaoConstants.SIGNO_APOSTROFO);
			query.append(TipoExpedienteEnum.TRADUCCION.getValue());
			query.append(DaoConstants.SIGNO_APOSTROFO);
			query.append(DaoConstants.OR);
			query.append(DaoConstants.T1_MINUSCULA);
			query.append(DaoConstants.SIGNO_PUNTO);
			query.append(DBConstants.ID_TIPO_EXPEDIENTE_051);
			query.append(DaoConstants.SIGNOIGUAL);
			query.append(DaoConstants.SIGNO_APOSTROFO);
			query.append(TipoExpedienteEnum.REVISION.getValue());
			query.append(DaoConstants.SIGNO_APOSTROFO);
			query.append(DaoConstants.CERRAR_PARENTESIS);
		}

		return query.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param startsWith
	 * @return
	 */
	private String getWhereBusquedaExpConfi(Expediente filter, List<Object> params, boolean startsWith) {
		// FILTROS BUSCADOR
		StringBuilder where = new StringBuilder();

		// NUM EXPEDIENTE
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));

		// TITULO
		where.append(SqlUtils.generarWhereLikeNormalizado(DBConstants.T1TITULO_051, filter.getTitulo(), params, false));

		// ESTADO
		where.append(filtroEstado(filter, params));

		// GESTOR EXPEDIENTE
		where.append(filtroGestorExpediente(filter, params, startsWith));

		return where.toString();
	}

	/**
	 * @param filter
	 * @param params
	 * @param startsWith
	 * @param where
	 * @return String
	 */
	private String filtroGestorExpediente(Expediente filter, List<Object> params, boolean startsWith) {
		StringBuilder where = new StringBuilder();

		if (filter.getGestorExpediente() != null) {
			// CONTACTO GESTOR
			if (ExpedienteDaoUtils.isSolicitanteNulo(filter)) {
				where.append(SqlUtils.generarWhereLike(
						DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.DNI_SOLICITANTE_054,
						filter.getGestorExpediente().getSolicitante().getDni(), params, startsWith));
			}
			// ENTIDAD GESTORA
			if (ExpedienteDaoUtils.isEntidadNula(filter)) {
				where.append(SqlUtils.generarWhereLike(
						DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054,
						filter.getGestorExpediente().getEntidad().getTipo(), params, startsWith));
				where.append(SqlUtils.generarWhereIgual(
						DaoConstants.E1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054,
						filter.getGestorExpediente().getEntidad().getCodigo(), params));
			}
		}

		return where.toString();
	}

	private String filtroEstado(Expediente filter, List<Object> params) {
		StringBuilder where = new StringBuilder();

		if (filter.getBitacoraExpediente() != null && filter.getBitacoraExpediente().getEstadoExp() != null
				&& filter.getBitacoraExpediente().getEstadoExp().getId() != null) {

			if (filter.getBitacoraExpediente().getEstadoExp().getId().equals(Constants.CEROLONG)) {
				where.append(SqlUtils.generarWhereIgual(
						DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ESTADO_BAJA_051,
						Constants.BAJA, params));
			} else {
				where.append(SqlUtils.generarWhereIgual(
						DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ESTADO_EXP_059,
						filter.getBitacoraExpediente().getEstadoExp().getId(), params));
			}

			if (filter.getBitacoraExpediente().getFaseExp() != null
					&& filter.getBitacoraExpediente().getFaseExp().getId() != null) {
				where.append(SqlUtils.generarWhereIgual(
						DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_FASE_EXPEDIENTE_059,
						filter.getBitacoraExpediente().getFaseExp().getId(), params));
			}
		}

		return where.toString();
	}

	@Override
	public Object busquedaexpconf(Expediente filter, String dni, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ExpedientesConfidencialesDaoImpl.STRING_BUILDER_INIT);
		StringBuilder paginatedQuery = new StringBuilder(ExpedientesConfidencialesDaoImpl.STRING_BUILDER_INIT);

		if (isCount) {
			query.append(DaoConstants.SELECT + DaoConstants.COUNT + DaoConstants.ABRIR_PARENTESIS);
			query.append(DaoConstants.DISTINCT);
			query.append("SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )");
			query.append(DaoConstants.CERRAR_PARENTESIS);
		} else {
			query.append(getSelect());
		}
		query.append(getFrom());
		query.append(getWhere(filter, dni, params, startsWith));

		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.rwMap, params.toArray());
		}
	}

}
