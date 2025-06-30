<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divCargaTrabajoTareas">
		<div id="busquedaGeneralInter_div" class="rup-table-container">
		<div id="busquedaGeneralInter_filter_div" class="rup-table-filter oculto"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="busquedaGeneralInter_filter_form" class="filterShadow filter">	
				<div id="busquedaGeneralInter_filter_toolbar" class="formulario_legend filterCabecera"></div>
				<fieldset id="busquedaGeneralInter_filter_fieldset"
							class="rup-table-filter-fieldset filterCuerpo">
				</fieldset>
			</form>
		</div>
		<div class="busquedaGeneralInter_grid_div horizontal_scrollable_table" style="width: 100% !important;">
			<!-- Tabla -->
			<table id="busquedaGeneralInter"></table>
			<!-- Barra de paginación -->
			<div id="busquedaGeneralInter_pager"></div>
		</div>
		
		<div class="row form-group">
			<div class="col-xs-4">
				<div class="col-xs-1">
					<i class="fa fa-star" aria-hidden="true" style="color: #ba1944;"></i>
				</div>
				<label class="control-label col-xs-10">
					<spring:message code="label.prioridadIconDesc" />
				</label>
			</div>
		</div>
		<div class="row form-group">
			<div class="col-xs-4">
				<div class="col-xs-1">
					<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-10">
					<spring:message code="label.noDisponible" />
				</label>
			</div>
		</div>
	</div>
</div>