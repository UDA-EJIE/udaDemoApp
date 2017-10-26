;
'use strict'

import { ListModel } from '../list/ListModel.js';
import { ListHandler } from '../list/ListHandler.js';
import { ListController } from '../list/ListController.js';
import { ListView } from '../list/ListView.js';


class BoardController{

  constructor(model, view){

    this._view = view;
    this._model = model;

    this._childController = undefined;


  }

  getItems (){
    let $controller = this;
    ListHandler.getAll().then(function( data ){
      $controller._model.listCollection = data.map(e => new ListModel(e.id, e.name, e.description, e.userId));
       $controller.updateView();
    });
  }

  setView (){
    let $controller = this;

    $controller.getItems();
  }

  updateView (){
    let $controller = this;
    this._view.render(this._model.listCollection);


    let containers = this._view.getListContainers();

    containers.forEach( c => {

      $controller._childController.updateView(c, $controller._getItemById(c.getAttribute("data-list-id")));
    });



  }

  setChild(controller){
    this._childController = controller;
  }


  _getItemById(id){
    return this._model.listCollection.find( x => x.id === Number(id));

  }

  //


}


export { BoardController };
