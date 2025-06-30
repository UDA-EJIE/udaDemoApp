package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosPersona;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Solicitante;

public class DatosPersonaSolicitanteRowMapper implements RowMapper<DatosPersona> {

	@Override
	public DatosPersona mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DatosPersona datosPersona = new DatosPersona();
		datosPersona.setDni(resultSet.getString(DBConstants.DNI));
		datosPersona.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
		datosPersona.setPreDni(resultSet.getString(DBConstants.PREDNI));
		datosPersona.setSufDni(resultSet.getString(DBConstants.SUFDNI));
		datosPersona.setNombre(resultSet.getString(DBConstants.NOMBRE));
		datosPersona.setApellido1(resultSet.getString(DBConstants.APEL1_DATOS_PERSONA));
		datosPersona.setApellido2(resultSet.getString(DBConstants.APEL2_DATOS_PERSONA));

		datosPersona.setRol(Constants.ROL_SOLICITANTE);

		Entidad entidad = new Entidad();
		entidad.setCodigo(resultSet.getInt(DBConstants.CODIGO));
		entidad.setTipo(resultSet.getString(DBConstants.TIPO));
		entidad.setTipoDesc(resultSet.getString(DBConstants.TIPOENTIDADDESCEU));
		Direccion direccion = new Direccion();
		direccion.setDirNora(resultSet.getInt(DBConstants.CDIRNORA));
		entidad.setDireccion(direccion);
		entidad.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPES));
		entidad.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPEU));
		entidad.setDescEs(resultSet.getString(DBConstants.DESCES));
		entidad.setDescEu(resultSet.getString(DBConstants.DESCEU));
		entidad.setCif(resultSet.getString(DBConstants.CIF));
		entidad.setIva(resultSet.getString(DBConstants.IVA));
		entidad.setFacturable(resultSet.getString(DBConstants.FACTURABLE));
		entidad.setEstado(resultSet.getString(DBConstants.ESTADO));

		datosPersona.setEntidad(entidad);

		Solicitante solicitante = new Solicitante();
		solicitante.setGestorExpedientes(resultSet.getString(DBConstants.GESTOREXPEDIENTES));
		solicitante.setGestorExpedientesVIP(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIP));
		solicitante.setGestorExpedientesBOPV(resultSet.getString(DBConstants.GESTOREXPEDIENTESBOPV));
		solicitante.setGestorFacturas(resultSet.getString(DBConstants.GESTORFACTURAS));
		solicitante.setCoordinador(resultSet.getString(DBConstants.COORDINADOR));
		datosPersona.setSolicitante(solicitante);

		return datosPersona;
	}

}
