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
           	<input id="${tableID}_multifilter_combo" class="rup_multifilter_selector" />
           	<label for="${tableID}_multifilter_combo" class="${labelClass}">
           		<spring:message code="multiFilter.filters" />
           	</label>
       	</div>
	</div>
	<div class="form-row">
       	<div id="${tableID}_multifilter_dropdownDialog_lineaDefault" class="${defaultContainerClass} col-12">
           	<input type="checkbox" id="${tableID}_multifilter_defaultFilter" class="${defaultCheckboxClass}"/>
           	<label for="${tableID}_multifilter_defaultFilter" class="${labelClass}">
           		<spring:message code="multiFilter.defaultFilter" />
           	</label>
		</div>
	</div>

	<!-- Formulario para añadir o actualizar filtros -->
	<spring:url value="${mapping}/add" var="addUrl"/>
	<form:form modelAttribute="filter" id="${tableID}_add_multiFilter_form" class="d-none" action="${addUrl}" method="POST">
		<form:input path="filterName" id="${tableID}_add_multifilter_combo" />
		<form:input path="filterDefault" value="0" id="${tableID}_add_multifilter_defaultFilter" />
		<form:input path="filterSelector" id="${tableID}_add_multifilter_selector" />
		<form:input path="filterUser" id="${tableID}_add_multifilter_user" />
		<form:input path="filterValue" id="${tableID}_add_multifilter_value" />
		<form:input path="filterFeedback" id="${tableID}_add_multifilter_feedback" />
	</form:form>
	
	<!-- Formulario para eliminar filtros -->
	<spring:url value="${mapping}/delete" var="deleteUrl"/>
	<form:form modelAttribute="filter" id="${tableID}_delete_multiFilter_form" class="d-none" action="${deleteUrl}" method="DELETE">
		<form:input path="filterId" id="${tableID}_delete_multifilter_id" />
		<form:input path="filterName" id="${tableID}_delete_multifilter_combo" />
		<form:input path="filterDefault" id="${tableID}_delete_multifilter_defaultFilter" />
		<form:input path="filterSelector" id="${tableID}_delete_multifilter_selector" />
		<form:input path="filterUser" id="${tableID}_delete_multifilter_user" />
		<form:input path="filterValue" id="${tableID}_delete_multifilter_value" />
		<form:input path="filterFeedback" id="${tableID}_delete_multifilter_feedback" />
	</form:form>
</div>
