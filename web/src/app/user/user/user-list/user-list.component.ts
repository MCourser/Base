import {Component, OnInit} from "@angular/core";
import {BaseCompoent} from "../../../BaseCompoent";
import {ToastyService} from "ng2-toasty";
import {Router} from "@angular/router";
import {UserService} from "../../../service/user.service";
import {Page} from "../../../model/Page";


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
    this.userService.list(this.page).toPromise().then(resp=>{
      this.userPage = resp.json();
    });
  }
}
