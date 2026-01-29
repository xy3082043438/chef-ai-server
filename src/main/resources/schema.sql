CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(255) NOT NULL COMMENT '用户名',
  `password` VARCHAR(255) NOT NULL COMMENT '密码（加密后存储）',
  `email` VARCHAR(255) NOT NULL COMMENT '用户邮箱',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `user_preference` (
  `preference_id` INT NOT NULL AUTO_INCREMENT COMMENT '偏好ID',
  `user_id` INT NOT NULL COMMENT '用户ID',
  `preference_name` VARCHAR(255) NOT NULL COMMENT '口味偏好名称',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`preference_id`),
  KEY `idx_preference_user_id` (`user_id`),
  CONSTRAINT `fk_preference_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户口味偏好表';

CREATE TABLE IF NOT EXISTS `user_inventory` (
  `inventory_id` INT NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  `user_id` INT NOT NULL COMMENT '用户ID',
  `food_name` VARCHAR(255) NOT NULL COMMENT '食材名字',
  `quantity` INT NOT NULL COMMENT '食材数量',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`inventory_id`),
  KEY `idx_inventory_user_id` (`user_id`),
  CONSTRAINT `fk_inventory_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食材库存表';

CREATE TABLE IF NOT EXISTS `menu` (
  `menu_id` INT NOT NULL AUTO_INCREMENT COMMENT '菜谱ID',
  `user_id` INT NOT NULL COMMENT '用户ID',
  `menu_name` VARCHAR(255) NOT NULL COMMENT '菜谱名称',
  `ingredients` TEXT NOT NULL COMMENT '食材（JSON格式）',
  `steps` TEXT NOT NULL COMMENT '烹饪步骤（JSON格式）',
  `recommendation` TEXT COMMENT '烹饪建议',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`),
  KEY `idx_menu_user_id` (`user_id`),
  CONSTRAINT `fk_menu_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜谱表';

CREATE TABLE IF NOT EXISTS `menu_image` (
  `image_id` INT NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `menu_id` INT NOT NULL COMMENT '菜谱ID',
  `image_url` VARCHAR(512) NOT NULL COMMENT '成品图链接',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`image_id`),
  UNIQUE KEY `uk_menu_image_menu_id` (`menu_id`),
  CONSTRAINT `fk_menu_image_menu_id`
    FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成品示例图表';
