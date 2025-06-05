<%@include file="/WEB-INF/includeTemplate.inc"%>
<fieldset class="p-4">
    <legend class="float-none"><spring:message code="lista.filter.legend" /></legend>
    <spring:url value="/patrones/lista/filter" var="url"/>
    <form:form modelAttribute="usuario" id="listFilterForm" action="${url}">
        <div class="row">
            <div class="form-groupMaterial col-sm">
                <form:input id="listFilterId" path="id"/>
            	<label for="listFilterId">
            		<spring:message code="lista.filter.id"/>
            	</label>
            </div>
            <div class="form-groupMaterial col-sm">
                <form:input id="listFilterNombre" path="nombre"/>
            	<label for="listFilterNombre">
            		<spring:message code="lista.filter.nombre"/>
            	</label>
            </div>
        </div>
        <div class="row">
            <div class="form-groupMaterial col-sm">
                <form:input id="listFilterApe1" path="apellido1"/>
            	<label for="listFilterApe1">
            		<spring:message code="lista.filter.apellido1"/>
            	</label>
            </div>
            <div class="form-groupMaterial col-sm">
                <form:input id="listFilterApe2" path="apellido2"/>
            	<label for="listFilterCodApe2">
            		<spring:message code="lista.filter.apellido2"/>
            	</label>
            </div>
        </div>
        <div class="text-end">
            <button id="listFilterLimpiar" class="btn-material btn-material-primary-low-emphasis me-2">
            	<i class="mdi mdi-eraser"></i>
            	<span><spring:message code="lista.filter.clean"/></span>
            </button>
            <button id="listFilterAceptar" class="btn-material btn-material-primary-high-emphasis">
            	<i class="mdi mdi-filter"></i>
            	<span><spring:message code="lista.filter.filter"/></span>
            </button>
        </div>
    </form:form>
</fieldset>