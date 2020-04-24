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
 -- SIN GARANTíAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implí­citas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<h1>Pestañas de navegación</h1>
<string:url value="#" var="urlHashtag" htmlEscape="true"/>
<p id="docu"><a target="_blank" href="${urlHashtag}">Descargar documentación</a></p>
<br>

	<h2>Descripción</h2>
	<p>Menú de  navegación de la aplicación que divide ésta en secciones claramente excluyentes  entre sí­ y da acceso a ellas.</p>
	<h2>Usar cuando</h2>
	<p>Cuando tengamos una aplicación web cuyos contenidos  se puedan dividir en secciones claramente excluyentes entre sí­ y queramos  proporcionar a los usuarios un menú de navegación para navegar por ellas.</p>

	<h2>Accesibilidad</h2>
	<p>Es muy importante que los contenidos están  estructurados y jerarquizados correctamente en función de los modelos mentales  de los usuarios y que los rótulos sean sencillos de comprender. Cuanto mí­s  lógica, sencilla y fácil de comprender sea la navegación mí­s accesible será,  especialmente para los usuarios con problemas cognitivos o sordera prelocutiva  ya que estos perfiles de accesibilidad pueden tener problemas a la hora de  comprender estructuras de información complejas o terminologí­a muy especí­fica.<br>
    <br>
    	Los menús de navegación basados en pestañas, si están bien implementados,  pueden suponer una mejora de accesibilidad notable ya que facilitan mucho la  comprensión de la estructura de la aplicación y del funcionamiento del sistema  de navegación.</p>
		<p><em>Nivel de accesibilidad alcanzado por cada tecnologí­a</em>:</p>
		<ul>
			<li style="margin-left: 25px; margin-top: 20px;"><em>Geremua 2:</em> El componente ha sido implementado con jQuery, con lo que al ejecutar javascript no cumple las normas de accesibilidad.</li>
			<li style="margin-left: 25px;"><em>.NET:</em> El componente ha sido implementado con jQuery, con lo que al ejecutar javascript no cumple las normas de accesibilidad.</li>
		</ul>
		<h2>Patrón relacionado con </h2>
		<ul>
		  <li style="margin-left: 25px; margin-top: 20px;">2.1.1. Organización y jerarquí­a de pantalla</li>
		  <li style="margin-left: 25px;">2.2.1.  Menús de navegación</li>
		</ul>

