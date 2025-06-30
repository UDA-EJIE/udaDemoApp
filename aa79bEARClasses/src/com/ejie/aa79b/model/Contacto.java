package com.ejie.aa79b.model;

/**
 * @author javarona
 *
 */

public class Contacto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String persona;
    private String email;
    private String telefono;
    private String direccion;
    private String entidad;
    
    public String getPersona() {
        return this.persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return this.direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEntidad() {
        return this.entidad;
    }
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }


}
