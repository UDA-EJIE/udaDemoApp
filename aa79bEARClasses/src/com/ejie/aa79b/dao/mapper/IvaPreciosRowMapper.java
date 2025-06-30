package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.IvaPrecios;

/**
 * 
 * @author aresua
 *
 */
public class IvaPreciosRowMapper implements RowMapper<IvaPrecios> {

    @Override()
    public IvaPrecios mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        final IvaPrecios ivaPrecios = new IvaPrecios();
        ivaPrecios.setId(resultSet.getLong("ID"));
        ivaPrecios.setIdOrden(resultSet.getLong("IDORDEN031"));
        ivaPrecios.setFechaDesdeVigencia(resultSet.getDate("FECHADESDEVIGENCIA031"));
        ivaPrecios.setFechaHastaVigencia(resultSet.getDate("FECHAHASTAVIGENCIA031"));
        ivaPrecios.setPorIva(resultSet.getLong("PORIVA031"));
        ivaPrecios.setIndVigente(resultSet.getString("INDVIGENTE031"));

        return ivaPrecios;
    }

}
