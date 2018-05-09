;
'use sctrict'

// import { TaskListController } from '../controller/TaskListController.js'


import Marionette from 'backbone.marionette';
import BoardView from './board/BoardView.js';

var App = new Marionette.Application();

App.on('start', function() {
  'use strict';

  App.rootLayout = new BoardView({el: '#board'});
  App.rootLayout.render();
});

App.start();
