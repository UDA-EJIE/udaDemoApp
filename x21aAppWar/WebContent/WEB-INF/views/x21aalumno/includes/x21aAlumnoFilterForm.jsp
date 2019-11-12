<%@include file="/WEB-INF/includeTemplate.inc"%>
<form:form modelAttribute="alumno" id="x21aAlumno_filter_form">						<!-- Formulario de filtrado -->
	<div id="x21aAlumno_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
	<fieldset id="x21aAlumno_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Campos del formulario de filtrado -->
			<div class="form-row">
				<div class="form-group col-sm">
					<form:input path="id" id="id_filter_table"/>
					<label for="id_filter_table"><spring:message code="id"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="usuario" id="usuario_filter_table"/>
					<label for="usuario_filter_table"><spring:message code="usuario"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="password" id="password_filter_table"/>
					<label for="password_filter_table"><spring:message code="password"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="nombre" id="nombre_filter_table"/>
					<label for="nombre_filter_table"><spring:message code="nombre"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="apellido1" id="apellido1_filter_table"/>
					<label for="apellido1_filter_table"><spring:message code="apellido1"/></label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm">
					<form:input path="provincia.id" id="provinciaId_filter_table"/>
					<label for="provinciaId_filter_table"><spring:message code="provinciaId"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="localidad" id="localidadId_filter_table"/>
					<label for="localidadId_filter_table"><spring:message code="localidadId"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="comarca" id="comarcaId_filter_table"/>
					<label for="comarcaId_filter_table"><spring:message code="comarcaId"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="municipio.id" id="municipioId_filter_table"/>
					<label for="municipioId_filter_table"><spring:message code="municipioId"/></label>
				</div>
				
				<div class="form-group col-sm">
					<form:input path="autonomia.id" id="autonomiaId_filter_table"/>
					<label for="autonomiaId_filter_table"><spring:message code="autonomiaId"/></label>
				</div>
			</div>
			<!-- Fin campos del formulario de filtrado -->
		</div>
		
		<!-- Botonera del formulario de filtrado -->
	    <div id="x21aAlumno_filter_buttonSet" class="text-right">
	    	<!-- Bot�n de limpiar -->
	        <button id="x21aAlumno_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>Limpiar</span>
	        </button>
	        <!-- Bot�n de filtrado -->
	        <button id="x21aAlumno_filter_filterButton" type="button" class="btn-material btn-material-primary-low-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>Filtrar</span>        	
	        </button>
	    </div>
	</fieldset>
</form:form>
