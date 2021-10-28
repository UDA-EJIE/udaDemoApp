/*!
 * Copyright 2021 E.J.I.E., S.A.
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
jQuery(function ($) {
	// Para evitar crear una JSP nueva, se elimina la parte que no nos interesa
	$('#example_tableConfiguration').remove();
	
	window.initRupI18nPromise.then(function () {
        var tableColModels = [
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
            	name: "apellido2", 
            	index: "apellido2", 
            	editable: true, 
            	hidden: false,
            	rupType: 'autocomplete',
                editoptions: {
                	source : '../table/apellidos',
                    sourceParam : {label: 'label', value: 'value'},
                    menuMaxHeight: 200,
                    minLength: 3,
                    combobox: true,
                    contains: true,
                    showDefault: true
                }
            },
            {
                name: 'ejie',
                index: 'ejie',
                editable: true,
                hidden: false,
                edittype: 'checkbox',
                formatter: 'checkbox',
                align: 'center',
                editoptions: {
                    value: '1:0'
                }
            },
            {
                name: 'fechaAlta',
                index: 'fechaAlta',
                editable: true,
                hidden: false,
                rupType: 'date',
                editoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                }
            },
            {
                name: 'fechaBaja',
                index: 'fechaBaja',
                editable: false,
                hidden: false,
                rupType: 'date',
                editoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                }
            },
            {
                name: 'rol',
                index: 'rol',
                editable: true,
                hidden: false,
                rupType: 'combo',
                editoptions: {
                    source : '../table/roles',
                    sourceParam : {label: 'label', value: 'value'},
                    blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                }
            }
        ];

        //Formulario de filtrado
        jQuery('#fechaAlta_filter_table').rup_date();
        jQuery('#fechaBaja_filter_table').rup_date();
        
        $('#example').rup_table({
        	urlBase: '../table',
	        filter: {
	        	id: 'example_filter_form',
            	filterToolbar: 'example_filter_toolbar',
            	collapsableLayerId: 'example_filter_fieldset'
	        },
	        colModel: tableColModels,
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        seeker: {
	            activate: true
	        },
	        buttons: {
	            activate: true
	        },
	        multiSelect: {
	            style: 'multi'
	        },
	        formEdit: {
	        	detailForm: '#example_detail_div',
                loadSpinner: true,
                data: {
                	'fixedMessage': 'Este mensaje fijado demuestra la posibilidad del envío de parámetros desde editForm :)'
                },
                validate: {
                    rules: {
                        'nombre': {
                            required: true
                        },
                        'apellido1': {
                            required: true
                        },
                        'fechaAlta': {
                            required: true,
                            date: true
                        },
                        'fechaBaja': {
                            date: true
                        }
                    }
                },
                customTitle: jQuery.rup.i18nParse(jQuery.rup.i18n.app, 'table.sampleTitle'),
                cancelDeleteFunction: function () {
                    console.log('Ha cancelado eliminar.');
                }
	        }
	    });
	});
});