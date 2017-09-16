insert into t_permission(id, value, resource, description) values(1, "permission:list", "/permission/", "list permissions");
insert into t_permission(id, value, resource, description) values(2, "role:add", "/role/", "add role");
insert into t_permission(id, value, resource, description) values(3, "role:delete", "/role/{id}", "delete role");
insert into t_permission(id, value, resource, description) values(4, "role:update", "/role/", "update role");
insert into t_permission(id, value, resource, description) values(5, "role:list", "/role/", "list roles");
insert into t_permission(id, value, resource, description) values(6, "role:load", "/role/{id}", "load role");
insert into t_permission(id, value, resource, description) values(7, "user:add", "/user/", "add users");
insert into t_permission(id, value, resource, description) values(8, "user:delete", "/user/{id}", "delete users");
insert into t_permission(id, value, resource, description) values(9, "user:update", "/user/", "update user");
insert into t_permission(id, value, resource, description) values(10, "user:list", "/user/", "list users");
insert into t_permission(id, value, resource, description) values(11, "user:load", "/user/{id}", "load user");

insert into t_role(id, name, description) values(1, "admin", "system admin");

insert into t_role_permission(role_id, permission_id) values(1, 1);
insert into t_role_permission(role_id, permission_id) values(1, 2);
insert into t_role_permission(role_id, permission_id) values(1, 3);
insert into t_role_permission(role_id, permission_id) values(1, 4);
insert into t_role_permission(role_id, permission_id) values(1, 5);
insert into t_role_permission(role_id, permission_id) values(1, 6);
insert into t_role_permission(role_id, permission_id) values(1, 7);
insert into t_role_permission(role_id, permission_id) values(1, 8);
insert into t_role_permission(role_id, permission_id) values(1, 9);
insert into t_role_permission(role_id, permission_id) values(1, 10);
insert into t_role_permission(role_id, permission_id) values(1, 11);

insert into t_user(id, name, password) values(1, 'admin', '$2a$10$jYsN/CucsC/fsbEewJocJ.agSWigHEnLa69ZmXxVqRbJ6Mihmr3Wy');

insert into t_user_role(user_id, role_id) values(1, 1);

