package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDatosPresupYFactTradRev;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;

public class Aa79bDatosFacturacionTraduccionRowMapper implements RowMapper<Aa79bSalidaDatosPresupuestoFacturacion> {

	@Override
	public Aa79bSalidaDatosPresupuestoFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bSalidaDatosPresupuestoFacturacion datosPresupYFactTrad = new Aa79bSalidaDatosPresupuestoFacturacion();
		// ANYO
		datosPresupYFactTrad.setAnyo(resultSet.getLong(DBConstants.ANYO));
		// NUMEXP
		datosPresupYFactTrad.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		// ANYONUMEXPCONCATENADO
		datosPresupYFactTrad.setAnyoNumExpConcatenado(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
		// TIPOEXPEDIENTE
		Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
		tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		// TIPOEXPEDIENTEDESCES
		tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		// TIPOEXPEDIENTEDESCEU
		tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		datosPresupYFactTrad.setTipoExpediente(tipoExpediente);
		// FECHA ALTA
		datosPresupYFactTrad.setFechaSolicitud(resultSet.getDate(DBConstants.FECHAALTA));
		datosPresupYFactTrad.setHoraSolicitud(resultSet.getString(DBConstants.HORAALTA));
		// IDRELEVANCIA
		Aa79bDatosPresupYFactTradRev datosTradRev = new Aa79bDatosPresupYFactTradRev();
		Aa79bDescripcionIdioma relevancia = new Aa79bDescripcionIdioma();
		relevancia.setId(resultSet.getLong(DBConstants.IDRELEVANCIA));
		// RELEVANCIADESCES
		relevancia.setDescEs(resultSet.getString(DBConstants.RELEVANCIADESCES));
		// RELEVANCIADESCEU
		relevancia.setDescEu(resultSet.getString(DBConstants.RELEVANCIADESCEU));
		datosTradRev.setRelevancia(relevancia);
		// IDIDIOMA
		Aa79bDescripcionIdioma idioma = new Aa79bDescripcionIdioma();
		idioma.setId(resultSet.getLong(DBConstants.IDIDIOMA));
		// IDIOMADESCES
		idioma.setDescEs(resultSet.getString(DBConstants.IDIOMADESCES));
		// IDIOMADESCEU
		idioma.setDescEu(resultSet.getString(DBConstants.IDIOMADESCEU));
		datosTradRev.setIdiomaDestino(idioma);
		// INDPUBLICARBOPV
		Aa79bDescripcionIdioma publicarBOPV = new Aa79bDescripcionIdioma();
		publicarBOPV.setCodigo(resultSet.getString(DBConstants.INDPUBLICARBOPV));
		/* PUBLICARBOPVDESCES */
		publicarBOPV.setDescEs(resultSet.getString(DBConstants.PUBLICARBOPVDESCES));
		/* PUBLICARBOPVDESCEU */
		publicarBOPV.setDescEu(resultSet.getString(DBConstants.PUBLICARBOPVDESCEU));
		datosTradRev.setBopv(publicarBOPV);
		// INDURGENTE
		Aa79bDescripcionIdioma urgente = new Aa79bDescripcionIdioma();
		urgente.setCodigo(resultSet.getString(DBConstants.INDURGENTE));
		/* APREMIODESCES */
		urgente.setDescEs(resultSet.getString(DBConstants.APREMIODESCES));
		/* APREMIODESCEU */
		urgente.setDescEu(resultSet.getString(DBConstants.APREMIODESCEU));
		datosTradRev.setUrgencia(urgente);
		// INDDIFICIL
		Aa79bDescripcionIdioma dificil = new Aa79bDescripcionIdioma();
		dificil.setCodigo(resultSet.getString(DBConstants.INDDIFICIL));
		/* DIFICULTADDESCES */
		dificil.setDescEs(resultSet.getString(DBConstants.DIFICULTADDESCES));
		/* DIFICULTADDESCEU */
		dificil.setDescEu(resultSet.getString(DBConstants.DIFICULTADDESCEU));
		datosTradRev.setDificultad(dificil);
		// TARIFAPAL
		datosTradRev.setTarifaPal(resultSet.getBigDecimal(DBConstants.TARIFAPAL));
		// NUMTOTALPALFACT
		datosTradRev.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPALFACT));
		// NUMPALCONCOR084
		datosTradRev.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		// NUMPALCONCOR8594
		datosTradRev.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		// NUMPALCONCOR95100
		datosTradRev.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosPresupYFactTrad.setDatosTradRev(datosTradRev);
		// PORIVA
		datosPresupYFactTrad.setPorcIva(resultSet.getBigDecimal(DBConstants.PORIVA));
		// IMPORTEBASE
		datosPresupYFactTrad.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		// IMPORTEIVA
		datosPresupYFactTrad.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		// IMPORTETOTAL
		datosPresupYFactTrad.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));

		return datosPresupYFactTrad;
	}

}
