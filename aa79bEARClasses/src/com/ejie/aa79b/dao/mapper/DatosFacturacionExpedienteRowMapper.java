package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.ExpedienteTradRev;

public class DatosFacturacionExpedienteRowMapper implements RowMapper<ExpedienteTradRev> {

	@Override()
	public ExpedienteTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setAnyo(resultSet.getLong("ANYO_097"));
		expedienteTradRev.setNumExp(resultSet.getInt("NUM_EXP_097"));

		DatosFacturacionExpediente datosFacturacionExpediente = new DatosFacturacionExpediente();

		datosFacturacionExpediente.setNumTotalPalFacturar(resultSet.getBigDecimal("NUM_TOTAL_PAL_FACTURAR_097"));
		datosFacturacionExpediente.setNumPalConcor084(resultSet.getBigDecimal("NUM_PAL_CONCOR_0_84_097"));
		datosFacturacionExpediente.setNumPalConcor8594(resultSet.getBigDecimal("NUM_PAL_CONCOR_85_94_097"));
		datosFacturacionExpediente.setNumPalConcor95100(resultSet.getBigDecimal("NUM_PAL_CONCOR_95_100_097"));
		datosFacturacionExpediente.setIdOrden(resultSet.getLong("ID_ORDEN_097"));
		datosFacturacionExpediente.setImporteBase(resultSet.getBigDecimal("IMPORTE_BASE_097"));
		datosFacturacionExpediente.setImporteIva(resultSet.getBigDecimal("IMPORTE_IVA_097"));
		datosFacturacionExpediente.setImporteTotal(resultSet.getBigDecimal("IMPORTE_TOTAL_097"));
		datosFacturacionExpediente.setImporteDificultad(resultSet.getBigDecimal("IMPORTE_DIFICULTAD_097"));
		datosFacturacionExpediente.setImporteUrgencia(resultSet.getBigDecimal("IMPORTE_URGENCIA_097"));
		expedienteTradRev.setDatosFacturacionExpediente(datosFacturacionExpediente);
		return expedienteTradRev;
	}

}
