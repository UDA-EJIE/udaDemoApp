package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.utils.Utils;

public class ExpedientesCambioEstadoRowMapper implements RowMapper<Expediente> {

	@Override
	public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Expediente expediente = new Expediente();
		expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expediente.setAnyoNumExpConcatenado(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
		expediente.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expediente.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTE));
		expediente.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTE));
		expediente.setTitulo(resultSet.getString(DBConstants.TITULO));

		// ExpedienteTradRev
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setFechaFinalSolic(resultSet.getDate(DBConstants.FECHAFINAL));
		expedienteTradRev.setHoraFinalSolic(resultSet.getString(DBConstants.HORAFINAL));
		expediente.setExpedienteTradRev(expedienteTradRev);

		// Bitacora
		BitacoraExpediente bitacora = new BitacoraExpediente();
		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(resultSet.getLong(DBConstants.IDESTADOEXP));
		estado.setDescEu(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCEU));
		estado.setDescAbrEu(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCABREU));
		bitacora.setEstadoExp(estado);
		FasesExpediente fase = new FasesExpediente();
		fase.setId(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		fase.setDescEu(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCEU));
		fase.setDescAbrEu(resultSet.getString(DBConstants.FASEEXPEDIENTEDESCABREU));
		bitacora.setFaseExp(fase);
		expediente.setBitacoraExpediente(bitacora);

		// Gestor expediente
		Gestor gestorExpediente = new Gestor();
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(resultSet.getString(DBConstants.DNI));
		solicitante.setPreDni(resultSet.getString(DBConstants.PREDNI));
		solicitante.setSufDni(resultSet.getString(DBConstants.SUFDNI));
		solicitante.setDniCompleto(resultSet.getString(DBConstants.DNICOMPLETO));
		solicitante.setGestorExpedientesVIP(resultSet.getString(DBConstants.INDVIP));
		solicitante.setGestorExpedientesVIPDescEu(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCEU));
		solicitante.setNombre(resultSet.getString(DBConstants.NOMBRE));
		solicitante.setApellido1(resultSet.getString(DBConstants.APEL1));
		solicitante.setApellido2(resultSet.getString(DBConstants.APEL2));
		gestorExpediente.setSolicitante(solicitante);
		Entidad entidad = new Entidad();
		entidad.setTipo(resultSet.getString(DBConstants.TIPOENTIDAD));
		entidad.setTipoDesc(Utils.getTipoEntidadDescLabel(entidad.getTipo()));
		entidad.setCodigo(resultSet.getInt(DBConstants.IDENTIDAD));
		if (null != resultSet.getString(DBConstants.CIF)) {
			entidad.setCif(resultSet.getString(DBConstants.CIF));
		}
		entidad.setDescEs(resultSet.getString(DBConstants.DESCENTIDADES));
		entidad.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEU));
		entidad.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPENTIDADES));
		entidad.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPENTIDADEU));
		gestorExpediente.setEntidad(entidad);
		expediente.setGestorExpediente(gestorExpediente);

		return expediente;
	}

}
