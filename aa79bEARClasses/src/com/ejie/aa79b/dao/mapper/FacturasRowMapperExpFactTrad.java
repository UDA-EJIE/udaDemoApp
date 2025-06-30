package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDatosFacturacionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bDatosTradRev;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;

public class FacturasRowMapperExpFactTrad implements RowMapper<Aa79bExpediente> {

	@Override
	public Aa79bExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Aa79bExpediente expediente = new Aa79bExpediente();

		// Expediente
		expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
		tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expediente.setTipoExpediente(tipoExpediente);

		// Expediente de traducción/revisión
		Aa79bDatosTradRev datosTradRev = new Aa79bDatosTradRev();

		datosTradRev.setIdRelevancia(resultSet.getLong(DBConstants.IDRELEVANCIA));
		datosTradRev.setRelevanciaDescEs(resultSet.getString(DBConstants.DESCRELEVANCIAES));
		datosTradRev.setRelevanciaDescEu(resultSet.getString(DBConstants.DESCRELEVANCIAEU));
		Aa79bDescripcionIdioma idioma = new Aa79bDescripcionIdioma();
		idioma.setId(resultSet.getLong(DBConstants.IDIDIOMA));
		datosTradRev.setIdioma(idioma);
		datosTradRev.setDescIdiomaEs(resultSet.getString(DBConstants.DESCIDIOMAES));
		datosTradRev.setDescIdiomaEu(resultSet.getString(DBConstants.DESCIDIOMAEU));

		// Datos facturación del expediente
		Aa79bDatosFacturacionExpediente datosFacturacionExpediente = new Aa79bDatosFacturacionExpediente();
		datosFacturacionExpediente.setTarifaPal(resultSet.getBigDecimal(DBConstants.TARIFAPAL));
		datosFacturacionExpediente.setNumTotalPalFacturar(resultSet.getInt(DBConstants.NUMTOTALPALFACTURAR));
		datosFacturacionExpediente.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosFacturacionExpediente.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosFacturacionExpediente.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosFacturacionExpediente.setImporteDificultad(resultSet.getBigDecimal(DBConstants.IMPORTEDIFICULTAD));
		datosFacturacionExpediente.setImporteUrgencia(resultSet.getBigDecimal(DBConstants.IMPORTEURGENCIA));
		datosFacturacionExpediente.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosFacturacionExpediente.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosFacturacionExpediente.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		datosTradRev.setDatosFacturacion(datosFacturacionExpediente);
		expediente.setDatosTradRev(datosTradRev);

		// Gestor del expediente
		Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();
		gestor.setNombreRepresentante(resultSet.getString(DBConstants.NOMBRESOLICITANTE));
		gestor.setApellido1Representante(resultSet.getString(DBConstants.APEL1SOLICITANTE));
		gestor.setApellido2Representante(resultSet.getString(DBConstants.APEL2SOLICITANTE));
		expediente.setGestor(gestor);

		return expediente;
	}

}
