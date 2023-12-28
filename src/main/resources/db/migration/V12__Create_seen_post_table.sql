CREATE TABLE seen_posts (
                            id BIGINT PRIMARY KEY,
                            user_id int NOT NULL,
                            post_id BIGINT NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES user(id),
                            FOREIGN KEY (post_id) REFERENCES post(id)
);