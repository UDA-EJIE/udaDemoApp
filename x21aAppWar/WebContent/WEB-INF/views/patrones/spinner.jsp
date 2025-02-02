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
		<h2>Spinner</h2>
		<hr>
		<p>
			<label for="spinner">Seleccione un valor:</label> <input id="spinner"
				name="value">
		</p>

		<p>
			<button id="disable">Habilitar/deshabilitar</button>
			<button id="destroy">Destruir/inicializar</button>
		</p>

		<p>
			<button id="getvalue">Obtener valor</button>
			<button id="setvalue">Asignar valor a 5</button>
		</p>
	</div>
</section>

