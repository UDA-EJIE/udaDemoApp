;
'use strict'

import { TaskView } from './TaskView.js';
import { TaskHandler } from './TaskHandler.js';
import {qs, qsa, $on, $delegate} from '../util/helper.js';

class TaskController{

  constructor(){
    this._view = new TaskView();
    this._handler = new TaskHandler();
    this._idTaskList = qs('#idTaskList').value;
    this._taskList = [];
  }

  setView(){
    this.getTasks();

  }

  getTasks(){
    var $controller = this;


    this._handler.getAllTask(this._idTaskList).then(function( data ){
      $controller._taskList = data;
      $controller._view.render(data);

    });
  }
};


export { TaskController };
