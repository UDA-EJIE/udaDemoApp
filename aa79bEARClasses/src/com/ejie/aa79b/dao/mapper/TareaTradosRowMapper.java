package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.IdiomaDestino;
import com.ejie.aa79b.model.TareaTrados;
import com.ejie.aa79b.model.TipoRelevancia;
import com.ejie.aa79b.utils.Utils;

public class TareaTradosRowMapper implements RowMapper<TareaTrados> {

	@Override
	public TareaTrados mapRow(ResultSet resultSet, int arg1) throws SQLException {
		TareaTrados tareaTrados = new TareaTrados();
		tareaTrados.setAnyo(resultSet.getLong(DBConstants.ANYO));
		tareaTrados.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		tareaTrados.setNumExpFormated(Utils.getAnyoNumExpConcatenado(tareaTrados.getAnyo(), tareaTrados.getNumExp()));
		tareaTrados.setTitulo(resultSet.getString(DBConstants.TITULO));
		tareaTrados.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		tareaTrados.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		tareaTrados.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		tareaTrados.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		tareaTrados.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		IdiomaDestino idioma = new IdiomaDestino();
		idioma.setIdIdioma(resultSet.getLong(DBConstants.IDIDIOMA));
		idioma.setCodIdioma(resultSet.getString(DBConstants.CODIDIOMA));
		idioma.setDescIdiomaEs(resultSet.getString(DBConstants.DESCIDIOMAES));
		idioma.setDescIdiomaEu(resultSet.getString(DBConstants.DESCIDIOMAEU));
		tareaTrados.setIdioma(idioma);
		TipoRelevancia relevancia = new TipoRelevancia();
		relevancia.setIdTipoRelevancia(resultSet.getLong(DBConstants.IDRELEVANCIA));
		relevancia.setDescRelevanciaEs(resultSet.getString(DBConstants.DESCRELEVANCIAES));
		relevancia.setDescRelevanciaEu(resultSet.getString(DBConstants.DESCRELEVANCIAEU));
		relevancia.setPrioridad(resultSet.getLong(DBConstants.PRIORIDADRELEVANCIA));
		tareaTrados.setRelevancia(relevancia);
		tareaTrados.setIndPublicarBopv(resultSet.getString(DBConstants.INDPUBLICARBOPV));
		tareaTrados.setPublicarBopvDescEs(resultSet.getString(DBConstants.PUBLICARBOPVDESCES));
		tareaTrados.setPublicarBopvDescEu(resultSet.getString(DBConstants.PUBLICARBOPVDESCEU));
		tareaTrados.setIndPresupuesto(resultSet.getString(DBConstants.INDPRESUPUESTO));
		tareaTrados.setFechaLimite(resultSet.getDate(DBConstants.FECHALIMITE));
		tareaTrados.setHoraLimite(resultSet.getString(DBConstants.HORALIMITE));
		tareaTrados.setIndUrgente(resultSet.getString(DBConstants.INDURGENTE));
		tareaTrados.setUrgenteDescEs(resultSet.getString(DBConstants.URGENTEDESCES));
		tareaTrados.setUrgenteDescEu(resultSet.getString(DBConstants.URGENTEDESCEU));
		tareaTrados.setIndDificultad(resultSet.getString(DBConstants.INDDIFICIL));
		tareaTrados.setDificultadDescEs(resultSet.getString(DBConstants.DIFICILDESCES));
		tareaTrados.setDificultadDescEu(resultSet.getString(DBConstants.DIFICILDESCEU));
		tareaTrados.setNumPalSolic(resultSet.getInt(DBConstants.NUMPALSOLIC));
		tareaTrados.setNumPalIzo(resultSet.getInt(DBConstants.NUMPALIZO));
		tareaTrados.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
		tareaTrados.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
		tareaTrados.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		tareaTrados.setIdRequerimiento(resultSet.getLong(DBConstants.IDREQUERIMIENTO));
		DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
		datosTareaTrados.setIdFicheroTrados(resultSet.getBigDecimal(DBConstants.IDFICHEROTRADOS));
		datosTareaTrados.setNombreFicheroTrados(resultSet.getString(DBConstants.NOMBREFICHERO));
		datosTareaTrados.setExtensionFicheroTrados(resultSet.getString(DBConstants.EXTENSIONFICHERO));
		datosTareaTrados.setIndEncriptadoFicheroTrados(resultSet.getString(DBConstants.INDENCRIPTADO));
		datosTareaTrados.setOidFicheroTrados(resultSet.getString(DBConstants.OIDFICHERO));
		datosTareaTrados.setIndVisible(resultSet.getString(DBConstants.INDVISIBLE));
		datosTareaTrados.setPresupuesto(resultSet.getBigDecimal(DBConstants.PRESUPUESTO));
		datosTareaTrados.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosTareaTrados.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosTareaTrados.setPorcentajeIva(resultSet.getLong(DBConstants.PORIVA));
		datosTareaTrados.setFechaEntrega(resultSet.getDate(DBConstants.FECHAENTREGA));
		datosTareaTrados.setHoraEntrega(resultSet.getString(DBConstants.HORAENTREGA));
		datosTareaTrados.setTarifaPal(resultSet.getBigDecimal(DBConstants.TARIFAPAL));
		datosTareaTrados.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPAL));
		datosTareaTrados.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosTareaTrados.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosTareaTrados.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosTareaTrados.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599));
		datosTareaTrados.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100));
		datosTareaTrados.setNombreProyecto(resultSet.getString(DBConstants.NOMBREPROYECTO));
		datosTareaTrados.setFechaProyecto(resultSet.getDate(DBConstants.FECHAPROYECTO));
		datosTareaTrados.setHoraProyecto(resultSet.getString(DBConstants.HORAPROYECTO));
		Integer iPalTrados = resultSet.getInt(DBConstants.PALTRADOS); // cambiado por palTrados
		if (iPalTrados != null) {
			// comprobacion para saber si es posible negociar nueva fecha
			// entrega IZO
			tareaTrados.setPosibleNegociarNuevaFechaEntregaIzo(tareaTrados.getNumPalIzo() > iPalTrados);
		}
		if (datosTareaTrados.getNumTotalPal() != null && tareaTrados.getNumPalIzo() != null) {
			// comprobacion de si, el num palabras extraido del xml es mayor que
			// el num palabras definido por el IZO,
			// si esa diferencia esta por encima o no del desfase asumible
			tareaTrados.setPalXmlSupPalIzoSupDesfaseAsumible(
					Utils.comprobarDesfaseAsumible(datosTareaTrados.getNumTotalPal(), tareaTrados.getNumPalIzo(),
							resultSet.getInt(DBConstants.PORDESVIACIONPAL)));
		}
		tareaTrados.setDatosTareaTrados(datosTareaTrados);
		return tareaTrados;
	}
}
