DROP TABLE SYS_USER IF EXISTS;

CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '会员id',
                            `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
                            `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
                            `name` varchar(50) DEFAULT NULL COMMENT '姓名',
                            `phone` varchar(11) DEFAULT NULL COMMENT '手机',
                            `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                            `description` varchar(255) DEFAULT NULL COMMENT '描述',
                            `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（1：正常 0：停用）',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';