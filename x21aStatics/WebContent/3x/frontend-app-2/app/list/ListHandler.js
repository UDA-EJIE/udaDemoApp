;
'use strict'


import  $  from 'jquery';



class ListHandler{


  static getAll() {


    return $.getJSON('/x21aModulesWar/taskList');

  }

  put(task){


    return $.ajax({
      url: '/x21aModulesWar/taskList',
      type: "POST",
      data: JSON.stringify(task.toJSON),
      contentType : 'application/json',
      dataType : 'json'
    });

  }

  post(task){


  }


};


export { ListHandler };
