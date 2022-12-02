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
var d1 = $.Deferred();
var d2 = $.Deferred();

var valorLeft = {
    action: './filter',
    filterForm: 'listFilterFormLeft',
    feedback: 'rup-list-left-feedback',
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
        var ejieval = item.find($.rup_utils.escapeId('#ejie_value_' + json.id));
        if (json.ejie == 1) {
            ejieval.text($.rup.i18n.app.comun.si);
        } else {
            ejieval.text($.rup.i18n.app.comun.no);
        }
    },
    load: function () {
        d1.resolve();
    }
};

var valorRight = {
    action: './filter',
    filterForm: 'listFilterFormRight',
    feedback: 'rup-list-right-feedback',
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
        var ejieval = item.find($.rup_utils.escapeId('#ejie_value_' + json.id));
        if (json.ejie == 1) {
            ejieval.text($.rup.i18n.app.comun.si);
        } else {
            ejieval.text($.rup.i18n.app.comun.no);
        }
    },
    load: function () {
        d2.resolve();
    }
};

function functionListConfigButton() {
    if ($('#listConfigLeftShow')[0].checked) {
        valorLeft.show = {};
        valorLeft.show.animation = $('#listConfigLeftShowAnimation').val();
        valorLeft.show.delay = $('#listConfigLeftShowDelay').val();
    } else {
        valorLeft.show = {};
        valorLeft.show.animation = 'drop';
        valorLeft.show.delay = 200;
    }

    if ($('#listConfigRightShow')[0].checked) {
        valorRight.show = {};
        valorRight.show.animation = $('#listConfigRightShowAnimation').val();
        valorRight.show.delay = $('#listConfigRightShowDelay').val();
    } else {
        valorRight.show = {};
        valorRight.show.animation = 'drop';
        valorRight.show.delay = 200;
    }

    if ($('#listConfigLeftHide')[0].checked) {
        valorLeft.hide = {};
        valorLeft.hide.animation = $('#listConfigLeftHideAnimation').val();
        valorLeft.hide.delay = $('#listConfigLeftHideDelay').val();
    } else {
        valorLeft.hide = {};
        valorLeft.hide.animation = 'drop';
        valorLeft.hide.delay = 200;
    }

    if ($('#listConfigRightHide')[0].checked) {
        valorRight.hide = {};
        valorRight.hide.animation = $('#listConfigRightHideAnimation').val();
        valorRight.hide.delay = $('#listConfigRightHideDelay').val();
    } else {
        valorRight.hide = {};
        valorRight.hide.animation = 'drop';
        valorRight.hide.delay = 200;
    }

    localStorage.setItem('isListConfigLeftSelectNo', $('#listConfigLeftSelectNo').is(':checked'));
    localStorage.setItem('isListConfigLeftSelectSimple', $('#listConfigLeftSelectSimple').is(':checked'));
    localStorage.setItem('isListConfigLeftSelectMultiple', $('#listConfigLeftSelectMultiple').is(':checked'));
    localStorage.setItem('isMultiSortLeft', $('#listConfigLeftMultiSort')[0].checked);
    localStorage.setItem('isScrollListLeft', $('#listConfigLeftScrollList')[0].checked);
    localStorage.setItem('isHeaderStickyLeft', $('#listConfigLeftHeaderSticky')[0].checked);
    localStorage.setItem('isMultiFilterLeft', $('#listConfigLeftMultiFilter')[0].checked);
    localStorage.setItem('isSuperSelectLeft', $('#listConfigLeftSuperSelect')[0].checked);

    localStorage.setItem('printLeft', $('#listConfigLeftPrint').val());

    localStorage.setItem('showLeft', JSON.stringify(valorLeft.show));
    localStorage.setItem('hideLeft', JSON.stringify(valorLeft.hide));

    localStorage.setItem('isListConfigRightSelectNo', $('#listConfigRightSelectNo').is(':checked'));
    localStorage.setItem('isListConfigRightSelectSimple', $('#listConfigRightSelectSimple').is(':checked'));
    localStorage.setItem('isListConfigRightSelectMultiple', $('#listConfigRightSelectMultiple').is(':checked'));
    localStorage.setItem('isMultiSortRight', $('#listConfigRightMultiSort')[0].checked);
    localStorage.setItem('isScrollListRight', $('#listConfigRightScrollList')[0].checked);
    localStorage.setItem('isHeaderStickyRight', $('#listConfigRightHeaderSticky')[0].checked);
    localStorage.setItem('isMultiFilterRight', $('#listConfigRightMultiFilter')[0].checked);
    localStorage.setItem('isSuperSelectRight', $('#listConfigRightSuperSelect')[0].checked);

    localStorage.setItem('printRight', $('#listConfigRightPrint').val());

    localStorage.setItem('showRight', JSON.stringify(valorRight.show));
    localStorage.setItem('hideRight', JSON.stringify(valorRight.hide));

    location.reload(true);
}


