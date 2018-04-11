/**
 * Created by cma2 on 17/7/13.
 */
import {ToastOptions, ToastyService} from 'ng2-toasty';
import {OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {UserService} from "./service/user.service";
import {User} from "./model/User";

export class BaseCompoent implements OnInit {
  constructor(
    protected router: Router,
    protected toastyService: ToastyService,
    protected userService: UserService) {

  }

  public ngOnInit() {
    this.userService.me().toPromise().then(
      json => {
        this.userService.currentUser = json as User;
      },
      error=> {
        this.router.navigate(['/login']);
      }
    );
  }

  public showToasty(type:ToastType, title:string, msg:string) {
    let toastOptions:ToastOptions = {
      title: title,
      msg: msg,
      showClose: false,
      timeout: 5000,
      theme: 'default'
    };
    switch (type) {
      case ToastType.info:
        this.toastyService.info(toastOptions);
        break;
      case ToastType.success:
        this.toastyService.success(toastOptions);
        break;
      case ToastType.wait:
        this.toastyService.wait(toastOptions);
        break;
      case ToastType.error:
        this.toastyService.error(toastOptions);
        break;
      case ToastType.warning:
        this.toastyService.warning(toastOptions);
        break;
    }

  }
}

export enum ToastType {
  info, success, wait, error, warning
}
