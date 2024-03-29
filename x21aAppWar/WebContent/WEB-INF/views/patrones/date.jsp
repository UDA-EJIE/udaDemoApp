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
		<h2 class="title mb-3">
			<spring:message code="date.title" />
		</h2>
		<p>
			<spring:message code="date.paragraph.line1" />
		</p>
		<p>El componente permite personalizar su uso mediante los
			parámetros de inicialización. Esto nos permite utilizar el componente
			fecha de los siguientes modos:</p>
		<h4>Fecha simple</h4>
		<p>El uso más básico del componente es mostrar un campo de texto
			simple con un control que permite desplegar el control de selección
			de fecha. Permite mostrar al usuario a modo de ayuda, la máscara de
			entrada que se va a emplear para la fecha a introducir. Existen dos
			modos de hacerlo:</p>
		<ul>
			<li><code>labelMaskId</code>: Permite indicar el id del elemento
				en el que se indicará el valor de la máscara.</li>
			<li><code>placeholderMask</code>: Muestra en el placeholder del
				elemento la máscara de fecha.</li>
		</ul>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<input id="fecha" type="text" />
				<label for="fecha">Fecha <span class="text-muted" id="fecha-mask"/>:</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input id="fechaPlaceholder" type="text" />
				<label for="fechaPlaceholder">Prueba</label>
				<label for="fechaPlaceholder">Varios</label>
				<label for="fechaPlaceholder">Labels:</label>
			</div>
		</div>

		<h4>Selección múltiple</h4>
		<p>
			El componente permite seleccionar varias fechas en un mismo campo.
			Para ello se debe de configurar el componente mediante la propiedad
			<code>multiSelect</code>
			.
		</p>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<input id="fecha_multi" type="text" />
				<label for="fecha_multi">Fecha multiple:</label>
			</div>
		</div>

		<h4>Intervalo de fechas</h4>

		<p>Se permite vincular dos campos fecha (desde y hasta) para
			permitir al usuario especificar un intervalo de fechas.</p>
		<div class="form-row">
          	<div class="form-groupMaterial col-sm">
              	<input type="text" id="desde" />
              	<label for="desde">Intervalo desde:</label>
          	</div>
          	<div class="form-groupMaterial col-sm">
            	<input type="text" id="hasta" />
            	<label for="hasta">hasta:</label>
        	</div>
      	</div>
		<div class="form-row">
            <div class="form-groupMaterial col-sm">
                <input type="text" id="desdeDateTime" />
                <label for="desdeDateTime">Intervalo desde:</label>
            </div>
            <div class="form-groupMaterial col-sm">
                <input type="text" id="hastaDateTime" />
                <label for="hastaDateTime">hasta:</label>
            </div>
		</div>

		<h4>Calendario en línea</h4>

		<p>El componente fecha permite su visualización en modo calendario
			en vez de estar asociado a un campo concreto.</p>

		<div class="form-row">
			<div id="fecha_inline"></div>
		</div>
	</div>
</section>
