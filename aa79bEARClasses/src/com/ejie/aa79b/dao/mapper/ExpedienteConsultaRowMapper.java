package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpedienteConsulta;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.FilterFactura;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.utils.Utils;

public class ExpedienteConsultaRowMapper implements RowMapper<ExpedienteConsulta> {

	@Override
	public ExpedienteConsulta mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ExpedienteConsulta expedienteConsulta = new ExpedienteConsulta();
		expedienteConsulta.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expedienteConsulta.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expedienteConsulta.setAnyoNumExpConcatenado(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
		expedienteConsulta.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expedienteConsulta.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expedienteConsulta.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expedienteConsulta.setTitulo(resultSet.getString(DBConstants.TITULO));
		expedienteConsulta.setOrigen(resultSet.getString(DBConstants.ORIGEN));
		expedienteConsulta.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		expedienteConsulta.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		expedienteConsulta.setTiposDocumentoListagg(resultSet.getString("TIPOSDOCUMENTOLISTAGG"));

		// Bitacora
		BitacoraExpediente bitacora = new BitacoraExpediente();
		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(resultSet.getLong(DBConstants.IDESTADOEXP));
		estado.setDescEu(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCEU));
		estado.setDescEs(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCES));
		estado.setDescAbrEu(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCABREU));
		estado.setDescAbrEs(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCABRES));
		FasesExpediente fase = new FasesExpediente();
		fase.setId(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		fase.setDescEu(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCEU));
		fase.setDescEs(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCES));
		fase.setDescAbrEu(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCABREU));
		fase.setDescAbrEs(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCABRES));
		bitacora.setEstadoExp(estado);
		bitacora.setFaseExp(fase);
		expedienteConsulta.setBitacoraExpediente(bitacora);
		// Gestor
		Gestor gestorExpediente = new Gestor();
		Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setDni(resultSet.getString(DBConstants.DNIGESTOR));
		solicitanteAux.setPreDni(resultSet.getString(DBConstants.PREDNIGESTOR));
		solicitanteAux.setSufDni(resultSet.getString(DBConstants.SUFDNIGESTOR));
		solicitanteAux.setDniCompleto(resultSet.getString(DBConstants.DNICOMPLETOGESTOR));
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString(DBConstants.INDVIPGESTOR));
		solicitanteAux.setGestorExpedientesVIPDescEu(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCEU));
		solicitanteAux.setGestorExpedientesVIPDescEs(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCES));
		solicitanteAux.setNombre(resultSet.getString(DBConstants.NOMBREGESTOR));
		solicitanteAux.setApellido1(resultSet.getString(DBConstants.APEL1GESTOR));
		solicitanteAux.setApellido2(resultSet.getString(DBConstants.APEL2GESTOR));

		String dniVinculado = resultSet.getString("DNIVINCULADOOBAJA");
		solicitanteAux.setSolicitanteVinculado(StringUtils.isNotBlank(dniVinculado));

		gestorExpediente.setSolicitante(solicitanteAux);
		Entidad entidadAux = new Entidad();
		entidadAux.setTipo(resultSet.getString(DBConstants.TIPOENTIDADGESTOR));
		entidadAux.setTipoDesc(Utils.getTipoEntidadDescLabel(entidadAux.getTipo()));
		entidadAux.setCodigo(resultSet.getInt(DBConstants.IDENTIDADGESTOR));
		entidadAux.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEUGESTOR));
		entidadAux.setDescEs(resultSet.getString(DBConstants.DESCENTIDADESGESTOR));
		entidadAux.setDescAmpEs(resultSet.getString("ENTIDAD_DESC_AMP_ES"));
		entidadAux.setDescAmpEu(resultSet.getString("ENTIDAD_DESC_AMP_EU"));
		gestorExpediente.setEntidad(entidadAux);
		expedienteConsulta.setGestorExpediente(gestorExpediente);
		// trad/rev
		ExpedienteTradRev datosTradRev = new ExpedienteTradRev();
		datosTradRev.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
		datosTradRev.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
		datosTradRev.setNumTotalPalIzo(resultSet.getInt(DBConstants.NUMPALIZO));
		datosTradRev.setNumTotalPalSolic(resultSet.getInt(DBConstants.NUMTOTALPALSOLIC));
		datosTradRev.setIndPublicarBopv(resultSet.getString(DBConstants.INDPUBLICARBOPV));
		datosTradRev.setPublicarBopvDescEu(resultSet.getString(DBConstants.PUBLICARBOPVDESCEU));
		datosTradRev.setPublicarBopvDescEs(resultSet.getString(DBConstants.PUBLICARBOPVDESCES));
		datosTradRev.setFechaEntregaReal(resultSet.getDate(DBConstants.FECHAENTREGAREAL));
		datosTradRev.setHoraEntregaReal(resultSet.getString(DBConstants.HORAENTREGAREAL));

		// ExpedienteInterpretacion
		com.ejie.aa79b.model.ExpedienteInterpretacion expedienteInterpretacion = new com.ejie.aa79b.model.ExpedienteInterpretacion();
		expedienteInterpretacion.setFechaIni(resultSet.getDate(DBConstants.FECHA_INI_INTER));
		expedienteInterpretacion.setHoraIni(resultSet.getString(DBConstants.HORA_INI_INTER));
		expedienteInterpretacion.setFechaFin(resultSet.getDate(DBConstants.FECHA_FIN_INTER));
		expedienteInterpretacion.setHoraFin(resultSet.getString(DBConstants.HORA_FIN_INTER));
		expedienteConsulta.setExpedienteInterpretacion(expedienteInterpretacion);

		DatosTareaTrados datosTrados = new DatosTareaTrados();
		datosTrados.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPALTRADOS));
		datosTrados.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084090));
		datosTrados.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594090));
		datosTrados.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100090));
		datosTrados.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599090));
		datosTrados.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100090));
		datosTradRev.setTradosExp(datosTrados);
		expedienteConsulta.setExpedienteTradRev(datosTradRev);

		FilterFactura filterFactura = new FilterFactura();
		filterFactura.setNumEstadoFacturas(resultSet.getString("NUMESTADOFACTURAS"));
		expedienteConsulta.setFilterFactura(filterFactura);
		return expedienteConsulta;
	}

}
