;
'use sctrict'

// import { TaskListController } from '../controller/TaskListController.js'

import { BoardModel } from './board/BoardModel.js'
import { BoardView } from './board/BoardView.js'
import { BoardController } from './board/BoardController.js'


import { ListView } from './list/ListView.js'
import { ListController } from './list/ListController.js'

class BoardApp{
  constructor() {

    this._model = new BoardModel();
    this._view = new BoardView("#board-template","#board");
    this._controller = new BoardController(this._model, this._view);



    // this._listView = new ListView("#list-template");
    // this._listController = new ListController(this._listView);

    // this._controller.setChild(this._listController);
  };

  init() {
    this._controller.setView();
  };
}

const app = new BoardApp();

window.addEventListener('load', () => app.init());
