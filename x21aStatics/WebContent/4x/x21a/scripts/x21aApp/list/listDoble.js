window.$ = $;

var valorLeft = {
    action: '/x21aAppWar/lista/filter',
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
        var ejieval = item.find('#ejie_value_' + json.id);
        if (json.ejie == 1) {
            ejieval.text($.rup.i18n.app.comun.si);
        } else {
            ejieval.text($.rup.i18n.app.comun.no);
        }
    },
    load: function () {}
}

var valorRight = {
        action: '/x21aAppWar/lista/filter',
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
            var ejieval = item.find('#ejie_value_' + json.id);
            if (json.ejie == 1) {
                ejieval.text($.rup.i18n.app.comun.si);
            } else {
                ejieval.text($.rup.i18n.app.comun.no);
            }
        },
        load: function () {}
    }

if (localStorage.length > 0) {
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
    
    // right
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
$('#rup-list-right').rup_list(valorRight);

$(window).load(function () {
    $('#listFilterFormLeft').find('#listFilterLimpiar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#listFilterFormLeft').find('input').val('');
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
      $('#listFilterFormRight').find('input').val('');
      $('#rup-list-right').rup_list('filter');
    });
    $('#listFilterFormRight').find('#listFilterAceptar').on('click', function (e) {
      e.stopImmediatePropagation();
      e.preventDefault();
      $('#rup-list-right').rup_list('filter');
    });


    $('#rup-list-left').rup_list('filter');
    $('#rup-list-right').rup_list('filter');
});


function functionListConfigButton (position) {
    valorLeft.isMultiSort = $('#listConfigLeftMultiSort')[0].checked;
    valorLeft.isScrollList = $('#listConfigLeftScrollList')[0].checked;
    valorLeft.isHeaderSticky = $('#listConfigLeftHeaderSticky')[0].checked;
    valorLeft.print = $('#listConfigLeftPrint').val();
    valorLeft.isMultiFilter = $('#listConfigLeftMultiFilter')[0].checked;
    valorLeft.isSuperSelect = $('#listConfigLeftSuperSelect')[0].checked;
    
    valorRight.isMultiSort = $('#listConfigRightMultiSort')[0].checked;
    valorRight.isScrollList = $('#listConfigRightScrollList')[0].checked;
    valorRight.isHeaderSticky = $('#listConfigRightHeaderSticky')[0].checked;
    valorRight.print = $('#listConfigRightPrint').val();
    valorRight.isMultiFilter = $('#listConfigRightMultiFilter')[0].checked;
    valorRight.isSuperSelect = $('#listConfigRightSuperSelect')[0].checked;

    if (valorLeft.print == '') {
        valorLeft.print = false;
    }
    
    if (valorRight.print == '') {
        valorRight.print = false;
    }

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
    
    localStorage.setItem('isMultiSortLeft', $('#listConfigLeftMultiSort')[0].checked);
    localStorage.setItem('isScrollListLeft', $('#listConfigLeftScrollList')[0].checked);
    localStorage.setItem('isHeaderStickyLeft', $('#listConfigLeftHeaderSticky')[0].checked);
    localStorage.setItem('isMultiFilterLeft', $('#listConfigLeftMultiFilter')[0].checked);
    localStorage.setItem('isSuperSelectLeft', $('#listConfigLeftSuperSelect')[0].checked);
    
    localStorage.setItem('printLeft', $('#listConfigLeftPrint').val());
    
    localStorage.setItem('showLeft', JSON.stringify(valorLeft.show));
    localStorage.setItem('hideLeft', JSON.stringify(valorLeft.hide));
    
    
    localStorage.setItem('isMultiSortRight', $('#listConfigRightMultiSort')[0].checked);
    localStorage.setItem('isScrollListRight', $('#listConfigRightScrollList')[0].checked);
    localStorage.setItem('isHeaderStickyRight', $('#listConfigRightHeaderSticky')[0].checked);
    localStorage.setItem('isMultiFilterRight', $('#listConfigRightMultiFilter')[0].checked);
    localStorage.setItem('isSuperSelectRight', $('#listConfigRightSuperSelect')[0].checked);
    
    localStorage.setItem('printRight', $('#listConfigRightPrint').val());
    
    localStorage.setItem('showRight', JSON.stringify(valorRight.show));
    localStorage.setItem('hideRight', JSON.stringify(valorRight.hide));
    
    
    
    deleteContent(position);
    setFunctionOnBtns(position);
    location.reload(true);
    console.log(valorLeft);
    console.log(valorRight);
    $('#rup-list-left').rup_list(valorLeft);
    $('#rup-list-right').rup_list(valorRight);
}


