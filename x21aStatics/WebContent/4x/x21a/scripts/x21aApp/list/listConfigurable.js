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
var valor = {
    action: './filter',
    filterForm: 'listFilterForm',
    feedback: 'rup-list-feedback',
    visiblePages: 3, //Mínimo 3
    key: 'id',
    selectable: {
        multi: true,
        selector: '.list-item'
    },
    sidx: {
        source: [{
            value: 'id',
            i18nCaption: 'Id'
        }, {
            value: 'nombre',
            i18nCaption: 'Nombre'
        }, {
            value: 'apellido1',
            i18nCaption: 'Apellido1'
        }],
        value: 'id'
    },
    rowNum: {
        source: [{
            value: '5',
            i18nCaption: 'Cinco'
        }, {
            value: '10',
            i18nCaption: 'Diez'
        }, {
            value: '20',
            i18nCaption: 'Veinte'
        }],
        value: '5'
    },
    modElement: function (ev, item, json) {
        var ejieval = item.find('#ejie_value_' + json.id);
        if (json.ejie == 1) {
            ejieval.text($.rup.i18n.app.comun.si);
        } else {
            ejieval.text($.rup.i18n.app.comun.no);
        }
    },
    load: function () {
        $('.contenedor').addClass('show');
    }
};

function setFunctionOnBtn() {
    //Preparamos los eventos de la pantalla
    $('#listFilterLimpiar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#listFilterForm').rup_form("resetForm");
        $('#rup-list').rup_list('filter');
    });
    $('#listFilterAceptar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#rup-list').rup_list('filter');
    });
}

function functionListConfigButton() {
    if ($('#listConfigShow')[0].checked) {
        valor.show = {};
        valor.show.animation = $('#listConfigShowAnimation').val();
        valor.show.delay = $('#listConfigShowDelay').val();
    } else {
        valor.show = {};
        valor.show.animation = 'drop';
        valor.show.delay = 200;
    }

    if ($('#listConfigHide')[0].checked) {
        valor.hide = {};
        valor.hide.animation = $('#listConfigHideAnimation').val();
        valor.hide.delay = $('#listConfigHideDelay').val();
    } else {
        valor.hide = {};
        valor.hide.animation = 'drop';
        valor.hide.delay = 200;
    }

    localStorage.setItem('isListConfigSelectNo', $('#listConfigSelectNo').is(':checked'));
    localStorage.setItem('isListConfigSelectSimple', $('#listConfigSelectSimple').is(':checked'));
    localStorage.setItem('isListConfigSelectMultiple', $('#listConfigSelectMultiple').is(':checked'));
    localStorage.setItem('isMultiSort', $('#listConfigMultiSort')[0].checked);
    localStorage.setItem('isScrollList', $('#listConfigScrollList')[0].checked);
    localStorage.setItem('isHeaderSticky', $('#listConfigHeaderSticky')[0].checked);
    localStorage.setItem('isMultiFilter', $('#listConfigMultiFilter')[0].checked);
    localStorage.setItem('isSuperSelect', $('#listConfigSuperSelect')[0].checked);

    localStorage.setItem('print', $('#listConfigPrint').val());

    localStorage.setItem('show', JSON.stringify(valor.show));
    localStorage.setItem('hide', JSON.stringify(valor.hide));

    location.reload(true);
}


