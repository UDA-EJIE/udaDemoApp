<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divAnularExpedientes" class="container-fluid aa79b-fade in">
	<h2><spring:message code="titulo.anularExpedientes"></spring:message></h2>
	<div id="anularExpedientes_div" class="rup-table-container">
		<div id="anularExpedientes_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="anularExpedientes_toolbar"></div>						<!-- Botonera de la tabla --> 
		<div id="anularExpedientes_filter_div"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="anularExpedientes_filter_form">	
				<fieldset id="anularExpedientes_filter_fieldset" class="form_fieldset ">
					<div class="rup-table-filter-fieldset">
						<legend><spring:message code="label.anulacionExpedientes"/></legend>	
					</div>
					<div class="row">
						<div class="form-group col-lg-5">
							<label for="idMotivosAnulacion_detail_table" class="control-label"><spring:message code="label.motivosAnulacion" /> (*):</label>
							<select  name="idMotivoAnulacion" class="form-control" id="idMotivosAnulacion_detail_table" ></select>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-12">
							<label for="observaciones_detail_table" class="control-label"><spring:message code="label.observaciones" />:</label>
							<textarea name="observacionesAnulExp.obsvAnulacion" class="form-control resizable-textarea" id="observaciones_detail_table" rows="4" cols="9" maxlength="4000"></textarea>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>