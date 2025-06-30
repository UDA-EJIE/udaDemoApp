package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Calle;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Localidad;
import com.ejie.aa79b.model.Municipio;
import com.ejie.aa79b.model.Pais;
import com.ejie.aa79b.model.Portal;
import com.ejie.aa79b.model.Provincia;

public class DirNoraRowMapper implements RowMapper<DireccionNora> {

	@Override()
	public DireccionNora mapRow(ResultSet rs, int rowNum) throws SQLException {
		DireccionNora direccionNora = new DireccionNora();

		direccionNora.setTipoNora(rs.getString(DBConstants.TIPONORA));

		Pais pais = new Pais();
		pais.setId(rs.getString(DBConstants.CODPAIS));
		pais.setDsO(rs.getString(DBConstants.PAIS));
		direccionNora.setPais(pais);

		Provincia provincia = new Provincia();
		provincia.setId(rs.getString(DBConstants.CODPROVINCIA));
		provincia.setDsO(rs.getString(DBConstants.PROVINCIA));
		direccionNora.setProvincia(provincia);

		Municipio municipio = new Municipio();
		municipio.setId(rs.getString(DBConstants.CODMUNICIPIO));
		municipio.setDsO(rs.getString(DBConstants.MUNICIPIO));
		direccionNora.setMunicipio(municipio);

		Localidad localidad = new Localidad();
		localidad.setId(rs.getLong(DBConstants.CODLOCALIDAD));
		localidad.setDsO(rs.getString(DBConstants.LOCALIDAD));
		direccionNora.setLocalidad(localidad);

		Calle calle = new Calle();
		calle.setId(rs.getLong(DBConstants.CODCALLE));
		calle.setDsO(rs.getString(DBConstants.CALLE));
		direccionNora.setCalle(calle);

		Portal portal = new Portal();
		portal.setId(rs.getLong(DBConstants.CODPORTAL));
		portal.setTxtPortal(rs.getString(DBConstants.PORTAL));
		direccionNora.setPortal(portal);

		direccionNora.setCodPostal(rs.getString(DBConstants.CODPOSTAL));
		direccionNora.setEscalera(rs.getString(DBConstants.ESCALERA));
		direccionNora.setPiso(rs.getString(DBConstants.PISO));
		direccionNora.setMano(rs.getString(DBConstants.MANO));
		direccionNora.setPuerta(rs.getString(DBConstants.PUERTA));
		direccionNora.setAprox(rs.getString(DBConstants.APROX));
		direccionNora.setDireccion(rs.getString(DBConstants.DIRECCION));
		return direccionNora;
	}

}
