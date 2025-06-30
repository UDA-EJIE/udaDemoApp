<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divDesgloseTareasConsultaPlanif">
	<div id="desgloseTareasConsultaPlanif_div" class="rup-table-container">
		<div id="desgloseTareasConsultaPlanif_toolbar"></div>
		<div id="desgloseTareasConsultaPlanif_feedback"></div>						<!-- Feedback de la tabla --> 					<!-- Botonera de la tabla --> 
		<div id="desgloseTareasConsultaPlanif_filter_div" class="mb-1 rup-table-filter oculto"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="desgloseTareasConsultaPlanif_filter_form" class="filterShadow filter">	
				<div id="desgloseTareasConsultaPlanif_filter_toolbar" class="formulario_legend" hidden = "hidden"></div>
				<input id="sIdsExpedientes" name="sIdsExpedientes" type="hidden"/>
			</form>
		</div>
		<div class="desgloseTareasConsultaPlanif_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="desgloseTareasConsultaPlanif" ></table>
			<!-- Barra de paginación -->
			<div id="desgloseTareasConsultaPlanif_pager"></div>
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