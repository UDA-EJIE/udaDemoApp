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
            name: 'nid',
            index: 'nid',
            editable: false,
            hidden: false,
            width: 80,
            obligatorio: true,
            formoptions: {
                rowpos: 1,
                colpos: 1
            }
        },
        {
            name: 'nombre',
            index: 'nombre',
            editable: true,
            hidden: false,
            obligatorio: true,
            formoptions: {
                rowpos: 2,
                colpos: 1
            }
        },
        {
            name: 'apellido1',
            index: 'apellido1',
            editable: true,
            hidden: false,
            formoptions: {
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
                rowpos: 5,
                colpos: 1
            }
        },
        {
            name: 'fechaAlta',
            index: 'fecha_alta',
            editable: true,
            hidden: false,
            width: 120,
            obligatorio: true,
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
                rowpos: 3,
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
                source: $.map(combo, function (elem) {
                    return {
                        label: elem.rol,
                        value: elem.codTipoSubsanacion
                    };

                }),
                width: '100%',
                customClasses: ['select-material']
            },
            formoptions: {
                rowpos: 3,
                colpos: 2
            }
        }
        ],
        optionalColumns = [{
            label: 'apellido1',
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

      //se inicializa
    
    $('#columsSelector').rup_combo({
        source: optionalColumns,
        ordered: false,
        multiselect: true,
        rowStriping: true,
        width: 350,
        readAsString: true,
        customClasses: ['select-material']
    });
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
    
    function loadTable(tableColModel) {
    	let valores = localStorage.columnas.replace('apellido1','3');
    	valores = valores.replace('ejie','4');
    	valores = valores.replace('fechaBaja','6');
    	valores = valores.replace('rol','7');
    	$('#columsSelector').rup_combo('setRupValue',valores);
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