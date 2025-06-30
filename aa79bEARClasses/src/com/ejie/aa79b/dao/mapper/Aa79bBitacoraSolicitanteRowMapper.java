package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraSolicitante;

/**
 * Aa79bBitacoraSolicitanteRowMapper
 * @author mrodriguez
 */
public class Aa79bBitacoraSolicitanteRowMapper implements RowMapper<BitacoraSolicitante> {
	
	@Override
    public BitacoraSolicitante mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    	BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
    	bitacoraSolicitante.setId(resultSet.getLong(DBConstants.ID));
    	bitacoraSolicitante.setAnyo(resultSet.getLong(DBConstants.ANYO));
    	bitacoraSolicitante.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
    	bitacoraSolicitante.setIdAccionBitacora(resultSet.getLong("IDACCIONBITACORA"));
    	bitacoraSolicitante.setDescAccionBitacoraEu(resultSet.getString(DBConstants.DESCEU));
    	bitacoraSolicitante.setDescAccionBitacoraEs(resultSet.getString(DBConstants.DESCES));
    	bitacoraSolicitante.setClassStyle(resultSet.getString("CLASS"));
    	
    	final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
    	if (fechaAlta != null){
    		bitacoraSolicitante.setFechaAlta(fechaAlta.getTime());
    	}
    	
    	bitacoraSolicitante.setHoraAlta(resultSet.getString("HORAALTA"));
    	bitacoraSolicitante.setUsuario(resultSet.getString("USUARIO"));
    	
        return bitacoraSolicitante;
    }

}
