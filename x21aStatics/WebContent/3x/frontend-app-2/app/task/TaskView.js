;
'use strict'

import { TaskHandler } from './TaskHandler.js';
import Handlebars from 'handlebars';

const TEMPLATE = `
<ul class="list-group">
  {{#each this}}
  <li class="list-group-item">{{name}}</li>
  {{/each}}
</ul>
`;

const EL = "#tasks";

class TaskView{

  constructor(){
    this._template = Handlebars.compile(TEMPLATE);
    this._taskContainer = EL;
    this._$taskContainer = document.querySelector(this._taskContainer);

  }

  render (content){

    this._$taskContainer.innerHTML = this._template(content);

  }

}


export { TaskView };
