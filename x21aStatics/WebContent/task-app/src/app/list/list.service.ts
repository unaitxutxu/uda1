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

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
