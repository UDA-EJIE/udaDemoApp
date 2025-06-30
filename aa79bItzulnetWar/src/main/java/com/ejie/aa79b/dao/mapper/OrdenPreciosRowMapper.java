package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.IvaPrecios;
import com.ejie.aa79b.model.OrdenPrecios;

/**
 * 
 * @author aresua
 *
 */
public class OrdenPreciosRowMapper implements RowMapper<OrdenPrecios> {

    @Override()
    public OrdenPrecios mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        final OrdenPrecios ordenPrecios = new OrdenPrecios();
        ordenPrecios.setId(resultSet.getLong("ID"));
        ordenPrecios.setTitulo(resultSet.getString("TITULO030"));
        ordenPrecios.setFechaVigor(resultSet.getDate("FECHAVIGOR030"));
        ordenPrecios.setFechaFinVigor(resultSet.getDate("FECHAFINVIGOR030"));
        ordenPrecios.setIndVigor(resultSet.getString("INDVIGOR030"));
        ordenPrecios.setUrlOrden(resultSet.getString("URLORDEN030"));

        final IvaPrecios ivaPrecios = new IvaPrecios();
        ivaPrecios.setId(resultSet.getLong("IVAID031"));
        ivaPrecios.setFechaDesdeVigencia(resultSet.getDate("IVAFECHADESDEVIGENCIA031"));
        ivaPrecios.setFechaHastaVigencia(resultSet.getDate("IVAFECHAHASTAVIGENCIA031"));
        ivaPrecios.setPorIva(resultSet.getLong("IVAPORIVA031"));
        ivaPrecios.setIndVigente(resultSet.getString("IVAINDVIGENTE031"));

        ordenPrecios.setIvaVigente(ivaPrecios);

        return ordenPrecios;
    }

}
