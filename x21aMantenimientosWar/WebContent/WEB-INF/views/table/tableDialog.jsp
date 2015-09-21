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
<h2>${tituloPagina}</h2> <!-- Titulo pagina -->

<button id="btnTablaDialog_div">Tabla en formulario (DIV)</button>
<button id="btnTablaDialog_ajax">Tabla en formulario (AJAX)</button>


<div id="tableDialog_layer_ajax"></div>
<div id="tableDialog_layer_div" style="display:none">
	<div id="tableDialog_div" class="rup-table-container">
		<div id="tableDialog_feedback"></div>
		<div id="tableDialog_toolbar"></div>
		<div id="tableDialog_filter_div" class="rup-table-filter">
			<form id="tableDialog_filter_form">
				<div id="tableDialog_filter_toolbar" class="formulario_legend"></div>
				<fieldset id="tableDialog_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<label for="id_filter_table" class="formulario_linea_label"><spring:message code="id" />:</label>
							<input type="text" name="id" class="formulario_linea_input" id="id_filter_table" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre" />:</label>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_filter_table" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1" />:</label>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_filter_table" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2" />:</label>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_filter_table" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="ejie_filter_table" class="formulario_linea_label"><spring:message code="ejie" />:</label>
							<div style="float: left;"><select id="ejie_filter_table" name="ejie" class="rup-combo" ></select></div>
						</div>
						<div class="formulario_linea_izda_float">
							<label for="fechaAlta_filter_table" class="formulario_linea_label"><spring:message code="fechaAlta" />:</label>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_filter_table" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="fechaBaja_filter_table" class="formulario_linea_label"><spring:message code="fechaBaja" />:</label>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_filter_table" />
						</div>
						<div class="formulario_linea_izda_float"  style="clear:left">
							<label for="rol_filter_table" class="formulario_linea_label"><spring:message code="rol" />:</label>
							<input type="text" name="rol" class="formulario_linea_input" id="rol_filter_table" />
						</div>
					</div>
					<div id="tableDialog_filter_buttonSet" class="right_buttons">
						<input id="tableDialog_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
						<a id="tableDialog_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
				</fieldset>
			</form>
		</div>
	
		<div id="tableDialog_grid_div">
			<!-- Tabla -->
			<table id="tableDialog"></table>
			<!-- Barra de paginación -->
			<div id="tableDialog_pager"></div>
		</div>
	</div>	
	
	<div id="tableDialog_detail_div" class="rup-table-formEdit-detail">
		<div id ="tableDialog_detail_navigation"></div>
		<div class="ui-dialog-content ui-widget-content" >
			<form id="tableDialog_detail_form">
				<div id ="tableDialog_detail_feedback"></div>
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
						<input type="checkbox" id="ejie_detail_table" name="ejie" />
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
				<button id="tableDialog_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<button id="tableDialog_detail_button_save_repeat" type="button">
					<spring:message code="saveAndContinue" />
				</button>
				<a href="javascript:void(0)" role="button" id="tableDialog_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
