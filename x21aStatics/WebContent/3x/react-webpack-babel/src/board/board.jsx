import React from 'react';


import List from './list';
import Dialog from '../rup/dialog';

import axios from 'axios';
import '../../styles/shared/footer.scss';

export default class Board extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      lists: []
    };
  }

  componentDidMount() {
    axios.get(`http://desarrollo.jakina.ejiedes.net:7001/x21aModulesWar/taskList`)
      .then(res => {

        const lists = res.data;
        console.log(lists);
        this.setState({ lists });
      });
  }


  render() {
    return (
      <div className="row">
        <Dialog></Dialog>

        {this.state.lists.map(list =>

          <div key={list.id}  className="col-md-4">

            <div className="card listItem shadow-2" >
              <List list={list}></List>

            </div>
          </div>
        )}


      </div>

    );
  }
}







// <rup-dialog #newListDialog [options]="dialogOptions">
// 	<div class="container">
// 		<form >
// 			<input type="hidden" name="id" [(ngModel)]="list.id" id="taskListId" />
// 			<div class="row">
// 				<div class="form-group">
// 		    	<label for="exampleInputEmail1">Titulo</label>
// 		    	<input type="text" class="form-control" id="taskListName" [(ngModel)]="list.name"  name="name" placeholder="Introduzca el título de la lista de tareas">
// 		  	</div>
//
// 		  	<div class="form-group">
// 		    	<label for="exampleInputEmail1">Descripcion</label>
// 		    	<input type="text" class="form-control" id="taskListDescription" [(ngModel)]="list.description" name="description" placeholder="Introduzca la descripción de la lista de tareas">
// 		  	</div>
// 		  	<input type="hidden" id="taskListId"/>
//
// 			</div>
//
// 		</form>
// 	</div>
// </rup-dialog>
//
//
// <rup-button  [fab]="true" [fixed]="true" [list]="fabButtonFixedList" (click)="addList()">
// 	<i class="fa fa-plus" aria-hidden="true"></i>
// </rup-button>
