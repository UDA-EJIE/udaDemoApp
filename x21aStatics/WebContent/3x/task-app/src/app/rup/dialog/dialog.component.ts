import { ViewChild, ElementRef, Input, OnInit, AfterViewInit, Component } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'rup-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent  implements AfterViewInit {

  @Input() options: any = {};

  @ViewChild('input') input: ElementRef;



  constructor() { }

  ngAfterViewInit() {
      jQuery(this.input.nativeElement).rup_dialog(this.options);
  }

  open(){
    jQuery(this.input.nativeElement).rup_dialog("open");
  }

  close(){
    jQuery(this.input.nativeElement).rup_dialog("close");
  }

}
