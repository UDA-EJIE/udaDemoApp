package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.SubsanacionExpediente;

public class BitacoraExpedienteRowMapper implements RowMapper<BitacoraExpediente> {

    @Override
    public BitacoraExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
        bitacoraExpediente.setIdEstadoBitacora(resultSet.getLong("ID_ESTADO_BITACORA_059"));
        bitacoraExpediente.setFechaAlta(resultSet.getDate("FECHA_ALTA_059"));
        EstadosExpediente estadoExp = new EstadosExpediente();
        estadoExp.setId(resultSet.getLong("ID_ESTADO_EXP_059"));
        estadoExp.setDescEs(resultSet.getString("ESTADOEXPEDIENTEDESCES"));
        estadoExp.setDescEu(resultSet.getString("ESTADOEXPEDIENTEDESCEU"));
        estadoExp.setDescAbrEs(resultSet.getString("ESTADOEXPEDIENTEDESCABRES"));
        estadoExp.setDescAbrEu(resultSet.getString("ESTADOEXPEDIENTEDESCABREU"));
        estadoExp.setClassStyle(resultSet.getString("ESTADOEXPEDIENTECLASS"));
        bitacoraExpediente.setEstadoExp(estadoExp);
        FasesExpediente faseExp = new FasesExpediente();
        faseExp.setId(resultSet.getLong("ID_FASE_EXPEDIENTE_059"));
        faseExp.setDescEs(resultSet.getString("FASEEXPEDIENTEDESCES"));
        faseExp.setDescEu(resultSet.getString("FASEEXPEDIENTEDESCEU"));
        faseExp.setDescAbrEs(resultSet.getString("FASEEXPEDIENTEDESCABRES"));
        faseExp.setDescAbrEu(resultSet.getString("FASEEXPEDIENTEDESCABREU"));
        bitacoraExpediente.setFaseExp(faseExp);
        bitacoraExpediente.setDatoAdic(resultSet.getString("DATO_ADIC_059"));
        bitacoraExpediente.setInfoAdic(resultSet.getString("INFO_ADIC_059"));
        bitacoraExpediente.setIdMotivoRechazo(resultSet.getLong("ID_RECHAZO_059"));
        SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
        subsanacionExpediente.setId(resultSet.getLong("ID_REQUERIMIENTO_059"));
        subsanacionExpediente.setDetalle(resultSet.getString("DETALLE_064"));
        subsanacionExpediente.setFechaReq(resultSet.getDate("FECHA_REQ_064"));
        subsanacionExpediente.setFechaLimite(resultSet.getDate("FECHA_LIMITE_064"));
        subsanacionExpediente.setIndSubsanado(resultSet.getString("IND_SUBSANADO_064"));
        subsanacionExpediente.setEstado(resultSet.getInt("ESTADO_SUBSANACION_064"));
        subsanacionExpediente.setFechaAceptacion(resultSet.getDate("FECHA_ACEPTACION_064"));
        subsanacionExpediente.setSubsanacionDescEs(resultSet.getString("SUBSANACIONDESCES"));
        subsanacionExpediente.setSubsanacionDescEu(resultSet.getString("SUBSANACIONDESCEU"));
        bitacoraExpediente.setNumAcciones(resultSet.getLong("NUMACCIONES"));

        return bitacoraExpediente;
    }

}
