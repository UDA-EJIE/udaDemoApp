;
'use strict'

// import { TaskHandler } from '../handler/TaskHandler.js';

import {qs, qsa, $on, $delegate} from '../util/helper.js';

import Handlebars from 'handlebars';

import { Dropdown } from 'bootstrap/js/src/dropdown.js';

import RupFeedback from 'uda-rup/src/rup.feedback.js';
import RupMessages from 'uda-rup/src/rup.message.js';

import 'jquery';


class ListView{

  constructor(template, container){

    this._container = container;
    this._template = Handlebars.compile(qs(template).innerHTML);

  }

  setModel(list){
    this._model = list;
  }

  render (){

    this._container.innerHTML = this._template(this._model);


    this.$taskList = $('.listItem', this._container);
    this.$listModify = $('.list-modify', this._container);
    this.$listDelete = $('.list-delete', this._container);


    // $on(this.$listModify, 'click', ({target}) => {
    //   let idList = target.getAttribute("data-listId");
    // });

    this.$listDelete

  }


  bindModifyList(handler){
    this.$listModify.on("click", function(){

      handler(this.getAttribute('data-listid'));
    });
  }

  bindDeleteList(handler){
    const $view = this;
    this.$listDelete.on("click", function(){
      $.rup_messages("msgConfirm", {
  			message: "¿Está seguro que desea eliminar la lista?",
  			title: "Confirmación",
  			OKFunction : function(){
          handler($view._model.id);
        }
  		});

    });
  }

};


export { ListView };
