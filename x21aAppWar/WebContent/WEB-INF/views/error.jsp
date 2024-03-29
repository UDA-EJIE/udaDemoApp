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
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid">
    <h2 class="title mb-3">ERROR</h2> <br>
    <spring:url value="/" var="urlError" htmlEscape="true"/>
    <a href="${urlError}"><spring:message code="error.volver" /></a>

    <h3>Name: </h3><c:out value="${(empty param) ? exception_name : param.exception_name}"/><br>
    <h3>Message: </h3><c:out value="${(empty param) ? exception_message : param.exception_message}"/><br>
    <h3>Trace: </h3><c:out value="${(empty param) ? exception_trace : param.exception_trace}"/><br>
</div>

<script type="text/javascript">
    document.querySelector('.contenedor').classList.add('show');
</script>