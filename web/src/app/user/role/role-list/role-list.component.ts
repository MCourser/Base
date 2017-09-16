import {Component, OnInit} from "@angular/core";
import {Role} from "../../../model/User";
import {RoleService} from "../../../service/role.service";
import {ToastyService} from "ng2-toasty";
import {BaseCompoent, ToastType} from "../../../BaseCompoent";
import { Router } from '@angular/router';
import {UserService} from "../../../service/user.service";

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent extends BaseCompoent implements OnInit {
  private roleList: Role[] = [];

  constructor(
    protected router: Router,
    protected toastyService: ToastyService,
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
    this.roleService.list().map(resp=>resp.json() as Role[]).subscribe(
      roles=>{
        this.roleList = roles;
      }
    );
  }

  public delete(id:number) {
    this.roleService.delete(id).map(resp=>resp.json() as Role).subscribe(
      role=>{
        super.showToasty(ToastType.info, '角色删除成功', '角色:' + role.name + ' 删除成功');
        this.list();
      },
      error=>{
        super.showToasty(ToastType.error, '角色删除失败', '角色正在被使用');
      }
    );
  }
}