function deleteContent (position) {
    $('#rup-list-' + position).rup_list('destroy');
    

    $('.col-md-6').eq(0).find('.p-4').eq(0)[0].outerHTML = '';
    $('.col-md-6').eq(0).find('#rup-list-left-feedback').eq(0)[0].outerHTML = '';
    $('.col-md-6').eq(0).find('#rup-list-left-content').eq(0)[0].outerHTML = '';
    
    $('.col-md-6').eq(1).find('.p-4').eq(0)[0].outerHTML = '';
    $('.col-md-6').eq(1).find('#rup-list-right-feedback').eq(0)[0].outerHTML = '';
    $('.col-md-6').eq(1).find('#rup-list-right-content').eq(0)[0].outerHTML = '';
    
    
    $('.col-md-6').find('script').eq(0).after('<div id="rup-list-feedback"></div><div id="rup-list-content"><div id="rup-list-header" class="row"><div id="rup-list-header-selectables" class="col-md-3">Opciones de selección:</div><div class="col-md-2"><label for="rup-list-header-rowNum">Elementos por página:</label><select id="rup-list-header-rowNum"></select></div><!-- Ordenar por --><div class="col-md-3"><div class="row"><div class="col-md-7"><label for="rup-list-header-sidx">Ordenar por:</label><select id="rup-list-header-sidx"></select></div><div class="col-md-2"><button id="rup-list-header-sord"><em class="mdi mdi-sort"></em></button></div></div></div><!-- Navegación --><div class="col-md-4"><nav id="rup-list-header-nav"><ul class="pagination"><li id="rup-list-header-page-prev" class="page-item disabled"><a href="javascript:void(0)" class="page-link d-none d-lg-flex" tabindex="-1">Anterior</a><a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1"><span class="mdi mdi-arrow-right-bold-circle-outline mdi-rotate-180"/></a></li><li class="page-item page-separator disabled"><a class="page-link" tabindex="-1">...</a></li><li class="page-item page-separator disabled"><a class="page-link" tabindex="-1">...</a></li><li id="rup-list-header-page-next" class="page-item disabled"><a href="javascript:void(0)" class="page-link d-none d-lg-flex">Siguiente</a><a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1"><span class="mdi mdi-arrow-right-bold-circle-outline"/></a></li></ul></nav></div></div><div id="rup-list"></div><div id="rup-list-itemTemplate" class="row list-item"><div class="col-md-1" style="border-right: 1px solid gray;display:flex;justify-content:center;align-items:center;"><span class="mdi mdi-account-circle" style="transform:scale(2);"/></div><div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div style="border-bottom: 1px gray solid;"><strong><span id="id_label" /></strong>: <span id="id_value"/></div><div><strong><span id="nombre_label"/></strong>: <span id="nombre_value"/></div></div><div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div><span id="apellido1_label"/></div><div><span id="apellido1_value"/></div></div><div class="col-md-4" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div style="border-bottom: 1px solid gray;"><span id="apellido2_label"></span>: <span id="apellido2_value"></span></div><div><span id="ejie_label"></span>: <span id="ejie_value"></span></div></div><div class="col-md-3" style="display: flex; justify-content:space-around;"><span class="mdi mdi-36px mdi-account-edit"/><span class="mdi mdi-36px mdi-account-remove"/><span class="mdi mdi-36px mdi-settings"/></div></div></div>');
    $('.col-md-6').find('script').eq(0).after('<div><fieldset class="p-4"><form id="listFilterForm"><div class="row pb-2"><label for="listFilterUsuario" class="col-md-2 col-form-label">Usuario:</label><div class="col-md-4"><input id="listFilterUsuario" type="text" name="usuario" class="form-control"></div><label for="listFilterEmail" class="col-md-2 col-form-label">Email:</label><div class="col-md-4"><input id="listFilterEmail" type="email" name="email" class="form-control"></div></div><div class="row pb-2"><label for="listFilterEdad" class="col-md-2 col-form-label">Edad:</label><div class="col-md-4"><input id="listFilterEdad" type="number" name="edad" class="form-control"></div><label for="listFilterCodCliente" class="col-md-2 col-form-label">Codigo cliente:</label><div class="col-md-4"><input id="listFilterCodCliente" type="number" name="codCliente" class="form-control"></div></div><div class="float-right"><button id="listFilterAceptar" class="btn btn-primary"><i class="mdi mdi-filter" /> Filtrar </button><button id="listFilterLimpiar"><i class="mdi mdi-broom" />Limpiar </button></div></form></fieldset></div>');


    
    $('.col-md-6').find('script').eq(0).after('<div id="rup-list-feedback"></div><div id="rup-list-content"><div id="rup-list-header" class="row"><div id="rup-list-header-selectables" class="col-md-3">Opciones de selección:</div><div class="col-md-2"><label for="rup-list-header-rowNum">Elementos por página:</label><select id="rup-list-header-rowNum"></select></div><!-- Ordenar por --><div class="col-md-3"><div class="row"><div class="col-md-7"><label for="rup-list-header-sidx">Ordenar por:</label><select id="rup-list-header-sidx"></select></div><div class="col-md-2"><button id="rup-list-header-sord"><em class="mdi mdi-sort"></em></button></div></div></div><!-- Navegación --><div class="col-md-4"><nav id="rup-list-header-nav"><ul class="pagination"><li id="rup-list-header-page-prev" class="page-item disabled"><a href="javascript:void(0)" class="page-link d-none d-lg-flex" tabindex="-1">Anterior</a><a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1"><span class="mdi mdi-arrow-right-bold-circle-outline mdi-rotate-180"/></a></li><li class="page-item page-separator disabled"><a class="page-link" tabindex="-1">...</a></li><li class="page-item page-separator disabled"><a class="page-link" tabindex="-1">...</a></li><li id="rup-list-header-page-next" class="page-item disabled"><a href="javascript:void(0)" class="page-link d-none d-lg-flex">Siguiente</a><a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1"><span class="mdi mdi-arrow-right-bold-circle-outline"/></a></li></ul></nav></div></div><div id="rup-list"></div><div id="rup-list-itemTemplate" class="row list-item"><div class="col-md-1" style="border-right: 1px solid gray;display:flex;justify-content:center;align-items:center;"><span class="mdi mdi-account-circle" style="transform:scale(2);"/></div><div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div style="border-bottom: 1px gray solid;"><strong><span id="id_label" /></strong>: <span id="id_value"/></div><div><strong><span id="nombre_label"/></strong>: <span id="nombre_value"/></div></div><div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div><span id="apellido1_label"/></div><div><span id="apellido1_value"/></div></div><div class="col-md-4" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div style="border-bottom: 1px solid gray;"><span id="apellido2_label"></span>: <span id="apellido2_value"></span></div><div><span id="ejie_label"></span>: <span id="ejie_value"></span></div></div><div class="col-md-3" style="display: flex; justify-content:space-around;"><span class="mdi mdi-36px mdi-account-edit"/><span class="mdi mdi-36px mdi-account-remove"/><span class="mdi mdi-36px mdi-settings"/></div></div></div>');
    $('.col-md-6').find('script').eq(0).after('<div><fieldset class="p-4"><form id="listFilterForm"><div class="row pb-2"><label for="listFilterUsuario" class="col-md-2 col-form-label">Usuario:</label><div class="col-md-4"><input id="listFilterUsuario" type="text" name="usuario" class="form-control"></div><label for="listFilterEmail" class="col-md-2 col-form-label">Email:</label><div class="col-md-4"><input id="listFilterEmail" type="email" name="email" class="form-control"></div></div><div class="row pb-2"><label for="listFilterEdad" class="col-md-2 col-form-label">Edad:</label><div class="col-md-4"><input id="listFilterEdad" type="number" name="edad" class="form-control"></div><label for="listFilterCodCliente" class="col-md-2 col-form-label">Codigo cliente:</label><div class="col-md-4"><input id="listFilterCodCliente" type="number" name="codCliente" class="form-control"></div></div><div class="float-right"><button id="listFilterAceptar" class="btn btn-primary"><i class="mdi mdi-filter" /> Filtrar </button><button id="listFilterLimpiar"><i class="mdi mdi-broom" />Limpiar </button></div></form></fieldset></div>');
    
}

