-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: hr_system
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicant` (
  `idApplicant` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `firstName` varchar(50) COLLATE utf8_bin NOT NULL,
  `lastName` varchar(50) COLLATE utf8_bin NOT NULL,
  `phone` varchar(50) COLLATE utf8_bin NOT NULL,
  `skype` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_bin NOT NULL,
  `city` varchar(100) COLLATE utf8_bin NOT NULL,
  `university` varchar(100) COLLATE utf8_bin NOT NULL,
  `graduationYear` year(4) NOT NULL,
  `englishLevel` enum('A1','A2','B1','B2','C1','C2') COLLATE utf8_bin NOT NULL,
  `professionalSkills` text COLLATE utf8_bin,
  PRIMARY KEY (`idApplicant`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `skype_UNIQUE` (`skype`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` VALUES (29,'kolediuk@gmail.com','76d80224611fc919a5d54f0ff9fba446','Pavel','Kolediuk','+375291234567','pashakolediuk','Belarus','Minsk','BSUIR',2018,'B1','Confident knowledge PHP and MySQL\r\nExperience with PHP frameworks (Zend2, Symfony2)\r\nFront-end coding in HTML5, JavaScript, JQuery, and CSS\r\nExperience building HTML/CSS across the major browsers\r\nKnowledge of the principles of OOP and design patterns\r\nAbility to communicate in written and spoken English'),(30,'goncharick@gmail.com','76d80224611fc919a5d54f0ff9fba446','Sergey','Goncharik','+375291511567','goncharik','Belarus','Brest','BSUIR',2012,'C2','Experience with Java, J2EE and object-oriented analysis and design principles;\r\nExperience designing and developing web based applications using Java, JavaScript (JQuery), Spring (MVC & Inversion of Control), XHTML, Eclipse, Tomcat, CSS, Hibernate;\r\nQuick learner, eager to leverage new technologies in a dynamic team environment;\r\nSQL, Oracle Database;\r\nApache Ant/Ivy;\r\nWeb Services, AJAX, REST, JSON;\r\nJunit;\r\nAgile/Scrum;\r\nGood speaking English: being able to discuss technical topics;\r\nCommunication skills: voice/e-mail.'),(53,'rybakov@gmail.com','46e44aa0bc21d8a826d79344df38be4b','Ivan','Rybakov','+375291234232','','Belarus','Minsk','BSU',2012,'B1',''),(54,'dichkovsky@gmail.com','46e44aa0bc21d8a826d79344df38be4b','Ilya','Dichkovsky','+531291230067',NULL,'Belarus','Minsk','BNTU',2006,'A1',''),(56,'test.email.first@email.com','fed3b61b26081849378080b34e693d2e','TestFirstName','TestLastName','+012345678910','test.skype','TestCountry','TestCity','TestUniversity',2017,'A1','TestProfessionalSkills');
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `idCompany` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(100) CHARACTER SET utf8 NOT NULL,
  `country` varchar(100) CHARACTER SET utf8 NOT NULL,
  `city` varchar(100) CHARACTER SET utf8 NOT NULL,
  `companyDescription` text CHARACTER SET utf8,
  `website` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idCompany`),
  UNIQUE KEY `companyName_UNIQUE` (`companyName`),
  UNIQUE KEY `website_UNIQUE` (`website`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (3,'Epam','Belarus','Minsk','The company was founded as EPAM by Belarus natives Arkadiy Dobkin in Princeton, NJ, and Leo Lozner in Minsk, Belarus in 1993[2] and incorporated as EPAM Systems on December 18, 2002.[3] Since then, the company has grown to approximately 18,354 tech employees.[1]\r\n\r\nOn January 24, 2012, EPAM announced the launch of an IPO on the New York Stock Exchange under the ticker EPAM.[4] This is the first IPO that comes from the outsourcing industry in Eastern Europe.[5]','epam.com'),(6,'Google, Inc','USA','Melno-Park','Google is an American multinational technology company specializing in Internet-related services and products that include online advertising technologies, search, cloud computing, software, and hardware.\r\n\r\nGoogle was founded in 1996 by Larry Page and Sergey Brin while they were Ph.D. students at Stanford University, California. Together, they own about 14 percent of its shares and control 56 percent of the stockholder voting power through supervoting stock. They incorporated Google as a privately held company on September 4, 1998. An initial public offering (IPO) took place on August 19, 2004, and Google moved to its new headquarters in Mountain View, California, nicknamed the Googleplex.[6]\r\n\r\nIn August 2015, Google announced plans to reorganize its various interests as a conglomerate called Alphabet. Google, Alphabet\'s leading subsidiary, will continue to be the umbrella company for Alphabet\'s Internet interests. Upon completion of the restructure, Sundar Pichai became CEO of Google, replacing Larry Page, who became CEO of Alphabet..\r\n\r\nRapid growth since incorporation has triggered a chain of products, acquisitions and partnerships beyond Google\'s core search engine (Google Search). It offers services designed for work and productivity (Google Docs, Sheets and Slides), email (Gmail/Inbox), scheduling and time management (Google Calendar), cloud storage (Google Drive), social networking (Google+), instant messaging and video chat (Google Allo/Duo/Hangouts), language translation (Google Translate), mapping and turn-by-turn navigation (Google Maps), video sharing (YouTube), taking notes (Google Keep), and organizing and editing photos (Google Photos). The company leads the development of the Android mobile operating system, the Google Chrome web browser and Chrome OS, a lightweight operating system based around the Chrome browser. ','google.com'),(9,'Apple, Inc','USA','Cupertino','Apple is an American multinational technology company headquartered in Cupertino, California, a suburb of San Jose, that designs, develops, and sells consumer electronics, computer software, and online services. Its hardware products include the iPhone smartphone, the iPad tablet computer, the Mac personal computer, the iPod portable media player, the Apple Watch smartwatch, and the Apple TV digital media player. Apple\'s consumer software includes the macOS and iOS operating systems, the iTunes media player, the Safari web browser, and the iLife and iWork creativity and productivity suites. Its online services include the iTunes Store, the iOS App Store and Mac App Store, Apple Music, and iCloud.','apple.com'),(10,'Twitter, Inc','USA','San-Francisco','Twitter was created in March 2006 by Jack Dorsey, Noah Glass, Biz Stone, and Evan Williams and launched in July, whereby the service rapidly gained worldwide popularity. In 2012, more than 100 million users posted 340 million tweets a day,[12] and the service handled an average of 1.6 billion search queries per day.[13][14][15] In 2013, it was one of the ten most-visited websites and has been described as \"the SMS of the Internet\".[16][17] As of March 2016, Twitter had more than 310 million monthly active users.[6] On the day of the 2016 U.S. presidential election, Twitter proved to be the largest source of breaking news, with 40 million tweets sent by 10 p.m. (Eastern Time) that day','twitter.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(60) COLLATE utf8_bin NOT NULL,
  `idCompany` int(11) NOT NULL,
  `fullName` varchar(100) COLLATE utf8_bin NOT NULL,
  `phone` varchar(50) COLLATE utf8_bin NOT NULL,
  `skype` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `status` enum('admin','hr') COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `skype_UNIQUE` (`skype`),
  KEY `employee_idCompany_FK_idx` (`idCompany`),
  CONSTRAINT `employee_idCompany_FK` FOREIGN KEY (`idCompany`) REFERENCES `company` (`idCompany`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (4,'76d80224611fc919a5d54f0ff9fba446','epam.kutz@gmail.com',3,'Egor Kutz','+375291234567','kutzegor','admin'),(13,'46e44aa0bc21d8a826d79344df38be4b','epam.leonova@gmail.com',3,'Elena Leonova','+375291234567','leonovaelena','hr'),(14,'76d80224611fc919a5d54f0ff9fba446','epam.mazanik@gmail.com',3,'Zhanna Mazanik','+375291234232','mazanik','hr'),(16,'76d80224611fc919a5d54f0ff9fba446','google.brin@gmail.com',6,'Sergey Brin','+531291234567','googlebrin','admin'),(17,'46e44aa0bc21d8a826d79344df38be4b','google.ivanov@gmail.com',6,'Denis Ivanov','+375532634536','','hr'),(20,'46e44aa0bc21d8a826d79344df38be4b','apple.jobs@gmail.com',9,'Steve Jobs','+375291234567','stevejobs','admin'),(21,'46e44aa0bc21d8a826d79344df38be4b','apple.kuchick@gmail.com',9,'Andrey Kuchick','+375291234567','kuchickandre','hr'),(25,'46e44aa0bc21d8a826d79344df38be4b','epam.bladyko@gmail.com',3,'Yliana Bladyko','+375532634536',NULL,'hr'),(26,'46e44aa0bc21d8a826d79344df38be4b','twitter.dorcey@gmail.com',10,'Jack Dorcey','+375291234232','jackdorcey','admin');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respond`
