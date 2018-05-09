;
'use strict'

class BoardModel {

  constructor(id, name, description, userId){
    this._listCollection = [];
  }

  set listCollection    (listCollection)                  { this._listCollection = listCollection;      }
  get listCollection    ()                    { return this._listCollection;    }

  addList(list){
    this._listCollection.push(list);
  }

  getList(listId){
    return this._items.find( x => x.id === Number(id));
  }



}

export { BoardModel };
