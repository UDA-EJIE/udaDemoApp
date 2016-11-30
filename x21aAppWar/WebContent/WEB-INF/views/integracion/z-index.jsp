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
<h2><spring:message code="z-index" /></h2>

<div id="idioma" class="rup-language_root"></div>
<div id="menu-horizontal" class="menu"></div>
<div id="breadCrumb" class="rup-breadCrumb_root"></div>
<div id="idioma_2" class="rup-language_root"></div>
<div id="menu-vertical" class="menu"></div>

<div id="contentMV">
	<div id="breadCrumb_2" class="rup-breadCrumb_root" title="Migas"></div>
	<div style="margin-left: 2em;"><input id="autocomplete" name="autocomplete" title="Autocompletar" /></div>
	<div style="margin-left: 4em;"><select id="combo" class="rup-combo" title="Combo"></select></div>
	<div style="margin-left: 6em;"><input id="date" type="text" title="Fecha" /></div>
	<div style="margin-left: 8em;"><input id="time" type="text" title="Hora"/></div>
</div>

<br>

<table border="1">
<tr><th>Patrón</th><th>.class</th><th>z-index</th></tr>

<tr><td>Tooltip</td><td>.rup-tooltip</td><td>1100</td></tr>
<tr><td>Migas (menú)</td><td>.rup-breadCrumb_main LI UL</td><td>1000</td></tr>
<tr><td>Idioma</td><td>.rup-language_root div</td><td>900</td></tr>
<tr><td>Menú (submenú horizontal)</td><td>.rup_div_menu .rup_menu_vertical</td><td>810</td></tr>
<tr><td>Menú vertical</td><td>.rup_menu_vertical</td><td>800</td></tr>
<tr><td>Combo</td><td>.ui-selectmenu-menu-dropdown </td><td>100</td></tr>
<tr><td>Autocomplete</td><td>.ui-autocomplete</td><td>100</td></tr>
<tr><td>Date - Time</td><td>&nbsp;</td><td>1 (auto)</td></tr>
<tr><td>Grid<i>(ui.jqgrid.css)</i></td><td>.ui-jqgrid .loading</td><td>101</td></tr>
<tr><td>Grid<i>(ui.jqgrid.css)</i></td><td>.ui-jqgrid .jqgrid-overlay</td><td>100</td></tr>
</table> 