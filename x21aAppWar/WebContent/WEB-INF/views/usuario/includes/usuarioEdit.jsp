<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="usuario_detail_div" class="rup-table-formEdit-detail">
	<div id ="usuario_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="usuario_detail_form">					<!-- Formulario -->
			<div id ="usuario_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="floating_left_pad_right">
			
				<!-- Campos del formulario de detalle -->
				<div class="floating_left_pad_right one-column">
					<label for="id_detail_table"><spring:message code="id"/>:</label>
					<input type="text" name="id" class="formulario_linea_input" id="id_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="nombre_detail_table"><spring:message code="nombre"/>:</label>
					<input type="text" name="nombre" class="formulario_linea_input" id="nombre_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="apellido1_detail_table"><spring:message code="apellido1"/>:</label>
					<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="apellido2_detail_table"><spring:message code="apellido2"/>:</label>
					<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="ejie_detail_table"><spring:message code="ejie"/>:</label>
					<input type="checkbox" name="ejie" class="formulario_linea_input" id="ejie_detail_table"/>							
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="fechaAlta_detail_table"><spring:message code="fechaAlta"/>:</label>
					<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="fechaBaja_detail_table"><spring:message code="fechaBaja"/>:</label>
					<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="rol_detail_table"><spring:message code="rol"/>:</label>
					<input type="text" name="rol" class="formulario_linea_input" id="rol_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="fechaModif_detail_table"><spring:message code="fechaModif"/>:</label>
					<input type="text" name="fechaModif" class="formulario_linea_input" id="fechaModif_detail_table"/>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="usuario_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="usuario_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
