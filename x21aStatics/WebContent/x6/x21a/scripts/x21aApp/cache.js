/*!
 * Copyright 2012 E.J.I.E., S.A.
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
jQuery(function($){
	window.initRupI18nPromise.then(function () {
		const tableColModels = [
			{
	            name: 'nombre',
	            editable: true,
	            hidden: false
	        },
	        {
                name: 'apellido1',
                editable: true,
                hidden: false,
            	rupType: 'select',
                editoptions: {
                	url: '../../table/apellidos',
                    sourceParam: {text: 'label', id: 'value'},
					autocomplete: true,
                    contains: true,
                    combo: true
                }
            },
            { 
            	name: "apellido2",
            	editable: true, 
            	hidden: false,
            	rupType: 'select',
                editoptions: {
                	url: '../../table/apellidos',
                    sourceParam : {text: 'label', id: 'value'},
					autocomplete: true,
                    contains: true,
                    combo: true
                }
            },
	        {
	            name: 'ejie',
	            editable: true,
	            hidden: false,
                edittype: 'checkbox'
	        },
	        {
	            name: 'fechaAlta',
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
                editable: true,
                hidden: false,
                rupType: 'select',
                editoptions: {
                    source : '../../table/roles',
                    sourceParam : {text: 'label', id: 'value'},
                    blank: ''
                }
            }
	    ];
    
	    $('#table').rup_table({
	        colModel: tableColModels,
	        pageLength: 100,
	        buttons: {
                activate: true,
                blackListButtons: ['addButton', 'cloneButton', 'deleteButton', 'reportsButton'],
                myButtons: [{
            	 	text: function () {
            	 		return 'Recargar tabla';
            	 	},
            	 	id: 'exampleRecargarTabla', // Campo obligatorio si se quiere usar desde el contextMenu
            	 	className: 'btn-material-primary-high-emphasis table_toolbar_btnReload order-last ml-1 ml-lg-auto',
            	 	icon: "mdi-refresh",
            	 	displayRegex: /^\d+$/, // Se muestra siempre que sea un numero positivo o neutro
            	 	insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
            	 	type: 'reload',
            	 	action: function () {
            	 		$('#table').rup_table('reload');
            	 	}
                }]
            },
            select: {
                activate: true
            },
	        inlineEdit: {
	            deselect: true,
	            url: './inlineEdit',
	            validate: {
	                rules: {
	                    'nombre': {
	                        required: true
	                    },
	                    'apellido1': {
	                        required: true
	                    },
	                    'fechaAlta': {
	                        required: true
	                    },
	                    'fechaBaja': {
	                        date: true
	                    }
	                },
	                messages: {
	                    required: 'Campo requerido'
	                }
	            }
	        },
            filter: 'noFilter'
	    });
	});

    $('.contenedor').addClass('show');
});