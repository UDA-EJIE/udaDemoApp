;
'use strict'

import { Component } from '../base/Component.js';

import { BoardModel } from './BoardModel.js';
import { BoardView } from './BoardView.js';
import { BoardController } from './BoardController.js';

class BoardComponent extends Component{

  constructor(template, el){
    const model = new BoardModel();
    const view = new BoardView(template, el);
    const controller = new BoardController(model, view);
    super(model, view, controller);
  }

}

export { BoardComponent };
