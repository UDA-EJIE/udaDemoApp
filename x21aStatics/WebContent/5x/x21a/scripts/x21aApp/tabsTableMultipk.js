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
	$('#multipk_tableConfiguration').remove();
	
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
		        name: 'apellido2',
		        index: 'apellido2',
		        editable: true,
		        hidden: false
		    }
	    ];
        
        $('#MultiPk').rup_table({
        	urlBase: '../table/multipk',
	        filter: {
	        	id: 'MultiPk_filter_form',
            	filterToolbar: 'MultiPk_filter_toolbar',
            	collapsableLayerId: 'MultiPk_filter_fieldset'
	        },
	        colModel: tableColModels,
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        fixedHeader: {
	        	footer: false,
	        	header: true
	        },
	        seeker: {
	            activate: true
	        },
	        buttons: {
	            activate: true
	        },
	        select: {
	        	activate: true
	        },
	        formEdit: {
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
	        }
	    });
	});
});