function setFunctionOnBtns (position) {
    if (position == 'left') {
        $('#listFilterFormLeft').find('#listFilterLimpiar').on('click', function (e) {
            e.stopImmediatePropagation();
            e.preventDefault();
            $('#listFilterFormLeft').find('input').val('');
            $('#rup-list-left').rup_list('filter');
        });
        $('#listFilterFormLeft').find('#listFilterAceptar').on('click', function (e) {
            e.stopImmediatePropagation();
            e.preventDefault();
            $('#rup-list-left').rup_list('filter');
        });
    } else if (position == 'right') {
        $('#listFilterFormRight').find('#listFilterLimpiar').on('click', function (e) {
            e.stopImmediatePropagation();
            e.preventDefault();
            $('#listFilterFormRight').find('input').val('');
            $('#rup-list-right').rup_list('filter');
          });
          $('#listFilterFormRight').find('#listFilterAceptar').on('click', function (e) {
            e.stopImmediatePropagation();
            e.preventDefault();
            $('#rup-list-right').rup_list('filter');
          });
    }
}

$('#listConfigLeftShow').on('click', (e) => {
    if ($(e.target)[0].checked) {
        $('#listConfigLeftShowAnimation')[0].disabled = false;
        $('#listConfigLeftShowDelay')[0].disabled = false;
    } else {
        $('#listConfigLeftShowAnimation')[0].disabled = true;
        $('#listConfigLeftShowDelay')[0].disabled = true;
    }
});

