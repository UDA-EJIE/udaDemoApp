// Preparamos los eventos de la pantalla
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


//Generamos el componente
$('#rup-list-left').rup_list({
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
  isMultiFilter: true,
  isMultiSort: true,
  load: function () {}
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


//Generamos el componente
$('#rup-list-right').rup_list({
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
  isMultiFilter: true,
  isMultiSort: true,
  load: function () {}
});

