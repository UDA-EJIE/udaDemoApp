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
package com.ejie.x21a.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import n38c.exe.N38API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.w3c.dom.Document;

import com.ejie.x38.security.UdaCustomJdbcDaoImpl;


/**
 * 
 * @author UDA
 *
 */
public class SecurityCustomJdbcDaoImpl extends UdaCustomJdbcDaoImpl {	
	
	private static final Logger logger = LoggerFactory
	.getLogger(SecurityCustomJdbcDaoImpl.class);
	
	protected String loadUserPosition(String userName, String dni, N38API n38Api, Document xmlSesion){
		
		List<String> result = getJdbcTemplate().query(this.getPositionByUserdataQuery(), new String[] { userName.toUpperCase(), dni.toUpperCase() }, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		
		if(!(result.size() == 1)){
			IllegalArgumentException exc = new IllegalArgumentException("loadUserPosition(): Revise su base de datos. El valor de puesto, asociado a un usuario, debe ser único.");
			logger.error("loadUserPosition(): Revise su base de datos. El valor de puesto, asociado a un usuario, debe ser único.", exc);
			throw exc;
		}
		
		return result.get(0);
	}
	
	protected Vector<String> loadUserAuthorities(String userName, String dni, N38API n38Api, Document xmlSesion){
		
		return new Vector<String>(getJdbcTemplate().query(this.getAuthoritiesByUserdataQuery(), new String[] { userName.toUpperCase(), dni.toUpperCase() }, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		}));
	}
	
	
			
}