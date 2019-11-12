<%--  
 -- Copyright 2011 E.J.I.E., S.A.
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
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="footer">
	<div style="float: left;">
		<spring:url value="http://www.euskadi.net/r33-2288/es/contenidos/informacion/informacion_legal/es_6303/informacion_legal.html" var="urlLegal" htmlEscape="true"/>
		<a target="_blank" title='<spring:message code="footer.avisoLegal" />' href="${urlLegal}" class="apartado">
			<spring:message code="footer.avisoLegal" />
		</a>
		<span class="ui-icon ui-icon-extlink rup_external_link">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		&nbsp;&nbsp;-&nbsp;&nbsp; 
		<a target="_blank" title='<spring:message code="footer.privacidad" />' href="${urlLegal}" class="apartado">
			<spring:message code="footer.privacidad" />
		</a>
		<span class="ui-icon ui-icon-extlink rup_external_link">&nbsp;&nbsp;&nbsp;&nbsp;</span>
	</div>
	<div style="float: right;">
		&copy; 2013 Eusko Jaurlaritza-Gobierno Vasco
	</div>
</div>