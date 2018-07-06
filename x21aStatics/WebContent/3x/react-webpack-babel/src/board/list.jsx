import React from 'react';

// import axios from 'axios';


export default class List extends React.Component {

  constructor(props) {
    super(props);


    this.state = {
      list: props.list
    };



  }

  componentDidMount() {
    // axios.get(`http://desarrollo.jakina.ejiedes.net:7001/x21aModulesWar/taskList`)
    //   .then(res => {
    //
    //     const lists = res.data;
    //     console.log(lists);
    //     this.setState({ lists });
    //   });
  }


  render() {
    return (
      <div className="card-block" data-value="list.id">
              <div className="card-title">
                <h4>{this.state.list.name}



          			<div className="align-right">
                  <span className="list-modify" data-listId="list.id"><i className="fa fa-pencil-square-o" aria-hidden="true"></i></span>
                  <span className="list-delete" data-listId="list.id"><i className="fa fa-trash-o" aria-hidden="true"></i></span>
                </div>
                </h4>
              </div>

      		<h6 className="card-subtitle mb-2 text-muted">{this.state.list.taskNum} Tareas</h6>
      		<p className="card-text">{this.state.list.description}</p>
      	  	<a className="card-link" id=""><span>Mostrar tareas</span></a>
            	<a className="card-link" href="/detail/{{list.id}}"><span>Ir detalle</span></a>
            </div>


    );
  }
}







// <rup-dialog #newListDialog [options]="dialogOptions">
// 	<div className="container">
// 		<form >
// 			<input type="hidden" name="id" [(ngModel)]="list.id" id="taskListId" />
// 			<div className="row">
// 				<div className="form-group">
// 		    	<label for="exampleInputEmail1">Titulo</label>
// 		    	<input type="text" className="form-control" id="taskListName" [(ngModel)]="list.name"  name="name" placeholder="Introduzca el título de la lista de tareas">
// 		  	</div>
//
// 		  	<div className="form-group">
// 		    	<label for="exampleInputEmail1">Descripcion</label>
// 		    	<input type="text" className="form-control" id="taskListDescription" [(ngModel)]="list.description" name="description" placeholder="Introduzca la descripción de la lista de tareas">
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
// 	<i className="fa fa-plus" aria-hidden="true"></i>
// </rup-button>
