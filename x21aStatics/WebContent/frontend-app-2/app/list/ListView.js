;
'use strict'

// import { TaskHandler } from '../handler/TaskHandler.js';

import {qs, qsa, $on, $delegate} from '../util/helper.js';

import Handlebars from 'handlebars';

import { Dropdown } from 'bootstrap/js/src/dropdown.js';


class ListView{

  constructor(template){


    this._template = Handlebars.compile(qs(template).innerHTML);

  }

  render (container, content){

    this._container = container;

    container.innerHTML = this._template(content);


    this.$taskList = qsa('.listItem');
    this.$listModify = qsa('.list-modify');
    this.$listDelete = qsa('.list-delete');


    $on(this.$taskList, 'click', ({target}) => {

      let idList = target.getAttribute("data-value");



			// const title = target.value.trim();
			// if (title) {
			// 	handler(title);
			// }
		});


    $on(this.$listModify, 'click', ({target}) => {
      let idList = target.getAttribute("data-listId");


    });

  }

};


export { ListView };
