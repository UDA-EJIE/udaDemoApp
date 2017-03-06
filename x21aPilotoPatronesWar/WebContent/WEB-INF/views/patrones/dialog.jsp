<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versi�n 1.1 exclusivamente (la �Licencia�);
 -- Solo podr� usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�,
 -- SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas.
 -- V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2><spring:message  code="patronDialogTitle" /></h2>		
<div id="idDialog" style="display:none"><spring:message code="divDialogContent" /></div>
<div id="idDialogAjaxWar" style="display: none"></div>
<div id="idDialogAjaxStatics" style="display: none"></div>
<div id="idDialogText" style="display: none"></div>
<div id="pruebas">
	<button id="btnDialog"><spring:message code="dialogNormal" /></button>
	<button id="btnAjaxDialogWAR"><spring:message code="dialogAjaxWAR" /></button>
	<button id="btnAjaxDialogStatics"><spring:message code="dialogAjaxStatics" /></button>
	<button id="btnTextDialog"><spring:message  code="dialogText" /></button>
</div>