jQuery(function ($) {

    $.when(d1, d2).then(function () {
        $('.contenedor').addClass('show');
    });

    if (localStorage.length > 0) {
        if (localStorage.isListConfigLeftSelectNo) {
            if (localStorage.isListConfigLeftSelectNo == 'true') {
                $('#listConfigLeftSelectNo')[0].checked = true;
                valorLeft.isListConfigLeftSelectNo = true;
            } else if (localStorage.isListConfigLeftSelectNo == 'false') {
                $('#listConfigLeftSelectNo')[0].checked = false;
                valorLeft.isListConfigLeftSelectNo = false;
            }
        }
        if (localStorage.isListConfigLeftSelectSimple) {
            if (localStorage.isListConfigLeftSelectSimple == 'true') {
                $('#listConfigLeftSelectSimple')[0].checked = true;
                valorLeft.isListConfigLeftSelectSimple = true;
            } else if (localStorage.isListConfigLeftSelectSimple == 'false') {
                $('#listConfigLeftSelectSimple')[0].checked = false;
                valorLeft.isListConfigLeftSelectSimple = false;
            }
        }
        if (localStorage.isListConfigLeftSelectMultiple) {
            if (localStorage.isListConfigLeftSelectMultiple == 'true') {
                $('#listConfigLeftSelectMultiple')[0].checked = true;
                valorLeft.isListConfigLeftSelectMultiple = true;
            } else if (localStorage.isListConfigLeftSelectMultiple == 'false') {
                $('#listConfigLeftSelectMultiple')[0].checked = false;
                valorLeft.isListConfigLeftSelectMultiple = false;
            }
        }
        if (localStorage.isMultiSortLeft) {
            if (localStorage.isMultiSortLeft == 'true') {
                $('#listConfigLeftMultiSort')[0].checked = true;
                valorLeft.isMultiSort = true;
            } else if (localStorage.isMultiSortLeft == 'false') {
                $('#listConfigLeftMultiSort')[0].checked = false;
                valorLeft.isMultiSort = false;
            }
        }
        if (localStorage.isScrollListLeft) {
            if (localStorage.isScrollListLeft == 'true') {
                $('#listConfigLeftScrollList')[0].checked = true;
                valorLeft.isScrollList = true;
            } else if (localStorage.isScrollListLeft == 'false') {
                $('#listConfigLeftScrollList')[0].checked = false;
                valorLeft.isScrollList = false;
            }
        }
        if (localStorage.isHeaderStickyLeft) {
            if (localStorage.isHeaderStickyLeft == 'true') {
                $('#listConfigLeftHeaderSticky')[0].checked = true;
                valorLeft.isHeaderSticky = true;
            } else if (localStorage.isHeaderStickyLeft == 'false') {
                $('#listConfigLeftHeaderSticky')[0].checked = false;
                valorLeft.isHeaderSticky = false;
            }
        }
        if (localStorage.isMultiFilterLeft) {
            if (localStorage.isMultiFilterLeft == 'true') {
                $('#listConfigLeftMultiFilter')[0].checked = true;
                valorLeft.isMultiFilter = true;
            } else if (localStorage.isMultiFilterLeft == 'false') {
                $('#listConfigLeftMultiFilter')[0].checked = false;
                valorLeft.isMultiFilter = false;
            }
        }

        if (localStorage.isSuperSelectLeft) {
            if (localStorage.isSuperSelectLeft == 'true') {
                $('#listConfigLeftSuperSelect')[0].checked = true;
                valorLeft.isSuperSelect = true;
            } else if (localStorage.isMultiFilterLeft == 'false') {
                $('#listConfigLeftSuperSelect')[0].checked = false;
                valorLeft.isSuperSelect = false;
            }
        }

        if (localStorage.printLeft) {
            $('#listConfigLeftPrint').val(localStorage.printLeft);
            valorLeft.print = localStorage.printLeft;
        }

        if (localStorage.showLeft) {
            valorLeft.show = JSON.parse(localStorage.showLeft);
            if (valorLeft.show.animation != 'drop') {
                $('#listConfigLeftShow')[0].checked = true;
                $('#listConfigLeftShowAnimation')[0].value = valorLeft.show.animation;
            }
            if (valorLeft.show.delay != '200') {
                $('#listConfigLeftShow')[0].checked = true;
                $('#listConfigLeftShowDelay').val(valorLeft.show.delay);
            }
        }

        if (localStorage.hideLeft) {
            valorLeft.hide = JSON.parse(localStorage.hideLeft);
            if (valorLeft.hide.animation != 'drop') {
                $('#listConfigLeftHide')[0].checked = true;
                $('#listConfigLeftHideAnimation')[0].value = valorLeft.hide.animation;
            }
            if (valorLeft.hide.delay != '200') {
                $('#listConfigLeftHide')[0].checked = true;
                $('#listConfigLeftHideDelay').val(valorLeft.hide.delay);
            }
        }

        if($('#listConfigLeftSelectMultiple').is(':checked')){
            $('#listConfigLeftSuperSelect').attr('disabled',false);
        } else {
            $('#listConfigLeftSuperSelect').attr('disabled',true);
            $('#listConfigLeftSuperSelect')[0].checked = false;
        }

        // right
        if (localStorage.isListConfigRightSelectNo) {
            if (localStorage.isListConfigRightSelectNo == 'true') {
                $('#listConfigRightSelectNo')[0].checked = true;
                valorRight.isListConfigRightSelectNo = true;
            } else if (localStorage.isListConfigRightSelectNo == 'false') {
                $('#listConfigRightSelectNo')[0].checked = false;
                valorRight.isListConfigRightSelectNo = false;
            }
        }
        if (localStorage.isListConfigRightSelectSimple) {
            if (localStorage.isListConfigRightSelectSimple == 'true') {
                $('#listConfigRightSelectSimple')[0].checked = true;
                valorRight.isListConfigRightSelectSimple = true;
            } else if (localStorage.isListConfigRightSelectSimple == 'false') {
                $('#listConfigRightSelectSimple')[0].checked = false;
                valorRight.isListConfigRightSelectSimple = false;
            }
        }
        if (localStorage.isListConfigRightSelectMultiple) {
            if (localStorage.isListConfigRightSelectMultiple == 'true') {
                $('#listConfigRightSelectMultiple')[0].checked = true;
                valorRight.isListConfigRightSelectMultiple = true;
            } else if (localStorage.isListConfigRightSelectMultiple == 'false') {
                $('#listConfigRightSelectMultiple')[0].checked = false;
                valorRight.isListConfigRightSelectMultiple = false;
            }
        }
        if (localStorage.isMultiSortRight) {
            if (localStorage.isMultiSortRight == 'true') {
                $('#listConfigRightMultiSort')[0].checked = true;
                valorRight.isMultiSort = true;
            } else if (localStorage.isMultiSortRight == 'false') {
                $('#listConfigRightMultiSort')[0].checked = false;
                valorRight.isMultiSort = false;
            }
        }
        if (localStorage.isScrollListRight) {
            if (localStorage.isScrollListRight == 'true') {
                $('#listConfigRightScrollList')[0].checked = true;
                valorRight.isScrollList = true;
            } else if (localStorage.isScrollListRight == 'false') {
                $('#listConfigRightScrollList')[0].checked = false;
                valorRight.isScrollList = false;
            }
        }
        if (localStorage.isHeaderStickyRight) {
            if (localStorage.isHeaderStickyRight == 'true') {
                $('#listConfigRightHeaderSticky')[0].checked = true;
                valorRight.isHeaderSticky = true;
            } else if (localStorage.isHeaderStickyRight == 'false') {
                $('#listConfigRightHeaderSticky')[0].checked = false;
                valorRight.isHeaderSticky = false;
            }
        }
        if (localStorage.isMultiFilterRight) {
            if (localStorage.isMultiFilterRight == 'true') {
                $('#listConfigRightMultiFilter')[0].checked = true;
                valorRight.isMultiFilter = true;
            } else if (localStorage.isMultiFilterRight == 'false') {
                $('#listConfigRightMultiFilter')[0].checked = false;
                valorRight.isMultiFilter = false;
            }
        }

        if (localStorage.isSuperSelectRight) {
            if (localStorage.isSuperSelectRight == 'true') {
                $('#listConfigRightSuperSelect')[0].checked = true;
                valorRight.isSuperSelect = true;
            } else if (localStorage.isMultiFilterRight == 'false') {
                $('#listConfigRightSuperSelect')[0].checked = false;
                valorRight.isSuperSelect = false;
            }
        }

        if (localStorage.printRight) {
            $('#listConfigRightPrint').val(localStorage.printRight);
            valorRight.print = localStorage.printRight;
        }

        if (localStorage.showRight) {
            valorRight.show = JSON.parse(localStorage.showRight);
            if (valorRight.show.animation != 'drop') {
                $('#listConfigRightShow')[0].checked = true;
                $('#listConfigRightShowAnimation')[0].value = valorRight.show.animation;
            }
            if (valorRight.show.delay != '200') {
                $('#listConfigRightShow')[0].checked = true;
                $('#listConfigRightShowDelay').val(valorRight.show.delay);
            }
        }

        if (localStorage.hideRight) {
            valorRight.hide = JSON.parse(localStorage.hideRight);
            if (valorRight.hide.animation != 'drop') {
                $('#listConfigRightHide')[0].checked = true;
                $('#listConfigRightHideAnimation')[0].value = valorRight.hide.animation;
            }
            if (valorRight.hide.delay != '200') {
                $('#listConfigRightHide')[0].checked = true;
                $('#listConfigRightHideDelay').val(valorRight.hide.delay);
            }
        }

        if($('#listConfigRightSelectMultiple').is(':checked')){
            $('#listConfigRightSuperSelect').attr('disabled',false);
        } else {
            $('#listConfigRightSuperSelect').attr('disabled',true);
            $('#listConfigRightSuperSelect')[0].checked = false;
        }
    }

    if($('input[name="listConfigLeftSelect"][value!="0"]:checked').length>0){
        valorLeft.selectable = {
            multi: $('#listConfigLeftSelectMultiple').is(':checked'),
            selector: '.list-item'
        }
    } else {
        delete valorLeft.selectable;
    }

    if($('input[name="listConfigRightSelect"][value!="0"]:checked').length>0){
        valorRight.selectable = {
            multi: $('#listConfigRightSelectMultiple').is(':checked'),
            selector: '.list-item'
        }
    } else {
        delete valorRight.selectable;
    }

    if ($('#listConfigLeftShow')[0].checked) {
        $('#listConfigLeftShowAnimation')[0].disabled = false;
        $('#listConfigLeftShowDelay')[0].disabled = false;
    } else {
        $('#listConfigLeftShowAnimation')[0].disabled = true;
        $('#listConfigLeftShowDelay')[0].disabled = true;
    }

    if ($('#listConfigLeftHide')[0].checked) {
        $('#listConfigLeftHideAnimation')[0].disabled = false;
        $('#listConfigLeftHideDelay')[0].disabled = false;
    } else {
        $('#listConfigLeftHideAnimation')[0].disabled = true;
        $('#listConfigLeftHideDelay')[0].disabled = true;
    }

    if ($('#listConfigRightShow')[0].checked) {
        $('#listConfigRightShowAnimation')[0].disabled = false;
        $('#listConfigRightShowDelay')[0].disabled = false;
    } else {
        $('#listConfigRightShowAnimation')[0].disabled = true;
        $('#listConfigRightShowDelay')[0].disabled = true;
    }

    if ($('#listConfigRightHide')[0].checked) {
        $('#listConfigRightHideAnimation')[0].disabled = false;
        $('#listConfigRightHideDelay')[0].disabled = false;
    } else {
        $('#listConfigRightHideAnimation')[0].disabled = true;
        $('#listConfigRightHideDelay')[0].disabled = true;
    }

    //Generamos el componente
    $('#rup-list-left').rup_list(valorLeft);
    $('#rup-list-left').on('initComplete', function () {
        $('#rup-list-left').rup_list('filter');
    });

    $('#rup-list-right').rup_list(valorRight);
    $('#rup-list-right').on('initComplete', function () {
        $('#rup-list-right').rup_list('filter');
    });


    $('#listFilterFormLeft').find('#listFilterLimpiar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#listFilterFormLeft').rup_form("resetForm");
        $('#rup-list-left').rup_list('filter');
    });
    $('#listFilterFormLeft').find('#listFilterAceptar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#rup-list-left').rup_list('filter');
    });

    //Preparamos los eventos de la pantalla
    $('#listFilterFormRight').find('#listFilterLimpiar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#listFilterFormRight').rup_form("resetForm");
        $('#rup-list-right').rup_list('filter');
    });
    $('#listFilterFormRight').find('#listFilterAceptar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#rup-list-right').rup_list('filter');
    });

    $('#listConfigLeftShow').on('click', function(e){
        if ($(e.target)[0].checked) {
            $('#listConfigLeftShowAnimation')[0].disabled = false;
            $('#listConfigLeftShowDelay')[0].disabled = false;
        } else {
            $('#listConfigLeftShowAnimation')[0].disabled = true;
            $('#listConfigLeftShowDelay')[0].disabled = true;
        }
    });

    $('#listConfigRightShow').on('click', function(e){
        if ($(e.target)[0].checked) {
            $('#listConfigRightShowAnimation')[0].disabled = false;
            $('#listConfigRightShowDelay')[0].disabled = false;
        } else {
            $('#listConfigRightShowAnimation')[0].disabled = true;
            $('#listConfigRightShowDelay')[0].disabled = true;
        }
    });


    $('#listConfigLeftHide').on('click', function(e){
        if ($(e.target)[0].checked) {
            $('#listConfigLeftHideAnimation')[0].disabled = false;
            $('#listConfigLeftHideDelay')[0].disabled = false;
        } else {
            $('#listConfigLeftHideAnimation')[0].disabled = true;
            $('#listConfigLeftHideDelay')[0].disabled = true;
        }
    });

    $('#listConfigRightHide').on('click', function(e){
        if ($(e.target)[0].checked) {
            $('#listConfigRightHideAnimation')[0].disabled = false;
            $('#listConfigRightHideDelay')[0].disabled = false;
        } else {
            $('#listConfigRightHideAnimation')[0].disabled = true;
            $('#listConfigRightHideDelay')[0].disabled = true;
        }
    });

    $('#listConfigLeftButton, #listConfigRightButton').on('click', function(e){
        e.preventDefault();
        functionListConfigButton();
    });

    $('input[name="listConfigLeftSelect"]').on('change', function(){
        if($('#listConfigLeftSelectMultiple').is(':checked')){
            $('#listConfigLeftSuperSelect').attr('disabled',false);
        } else {
            $('#listConfigLeftSuperSelect').attr('disabled',true);
            $('#listConfigLeftSuperSelect')[0].checked = false;
        }
    });

    $('input[name="listConfigRightSelect"]').on('change', function(){
        if($('#listConfigRightSelectMultiple').is(':checked')){
            $('#listConfigRightSuperSelect').attr('disabled',false);
        } else {
            $('#listConfigRightSuperSelect').attr('disabled',true);
            $('#listConfigRightSuperSelect')[0].checked = false;
        }
    });
});