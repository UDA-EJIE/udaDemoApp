<%--  
 -- Copyright 2021 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<spring:url value="/x21aalumno/filter" var="url"/>
<!-- Formulario de filtrado -->
<form:form modelAttribute="X21aAlumno" id="x21aAlumno_filter_form" action="${url}" method="POST">
	<!-- Barra de herramientas del formulario de filtrado -->
	<div id="x21aAlumno_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="x21aAlumno_filter_fieldset" class="rup-table-filter-fieldset">
		<!-- Campos del formulario de filtrado -->
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="id" id="id_filter_table"/>
				<label for="id_filter_table"><spring:message code="id"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="usuario" id="usuario_filter_table"/>
				<label for="usuario_filter_table"><spring:message code="usuario"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="password" id="password_filter_table"/>
				<label for="password_filter_table"><spring:message code="password"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="nombre" id="nombre_filter_table"/>
				<label for="nombre_filter_table"><spring:message code="nombre"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="apellido1" id="apellido1_filter_table"/>
				<label for="apellido1_filter_table"><spring:message code="apellido1"/></label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="provincia" id="provinciaId_filter_table"/>
				<label for="provinciaId_filter_table"><spring:message code="provinciaId"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="comarca" id="comarcaId_filter_table"/>
				<label for="comarcaId_filter_table"><spring:message code="comarcaId"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="localidad" id="localidadId_filter_table"/>
				<label for="localidadId_filter_table"><spring:message code="localidadId"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="municipio" id="municipioId_filter_table"/>
				<label for="municipioId_filter_table"><spring:message code="municipioId"/></label>
			</div>
			
			<div class="form-groupMaterial col-sm">
				<form:input path="autonomia" id="autonomiaId_filter_table"/>
				<label for="autonomiaId_filter_table"><spring:message code="autonomiaId"/></label>
			</div>
		</div>
		<!-- Fin campos del formulario de filtrado -->
		
		<!-- Botonera del formulario de filtrado -->
	    <div id="x21aAlumno_filter_buttonSet" class="text-right">
	    	<!-- Botón de limpiar -->
	        <button id="x21aAlumno_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>Limpiar</span>
	        </button>
	        <!-- Botón de filtrado -->
	        <button id="x21aAlumno_filter_filterButton" type="button" class="btn-material btn-material-primary-low-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>Filtrar</span>        	
	        </button>
	    </div>
	</fieldset>
</form:form>
