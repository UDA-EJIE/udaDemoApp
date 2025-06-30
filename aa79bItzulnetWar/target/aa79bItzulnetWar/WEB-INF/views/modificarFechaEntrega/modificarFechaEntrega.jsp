<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divModificarFechaEntrega">
	<div id="modificarFechaEntrega">
		
		<h2><spring:message code="label.modificarFechaEntrega" /></h2>
		<div id="modificarFechaEntrega_div" class="rup-table-container ">
		<div id="modificarFechaEntrega_feedback"></div>
		<div id="modificarFechaEntrega_toolbar" class="mt-1"></div>
			<form id="modificarFechaEntregaform">
				<input type="hidden" name="anyo" class="form-control numeric" id="anyoExpediente_filter_table" /> 
				<input type="hidden" name="numExp" class="form-control numeric" id="numExpediente_filter_table" />
				<div id="modificarFechaEntrega_feedback"></div>
				<fieldset id="modificarFechaEntrega_fieldset" class="form_fieldset">
					<div class="p-2">
						<div class="row">
							<div class="form-group col-md-6 col-lg-6 grupoFechaHora">
								<label for="fechaEntrega" class="control-label" data-i18n="label.fechaHoraEntrega"><spring:message code="label.fechaHoraEntrega" /> :</label>
								<label id="fechaEntrega" class="control-label col-xs-12">${fechaHoraEntrega}</label>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6 col-lg-6 grupoFechaHora">
								<label class="control-label valFecha" for="fechaEntregaNueva" data-i18n="label.nuevaFechaHoraEntrega"><spring:message code="label.nuevaFechaHoraEntrega" /> (*):</label> 
								<input type="text" name="fechaEntrega" class="form-control" id="fechaEntregaNueva"> 
								<input type="text" name="horaEntrega" class="form-control campohora" id="horaEntregaNueva" placeholder="hh:mm" maxlength="5">
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>