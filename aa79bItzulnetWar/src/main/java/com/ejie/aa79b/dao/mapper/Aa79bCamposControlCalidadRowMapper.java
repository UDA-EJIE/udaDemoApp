package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.CamposControlCalidad;
import com.ejie.aa79b.model.PesosValoracionAuditoria;
import com.ejie.aa79b.model.SeccionesControlDeCalidad;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bCamposControlCalidadRowMapper implements RowMapper<CamposControlCalidad> {

	@Override
	public CamposControlCalidad mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CamposControlCalidad controlCalidad = new CamposControlCalidad();
		controlCalidad.setIdCampoControlCalidad(resultSet.getLong(DBConstants.ID0C1));
		controlCalidad.setNombreCampo(resultSet.getString("NOMBRECAMPO"));
		controlCalidad.setTipoCampo(resultSet.getLong("TIPOCAMPO"));
		controlCalidad.setTipoCampoEU(resultSet.getString("TIPOCAMPOEU"));
		controlCalidad.setIndVisible(resultSet.getString("VISIBLE"));
		controlCalidad.setIndObservaciones(resultSet.getString("INDOBSERVACIONES"));
		controlCalidad.setIndObligatorio(resultSet.getString("OBLIGATORIO"));
		SeccionesControlDeCalidad seccion = new SeccionesControlDeCalidad();
		seccion.setId(resultSet.getLong("IDSECCION"));
		seccion.setNombreEu(resultSet.getString("SECCIONEU"));
		controlCalidad.setSeccion(seccion);
		PesosValoracionAuditoria peso = new PesosValoracionAuditoria();
		peso.setId(resultSet.getLong("IDPESO"));
		controlCalidad.setPeso(peso);
		controlCalidad.setOrden(resultSet.getLong("ORDEN"));

		return controlCalidad;
	}

}
