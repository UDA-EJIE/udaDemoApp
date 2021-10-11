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
			<label for="selectSimple">Select simple local</label>
			<select id="selectSimple"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="selectRemoto">Select remoto</label>
			<select id="selectRemoto"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="selectLargoMulti">Select Multi con texto largo</label>
			<select id="selectLargoMulti"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="selectLargo">Select Simple con texto largo</label>
			<select id="selectLargo"></select>
		</div>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="selectGrupos">Select con 'optgroups'</label>
			<select id="selectGrupos"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="selectGruposRemoto">Select con 'optgroups' remoto</label>
			<select id="selectGruposRemoto"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="selectImgs">Select (no i18n) con imagenes</label>
			<select id="selectImgs"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="SelectLoadFromSelect">Select carga inicial desde <strong>HTML</strong></label>
			<select id="SelectLoadFromSelect">
				<option value="1">Alava</option>
				<option value="3">Gipuzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select>
		</div>
	</div>
</div>
