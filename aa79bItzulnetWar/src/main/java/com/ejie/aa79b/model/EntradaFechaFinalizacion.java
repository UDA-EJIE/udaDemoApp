package com.ejie.aa79b.model;

/**
 * EntradaFechafinalizacion
 * @author javarona
 */

import java.io.Serializable;

public class EntradaFechaFinalizacion implements Serializable {
  
    private static final long serialVersionUID = 1L;
    private String dniGestor;
    private String tipoExpediente;
    private Integer numPalabras;
    private String indPresupuesto;
    
    
    public String getDniGestor() {
        return this.dniGestor;
    }
    public void setDniGestor(String dniGestor) {
        this.dniGestor = dniGestor;
    }
    public String getTipoExpediente() {
        return this.tipoExpediente;
    }
    public void setTipoExpediente(String tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }
    public Integer getNumPalabras() {
        return this.numPalabras;
    }
    public void setNumPalabras(Integer numPalabras) {
        this.numPalabras = numPalabras;
    }
    public String getIndPresupuesto() {
        return this.indPresupuesto;
    }
    public void setIndPresupuesto(String indPresupuesto) {
        this.indPresupuesto = indPresupuesto;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getName()).append(" Object { ");
        result.append("[ dniGestor: ").append(this.dniGestor).append(" ]");
        result.append(",[ tipoExpediente: ").append(this.tipoExpediente).append(" ]");
        result.append(",[ numPalabras: ").append(this.numPalabras).append(" ]");
        result.append(",[ indPresupuesto: ").append(this.indPresupuesto).append(" ]");
        result.append("}");
        return result.toString();
    }
}