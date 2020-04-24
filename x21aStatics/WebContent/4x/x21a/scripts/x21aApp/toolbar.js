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
jQuery(document).ready(function () {
    $('#toolbar').rup_toolbar({

        buttons:[
            {i18nCaption:'buscar', css:'mdi mdi-magnify', click: handlerBoton },
            {id: 'mbuton1', i18nCaption:'otros', buttons:[
                {i18nCaption:'nuevo', css:'mdi mdi-file-outline', click: handlerMButtons},
                {i18nCaption:'editar', css:'mdi mdi-square-edit-outline', click: handlerMButtons},
                {i18nCaption:'cancelar', css:'mdi mdi-close-circle-outline', click: handlerMButtons},
                {i18nCaption:'borrar', css:'mdi mdi-delete', click: handlerMButtons},
                {i18nCaption:'filtrar', css:'mdi mdi-filter', click: handlerMButtons},
                {i18nCaption:'imprimir', css:'mdi mdi-printer', click: handlerMButtons}
            ]},
            {i18nCaption:'editar', css:'mdi mdi-square-edit-outline', click: handlerMButtons},
            {i18nCaption:'borrar', css:'mdi mdi-delete', click: handlerMButtons},
            {id : 'mbuton2', i18nCaption:'ficheros', buttons:[
                {i18nCaption:'DLL', css:'mdi mdi-file-outline', click: handlerMButtons },
                {i18nCaption:'DOC', css:'mdi mdi-file-word', click: handlerMButtons},
                {i18nCaption:'EXE', css:'mdi mdi-file-outline', click: handlerMButtons},
                {i18nCaption:'GIF', css:'mdi mdi-file-image', click: handlerMButtons},
                {i18nCaption:'JPG', css:'mdi mdi-file-image', click: handlerMButtons},
                {i18nCaption:'JS',  css:'mdi mdi-file-xml',  click: handlerMButtons},
                {i18nCaption:'PDF', css:'mdi mdi-file-pdf', click: handlerMButtons},
                {i18nCaption:'PPT', css:'mdi mdi-file-powerpoint', click: handlerMButtons},
                {i18nCaption:'TXT', css:'mdi mdi-file-document', click: handlerMButtons},
                {i18nCaption:'XLS', css:'mdi mdi-file-excel', click: handlerMButtons},
                {i18nCaption:'ZIP', css:'mdi mdi-file-outline', click: handlerMButtons}
            ]},
            {i18nCaption:'filtrar', css:'mdi mdi-filter', click: handlerMButtons}
        ]
    });

    $('#toolbarRight').rup_toolbar({

        buttons:[
            {i18nCaption:'cancelar', css:'mdi mdi-close-circle-outline', click: handlerMButtons, right: true},
            {i18nCaption:'borrar', css:'mdi mdi-delete', click: handlerMButtons, right: true},
            {id: 'mbuton1', i18nCaption:'otros', right: true, buttons:[
                {i18nCaption:'nuevo', css:'mdi mdi-file-outline', click: handlerMButtons},
                {i18nCaption:'editar', css:'mdi mdi-square-edit-outline', click: handlerMButtons},
                {i18nCaption:'cancelar', css:'mdi mdi-close-circle-outline', click: handlerMButtons},
                {i18nCaption:'borrar', css:'mdi mdi-delete', click: handlerMButtons},
                {i18nCaption:'filtrar', css:'mdi mdi-filter', click: handlerMButtons},
                {i18nCaption:'imprimir', css:'mdi mdi-printer', click: handlerMButtons}
            ]},
            {i18nCaption:'editar', css:'mdi mdi-square-edit-outline', click: handlerMButtons, right: true},
            {id : 'mbuton2', i18nCaption:'ficheros', right: true, buttons:[
                {i18nCaption:'DLL', css:'mdi mdi-file-outline', click: handlerMButtons },
                {i18nCaption:'DOC', css:'mdi mdi-file-word', click: handlerMButtons},
                {i18nCaption:'EXE', css:'mdi mdi-file-outline', click: handlerMButtons},
                {i18nCaption:'GIF', css:'mdi mdi-file-image', click: handlerMButtons},
                {i18nCaption:'JPG', css:'mdi mdi-file-image', click: handlerMButtons},
                {i18nCaption:'JS',  css:'mdi mdi-file-xml',  click: handlerMButtons},
                {i18nCaption:'PDF', css:'mdi mdi-file-pdf', click: handlerMButtons},
                {i18nCaption:'PPT', css:'mdi mdi-file-powerpoint', click: handlerMButtons},
                {i18nCaption:'TXT', css:'mdi mdi-file-document', click: handlerMButtons},
                {i18nCaption:'XLS', css:'mdi mdi-file-excel', click: handlerMButtons},
                {i18nCaption:'ZIP', css:'mdi mdi-file-outline', click: handlerMButtons}
            ]},
            {i18nCaption:'filtrar', css:'mdi mdi-filter', click: handlerMButtons, right: true},
            {i18nCaption:'imprimir', css:'mdi mdi-printer', click: handlerMButtons, right: true}
        ]
    });

    $('#toolbarRwd').rup_toolbar({
        buttons:[
            {id: 'filter', text:'Filtrar', css:'mdi mdi-filter', click: handlerMButtons},
            {id: 'print', text:'Imprimir', css:'mdi mdi-printer', click: handlerMButtons},
            {id: 'others', text:'Otros', groupClasses:'rup-collapsed-md', buttons:[
                {id: 'new', text:'Nuevo', css:'mdi mdi-file-outline', click: handlerMButtons},
                {id: 'edit', text:'Editar', css:'mdi mdi-square-edit-outline', click: handlerMButtons},
                {id: 'cancel', text:'Cancelar', css:'mdi mdi-close-circle-outline', click: handlerMButtons},
                {id: 'delete', text:'Borrar', css:'mdi mdi-delete', click: handlerMButtons}
            ]}
        ]
    });

    $('#toolbarMixta').rup_toolbar({

        buttons:[
            {i18nCaption:'cancelar', css:'mdi mdi-close-circle-outline', click: handlerMButtons, right: true},
            {i18nCaption:'buscar', css:'mdi mdi-magnify', click: handlerBoton },
            {id: 'mbuton1', i18nCaption:'otros', buttons:[
                {i18nCaption:'nuevo', css:'mdi mdi-file-outline', click: handlerMButtons},
                {i18nCaption:'editar', css:'mdi mdi-square-edit-outline', click: handlerMButtons},
                {i18nCaption:'cancelar', css:'mdi mdi-close-circle-outline', click: handlerMButtons},
                {i18nCaption:'borrar', css:'mdi mdi-delete', click: handlerMButtons},
                {i18nCaption:'filtrar', css:'mdi mdi-filter', click: handlerMButtons},
                {i18nCaption:'imprimir', css:'mdi mdi-printer', click: handlerMButtons}
            ]},
            {id : 'mbuton2', i18nCaption:'ficheros', right: true, buttons:[
                {i18nCaption:'DLL', css:'mdi mdi-file-outline', click: handlerMButtons },
                {i18nCaption:'DOC', css:'mdi mdi-file-word', click: handlerMButtons},
                {i18nCaption:'EXE', css:'mdi mdi-file-outline', click: handlerMButtons},
                {i18nCaption:'GIF', css:'mdi mdi-file-image', click: handlerMButtons},
                {i18nCaption:'JPG', css:'mdi mdi-file-image', click: handlerMButtons},
                {i18nCaption:'JS',  css:'mdi mdi-file-xml',  click: handlerMButtons},
                {i18nCaption:'PDF', css:'mdi mdi-file-pdf', click: handlerMButtons},
                {i18nCaption:'PPT', css:'mdi mdi-file-powerpoint', click: handlerMButtons},
                {i18nCaption:'TXT', css:'mdi mdi-file-document', click: handlerMButtons},
                {i18nCaption:'XLS', css:'mdi mdi-file-excel', click: handlerMButtons},
                {i18nCaption:'ZIP', css:'mdi mdi-file-outline', click: handlerMButtons}
            ]},
            {i18nCaption:'filtrar', css:'mdi mdi-filter', click: handlerMButtons},
            {i18nCaption:'imprimir', css:'mdi mdi-printer', click: handlerMButtons, right: true}
        ]
    });
    
    function handlerBoton(){
        alert ('Se ha pulsado el boton');
    }
    function handlerMButtons(event){
        alert ('MButton:' + event.data.i18nCaption);
    }


    $('.contenedor').addClass('show');
});