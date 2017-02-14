/*
 * Copyright 2011 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Expediente;

/**
 * * AlumnoDaoImpl generated by UDA, 01-mar-2012 9:33:10.
 * 
 * @author UDA
 */

@Repository
@Transactional
public class ExpedienteDaoImpl implements ExpedienteDao {

	private JdbcTemplate jdbcTemplate;

	private RowMapper<Expediente> rowMapper = new RowMapper<Expediente>() {
		public Expediente mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {

			Expediente expediente = new Expediente();

			expediente.setId(resultSet.getInt("ID"));
			expediente.setTitulo(resultSet.getString("TITULO"));
			expediente.setCodigo(resultSet.getString("CODIGO"));
			expediente.setNombre(resultSet.getString("NOMBRE"));
			expediente.setBuzonTramitador(resultSet.getString("BUZON_TRAMITADOR"));
			expediente.setNombreTramitador(resultSet.getString("NOMBRE_TRAMITADOR"));
			expediente.setEmail(resultSet.getString("EMAIL"));
			expediente.setPregunta(resultSet.getString("PREGUNTA"));
			expediente.setIdFase(resultSet.getInt("ID_FASE"));
			expediente.setNombreFaseEs(resultSet.getString("NOMBRE_FASE_ES"));
			expediente.setNombreFaseEu(resultSet.getString("NOMBRE_FASE_EU"));
			expediente.setFecha(resultSet.getDate("FECHA"));
			expediente.setAlertaBajaFecha(resultSet.getDate("ALERTA_BAJA_FECHA"));
			expediente.setAlertaMediaFecha(resultSet.getDate("ALERTA_MEDIA_FECHA"));
			expediente.setAlertaAltaFecha(resultSet.getDate("ALERTA_ALTA_FECHA"));
			expediente.setIdAlerta(resultSet.getInt("ID_ALERTA"));
			expediente.setNombreAlertaEu(resultSet.getString("NOMBRE_ALERTA_EU"));
			expediente.setNombreAlertaEu(resultSet.getString("NOMBRE_ALERTA_EU"));
			
			
			return expediente;
		}
	};

	/**
	 * Method use to set the datasource.
	 * 
	 * @param dataSource
	 *            DataSource
	 * @return
	 */
	@Resource
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<Expediente> getAll() {

		String query = "SELECT * FROM expediente";
		
		return this.jdbcTemplate.query(query, this.rowMapper);
	}


	@Override
	public Boolean putFase(Expediente expediente) {
		
		String nombreFaseEs ="";
		String nombreFaseEu ="";
		
		switch (expediente.getIdFase()) {
		case 501:
			nombreFaseEs = "Gestión";
			nombreFaseEu = "Kudeaketa";
			break;
		case 504:
			nombreFaseEs = "Cierre";
			nombreFaseEu = "Itxiera";
			break;
		case 506:
			nombreFaseEs = "Derivación";
			nombreFaseEu = "Desbideratzea";
			break;
		default:
			break;
		}
		
		String query = "UPDATE expediente SET ID_FASE=?, NOMBRE_FASE_ES=?, NOMBRE_FASE_EU=? WHERE ID=?";

		
		
		return this.jdbcTemplate.update(query, expediente.getIdFase(), nombreFaseEs, nombreFaseEu, expediente.getId())>0;
	}

	
	
	
}