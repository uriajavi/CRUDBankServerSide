-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: bankdb
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `balance`, `beginBalance`, `beginBalanceTimestamp`, `creditLine`, `description`, `type`) VALUES (1569874954,999.99,999.99,'2019-01-14 19:19:04',0,'Savings Account',0),(2654785441,10000,10000,'2019-01-14 19:29:50',0,'Check Account',0),(3252214522,200,100,'2019-01-14 19:28:28',1000,'Check Account with Credit Line',1),(5255214522,3999,2000,'2020-04-05 18:26:28',2000,'Check Account with Credit Line',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `city`, `email`, `firstName`, `lastName`, `middleInitial`, `phone`, `state`, `street`, `zip`) VALUES (102263301,'New York','jsmith@enterprise.net','John','Smith','S.',15556969699,'New York','163rd St.',10032),(299985563,'Philadelphia','awallace@gmail.com','Ann','Wallace','M.',16665984477,'Pennsylvania','Main St.',10056),(345678401,'New York','rjwilliams@enterprise.net','Raymond','Williams','J.',15556969699,'New York','163rd St.',10032),(799985563,'Minneapolis','sbarnaby@gmail.com','Sean','Barnaby','G.',16665998777,'Minesota','York St.',36897),(799985598,'Atlanta','kjones@gmail.com','Katheleen','Jones','M.',987798777,'Georgia','56th st.',98745);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer_account`
--

LOCK TABLES `customer_account` WRITE;
/*!40000 ALTER TABLE `customer_account` DISABLE KEYS */;
INSERT INTO `customer_account` (`customers_id`, `accounts_id`) VALUES (102263301,1569874954),(102263301,2654785441),(299985563,2654785441),(299985563,3252214522),(102263301,5255214522),(799985598,5255214522);
/*!40000 ALTER TABLE `customer_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `movement`
--

LOCK TABLES `movement` WRITE;
/*!40000 ALTER TABLE `movement` DISABLE KEYS */;
INSERT INTO `movement` (`id`, `amount`, `balance`, `description`, `timestamp`, `account_id`) VALUES (1,100,100,'Deposit','2019-01-14 19:34:06',2654785441),(2,9900,10000,'Deposit','2019-02-02 16:32:41',2654785441),(3,200,10200,'Deposit','2019-02-02 16:35:11',2654785441),(4,-200,10000,'Payment','2019-02-02 16:35:47',2654785441),(7,100,200,'Deposit','2019-02-02 16:57:40',3252214522),(11,1999,2999,'Deposit','2020-04-04 20:23:40',5255214522),(12,1000,3999,'Deposit','2020-04-04 20:23:40',5255214522);
/*!40000 ALTER TABLE `movement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-06 20:35:49
