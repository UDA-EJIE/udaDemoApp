<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="cabecera" class="cabecera" style="margin-bottom: 2em;" >
	<div style="float: left; margin-top:-1.5em;margin-bottom:1em;" >
		<img src="${staticsUrl}/x21a/images/logo_themeRoller.png" alt="logo_themeRoller"/>
		<a href="#" id="themeroller" ></a>
		<a href="#" id="borrar_themeroller" style="display: none;">Borrar ThemeRoller<img src="${staticsUrl}/x21a/images/icons/trash.png"/></a>
	</div>
	<span style="font-size: 2em;color: #000000;margin-left: 1em; "><spring:message code="cabeceraPortal"/></span>
	<a href="<%= request.getContextPath()%>/" style="float: right; margin-top:-2em;">
		<img src="${staticsUrl}/rup/css/images/euskadi_net.gif" alt="Euskadi Net"/>
	</a>
</div>
