import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { ListService } from '../list/list.service';
import { List } from '../list/list';

import { DialogComponent } from '../rup/dialog/dialog.component';
import { MessageService } from '../rup/message/message.service';

declare var jQuery: any;

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  @ViewChild('newListDialog') dialog: DialogComponent;

  lists: List[];

  list: List;

  dialogOptions =  {};

  constructor(
    private listService: ListService,
    private messageService: MessageService) {
      const $component = this;

      this.list = new List();

      this.dialogOptions =  {
          type: jQuery.rup.dialog.DIV,
      		autoOpen: false,
      		modal: true,
      		resizable: false,
          width: 800,
      		title: "Lista de tareas",
      		buttons: [{
      				text: "Aceptar",
              id: "save",
              click: function () {
                console.log("Enviar");
                $component.saveList();
      				}
      			},

      			{
      				text: "Cancelar",
      				click: function () {
      					console.log("Cancelar");
      				},
      				btnType: jQuery.rup.dialog.LINK
      			}
      		]
        };

  }

  ngOnInit() {

    this.getLists();

    console.log(this.lists);
  }

  saveList(){
    const $component = this;
    console.log("saveList");

    if (this.list.id !== undefined && this.list.id !== null){
      this.listService.put(this.list).then(() => {
        this.dialog.close();
        $component.getLists();
      });
    }else{
      this.listService.post(this.list).then(() => {
        this.dialog.close();
        $component.getLists();
      });
    }


  }



  getLists(){
    this.listService.getAll().then(lists => this.lists = lists);
  }

  addList(){
    this.list = new List();
    this.dialog.open();
  }

  modifyList(list){
    this.list = list;
    this.dialog.open();
  }

  deleteList(list){
    const $component = this;

    this.messageService.msgConfirm({
			message: "¿Está seguro que desea eliminar la lista?",
			title: "Confirmación borrado",
			OKFunction : function name(parameter) {
        $component.listService.delete(list).then(() =>{
          $component.getLists();
        });
			}
		})


  }

}
