<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="MultiPk_detail_div" class="rup-table-formEdit-detail">
	<!-- Barra de navegación del detalle -->
	<div id ="MultiPk_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form id="MultiPk_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="MultiPk_detail_feedback"></div>
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
					<input type="text" name="ida" id="ida_detail_table"/>
					<label for="ida_detail_table"><spring:message code="ida"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="idb" id="idb_detail_table"/>
					<label for="idb_detail_table"><spring:message code="idb"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="nombre" id="nombre_detail_table"/>
					<label for="nombre_detail_table"><spring:message code="nombre"/></label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
					<input type="text" name="apellido1" id="apellido1_detail_table"/>
					<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="apellido2" id="apellido2_detail_table"/>
					<label for="apellido2_detail_table"><spring:message code="apellido2"/></label>
				</div>
				<!-- Fin campos del formulario de detalle -->
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material ui-helper-clearfix">
		<div class="text-right">
			<!-- Enlace cancelar -->
			<button id="MultiPk_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Botón Guardar -->
			<button id="MultiPk_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
		</div>
	</div>
</div>
