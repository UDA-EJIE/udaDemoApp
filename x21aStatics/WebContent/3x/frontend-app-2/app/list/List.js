;
'use strict'

class ListModel {
  constructor(id, name, user){
    this._id = id;
    this._name = name;
    this._description = description;
    this._user = user;
  }

  set id    (id)                  { this._id = id;      }
  get id    ()                    { return this._id;    }
  set name  (name)                { this._name = name;  }
  get name  ()                    { return this._name;  }
  set description  (description)  { this._description = description;  }
  get description  ()             { return this._description;  }
  set user  (user)                { this._user = user;  }
  get user  ()                    { return user._user;  }

  toJson(){
    return {
      id: this._id,
      name: this._name,
      description: this._description,
      user: this._user
    };
  }

}

export { ListModel };
