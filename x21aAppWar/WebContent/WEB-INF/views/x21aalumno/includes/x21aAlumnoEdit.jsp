<%@include file="/WEB-INF/includeTemplate.inc"%>

<!-- Formulario de detalle -->
<div id="x21aAlumno_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegación del detalle -->
	<div id ="x21aAlumno_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<!-- Formulario -->
		<form:form modelAttribute="alumno" id="x21aAlumno_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id="x21aAlumno_detail_feedback"></div>
			<!-- Campos del formulario de detalle -->
			<div class="form-row">
				<div class="form-group col-sm">
					<label for="id_detail_table"><spring:message code="id"/></label>
					<form:input path="id" class="formulario_linea_input form-control" id="id_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="usuario_detail_table"><spring:message code="usuario"/></label>
					<form:input path="usuario" class="formulario_linea_input form-control" id="usuario_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="password_detail_table"><spring:message code="password"/></label>
					<form:input path="password" class="formulario_linea_input form-control" id="password_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="nombre_detail_table"><spring:message code="nombre"/></label>
					<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
					<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="provinciaId_detail_table"><spring:message code="provinciaId"/></label>
					<form:input path="provincia.id" class="formulario_linea_input form-control" id="provinciaId_detail_table"/>
				</div>		
				<div class="form-group col-sm">
					<label for="comarcaId_detail_table"><spring:message code="comarcaId"/></label>
					<form:input path="comarca" class="formulario_linea_input form-control" id="comarcaId_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="localidadId_detail_table"><spring:message code="localidadId"/></label>
					<form:input path="localidad" class="formulario_linea_input form-control" id="localidadId_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="municipioId_detail_table"><spring:message code="municipioId"/></label>
					<form:input path="municipio.id" class="formulario_linea_input form-control" id="municipioId_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="autonomiaId_detail_table"><spring:message code="autonomiaId"/></label>
					<form:input path="autonomia.id" class="formulario_linea_input form-control" id="autonomiaId_detail_table"/>
				</div>
			</div>
		</form:form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_tableEdit_buttonsContainerResposive">
			<!-- Botón Guardar -->
			<button id="x21aAlumno_detail_button_save" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<!-- Botón cancelar -->
			<button id="x21aAlumno_detail_button_cancel"
				 class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button"><spring:message code="cancel" /></a>
		</div>
	</div>	
</div>
