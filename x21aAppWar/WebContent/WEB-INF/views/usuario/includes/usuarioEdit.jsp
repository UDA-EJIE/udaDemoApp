<%@include file="/WEB-INF/includeTemplate.inc"%>


<!-- Formulario de detalle -->
<div id="usuario_detail_div" class="rup-table-formEdit-detail">
	<div id ="usuario_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="usuario_detail_form">					<!-- Formulario -->
			<div id ="usuario_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="form-row">
			
				<!-- Campos del formulario de detalle -->
				<div class="form-group col-sm">
					<label for="id_detail_table" class="formulario_linea_label"><spring:message code="id"/></label>
					<input type="text" name="id" class="formulario_linea_input form-control" id="id_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="nombre_detail_table" class="formulario_linea_label"><spring:message code="nombre"/></label>
					<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="apellido1_detail_table" class="formulario_linea_label"><spring:message code="apellido1"/></label>
					<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="apellido2_detail_table" class="formulario_linea_label"><spring:message code="apellido2"/></label>
					<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_detail_table"/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm">
					<label for="ejie_detail_table" class="formulario_linea_label"><spring:message code="ejie"/></label>
					<input type="checkbox" name="ejie" class="formulario_linea_input form-control" id="ejie_detail_table"/>							
				</div>
				<div class="form-group col-sm">
					<label for="fechaAlta_detail_table" class="formulario_linea_label"><spring:message code="fechaAlta"/></label>
					<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="fechaBaja_detail_table" class="formulario_linea_label"><spring:message code="fechaBaja"/></label>
					<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="rol_detail_table" class="formulario_linea_label"><spring:message code="rol"/></label>
					<input type="text" name="rol" class="formulario_linea_input form-control" id="rol_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="fechaModif_detail_table" class="formulario_linea_label"><spring:message code="fechaModif"/></label>
					<input type="text" name="fechaModif" class="formulario_linea_input form-control" id="fechaModif_detail_table"/>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div id="table_filter_buttonSet" class="right_buttons">
		<!-- Enlace cancelar -->
        <button id="usuario_detail_button_cancel" type="button" class="btn btn-warning rup-limpiar">
        	<i class="fa fa-eraser"></i>
        	<span>
        		<spring:message code="clear" />
        	</span>
        </button>
        <!-- Botón Guardar -->
        <button id="usuario_detail_button_save" type="button" class="btn btn-info rup-filtrar">
        	<i class="fa fa-filter"></i>
        	<span>
        		<spring:message code="filter" />
        	</span>
        </button>
    </div>
</div>