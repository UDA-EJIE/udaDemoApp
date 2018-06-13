<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<!-- Titulo pagina -->
<!-- <h2>table</h2> -->

<div id="example_detail_div" class="rup-table-formEdit-detail">
	<div id ="example_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<form id="example_detail_form">
			<div id ="example_detail_feedback"></div>
			
			<div class="floating_left_pad_right">
				<div class="floating_left_pad_right one-column">
					<label for="id_detailForm_table"><spring:message code="id" />:</label>
					<input type="text" name="id" class="formulario_linea_input" id="id_detailForm_table" />
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="nombre_detail_table"><spring:message code="nombre" />:</label>
					<input type="text" name="nombre" class="formulario_linea_input" id="nombre_detail_table" />
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="apellido1_detail_table"><spring:message code="apellido1" />:</label>
					<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_detail_table" />
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="apellido2_detail_table"><spring:message code="apellido2" />:</label>
					<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_detail_table" />
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="ejie_detail_table"><spring:message code="ejie" />:</label>
					<input type="checkbox" id="ejie_detail_table" name="ejie"  value="1"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="fechaAlta_detail_table"><spring:message code="fechaAlta" />:</label>
					<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_detail_table" />
				</div>
				<div class="floating_left_pad_right one-column" style="clear: left;">
					<label for="fechaBaja_detail_table"><spring:message code="fechaBaja" />:</label>
					<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_detail_table" />
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="rol_detail_table"><spring:message code="rol" />:</label>
					<select id="rol_detail_table" name="rol" class="formulario_linea_input" ></select>
				</div>
			</div>
			
		</form>
	</div>
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<button id="example_detail_button_save" class="btn btn-outline-success" type="button">
				<spring:message code="save" />
			</button>
			<button id="example_detail_button_save_repeat" class="btn btn-outline-success" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<button id="example_detail_link_cancel" class="btn btn-outline-danger" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
