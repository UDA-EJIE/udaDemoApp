<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divDesgloseTareas">
	<div id="desgloseTareas_div" class="rup-table-container ">
		<div id="desgloseTareas_toolbar"></div>
		<div id="desgloseTareas_feedback"></div>						<!-- Feedback de la tabla --> 					<!-- Botonera de la tabla --> 
		<div id="desgloseTareas_filter_div" class="mb-1 rup-table-filter"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="desgloseTareas_filter_form" class="filterShadow filter">	
				<div id="desgloseTareas_filter_toolbar" class="formulario_legend" hidden = "hidden"></div>
				<input id="sIdsExpedientes" name="sIdsExpedientes" type="hidden"/>
			</form>
		</div>
		<div class="desgloseTareas_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="desgloseTareas" ></table>
			<!-- Barra de paginación -->
			<div id="desgloseTareas_pager"></div>
		</div>
		<br/>
		<div class="row form-group">
			<div class="col-xs-12">	
					<i class='fa fa-exclamation-circle'></i>
				<label class="control-label">
					<spring:message code="comun.recursoNoDisponible"/>
				</label>
			</div>
		</div>
	</div>
</div>