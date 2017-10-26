import { ViewChild, ElementRef, Input, OnInit, AfterViewInit, Component } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'rup-feedback',
  template: '<div #input id="feedback"></div>',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements AfterViewInit {

  @Input() options: any = {};

  @ViewChild('input') input: ElementRef;


  constructor() { }

  ngAfterViewInit() {
    jQuery(this.input.nativeElement).rup_feedback({});
    jQuery(this.input.nativeElement).rup_feedback("set","Hola","ok");

  }

}
