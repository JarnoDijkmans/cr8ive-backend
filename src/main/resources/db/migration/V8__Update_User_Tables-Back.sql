DROP TABLE IF EXISTS `personal_account`;

-- Create a new table with desired columns
CREATE TABLE personal_account (
                                  user_id INT PRIMARY KEY,
                                  FOREIGN KEY (user_id) REFERENCES user (id)
);

DROP TABLE IF EXISTS `business_account`;

CREATE TABLE `business_account` (
                                    user_id INT PRIMARY KEY,
                                    FOREIGN KEY (user_id) REFERENCES user (id)
);