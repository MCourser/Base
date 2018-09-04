import {AppComponent} from './app.component';
import {
  NbActionsModule,
  NbAlertModule,
  NbCardModule,
  NbCheckboxModule,
  NbContextMenuModule,
  NbLayoutModule,
  NbMediaBreakpointsService,
  NbMenuModule,
  NbMenuService,
  NbPopoverModule,
  NbProgressBarModule,
  NbSearchModule,
  NbSidebarModule,
  NbSidebarService,
  NbThemeModule,
  NbUserModule
} from '@nebular/theme';
import {RouterModule, Routes} from '@angular/router';
import {NbMenuInternalService} from '@nebular/theme/components/menu/menu.service';
import {UserService} from './service/user.service';
import {RoleService} from './service/role.service';
import {PermissionService} from './service/permission.service';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './components/login/login.component';
import {UserComponent} from './components/home/user/user/user.component';
import {UserEditComponent} from './components/home/user/user/user-edit/user-edit.component';
import {UserListComponent} from './components/home/user/user/user-list/user-list.component';
import {RoleComponent} from './components/home/user/role/role.component';
import {RoleListComponent} from './components/home/user/role/role-list/role-list.component';
import {PermissionComponent} from './components/home/user/permission/permission.component';
import {HomeComponent} from './components/home/home.component';
import {RoleEditComponent} from './components/home/user/role/role-edit/role-edit.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToasterModule, ToasterService} from 'angular2-toaster';
import {LogoutComponent} from './components/logout/logout.component';
import {UserProfileComponent} from './components/home/user/user/user-profile/user-profile.component';
import {FileUploadModule} from 'ng2-file-upload';
import {PictureStaticResourceComponent} from './components/home/static-resource/picture-static-resource/picture-static-resource.component';
import {StaticResourceService} from './service/static-resource.service';
import {PaginationModule, ProgressbarModule, TooltipModule} from 'ngx-bootstrap';
import {AudioStaticResourceComponent} from './components/home/static-resource/audio-static-resource/audio-static-resource.component';
import {MusicPlayerService} from './service/music-player.service';
import {VideoStaticResourceComponent} from './components/home/static-resource/video-static-resource/video-static-resource.component';
import {ImageViewerModule} from 'ngx-image-viewer';
import {UiSwitchModule} from 'ngx-toggle-switch';


const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'logout',
    component: LogoutComponent
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
            path: 'profile',
            component: UserProfileComponent,
          },
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
      },
      {
        path: 'static-resource',
        children: [
          {
            path: 'picture',
            component: PictureStaticResourceComponent,
          },
          {
            path: 'audio',
            component: AudioStaticResourceComponent,
          },
          {
            path: 'video',
            component: VideoStaticResourceComponent,
          },
        ]
      }
    ]
  }
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    UserEditComponent,
    UserListComponent,
    RoleComponent,
    RoleEditComponent,
    RoleListComponent,
    PermissionComponent,
    HomeComponent,
    LogoutComponent,
    UserProfileComponent,
    PictureStaticResourceComponent,
    AudioStaticResourceComponent,
    VideoStaticResourceComponent,
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes, {useHash: true}),
    NbThemeModule.forRoot({name: 'corporate'}),
    NbLayoutModule,
    NbSidebarModule,
    NbMenuModule,
    ToasterModule,
    NbActionsModule,
    NbAlertModule,
    NbCardModule,
    NbUserModule,
    NbPopoverModule,
    NbSearchModule,
    NbContextMenuModule,
    NbCheckboxModule,
    FileUploadModule,
    NbProgressBarModule,
    PaginationModule.forRoot(),
    TooltipModule.forRoot(),
    ProgressbarModule.forRoot(),
    ImageViewerModule.forRoot(),
    UiSwitchModule
  ],
  providers: [
    NbSidebarService,
    NbMenuService,
    NbMenuInternalService,
    UserService,
    RoleService,
    PermissionService,
    ToasterService,
    NbMediaBreakpointsService,
    StaticResourceService,
    MusicPlayerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
