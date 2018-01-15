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
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<section class="row">
	<div class="col-xs-12">

		<h2>Autocomplete</h2>
		<hr>


		<!-- <div id="dialogo"> -->
		<h3>Autocomplete local</h3>
		<label for="autocomplete">Lenguaje:</label><input id="autocomplete"
			name="autocomplete" /> [asp, c, c++, coldfusion, groovy, haskell,
		java, javascript, perl, php, python, ruby, scala] <br />
		<!-- </div> -->
		
		<h3>Autocomplete local sin acentos</h3>
		<label for="autocompleteNotAccent">Lenguaje:</label><input id="autocompleteNotAccent"
			name="autocompleteNotAccent" /> [asp, c, c++, coldfusion, groovy, haskell,
		java, javascript, perl, php, python, ruby, scala] <br />

		<h3>Autocomplete remoto</h3>
		<label for="patron">Departamento-Provincia:</label><input id="patron"
			name="patron" /> [Castellano: " de " // Euskara: arab, gipuz, bilb]
		<br />


		<h3>Combobox local</h3>
		<label for="comboboxLocal">Lenguaje:</label><input id="comboboxLocal"
			name="comboboxLocal" /> [asp, c, c++, coldfusion, groovy, haskell,
		java, javascript, perl, php, python, ruby, scala]


		<h3>Combobox remoto</h3>
		<label for="comboboxRemoto">Departamento-Provincia:</label><input
			id="comboboxRemoto" name="comboboxRemoto" /> [Castellano: " de " //
		Euskara: arab, gipuz, bilb] <br />
	</div>
</section>


