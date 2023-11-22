
CREATE TABLE `hashtag` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) NOT NULL,
                           PRIMARY KEY (`id`)
);

CREATE TABLE `post_hashtags` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `post_id` bigint DEFAULT NULL,
                                 `hashtag_id` int DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `post_id` (`post_id`),
                                 KEY `hashtag_id` (`hashtag_id`),
                                 FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
                                 FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`id`)
);