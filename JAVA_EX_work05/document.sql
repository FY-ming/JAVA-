use filelist;

DROP TABLE IF EXISTS `doc_info`;
CREATE TABLE `doc_info` (
`ID` varchar(11) NOT NULL DEFAULT '' ,
`creator` varchar(255) NOT NULL DEFAULT '',
`timestamp` datetime NOT NULL DEFAULT '2023-01-01 00:00:00',
`description` text,
`filename` varchar(255) NOT NULL DEFAULT '',
PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `doc_info` VALUES (1,'jack','2016-11-17 00:00:00','NULL','doc.java');

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
`username` varchar(255) NOT NULL DEFAULT '',
`password` varchar(32) NOT NULL DEFAULT '',
`role` varchar(16) NOT NULL DEFAULT '',
PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user_info` VALUES ('jack','123','operator'),('kate','123','administrator'),('rose','123','browser');
SELECT `doc_info`.`ID`,
`doc_info`.`creator`,
`doc_info`.`timestamp`,
`doc_info`.`description`,
`doc_info`.`filename`
FROM `filelist`.`doc_info`;
