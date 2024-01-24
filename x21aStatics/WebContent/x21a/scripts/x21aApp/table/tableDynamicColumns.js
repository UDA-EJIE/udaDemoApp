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
    let combo = [{
            rol: '---',
            codTipoSubsanacion: ''
        },
        {
            rol: 'Administrador',
            codTipoSubsanacion: 'administrador'
        },
        {
            rol: 'Desarrollador',
            codTipoSubsanacion: 'desarrollador'
        },
        {
            rol: 'Espectador',
            codTipoSubsanacion: 'espectador'
        },
        {
            rol: 'Informador',
            codTipoSubsanacion: 'informador'
        },
        {
            rol: 'Manager',
            codTipoSubsanacion: 'manager'
        }
        ];
      let tableColModel = [{
            name: 'id',
            index: 'id',
            editable: true,
            hidden: false,
            obligatorio: true
        },
        {
            name: 'nombre',
            index: 'nombre',
            editable: true,
            hidden: false,
            obligatorio: true
        },
        {
            name: 'apellido1',
            index: 'apellido1',
            editable: true,
            hidden: false
        },
        {
            name: 'ejie',
            index: 'ejie',
            editable: true,
            hidden: false
        },
        {
            name: 'fechaAlta',
            index: 'fechaAlta',
            editable: true,
            hidden: false,
            obligatorio: true,
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
            rupType: 'select',
            editoptions: {
                data: $.map(combo, function (elem) {
                    return {
                        text: elem.rol,
                        id: elem.codTipoSubsanacion
                    };

                })
            }
        }
        ],
        optionalColumns = [{
            text: 'apellido1',
            id: '3'
        },
        {
            text: 'ejie',
            id: '4'
        },
        {
            text: 'fechaBaja',
            id: '6'
        },
        {
            text: 'rol',
            id: '7'
        }
        ];

      //se inicializa
    
    $('#columsSelector').rup_select({
        url: optionalColumns,
        ordered: false,
        multiselect: true,
        rowStriping: true,
        readAsString: true
    });
    $('#columsSelector').rup_select('setRupValue', '0');

    $('#btnTableLoad').click(function () {
    	let columnas = [];
        $.each($('#columsSelector').rup_select('getRupValue'), function () {
             columnas.push(tableColModel[this - 1].name);
        });
        localStorage.columnas = columnas;
    	location.reload();
    });


    $('.contenedor').addClass('show');
    
    function loadTable(tableColModel) {
    	let valores = localStorage.columnas.replace('apellido1', '3');
    	valores = valores.replace('ejie', '4');
    	valores = valores.replace('fechaBaja', '6');
    	valores = valores.replace('rol', '7');
    	$('#columsSelector').rup_select('setRupValue', valores);
        tableColModel = jQuery.grep(tableColModel, function (item) {
            var temp = '';

            if (!item.obligatorio) {
                // Bucle para los opcionales
                $.each(localStorage.columnas.split(','), function () {
                    if (item.name === this.toString()) {
                        temp = item;
                        return;
                    }
                });
            }

            if (temp !== '' || item.obligatorio) {
                return item;
            } else {
                // Eliminamos la columna
                $('th[data-col-prop=\'' + item.name + '\']').remove();
            }
        });

        $('#columnasDinamicas').rup_table({
            colModel: tableColModel,
            filterMessage: false,
            buttons: {
                activate: true
            },
            select: {
                activate: true
            },
            inlineEdit: {
                deselect: true,
                validate: {
                    rules: {
                        'id': {
                            required: true
                        },
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
	
	cargarTablas(localStorage,tableColModel);
	});


});