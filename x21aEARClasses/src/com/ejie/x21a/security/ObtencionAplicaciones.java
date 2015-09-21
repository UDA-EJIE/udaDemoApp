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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.ejie.x21a.dao.AppCodesDao;
import com.ejie.x38.security.AlternativeOriginCredentialsApp;


/**
 * 
 * @author UDA
 *
 */
public class ObtencionAplicaciones implements AlternativeOriginCredentialsApp {
	
	@Autowired
	private AppCodesDao appCodesDao;
	private List<String> appCodes = null;
	
	public boolean existAditionalsAppCodes(HttpServletRequest httpRequest){
		HttpSession httpSession = httpRequest.getSession(false);
		String userAppCodes = "";
		
		this.appCodes = null;
		
		if (httpSession != null){
			userAppCodes = ((String)httpSession.getAttribute("userName")).toLowerCase();
			if (!userAppCodes.equals("")){
				this.appCodes = appCodesDao.find(userAppCodes);
				if(this.appCodes.size() > 0){
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	} 
	
	public List<String> getAppCodes(HttpServletRequest httpRequest){
		return this.appCodes;
	}
		
	
			
}