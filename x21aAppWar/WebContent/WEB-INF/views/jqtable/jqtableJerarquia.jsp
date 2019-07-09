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

<div id="table_div" class="rup-jqtable-container">
	<div id="table_feedback"></div>
	<div id="table_toolbar"></div>
	<div id="table_filter_div" class="rup-jqtable-filter">
		<form id="table_filter_form">
			<div id="table_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="table_filter_fieldset" class="rup-jqtable-filter-fieldset">
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="id_filter_table" class="formulario_linea_label"><spring:message code="id" /></label>
						<input type="text" name="id" class="formulario_linea_input form-control" id="id_filter_table" />
					</div>
					<div class="form-group col-sm">
						<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre" /></label>
						<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_filter_table" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1" /></label>
						<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table" />
					</div>
					<div class="form-group col-sm">
						<label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2" /></label>
						<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="ejie_filter_table" class="formulario_linea_label"><spring:message code="ejie" /></label>
						<div class="formulario_linea_input form-control">
							<select id="ejie_filter_table" name="ejie" class="rup-combo" ></select>
						</div>
					</div>
					<div class="form-group col-sm">
						<label for="fechaAlta_filter_table" class="formulario_linea_label"><spring:message code="fechaAlta" /></label>
						<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm" style="clear: left;">
						<label for="fechaBaja_filter_table" class="formulario_linea_label"><spring:message code="fechaBaja" /></label>
						<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table" />
					</div>
					<div class="form-group col-sm">
						<label for="rol_filter_table" class="formulario_linea_label"><spring:message code="rol" /></label>
						<select id="rol_filter_table" name="rol" class="formulario_linea_input form-control" ></select>
					</div>
				</div>
				
				<div id="table_filter_buttonSet" class="right_buttons">
			        <button id="table_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="mdi mdi-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <button id="table_filter_filterButton" type="button" class="btn btn-info rup-filtrar">
			        	<i class="mdi mdi-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
			        </button>
			    </div>
			</fieldset>
		</form>
	</div>

	<div id="table_grid_div">
		<!-- Tabla -->
		<table id="table"></table>
		<!-- Barra de paginación -->
		<div id="table_pager"></div>
	</div>
</div>	

<div id="table_detail_div" class="rup-jqtable-formEdit-detail">
	<div id ="table_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<form id="table_detail_form">
			<div id ="FormError"></div>
			<div class="form-row">
 				<div class="form-group col-sm">
					<label for="id_detailForm_table" class="formulario_linea_label"><spring:message code="id" /></label>
					<input type="hidden" name="id" class="formulario_linea_input form-control" id="id_detailForm_table" />
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
				<div class="form-group col-sm">
					<label for="ejie_detail_table" class="formulario_linea_label"><spring:message code="ejie" /></label>
					<input type="checkbox" id="ejie_detail_table" class="formulario_linea_input form-control" name="ejie" />
				</div>
				<div class="form-group col-sm">
					<label for="fechaAlta_detail_table" class="formulario_linea_label"><spring:message code="fechaAlta" /></label>
					<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_detail_table" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm">
					<label for="fechaBaja_detail_table" class="formulario_linea_label"><spring:message code="fechaBaja" /></label>
					<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_detail_table" />
				</div>
				<div class="form-group col-sm">
					<label for="rol_detail_table" class="formulario_linea_label"><spring:message code="rol" /></label>
					<select id="rol_detail_table" name="rol" class="formulario_linea_input form-control" ></select>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm">
					<label for="idPadre_detailForm_table" class="formulario_linea_label"><spring:message code="idPadre" /></label>
					<input type="text" name="idPadre" class="formulario_linea_input form-control" id="idPadre_detailForm_table" />
				</div>
			</div>
		</form>
	</div>
	<div class="rup-jqtable-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_jqtableEdit_buttonsContainerResposive">
			<button id="table_detail_button_save" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive fix-editForm-buttons-align" type="button">
				<spring:message code="save" />
			</button>
			<button id="table_detail_button_save_repeat" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive fix-editForm-buttons-align" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<button id="table_detail_button_cancel" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
