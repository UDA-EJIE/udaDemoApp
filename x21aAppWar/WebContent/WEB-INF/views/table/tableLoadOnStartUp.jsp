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
<h2>loadOnStartUp=False</h2>
<div id="error" style="display:none"></div>

<h2>Comarca</h2>
<div id="comarca_div" class="rup-table-container">
<!-- 	<div id="error_comarca" style="display:none"></div> -->
	<div id="comarca_feedback"></div>
	<div id="comarca_toolbar"></div>
	<div id="comarca_filter_div"  class="rup-table-filter">
		<form id="comarca_filter_form">
			<div  id="comarca_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="comarca_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="code_filter_comarca" class="formulario_linea_label">code:</label>
						<input type="text" name="code" class="formulario_linea_input" id="code_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="codeProvincia_filter_comarca" class="formulario_linea_label">codeProvincia:</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="codeProvincia_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEs_filter_comarca" class="formulario_linea_label">descEs:</label>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEu_filter_comarca" class="formulario_linea_label">descEu:</label>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="css_filter_comarca" class="formulario_linea_label">css:</label>
						<input type="text" name="css" class="formulario_linea_input" id="css_filter_comarca" />
					</div>
				</div>
				<div id="comarca_filter_buttonSet" class="right_buttons">
					<button id="comarca_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button> 
					<a id="comarca_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar">Limpiar</a>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="comarca_grid_div">
		<!-- Tabla -->
		<table id="comarca"></table>
		<!-- Barra de paginaci�n -->
		<div id="comarca_pager"></div>
	</div>
</div>


