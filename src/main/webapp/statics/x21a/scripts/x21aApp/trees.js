/*!
 * Copyright 2013 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
$(function () {

    var codeDialogSelector = $('#treeCodeDialog');

    // Copia fuente del código del árbol utilizado
    $('#defaultDemoCodeHtml code').append('&lt;div id="defaultDemo" class="rup_tree"&gt;<br/>');
    $('#defaultDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#changedThemeDemoCodeHtml code').append('&lt;div id="changedThemeDemo" class="rup_tree"&gt;<br/>');
    $('#changedThemeDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#changeViewDemoCodeHtml code').append('&lt;div id="changeViewDemo" class="rup_tree"&gt;<br/>');
    $('#changeViewDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#jqueryuiStylesDemoCodeHtml code').append('&lt;div id="jqueryuiStylesDemo" class="rup_tree"&gt;<br/>');
    $('#jqueryuiStylesDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#selectDemoCodeHtml code').append('&lt;div id="selectDemo" class="rup_tree"&gt;<br/>');
    $('#selectDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#hotkeysDemoCodeHtml code').append('&lt;div id="hotkeysDemo" class="rup_tree"&gt;<br/>');
    $('#hotkeysDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#sortDemoCodeHtml code').append('&lt;div id="sortDemo" class="rup_tree"&gt;<br/>');
    $('#sortDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#checkboxDemoCodeHtml code').append('&lt;div id="checkboxDemo" class="rup_tree"&gt;<br/>');
    $('#checkboxDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#contextmenuDemoCodeHtml code').append('&lt;div id="contextmenuDemo" class="rup_tree"&gt;<br/>');
    $('#contextmenuDemoCodeHtml code').append($($('#model_example_1_code').html()).clone());

    $('#uniqueDemoCodeHtml code').append('&lt;div id="uniqueDemo" class="rup_tree"&gt;<br/>');
    $('#uniqueDemoCodeHtml code').append($($('#model_example_2_code').html()).clone());

    $('#jsonDemoCodeHtml code').append('&lt;div id="jsonTreeDemo" class="rup_tree"&gt;<br/>');

    $('#jsonAjaxDemoCodeHtml code').append('&lt;div id="ajaxTreeDemo" class="rup_tree"&gt;<br/>');

    function codeCleanLoad(id) {
        codeDialogSelector.find('#htmlCode').children().remove();
        codeDialogSelector.find('#javaScriptCode').children().remove();
        codeDialogSelector.find('#htmlCode').append($('#' + id + 'JS').clone());
        codeDialogSelector.find('#javaScriptCode').append($('#' + id + 'Html').clone());
    }

    // Dialogo de visualización de código
    codeDialogSelector.rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: true,
        width: 'auto',
        title: $.rup.i18nParse($.rup.i18n.app.treeCodeDialog, 'title'),
        buttons: [{
            text: $.rup.i18nParse($.rup.i18n.base, 'rup_global.close'),
            click: function () {
                $(this).dialog('close');
            }
        }]
    });

    // Ejemplo de un árbol por defecto
    // Copia del código del árbol utilizado
    $('#defaultDemo').html($($('#model_example_1').html()).clone());

    $('#defaultDemo').rup_tree();

    $('#btnDefaultDemo').bind('click', function () {
        codeCleanLoad('defaultDemoCode');
        codeDialogSelector.rup_dialog('open');
    });


    // Ejemplo de un árbol con estilo especificado por la aplicación
    // Copia del código del árbol utilizado
    $('#changedThemeDemo').html($($('#model_example_1').html()).clone());

    $('#changedThemeDemo').rup_tree({
    	'core': {
    		'themes': {
    			'name': 'default-dark'
    		}
    	}
    });

    $('#btnChangedThemeDemo').bind('click', function () {
        codeCleanLoad('changedThemeDemoCode');
        codeDialogSelector.rup_dialog('open');
    });


    // Ejemplo de un árbol que cambie su aspecto visual
    // Copia del código del árbol utilizado
    $('#changeViewDemo').prepend($($('#model_example_1').html()).clone());

    $('#changeViewDemo').rup_tree({
    	'core': {
    		'themes': {
    			'dots': true,
    			'icons': false
    		}
    	}
    });

    $('#btnChangeViewDemo').bind('click', function () {
        codeCleanLoad('changeViewDemoCode');
        codeDialogSelector.rup_dialog('open');
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

    // Ejemplo de un árbol con cambios en el modo de selección
    // Copia del código del árbol utilizado
    $('#selectDemo').prepend($($('#model_example_1').html()).clone());

    $('#selectDemo').rup_tree({
    	'plugins': ['conditionalselect', 'state'],
    	'core': {
    		'multiple': true
    	},
    	'conditionalselect': function () {
    		// limit selection to 2 nodes
    		return this.get_selected().length < 2;
    	}
    });
    
    $('#selectDemo').on('ready.jstree', function () {
    	$('#selectDemo').rup_tree('selectNode', ['node122', 'node1213'], true, false);
	});

    $('#btnSelectDemo').bind('click', function () {
        codeCleanLoad('selectDemoCode');
        codeDialogSelector.rup_dialog('open');
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

    // Ejemplo de ordenacion de nodos de un árbol
    // Copia del código del árbol utilizado
    $('#sortDemo').html($($('#model_example_1').html()).clone());

    $('#sortDemo').rup_tree({
    	'plugins': ['sort']
    });
    
    $('#sortDemo').on('ready.jstree', function () {
    	$('#sortDemo').rup_tree('openNode', ['node121', 'node112']);
	});

    $('#renameButton').click(function () {
        if ($('#sortDemo').rup_tree('getSelected').length > 0) {
            $('#sortDemo').rup_tree('renameNode', 'node111', 'prueba');
        }
    });

    $('#btnSortDemo').bind('click', function () {
        codeCleanLoad('sortDemoCode');
        codeDialogSelector.rup_dialog('open');
    });

    // Ejemplo de árbol con checkbox
    // Copia del código del árbol utilizado
    $('#checkboxDemo').html($($('#model_example_1').html()).clone());

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

    $('#btnCheckboxDemo').bind('click', function () {
        codeCleanLoad('checkboxDemoCode');
        codeDialogSelector.rup_dialog('open');
    });

    // Ejemplo de árbol con contextmenu
    // Copia del código del árbol utilizado
    $('#contextmenuDemo').html($($('#model_example_1').html()).clone());

    $('#contextmenuDemo').rup_tree({
        'plugins': ['sort', 'contextmenu']
    });
    
    $('#contextmenuDemo').on('ready.jstree', function () {
    	$('#contextmenuDemo').rup_tree('openNode', ['node121', 'node112', 'node113']);
	});

    $('#btnContextmenuDemo').bind('click', function () {
        codeCleanLoad('contextmenuDemoCode');
        codeDialogSelector.rup_dialog('open');
    });

    // Ejemplo de nodos, en el mismo subgrupo, que tengan diferente nombre (no puede haber dos iguales)
    // Copia del código del árbol utilizado
    $('#uniqueDemo').html($($('#model_example_2').html()).clone());

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

    $('#btnUniqueDemo').bind('click', function () {
        codeCleanLoad('uniqueDemoCode');
        codeDialogSelector.rup_dialog('open');
    });

    // Ejemplo de carga de datos mediante JSON
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

    $('#btnJsonTreeDemo').bind('click', function () {
        codeCleanLoad('jsonDemoCode');
        codeDialogSelector.rup_dialog('open');
    });

    // Ejemplo de carga de datos mediante AJAX
    $('#ajaxTreeDemo').rup_tree({
        'core': {
            'data': {
                'url': 'ajaxTree'
            }
        }
    });

    $('#codeAjaxTreeControl').bind('click', function () {
        codeCleanLoad('jsonAjaxDemoCode');
        codeDialogSelector.rup_dialog('open');
    });

    $('.contenedor').addClass('show');
});