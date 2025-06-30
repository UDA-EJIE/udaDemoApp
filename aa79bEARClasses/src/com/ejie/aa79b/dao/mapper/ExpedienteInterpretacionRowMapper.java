package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteInterpretacion;

/**
 * @author mrodriguez
 *
 */
public class ExpedienteInterpretacionRowMapper implements RowMapper<Expediente> {
		
	@Override()
	public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Expediente expediente = new Expediente();
		expediente.setAnyo(resultSet.getLong("ANYO_052"));
		expediente.setNumExp(resultSet.getInt("NUM_EXP_052"));

		ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();

		expedienteInterpretacion.setModoInterpretacion(resultSet.getLong("MODO_INTERPRETACION_052"));
		expedienteInterpretacion.setTipoActo(resultSet.getLong("TIPO_ACTO_052"));
		expedienteInterpretacion.setTipoPeticionario(resultSet.getString("TIPO_PETICIONARIO_052"));
		expedienteInterpretacion.setIndProgramada(resultSet.getString("IND_PROGRAMADA_052"));
		expedienteInterpretacion.setFechaIni(resultSet.getDate("FECHA_INI_052"));
		expedienteInterpretacion.setHoraIni(resultSet.getString("HORA_INI_052"));
		expedienteInterpretacion.setFechaFin(resultSet.getDate("FECHA_FIN_052"));
		expedienteInterpretacion.setHoraFin(resultSet.getString("HORA_FIN_052"));

		DireccionNora direccionNora = new DireccionNora();
		direccionNora.setDirNora(resultSet.getInt("DIR_NORA_052"));
		expedienteInterpretacion.setDirNora(direccionNora);

		expedienteInterpretacion.setIndPresupuesto(resultSet.getString("IND_PRESUPUESTO_052"));
		expedienteInterpretacion.setPresupuestoDescEs(resultSet.getString("PRESUPUESTODESCES"));
		expedienteInterpretacion.setPresupuestoDescEu(resultSet.getString("PRESUPUESTODESCEU"));
		expedienteInterpretacion.setPersonaContacto(resultSet.getString("PERSONA_CONTACTO_052"));
		expedienteInterpretacion.setEmailContacto(resultSet.getString("EMAIL_CONTACTO_052"));
		expedienteInterpretacion.setTelefonoContacto(resultSet.getString("TELEFONO_CONTACTO_052"));
		expedienteInterpretacion.setIndObservaciones(resultSet.getString("IND_OBSERVACIONES_052"));

		expediente.setExpedienteInterpretacion(expedienteInterpretacion);

		return expediente;
	}

}
