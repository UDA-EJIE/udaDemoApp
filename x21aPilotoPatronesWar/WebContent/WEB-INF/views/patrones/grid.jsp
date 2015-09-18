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
<h2>Grid</h2>		
<div id="grid">
	<table id="grid_usuarios" cellpadding="0" cellspacing="0">
		<tbody><tr><td>&nbsp;</td></tr></tbody>
	</table>
	<div id="pager"></div>
</div>
<div style="margin-top:20px">
		<label for="id">ID:</label>
		<input type="text" name="id" maxlength="25" id="id" tabindex="1" />
		<br><br>
		
		<label for="nombre">Nombre:</label>
		<input type="text" name="nombre" maxlength="25" id="nombre" tabindex="1" />
		<br><br>
		
		<label for="apellido1">Apellido 1:</label>
		<input type="text" name="apellido1" maxlength="25" id="apellido1" tabindex="1" />
		<br><br>
		
		<label for="apellido2">Apellido 2:</label>
		<input type="text" name="apellido2" maxlength="25" id="apellido2" tabindex="1" />
		<br><br>

		<label for="ejie">Ejie:</label>
		<select name="ejie" id="ejie" tabindex="1">
			<option value="0">No</option>
			<option value="1">Sí</option>
		</select>
		<br><br>

		<label for="fechaAlta">Fecha Alta:</label>
		<input type="text" name="fechaAlta" id="fechaAlta" tabindex="1" />
		<br><br>

		<label for="fechaBaja">Fecha Baja:</label>
		<input type="text" name="fechaBaja" id="fechaBaja" tabindex="1" />
		<br><br>
		
		
	<div style="margin-top:12px">
		<button id="btnReload">reload</button>
		<button id="btnSel">getRow</button>
		<button id="btnEditar">setRow</button>
		<button id="btnInsertar">insertRow</button>
		<button id="btnBorrar">deleteSelectedRow</button>
	</div>
</div>
