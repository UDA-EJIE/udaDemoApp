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
<legend class="wizardLegend">Paso dinámico</legend>
<div id="tabs"></div>
<div id="empleado">
	<input type="radio" name="group1" value="hombre" id="radio_hombre" checked="checked"/> <label for="radio_hombre">Hombre</label>
	<input type="radio" name="group1" value="mujer" id="radio_mujer"/> <label for="radio_mujer">Mujer</label> <br/><br/>
	<label for="nombre">Nombre</label> <input id="nombre" name="nombre" type="text" /> <br/><br/>
	<label for="apellido">Apellido</label> <input id="apellido" name="apellido" type="text" /> <br/><br/>
	<label for="provincia">Delegación</label> <select id="provincia" name="provincia" class="rup-combo"></select>
</div>
<div id="empresa_datos">
	<label for="empresa_nombre">Nombre</label> <input id="empresa_nombre" name="empresa_nombre" type="text" /> <br/><br/> 
	<label for="empresa_web">Website</label> <input id="empresa_web" name="empresa_web" type="text" />  <br/><br/>
	<label for="empresa_email">E-mail</label> <input id="empresa_email" name="empresa_email" type="text" /> <br/><br/>
</div>
<div id="empresa_araba">
	<label for="direccion_araba">Dirección</label> <input id="direccion_araba" name="direccion_araba" type="text" /> <br/><br/> 
	<label for="telefono_araba">Teléfono</label> <input id="telefono_araba" name="telefono_araba" type="text" /> <br/><br/>
</div>
<div id="empresa_bizkaia">
	<label for="direccion_bizkaia">Dirección</label> <input id="direccion_bizkaia" name="direccion_bizkaia"  type="text" /> <br/><br/>
	<label for="telefono_bizkaia">Teléfono</label> <input id="telefono_bizkaia" name="telefono_bizkaia" type="text" /> <br/><br/> 
</div>
<div id="empresa_gipuzkoa">
	<label for="direccion_gipuzkoa">Dirección</label> <input id="direccion_gipuzkoa" name="direccion_gipuzkoa"  type="text" /> <br/><br/>
	<label for="telefono_gipuzkoa">Teléfono</label> <input id="telefono_gipuzkoa" name="telefono_gipuzkoa" type="text" /> <br/><br/> 
</div>
<div id="otros_datos">
	<label for="aficiones">Aficiones</label><br/><br/> 
	<textarea id="aficiones" name="aficiones" cols="150" rows="10"></textarea> 
</div>