--

DROP TABLE IF EXISTS `respond`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respond` (
  `idApplicant` int(11) NOT NULL,
  `idVacancy` int(11) NOT NULL,
  `stage` enum('phone','interview','answer') CHARACTER SET utf8 NOT NULL DEFAULT 'phone',
  `respondDate` date NOT NULL,
  `conversationDate` date DEFAULT NULL,
  `conversationNote` text CHARACTER SET utf8,
  `totalMark` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`idApplicant`,`idVacancy`),
  KEY `result_idVacancy_FK_idx` (`idVacancy`),
  KEY `result_idApplicant_FK` (`idApplicant`),
  CONSTRAINT `result_idApplicant_FK` FOREIGN KEY (`idApplicant`) REFERENCES `applicant` (`idApplicant`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `result_idVacancy_FK` FOREIGN KEY (`idVacancy`) REFERENCES `vacancy` (`idVacancy`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respond`
--

LOCK TABLES `respond` WRITE;
/*!40000 ALTER TABLE `respond` DISABLE KEYS */;
INSERT INTO `respond` VALUES (29,22,'interview','2017-01-19','2017-01-19','',0),(29,23,'phone','2017-01-21',NULL,NULL,0),(29,24,'phone','2017-01-25',NULL,NULL,0),(29,25,'phone','2017-01-21',NULL,NULL,0),(29,26,'phone','2017-01-24',NULL,NULL,0),(29,43,'phone','2017-01-31',NULL,NULL,0),(29,44,'phone','2017-01-31',NULL,NULL,0),(29,45,'phone','2017-01-31',NULL,NULL,0),(29,46,'phone','2017-01-31',NULL,NULL,0),(30,23,'phone','2017-01-31',NULL,NULL,0),(30,40,'interview','2017-01-31','2017-02-03','He is not bad at all\r\n',0),(30,43,'phone','2017-01-31',NULL,NULL,0),(30,46,'phone','2017-01-31',NULL,NULL,0),(53,22,'phone','2017-01-31',NULL,NULL,0),(53,23,'phone','2017-01-31',NULL,NULL,0),(53,26,'phone','2017-01-31',NULL,NULL,0),(53,40,'interview','2017-01-31','2017-02-05','Not so good\r\n',1),(53,43,'phone','2017-01-31',NULL,NULL,0),(53,44,'phone','2017-01-31',NULL,NULL,0),(53,45,'phone','2017-01-31',NULL,NULL,0),(54,23,'phone','2017-01-31',NULL,NULL,0),(54,40,'interview','2017-01-31','2017-02-05','',9),(54,43,'phone','2017-01-31',NULL,NULL,0);
/*!40000 ALTER TABLE `respond` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy`
--

DROP TABLE IF EXISTS `vacancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancy` (
  `idVacancy` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `idCompany` int(11) NOT NULL,
  `salary` int(11) NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  `requiredExperience` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `requiredSkills` text NOT NULL,
  `vacancyDescription` text NOT NULL,
  PRIMARY KEY (`idVacancy`),
  KEY `vacancy_idCompany_FK_idx` (`idCompany`),
  CONSTRAINT `vacancy_idCompany_FK` FOREIGN KEY (`idCompany`) REFERENCES `company` (`idCompany`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy`
--

LOCK TABLES `vacancy` WRITE;
/*!40000 ALTER TABLE `vacancy` DISABLE KEYS */;
INSERT INTO `vacancy` VALUES (22,'Senior Business Analyst',3,2100,'2017-01-15',4,'Work closely with customers and WorkFusioners to transform and develop new requirements into implementation\r\nProactively communicate and collaborate with external and internal customers to analyze information needs and functional requirements and deliver the needed artifacts (i.e., functional requirements, business requirements document, use cases, GUI and screen and Interface designs)\r\nOrganize and hold requirements workshops and drive the discussion','Imagine the expanse of human talent within a mega-bank, a huge insurance company or a giant healthcare organization: 20,000 super smart people spend half of their days hunting for information, categorizing and extracting documents, PDFs and websites, and keying the bits that matter into a structured format like an Excel spreadsheet. It’s drudgery, and it saps their capacity for more creative, revenue-generating work. Born of a research project at MIT CSAIL, we’ve built a platform that unobtrusively watches their efforts, finds patterns in huge volumes of data, and uses robotics and machine learning to automate the routine work. The smartest companies in every major industry use WorkFusion to improve workforce engagement, drive operational agility, and improve customer service.'),(23,'Senior iOS developer',6,0,'2017-01-31',0,'Strong knowledge of iOS is required (3+ years)\r\nSpoken English (Intermediate level or above) is required\r\nExcellent knowledge of SWIFT (or Objective C)\r\nAbility to travel to Europe and Unites States of America is a big plus','Opportunity for professional self-realization\r\nFriendly and united team\r\nCompetitive salary\r\n25-days of paid vacation\r\n100%-paid sick leave\r\nSport-program\r\nLanguage course and other corporate programs\r\nMedical insurance\r\nCompetitive (official) salary.'),(24,'Mid/Senior Python developer',6,0,'2017-01-15',0,'University Degree in software engineering or computer science field\r\nProfessional experience in Python + Django\r\nA deep knowledge of web development technologies and frameworks\r\nSolid professional MySQL/Postgresql experience\r\nUnderstanding the fundamentals of software design principles\r\nUpper-intermediate English is a must (internal language)','Opportunity for professional self-realization\r\nFriendly and united team\r\n25-days of paid vacation\r\n100%-paid sick leave\r\nSport-program\r\nLanguage course and other corporate programs\r\nMedical insurance\r\nCompetitive (official) salary'),(25,'Senior Java Developer',6,2500,'2017-01-15',7,'Experience in Java technologies > 3 years\r\nExpert of Java, OOP patterns\r\nArchitecture of high loaded Java applications\r\nSQL\r\njUnit\r\nGood Communication Skills\r\nGood Logical & Anlytical skills\r\nIntermediate English','Opportunity for professional self-realization\r\nFriendly and united team\r\ndays of paid vacation\r\n100%-paid sick leave\r\nSport-program\r\nLanguage courses and other corporate programs\r\nMedical insurance\r\nCompetitive (official) salary.'),(26,'Middle/Senior Java Developer',6,1700,'2017-01-15',3,'Experience with Java, J2EE and object-oriented analysis and design principles;\r\nExperience designing and developing web based applications using Java, JavaScript (JQuery), Spring (MVC & Inversion of Control), XHTML, Eclipse, Tomcat, CSS, Hibernate;\r\nQuick learner, eager to leverage new technologies in a dynamic team environment;\r\nSQL, Oracle Database;\r\nApache Ant/Ivy;\r\nWeb Services, AJAX, REST, JSON;\r\nJunit;\r\nAgile/Scrum;\r\nGood speaking English: being able to discuss technical topics;\r\nCommunication skills: voice/e-mail.','Project Description:\r\n\r\nAs a Middle/Senior Java Developer, you will get a unique chance to develop an innovative case management system, which allows keeping cases moving efficiently through the court. This system provides a total court solution by integrating e-filing, case management, and public access solutions with other court applications.\r\nAbout the customer:\r\n\r\nOur client is the world’s leading source of intelligent information for businesses and professionals. It combines industry expertise with innovative technology to deliver critical information to leading decision makers in the financial, legal, tax and accounting, scientific, healthcare and media markets, powered by the world’s most trusted news organization. With headquarters in New York and major operations in London and Eagan, Minnesota, our client employs more than 50,000 people in 93 countries.'),(40,'Global Publishing Producer (Mobile)',3,0,'2017-01-31',3,'2+ years’ experience in mobile F2P games production/publishing\r\nAt least 1 launched commercially successful F2P international mobile title (1M+ installs)\r\nUnderstanding what it takes to successfully publish a F2P mobile MMO\r\nStrong analytical skills\r\nAdvanced Excel user (as a data-driven instrument)\r\nFluent English\r\nAbility to set data-driven targets and achieve them\r\nAbility to make decisions and take responsibility for them\r\nAbility to work with limited resources\r\nPassion for mobile games','Take part in sourcing new projects\r\nCreate the product publishing strategy, joint KPIs and product roadmap\r\nCollaborate with the studio on the creation and tracking of the project’s milestone plan objectives, priorities and overall schedule\r\nSupervise the project’s development to maintain its area of focus and vision, work with dev roadmap and check development milestones\r\nBuild a strong working relationship with the game development team and help them with any obstacles to ensure that the game is fun, high-quality, on budget, and on time\r\nPartner with regional and global publishing teams to ensure developmental support as needed to achieve marketing, PR, and live operations goals globally\r\nRepresent the game in both internal and external communications. This includes delivering strong presentations and taking responsibility for internal reviews with key organizational stakeholder partner companies and handling external interviews with the media\r\n '),(43,'Delivery Manager (North America Delivery Organization)',9,2500,'2017-01-31',4,'Solid understanding of project management including different methodologies and tools;\r\n3+ years’ IT project management experience (planning, scheduling, project communication, issue resolution, change management, and risk management);\r\nStrong technical background: ability to be engaged into technology discussions with the client and stakeholders is a big plus;\r\nPractical and pragmatic knowledge of SDLCs; ability to transform project processes to Agile;\r\nExperience in managing implementation challenges in the area of integration, design, configurability, and infrastructure;\r\nGreat leadership skills: ability to manage cross-functional teams across multiple locations and domains implementing enterprise-wide and custom solutions, as well as direct and command respect from technical and delivery team members;\r\nExcellent communication, negotiation and client relationship management skills;\r\nGreat problem solving skills;\r\nGood command of English: Upper-Intermediate and higher level.','Currently we are looking for a Project Manager with the ability to learn quickly and join our Minsk group as a Delivery Manager in North America Delivery Organization.\r\n\r\nAs a Delivery Manager, you will get an opportunity to practice your skills in Product Management domain, SWAT cases and complex management situations resolutions while working on building sustainable partnership with world-famous brands in Media, Entertainment, Publishing, Travel and Hospitality business domains.\r\n\r\nYou will be responsible for the high-level project management and end-to-end product development. As a Delivery Manager, you will need to provide full assistance to the Project Management level in planning and problems solving, and to Project Sponsor in status tracking and business development.\r\n\r\nThe projects in our Delivery Group are not limited to a certain technology or platform. Instead, they are a mix of modern and trending solutions: video streaming; high load, Big Data and analytics; client applications for popular mobile, TV and IoT platforms; cloud management and cloud computing; content management and distribution platforms; Intelligent Search and Recommendation engines.'),(44,'Platform Manager',9,1500,'2017-01-31',3,'1+ year experience in B2B web projects management -or- in B2B web projects product management\\ownership\r\nUnderstanding of product management\r\nIntermediate+ English\r\nAnalytical mindset\r\nCommon sense','We’re looking for a product manager for our internal platforms. These are mostly inhouse web-based services and tools used for Apalon’s mobile marketing\\analytics\\sales needs. This is rare opportunity for experienced project manager to step up to the product owner shoes.'),(45,'.NET developer',9,300,'2017-01-31',4,'высшее техническое образование;\r\nотличное знание и понимание принципов ООП и практический опыт применения;\r\nопыт программирования с использованием .NET (C#, ASP.NET (MVC), Web API);\r\nпрактический опыт работы с СУБД;\r\nзнание JavaScript будет большим плюсом;\r\nжелание развиваться в сторону full-stack разработки;\r\nтехнический английский язык на уровне не ниже pre- Intermediate;\r\nличная инициативность и работоспособность;\r\nжелание работать в команде;\r\nстремление к постоянному развитию.','достойное вознаграждение по итогам интервью;\r\nработу над интересными проектами в молодой, сплоченной команде высококвалифицированных специалистов;\r\nотличные возможности для профессионального и карьерного роста;\r\nгибкий рабочий график, возможность совмещения работы и обучения;\r\nразвернутый соцпакет, бонусы и премии, занятия спортом, регулярные тимбилдинговые мероприятия, медстраховка и т.д.'),(46,'Desktop Software Engineer C++',10,0,'2017-01-31',3,'Fast learner that takes pride in their work and is very passionate about software technology.\r\n3+ years object-oriented development experience using C++ in a professional environment.\r\nSolid foundation in computer science fundamentals from data structures and algorithms to high-level design patterns.\r\nExcellent systems analytical, problem solving and technical skills.\r\nA commitment and drive for excellence and continual improvement and a strong sense of adventure, excitement and enthusiasm.\r\nBachelor\'s degree (CS, EE or Math preferred) or equivalent work experience as well as interest in a fast paced, complex, multi-team environment.','As a division of IAC (Nasdaq: IAC), IAC Applications brings together a unique collection of award-winning technology companies to form one of the world’s largest distributors of utility applications, with its products downloaded more than one million times a day across desktop, browser and mobile devices.\r\nSlimware Utilities is a leading desktop utilities software company that revolutionizes the way people clean, repair, update and optimize their personal computers. Our innovative products have won many prestigious awards, including PCMag’s Product of the Year. They are trusted by millions of users in more than 200 countries worldwide.\r\nSlimware Utilities has big aspirations and we’re looking for a rock-star Software Engineers to match. If you like developing innovative cutting edge solutions, improving best-in-class software to create perfection, then please read on! Slimware is looking for top \"roll-up your sleeves\" problem solving engineers who will build the best products in our industry. You\'ll get to develop cutting edge software with the latest tools in the trade and work with a world class team of engineers.\r\n'),(47,'Middle Salesforce Developer',10,1200,'2017-01-31',0,'Experience in Apex programing (triggers, controllers and classes) and testing;\r\nVisualforce programing;\r\nSalesforce coding limitations;\r\nExperience using Salesforce data tools;\r\nHTML/CSS/JavaScript experience;\r\nKnowledge of web development;\r\nDatabase knowledge;\r\nOOP knowledge;\r\nDesign patterns;\r\nIntermediate+ (B1+) level of English.','Currently we are looking for a Middle Salesforce Developer for our Vitebsk office to make the team even stronger.\r\nAs a Middle Salesforce Developer, you will join a Salesforce-based project, which has lots of integrations with internal customer’s systems as well as external systems; it also employs almost every feature provided by the platform. Meanwhile, there is a scope for optimization improvements, which are very often not trivial, but quite complicated. Thus, a possibility to grow professionally and lots of challenging tasks are waiting for you!\r\n\r\nBusiness trips to customer’s location are possible.');
/*!40000 ALTER TABLE `vacancy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-16 13:37:03
