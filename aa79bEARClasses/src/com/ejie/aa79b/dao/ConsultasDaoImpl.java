package com.ejie.aa79b.dao;

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
import com.ejie.aa79b.dao.mapper.DatosDetalleExpDesdeClienteConsultaRowMapper;
import com.ejie.aa79b.dao.mapper.DatosPagoProveedoresConsultaInterRowMapper;
import com.ejie.aa79b.dao.mapper.DatosPagoProveedoresConsultaTradRevRowMapper;
import com.ejie.aa79b.dao.mapper.DatosTareaTradosConsultaConsultaRowMapper;
import com.ejie.aa79b.dao.mapper.ExpedienteConsultaRowMapper;
import com.ejie.aa79b.model.AnulacionRechazo;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DetalleExpedienteVisionCliente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteConsulta;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoGestorEnum;
import com.ejie.aa79b.model.enums.OrigenExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.utils.ConsultaExpedienteUtils;
import com.ejie.aa79b.utils.DAOUtils;
import com.ejie.aa79b.utils.ExpedienteDaoUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;

@Repository
@Transactional
public class ConsultasDaoImpl extends GenericoDaoImpl<ExpedienteConsulta> implements ConsultasDao {

	private static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ANYO, DBConstants.NUMEXP,
			DBConstants.ANYONUMEXPCONCATENADO, DBConstants.IDTIPOEXPEDIENTE, DBConstants.TITULO,
			DBConstants.NOMBREGESTOR, DBConstants.IDESTADOEXP, DBConstants.FECHAALTA, DBConstants.INDPUBLICARBOPV };

	protected static final String ACTIVO_ENUM = "ActivoEnum";

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public ConsultasDaoImpl() {
		// Constructor
		super(ExpedienteConsulta.class);
	}

	/*
	 * ROW_MAPPERS - INICIO
	 */

	private RowMapper<ExpedienteConsulta> rwMap = new ExpedienteConsultaRowMapper();
	private RowMapper<DatosPagoProveedores> rwMapDatosPagoProveedoresConsultaTradRev = new DatosPagoProveedoresConsultaTradRevRowMapper();
	private RowMapper<DatosPagoProveedores> rwMapDatosPagoProveedoresConsultaInter = new DatosPagoProveedoresConsultaInterRowMapper();
	private RowMapper<DetalleExpedienteVisionCliente> rwMapDatosDetalleExpedienteDesdeClienteConsulta = new DatosDetalleExpDesdeClienteConsultaRowMapper();
	private RowMapper<DatosTareaTrados> rwMapDatosTareaTradosConsulta = new DatosTareaTradosConsultaConsultaRowMapper();

	private RowMapper<ExpedienteConsulta> rwMapPK = new RowMapper<ExpedienteConsulta>() {
		@Override
		public ExpedienteConsulta mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ExpedienteConsulta expedienteConsulta = new ExpedienteConsulta();
			expedienteConsulta.setAnyo(resultSet.getLong(DBConstants.ANYO));
			expedienteConsulta.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
			return expedienteConsulta;
		}
	};

	protected RowMapper<ExpedienteConsulta> getRwMapConsultaExpedientes() {
		return this.rwMap;
	}

	protected RowMapper<ExpedienteConsulta> getRwMapConsultaExpedientesIds() {
		return this.rwMapPK;
	}

	protected RowMapper<DatosPagoProveedores> getRwMapDatosPagoProveedoresConsultaTradRev() {
		return this.rwMapDatosPagoProveedoresConsultaTradRev;
	}

	protected RowMapper<DatosPagoProveedores> getRwMapDatosPagoProveedoresConsultaInter() {
		return this.rwMapDatosPagoProveedoresConsultaInter;
	}

	protected RowMapper<DetalleExpedienteVisionCliente> getRwMapDatosDetalleExpedienteDesdeClienteConsulta() {
		return this.rwMapDatosDetalleExpedienteDesdeClienteConsulta;
	}

	protected RowMapper<DatosTareaTrados> getRwMapDatosTareaTradosConsultaClienteConsulta() {
		return this.rwMapDatosTareaTradosConsulta;
	}

	private RowMapper<AnulacionRechazo> anulacionRechazo = new RowMapper<AnulacionRechazo>() {
		@Override
		public AnulacionRechazo mapRow(ResultSet resultSet, int arg1) throws SQLException {
			AnulacionRechazo bean = new AnulacionRechazo();
			bean.setObservaciones(resultSet.getString("OBSERVACIONES"));
			bean.setMotivoDescEs(resultSet.getString("DESCES"));
			bean.setMotivoDescEu(resultSet.getString("DESCEU"));
			return bean;
		}
	};

	/*
	 * ROW_MAPPERS - FIN
	 */

	@Override
	protected String getSelect() {
		Locale eu = new Locale(Constants.LANG_EUSKERA);
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		StringBuilder selectCES = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		selectCES.append(DaoConstants.SELECT + /*DaoConstants.DISTINCT +*/ DaoConstants.T1_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051 + DaoConstants.BLANK + DBConstants.ANYO);
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_051 + DaoConstants.BLANK + DBConstants.NUMEXP);

		// ANYONUMEXPCONCATENADO
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.SUBSTR + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		selectCES.append(DaoConstants.SIGNO_COMA + Constants.DOS + DaoConstants.SIGNO_COMA + Constants.CUATRO
				+ DaoConstants.CERRAR_PARENTESIS);
		selectCES.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.SIGNO_APOSTROFO + Constants.CONTRABARRA
				+ DaoConstants.SIGNO_APOSTROFO);
		selectCES.append(DaoConstants.SIGNO_CONCATENACION + DaoConstants.LPAD + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		selectCES.append(DaoConstants.SIGNO_COMA + Constants.SEIS + DaoConstants.SIGNO_COMA
				+ DaoConstants.SIGNO_APOSTROFO + Constants.CERO + DaoConstants.SIGNO_APOSTROFO);
		selectCES.append(DaoConstants.CERRAR_PARENTESIS + DBConstants.ANYONUMEXPCONCATENADO);
		// IDTIPOEXPEDIENTE
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_TIPO_EXPEDIENTE_051 + DaoConstants.BLANK + DBConstants.IDTIPOEXPEDIENTE);
		// ORIGEN
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ORIGEN_051 + DaoConstants.BLANK + DBConstants.ORIGEN);
		// TIPOEXPEDIENTEDESCEU
		selectCES
				.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + DaoConstants.SIGNO_COMA
						+ DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
						+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.TRADUCCION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, eu))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.REVISION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, eu)).append(DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPOEXPEDIENTEDESCEU);
		// TIPOEXPEDIENTEDESCES
		selectCES
				.append(DaoConstants.SIGNO_COMA + DBConstants.DECODE_T1ID_TIPO_EXPEDIENTE_051 + DaoConstants.SIGNO_COMA
						+ DaoConstants.SIGNO_APOSTROFO + TipoExpedienteEnum.INTERPRETACION.getValue()
						+ DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(this.msg.getMessage(DBConstants.LABEL_INTERPRETACIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.TRADUCCION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(this.msg.getMessage(DBConstants.LABEL_TRADUCCIONABR, null, es))
				.append(DaoConstants.SIGNO_APOSTROFO + DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO
						+ TipoExpedienteEnum.REVISION.getValue() + DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.SIGNO_COMA + DaoConstants.SIGNO_APOSTROFO)
				.append(this.msg.getMessage(DBConstants.LABEL_REVISIONABR, null, es)).append(DaoConstants.SIGNO_APOSTROFO
						+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.TIPOEXPEDIENTEDESCES);
		// TITULO
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.TITULO_051 + DaoConstants.BLANK + DBConstants.TITULO);
		// FECHAALTA
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_ALTA_051 + DaoConstants.BLANK + DBConstants.FECHAALTA);
		// HORAALTA
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_ALTA_051
				+ DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA + DaoConstants.CERRAR_PARENTESIS
				+ DBConstants.HORAALTA);
		// FECHA INTERPRETACION INI
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_INI_052 + DaoConstants.BLANK + DBConstants.FECHA_INI_INTER);
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_INI_052
				+ DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA + DaoConstants.CERRAR_PARENTESIS
				+ DBConstants.HORA_INI_INTER);

		// FECHA INTERPRETACION FIN
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_FIN_052 + DaoConstants.BLANK + DBConstants.FECHA_FIN_INTER);
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FIN_052
				+ DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA + DaoConstants.CERRAR_PARENTESIS
				+ DBConstants.HORA_FIN_INTER);
		// IDESTADOEXP
		selectCES.append(", DECODE(t1.ESTADO_BAJA_051,'B',0" + DaoConstants.SIGNO_COMA + DaoConstants.T4_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.ID_ESTADO_EXP_059 + ")" + DaoConstants.BLANK
				+ DBConstants.IDESTADOEXP);
		// ESTADOEXPEDIENTEDESCEU
		selectCES.append(", DECODE(t1.ESTADO_BAJA_051,'B','" + this.msg.getMessage("label.eliminado", null, eu) + "'"
				+ DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_EU_060 + ")" + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCEU);
		// ESTADOEXPEDIENTEDESCES
		selectCES.append(", DECODE(t1.ESTADO_BAJA_051,'B','" + this.msg.getMessage("label.eliminado", null, es) + "'"
				+ DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ES_060 + ")" + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCES);
		// ESTADOEXPEDIENTEDESCABREU
		selectCES.append(", DECODE(t1.ESTADO_BAJA_051,'B','" + this.msg.getMessage("label.eliminado", null, eu) + "'"
				+ DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_EU_060 + ")" + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCABREU);
		// ESTADOEXPEDIENTEDESCABRES
		selectCES.append(", DECODE(t1.ESTADO_BAJA_051,'B','" + this.msg.getMessage("label.eliminado", null, es) + "'"
				+ DaoConstants.SIGNO_COMA + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_ES_060 + ")" + DaoConstants.BLANK + DBConstants.ESTADOEXPEDIENTEDESCABRES);
		// IDFASEEXPEDIENTE
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_FASE_EXPEDIENTE_059 + DaoConstants.BLANK + DBConstants.IDFASEEXPEDIENTE);
		// FASEEXPEDIENTEDESCEU
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_EU_061 + DaoConstants.BLANK + DBConstants.FASEEXPEDIENTEDESCEU);
		// FASEEXPEDIENTEDESCES
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ES_061 + DaoConstants.BLANK + DBConstants.FASEEXPEDIENTEDESCES);
		// FASEEXPEDIENTEDESCABREU
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_EU_061 + DaoConstants.BLANK + DBConstants.FASEEXPEDIENTEDESCABREU);
		// FASEEXPEDIENTEDESCABRES
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ABR_ES_061 + DaoConstants.BLANK + DBConstants.FASEEXPEDIENTEDESCABRES);
		// GESTOR ACTIVO
		selectCES.append(", t7.DNI DNIVINCULADOOBAJA ");
		// DNI GESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DNI_SOLICITANTE_054 + DaoConstants.BLANK + DBConstants.DNIGESTOR);
		// NOMBREGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NOMBRE_077 + DaoConstants.BLANK + DBConstants.NOMBREGESTOR);
		// APEL1GESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.APEL1_077 + DaoConstants.BLANK + DBConstants.APEL1GESTOR);
		// APEL2GESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.APEL2_077 + DaoConstants.BLANK + DBConstants.APEL2GESTOR);
		// PREDNIGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.PREDNI_077 + DaoConstants.BLANK + DBConstants.PREDNIGESTOR);
		// SUFDNIGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.SUFDNI_077 + DaoConstants.BLANK + DBConstants.SUFDNIGESTOR);
		// DNICOMPLETOGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.ABRIR_PARENTESIS + DaoConstants.T8_MINUSCULA
				+ DaoConstants.SIGNO_PUNTO + DBConstants.PREDNI_077);
		selectCES.append(DaoConstants.BLANK + DaoConstants.SIGNO_CONCATENACION + DaoConstants.BLANK);
		selectCES.append(DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.DNI_SOLICITANTE_054);
		selectCES.append(DaoConstants.BLANK + DaoConstants.SIGNO_CONCATENACION + DaoConstants.BLANK);
		selectCES.append(DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.SUFDNI_077);
		selectCES.append(DaoConstants.CERRAR_PARENTESIS + DaoConstants.AS + DBConstants.DNICOMPLETOGESTOR);
		// TIPOENTIDADGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.TIPO_ENTIDAD_054 + DaoConstants.BLANK + DBConstants.TIPOENTIDADGESTOR);
		// IDENTIDADGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_ENTIDAD_054 + DaoConstants.BLANK + DBConstants.IDENTIDADGESTOR);
		// INDVIPGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.IND_VIP_054 + DaoConstants.BLANK + DBConstants.INDVIPGESTOR);
		// GESTOREXPEDIENTESVIPDESCEU
		selectCES.append(DAOUtils.getDecodeAcciones(
				DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_VIP_054,
				DBConstants.GESTOREXPEDIENTESVIPDESCEU, this.msg, ACTIVO_ENUM, eu));
		// GESTOREXPEDIENTESVIPDESCES
		selectCES.append(DAOUtils.getDecodeAcciones(
				DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_VIP_054,
				DBConstants.GESTOREXPEDIENTESVIPDESCES, this.msg, ACTIVO_ENUM, es));
		// ENTIDADIVAGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.IVA + DaoConstants.BLANK + DBConstants.ENTIDADIVAGESTOR);
		// DESCENTIDADEUGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_EU + DaoConstants.BLANK + DBConstants.DESCENTIDADEUGESTOR);
		// DESCENTIDADESGESTOR
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DESC_ES + DaoConstants.BLANK + DBConstants.DESCENTIDADESGESTOR);
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO + "DESC_AMP_EU"
				+ DaoConstants.BLANK + "ENTIDAD_DESC_AMP_EU");
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO + "DESC_AMP_ES"
				+ DaoConstants.BLANK + "ENTIDAD_DESC_AMP_ES");
		// FECHA FINAL IZO
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.FECHA_FINAL_IZO_053 + DaoConstants.BLANK + DBConstants.FECHAFINALIZO);
		// HORA FINAL IZO
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.TO_CHAR + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FINAL_IZO_053
				+ DaoConstants.SIGNO_COMA + DaoConstants.FORMATO_HORA + DaoConstants.CERRAR_PARENTESIS
				+ DBConstants.HORAFINALIZO);
		// FECHA ENTREGA REAL
		selectCES.append(", t3.").append(DBConstants.FECHA_ENTREGA_REAL_053).append(" ")
				.append(DBConstants.FECHAENTREGAREAL);
		// HORA ENTREGA REAL
		selectCES.append(", TO_CHAR( t3.").append(DBConstants.FECHA_ENTREGA_REAL_053).append(",")
				.append(DaoConstants.FORMATO_HORA).append(") ").append(DBConstants.HORAENTREGAREAL);
		// NUMPALIZO
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_TOTAL_PAL_IZO_053 + DaoConstants.BLANK + DBConstants.NUMPALIZO);
		// NUMPALSOLIC
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ "NUM_TOTAL_PAL_SOLIC_053 " + DBConstants.NUMTOTALPALSOLIC);
		// INDPUBLICARBOPV
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.IND_PUBLICAR_BOPV_053 + DaoConstants.BLANK + DBConstants.INDPUBLICARBOPV);
		// PUBLICARBOPVDESCEU
		selectCES.append(DAOUtils.getDecodeAcciones(
				DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053,
				DBConstants.PUBLICARBOPVDESCEU, this.msg, ACTIVO_ENUM, eu));
		// PUBLICARBOPVDESCES
		selectCES.append(DAOUtils.getDecodeAcciones(
				DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053,
				DBConstants.PUBLICARBOPVDESCES, this.msg, ACTIVO_ENUM, es));
		// NUMTOTALPALTRADOS
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_TOTAL_PAL_090 + DaoConstants.BLANK + DBConstants.NUMTOTALPALTRADOS);
		// NUMPALCONCOR084090
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_PAL_CONCOR_0_84_090 + DaoConstants.BLANK + DBConstants.NUMPALCONCOR084090);
		// NUMPALCONCOR8594090
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_PAL_CONCOR_85_94_090 + DaoConstants.BLANK + DBConstants.NUMPALCONCOR8594090);
		// NUMPALCONCOR95100090
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_PAL_CONCOR_95_100_090 + DaoConstants.BLANK + DBConstants.NUMPALCONCOR95100090);
		// NUMPALCONCOR9599090
				selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
						+ DBConstants.NUM_PAL_CONCOR_95_99_090 + DaoConstants.BLANK + DBConstants.NUMPALCONCOR9599090);
		// NUMPALCONCOR9599090
		selectCES.append(DaoConstants.SIGNO_COMA + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_PAL_CONCOR_100_090 + DaoConstants.BLANK + DBConstants.NUMPALCONCOR100090);
		// NUMPALCOLORDER
		selectCES.append(",  CASE WHEN t1.ID_TIPO_EXPEDIENTE_051 = '" + TipoExpedienteEnum.INTERPRETACION.getValue()
				+ "' THEN -1 ELSE "
				+ "NVL(t11.NUM_TOTAL_PAL_090,NVL(t3.NUM_TOTAL_PAL_IZO_053,NVL(NUM_TOTAL_PAL_SOLIC_053,0))) END AS NUMPALCOLORDER ");
		// GESTORCOLORDEREU
		selectCES.append("," + SqlUtils.normalizarCampoSql("t9.DESC_EU") + " || "
				+ SqlUtils.normalizarCampoSql("t8.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("t8.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("t8.NOMBRE_077") + " GESTORCOLORDEREU");
		// GESTORCOLORDERES
		selectCES.append("," + SqlUtils.normalizarCampoSql("t9.DESC_ES") + " || "
				+ SqlUtils.normalizarCampoSql("t8.APEL1_077") + " || " + SqlUtils.normalizarCampoSql("t8.APEL2_077")
				+ " || " + SqlUtils.normalizarCampoSql("t8.NOMBRE_077") + " GESTORCOLORDERES");
		// FACTURAS
		selectCES.append(", NVL(a5.estado, '-')  AS NUMESTADOFACTURAS");

		return selectCES.toString();
	}



	@Override
	protected String getFrom() {
		StringBuilder fromCEF = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		fromCEF.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		// 52 INTERPRETACION
		fromCEF.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_52 + DaoConstants.BLANK + DaoConstants.T2_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_052);
		fromCEF.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.T2_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_052);
		// 53 TRAD/REV
		fromCEF.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_53 + DaoConstants.BLANK + DaoConstants.T3_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_053);
		fromCEF.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_053);
		// 59 BITACORA
		fromCEF.append(DaoConstants.JOIN + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.T4_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_059);
		fromCEF.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_059);
		fromCEF.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ESTADO_BITACORA_051);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_ESTADO_BITACORA_059);
		// 60 DESC ESTADO EXPEDIENTE
		fromCEF.append(DaoConstants.JOIN + DBConstants.TABLA_60 + DaoConstants.BLANK + DaoConstants.T5_MINUSCULA);
		fromCEF.append(
				DaoConstants.ON + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ESTADO_EXP_059);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T5_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_060);
		// 61 DESC FASE EXPEDIENTE
		fromCEF.append(DaoConstants.JOIN + DBConstants.TABLA_61 + DaoConstants.BLANK + DaoConstants.T6_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.T4_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_FASE_EXPEDIENTE_059);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T6_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_061);
		// 54 GESTOR
		fromCEF.append(DaoConstants.JOIN + DBConstants.TABLA_54 + DaoConstants.BLANK + DaoConstants.G1_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_054);
		fromCEF.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_054);
		// 77 FOTO PERSONAS
		fromCEF.append(DaoConstants.JOIN + DBConstants.TABLA_77 + DaoConstants.BLANK + DaoConstants.T8_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.DNI_SOLICITANTE_054);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T8_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.DNI_077);
		// GESTOR ACTIVO
		fromCEF.append(
				" LEFT JOIN X54JAPI_SOLICITANTES t7 ON t7.DNI = g1.DNI_SOLICITANTE_054 AND t7.TIPO_ENTIDAD =  g1.TIPO_ENTIDAD_054 AND t7.COD_ENTIDAD = g1.ID_ENTIDAD_054 ");
		// X54JAPI_ENTIDADES_SOLIC ENTIDADES
		fromCEF.append(DaoConstants.JOIN + DBConstants.VISTAX54JAPIENTIDADESSOLIC + DaoConstants.BLANK
				+ DaoConstants.T9_MINUSCULA);
		fromCEF.append(
				DaoConstants.ON + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO);
		fromCEF.append(
				DaoConstants.AND + DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T9_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.CODIGO);
		// 81 TAREAS
		fromCEF.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_81 + DaoConstants.BLANK + DaoConstants.T10_MINUSCULA);
		fromCEF.append(DaoConstants.ON + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051);
		fromCEF.append(
				DaoConstants.SIGNOIGUAL + DaoConstants.T10_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_081);
		fromCEF.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.T10_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.NUM_EXP_081);
		fromCEF.append(DaoConstants.AND + DaoConstants.T10_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_TIPO_TAREA_081);
		fromCEF.append(DaoConstants.SIGNOIGUAL + TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue());
		// 90 TAREA TRADOS
		fromCEF.append(DaoConstants.LEFT_JOIN + DBConstants.TABLA_90 + DaoConstants.BLANK + DaoConstants.T11_MINUSCULA);
		fromCEF.append(
				DaoConstants.ON + DaoConstants.T10_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_TAREA_081);
		fromCEF.append(DaoConstants.SIGNOIGUAL + DaoConstants.T11_MINUSCULA + DaoConstants.SIGNO_PUNTO
				+ DBConstants.ID_TAREA_090);
		fromCEF.append(" LEFT JOIN ( ");
		fromCEF.append("SELECT ");
		fromCEF.append("a5.anyo_0a5, ");
		fromCEF.append("a5.num_exp_0a5, ");
		fromCEF.append("LISTAGG( ");
		fromCEF.append(
				"substr(t12.t12_ej_contable, 3, 2) || lpad(a4.app_id_0a4, 2, '0') || substr(a4.id_liquidacion_0a4, 3, 6) || ' (' || e2.T00_DESC_EU ||') <br />', ");
		fromCEF.append("'') WITHIN GROUP( ");
		fromCEF.append("ORDER BY ");
		fromCEF.append(
				"substr(t12.t12_ej_contable, 3, 2) || lpad(a4.app_id_0a4, 2, '0') || substr(a4.id_liquidacion_0a4, 3, 6) ");
		fromCEF.append(") AS estado ");
		fromCEF.append("FROM ");
		fromCEF.append("aa79ba5s01 a5 ");
		fromCEF.append("JOIN aa79ba4s01 a4 ON a5.id_factura_0a5 = a4.id_factura_0a4 ");
		fromCEF.append("JOIN w0512s01 t12 ON a4.id_liquidacion_0a4 = t12.t12_referencia ");
		fromCEF.append("JOIN AA79B.W05B_ESTADOS_FACTURA e2 ON t12.T00_ESTADO_ID = e2.T00_REG_ID ");
		fromCEF.append("GROUP BY a5.anyo_0a5, a5.num_exp_0a5 ");
		fromCEF.append(") a5  ");
		fromCEF.append("ON a5.anyo_0a5 = t1.ANYO_051 AND a5.num_exp_0a5 = t1.NUM_EXP_051 ");
		return fromCEF.toString();
	}

	@Override
	protected RowMapper<ExpedienteConsulta> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ConsultasDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return DBConstants.ANYO_051 + "," + DBConstants.NUM_EXP_051;
	}

	@Override
	protected RowMapper<ExpedienteConsulta> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(ExpedienteConsulta bean, List<Object> params) {
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		StringBuilder wherePK = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		wherePK.append(DaoConstants.WHERE + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_051
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		wherePK.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_051
				+ DaoConstants.SIGNOIGUAL_INTERROGACION);
		return wherePK.toString();
	}

	private static void filtrosTradRev(StringBuilder whereCEW, ExpedienteConsulta filter, List<Object> params) {
		if (filter.getExpedienteTradRev() != null) {
			// FECHA ENTREGA IZO
			whereCEW.append(SqlUtils.generarWhereMayorIgualFecha(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FINAL_IZO_053,
					DaoConstants.DDMMYY, filter.getExpedienteTradRev().getFechaEntregaIzoDesde(), params));
			whereCEW.append(SqlUtils.generarWhereMenorIgualFecha(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_FINAL_IZO_053,
					DaoConstants.DDMMYY, filter.getExpedienteTradRev().getFechaEntregaIzoHasta(), params));
			// REF TRAMITAGUNE
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.REF_TRAMITAGUNE_053,
					filter.getExpedienteTradRev().getRefTramitagune(), params, false));
			// IND PUBLICAR BOPV
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_PUBLICAR_BOPV_053,
					filter.getExpedienteTradRev().getIndPublicarBopv(), params, false));
			// IND PREVISTO BOPV
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_PREVISTO_BOPV_053,
					filter.getExpedienteTradRev().getIndPrevistoBopv(), params, false));
			// IDIOMA DESTINO
			whereCEW.append(SqlUtils.generarWhereIgual(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_IDIOMA_053,
					filter.getExpedienteTradRev().getIdIdioma(), params));
			// IND CORREDACCION
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_CORREDACCION_053,
					filter.getExpedienteTradRev().getIndCorredaccion(), params, false));
			// IND URGENTE
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_URGENTE_053,
					filter.getExpedienteTradRev().getIndUrgente(), params, false));
			// IND PUBLICADO BOE
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_PUBLICADO_BOE_053,
					filter.getExpedienteTradRev().getIndPublicadoBoe(), params, false));
			// IND CONFIDENCIAL
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_CONFIDENCIAL_053,
					filter.getExpedienteTradRev().getIndConfidencial(), params, false));
			// IND FACTURABLE
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.T3_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_FACTURABLE_053,
					filter.getExpedienteTradRev().getIndFacturable(), params, false));
		}

	}

	private static void filtroGestorExpediente(StringBuilder whereCEW, ExpedienteConsulta filter, List<Object> params) {
		if (filter.getGestorExpediente() != null) {
			if (ExpedienteDaoUtils.solicitanteValido(filter.getGestorExpediente())) {
				whereCEW.append(SqlUtils.generarWhereLike(
						DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.DNI_SOLICITANTE_054,
						filter.getGestorExpediente().getSolicitante().getDni(), params, false));
			}
			if (ExpedienteDaoUtils.entidadValida(filter.getGestorExpediente())) {
				whereCEW.append(SqlUtils.generarWhereLike(
						DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.TIPO_ENTIDAD_054,
						filter.getGestorExpediente().getEntidad().getTipo(), params, false));
				whereCEW.append(SqlUtils.generarWhereIgual(
						DaoConstants.G1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_ENTIDAD_054,
						filter.getGestorExpediente().getEntidad().getCodigo(), params));
			}
		}
	}

	private static void filtroEstadoRequerimiento(StringBuilder whereCEW, ExpedienteConsulta filter,
			List<Object> params, String reqValor, int tipoReqValor) {
		if (TipoRequerimientoEnum.SUBSANACION.getValue() == tipoReqValor && filter.getBitacoraExpediente() != null
				&& filter.getBitacoraExpediente().getSubsanacionExp() != null) {
			whereCEW.append(SqlUtils.generarWhereLike(
					DaoConstants.S2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.IND_SUBSANADO_064,
					filter.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado(), params, false));
		} else {
			int valorEstado = 0;
			if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() == tipoReqValor
					&& filter.getEstadoPresupuesto() != null) {
				valorEstado = filter.getEstadoPresupuesto();
			} else if (TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue() == tipoReqValor
					&& filter.getEstadoNegociacion() != null) {
				valorEstado = filter.getEstadoNegociacion();
			}
			if (valorEstado != 0) {
				whereCEW.append(SqlUtils.generarWhereIgual(
						DaoConstants.S2_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ESTADO_SUBSANACION_064,
						valorEstado, params));
			}

		}

	}

	public static void filtroRequerimiento(StringBuilder whereCEW, ExpedienteConsulta filter, List<Object> params,
			String reqValor, int tipoReqValor) {
		if (reqValor != null) {
			whereCEW.append(DaoConstants.AND + Constants.CERO);
			if (ActivoEnum.SI.getValue().equals(reqValor)) {
				whereCEW.append(DaoConstants.SIGNO_MENOR_QUE);
			} else if (ActivoEnum.NO.getValue().equals(reqValor)) {
				whereCEW.append(DaoConstants.SIGNOIGUAL);
			}
			whereCEW.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.SELECT_COUNT);
			whereCEW.append(DaoConstants.FROM + DBConstants.TABLA_59 + DaoConstants.BLANK + DaoConstants.S1_MINUSCULA);
			whereCEW.append(DaoConstants.JOIN + DBConstants.TABLA_064 + DaoConstants.BLANK + DaoConstants.S2_MINUSCULA);
			whereCEW.append(DaoConstants.ON + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.ID_REQUERIMIENTO_059);
			whereCEW.append(DaoConstants.SIGNOIGUAL + DaoConstants.S2_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.ID_064);
			whereCEW.append(DaoConstants.AND + DaoConstants.S2_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.TIPO_REQUERIMIENTO_064);
			if (Constants.UNO == tipoReqValor) {
				whereCEW.append(DaoConstants.SIGNOIGUAL + tipoReqValor);
			} else {
				whereCEW.append(DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS + tipoReqValor);
				whereCEW.append(
						DaoConstants.SIGNO_COMA + TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue()
								+ DaoConstants.CERRAR_PARENTESIS);
			}
			whereCEW.append(
					DaoConstants.WHERE + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ANYO_059);
			whereCEW.append(DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.ANYO_051);
			whereCEW.append(
					DaoConstants.AND + DaoConstants.S1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.NUM_EXP_059);
			whereCEW.append(DaoConstants.SIGNOIGUAL + DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.NUM_EXP_051);
			if (ActivoEnum.SI.getValue().equals(reqValor)) {
				// si subsanacion requerida aplicamos el filtro de estado
				// subsanacion
				filtroEstadoRequerimiento(whereCEW, filter, params, reqValor, tipoReqValor);
			}
			whereCEW.append(DaoConstants.CERRAR_PARENTESIS);
		}

	}

	private static void filtroExpedientesRelacionados(StringBuilder whereCEW, ExpedienteConsulta filter,
			List<Object> params) {
		if (StringUtils.isNotBlank(filter.getIdsExpedientesRelacionados())) {
			final String[] idsExpedientesRelacionados = filter.getIdsExpedientesRelacionados()
					.split(Constants.GUION_BAJO);

			whereCEW.append(" AND (t1.anyo_051, t1.num_Exp_051) in (SELECT t15.anyo_057, t15.num_exp_057 from  aa79b57s01 t15 WHERE ");
			whereCEW.append(DaoConstants.ABRIR_PARENTESIS);
			boolean esAnd = true;
			for (int i = Constants.CERO; i < idsExpedientesRelacionados.length; i++) {
				if (esAnd) {
					esAnd = false;
				} else {
					whereCEW.append(DaoConstants.OR);
				}
				String[] anyoNumExp = idsExpedientesRelacionados[i].split(DaoConstants.SIGNO_COMA_SIN_ESPACIOS);
				whereCEW.append(DaoConstants.ABRIR_PARENTESIS + DaoConstants.T15_MINUSCULA + DaoConstants.SIGNO_PUNTO
						+ DBConstants.ANYO_EXP_REL_057);
				whereCEW.append(DaoConstants.SIGNOIGUAL + anyoNumExp[0]);
				whereCEW.append(DaoConstants.AND + DaoConstants.T15_MINUSCULA + DaoConstants.SIGNO_PUNTO
						+ DBConstants.NUM_EXP_REL_057);
				whereCEW.append(DaoConstants.SIGNOIGUAL + anyoNumExp[1] + DaoConstants.CERRAR_PARENTESIS);
			}
			whereCEW.append(DaoConstants.CERRAR_PARENTESIS);
			whereCEW.append(DaoConstants.CERRAR_PARENTESIS);
		}

	}

	private static void filtroMetadatos(StringBuilder whereCEW, ExpedienteConsulta filter, List<Object> params) {
		if (StringUtils.isNotBlank(filter.getIdsMetadatosBusqueda())) {
			final String[] idsMetadatosBusqueda = filter.getIdsMetadatosBusqueda().split(Constants.COMA);
			whereCEW.append(" AND (t1.anyo_051, t1.num_Exp_051) in (SELECT t14.anyo_062, t14.num_exp_062 from  aa79b62s01 t14 WHERE ");
			whereCEW.append(DaoConstants.T14_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.ID_METADATO_062 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
			for (int i = Constants.CERO; i < idsMetadatosBusqueda.length; i++) {
				whereCEW.append(idsMetadatosBusqueda[i]);
				if (i != idsMetadatosBusqueda.length - 1) {
					whereCEW.append(DaoConstants.SIGNO_COMA);
				}
			}
			whereCEW.append(DaoConstants.CERRAR_PARENTESIS);
			whereCEW.append(DaoConstants.CERRAR_PARENTESIS);
		}

	}

	@Override
	protected String getWhere(ExpedienteConsulta filter, List<Object> params) {
		StringBuilder whereCEW = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		whereCEW.append(DaoConstants.WHERE_1_1);
		// ID_TIPO_EXPEDIENTE_051
		if (null != filter.getIdTipoExpediente()) {
			if (filter.getIdTipoExpediente().equals(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode())) {
				List<String> listaExpedientes = new ArrayList<String>();
				listaExpedientes.add(TipoExpedienteEnum.REVISION.getValue());
				listaExpedientes.add(TipoExpedienteEnum.TRADUCCION.getValue());
				whereCEW.append(SqlUtils.generarWhereIn(
						DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051,
						listaExpedientes, params));
			} else {
				whereCEW.append(SqlUtils.generarWhereIgual(
						DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.ID_TIPO_EXPEDIENTE_051,
						filter.getIdTipoExpediente(), params));
			}
		}

		// ANYO Y NUM EXPEDIENTE
		whereCEW.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				filter.getAnyo() != null ? filter.getAnyo().toString() : filter.getAnyo(), params));
		whereCEW.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, filter.getNumExp(), params));
		// TITULO
		whereCEW.append(
				SqlUtils.generarWhereLikeNormalizado(DBConstants.T1TITULO_051, filter.getTitulo(), params, false));
		// ESTADO Y FASE COMBINADO CON ESTADO DE BAJA
		if (filter != null) {
			ConsultaExpedienteUtils.filtroEstado(whereCEW, filter.getBitacoraExpediente(), params,
					DaoConstants.T4_MINUSCULA, DaoConstants.T1_MINUSCULA);
		}
		// FECHA ALTA
		whereCEW.append(SqlUtils.generarWhereMayorIgualFecha(
				DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_ALTA_051, DaoConstants.DDMMYY,
				filter.getFechaAltaDesde(), params));
		whereCEW.append(SqlUtils.generarWhereMenorIgualFecha(
				DaoConstants.T1_MINUSCULA + DaoConstants.SIGNO_PUNTO + DBConstants.FECHA_ALTA_051, DaoConstants.DDMMYY,
				filter.getFechaAltaHasta(), params));
		// FILTROS TRAD/REV
		filtrosTradRev(whereCEW, filter, params);
		// GESTOR EXPEDIENTE
		filtroGestorExpediente(whereCEW, filter, params);
		// SUBSANACION REQUERIDA
		filtroRequerimiento(whereCEW, filter, params, filter.getSubsRequerida(),
				TipoRequerimientoEnum.SUBSANACION.getValue());
		// EXPEDIENTES RELACIONADOS
		filtroExpedientesRelacionados(whereCEW, filter, params);
		// METADATOS
		filtroMetadatos(whereCEW, filter, params);
		// PRESUPUESTO
		filtroRequerimiento(whereCEW, filter, params, filter.getRequierePresupuesto(),
				TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue());
		// NEGOCIACION FECHA
		filtroRequerimiento(whereCEW, filter, params, filter.getRequiereNegociacion(),
				TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue());
		// GRUPOS DE TRABAJO
		ExpedienteDaoUtils.getWhereGruposTrabajo(filter, whereCEW, params, false);
		return whereCEW.toString();
	}

	@Override
	protected String getWhereLike(ExpedienteConsulta bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder whereLike = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		whereLike.append(DaoConstants.WHERE_1_1);
		return whereLike.toString();
	}

	@Override
	public Object consultaexpedientes(ExpedienteConsulta filter, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);

		if (isCount) {
			query.append(DaoConstants.SELECT + DaoConstants.COUNT + DaoConstants.ABRIR_PARENTESIS);
			query.append("SUBSTR (t1.ANYO_051, 2, 4)  || LPAD ( t1.NUM_EXP_051, 6, '0' )");
			query.append(DaoConstants.CERRAR_PARENTESIS);

		} else {
			query.append(this.getSelect());
			query.append(", tDocs.DOCS TIPOSDOCUMENTOLISTAGG");
		}

		query.append(this.getFrom());
		query.append(" LEFT JOIN ( ");
		query.append(" SELECT ANYO_056, NUM_EXP_056, LISTAGG(desc_eu_042, ', ') WITHIN GROUP (ORDER BY desc_eu_042) AS docs ");
		query.append(" FROM aa79b56t00 ");
		query.append(" LEFT JOIN aa79b42t00 ON tipo_documento_056 = id_042 ");
		query.append(" GROUP BY ANYO_056, NUM_EXP_056 ");
		query.append(" ) tDocs ON tDocs.ANYO_056 = t1.anyo_051 and tDocs.NUM_EXP_056 = t1.num_exp_051 ");
		query.append(this.getWhere(filter, params));

		if (filter.getFilterFactura() != null) {
			query.append(" AND 0 < ( ");
			query.append("SELECT ");
			query.append("count (1) ");

			query.append("FROM ");
			query.append("aa79ba5s01 a5 ");
			query.append("JOIN aa79ba4s01 a4 ON a5.id_factura_0a5 = a4.id_factura_0a4 ");
			query.append("JOIN w0512s01 t12 ON a4.id_liquidacion_0a4 = t12.t12_referencia ");
			query.append("where a5.num_exp_0a5 = t1.NUM_EXP_051 and a5.anyo_0a5 = t1.ANYO_051 ");
			if (filter.getFilterFactura().getIdFactura() != null) {
				query.append(
						"AND substr(t12.t12_ej_contable, 3, 2) || lpad(a4.app_id_0a4, 2, '0') || substr(a4.id_liquidacion_0a4, 3, 6) = "
								+ filter.getFilterFactura().getIdFactura() + " ");
			}
			if (filter.getFilterFactura().getEstadoFactura() != null) {
				query.append("AND t12.t00_estado_id = "
						+ filter.getFilterFactura().getEstadoFactura().getIndEstadoFactura() + " ");
			}
			query.append(") ");

		}

		if (filter.getTipoDocumento() != null) {

			query.append(" AND ( SELECT ");
			query.append(" COUNT(1) ");

			query.append(" FROM AA79B56S01 t56 ");

			query.append(" WHERE t56.ANYO_056 = t1.ANYO_051 ");
			query.append(" AND t56.NUM_EXP_056 = t1.NUM_EXP_051 AND t56.TIPO_DOCUMENTO_056 = ?) > 0 ");
			params.add(filter.getTipoDocumento());
		}

		StringBuilder paginatedQuery = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			return this.getJdbcTemplate().query(paginatedQuery.toString(), this.getRwMapConsultaExpedientes(),
					params.toArray());
		}
	}
	/*


*/

	@Override
	public Integer asignarGestorAExpedientes(ListaExpediente listaExpedientes, PersonalIZO personalAInsertar) {
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		if (ConsultaExpedienteUtils.listaExpYGestorValidos(listaExpedientes)) {
			List<Object> params = new ArrayList<Object>();
			query.append(DaoConstants.UPDATE + DBConstants.TABLA_54);
			query.append(DaoConstants.SET + DBConstants.DNI_SOLICITANTE_054 + DaoConstants.SIGNOIGUAL_INTERROGACION
					+ DaoConstants.SIGNO_COMA);
			query.append(
					DBConstants.TIPO_ENTIDAD_054 + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.SIGNO_COMA);
			query.append(DBConstants.ID_ENTIDAD_054 + DaoConstants.SIGNOIGUAL_INTERROGACION + DaoConstants.SIGNO_COMA);
			query.append(DBConstants.IND_VIP_054 + DaoConstants.SIGNOIGUAL_INTERROGACION);
			query.append(DaoConstants.WHERE);
			params.add(listaExpedientes.getListaExpediente().get(0).getGestorExpediente().getSolicitante().getDni());
			params.add(personalAInsertar.getEntidad().getTipo());
			params.add(personalAInsertar.getEntidad().getCodigo());
			params.add(personalAInsertar.getGestorExpedientesVIP());
			boolean esPrimero = true;
			for (Expediente expediente : listaExpedientes.getListaExpediente()) {
				if (esPrimero) {
					esPrimero = false;
				} else {
					query.append(DaoConstants.OR);
				}
				query.append(DBConstants.ANYO_054 + DaoConstants.SIGNOIGUAL_INTERROGACION);
				query.append(DaoConstants.AND + DBConstants.NUM_EXP_054 + DaoConstants.SIGNOIGUAL_INTERROGACION);
				params.add(expediente.getAnyo());
				params.add(expediente.getNumExp());
			}
			return this.getJdbcTemplate().update(query.toString(), params.toArray());

		}
		return Constants.CERO;

	}

	@Override
	public Integer getEstadoGestor(String dni) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.SELECT_COUNT + DaoConstants.FROM + DBConstants.VISTAX54JAPISOLICITANTES
				+ DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereLike(DBConstants.DNI, dni, params, false));
		query.append(SqlUtils.generarWhereLike(DBConstants.ESTADO, EstadoGestorEnum.ALTA.getValue(), params, false));

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Integer comprobarDatosPagoAProveedores(Expediente expediente) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		boolean esIntepretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
			esIntepretacion = true;
		}
		query.append(DaoConstants.SELECT_COUNT);
		query.append(ConsultaExpedienteUtils.getDatosPagoProveedoresConsultaFrom(expediente, false, null));
		query.append(ConsultaExpedienteUtils.getDatosPagoProveedoresConsultaWhere(expediente, params, false,
				esIntepretacion));
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Object datosPagoProveedoresConsulta(Expediente filter, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		boolean esIntepretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(filter.getIdTipoExpediente())) {
			esIntepretacion = true;
		}
		if (isCount) {
			query.append(DaoConstants.SELECT_COUNT);
		} else {
			query.append(ConsultaExpedienteUtils.getDatosPagoProveedoresConsultaSelect(filter, params, this.msg,
					esIntepretacion));
		}

		query.append(ConsultaExpedienteUtils.getDatosPagoProveedoresConsultaFrom(filter, true, esIntepretacion));
		query.append(
				ConsultaExpedienteUtils.getDatosPagoProveedoresConsultaWhere(filter, params, true, esIntepretacion));

		StringBuilder paginatedQuery = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		paginatedQuery.append(Utils.getPaginationQuery(jqGridRequestDto, isCount, query));

		if (isCount) {
			return this.getJdbcTemplate().queryForObject(paginatedQuery.toString(), params.toArray(), Long.class);
		} else {
			if (esIntepretacion) {
				return this.getJdbcTemplate().query(paginatedQuery.toString(),
						this.getRwMapDatosPagoProveedoresConsultaInter(), params.toArray());
			} else {
				return this.getJdbcTemplate().query(paginatedQuery.toString(),
						this.getRwMapDatosPagoProveedoresConsultaTradRev(), params.toArray());
			}

		}
	}

	@Override
	public Integer comprobarDatosFacturacionExpedienteConsulta(Expediente expediente) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		boolean esIntepretacion = false;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
			esIntepretacion = true;
		}
		query.append(DaoConstants.SELECT_COUNT);
		query.append(ConsultaExpedienteUtils.getDatosFacturacionExpedienteConsultaFrom(expediente, esIntepretacion));
		query.append(ConsultaExpedienteUtils.getDatosFacturacionExpedienteConsultaWhere(expediente, params,
				esIntepretacion));
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public Integer comprobarEsSolicitud(Expediente expediente) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		query.append(DaoConstants.SELECT_COUNT);
		query.append(DaoConstants.FROM + DBConstants.TABLA_51 + DaoConstants.BLANK + DaoConstants.T1_MINUSCULA);
		query.append(DaoConstants.WHERE_1_1);
		query.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051,
				expediente.getAnyo(), params));
		query.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051,
				expediente.getNumExp(), params));
		query.append(DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ORIGEN_051 + DaoConstants.NOT
				+ DaoConstants.LIKE + "'" + OrigenExpedienteEnum.OFICIO.getValue() + "'");
		query.append(
				DaoConstants.AND + DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ORIGEN_051 + DaoConstants.IS_NOT_NULL);
		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public DetalleExpedienteVisionCliente findDatosDetalleExpedienteDesdeClienteConsulta(Expediente expediente) {
		List<Object> paramsDDEDCC = new ArrayList<Object>();
		StringBuilder queryDDEDCC = new StringBuilder();
		Locale es = new Locale(Constants.LANG_CASTELLANO);
		Locale eu = new Locale(Constants.LANG_EUSKERA);

		// SELECT - INICIO
		queryDDEDCC.append(ConsultaExpedienteUtils.selectDatosDetalleExpedienteDesdeClienteConsulta(es, eu, this.msg));
		// SELECT - FIN
		// FROM - INICIO
		queryDDEDCC.append(ConsultaExpedienteUtils.fromDatosDetalleExpedienteDesdeClienteConsulta());
		// WHERE - INICIO
		queryDDEDCC.append(DaoConstants.WHERE_1_1);
		queryDDEDCC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.ANYO_051,
				expediente.getAnyo(), paramsDDEDCC));
		queryDDEDCC.append(SqlUtils.generarWhereIgual(DaoConstants.T1_MINUSCULA_PUNTO + DBConstants.NUM_EXP_051,
				expediente.getNumExp(), paramsDDEDCC));
		// WHERE - FIN
		List<DetalleExpedienteVisionCliente> beanList = this.getJdbcTemplate().query(queryDDEDCC.toString(),
				getRwMapDatosDetalleExpedienteDesdeClienteConsulta(), paramsDDEDCC.toArray());
		DetalleExpedienteVisionCliente datosExp = DataAccessUtils.uniqueResult(beanList);

		if (!TipoExpedienteEnum.INTERPRETACION.getValue().equals(datosExp.getIdTipoExpediente()) &&
				TipoExpedienteEnum.TRADUCCION.getValue().equals(datosExp.getIdTipoExpediente())) {
				DatosTareaTrados datosTrados = this.getDatosTareaTrados(expediente);
			datosExp.getExpedienteTradRev().setTradosExp(datosTrados);
		}

		if (EstadoExpedienteEnum.ANULADO.getValue() == datosExp.getBitacoraExpediente().getEstadoExp().getId()
				|| EstadoExpedienteEnum.RECHAZADO.getValue() == datosExp.getBitacoraExpediente().getEstadoExp()
						.getId()) {
			AnulacionRechazo anulacionRechazoBean = this.recuperarRechazoAnulacion(expediente);
			datosExp.setAnulacionRechazo(anulacionRechazoBean);
		}

		return datosExp;
	}

	private DatosTareaTrados getDatosTareaTrados(Expediente expediente) {
		List<Object> paramsDTT = new ArrayList<Object>();
		StringBuilder queryDTT = new StringBuilder();
		queryDTT.append(ConsultaExpedienteUtils.getDatosTareaTradosQuery(expediente, paramsDTT));
		List<DatosTareaTrados> beanList = this.getJdbcTemplate().query(queryDTT.toString(), paramsDTT.toArray(),
				getRwMapDatosTareaTradosConsultaClienteConsulta());
		return DataAccessUtils.uniqueResult(beanList);
	}

	private AnulacionRechazo recuperarRechazoAnulacion(Expediente expediente) {
		List<Object> paramsAnul = new ArrayList<Object>();
		StringBuilder queryAnul = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);

		queryAnul.append(DaoConstants.SELECT
				+ " NVL(OBSV_RECHAZO_068,OBSV_ANULACION_0A2) OBSERVACIONES, NVL(DESC_ES_013,DESC_ES_012) DESCES, NVL(DESC_EU_013,DESC_EU_012) DESCEU "
				+ DaoConstants.FROM + " AA79B51S01 ");
		queryAnul.append(
				" JOIN AA79B59S01 ON ANYO_059 = ANYO_051 AND NUM_EXP_059 = NUM_EXP_051 AND ID_ESTADO_BITACORA_059 = ESTADO_BITACORA_051");
		queryAnul.append(" LEFT JOIN AA79B67S01 ON ID_067 = ID_RECHAZO_059 ");
		queryAnul.append(" LEFT JOIN AA79B13S01 ON ID_013 = ID_MOTIVO_RECHAZO_067 ");
		queryAnul.append(" LEFT JOIN AA79B68S01 ON ID_068 = ID_RECHAZO_059 ");

		queryAnul.append(" LEFT JOIN AA79BA1S01 ON ID_0A1 = ID_ANULACION_059 ");
		queryAnul.append(" LEFT JOIN AA79B12S01 ON ID_012 = ID_MOTIVO_ANULACION_0A1 ");
		queryAnul.append(" LEFT JOIN AA79BA2S01 ON ID_0A2 = ID_ANULACION_059 ");

		queryAnul.append(DaoConstants.WHERE
				+ " ANYO_051 = ? AND NUM_EXP_051 = ? AND (ID_RECHAZO_059 IS NOT NULL OR ID_ANULACION_059 IS NOT NULL)");

		paramsAnul.add(expediente.getAnyo());
		paramsAnul.add(expediente.getNumExp());

		List<AnulacionRechazo> listaAnulacionRechazo = this.getJdbcTemplate().query(queryAnul.toString(),
				paramsAnul.toArray(), this.anulacionRechazo);

		return DataAccessUtils.uniqueResult(listaAnulacionRechazo);

	}

	@Override
	public List<ExpedienteConsulta> consultaexpedientesGetSelectedIds(ExpedienteConsulta filterExpedienteConsulta,
			JQGridRequestDto tableData) {
		List<Object> paramsCEGSI = new ArrayList<Object>();
		StringBuilder queryCEGSI = new StringBuilder(ConsultasDaoImpl.STRING_BUILDER_INIT);
		List<String> camposIds = new ArrayList<String>();
		camposIds.add("t1.ANYO_051");
		camposIds.add("t1.NUM_EXP_051");
		// SELECT
		queryCEGSI.append("SELECT DISTINCT t1.ANYO_051 " + DBConstants.ANYO);
		queryCEGSI.append(", t1.NUM_EXP_051 " + DBConstants.NUMEXP);
		// FROM
		queryCEGSI.append(this.getFrom());
		// WHERE
		queryCEGSI.append(this.getWhere(filterExpedienteConsulta, paramsCEGSI));
		queryCEGSI.append(SqlUtils.generarRupTableSelectedIds(camposIds, tableData.getMultiselection().getSelectedIds(),
				tableData.getMultiselection().getSelectedAll(), tableData.getCore().getPkToken()));

		return this.getJdbcTemplate().query(queryCEGSI.toString(), this.getRwMapConsultaExpedientesIds(),
				paramsCEGSI.toArray());
	}

}