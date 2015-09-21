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
 <%@include file="/WEB-INF/includeTemplate.inc"%>	
<h2>Tooltip</h2>
<fieldset>
	<div>
		<label for="nombre">Nombre:</label>
		<input id="nombre" name="nombre" title="Introduzca su nombre."/>
	</div>
	<br/>
	<div>
		<label for="apellido">Apellido:</label>
		<input id="apellido" name="apellido" title="Introduzca su apellido."/>
	</div>
	<br/>
	<div>
		<label for="code">Código:</label>
		<input id="code" name="code" />&nbsp;<img id="codeTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/basic-theme/images/rup.confirm.png" />
	</div>
	<br/>
	<div>
		<label for="identificador">Identificador:</label>
		<input id="identificador" name="identificador" />&nbsp;<img id="idTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/basic-theme/images/rup.confirm.png" />
	</div>
	<br/>
	<input id="button" type="button" value="Aplicar ThemeRoller" />
</fieldset>