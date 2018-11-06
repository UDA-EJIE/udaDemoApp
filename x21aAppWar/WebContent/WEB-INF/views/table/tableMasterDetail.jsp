<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la �Licencia�);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�,
 -- SIN GARANT�AS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>Maestro-Detalle</h2>
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
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="code_filter_comarca" class="formulario_linea_label">code</label>
						<input type="text" name="code" class="formulario_linea_input form-control" id="code_filter_comarca" />
					</div>
					<div class="form-group col-sm">
						<label for="codeProvincia_filter_comarca" class="formulario_linea_label">codeProvincia</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input form-control" id="codeProvincia_filter_comarca" />
					</div>
					<div class="form-group col-sm">
						<label for="descEs_filter_comarca" class="formulario_linea_label">descEs</label>
						<input type="text" name="descEs" class="formulario_linea_input form-control" id="descEs_filter_comarca" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="descEu_filter_comarca" class="formulario_linea_label">descEu</label>
						<input type="text" name="descEu" class="formulario_linea_input form-control" id="descEu_filter_comarca" />
					</div>
					<div class="form-group col-sm">
						<label for="css_filter_comarca" class="formulario_linea_label">css</label>
						<input type="text" name="css" class="formulario_linea_input form-control" id="css_filter_comarca" />
					</div>
				</div>
				<div id="comarca_filter_buttonSet" class="right_buttons">
					<button id="comarca_filter_cleanButton" type="button" class="btn btn-warning rup-limpiar">
						<i class="fa fa-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
					</button>
					<button id="comarca_filter_filterButton" type="button" class="btn rup-filtrar rup-filter-dropdown">
						<i class="fa fa-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="comarca_grid_div">
		<!-- Tabla -->
		<table id="comarca"></table>
		<!-- Barra de paginación -->
		<div id="comarca_pager"></div>
	</div>
</div>

<h2>Localidad</h2>
<div id="localidad_div">
<!-- 	<div id="error_localidad" style="display:none"></div> -->
	<div id="localidad_feedback"></div>
	<div id="localidad_toolbar"></div>
	<div id="localidad_filter_div"  class="rup-table-filter">
		<form id="localidad_filter_form">
			<div  id="localidad_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="localidad_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="code_filter_localidad" class="formulario_linea_label">code</label>
						<input type="text" name="code" class="formulario_linea_input form-control" id="code_filter_localidad" />
					</div>
					<div class="form-group col-sm">
						<label for="codeProvincia_filter_localidad" class="formulario_linea_label">codeProvincia</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input form-control" id="codeProvincia_filter_localidad" />
					</div>
					<div class="form-group col-sm">
						<label for="descEs_filter_localidad" class="formulario_linea_label">descEs</label>
						<input type="text" name="descEs" class="formulario_linea_input form-control" id="descEs_filter_localidad" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="descEu_filter_localidad" class="formulario_linea_label">descEu</label>
						<input type="text" name="descEu" class="formulario_linea_input form-control" id="descEu_filter_localidad" />
					</div>
					<div class="form-group col-sm">
						<label for="css_filter_localidad" class="formulario_linea_label">css</label>
						<input type="text" name="css" class="formulario_linea_input form-control" id="css_filter_localidad" />
					</div>
				</div>
				<div id="localidad_filter_buttonSet" class="right_buttons">
					<button id="localidad_filter_cleanButton" type="button" class="btn btn-warning rup-limpiar">
						<i class="fa fa-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
					</button>
					<button id="localidad_filter_filterButton" type="button" class="btn rup-filtrar rup-filter-dropdown">
						<i class="fa fa-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="localidad_grid_div">
		<!-- Tabla -->
		<table id="localidad"></table>
		<!-- Barra de paginación -->
		<div id="localidad_pager" ></div>
	</div>

</div>