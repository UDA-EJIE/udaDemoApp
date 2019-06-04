<%@include file="/WEB-INF/includeTemplate.inc"%>
<!-- Formulario de filtrado -->
<form id="x21aAlumno_filter_form">
	<!-- Barra de herramientas del formulario de filtrado -->
	<div id="x21aAlumno_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="x21aAlumno_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Campos del formulario de filtrado -->
			<div class="form-groupMaterial col-sm">
				<input type="text" name="id" id="id_filter_table"/>
				<label for="id_filter_table"><spring:message code="id"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="usuario" id="usuario_filter_table"/>
				<label for="usuario_filter_table"><spring:message code="usuario"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="password" id="password_filter_table"/>
				<label for="password_filter_table"><spring:message code="password"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="nombre" id="nombre_filter_table"/>
				<label for="nombre_filter_table"><spring:message code="nombre"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="apellido1" id="apellido1_filter_table"/>
				<label for="apellido1_filter_table"><spring:message code="apellido1"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="provinciaId" id="provinciaId_filter_table"/>
				<label for="provinciaId_filter_table"><spring:message code="provinciaId"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="localidadId" id="localidadId_filter_table"/>
				<label for="localidadId_filter_table"><spring:message code="localidadId"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="comarcaId" id="comarcaId_filter_table"/>
				<label for="comarcaId_filter_table"><spring:message code="comarcaId"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="municipioId" id="municipioId_filter_table"/>
				<label for="municipioId_filter_table"><spring:message code="municipioId"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="autonomiaId" id="autonomiaId_filter_table"/>
				<label for="autonomiaId_filter_table"><spring:message code="autonomiaId"/></label>
			</div>
			<!-- Fin campos del formulario de filtrado -->
		</div>
		<!-- Botonera del formulario de filtrado -->
	    <div id="x21aAlumno_filter_buttonSet" class="text-right">
	    	<!-- Botón de limpiar -->
	        <button id="x21aAlumno_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>
	        		<spring:message code="clear" />
	        	</span>
	        </button>
	        <!-- Botón de filtrado -->
	        <button id="x21aAlumno_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>
	        		<spring:message code="filter" />
	        	</span>        	
	        </button>
	    </div>
	</fieldset>
</form>