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
	        formEdit: {
	            detailForm: '#comarca_detail_div',
	            url: '../tableComarcaExtendida/editForm',
	        }
	    });
	
	    $('.contenedor').addClass('show');
	 });
});