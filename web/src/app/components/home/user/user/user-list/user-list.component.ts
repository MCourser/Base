import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BaseCompoent, ToasterType} from '../../../../BaseCompoent';
import {UserService} from '../../../../../service/user.service';
import {Page} from '../../../../../model/Page';
import {User} from '../../../../../model/User';
import {ToasterService} from 'angular2-toaster';


@Component({
  selector: 'app-user-list',
  templateUrl: 'user-list.component.html',
  styleUrls: ['user-list.component.css']
})
export class UserListComponent extends BaseCompoent implements OnInit {
  public page: Page = new Page(0, 10);
  public userPage: any = {};

  constructor(
    protected router: Router,
    protected toastyService: ToasterService,
    protected userService: UserService
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
    super.ngOnInit();

    this.list();
  }

  public list() {
    this.userService.list(this.page).toPromise().then(json => {
      this.userPage = json;
      console.log(this.userPage);
    });
  }

  public delete(id: number) {
    this.userService.delete(id).toPromise().then(json => {
      const user = json as User;
      super.showToasty(ToasterType.success, '成功', '角色:' + user.name + ' 删除成功');
      this.list();
    }).catch(() => {
      super.showToasty(ToasterType.error, '失败', '角色删除失败, 角色正在被使用');
    });
  }
}
