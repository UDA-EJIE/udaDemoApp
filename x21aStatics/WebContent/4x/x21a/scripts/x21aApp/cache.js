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
		let tableColModel = [
			{
	            name: 'nombre',
	            index: 'nombre',
	            editable: true,
	            hidden: false,
	            compulsory: true
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
                	source : '../../table/apellidos',
                    sourceParam : {label: 'label', value: 'value'},
                    menuMaxHeight: 200,
                    minLength: 3,
                    combobox: true,
                    contains: true
                }
            },
	        {
	            name: 'ejie',
	            index: 'ejie',
	            editable: true,
	            hidden: false,
	            width: 60,
	            edittype: 'checkbox',
	            formatter: 'checkbox',
	            rwdClasses: 'hidden-xs hidden-sm hidden-md',
	            align: 'center',
	            editoptions: {
	                value: '1:0'
	            }
	        },
	        {
	            name: 'fechaAlta',
	            index: 'fecha_alta',
	            editable: true,
	            hidden: false,
	            width: 120,
	            compulsory: true,
	            rupType: 'date',
	            rwdClasses: 'hidden-xs hidden-sm hidden-md',
	            editoptions: {
	                labelMaskId: 'fecha-mask',
	                showButtonPanel: true,
	                showOtherMonths: true,
	                noWeekend: true
	            }
	        },
	        {
	            name: 'fechaBaja',
	            index: 'fecha_baja',
	            editable: false,
	            hidden: false,
	            width: 120,
	            rupType: 'date',
	            rwdClasses: 'hidden-xs hidden-sm hidden-md',
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
                width: 140,
                rupType: 'combo',
                rwdClasses: 'hidden-xs hidden-sm hidden-md',
                formatter: 'rup_combo',
                editoptions: {
                    source : '../../table/roles',
                    sourceParam : {label: 'label', value: 'value'},
                    blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                }
            }
	    ];
    
	    $('#table').rup_table({
	        colModel: tableColModel,
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
	        enableDynamicForms: true,
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