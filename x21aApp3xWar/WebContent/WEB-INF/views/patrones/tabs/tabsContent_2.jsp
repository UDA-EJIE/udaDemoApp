<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 -- Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 -- SIN GARANTÃAS NI CONDICIONES DE NINGÃN TIPO, ni expresas ni implÃ­citas.
 -- VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<h1>PestaÃ±as de navegaciÃ³n</h1>
<spring:url value="#" var="urlHashtag" htmlEscape="true"/>
<p id="docu"><a target="_blank" href="${urlHashtag}">Descargar documentaciÃ³n</a></p>
<br>

	<h2>DescripciÃ³n</h2>
	<p>MenÃº de  navegaciÃ³n de la aplicaciÃ³n que divide Ã©sta en secciones claramente excluyentes  entre sÃ­ y da acceso a ellas.</p>
	<h2>Usar cuando</h2>
	<p>Cuando tengamos una aplicaciÃ³n web cuyos contenidos  se puedan dividir en secciones claramente excluyentes entre sÃ­ y queramos  proporcionar a los usuarios un menÃº de navegaciÃ³n para navegar por ellas.</p>

	<h2>Accesibilidad</h2>
	<p>Es muy importante que los contenidos estÃ¡n  estructurados y jerarquizados correctamente en funciÃ³n de los modelos mentales  de los usuarios y que los rÃ³tulos sean sencillos de comprender. Cuanto mÃ­s  lÃ³gica, sencilla y fÃ¡cil de comprender sea la navegaciÃ³n mÃ­s accesible serÃ¡,  especialmente para los usuarios con problemas cognitivos o sordera prelocutiva  ya que estos perfiles de accesibilidad pueden tener problemas a la hora de  comprender estructuras de informaciÃ³n complejas o terminologÃ­a muy especÃ­fica.<br>
    <br>
    	Los menÃºs de navegaciÃ³n basados en pestaÃ±as, si estÃ¡n bien implementados,  pueden suponer una mejora de accesibilidad notable ya que facilitan mucho la  comprensiÃ³n de la estructura de la aplicaciÃ³n y del funcionamiento del sistema  de navegaciÃ³n.</p>
		<p><em>Nivel de accesibilidad alcanzado por cada tecnologÃ­a</em>:</p>
		<ul>
			<li style="margin-left: 25px; margin-top: 20px;"><em>Geremua 2:</em> El componente ha sido implementado con jQuery, con lo que al ejecutar javascript no cumple las normas de accesibilidad.</li>
			<li style="margin-left: 25px;"><em>.NET:</em> El componente ha sido implementado con jQuery, con lo que al ejecutar javascript no cumple las normas de accesibilidad.</li>
		</ul>
		<h2>PatrÃ³n relacionado con </h2>
		<ul>
		  <li style="margin-left: 25px; margin-top: 20px;">2.1.1. OrganizaciÃ³n y jerarquÃ­a de pantalla</li>
		  <li style="margin-left: 25px;">2.2.1.  MenÃºs de navegaciÃ³n</li>
		</ul>

