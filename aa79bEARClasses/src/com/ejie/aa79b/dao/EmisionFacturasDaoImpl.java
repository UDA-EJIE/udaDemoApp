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
import com.ejie.aa79b.common.PropertiesConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.ConsultaFacturacionExpedienteRowMapper;
import com.ejie.aa79b.dao.mapper.EntidadSolicitanteRowMapper;
import com.ejie.aa79b.model.ConsultaFacturacionExpediente;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoFacturaEnum;
import com.ejie.aa79b.model.enums.TipoEntidadEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.Utils;

@Repository()
@Transactional()
public class EmisionFacturasDaoImpl extends GenericoDaoImpl<ConsultaFacturacionExpediente>
		implements EmisionFacturasDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public EmisionFacturasDaoImpl() {
		super(ConsultaFacturacionExpediente.class);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { "DESCENTIDADEU", "NOMBRE", "IVA",
			"COUNTEXPEDIENTES", DBConstants.CENTRO_ORGANICO_ES, DBConstants.CENTRO_ORGANICO_EU };

	private RowMapper<ConsultaFacturacionExpediente> rwMap = new ConsultaFacturacionExpedienteRowMapper();

	@Override
	protected String getWith() {
		StringBuilder with = new StringBuilder();
		with.append("WITH EXP_PENDIENTES AS (");
		with.append("SELECT TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058, DNI_CONTACTO_058");
		with.append(", DECODE(T1.ID_TIPO_EXPEDIENTE_051, '").append(TipoExpedienteEnum.INTERPRETACION.getValue())
				.append("', 0, 1) AS TIPO_EXPEDIENTE");
		with.append(
				", FECHA_ALTA_051, FECHA_FINAL_IZO_053, FECHA_INI_052, TIPO_ENTIDAD_054, ID_ENTIDAD_054, DNI_SOLICITANTE_054");
		with.append(" FROM AA79B51S01 T1");
		with.append(" LEFT JOIN AA79B53S01 T3 ON T1.ANYO_051 = T3.ANYO_053 AND T1.NUM_EXP_051 = T3.NUM_EXP_053");
		with.append(" LEFT JOIN AA79B52S01 T2 ON T1.ANYO_051 = T2.ANYO_052 AND T1.NUM_EXP_051 = T2.NUM_EXP_052");
		with.append(" JOIN AA79B54S01 T4 ON T1.ANYO_051 = T4.ANYO_054 AND T1.NUM_EXP_051 = T4.NUM_EXP_054");
		with.append(" JOIN AA79B58S01 T5 ON T1.ANYO_051 = T5.ANYO_058 AND T1.NUM_EXP_051 = T5.NUM_EXP_058");
		with.append(
				" JOIN AA79B59S01 T8 ON T1.ANYO_051 = T8.ANYO_059 AND T1.NUM_EXP_051 = T8.NUM_EXP_059 AND T1.ESTADO_BITACORA_051 = T8.ID_ESTADO_BITACORA_059");
		// release/v3.9.40 descarte los expedietes con estado ANULADO para la emisión
		// de facturas, contador de expedientes del buscador
		with.append(" AND T8.ID_ESTADO_EXP_059 IN (").append(EstadoExpedienteEnum.CERRADO.getValue()).append(", ")
				.append(EstadoExpedienteEnum.FINALIZADO.getValue()).append(")");
		// Esta tabla no hace falta
		with.append(" LEFT JOIN AA79B97S01 T12 ON T1.ANYO_051 = T12.ANYO_097 AND T1.NUM_EXP_051 = T12.NUM_EXP_097");
		with.append(" LEFT JOIN AA79B98S01 T13 ON T5.ID_058 = T13.ID_098 ");
		// Esta tabla no hace falta
		with.append(" LEFT JOIN AA79BA6S01 T14 ON T5.ID_058 = t14.ID_0A6 ");
		with.append(
				" LEFT JOIN (SELECT t10.ANYO_0A5, t10.NUM_EXP_0A5, TIPO_ENTIDAD_ASOC_0A4, ID_ENTIDAD_ASOC_0A4, DNI_CONTACTO_0A4");
		with.append(" FROM AA79BA5S01 t10");
		with.append(" JOIN AA79BA4S01 t9 ON t9.ID_FACTURA_0A4 = t10.ID_FACTURA_0A5");
		with.append(" LEFT JOIN W0512S01 t11 ON t9.ID_LIQUIDACION_0A4 = t11.T12_REFERENCIA");
		with.append(" WHERE CASE WHEN t9.ID_LIQUIDACION_0A4 IS NULL THEN 1");
		with.append(" WHEN t11.T00_ESTADO_ID <>" + EstadoFacturaEnum.ANULADA.getValue() + " THEN 1 ELSE 0 END = 1)");
		with.append(" ON t1.ANYO_051 = ANYO_0A5");
		with.append(" AND t1.NUM_EXP_051 = NUM_EXP_0A5");
		with.append(" AND t5.TIPO_ENTIDAD_ASOC_058 = TIPO_ENTIDAD_ASOC_0A4");
		with.append(" AND t5.ID_ENTIDAD_ASOC_058 = ID_ENTIDAD_ASOC_0A4");
		with.append(" AND nvl(t5.DNI_CONTACTO_058,-1) = nvl(DNI_CONTACTO_0A4,-1)");

		with.append(" WHERE CASE WHEN T1.ID_TIPO_EXPEDIENTE_051 IN ('").append(TipoExpedienteEnum.REVISION.getValue())
				.append("', '").append(TipoExpedienteEnum.TRADUCCION.getValue())
				.append("') AND T3.IND_PUBLICADO_BOE_053 = '" + ActivoEnum.SI.getValue() + "' THEN 1");
		with.append(" WHEN T1.ID_TIPO_EXPEDIENTE_051 IN ('").append(TipoExpedienteEnum.REVISION.getValue())
				.append("', '").append(TipoExpedienteEnum.TRADUCCION.getValue())
				.append("') AND (t4.TIPO_ENTIDAD_054, T4.ID_ENTIDAD_054) <> (SELECT '")
				.append(TipoEntidadEnum.ENTIDAD.getValue()).append("', VALOR_000 FROM AA79B00S01 WHERE ID_000 = ")
				.append(PropertiesConstants.COD_ENTIDAD_BOE).append(") THEN 1");
		with.append(" WHEN T1.ID_TIPO_EXPEDIENTE_051 = '").append(TipoExpedienteEnum.INTERPRETACION.getValue())
				.append("' THEN 1");
		with.append(" ELSE 0 END = 1");
		with.append(" AND T1.ESTADO_BAJA_051 = '" + EstadoEnum.ALTA.getValue() + "'");
		with.append(" AND CASE WHEN T1.ID_TIPO_EXPEDIENTE_051 = '").append(TipoExpedienteEnum.INTERPRETACION.getValue())
				.append("' THEN 1");
		with.append(" WHEN T1.ID_TIPO_EXPEDIENTE_051 IN ('").append(TipoExpedienteEnum.REVISION.getValue())
				.append("', '").append(TipoExpedienteEnum.TRADUCCION.getValue())
				.append("') AND ID_098 IS NOT NULL AND T12.IND_REVISADO_097 = '" + ActivoEnum.SI.getValue()
						+ "' AND t3.ind_facturable_053 = '"+ ActivoEnum.SI.getValue()+"' THEN 1 ");
		with.append(" ELSE 0 END = 1");
		with.append(" AND ANYO_0A5 IS NULL");
		with.append(" AND T1.ANYO_051 > " + Constants.ANYO_FACTURABLE);
		with.append(")");

		return with.toString();
	}

	@Override
	protected String getSelect() {
		StringBuilder select = new StringBuilder();
		select.append("SELECT T5.TIPO_ENTIDAD_ASOC_058 TIPOENTIDADASOCIADA");
		select.append(", T5.ID_ENTIDAD_ASOC_058 IDENTIDADASOCIADA");
		select.append(", T5.DNI_CONTACTO_058 DNICONTACTO");
		select.append(", T6.DESC_EU DESCENTIDADEU");
		select.append(", T6.DESC_ES DESCENTIDADES");
		select.append(", T7.NOMBRE_077 NOMBRE");
		select.append(", T7.APEL1_077 APEL1");
		select.append(", T7.APEL2_077 APEL2");
		select.append(", T14.DNI DNIVINCULADOOBAJA");
		select.append(", NVL(T6.IVA, '").append(ActivoEnum.NO.getValue()).append("') IVA");
		select.append(", (SELECT COUNT(1)");
		select.append(" FROM EXP_PENDIENTES EXP");
		select.append(" WHERE EXP.TIPO_ENTIDAD_ASOC_058 = T5.TIPO_ENTIDAD_ASOC_058");
		select.append(" AND EXP.ID_ENTIDAD_ASOC_058 = T5.ID_ENTIDAD_ASOC_058");
		select.append(" AND EXP.TIPO_EXPEDIENTE = T5.TIPO_EXPEDIENTE");
		select.append(" AND NVL(EXP.DNI_CONTACTO_058,0) = NVL(T5.DNI_CONTACTO_058,0) ");
		select.append(") COUNTEXPEDIENTES");
		select.append(", T15.DESCRIPCION_CASTELLANO_AMP CENTROORGANICOES");
		select.append(", T15.DESCRIPCION_EUSKERA_AMP CENTROORGANICOEU");
		return select.toString();
	}

	@Override
	protected String getFrom() {
		return "";
	}

	@Override
	protected String getFrom(ConsultaFacturacionExpediente bean, List<Object> params) {
		StringBuilder from = new StringBuilder();
		from.append(" FROM (SELECT TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058, DNI_CONTACTO_058, TIPO_EXPEDIENTE");
		from.append(" FROM EXP_PENDIENTES");
		from.append(" WHERE 1 = 1");

		from.append(SqlUtils.generarWhereMayorIgualFecha("FECHA_ALTA_051", bean.getFechaDesdeSolicitud(), params));
		from.append(SqlUtils.generarWhereMenorIgualFecha("FECHA_ALTA_051", bean.getFechaHastaSolicitud(), params));
		if (Constants.UNO == bean.getIdTipoExpediente()) {
			// tradRev
			from.append(SqlUtils.generarWhereMayorIgualFecha("FECHA_FINAL_IZO_053", bean.getFechaDesdeEntregaReal(),
					params));
			from.append(SqlUtils.generarWhereMenorIgualFecha("FECHA_FINAL_IZO_053", bean.getFechaHastaEntregaReal(),
					params));
		} else {
			// interpretacion
			from.append(SqlUtils.generarWhereMayorIgualFecha("FECHA_INI_052", bean.getFechaDesdeEntregaReal(), params));
			from.append(SqlUtils.generarWhereMenorIgualFecha("FECHA_INI_052", bean.getFechaHastaEntregaReal(), params));
		}
		ContactoFacturacionExpediente contactoFacturacionExpediente = bean.getContactoFacturacionExpediente();
		if (contactoFacturacionExpediente != null) {
			from.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_054",
					Utils.sustituirStringMinusPorNull(contactoFacturacionExpediente.getTipoEntidadAsoc058()), params));
			from.append(SqlUtils.generarWhereIgual("ID_ENTIDAD_054",
					contactoFacturacionExpediente.getIdEntidadAsoc058(), params));
			from.append(SqlUtils.generarWhereIgual("DNI_SOLICITANTE_054",
					contactoFacturacionExpediente.getDniContacto058(), params));
		}

		from.append(" GROUP BY TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058, DNI_CONTACTO_058, TIPO_EXPEDIENTE) T5");
		from.append(
				" JOIN X54JAPI_ENTIDADES_SOLIC T6 ON T5.TIPO_ENTIDAD_ASOC_058 = T6.TIPO AND T5.ID_ENTIDAD_ASOC_058 = T6.CODIGO");
		from.append(" LEFT JOIN AA79B77S01 T7 ON T5.DNI_CONTACTO_058 = T7.DNI_077 ");
		from.append(
				" LEFT JOIN X54JAPI_SOLICITANTES T14 ON T5.DNI_CONTACTO_058 = T14.DNI AND T5.TIPO_ENTIDAD_ASOC_058 = T14.TIPO_ENTIDAD AND T5.ID_ENTIDAD_ASOC_058 = T14.COD_ENTIDAD");
		from.append(" LEFT JOIN X54J52S01 T15 ON T5.DNI_CONTACTO_058 = T15.DNI ");
		return from.toString();
	}

	@Override
	protected RowMapper<ConsultaFacturacionExpediente> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RowMapper<ConsultaFacturacionExpediente> getRwMapPK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getWherePK(ConsultaFacturacionExpediente bean, List<Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getWhere(ConsultaFacturacionExpediente bean, List<Object> params) {
		return this.getWhereLike(bean, false, params, false);
	}

	@Override
	protected String getWhereLike(ConsultaFacturacionExpediente bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		StringBuilder where = new StringBuilder();
		where.append(SqlUtils.generarWhereIgual("t5.TIPO_EXPEDIENTE", bean.getIdTipoExpediente(), params));

		Entidad entidadAFacturar = bean.getEntidadAFacturar();
		if (entidadAFacturar != null) {
			where.append(SqlUtils.generarWhereIgual("t5.TIPO_ENTIDAD_ASOC_058", entidadAFacturar.getTipo(), params));
			where.append(SqlUtils.generarWhereIgual("t5.ID_ENTIDAD_ASOC_058", entidadAFacturar.getCodigo(), params));
			where.append(SqlUtils.generarWhereIgual("t6.FACTURABLE", entidadAFacturar.getFacturable(), params));
		}
		Solicitante contactoAFacturar = bean.getContactoAFacturar();
		if (contactoAFacturar != null) {
			where.append(SqlUtils.generarWhereIgual("t5.DNI_CONTACTO_058", contactoAFacturar.getDni(), params));
		}

		return where.toString();
	}

	private RowMapper<Entidad> rwMapEntidad = new EntidadSolicitanteRowMapper();

	private RowMapper<Solicitante> rwMapContacto = new RowMapper<Solicitante>() {
		@Override()
		public Solicitante mapRow(ResultSet rs, int rowNum) throws SQLException {
			Solicitante solic = new Solicitante();
			solic.setTipIden(rs.getInt(DBConstants.TIPIDEN));
			solic.setPreDni(rs.getString(DBConstants.PREDNI));
			solic.setDni(rs.getString(DBConstants.DNI));
			solic.setSufDni(rs.getString(DBConstants.SUFDNI));
			solic.setNombre(rs.getString(DBConstants.NOMBRE));
			solic.setApellido1(rs.getString(DBConstants.APEL1));
			solic.setApellido2(rs.getString(DBConstants.APEL2));
			return solic;
		}
	};

	@Override
	public List<Entidad> getEntidadesSolicitantes(Entidad entidad) {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder entSolic = new StringBuilder(this.getWith());
		List<Object> params = new ArrayList<Object>();
		entSolic.append(
				"SELECT t1.CODIGO CODIGO, t1.TIPO TIPO, TRIM(t1.DESC_ES) DESCES, TRIM(NVL(t1.DESC_EU,t1.DESC_ES)) DESCEU, TRIM(t1.DESC_AMP_ES) DESCAMPES");
		entSolic.append(", TRIM(NVL(t1.DESC_AMP_EU,t1.DESC_AMP_ES)) DESCAMPEU, t1.CIF CIF, t1.ESTADO ESTADO ");
		entSolic.append(", t1.CDIRNORA CDIRNORA ");
		entSolic.append(", DECODE(T1.TIPO");
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			entSolic.append(", '").append(tipo.getValue()).append("'");
			entSolic.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		entSolic.append(") AS TIPODESC");
		entSolic.append(" FROM X54JAPI_ENTIDADES_SOLIC t1");
		entSolic.append(" JOIN (SELECT TIPO_ENTIDAD_054, ID_ENTIDAD_054");
		entSolic.append(" FROM EXP_PENDIENTES");
		entSolic.append(" WHERE 1 = 1");
		entSolic.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_054", entidad.getTipo(), params));
		entSolic.append(
				" GROUP BY TIPO_ENTIDAD_054, ID_ENTIDAD_054) ON t1.TIPO = TIPO_ENTIDAD_054 AND t1.CODIGO = ID_ENTIDAD_054");
		return this.getJdbcTemplate().query(entSolic.toString(), this.rwMapEntidad, params.toArray());
	}

	@Override
	public List<Solicitante> getContactosSolicitantes(Solicitante contacto) {
		StringBuilder contSolic = new StringBuilder(this.getWith());
		List<Object> params = new ArrayList<Object>();
		contSolic.append(
				"SELECT t1.DNI_077 DNI, t1.TIPIDEN_077 TIPIDEN, t1.PREDNI_077 PREDNI, t1.SUFDNI_077 SUFDNI, TRIM(t1.NOMBRE_077) NOMBRE, TRIM(t1.APEL1_077) APEL1, TRIM(t1.APEL2_077) APEL2");
		contSolic.append(" FROM AA79B77S01 t1");
		contSolic.append(" JOIN (SELECT DNI_SOLICITANTE_054");
		contSolic.append(" FROM EXP_PENDIENTES");
		contSolic.append(" WHERE 1 = 1");
		Entidad entidad = contacto.getEntidad();
		if (entidad != null) {
			contSolic.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_054", entidad.getTipo(), params));
			contSolic.append(SqlUtils.generarWhereIgual("ID_ENTIDAD_054", entidad.getCodigo(), params));
		}
		contSolic.append(" GROUP BY DNI_SOLICITANTE_054) ON t1.DNI_077 = DNI_SOLICITANTE_054");
		return this.getJdbcTemplate().query(contSolic.toString(), this.rwMapContacto, params.toArray());
	}

	@Override
	public List<Entidad> getEntidadesFacturar(Entidad entidad) {
		Locale locale = LocaleContextHolder.getLocale();
		StringBuilder entFact = new StringBuilder(this.getWith());
		List<Object> params = new ArrayList<Object>();
		entFact.append(
				"SELECT t1.CODIGO CODIGO, t1.TIPO TIPO, TRIM(t1.DESC_ES) DESCES, TRIM(NVL(t1.DESC_EU,t1.DESC_ES)) DESCEU, TRIM(t1.DESC_AMP_ES) DESCAMPES");
		entFact.append(", TRIM(NVL(t1.DESC_AMP_EU,t1.DESC_AMP_ES)) DESCAMPEU, t1.CIF CIF, t1.ESTADO ESTADO ");
		entFact.append(", t1.CDIRNORA CDIRNORA ");
		entFact.append(", DECODE(T1.TIPO");
		for (TipoEntidadEnum tipo : TipoEntidadEnum.values()) {
			entFact.append(", '").append(tipo.getValue()).append("'");
			entFact.append(", '").append(this.msg.getMessage(tipo.getLabel(), null, locale)).append("'");
		}
		entFact.append(") AS TIPODESC");
		entFact.append(" FROM X54JAPI_ENTIDADES_SOLIC t1");
		entFact.append(" JOIN (SELECT TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058");
		entFact.append(" FROM EXP_PENDIENTES ");
		entFact.append(" WHERE 1 = 1");
		entFact.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_ASOC_058", entidad.getTipo(), params));
		entFact.append(
				"GROUP BY TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058) ON t1.TIPO = TIPO_ENTIDAD_ASOC_058 AND t1.CODIGO = ID_ENTIDAD_ASOC_058");
		entFact.append(SqlUtils.generarWhereIgual("FACTURABLE", entidad.getFacturable(), params));
		return this.getJdbcTemplate().query(entFact.toString(), this.rwMapEntidad, params.toArray());
	}

	@Override
	public List<Solicitante> getContactosFacturar(Solicitante contacto) {
		StringBuilder contFact = new StringBuilder(this.getWith());
		List<Object> params = new ArrayList<Object>();
		contFact.append(
				"SELECT t1.DNI_077 DNI, t1.TIPIDEN_077 TIPIDEN, t1.PREDNI_077 PREDNI, t1.SUFDNI_077 SUFDNI, TRIM(t1.NOMBRE_077) NOMBRE, TRIM(t1.APEL1_077) APEL1, TRIM(t1.APEL2_077) APEL2");
		contFact.append(" FROM AA79B77S01 t1");
		contFact.append(" JOIN (SELECT DNI_CONTACTO_058");
		contFact.append(" FROM EXP_PENDIENTES");
		contFact.append(" JOIN (SELECT TIPO, CODIGO");
		contFact.append(" FROM X54JAPI_ENTIDADES_SOLIC ");
		contFact.append(" WHERE 1 = 1");
		contFact.append(SqlUtils.generarWhereIgual("FACTURABLE", contacto.getEntidad().getFacturable(), params));
		contFact.append("GROUP BY TIPO, CODIGO) ON TIPO = TIPO_ENTIDAD_ASOC_058 AND CODIGO = ID_ENTIDAD_ASOC_058");
		contFact.append(" WHERE 1 = 1");
		Entidad entidad = contacto.getEntidad();
		if (entidad != null) {
			contFact.append(SqlUtils.generarWhereIgual("TIPO_ENTIDAD_ASOC_058", entidad.getTipo(), params));
			contFact.append(SqlUtils.generarWhereIgual("ID_ENTIDAD_ASOC_058", entidad.getCodigo(), params));
		}
		contFact.append(" GROUP BY DNI_CONTACTO_058) ON t1.DNI_077 = DNI_CONTACTO_058");
		return this.getJdbcTemplate().query(contFact.toString(), this.rwMapContacto, params.toArray());
	}

}
