import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BaseCompoent, ToasterType} from '../../../../BaseCompoent';
import {Permission, Role} from '../../../../../model/User';
import {UserService} from '../../../../../service/user.service';
import {PermissionService} from '../../../../../service/permission.service';
import {RoleService} from '../../../../../service/role.service';
import {ToasterService} from 'angular2-toaster';

@Component({
  selector: 'app-role-add',
  templateUrl: 'role-edit.component.html',
  styleUrls: ['role-edit.component.css']
})
export class RoleEditComponent extends BaseCompoent implements OnInit {
  public isEditMode = false;

  public permissionList: Permission[] = [];
  public roleForm = {
    id: 0,
    name: '',
    description: '',
    permissions: []
  };

  constructor(
    protected router: Router,
    protected toastyService: ToasterService,
    protected userService: UserService,
    private permissionService: PermissionService,
    private roleService: RoleService,
    private activatedRoute: ActivatedRoute
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.activatedRoute.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.isEditMode = true;
        this.loadEditMode(id);
      } else {
        this.isEditMode = false;
        this.loadAddModel();
      }
    });
  }

  public loadAddModel() {
    this.permissionService.list().toPromise().then(json => {
      this.permissionList = json as Permission[];
      this.permissionList.forEach(permission => {
        permission['isSelected'] = false;
      });
    });
  }

  public loadEditMode(id: number) {
    this.permissionService.list().toPromise().then(permissionListJson => {
      this.permissionList = permissionListJson as Permission[];
      this.roleService.load(id).toPromise().then(roleJson => {
        const role = roleJson as Role;
        this.roleForm.id = role.id;
        this.roleForm.name = role.name;
        this.roleForm.description = role.description;

        this.permissionList.forEach(permission => {
          permission['isSelected'] = false;
        });
        role.permissions.forEach(permissionInRole => {
          this.permissionList.forEach(permissionInList => {
            if (permissionInList.id === permissionInRole.id) {
              permissionInList['isSelected'] = true;
              return;
            }
          });
        });
      });
    });
  }

  public submit() {
    this.roleForm.permissions = [];
    this.permissionList.filter(permission => {
      return permission['isSelected'];
    }).forEach(permission => {
      this.roleForm.permissions.push(permission.id);
    });

    if (!this.isEditMode) {
      delete this.roleForm.id;
      this.roleService.create(this.roleForm).toPromise().then(json => {
        const role = json as Role;
        super.showToasty(ToasterType.success, '成功', '角色:' + role.name + ' 创建成功');
        this.router.navigate(['/home/role/list']);
      }).catch(() => {
        super.showToasty(ToasterType.error, '失败', '角色创建失败, 请检查输入是否正确');
      });
    } else {
      this.roleService.update(this.roleForm).toPromise().then(json => {
        const role = json as Role;
        super.showToasty(ToasterType.success, '成功', '角色:' + role.name + ' 更新成功');
        this.router.navigate(['/home/role/list']);
      }).catch(() => {
        super.showToasty(ToasterType.error, '失败', '角色更新失败, 请检查输入是否正确');
      });
    }
  }
}
