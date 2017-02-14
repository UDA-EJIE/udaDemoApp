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
 
<h4>Botón básico</h4>
<p>El componente facilita la creación de un <code>button</code>. El botón puede configurarse mediante los tags de html como mediante las propiedades js en la inicialización del componente.</p>
<p>Un ejemplo de creación de un botón básico sería el siguiente:</p>
<div class="example">
	<button type="button" id="boton">Acci&oacute;n</button>
</div>

<h4>Botón con icono</h4>

<p>Es posible añadir al botón un icono que identifique de manera visual la acción que va a desencadenar.</p>
<p>En el caso de configurarse mediante propiedades js se deberá de hacer uso de la propiedad <code>iconCss</code>.</p>
<div class="example">
	<button type="button" id="btnIconHtml">
		<i class="fa fa-cog" aria-hidden="true"></i>
		Configuración HTML
	</button>
	<button type="button" id="btnIconJs">
		Configuración JS
	</button>
</div>

<h4>Responsive Web Design</h4>

<p>En el caso de un sitio web que implemente RWD es posible especificar al componente que en unas determinadas resoluciones solo muestre el icono. De este modo se aprovechará mejor el espacio disponible de la pantalla mejorando la usabilidad.</p>
<p>Para ello se hará uso de las clases de Bootstrap definidas para ello como son <code>hidden-sm-down</code>, <code>hidden-sm-up</code>, <code>hidden-md-down</code>...</p>
<p>Las clases pueden agregarse al label mediante la propiedad <code>labelCss</code> en el caso de configurarse mediante propiedades Js.</p>

<div class="example">
	<button type="button" id="btnRwdHtmlSm" class="">
		<i class="fa fa-cog" aria-hidden="true"></i>
		<span class="rup-ui-button-text hidden-sm-down">Rwd Html Sm</span>
	</button>
	<button type="button" id="btnRwdHtmlMd" class="">
		<i class="fa fa-cog" aria-hidden="true"></i>
		<span class="rup-ui-button-text hidden-md-down">Rwd Html Md</span>
	</button>
	<button type="button" id="btnRwdJsSm" class="">Rwd Js Sm</button>
	<button type="button" id="btnRwdJsMd" class="">Rwd Js Md</button>
</div>

<h4>MButtons</h4>

<p>Entre los componentes RUP existen los MButtons.</p>

<p>Este tipo de botón consiste en una agrupación de botones que se muestran al accionar un botón que sirve como desencadenante. </p>

<div class="example">
	<div class="rup-mbutton">
		<button type="button" id="btnMButton" data-mbutton="true">
			<i class="fa fa-cog" aria-hidden="true"></i>
			<span class="rup-ui-button-text hidden-md-down">MButton</span>
		</button>
		<ul id="mbuttonContainer" class="rup-mbutton-container" aria-labelledby="btnMButton">
			<li>
				<button type="button" id="mbutton-buttonNew">
					<i class="fa fa-cog" aria-hidden="true"></i>
					<span class="rup-ui-button-text hidden-md-down">Nuevo</span>
				</button>
			</li>
			<li>
				<button type="button" id="mbutton-buttonEdit">
					<i class="fa fa-cog" aria-hidden="true"></i>
					<span class="rup-ui-button-text hidden-md-down">Editar</span>
				</button>
			</li>
			<li>
				<button type="button" id="mbutton-buttonCancel">
					<i class="fa fa-cog" aria-hidden="true"></i>
					<span class="rup-ui-button-text hidden-md-down">Cancelar</span>
				</button>
			</li>
		</ul>
	</div>
</div>

<h4>RWD MButtons</h4>

<div class="example">
	<div class="rup-mbutton rup-collapsed-md">
		<a type="button" id="btnMButton" data-mbutton="true">
			<i class="fa fa-cog" aria-hidden="true"></i>
			<span class="rup-ui-button-text hidden-xs-down">MButton</span>
		</a>
		<ul id="mbuttonContainer" class="rup-mbutton-container" aria-labelledby="btnMButton">
			<li>
				<button type="button" id="mbutton-buttonNew">
					<i class="fa fa-cog" aria-hidden="true"></i>
					<span class="rup-ui-button-text">Nuevo</span>
				</button>
			</li>
			<li>
				<button type="button" id="mbutton-buttonEdit">
					<i class="fa fa-cog" aria-hidden="true"></i>
					<span class="rup-ui-button-text">Editar</span>
				</button>
			</li>
			<li>
				<button type="button" id="mbutton-buttonCancel">
					<i class="fa fa-cog" aria-hidden="true"></i>
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
		<li><a href="#" id="dropdownElem1">Elemento 1</a></li>
		<li><a href="#" id="dropdownElem2">Elemento 2</a></li>
		<li><a href="#" id="dropdownElem3">Elemento 3</a></li>
		<li class="divider"></li>
		<li><a href="#" id="dropdownElem4">Elemento 4</a></li>
	</ul>


	<button type="button" id="dropdownDialogButton">Dialogo</button>
	<div id="dropdownDialog" style="width:380px;">
		<form>
			<fieldset class="dropdownButton-inputs">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="dropdownButton-combo">Filtros</label>
						<input id="dropdownButton-combo" />
					</div>
					<div class="formulario_linea_izda_float">
						<input type="checkbox" id="dropdownButton-defaultFilter"/>
						<label for="dropdownButton-defaultFilter">Filtro por defecto</label>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<p>Botón FAB (Floating Action Button)</p>
<div class="example">
	<button type="button" id="fabButton" data-fab="true"><span class="glyphicon glyphicon-cog" /></button>
	<button type="button" id="fabButtonLayer" data-fab="true"><span class="glyphicon glyphicon-cog" /></button>

	<button type="button" id="fabButtonFixed" data-fab="true" data-fixed="true" data-list="fabButtonFixedList"><span class="glyphicon glyphicon-cog" /></button>
	<ul id="fabButtonFixedList">
		<li><button type="button" id="fabButtonList1" data-fab="true" ><span class="glyphicon glyphicon-plus" /></button></li>
		<li><button type="button" id="fabButtonList2" data-fab="true" ><span class="glyphicon glyphicon-pencil" /></button></li>
		<li><button type="button" id="fabButtonList3" data-fab="true" ><span class="glyphicon glyphicon-trash" /></button></li>
	</ul>

</div>

<h4>Eventos</h4>

<p>El modo recomedado para asociar una función de callback a un evento <code>click</code> es hacer uso de la función <code>.on("click", funcion(){})</code> de jQuery.</p>
<p>Sin embargo, para mantener la compatibilidad con la configuración Js del componente Tollbar, se permite especificar una función de callback en la propiedad <code>click</code>.</p>
<div class="example">
	<button type="button" id="btnClickJQuery" class="">
		<i class="fa fa-cog" aria-hidden="true"></i>
		<span class="rup-ui-button-text hidden-md-down">Handler jQuery</span>
	</button>
	<button type="button" id="btnClickRup" class="">Handler RUP</button>
</div>

<h4>i18n</h4>

<p>Del mismo modo, para manener la compatibilidad con el componente Toolbar, es posible especificar el valor del label del botón mediante el atributo <code>i18nCaption</code></p>
<div class="example">
	<button type="button" id="btnI18n" class="">Handler RUP</button>
</div>

