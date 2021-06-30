<%@include file="/WEB-INF/includeTemplate.inc"%>
<fieldset class="p-4">
    <legend><spring:message code="lista.filter.legend" /></legend>
    <spring:url value="/patrones/lista/filter" var="url"/>
    <form:form modelAttribute="usuario" id="listFilterFormRight" action="${url}">
        <div class="form-row">
        	<div class="form-groupMaterial col-sm">
	            <form:input path="nombre" id="listFilterNombreRight"/>
	            <label for="listFilterNombreRight">
	            	<spring:message code="lista.filter.nombre"/>
	            </label>
            </div>
            <div class="form-groupMaterial col-sm">
	            <form:input path="apellido1" id="listFilterApe1Right"/>
	            <label for="listFilterApe1Right">
	            	<spring:message code="lista.filter.apellido1"/>
	            </label>
	        </div>
        </div>
        <div class="form-row">
        	<div class="form-groupMaterial col-sm">
                <form:input path="fechaAlta" id="listFilterFechaAltaRight"/>
            	<label for="listFilterFechaAltaRight">
            		<spring:message code="lista.filter.fechaAlta"/>
            	</label>
            </div>
	        <div class="form-groupMaterial col-sm">
                <form:select path="rol" id="listFilterRolRight" items="${comboRol}" />
            	<label for="listFilterRolRight">
            		<spring:message code="lista.filter.rol"/>
            	</label>
            </div>
            <div class="form-groupMaterial col-sm">  
	    		<form:select path="ejie" id="listFilterEjieRight" items="${comboEjie}" />
	    		<label for="listFilterEjieRight">
					<spring:message code="lista.filter.ejie"/>
				</label>
	    	</div>
        </div>
        <!-- Botonera del formulario de filtrado -->
        <div class="form-row justify-content-end px-2">
        	<!-- Bot�n de filtrado -->
            <button id="listFilterAceptar" class="btn-material btn-material-primary-high-emphasis order-last">
            	<i class="mdi mdi-filter"></i>
            	<span>
            		<spring:message code="filter"/>
            	</span>
            </button>
            <!-- Bot�n de limpiar -->
            <button id="listFilterLimpiar" class="btn-material btn-material-primary-low-emphasis order-first mr-2">
            	<i class="mdi mdi-eraser"></i>
            	<span>
            		<spring:message code="clear"/>
            	</span>
            </button>
        </div>
    </form:form>
</fieldset>
