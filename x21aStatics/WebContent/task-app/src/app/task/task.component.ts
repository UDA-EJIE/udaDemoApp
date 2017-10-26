import { Component, ElementRef, EventEmitter, ViewChild, Input, Output, OnInit } from '@angular/core';

import { TaskService } from "./task.service";
import { Task } from "./task";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @Input() task: Task;

  @ViewChild('doneCheck') doneCheck:ElementRef;

  @Output() deleteTaskEvent: EventEmitter<Task> = new EventEmitter(true);

  constructor(private taskService : TaskService) { }

  ngOnInit() {

  }

  done(){
    this.task.done = this.doneCheck.nativeElement.checked;
    if (this.task.done){
      this.taskService.done(this.task);
    }else{
      this.taskService.undone(this.task);
    }
    console.log("done");
  }

  deleteTask(){
    this.deleteTaskEvent.emit(this.task);
  }



}
