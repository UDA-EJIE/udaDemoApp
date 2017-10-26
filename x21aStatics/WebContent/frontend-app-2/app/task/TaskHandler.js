;
'use strict'


import $ from 'jquery';

class TaskHandler{


  getAllTask(listId) {


    return $.getJSON('/x21aModulesWar/taskList/'+listId+'/task');

  }




};

export { TaskHandler };
