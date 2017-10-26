import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

declare var jQuery: any;

@Injectable()
export class MessageService {

  constructor(private http: Http) {

  }

  msgConfirm(options: any){
    jQuery.rup_messages("msgConfirm", options);
  }

}
