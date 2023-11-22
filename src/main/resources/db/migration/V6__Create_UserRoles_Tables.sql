CREATE TABLE user_role (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           role_name VARCHAR(255) NOT NULL,
                           user_id INT,
                           PRIMARY KEY (id),
                           FOREIGN KEY (user_id) REFERENCES user(id)
);