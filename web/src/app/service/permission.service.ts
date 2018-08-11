import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class PermissionService {

  constructor(private http: HttpClient) { }

  public list() {
    return this.http.get('/api/permission/');
  }

}
