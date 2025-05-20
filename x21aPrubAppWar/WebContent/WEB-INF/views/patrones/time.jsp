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
		<h2>Hora</h2>
		<hr>

		<p>El usuario puede introducir y seleccionar una hora tanto de
			forma manual como visual, moviéndose fácilmente por las horas y los
			minutos, recibiendo ayudas y sugerencias para minimizar las
			posibilidades de introducir una hora incorrecta.</p>

		<h4>Hora simple</h4>
		<p>El uso más básico del componente es mostrar un campo de texto
			simple con un control que permite desplegar el control de selección
			de hora. Permite mostrar al usuario a modo de ayuda, la máscara de
			entrada que se va a emplear para la hora a introducir. Existen dos
			modos de hacerlo:</p>
		<ul>
			<li><code>labelMaskId</code>: Permite indicar el id del elemento
				en el que se indicará el valor de la máscara.</li>
			<li><code>placeholderMask</code>: Muestra en el placeholder del
				elemento la máscara de hora.</li>
		</ul>
		<label for="hora2">Exterior </label>

		<div class="form-row example">
			<div class="form-groupMaterial col-sm">
				<input id="hora" type="text" />
				<label for="hora">Hora <span id="hora-mask"></span></label>
			</div>
			<div class="form-groupMaterial col-sm">
				<input id="horaPlaceholder" type="text" />
				<label for="horaPlaceholder">Hora </label>
			</div>
		</div>

		<p>El componente permite configurar muchos aspectos de su
			comportamiento. Un ejemplo de personalización sería:</p>
		<div class="form-row example">
			<div class="form-groupMaterial col-sm">
				<input id="hora2" type="text" />
				<label for="hora2">Hora </label>
				<label for="hora2">Label2 </label>
			</div>
		</div>

		<h4>Selector en línea</h4>

		<p>El componente hora permite su visualización en modo directo,
			mostrando el selector de hora completo en vez de estar asociado a un
			campo concreto.</p>

		<div class="example">
			<div id="hora_inline"></div>
		</div>
	</div>
</section>
