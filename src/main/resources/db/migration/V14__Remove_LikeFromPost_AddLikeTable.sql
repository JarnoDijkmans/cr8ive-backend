ALTER TABLE post
DROP COLUMN likes;


CREATE TABLE Likes (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       post_id BIGINT NOT NULL,
                       user_id int NOT NULL,
                       like_date TIMESTAMP NOT NULL,
                       FOREIGN KEY (post_id) REFERENCES Post(id)
);