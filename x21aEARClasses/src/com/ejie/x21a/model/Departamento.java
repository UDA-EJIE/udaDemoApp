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


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
*  * Departamento generated by UDA 1.0, 26-may-2011 13:45:00.
* @author UDA
 */

public class Departamento implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
      private BigDecimal code;
      private String descEs;
      private String descEu;
      private String css;
      private List<DepartamentoProvincia> departamentoProvincias = new ArrayList<DepartamentoProvincia>();

/** Method 'Departamento'.
*
*/
    public Departamento() {
    }
    /** Method 'Departamento'.
    * @param  code BigDecimal
    * @param  descEs String
    * @param  descEu String
    * @param  css String
    */
    public Departamento(BigDecimal code, String descEs, String descEu, String css ) {	
        this.code = code;
        this.descEs = descEs;
        this.descEu = descEu;
        this.css = css;
    }

    /** Method 'Departamento'.
    * @param  code BigDecimal
    * @param  descEs String
    * @param  descEu String
    * @param  css String
    * @param  departamentoProvincias List<DepartamentoProvincia>
    */
   public Departamento(BigDecimal code, String descEs, String descEu, String css, List<DepartamentoProvincia> departamentoProvincias) {	

           this.code = code;
           this.descEs = descEs;
           this.descEu = descEu;
           this.css = css;
           this.departamentoProvincias = departamentoProvincias;
    }

    /**
     * Method 'getCode'.
     *
     * @return BigDecimal
     */
	 
    
    public BigDecimal getCode() {
       return this.code;
     }

    /**
     * Method 'setCode'.
     *
     * @param code BigDecimal
     */
    
    public void setCode(BigDecimal code) {
      this.code = code;
    }
    	
    /**
     * Method 'getDescEs'.
     *
     * @return String
     */
	 
    
    public String getDescEs() {
       return this.descEs;
     }

    /**
     * Method 'setDescEs'.
     *
     * @param descEs String
     */
    
    public void setDescEs(String descEs) {
      this.descEs = descEs;
    }
    	
    /**
     * Method 'getDescEu'.
     *
     * @return String
     */
	 
    
    public String getDescEu() {
       return this.descEu;
     }

    /**
     * Method 'setDescEu'.
     *
     * @param descEu String
     */
    
    public void setDescEu(String descEu) {
      this.descEu = descEu;
    }
    	
    /**
     * Method 'getCss'.
     *
     * @return String
     */
	 
    
    public String getCss() {
       return this.css;
     }

    /**
     * Method 'setCss'.
     *
     * @param css String
     */
    
    public void setCss(String css) {
      this.css = css;
    }
    	
    /**
     * Method 'getDepartamentoProvincias'.
     *
     *
     * @return List
     */
	 @JsonIgnore
     public List<DepartamentoProvincia> getDepartamentoProvincias() {
         return this.departamentoProvincias;
     }
    /**
     * Method 'setDepartamentoProvincias'.
     *
     * @param departamentoProvincias List
     */
	 
    public void setDepartamentoProvincias(List<DepartamentoProvincia> departamentoProvincias) {
       this.departamentoProvincias = departamentoProvincias;
    }
	

/**
* Intended only for logging and debugging.
* 
* Here, the contents of every main field are placed into the result.
* @return String
*/
@Override
 public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(this.getClass().getName()).append(" Object { " ); 
				result.append(" [ code: ").append(this.code).append(" ]");
				result.append(", [ descEs: ").append(this.descEs).append(" ]");
				result.append(", [ descEu: ").append(this.descEu).append(" ]");
				result.append(", [ css: ").append(this.css).append(" ]");
	result.append("}");
    return result.toString();
     }


}

