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
<spring:url value="/table/${endpoint}" var="url"/>
<form:form modelAttribute="usuario" id="example_detail_form" action="${url}" method="${actionType}" enctype="${enctype}">
	<!-- Feedback del formulario de detalle -->
	<div id="example_detail_feedback"></div>
	<!-- Campos del formulario de detalle -->
	<c:if test="${not empty pkValue}">
		<form:hidden path="id" value="${pkValue}" id="id_detail_table" />
	</c:if>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
	    	<form:input path="nombre" id="nombre_detail_table" />
	    	<label for="nombre_detail_table"><spring:message code="nombre" /></label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:select path="apellido1" id="apellido1_detail_table" />
	    	<label for="apellido1_detail_table"><spring:message code="apellido1" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:select path="apellido2" id="apellido2_detail_table" />
	    	<label for="apellido2_detail_table"><spring:message code="apellido2" /></label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="fechaBaja" id="fechaBaja_detail_table" />
	    	<label for="fechaBaja_detail_table"><spring:message code="fechaBaja" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="fechaAlta" id="fechaAlta_detail_table" />
	    	<label for="fechaAlta_detail_table"><spring:message code="fechaAlta" /></label>
	    </div>
	    <div class="checkbox-material col-sm">
	    	<form:checkbox path="ejie" id="ejie_detail_table" value="1" />
	    	<label for="ejie_detail_table"><spring:message code="ejie" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:select path="rol" id="rol_detail_table" /> 
	    	<label for="rol_detail_table"><spring:message code="rol" /></label>
	    </div>
	</div>
	<c:if test="${isMultipart}">
	<div class="form-row">	
		<div class="form-groupMaterial col-sm">
			<form:input path="imagenAlumno" type="file" id="imagenAlumno_detail_table" />
			<label for="imagenAlumno_detail_table"><spring:message code="subidaImg" /></label>
		</div>	
	</div>
	</c:if>
</form:form>
