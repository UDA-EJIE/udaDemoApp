import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { TaskService } from '../task/task.service';
import { ListService } from '../list/list.service';
import { MessageService } from '../rup/message/message.service';

import { Task } from '../task/task';
import { List } from '../list/list';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  private tasks: Task[];

  @Input() list: List;

  @Input() task : Task;

  navigated = false;

  constructor(
    private taskService: TaskService,
    private listService: ListService,
    private messageService: MessageService,
    private route: ActivatedRoute
  ) { }


  ngOnInit() {
    const $component = this;


    this.list = new List();
    this.task = new Task();



    this.route.params.forEach((params: Params) => {
      if (params['id'] !== undefined) {
        const id = +params['id'];
        this.navigated = true;
        this.listService.get(id)
            .then(list => {
              this.list = list;
              $component.getTasks(list.id);
              this.task = new Task();
              this.task.idList = list.id;
        });


      } else {
        this.navigated = false;
        this.list = new List();
      }
    });


  }

  getTasks(id){
    this.taskService.getAll(id).then(tasks => this.tasks = tasks);
  }

  addTask(){
    const $component = this;

    this.taskService.post(this.task).then(() => {
      $component.getTasks($component.list.id);
    });
  }

  deleteTask(task){
    const $component = this;

    this.messageService.msgConfirm({
			message: "¿Está seguro que desea eliminar la lista?",
			title: "Confirmación borrado",
			OKFunction : function name(parameter) {
        $component.taskService.delete(task).then(() =>{
          $component.getTasks($component.list.id);
        });
			}
		})


  }

}
