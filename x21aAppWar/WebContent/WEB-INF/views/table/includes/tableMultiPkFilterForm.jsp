<%@include file="/WEB-INF/includeTemplate.inc"%>
<form id="MultiPk_filter_form">						<!-- Formulario de filtrado -->
	<div id="MultiPk_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
	<fieldset id="MultiPk_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Campos del formulario de filtrado -->
			<div class="form-groupMaterial col-sm">
				<input type="text" name="ida" id="ida_filter_table"/>
				<label for="ida_filter_table">
					<spring:message code="ida"/>
				</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="idb" id="idb_filter_table"/>
				<label for="idb_filter_table">
					<spring:message code="idb"/>
				</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="nombre" id="nombre_filter_table"/>
				<label for="nombre_filter_table" >
					<spring:message code="nombre"/>
				</label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<input type="text" name="apellido1" id="apellido1_filter_table"/>
				<label for="apellido1_filter_table">
					<spring:message code="apellido1"/>
				</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input type="text" name="apellido2" id="apellido2_filter_table"/>
				<label for="apellido2_filter_table">
					<spring:message code="apellido2"/>
				</label>
			</div>
			<!-- Fin campos del formulario de filtrado -->
		</div>
		<!-- Botonera del formulario de filtrado -->
		<div id="MultiPk_filter_buttonSet" class="right_buttons">
			<button id="MultiPk_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>
	        		<spring:message code="clear" />
	        	</span>
	        </button>
	        <button id="MultiPk_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>
	        		<spring:message code="filter" />
	        	</span>
	        </button>
		</div>
	</fieldset>
</form>