;
'use strict'

class ListModel {

  constructor(id, name, description, userId, taskNum){
    this._id = id;
    this._name = name;
    this._description = description;
    this._taskNum = taskNum;
    this._userId = userId;
  }

  set id    (id)                  { this._id = id;      }
  get id    ()                    { return this._id;    }
  set name  (name)                { this._name = name;  }
  get name  ()                    { return this._name;  }
  set description  (description)  { this._description = description;  }
  get description  ()             { return this._description;  }
  set userId  (userId)            { this._userId = userId;  }
  get userId  ()                  { return this._userId;  }
  set taskNum  (taskNum)            { this._taskNum = taskNum;  }
  get taskNum  ()                  { return this._taskNum;  }


  toJson(){
    return {
      id: this._id,
      name: this._name,
      description: this._description,
      userId: this._userId
    };
  }
}

export { ListModel };
