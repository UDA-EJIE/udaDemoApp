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

jQuery(function($) {
    
    $('#tabsMaint').rup_tabs({
        tabs:[
            {i18nCaption:'maint1', url:'/x21aAppWar/calendar/noTemplate'},
            {i18nCaption:'maint2', url:'/x21aAppWar/calendar/noTemplate'},
            {i18nCaption:'maint3', url:'/x21aAppWar/calendar/noTemplate'},
            {i18nCaption:'edit1', url:'/x21aAppWar/lista/noTemplate'},
            {i18nCaption:'multi1', url:'/x21aAppWar/lista/noTemplate'}],
        load: function(){
            if($('#mockPageContent').length > 0){
                $('#tabsMaint').rup_tabs('disableTabs', {
                    idTab: 'tabsMaint',
                    position: [1,2,3,4]
                });
                $('#mockPageContent #loginButtonObject').on('click', function (){
                    $('#tabsMaint').rup_tabs('enableTabs', {
                        idTab: 'tabsMaint',
                        position: [1,2,3,4]
                    });
                });
            }
        }
    });
});
