import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {UserService} from "../service/user.service";
import {BaseCompoent} from "../BaseCompoent";
import {ToastyService} from "ng2-toasty";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent extends BaseCompoent implements OnInit {

  constructor(
    protected router: Router,
    protected toastyService: ToastyService,
    protected userService: UserService,
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  public logout() {
    this.userService.logout().toPromise().then(()=>{
      this.userService.currentUser = null;
      this.router.navigate(['/login']);
    });
  }
}
