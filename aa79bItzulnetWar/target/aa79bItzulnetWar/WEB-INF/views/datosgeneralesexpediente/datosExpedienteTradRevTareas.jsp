<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divExpedienteTradRevTareas">
	<fieldset id="datos_expediente_trad_rev_filter_fieldset">
		<legend>
			<spring:message code="label.datosExpedienteTradRev"></spring:message>
		</legend>
		<div class="row">
			<input type="hidden" name="expedienteTradRev.indObservaciones" id="indObservacionesExpTradRev">
			<div class="form-group col-lg-2">
				<label class="control-label col-xs-12 no-padding-left" for="indPublicarBopv"><spring:message code="label.bopv"/> (*):</label>
				<input type="checkbox" name="expedienteTradRev.indPublicarBopv" id="indPublicarBopv" value="S" data-switch-pestana="true">
			</div>
			<div class="form-group col-lg-3">
				<label class="control-label" for="indPrevistoBopv"><spring:message code="label.previstoBopv"/>:</label>
				<input type="checkbox" name="expedienteTradRev.indPrevistoBopv" id="indPrevistoBopv" value="S" data-switch-pestana="true">
			</div>
			<div class="form-group col-lg-2">
				<label class="control-label" for="idiomaDestino"><spring:message code="label.idiomaDestino"/> (*):</label>
				<div class="divComboIdiomaDestino">
					<select name="expedienteTradRev.idIdioma" id="idiomaDestino" class="form-control">
					</select>
				</div>
			</div>
			<div class="form-group col-lg-2">
				<label class="control-label col-xs-12 no-padding-left" for="indConfidencial"><spring:message code="label.indConfidencial"/> (*):</label>
				<input type="checkbox" name="expedienteTradRev.indConfidencial" id="indConfidencial" value="S" data-switch-pestana="true">
			</div>
			<div class="form-group col-lg-2">
				<label class="control-label" for="indCorredaccion"><spring:message code="label.indCorredaccion"/>:</label>
				<input type="checkbox" name="expedienteTradRev.indCorredaccion" id="indCorredaccion" value="S" data-switch-pestana="true" disabled="disabled">
			</div>
		</div>
		<div id="divDatosCorredacccion">
			<fieldset>
				<legend>
					<spring:message code="label.datosCorredaccion"></spring:message>
				</legend>
				<div class="row">
					<div class="form-group col-lg-9">
						<label class="control-label" for="texto"><spring:message code="label.texto"/> (*):</label>
						<input type="text" name="expedienteTradRev.texto" class="form-control" id="texto" maxlength="250">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-lg-9">
						<label class="control-label" for="tipoRedaccion"><spring:message code="label.tipoRedaccionBilingue"/> (*):</label>
						<input type="text" name="expedienteTradRev.tipoRedaccion" class="form-control" id="tipoRedaccion" maxlength="250">
					</div>
					<div class="form-group col-lg-9">
						<label class="control-label" for="participantes"><spring:message code="label.participantes"/> (*):</label>
						<textarea name="expedienteTradRev.participantes" class="form-control" id="participantes" rows="4" cols="9" maxlength="2000"></textarea>
					</div>
				</div>
			</fieldset>
		</div>
		<div class="row">
			<div class="form-group col-lg-5">
				<label class="control-label" for="refTramitagune"><spring:message code="label.referenciaTramitagune"/>:</label>
				<input type="text" name="expedienteTradRev.refTramitagune" class="form-control" id="refTramitagune" maxlength="50">
			</div>
			<div class="form-group col-lg-2">
				<label class="control-label col-xs-12 no-padding-left" for="inBoe"><spring:message code="label.boe"/>:</label>
				<input type="checkbox" name="expedienteTradRev.indConfidencial" id="inBoe" value="S" data-switch-pestana="true">
			</div>
			<div class="form-group col-lg-5">
				<label class="control-label" for="urlBoe"><spring:message code="label.urlBoe"/>:</label>
				<input type="text" name="expedienteTradRev.refTramitagune" class="form-control" id="urlBoe" >
			</div>
		</div>
	</fieldset>
</div>