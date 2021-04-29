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
		let tableColModel = [
			{
	            name: 'nombre',
	            index: 'nombre',
	            editable: true,
	            hidden: false,
	            compulsory: true,
	            formoptions: {
	                rowpos: 1,
	                colpos: 1
	            }
	        },
	        {
                name: 'apellido1',
                index: 'apellido1',
                editable: true,
                hidden: false,
                rupType: 'autocomplete',
                editoptions: {
                	source : '../table/apellidos',
                    sourceParam : {label: 'label', value: 'value'},
                    menuMaxHeight: 200,
                    minLength: 3,
                    combobox: true,
                    contains: true
                },
                formoptions: {
                    rowpos: 2,
                    colpos: 1
                },
                classes: 'ui-ellipsis'
            },
            { 
            	name: "apellido2", 
            	index: "apellido2", 
            	editable: true, 
            	hidden: false,
            	rupType: 'combo',
                editoptions: {
                	source : '../table/apellidos',
                    sourceParam : {label: 'label', value: 'value'},
                    blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                },
            	formoptions:{
            		rowpos: 3, 
            		colpos: 1
            	},
            	classes: 'ui-ellipsis'
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
	            },
	            formoptions: {
	                rowpos: 4,
	                colpos: 1
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
	            },
	            formoptions: {
	                rowpos: 1,
	                colpos: 2
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
	            },
	            formoptions: {
	                rowpos: 2,
	                colpos: 2
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
                    source : '../table/roles',
                    sourceParam : {label: 'label', value: 'value'},
                    blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                },
                formoptions: {
                    rowpos: 3,
                    colpos: 2
                }
            }
	    ];
	        
		let optionalColumns = [
			{
	            label: 'apellido1',
	            value: '2'
	        },
	        {
	            label: 'apellido2',
	            value: '3'
	        },
	        {
	            label: 'ejie',
	            value: '4'
	        },
	        {
	            label: 'fechaBaja',
	            value: '6'
	        },
	        {
	            label: 'rol',
	            value: '7'
	        }
        ];

		// Se inicializa
	    $('#columsSelector').rup_combo({
	        source: optionalColumns,
	        ordered: false,
	        multiselect: true,
	        rowStriping: true,
	        width: 350,
	        readAsString: true,
	        customClasses: ['select-material'],
	        onLoadSuccess: function () {
	        	$('#columsSelector').rup_combo('setRupValue','0');
	        	
	        	$('#btnTableLoad').click(function () {
	    	    	let columnas = [];
	    	        $.each($('#columsSelector').rup_combo('getRupValue'), function () {
	    	             columnas.push(tableColModel[this - 1].name);
	    	        });
	    	        localStorage.columnas = columnas;
	    	    	location.reload();
	    	    });
	        	
	        	$('.contenedor').addClass('show');
	    		
	    		cargarTablas(localStorage, tableColModel);
	        }
	    });
	    
	    function loadTable(tableColModel) {
	    	let valores = localStorage.columnas.replace('apellido1','2');
	    	valores = valores.replace('apellido2','3');
	    	valores = valores.replace('ejie','4');
	    	valores = valores.replace('fechaBaja','6');
	    	valores = valores.replace('rol','7');
	    	$('#columsSelector').rup_combo('setRupValue', valores);
	        tableColModel = jQuery.grep(tableColModel, function (item) {
	            let temp = '';
	
	            if (!item.compulsory) {
	                // Bucle para los opcionales
	                $.each(localStorage.columnas.split(','), function () {
	                    if (item.name === this.toString()) {
	                        temp = item;
	                        return;
	                    }
	                });
	            }
	
	            if (temp !== '' || item.compulsory) {
	                return item;
	            } else {
	                // Eliminamos la columna
	                $('th[data-col-prop=\'' + item.name + '\']').remove();
	            }
	        });
	
	        $('#columnasDinamicas').rup_table({
	            colModel: tableColModel,
	            columnDefs: [{
	         	   'targets': 0,
	         	   'visible': false
	         	}],
	            buttons: {
	                activate: true
	            },
	            select: {
	                activate: true
	            },
	            enableDynamicForms: true,
	            inlineEdit: {
	                deselect: true,
	                validate: {
	                    rules: {
	                        'nombre': {
	                            required: true
	                        },
	                        'fechaAlta': {
	                            required: true
	                        }
	                    }
	                }
	            },
	            filter : 'noFilter'
	        });
	
	        // Ocultamos los elementos configuradores de la tabla
	        //$('div#columsSelectorContainer, button#btnTableLoad').addClass('d-none');
	        $('table#columnasDinamicas').removeClass('d-none');
	    }
		
		function cargarTablas(localStorage,tableColModel){
		    if (localStorage.columnas !== undefined) { //si esta undefined es que es la primera vez.
		    	loadTable(tableColModel);
		    }
		}
	});
});