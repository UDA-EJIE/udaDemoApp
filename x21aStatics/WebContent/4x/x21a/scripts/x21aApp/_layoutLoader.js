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
jQuery(function($) {
    //Habilitamos el envío de trazas a PIB
    window.IS_EJIE = true;
    
    jQuery('#rup_dept_logo').attr('src', jQuery.rup.APP_STATICS + '/images/dept_logo_' + jQuery.rup.lang + '.gif');
    
    //rastro de migas
    jQuery('#x21aAppWar_migas').rup_breadCrumb({
        logOutUrl: '/x21aAppWar/logout',
        breadCrumb: {
            'patrones' : {
                //Literal
                'i18nCaption' : 'patrones',
                //Elementos (url)
                'all' : { 'i18nCaption' : 'all' },
                'accordion' : { 'i18nCaption' : 'accordion' },
                'treeDAD' : {'i18nCaption':'treeDAD' },
                //				"treePanel" : {"i18nCaption":"treePanel" },
                'trees' : {'i18nCaption':'trees' },
                //				"treesFormats" : {"i18nCaption":"treesFormats" },
                'autocomplete' : { 'i18nCaption' : 'autocomplete' }, 
                'button' : { 'i18nCaption' : 'button' }, 
                'toolbar' : { 'i18nCaption' : 'toolbar' },
                'page' : { 'i18nCaption' : 'calendarioSimple' },
                'pageDouble' : { 'i18nCaption' : 'calendarioDoble' },
                'comboSimple' : {'i18nCaption':'comboSimple' },
                'comboEnlazadoSimple' : { 'i18nCaption':'comboEnlazadoSimple' },
                'comboEnlazadoMultiple' : { 'i18nCaption':'comboEnlazadoMulti' },
                'multicombo' : { 'i18nCaption':'multicombo' },
                'comboMantenimiento': {'i18nCaption':'comboMantenimiento'},
                'slider' : { 'i18nCaption' : 'slider' },
                'dialog' : { 'i18nCaption' : 'dialog' },
                'date' : { 'i18nCaption' : 'date' },
                'feedback' : { 'i18nCaption' : 'feedback' },
                'form' : { 'i18nCaption' : 'form' },
                'charts' : { 'i18nCaption' : 'charts' },
                'time' : { 'i18nCaption' : 'time' },
                'configurable' : { 'i18nCaption' : 'listaConfigurable' },
                'doble' : { 'i18nCaption' : 'listaDoble' },
                'message' : { 'i18nCaption' : 'message' },
                'contextMenu' : { 'i18nCaption' : 'contextMenu' },
                'progressBar' : { 'i18nCaption' : 'progressBar' },
                'tabsStatic' : {'i18nCaption':'tabsStatic'},
                'tabsAjax' : {'i18nCaption':'tabsAjax'},
                'tabsMixto' : {'i18nCaption':'tabsMixto'},
                'tabsScrollable' : {'i18nCaption':'tabsScrollable'},
                'maintTab' : {'i18nCaption':'maintTab'},
                'validateRules' : { 'i18nCaption' : 'validateRules' },
                'spinner' : { 'i18nCaption' : 'spinner' },
                'tooltip' : { 'i18nCaption' : 'tooltip' },
                'upload' : { 'i18nCaption' : 'upload' },
                'validate' : { 'i18nCaption' : 'validate' },
                'wizard' : {'i18nCaption':'wizardA' },
                'wizard_includeFile' : {'i18nCaption':'wizardB' },
                'wizard_jspInclude' : {'i18nCaption':'wizardC' },
                'wizard_jstlImport' : {'i18nCaption':'wizardD' },
                'wizard_dinamico' : {'i18nCaption':'wizardE' },
                //Submenu
                'subLevel':[
                    {'i18nCaption':'all', 'url': '/x21aAppWar/patrones/all' },
                    {'i18nCaption':'accordion', 'url': '/x21aAppWar/patrones/accordion' },
                    {'i18nCaption':'treeDAD', 'url': '/x21aAppWar/patrones/treeDAD' },
                    //	                {"i18nCaption":"treePanel", "url": "/x21aAppWar/patrones/trees" },
                    {'i18nCaption':'trees', 'url': '/x21aAppWar/patrones/trees' },
                    //	                {"i18nCaption":"treesFormats", "url": "/x21aAppWar/patrones/trees" },
                    {'i18nCaption':'autocomplete', 'url': '/x21aAppWar/patrones/autocomplete' },
                    {'i18nCaption':'progressBar', 'url': '/x21aAppWar/patrones/progressBar' },
                    {'i18nCaption':'button', 'url': '/x21aAppWar/patrones/button' },
                    {'i18nCaption':'toolbar', 'url': '/x21aAppWar/patrones/toolbar' },
                    {'i18nCaption':'calendarioSimple', 'url': '/x21aAppWar/patrones/calendar/page' },
                    {'i18nCaption':'calendarioDoble', 'url': '/x21aAppWar/patrones/calendar/pageDouble' },
                    {'i18nCaption':'comboSimple', 'url': '/x21aAppWar/patrones/comboSimple', 'newWindow': true },
                    {'i18nCaption':'comboEnlazadoSimple', 'url': '/x21aAppWar/patrones/comboEnlazadoSimple' },
                    {'i18nCaption':'comboEnlazadoMulti', 'url': '/x21aAppWar/patrones/comboEnlazadoMultiple' },
                    {'i18nCaption':'multicombo', 'url': '/x21aAppWar/patrones/multicombo' },
                    {'i18nCaption':'comboMantenimiento', 'url': '/x21aAppWar/patrones/comboMantenimiento' },
                    {'i18nCaption':'slider', 'url': '/x21aAppWar/patrones/slider' },
                    {'i18nCaption':'dialog', 'url': '/x21aAppWar/patrones/dialog' },
                    {'i18nCaption':'date', 'url': '/x21aAppWar/patrones/date' },
                    {'i18nCaption':'feedback', 'url': '/x21aAppWar/patrones/feedback' },
                    {'i18nCaption':'form', 'url': '/x21aAppWar/patrones/form' },
                    {'i18nCaption':'charts', 'url': '/x21aAppWar/patrones/charts' },
                    {'i18nCaption':'time', 'url': '/x21aAppWar/patrones/time' },
                    {'i18nCaption':'listaConfigurable', 'url': '/x21aAppWar/patrones/lista/configurable' },
                    {'i18nCaption':'listaDoble', 'url': '/x21aAppWar/patrones/lista/doble' },
                    {'i18nCaption':'message', 'url': '/x21aAppWar/patrones/message' },
                    {'i18nCaption':'contextMenu', 'url': '/x21aAppWar/patrones/contextMenu' },
                    {'i18nCaption':'tabsStatic', 'url': '/x21aAppWar/patrones/tabsStatic' },
                    {'i18nCaption':'tabsAjax', 'url': '/x21aAppWar/patrones/tabsAjax' },
                    {'i18nCaption':'tabsMixto', 'url': '/x21aAppWar/patrones/tabsMixto' },
                    {'i18nCaption':'tabsScrollable', 'url': '/x21aAppWar/patrones/tabsScrollable' },
                    {'i18nCaption':'maintTab', 'url': '/x21aAppWar/patrones/maintTab' },
                    {'i18nCaption':'validateRules', 'url': '/x21aAppWar/patrones/validateRules' },
                    {'i18nCaption':'spinner', 'url': '/x21aAppWar/patrones/spinner' },
                    {'i18nCaption':'tooltip', 'url': '/x21aAppWar/patrones/tooltip' },
                    {'i18nCaption':'upload', 'url': '/x21aAppWar/patrones/upload' },
                    {'i18nCaption':'validate', 'url': '/x21aAppWar/patrones/validate' },
                    {'i18nCaption':'wizardA', 'url': '/x21aAppWar/patrones/wizard' },
                    {'i18nCaption':'wizardB', 'url': '/x21aAppWar/patrones/wizard_includeFile' },
                    {'i18nCaption':'wizardC', 'url': '/x21aAppWar/patrones/wizard_jspInclude' },
                    {'i18nCaption':'wizardD', 'url': '/x21aAppWar/patrones/wizard_jstlImport' },
                    {'i18nCaption':'wizardE', 'url': '/x21aAppWar/patrones/wizard_dinamico' }
                ]
            },
            'table' : {
                //Literal
                'i18nCaption' : 'table',
                //Elementos (url)
                'configurable' : { 'i18nCaption' : 'tableConfigurable' },
                'double' : { 'i18nCaption' : 'tableDouble' },
                'doubleMultipk' : { 'i18nCaption' : 'tableDoubleMultipk' },
                'multipk' : { 'i18nCaption' : 'tableMultipk' },
                'masterDetail' : { 'i18nCaption' : 'tableMasterDetail' },
                'tableMasterDialog' : { 'i18nCaption' : 'tableMasterDialog' },
                'tableDialog' : { 'i18nCaption' : 'tableDialog' },
                'dynamicColumns' : { 'i18nCaption' : 'tableDynamicColumns' },
                'subLevel':[
                    {'i18nCaption': 'tableConfigurable', 'url': '/x21aAppWar/table/configurable' },
                    {'i18nCaption': 'tableDouble', 'url': '/x21aAppWar/table/double' },
                    {'i18nCaption': 'tableDoubleMultipk', 'url': '/x21aAppWar/table/multipk/double' },
                    {'i18nCaption': 'tableMultipk', 'url': '/x21aAppWar/table/multipk' },
                    {'i18nCaption': 'tableMasterDetail', 'url': '/x21aAppWar/table/masterDetail' },
                    {'i18nCaption': 'tableMasterDialog', 'url': '/x21aAppWar/table/masterDialog' },
                    {'i18nCaption': 'tableDialog', 'url': '/x21aAppWar/table/tableDialog' },
                    {'i18nCaption': 'tableDynamicColumns', 'url': '/x21aAppWar/table/dynamicColumns' }
                ]
            },
            'tableLegacy' : {
                //Literal
                'i18nCaption' : 'tableLegacy',
                //Elementos (url)
                'filtroSimple' : { 'i18nCaption' : 'filtroSimple' },
                'formEditAutogenerated' : { 'i18nCaption' : 'formEditAutogenerated' },
                'formEdit' : { 'i18nCaption' : 'formEdit' },
                'formEditMultiselection' : { 'i18nCaption' : 'formEditMultiselection' },
                'inlineEditExcelMode' : { 'i18nCaption' : 'inlineEditExcelMode' },
                'inlineEdit' : { 'i18nCaption' : 'inlineEdit' },
                'inlineEditDynamicColumns' : { 'i18nCaption' : 'inlineEditDynamicColumns' },
                'inlineEditMultiselection' : { 'i18nCaption' : 'inlineEditMultiselection' },
                //"grouping" : { "i18nCaption" : "grouping" },
                'masterDetail' : { 'i18nCaption' : 'masterDetail' },
                'tableLoadOnStartUp' : { 'i18nCaption' : 'tableLoadOnStartUp' },
                'dialog' : { 'i18nCaption' : 'tableDialog' },
                'tableRadiobutton' : { 'i18nCaption' : 'tableRadiobutton' },
                //"tabs" : { "i18nCaption" : "tableTabs" }
                'subLevel':[
                    {'i18nCaption': 'filtroSimple', 'url': '/x21aAppWar/tableLegacy/filtroSimple' },
                    {'i18nCaption': 'formEditAutogenerated', 'url': '/x21aAppWar/tableLegacy/formEditAutogenerated' },
                    {'i18nCaption': 'formEdit', 'url': '/x21aAppWar/tableLegacy/formEdit' },
                    {'i18nCaption': 'formEditMultiselection', 'url': '/x21aAppWar/tableLegacy/formEditMultiselection' },
                    {'i18nCaption': 'inlineEditExcelMode', 'url': '/x21aAppWar/tableLegacy/inlineEditExcelMode' },
                    {'i18nCaption': 'inlineEdit', 'url': '/x21aAppWar/tableLegacy/inlineEdit' },
                    {'i18nCaption': 'inlineEditDynamicColumns', 'url': '/x21aAppWar/tableLegacy/inlineEditDynamicColumns' },
                    {'i18nCaption': 'inlineEditMultiselection', 'url': '/x21aAppWar/tableLegacy/inlineEditMultiselection' },
                    //{"i18nCaption": "grouping", "url": "/x21aAppWar/tableLegacy/grouping" },
                    {'i18nCaption': 'masterDetail', 'url': '/x21aAppWar/tableLegacy/masterDetail' },
                    {'i18nCaption': 'tableLoadOnStartUp', 'url': '/x21aAppWar/tableLegacy/tableLoadOnStartUp' },
                    {'i18nCaption': 'tableDialog', 'url': '/x21aAppWar/tableLegacy/dialog' },
                    {'i18nCaption': 'tableRadiobutton', 'url': '/x21aAppWar/tableLegacy/tableRadiobutton' }
                    //,{"i18nCaption": "tableTabs", "url": "/x21aAppWar/tableLegacy/tabs" }
                ]
            },
            'bootstrap' : {
                //Literal
                'i18nCaption' : 'bootstrap',
                //Elementos (url)
                'stackedHorizontal' : { 'i18nCaption' : 'stackedHorizontal' },
                'mobileDesktop' : { 'i18nCaption' : 'mobileDesktop' },
                'mobileTabletDesktop' : { 'i18nCaption' : 'mobileTabletDesktop' },
                'exampleForm' : { 'i18nCaption' : 'exampleForm' },
                'subLevel':[
                    {'i18nCaption': 'stackedHorizontal', 'url': '/x21aAppWar/bootstrap/stackedHorizontal' },
                    {'i18nCaption': 'mobileDesktop', 'url': '/x21aAppWar/bootstrap/mobileDesktop' },
                    {'i18nCaption': 'mobileTabletDesktop', 'url': '/x21aAppWar/bootstrap/mobileTabletDesktop' },
                    {'i18nCaption': 'exampleForm', 'url': '/x21aAppWar/bootstrap/exampleForm' }
                ]
            },
            'styleGuide' : {
                //Literal
                'i18nCaption' : 'styleGuide'
            },
            'experimental' : {
                //Literal
                'i18nCaption' : 'experimental'
                //Elementos (url)

            },
            'integracion' : {
                //Literal
                'i18nCaption' : 'integracion',
                //Elementos (url)
                'geoEuskadi' : {'i18nCaption' : 'geoEuskadi' },
                //"z-index" : {"i18nCaption" : "z-index" },
                //"nora" : {"i18nCaption" : "nora" },
                'tiny' : {'i18nCaption' : 'tiny' },
                'webdav' : {'i18nCaption' : 'webdav' },
                'cache' : {'i18nCaption' : 'cache' },
                //Submenu
                'subLevel':[
                    {'i18nCaption':'geoEuskadi', 'url': '/x21aAppWar/integracion/geoEuskadi' },
                    //{"i18nCaption":"z-index", "url": "/x21aAppWar/integracion/z-index" },
                    //{"i18nCaption":"nora", "url": "/x21aAppWar/integracion/nora" },
                    {'i18nCaption':'tiny', 'url': '/x21aAppWar/integracion/tiny' },
                    {'i18nCaption':'webdav', 'url': '/x21aAppWar/integracion/webdav' },
                    {'i18nCaption':'cache', 'url': '/x21aAppWar/integracion/cache/view' }
                ]
            },
            'uda' : {
                //Literal
                'i18nCaption' : 'uda'
            }
        }
    });
    //idioma
    jQuery('#x21aApp_language').rup_language({languages: jQuery.rup.AVAILABLE_LANGS_ARRAY});
    
    //NAVBAR Menu
    if($('#navbarResponsive').length > 0){
        $('#navbarResponsive').rup_navbar({
            sticky:false
        }).find('a:not([href^=#])').on('click', function(){
            $('.contenedor').removeClass('show');
        });
    }

    window.initRupI18nPromise.then(function() {
        jQuery.extend(true, jQuery.rup.i18n.base.rup_combo, { blankNotDefined : '----' });
    });

    //pie
    jQuery('.footer [title]').rup_tooltip();
    
    //Evitar CABECERA y PIE en PORTAL
    if (jQuery.rup_utils.aplicatioInPortal()){
        jQuery('header').remove();
        jQuery('footer').remove();
    }

    // Gestión de errores genérica en frontend
    window.onerror = function(message, uri, line) {
        var fullMessage = location.href + '\n' + uri + '\n' + line;
        console.error(fullMessage);
        document.querySelector('.contenedor').classList.add('show');
        return false;
    }

    // Tiempo excesivo de carga
    setTimeout(function(){
        if(!$('.contenedor').hasClass('show')){
            console.warn('Atención, demasiado tiempo esperando para cargar la pantalla...');
            document.querySelector('.contenedor').classList.add('show');
        }
    }, 5000);
});