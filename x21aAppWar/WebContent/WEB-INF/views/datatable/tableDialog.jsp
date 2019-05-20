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
<form id="MultiPk_filter_form">						<!-- Formulario de filtrado -->
			<div id="MultiPk_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="MultiPk_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-sm">
						<label for="ida_filter_table" class="formulario_linea_label">
							<spring:message code="ida"/>
						</label>
						<input type="text" name="ida" class="formulario_linea_input form-control" id="ida_filter_table"/>
					</div>
					<div class="form-group col-sm">
						<label for="idb_filter_table" class="formulario_linea_label">
							<spring:message code="idb"/>
						</label>
						<input type="text" name="idb" class="formulario_linea_input form-control" id="idb_filter_table"/>
					</div>
					<div class="form-group col-sm">
						<label for="nombre_filter_table" class="formulario_linea_label">
							<spring:message code="nombre"/>
						</label>
						<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_filter_table"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="apellido1_filter_table" class="formulario_linea_label">
							<spring:message code="apellido1"/>
						</label>
						<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table"/>
					</div>
					<div class="form-group col-sm">
						<label for="apellido2_filter_table" class="formulario_linea_label">
							<spring:message code="apellido2"/>
						</label>
						<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table"/>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="MultiPk_filter_buttonSet" class="right_buttons">
					<button id="MultiPk_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="mdi mdi-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <button id="MultiPk_filter_filterButton" type="button" class="btn btn-primary rup-filtrar">
			        	<i class="mdi mdi-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
			        </button>
				</div>
			</fieldset>
</form>
		</div>
	
		<div id="tableDialog_grid_div">
			<!-- Tabla -->
			<table id="MultiPk" class="tableFit table-striped table-bordered" 
			data-url-base="./multipk"
			data-filter-form="#MultiPk_filter_form" 
			cellspacing="0">
		        <thead>
		            <tr>
			                <th data-col-prop="ida" data-col-sidx="IDA" >ida</th>
			                <th data-col-prop="idb" data-col-sidx="IDB" >idb</th>
			                <th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
			                <th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
			                <th data-col-prop="apellido2" data-col-sidx="APELLIDO2" >apellido2</th>
		            </tr>
		        </thead>
		</table>
			<!-- Barra de paginación -->
			<div id="tableDialog_pager"></div>
		</div>
	</div>	
	
<!-- Formulario de detalle -->
<div id="MultiPk_detail_div" class="rup-table-formEdit-detail">
	<div id ="MultiPk_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="MultiPk_detail_form">					<!-- Formulario -->
			<div id ="MultiPk_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-group col-sm">
					<label for="ida_detail_table" class="formulario_linea_label"><spring:message code="ida"/></label>
					<input type="text" name="ida" class="formulario_linea_input form-control" id="ida_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="idb_detail_table" class="formulario_linea_label"><spring:message code="idb"/></label>
					<input type="text" name="idb" class="formulario_linea_input form-control" id="idb_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="nombre_detail_table" class="formulario_linea_label"><spring:message code="nombre"/></label>
					<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_detail_table"/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm">
					<label for="apellido1_detail_table" class="formulario_linea_label"><spring:message code="apellido1"/></label>
					<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table"/>
				</div>
				<div class="form-group col-sm">
					<label for="apellido2_detail_table" class="formulario_linea_label"><spring:message code="apellido2"/></label>
					<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_detail_table"/>
				</div>
				<!-- Fin campos del formulario de detalle -->
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_tableEdit_buttonsContainerResposive">
			<!-- Botón Guardar -->
			<button id="MultiPk_detail_button_save" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<button id="MultiPk_detail_button_cancel" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
</div>
