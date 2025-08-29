/*!
 * Copyright 2019 E.J.I.E., S.A.
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
	 window.initRupI18nPromise.then(function () {
	    var tableColModelsComarca = [
	    	{
		        name: 'descEs',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'descEu',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'css',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'provincia.code',
		        editable: true,
		        hidden: false,
		        rupType: 'select',
                editoptions: {
                	url: '../tableComarca/provincia',
                    sourceParam: {text: 'desc' +$.rup_utils.capitalizedLang(), id: 'code'},
					autocomplete: true,
                    contains: true,
                    combo: true
                }
		    },
		    {
		        name: 'provincia.descEs',
		        editable: true,
		        hidden: false
		    }
	    ];
	
	    $('#comarca').rup_table({
	        primaryKey: 'code',
	        loadOnStartUp: true,
            enableDynamicForms: true,
	        filter: {
	            id: 'comarca_filter_form',
	            filterToolbar: 'comarca_filter_toolbar',
	            collapsableLayerId: 'comarca_filter_fieldset'
	        },
	        colModel: tableColModelsComarca,
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        seeker: {
	            activate: true,
	            colModel: tableColModelsComarca
	        },
	        buttons: {
	            activate: true
	        },
	        select: {
	            style: 'multi'
	        },
	        formEdit: {
	            detailForm: '#comarca_detail_div',
	            url: '../tableComarca/editFormDialog',
	            /*validate: {
	                rules: {
	                    'code': {
	                        required: true
	                    },
	                    'provincia.code': {
	                        range: [1, 3]
	                    }
	                }
	            }*/
	        }
	    });
	
	    var tableColModelsLocalidad = [
	    	{
		        name: 'descEs',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'descEu',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'css',
		        editable: true,
		        hidden: false
		    }
	    ];
	
	    $('#localidad').rup_table({
	        primaryKey: 'code',
	        loadOnStartUp: false,
	        filter: {
	            id: 'localidad_filter_form',
	            filterToolbar: 'localidad_filter_toolbar',
	            collapsableLayerId: 'localidad_filter_fieldset'
	        },
	        colModel: tableColModelsLocalidad,
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        buttons: {
	            activate: true
	        },
	        select: {
	            style: 'multi'
	        },
	        order: [
	            [1, 'desc']
	        ],
	        drawCallback: function(ctx) {
	        	$('#localidad tr[role="row"]').on('tableSelectAfterSelectRow', function () {
	        		if (ctx.multiselection.selectedRowsPerPage.length > 0) {
	        			let line = ctx.multiselection.selectedRowsPerPage[0].line;
	        			let datos = ctx.json.rows[line]; //se carga el dato
	        			$('#css_detailForm_tableComarca').val(datos.css);
	        		}
	        	});
	        }
	    });
	
	    $('.contenedor').addClass('show');
	 });
});