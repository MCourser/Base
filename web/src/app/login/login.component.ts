import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {UserService} from "../service/user.service";

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

  public isLoginFail = false;

  constructor(
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.userService.me().toPromise().then(()=>{
      this.router.navigate(['/home']);
    });
  }

  public login() {
    this.userService.login(this.userLoginForm.username, this.userLoginForm.password).
    toPromise().then(()=>{
      this.isLoginFail = false;
      this.router.navigate(['/home']);
    }).catch(error=>{
      this.isLoginFail = true;
    });
}

}
