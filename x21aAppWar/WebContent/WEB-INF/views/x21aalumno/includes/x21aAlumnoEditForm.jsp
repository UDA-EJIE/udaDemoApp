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
<%@taglib prefix="form" uri="/WEB-INF/tld/x38-form.tld"%>

<!-- Formulario -->
<spring:url value="/x21aAlumno/${endpoint}" var="url"/>
<form:form modelAttribute="x21aAlumno" id="x21aAlumno_detail_form" action="${url}" method="${actionType}" enctype="${enctype}">
	<!-- Feedback del formulario de detalle -->
	<div id="x21aAlumno_detail_feedback"></div>
	<!-- Campos del formulario de detalle -->
	<c:if test="${not empty pkValue}">
		<form:hidden path="id" value="${pkValue.id}" id="id_detail_table" />
	</c:if>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<form:input path="usuario" id="usuario_detail_table"/>
			<label for="usuario_detail_table"><spring:message code="usuario"/></label>
		</div>
	</div>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<form:password path="password" id="password_detail_table"/>
			<label for="password_detail_table"><spring:message code="password"/></label>
		</div>
		<div class="form-groupMaterial col-sm">
			<form:input path="nombre" id="nombre_detail_table"/>
			<label for="nombre_detail_table"><spring:message code="nombre"/></label>
		</div>
	</div>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<form:input path="apellido1" id="apellido1_detail_table"/>
			<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
		</div>
		<div class="form-groupMaterial col-sm">
			<form:select path="provincia.code" id="provinciaId_detail_table">
				<form:option value="">---</form:option>
			</form:select>
			<label for="provinciaId_detail_table"><spring:message code="provinciaId"/></label>
		</div>
	</div>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<form:select path="comarcaId" id="comarcaId_detail_table">
				<form:option value="">---</form:option>
			</form:select>
			<label for="comarcaId_detail_table"><spring:message code="comarcaId"/></label>
		</div>
		<div class="form-groupMaterial col-sm">
			<form:input path="localidadId" id="localidadId_detail_table"/>
			<label for="localidadId_detail_table"><spring:message code="localidadId"/></label>
		</div>
	</div>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<form:input path="municipioId" id="municipioId_detail_table"/>
			<label for="municipioId_detail_table"><spring:message code="municipioId"/></label>
		</div>
	</div>
</form:form>
