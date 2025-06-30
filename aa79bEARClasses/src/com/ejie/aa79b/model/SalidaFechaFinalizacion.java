package com.ejie.aa79b.model;

/**
 * SalidaFechaFinalizacion
 * @author javarona
 */

import java.io.Serializable;
import java.util.List;

public class SalidaFechaFinalizacion extends DatosSalidaWS implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Long> diasFestivos;
    private Long plazoMinimo;
    private Integer palPresupuestoTrados;
    
    public List<Long> getDiasFestivos() {
        return this.diasFestivos;
    }
    public void setDiasFestivos(List<Long> diasFestivos) {
        this.diasFestivos = diasFestivos;
    }
    public Long getPlazoMinimo() {
        return this.plazoMinimo;
    }
    public void setPlazoMinimo(Long plazoMinimo) {
        this.plazoMinimo = plazoMinimo;
    }
    
    /**
	 * @return the palPresupuestoTrados
	 */
	public Integer getPalPresupuestoTrados() {
		return this.palPresupuestoTrados;
	}
	/**
	 * @param palPresupuestoTrados the palPresupuestoTrados to set
	 */
	public void setPalPresupuestoTrados(Integer palPresupuestoTrados) {
		this.palPresupuestoTrados = palPresupuestoTrados;
	}
	
	@Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getName()).append(" Object { ");
        result.append("[ diasFestivos: ").append(this.diasFestivos).append(" ]");
        result.append(",[ plazoMinimo: ").append(this.plazoMinimo).append(" ]");
        result.append(",[ palPresupuestoTrados: ").append(this.palPresupuestoTrados).append(" ]");
        result.append("}");
        return result.toString();
    }
    
}
