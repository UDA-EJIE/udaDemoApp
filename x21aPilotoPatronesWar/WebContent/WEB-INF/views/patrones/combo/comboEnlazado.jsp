<h1>Combo Enlazado (simple)</h1>
 
<div id="local" style="float: left;">
	<h2>Local</h2>
	<div style="margin-left:1.5em;">
		<h3>Provincia</h3>
		<select id="comboAbuelo" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Comarca</h3>
		<select id="comboPadre" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Localidad</h3>
		<select id="comboHijo" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>

<div id="remote" style="float: left; margin-left: 4em;">
	<h2>Remoto</h2>
	<div style="margin-left:1.5em;">
		<h3>Provincia</h3>
		<select id="comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Comarca</h3>
		<select id="comboPadreRemoto" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Localidad</h3>
		<select id="comboHijoRemoto" class="rup-combo"><option>&nbsp;</option></select>
	</div>		
</div>

<div id="mixto" style="float: left; margin-left: 4em;">
	<h2>Mixto I</h2>
	<div style="margin-left:1.5em;">
		<h3>Provincia (remoto)</h3>
		<select id="mixto_comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Comarca (local)</h3>
		<select id="mixto_comboPadre" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Localidad (remoto)</h3>
		<select id="mixto_comboHijoRemoto" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>

<div id="mixto2" style="float: left; margin-left: 4em; margin-bottom: 3em;">
	<h2>Mixto II</h2>
	<div style="margin-left:1.5em;">
		<h3>Provincia (local)</h3>
		<select id="mixto2_comboAbuelo" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Comarca (remoto)</h3>
		<select id="mixto2_comboPadreRemoto" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Localidad (local)</h3>
		<select id="mixto2_comboHijo" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>
