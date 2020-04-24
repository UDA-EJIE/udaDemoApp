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
	    var tableColModelsComarca = [{
	        name: 'code',
	        index: 'code',
	        editable: true,
	        hidden: false,
	        width: 80,
	        formoptions: {
	            rowpos: 1,
	            colpos: 1
	        }
	    },
	    {
	        name: 'descEs',
	        index: 'descEs',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 2,
	            colpos: 1
	        }
	    },
	    {
	        name: 'descEu',
	        index: 'descEu',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 3,
	            colpos: 1
	        }
	    },
	    {
	        name: 'css',
	        index: 'css',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 4,
	            colpos: 1
	        }
	    },
	    {
	        name: 'provincia.code',
	        index: 'provincia.code',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 5,
	            colpos: 1
	        }
	    },
	    {
	        name: 'provincia.descEs',
	        index: 'provincia.descEs',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 6,
	            colpos: 1
	        }
	    }
	    ];
	
	    $('#comarca').rup_table({
	
	        primaryKey: 'code',
	        loadOnStartUp: true,
	        filter: {
	            id: 'comarca_filter_form',
	            filterToolbar: 'comarca_filter_toolbar',
	            collapsableLayerId: 'comarca_filter_fieldset'
	        },
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
	            validate: {
	                rules: {
	                    'code': {
	                        required: true
	                    },
	                    'provincia.code': {
	                        range: [1, 3]
	                    }
	                }
	            },
	            titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, 'rup_jqtable.edit.editCaption')
	        }
	    });
	
	    var tableColModelsLocalidad = [{
	        name: 'code',
	        index: 'code',
	        editable: true,
	        hidden: false,
	        width: 80,
	        formoptions: {
	            rowpos: 1,
	            colpos: 1
	        }
	    },
	    {
	        name: 'descEs',
	        index: 'descEs',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 2,
	            colpos: 1
	        }
	    },
	    {
	        name: 'descEu',
	        index: 'descEu',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 3,
	            colpos: 1
	        }
	    },
	    {
	        name: 'css',
	        index: 'css',
	        editable: true,
	        hidden: false,
	        formoptions: {
	            rowpos: 4,
	            colpos: 1
	        }
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
	        drawCallback: function( ctx ) {
	    		$('#localidad tr[role="row"]').on('tableSelectAfterSelectRow', function () {
	    			let line = ctx.multiselection.selectedRowsPerPage[0].line;
	    			let datos = ctx.json.rows[line]; //se carga el dato
	    			$('#css_detail_tableComarca').val(datos.css);
	    		});
	        }
	
	    });
	
	    $('#provinciaRemote').rup_combo({
	        source: '../jqGridComarca/provincia',
	        sourceParam: {
	            label: 'desc' + $.rup_utils.capitalizedLang(),
	            value: 'code',
	            style: 'css'
	        },
	        rowStriping: true,
	        blank: '',
	        width: '100%',
	        customClasses: ['select-material']
	    });
	
	    $('.contenedor').addClass('show');
	 });
});