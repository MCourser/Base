import {Injectable} from '@angular/core';
import {User} from '../model/User';
import {Page} from '../model/Page';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UserService {

  public currentUser: User = null;

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    return this.http.post('/api/user/login', {
      'username': username,
      'password': password
    });
  }

  public logout() {
    return this.http.get('/api/user/logout');
  }

  public me() {
    return this.http.get('/api/user/me');
  }

  public list(page: Page) {
    return this.http.get('/api/user/?page=' + (page.page - 1) + '&size=' + page.itemsPerPage);
  }

  public load(id: number) {
    return this.http.get('/api/user/' + id);
  }

  public create(form) {
    return this.http.post('/api/user/', form);
  }

  public update(form) {
    return this.http.put('/api/user/', form);
  }

  public delete(id: number) {
    return this.http.delete('/api/user/' + id);
  }

  public changeCurrentUserPassword(password: string) {
    return this.http.put('/api/user/password/', {
        password: password
      }
    );
  }
}
