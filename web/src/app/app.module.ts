import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {UserService} from "./service/user.service";
import {PermissionService} from "./service/permission.service";
import {
  AlertModule,
  BsDropdownConfig,
  BsDropdownModule,
  BsDropdownState,
  ComponentLoaderFactory,
  ModalModule,
  PaginationModule,
  PositioningService,
  TooltipModule
} from "ngx-bootstrap";
import {ToastyModule} from 'ng2-toasty';
import {PermissionComponent} from "./user/permission/permission.component";
import {RoleComponent} from "./user/role/role.component";
import {UserComponent} from "./user/user/user.component";
import {RoleService} from "./service/role.service";
import {RoleListComponent} from './user/role/role-list/role-list.component';
import {RoleEditComponent} from './user/role/role-edit/role-edit.component';
import {UserListComponent} from './user/user/user-list/user-list.component';
import {UserEditComponent} from './user/user/user-edit/user-edit.component';
import {HttpClientModule} from "@angular/common/http";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'permission',
        component: PermissionComponent
      },
      {
        path: 'role',
        component: RoleComponent,
        children: [
          {
            path: 'list',
            component: RoleListComponent,
          },
          {
            path: 'add',
            component: RoleEditComponent,
          },
          {
            path: ':id/update',
            component: RoleEditComponent,
          }
        ]
      },
      {
        path: 'user',
        component: UserComponent,
        children: [
          {
            path: 'list',
            component: UserListComponent,
          },
          {
            path: 'add',
            component: UserEditComponent,
          },
          {
            path: ':id/update',
            component: UserEditComponent,
          }
        ]
      }
    ]
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    PermissionComponent,
    RoleComponent,
    UserComponent,
    RoleListComponent,
    RoleEditComponent,
    UserListComponent,
    UserEditComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BsDropdownModule,
    ToastyModule.forRoot(),
    ModalModule.forRoot(),
    TooltipModule.forRoot(),
    AlertModule.forRoot(),
    PaginationModule.forRoot(),
    HttpClientModule,
    RouterModule.forRoot(routes, {useHash: true})
  ],
  providers: [
    ComponentLoaderFactory,
    PositioningService,
    BsDropdownConfig,
    BsDropdownState,
    UserService,
    RoleService,
    PermissionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
