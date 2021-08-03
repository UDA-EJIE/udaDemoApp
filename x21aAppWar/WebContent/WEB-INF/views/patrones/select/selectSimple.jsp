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
<h2>Select</h2>
<div class="container-fluid">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="select">Select simple local</label>
			<select id="selectSimple"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="comboRemoto">Combo remoto</label>
			<select id="comboRemoto"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="comboLargo">Combo con texto largo</label>
			<select id="comboLargo"></select>
		</div>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="comboGrupos">Combo con 'optgroups'</label>
			<select id="comboGrupos"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="comboGruposRemoto">Combo con 'optgroups' remoto</label>
			<select id="comboGruposRemoto"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="comboImgs">Combo (no i18n) con imagenes</label>
			<select id="comboImgs"></select>
		</div>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="comboInput">Combo sobre <strong>Input</strong></label>
			<input type="text" name="comboInput" id="comboInput" value="python" />
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="comboLoadFromSelect">Combo carga inicial desde <strong>HTML</strong></label>
			<select id="comboLoadFromSelect">
				<option value="1">Alava</option>
				<option value="3">Gipuzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select>
		</div>
	</div>
</div>
