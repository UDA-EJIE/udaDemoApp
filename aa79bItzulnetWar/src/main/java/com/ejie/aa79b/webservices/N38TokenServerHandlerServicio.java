package com.ejie.aa79b.webservices;

import javax.xml.xpath.XPathExpressionException;

public class N38TokenServerHandlerServicio extends N38TokenServerHandler {


	private static String FUNCION_WS = "AA79B-FN-0003";
	
	@Override()
	protected String getFuncion() {
		return FUNCION_WS;
	}
	
	public N38TokenServerHandlerServicio() throws XPathExpressionException {
		super();
	}
	
	

}
