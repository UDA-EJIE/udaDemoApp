<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="aa79b-fade in" id="divReasignarTecnicoPestBusqueda">
		<h2>
			<spring:message code="label.reasignarTecnicoPestBusqueda" />
		</h2>
		<div id="reasignarTecnicoPestBusqueda_div" class="row">
			<div id="reasignarTecnicoPestBusqueda_feedback"></div>
			<div id="reasignarTecnicoPestBusqueda_toolbar"></div>
			<div id="reasignarTecnicoPestBusqueda_filter_div">
				<fieldset id="rechazarExp_fieldset" class="form_fieldset">
					<form id="reasignarTecnicoPestBusquedaform">
						<div class="form-group col-md-4">
							<label for="reasignarTecnicoAsignadoPestBusqueda" class="control-label"><spring:message code="label.asignadorAsignadoPlanifExp" />:</label>
							<select id="reasignarTecnicoAsignadoPestBusqueda" class="form-control" />
						</div>
					</form>
				</fieldset>
			</div>
		</div>
</div>