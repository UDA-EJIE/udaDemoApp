;
'use strict'

import {qs, qsa, $on, $delegate} from '../util/helper.js';

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
    this.afterRender();
  }

  afterRender(){

  }

}


export { View };
