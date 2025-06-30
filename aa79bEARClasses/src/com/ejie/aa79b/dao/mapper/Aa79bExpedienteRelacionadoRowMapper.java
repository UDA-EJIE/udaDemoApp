package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;

public class Aa79bExpedienteRelacionadoRowMapper implements RowMapper<Aa79bExpediente> {

    @Override
    public Aa79bExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Aa79bExpediente aa79bExpediente = new Aa79bExpediente();
        aa79bExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
        aa79bExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
        aa79bExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
        aa79bExpediente.setAnyoNumExp(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));

        final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
        final Date fechaFinalIzo = resultSet.getTimestamp("FECHAFINALIZO");
        if(fechaAlta != null){
        	aa79bExpediente.setFechaSolicitud(fechaAlta.getTime());
        }
        aa79bExpediente.setHoraSolicitud(resultSet.getString("HORAALTA"));
        if(fechaFinalIzo != null){
        	aa79bExpediente.setFechaEntrega(fechaFinalIzo.getTime());
        }
        aa79bExpediente.setHoraSolicitud(resultSet.getString("HORAFINALIZO"));

        return aa79bExpediente;
    }

}
