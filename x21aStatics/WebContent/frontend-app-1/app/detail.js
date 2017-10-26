;
'use sctrict'

import { TaskController } from './task/TaskController.js';
import { TaskView } from './task/TaskView.js';
import { TaskModel } from './task/TaskModel.js';

class DetailApp{
  constructor() {

    this._model = new TaskModel();
    this._view = new TaskView("#task-template","#tasks");
    this._controller = new TaskController(this._view);
  };

  init() {
    this._controller.setView();
  };
}

const app = new DetailApp();

window.addEventListener('load', () => app.init());
