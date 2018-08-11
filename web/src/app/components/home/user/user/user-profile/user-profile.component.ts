import { Component, OnInit } from '@angular/core';
import {BaseCompoent, ToasterType} from '../../../../BaseCompoent';
import {Router} from '@angular/router';
import {ToasterService} from 'angular2-toaster';
import {UserService} from '../../../../../service/user.service';
import {User} from '../../../../../model/User';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent extends BaseCompoent  implements OnInit {
  public userForm = {
    password: ''
  };

  constructor(
    protected router: Router,
    protected toastyService: ToasterService,
    protected userService: UserService
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
  }

  public changeCurrentUserPassword() {
    this.userService.changeCurrentUserPassword(this.userForm.password).toPromise().then(json => {
      const user = json as User;
      super.showToasty(ToasterType.success, '成功', '密码修改成功');
      this.router.navigate(['/logout']);
    }).catch(() => {
      super.showToasty(ToasterType.error, '失败', '密码修改失败');
    });
  }

}
