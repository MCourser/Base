insert into t_permission(id, value, resource, description) values(1, "permission:list", "/permission/", "list permissions");
insert into t_permission(id, value, resource, description) values(100, "role:add", "/role/", "add role");
insert into t_permission(id, value, resource, description) values(101, "role:delete", "/role/{id}", "delete role");
insert into t_permission(id, value, resource, description) values(102, "role:update", "/role/", "update role");
insert into t_permission(id, value, resource, description) values(103, "role:list", "/role/", "list roles");
insert into t_permission(id, value, resource, description) values(104, "role:load", "/role/{id}", "load role");
insert into t_permission(id, value, resource, description) values(200, "user:add", "/user/", "add users");
insert into t_permission(id, value, resource, description) values(201, "user:delete", "/user/{id}", "delete users");
insert into t_permission(id, value, resource, description) values(202, "user:update", "/user/", "update user");
insert into t_permission(id, value, resource, description) values(203, "user:list", "/user/", "list users");
insert into t_permission(id, value, resource, description) values(204, "user:load", "/user/{id}", "load user");
insert into t_permission(id, value, resource, description) values(300, "static-resource:add", "/static-resource/", "add static resource");
insert into t_permission(id, value, resource, description) values(301, "static-resource:delete", "/static-resource/{id}", "delete static resource");
insert into t_permission(id, value, resource, description) values(302, "static-resource:list", "/static-resource/type/{type}", "list static resources");
insert into t_permission(id, value, resource, description) values(303, "static-resource:load", "/static-resource/{id}", "load static resource");
insert into t_permission(id, value, resource, description) values(304, "static-resource:public-toggle", "/static-resource/{id}/public/toggle", "toggle public or private for static resource");

insert into t_role(id, name, description) values(1, "admin", "system admin");

insert into t_role_permission(role_id, permission_id) values(1, 1);
insert into t_role_permission(role_id, permission_id) values(1, 100);
insert into t_role_permission(role_id, permission_id) values(1, 101);
insert into t_role_permission(role_id, permission_id) values(1, 102);
insert into t_role_permission(role_id, permission_id) values(1, 103);
insert into t_role_permission(role_id, permission_id) values(1, 104);
insert into t_role_permission(role_id, permission_id) values(1, 200);
insert into t_role_permission(role_id, permission_id) values(1, 201);
insert into t_role_permission(role_id, permission_id) values(1, 202);
insert into t_role_permission(role_id, permission_id) values(1, 203);
insert into t_role_permission(role_id, permission_id) values(1, 204);
insert into t_role_permission(role_id, permission_id) values(1, 300);
insert into t_role_permission(role_id, permission_id) values(1, 301);
insert into t_role_permission(role_id, permission_id) values(1, 302);
insert into t_role_permission(role_id, permission_id) values(1, 303);
insert into t_role_permission(role_id, permission_id) values(1, 304);

insert into t_user(id, name, password) values(1, 'admin', '$2a$10$jYsN/CucsC/fsbEewJocJ.agSWigHEnLa69ZmXxVqRbJ6Mihmr3Wy');
insert into t_user(id, name, password) values(2, 'test', '$2a$10$jYsN/CucsC/fsbEewJocJ.agSWigHEnLa69ZmXxVqRbJ6Mihmr3Wy');

insert into t_user_role(user_id, role_id) values(1, 1);
insert into t_user_role(user_id, role_id) values(2, 1);

