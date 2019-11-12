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

		<h3>Botonera</h3>
		<hr>

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



		<h4>Compatibilidad jQueryUI</h4>
		<p>El componente proporciona compatibilidad con las
			configuraciones y estilos utilizados en las aplicaciones UDA
			anteriores a la versión v3.0.0.</p>
		<p>En el caso de que se quiera inicializar un componente Toolbar
			con el modo de compatibilidad activado, se debera:</p>
		<ul>
			<li>Utilizar la propiedad <code>adapter:
					"toolbar_jqueryui"</code> en la inicialización del componente.
			</li>
			<li>Indicar la propiedad <code>data-adapter="toolbar_jqueryui"</code>
				en el <code>&lt;div&gt;</code> sobre el que se inicializa el
				componente.
			</li>
		</ul>
		<p>Este es un ejemplo de las posibles configuraciones:</p>

		<div class="example">

			<div id="jQueryUIToolbar"></div>
			<br />
			<div class="separator"></div>
			<div class="separator"></div>
			<br />
			<div id="jQueryUIToolbarMixta"></div>
			<br />
			<div class="separator"></div>
			<div class="separator"></div>
			<br />
			<div id="jQueryUIToolbarRight"></div>

		</div>
	</div>
</section>


<!-- <div id="toolbarNew" class="rup-toolbar">

  <button type="button"class="btn btn-secondary">
    <i class="fa fa-search" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Buscar</span>
  </button>
  <button type="button"class="btn btn-secondary">
    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Editar</span>
  </button>
  <button type="button"class="btn btn-secondary">
    <i class="fa fa-trash-o" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Borrar</span>
  </button>
  <button type="button"class="btn btn-secondary">
    <i class="fa fa-filter" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Filtrar</span>
  </button>

  <button type="button"class="btn btn-secondary rup-button-right rup-toolbar_menuButton" data-mbutton="ficherosMButton">
    <i class="fa fa-print" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Ficheros</span>
  </button>
  <div class="rup-toolbar-mbutton" id="ficherosMButton">
    <li>
      <button type="button"class="btn btn-secondary rup-button-right">
        <i class="fa fa-print" aria-hidden="true"></i>
        <span class="rup-ui-button-text">Ficheros</span>
      </button>
    </li>
    <li>
      <button type="button"class="btn btn-secondary rup-button-right">
        <i class="fa fa-print" aria-hidden="true"></i>
        <span class="rup-ui-button-text">Ficheros</span>
      </button>
    </li>
    <li>
      <button type="button"class="btn btn-secondary rup-button-right">
        <i class="fa fa-print" aria-hidden="true"></i>
        <span class="rup-ui-button-text">Ficheros</span>
      </button>
    </li>
    <li>
      <button type="button"class="btn btn-secondary rup-button-right">
        <i class="fa fa-print" aria-hidden="true"></i>
        <span class="rup-ui-button-text">Ficheros</span>
      </button>
    </li>
  </ul>


  <button type="button"class="btn btn-secondary rup-button-right">
    <i class="fa fa-print" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Imprimir</span>
  </button>
  <button type="button"class="btn btn-secondary rup-button-right">
    <i class="fa fa-times-circle" aria-hidden="true"></i>
    <span class="rup-ui-button-text">Cancelar</span>
  </button>



</div> -->
