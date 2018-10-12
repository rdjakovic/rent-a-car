-- DELETE FROM roles;
-- DELETE FROM users;
-- DELETE FROM users_roles;

INSERT IGNORE INTO users(username,PASSWORD, email, enabled, firstname, lastname)
VALUES ('admin','$2a$04$t6h3xixKUdaNFmmue3AM9e5cvXqkj8zEjWdWZh1SelhqFDOpKzJXS', 'admin@test.com', 1, 'Admin', 'Adminovic');
INSERT IGNORE INTO users(username,PASSWORD, email, enabled, firstname, lastname)
VALUES ('user','$2a$04$3fBTjewynEO6np/JwHSgheobBwe5lV0njyLazoF5peu276Nm6nu.a', 'user@test.com', 1, 'User', 'Userovski');


INSERT IGNORE INTO roles (role) VALUES ('ROLE_ADMIN');
INSERT IGNORE INTO roles (role) VALUES ('ROLE_USER');

INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (1,1);
INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (2,2);