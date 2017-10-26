;
'use strict'

import { TaskView } from './TaskView.js';
import { TaskHandler } from './TaskHandler.js';
import {qs, qsa, $on, $delegate} from '../util/helper.js';

class TaskController{

  constructor(view){
    this._view = view;
    this._idTaskList = qs('#idTaskList').value;
    this._taskList = [];
  }

  setView(){
    this.getTasks();

  }

  getTasks(){
    var $controller = this;


    TaskHandler.getAllTask(this._idTaskList).then(function( data ){
      $controller._taskList = data;
      $controller._view.render(data);

      $controller.updateView();
    });
  }

  updateView(){

    this.bindViewEvents();

  }


  bindViewEvents(){
    this._view.bindDoneTask(this.doneTask.bind(this));
    this._view.bindDeleteTask(this.deleteTask.bind(this));
    this._view.bindAddTask(this.addTask.bind(this));


  }


  doneTask(taskId, done){
    if (done){
      TaskHandler.done(taskId);
    }else{
      TaskHandler.undone(taskId);
    }
  }

  addTask(task){

    TaskHandler.post(task);

  }

  deleteTask(taskId){
    const $controller = this;
    TaskHandler.remove(taskId).then(function(){

      $controller._view.setFeedback("La tarea se ha eliminado correctamente", "ok");

    });
  }
};


export { TaskController };