jQuery(function ($) {

    setFunctionOnBtn();

    if (localStorage.length > 0) {
        if (localStorage.isListConfigSelectNo) {
            if (localStorage.isListConfigSelectNo == 'true') {
                $('#listConfigSelectNo')[0].checked = true;
                valor.isListConfigSelectNo = true;
            } else if (localStorage.isListConfigSelectNo == 'false') {
                $('#listConfigSelectNo')[0].checked = false;
                valor.isListConfigSelectNo = false;
            }
        }
        if (localStorage.isListConfigSelectSimple) {
            if (localStorage.isListConfigSelectSimple == 'true') {
                $('#listConfigSelectSimple')[0].checked = true;
                valor.isListConfigSelectSimple = true;
            } else if (localStorage.isListConfigSelectSimple == 'false') {
                $('#listConfigSelectSimple')[0].checked = false;
                valor.isListConfigSelectSimple = false;
            }
        }
        if (localStorage.isListConfigSelectMultiple) {
            if (localStorage.isListConfigSelectMultiple == 'true') {
                $('#listConfigSelectMultiple')[0].checked = true;
                valor.isListConfigSelectMultiple = true;
            } else if (localStorage.isListConfigSelectMultiple == 'false') {
                $('#listConfigSelectMultiple')[0].checked = false;
                valor.isListConfigSelectMultiple = false;
            }
        }
        if (localStorage.isMultiSort) {
            if (localStorage.isMultiSort == 'true') {
                $('#listConfigMultiSort')[0].checked = true;
                valor.isMultiSort = true;
            } else if (localStorage.isMultiSort == 'false') {
                $('#listConfigMultiSort')[0].checked = false;
                valor.isMultiSort = false;
            }
        }
        if (localStorage.isScrollList) {
            if (localStorage.isScrollList == 'true') {
                $('#listConfigScrollList')[0].checked = true;
                valor.isScrollList = true;
            } else if (localStorage.isScrollList == 'false') {
                $('#listConfigScrollList')[0].checked = false;
                valor.isScrollList = false;
            }
        }
        if (localStorage.isHeaderSticky) {
            if (localStorage.isHeaderSticky == 'true') {
                $('#listConfigHeaderSticky')[0].checked = true;
                valor.isHeaderSticky = true;
            } else if (localStorage.isHeaderSticky == 'false') {
                $('#listConfigHeaderSticky')[0].checked = false;
                valor.isHeaderSticky = false;
            }
        }
        if (localStorage.isMultiFilter) {
            if (localStorage.isMultiFilter == 'true') {
                $('#listConfigMultiFilter')[0].checked = true;
                valor.isMultiFilter = true;
            } else if (localStorage.isMultiFilter == 'false') {
                $('#listConfigMultiFilter')[0].checked = false;
                valor.isMultiFilter = false;
            }
        }

        if (localStorage.isSuperSelect) {
            if (localStorage.isSuperSelect == 'true') {
                $('#listConfigSuperSelect')[0].checked = true;
                valor.isSuperSelect = true;
            } else if (localStorage.isMultiFilter == 'false') {
                $('#listConfigSuperSelect')[0].checked = false;
                valor.isSuperSelect = false;
            }
        }

        if (localStorage.print) {
            $('#listConfigPrint').val(localStorage.print);
            valor.print = localStorage.print;
        }

        if (localStorage.show) {
            valor.show = JSON.parse(localStorage.show);
            if (valor.show.animation != 'drop') {
                $('#listConfigShow')[0].checked = true;
                $('#listConfigShowAnimation')[0].value = valor.show.animation;
            }
            if (valor.show.delay != '200') {
                $('#listConfigShow')[0].checked = true;
                $('#listConfigShowDelay').val(valor.show.delay);
            }
        }

        if (localStorage.hide) {
            valor.hide = JSON.parse(localStorage.hide);
            if (valor.hide.animation != 'drop') {
                $('#listConfigHide')[0].checked = true;
                $('#listConfigHideAnimation')[0].value = valor.hide.animation;
            }
            if (valor.hide.delay != '200') {
                $('#listConfigHide')[0].checked = true;
                $('#listConfigHideDelay').val(valor.hide.delay);
            }
        }

        if($('#listConfigSelectMultiple').is(':checked')){
            $('#listConfigSuperSelect').attr('disabled',false);
        } else {
            $('#listConfigSuperSelect').attr('disabled',true);
            $('#listConfigSuperSelect')[0].checked = false;
        }
    }

    if($('input[name="listConfigSelect"][value!="0"]:checked').length>0){
        valor.selectable = {
            multi: $('#listConfigSelectMultiple').is(':checked'),
            selector: '.list-item'
        }
    } else {
        delete valor.selectable;
    }

    if ($('#listConfigShow')[0].checked) {
        $('#listConfigShowAnimation')[0].disabled = false;
        $('#listConfigShowDelay')[0].disabled = false;
    } else {
        $('#listConfigShowAnimation')[0].disabled = true;
        $('#listConfigShowDelay')[0].disabled = true;
    }

    if ($('#listConfigHide')[0].checked) {
        $('#listConfigHideAnimation')[0].disabled = false;
        $('#listConfigHideDelay')[0].disabled = false;
    } else {
        $('#listConfigHideAnimation')[0].disabled = true;
        $('#listConfigHideDelay')[0].disabled = true;
    }

    //Generamos el componente
    $('#rup-list').rup_list(valor);

    $('#rup-list').on('initComplete', function () {
        $('#rup-list').rup_list('filter');
    });

    $('#listConfigShow').on('click', function(e){
        if ($(e.target)[0].checked) {
            $('#listConfigShowAnimation')[0].disabled = false;
            $('#listConfigShowDelay')[0].disabled = false;
        } else {
            $('#listConfigShowAnimation')[0].disabled = true;
            $('#listConfigShowDelay')[0].disabled = true;
        }
    });

    $('#listConfigHide').on('click', function(e){
        if ($(e.target)[0].checked) {
            $('#listConfigHideAnimation')[0].disabled = false;
            $('#listConfigHideDelay')[0].disabled = false;
        } else {
            $('#listConfigHideAnimation')[0].disabled = true;
            $('#listConfigHideDelay')[0].disabled = true;
        }
    });

    $('#listConfigButton').on('click', function(e){
        e.preventDefault();
        functionListConfigButton();
    });

    $('input[name="listConfigSelect"]').on('change', function(){
        if($('#listConfigSelectMultiple').is(':checked')){
            $('#listConfigSuperSelect').attr('disabled',false);
        } else {
            $('#listConfigSuperSelect').attr('disabled',true);
            $('#listConfigSuperSelect')[0].checked = false;
        }
    });
});