DROP TABLE IF EXISTS `personal_account`;

-- Create a new table with desired columns
CREATE TABLE personal_account (
                                  id INT,
                                  FOREIGN KEY (id) REFERENCES user (id)
);

DROP TABLE IF EXISTS `business_account`;

CREATE TABLE `business_account` (
                                    id INT NOT NULL,
                                    FOREIGN KEY (id) REFERENCES user (id)
);