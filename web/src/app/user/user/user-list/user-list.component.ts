import {Component, OnInit} from "@angular/core";
import {Permission, Role} from "../../../model/User";
import {PermissionService} from "../../../service/permission.service";
import {RoleService} from "../../../service/role.service";
import {BaseCompoent, ToastType} from "../../../BaseCompoent";
import {ToastyService} from "ng2-toasty";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../service/user.service";
import {Page} from "../../../model/Page";


@Component({
  selector: 'app-user-list',
  templateUrl: 'user-list.component.html',
  styleUrls: ['user-list.component.css']
})
export class UserListComponent extends BaseCompoent implements OnInit {
  private page: Page = new Page();
  private userPage:any = {};

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
    this.userService.list(this.page).map(resp=>resp.json()).subscribe(
      userPage=>{
        this.userPage = userPage;
      }
    );
  }
}
