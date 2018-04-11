import {Component, OnInit} from "@angular/core";
import {Role, User} from "../../../model/User";
import {RoleService} from "../../../service/role.service";
import {BaseCompoent, ToastType} from "../../../BaseCompoent";
import {ToastyService} from "ng2-toasty";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../service/user.service";

@Component({
  selector: 'app-user-edit',
  templateUrl: 'user-edit.component.html',
  styleUrls: ['user-edit.component.css']
})
export class UserEditComponent extends BaseCompoent implements OnInit {
  public isEditMode:boolean = false;
  public roleList: Role[] = [];
  public userForm = {
    id: 0,
    name: '',
    password: '',
    roles: []
  };

  constructor(
    protected router: Router,
    protected toastyService: ToastyService,
    protected userService: UserService,
    private roleService:RoleService,
    private activatedRoute: ActivatedRoute
  ) {
    super(router, toastyService, userService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if(id) {
        this.isEditMode = true;
        this.loadEditMode(id);
      } else {
        this.isEditMode = false;
        this.loadAddModel();
      }
    });
  }

  public loadAddModel() {
    this.roleService.list().toPromise().then(json=>{
      let roles = json as Role[];
      this.roleList = roles;
      this.roleList.forEach(role=>{
        role['isSelected'] = false;
      });
    });
  }

  public loadEditMode(id:number) {
    this.roleService.list().toPromise().then(json=>{
      let roles = json as Role[];
      this.roleList = roles;
      this.userService.load(id).toPromise().then(json=>{
        let user = json as User;
        this.userForm.id = user.id;
        this.userForm.name = user.name;

        this.roleList.forEach(role=>{
          user.roles.forEach(userRole=>{
            if(role.id==userRole.id) {
              role['isSelected'] = true;
              this.userForm.roles.push(role.id);
            }
          })
        });
      });
    });
  }

  public submit(){
    this.userForm.roles = [];
    this.roleList.filter(role=>{
      return role['isSelected'];
    }).forEach(role=>{
      this.userForm.roles.push(role.id);
    });

    if(!this.isEditMode) {
      delete this.userForm.id;
      this.userService.create(this.userForm).toPromise().then(json=>{
        let user = json as User;
        super.showToasty(ToastType.info, '用户创建成功', '用户:' + user.name + ' 创建成功');
        this.router.navigate(['/home/user/list']);
      }).catch(error=>{
        super.showToasty(ToastType.error, '用户创建失败', '请检查输入是否正确');
      });
    } else {
      this.userService.update(this.userForm).toPromise().then(json=>{
        let user = json as User;
        super.showToasty(ToastType.info, '用户更新成功', '用户:' + user.name + ' 更新成功');
        this.router.navigate(['/home/user/list']);
      }).catch(error=>{
        super.showToasty(ToastType.error, '用户更新失败', '请检查输入是否正确');
      });
    }
  }

}
