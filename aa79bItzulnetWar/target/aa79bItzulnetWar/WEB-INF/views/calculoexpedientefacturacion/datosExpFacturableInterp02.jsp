<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="datosExpFacturableInterp02" class="row marginBot5 ">
	<div class="col-md-3 no-padding-right">
		<label class="" for="interpProgramada"><spring:message code="label.interpretacionProgramadaSimple"></spring:message>: </label>
		<input type="checkbox" class="form-control" id="interpProgramada" value="S" data-switch-pestana=true />
	</div>
	<div class="control-label col-md-9">
		<label for="direccionInterp" class="no-padding-left"><spring:message code="label.direccionInterpretacion"></spring:message>: </label>
		<label id="direccionInterp" class="control-label col-md-12 labelInput marginBot5"></label>
	</div>
	
</div>