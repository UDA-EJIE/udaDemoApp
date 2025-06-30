<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divExpedienteTradRev">
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
			<div class="form-group col-lg-3">
				<label class="control-label" for="indSolicitadoTramitagune"><spring:message code="label.solicitadoTramitagune"/>:</label>
				<input type="checkbox" name="expedienteTradRev.indSolicitadoTramitagune" id="indSolicitadoTramitagune" value="S" data-switch-pestana="true">
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
		<div id="divSeccionObservaciones" class="row">
			<div class="col-lg-4">
				<label class="control-label" for="observacionesExpTradRev">
					<spring:message code="label.observacionesSolicitud"/>:
					<div id="observaciones_mostrarLink_div" class="inline">
						(<a id="observaciones_mostrarLink" href="#" class="control-label"><spring:message code="label.mostrar" /></a>)
					</div>
				</label>
			</div>
		</div>
		<div id="divDetalleObservaciones">
			<div class="row">
				<div class="form-group col-lg-12">
					<span id="enlaceDescargaDetalle_2" style="display:contents;"></span> 
					<div id="capaBtnPID" class="inline">
						<button id="pidButton_2" type="button" class="ui-button ui-corner-all ui-widget no-obligatorio" style="margin-top:-4px; height:22px"><spring:message code="label.adjuntarFichero"/></button>
						<input type="text" name="expedienteTradRev.observaciones.nombreUpload" class="form-control" id="nombreFicheroInfo_2" readonly="readonly" style="width:200px; display:inline-block"/>
					</div>
					<div style="display:inline-flex"><button id="btnEliminarObserv" type="button" class="rup-enlaceCancelar oculto"><spring:message code="eliminar" /></button></div>
				</div>
				<input type="hidden" name="expedienteTradRev.observaciones.rutaPif" id="rutaPif_2"/>
				<input type="hidden" name="expedienteTradRev.observaciones.nombre" id="nombre_2" readonly="readonly"/>
				<input type="hidden" name="expedienteTradRev.observaciones.oidFichero" id="oidFichero056_2" readonly="readonly"/>
				<input type="hidden" name="expedienteTradRev.observaciones.extension" id="extensionDoc056_2" readonly="readonly"/>
				<input type="hidden" name="expedienteTradRev.observaciones.contentType" id="contentType056_2" readonly="readonly"/>
				<input type="hidden" name="expedienteTradRev.observaciones.tamano" id="tamanoDoc056_2" readonly="readonly"/>
			</div>
			<div class="row">
				<div class="form-group col-lg-12">
					<textarea name="expedienteTradRev.observaciones.observaciones" class="form-control resizable-textarea" id="observacionesExpTradRev" rows="1" cols="9" maxlength="4000"></textarea>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-5">
				<label class="control-label" for="refTramitagune"><spring:message code="label.referenciaTramitagune"/>:</label>
				<input type="text" name="expedienteTradRev.refTramitagune" class="form-control" id="refTramitagune" maxlength="50">
			</div>
		</div>
	</fieldset>
</div>