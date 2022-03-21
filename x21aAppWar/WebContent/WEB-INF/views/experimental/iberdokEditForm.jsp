<%--  
 -- Copyright 2021 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 -- Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 -- SIN GARANTÃAS NI CONDICIONES DE NINGÃN TIPO, ni expresas ni implÃ­citas.
 -- VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"%>
<%@taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>

<!-- Formulario -->

<spring:url value="https://apps.euskadi.ejiedes.eus/euskodok/editor_documentos/acceso.htm" var="url"/>
<form:form modelAttribute="randomForm" id="iberdokTable_detail_form" action="${url}" method="post" accept-charset="windows-1252">
	<!-- Feedback del formulario de detalle -->
	<div id="iberdokTable_detail_feedback"></div>
	<h3>
		<spring:message code="iberdok.dialog.editor" />
	</h3>
	<!-- Campos del formulario de detalle -->
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
	    	<form:select path="lang" items="${lang}" id="lang_detail_table" />
	    	<label for="lang_detail_table"><spring:message code="iberdok.lang" /></label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:select path="modo" items="${modo}" id="modo_detail_table" />
	    	<label for="modo_detail_table"><spring:message code="iberdok.modo" /></label>
	    </div>
	    <div class="form-groupMaterial col-sm d-none">
	    	<form:input path="token" id="token_detail_table" />
	    	<label for="token_detail_table"><spring:message code="iberdok.token" /></label>
	    </div> 
	</div>
	<div class="form-row d-none">      
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="idUsuario" id="idUsuario_detail_table" />
	    	<label for="idUsuario_detail_table"><spring:message code="iberdok.idUsuario" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="urlRetorno" id="urlRetorno_detail_table" />
	    	<label for="urlRetorno_detail_table"><spring:message code="iberdok.urlRetorno" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="urlFinalizacion" id="urlFinalizacion_detail_table" />
	    	<label for="urlFinalizacion_detail_table"><spring:message code="iberdok.urlFinalizacion" /></label>
	    </div>
	</div>
	<h3 id="datosNecesarios">
		<spring:message code="iberdok.datosNecesarios" />
	</h3>
	<div id="divModo1" class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="idModelo" id="idModelo_detail_table" />
	    	<label for="idModelo_detail_table"><spring:message code="iberdok.idModelo" /></label>
	    </div>
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="nombre" id="nombre_detail_table" />
	    	<label for="nombre_detail_table"><spring:message code="iberdok.nombre" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div id="divModo2" class="form-groupMaterial col-sm">
	    	<form:input path="xhtml64" id="xhtml64_detail_table" readonly="readonly" />
	    	<label for="xhtml64_detail_table"><spring:message code="iberdok.xhtml64" /></label>
	    </div>
	    <div id="divModo7" class="form-groupMaterial col-sm">
	    	<form:input path="idDocumento" id="idDocumento_detail_table" readonly="readonly" />
	    	<label for="idDocumento_detail_table"><spring:message code="iberdok.idDocumento" /></label>
	    </div>
	</div>	
	<div id="semillas" class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:textarea path="semilla" id="semilla_detail_table" />
	    	<label for="semilla_detail_table"><spring:message code="iberdok.semilla" /></label>
	    </div>
	    <div class="form-groupMaterial col-sm">
	    	<form:textarea path="semillaTXT" id="semillaTXT_detail_table" />
	    	<label for="semillaTXT_detail_table"><spring:message code="iberdok.semillaTXT" /></label>
	    </div>
	    <div class="form-groupMaterial col-sm">
	    	<form:textarea path="semillaXML" id="semillaXML_detail_table" />
	    	<label for="semillaXML_detail_table"><spring:message code="iberdok.semillaXML" /></label>
	    </div>
	</div>
</form:form>
