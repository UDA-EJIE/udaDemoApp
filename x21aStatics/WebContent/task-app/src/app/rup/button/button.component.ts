import { ViewChild, ElementRef, Input, OnInit, AfterViewInit, Component } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'rup-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements AfterViewInit {

  @Input() options: any = {};

  @ViewChild('rupButton') input: ElementRef;

  @Input() fab: string;
  @Input() fixed: string;
  @Input() list: string;

  constructor() { }

  ngAfterViewInit() {
      jQuery(this.input.nativeElement).rup_button({});
  }

}
