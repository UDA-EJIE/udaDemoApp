<%--  
 -- Copyright 2013 E.J.I.E., S.A.
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

<%@include file="/WEB-INF/includeTemplate.inc"%>

<code id="model_example_1_code" class="model_example_1_code">
	<!-- Codigo fuente  -->
	&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.Hijo1"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.Hijo2"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.Hijo3"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.Hijo3"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.Hijo2"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.Hijo3"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a&gt;&lt;spring:message code="treeExample.hoja"/&gt;&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&lt;/div&gt;
	<!-- Fin codigo fuente  -->
</code>

<code id="model_example_2_code" class="model_example_2_code">
	<!-- Codigo fuente  -->
	&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeA"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;A&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeAA"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;AA&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeAB"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;AB&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeAC"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;AC&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeACA"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;ACA&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeACB"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;ACB&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeB"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;B&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeC"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;C&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeCA"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;CA&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;li id="nodeCB"&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;a href="#"&gt;CB&lt;/a&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/li&gt;<br/>
	&nbsp;&nbsp;&nbsp;&lt;/ul&gt;<br/>
	&lt;/div&gt;<br/>
	<!-- Fin codigo fuente  -->
</code>

<div id="treeCode" class="treeCode">

	<div id="defaultDemoCode" class="treeCode">
		<fieldset id="defaultDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de un árbol por defecto */<br/>
				$("#defaultDemo").rup_tree();
			</code>
		</fieldset>
		<fieldset id="defaultDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="specificStyleDemoCode" class="treeCode">
		<fieldset id="specificStyleDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de un árbol con estilo especificado por la aplicación */<br/>
				$("#specificStyleDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"themes" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"theme" : "default",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"url" : "/x21aStatics/x21a/styles/tree/themes/default/style.css"<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
			</code>
		</fieldset>
		<fieldset id="specificStyleDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="changeViewDemoCode" class="treeCode">
		<fieldset id="changeViewDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de un árbol que cambie su aspecto visual */<br/>
				$("#changeViewDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"themes" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"dots" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icons" : false<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>
				$("#toggle_dots").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#changeViewDemo").rup_tree("toggle_dots");<br/>
				});<br/>
				
				$("#toggle_icons").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#changeViewDemo").rup_tree("toggle_icons");<br/>
				});<br/>
				
				$("#changeTheme").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;var type = $("#changeViewDemo").rup_tree("get_theme");<br/>
				&nbsp;&nbsp;&nbsp;if(type === "rup-default"){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#changeViewDemo").rup_tree("set_theme","apple", "/x21aStatics/x21a/styles/tree/themes/apple/style.css");<br/>
				&nbsp;&nbsp;&nbsp;} else if (type === "apple"){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#changeViewDemo").rup_tree("set_theme","default", "/x21aStatics/x21a/styles/tree/themes/default/style.css");<br/>
				&nbsp;&nbsp;&nbsp;} else if (type === "default"){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#changeViewDemo").rup_tree("set_theme","classic", "/x21aStatics/x21a/styles/tree/themes/classic/style.css");<br/>			
				&nbsp;&nbsp;&nbsp;} else {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#changeViewDemo").rup_tree("set_theme","rup-default", false);<br/>
				&nbsp;&nbsp;&nbsp;}<br/>				
				});<br/>
			</code>
		</fieldset>
		<fieldset id="changeViewDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="jqueryuiStylesDemoCode" class="treeCode">
		<fieldset id="jqueryuiStylesDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de árbol con estilos de jQueryUI */<br/>
				$("#jqueryuiStylesDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"core" : { "initially_open" : [ "node121","node112","node113" ] }<br/>
				&nbsp;&nbsp;&nbsp;,"jQueryUIStyles" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
			</code>
		</fieldset>
		<fieldset id="jqueryuiStylesDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="selectDemoCode" class="treeCode">
		<fieldset id="selectDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de un árbol con cambios en el modo de selección */<br/>
				$("#selectDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"select" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"select_limit" : "2",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"initially_select" : ["node122","node1213"]<br/>
				&nbsp;&nbsp;&nbsp;}<br/> 
				});<br/>
				<br/>
				$("#saveSelection").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#selectDemo").rup_tree("save_selected");<br/>
				});<br/>
				<br/>
				$("#loadSelection").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#selectDemo").rup_tree("deselect_all");<br/>				
				&nbsp;&nbsp;&nbsp;$("#selectDemo").rup_tree("reselect");<br/>
				});<br/>
				<br/>
				$("#deselectAll").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#selectDemo").rup_tree("deselect_all");<br/>				
				});<br/>
			</code>
		</fieldset>
		<fieldset id="selectDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="hotkeysDemoCode" class="treeCode">
		<fieldset id="hotkeysDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo del manejo de un árbol con el teclado (por defecto activo) */<br/>
				$("#hotkeysDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"core" : { "initially_open" : [ "node121" ] }<br/>
				});<br/>
				<br/>
				$("#enableHotkeys").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#hotkeysDemo").rup_tree("enable_hotkeys");<br/>
				});<br/>
				<br/>
				$("#disableHotkeys").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#hotkeysDemo").rup_tree("disable_hotkeys");<br/>
				});<br/>
			</code>
		</fieldset>
		<fieldset id="hotkeysDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="sortDemoCode" class="treeCode">
		<fieldset id="sortDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de ordenacion de nodos de un árbol */<br/>
				$("#sortDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"core" : { "initially_open" : [ "node121","node112" ] },<br/>
				&nbsp;&nbsp;&nbsp;"sort" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>
				$("#renameButton").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;if ($("#sortDemo").rup_tree("get_selected").size() > 0){<br/>
				&nbsp;&nbsp;&nbsp;$("#sortDemo").rup_tree("rename");<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>	
			</code>
		</fieldset>
		<fieldset id="sortDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="checkboxDemoCode" class="treeCode">
		<fieldset id="checkboxDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de árbol con checkbox */<br/>
				$("#checkboxDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"core" : { "initially_open" : [ "node121","node112","node113" ] }<br/>
				&nbsp;&nbsp;&nbsp;,"checkbox" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>
				var checkboxState = true;<br/>
				<br/>
				$("#checkboxShowHide").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;if(checkboxState){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;checkboxState = false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#checkboxDemo").rup_tree("hide_checkboxes");<br/>
				&nbsp;&nbsp;&nbsp;} else {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;checkboxState = true;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#checkboxDemo").rup_tree("show_checkboxes");<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>
				$("#checkboxCheckAll").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#checkboxDemo").rup_tree("check_all");<br/>
				});<br/>
				<br/>
				$("#checkboxUncheckAll").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;$("#checkboxDemo").rup_tree("uncheck_all");<br/>
				});<br/>
			</code>
		</fieldset>
		<fieldset id="checkboxDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="contextmenuDemoCode" class="treeCode">
		<fieldset id="contextmenuDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de árbol con contextmenu */<br/>		
				$("#contextmenuDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"core" : { "initially_open" : [ "node121","node112","node113" ]},<br/>
				&nbsp;&nbsp;&nbsp;"contextmenu" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"defaultOptions" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"create" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"remove" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"rename" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"ccp" : false<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"ccp" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"cut" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"paste" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"copy" : false<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"items" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Some key<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"exampleItem" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// The item label<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"label"             : $.rup.i18nParse($.rup.i18n.app["contextmenuDemo"],"exampleEntry"),<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// The function to execute upon a click<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"action"            : function (obj) { $("#contextmenuDemo").rup_tree("rename",obj); },<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// All below are optional<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"_disabled"         : false,     // clicking the item won't do a thing<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"_class"            : "class",  // class is applied to the item LI node<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"separator_before"  : false,    // Insert a separator before the item<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"separator_after"   : false,     // Insert a separator after the item<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// false or string - if does not contain `/` - used as classname<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon"              : false//,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//"submenu"           : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/* Collection of objects (the same structure) */<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"rename" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// The item label<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"label"             : $.rup.i18nParse($.rup.i18n.app["contextmenuDemo"],"udaRename"),<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// The function to execute upon a click<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"action"            : function (obj) { $("#contextmenuDemo").rup_tree("rename",obj); },<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// All below are optional<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"_disabled"         : false,     // clicking the item won't do a thing<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"_class"            : "class",  // class is applied to the item LI node<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"separator_before"  : false,    // Insert a separator before the item<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"separator_after"   : false,     // Insert a separator after the item<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// false or string - if does not contain `/` - used as classname<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon"              : false<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"sort" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
			</code>
		</fieldset>
		<fieldset id="contextmenuDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
	<div id="uniqueDemoCode" class="treeCode">
		<fieldset id="uniqueDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<code>
				/* Ejemplo de nodos, en el mismo subgrupo, que tengan diferente nombre (no puede haber dos iguales) */<br/>
				$("#uniqueDemo").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"core" : { "initially_open" : ["nodeC","nodeAC"] },<br/>
				&nbsp;&nbsp;&nbsp;"contextmenu" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"unique" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"error_callback" : function (n, p, f) {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$.rup_messages("msgError", {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title: $.rup.i18nParse($.rup.i18n.app["uniqueDemo"],"duplicateKey"),<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;message: $.rup.i18nParse($.rup.i18n.app["uniqueDemo"],"duplicateNodes")+<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"\""+ n +"\""+ $.rup.i18nParse($.rup.i18n.app["uniqueDemo"],"inFunction") +"\""+ f + "\"!"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>
				$("#uniqueRenameButton").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;if ($("#uniqueDemo").rup_tree("get_selected").size() > 0){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#uniqueDemo").rup_tree("rename");<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>
				$("#uniqueNewButton").click(function () {<br/>
				&nbsp;&nbsp;&nbsp;if ($("#uniqueDemo").rup_tree("get_selected").size() > 0){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("#uniqueDemo").rup_tree("create");<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
			</code>
		</fieldset>
		<fieldset id="uniqueDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<code></code>
		</fieldset>
	</div>
	
</div>