import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private router: Router,
    private userService: UserService) {
  }

  ngOnInit() {
    this.logout();
  }


  public logout() {
    this.userService.logout().toPromise().then(() => {
      this.userService.currentUser = null;
      this.router.navigate(['/login']);
    });
  }
}
