;
'use strict'

class TaskModel{
  constructor(id, name, detail, done, idList){
    this._id = id;
    this._name = name;
    this._detail = detail;
    this._done = done;
    this._idList = idList;
  }

  set id      (id)      { this._id = id;        }
  get id      ()        { return this._id;      }
  set name    (name)    { this._name = name;    }
  get name    ()        { return this._name;    }
  set detail  (detail)  { this._detail = detail;}
  get detail  ()        { return this._detail;  }
  set done  (done)      { this._done = done;    }
  get done  ()          { return this._done;    }
  set idList  (idList)  { this._idList = idList;}
  get idList  ()        { return this._idList;  }


  toJson(){
    return {
      id: this._id,
      name: this._name,
      detail: this._detail,
      done: this._done,
      idList: this._idList
    };
  }

};

export { TaskModel };
