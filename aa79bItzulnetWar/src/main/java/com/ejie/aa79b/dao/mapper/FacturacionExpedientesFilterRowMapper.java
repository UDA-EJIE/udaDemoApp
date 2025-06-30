package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;

public class FacturacionExpedientesFilterRowMapper implements RowMapper<ExpedienteFacturacion> {

	@Override
	public ExpedienteFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ExpedienteFacturacion expedienteFacturacion = new ExpedienteFacturacion();
		expedienteFacturacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expedienteFacturacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expedienteFacturacion.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expedienteFacturacion.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expedienteFacturacion.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expedienteFacturacion.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		expedienteFacturacion.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		expedienteFacturacion.setTitulo(resultSet.getString(DBConstants.TITULO));
		expedienteFacturacion.setPorFactura(resultSet.getInt("PORFACTURA"));
		if (TipoExpedienteEnum.TRADUCCION.getValue().equals(expedienteFacturacion.getIdTipoExpediente())
				|| TipoExpedienteEnum.REVISION.getValue().equals(expedienteFacturacion.getIdTipoExpediente())) {
			// traduccion revision
			ExpedienteTradRev datosTradRev = new ExpedienteTradRev();
			datosTradRev.setIdRelevancia(resultSet.getLong(DBConstants.IDRELEVANCIA));
			datosTradRev.setIndUrgente(resultSet.getString(DBConstants.INDURGENTE));
			datosTradRev.setIndDificil(resultSet.getString(DBConstants.INDDIFICIL));
			datosTradRev.setRelevanciaDescEs(resultSet.getString(DBConstants.DESCRELEVANCIAES));
			datosTradRev.setRelevanciaDescEu(resultSet.getString(DBConstants.DESCRELEVANCIAEU));
			datosTradRev.setIdIdioma(resultSet.getLong(DBConstants.IDIDIOMA));
			datosTradRev.setIdIdiomaDescEs(resultSet.getString(DBConstants.DESCIDIOMAES));
			datosTradRev.setIdIdiomaDescEu(resultSet.getString(DBConstants.DESCIDIOMAEU));
			datosTradRev.setIndPublicadoBoe(resultSet.getString("INDPUBLICADOBOE"));
			datosTradRev.setIndPublicadoBoeDescEs(resultSet.getString("INDPUBLICADOBOEDESCES"));
			datosTradRev.setIndPublicadoBoeDescEu(resultSet.getString("INDPUBLICADOBOEDESCEU"));
			DatosFacturacionExpediente datosFacturacionExpediente = new DatosFacturacionExpediente();
			datosFacturacionExpediente.setTarifaPal(resultSet.getBigDecimal(DBConstants.TARIFAPAL));
			datosFacturacionExpediente.setPorcentajeIva(resultSet.getBigDecimal(DBConstants.PORIVA));
			datosFacturacionExpediente.setNumTotalPalFacturar(resultSet.getBigDecimal(DBConstants.NUMTOTALPAL));
			datosFacturacionExpediente.setNumPalConcor084(resultSet.getBigDecimal(DBConstants.NUMPALCONCOR084));
			datosFacturacionExpediente.setNumPalConcor8594(resultSet.getBigDecimal(DBConstants.NUMPALCONCOR8594));
			datosFacturacionExpediente.setNumPalConcor95100(resultSet.getBigDecimal(DBConstants.NUMPALCONCOR95100));
			datosFacturacionExpediente.setImporteDificultad(resultSet.getBigDecimal(DBConstants.IMPORTEDIFICULTAD));
			datosFacturacionExpediente.setImporteUrgencia(resultSet.getBigDecimal(DBConstants.IMPORTEURGENCIA));
			datosFacturacionExpediente.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
			datosFacturacionExpediente.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
			datosFacturacionExpediente.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
			datosTradRev.setDatosFacturacionExpediente(datosFacturacionExpediente);
			expedienteFacturacion.setExpedienteTradRev(datosTradRev);
		} else {
			// interpretacion
			ExpedienteInterpretacion datosInterpretacion = new ExpedienteInterpretacion();
			datosInterpretacion.setTipoActo(resultSet.getLong(DBConstants.TIPOACTO));
			datosInterpretacion.setTipoActoDescEs(resultSet.getString(DBConstants.TIPOACTODESCES));
			datosInterpretacion.setTipoActoDescEu(resultSet.getString(DBConstants.TIPOACTODESCEU));
			datosInterpretacion.setFechaIni(resultSet.getDate(DBConstants.FECHAINICIO));
			datosInterpretacion.setHoraIni(resultSet.getString(DBConstants.HORAINICIO));
			datosInterpretacion.setFechaFin(resultSet.getDate(DBConstants.FECHAFIN));
			datosInterpretacion.setHoraFin(resultSet.getString(DBConstants.HORAFIN));
			DatosFacturacionExpedienteInterpretacion datosFactInter = new DatosFacturacionExpedienteInterpretacion();
			datosFactInter.setNumInterpretes(resultSet.getInt(DBConstants.NUMINTERPRETES));
			datosFactInter.setHorasInterpretacion(resultSet.getString(DBConstants.HORASINTERPRETACION));
			datosFactInter.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
			datosFactInter.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
			datosFactInter.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
			datosFactInter.setTipoIva(resultSet.getBigDecimal(DBConstants.PORIVA));
			expedienteFacturacion.setExpedienteInterpretacion(datosInterpretacion);
			expedienteFacturacion.setDatosFacturacionInterpretacion(datosFactInter);
		}
		return expedienteFacturacion;
	}

}
