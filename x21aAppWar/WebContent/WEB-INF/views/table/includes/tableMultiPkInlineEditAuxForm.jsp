<%--  
 -- Copyright 2023 E.J.I.E., S.A.
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
<spring:url value="/table/multipk/${endpoint}" var="url"/>
<form:form modelAttribute="multiPk" id="multiPk_detail_inlineEdit_aux_form" class="d-none" action="${url}" method="${actionType}" enctype="${enctype}">
	<c:choose>
		<c:when test="${actionType eq 'POST'}">
			<form:input path="ida" id="ida_multiPk_detail_table"/>
			<form:input path="idb" id="idb_multiPk_detail_table"/>
		</c:when>
		<c:when test="${not empty pkValue}">
			<form:hidden path="id" value="${pkValue}" id="id_multiPk_inlineEdit_aux_form" />
		</c:when>
	</c:choose>
	<form:input path="nombre" id="nombre_multiPk_inlineEdit_aux_form" />
	<form:input path="apellido1" id="apellido1_multiPk_inlineEdit_aux_form" />
	<form:input path="apellido2" id="apellido2_multiPk_inlineEdit_aux_form" />
</form:form>
