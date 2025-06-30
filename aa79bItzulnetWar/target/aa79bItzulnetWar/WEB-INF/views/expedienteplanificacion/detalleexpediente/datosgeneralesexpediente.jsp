<%@include file="/WEB-INF/includeTemplate.inc"%> 

<div id="capaPestanaCompletaAlta" class="container-fluid aa79b-fade in" style="padding:0 1.5rem"> 	
	<div id="divDatosGeneralesExpedienteForm">
			<div id="datosgeneralesexpedienteform_div">
				<div id="datosgeneralesexpediente_filter_div">
					<form id="datosgeneralesexpedienteform">
						<div id="datosgeneralesexpedienteform_feedback"></div>
						
						<input type="hidden" name="anyo" id="anyoExpediente">
						<input type="hidden" name="tecnico.dni" id="dniTecnico">
						<input type="hidden" name="observacionesVisibles" id="observacionesVisibles">
						
						<%@include file="/WEB-INF/views/expedienteplanificacion/detalleexpediente/datosExpedientesRelacionados.jsp"%>
						<%@include file="/WEB-INF/views/expedienteplanificacion/detalleexpediente/datosSolicitante.jsp"%>
						<%@include file="/WEB-INF/views/expedienteplanificacion/detalleexpediente/datosExpediente.jsp"%>
						<%@include file="/WEB-INF/views/expedienteplanificacion/detalleexpediente/datosExpedienteInterpretacion.jsp"%>
						<%@include file="/WEB-INF/views/expedienteplanificacion/detalleexpediente/datosExpedienteTradRev.jsp"%>
					
					</form>
				</div>
	
				<!-- Botonera del formulario de detalle -->
				<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
					<div class="ui-dialog-buttonset">
						<!-- Botón Guardar -->
						<button id="datosgeneralesexpedienteform_button_save" type="button" 
							class="ui-button ui-widget ui-state-default ui-corner-all">
							<spring:message code="next" />
						</button>
						<!-- Botón Cancelar -->
						<button id="datosgeneralesexpediente_cancel_cancelButton" type="button" 
							class="rup-enlaceCancelar">
							<spring:message code="cancel" />
						</button>
					</div>
				</div>
				
			</div>	
		
	</div>
</div>