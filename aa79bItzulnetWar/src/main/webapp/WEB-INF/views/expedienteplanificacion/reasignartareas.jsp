<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divReasignarTareas">
	<div id="reasignarTareas">
		<h2><spring:message code="comun.reasignacionDeTareas"/></h2>
		<div class="rup-table-container ">
		<div id="reasignarTareas_feedback"></div>
		<div id="reasignarTareas_toolbar"></div>
		<div id="reasignarTareas_filter_div" class="mb-1 rup-table-filter"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="reasignarTareas_filter_form" class="filterShadow filter">	
				<div id="estudioEstimado_filter_toolbar" class="formulario_legend" hidden="hidden"></div>
				<fieldset id="reasignarTareas_filter_fieldset" class="rup-table-filter-fieldset filterCuerpo">
					<div class="p-2">
					<div class="row">
						<input type="hidden" id="tipoEntidad" name="entidad.tipo"/>
						<input type="hidden" id="idEntidad" name="entidad.codigo"/>
						<input type="hidden" id="dniReasignacion" name="dni"/>
						<div class="form-group col-md-8 col-xl-8 ">
							<label for="asignadoA" class="control-label col-md-12 p-0" data-i18n="label.asignadoA"><spring:message code="label.asignadoA"/> (*):</label>
							 <div class="input-group">
								<input type="text" id="asignadoA" name="nombre" class="form-control" disabled="disabled" readOnly="readonly"/>
		                    	<span class="input-group-addon autoasignarLink_div" id="divLinkAutoasignar"><a href="#" id="autoasignarLink" class="linkReasignacion"><spring:message code="label.autoasignar"/></a></span>
		                   	 	<span class="input-group-addon personalIzoLink_div" id="divLinkRecursoInterno"><a href="#" id="personalIzoLink" class="linkReasignacion"><spring:message code="label.personalIzo"/></a></span>
							</div>
						</div>
						<!-- <div class="form-group col-md-3 col-xl-1 ">
							<div id="personalIzoLink_div" class="link">
								<a id="personalIzoLink" href="#" class="rup-enlaceCancelar izda enlace"><spring:message code="label.personalIzo" /></a>
							</div>
						</div>
						<div class="form-group col-md-3 col-xl-1 ">
							<div id="autoasignarLink_div" class="link">
								<a id="autoasignarLink" href="#" class="rup-enlaceCancelar izda enlace"><spring:message code="label.autoasignar" /></a>
							</div>
						</div>-->
					</div>
					</div>
					<div>
						<legend>(*) <spring:message code="label.tareasEjecutadaNoReasignable"/></legend>
					</div>
				</fieldset>
			</form>
		</div>
		</div>
	</div>
	
	<div id="buscadorIZO" class="oculto"></div>
	
</div>