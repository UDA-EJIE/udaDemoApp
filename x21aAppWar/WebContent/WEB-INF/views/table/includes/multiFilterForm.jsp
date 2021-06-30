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
	<!-- Formulario -->
	<spring:url value="${mapping}/add" var="url"/>
	<form:form modelAttribute="${entity}" id="${tableID}_multiFilter_form" action="${url}" method="POST">
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
	</form:form>
</div>
