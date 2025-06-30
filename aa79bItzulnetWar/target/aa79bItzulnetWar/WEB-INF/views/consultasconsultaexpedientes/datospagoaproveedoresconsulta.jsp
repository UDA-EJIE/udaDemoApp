<%@include file="/WEB-INF/includeTemplate.inc"%>

<div  id="divDatosPagoAProveedoresConsulta">
	<h2>
		<spring:message code="label.datosPagoAProveedoresConsulta" />
	</h2>
	<div id="datosPagoAProveedoresConsulta_div" class="row">
		<div id="datosPagoAProveedoresConsulta_feedback"></div>
		<div id="datosPagoAProveedoresConsulta_toolbar"></div>
		<div id="datosPagoAProveedoresConsulta_filter_div" style="display: none;">
			<form id="datosPagoAProveedoresConsulta_filter_form">
				<div id="datosPagoAProveedoresConsulta_filter_toolbar" class="formulario_legend"></div>
					<fieldset id="datosPagoAProveedoresConsulta_filter_fieldset" class="rup-table-filter-fieldset ">
						<div class="p-2">
							<input type="hidden" id="datosPagoProvConsultaAnyo" name="anyo">
							<input type="hidden" id="datosPagoProvConsultaNumExp" name="numExp">
							<input type="hidden" id="datosPagoProvConsultaIdTipoExp" name="idTipoExpediente">
						</div>	
					</fieldset>
			</form>
		</div>
		<div class="datosPagoAProveedoresConsulta_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="datosPagoAProveedoresConsulta"></table>
			<!-- Barra de paginación -->
			<div id="datosPagoAProveedoresConsulta_pager"></div>
		</div>
	</div>
</div>