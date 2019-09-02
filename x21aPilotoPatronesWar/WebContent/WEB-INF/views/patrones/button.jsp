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
<h2>Button</h2>

<button type="button" id="boton">Acci&oacute;n</button>

<button type="button" id="dropdownHtmlListButton">Lista HTML</button>
<spring:url value="#" var="urlHashtag" htmlEscape="true"/>
<ul id="dropdownHtmlList" class="rup-dropdown-option-list">
	<li><a href="${urlHashtag}" id="dropdownElem1">Elemento 1</a></li>
	<li><a href="${urlHashtag}" id="dropdownElem2">Elemento 2</a></li>
	<li><a href="${urlHashtag}" id="dropdownElem3">Elemento 3</a></li>
	<li class="divider"></li>
	<li><a href="${urlHashtag}" id="dropdownElem4">Elemento 4</a></li>
</ul>


<button type="button" id="dropdownDialogButton">Dialogo</button>

<div id="dropdownDialog" style="width:380px;">
	<form>
		<fieldset class="dropdownButton-inputs">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
					<label for="dropdownButton-combo">Filtros</label>
					<input id="dropdownButton-combo" />
				</div>
				<div class="formulario_linea_izda_float">
					<input type="checkbox" id="dropdownButton-defaultFilter"/>
					<label for="dropdownButton-defaultFilter">Filtro por defecto</label>
				</div>
			</div>
		</fieldset>
	</form> 
</div>
