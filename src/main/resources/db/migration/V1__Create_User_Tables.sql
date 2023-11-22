CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(50) DEFAULT NULL,
                        `last_name` varchar(50) DEFAULT NULL,
                        `phone_number` varchar(20) DEFAULT NULL,
                        `email_address` varchar(100) DEFAULT NULL,
                        `birthday` date DEFAULT NULL,
                        `profile_picture` varchar(255) DEFAULT NULL,
                        `bio` text,
                        `location` varchar(50) DEFAULT NULL,
                        `current_job` varchar(50) DEFAULT NULL,
                        `follow_list` text,
                        `following_list` text,
                        `role` int DEFAULT NULL,
                        `password_hash` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
);

CREATE TABLE `PersonalAccount` (
                           `personal_specific_field` varchar(255) DEFAULT NULL,
                           `id` int DEFAULT NULL,
                           KEY `FK_personal_account_user` (`id`),
                           FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `BusinessAccount` (
                                   `id` int DEFAULT NULL,
                                   KEY `FK_business_account_user` (`id`),
                                   FOREIGN KEY (`id`) REFERENCES `user` (`id`)
)










