/*!
 * Copyright 2011 E.J.I.E., S.A.
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
$(document).ready(function () {

    $('#idDialog').rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: true,
        title: '<i>Título del dialog (div)</i>',
        buttons: [{
            text: 'Aceptar',
            click: function () { 
                $('#idDialog').rup_dialog('close'); 
            }
        },
        {
            text: 'Enviar',
            click: function () { 
                $('#idDialog').rup_dialog('close'); 
            }
        },
        {
            text: 'Abandonar',
            click: function () { 
                $('#idDialog').rup_dialog('close'); 
            },
            btnType: $.rup.dialog.LINK
        }
        ],
        position: { my: 'left top', at: 'left bottom', of: $('#btnAjaxDialogWAR') } 
    });	
    $('#btnDialog').bind('click', function () {
        $('#idDialog').rup_dialog('open');
    });

    
    $('#btnAjaxDialogWAR').bind('click', function () {
        $('#idDialogAjaxWar').rup_dialog({
            type: $.rup.dialog.AJAX,
            url: '../patrones/dialogAjax' ,
            autoOpen: true,
            modal: true,
            width: '650',
            resizable: true,
            title: 'Diálogo Ajax (War)',
            buttons: [{
                text: 'Aceptar',
                click: function () { 
                    $('#idDialogAjaxWar').rup_dialog('close');
                }					
            },
            {
                text: 'Enviar',
                click: function () { 
                    $('#idDialogAjaxWar').rup_dialog('close'); 
                }
            },
            {
                text: 'Abandonar',
                click: function () { 
                    $('#idDialogAjaxWar').rup_dialog('close');
                },
                btnType: $.rup.dialog.LINK
            }]
        });	
    });
    
    $('#btnAjaxDialogStatics').bind('click', function () {
        $('#idDialogAjaxStatics').rup_dialog({
            type: $.rup.dialog.AJAX,
            url: $.rup.APP_STATICS + '/resources/ajaxDiv.htm',
            autoOpen: true,
            modal: true,
            width: '650',
            resizable: true,
            title: 'Diálogo Ajax (Statics)',
            buttons: [{
                text: 'Aceptar',
                click: function () { 
                    $(this).dialog('close');
                }					
            }]
        });	
    });
    
    $('#btnTextDialog').bind('click', function () {
        $('#idDialogText').rup_dialog({
            type: $.rup.dialog.TEXT,
            autoOpen: true,
            modal: true,
            resizable: true,
            title: 'Título del dialog (text) ',
            message: 'Se esta creando un div con el mensaje puesto por parametro.'
        });
    });

    $('#btnSetOptionDialog').bind('click', function () {
        $('#idDialogSetOption').rup_dialog({
            type: $.rup.dialog.AJAX,
            url: $.rup.APP_STATICS + '/resources/ajaxDiv.htm',
            autoOpen: true,
            modal: true,
            width: '650',
            resizable: true,
            title: 'Botones con "setOption"'
        });

        $('#idDialogSetOption').rup_dialog("setOption", "buttons",
            [{
                text: 'Aceptar',
                click: function () { 
                    $('#idDialogAjaxWar').rup_dialog('close');
                }				
            },
            {
                text: 'Enviar',
                click: function () { 
                    $('#idDialogAjaxWar').rup_dialog('close'); 
                }
            },
            {
                text: 'Abandonar',
                click: function () { 
                    $('#idDialogAjaxWar').rup_dialog('close');
                },
                btnType: $.rup.dialog.LINK
            }]
        );
    });

    $('.contenedor').addClass('show');
});