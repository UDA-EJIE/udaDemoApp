<%@include file="/WEB-INF/includeTemplate.inc"%>
<!-- Formulario de filtrado -->
<form id="usuario_filter_form">
	<!-- Barra de herramientas del formulario de filtrado -->
	<div id="usuario_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="usuario_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Campos del formulario de filtrado -->
			<div class="form-groupMaterial col-sm">
				<input type="text" name="id" id="id_filter_table"/>
				<label for="id_filter_table"><spring:message code="id"/></label>
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
				<input type="text" name="apellido2" id="apellido2_filter_table"/>
				<label for="apellido2_filter_table"><spring:message code="apellido2"/></label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<input type="text" name="ejie" id="ejie_filter_table"/>
				<label for="ejie_filter_table"><spring:message code="ejie"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="fechaAlta" id="fechaAlta_filter_table"/>
				<label for="fechaAlta_filter_table"><spring:message code="fechaAlta"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="fechaBaja" id="fechaBaja_filter_table"/>
				<label for="fechaBaja_filter_table"><spring:message code="fechaBaja"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="rol" id="rol_filter_table"/>
				<label for="rol_filter_table"><spring:message code="rol"/></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="fechaModif" id="fechaModif_filter_table"/>
				<label for="fechaModif_filter_table"><spring:message code="fechaModif"/></label>
			</div>
		</div>
		
		<!-- Botonera del formulario de filtrado -->
	    <div id="usuario_filter_buttonSet" class="text-right">
	    	<!-- Botón de limpiar -->
	        <button id="usuario_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>
	        		<spring:message code="clear" />
	        	</span>
	        </button>
	        <!-- Botón de filtrado -->
	        <button id="usuario_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>
	        		<spring:message code="filter" />
	        	</span>        	
	        </button>
	    </div>
	</fieldset>
</form>