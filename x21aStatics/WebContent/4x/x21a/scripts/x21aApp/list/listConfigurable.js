window.$ = $;

setFunctionOnBtn();

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

var valor = {
    action: '/x21aAppWar/lista/filter',
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
    isMultiSort: true,
    load: function () {}
}

//Generamos el componente
$('#rup-list').rup_list(valor);

function setFunctionOnBtn () {
    //Preparamos los eventos de la pantalla
    $('#listFilterLimpiar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#listFilterForm').find('input').val('');
        $('#rup-list').rup_list('filter');
    });
    $('#listFilterAceptar').on('click', function (e) {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#rup-list').rup_list('filter');
    });
}

function functionListConfigButton () {
    valor.isMultiSort = $('#listConfigMultiSort')[0].checked;
    valor.isScrollList = $('#listConfigScrollList')[0].checked;
    valor.isHeaderSticky = $('#listConfigHeaderSticky')[0].checked;
    valor.print = $('#listConfigPrint').val();

    if (valor.print == '') {
        valor.print = false;
    }

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
    
    deleteContent();
    setFunctionOnBtns();
    console.log(valor);
    $('#rup-list').rup_list(valor);
}

function setFunctionOnBtns () {
    $('#listFilterLimpiar').on('click', (e) => {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#listFilterForm').find('input').val('');
        $('#rup-list').rup_list('filter');
    });
    $('#listFilterAceptar').on('click', (e) => {
        e.stopImmediatePropagation();
        e.preventDefault();
        $('#rup-list').rup_list('filter');
    });
}

function deleteContent () {
    $('.content').find('h2').eq(0).remove();
    $('.content').find('fieldset.p-4').eq(0).remove();
    $('.content').find('#rup-list-feedback').eq(0).remove();
    $('.content').find('#rup-list-content').eq(0).remove();
    $('.content').find('script').eq(1).after('<div id="rup-list-feedback"></div><div id="rup-list-content"><div id="rup-list-header" class="row"><div id="rup-list-header-selectables" class="col-md-3">Opciones de selección:</div><div class="col-md-2"><label for="rup-list-header-rowNum">Elementos por página:</label><select id="rup-list-header-rowNum"></select></div><!-- Ordenar por --><div class="col-md-3"><div class="row"><div class="col-md-7"><label for="rup-list-header-sidx">Ordenar por:</label><select id="rup-list-header-sidx"></select></div><div class="col-md-2"><button id="rup-list-header-sord"><em class="mdi mdi-sort"></em></button></div></div></div><!-- Navegación --><div class="col-md-4"><nav id="rup-list-header-nav"><ul class="pagination"><li id="rup-list-header-page-prev" class="page-item disabled"><a href="javascript:void(0)" class="page-link d-none d-lg-flex" tabindex="-1">Anterior</a><a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1"><span class="mdi mdi-arrow-right-bold-circle-outline mdi-rotate-180"/></a></li><li class="page-item page-separator disabled"><a class="page-link" tabindex="-1">...</a></li><li class="page-item page-separator disabled"><a class="page-link" tabindex="-1">...</a></li><li id="rup-list-header-page-next" class="page-item disabled"><a href="javascript:void(0)" class="page-link d-none d-lg-flex">Siguiente</a><a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1"><span class="mdi mdi-arrow-right-bold-circle-outline"/></a></li></ul></nav></div></div><div id="rup-list"></div><div id="rup-list-itemTemplate" class="row list-item"><div class="col-md-1" style="border-right: 1px solid gray;display:flex;justify-content:center;align-items:center;"><span class="mdi mdi-account-circle" style="transform:scale(2);"/></div><div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div style="border-bottom: 1px gray solid;"><strong><span id="id_label" /></strong>: <span id="id_value"/></div><div><strong><span id="nombre_label"/></strong>: <span id="nombre_value"/></div></div><div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div><span id="apellido1_label"/></div><div><span id="apellido1_value"/></div></div><div class="col-md-4" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;"><div style="border-bottom: 1px solid gray;"><span id="apellido2_label"></span>: <span id="apellido2_value"></span></div><div><span id="ejie_label"></span>: <span id="ejie_value"></span></div></div><div class="col-md-3" style="display: flex; justify-content:space-around;"><span class="mdi mdi-36px mdi-account-edit"/><span class="mdi mdi-36px mdi-account-remove"/><span class="mdi mdi-36px mdi-settings"/></div></div></div>');
    $('.content').find('script').eq(1).after('<div><fieldset class="p-4"><form id="listFilterForm"><div class="row pb-2"><label for="listFilterUsuario" class="col-md-2 col-form-label">Usuario:</label><div class="col-md-4"><input id="listFilterUsuario" type="text" name="usuario" class="form-control"></div><label for="listFilterEmail" class="col-md-2 col-form-label">Email:</label><div class="col-md-4"><input id="listFilterEmail" type="email" name="email" class="form-control"></div></div><div class="row pb-2"><label for="listFilterEdad" class="col-md-2 col-form-label">Edad:</label><div class="col-md-4"><input id="listFilterEdad" type="number" name="edad" class="form-control"></div><label for="listFilterCodCliente" class="col-md-2 col-form-label">Codigo cliente:</label><div class="col-md-4"><input id="listFilterCodCliente" type="number" name="codCliente" class="form-control"></div></div><div class="float-right"><button id="listFilterAceptar" class="btn btn-primary"><i class="mdi mdi-filter" /> Filtrar </button><button id="listFilterLimpiar"><i class="mdi mdi-broom" />Limpiar </button></div></form></fieldset></div>');
    $('.content').find('script').eq(0).after('<h2> Componente rup_list </h2>');
}

$('#listConfigShow').on('click', (e) => {
    if ($(e.target)[0].checked) {
        $('#listConfigShowAnimation')[0].disabled = false;
        $('#listConfigShowDelay')[0].disabled = false;
    } else {
        $('#listConfigShowAnimation')[0].disabled = true;
        $('#listConfigShowDelay')[0].disabled = true;
    }
});

$('#listConfigHide').on('click', (e) => {
    if ($(e.target)[0].checked) {
        $('#listConfigHideAnimation')[0].disabled = false;
        $('#listConfigHideDelay')[0].disabled = false;
    } else {
        $('#listConfigHideAnimation')[0].disabled = true;
        $('#listConfigHideDelay')[0].disabled = true;
    }
});

$('#listConfigButton').on('click', (e) => {
    e.preventDefault();
    functionListConfigButton();
});