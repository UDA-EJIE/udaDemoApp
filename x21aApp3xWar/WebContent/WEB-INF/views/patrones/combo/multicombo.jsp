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
<h2><spring:message code="multicombo" /></h2>

<label for="multicombo">Multicombo local</label><br>
<select id="multicombo"></select>
<br><br>

<label for="multicomboRemoto">MultiCombo remoto</label><br>
<select id="multicomboRemoto"></select>
<br><br>

<label for="multicomboGrupos">MultiCombo con 'optgroups'</label><br>
<select id="multicomboGrupos"></select>
<br><br>

<label for="multicomboGruposRemoto">MultiCombo con 'optgroups' remoto</label><br>
<select id="multicomboGruposRemoto"></select>
<br><br>

<label for="multicomboInput">MultiCombo sobre <strong>Input</strong></label><br>
<input type="text" name="multicomboInput" id="multicomboInput" value="coldfusion##python" />
<br><br>

<label for="multicomboLoadFromSelect">MultiCombo carga inicial desde <strong>HTML</strong></label><br>
<select id="multicomboLoadFromSelect" multiple="multiple">
	<option value="1" selected="selected">Alava</option>
	<option value="3">Gipuzcoa</option>
	<option value="2" selected="selected">Vizcaya</option>
</select>
<br><br>
