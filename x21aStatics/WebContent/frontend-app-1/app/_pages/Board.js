;
'use sctrict'

// import { TaskListController } from '../controller/TaskListController.js'

import { BoardView } from '../board/BoardView.js'
import { BoardController } from '../board/BoardController.js'


import { ListView } from '../list/ListView.js'
import { ListController } from '../list/ListController.js'


import {qs, qsa, $on, $delegate} from '../util/helper.js';




let boardView = new BoardView("#board", "#board-template");
let boardController = new BoardController(boardView);

let listView = new ListView("#list-template");
let listController = new ListController(listView);


boardController.setChild(listController);

boardController.setView();
