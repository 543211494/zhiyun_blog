/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 17/08/2022 11:08:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_author_id` int(0) UNSIGNED NOT NULL COMMENT '作者ID',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `article_update_time` datetime(0) NOT NULL COMMENT '更新时间，默认当前时间',
  `article_create_time` datetime(0) NOT NULL COMMENT '创建时间，默认当前时间',
  `article_summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要，默认正文内容截取',
  `article_thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '缩略图，默认图片地址',
  `article_visible` int(0) NOT NULL COMMENT '文章是否审核通过，1通过，0未通过',
  `article_deleted` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除，0未删除，1已删除',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `article_author_id`(`article_author_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_category_ref
-- ----------------------------
DROP TABLE IF EXISTS `article_category_ref`;
CREATE TABLE `article_category_ref`  (
  `article_id` int(0) NOT NULL COMMENT '文章ID',
  `category_id` int(0) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`article_id`, `category_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_tag_ref
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_ref`;
CREATE TABLE `article_tag_ref`  (
  `article_id` int(0) NOT NULL COMMENT '文章ID',
  `tag_id` int(0) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`article_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_view
-- ----------------------------
DROP TABLE IF EXISTS `article_view`;
CREATE TABLE `article_view`  (
  `article_id` int(0) NOT NULL COMMENT '文章ID',
  `date` date NOT NULL COMMENT '日期2022/8/15',
  `view_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '阅读量',
  PRIMARY KEY (`article_id`, `date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `category_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `category_create_time` datetime(0) NOT NULL COMMENT '创建时间，默认当前时间',
  `category_update_time` datetime(0) NOT NULL COMMENT '更新时间，默认当前时间',
  `category_deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否已删除,1已删除',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `category_name`(`category_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `user_id` int(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `article_id` int(0) NOT NULL COMMENT '文章ID',
  `collection_create_time` datetime(0) NOT NULL COMMENT '收藏时间',
  PRIMARY KEY (`user_id`, `article_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `comment_pid` int(0) NOT NULL DEFAULT -1 COMMENT '上级评论ID,默认-1',
  `comment_article_id` int(0) NOT NULL COMMENT '文章ID',
  `comment_author_id` int(0) UNSIGNED NOT NULL COMMENT '评论人ID',
  `comment_user_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '评论对象ID,可能为空',
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `comment_create_time` datetime(0) NOT NULL COMMENT '评论创建时间',
  `comment_deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否已删除，1已删除',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `comment_article_id`(`comment_article_id`) USING BTREE,
  INDEX `comment_author_id`(`comment_author_id`) USING BTREE,
  INDEX `comment_user_id`(`comment_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `message_pid` int(0) NOT NULL DEFAULT -1 COMMENT '留言父ID，默认-1',
  `message_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `mesaage_author_id` int(0) UNSIGNED NOT NULL COMMENT '留言者ID',
  `message_create_time` datetime(0) NOT NULL COMMENT '留言创建时间，默认当前时间',
  `message_deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否已删除，1已删除',
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `mesaage_author_id`(`mesaage_author_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_publisher_id` int(0) UNSIGNED NOT NULL COMMENT '公告发布者ID',
  `notice_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `notice_order` int(0) NOT NULL COMMENT '公告优先级，1紧急公告，2普通公告',
  `notice_create_time` datetime(0) NOT NULL COMMENT '公告创建时间，默认当前时间',
  `notice_update_time` datetime(0) NOT NULL COMMENT '公告修改时间，默认当前时间',
  `notice_deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否已删除，1已删除',
  PRIMARY KEY (`notice_id`) USING BTREE,
  INDEX `notice_publisher_id`(`notice_publisher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `score_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评分ID',
  `score_user_id` int(0) UNSIGNED NOT NULL COMMENT '评分人ID',
  `score_article_id` int(0) NOT NULL COMMENT '评分文章ID',
  `score_number` int(0) NOT NULL DEFAULT 0 COMMENT '评分数值0~5，默认0',
  `score_create_time` datetime(0) NOT NULL COMMENT '评分时间',
  PRIMARY KEY (`score_id`) USING BTREE,
  UNIQUE INDEX `score_user_id`(`score_user_id`, `score_article_id`) USING BTREE COMMENT '单个用户不能重复评分',
  INDEX `score_article_id`(`score_article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `tag_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `tag_create_time` datetime(0) NOT NULL COMMENT '标签创建时间，默认当前时间',
  `tag_update_time` datetime(0) NOT NULL COMMENT '标签修改时间，默认当前时间',
  `tag_deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否已删除，1已删除，0未删除',
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `tag_name`(`tag_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_register_time` datetime(0) NOT NULL COMMENT '注册时间，默认当前时间',
  `user_lastLogin_time` datetime(0) NOT NULL COMMENT '用户最近登录时间，默认当前时间',
  `user_deleted` int(0) NOT NULL DEFAULT 0 COMMENT '用户是否删除，1已删除',
  `user_role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'USER' COMMENT '用户角色，SUPER/ADMIN/USER,默认USER',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_nickname`(`user_nickname`) USING BTREE COMMENT '昵称不能重复'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

