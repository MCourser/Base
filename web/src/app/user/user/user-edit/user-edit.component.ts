import {Component, OnInit} from "@angular/core";
import {Role, User} from "../../../model/User";
import {PermissionService} from "../../../service/permission.service";
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
  private isEditMode:boolean = false;
  private roleList: Role[] = [];
  private userForm = {
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
    this.roleService.list().map(resp=>resp.json() as Role[]).subscribe(
      roles=>{
        this.roleList = roles;
        this.roleList.forEach(role=>{
          role['isSelected'] = false;
        })
      }
    );
  }

  public loadEditMode(id:number) {
    this.roleService.list().map(resp=>resp.json() as Role[]).subscribe(
      roles=>{
        this.roleList = roles;
        this.userService.load(id).map(resp=>resp.json() as User).subscribe(
          user=>{
            this.userForm.id = user.id;
            this.userForm.name = user.name;

            this.roleList.forEach(role=>{
              role['isSelected'] = false;
            })
          }
        );
      }
    );
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
      this.userService.create(this.userForm).map(resp=>resp.json() as User).subscribe(
        user=>{
          super.showToasty(ToastType.info, '用户创建成功', '用户:' + user.name + ' 创建成功');
          this.router.navigate(['/home/user/list']);
        },
        error=> {
          super.showToasty(ToastType.error, '用户创建失败', '请检查输入是否正确');
        }
      );
    } else {

    }
  }

}
