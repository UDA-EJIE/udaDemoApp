<%@include file="/WEB-INF/includeTemplate.inc"%>

<fieldset id="tecnico_expediente_filter_fieldset">
	<legend><spring:message code="label.tecnicoAsignadoDocumentacion"></spring:message></legend>
	<div class="row">
		<div class="form-group col-lg-12">
			<input type="text" name="tecnico.nombreCompleto" class="form-control" id="nombreApellidosTecnico" maxlength="150" readonly="readonly" disabled="disabled">
		</div>
	</div>
</fieldset>