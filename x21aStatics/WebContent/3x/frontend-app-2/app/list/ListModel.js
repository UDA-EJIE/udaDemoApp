;
'use strict'

class ListModel {

  constructor(id, name, description, userId){
    this._id = id;
    this._name = name;
    this._description = description;
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



}

export { ListModel };