$('#listConfigRightShow').on('click', (e) => {
    if ($(e.target)[0].checked) {
        $('#listConfigRightShowAnimation')[0].disabled = false;
        $('#listConfigRightShowDelay')[0].disabled = false;
    } else {
        $('#listConfigRightShowAnimation')[0].disabled = true;
        $('#listConfigRightShowDelay')[0].disabled = true;
    }
});


$('#listConfigLeftHide').on('click', (e) => {
    if ($(e.target)[0].checked) {
        $('#listConfigLeftHideAnimation')[0].disabled = false;
        $('#listConfigLeftHideDelay')[0].disabled = false;
    } else {
        $('#listConfigLeftHideAnimation')[0].disabled = true;
        $('#listConfigLeftHideDelay')[0].disabled = true;
    }
});

$('#listConfigRightHide').on('click', (e) => {
    if ($(e.target)[0].checked) {
        $('#listConfigRightHideAnimation')[0].disabled = false;
        $('#listConfigRightHideDelay')[0].disabled = false;
    } else {
        $('#listConfigRightHideAnimation')[0].disabled = true;
        $('#listConfigRightHideDelay')[0].disabled = true;
    }
});

$('#listConfigLeftButton').on('click', (e) => {
    e.preventDefault();
    functionListConfigButton('left');
});


$('#listConfigRightButton').on('click', (e) => {
    e.preventDefault();
    functionListConfigButton('right');
});