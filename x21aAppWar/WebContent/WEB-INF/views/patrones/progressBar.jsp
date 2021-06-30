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
		<h2>
			<spring:message code="progressBar" />
		</h2>
		<hr>

		<div class="card">
			<div class="card-header">Barra de progreso simple</div>
			<div class="card-body">
				<blockquote class="blockquote">
					<div id="progressBar"></div>
				</blockquote>
			</div>
		</div>

		<div class="card">
			<div class="card-header">Barra de progreso mostrando un mensaje</div>
			<div class="card-body">
				<blockquote class="blockquote">
					<div id="progressBarLabel"></div>
			</div>
		</div>

		<div class="card">
			<div class="card-header">Barra de progreso sin mostrar un valor
				concreto</div>
			<div class="card-body">
				<blockquote class="blockquote">
					<div id="progressBarValueFalse"></div>
				</blockquote>
			</div>
		</div>
	</div>
</section>