<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="x21aAlumno_detail_div" class="rup-table-formEdit-detail">
	<div id ="x21aAlumno_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="x21aAlumno_detail_form">					<!-- Formulario -->
			<div id ="x21aAlumno_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="form-row">
			
				<!-- Campos del formulario de detalle -->
				<div class="form-group col-sm">
					<label for="id_detail_table"><spring:message code="id"/></label>
					<input type="text" name="id" class="formulario_linea_input form-control" id="id_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="usuario_detail_table"><spring:message code="usuario"/></label>
					<input type="text" name="usuario" class="formulario_linea_input form-control" id="usuario_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="password_detail_table"><spring:message code="password"/></label>
					<input type="text" name="password" class="formulario_linea_input form-control" id="password_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="nombre_detail_table"><spring:message code="nombre"/></label>
					<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
					<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="provinciaId_detail_table"><spring:message code="provinciaId"/></label>
					<input type="text" name="provinciaId" class="formulario_linea_input form-control" id="provinciaId_detail_table"/>
				</div>
				
				<div class="form-group col-sm">
					<label for="comarcaId_detail_table"><spring:message code="comarcaId"/></label>
					<input type="text" name="comarcaId" class="formulario_linea_input form-control" id="comarcaId_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="localidadId_detail_table"><spring:message code="localidadId"/></label>
					<input type="text" name="localidadId" class="formulario_linea_input form-control" id="localidadId_detail_table"/>
				</div>

				<div class="form-group col-sm">
					<label for="municipioId_detail_table"><spring:message code="municipioId"/></label>
					<input type="text" name="municipioId" class="formulario_linea_input form-control" id="municipioId_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="autonomiaId_detail_table"><spring:message code="autonomiaId"/></label>
					<input type="text" name="autonomiaId" class="formulario_linea_input form-control" id="autonomiaId_detail_table"/>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_jqtableEdit_buttonsContainerResposive">
			<!-- Botón Guardar -->
			<button id="x21aAlumno_detail_button_save" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<!-- Botón cancelar -->
			<button id="x21aAlumno_detail_button_cancel"
				 class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button"><spring:message code="cancel" /></a>
		</div>
	</div>	
</div>
