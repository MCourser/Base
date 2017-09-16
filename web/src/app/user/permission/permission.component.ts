import { Component, OnInit } from '@angular/core';
import {PermissionService} from "../../service/permission.service";
import {Permission} from "../../model/User";

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissionComponent implements OnInit {

  public permissionList: Permission[] = [];

  constructor(private permissionService: PermissionService) { }

  ngOnInit() {
    this.list();
  }

  public list() {
    this.permissionService.list().map(resp=>resp.json() as Permission[]).subscribe(
      permissions=> {
        this.permissionList = permissions;
      }
    );
  }

}
