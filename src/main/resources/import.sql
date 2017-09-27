CREATE TABLE `pub_authorities` (
  `authority_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `authority_name` varchar(40) COLLATE utf8_bin NOT NULL,
  `authority_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `enabled` int(10) NOT NULL,
  `issys` int(10) NOT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_authorities` VALUES ('1', 'ROLE_ADMIN', '首页广告条管理', '1', '0');
INSERT INTO `pub_authorities` VALUES ('2', 'ROLE_USER', '首页', '1', '0');


CREATE TABLE `pub_resources` (
  `resource_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `resource_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `resource_type` varchar(40) COLLATE utf8_bin NOT NULL,
  `priority` int(10) NOT NULL,
  `resource_string` varchar(200) COLLATE utf8_bin NOT NULL,
  `resource_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `enabled` int(10) NOT NULL,
  `issys` int(10) NOT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_resources` VALUES ('1', '首页广告条管理', 'action', '0', '/funcPages/adManager', '首页广告条管理', '1', '0');
INSERT INTO `pub_resources` VALUES ('2', '首页', 'action', '0', '/index', '首页', '1', '0');

CREATE TABLE `pub_authorities_resources` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `authority_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `resource_id` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_res_aut` (`authority_id`),
  KEY `fk_res` (`resource_id`),
  CONSTRAINT `fk_res` FOREIGN KEY (`resource_id`) REFERENCES `pub_resources` (`resource_id`),
  CONSTRAINT `fk_res_aut` FOREIGN KEY (`authority_id`) REFERENCES `pub_authorities` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_authorities_resources` VALUES ('1', '1', '1');
INSERT INTO `pub_authorities_resources` VALUES ('2', '2', '2');

CREATE TABLE `pub_roles` (
  `role_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `enabled` int(10) NOT NULL,
  `issys` int(10) NOT NULL COMMENT '角色表',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_roles` VALUES ('1', 'ROLE_ADMIN', '系统登录', '1', '0');
INSERT INTO `pub_roles` VALUES ('2', 'ROLE_USER', '普通用户', '1', '0');


CREATE TABLE `pub_roles_authorities` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `authority_id` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_aut_role` (`role_id`),
  KEY `fk_aut` (`authority_id`),
  CONSTRAINT `fk_aut` FOREIGN KEY (`authority_id`) REFERENCES `pub_authorities` (`authority_id`),
  CONSTRAINT `fk_aut_role` FOREIGN KEY (`role_id`) REFERENCES `pub_roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_roles_authorities` VALUES ('1', '1', '1');
INSERT INTO `pub_roles_authorities` VALUES ('2', '1', '2');
INSERT INTO `pub_roles_authorities` VALUES ('3', '2', '2');

CREATE TABLE `pub_users` (
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_account` varchar(30) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(40) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(100) COLLATE utf8_bin NOT NULL,
  `enabled` int(10) NOT NULL,
  `issys` int(10) NOT NULL,
  `user_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户表',
  `last_password_reset_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_users` (`user_id`, `user_account`, `user_name`, `user_password`, `enabled`, `issys`, `user_desc`, `last_password_reset_date`) VALUES ('1', 'admin', 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', '1', '1', '超级管理员', '2017-09-26 15:10:50');
INSERT INTO `pub_users` (`user_id`, `user_account`, `user_name`, `user_password`, `enabled`, `issys`, `user_desc`, `last_password_reset_date`) VALUES ('2', 'user', 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', '1', '0', '普通用户', '2017-09-26 15:10:54');

CREATE TABLE `pub_users_roles` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '角色和用户中间表',
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_role` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `pub_roles` (`role_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `pub_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `pub_users_roles` VALUES ('1', '1', '1');
INSERT INTO `pub_users_roles` VALUES ('2', '2', '2');