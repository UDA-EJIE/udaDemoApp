<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%--  
 -- Copyright 2012 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@ include file="/WEB-INF/includeTemplate.inc" %>
<%@ include file="/WEB-INF/includeTemplate.inc" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld" %>

	<!-- Datos asociados a las llamadas variables de mantenimientos -->
	<tiles:importAttribute name="js" />
		
		<%@include file="/WEB-INF/layouts/includes/x21a.styles.inc"%>
		
		<!-- Contenidos -->
		<tiles:insertAttribute name="content"/>
		
		<!-- Includes JS -->
		<script src="${staticsUrl}/x21a/scripts/x21aMantenimientos/${js}" type="text/javascript"></script>
