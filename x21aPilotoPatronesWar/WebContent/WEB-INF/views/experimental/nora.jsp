<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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
<h2>Nora</h2>
<div id="error" style="display: none"></div>

<div id="contenido" style="margin-top: 0.5em; margin-bottom: 0.5em;">
	<form id="searchForm">
		<div class="formulario_legend" id="titleSearch_yy">
			<spring:message code="searchCriteria" />:
		</div>
		<fieldset style="border: 1px solid #DADADA;" id="FIELDSET_SEARCH_yy">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
					<label for="autonomia_search" class="formulario_linea_label">Autonomía:</label>
					<input type="text" name="autonomia.codeAutonomia" class="formulario_linea_input" id="autonomia_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="comarca_search" class="formulario_linea_label">Comarca:</label>
					<input type="text" name="comarca.codeComarca" class="formulario_linea_input" id="comarca_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="provincia_search" class="formulario_linea_label">Provincia:</label>
					<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="provincia_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="localidad_search" class="formulario_linea_label">Localidad:</label>
					<input type="text" name="localidad.codeLocalidad" class="formulario_linea_input" id="localidad_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="municipio_search" class="formulario_linea_label">Municipio:</label>
					<input type="text" name="municipio.codeMunicipio" class="formulario_linea_input" id="municipio_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="calle_search" class="formulario_linea_label">Calle:</label>
					<input type="text" name="calle" class="formulario_linea_input" id="calle_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="portal_search" class="formulario_linea_label">Portal:</label>
					<input type="text" name="portal" class="formulario_linea_input" id="portal_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="cp_search" class="formulario_linea_label">CP:</label>
					<input type="text" name="cp" class="formulario_linea_input" id="cp_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="escalera_search" class="formulario_linea_label">Escalera:</label>
					<input type="text" name="escalera" class="formulario_linea_input" id="escalera_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="piso_search" class="formulario_linea_label">Piso:</label>
					<input type="text" name="piso" class="formulario_linea_input" id="piso_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="mano_search" class="formulario_linea_label">Mano:</label>
					<input type="text" name="mano" class="formulario_linea_input" id="mano_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="puerta_search" class="formulario_linea_label">Puerta:</label>
					<input type="text" name="puerta" class="formulario_linea_input" id="puerta_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="aprox_postal_search" class="formulario_linea_label">Aprox. Postal:</label>
					<input type="text" name="aprox_postal" class="formulario_linea_input" id="aprox_postal_search" />
				</div>
			</div>
			<div>
				<a href="#" onclick="limpiarFiltros()">Limpiar</a>
			</div>

		</fieldset>
	</form>
</div>
<div class="formulario_linea_label">&nbsp;</div>

<div id="combosNora">
	<div class="formulario_columna_cnt">
		<div class="formulario_linea_izda_float">NORA - Tablas:</div>
	</div>
	<div class="formulario_columna_cnt">
		<label for="comboProvincias" class="formulario_linea_label">Provincia:</label>
		<div class="formulario_linea_izda_float">
			<select name="provincia" class="combo" id="comboProvincias"></select>
		</div>
		<label for="comboMunicipios" class="formulario_linea_label">Municipio:</label>
		<div class="formulario_linea_izda_float">
			<select class="combo" id="comboMunicipios"></select>
		</div>
		<label for="autocomplete" class="formulario_linea_label">Calle:</label>
		<div class="formulario_linea_izda_float">
			<input id="autocomplete" name="autocomplete" disabled="disabled"/>
		</div>
	</div>
		<div class="formulario_linea_izda_float">
		<div class="formulario_linea_label">NORA - API JS:</div>	
	</div>
	<div class="formulario_columna_cnt">
		<label for="comboProvinciasAPI" class="formulario_linea_label">Provincia:</label>
		<div class="formulario_linea_izda_float">
			<select class="combo" id="comboProvinciasAPI"></select>
		</div>
		<label for="comboMunicipiosAPI" class="formulario_linea_label">Municipio:</label>
		<div class="formulario_linea_izda_float">
			<select class="combo" id="comboMunicipiosAPI"></select>
		</div>
		<label for="autocompleteAPI" class="formulario_linea_label">Calle:</label>
		<div class="formulario_linea_izda_float">
			<input id="autocompleteAPI" name="autocompleteAPI" disabled="disabled"/>
		</div>
	</div>
	<div class="formulario_columna_cnt">
		<div class="formulario_linea_izda_float">
			<button onclick="mostrarVisor()">Visor LT</button>
		</div>
	</div>
	<div class="formulario_columna_cnt">
		<div class="formulario_linea_izda_float">
			<button onclick="mostrarFormulario()">Formulario</button>
		</div>
	</div>
	<div class="formulario_columna_cnt">&nbsp;</div>
</div>

<div id="id_capaVisor" style="visibility: hidden;">
	<div id="overlay_modal"
		style="position: absolute; top: 0pt; left: 0pt; z-index: 1; width: 100%; height: 841px; opacity: 0.6; background-color: #000000;"></div>
	<iframe src="${staticsUrl}/x21a/resources/visor.html" width=1100
		height=800
		style="position: absolute; top: 1%; left: 1%; opacity: 0.999999; z-index: 2; background-color: white;">Servicio
		NORA</iframe>
</div>

<div id="id_capaFormulario" style="visibility: hidden;">
	<div id="overlay_modal"
		style="position: absolute; top: 0pt; left: 0pt; z-index: 1; width: 100%; height: 841px; opacity: 0.6; background-color: #000000;"></div>
	<iframe src="${staticsUrl}/x21a/resources/formularioNora.html"
		width=1100 height=800
		style="position: absolute; top: 1%; left: 1%; opacity: 0.999999; z-index: 2; background-color: white;">Formulario
		NORA</iframe>
</div>

