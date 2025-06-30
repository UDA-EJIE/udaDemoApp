<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divCargaTrabajoTareas">
		<div id="busquedaGeneralTrabajo_div" class="rup-table-container">
		<div id="busquedaGeneralTrabajo_filter_div" class="rup-table-filter oculto"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="busquedaGeneralTrabajo_filter_form" class="filterShadow filter">	
				<div id="busquedaGeneralTrabajo_filter_toolbar" class="formulario_legend filterCabecera"></div>
				<fieldset id="busquedaGeneralTrabajo_filter_fieldset"
							class="rup-table-filter-fieldset filterCuerpo">
				</fieldset>
			</form>
		</div>
		<div class="busquedaGeneralTrabajo_grid_div horizontal_scrollable_table" style="width: 100% !important;">
			<!-- Tabla -->
			<table id="busquedaGeneralTrabajo"></table>
			<!-- Barra de paginación -->
			<div id="busquedaGeneralTrabajo_pager"></div>
		</div>
		
		
	</div>
</div>