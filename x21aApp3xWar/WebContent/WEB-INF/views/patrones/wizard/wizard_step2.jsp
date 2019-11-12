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
	<legend class="wizardLegend">Datos del trabajador</legend>
	<div id="tabs"></div>
	<div id="empleado" style="display: none;">
		<form:radiobutton path="group1" value="hombre" id="radio_hombre" checked="checked"/> <label for="radio_hombre">Hombre</label>
		<form:radiobutton path="group1" value="mujer" id="radio_mujer"/> <label for="radio_mujer">Mujer</label> <br/><br/>
		<label for="nombre">Nombre</label> <form:input id="nombre" path="nombre" /> <br/><br/>
		<label for="apellido">Apellido</label> <form:input id="apellido" path="apellido" /> <br/><br/>
		<label for="provincia">Delegación</label> <form:select id="provincia" path="provincia" class="rup-combo"/>
	</div>
	<div id="empresa_datos" style="display: none;">
		<label for="empresa_nombre">Nombre</label> <form:input id="empresa_nombre" path="empresa_nombre" /> <br/><br/> 
		<label for="empresa_web">Website</label> <form:input id="empresa_web" path="empresa_web" />  <br/><br/>
		<label for="empresa_email">E-mail</label> <form:input id="empresa_email" path="empresa_email" /> <br/><br/>
	</div>
	<div id="empresa_araba" style="display: none;">
		<label for="direccion_araba">Dirección</label> <form:input id="direccion_araba" path="direccion_araba" /> <br/><br/> 
		<label for="telefono_araba">Teléfono</label> <form:input id="telefono_araba" path="telefono_araba" /> <br/><br/>
	</div>
	<div id="empresa_bizkaia" style="display: none;">
		<label for="direccion_bizkaia">Dirección</label> <form:input id="direccion_bizkaia" path="direccion_bizkaia" /> <br/><br/>
		<label for="telefono_bizkaia">Teléfono</label> <form:input id="telefono_bizkaia" path="telefono_bizkaia" /> <br/><br/> 
	</div>
	<div id="empresa_gipuzkoa" style="display: none;">
		<label for="direccion_gipuzkoa">Dirección</label> <form:input id="direccion_gipuzkoa" path="direccion_gipuzkoa" /> <br/><br/>
		<label for="telefono_gipuzkoa">Teléfono</label> <form:input id="telefono_gipuzkoa" path="telefono_gipuzkoa" /> <br/><br/> 
	</div>
	<div id="otros_datos" style="display: none;">
		<label for="aficiones">Aficiones</label><br/><br/> 
		<form:textarea id="aficiones" path="aficiones" cols="150" rows="10" />
	</div>
</fieldset>
	