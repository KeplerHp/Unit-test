DROP SCHEMA IF EXISTS bookstore_project;
CREATE SCHEMA bookstore_project;
use bookstore_project;
SET foreign_key_checks = 0;
/* Table structure for book */
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int auto_increment,
  `name` varchar(63) NOT NULL,
  `author` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `isbn` varchar(31) NOT NULL,
  `inventory` int NOT NULL,
  `description` varchar(2000),
  `image` varchar(255) NOT NULL,
  `type` varchar(15),
  `brief` varchar(63),
  `enabled` boolean DEFAULT true,
  PRIMARY KEY (`book_id`)
) ENGINE = InnoDB
  CHARSET = utf8;

/* Insert records of books */
INSERT INTO `book`(`name`,`author`,`price`,`isbn`,`inventory`,`description`,`image`,`type`,`brief`,`enabled`) 
VALUES ('新教伦理与资本主义精神','[德] 马克斯·韦伯','15.00','9787506393102','10','《新教伦理与资本主义精神》是马克斯·韦伯最著名的著作之一。在这部作品中，韦伯提出了一个知名的论点：新教教徒的思想影响了资本主义的发展。宗教教徒往往排斥世俗的事务，尤其是经济成就上的追求，但为什么新教教徒却是例外？韦伯在该书中论述宗教观念（新教伦理）与隐藏在资本主义发展背后的某种心理驱力（资本主义精神）之间的关系。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_1.4zmxniwd0nsw.jpg','哲学/宗教类','组织理论之父 现代社会学经典','1'),
 ('深入理解计算机系统','[美] 兰德尔·布莱恩特','90.40','9787111544937','22','本书是一本将计算机软件和硬件理论结合讲述的经典教程，内容覆盖计算机导论、体系结构和处理器设计等夺门课程。本书的最大优点是从程序员的角度描述计算机系统的实现细节，通过描述程序是如何映射到系统上，以及程序是如何执行的，使读者更好地理解程序的行为，以及程序效率。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_2.qxotjmzuqww.jpg','计算机/网络类','程序员必读经典著作！理解计算机系统书目，10万程序员共同选择。','1'),
 ('货币的非国家化','[英] 弗里德里希·冯·哈耶克','27.20','9787544385435','5','《货币的非国家化——对多元货币的理论与实践的分析》是哈耶克晚年最后一本经济学专著。他在书中颠覆了正统的货币制度观念：既然在一般商品、服务市场上自由竞争最有效率，那为什么不能在货币领域引入自由竞争？哈耶克提出了一个革命性建议：废除中央银行制度，允许私人发行货币，并自由竞争，这个竞争过程将会发现最好的货币。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_3.7kcgy17p6buo.jpg','经济类','只有真正对人、社会、经济与自由有深刻认知，才有如此洞见。','1'),
 ('恶意','[日] 东野圭吾','25.30','9787544285148','11','作为一部手记体杰作，《恶意》多年来在票选中始终名列前茅，同时被评论界和众多读者视为东野圭吾的巅峰之作，与《白夜行》同享光辉与荣耀：环环相扣的侦破进展百转千回，将手记体叙事的无限可能发挥得淋漓尽致；对复杂人性抽丝剥茧的深刻描画，令人眼花缭乱、哑口无言。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_4.7bn25h2vfb0g.jpg','小说类','读完《恶意》，才算真正认识东野圭吾！','1'),
 ('三体','刘慈欣','55.80','9787536692930','15','文化大革命如火如荼进行的同时。军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……四光年外，“三体文明”正苦苦挣扎——三颗无规则运行的太阳主导下的百余次毁灭与重生逼迫他们逃离母星。人类的末日悄然来临。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_5.cd9q5ewbalc.jpg','小说类','刘慈欣代表作，亚洲首部“雨果奖”获奖作品！','1'),
 ('道德情操论','[英] 亚当·斯密','29.10','9787100028264','17','《道德情操论》是斯密的伦理学著作，首次出版于1759年，斯密去世前共出版过六次。全书共有七卷构成，主要阐释的是道德情感的本质和道德评价的性质。斯密在该书中继承了哈奇森的道德感学说和休谟的同情论思想，形成了自己的道德情感理论。他反对神学家用天启 来说明道德的根源，而把他认为是人的本性中所有的同情的情感作为阐释道德的基础。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_6.4phqcg4ggikg.jpg','哲学/宗教类','世界思想史上的经典之作','1'),
 ('经济学原理','[美] 格里高利·曼昆','153.90','9787301312971','29','曼昆的《经济学原理》是国内外市场上广受欢迎的经济学经典教材之一。与同类书相比，本书的特点在于，更多地强调经济学原理的应用和思维方式的培养，而不是经济学模型。书中包含了大量贴近生活的案例研究和政策讨论，适合经济学专业本科生的宏观经济学课程以及对经济学感兴趣的普通读者使用。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_7.663zd41s21hc.jpg','教材类','哈佛大学曼昆教授扛鼎之作，广受欢迎的经济学入门读物，带你迈进经济学的殿堂！','1'),
 ('沉默的大多数','王小波','28.90','9787500627098','5','这本杂文随笔集包括思想文化方面的文章，涉及知识分子的处境及思考，社会道德伦理，文化论争，国学与新儒家，民族主义等问题；包括从日常生活中发掘出来的各种真知灼见，涉及科学与邪道，女权主义等；包括对社会科学研究的评论，涉及性问题，生育问题，同性恋问题，社会研究的伦理问题和方法问题等艺术的看法。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_8.151rhse3cosg.jpg','文学类','用沉默影响世界！','1'),
 ('笑傲江湖','金庸','76.80','9787108006639','20','名门正派的华山派大弟子令狐冲只因心性自由、不受羁勒，喜欢结交左道人士，被逐出师门，遭到正宗门派武林人士的唾弃而流落江湖。令狐冲依然率性而为，只因正义良知自在心中。后来他认识了魔教圣姑任盈盈，两个不喜权势、向往自由的年轻人几经生死患难，笑傲江湖，终成知心情侣。本书处处渗透着追求个性解放与人格独立的精神，对人性的刻画殊为深刻。','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/bedd2327820c3b2eee3835822a665055.3d1u39vazvuo.jpeg','小说类','一曲《笑傲江湖》，传一段天荒地老。','1'),
 ('无人生还','[英] 阿加莎·克里斯蒂','35.00','9787513322331','23','十个相互陌生、身份各异的人受邀前往德文郡海岸边一座孤岛上的豪宅。客人到齐后，主人却没有出现。当晚，一个神秘的声音发出指控，分别说出每个人心中罪恶的秘密。接着，一位客人离奇死亡。暴风雨让小岛与世隔绝，《十个小士兵》——这首古老的童谣成了死亡咒语。如同歌谣中所预言的，客人一个接一个死去……杀人游戏结束后，竟无一人生还!','https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/book_10.3zkoq93b616o.jpg','小说类','无可争议的侦探小说女王','1'),
 ('金阁寺','[日] 三岛由纪夫','24.00','9787532744565','7','《金阁寺》取材于1950年金阁寺僧徒林养贤放火烧掉金阁寺的真实事件。据林养贤说他的犯罪动机是对金阁寺的美的嫉妒。《金阁寺》发表后大受好评，获第八届读卖文学奖。','http://img3m3.ddimg.cn/50/28/29220593-1_u_9.jpg','小说类','文学大师三岛由纪夫集大成之作，一个人走向毁灭的心理独白。','1'),
 ('万历十五年','[美] 黄仁宇','18.00','9787108009821','9','《万历十五年》是黄仁宇的成名之作，也是他的代表作之一。这本书融会了他数十年人生经历与治学体会，首次以“大历史观”分析明代社会之症结，观察现代中国之来路，给人启发良多。英文原本推出后，被美国多所大学采用为教科书，并两次获得美国书卷奖历史类好书的提名。','http://img3m3.ddimg.cn/4/23/25251043-1_u_195378.jpg','历史类','入选改革开放40年的40本好书。','1');

