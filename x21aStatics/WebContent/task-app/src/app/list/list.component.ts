import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';

import { List } from "./list";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input() list: List;

  @Input() value: string;

  @Output() modifyListEvent: EventEmitter<List> = new EventEmitter(true);
  @Output() deleteListEvent: EventEmitter<List> = new EventEmitter(true);

  constructor() { }

  ngOnInit() {
  }


  modifyList(){
    console.log("modifyList");
    this.modifyListEvent.emit(this.list);

  }

  deleteList(){
    console.log("modifyList");
    this.deleteListEvent.emit(this.list);

  }

}
