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
jQuery(function($){
    
    function loadTable(){
        
        var tableColModels = jQuery.grep(tableColModels, function(item) {
            var temp = '';
            
            if(!item.obligatorio){
                // Bucle para los opcionales
                $.each($('#columsSelector').rup_combo('getRupValue'), function () {
                    if(item.name === tableColModels[this - 1].name) {
                        temp = item;
                        return;
                    }
                });
            }
            
            if(temp !== '' || item.obligatorio){
                return item;
            }
        });
        
        jQuery('#table').rup_jqtable({
            url: '../jqGridUsuario',
            colModel: tableColModels,
            usePlugins:[
                'inlineEdit',
                'feedback',
                'toolbar',
                'contextMenu',
                'responsive',
                'filter',
                'report'
            ],
            editOptions:{
                fillDataMethod:'clientSide'
            },
            primaryKey: 'id',
            sortname: 'id',
            validate:{
                rules:{
                    'nombre':{required:true},
                    'apellido1':{required:true},
                    'fechaAlta':{date:true},
                    'fechaBaja':{date:true}
                }
            },
            filter:{
                validate:{
                    rules:{
                        'fechaAlta':{date:true},
                        'fechaBaja':{date:true}
                    }
                }
            },
            report: window.options_table_report
        });
        
        // Ocultamos los elementos configuradores de la tabla
        $('div#columsSelectorContainer, button#btnTableLoad').addClass('d-none');
        $('div#table_div, div#table_detail_div').removeClass('d-none');
    }
    
    $('#columsSelector').rup_combo({
        source: window.optionalColumns,
        ordered: false,
        multiselect: true,
        rowStriping : true,
        width: 350,
        customClasses: ['select-material']
    });
    
    $('#btnTableLoad').click(function() {
        loadTable();
    });	
});