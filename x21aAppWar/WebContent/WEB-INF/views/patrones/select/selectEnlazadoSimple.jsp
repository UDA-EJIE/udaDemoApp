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
<h2>Select Enlazado (simple)</h2>

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<label for="selectAbuelo">Provincia</label>
				<select id="selectAbuelo"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectPadre">Comarca</label>
				<select id="selectPadre"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectHijo">Localidad</label>
				<select id="selectHijo"><option>&nbsp;</option></select>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto</legend>
			
			<input class="d-none" id="hiddenAbueloRemoto" name="provincia" value="02" />
			<div class="form-groupMaterial">
				<label for="selectAbueloRemoto">Provincia</label>
				<select id="selectAbueloRemoto" name="provincia"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectPadreRemoto">Comarca</label>
				<select id="selectPadreRemoto" name="comarca"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectHijoRemoto">Localidad</label>
				<select id="selectHijoRemoto"><option>&nbsp;</option></select>
			</div>
		</fieldset>
		
		<!--
		<fieldset id="mixto" class="col-sm">
			<legend>Mixto I</legend>
			
			<div class="form-groupMaterial">
				<label for="mixto_selectAbueloRemoto">Provincia (remoto)</label>
				<select id="mixto_selectAbueloRemoto" name="provincia"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="mixto_selectPadre">Comarca (local)</label>
				<select id="mixto_selectPadre" name="comarca"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="mixto_selectHijoRemoto">Localidad (remoto)</label>
				<select id="mixto_selectHijoRemoto"><option>&nbsp;</option></select>
			</div>
		</fieldset>
		-->
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<!--
		<div id="mixto2" class="col-sm">
			<fieldset class="select_fieldset mr-sm-3">
				<legend class="select_legend">Mixto II</legend>
				
				<div class="form-groupMaterial">
					<label for="mixto2_selectAbuelo">Provincia (local)</label>
					<select id="mixto2_selectAbuelo" name="provincia"><option>&nbsp;</option></select>
				</div>
				
				<div class="form-groupMaterial">
					<label for="mixto2_selectPadreRemoto">Comarca (remoto)</label>
					<select id="mixto2_selectPadreRemoto"><option>&nbsp;</option></select>
				</div>
				
				<div class="form-groupMaterial">
					<label for="mixto2_selectHijo">Localidad (local)</label>
					<select id="mixto2_selectHijo"><option>&nbsp;</option></select>
				</div>
			</fieldset>
		</div>
		-->
		
		<div id="remoteGroup" class="col-sm">
			<fieldset>
				<legend>Remoto agrupado</legend>
		<!--	<div class="form-groupMaterial">	-->
		<!-- 		<label for="mixto2_selectAbuelo">Provincia (local)</label>	-->
		<!-- 		<select id="mixto2_selectAbuelo" name="provincia"><option>&nbsp;</option></select>	-->
		<!-- 	</div>	 -->
				<div class="form-groupMaterial">
					<label for="remoteGroup_selectPadre">Provincia</label>
					<select id="remoteGroup_selectPadre" name="provincia"><option>&nbsp;</option></select>
				</div>
				
				<div class="form-groupMaterial">
					<label for="remoteGroup_selectHijo">Comarca y Localidades</label>
					<select id="remoteGroup_selectHijo"><option>&nbsp;</option></select>
				</div>
			</fieldset>
		</div>
	</div>
</div>
