/*!
 * Copyright 2019 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, multipk_aplicar").click(function(){
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
jQuery(function ($) {

    var tableColModels = [
    	{
	        name: 'ida',
	        index: 'ida',
	        editable: true,
	        hidden: false
	    },
    	{
	        name: 'idb',
	        index: 'idb',
	        editable: true,
	        hidden: false
	    },
    	{
	        name: 'nombre',
	        index: 'nombre',
	        editable: true,
	        hidden: false
	    },
	    {
	        name: 'apellido1',
	        index: 'apellido1',
	        editable: true,
	        hidden: false
	    },
	    {
	        name: 'apellido2',
	        index: 'apellido2',
	        editable: true,
	        hidden: false
	    }
    ];

    var listaPlugins = 'editForm,colReorder,selection,seeker,buttons,';

    var allowedPluginsBySelecionType = {
        multiSelection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'multiSelection', 'inlineEdit'],
        selection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'selection', 'inlineEdit'],
        noSelection: ['colReorder', 'seeker', 'groups', 'noSelection']
    };

    function loadTable() {
        $('#MultiPk').rup_table(loadPlugins());
    }

    function loadPlugins() {

        if (localStorage.plugins === undefined) { //si esta undefined es que es la primera vez.
            localStorage.plugins = listaPlugins;
        }

        var plugins = {};
            
		plugins.enableDynamicForms = true;

        plugins.loadOnStartUp = true;

        var fixedHeader = {
            footer: false,
            header: true
        };
        plugins.fixedHeader = fixedHeader;

        var filter = {
            id: 'MultiPk_filter_form',
            filterToolbar: 'MultiPk_filter_toolbar',
            collapsableLayerId: 'MultiPk_filter_fieldset'
        };
        plugins.filter = filter;

        if (localStorage.plugins.indexOf('multiSelection') > -1) {
            var multiSelect = {
                style: 'multi'
            };
            plugins.multiSelect = multiSelect;
            $('#noSelection').prop('checked', false);
            $('#selection').prop('checked', false);
            $('#multiSelection').prop('checked', true);

        } else {
            $('#multiSelection').prop('checked', false);
        }

        if (localStorage.plugins.indexOf('selection') > -1) {
            var select = {
                activate: true
            };
            plugins.select = select;
            $('#noSelection').prop('checked', false);
            $('#selection').prop('checked', true);
        } else {
            $('#selection').prop('checked', false);
        }

        if (localStorage.plugins.indexOf('noSelection') > -1) {
            $('#noSelection').prop('checked', true);
        } else {
            $('#noSelection').prop('checked', false);
        }

        var formEdit;

        if (localStorage.plugins.indexOf('editForm') > -1) {
            formEdit = {
                detailForm: '#MultiPk_detail_div',
                fillDataMethod: 'clientSide',
                validate: {
                    rules: {
                        'nombre': {
                            required: false
                        },
                        'apellido1': {
                            required: false
                        },
                        'apellido2': {
                            required: false
                        }
                    }
                }
            };
            plugins.formEdit = formEdit;

            $('#editForm').prop('checked', true);
        } else {
            $('#editForm').prop('checked', false);
        }

        if (localStorage.plugins.indexOf('inlineEdit') > -1) {
            var inlineEdit = {
                deselect: true,
                validate: {
                    rules: {
                        'nombre': {
                            required: true
                        },
                        'apellido1': {
                            required: true
                        },
                        'apellido2': {
                            required: true
                        }
                    }
                }
            };
            plugins.inlineEdit = inlineEdit;

            $('#inlineEdit').prop('checked', true);
        } else {
            $('#inlineEdit').prop('checked', false);
        }

        if (localStorage.plugins.indexOf('buttons') > -1) {
            var buttons = {
                activate: true
            };
            plugins.buttons = buttons;
            $('#buttons').prop('checked', true);

        } else {
            $('#buttons').prop('checked', false);
        }

        if (localStorage.plugins.indexOf('seeker') > -1) {
            var seeker = {
                activate: true
            };
            plugins.seeker = seeker;
            $('#seeker').prop('checked', true);
        } else {
            $('#seeker').prop('checked', false);
        }


        if (localStorage.plugins.indexOf('colReorder') > -1) {
            var colReorder = {
                fixedColumnsLeft: 1
            };
            plugins.colReorder = colReorder;
            $('#colReorder').prop('checked', true);
        } else {
            $('#colReorder').prop('checked', false);
        }

        if (localStorage.plugins.indexOf('groups') > -1) {
            var rowGroup = {
                endRender: false,
                startRender: function (rows, group) {

                    return $('<tr/>')
                        .append('<td colspan="8"><b>' + group + ' - ' + rows[0].length + ' Elemento(s) </b></td>');
                },
                dataSrc: 'nombre'
            };
            plugins.rowGroup = rowGroup;
            $('#groups').prop('checked', true);
        } else {
            $('#groups').prop('checked', false);
        }

        //Col model es obligatorio,se mete como generico
        plugins.colModel = tableColModels;

        localStorage.clear();
        return plugins;
    }

    $('#multipk_aplicar').click(function () {
        if (localStorage.plugins === undefined) {
            localStorage.plugins = '';
        }

        var selectionType = $('#multipk_tableConfiguration input[name = "selectionType"]:checked')[0].id;

        $.each($('#multipk_tableConfiguration .pluginsControl input'), function () {
            if ($('#' + this.id).prop('checked') && allowedPluginsBySelecionType[selectionType].indexOf(this.id) > -1) {
                localStorage.plugins = localStorage.plugins + this.id + ',';
            }
        });

        location.reload();
    });
    
    $('#MultiPk').on('tableInlineEdit', function() {
		const $rowSelect = $('#MultiPk > tbody > tr.selected.tr-highlight.editable:not(.group)');
		$rowSelect.find("input[id^=id]").prop('disabled', 'disabled');
	});

    loadTable();

    $('.contenedor').addClass('show');
});