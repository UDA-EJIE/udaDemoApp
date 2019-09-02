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

<div id="contenido">
	<form:form modleAttribute="randomForm" id="searchForm" style="margin: 0 0.5em 0 0.5em;">
		<div class="formulario_legend" id="titleSearch_yy">
			<spring:message code="searchCriteria" />:
		</div>
		<fieldset style="border: 1px solid #DADADA;" id="FIELDSET_SEARCH_yy">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
					<label for="autonomia_search" class="formulario_linea_label">Autonomía:</label>
					<form:input path="autonomia.codeAutonomia" class="formulario_linea_input" id="autonomia_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="comarca_search" class="formulario_linea_label">Comarca:</label>
					<form:input path="comarca.codeComarca" class="formulario_linea_input" id="comarca_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="provincia_search" class="formulario_linea_label">Provincia:</label>
					<form:input path="provincia.codeProvincia" class="formulario_linea_input" id="provincia_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="localidad_search" class="formulario_linea_label">Localidad:</label>
					<form:input path="localidad.codeLocalidad" class="formulario_linea_input" id="localidad_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="municipio_search" class="formulario_linea_label">Municipio:</label>
					<form:input path="municipio.codeMunicipio" class="formulario_linea_input" id="municipio_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="calle_search" class="formulario_linea_label">Calle:</label>
					<form:input path="calle" class="formulario_linea_input" id="calle_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="portal_search" class="formulario_linea_label">Portal:</label>
					<form:input path="portal" class="formulario_linea_input" id="portal_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="cp_search" class="formulario_linea_label">CP:</label>
					<form:input path="cp" class="formulario_linea_input" id="cp_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="escalera_search" class="formulario_linea_label">Escalera:</label>
					<form:input path="escalera" class="formulario_linea_input" id="escalera_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="piso_search" class="formulario_linea_label">Piso:</label>
					<form:input path="piso" class="formulario_linea_input" id="piso_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="mano_search" class="formulario_linea_label">Mano:</label>
					<form:input path="mano" class="formulario_linea_input" id="mano_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="puerta_search" class="formulario_linea_label">Puerta:</label>
					<form:input path="puerta" class="formulario_linea_input" id="puerta_search" />
				</div>
				<div class="formulario_linea_izda_float">
					<label for="aprox_postal_search" class="formulario_linea_label">Aprox. Postal:</label>
					<form:input path="aprox_postal" class="formulario_linea_input" id="aprox_postal_search" />
				</div>
				<div class="formulario_columna_cnt">&nbsp;</div>
				<div style="float: right; margin-right: 3em;">
					<button id="visorLT">Visor LT</button>
					<button id="visorFormulario" >Formulario</button>
					<spring:url value="#" var="urlHashtag" htmlEscape="true"/>
					<a id="limpiar" href="${urlHashtag}">Limpiar</a>
				</div>
				<div class="formulario_columna_cnt">&nbsp;</div>
			</div>

		</fieldset>
	</form:form>
	
	<div id="nora_tablas" style="float: left; width: 50%;">
		<fieldset class="combo_fieldset">
		<legend class="combo_legend">NORA - Tablas:</legend>
			<label for="comboProvincias" class="formulario_linea_label">Provincia:</label>
			<select name="provincia" class="combo" id="comboProvincias"></select>
			<br><br>
			
			<label for="comboMunicipios" class="formulario_linea_label">Municipio:</label>
			<select class="combo" id="comboMunicipios"></select>
			<br><br>
			
			<label for="autocomplete" class="formulario_linea_label">Calle:</label>
			<input id="autocomplete" name="autocomplete" disabled="disabled"/>
			<br><br>
		</fieldset>
	</div>
	
	<div id="nora_api" style="float: left; width: 50%;">
		<fieldset class="combo_fieldset">
			<legend class="combo_legend">NORA - API JS:</legend>	
				<label for="comboProvinciasAPI" class="formulario_linea_label">Provincia:</label>
				<select class="combo" id="comboProvinciasAPI"></select>
				<br><br>
				
				<label for="comboMunicipiosAPI" class="formulario_linea_label">Municipio:</label>
				<select class="combo" id="comboMunicipiosAPI"></select>
				<br><br>
				
				<label for="autocompleteAPI" class="formulario_linea_label">Calle:</label>
				<input id="autocompleteAPI" name="autocompleteAPI" disabled="disabled"/>
				<br><br>
		</fieldset>
	</div>
</div>


<div id="id_capaVisor" style="visibility: hidden;">
	<div id="overlay_modal"
		style="position: absolute; top: 0pt; left: 0pt; z-index: 1000; width: 100%; height: 841px; opacity: 0.6; background-color: #000000;"></div>
	<iframe src="${staticsUrl}/x21a/resources/visor.html" width=1100
		height=800
		style="position: absolute; top: 1%; left: 1%; opacity: 0.999999; z-index: 2000; background-color: white;">Servicio
		NORA</iframe>
</div>

<div id="id_capaFormulario" style="visibility: hidden;">
	<div id="overlay_modal"
		style="position: absolute; top: 0pt; left: 0pt; z-index: 1000; width: 100%; height: 841px; opacity: 0.6; background-color: #000000;"></div>
	<iframe src="${staticsUrl}/x21a/resources/formularioNora.html"
		width=1100 height=800
		style="position: absolute; top: 1%; left: 1%; opacity: 0.999999; z-index: 2000; background-color: white;">Formulario
		NORA</iframe>
</div>

