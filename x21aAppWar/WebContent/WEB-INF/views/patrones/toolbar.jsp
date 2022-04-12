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
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<section class="row">
	<div class="col-12">

		<h2 class="title mb-3">Botonera</h2>

		<p>Se les presenta a los usuarios una barra de botones con
			diversas funcionalidades relacionadas a elementos de la página.
			Gracias a este componente se presentan, ordenan y agrupan las
			distintas funcionalidades gestionadas por las aplicaciones.</p>
		<p>La configuración de la botonera puede realizarse mediante un
			json en el que indican los diferentes botones que se van a incluir o
			directamente mediante elementos HTML.</p>
		<p>Un ejemplo de construcción de una botonera a partir de un json
			sería la siguiente:</p>
		<div class="example">
			<div id="toolbar"></div>
		</div>

		<p>En el json de definición de los botones es posible especificar
			que ciertos botones se agrupen a la izquierda de la botonera.</p>
		<p>
			Para ello, se debe de hacer uso de la propiedad
			<code>right: true</code>
			en la definición del botón.
		</p>
		<div class="example">
			<div id="toolbarRight"></div>
		</div>

		<p>Naturalmente, es posible combinar botones alineados tanto a la
			derecha como a la izquierda.</p>
		<div class="example">
			<div id="toolbarMixta"></div>
		</div>

		<p>Configruación de mbuttons responsive</p>
		<div class="example">
			<div id="toolbarRwd"></div>
		</div>
		
	</div>
</section>
