<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="usuario_detail_div" class="rup-table-formEdit-detail">
	<!-- Barra de navegación del detalle -->
	<div id ="usuario_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form id="usuario_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="usuario_detail_feedback"></div>
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
					<input type="text" name="id" id="id_detail_table"/>
					<label for="id_detail_table"><spring:message code="id"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="nombre" id="nombre_detail_table"/>
					<label for="nombre_detail_table"><spring:message code="nombre"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="apellido1" id="apellido1_detail_table"/>
					<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="apellido2" id="apellido2_detail_table"/>
					<label for="apellido2_detail_table"><spring:message code="apellido2"/></label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
					<input type="checkbox" name="ejie" id="ejie_detail_table"/>			
					<label for="ejie_detail_table"><spring:message code="ejie"/></label>				
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="fechaAlta" id="fechaAlta_detail_table"/>
					<label for="fechaAlta_detail_table"><spring:message code="fechaAlta"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="fechaBaja" id="fechaBaja_detail_table"/>
					<label for="fechaBaja_detail_table"><spring:message code="fechaBaja"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="rol" id="rol_detail_table"/>
					<label for="rol_detail_table"><spring:message code="rol"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="fechaModif" id="fechaModif_detail_table"/>
					<label for="fechaModif_detail_table"><spring:message code="fechaModif"/></label>
				</div>
				<!-- Fin campos del formulario de detalle -->	
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material ui-helper-clearfix">
		<div class="text-right">
			<!-- Enlace cancelar -->
			<button id="usuario_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Botón Guardar -->
			<button id="usuario_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
		</div>
    </div>
</div>
