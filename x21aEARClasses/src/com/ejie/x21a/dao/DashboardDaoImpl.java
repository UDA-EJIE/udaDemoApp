/*
* Copyright 2012 E.J.I.E., S.A.
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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Dashboard;

/**
 * ProvinciaDaoImpl generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */
 
@Repository
@Transactional
public class DashboardDaoImpl implements DashboardDao {
    private JdbcTemplate jdbcTemplate;
	private RowMapper<Dashboard> rwMap = new RowMapper<Dashboard>() {
		public Dashboard mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Dashboard(
					resultSet.getString("ID"), resultSet.getString("NOMBRE"), resultSet.getString("SERIALIZED_ARRAY"));
		}
	};

	/**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     * @return
     */
    @Resource
    public void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the Provincia table.
     *
     * @param provincia TableRequestDto
     * @return Provincia
     */
	public List<Dashboard> getAll() {
    	String query = "SELECT * FROM DASHBOARD";
		return this.jdbcTemplate.query(query, rwMap);
		
	}

	@Override
	public Dashboard get(Dashboard dashboard) {
		
		List<Object> params = new ArrayList<Object>();
		
		String query = "SELECT * FROM DASHBOARD WHERE ID=?";
		params.add(dashboard.getId());
		List<Dashboard> list = this.jdbcTemplate.query(query, rwMap, params.toArray());
		
		if (list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public Dashboard post(Dashboard dashboard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dashboard put(Dashboard dashboard) {
		String query = "UPDATE DASHBOARD SET NOMBRE=?, SERIALIZED_ARRAY=? WHERE ID=?";
		this.jdbcTemplate.update(query, dashboard.getNombre(), dashboard.getSerializedArray(), dashboard.getId());
		
		return dashboard;
	}
	
	
	

}

