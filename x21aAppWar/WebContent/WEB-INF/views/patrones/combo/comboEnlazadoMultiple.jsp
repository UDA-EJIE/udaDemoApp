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
<h2 class="title mb-3">Combo Enlazado (múltiple)</h2>

<!-- Feedback -->
<div id="x21aAppWar_feedback"></div><br/>
 
<div class="container-fluid">
	<div class="form-row">
		<fieldset class="col-sm mr-sm-5">
			<legend>Local (no i18n)</legend>
			
			<div class="form-groupMaterial">
				<label for="departamento">Departamento</label>
				<select id="departamento"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="provincia">Provincia</label>
				<select id="provincia"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="dptoProv">Departamento-Provincia</label>
				<select id="dptoProv"><option>&nbsp;</option></select>
			</div>
		</fieldset>
	
		<fieldset class="col-sm">
			<legend>Remoto</legend>
			
			<div class="form-groupMaterial">
				<label for="departamentoRemote">Departamento</label>
				<select id="departamentoRemote" name="departamento"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="provinciaRemote">Provincia</label>
				<select id="provinciaRemote" name="provincia"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="dptoProvRemote">Departamento-Provincia</label>
				<select id="dptoProvRemote"><option>&nbsp;</option></select>
			</div>
		</fieldset>
	</div>
</div>
