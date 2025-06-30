package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateTimeDeserializer;
import com.ejie.x38.serialization.JsonDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author javarona
 *
 */

public class FicheroVersionExp implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String oid;
    private String contentType;
    private String persona;
    private String nombre;
    private Date fechaAlta; 
    private BigDecimal idDocInsertado; //Para facilitar el caso en que hay dos ficheros que se guardan (2 idDoc distintos en la lista de ficheros)
    //Ahora realmente valdría para guardar el idDoc definitivo...
    

    /**
     * @return the oid
     */
    public String getOid() {
        return this.oid;
    }

    /**
     * @param oid
     *            the oid to set
     */
    public void setOid(String oid) {
        this.oid = oid;
    }


    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public BigDecimal getIdDocInsertado() {
        return this.idDocInsertado;
    }

    public void setIdDocInsertado(BigDecimal idDocInsertado) {
        this.idDocInsertado = idDocInsertado;
    }
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getPersona() {
        return this.persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
