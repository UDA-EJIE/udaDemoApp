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
<h2>Maestro-Detalle</h2>
<div id="error" style="display:none"></div>

<h2>Comarca</h2>
<div id="comarca">
	<div id="error_comarca" style="display:none"></div>
	<div id="tableFeedback_comarca"></div>
	<div id="toolbar_comarca"></div>
	<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
		<form id="searchForm_comarca">
			<div  class="formulario_legend" id="titleSearch_comarca">
				<span id="toggle_search_form_comarca" class="collapse_icon ui-icon ui-icon-triangle-1-s"></span>
				<span id="toggle_search_form_label_comarca" class="cursor_pointer"><spring:message code="searchCriteria" />:</span>
				<span id="filter_params_comarca"></span>
			</div>
			<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_comarca">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="code_search" class="formulario_linea_label">code:</label>
						<input type="text" name="code" class="formulario_linea_input" id="code_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="provincia.codeProvincia_search" class="formulario_linea_label">codeProvincia:</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="provincia.codeProvincia_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEs_search" class="formulario_linea_label">descEs:</label>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEu_search" class="formulario_linea_label">descEu:</label>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="css_search" class="formulario_linea_label">css:</label>
						<input type="text" name="css" class="formulario_linea_input" id="css_search" />
					</div>
				</div>
				<div id="filterButtonSet_comarca" class="right_buttons">
					<input id="filterButton_comarca" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Buscar" />
					<a id="cleanLink_comarca" href="javascript:void(0)" class="rup-enlaceCancelar">Limpiar</a>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="RUP_GRID_comarca">
		<!-- Tabla -->
		<table id="GRID_comarca" cellpadding="0" cellspacing="0"></table>
		<!-- Barra de paginaci�n -->
		<div id="pager_comarca" style="text-align:center;"></div>
	</div>
</div>

<h2>Localidad</h2>
<div id="localidad">
	<div id="error_localidad" style="display:none"></div>
	<div id="tableFeedback_localidad"></div>
	<div id="toolbar_localidad"></div>
	<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
		<form id="searchForm_localidad">
			<div  class="formulario_legend" id="titleSearch_comarca">
				<span id="toggle_search_form_localidad" class="collapse_icon ui-icon ui-icon-triangle-1-s"></span>
				<span id="toggle_search_form_label_localidad" class="cursor_pointer"><spring:message code="searchCriteria" />:</span>
				<span id="filter_params_localidad"></span>
			</div>
			<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_localidad">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="code_search" class="formulario_linea_label">code:</label>
						<input type="text" name="code" class="formulario_linea_input" id="code_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="provincia.codeProvincia_search" class="formulario_linea_label">codeProvincia:</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="provincia.codeProvincia_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEs_search" class="formulario_linea_label">descEs:</label>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEu_search" class="formulario_linea_label">descEu:</label>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="css_search" class="formulario_linea_label">css:</label>
						<input type="text" name="css" class="formulario_linea_input" id="css_search" />
					</div>
				</div>
				<div id="filterButtonSet_localidad" class="right_buttons">
					<input id="filterButton_localidad" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Buscar" />
					<a id="cleanLink_localidad" href="javascript:void(0)" class="rup-enlaceCancelar">Limpiar</a>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="RUP_GRID_localidad">
		<!-- Tabla -->
		<table id="GRID_localidad" cellpadding="0" cellspacing="0"></table>
		<!-- Barra de paginaci�n -->
		<div id="pager_localidad" style="text-align:center;"></div>
	</div>

</div>