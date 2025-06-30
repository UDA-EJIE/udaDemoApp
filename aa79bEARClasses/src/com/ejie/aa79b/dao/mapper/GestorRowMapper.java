package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.PersonalIZO;

public class GestorRowMapper implements RowMapper<PersonalIZO> {

	@Override
	public PersonalIZO mapRow(ResultSet resultSet, int arg1) throws SQLException {
		PersonalIZO personalIzo = new PersonalIZO();
		personalIzo.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
		personalIzo.setPreDni(resultSet.getString(DBConstants.PREDNI));
		personalIzo.setDni(resultSet.getString(DBConstants.DNI));
		personalIzo.setSufDni(resultSet.getString(DBConstants.SUFDNI));
		personalIzo.setNombre(resultSet.getString(DBConstants.NOMBRE));
		personalIzo.setApellido1(resultSet.getString(DBConstants.APEL1));
		personalIzo.setApellido2(resultSet.getString(DBConstants.APEL2));
		personalIzo.setEstado(resultSet.getString(DBConstants.ESTADO));

		Entidad entidad = personalIzo.getEntidad();
		entidad.setTipo(resultSet.getString(DBConstants.TIPOENTIDAD));
		entidad.setCodigo(resultSet.getInt(DBConstants.CODENTIDAD));
		entidad.setDescEs(resultSet.getString(DBConstants.DESCES));
		entidad.setDescEu(resultSet.getString(DBConstants.DESCEU));
		entidad.setCif(resultSet.getString(DBConstants.CIF));

		personalIzo.setTecnicoGestor(resultSet.getString("TECNICOGESTOR"));
		personalIzo.setAsignador(resultSet.getString("ASIGNADOR"));
		personalIzo.setTraductor(resultSet.getString("TRADUCTOR"));
		personalIzo.setInterprete(resultSet.getString("INTERPRETE"));
		personalIzo.setGestorBopv(resultSet.getString(DBConstants.GESTORBOPV));
		personalIzo.setGestorExpedientesVIP(resultSet.getString(DBConstants.GESTOR_EXPEDIENTES_VIP));

		return personalIzo;
	}

}
