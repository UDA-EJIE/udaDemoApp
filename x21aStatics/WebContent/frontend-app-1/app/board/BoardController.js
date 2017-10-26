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
      $controller._model.listCollection = data.map(e => new ListModel(e.id, e.name, e.description, e.userId, e.taskNum));
       $controller.updateView();
    });
  }

  setView (){
    let $controller = this;

    $controller.getItems();
  }

  refresh(){
    this.getItems();
  }

  updateView (){
    let $controller = this;

    this._childViews = [];


    this._view.render(this._model.listCollection);


    this._bindViewEvents();


    let containers = this._view.getListContainers();

    containers.forEach( c => {
      $controller.addChildView("#list-template", c);

      // $controller._childController.updateView(c, $controller._getItemById(c.getAttribute("data-list-id")));
    });

    $controller.renderChildView();
    $controller.bindChildView();



  }

  _bindViewEvents(){
    //binds

    this._view.bindSaveList(this.saveList.bind(this));
    this._view.bindNewList(this.newList.bind(this));


  }

  setChild(controller){
    this._childController = controller;
  }


  _getItemById(id){
    return this._model.listCollection.find( x => x.id === Number(id));

  }

  // Child Views

  addChildView(template, container){
    const view = new ListView(template, container);
    view.setModel(this._getItemById(container.getAttribute("data-list-id")));



    this._childViews.push(view);
  }


  bindChildView(){
    const $controller = this;
    this._childViews.forEach(v => {
      v.bindModifyList(this.modifyList.bind($controller));
      v.bindDeleteList(this.deleteList.bind($controller));
    });
  }

  renderChildView(){
    this._childViews.forEach(v => v.render());
  }

  //

  newList(){
    this.showDialog();
  }

  deleteList(listId){
    const $controller = this;

    ListHandler.delete(listId).then(function(){

      $controller._view.setFeedback("La lista se ha eliminado correctamente", "ok");
      $controller.refresh();
    });
  }

  modifyList(listId){
    const list = this._getItemById(listId)
    this.showDialog(list);
  }

  showDialog(list){
    this._view.showDialog(list);
  }

  closeDialog(){
    this._view.closeDialog();
  }

  saveList(list) {
    const $controller = this;
    if (list.id === ''){
      ListHandler.post(list).then(function(){
        $controller.closeDialog();
        $controller._view.setFeedback("La lista se ha añadido correctamente", "ok");
        $controller.refresh();
      });
    }else{
      ListHandler.put(list).then(function(){
        $controller.closeDialog();
        $controller._view.setFeedback("La lista se ha modificado correctamente", "ok");
        $controller.refresh();
      });
    }
	}


}


export { BoardController };
