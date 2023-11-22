CREATE TABLE `post` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `description` varchar(255) DEFAULT NULL,
                        `creation_date` datetime DEFAULT NULL,
                        `likes` bigint DEFAULT NULL,
                        `share_count` bigint DEFAULT NULL,
                        `user_id` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`)
);


CREATE TABLE `post_content` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `post_id` bigint DEFAULT NULL,
                                `file_url` varchar(255) DEFAULT NULL,
                                `extension` varchar(45) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `post_id` (`post_id`),
                                FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
);



