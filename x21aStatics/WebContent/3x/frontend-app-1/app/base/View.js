;
'use strict'

import {qs, qsa, $on, $delegate} from '../util/helper.js';

import 'jquery';

import Handlebars from 'handlebars';

class View{

  constructor(template, el){
    this._template  = Handlebars.compile(qs(template).innerHTML);;
    this._el = el;
  }


  render(content){
    // return new Promise(function(resolve, reject){
      this._$el = qs(this._el);
      this._$el.innerHTML = this._template(content);
    // });
    this._buildUI();
    this.afterRender();
  }

  _buildUI(){

    for (var key of Object.keys(this._ui)) {
      this._ui['$'+key] = $(this._ui[key]);
    }

  }

  afterRender(){

  }

}


export { View };
