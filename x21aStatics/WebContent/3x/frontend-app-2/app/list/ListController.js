;
'use strict'

import { ListModel } from '../list/ListModel.js';

import { ListHandler } from '../list/ListHandler.js';


class ListController{

  constructor(view){

    this._view = view;

  }

  // getItems (){
  //   let $controller = this;
  //   ListHandler.getAll().then(function( data ){
  //     $controller._items = data.map(e => new ListModel(e.id, e.name, e.description, e.userId));
  //      $controller.updateView();
  //   });
  // }

  updateView (container, context){
    // let $controller = this;
    //
    // $controller.getItems();
    this._view.render(container, context);
  }

  // updateView (){
  //   this._view.render(this._items);
  //
  //
  // }



}


export { ListController };