/* Table structure for carousel */
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home`(
  `image_id` int auto_increment,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE = InnoDB
  CHARSET = utf8;

/* Insert records of carousels */
INSERT INTO `home`(`image`)
VALUES ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/carousel_4.1nilc4okk7z4.jpg'),
 ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/carousel_5.64boo7yrm6f4.jpg'),
 ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/carousel_6.2zqpk6gl2se8.png'),
 ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/carousel_7.30k8yzfndim8.jpg'),
 ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/1.5dlgo0nxnwg.jpg'),
 ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/2.4iml4zt78hds.jpg'),
 ('https://cdn.jsdelivr.net/gh/xtommy-1/Images@master/3.7g29xt16e000.jpg');

/* Table structure for user */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int auto_increment,
  `name` varchar(31) NOT NULL,
  `email` varchar(63) NOT NULL,
  `enabled` boolean DEFAULT true,
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  CHARSET = utf8;

/* Insert records of user */
INSERT INTO `user`(`name`,`email`) VALUES ('demo1','demo1@sjtu.edu.cn');
INSERT INTO `user`(`name`,`email`) VALUES ('Admin','admin@sjtu.edu.cn');
INSERT INTO `user`(`name`,`email`) VALUES ('Admin2','admin2@sjtu.edu.cn');
INSERT INTO `user`(`name`,`email`) VALUES ('demo2','demo2@sjtu.edu.cn');


/* Table structure for user_auth */
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
	`user_id` int NOT NULL,
	`username` varchar(31) NOT NULL,
	`user_password` varchar(31) NOT NULL,
	`user_type` int(11) NOT NULL,       /* 0: user  1: admin */   
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE     
) ENGINE=InnoDB 
  CHARSET=utf8;

/* Insert records of user_auth */
INSERT INTO `user_auth` VALUES ('1','demo1','123','0');
INSERT INTO `user_auth` VALUES ('2','admin','admin','1');
INSERT INTO `user_auth` VALUES ('3','admin2','admin2','1');
INSERT INTO `user_auth` VALUES ('4','demo2','123','0');

/* Table structure for cart_item */
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `amount` int NOT NULL,
  `active` bool NOT NULL,
   -- PRIMARY KEY ('cart_id'),
   PRIMARY KEY (`user_id`,`book_id`),
   -- FOREIGN KEY (`cart_id`) REFERENCES `cart`(`cart_id`) ON DELETE CASCADE
   FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
   FOREIGN KEY (`book_id`) REFERENCES `book`(`book_id`) ON DELETE CASCADE
) ENGINE=InnoDB 
  CHARSET=utf8;


DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int auto_increment,
  `user_id` int NOT NULL,
  `time` timestamp NOT NULL,
  `price` decimal(10,2),
  primary key (`order_id`),
  foreign key (`user_id`) references `user`(`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB 
  CHARSET=utf8;

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `order_id` int NOT NULL,
  `book_id` int NOT NULL,
  `amount` int NOT NULL,
  `price` decimal(10,2),
  primary key (`order_id`,`book_id`),
  foreign key (`order_id`) references `order`(`order_id`) ON DELETE CASCADE,
  foreign key (`book_id`) references `book`(`book_id`) ON DELETE RESTRICT
) ENGINE=InnoDB 
  CHARSET=utf8;

