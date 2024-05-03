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
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>

<div id="${tableID}_multifilter_dropdownDialog" style="display:none" class="dialog-content-material">
	<div id="${tableID}_multifilter_dropdownDialog_feedback"></div>
	<div class="form-row">
       	<div id="${tableID}_multifilter_dropdownDialog_lineaCombo" class="${containerClass} col-12">
           	<select id="${tableID}_multifilter_select" class="rup_multifilter_selector"></select>
           	<label for="${tableID}_multifilter_select" class="${labelClass}">
           		<spring:message code="multiFilter.filters" />
           	</label>
       	</div>
	</div>
	<div class="form-row">
       	<div id="${tableID}_multifilter_dropdownDialog_lineaDefault" class="${defaultContainerClass} col-12">
           	<input type="checkbox" id="${tableID}_multifilter_activeFilter" class="${defaultCheckboxClass}"/>
           	<label for="${tableID}_multifilter_activeFilter" class="${labelClass}">
           		<spring:message code="multiFilter.activeFilter" />
           	</label>
		</div>
	</div>

	<!-- Formulario para añadir o actualizar filtros -->
	<spring:url value="${mapping}/add" var="addUrl"/>
	<form:form modelAttribute="filter" id="${tableID}_add_multiFilter_form" class="d-none" action="${addUrl}" method="POST">
		<form:input path="text" id="${tableID}_add_multifilter_name" />
		<form:input path="data" id="${tableID}_add_multifilter_data" />
		<form:input path="active" value="0" id="${tableID}_add_multifilter_active" />
		<form:input path="selector" id="${tableID}_add_multifilter_selector" />
		<form:input path="user" id="${tableID}_add_multifilter_user" />
		<form:input path="feedback" id="${tableID}_add_multifilter_feedback" />
	</form:form>
	
	<!-- Formulario para eliminar filtros -->
	<spring:url value="${mapping}/delete" var="deleteUrl"/>
	<form:form modelAttribute="filter" id="${tableID}_delete_multiFilter_form" class="d-none" action="${deleteUrl}" method="DELETE">
		<form:input path="id" id="${tableID}_delete_multifilter_id" />
		<form:input path="text" id="${tableID}_delete_multifilter_name" />
		<form:input path="data" id="${tableID}_delete_multifilter_data" />
		<form:input path="active" id="${tableID}_delete_multifilter_active" />
		<form:input path="selector" id="${tableID}_delete_multifilter_selector" />
		<form:input path="user" id="${tableID}_delete_multifilter_user" />
		<form:input path="feedback" id="${tableID}_delete_multifilter_feedback" />
	</form:form>
</div>
