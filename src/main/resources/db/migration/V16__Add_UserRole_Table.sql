CREATE TABLE moderator_Account (
                                   `id` int DEFAULT NULL,
                                   FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);