import { Injectable } from '@angular/core';
import {User} from "../model/User";
import {Http} from "@angular/http";

@Injectable()
export class PermissionService {

  constructor(private http: Http) { }

  public list() {
    return this.http.get('/api/permission/');
  }

}
