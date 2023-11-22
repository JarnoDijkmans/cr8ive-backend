CREATE TABLE `follow` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `follower_id` int DEFAULT NULL,
                          `followed_id` int DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `follower_id` (`follower_id`),
                          KEY `followed_id` (`followed_id`),
                          FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`),
                          FOREIGN KEY (`followed_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `user_follows_user` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `follower_id` int DEFAULT NULL,
                                     `followed_id` int DEFAULT NULL,
                                     PRIMARY KEY (`id`),
                                     KEY `follower_id` (`follower_id`),
                                     KEY `followed_id` (`followed_id`),
                                     FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`),
                                     FOREIGN KEY (`followed_id`) REFERENCES `user` (`id`)
);


