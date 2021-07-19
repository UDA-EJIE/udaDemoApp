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
jQuery(document).ready(function () {
    
    $('.rup-button').rup_button();

    $('#dropdownHtmlListButton').rup_button({
        dropdown:{
            dropdownListId:'dropdownHtmlList'
        }
    });
    
    $('#autocompleteDefault').rup_autocomplete({
        source : ['asp', 'c', 'c++', 'coldfusion', 'groovy', 'haskell', 'java', 'javascript', 'perl', 'php', 'python', 'ruby', 'scala'],
        defaultValue : '',
        contains : false
    });
    
    $('#autocompleteMaterialized').rup_autocomplete({
        source : ['asp', 'c', 'c++', 'coldfusion', 'groovy', 'haskell', 'java', 'javascript', 'perl', 'php', 'python', 'ruby', 'scala'],
        defaultValue : '',
        contains : false
    });

    $('#rupCombo').rup_combo({
        loadFromSelect: true
    });
    
    $('#rupComboMaterial').rup_combo({
        loadFromSelect: true,
        customClasses: ['select-material']
    });
    
    $('#fechaMaterial').rup_date({
        labelMaskId : 'fecha-mask',
        showButtonPanel : true,
        showOtherMonths : true,
        noWeekend : true
    });
    
    $('#fechaMaterialPlaceholder').rup_date({
        placeholderMask : true,
        showButtonPanel : true,
        showOtherMonths : true,
        noWeekend : true
    });


    document.querySelector('.contenedor').classList.add('show');
});