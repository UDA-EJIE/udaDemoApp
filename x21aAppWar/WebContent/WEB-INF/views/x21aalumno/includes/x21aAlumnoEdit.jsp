<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="x21aAlumno_detail_div" class="rup-table-formEdit-detail">
	<!-- Barra de navegaciÃ³n del detalle -->
	<div id ="x21aAlumno_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<!-- Formulario -->
		<form id="x21aAlumno_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="x21aAlumno_detail_feedback"></div>
			<div class="form-row">
			
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
					<input type="text" name="id" id="id_detail_table"/>
					<label for="id_detail_table"><spring:message code="id"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="usuario" id="usuario_detail_table"/>
					<label for="usuario_detail_table"><spring:message code="usuario"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="password" id="password_detail_table"/>
					<label for="password_detail_table"><spring:message code="password"/></label>
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
					<input type="text" name="provinciaId" id="provinciaId_detail_table"/>
					<label for="provinciaId_detail_table"><spring:message code="provinciaId"/></label>
				</div>
				
				<div class="form-groupMaterial col-sm">
					<input type="text" name="comarcaId" id="comarcaId_detail_table"/>
					<label for="comarcaId_detail_table"><spring:message code="comarcaId"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="localidadId" id="localidadId_detail_table"/>
					<label for="localidadId_detail_table"><spring:message code="localidadId"/></label>
				</div>

				<div class="form-groupMaterial col-sm">
					<input type="text" name="municipioId" id="municipioId_detail_table"/>
					<label for="municipioId_detail_table"><spring:message code="municipioId"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<input type="text" name="autonomiaId" id="autonomiaId_detail_table"/>
					<label for="autonomiaId_detail_table"><spring:message code="autonomiaId"/></label>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<!-- Botón cancelar -->
			<button id="x21aAlumno_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Botón guardar -->
			<button id="x21aAlumno_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
		</div>
	</div>
</div>
