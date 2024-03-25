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
jQuery(function($){
    $('#idioma').rup_language();
    
    $('#menu-horizontal').rup_menu({
        display: 'horizontal',
        menu: [
            {'i18nCaption':'inicio', 'url': '' },
            {'i18nCaption':'patrones', 'submenu':[
                {'i18nCaption':'all', 'url': 'patrones/all' },  
                {'i18nCaption':'autocomplete', 'url': 'patrones/autocomplete' },
                {'i18nCaption':'toolbar', 'url': 'patrones/toolbar' },
                {'i18nCaption':'combo', 'submenu':[
                    {'i18nCaption':'comboSimple', 'url': 'patrones/comboSimple' },
                    {'i18nCaption':'comboEnlazadoSimple', 'url': 'patrones/comboEnlazadoSimple' },
                    {'i18nCaption':'comboEnlazadoMulti', 'url': 'patrones/comboEnlazadoMultiple' }
                ]},
                {'i18nCaption':'dialog', 'url': 'patrones/dialog' },
                {'i18nCaption':'date', 'url': 'patrones/date' },
                {'i18nCaption':'feedback', 'url': 'patrones/feedback' },
                {'i18nCaption':'time', 'url': 'patrones/time' },
                {'i18nCaption':'message', 'url': 'patrones/message' },
                {'i18nCaption':'menu', 'submenu':[
                    {'i18nCaption':'menuHorizontal', 'url': 'patrones/menu' },
                    {'i18nCaption':'menuVertical', 'url': 'patrones/menuVertical' },
                    {'i18nCaption':'menuMixto', 'url': 'patrones/menuMixto' }
                ]}, 
                {'i18nCaption':'tabs', 'url': 'patrones/tabs' },
                {'i18nCaption':'grid', 'url': 'patrones/grid' },
                {'i18nCaption':'tooltip', 'url': 'patrones/tooltip' }
            ]}
        ]
    });
    
    $('#breadCrumb').rup_breadCrumb({
        breadCrumb: {
            'integracion' : {
                //Literal
                'i18nCaption' : 'integracion',
                //Elementos (url)
                'z-index' : {'i18nCaption' : 'z-index' },
                'nora' : {'i18nCaption' : 'nora' },
                'tiny' : {'i18nCaption' : 'tiny' },
                //Submenu
                'subLevel':[
                    {'i18nCaption':'z-index', 'url': '/x21aAppWar/integracion/z-index' },
                    {'i18nCaption':'nora', 'url': '/x21aAppWar/integracion/nora' },
                    {'i18nCaption':'tiny', 'url': '/x21aAppWar/integracion/tiny' }
                ]
            }
        }
    });
    
    $('#idioma_2').rup_language();
    
    $('#menu-vertical').rup_menu({
        display: 'vertical',
        verticalWidth: '13em',
        menu: [
            {'i18nCaption':'patrones', 'submenu':[
                {'i18nCaption':'all', 'url': 'patrones/all' },  
                {'i18nCaption':'autocomplete', 'url': 'patrones/autocomplete' },
                {'i18nCaption':'toolbar', 'url': 'patrones/toolbar' },
                {'i18nCaption':'combo', 'submenu':[
                    {'i18nCaption':'comboSimple', 'url': 'patrones/comboSimple' },
                    {'i18nCaption':'comboEnlazadoSimple', 'url': 'patrones/comboEnlazadoSimple' },
                    {'i18nCaption':'comboEnlazadoMulti', 'url': 'patrones/comboEnlazadoMultiple' }
                ]},
                {'i18nCaption':'dialog', 'url': 'patrones/dialog' },
                {'i18nCaption':'date', 'url': 'patrones/date' },
                {'i18nCaption':'feedback', 'url': 'patrones/feedback' },
                {'i18nCaption':'time', 'url': 'patrones/time' },
                {'i18nCaption':'message', 'url': 'patrones/message' },
                {'i18nCaption':'menu', 'submenu':[
                    {'i18nCaption':'menuHorizontal', 'url': 'patrones/menu' },
                    {'i18nCaption':'menuVertical', 'url': 'patrones/menuVertical' },
                    {'i18nCaption':'menuMixto', 'url': 'patrones/menuMixto' }
                ]}, 
                {'i18nCaption':'tabs', 'url': 'patrones/tabs' },
                {'i18nCaption':'grid', 'url': 'patrones/grid' },
                {'i18nCaption':'tooltip', 'url': 'patrones/tooltip' }
            ]}
        ]
    });
    
    $('#breadCrumb_2').rup_breadCrumb({
        breadCrumb: {
            'integracion' : {
                //Literal
                'i18nCaption' : 'integracion',
                //Elementos (url)
                'z-index' : {'i18nCaption' : 'z-index' },
                'nora' : {'i18nCaption' : 'nora' },
                'tiny' : {'i18nCaption' : 'tiny' },
                //Submenu
                'subLevel':[
                    {'i18nCaption':'z-index', 'url': '/x21aAppWar/integracion/z-index' },
                    {'i18nCaption':'nora', 'url': '/x21aAppWar/integracion/nora' },
                    {'i18nCaption':'tiny', 'url': '/x21aAppWar/integracion/tiny' }
                ]
            }
        }
    });
    
    $('#date').rup_date({
        changeMonth : false,
        changeYear	: false,
        numberOfMonths : 1
    });
    
    $('#time').rup_time({});
    
    $('#combo').rup_select({
        url: '../patrones/comboEnlazadoSimple/remoteEnlazadoProvincia',
        sourceParam: {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
        selected: 3
    });
    $('#autocomplete').rup_select({
        data: [
			{id: 'asp', text: 'asp'},
			{id: 'c', text: 'c'},
			{id: 'c++', text: 'c++'},
			{id: 'coldfusion', text: 'coldfusion'},
			{id: 'groovy', text: 'groovy'},
			{id: 'haskell', text: 'haskell'},
			{id: 'java', text: 'java'},
			{id: 'javascript', text: 'javascript'},
			{id: 'perl', text: 'perl'},
			{id: 'php', text: 'php'},
			{id: 'python', text: 'python'},
			{id: 'ruby', text: 'ruby'},
			{id: 'scala', text: 'scala'}
		]
    });
    
    $('[title]').rup_tooltip(); 
});