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
<h2><spring:message  code="patronDialogTitle" /></h2>		
<div id="idDialog" style="display:none"><spring:message code="divDialogContent" /></div>
<div id="pruebas">
	<button id="btnDialog"><spring:message code="dialogNormal" /></button>
	<button id="btnAjaxDialogWAR"><spring:message code="dialogAjaxWAR" /></button>
	<button id="btnAjaxDialogStatics"><spring:message code="dialogAjaxStatics" /></button>
	<button id="btnTextDialog"><spring:message  code="dialogText" /></button>
</div>
