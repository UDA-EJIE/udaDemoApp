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
	<div class="col-xs-12">
		<h2>Context Menu</h2>
		<hr>

		<div id="contextMenu" class="card card-inverse"
			style="background-color: #333; border-color: #333;">
			<div class="card-block">
				<h2 class="card-blockquote">
					<center>Right click + Opciones desabilitadas</center>
				</h2>
			</div>
		</div>

		<div id="contextMenu-left" class="card card-inverse contextMenu-left"
			style="background-color: #333; border-color: #333;">
			<div class="card-block">
				<h2 class="card-blockquote">
					<center>Left click + Atajos de teclado</center>
				</h2>
			</div>
		</div>

		<div id="contextMenu-hover" class="card card-inverse"
			style="background-color: #333; border-color: #333;">
			<div class="card-block">
				<h2 class="card-blockquote">
					<center>Hover + submenus</center>
				</h2>
			</div>
		</div>

		<div id="contextMenu-other"
			class="card card-inverse contextMenu-other"
			style="background-color: #333; border-color: #333;">
			<div class="card-block">
				<h2 class="card-blockquote">
					<center>
						Abrir desde otro elemento + bajo demanda
						<button type="button" id="activate-menu">click me</button>
					</center>
				</h2>
			</div>
		</div>
	</div>
</section>