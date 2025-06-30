package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;

public class EntidadExtendRowMapper extends EntidadSolicitanteRowMapper {

	@Override()
	public Entidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		Entidad bean = super.mapRow(rs, rowNum);

		Direccion direccion = bean.getDireccion();
		direccion.setTipoNora(rs.getString(DBConstants.TIPONORA));
		direccion.setTxtCalle(rs.getString(DBConstants.CALLE));
		direccion.setTxtPortal(rs.getString(DBConstants.PORTAL));
		direccion.setEscalera(rs.getString(DBConstants.ESCALERA));
		direccion.setPiso(rs.getString(DBConstants.PISO));
		direccion.setMano(rs.getString(DBConstants.MANO));
		direccion.setPuerta(rs.getString(DBConstants.PUERTA));
		direccion.setCodPostal(rs.getString(DBConstants.CODPOSTAL));
		direccion.setTxtLocalidad(rs.getString(DBConstants.LOCALIDAD));
		direccion.setCodMunicipio(rs.getString(DBConstants.CODMUNICIPIO));
		direccion.setTxtMunicipio(rs.getString(DBConstants.MUNICIPIO));
		direccion.setCodProvincia(rs.getString(DBConstants.CODPROVINCIA));
		direccion.setTxtProvincia(rs.getString(DBConstants.PROVINCIA));
		direccion.setTxtPais(rs.getString(DBConstants.PAIS));
		direccion.setDireccion(rs.getString(DBConstants.DIRECCION));
		bean.setDireccion(direccion);

		return bean;
	}

}
