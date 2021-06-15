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
		        index: 'descEs',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'descEu',
		        index: 'descEu',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'css',
		        index: 'css',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'provincia.code',
		        index: 'provincia.code',
		        editable: true,
		        hidden: false,
		        rupType: 'combo',
                editoptions: {
                	source : '../tableComarca/provincia',
                    sourceParam : {
                    	label: 'desc' + $.rup_utils.capitalizedLang(), 
                    	value: 'code',
        	            style: 'css'
                    },
        	        rowStriping: true,
        	        blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                }
		    },
		    {
		        name: 'provincia.descEs',
		        index: 'provincia.descEs',
		        editable: true,
		        hidden: false
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
            enableDynamicForms: true,
	        formEdit: {
	            detailForm: '#comarca_detail_div',
	            url: '../tableComarca/editForm',
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
		        index: 'descEs',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'descEu',
		        index: 'descEu',
		        editable: true,
		        hidden: false
		    },
		    {
		        name: 'css',
		        index: 'css',
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
	        seeker: {
	            activate: true,
	            colModel: tableColModelsLocalidad
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
	        masterDetail: {
	            master: '#comarca',
	            masterPrimaryKey: 'comarca.code',
	            masterPrimaryNid: true
	        },
            enableDynamicForms: true,
	        formEdit: {
	            detailForm: '#localidad_detail_div',
	            url: '../tableLocalidad/editForm',
	            /*validate: {
	                rules: {
	                    'code': {
	                        required: true
	                    }
	                }
	            }*/
	        }
	
	    });	
	
	    $('.contenedor').addClass('show');
	 });
});