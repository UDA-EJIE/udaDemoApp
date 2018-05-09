;
'use strict'

import { View } from '../base/View.js';


import {qs, qsa, $on, $delegate} from '../util/helper.js';

import { ListHandler } from '../list/ListHandler.js';
import { ListModel } from '../list/ListModel.js';
import Handlebars from 'handlebars';

import 'jquery';

// import { RupButton } from 'rup/rup.button';
import RupButton from 'uda-rup/src/rup.button.js';
// import { RupDialog } from 'rup/rup.dialog';
import RupDialog from 'uda-rup/src/rup.dialog.js';
import RupFeedback from 'uda-rup/src/rup.feedback.js';

Handlebars.registerHelper("showHr", function(index_count,block) {

  if(parseInt(index_count+1)%3=== 0){
    return block.fn(this);}


});


class BoardView extends View{

  constructor(template, el){





    super(template, el);

    this._ui = {
      addButton: '#fabButton',
      dialog: '#taskListDetailDialog',
      feedback: '#feedback',
      txtListName: '#taskListName',
      txtListDescription: '#taskListDescription',
      txtListId: '#taskListId'
    };
  }

  afterRender (){

    const $view = this;
    // this._container.innerHTML = this._template(content);


    // FAB BUTTON
    $view._ui.$addButton.rup_button({


    });

    // feedback

    this._ui.$feedback.rup_feedback({});


    // Detail Dialog

    $view._ui.$dialog.rup_dialog({
      type: $.rup.dialog.DIV,
  		autoOpen: false,
  		modal: true,
  		resizable: false,
      width: 800,
  		title: "Lista de tareas",
  		buttons: [{
  				text: "Aceptar",
          id: "save",
          click: function () {

  				}
  			},
  			{
  				text: "Enviar",
  				click: function () {
  					console.log("Enviar");
  				}
  			},
  			{
  				text: "Cancelar",
  				click: function () {
  					console.log("Cancelar");
  				},
  				btnType: $.rup.dialog.LINK
  			}
  		],
    });




  }

  getListContainers(){
    return this._$el.querySelectorAll(".listItem");
  }

  showDialog(list){

    this._ui.$txtListName[0].value = list?list.name:'';
    this._ui.$txtListDescription[0].value = list?list.description:'';
    this._ui.$txtListId[0].value = list?list.id:'';

    this._ui.$dialog.rup_dialog("open");
  }

  closeDialog(){
    this._ui.$dialog.rup_dialog("close");
  }

  bindNewList(handler){
      this._ui.$addButton.on("click", handler);
  }

  bindSaveList(handler){
    const $view = this;


    $("#save").on("click", () =>  {

      const list = new ListModel();

      list.name = $view._ui.$txtListName[0].value;
      list.description = $view._ui.$txtListDescription[0].value;
      list.id = $view._ui.$txtListId[0].value;

      handler(list)
    });
  }

  bindDeleteList(listId){

  }

  setFeedback(text, type){
    this._ui.$feedback.rup_feedback("set", text, type);
  }

}


export { BoardView };
