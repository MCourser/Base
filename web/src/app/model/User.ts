/**
 * Created by cma2 on 17/7/12.
 */
export class User {
  public id: number = 0;
  public name: string = '';
  public password: string = '';
  public roles: Role[] = [];
}

export class Role {
  public id: number = 0;
  public name: string = '';
  public description: string = '';
  public permissions: Permission[] = [];
}

export class Permission {
  public id: number = 0;
  public value: string = '';
  public resource: string = '';
  public description: string = '';
}
