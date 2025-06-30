<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="datosExpFacturableInterp05">
	<fieldset id="precioHoraFieldset">
		<legend>
			<spring:message code="label.tarifaAplicar"></spring:message>
		</legend>
		<label for="precioHoraInterprete" class="control-label col-md-4 precioHoraInLabel"><spring:message code="label.precioHoraYInterprete"></spring:message>: </label>
		<label id="precioHoraInterprete" class="control-label col-md-6 labelInput"></label>
	</fieldset>
	<fieldset id="precioInterpreteFieldset">
		<legend>
			<spring:message code="label.tarifaAplicar"></spring:message>
		</legend>
		<div class="row">
			<label for="precioJornadaCompleta" class="control-label col-md-4 preciosTarifasInterLabel"><spring:message code="label.precioJornadaCompleta"></spring:message>: </label>
			<label id="precioJornadaCompleta" class="control-label col-md-7 labelInput"></label>
		</div>
		<div class="row">
			<label for="precioMediaJornada" class="control-label col-md-4 preciosTarifasInterLabel"><spring:message code="label.precioJornadaMedia"></spring:message>: </label>
			<label id="precioMediaJornada" class="control-label col-md-7 labelInput"></label>
		</div>
		<div class="row">
			<label for="precioHoraFraccionAdic" class="control-label col-md-4 preciosTarifasInterLabel"><spring:message code="label.precioHoraOFrac"></spring:message>: </label>
			<label id="precioHoraFraccionAdic" class="control-label col-md-7 labelInput"></label>
		</div>
	</fieldset>
</div>