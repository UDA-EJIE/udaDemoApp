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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import n38c.exe.N38API;

import com.ejie.x38.security.ExcludeFilter;
import com.ejie.x38.security.XlnetCore;


/**
 * 
 * @author UDA
 *
 */
public class UsuariosConCertificado implements ExcludeFilter {
	
	private String accessDeniedUrl;
	
	public String getAccessDeniedUrl(){
		return this.accessDeniedUrl;
	}
	
	public void setAccessDeniedUrl(String accessDeniedUrl){
		this.accessDeniedUrl = accessDeniedUrl;
	}
	
	public boolean accept(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		N38API n38Api = XlnetCore.getN38API(httpRequest);
		String policy;
		
		policy = XlnetCore.getN38ItemSesion(n38Api, N38API.NOMBRE_N38CERTIFICADOPOLITICAS);
		
		if (!(policy.toLowerCase().equals("no"))){
			return true;
		} else {
			return false;
		}
	}
	
	
			
}