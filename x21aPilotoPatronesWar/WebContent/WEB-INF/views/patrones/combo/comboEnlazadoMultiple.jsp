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
<h2>Combo Enlazado (multiple)</h2>

<div id="local" style="float: left;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Local (no i18n)</legend>
		
		<label for="departamento">Departamento</label>
		<select id="departamento" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="provincia">Provincia</label>
		<select id="provincia" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="dptoProv">Departamento-Provincia</label>
		<select id="dptoProv" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>

<div id="remote" style="float: left; margin-left: 4em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Remoto</legend>
		
		<label for="departamentoRemote">Departamento</label>
		<select id="departamentoRemote" name="departamento" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="provinciaRemote">Provincia</label>
		<select id="provinciaRemote" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="dptoProvRemote">Departamento-Provincia</label>
		<select id="dptoProvRemote" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>		
</div>

<div id="mixto" style="float: left; margin-left: 4em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Mixto I</legend>
		
		<label for="mixto_departamentoRemote">Departamento (remoto)</label>
		<select id="mixto_departamentoRemote" name="departamento" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto_provincia">Provincia (local)</label>
		<select id="mixto_provincia" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto_dptoProvRemote">Departamento-Provincia (remoto)</label>
		<select id="mixto_dptoProvRemote" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>

<div id="mixto2" style="float: left; margin-left: 4em; margin-bottom: 3em;">
	<fieldset class="combo_fieldset">
		<legend class="combo_legend">Mixto II</legend>
		
		<label for="mixto2_departamento">Departamento (local)</label>
		<select id="mixto2_departamento" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto2_provinciaRemote">Provincia (remoto)</label>
		<select id="mixto2_provinciaRemote" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
		
		<label for="mixto2_dptoProv">Departamento-Provincia (local)</label>
		<select id="mixto2_dptoProv" class="rup-combo"><option>&nbsp;</option></select>
		<br><br>
	</fieldset>
</div>