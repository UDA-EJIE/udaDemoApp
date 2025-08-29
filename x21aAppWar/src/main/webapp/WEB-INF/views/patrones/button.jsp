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
		<h2>Botón</h2>
		<hr>

		<h4>Botón básico</h4>
		<p>
			El componente facilita la creación de un
			<code>button</code>
			. El botón puede configurarse mediante los tags de html como mediante
			las propiedades js en la inicialización del componente.
		</p>
		<p>Un ejemplo de creación de un botón básico sería el siguiente:</p>
		<div class="example">
			<button type="button" id="boton">
				<span>Acci&oacute;n</span>
			</button>
		</div>

		<h4>Botón con icono</h4>

		<p>Es posible añadir al botón un icono que identifique de manera
			visual la acción que va a desencadenar.</p>
		<p>
			En el caso de configurarse mediante propiedades js se deberá de hacer
			uso de la propiedad
			<code>iconCss</code>
			.
		</p>
		<div class="example">
			<button type="button" id="btnIconHtml">
				<i class="mdi mdi-cog" aria-hidden="true"></i>
				<span>Configuración HTML</span>
			</button>
			<button type="button" id="btnIconJs">
				<span>Configuración JS</span>
			</button>
		</div>

		<h4>Responsive Web Design</h4>

		<p>En el caso de un sitio web que implemente RWD es posible
			especificar al componente que en unas determinadas resoluciones solo
			muestre el icono. De este modo se aprovechará mejor el espacio
			disponible de la pantalla mejorando la usabilidad.</p>
		<p>
			Para ello se hará uso de las clases de Bootstrap definidas para ello
			como son
			<code>d-none d-md-inline</code>
			,
			<code>d-none</code>
			,
			<code>d-none d-lg-inline</code>
			...
		</p>
		<p>
			Las clases pueden agregarse al label mediante la propiedad
			<code>labelCss</code>
			en el caso de configurarse mediante propiedades Js.
		</p>

		<div class="example">
			<button type="button" id="btnRwdHtmlSm">
				<i class="mdi mdi-cog" aria-hidden="true"></i> 
				<span class="rup-ui-button-text d-none d-md-inline">Rwd Html Sm</span>
			</button>
			<button type="button" id="btnRwdHtmlMd">
				<i class="mdi mdi-cog" aria-hidden="true"></i>
				<span class="rup-ui-button-text d-none d-lg-inline">Rwd Html Md</span>
			</button>
			<button type="button" id="btnRwdJsSm">
				<span>Rwd Js Sm</span>
			</button>
			<button type="button" id="btnRwdJsMd">
				<span>Rwd Js Md</span>
			</button>
		</div>

		<h4>MButtons</h4>

		<p>Entre los componentes RUP existen los MButtons.</p>

		<p>Este tipo de botón consiste en una agrupación de botones que se
			muestran al accionar un botón que sirve como desencadenante.</p>

		<div class="example">
			<div class="rup-mbutton">
				<button type="button" id="btnMButton" data-mbutton="true">
					<i class="mdi mdi-cog" aria-hidden="true"></i> 
					<span class="rup-ui-button-text d-none d-lg-inline">MButton</span>
				</button>
				<ul id="mbuttonContainer" class="rup-mbutton-container"
					aria-labelledby="btnMButton">
					<li>
						<button type="button" id="mbutton-buttonNew">
							<i class="mdi mdi-18px mdi-cog" aria-hidden="true"></i>
							<span class="rup-ui-button-text d-none d-lg-inline">Nuevo</span>
						</button>
					</li>
					<li>
						<button type="button" id="mbutton-buttonEdit">
							<i class="mdi mdi-18px mdi-cog" aria-hidden="true"></i>
							<span class="rup-ui-button-text d-none d-lg-inline">Editar</span>
						</button>
					</li>
					<li>
						<button type="button" id="mbutton-buttonCancel">
							<i class="mdi mdi-18px mdi-cog" aria-hidden="true"></i>
							<span class="rup-ui-button-text d-none d-lg-inline">Cancelar</span>
						</button>
					</li>
				</ul>
			</div>
		</div>

		<h4>RWD MButtons</h4>

		<div class="example">
			<div class="rup-mbutton rup-collapsed-md">
				<button role="button" id="btnMButton" data-mbutton="true" class="btn btn-secondary">
					<i class="mdi mdi-cog" aria-hidden="true"></i>
					<span class="rup-ui-button-text d-none d-sm-inline">MButton</span>
				</button>
				<ul id="mbuttonContainer" class="rup-mbutton-container"
					aria-labelledby="btnMButton">
					<li>
						<button type="button" id="mbutton-buttonNew">
							<i class="mdi mdi-cog" aria-hidden="true"></i>
							<span class="rup-ui-button-text">Nuevo</span>
						</button>
					</li>
					<li>
						<button type="button" id="mbutton-buttonEdit">
							<i class="mdi mdi-cog" aria-hidden="true"></i>
							<span class="rup-ui-button-text">Editar</span>
						</button>
					</li>
					<li>
						<button type="button" id="mbutton-buttonCancel">
							<i class="mdi mdi-cog" aria-hidden="true"></i>
							<span class="rup-ui-button-text">Cancelar</span>
						</button>
					</li>
				</ul>
			</div>
		</div>


		<h4>Botón con desplegable</h4>
		<div class="example">
			<button type="button" id="dropdownHtmlListButton">Lista HTML</button>
			<ul id="dropdownHtmlList" class="rup-dropdown-option-list">
				<spring:url value="/patrones/button" var="urlHashtag" htmlEscape="true"/>
				<li><a href="${urlHashtag}" id="dropdownElem1">Elemento 1</a></li>
				<li><a href="${urlHashtag}" id="dropdownElem2">Elemento 2</a></li>
				<li><a href="${urlHashtag}" id="dropdownElem3">Elemento 3</a></li>
				<li class="divider"></li>
				<li><a href="${urlHashtag}" id="dropdownElem4">Elemento 4</a></li>
			</ul>


			<button type="button" id="dropdownDialogButton">Dialogo</button>
			<div id="dropdownDialog" class="dialog-content-material">
				<form>
					<div class="row">
						<div class="form-groupMaterial col-12">
							<select id="dropdownButton-combo"></select>
							<label for="dropdownButton-combo">Filtros</label>
						</div>
					</div>
					<div class="row">
						<div class="checkbox-material col-12">
							<input type="checkbox" id="dropdownButton-defaultFilter" />
							<label for="dropdownButton-defaultFilter">Filtro por defecto</label>
						</div>
					</div>
				</form>
			</div>
		</div>

		<p>Botón FAB (Floating Action Button)</p>
		<div class="example">
			<button type="button" id="fabButton_18px" class="rup-button-fab-sm" data-fab="true">
				<span class="mdi mdi-18px mdi-cog" />
			</button>
			
			<button type="button" id="fabButton_24px" data-fab="true">
				<span class="mdi mdi-24px mdi-cog" />
			</button>
			
			<button type="button" id="fabButton_36px" data-fab="true">
				<span class="mdi mdi-36px mdi-cog" />
			</button>
			
			<button type="button" id="fabButton_48px" class="rup-button-fab-lg" data-fab="true">
				<span class="mdi mdi-48px mdi-cog" />
			</button>

			<div class="rup-button-fab-group">
				<ul id="fabButtonFixedList" class="rup-button-fab-list">
					<li>
						<button type="button" id="fabButtonList1" data-fab="true"
							class="rup-button-fab-sm rup-button-fab ui-button ui-corner-all ui-widget rup-button">
							<i class="mdi mdi-printer" aria-hidden="true"></i>
						</button>
					</li>
					<li>
						<button type="button" id="fabButtonList2" data-fab="true"
							class="rup-button-fab-sm rup-button-fab ui-button ui-corner-all ui-widget rup-button">
							<i class="mdi mdi-delete" aria-hidden="true"></i>
						</button>
					</li>
					<li>
						<button type="button" id="fabButtonList3" data-fab="true"
							class="rup-button-fab-sm rup-button-fab ui-button ui-corner-all ui-widget rup-button">
							<i class="mdi mdi-pencil" aria-hidden="true"></i>
						</button>
					</li>
				</ul>
				<button type="button" id="fabButtonFixed" data-fab="true"
					data-fixed="true" data-list="fabButtonFixedList"
					class="rup-button-fab rup-button-fixed ui-button ui-corner-all ui-widget rup-button">
					<i class="mdi mdi-24px mdi-plus" aria-hidden="true"></i>
				</button>
			</div>

		</div>

		<h4>Eventos</h4>

		<p>
			El modo recomedado para asociar una función de callback a un evento
			<code>click</code>
			es hacer uso de la función
			<code>.on("click", funcion(){})</code>
			de jQuery.
		</p>
		<p>
			Sin embargo, para mantener la compatibilidad con la configuración Js
			del componente Tollbar, se permite especificar una función de
			callback en la propiedad
			<code>click</code>
			.
		</p>
		<div class="example">
			<button type="button" id="btnClickJQuery">
				<i class="mdi mdi-cog" aria-hidden="true"></i>
				<span class="rup-ui-button-text d-none d-lg-inline">Handler jQuery</span>
			</button>
			<button type="button" id="btnClickRup">Handler RUP</button>
		</div>

		<h4>i18n</h4>

		<p>
			Del mismo modo, para manener la compatibilidad con el componente
			Toolbar, es posible especificar el valor del label del botón mediante
			el atributo
			<code>i18nCaption</code>
		</p>
		<div class="example">
			<button type="button" id="btnI18n">Handler RUP</button>
		</div>
	</div>
</section>
