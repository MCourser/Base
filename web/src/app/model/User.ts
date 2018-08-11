/**
 * Created by cma2 on 17/7/12.
 */
export class User {
  public id = 0;
  public name = '';
  public password = '';
  public roles: Role[] = [];
}

export class Role {
  public id = 0;
  public name = '';
  public description = '';
  public permissions: Permission[] = [];
}

export class Permission {
  public id = 0;
  public value = '';
  public resource = '';
  public description = '';
}
