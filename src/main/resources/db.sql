delete from roles;
delete from users;

 INSERT INTO users(username,PASSWORD, email, enabled)
 VALUES ('admin','$2a$04$t6h3xixKUdaNFmmue3AM9e5cvXqkj8zEjWdWZh1SelhqFDOpKzJXS', TRUE);
 INSERT INTO users(username,PASSWORD,enabled)
 VALUES ('user','$2a$04$3fBTjewynEO6np/JwHSgheobBwe5lV0njyLazoF5peu276Nm6nu.a', TRUE);

 INSERT INTO user_roles (username, role)
 VALUES ('admin', 'ADMIN', 1);
 INSERT INTO user_roles (username, role)
 VALUES ('user', 'USER', 2);
