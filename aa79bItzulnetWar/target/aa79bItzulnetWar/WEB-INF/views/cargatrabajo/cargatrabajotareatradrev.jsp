<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divCargaTrabajoTareas">
		<div id="busquedaGeneral_div" class="rup-table-container">
		<div id="busquedaGeneral_filter_div" class="rup-table-filter oculto"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="busquedaGeneral_filter_form" class="filterShadow filter">	
				<div id="busquedaGeneral_filter_toolbar" class="formulario_legend filterCabecera"></div>
				<div class="formulario_columna_cnt">
					<input type="hidden" name="tipo" id="tipo" value="">
				</div>
			</form>
		</div>
		<div class="busquedaGeneral_grid_div horizontal_scrollable_table" style="width: 100% !important;">
			<!-- Tabla -->
			<table id="busquedaGeneral"></table>
			<!-- Barra de paginación -->
			<div id="busquedaGeneral_pager"></div>
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