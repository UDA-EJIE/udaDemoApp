package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;

public class Aa79bExpedientesRelacionadosRowMapper implements RowMapper<Aa79bExpedienteRelacionado> {

    @Override
    public Aa79bExpedienteRelacionado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Aa79bExpedienteRelacionado expRel = new Aa79bExpedienteRelacionado();
        expRel.setAnyo(resultSet.getLong(DBConstants.ANYO));
        expRel.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
        expRel.setAnyoNumExp(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
        Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
        tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
        tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
        tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
        expRel.setTipoExpediente(tipoExpediente);
        expRel.setTitulo(resultSet.getString(DBConstants.TITULO));
        final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
        final Date fechaFinalIzo = resultSet.getTimestamp("FECHAFINALIZO");
        if(fechaAlta!=null){
        	expRel.setFechaSolicitud(fechaAlta.getTime());
        }
        if(fechaFinalIzo!=null){
        	expRel.setFechaEntrega(fechaFinalIzo.getTime());
        }

        return expRel;
    }

}
