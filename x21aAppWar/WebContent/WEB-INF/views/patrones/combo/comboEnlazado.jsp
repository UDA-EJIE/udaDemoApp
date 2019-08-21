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
<h2>Combo Enlazado (simple)</h2>
 
<div id="local" style="float: left;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Local</legend>
		
		<label for="comboAbuelo">Provincia</label>
		<select id="comboAbuelo" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="comboPadre">Comarca</label>
		<select id="comboPadre" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="comboHijo">Localidad</label>
		<select id="comboHijo" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>

<div id="remote" style="float: left; margin-left: 4em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Remoto</legend>
		
		<input style="display:none;" id="hiddenAbueloRemoto" name="provincia" value="02" />
		<label for="comboAbueloRemoto">Provincia</label>
		<select id="comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="comboPadreRemoto">Comarca</label>
		<select id="comboPadreRemoto" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="comboHijoRemoto">Localidad</label>
		<select id="comboHijoRemoto" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>

<div id="mixto" style="float: left; margin-left: 4em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Mixto I</legend>
		
		<label for="mixto_comboAbueloRemoto">Provincia (remoto)</label>
		<select id="mixto_comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto_comboPadre">Comarca (local)</label>
		<select id="mixto_comboPadre" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto_comboHijoRemoto">Localidad (remoto)</label>
		<select id="mixto_comboHijoRemoto" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>

<div id="mixto2" style="float: left; margin-left: 4em; margin-bottom: 3em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Mixto II</legend>
		
		<label for="mixto2_comboAbuelo">Provincia (local)</label>
		<select id="mixto2_comboAbuelo" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto2_comboPadreRemoto">Comarca (remoto)</label>
		<select id="mixto2_comboPadreRemoto" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto2_comboHijo">Localidad (local)</label>
		<select id="mixto2_comboHijo" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>

<div id="remoteGroup" style="float: left; margin-left: 4em; margin-bottom: 3em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Remoto agrupado</legend>
		
<!-- 		<label for="mixto2_comboAbuelo">Provincia (local)</label> -->
<!-- 		<select id="mixto2_comboAbuelo" name="provincia" class="rup-combo"><option>&nbsp;</option></select> -->
<!-- 		<br><br> -->
		<label for="remoteGroup_comboPadre">Provincia</label>
		<select id="remoteGroup_comboPadre" class="rup-combo" name="provincia"><option>&nbsp;</option></select>
		<br><br>
		<label for="remoteGroup_comboHijo">Comarca y Localidades</label>
		<select id="remoteGroup_comboHijo" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
	</fieldset>
</div>
