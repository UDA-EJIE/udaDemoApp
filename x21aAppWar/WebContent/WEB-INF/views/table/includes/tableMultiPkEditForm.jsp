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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>

<!-- Formulario -->
<c:set value="${actionType == 'POST' ? 'add': 'edit'}" var="endpoint" />
<spring:url value="/table/multipk/${endpoint}" var="url"/>
<form:form modelAttribute="multiPk" id="MultiPk_detail_form" action="${url}" method="${actionType}">
	<!-- Feedback del formulario de detalle -->
	<div id ="MultiPk_detail_feedback"></div>
	<!-- Campos del formulario de detalle -->
	<c:if test="${actionType == 'POST'}">
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="ida" id="ida_multipk_detail_table"/>
				<label for="ida_multipk_detail_table"><spring:message code="ida"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="idb" id="idb_multipk_detail_table"/>
				<label for="idb_multipk_detail_table"><spring:message code="idb"/></label>
			</div>
		</div>
	</c:if>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
		
			<form:input path="nombre" id="nombre_multipk_detail_table"/>
			<label for="nombre_multipk_detail_table"><spring:message code="nombre"/></label>
		</div>
	</div>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<form:input path="apellido1" id="apellido1_multipk_detail_table"/>
			<label for="apellido1_multipk_detail_table"><spring:message code="apellido1"/></label>
		</div>
		<div class="form-groupMaterial col-sm">
			<form:input path="apellido2" id="apellido2_multipk_detail_table"/>
			<label for="apellido2_multipk_detail_table"><spring:message code="apellido2"/></label>
		</div>
	</div>
</form:form>
