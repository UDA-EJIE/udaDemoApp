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
	<h2>Selección simple</h2>
	<div id="error" style="display:none"></div>
	<div id="simple">
		<div id="toolbar"></div>
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_simple"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_simple">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<label for="id_search"class="formulario_linea_label">id:</label>
							<input type="text" name="id" class="formulario_linea_input" id="id_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="nombre_search"class="formulario_linea_label">nombre:</label>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="apellido1_search"class="formulario_linea_label">apellido1:</label>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="apellido2_search"class="formulario_linea_label">apellido2:</label>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="ejie_search"class="formulario_linea_label">ejie:</label>
							<div style="float: left;"><select id="ejie_search" name="ejie" class="rup-combo" ></select></div>
						</div>
						<div class="formulario_linea_izda_float">
							<label for="fechaAlta_search"class="formulario_linea_label">fechaAlta:</label>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
						</div>
						<div class="formulario_linea_izda_float" style="clear: left;">
							<label for="fechaBaja_search"class="formulario_linea_label">fechaBaja:</label>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<div id="RUP_GRID_simple">
			<!-- Tabla -->
			<table id="GRID_simple" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginación -->
			<div id="pager" style="text-align:center;"></div>
		</div>
	</div>