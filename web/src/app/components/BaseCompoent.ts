/**
 * Created by cma2 on 17/7/13.
 */
import {OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/User';
import {UserService} from '../service/user.service';
import {ToasterService} from 'angular2-toaster';

export class BaseCompoent implements OnInit {
  constructor(
    public router: Router,
    public toastyService: ToasterService,
    public userService: UserService) {

  }

  public ngOnInit() {
    this.userService.me().toPromise().then(
      json => {
        this.userService.currentUser = json as User;
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }

  public showToasty(type: string, title: string, msg: string) {
    this.toastyService.pop(type, title, msg);
  }
}

export class ToasterType {
  public static success = 'success';
  public static warning = 'warning';
  public static danger = 'danger';
  public static error = 'error';
  public static default = 'default';
}
