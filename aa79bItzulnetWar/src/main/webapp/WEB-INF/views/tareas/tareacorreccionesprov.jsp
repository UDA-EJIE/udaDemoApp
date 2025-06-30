<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="notificarCorreccionProv_detail_div">
		<div id="notificarCorreccionProv_detail_toolbar"></div>			<!-- Toolbar -->
		<div id="notificarCorreccionProv_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
		<form id="notificarCorreccionProv_detail_form">					<!-- Formulario -->
			<div class="col-xs-12">
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="documentacion_detail_table" class="control-label col-xs-4"><spring:message code="label.documentos"/>:</label>	
						<div class="col-xs-12">
												
							<div class="form-group col-lg-12 padding-top-2">	
								<span id="enlaceDescargaDetalle_0" class="col-lg-12"></span>
							</div>
						</div>
					</div>
					<div class="form-group col-lg-6">
						<label for="obsCorreccionProv_detail_table" class="control-label col-xs-12"><spring:message code="label.observaciones"/>:</label>
						<div class="col-xs-12">
							<textarea  name="observaciones" class="form-control" id="obsCorreccionProv_detail_table" style="resize: none" rows="6" maxlength="2000" readonly="readonly" disabled="disabled"></textarea>
						</div>	
					</div>
				</div>	
			</div>
		</form>
	</div>
</div>