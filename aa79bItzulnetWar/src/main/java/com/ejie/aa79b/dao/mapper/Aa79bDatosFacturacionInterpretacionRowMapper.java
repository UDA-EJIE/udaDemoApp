package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDatosPresupYFactInter;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;

public class Aa79bDatosFacturacionInterpretacionRowMapper implements RowMapper<Aa79bSalidaDatosPresupuestoFacturacion> {

	@Override
	public Aa79bSalidaDatosPresupuestoFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bSalidaDatosPresupuestoFacturacion datosPresupYFactInter = new Aa79bSalidaDatosPresupuestoFacturacion();
		// ANYO
		datosPresupYFactInter.setAnyo(resultSet.getLong(DBConstants.ANYO));
		// NUMEXP
		datosPresupYFactInter.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		// ANYONUMEXPCONCATENADO
		datosPresupYFactInter.setAnyoNumExpConcatenado(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
		// TIPOEXPEDIENTE
		Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
		tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		datosPresupYFactInter.setTipoExpediente(tipoExpediente);
		// FECHA ALTA
		datosPresupYFactInter.setFechaSolicitud(resultSet.getDate(DBConstants.FECHAALTA));
		datosPresupYFactInter.setHoraSolicitud(resultSet.getString(DBConstants.HORAALTA));
		// TIPOACTO
		Aa79bDatosPresupYFactInter datosInter = new Aa79bDatosPresupYFactInter();
		Aa79bDescripcionIdioma tipoActo = new Aa79bDescripcionIdioma();
		tipoActo.setId(resultSet.getLong(DBConstants.TIPOACTO));
		tipoActo.setDescEs(resultSet.getString(DBConstants.TIPOACTODESCES));
		tipoActo.setDescEu(resultSet.getString(DBConstants.TIPOACTODESCEU));
		datosInter.setTipoActo(tipoActo);
		// PROGRAMADA
		Aa79bDescripcionIdioma programada = new Aa79bDescripcionIdioma();
		programada.setCodigo(resultSet.getString(DBConstants.INDPROGRAMADA));
		programada.setDescEs(resultSet.getString(DBConstants.INDPROGRAMADADESCES));
		programada.setDescEu(resultSet.getString(DBConstants.INDPROGRAMADADESCEU));
		datosInter.setProgramada(programada);
		// ENCAE
		Aa79bDescripcionIdioma enCAE = new Aa79bDescripcionIdioma();
		enCAE.setDescEs(resultSet.getString(DBConstants.ESCAEDESCES));
		enCAE.setDescEu(resultSet.getString(DBConstants.ESCAEDESCEU));
		datosInter.setEnCAE(enCAE);
		datosInter.setNumInterpretes(resultSet.getInt(DBConstants.NUMINTERPRETES));
		datosInter.setHorasInterpretacion(resultSet.getString(DBConstants.HORASINTERPRETACION));
		datosPresupYFactInter.setDatosInter(datosInter);
		datosPresupYFactInter.setPorcIva(resultSet.getBigDecimal(DBConstants.PORIVA));
		datosPresupYFactInter.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosPresupYFactInter.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosPresupYFactInter.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		return datosPresupYFactInter;
	}

}
