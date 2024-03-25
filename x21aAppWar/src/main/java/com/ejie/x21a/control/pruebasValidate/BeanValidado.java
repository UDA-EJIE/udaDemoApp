package com.ejie.x21a.control.pruebasValidate;

import javax.validation.constraints.Max;


public class BeanValidado  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
   private int unNumero;
   @Max(2)
   private int otroNumero;

   public BeanValidado() {
   }

   public int getUnNumero() {
      return unNumero;
   }

   public void setUnNumero(int unNumero) {
      this.unNumero = unNumero;
   }

   public int getOtroNumero() {
      return otroNumero;
   }

   public void setOtroNumero(int	 otroNumero) {
      this.otroNumero = otroNumero;
   }

}