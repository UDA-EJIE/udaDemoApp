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
<h2>Combo</h2>

<label for="combo">Combo local</label>
<select id="combo" class="rup-combo"></select>
<br><br>

<label for="comboRemoto">Combo remoto</label>
<select id="comboRemoto" class="rup-combo"></select>
<br><br>

<label for="comboLargo">Combo con texto largo</label>
<select id="comboLargo" class="rup-combo"></select>
<br><br>

<label for="comboGrupos">Combo con 'optgroups'</label>
<select id="comboGrupos" class="rup-combo"></select>
<br><br>

<label for="comboGruposRemoto">Combo con 'optgroups' remoto</label>
<select id="comboGruposRemoto" class="rup-combo"></select>
<br><br>

<label for="comboImgs">Combo (no i18n) con imagenes</label>
<select id="comboImgs" class="rup-combo"></select>
<br><br>

<label for="comboInput">Combo sobre <strong>Input</strong></label>
<input type="text" name="comboInput" id="comboInput" value="python" />
<br><br>

<label for="comboLoadFromSelect">Combo carga inicial desde <strong>HTML</strong></label>
<select id="comboLoadFromSelect">
	<option value="1">Alava</option>
	<option value="3">Gipuzcoa</option>
	<option value="2" selected="selected">Vizcaya</option>
</select>
<br><br>
