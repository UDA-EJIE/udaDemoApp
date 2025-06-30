<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="datosExpFacturableInterp01">
	<div class="control-label col-md-3">
		<label for="tipoPeticionario" class="no-padding-left"><spring:message code="label.tipoPeticionario"></spring:message>: </label>
		<label id="tipoPeticionario" class="control-label col-md-12 labelInput"></label>
	</div>
	<div class="control-label col-md-2">
		<label for="tipoActo" class="no-padding-left"><spring:message code="label.tipoActo"></spring:message>: </label>
		<label id="tipoActo" class="control-label col-md-12 labelInput"></label>
	</div>
	
	<div class="col-md-2 no-padding-right">
		<label class="" for="interpPresupuesto"><spring:message code="label.presupuesto"></spring:message>: </label>
		<input type="checkbox" class="form-control" id="interpPresupuesto" value="S" data-switch-pestana=true />
	</div>
	
	<div class="col-md-2 no-padding-right">
		<label class="" for="enCae"><spring:message code="label.enLaCae"></spring:message>: </label>
		<input type="checkbox" class="form-control" id="enCae" value="S" data-switch-pestana=true />
	</div>
</div>