package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.model.Contacto;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosSolicitante;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosSolicitanteSolicitud;
import com.ejie.aa79b.service.ExpedienteTradRevService;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.PLConnectionUtils;
import com.ejie.aa79b.utils.Utils;
import org.apache.commons.lang.StringUtils;
@Repository
@Transactional
public class Aa79bServicioItzulnetWsDaoImpl extends BaseDaoImpl implements Aa79bServicioItzulnetWsDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired()
	private ExpedienteTradRevService expedienteTradRevService;

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Expediente> rwMap = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			return expediente;
		}
	};

	private RowMapper<AA79bDatosSolicitante> rwMapDatosEntidad = new RowMapper<AA79bDatosSolicitante>() {
		@Override
		public AA79bDatosSolicitante mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			AA79bDatosSolicitante datosSolicitante = new AA79bDatosSolicitante();
			datosSolicitante.setCodigoEntidad(resultSet.getString("CODIGOENTIDAD"));
			datosSolicitante.setTipoEntidad(resultSet.getString("TIPOENTIDAD"));
			return datosSolicitante;
		}
	};

	@Override()
	public Integer addExpediente(Expediente expediente) {
		Calendar cal = Calendar.getInstance();
		Long anyo = (long) cal.get(Calendar.YEAR);
		expediente.setAnyo(anyo);
		Integer numExp = (int) PLConnectionUtils.crearNumExp(anyo, this.getJdbcTemplate());
		expediente.setNumExp(numExp);
		List<Object> params = new ArrayList<Object>();
		StringBuilder str = new StringBuilder();
		str.append("INSERT INTO AA79B51S01");
		str.append(" (ANYO_051");
		str.append(", NUM_EXP_051");
		str.append(", ID_TIPO_EXPEDIENTE_051");
		str.append(", ORIGEN_051");
		str.append(", FECHA_ALTA_051");
		str.append(", TITULO_051");
		str.append(", APLIC_ORIGEN_051");
		str.append(", OBSV_FACT_051");
		str.append(") VALUES (?,?,?,?,?,?,?,?)");

		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(expediente.getIdTipoExpediente());
		params.add(expediente.getOrigen());
		params.add(expediente.getFechaAlta());
		params.add(expediente.getTitulo());
		params.add(expediente.getAplicacionOrigen());
		params.add(expediente.getObsvFacturacion());

		return this.getJdbcTemplate().update(str.toString(), params.toArray());
	}

	@Override()
	public Integer addExpedienteTradRev(Expediente expediente) {
		ExpedienteTradRev datosTradRev = expediente.getExpedienteTradRev();
		Date fecha = datosTradRev.getFechaFinalIzo();
		String hora = datosTradRev.getHoraFinalIzo();
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO AA79B53S01");
		query.append(" (ANYO_053");
		query.append(", NUM_EXP_053");
		query.append(", IND_PUBLICAR_BOPV_053");
		query.append(", IND_PREVISTO_BOPV_053");
		query.append(", ID_IDIOMA_053");
		query.append(", IND_CONFIDENCIAL_053");
		query.append(", IND_CORREDACCION_053");
		query.append(", TEXTO_053");
		query.append(", TIPO_REDACCION_053");
		query.append(", PARTICIPANTES_053");
		query.append(", REF_TRAMITAGUNE_053");
		query.append(", NUM_TOTAL_PAL_IZO_053");
		query.append(", FECHA_FINAL_IZO_053");
		query.append(", IND_FACTURABLE_053");
		query.append(", ID_RELEVANCIA_053");
		query.append(", IND_URGENTE_053");
		query.append(", FECHA_FINAL_SOLIC_053");
		query.append(",FECHA_FINAL_PROP_053");
		query.append(", IND_PRESUPUESTO_053");
		query.append(", NUM_TOTAL_PAL_SOLIC_053");
		query.append(", IND_OBSERVACIONES_053)");
		query.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(Utils.getValueInd(datosTradRev.getIndPublicarBopv()));
		params.add(Utils.getValueInd(datosTradRev.getIndPrevistoBopv()));
		params.add(datosTradRev.getIdIdioma());
		params.add(Utils.getValueInd(datosTradRev.getIndConfidencial()));
		params.add(Utils.getValueInd(datosTradRev.getIndCorredaccion()));
		params.add(datosTradRev.getTexto());
		params.add(datosTradRev.getTipoRedaccion());
		params.add(datosTradRev.getParticipantes());
		params.add(datosTradRev.getRefTramitagune());
		params.add(datosTradRev.getNumTotalPalIzo());
		params.add(DateUtils.obtFechaHoraFormateada(fecha, hora));
		params.add(Utils.getValueInd(datosTradRev.getIndFacturable()));
		params.add(datosTradRev.getIdRelevancia());
		params.add(Utils.getValueInd(datosTradRev.getIndUrgente()));
		params.add(datosTradRev.getFechaFinalSolic());
		params.add(datosTradRev.getFechaFinalProp());
		params.add(Utils.getValueInd(datosTradRev.getIndPresupuesto()));
		params.add(datosTradRev.getNumTotalPalSolic());
		params.add(Utils.getValueInd(datosTradRev.getIndObservaciones()));

		return this.getJdbcTemplate().update(query.toString(), params.toArray());
	}

	@Override()
	public Integer addObservacionesExpediente(Expediente expediente) {
		ObservacionesExpediente observaciones = expediente.getExpedienteTradRev().getObservaciones();
		List<Object> params = new ArrayList<Object>();
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO AA79B55S01");
		insert.append(" (ANYO_055");
		insert.append(", NUM_EXP_055");
		insert.append(", OBSERVACIONES_055");
		insert.append(", OID_FICHERO_055");
		insert.append(", EXTENSION_DOC_055");
		insert.append(", CONTENT_TYPE_055");
		insert.append(", NOMBRE_FICHERO_055");
		insert.append(", TAMANO_DOC_055)");
		insert.append(" VALUES (?,?,?,?,?,?,?,?)");

		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(observaciones.getObservaciones());
		params.add(observaciones.getOidFichero());
		params.add(observaciones.getExtension());
		params.add(observaciones.getContentType());
		params.add(observaciones.getNombre());
		params.add(observaciones.getTamano());

		return this.getJdbcTemplate().update(insert.toString(), params.toArray());
	}

	@Override
	public Integer addGestorExpediente(Expediente expediente) {
		Gestor gestor = expediente.getGestorExpediente();
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO AA79B54S01");
		query.append(" (ANYO_054");
		query.append(", NUM_EXP_054");
		query.append(", DNI_SOLICITANTE_054");
		query.append(", TIPO_ENTIDAD_054");
		query.append(", ID_ENTIDAD_054");
		query.append(", IND_VIP_054");
		query.append(", ES_GRUPO_BOLETIN_054");
		query.append(") VALUES (?,?,?,?,?,?,?)");

		//Anadir el campo esGrupoBoletin

		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(gestor.getSolicitante().getDni());
		params.add(gestor.getEntidad().getTipo());
		params.add(gestor.getEntidad().getCodigo());
		params.add(Utils.getValueInd(gestor.getSolicitante().getGestorExpedientesVIP()));
		params.add(Utils.getValueInd(gestor.getSolicitante().getGrupoBoletin()));

		return this.getJdbcTemplate().update(query.toString(), params.toArray());

	}

	@Override()
	public Integer addContactoFacturacionExpediente(Expediente expediente) {
		ContactoFacturacionExpediente contactoFacturacionExpediente = expediente.getContactosFacturacion().get(0);
		final Long elId = this.getNextVal("AA79B58Q00");
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO AA79B58S01");
		query.append(" (ID_058");
		query.append(", ANYO_058");
		query.append(", NUM_EXP_058");
		query.append(", TIPO_ENTIDAD_ASOC_058");
		query.append(", ID_ENTIDAD_ASOC_058");
		query.append(", DNI_CONTACTO_058");
		query.append(", POR_FACTURA_058)");
		query.append(" VALUES (?,?,?,?,?,?,?)");

		params.add(elId);
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		params.add(contactoFacturacionExpediente.getEntidadSolicitante().getTipo());
		params.add(contactoFacturacionExpediente.getEntidadSolicitante().getCodigo());
		params.add(contactoFacturacionExpediente.getContacto().getDni());
		params.add(contactoFacturacionExpediente.getPorFactura058());

		return this.getJdbcTemplate().update(query.toString(), params.toArray());

	}

	@Override
	public Integer addDocumentosExpediente(Expediente expediente) {
		Long anyo = expediente.getAnyo();
		Integer numExp = expediente.getNumExp();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO AA79B56S01");
		query.append(" (ID_DOC_056");
		query.append(", ANYO_056");
		query.append(", NUM_EXP_056");
		query.append(", OID_FICHERO_056");
		query.append(", EXTENSION_DOC_056");
		query.append(", TAMANO_DOC_056");
		query.append(", IND_ENCRIPTADO_056");
		query.append(", ID_DOC_REL_056");
		query.append(", CLASE_DOCUMENTO_056");
		query.append(", TIPO_DOCUMENTO_056");
		query.append(", TITULO_056");
		query.append(", NUM_PAL_SOLIC_056");
		query.append(", NUM_PAL_IZO_056");
		query.append(", PERSONA_CONTACTO_056");
		query.append(", EMAIL_CONTACTO_056");
		query.append(", TELEFONO_CONTACTO_056");
		query.append(", FECHA_ALTA_056");
		query.append(", DNI_ALTA_056");
		query.append(", DIRECCION_CONTACTO_056");
		query.append(", ENTIDAD_CONTACTO_056");
		query.append(", CONTENT_TYPE_056");
		query.append(", NOMBRE_FICHERO_056");
		query.append(", RUTA_PIF_FICHERO_056");
		query.append(", OID_DOKUSI_056)");
		query.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Gestor gestor = expediente.getGestorExpediente();
		for (DocumentosExpediente beanDocExpediente : expediente.getDocumentosExpediente()) {
			Long idDocumento = null;
			for (FicheroDocExp ficheroDocExp : beanDocExpediente.getFicheros()) {
				final Long elId = this.getNextVal("AA79B56Q00");

				if (StringUtils.isBlank(beanDocExpediente.getTitulo())) {
					String elTitulo = ficheroDocExp.getNombre();
					elTitulo = elTitulo.substring(Constants.CERO, elTitulo.lastIndexOf('.'));
					beanDocExpediente.setTitulo(elTitulo);
				}

				List<Object> params = new ArrayList<Object>();
				params.add(elId);
				params.add(expediente.getAnyo());
				params.add(expediente.getNumExp());
				params.add(ficheroDocExp.getOid());
				params.add(ficheroDocExp.getExtension());
				params.add(ficheroDocExp.getTamano());
				params.add(Utils.getValueInd(ficheroDocExp.getEncriptado()));
				// Se almacena el id del documento relacionado
				params.add(idDocumento);
				params.add(beanDocExpediente.getClaseDocumento());
				params.add(beanDocExpediente.getTipoDocumento());
				params.add(beanDocExpediente.getTitulo());
				params.add(beanDocExpediente.getNumPalSolic());
				params.add(beanDocExpediente.getNumPalIzo());
				Contacto contacto = ficheroDocExp.getContacto();
				if (contacto == null) {
					contacto = new Contacto();
				}
				params.add(contacto.getPersona());
				params.add(contacto.getEmail());
				params.add(contacto.getTelefono());
				params.add(new Date());
				// dni usuario conectado
				params.add(gestor.getSolicitante().getDni());
				params.add(contacto.getDireccion());
				params.add(contacto.getEntidad());
				params.add(ficheroDocExp.getContentType());
				params.add(ficheroDocExp.getNombre());
				params.add(ficheroDocExp.getRutaPif());
				params.add(beanDocExpediente.getOidDokusi());

				this.getJdbcTemplate().update(query.toString(), params.toArray());
				idDocumento = elId;
			}
		}
		this.expedienteTradRevService.procRecalcularCamposDocumentacionPL(anyo, numExp);
		return 1;
	}

	@Override
	public Integer addBitacoraExpediente(Expediente expediente, EstadoExpedienteEnum estado, FaseExpedienteEnum fase) {
		JdbcTemplate jdbc = this.getJdbcTemplate();

		String queryId = "SELECT NVL(MAX(ID_ESTADO_BITACORA_059),0) + 1 FROM AA79B59S01 WHERE ANYO_059 = ? AND NUM_EXP_059 = ?";
		Integer idBitacora = jdbc.queryForObject(queryId, Integer.class, expediente.getAnyo(), expediente.getNumExp());

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO AA79B59S01");
		query.append(" (ANYO_059");
		query.append(", NUM_EXP_059");
		query.append(", ID_ESTADO_BITACORA_059");
		query.append(", ID_ESTADO_EXP_059");
		query.append(", ID_FASE_EXPEDIENTE_059");
		query.append(", FECHA_ALTA_059)");
		query.append(" VALUES (?,?,?,?,?, SYSDATE)");
		Integer rdo = jdbc.update(query.toString(), expediente.getAnyo(), expediente.getNumExp(), idBitacora,
				estado.getValue(), fase.getValue());

		String update = "UPDATE AA79B51S01 SET ESTADO_BITACORA_051=? WHERE ANYO_051=? AND NUM_EXP_051=?";
		jdbc.update(update, idBitacora, expediente.getAnyo(), expediente.getNumExp());
		return rdo;
	}

	@Override()
	public Integer generarSolicitud(Expediente expediente) {
		String query = "INSERT INTO AA79B69S01 (ANYO_069, NUM_EXP_069, ID_TIPO_EXPEDIENTE_069, FECHA_ALTA_069, TITULO_069) "
				+ " SELECT ANYO_051, NUM_EXP_051, ID_TIPO_EXPEDIENTE_051, FECHA_ALTA_051, TITULO_051"
				+ " FROM AA79B51S01 WHERE ANYO_051 = ? AND NUM_EXP_051 = ?";
		this.getJdbcTemplate().update(query, expediente.getAnyo(), expediente.getNumExp());

		String queryTradRev = "INSERT INTO AA79B71S01 (ANYO_071, NUM_EXP_071, IND_PUBLICAR_BOPV_071,"
				+ "ID_IDIOMA_071, IND_CONFIDENCIAL_071, IND_CORREDACCION_071, TEXTO_071, TIPO_REDACCION_071, "
				+ "PARTICIPANTES_071, REF_TRAMITAGUNE_071, NUM_TOTAL_PAL_SOLIC_071, FECHA_FINAL_SOLIC_071, "
				+ "IND_FACTURABLE_071, ID_RELEVANCIA_071, IND_URGENTE_071,IND_PRESUPUESTO_071,IND_OBSERVACIONES_071) "
				+ "SELECT ANYO_053, NUM_EXP_053, IND_PUBLICAR_BOPV_053, "
				+ "ID_IDIOMA_053, IND_CONFIDENCIAL_053, IND_CORREDACCION_053, TEXTO_053, TIPO_REDACCION_053, "
				+ "PARTICIPANTES_053, REF_TRAMITAGUNE_053, NUM_TOTAL_PAL_SOLIC_053, FECHA_FINAL_SOLIC_053"
				+ ", IND_FACTURABLE_053, ID_RELEVANCIA_053, IND_URGENTE_053, IND_PRESUPUESTO_053, IND_OBSERVACIONES_053"
				+ " FROM AA79B53S01 WHERE ANYO_053 = ? AND NUM_EXP_053 = ?";
		this.getJdbcTemplate().update(queryTradRev, expediente.getAnyo(), expediente.getNumExp());

		String queryDocumentos = "INSERT INTO AA79B73S01 (ID_DOC_073, ANYO_073, NUM_EXP_073,"
				+ "OID_FICHERO_073, EXTENSION_DOC_073, TAMANO_DOC_073, IND_ENCRIPTADO_073,"
				+ "CONTENT_TYPE_073, ID_DOC_REL_073, CLASE_DOCUMENTO_073, TIPO_DOCUMENTO_073, "
				+ "TITULO_073, NUM_PAL_SOLIC_073, PERSONA_CONTACTO_073, EMAIL_CONTACTO_073, "
				+ "TELEFONO_CONTACTO_073, DIRECCION_CONTACTO_073, ENTIDAD_CONTACTO_073, "
				+ "FECHA_ALTA_073, NOMBRE_FICHERO_073)" + " SELECT ID_DOC_056, ANYO_056, NUM_EXP_056"
				+ ", OID_FICHERO_056, EXTENSION_DOC_056, TAMANO_DOC_056, IND_ENCRIPTADO_056"
				+ ", CONTENT_TYPE_056, ID_DOC_REL_056, CLASE_DOCUMENTO_056, TIPO_DOCUMENTO_056"
				+ ", TITULO_056, NUM_PAL_SOLIC_056, PERSONA_CONTACTO_056, EMAIL_CONTACTO_056"
				+ ", TELEFONO_CONTACTO_056, DIRECCION_CONTACTO_056, ENTIDAD_CONTACTO_056"
				+ ", FECHA_ALTA_056, NOMBRE_FICHERO_056" + " FROM AA79B56S01 WHERE ANYO_056 = ? AND NUM_EXP_056 = ?";
		this.getJdbcTemplate().update(queryDocumentos, expediente.getAnyo(), expediente.getNumExp());

		String queryObservaciones = "INSERT INTO AA79B72S01 (ANYO_072, NUM_EXP_072, OBSERVACIONES_072, OID_FICHERO_072, EXTENSION_DOC_072, CONTENT_TYPE_072, NOMBRE_FICHERO_072, TAMANO_DOC_072)"
				+ " SELECT ANYO_055, NUM_EXP_055, OBSERVACIONES_055, OID_FICHERO_055, EXTENSION_DOC_055, CONTENT_TYPE_055, NOMBRE_FICHERO_055, TAMANO_DOC_055"
				+ " FROM AA79B55S01" + " WHERE ANYO_055 = ? AND NUM_EXP_055 = ?";
		this.getJdbcTemplate().update(queryObservaciones, expediente.getAnyo(), expediente.getNumExp());

		return 1;
	}

	@Override
	public int addBitacoraSolicitud(Expediente expediente, AccionBitacoraEnum accionBitacora) {
		final Long elId = this.getNextVal("AA79B79Q00");
		if (expediente.getFechaAlta() == null) {
			expediente.setFechaAlta(new Date());
		}
		String query = "INSERT INTO AA79B79S01 (ID_079, ANYO_079, NUM_EXP_079,"
				+ "ID_ACCION_BITACORA_079, FECHA_ALTA_079, USUARIO_079) " + "VALUES (?,?,?,?,?,?)";
		return this.getJdbcTemplate().update(query, elId, expediente.getAnyo(), expediente.getNumExp(),
				accionBitacora.getValue(), expediente.getFechaAlta(),
				expediente.getGestorExpediente().getSolicitante().getNombreCompleto());
	}

	private Object obtenerMensajeObservaciones(Expediente bean, Expediente original, Locale locale) {
		StringBuilder strObservaciones = new StringBuilder();
		if (original != null) {
			String strActual = this.obtenerObservaciones(bean);
			String strOriginal = this.obtenerObservaciones(original);
			if (!StringUtils.equals(strOriginal, strActual)) {
				strObservaciones.append(this.msg.getMessage(Constants.LABEL_OBSERVACIONES_MODIFICADAS, null, locale));
			}
		}

		return strObservaciones.toString();
	}

	private String obtenerObservaciones(Expediente bean) {
		String observaciones = "";
		if (bean != null) {
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(bean.getIdTipoExpediente())
					&& bean.getExpedienteInterpretacion() != null
					&& bean.getExpedienteInterpretacion().getObservaciones() != null) {
				observaciones = bean.getExpedienteInterpretacion().getObservaciones().getObservaciones();
			} else if (bean.getExpedienteTradRev() != null && bean.getExpedienteTradRev().getObservaciones() != null) {
				observaciones = bean.getExpedienteTradRev().getObservaciones().getObservaciones();
			}
		}
		return observaciones;
	}

	@Override
	public void addRegistroAcciones(Expediente expediente, Expediente original, Long accionAlta,
			Map<String, String> parametros, String mensaje) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.SOLICITANTES_ALTA.getValue());
		reg.setIdAccion(accionAlta);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		String aux = Utils.observacionesRegistro(expediente, original, parametros, this.msg, locale);
		if (StringUtils.isNotBlank(aux)) {
			StringBuilder observ = new StringBuilder();
			observ.append(this.msg.getMessage(mensaje, null, locale)).append(" \n");
			observ.append(aux);
			observ.append(this.obtenerMensajeObservaciones(expediente, original, locale));
			reg.setAnyo(expediente.getAnyo());
			reg.setNumExp(expediente.getNumExp());
			reg.setIdEstadoBitacora(expediente.getEstadoBitacora());
			reg.setObserv(observ.toString());
			reg.setUsuarioRegistro(expediente.getGestorExpediente().getSolicitante().getDni());
			this.addRegistroAccionesDao(reg);
		}
	}

	public RegistroAcciones addRegistroAccionesDao(RegistroAcciones registroAcciones) {
		String query = "INSERT INTO AA79B43S01 (ID_REGISTRO_ACCION_043, ID_PUNTO_MENU_043, ID_ACCION_043, IP_043, FECHA_REGISTRO_043, USUARIO_REGISTRO_043, ANYO_EXP_043, NUM_EXP_043,  OBSERV_043, ID_ESTADO_BITACORA_043) VALUES (AA79B43Q00.NEXTVAL,?,?,?,SYSDATE,?,?,?,?,?)";
		this.getJdbcTemplate().update(query, registroAcciones.getIdPuntoMenu(), registroAcciones.getIdAccion(),
				registroAcciones.getIp(), registroAcciones.getUsuarioRegistro(), registroAcciones.getAnyo(),
				registroAcciones.getNumExp(), registroAcciones.getObserv(), registroAcciones.getIdEstadoBitacora());
		return registroAcciones;
	}

	@Override
	public void guardarTecnico(String dni) {
		StringBuilder str = new StringBuilder();
		str.append("MERGE INTO AA79B77S01");
		str.append(" USING (SELECT DNI, TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2");
		str.append(" FROM X54JAPI_SOLICITANTES");
		str.append(" WHERE DNI = ?) ON (DNI_077 = DNI)");
		str.append(" WHEN MATCHED THEN UPDATE SET NOMBRE_077 = NOMBRE, APEL1_077 = APEL1, APEL2_077 = APEL2");
		str.append(
				" WHEN NOT MATCHED THEN INSERT (DNI_077, TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077)");
		str.append(" VALUES (DNI, TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2)");
		this.getJdbcTemplate().update(str.toString(), dni);
	}

	@Override
	public void guardarTecnico(List<String> dnis) {
		StringBuilder str = new StringBuilder();
		str.append("MERGE INTO AA79B77S01");
		str.append(" USING (SELECT DNI, TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2");
		str.append(" FROM X54JAPI_SOLICITANTES");
		str.append(" WHERE DNI IN (");
		for (int i = 0; i < dnis.size(); i++) {
			if (i > 0) {
				str.append(", ");
			}
			str.append("?");
		}
		str.append(")) ON (DNI_077 = DNI)");
		str.append(" WHEN MATCHED THEN UPDATE SET NOMBRE_077 = NOMBRE, APEL1_077 = APEL1, APEL2_077 = APEL2");
		str.append(
				" WHEN NOT MATCHED THEN INSERT (DNI_077, TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077)");
		str.append(" VALUES (DNI, TIPIDEN, PREDNI, SUFDNI, NOMBRE, APEL1, APEL2)");
		this.getJdbcTemplate().update(str.toString(), dnis.toArray());
	}

	@Override
	public List<Expediente> getExpedientesRefAplic(Expediente expediente){
		String query = "SELECT ANYO_053 ANYO, NUM_EXP_053 NUMEXP FROM AA79B53T00 JOIN AA79B51T00 ON ANYO_053 = ANYO_051 AND NUM_EXP_053 = NUM_EXP_051"
				+ " WHERE REF_TRAMITAGUNE_053 = ? AND APLIC_ORIGEN_051 = ?  AND (ANYO_053 <> ? OR NUM_EXP_053 <> ?)";

		return this.getJdbcTemplate().query(query, this.rwMap, expediente.getExpedienteTradRev().getRefTramitagune(), expediente.getAplicacionOrigen(),
				expediente.getAnyo(), expediente.getNumExp());
	}

	@Override
	public boolean comprobacionSolicitante(AA79bDatosSolicitanteSolicitud datosSolicitante) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) FROM AA79B_SOLICITANTES_TRAMITAGUNE WHERE ");
		query.append((datosSolicitante.getTipoDocumento() == null) ? "TIPIDEN IS NULL " : "TIPIDEN = ? ");
		query.append((datosSolicitante.getPrefijoDocumento() == null) ? "AND PREDNI IS NULL " : "AND PREDNI = ? ");
		query.append((datosSolicitante.getNifSolicitante() == null) ? "AND DNI IS NULL " : "AND DNI = ? ");
		query.append((datosSolicitante.getSufijoDocumento() == null) ? "AND SUFDNI IS NULL " : "AND SUFDNI = ? ");

		List<Object> params = new ArrayList<Object>();
		if (datosSolicitante.getTipoDocumento() != null) {
			params.add(datosSolicitante.getTipoDocumento());
		}
		if (datosSolicitante.getPrefijoDocumento() != null) {
			params.add(datosSolicitante.getPrefijoDocumento());
		}
		if (datosSolicitante.getNifSolicitante() != null) {
			params.add(datosSolicitante.getNifSolicitante());
		}
		if (datosSolicitante.getSufijoDocumento() != null) {
			params.add(datosSolicitante.getSufijoDocumento());
		}

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class) > 0;
	}

	@Override
	public Integer comprobacionPersona(AA79bDatosSolicitanteSolicitud datosSolicitante) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) FROM AA79B_PERSONAS WHERE ");
		query.append((datosSolicitante.getTipoDocumento() == null) ? "TIPO_DOCUMENTO IS NULL " : "TIPO_DOCUMENTO = ? ");
		query.append((datosSolicitante.getPrefijoDocumento() == null) ? "AND PREFIJO_DOCUMENTO IS NULL " : "AND PREFIJO_DOCUMENTO = ? ");
		query.append((datosSolicitante.getNifSolicitante() == null) ? "AND NIF IS NULL " : "AND NIF = ? ");
		query.append((datosSolicitante.getSufijoDocumento() == null) ? "AND SUFIJO_DOCUMENTO IS NULL " : "AND SUFIJO_DOCUMENTO = ? ");

		List<Object> params = new ArrayList<Object>();
		if (datosSolicitante.getTipoDocumento() != null) {
			params.add(datosSolicitante.getTipoDocumento());
		}
		if (datosSolicitante.getPrefijoDocumento() != null) {
			params.add(datosSolicitante.getPrefijoDocumento());
		}
		if (datosSolicitante.getNifSolicitante() != null) {
			params.add(datosSolicitante.getNifSolicitante());
		}
		if (datosSolicitante.getSufijoDocumento() != null) {
			params.add(datosSolicitante.getSufijoDocumento());
		}

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
	}

	@Override
	public AA79bDatosSolicitante getDatosEntidad(AA79bDatosSolicitanteSolicitud datosSolicitante) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT TIPO_ENTIDAD TIPOENTIDAD, CODIGO_ENTIDAD CODIGOENTIDAD FROM AA79B_PERSONAS WHERE ");
		query.append((datosSolicitante.getTipoDocumento() == null) ? "TIPO_DOCUMENTO IS NULL " : "TIPO_DOCUMENTO = ? ");
		query.append((datosSolicitante.getPrefijoDocumento() == null) ? "AND PREFIJO_DOCUMENTO IS NULL " : "AND PREFIJO_DOCUMENTO = ? ");
		query.append((datosSolicitante.getNifSolicitante() == null) ? "AND NIF IS NULL " : "AND NIF = ? ");
		query.append((datosSolicitante.getSufijoDocumento() == null) ? "AND SUFIJO_DOCUMENTO IS NULL " : "AND SUFIJO_DOCUMENTO = ? ");

		List<Object> params = new ArrayList<Object>();
		if (datosSolicitante.getTipoDocumento() != null) {
			params.add(datosSolicitante.getTipoDocumento());
		}
		if (datosSolicitante.getPrefijoDocumento() != null) {
			params.add(datosSolicitante.getPrefijoDocumento());
		}
		if (datosSolicitante.getNifSolicitante() != null) {
			params.add(datosSolicitante.getNifSolicitante());
		}
		if (datosSolicitante.getSufijoDocumento() != null) {
			params.add(datosSolicitante.getSufijoDocumento());
		}

		return DataAccessUtils.uniqueResult(this.getJdbcTemplate().query(query.toString(), this.rwMapDatosEntidad, params.toArray()));
	}

	@Override
	public String validarSolicitud(Expediente expediente) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ESTADO_BAJA_051 ");
		query.append("FROM AA79B51S01 ");
		query.append("WHERE ANYO_051 = ? AND NUM_EXP_051 = ?");

		List<Object> params = new ArrayList<Object>();
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		try {
			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), String.class);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return "";
		}
	}


	@Override
	public Integer validarEliminacion(Expediente expediente) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT t59.id_fase_expediente_059");
		query.append(" FROM aa79b51t00 t51");
		query.append(" INNER JOIN aa79b54t00 t54 ON t54.anyo_054 = t51.anyo_051 AND t54.num_exp_054 = t51.num_exp_051");
		query.append(" INNER JOIN aa79b59t00 t59 ON t59.anyo_059 = t51.anyo_051 AND t59.num_exp_059 = t51.num_exp_051 AND "
				+ "t59.id_estado_bitacora_059  = t51.estado_bitacora_051");
		query.append(" INNER JOIN aa79b77t00 t77 ON t54.dni_solicitante_054 = t77.dni_077");
		query.append(" INNER JOIN aa79b53t00 t53 ON t53.anyo_053 = t51.anyo_051 AND t53.num_exp_053 = t51.num_exp_051");
        query.append(" WHERE t51.origen_051 = '" + Constants.ORIGEN_WEB_SERVICE + "' AND t51.aplic_origen_051 = ? AND t77.tipiden_077 = ?");
		if (StringUtils.isEmpty(expediente.getGestorExpediente().getSolicitante().getPreDni())) {
            query.append(" AND (t77.predni_077 IS NULL OR t77.predni_077 = '')");
        }else {
            query.append(" AND t77.predni_077 = ?");
        }
        query.append(" AND t77.dni_077 = ? ");
        if (StringUtils.isEmpty(expediente.getGestorExpediente().getSolicitante().getSufDni())) {
            query.append(" AND (t77.sufdni_077 IS NULL OR t77.sufdni_077 = '')");
        }else {
            query.append(" AND t77.sufdni_077 = ?");
        }
		query.append(" AND t53.ref_tramitagune_053 = ? AND t51.anyo_051 = ? AND t51.num_exp_051 = ? ");

		List<Object> params = new ArrayList<Object>();
		params.add(expediente.getAplicacionOrigen());
		params.add(expediente.getGestorExpediente().getSolicitante().getTipIden());
		if (!StringUtils.isEmpty(expediente.getGestorExpediente().getSolicitante().getPreDni())) {
			params.add(expediente.getGestorExpediente().getSolicitante().getPreDni());
		}
		params.add(expediente.getGestorExpediente().getSolicitante().getDni());
		if (!StringUtils.isEmpty(expediente.getGestorExpediente().getSolicitante().getSufDni())) {
			params.add(expediente.getGestorExpediente().getSolicitante().getSufDni());
		}
		params.add(expediente.getExpedienteTradRev().getRefTramitagune());
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		try {
			return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Integer.class);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return -1;
		}

	}

	@Override
	public void eliminarSolicitud(Expediente expediente) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE AA79B51S01 SET FECHA_BAJA_051=SYSDATE, ESTADO_BAJA_051='" + Constants.BAJA + "', OBSV_BAJA_051=? "
				+ " WHERE ANYO_051=? AND NUM_EXP_051=?");

		this.getJdbcTemplate().update(query.toString(), expediente.getMotivosAnulacion().getDescEs012(), expediente.getAnyo(), expediente.getNumExp());
	}


	@Override()
	public Integer addComunicacion(Comunicaciones comunicaciones) {

		if ( StringUtils.isNotEmpty(comunicaciones.getOidFichero())) {
			//Si hay documento adjunto lo inserto
			Long elID = this.getNextVal("AA79B88Q00");
			String query = "INSERT INTO AA79B88S01 (ID_FICHERO_088, NOMBRE_FICHERO_088, TITULO_FICHERO_088, EXTENSION_FICHERO_088, CONTENT_TYPE_FICHERO_088, TAMANO_FICHERO_088, IND_ENCRIPTADO_088,OID_FICHERO_088 ) VALUES (?,?,?,?,?,?,?,?)";

			this.getJdbcTemplate().update(query, elID, comunicaciones.getNombre(), comunicaciones.getTitulo(),
					comunicaciones.getExtension(), comunicaciones.getContentType(),
					comunicaciones.getTamano(), comunicaciones.getEncriptado(),
					comunicaciones.getOidFichero() );
			comunicaciones.setIdFichero0D4(new BigDecimal(elID));
			comunicaciones.setCodigo(elID);
		}

		//Inserta la comunicacion
		Long elIdComunicacion = this.getNextVal("AA79BD4Q00");
		comunicaciones.setId0D4(elIdComunicacion);
		StringBuilder queryAP = new StringBuilder(GenericoDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsAP = new ArrayList<Object>();
		paramsAP.add(elIdComunicacion);
		paramsAP.add(comunicaciones.getAnyo());
		paramsAP.add(comunicaciones.getNumExp());
		paramsAP.add(comunicaciones.getRefTramitagune());
		paramsAP.add(comunicaciones.getTipo());
		paramsAP.add(comunicaciones.getRemitente());
		paramsAP.add(comunicaciones.getAsunto());
		paramsAP.add(comunicaciones.getMensaje());
		paramsAP.add(comunicaciones.getIdFichero0D4());
		queryAP.append(
				"INSERT INTO AA79BD4S01 (ID_0D4, ANYO_0D4, NUM_EXP_0D4, REF_TRAMITAGUNE_0D4, FECHA_0D4, TIPO_0D4, REMITENTE_0D4, ASUNTO_0D4, MENSAJE_0D4, ID_FICHERO_0D4) VALUES (?,?,?,?,SYSDATE,?,?,?,?,?)");
		return this.getJdbcTemplate().update(queryAP.toString(), paramsAP.toArray());

	}
}
