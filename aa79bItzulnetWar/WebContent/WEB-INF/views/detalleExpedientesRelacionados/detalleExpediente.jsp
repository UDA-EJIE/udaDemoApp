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
						
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/datosExpedientesRelacionados.jsp"%>
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/datosSolicitante.jsp"%>
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/datosExpediente.jsp"%>
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/datosExpedienteInterpretacion.jsp"%>
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/datosExpedienteTradRev.jsp"%>
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/documentos.jsp"%>
						<%@include file="/WEB-INF/views/detalleExpedientesRelacionados/datosTecnico.jsp"%>
					
					</form>
				</div>
			</div>	
	</div>
	<div class="row form-group" id="leyendaPlanificacion" style="padding-left: 10px;">
			<div class="col-xs-8">
				<div class="col-xs-1" style="width: 35px!important;">
					<i class="fa fa-exclamation-circle iconoGestorNoActivo" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-10 textoGestorNoActivo">
					<spring:message code="label.gestorInactivo"/>
				</label>
			</div>
		</div>
</div>