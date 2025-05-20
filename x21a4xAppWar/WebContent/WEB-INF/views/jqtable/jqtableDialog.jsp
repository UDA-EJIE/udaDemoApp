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
	<div id="tableDialog_div" class="rup-jqtable-container">
		<div id="tableDialog_feedback"></div>
		<div id="tableDialog_toolbar"></div>
		<div id="tableDialog_filter_div" class="rup-jqtable-filter">
			<form:form modelAttribute="usuario" id="tableDialog_filter_form">
				<div id="tableDialog_filter_toolbar" class="formulario_legend"></div>
				<fieldset id="tableDialog_filter_fieldset" class="rup-jqtable-filter-fieldset">
					<div class="form-row">
						<div class="form-group col-sm">
							<label for="id_filter_table" class="formulario_linea_label"><spring:message code="id" /></label>
							<form:input path="id" class="formulario_linea_input form-control" id="id_filter_table" />
						</div>
						<div class="form-group col-sm">
							<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre" /></label>
							<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_filter_table" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm">
							<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1" /></label>
							<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table" />
						</div>
						<div class="form-group col-sm">
							<label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2" /></label>
							<form:input path="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group fix-align col-sm">
							<label for="ejie_filter_table" class="formulario_linea_label"><spring:message code="ejie" /></label>
							<form:input id="ejie_filter_table" path="ejie" class="formulario_linea_input form-control" />
						</div>
						<div class="form-group fix-align col-sm">
							<label for="fechaAlta_filter_table" class="formulario_linea_label"><spring:message code="fechaAlta" /></label>
							<form:input path="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group fix-align col-sm">
							<label for="fechaBaja_filter_table" class="formulario_linea_label"><spring:message code="fechaBaja" /></label>
							<form:input path="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table" />
						</div>
						<div class="form-group fix-align col-sm">
							<label for="rol_filter_table" class="formulario_linea_label"><spring:message code="rol" /></label>
							<form:input path="rol" class="formulario_linea_input form-control" id="rol_filter_table" />
						</div>
					</div>
					
					<div id="tableDialog_filter_buttonSet" class="right_buttons">
						<button id="tableDialog_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
							<i class="mdi mdi-eraser"></i>
				        	<span>
				        		<spring:message code="clear" />
				        	</span>
						</button>
						<button id="tableDialog_filter_filterButton" type="button" class="btn btn-info rup-filtrar">
							<i class="mdi mdi-filter"></i>
				        	<span>
				        		<spring:message code="filter" />
				        	</span>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	
		<div id="tableDialog_grid_div">
			<!-- Tabla -->
			<table id="tableDialog"></table>
			<!-- Barra de paginación -->
			<div id="tableDialog_pager"></div>
		</div>
	</div>	
	
	<div id="tableDialog_detail_div" class="rup-jqtable-formEdit-detail">
		<div id ="tableDialog_detail_navigation"></div>
		<div class="ui-dialog-content ui-widget-content" >
			<form id="tableDialog_detail_form">
				<div id ="tableDialog_detail_feedback"></div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="id_detailForm_table" class="formulario_linea_label"><spring:message code="id" /></label>
						<input type="text" name="id" class="formulario_linea_input form-control" id="id_detailForm_table" />
					</div>
					<div class="form-group col-sm">
						<label for="nombre_detail_table" class="formulario_linea_label"><spring:message code="nombre" /></label>
						<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_detail_table" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="apellido1_detail_table" class="formulario_linea_label"><spring:message code="apellido1" /></label>
						<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table" />
					</div>
					<div class="form-group col-sm">
						<label for="apellido2_detail_table" class="formulario_linea_label"><spring:message code="apellido2" /></label>
						<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_detail_table" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group fix-align col-sm">
						<label for="fechaBaja_detail_table" class="formulario_linea_label"><spring:message code="fechaBaja" /></label>
						<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_detail_table" />
					</div>
					<div class="form-group fix-align col-sm">
						<label for="fechaAlta_detail_table" class="formulario_linea_label"><spring:message code="fechaAlta" /></label>
						<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_detail_table" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group fix-align col-sm">
						<label for="ejie_detail_table" class="formulario_linea_label"><spring:message code="ejie" /></label>
						<input type="checkbox" id="ejie_detail_table" class="formulario_linea_input form-control" value="1" name="ejie" />
					</div>
					<div class="form-group fix-align col-sm">
						<label for="rol_detail_table" class="formulario_linea_label"><spring:message code="rol" /></label>
						<input type="text" id="rol_detail_table" name="rol" class="formulario_linea_input form-control" />
					</div>
				</div>
			</form>
		</div>
		
		<div class="rup-jqtable-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset rup_jqtableEdit_buttonsContainerResposive">
				<button id="tableDialog_detail_button_save" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive fix-editForm-buttons-align" type="button">
					<spring:message code="save" />
				</button>
				<button id="tableDialog_detail_button_save_repeat" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive fix-editForm-buttons-align" type="button">
					<spring:message code="saveAndContinue" />
				</button>
				<button id="tableDialog_detail_button_cancel" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button">
					<spring:message code="cancel" />
				</button>
			</div>
		</div>
	</div>
</div>
