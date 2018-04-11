import {Component, OnInit} from "@angular/core";
import { Router } from '@angular/router';
import {UserService} from "../service/user.service";
import {User} from "../model/User";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public userLoginForm = {
    username: '',
    password: ''
  };

  private isLoginFail = false;

  constructor(
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.userService.me().subscribe(
      resp => {
        if(resp.status == 200) {
          this.router.navigate(['/home']);
        }
      }
    );
  }

  public login() {
    this.userService.login(this.userLoginForm.username, this.userLoginForm.password).
      subscribe(
        resp=>{
          if(resp.status == 200) {
            this.isLoginFail = false;
            this.router.navigate(['/home']);
          }
        },
        error=>{
          this.isLoginFail = true;
        }
      );
}

}
