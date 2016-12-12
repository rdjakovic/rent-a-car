delete from roles;
delete from users;
delete from users_roles;

 INSERT INTO users(username,PASSWORD, email, enabled, firstname, lastname)
 VALUES ('admin','$2a$04$t6h3xixKUdaNFmmue3AM9e5cvXqkj8zEjWdWZh1SelhqFDOpKzJXS', 1, 'Admin', 'Adminovic');
 INSERT INTO users(username,PASSWORD,enabled, firstname, lastname)
 VALUES ('user','$2a$04$3fBTjewynEO6np/JwHSgheobBwe5lV0njyLazoF5peu276Nm6nu.a', 1, 'User', 'Userovski');

 INSERT INTO roles (role)
 VALUES ('ROLE_ADMIN');
 INSERT INTO user_roles (username, role)
 VALUES ('ROLE_USER');

 INSERT INTO users_roles (user_id, role_id)
 VALUES (1,1);
 INSERT INTO user_roles (username, role)
 VALUES (2,2);