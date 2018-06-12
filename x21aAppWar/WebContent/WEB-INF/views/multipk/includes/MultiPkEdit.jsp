<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="MultiPk_detail_div" class="rup-table-formEdit-detail">
	<div id ="MultiPk_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="MultiPk_detail_form">					<!-- Formulario -->
			<div id ="MultiPk_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="floating_left_pad_right">
			
				<!-- Campos del formulario de detalle -->
				<div class="floating_left_pad_right one-column">
					<label for="ida_detail_table"><spring:message code="ida"/>:</label>
					<input type="text" name="ida" class="formulario_linea_input form-control" id="ida_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="idb_detail_table"><spring:message code="idb"/>:</label>
					<input type="text" name="idb" class="formulario_linea_input form-control" id="idb_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="nombre_detail_table"><spring:message code="nombre"/>:</label>
					<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="apellido1_detail_table"><spring:message code="apellido1"/>:</label>
					<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="apellido2_detail_table"><spring:message code="apellido2"/>:</label>
					<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_detail_table"/>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="MultiPk_detail_button_save" class="btn btn-outline-success" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<button id="MultiPk_detail_link_cancel" class="btn btn-outline-danger" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
