import { Injectable } from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class RoleService {

  constructor(private http: Http) { }

  public list() {
    return this.http.get('/api/role/');
  }

  public load(id:number) {
    return this.http.get('/api/role/' + id);
  }

  public create(form) {
    return this.http.post('/api/role/', form);
  }

  public update(form) {
    return this.http.put('/api/role/', form);
  }

  public delete(id:number) {
    return this.http.delete('/api/role/'+ id);
  }

}
