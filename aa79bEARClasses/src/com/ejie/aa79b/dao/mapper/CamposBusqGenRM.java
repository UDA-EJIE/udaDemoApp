package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.GruposTrabajo;
import com.ejie.aa79b.model.PersonalIZO;

public class CamposBusqGenRM implements RowMapper<GruposTrabajo>{

	@Override
	public GruposTrabajo mapRow(ResultSet resultset, int arg1) throws SQLException {
		GruposTrabajo gruposTrabajo = new GruposTrabajo();
		gruposTrabajo.setIdTipoExpediente(resultset.getInt("IDTIPOEXPEDIENTE"));
		gruposTrabajo.setIndBopv(resultset.getString("INDBOPV"));
		gruposTrabajo.setIndPrevistoBopv(resultset.getString("INDPREVBOPV"));
		PersonalIZO responsable = new PersonalIZO();
		responsable.setDni(resultset.getString("DNIRESPOSNABLE"));
		gruposTrabajo.setResponsable(responsable);
		gruposTrabajo.setPalabrasDesde(resultset.getLong("PALABRASDESDE"));
		gruposTrabajo.setPalabrasHasta(resultset.getLong("PALABRASHASTA"));
		return gruposTrabajo;
	}
}