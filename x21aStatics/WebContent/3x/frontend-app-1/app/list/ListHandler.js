;
'use strict'


import  $  from 'jquery';



class ListHandler{


  static getAll() {


    return $.getJSON('/x21aModulesWar/taskList');

  }

  static put(list){


    return $.ajax({
      url: '/x21aModulesWar/taskList',
      type: "PUT",
      data: JSON.stringify(list.toJson()),
      contentType : 'application/json',
      dataType : 'json'
    });

  }

  static post(list){
    return $.ajax({
      url: '/x21aModulesWar/taskList',
      type: "POST",
      data: JSON.stringify(list.toJson()),
      contentType : 'application/json',
      dataType : 'json'
    });

  }

  static delete(listId){
    return $.ajax({
      url: '/x21aModulesWar/taskList/'+listId,
      type: "DELETE",
      contentType : 'application/json',
      dataType : 'json'
    });

  }


};


export { ListHandler };
