 // Preparamos los eventos de la pantalla
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

//Generamos el componente
$('#rup-list').rup_list({
    action: './filter',
    filterForm: 'listFilterForm',
    feedback: 'rup-list-feedback',
    visiblePages: 3, //Mínimo 3
    key: 'id',
    isMultiSort: true,
    selectable:{
        multi: true
        , selector: '.list-item'
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
    modElement:(ev, item, json) => {
        var ejieval = item.find('#ejie_value_' + json.id);
        if(json.ejie == 1) {
            ejieval.text($.rup.i18n.app.comun.si);
        } else {
            ejieval.text($.rup.i18n.app.comun.no);
        }
    },
    load: () => {}
});