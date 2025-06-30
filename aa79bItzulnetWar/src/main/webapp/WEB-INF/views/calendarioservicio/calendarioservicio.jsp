<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divCalendarioServicios"> 
	<h2><spring:message code="menu.calendarioServicio"/></h2>
	<hr class="mb-2">
		<div id="calendarioservicio_div" class="rup-table-container">
			<div id="calendarioservicio_feedback"></div>						<!-- Feedback de la tabla --> 
			<div id="calendarioservicio_toolbar"></div>
			<div id ="calendarioservicio_navigation"></div>			<!-- Barra de navegación del detalle -->			
			<div class="row container">
				<!-- Campos del formulario de detalle -->
				<div class="form-group col-md-3 col-lg-2">
					<label for="anyoCalendario_detail_table" class="control-label"><spring:message code="label.anyoCalendario"/>:</label>
					<div class="divComboW125">
						<select name="anyoCalendario" id="anyoCalendario" class="form-control" style="clear:left"></select>					
					</div>
				</div>
				<div class="pt-20">
					<a href="#" style="font-size:1rem" id="enlaceInicializar"><spring:message code="label.inicializarcalendario"/></a>
				</div>
			</div>	
			<form id="calendarioservicio_detail_form">					<!-- Formulario -->
				<div id ="calendarioservicio_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
						
				<div id="capaOcultar">
					<div class="row container">
						<div class="form-group col-md-3 col-lg-2" style="clear: left;">							
							<label class="control-label" for="intensiva"><spring:message code="label.jornadaintensiva"/>:</label>
						</div>
						<div class="form-group col-md-4 col-lg-3">
							<label class="control-label" for="veranoDesde_detail_table"><spring:message code="label.veranoDesde"/> (*):</label>
							<input type="text" name="veranoDesde" class="form-control" id="veranoDesde" >
						</div>
						<div class="form-group col-md-4 col-lg-3">
							<label class="control-label" for="veranoHasta_detail_table"><spring:message code="label.veranoHasta"/> (*):</label>
							<input type="text" name="veranoHasta" class="form-control" id="veranoHasta" >
						</div>						
					</div>
					<div class="row container">
						<div class="form-group col-md-3 col-lg-2" style="clear: left;">		
						<label class="control-label" style="text-align: left;" for="festivos"><spring:message code="label.festivos"/>:</label>
						</div>
						<!-- Fin campos del formulario de detalle -->
						<div class="col-xs-12 calendarioServicio">
																							
								<div id="previewcalendario" class="calendarioSinActive"></div>
						</div>
					</div>
					
				</div>
					
			</form>
			
			<!-- Botonera del formulario de detalle -->
			<div id="capaBotones" class="rup-table-buttonpane ui-widget-content ui-helper-clearfix" style="clear:both">
				<div class="ui-dialog-buttonset">
					<!-- Botón Guardar -->
					<button id="calendarioservicio_detail_button_save" type="button">
						<spring:message code="save" />
					</button>
					<!-- Enlace cancelar -->
					<a href="#" id="calendarioservicio_link_cancel"
						class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
				</div>
			</div>
		</div>

</div>





