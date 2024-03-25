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

<div id="treeCode" class="treeCode">

	<div id="defaultDemoCode" class="treeCode">
		<fieldset id="defaultDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
/* Ejemplo de un árbol por defecto */<br/>
$("#defaultDemo").rup_tree();
				</code>
			</pre>
		</fieldset>
		<fieldset id="defaultDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id=changedThemeDemoCode class="treeCode">
		<fieldset id="changedThemeDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#changedThemeDemo').rup_tree({
	'core': {
		'themes': {
			'name': 'default-dark'
		}
	}
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="changedThemeDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="specificStyleDemoCode" class="treeCode">
		<fieldset id="specificStyleDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#changedThemeDemo').rup_tree({
	'core': {
		'themes': {
			'name': 'default-dark'
		}
	}
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="specificStyleDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="changeViewDemoCode" class="treeCode">
		<fieldset id="changeViewDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#changeViewDemo').rup_tree({
	'core': {
		'themes': {
			'dots': true,
			'icons': false
		}
	}
});

$('#toggle_dots').click(function () {
    $('#changeViewDemo').rup_tree('toggleDots');
});

$('#toggle_icons').click(function () {
    $('#changeViewDemo').rup_tree('toggleIcons');
});

$('#changeTheme').click(function () {
    const type = $('#changeViewDemo').rup_tree('getTheme');
    if (type === 'default') {
        $('#changeViewDemo').rup_tree('setTheme', 'default-dark', false);
    } else {
        $('#changeViewDemo').rup_tree('setTheme', 'default', false);
    }
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="changeViewDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="selectDemoCode" class="treeCode">
		<fieldset id="selectDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#selectDemo').rup_tree({
	'plugins': ['conditionalselect', 'state'],
	'core': {
		'multiple': true
	},
	'conditionalselect': function () {
		// limit selection to 2 nodes
		return this.get_selected().length &lt; 2;
	}
});

$('#selectDemo').on('ready.jstree', function () {
    $('#selectDemo').rup_tree('selectNode', ['node122', 'node1213'], true, false);
});

$('#saveSelection').click(function () {
    $('#selectDemo').rup_tree('saveState');
});

$('#loadSelection').click(function () {
    $('#selectDemo').rup_tree('restoreState');
});

$('#deselectAll').click(function () {
    $('#selectDemo').rup_tree('deselectAll');
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="selectDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="sortDemoCode" class="treeCode">
		<fieldset id="sortDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#sortDemo').rup_tree({
	'plugins': ['sort']
});

    $('#sortDemo').on('ready.jstree', function () {
    $('#sortDemo').rup_tree('openNode', ['node121', 'node112']);
});

$('#renameButton').click(function () {
    if ($('#sortDemo').rup_tree('getSelected').length &gt; 0) {
        $('#sortDemo').rup_tree('renameNode', 'node111', 'prueba');
    }
});	
				</code>
			</pre>
		</fieldset>
		<fieldset id="sortDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="checkboxDemoCode" class="treeCode">
		<fieldset id="checkboxDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#checkboxDemo').rup_tree({
	'plugins': ['checkbox']
});

$('#checkboxDemo').on('ready.jstree', function () {
    $('#checkboxDemo').rup_tree('openNode', ['node121', 'node112', 'node113']);
});


var checkboxState = true;

$('#checkboxShowHide').click(function () {
    if (checkboxState) {
        checkboxState = false;
        $('#checkboxDemo').rup_tree('hideCheckboxes');
    } else {
        checkboxState = true;
        $('#checkboxDemo').rup_tree('showCheckboxes');
    }
});

$('#checkboxCheckAll').click(function () {
    $('#checkboxDemo').rup_tree('checkAll');
});

$('#checkboxUncheckAll').click(function () {
    $('#checkboxDemo').rup_tree('uncheckAll');
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="checkboxDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="contextmenuDemoCode" class="treeCode">
		<fieldset id="contextmenuDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#contextmenuDemo').rup_tree({
    'plugins': ['sort', 'contextmenu']
});

$('#contextmenuDemo').on('ready.jstree', function () {
    $('#contextmenuDemo').rup_tree('openNode', ['node121', 'node112', 'node113']);
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="contextmenuDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="uniqueDemoCode" class="treeCode">
		<fieldset id="uniqueDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#uniqueDemo').rup_tree({
    'plugins': ['unique', 'contextmenu'],
    'duplicate': function (name, counter) {
        $.rup_messages('msgError', {
            title: $.rup.i18nParse($.rup.i18n.app.uniqueDemo, 'duplicateKey'),
            message: $.rup.i18nParse($.rup.i18n.app.uniqueDemo, 'duplicateNodes') + '"' + name + '"' + $.rup.i18nParse($.rup.i18n.app.uniqueDemo, 'inFunction') + '"' + counter + '"!'
        });
    }
});

$('#contextmenuDemo').on('ready.jstree', function () {
	$('#contextmenuDemo').rup_tree('openNode', ['nodeC', 'nodeAC']);
});

$('#uniqueRenameButton').click(function () {
	const selected = $('#uniqueDemo').rup_tree('getSelected');
	if (selected.length > 0) {
		$('#uniqueDemo').rup_tree('renameNode', selected, 'D');
	}
});

$('#uniqueNewButton').click(function () {
	$('#uniqueDemo').rup_tree('createNode', '#', {'text': 'Z'}, 'last', function () { console.log("Nodo creado");}, true, 'nodeZ');
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="uniqueDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="jsonDemoCode" class="treeCode">
		<fieldset id="jsonDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#jsonTreeDemo').rup_tree({
	'core': {
		'data': [{
			'text': 'Padre 1',
				'children': [{
					'text': 'Padre 1.1',
					'children': [{
							'text': 'Hoja 1.1.1'
						},
						{
							'text': 'Padre 1.1.2',
							'children': ['Hoja 1.1.2.1', 'Hoja 1.1.2.2']
						}
					]
				},
				{
					'text': 'Hoja 1.2'
				}
			]
		}]
	}
});
   
$('#jsonTreeDemo').on('ready.jstree', function () {
	$('#jsonTreeDemo').rup_tree('openNode', 'Padre 1');
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="jsonDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
	
	<div id="jsonAjaxDemoCode" class="treeCode">
		<fieldset id="jsonAjaxDemoCodeJS" class="codePanelObject codePanelObjectLeft">
			<legend><spring:message code="tree.CodigoJS" /></legend>
			<pre>
				<code>
$('#ajaxTreeDemo').rup_tree({
	'core': {
		'data': {
			'url': 'ajaxTree'
		}
	}
});
				</code>
			</pre>
		</fieldset>
		<fieldset id="jsonAjaxDemoCodeHtml" class="codePanelObject codePanelObjectRight">
			<legend><spring:message code="tree.CodigoHtml" /></legend>
			<pre><code></code></pre>
		</fieldset>
	</div>
</div>