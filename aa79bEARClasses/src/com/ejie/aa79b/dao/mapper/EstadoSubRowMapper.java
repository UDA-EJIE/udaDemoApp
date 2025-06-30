package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.SubsanacionExpediente;

public class EstadoSubRowMapper implements RowMapper<Expediente> {

	@Override
	public Expediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Expediente expediente = new Expediente();
		expediente.setAnyo(resultSet.getLong("ANYO051"));
		expediente.setNumExp(resultSet.getInt("NUMEXP051"));
		FasesExpediente faseExp = new FasesExpediente();
		faseExp.setId(resultSet.getLong("IDFASEEXPEDIENTE059"));
		EstadosExpediente estadoExp = new EstadosExpediente();
		estadoExp.setId(resultSet.getLong("IDESTADOEXP059"));
		SubsanacionExpediente subExp = new SubsanacionExpediente();
		subExp.setId(resultSet.getLong("ID064"));
		subExp.setDetalle(resultSet.getString("DETALLE064"));
		subExp.setIndSubsanado(resultSet.getString("INDSUBSANADO064"));
		subExp.setFechaLimite(resultSet.getDate("FECHALIMITE064"));
		subExp.setHoraLimite(resultSet.getString("HORALIMITE064"));
		subExp.setEstado(resultSet.getInt("ESTADOSUBSANACION064"));
		BitacoraExpediente bitacora = new BitacoraExpediente();
		bitacora.setEstadoExp(estadoExp);
		bitacora.setFaseExp(faseExp);
		bitacora.setSubsanacionExp(subExp);
		expediente.setBitacoraExpediente(bitacora);
		return expediente;
	}

}
