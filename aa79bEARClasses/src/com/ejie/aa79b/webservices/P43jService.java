package com.ejie.aa79b.webservices;

import java.net.MalformedURLException;

import com.ejie.aa79b.webservices.p43j.P43JFinalizacionIzoberriRespuestaType;

/**
 * 
 * @author javarona
 *
 */
public interface P43jService {

	/**
	 * 
	 * @param expeIzo
	 * @param expeApli
	 * @param rutaPif
	 * @return
	 * @throws Exception
	 */
	P43JFinalizacionIzoberriRespuestaType llamadaWSBoletinP43(String expeIzo, String expeApli, String rutaPif)
			throws MalformedURLException;

}
