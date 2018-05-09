;
'use strict'

import { View } from '../base/View.js';

import 'jquery';

import { TaskHandler } from './TaskHandler.js';
import { TaskModel } from './TaskModel.js';
import Handlebars from 'handlebars';

import RupFeedback from 'uda-rup/src/rup.feedback.js';
import RupMessages from 'uda-rup/src/rup.message.js';

class TaskView extends View{

  constructor(template, el){

    super(template, el);

    this._ui = {
        taskDoneCheckbox: '.task-done-checkbox',
        taskList: "#taskList",
        taskListItems: ".task-list-item ",
        taskDelete: ".delete-task",
        feedback: "#feedback",
        txtNewTask: "#newTask",
        sendTask: "#sendTask",
        idTaskList: "#idTaskList"
      // dialog: '#taskListDetailDialog',
      // feedback: '#feedback',
      // txtListName: '#taskListName',
      // txtListDescription: '#taskListDescription',
      // txtListId: '#taskListId'
    };
  }

  afterRender (content){

      this._ui.$feedback.rup_feedback({});

  }

  bindDoneTask(handler){
    const $view = this;

    $(this._ui.$taskDoneCheckbox).on("click", function(){

      const taskId = this.getAttribute("data-task-id");
      const done = this.checked;

      const $taskListItem = $($view._ui.$taskList).find("li[data-task-id='"+taskId+"']");

      if (done){
          $taskListItem.addClass("task-done");
      }else{
        $taskListItem.removeClass("task-done");
      }

      handler(taskId, done);

    });
  }

  bindDeleteTask(handler){
    const $view = this;
    $(this._ui.$taskDelete).on("click", function(){

      const taskId = this.getAttribute("data-task-id");

      $.rup_messages("msgConfirm", {
  			message: "¿Está seguro que desea eliminar la tarea?",
  			title: "Confirmación",
  			OKFunction : function(){
          $($view._ui.$taskList).find("li[data-task-id='"+taskId+"']").remove();

          handler(taskId);
        }
  		});

    });
  }

  bindAddTask(handler){
    const $view = this;
    this._ui.$sendTask.on("click", function(){
      if ($view._ui.$txtNewTask.val()===""){
        $view._ui.$feedback.rup_feedback("set", "La tarea no puede estar vacía.", "alert");
      }else{
        const task = new TaskModel();
        task.name = $view._ui.$txtNewTask.val();
        task._idList = $view._ui.$idTaskList.val();

        handler(task);
      }
    });

  }

  setFeedback(text, type){
    this._ui.$feedback.rup_feedback("set", text, type);
  }
}




export { TaskView };
