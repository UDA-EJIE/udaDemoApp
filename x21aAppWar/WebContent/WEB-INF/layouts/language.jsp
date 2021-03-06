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
<div id="x21aAppWar_language" class="rup-language_root">
	<div class="languageHelpContact">
		<spring:url value="https://elkarlan.ejgv.euskadi.net/webguneak/jakingune/RecursosDidacticos/Guias/Gaveta/Paginas%20Wiki/Inicio.aspx" var="urlLang" htmlEscape="true"/>
		<a target="_blank" title='<spring:message code="language.ayuda" />' href="${urlLang}" id="header_ayuda">
			<spring:message code="language.ayuda" />
		</a>
		<span class="ui-icon ui-icon-extlink rup_external_link">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		&nbsp;&nbsp;&nbsp;
		<spring:url value="mailto:uda@uda.es" var="urlContact" htmlEscape="true"/>
		<a target="_blank" title='<spring:message code="language.contacto" />' href="${urlContact}">
			<spring:message code="language.contacto" />	
		</a>
		<span class="ui-icon ui-icon-extlink rup_external_link">&nbsp;&nbsp;&nbsp;&nbsp;</span>
	</div>
</div>