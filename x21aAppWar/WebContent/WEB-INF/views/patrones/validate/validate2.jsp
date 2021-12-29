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
<h2 class="title mb-3">Validacion</h2>


<div id="feedbackErroresValidaciones"></div>
<div>
<fieldset class="alumnoFieldset">
	<legend><spring:message code="configuracion" /></legend>
	<span><spring:message code="patron.validacion.configuracion.texto" /></span>
	<br/><br/>
	
	<input type="checkbox" id="liveCheckingErrors" /><label for="liveCheckingErrors"><spring:message code="patron.validacion.configuracion.liveCheckingErrors" /></label><br/>
	<input type="checkbox" id="checkFeedbackError" checked="checked" /><label for="checkFeedbackError"><spring:message code="patron.validacion.configuracion.checkFeedbackError" /></label><br/>
	<input type="checkbox" id="checkShowErrorsFeedback" checked="checked"/><label for="checkShowErrorsFeedback"><spring:message code="patron.validacion.configuracion.checkShowErrorsFeedback" /></label><br/>
	<input type="checkbox" id="checkShowFieldErrorsTip" checked="checked"/><label for="checkShowFieldErrorsTip"><spring:message code="patron.validacion.configuracion.checkShowFieldErrorsTip" /></label><br/> 
	<br/>
	<form:errors></form:errors>
	<button id="botonConfiguracion"><spring:message code="aplicarConfiguracion" /></button>
</fieldset>
</div>

<div id="tabsValidacion"></div>
<div id="validacionesServidor"></div>
<div id="validacionesCliente"></div>
	
<div id="divValidaciones">
	<jsp:include page="includes/clientRules.jsp"></jsp:include>
</div>

<div id="divValidaciones2">
	<jsp:include page="includes/clientComponents.jsp"></jsp:include>
</div>

<div id="divValidaciones3">
	<jsp:include page="includes/serverForm1.jsp"></jsp:include>
	
</div>

<div id="divValidaciones4">
	<jsp:include page="includes/serverForm2.jsp"></jsp:include>
	
</div>

