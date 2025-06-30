package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * DatosExpediente
 * 
 * @author mrodriguez
 */
public class DatosDocumentoSalida extends DatosSalidaWS implements Serializable {

    private static final long serialVersionUID = 6690977761160890591L;
    private String rutaPif;
    private String nombre;

    public String getRutaPif() {
        return rutaPif;
    }

    public void setRutaPif(String rutaPif) {
        this.rutaPif = rutaPif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "DatosDocumentoSalida [rutaPif=" + rutaPif + ", nombre=" + nombre + "]";
    }
}
