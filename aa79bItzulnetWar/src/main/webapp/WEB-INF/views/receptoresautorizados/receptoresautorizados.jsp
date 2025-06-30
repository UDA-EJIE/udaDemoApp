<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divReceptoresAutorizados" class="container-fluid aa79b-fade collapsed capaExpedienteMYO">
	<h2><spring:message code="menu.receptoresAutorizados"/></h2>
	<hr class="mb-2">
	<div id="receptoresAutorizados_div" class="rup-table-container">
		<div id="receptoresAutorizados_feedback"></div> <!-- Feedback de la tabla -->	
		<div id="receptoresAutorizados_toolbar"></div> <!-- Botonera de la tabla -->
		<div id="receptoresAutorizados_filter_div" class="rup-table-filter" style="display:none">
			<form id="receptoresAutorizados_filter_form"> <!-- Formulario de filtrado -->
				<div id="receptoresAutorizados_filter_toolbar" class="formulario_legend"></div>	Barra de herramientas del formulario de filtrado
				<fieldset id="receptoresAutorizados_filter_fieldset" class="rup-table-filter-fieldset">
						<div class="row">
							<!-- Campos del formulario de filtrado -->
								<input type="hidden" name="anyo" class="form-control numeric" id="anyoreceptoresAutorizados_filter_table" /> 
								<input type="hidden" name="numExp" class="form-control numeric" id="numExpreceptoresAutorizados_filter_table" />
							<!-- Fin campos del formulario de filtrado -->
						</div>
						<div id="receptoresAutorizados_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<input id="receptoresAutorizados_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
							<!-- Enlace de limpiar -->
							<a id="receptoresAutorizados_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
				</fieldset>
			</form>
		</div>
		<div class="receptoresAutorizados_grid_div ">
			<!-- Tabla -->
			<table id="receptoresAutorizados"></table>
			<!-- Barra de paginación -->
			<div id="receptoresAutorizados_pager"></div>
		</div>
	</div>
	
<div id="buscadorPersonasReceptores" class="oculto"></div>
</div>