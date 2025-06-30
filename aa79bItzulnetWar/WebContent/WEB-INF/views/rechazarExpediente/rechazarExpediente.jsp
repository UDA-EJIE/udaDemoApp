<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divRechazarExpedientes">
	<div id="rechazarExpedientes">
		
		<h2><spring:message code="menu.rechazarExp" /></h2>
		<div id="rechazarExpedientes_div" class="rup-table-container ">
		<div id="rechazarExpedientes_feedback"></div>
		<div id="rechazarExpedientes_toolbar" class="mt-1"></div>
			<form id="rechazarExpform">
				<div id="rechazarExp_feedback"></div>
				<fieldset id="rechazarExp_fieldset" class="form_fieldset">
					<div class="p-2">
						<div class="form-group col-md-3">
							<label for="motivoRechazo" class="control-label" data-i18n="label.motivoRechazo"><spring:message code="label.motivoRechazo" /> (*):</label>
							<input id="motivoRechazo" name="motivoRechazo" class="form-control" />
						</div>
						<div class="form-group col-md-9">
							<label class="control-label" for="descRechazo" data-i18n="comun.descripcion"><spring:message code="comun.descripcion"/>:</label>
							<textarea class="form-control resizable-textarea" id="descRechazo" name="descRechazo" rows="6" maxlength="4000" ></textarea>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>