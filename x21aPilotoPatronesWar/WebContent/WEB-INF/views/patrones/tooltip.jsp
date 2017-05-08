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

<div id="accordionExample1" class="rup_accordion" style="display: none;">

	<h1><a><spring:message  code="seccion1" /></a></h1>
	<div class="section">
		<img alt="La primera sección" src="${staticsUrl}/x21a/images/primera_seccion.PNG">
	</div>
	<h1><a><spring:message  code="seccion2" /></a></h1>
	<div class="section2">
		<img alt="La segunda sección" src="${staticsUrl}/x21a/images/segunda_seccion.PNG">
	</div>
	<h1><a><spring:message  code="seccion3" /></a></h1>
	<div class="section">
		<img alt="La tercera sección" src="${staticsUrl}/x21a/images/tercera_seccion.PNG">
	</div>
	<h1><a><spring:message  code="seccion4" /></a></h1>
	<div class="section2">
		<img alt="La cuarta sección" src="${staticsUrl}/x21a/images/cuarta_seccion.PNG">
	</div>
	
</div>

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
		<input id="code" name="code" />&nbsp;<img id="codeTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/css/images/rup.confirm.png" />
	</div>
	<br/>
	<div>
		<label for="identificador">Identificador:</label>
		<input id="identificador" name="identificador" />&nbsp;<img id="idTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/css/images/rup.confirm.png" />
	</div>
	<br/>
	<div>
		<label for="htmlTooltip">HTML:</label>
		<input id="htmlTooltip" name="htmlTooltip" />&nbsp;<img id="idHtmlTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/css/images/rup.confirm.png" />
	</div>
	<br/>
	<input id="button" type="button" value="Aplicar ThemeRoller" />
</fieldset>