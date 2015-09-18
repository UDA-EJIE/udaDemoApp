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
<h1>Combo Enlazado (multiple)</h1>

<div id="local" style="float: left;">
	<h2>Local (no i18n)</h2>
	<div style="margin-left:1.5em;">
		<h3>Departamento</h3>
		<select id="departamento" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Provincia</h3>
		<select id="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Departamento-Provincia</h3>
		<select id="dptoProv" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>

<div id="remote" style="float: left; margin-left: 4em;">
	<h2>Remoto</h2>
	<div style="margin-left:1.5em;">
		<h3>Departamento</h3>
		<select id="departamentoRemote" name="departamento" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Provincia</h3>
		<select id="provinciaRemote" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Departamento-Provincia</h3>
		<select id="dptoProvRemote" class="rup-combo"><option>&nbsp;</option></select>
	</div>		
</div>

<div id="mixto" style="float: left; margin-left: 4em;">
	<h2>Mixto I</h2>
	<div style="margin-left:1.5em;">
		<h3>Departamento (remoto)</h3>
		<select id="mixto_departamentoRemote" name="departamento" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Provincia (local)</h3>
		<select id="mixto_provincia" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Departamento-Provincia (remoto)</h3>
		<select id="mixto_dptoProvRemote" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>

<div id="mixto2" style="float: left; margin-left: 4em; margin-bottom: 3em;">
	<h2>Mixto II</h2>
	<div style="margin-left:1.5em;">
		<h3>Departamento (local)</h3>
		<select id="mixto2_departamento" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Provincia (remoto)</h3>
		<select id="mixto2_provinciaRemote" class="rup-combo"><option>&nbsp;</option></select>
		
		<h3>Departamento-Provincia (local)</h3>
		<select id="mixto2_dptoProv" class="rup-combo"><option>&nbsp;</option></select>
	</div>
</div>