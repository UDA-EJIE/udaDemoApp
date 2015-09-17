<h1>Combo Enlazado (simple)</h1>
 
<div id="local" style="float: left;">
	<h2>Local</h2>
	<div style="margin-left:1.5em;">
		<h3><label for="comboAbuelo">Combo abuelo</label></h3>
		<select id="comboAbuelo" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="comboPadre">Combo padre</label></h3>
		<select id="comboPadre" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="comboHijo">Combo hijo</label></h3>
		<select id="comboHijo" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>

<div id="remote" style="float: left; margin-left: 4em;">
	<h2>Remoto</h2>
	<div style="margin-left:1.5em;">
		<h3><label for="comboAbueloRemoto">Combo abuelo</label></h3>
		<select id="comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="comboPadreRemoto">Combo padre</label></h3>
		<select id="comboPadreRemoto" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="comboHijoRemoto">Combo hijo</label></h3>
		<select id="comboHijoRemoto" class="rup-combo"><option>&nbsp;</option></select>
	</div>		
</div>

<div id="mixto" style="float: left; margin-left: 4em;">
	<h2>Mixto I</h2>
	<div style="margin-left:1.5em;">
		<h3><label for="mixto_comboAbueloRemoto">Combo abuelo (remoto)</label></h3>
		<select id="mixto_comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="mixto_comboPadre">Combo padre (local)</label></h3>
		<select id="mixto_comboPadre" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="mixto_comboHijoRemoto">Combo hijo (remoto)</label></h3>
		<select id="mixto_comboHijoRemoto" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>

<div id="mixto2" style="float: left; margin-left: 4em; margin-bottom: 3em;">
	<h2>Mixto II</h2>
	<div style="margin-left:1.5em;">
		<h3><label for="mixto2_comboAbuelo">Combo abuelo (local)</label></h3>
		<select id="mixto2_comboAbuelo" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="mixto2_comboPadreRemoto">Combo padre (remoto)</label></h3>
		<select id="mixto2_comboPadreRemoto" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3><label for="mixto2_comboHijo">Combo hijo (local)</label></h3>
		<select id="mixto2_comboHijo" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>
