package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.EmpresasProveedoras;

/**
 * EmpresasProveedorasRowMapper, 24-ene-2018 09:18:39.
 */

public class EmpresasProveedorasExtendRowMapper extends EmpresasProveedorasRowMapper {

	@Override()
	public EmpresasProveedoras mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpresasProveedoras bean = new EmpresasProveedoras();

		bean.setTipo(rs.getString(DBConstants.TIPO));
		bean.setCodigo(rs.getLong(DBConstants.CODIGO));
		bean.setDescAmpEs(rs.getString(DBConstants.DESCAMPES));
		bean.setDescAmpEu(rs.getString(DBConstants.DESCAMPEU));
		bean.setDescEs(rs.getString(DBConstants.DESCES));
		bean.setDescEu(rs.getString(DBConstants.DESCEU));
		bean.setCif(rs.getString(DBConstants.CIF));
		bean.setEstado(rs.getString(DBConstants.ESTADO));
		bean.setEstadoDesc(rs.getString(DBConstants.ESTADODESC));
		bean.setFacturable(rs.getString(DBConstants.FACTURABLE));
		bean.setIva(rs.getString(DBConstants.IVA));
		bean.setEsProveedora(rs.getInt("ESPROVEEDORA"));

		Direccion direccion = new Direccion();
		direccion.setDirNora(rs.getInt(DBConstants.CDIRNORA));
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
		bean.setDireccion(direccion);

		return bean;
	}

}
