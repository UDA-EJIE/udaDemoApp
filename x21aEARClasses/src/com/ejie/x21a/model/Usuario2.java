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
package com.ejie.x21a.model;

import java.util.Date;

import org.hdiv.services.SecureIdContainer;
import org.hdiv.services.TrustAssertion;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Usuario2 extends Usuario implements SecureIdContainer {

	private static final long serialVersionUID = 1L;
	
	// Añadido para que no falle la obtención del campo por instrospección en el método getCampoByIntrospection de la clase TableManager (x38).
	@TrustAssertion(idFor = Usuario2.class)  
	private String id;

	/**
	 * Method 'Usuario2'.
	 *
	 */
	public Usuario2() { }

	/**
	 * Method 'Usuario2'.
	 * 
	 * @param id
	 */
	public Usuario2(String id) {
		super(id);
	}
	
	/**
	 * Method 'Usuario2'.
	 * 
	 * @param id        String
	 * @param nombre    String
	 * @param apellido1 String
	 * @param apellido2 String
	 * @param ejie      String
	 * @param fechaAlta Date
	 * @param fechaBaja Date
	 */
	public Usuario2(String id, String nombre, String apellido1, String apellido2, String ejie, Date fechaAlta, Date fechaBaja) {
		super(id, nombre, apellido1, apellido2, ejie, fechaAlta, fechaBaja, null);
	}

	/**
	 * Method 'Usuario2'.
	 * 
	 * @param id        String
	 * @param nombre    String
	 * @param apellido1 String
	 * @param apellido2 String
	 * @param ejie      String
	 * @param fechaAlta Date
	 * @param fechaBaja Date
	 * @param rol       String
	 */
	public Usuario2(String id, String nombre, String apellido1, String apellido2, String ejie, Date fechaAlta, Date fechaBaja, String rol) {
		super(id, nombre, apellido1, apellido2, ejie, fechaAlta, fechaBaja, rol);
	}

	/**
	 * Method 'Usuario2'.
	 * 
	 * @param nombre    String
	 * @param apellido1 String
	 * @param apellido2 String
	 * @param ejie      String
	 * @param fechaAlta Date
	 * @param fechaBaja Date
	 * @param rol       String
	 */
	public Usuario2(String nombre, String apellido1, String apellido2, String ejie, Date fechaAlta, Date fechaBaja, String rol) {
		super(nombre, apellido1, apellido2, ejie, fechaAlta, fechaBaja, rol);
	}
	
	/**
	 * Method 'getId'.
	 *
	 * @return String
	 */
    public String getId() {
		return super.getId();
	}

	/**
	 * Method 'setId'.
	 *
	 * @param id String
     */
	public void setId(String id) {
		super.setId(id);
	}
	
    /**
	 * Method 'getNombre'.
	 *
	 * @return String
	 */
	public String getNombre() {
		return super.getNombre();
	}

	/**
	 * Method 'setNombre'.
	 *
	 * @param nombre String
     */
	public void setNombre(String nombre) {
		super.setNombre(nombre);
	}
	
    /**
	 * Method 'getApellido1'.
	 *
	 * @return String
	 */	
	public String getApellido1() {
		return super.getApellido1();
	}

	/**
	 * Method 'setApellido1'.
	 *
	 * @param apellido1 String
     */
	public void setApellido1(String apellido1) {
		super.setApellido1(apellido1);
	}
	
    /**
	 * Method 'getApellido2'.
	 *
	 * @return String
	 */
	public String getApellido2() {
		return super.getApellido2();
	}

	/**
	 * Method 'setApellido2'.
	 *
	 * @param apellido2 String
     */
	public void setApellido2(String apellido2) {
		super.setApellido2(apellido2);
	}
	
    /**
	 * Method 'getEjie'.
	 *
	 * @return String
	 */
	public String getEjie() {
		return super.getEjie();
	}

	/**
	 * Method 'setEjie'.
	 *
	 * @param ejie String
     */
	public void setEjie(String ejie) {
		super.setEjie(ejie);
	}
	
	
	/**
	 * Method 'getRol'.
	 * 
	 * @return rol String
	 */
    public String getRol() {
		return super.getRol();
	}

    /**
     * Method 'setRol'.
     * 
     * @param rol String
     */
	public void setRol(String rol) {
		super.setRol(rol);
	}

	/**
	 * Method 'getFechaAlta'.
	 *
	 * @return Date
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAlta() {
		return super.getFechaAlta();
	}

	/**
	 * Method 'setFechaAlta'.
	 *
	 * @param fechaAlta Date
     */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAlta(Date fechaAlta) {
		super.setFechaAlta(fechaAlta);
	}
    /**
	 * Method 'getFechaBaja'.
	 *
	 * @return Date
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaBaja() {
		return super.getFechaBaja();
	}

	/**
	 * Method 'setFechaBaja'.
	 *
	 * @param fechaBaja Date
     */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaBaja(Date fechaBaja) {
		super.setFechaBaja(fechaBaja);
	}
}
