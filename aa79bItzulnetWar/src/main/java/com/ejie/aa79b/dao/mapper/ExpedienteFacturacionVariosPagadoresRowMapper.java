package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;

public class ExpedienteFacturacionVariosPagadoresRowMapper implements RowMapper<ExpedienteFacturacion> {

	@Override
	public ExpedienteFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ExpedienteFacturacion expFacturacion = new ExpedienteFacturacion();
		expFacturacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expFacturacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expFacturacion.setIdTipoExpediente(resultSet.getString(DBConstants.TIPOEXPEDIENTE));
		// entidad
		Entidad entidad = new Entidad();
		entidad.setTipo(resultSet.getString(DBConstants.TIPOENTIDAD));
		entidad.setCodigo(resultSet.getInt(DBConstants.IDENTIDAD));
		entidad.setDescEs(resultSet.getString(DBConstants.DESCENTIDADES));
		entidad.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEU));
		entidad.setFacturable(resultSet.getString(DBConstants.FACTURABLE));

		Gestor gestor = new Gestor();
		gestor.setEntidad(entidad);
		expFacturacion.setEntidadContactoFactura(gestor);

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expFacturacion.getIdTipoExpediente())) {
			// interpretacion
			DatosFacturacionExpedienteInterpretacion datosFacturacionInterpretacion = new DatosFacturacionExpedienteInterpretacion();
			datosFacturacionInterpretacion.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
			datosFacturacionInterpretacion.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
			datosFacturacionInterpretacion.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
			datosFacturacionInterpretacion
					.setPorcentajeFacturacion(resultSet.getBigDecimal(DBConstants.PORCENTAJEFACTURACION));
			expFacturacion.setDatosFacturacionInterpretacion(datosFacturacionInterpretacion);

		} else {
			// trad/rev
			ExpedienteTradRev expTradRev = new ExpedienteTradRev();
			DatosFacturacionExpediente datosFacturacion = new DatosFacturacionExpediente();
			datosFacturacion.setPorcentajeFacturacion(resultSet.getBigDecimal(DBConstants.PORCENTAJEFACTURACION));
			datosFacturacion.setNumTotalPalFacturar(resultSet.getBigDecimal(DBConstants.NUMTOTALPAL));
			datosFacturacion.setNumPalConcor084(resultSet.getBigDecimal(DBConstants.NUMPALCONCOR084));
			datosFacturacion.setNumPalConcor8594(resultSet.getBigDecimal(DBConstants.NUMPALCONCOR8594));
			datosFacturacion.setNumPalConcor95100(resultSet.getBigDecimal(DBConstants.NUMPALCONCOR95100));
			datosFacturacion.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
			datosFacturacion.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
			datosFacturacion.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
			expTradRev.setDatosFacturacionExpediente(datosFacturacion);
			expFacturacion.setExpedienteTradRev(expTradRev);
		}
		expFacturacion.setIndIva(resultSet.getString(DBConstants.IVA));

		return expFacturacion;
	}

}
