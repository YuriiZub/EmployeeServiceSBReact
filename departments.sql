DROP SCHEMA IF EXISTS `departments` ;
CREATE SCHEMA IF NOT EXISTS `departments` ;

CREATE TABLE IF NOT EXISTS `departments`.`tbldepartments` (
  `dpID` INT(11) NOT NULL AUTO_INCREMENT,
  `dpName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`dpID`),
  UNIQUE INDEX `dpName_UNIQUE` (`dpName` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `departments`.`tblemployees` (
  `empID` INT(11) NOT NULL AUTO_INCREMENT,
  `empName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `empActive` TINYINT(4) NOT NULL DEFAULT '1',
  `emp_dpID` INT(11) NOT NULL,
  PRIMARY KEY (`empID`),
  UNIQUE INDEX `empName_UNIQUE` (`empName` ASC) ,
  INDEX `FK_EMP_DEPTH_idx` (`emp_dpID` ASC) ,
  CONSTRAINT `FK_EMP_DEPTH`
    FOREIGN KEY (`emp_dpID`)
    REFERENCES `departments`.`tbldepartments` (`dpID`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci

CREATE TABLE `departments`.`users` (
  `usrId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `loginId` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `active` tinyint(4) NOT NULL,

  PRIMARY KEY (`usrId`),
  UNIQUE KEY `usrId_UNIQUE` (`usrId`),
  UNIQUE KEY `loginId_UNIQUE` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `departments`.`roles` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE INDEX `roleName_UNIQUE` (`roleName` ASC) ,
  UNIQUE INDEX `roleId_UNIQUE` (`roleId` ASC) );

CREATE TABLE `departments`.`users_roles` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `userId` INT(11) NOT NULL,
  `roleId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) ,
  INDEX `usr_roles_usr_FK_idx` (`userId` ASC) ,
  UNIQUE INDEX `usr_roles_UNIQUE` (`userId` ASC, `roleId` ASC) ,
  CONSTRAINT `usr_roles_usr_FK`
    FOREIGN KEY (`userId`)
    REFERENCES `departments`.`users` (`usrId`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `usr_roles_roles_FK`
    FOREIGN KEY (`roleId`)
    REFERENCES `departments`.`roles` (`roleId`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

    INSERT INTO `departments`.`roles` VALUES (1,'ADMIN');
    INSERT INTO `departments`.`roles` VALUES (2,'USER');

