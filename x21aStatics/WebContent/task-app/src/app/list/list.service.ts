import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { List } from './list';

@Injectable()
export class ListService {

  private listUrl = 'http://desarrollo.jakina.ejiedes.net:7001/x21aModulesWar/taskList';

  constructor(private http: Http) {

  }

  getAll(): Promise<Array<List>> {

    return this.http.get(this.listUrl)
    .toPromise()
    .then((response) => {
      return response.json() as List[];
    })
    .catch(this.handleError);
  }

  get(id): Promise<List> {

    return this.http.get(this.listUrl+"/"+id)
    .toPromise()
    .then((response) => {
      return response.json() as List;
    })
    .catch(this.handleError);
  }

  post(list: List): Promise<List> {

    const headers = new Headers({
      'Content-Type': 'application/json'
    });


    return this.http.post(this.listUrl, JSON.stringify(list), {headers: headers})
    .toPromise()
    .then((response) => {
      return response.json() as List;
    })
    .catch(this.handleError);
  }

  put(list: List): Promise<List> {

    const headers = new Headers({
      'Content-Type': 'application/json'
    });


    return this.http.put(this.listUrl, JSON.stringify(list), {headers: headers})
    .toPromise()
    .then((response) => {
      return response.json() as List;
    })
    .catch(this.handleError);
  }

  delete(list: List): Promise<List> {

    const headers = new Headers({
      'Content-Type': 'application/json'
    });


    return this.http.delete(this.listUrl+"/"+list.id, {headers: headers})
    .toPromise()
    .then((response) => {
      return list;
    })
    .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
