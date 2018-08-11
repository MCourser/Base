import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Role} from '../../../../../model/User';
import {UserService} from '../../../../../service/user.service';
import {RoleService} from '../../../../../service/role.service';
import {BaseCompoent, ToasterType} from '../../../../BaseCompoent';
import {ToasterService} from 'angular2-toaster';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent extends BaseCompoent implements OnInit {
  public roleList: Role[] = [];

  constructor(
    protected router: Router,
    protected toastyService: ToasterService,
    protected userService: UserService,
    private roleService: RoleService
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
    super.ngOnInit();

    this.list();
  }

  public list() {
    this.roleService.list().toPromise().then(json => {
      this.roleList = json as Role[];
    });
  }

  public delete(id: number) {
    this.roleService.delete(id).toPromise().then(json => {
      const role = json as Role;
      super.showToasty(ToasterType.success, '成功', '角色:' + role.name + ' 删除成功');
      this.list();
    }).catch(() => {
      super.showToasty(ToasterType.error, '失败', '角色删除失败, 角色正在被使用');
    });
  }
}
