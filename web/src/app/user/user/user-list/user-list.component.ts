import {Component, OnInit} from "@angular/core";
import {BaseCompoent, ToastType} from "../../../BaseCompoent";
import {ToastyService} from "ng2-toasty";
import {Router} from "@angular/router";
import {UserService} from "../../../service/user.service";
import {Page} from "../../../model/Page";
import {User} from "../../../model/User";


@Component({
  selector: 'app-user-list',
  templateUrl: 'user-list.component.html',
  styleUrls: ['user-list.component.css']
})
export class UserListComponent extends BaseCompoent implements OnInit {
  public page: Page = new Page();
  public userPage:any = {};

  constructor(
    protected router: Router,
    protected toastyService: ToastyService,
    protected userService: UserService
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
    super.ngOnInit();

    this.list();
  }

  public list() {
    this.userService.list(this.page).toPromise().then(json=>{
      this.userPage = json;
    });
  }

  public delete(id:number) {
    this.userService.delete(id).toPromise().then(json=>{
      let user = json as User;
      super.showToasty(ToastType.info, '角色删除成功', '角色:' + user.name + ' 删除成功');
      this.list();
    }).catch(()=>{
      super.showToasty(ToastType.error, '角色删除失败', '角色正在被使用');
    });
  }
}
