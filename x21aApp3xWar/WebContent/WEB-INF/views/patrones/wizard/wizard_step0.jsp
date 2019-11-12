<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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
<fieldset>
	<legend class="wizardLegend">Datos de la cuenta</legend>
	<label for="username">Usuario</label> <form:input id="username" path="username" type="text" /> <br/><br/>
	<label for="password">Password</label> <form:input id="password" path="password" type="password" /> <br/><br/>
	<label for="ejie">Ejie</label> 
		<form:select id="ejie">
			<form:option value="0" selected="selected">No</form:option>
			<form:option value="1">Sí</form:option>
		</form:select> 
	<br/><br/>
</fieldset>
