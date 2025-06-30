package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Calle;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Localidad;
import com.ejie.aa79b.model.Municipio;
import com.ejie.aa79b.model.Pais;
import com.ejie.aa79b.model.Portal;
import com.ejie.aa79b.model.Provincia;
import com.ejie.aa79b.model.enums.TipoNoraEnum;

/**
 * @author mrodriguez
 *
 */
public class DireccionNoraRowMapper implements RowMapper<DireccionNora> {

    @Override()
    public DireccionNora mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DireccionNora direccion = new DireccionNora();
        direccion.setDirNora(resultSet.getInt(DBConstants.CDIRNORA));
        direccion.setTipoNora(resultSet.getString(DBConstants.TIPONORA));
        final Pais pais = new Pais();
        pais.setId(resultSet.getString("CODPAIS"));
        direccion.setPais(pais);
        direccion.setCodPostal(resultSet.getString("CODPOST"));

        String codLocal = resultSet.getString("CODLOCAL");

        final Provincia provincia = new Provincia();
        final Municipio municipio = new Municipio();
        final Localidad localidad = new Localidad();
        final Portal portal = new Portal();
        final Calle calle = new Calle();

        final String aprox = resultSet.getString("APROX");
        if (TipoNoraEnum.CERO.getValue().equals(direccion.getTipoNora())) {
            provincia.setId(resultSet.getString("CODPROV"));
            direccion.setProvincia(provincia);
            municipio.setId(resultSet.getString("CODMUNI"));
            direccion.setMunicipio(municipio);

            if (StringUtils.isNotBlank(codLocal)) {
                localidad.setId(Long.valueOf(codLocal));
                direccion.setLocalidad(localidad);
            }
            Long codNora = resultSet.getLong("CODNORA");
            if (codNora != 0) {
                portal.setId(codNora);
                direccion.setPortal(portal);
            }
            direccion.setEscalera(resultSet.getString("ESCALER"));
            direccion.setPiso(resultSet.getString(DBConstants.PISO));
            direccion.setMano(resultSet.getString(DBConstants.MANO));
            direccion.setPuerta(resultSet.getString(DBConstants.PUERTA));
            direccion.setAprox(aprox);
        } else if (TipoNoraEnum.UNO.getValue().equals(direccion.getTipoNora())
                || TipoNoraEnum.DOS.getValue().equals(direccion.getTipoNora())) {

            provincia.setId(resultSet.getString("CODPROV"));
            direccion.setProvincia(provincia);
            municipio.setId(resultSet.getString("CODMUNI"));
            direccion.setMunicipio(municipio);
            if (StringUtils.isNotBlank(codLocal)) {
                localidad.setId(Long.valueOf(codLocal));
                direccion.setLocalidad(localidad);
            }
            portal.setTxtPortal(resultSet.getString("TXTPORTAL"));
            direccion.setPortal(portal);
            calle.setDsO(resultSet.getString("TXTCALLE"));
            direccion.setCalle(calle);

            direccion.setEscalera(resultSet.getString("ESCALER"));
            direccion.setPiso(resultSet.getString(DBConstants.PISO));
            direccion.setMano(resultSet.getString(DBConstants.MANO));
            direccion.setPuerta(resultSet.getString(DBConstants.PUERTA));
            direccion.setAprox(aprox);
        } else if (TipoNoraEnum.TRES.getValue().equals(direccion.getTipoNora())) {
            provincia.setDsO(resultSet.getString("TXTPROV"));
            direccion.setProvincia(provincia);
            localidad.setDsO(resultSet.getString("TXTCIUDAD"));
            direccion.setLocalidad(localidad);
            calle.setDsO(resultSet.getString("TXTDIREC"));
            direccion.setCalle(calle);
        } else {
            direccion.setAprox(aprox);
        }

        return direccion;
    }

}
