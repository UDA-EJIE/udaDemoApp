<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versi�n 1.1 exclusivamente (la �Licencia�);
 -- Solo podr� usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�,
 -- SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas.
 -- V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>${tituloPagina}</h2> <!-- Titulo pagina -->

<div id="table_div" class="rup-table-container">
	<div id="table_feedback"></div>		<!-- Feedback de la tabla -->
	<div id="table_toolbar"></div>		<!-- Botonera de la tabla -->
	<div id="table_filter_div" class="rup-table-filter">	<!-- Capa contenedora del formulario de filtrado -->
		<form id="table_filter_form">						<!-- Formulario de filtrado -->
			<div id="table_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
				
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-sm">
						<label for="countryId_filter_table" class="formulario_linea_label">
							<spring:message code="countryId" />
						</label>
						<input type="text" name="countryId" class="formulario_linea_input form-control" id="countryId_filter_table" />
					</div>
					<div class="form-group col-sm">
						<label for="regionId_filter_table" class="formulario_linea_label">
							<spring:message code="regionId" />
						</label>
						<input type="text" name="regionId" class="formulario_linea_input form-control" id="regionId_filter_table" />
					</div>
					<div class="form-group col-sm">
						<label for="countryName_filter_table" class="formulario_linea_label">
							<spring:message code="countryName" />
						</label>
						<input type="text" name="countryName" class="formulario_linea_input form-control" id="countryName_filter_table" />
					</div>
					<!-- Fin campos del formulario de filtrado -->
					
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="table_filter_buttonSet" class="right_buttons">
					<!-- Botón de limpiar -->
					<button id="table_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="fa fa-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <!-- Botón de filtrado -->
			        <button id="table_filter_filterButton" type="button" class="btn btn-info rup-filtrar">
			        	<i class="fa fa-filter"></i>
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

<!-- Formulario de detalle -->
<div id="table_detail_div" class="rup-table-formEdit-detail">
	<div id ="table_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="table_detail_form">					<!-- Formulario -->
			<div id ="table_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="form-row">
			
				<!-- Campos del formulario de detalle -->
				<div class="form-group col-sm">
					<label for="countryId_detailForm_table"><spring:message code="countryId" /></label>
					<input type="text" name="countryId" class="formulario_linea_input form-control" id="countryId_detailForm_table" />
				</div>
				<div class="form-group col-sm">
					<label for="regionId_detail_table"><spring:message code="regionId" /></label>
					<input type="text" name="regionId" class="formulario_linea_input form-control" id="regionId_detail_table" />
				</div>
				<div class="form-group col-sm">
					<label for="countryName_detail_table"><spring:message code="countryName" /></label>
					<input type="text" name="countryName" class="formulario_linea_input form-control" id="countryName_detail_table" />
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_tableEdit_buttonsContainerResposive">
			<!-- Botón Guardar -->
			<button id="table_detail_button_save" class="btn-material btn-material-sm btn-material-primary-high-emphasis rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<!-- Botón Guardar y continuar -->
			<button id="table_detail_button_save_repeat" class="btn-material btn-material-sm btn-material-primary-high-emphasis rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<!-- Botón cancelar -->
			<button id="table_detail_button_cancel" class="btn-material btn-material-sm btn-material-primary-low-emphasis rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
