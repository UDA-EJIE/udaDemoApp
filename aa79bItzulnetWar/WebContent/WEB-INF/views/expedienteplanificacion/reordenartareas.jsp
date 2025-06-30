<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divReordenarTareasExp">
	<div id="reordenarTareasExp">
		<h2><spring:message code="comun.reordenarTareasExpediente"/></h2>
		<div id="reordenarTareasExp_div" class="rup-table-container ">
		<div id="reordenarTareasExp_feedback"></div>
		<div id="reordenarTareasExp_toolbar" class="mt-1"></div>			
			<div id="reordenarTareasExpForm_div" class="rup-table-container aliniarForm">
				<div id="reordenarTareasExpForm_filter_div" class="rup-table-filter" hidden="hidden"> <!-- Capa contenedora del formulario de filtrado -->
					<div id="reordenarTareasExpForm_feedback"></div>	
					<form id="reordenarTareasExpForm_filter_form">	
						<div id="reordenarTareasExpForm_filter_toolbar" class="formulario_legend"></div>
						<fieldset id="reordenarTareasExpForm_filter_fieldset" class="rup-table-filter-fieldset">
							<div class="formulario_columna_cnt">
								<input type="hidden" name="anyo" id="anyo_detail_filter_table" >
								<input type="hidden" name="numExp" id="numExp_detail_filter_table" >
							</div>
						</fieldset>
					</form>
				</div>
				<div class="reordenarTareasExpForm_grid_div horizontal_scrollable_table" >
					<input type="hidden" name="anyo" id="anyo_detail_filter_table" >
					<input type="hidden" name="numExp" id="numExp_detail_filter_table" >
					<!-- Tabla -->
					<table id="reordenarTareasExpForm" ></table>
					<!-- Barra de paginación -->
					<div id="reordenarTareasExpForm_pager" class="oculto"></div>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-12">	
					<div class="col-xs-1 legendOnBottom">				
						<i class='fa fa-exclamation-circle'></i>
					</div>	
					<label class="control-label col-xs-11">
						<spring:message code="label.ultimasTareasEntregaCliente"/>
					</label>
				</div>
			</div>
		</div>
	</div>
</div>