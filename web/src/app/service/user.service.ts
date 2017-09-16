import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import { URLSearchParams } from "@angular/http"
import {User} from "../model/User";
import {Page} from "../model/Page";

@Injectable()
export class UserService {

  public currentUser: User = null;

  constructor(private http: Http) { }

  public login(username: string, password:string) {
    let data = new URLSearchParams();
    data.append('username', username);
    data.append('password', password);
    return this.http.post('/api/user/login', data);
  }

  public logout() {
    return this.http.get('/api/user/logout');
  }

  public me() {
    return this.http.get('/api/user/me');
  }

  public list(page: Page) {
    return this.http.get('/api/user/?page=' + page.page + '&size=' + page.size);
  }

  public load(id:number) {
    return this.http.get('/api/user/' + id);
  }

  public create(form) {
    return this.http.post('/api/user/', form);
  }

  public update(form) {
    return this.http.put('/api/user/', form);
  }

  public delete(id:number) {
    return this.http.delete('/api/user/' + id);
  }
}
