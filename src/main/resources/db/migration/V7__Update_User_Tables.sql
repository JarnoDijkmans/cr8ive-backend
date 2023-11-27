DROP TABLE IF EXISTS `personal_account`;

-- Create a new table with desired columns
CREATE TABLE `personal_account` (
                                   `id` int PRIMARY KEY,
                                   `user_id` int,
                                   FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);


DROP TABLE IF EXISTS `business_account`;

CREATE TABLE `business_account` (
                                    `id` int PRIMARY KEY,
                                    `user_id` int,
                                    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);