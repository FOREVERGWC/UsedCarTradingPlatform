/*
 Navicat Premium Dump SQL

 Source Server         : MySQL8
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3306
 Source Schema         : 博客系统

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 27/10/2024 15:07:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_article
-- ----------------------------
DROP TABLE IF EXISTS `biz_article`;
CREATE TABLE `biz_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `category_id` bigint NOT NULL COMMENT '类别ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `user_id` bigint NOT NULL COMMENT '作者ID',
  `view_count` bigint NOT NULL COMMENT '浏览量',
  `like_count` bigint NOT NULL COMMENT '点赞量',
  `dislike_count` bigint NOT NULL COMMENT '点踩量',
  `comment_count` bigint NOT NULL COMMENT '评论量',
  `collection_count` bigint NOT NULL COMMENT '收藏量',
  `top` tinyint(1) NOT NULL COMMENT '置顶(0否、1是)',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '可见性(0私有、1公开)',
  `commentable` tinyint(1) NOT NULL COMMENT '允许评论(0否、1是)',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0未发布、1已发布、2定时发布)',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章表';

-- ----------------------------
-- Records of biz_article
-- ----------------------------
BEGIN;
INSERT INTO `biz_article` (`id`, `title`, `category_id`, `content`, `user_id`, `view_count`, `like_count`, `dislike_count`, `comment_count`, `collection_count`, `top`, `visible`, `commentable`, `status`, `release_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, '测试标题1', 9, '# 一级标题\n## 二级标题\n### 三级标题\n# 一级标题\n## 二级标题\n### 三级标题\n#### 四级标题\n# 一级标题\n## 二级标题\n', 3, 0, 0, 0, 0, 0, 0, '0', 0, '1', '2024-08-29 00:00:00', '1', '2024-08-16 01:39:33', '1', '2024-08-16 02:26:05', '');
INSERT INTO `biz_article` (`id`, `title`, `category_id`, `content`, `user_id`, `view_count`, `like_count`, `dislike_count`, `comment_count`, `collection_count`, `top`, `visible`, `commentable`, `status`, `release_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, '测试2', 10, '测试2', 3, 0, 0, 0, 0, 0, 1, '0', 0, '1', '2024-08-16 01:58:44', '1', '2024-08-16 01:58:44', '1', '2024-08-19 19:11:26', '');
INSERT INTO `biz_article` (`id`, `title`, `category_id`, `content`, `user_id`, `view_count`, `like_count`, `dislike_count`, `comment_count`, `collection_count`, `top`, `visible`, `commentable`, `status`, `release_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, '测试3', 11, '　　东京也无非是这样。上野的樱花烂漫的时节，望去确也像绯红的轻云，但花下也缺不了成群结队的“清国留学生”的速成班，头顶上盘着大辫子，顶得学生制帽的顶上高高耸起，形成一座富士山。也有解散辫子，盘得平的，除下帽来，油光可鉴，宛如小姑娘的发髻一般，还要将脖子扭几扭。实在标致极了。\n \n　　中国留学生会馆的门房里有几本书卖，有时还值得去一转；倘在上午，里面的几间洋房里倒也还可以坐坐的。但到傍晚，有一间的地板便常不免要咚咚咚地响得震天，兼以满房烟尘斗乱；问问精通时事的人，答道：“那是在学跳舞。”\n\n　　到别的地方去看看，如何呢？\n \n　　我就往仙台的医学专门学校去。从东京出发，不久便到一处驿站，写道：日暮里。不知怎地，我到现在还记得这名目。其次却只记得水户了，这是明的遗民朱舜水先生客死的地方。仙台是一个市镇，并不大；冬天冷得利害；还没有中国的学生。\n \n　　大概是物以稀为贵罢。北京的白菜运往浙江，便用红头绳系住菜根，倒挂在水果店头，尊为“胶菜”；福建野生着的芦荟，一到北京就请进温室，且美其名曰“龙舌兰”。我到仙台也颇受了这样的优待，不但学校不收学费，几个职员还为我的食宿操心。我先是住在监狱旁边一个客店里的，初冬已经颇冷，蚊子却还多，后来用被盖了全身，用衣服包了头脸，只留两个鼻孔出气。在这呼吸不息的地方，蚊子竟无从插嘴，居然睡安稳了。饭食也不坏。但一位先生却以为这客店也包办囚人的饭食，我住在那里不相宜，几次三番，几次三番地说。我虽然觉得客店兼办囚人的饭食和我不相干，然而好意难却，也只得别寻相宜的住处了。于是搬到别一家，离监狱也很远，可惜每天总要喝难以下咽的芋梗汤。\n \n　　从此就看见许多陌生的先生，听到许多新鲜的讲义。解剖学是两个教授分任的。最初是骨学。其时进来的是一个黑瘦的先生，八字须，戴着眼镜，挟着一叠大大小小的书。一将书放在讲台上，便用了缓慢而很有顿挫的声调，向学生介绍自己道：\n \n　　“我就是叫做藤野严九郎的……”\n \n　　后面有几个人笑起来了。他接着便讲述解剖学在日本发达的历史，那些大大小小的书，便是从最初到现今关于这一门学问的著作。起初有几本是线装的；还有翻刻中国译本的，他们的翻译和研究新的医学，并不比中国早。\n \n　　那坐在后面发笑的是上学年不及格的留级学生，在校已经一年，掌故颇为熟悉的了。他们便给新生讲演每个教授的历史。这藤野先生，据说是穿衣服太模糊了，有时竟会忘记带领结；冬天是一件旧外套，寒颤颤的，有一回上火车去，致使管车的疑心他是扒手，叫车里的客人大家小心些。\n \n　　他们的话大概是真的，我就亲见他有一次上讲堂没有带领结。\n \n　　过了一星期，大约是星期六，他使助手来叫我了。到得研究室，见他坐在人骨和许多单独的头骨中间——他其时正在研究着头骨，后来有一篇论文在本校的杂志上发表出来。\n \n　　“我的讲义，你能抄下来吗？”他问。\n \n　　“可以抄一点。”\n \n　　“拿来我看！”\n \n　　我交出所抄的讲义去，他收下了，第二三天便还我，并且说，此后每一星期要送给他看一回。我拿下来打开看时，很吃了一惊，同时也感到一种不安和感激。原来我的讲义已经从头到末，都用红笔添改过了，不但增加了许多脱漏的地方，连文法的错误也都一一订正。这样一直继续到教完了他所担任的功课：骨学、血管学、神经学。\n \n　　可惜我那时太不用功，有时也很任性。还记得有一回藤野先生将我叫到他的研究室里去，翻出我那讲义上的一个图来，是下臂的血管，指着，向我和蔼地说道：\n \n　　“你看，你将这条血管移了一点位置了——自然，这样一移，的确比较好看些，然而解剖图不是美术，实物是那么样的，我们没法改换它。现在我给你改好了，以后你要全照着黑板上那样的画。”\n \n　　但是我还不服气，口头答应着，心里却想道：\n \n　　“图还是我画的不错；至于实在的情形，我心里自然记得的。”\n \n　　学年试验完毕之后，我便到东京玩了一夏天，秋初再回学校，成绩早已发表了，同学一百余人之中，我在中间，不过是没有落第。这回藤野先生所担任的功课，是解剖实习和局部解剖学。\n \n　　解剖实习了大概一星期，他又叫我去了，很高兴地，仍用了极有抑扬的声调对我说道：\n \n　　“我因为听说中国人是很敬重鬼的，所以很担心，怕你不肯解剖尸体。现在总算放心了，没有这回事。”\n \n　　但他也偶有使我很为难的时候。他听说中国的女人是裹脚的，但不知道详细，所以要问我怎么裹法，足骨变成怎样的畸形，还叹息道：“总要看一看才知道。究竟是怎么一回事呢？”\n \n　　有一天，本级的学生会干事到我寓里来了，要借我的讲义看。我检出来交给他们，却只翻检了一通，并没有带走。但他们一走，邮差就送到一封很厚的信，拆开看时，第一句是：\n \n　　“你改悔罢！”\n \n　　这是《新约》上的句子罢，但经托尔斯泰新近引用过的。其时正值日俄战争，托老先生便写了一封给俄国和日本的皇帝的信，开首便是这一句。日本报纸上很斥责他的不逊，爱国青年也愤然，然而暗地里却早受了他的影响了。其次的话，大略是说上年解剖学试验的题目，是藤野先生讲义上做了记号，我预先知道的，所以能有这样的成绩。末尾是匿名。\n \n　　我这才回忆到前几天的一件事。因为要开同级会，干事便在黑板上写广告，末一句是“请全数到会勿漏为要”，而且在“漏”字旁边加了一个圈。我当时虽然觉到圈得可笑，但是毫不介意，这回才悟出那字也在讥刺我了，犹言我得了教员漏泄出来的题目。\n \n　　我便将这事告知了藤野先生；有几个和我熟识的同学也很不平，一同去诘责干事托辞检查的无礼，并且要求他们将检查的结果发表出来。终于这流言消灭了，干事却又竭力运动，要收回那一封匿名信去。结末是我便将这托尔斯泰式的信退还了他们。\n \n　　中国是弱国，所以中国人当然是低能儿，分数在六十分以上，便不是自己的能力了：也无怪他们疑惑。但我接着便有参观枪毙中国人的命运了。第二年添教霉菌学，细菌的形状是全用电影来显示的，一段落已完而还没有到下课的时候，便影几片时事的片子，自然都是日本战胜俄国的情形。但偏有中国人夹在里边：给俄国人做侦探，被日本军捕获，要枪毙了，围着看的也是一群中国人；在讲堂里的还有一个我。\n \n　　“万岁！”他们都拍掌欢呼起来。\n \n　　这种欢呼，是每看一片都有的，但在我，这一声却特别听得刺耳。此后回到中国来，我看见那些闲看枪毙犯人的人们，他们也何尝不酒醉似的喝彩——呜呼，无法可想！但在那时那地，我的意见却变化了。\n \n　　到第二学年的终结，我便去寻藤野先生，告诉他我将不学医学，并且离开这仙台。他的脸色仿佛有些悲哀，似乎想说话，但竟没有说。\n \n　　“我想去学生物学，先生教给我的学问，也还有用的。”其实我并没有决意要学生物学，因为看得他有些凄然，便说了一个慰安他的谎话。\n \n　　“为医学而教的解剖学之类，怕于生物学也没有什么大帮助。”他叹息说。\n \n　　将走的前几天，他叫我到他家里去，交给我一张照相，后面写着两个字道“惜别”，还说希望将我的也送他。但我这时适值没有照相了；他便叮嘱我将来照了寄给他，并且时时通信告诉他此后的状况。\n \n　　我离开仙台之后，就多年没有照过相，又因为状况也无聊，说起来无非使他失望，便连信也怕敢写了。经过的年月一多，话更无从说起，所以虽然有时想写信，却又难以下笔，这样的一直到现在，竟没有寄过一封信和一张照片。从他那一面看起来，是一去之后，杳无消息了。\n \n　　但不知怎地，我总还时时记起他，在我所认为我师的之中，他是最使我感激，给我鼓励的一个。有时我常常想：他的对于我的热心的希望，不倦的教诲，小而言之，是为中国，就是希望中国有新的医学；大而言之，是为学术，就是希望新的医学传到中国去。他的性格，在我的眼里和心里是伟大的，虽然他的姓名并不为许多人所知道。\n \n　　他所改正的讲义，我曾经订成三厚本，收藏着的，将作为永久的纪念。不幸七年前迁居的时候，中途毁坏了一口书箱，失去半箱书，恰巧这讲义也遗失在内了。责成运送局去找寻，寂无回信。只有他的照相至今还挂在我北京寓居的东墙上，书桌对面。每当夜间疲倦，正想偷懒时，仰面在灯光中瞥见他黑瘦的面貌，似乎正要说出抑扬顿挫的话来，便使我忽又良心发现，而且增加勇气了，于是点上一支烟，再继续写些为“正人君子”之流所深恶痛疾的文字。', 3, 10, 0, 0, 0, 0, 0, '1', 1, '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '1', '2024-09-02 22:20:16', '');
INSERT INTO `biz_article` (`id`, `title`, `category_id`, `content`, `user_id`, `view_count`, `like_count`, `dislike_count`, `comment_count`, `collection_count`, `top`, `visible`, `commentable`, `status`, `release_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (14, '测试4', 12, '测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4测试4', 3, 0, 0, 0, 0, 0, 0, '0', 0, '1', '2024-08-16 23:20:00', '1', '2024-08-16 23:20:00', '1', '2024-09-02 22:20:16', '');
COMMIT;

-- ----------------------------
-- Table structure for biz_article_category
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_category`;
CREATE TABLE `biz_article_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint NOT NULL COMMENT '父级分类ID',
  `user_id` bigint NOT NULL COMMENT '作者ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除(0正常、1删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章类别表';

-- ----------------------------
-- Records of biz_article_category
-- ----------------------------
BEGIN;
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (9, '测试1', 0, 3, '1', '2024-08-16 01:28:25', '1', '2024-08-16 01:28:25', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (10, '测试2', 9, 3, '1', '2024-08-16 01:30:34', '1', '2024-08-16 01:30:34', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (11, '测试3', 9, 3, '1', '2024-08-16 01:31:00', '1', '2024-08-16 01:31:00', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (12, '测试4', 9, 3, '1', '2024-08-16 01:31:06', '1', '2024-08-16 01:31:06', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (13, '测试5', 9, 3, '1', '2024-08-16 01:31:13', '1', '2024-08-16 01:31:13', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (14, '测试6', 9, 3, '1', '2024-08-16 01:31:21', '1', '2024-08-16 01:31:21', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (15, '测试7', 9, 3, '1', '2024-08-16 01:31:26', '1', '2024-08-16 01:31:26', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (16, '测试8', 11, 2, '1', '2024-08-16 03:17:56', '1', '2024-08-16 03:17:56', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (17, '测1', 0, 4, '2', '2024-08-23 14:29:11', '2', '2024-08-23 14:29:11', '1', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (18, '1', 17, 4, '2', '2024-08-23 14:33:41', '2', '2024-08-23 14:33:41', '', 0);
INSERT INTO `biz_article_category` (`id`, `name`, `parent_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (19, '测试3', 17, 4, '2', '2024-08-23 14:33:56', '2', '2024-08-23 14:33:56', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for biz_article_label
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_label`;
CREATE TABLE `biz_article_label` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章标签表';

-- ----------------------------
-- Records of biz_article_label
-- ----------------------------
BEGIN;
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '便', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '中国', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '先生', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '讲义', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '说', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, '里', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, '想', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, '回', '1', '2024-08-16 03:57:05', '1', '2024-08-16 03:57:05', '');
INSERT INTO `biz_article_label` (`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (9, '测试', '1', '2024-08-16 23:20:01', '1', '2024-08-16 23:20:01', '');
COMMIT;

-- ----------------------------
-- Table structure for biz_article_label_link
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_label_link`;
CREATE TABLE `biz_article_label_link` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `label_id` bigint NOT NULL COMMENT '标签ID',
  `user_id` bigint NOT NULL COMMENT '作者ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `article_id` (`article_id`,`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章、文章标签关系表';

-- ----------------------------
-- Records of biz_article_label_link
-- ----------------------------
BEGIN;
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, 13, 1, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, 13, 2, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, 13, 3, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, 13, 4, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, 13, 5, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, 13, 6, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, 13, 7, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, 13, 8, 3, '1', '2024-08-16 04:04:20', '1', '2024-08-16 04:04:20', '');
INSERT INTO `biz_article_label_link` (`id`, `article_id`, `label_id`, `user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (9, 14, 9, 3, '1', '2024-08-16 23:20:01', '1', '2024-08-16 23:20:01', '');
COMMIT;

-- ----------------------------
-- Table structure for biz_chat
-- ----------------------------
DROP TABLE IF EXISTS `biz_chat`;
CREATE TABLE `biz_chat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sender_id` bigint NOT NULL COMMENT '发送者ID',
  `receiver_id` bigint NOT NULL COMMENT '接收者ID',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sender_id` (`sender_id`) USING BTREE,
  KEY `receiver_id` (`receiver_id`) USING BTREE,
  CONSTRAINT `biz_chat_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `biz_chat_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='聊天表';

-- ----------------------------
-- Records of biz_chat
-- ----------------------------
BEGIN;
INSERT INTO `biz_chat` (`id`, `sender_id`, `receiver_id`, `message`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, 3, 4, 'zxf', '1', '2024-08-16 23:26:48', '1', '2024-08-16 23:26:48', '');
COMMIT;

-- ----------------------------
-- Table structure for biz_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_comment`;
CREATE TABLE `biz_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `biz_id` bigint NOT NULL COMMENT '业务ID',
  `biz_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业务类型',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `reply_id` bigint NOT NULL COMMENT '回复ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `os` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作系统',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'IP',
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'IP属地',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='评论表';

-- ----------------------------
-- Records of biz_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for biz_follow
-- ----------------------------
DROP TABLE IF EXISTS `biz_follow`;
CREATE TABLE `biz_follow` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `follower_id` bigint unsigned NOT NULL COMMENT '关注者ID',
  `followed_id` bigint unsigned NOT NULL COMMENT '被关注者ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_follow` (`follower_id`,`followed_id`) USING BTREE COMMENT '唯一关注关系'
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='关注表';

-- ----------------------------
-- Records of biz_follow
-- ----------------------------
BEGIN;
INSERT INTO `biz_follow` (`id`, `follower_id`, `followed_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (29, 4, 3, '2', '2024-08-22 23:53:48', '2', '2024-08-22 23:53:48', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `hash_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '散列值',
  `biz_id` bigint NOT NULL COMMENT '业务ID',
  `biz_type` tinyint NOT NULL COMMENT '业务类型',
  `bucket_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '桶名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名称',
  `file_size` bigint NOT NULL COMMENT '文件大小',
  `chunk_total` int NOT NULL COMMENT '分片数量',
  `chunk_size` bigint NOT NULL COMMENT '分片大小',
  `status` tinyint(1) NOT NULL COMMENT '上传状态(0未完成、1已完成)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `hash_code` (`hash_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='附件表';

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
BEGIN;
INSERT INTO `sys_attachment` (`id`, `hash_code`, `biz_id`, `biz_type`, `bucket_name`, `file_path`, `file_name`, `file_size`, `chunk_total`, `chunk_size`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, '5d08ced39910341325c102af785beb54', 0, 0, '', '/file/5d08ced39910341325c102af785beb54.jpg', 'profile.jpg', 69348, 1, 10485760, 1, '', '2024-10-27 14:47:48', '', '2024-10-27 14:47:48', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_attachment_chunk
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment_chunk`;
CREATE TABLE `sys_attachment_chunk` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `file_id` bigint NOT NULL COMMENT '文件ID',
  `hash_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '散列值',
  `chunk_index` int NOT NULL COMMENT '分片序号',
  `chunk_size` bigint NOT NULL COMMENT '分片大小',
  `status` tinyint(1) NOT NULL COMMENT '上传状态(0未完成、1已完成)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=691 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='附件分片表';

-- ----------------------------
-- Records of sys_attachment_chunk
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `label` varchar(20) NOT NULL COMMENT '标签',
  `value` varchar(20) NOT NULL COMMENT '键值',
  `type_id` bigint NOT NULL COMMENT '类型ID',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) NOT NULL COMMENT '状态(0禁用、1正常)',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` (`id`, `label`, `value`, `type_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '女', '0', 1, 1, '1', '1', '2024-09-25 22:56:53', '1', '2024-10-10 22:04:54', '');
INSERT INTO `sys_dict_data` (`id`, `label`, `value`, `type_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '男', '1', 1, 2, '1', '1', '2024-09-25 22:57:03', '1', '2024-10-10 22:04:57', '');
INSERT INTO `sys_dict_data` (`id`, `label`, `value`, `type_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '未知', '2', 1, 3, '1', '1', '2024-09-25 22:57:14', '1', '2024-10-10 22:05:00', '');
INSERT INTO `sys_dict_data` (`id`, `label`, `value`, `type_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '禁用', '0', 2, 1, '1', '1', '2024-10-10 22:37:46', '1', '2024-10-10 22:37:46', '');
INSERT INTO `sys_dict_data` (`id`, `label`, `value`, `type_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '正常', '1', 2, 2, '1', '1', '2024-10-10 22:38:05', '1', '2024-10-10 22:38:05', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '字典名称',
  `type` varchar(20) NOT NULL COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典状态(0禁用、1正常)',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` (`id`, `name`, `type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '性别', 'gender', '1', '1', '2024-09-25 22:56:38', '1', '2024-10-10 22:04:48', '');
INSERT INTO `sys_dict_type` (`id`, `name`, `type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '启用状态', 'enable_status', '1', '1', '2024-10-10 22:36:51', '1', '2024-10-10 22:40:29', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `login_type` char(1) NOT NULL COMMENT '登录类型(1账密、2邮箱验证码、3手机验证码)',
  `os` varchar(20) NOT NULL COMMENT '操作系统',
  `browser` varchar(20) NOT NULL COMMENT '浏览器',
  `ip` varchar(128) NOT NULL COMMENT 'IP',
  `location` varchar(20) NOT NULL COMMENT 'IP属地',
  `status` tinyint(1) NOT NULL COMMENT '状态(0失败、1成功)',
  `msg` varchar(255) NOT NULL COMMENT '消息',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time` DESC) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5000161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表';

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `title` varchar(20) NOT NULL COMMENT '标题',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图标',
  `parent_id` bigint NOT NULL COMMENT '父级菜单ID',
  `ancestor_id` bigint NOT NULL COMMENT '祖级菜单ID',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由地址',
  `redirect` varchar(50) NOT NULL COMMENT '重定向地址',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件路径',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型(1目录、2菜单、3按钮)',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `visible` tinyint(1) NOT NULL COMMENT '可见(0否、1是)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (12, '仪表盘', '仪表盘', 'Odometer', 22, 22, '/dashboard', '', '/backend/dashboard/index.vue', '2', 1, '1', 1, '1', '2024-09-02 16:58:27', '1', '2024-09-14 21:31:21', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, '系统管理', '系统管理', 'House', 0, 0, '/system', '/user', '/', '1', 2, '1', 1, '1', '2024-09-02 17:00:03', '1', '2024-09-02 17:00:03', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (14, '用户管理', '用户管理', 'User', 13, 13, '/user', '', '/backend/system/user/index.vue', '2', 1, '1', 1, '1', '2024-09-02 17:04:45', '1', '2024-09-02 17:04:45', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (15, '角色管理', '角色管理', 'Avatar', 13, 13, '/role', '', '/backend/system/role/index.vue', '2', 2, '1', 1, '1', '2024-09-02 17:05:38', '1', '2024-09-05 22:14:14', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (16, '菜单管理', '菜单管理', 'Menu', 13, 13, '/menu', '', '/backend/system/menu/index.vue', '2', 3, '1', 1, '1', '2024-09-02 17:07:21', '1', '2024-09-02 17:07:32', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (17, '文章管理', '文章管理', 'Document', 0, 0, '/article', '/article-index', '/', '1', 3, '1', 1, '1', '2024-09-02 17:08:16', '1', '2024-09-02 17:08:16', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (18, '文章', '文章', 'Document', 17, 17, '/article-index', '', '/backend/article/index.vue', '2', 1, '1', 1, '1', '2024-09-02 17:14:03', '1', '2024-09-02 17:14:03', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (19, '文章类别', '文章类别', 'AddLocation', 17, 17, '/category', '', '/backend/article/category/index.vue', '2', 2, '1', 1, '1', '2024-09-02 17:16:49', '1', '2024-09-02 17:16:49', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (20, '文章标签', '文章标签', 'BrushFilled', 17, 17, '/label', '', '/backend/article/label/index.vue', '2', 3, '1', 1, '1', '2024-09-02 17:17:49', '1', '2024-09-02 17:18:16', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (21, '权限管理', '权限管理', 'Stamp', 13, 13, '/permission', '', '/backend/system/permission/index.vue', '2', 4, '1', 1, '1', '2024-09-05 22:53:27', '1', '2024-09-05 22:53:41', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (22, '首页', '首页', 'HomeFilled', 0, 0, '/', '/dashboard', '/', '1', 1, '1', 1, '1', '2024-09-14 21:30:24', '1', '2024-09-15 18:19:03', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (23, '日志管理', '日志管理', 'Cellphone', 13, 13, '/log', '/log/login', '', '1', 6, '1', 1, '1', '2024-09-14 21:40:26', '1', '2024-09-25 22:46:40', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (24, '登录日志', '登录日志', 'Key', 23, 13, '/log/login', '', '/backend/system/log/login/index.vue', '2', 1, '1', 1, '1', '2024-09-14 21:42:41', '1', '2024-09-14 21:44:42', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (25, 'dict-manage', '字典管理', 'Apple', 13, 13, '/dict', '', '', '1', 5, '1', 1, '1', '2024-09-25 22:46:27', '1', '2024-09-25 22:47:36', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (26, 'dict-type', '字典类型', 'Apple', 25, 13, '/dict/type', '', '/backend/system/dict/type/index.vue', '2', 1, '1', 1, '1', '2024-09-25 22:49:46', '1', '2024-09-25 22:49:46', '');
INSERT INTO `sys_menu` (`id`, `name`, `title`, `icon`, `parent_id`, `ancestor_id`, `path`, `redirect`, `component`, `type`, `sort`, `status`, `visible`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (27, 'dict-data', '字典数据', 'Burger', 25, 13, '/dict/data', '', '/backend/system/dict/data/index.vue', '2', 2, '1', 1, '1', '2024-09-25 22:50:45', '1', '2024-09-25 22:51:16', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限标识',
  `parent_id` bigint NOT NULL COMMENT '父级权限ID',
  `ancestor_id` bigint NOT NULL COMMENT '祖级权限ID',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) NOT NULL COMMENT '状态(0禁用、1正常)',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '用户添加', 'system:user:add', 11, 11, 1, '1', '1', '2024-09-05 22:58:46', '1', '2024-09-05 23:32:22', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '用户删除', 'system:user:delete', 11, 11, 2, '1', '1', '2024-09-05 22:59:07', '1', '2024-09-05 23:32:37', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '用户修改', 'system:user:edit', 11, 11, 3, '1', '1', '2024-09-05 22:59:38', '1', '2024-09-05 23:44:37', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '用户列表', 'system:user:list', 11, 11, 4, '1', '1', '2024-09-05 23:00:37', '1', '2024-09-05 23:44:42', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '用户查询', 'system:user:query', 11, 11, 5, '1', '1', '2024-09-05 23:05:18', '1', '2024-09-05 23:44:49', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, '角色添加', 'system:role:add', 12, 12, 1, '1', '1', '2024-09-05 23:11:59', '1', '2024-09-06 00:24:12', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, '角色删除', 'system:role:delete', 12, 12, 2, '1', '1', '2024-09-05 23:12:13', '1', '2024-09-06 00:24:16', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, '角色修改', 'system:role:edit', 12, 12, 3, '1', '1', '2024-09-05 23:12:29', '1', '2024-09-06 00:24:21', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (9, '角色列表', 'system:role:list', 12, 12, 4, '1', '1', '2024-09-05 23:12:50', '1', '2024-09-06 00:24:26', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, '角色查询', 'system:role:query', 12, 12, 5, '1', '1', '2024-09-05 23:13:13', '1', '2024-09-06 00:24:30', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, '用户', 'system:user:*', 0, 0, 1, '1', '1', '2024-09-05 23:14:40', '1', '2024-09-14 01:12:17', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (12, '角色', 'system:role:*', 0, 0, 2, '1', '1', '2024-09-05 23:14:57', '1', '2024-09-05 23:14:57', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, '菜单', 'system:menu:*', 0, 0, 3, '1', '1', '2024-09-06 01:05:09', '1', '2024-09-06 01:05:09', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (14, '菜单添加', 'system:menu:add', 13, 13, 1, '1', '1', '2024-09-06 01:51:27', '1', '2024-09-06 01:53:33', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (15, '菜单删除', 'system:menu:delete', 13, 13, 2, '1', '1', '2024-09-06 01:51:36', '1', '2024-09-06 01:53:42', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (16, '菜单修改', 'system:menu:edit', 13, 13, 3, '1', '1', '2024-09-06 01:51:50', '1', '2024-09-06 01:53:48', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (17, '菜单列表', 'system:menu:list', 13, 13, 4, '1', '1', '2024-09-06 01:52:21', '1', '2024-09-06 01:53:58', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (18, '菜单查询', 'system:menu:query', 13, 13, 5, '1', '1', '2024-09-06 01:52:34', '1', '2024-09-06 01:54:06', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (19, '权限', 'system:permission:*', 0, 0, 4, '1', '1', '2024-09-13 17:52:19', '1', '2024-09-13 17:52:56', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (20, '权限添加', 'system:permission:add', 19, 19, 1, '1', '1', '2024-09-13 17:53:20', '1', '2024-09-13 17:56:47', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (21, '权限删除', 'system:permission:delete', 19, 19, 2, '1', '1', '2024-09-13 17:53:27', '1', '2024-09-13 17:56:40', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (22, '权限修改', 'system:permission:edit', 19, 19, 3, '1', '1', '2024-09-13 17:53:36', '1', '2024-09-13 17:56:29', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (23, '权限列表', 'system:permission:list', 19, 19, 4, '1', '1', '2024-09-13 17:53:44', '1', '2024-09-13 17:56:20', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (24, '权限查询', 'system:permission:query', 19, 19, 5, '1', '1', '2024-09-13 17:54:03', '1', '2024-09-13 17:56:12', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (25, '角色分配菜单', 'system:role:assign:menu', 12, 12, 8, '1', '1', '2024-09-14 01:14:35', '1', '2024-09-24 19:56:09', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (26, '角色分配权限', 'system:role:assign:permission', 12, 12, 9, '1', '1', '2024-09-14 01:15:27', '1', '2024-09-24 19:56:14', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (27, '用户导入', 'system:user:import', 11, 11, 6, '1', '1', '2024-09-24 19:52:15', '1', '2024-09-24 19:52:15', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (28, '角色导入', 'system:role:import', 12, 12, 6, '1', '1', '2024-09-24 19:52:50', '1', '2024-09-24 19:52:50', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (29, '菜单导入', 'system:menu:import', 13, 13, 6, '1', '1', '2024-09-24 19:54:38', '1', '2024-09-24 19:54:38', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (30, '权限导入', 'system:permission:import', 19, 19, 6, '1', '1', '2024-09-24 19:55:07', '1', '2024-09-24 19:55:07', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (31, '用户导出', 'system:user:export', 11, 11, 7, '1', '1', '2024-09-24 19:55:40', '1', '2024-09-24 19:55:40', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (32, '角色导出', 'system:role:export', 12, 12, 7, '1', '1', '2024-09-24 19:56:04', '1', '2024-09-24 19:56:04', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (33, '菜单导出', 'system:menu:export', 13, 13, 7, '1', '1', '2024-09-24 19:57:30', '1', '2024-09-24 19:57:30', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (34, '权限导出', 'system:permission:export', 19, 19, 7, '1', '1', '2024-09-24 19:58:16', '1', '2024-09-24 19:58:16', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (35, '日志', 'system:log:*', 0, 0, 6, '1', '1', '2024-09-24 21:06:30', '1', '2024-09-25 22:27:06', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (36, '登录日志', 'system:log:login:*', 35, 35, 1, '1', '1', '2024-09-24 21:06:57', '1', '2024-09-24 21:06:57', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (37, '操作日志', 'system:log:operation:*', 35, 35, 2, '1', '1', '2024-09-24 21:07:37', '1', '2024-09-24 21:07:37', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (38, '字典', 'system:dict:*:*', 0, 0, 5, '1', '1', '2024-09-25 22:26:43', '1', '2024-09-25 22:26:59', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (39, '字典类型', 'system:dict:type:*', 38, 38, 1, '1', '1', '2024-09-25 22:27:41', '1', '2024-09-25 22:27:41', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (40, '字典数据', 'system:dict:data:*', 38, 38, 2, '1', '1', '2024-09-25 22:27:54', '1', '2024-09-25 22:27:54', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (41, '字典类型添加', 'system:dict:type:add', 39, 38, 1, '1', '1', '2024-09-25 22:28:53', '1', '2024-09-25 22:32:08', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (42, '字典类型删除', 'system:dict:type:delete', 39, 38, 2, '1', '1', '2024-09-25 22:29:01', '1', '2024-09-25 22:32:21', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (43, '字典类型修改', 'system:dict:type:edit', 39, 38, 3, '1', '1', '2024-09-25 22:29:11', '1', '2024-09-25 22:32:56', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (44, '字典类型列表', 'system:dict:type:list', 39, 38, 4, '1', '1', '2024-09-25 22:29:31', '1', '2024-09-25 22:33:04', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (45, '字典类型查询', 'system:dict:type:query', 39, 38, 5, '1', '1', '2024-09-25 22:29:46', '1', '2024-09-25 22:33:16', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (46, '字典类型导入', 'system:dict:type:import', 39, 38, 6, '1', '1', '2024-09-25 22:29:54', '1', '2024-09-25 22:33:27', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (47, '字典类型导出', 'system:dict:type:export', 39, 38, 7, '1', '1', '2024-09-25 22:30:14', '1', '2024-09-25 22:33:38', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (48, '字典数据添加', 'system:dict:data:add', 40, 38, 1, '1', '1', '2024-09-25 22:34:02', '1', '2024-09-25 22:36:39', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (49, '字典数据删除', 'system:dict:data:delete', 40, 38, 2, '1', '1', '2024-09-25 22:34:13', '1', '2024-09-25 22:37:33', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (50, '字典数据修改', 'system:dict:data:edit', 40, 38, 3, '1', '1', '2024-09-25 22:34:22', '1', '2024-09-25 22:36:47', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (51, '字典数据列表', 'system:dict:data:list', 40, 38, 4, '1', '1', '2024-09-25 22:34:30', '1', '2024-09-25 22:37:20', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (52, '字典数据查询', 'system:dict:data:query', 40, 38, 5, '1', '1', '2024-09-25 22:34:39', '1', '2024-09-25 22:37:11', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (53, '字典数据导入', 'system:dict:data:import', 40, 38, 6, '1', '1', '2024-09-25 22:34:47', '1', '2024-09-25 22:36:55', '');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `parent_id`, `ancestor_id`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (54, '字典数据导出', 'system:dict:data:export', 40, 38, 7, '1', '1', '2024-09-25 22:34:55', '1', '2024-09-25 22:37:03', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) NOT NULL COMMENT '状态(0禁用、1正常)',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除(0正常、1删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (1, '管理员', 1, '1', '1', '2024-08-19 20:29:06', '1', '2024-08-19 20:39:39', '', 0);
INSERT INTO `sys_role` (`id`, `name`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (2, '用户', 2, '1', '1', '2024-08-19 20:29:28', '1', '2024-09-05 22:17:28', '', 0);
INSERT INTO `sys_role` (`id`, `name`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (3, '游客', 3, '1', '1', '2024-08-19 20:29:38', '1', '2024-09-25 00:11:45', '', 0);
INSERT INTO `sys_role` (`id`, `name`, `sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `deleted`) VALUES (4, '测试1', 1, '1', '1', '2024-08-19 20:35:04', '1', '2024-08-21 11:32:50', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_link`;
CREATE TABLE `sys_role_menu_link` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色、菜单关系表';

-- ----------------------------
-- Records of sys_role_menu_link
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (119, 2, 12, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (120, 2, 17, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (121, 2, 18, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (122, 2, 19, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (123, 2, 20, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (124, 2, 22, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (125, 3, 12, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (126, 3, 18, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (127, 3, 19, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (128, 3, 17, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (129, 3, 20, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (130, 3, 13, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (131, 3, 14, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (132, 3, 15, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (133, 3, 16, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (134, 3, 21, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (135, 3, 22, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (149, 1, 12, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (150, 1, 17, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (151, 1, 18, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (152, 1, 19, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (153, 1, 20, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (154, 1, 13, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (155, 1, 14, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (156, 1, 15, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (157, 1, 16, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (158, 1, 21, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (159, 1, 22, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (160, 1, 23, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (161, 1, 24, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (162, 1, 25, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (163, 1, 26, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (164, 1, 27, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_link`;
CREATE TABLE `sys_role_permission_link` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色、权限关系表';

-- ----------------------------
-- Records of sys_role_permission_link
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (89, 3, 4, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (90, 3, 5, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (91, 3, 10, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (92, 3, 9, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (93, 3, 17, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (94, 3, 18, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (95, 3, 24, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (96, 3, 23, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (114, 2, 13, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (115, 2, 3, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (116, 2, 5, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (117, 2, 10, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (118, 2, 17, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (119, 2, 18, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (120, 2, 23, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (121, 2, 24, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (122, 2, 4, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (123, 2, 9, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (158, 1, 1, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (159, 1, 2, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (160, 1, 3, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (161, 1, 4, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (162, 1, 5, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (163, 1, 12, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (164, 1, 6, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (165, 1, 7, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (166, 1, 8, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (167, 1, 9, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (168, 1, 10, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (169, 1, 13, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (170, 1, 14, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (171, 1, 15, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (172, 1, 16, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (173, 1, 17, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (174, 1, 18, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (175, 1, 19, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (176, 1, 20, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (177, 1, 21, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (178, 1, 22, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (179, 1, 23, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (180, 1, 24, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (181, 1, 25, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (182, 1, 26, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (183, 1, 11, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (184, 1, 31, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (185, 1, 27, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (186, 1, 28, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (187, 1, 32, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (188, 1, 29, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (189, 1, 33, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (190, 1, 30, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (191, 1, 34, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (192, 1, 38, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (193, 1, 39, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (194, 1, 41, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (195, 1, 42, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (196, 1, 43, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (197, 1, 44, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (198, 1, 45, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (199, 1, 46, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (200, 1, 47, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (201, 1, 40, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (202, 1, 48, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (203, 1, 49, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (204, 1, 50, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (205, 1, 51, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (206, 1, 52, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (207, 1, 54, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` (`id`, `role_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (208, 1, 53, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `gender` char(1) NOT NULL COMMENT '性别(0女、1男、2未知)',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信小程序开放ID',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最后登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `name`, `avatar`, `gender`, `birthday`, `status`, `phone`, `email`, `open_id`, `balance`, `login_ip`, `login_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '1', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '', '管理员', '/file/5d08ced39910341325c102af785beb54.jpg', '2', '2024-08-01', '1', '13037503398', '916586595@qq.com', '1', 0.00, '0:0:0:0:0:0:0:1', '2024-10-27 13:35:56', '', '2024-08-16 01:26:41', '1', '2024-10-27 14:47:48', '');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `name`, `avatar`, `gender`, `birthday`, `status`, `phone`, `email`, `open_id`, `balance`, `login_ip`, `login_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '2', '$2a$10$elhEi/ohemfnXateL1BLZ.lLi.fJ31tDVKdSpr3xnr40pdMjAlqlG', '', '张三', '1', '2', '2024-08-22', '1', '13037503390', '1@qq.com', '1', 0.00, '1', '2024-08-28 00:00:00', '', '2024-08-16 09:00:11', '', '2024-09-14 06:58:03', '');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `name`, `avatar`, `gender`, `birthday`, `status`, `phone`, `email`, `open_id`, `balance`, `login_ip`, `login_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '3', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '1', '1', '/file/c3f7a394-7b91-43b3-b924-5d1592426f06.jpg', '2', '2024-08-27', '1', '13037503391', '2@qq.com', '1', 0.00, '', NULL, '1', '2024-08-21 14:25:56', '1', '2024-08-21 14:25:56', '');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `name`, `avatar`, `gender`, `birthday`, `status`, `phone`, `email`, `open_id`, `balance`, `login_ip`, `login_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, '4', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '1', '1', '/file/a3336d6e-4ef8-46f0-99e6-a104122b9f88.jpg', '2', '2024-08-17', '0', '13037503392', '3@qq.com', '1', 0.00, '', NULL, '1', '2024-08-21 14:34:13', '1', '2024-08-21 15:13:15', '1');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `name`, `avatar`, `gender`, `birthday`, `status`, `phone`, `email`, `open_id`, `balance`, `login_ip`, `login_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, '11', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '11', '', '', '2', '2024-10-04', '1', '13037503314', '4@qq.com', '', 0.00, '', NULL, '', '2024-09-13 23:42:22', '', '2024-10-11 13:46:24', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_link`;
CREATE TABLE `sys_user_role_link` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_by` varchar(20) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户、角色关系表';

-- ----------------------------
-- Records of sys_user_role_link
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (12, 4, 2, '1', '2024-08-21 12:10:42', '1', '2024-08-21 12:10:42', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, 5, 2, '1', '2024-08-21 14:25:56', '1', '2024-08-21 14:25:56', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (14, 5, 3, '1', '2024-08-21 14:25:56', '1', '2024-08-21 14:25:56', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (21, 6, 2, '1', '2024-08-21 14:58:22', '1', '2024-08-21 14:58:22', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (22, 3, 1, '1', '2024-08-21 15:13:59', '1', '2024-08-21 15:13:59', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (23, 7, 2, '', '2024-09-02 15:48:49', '', '2024-09-02 15:48:49', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (24, 8, 2, '', '2024-09-02 15:48:55', '', '2024-09-02 15:48:55', '');
INSERT INTO `sys_user_role_link` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (26, 10, 2, '', '2024-09-13 23:42:22', '', '2024-09-13 23:42:22', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
