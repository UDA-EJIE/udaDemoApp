;
'use strict'


import $ from 'jquery';

class TaskHandler{


  static getAllTask(listId) {


    return $.getJSON('/x21aModulesWar/taskList/'+listId+'/task');

  }

  static done(taskId){
    return $.ajax({
      url: '/x21aModulesWar/task/'+taskId+'/done/true',
      type: "PUT",
      contentType : 'application/json',
      dataType : 'json'
    });
  }

  static undone(taskId){
    return $.ajax({
      url: '/x21aModulesWar/task/'+taskId+'/done/false',
      type: "PUT",
      contentType : 'application/json',
      dataType : 'json'
    });
  }

  static remove(taskId){
    return $.ajax({
      url: '/x21aModulesWar/task/'+taskId,
      type: "DELETE",
      contentType : 'application/json',
      dataType : 'json'
    });
  }


  static post(task){
    return $.ajax({
      url: '/x21aModulesWar/task',
      type: "POST",
      data: JSON.stringify(task.toJson()),
      contentType : 'application/json',
      dataType : 'json'
    });
  }

  static put(task){

  }



};

export { TaskHandler };
