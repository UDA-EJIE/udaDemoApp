package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.GruposTrabajo;
import com.ejie.aa79b.model.PersonalIZO;

public class GruposTrabajoRowMapper implements RowMapper<GruposTrabajo> {

	@Override()
	public GruposTrabajo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		GruposTrabajo gruposTrabajo = new GruposTrabajo();
		gruposTrabajo.setId(resultSet.getLong(DBConstants.ID026));
		gruposTrabajo.setIdTipoExpediente(resultSet.getInt("IDTIPOEXPEDIENTE026"));
		gruposTrabajo.setTipoExpedienteDesc(resultSet.getString("TIPOEXPEDIENTEDESC"));
		gruposTrabajo.setDescEu(resultSet.getString("DESCEU026"));
		gruposTrabajo.setIndBopv(resultSet.getString("INDBOPV026"));
		gruposTrabajo.setBopvDesc(resultSet.getString(DBConstants.BOPVDESC));
		gruposTrabajo.setIndPrevistoBopv(resultSet.getString("INDPREVISTOBOPV026"));
		gruposTrabajo.setPrevistoBopvDesc(resultSet.getString(DBConstants.PREVISTOBOPVDESC));
		PersonalIZO responsable = new PersonalIZO();
		responsable.setDni(resultSet.getString(DBConstants.DNI));
		responsable.setNombre(resultSet.getString(DBConstants.NOMBRE));
		responsable.setApellido1(resultSet.getString(DBConstants.APEL1));
		responsable.setApellido2(resultSet.getString(DBConstants.APEL2));
		responsable.setTipIden(resultSet.getInt(DBConstants.TIPIDEN));
		responsable.setPreDni(resultSet.getString(DBConstants.PREDNI));
		responsable.setSufDni(resultSet.getString(DBConstants.SUFDNI));
		gruposTrabajo.setResponsable(responsable);
		gruposTrabajo.setPalabrasDesde(SqlUtils.getLong(resultSet, "PALABRASDESDE026"));
		gruposTrabajo.setPalabrasHasta(SqlUtils.getLong(resultSet, "PALABRASHASTA026"));
		gruposTrabajo.setEstado(resultSet.getString("ESTADO026"));
		gruposTrabajo.setEstadoDesc(resultSet.getString(DBConstants.ESTADODESC));
		gruposTrabajo.setFechaAlta(resultSet.getDate("FECHAALTA026"));
		gruposTrabajo.setFechaBaja(resultSet.getDate("FECHABAJA026"));

		return gruposTrabajo;
	}

}
