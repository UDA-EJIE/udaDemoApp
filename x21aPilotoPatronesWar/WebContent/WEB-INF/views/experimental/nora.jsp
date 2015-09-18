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
<h1>Nora</h1>
<div id="error" style="display: none"></div>

<div id="contenido" style="margin-top: 0.5em; margin-bottom: 0.5em;">
	<form id="searchForm">
		<div class="formulario_legend" id="titleSearch_yy">
			<spring:message code="searchCriteria" />
			:
		</div>
		<fieldset style="border: 1px solid #DADADA;" id="FIELDSET_SEARCH_yy">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Autonomía:</div>
					<input type="text" name="autonomia.codeAutonomia"
						class="formulario_linea_input" id="autonomia_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Comarca:</div>
					<input type="text" name="comarca.codeComarca"
						class="formulario_linea_input" id="comarca_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Provincia:</div>
					<input type="text" name="provincia.codeProvincia"
						class="formulario_linea_input" id="provincia_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Localidad:</div>
					<input type="text" name="localidad.codeLocalidad"
						class="formulario_linea_input" id="localidad_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Municipio:</div>
					<input type="text" name="municipio.codeMunicipio"
						class="formulario_linea_input" id="municipio_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Calle:</div>
					<input type="text" name="calle" class="formulario_linea_input"
						id="calle_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Portal:</div>
					<input type="text" name="portal" class="formulario_linea_input"
						id="portal_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">CP:</div>
					<input type="text" name="cp" class="formulario_linea_input"
						id="cp_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Escalera:</div>
					<input type="text" name="escalera" class="formulario_linea_input"
						id="escalera_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Piso:</div>
					<input type="text" name="piso" class="formulario_linea_input"
						id="piso_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Mano:</div>
					<input type="text" name="mano" class="formulario_linea_input"
						id="mano_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Puerta:</div>
					<input type="text" name="puerta" class="formulario_linea_input"
						id="puerta_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<div class="formulario_linea_label">Aprox. Postal:</div>
					<input type="text" name="aprox_postal"
						class="formulario_linea_input" id="aprox_postal_search" />
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
		<div class="formulario_linea_label">Provincia:</div>
		<div class="formulario_linea_izda_float">
			<select name="provincia" class="combo" id="comboProvincias"></select>
		</div>
		<div class="formulario_linea_label">Municipio:</div>
		<div class="formulario_linea_izda_float">
			<select class="combo" id="comboMunicipios"></select>
		</div>
		<div class="formulario_linea_label">Calle:</div>
		<div class="formulario_linea_izda_float">
			<input id="autocomplete" name="autocomplete" disabled="disabled"/>
		</div>
	</div>
		<div class="formulario_linea_izda_float">
		<div class="formulario_linea_label">NORA - API JS:</div>
	</div>
	<div class="formulario_columna_cnt">
		<div class="formulario_linea_label">Provincia:</div>
		<div class="formulario_linea_izda_float">
			<select class="combo" id="comboProvinciasAPI"></select>
		</div>
		<div class="formulario_linea_label">Municipio:</div>
		<div class="formulario_linea_izda_float">
			<select class="combo" id="comboMunicipiosAPI"></select>
		</div>
		<div class="formulario_linea_label">Calle:</div>
		<div class="formulario_linea_izda_float">
			<input id="autocompleteAPI" name="autocompleteAPI" disabled="disabled"/>
		</div>
	</div>
	<div class="formulario_columna_cnt">
		<div class="formulario_linea_label">Visor LT:</div>
		<div class="formulario_linea_izda_float">
			<button onclick="mostrarVisor()">Visor LT</button>
		</div>
	</div>
	<div class="formulario_columna_cnt">
		<div class="formulario_linea_label">Formulario:</div>
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

