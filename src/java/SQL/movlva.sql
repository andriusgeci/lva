-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2016 at 12:21 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
DROP TABLE IF EXISTS movie_in_genre;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS movies;
--
-- Database: `movlva`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `remove_review_update_movieRating_procedure` (IN `revID` INT)  BEGIN
	DECLARE 
		movieIdInReviews, 
		review_ratingInReviews, 
		ratingTotalInMovies, 
		num_ratingsInMovies 
	INT DEFAULT 0;
	
	DECLARE 
		avg_ratingInMovie 
	DECIMAL(2,1);
	
	SELECT movie_id 
	INTO movieIdInReviews 
	FROM reviews 
	WHERE review_id = revID 
	LIMIT 1;
	
	SELECT review_rating 
	INTO review_ratingInReviews 
	FROM reviews 
	WHERE review_id = revID 
	LIMIT 1;
		
	SELECT rating_total 
	INTO ratingTotalInMovies 
	FROM movies 
	WHERE movie_id = movieIdInReviews 
	LIMIT 1;
	
	SET ratingTotalInMovies := ratingTotalInMovies - review_ratingInReviews;
	
	SELECT num_ratings 
	INTO num_ratingsInMovies 
	FROM movies 
	WHERE movie_id = movieIdInReviews 
	LIMIT 1;
	
	SET num_ratingsInMovies := num_ratingsInMovies - 1;
	
	SELECT avg_rating 
	INTO avg_ratingInMovie 
	FROM movies 
	WHERE movie_id = movieIdInReviews 
	LIMIT 1;
	
	IF ratingTotalInMovies > 0 AND num_ratingsInMovies > 0  THEN
		SET avg_ratingInMovie := ratingTotalInMovies / num_ratingsInMovies;
		UPDATE movies 
		SET avg_rating = avg_ratingInMovie, 
			num_ratings = num_ratingsInMovies, 
			rating_total = ratingTotalInMovies 
		WHERE 
			movie_id = movieIdInReviews;

		DELETE FROM reviews 
		WHERE review_ID = revID; 
	
	ELSE
		UPDATE movies 
		SET avg_rating = 0, 
			num_ratings = 0, 
			rating_total = 0 
		WHERE 
			movie_id = movieIdInReviews;

		DELETE FROM reviews 
		WHERE review_ID = revID;
	END IF;
	
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `splitter` (IN `x` VARCHAR(255), IN `delim` VARCHAR(12), OUT `changedText` VARCHAR(255))  BEGIN


SET @Valcount = substrCount(x,delim)+1;
SET @v1=0;
SET @tm=0;

drop table if exists splitResults;
create TEMPORARY
table splitResults ( split_value varchar(255));

WHILE (@v1 < @Valcount) DO

set @val = stringSplit(x,delim,@v1+1);

SELECT count(*) 
	INTO @tm 
	FROM bad_words 
	WHERE word=@val
	LIMIT 1;

IF @tm > 0  THEN
	SET @val = '[%&$#?@!]';
ELSE
	SET @val = @val;
END IF;

INSERT INTO splitResults ( split_value) VALUES (@val);
SET @v1 = @v1 + 1;

END WHILE;


SELECT GROUP_CONCAT(split_value SEPARATOR ' ') FROM splitResults GROUP BY NULL INTO changedText;
select changedText;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `stringSplit` (`x` VARCHAR(255), `delim` VARCHAR(12), `pos` INT) RETURNS VARCHAR(255) CHARSET latin1 RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1), delim, '')$$

CREATE DEFINER=`root`@`localhost` FUNCTION `substrCount` (`s` VARCHAR(255), `ss` VARCHAR(255)) RETURNS TINYINT(3) UNSIGNED READS SQL DATA
BEGIN
DECLARE count TINYINT(3) UNSIGNED;
DECLARE offset TINYINT(3) UNSIGNED;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s = NULL;
SET count = 0;
SET offset = 1;
REPEAT
IF NOT ISNULL(s) AND offset > 0 THEN
SET offset = LOCATE(ss, s, offset);
IF offset > 0 THEN
SET count = count + 1;
SET offset = offset + 1;
END IF;
END IF;
UNTIL ISNULL(s) OR offset = 0 END REPEAT;
RETURN count;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `address_line_1` varchar(100) NOT NULL,
  `address_line_2` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `county` varchar(50) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `country` varchar(20) NOT NULL,
  `primary_address` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `user_id`, `address_line_1`, `address_line_2`, `city`, `county`, `postal_code`, `country`, `primary_address`) VALUES
(7, 1, '11 The Willows, rathmullan Road', '', 'Drogheda', 'Co.Louth', '123456', 'Ireland', 1);

-- --------------------------------------------------------

--
-- Table structure for table `audit`
--

CREATE TABLE `audit` (
  `audit_id` int(11) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `audit`
--

INSERT INTO `audit` (`audit_id`, `description`) VALUES
(3, 'User deleted: aaaaaa bbbbbb 2016-04-19 15:58:22'),
(4, 'User updated: testXXXXX test 2016-04-19 16:35:33'),
(5, 'User updated: ALikinas ttt 2016-04-19 17:01:24'),
(6, 'User deleted: ALikinas ttt 2016-04-19 17:02:04'),
(7, 'User deleted: testXXXXX test 2016-04-19 17:02:04'),
(8, 'New user Registered: testas testas 2016-04-20 20:54:38'),
(9, 'New user Registered: sdsd Willows 2016-04-20 21:38:52'),
(10, 'New user Registered: ddddddddd ddddddddd 2016-04-20 21:42:33'),
(11, 'New user Registered: xxx xxx 2016-04-20 21:45:08'),
(12, 'User deleted: testas testas 2016-04-20 22:31:37'),
(13, 'User deleted: sdsd Willows 2016-04-20 22:31:37'),
(14, 'User deleted: ddddddddd ddddddddd 2016-04-20 22:31:37'),
(15, 'User deleted: xxx xxx 2016-04-20 22:31:37'),
(16, 'New user Registered: xxx xxx 2016-04-20 22:33:28'),
(17, 'New user Registered: ccc ccc 2016-04-20 23:59:41'),
(18, 'New user Registered: ffff ffff 2016-04-21 13:27:07'),
(19, 'User deleted: ffff ffff 2016-04-21 13:44:52'),
(20, 'User deleted: xxx xxx 2016-04-21 13:45:26'),
(21, 'User deleted: ccc ccc 2016-04-21 13:48:45'),
(22, 'New user Registered: testas testas 2016-04-21 15:28:15'),
(23, 'User deleted: testas testas 2016-04-21 19:44:05'),
(24, 'New user Registered: sample forget 2016-04-21 19:52:42'),
(25, 'User deleted: sample forget 2016-04-26 07:12:14'),
(26, 'New user Registered: Lexi Forgt 2016-04-26 07:16:31'),
(27, 'New user Registered: tes tes 2016-04-26 14:38:02'),
(28, 'User updated: Lorenas Orleckas 2016-04-26 18:25:50'),
(29, 'User updated: Lorenas Orleckas 2016-04-26 18:27:18'),
(30, 'User updated: Lorenas Orleckas 2016-04-26 18:38:00'),
(31, 'User updated: Lorenas Orleckas 2016-04-26 18:38:38'),
(32, 'User updated: Lorenas Orleckas 2016-04-26 18:39:06'),
(33, 'User updated: Lorenas Orleckas 2016-04-26 18:40:09'),
(34, 'New user Registered: abc abc 2016-04-26 19:39:15'),
(35, 'User updated: Lorenas Orleckas 2016-04-26 22:37:49'),
(36, 'User updated: Lorenas Orleckas 2016-04-27 00:43:40'),
(37, 'User updated: Lorenas Orleckas 2016-04-27 09:55:50'),
(38, 'User updated: Lorenas Orleckas 2016-04-27 09:55:55'),
(39, 'User deleted: abc abc 2016-04-27 13:11:51'),
(40, 'User updated: Lorenas Orleckas 2016-04-27 13:25:28'),
(41, 'User updated: Andrius Geciauskas 2016-04-27 13:25:45'),
(42, 'User updated: Vitalijus Nikovas 2016-04-27 13:26:03'),
(43, 'User updated: John Delainey 2016-04-27 13:26:25'),
(44, 'User updated: Connor McGregor 2016-04-27 13:26:40'),
(45, 'User updated: Arnold Swarzneger 2016-04-27 13:27:01'),
(46, 'User updated: Kim Kardashian 2016-04-27 13:27:13'),
(47, 'User updated: Alex Brewer 2016-04-27 13:27:23'),
(48, 'User updated: Benjamin Brewer 2016-04-27 13:27:42'),
(49, 'User updated: Adam Hirsch 2016-04-27 13:27:54'),
(50, 'User updated: Nicolas Cage 2016-04-27 13:28:13'),
(51, 'User updated: Elijah Wood 2016-04-27 13:28:25'),
(52, 'User updated: Jerry Lewis 2016-04-27 13:28:40'),
(53, 'User updated: Ilya Naishuller 2016-04-27 13:29:27'),
(54, 'User updated: Haley Bennett 2016-04-27 13:29:37'),
(55, 'User updated: Tim Roth 2016-04-27 13:29:54'),
(56, 'User updated: Sharlto Copley 2016-04-27 13:30:06'),
(57, 'User updated: Will Stewart 2016-04-27 13:30:16'),
(58, 'User updated: Mark Strong 2016-04-27 13:30:32'),
(59, 'User updated: Chloe Grace 2016-04-27 13:30:44'),
(60, 'User updated: Frank DAmico 2016-04-27 13:30:58'),
(61, 'User updated: Milana Vayntrub 2016-04-27 13:31:10'),
(62, 'User updated: Freddie Prinze 2016-04-27 13:31:20'),
(63, 'User updated: Lorenas Orleckas 2016-04-27 13:31:37'),
(64, 'User updated: Aidan Quinn 2016-04-27 13:31:48'),
(65, 'User updated: Boris Kodjoe 2016-04-27 13:31:59'),
(66, 'New user Registered: firstName lastName 2016-04-27 23:18:25'),
(67, 'User deleted: firstName lastName 2016-04-27 23:18:25');

-- --------------------------------------------------------

--
-- Table structure for table `bad_words`
--

CREATE TABLE `bad_words` (
  `id` int(11) NOT NULL,
  `word` varchar(100) NOT NULL,
  `replacement` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bad_words`
--

INSERT INTO `bad_words` (`id`, `word`, `replacement`) VALUES
(0, 'ahole ', '[censored]'),
(1, 'anus ', '[censored]'),
(2, 'ash0le ', '[censored]'),
(3, 'ash0les ', '[censored]'),
(4, 'asholes ', '[censored]'),
(5, 'ass ', '[censored]'),
(6, 'Ass Monkey ', '[censored]'),
(7, 'Assface ', '[censored]'),
(8, 'assh0le ', '[censored]'),
(9, 'assh0lez ', '[censored]'),
(10, 'asshole ', '[censored]'),
(11, 'assholes ', '[censored]'),
(12, 'assholz ', '[censored]'),
(13, 'asswipe ', '[censored]'),
(14, 'azzhole ', '[censored]'),
(15, 'bassterds ', '[censored]'),
(16, 'bastard ', '[censored]'),
(17, 'bastards ', '[censored]'),
(18, 'bastardz ', '[censored]'),
(19, 'basterds ', '[censored]'),
(20, 'basterdz ', '[censored]'),
(21, 'Biatch ', '[censored]'),
(22, 'bitch ', '[censored]'),
(23, 'bitches ', '[censored]'),
(24, 'Blow Job ', '[censored]'),
(25, 'boffing ', '[censored]'),
(26, 'butthole ', '[censored]'),
(27, 'buttwipe ', '[censored]'),
(28, 'c0ck ', '[censored]'),
(29, 'c0cks ', '[censored]'),
(30, 'c0k ', '[censored]'),
(31, 'Carpet Muncher ', '[censored]'),
(32, 'cawk ', '[censored]'),
(33, 'cawks ', '[censored]'),
(34, 'Clit ', '[censored]'),
(35, 'cnts ', '[censored]'),
(36, 'cntz ', '[censored]'),
(37, 'cock ', '[censored]'),
(38, 'cockhead ', '[censored]'),
(39, 'cock-head ', '[censored]'),
(40, 'cocks ', '[censored]'),
(41, 'CockSucker ', '[censored]'),
(42, 'cock-sucker ', '[censored]'),
(43, 'crap ', '[censored]'),
(44, 'cum ', '[censored]'),
(45, 'cunt ', '[censored]'),
(46, 'cunts ', '[censored]'),
(47, 'cuntz ', '[censored]'),
(48, 'dick ', '[censored]'),
(49, 'dild0 ', '[censored]'),
(50, 'dild0s ', '[censored]'),
(51, 'dildo ', '[censored]'),
(52, 'dildos ', '[censored]'),
(53, 'dilld0 ', '[censored]'),
(54, 'dilld0s ', '[censored]'),
(55, 'dominatricks ', '[censored]'),
(56, 'dominatrics ', '[censored]'),
(57, 'dominatrix ', '[censored]'),
(58, 'dyke ', '[censored]'),
(59, 'enema ', '[censored]'),
(60, 'f u c k ', '[censored]'),
(61, 'f u c k e r ', '[censored]'),
(62, 'fag ', '[censored]'),
(63, 'fag1t ', '[censored]'),
(64, 'faget ', '[censored]'),
(65, 'fagg1t ', '[censored]'),
(66, 'faggit ', '[censored]'),
(67, 'faggot ', '[censored]'),
(68, 'fagit ', '[censored]'),
(69, 'fags ', '[censored]'),
(70, 'fagz ', '[censored]'),
(71, 'faig ', '[censored]'),
(72, 'faigs ', '[censored]'),
(73, 'fart ', '[censored]'),
(74, 'flipping the bird ', '[censored]'),
(75, 'fuck ', '[censored]'),
(76, 'fucker ', '[censored]'),
(77, 'fuckin ', '[censored]'),
(78, 'fucking ', '[censored]'),
(79, 'fucks ', '[censored]'),
(80, 'Fudge Packer ', '[censored]'),
(81, 'fuk ', '[censored]'),
(82, 'Fukah ', '[censored]'),
(83, 'Fuken ', '[censored]'),
(84, 'fuker ', '[censored]'),
(85, 'Fukin ', '[censored]'),
(86, 'Fukk ', '[censored]'),
(87, 'Fukkah ', '[censored]'),
(88, 'Fukken ', '[censored]'),
(89, 'Fukker ', '[censored]'),
(90, 'Fukkin ', '[censored]'),
(91, 'g00k ', '[censored]'),
(92, 'gay ', '[censored]'),
(93, 'gayboy ', '[censored]'),
(94, 'gaygirl ', '[censored]'),
(95, 'gays ', '[censored]'),
(96, 'gayz ', '[censored]'),
(97, 'God-damned ', '[censored]'),
(98, 'h00r ', '[censored]'),
(99, 'h0ar ', '[censored]'),
(100, 'h0re ', '[censored]'),
(101, 'hells ', '[censored]'),
(102, 'hoar ', '[censored]'),
(103, 'hoor ', '[censored]'),
(104, 'hoore ', '[censored]'),
(105, 'jackoff ', '[censored]'),
(106, 'jap ', '[censored]'),
(107, 'japs ', '[censored]'),
(108, 'jerk-off ', '[censored]'),
(109, 'jisim ', '[censored]'),
(110, 'jiss ', '[censored]'),
(111, 'jizm ', '[censored]'),
(112, 'jizz ', '[censored]'),
(113, 'knob ', '[censored]'),
(114, 'knobs ', '[censored]'),
(115, 'knobz ', '[censored]'),
(116, 'kunt ', '[censored]'),
(117, 'kunts ', '[censored]'),
(118, 'kuntz ', '[censored]'),
(119, 'Lesbian ', '[censored]'),
(120, 'Lezzian ', '[censored]'),
(121, 'Lipshits ', '[censored]'),
(122, 'Lipshitz ', '[censored]'),
(123, 'masochist ', '[censored]'),
(124, 'masokist ', '[censored]'),
(125, 'massterbait ', '[censored]'),
(126, 'masstrbait ', '[censored]'),
(127, 'masstrbate ', '[censored]'),
(128, 'masterbaiter ', '[censored]'),
(129, 'masterbate ', '[censored]'),
(130, 'masterbates ', '[censored]'),
(131, 'Motha Fucker ', '[censored]'),
(132, 'Motha Fuker ', '[censored]'),
(133, 'Motha Fukkah ', '[censored]'),
(134, 'Motha Fukker ', '[censored]'),
(135, 'Mother Fucker ', '[censored]'),
(136, 'Mother Fukah ', '[censored]'),
(137, 'Mother Fuker ', '[censored]'),
(138, 'Mother Fukkah ', '[censored]'),
(139, 'Mother Fukker ', '[censored]'),
(140, 'mother-fucker ', '[censored]'),
(141, 'Mutha Fucker ', '[censored]'),
(142, 'Mutha Fukah ', '[censored]'),
(143, 'Mutha Fuker ', '[censored]'),
(144, 'Mutha Fukkah ', '[censored]'),
(145, 'Mutha Fukker ', '[censored]'),
(146, 'n1gr ', '[censored]'),
(147, 'nastt ', '[censored]'),
(148, 'nigger; ', '[censored]'),
(149, 'nigur; ', '[censored]'),
(150, 'niiger; ', '[censored]'),
(151, 'niigr; ', '[censored]'),
(152, 'orafis ', '[censored]'),
(153, 'orgasim; ', '[censored]'),
(154, 'orgasm ', '[censored]'),
(155, 'orgasum ', '[censored]'),
(156, 'oriface ', '[censored]'),
(157, 'orifice ', '[censored]'),
(158, 'orifiss ', '[censored]'),
(159, 'packi ', '[censored]'),
(160, 'packie ', '[censored]'),
(161, 'packy ', '[censored]'),
(162, 'paki ', '[censored]'),
(163, 'pakie ', '[censored]'),
(164, 'paky ', '[censored]'),
(165, 'pecker ', '[censored]'),
(166, 'peeenus ', '[censored]'),
(167, 'peeenusss ', '[censored]'),
(168, 'peenus ', '[censored]'),
(169, 'peinus ', '[censored]'),
(170, 'pen1s ', '[censored]'),
(171, 'penas ', '[censored]'),
(172, 'penis ', '[censored]'),
(173, 'penis-breath ', '[censored]'),
(174, 'penus ', '[censored]'),
(175, 'penuus ', '[censored]'),
(176, 'Phuc ', '[censored]'),
(177, 'Phuck ', '[censored]'),
(178, 'Phuk ', '[censored]'),
(179, 'Phuker ', '[censored]'),
(180, 'Phukker ', '[censored]'),
(181, 'polac ', '[censored]'),
(182, 'polack ', '[censored]'),
(183, 'polak ', '[censored]'),
(184, 'Poonani ', '[censored]'),
(185, 'pr1c ', '[censored]'),
(186, 'pr1ck ', '[censored]'),
(187, 'pr1k ', '[censored]'),
(188, 'pusse ', '[censored]'),
(189, 'pussee ', '[censored]'),
(190, 'pussy ', '[censored]'),
(191, 'puuke ', '[censored]'),
(192, 'puuker ', '[censored]'),
(193, 'queer ', '[censored]'),
(194, 'queers ', '[censored]'),
(195, 'queerz ', '[censored]'),
(196, 'qweers ', '[censored]'),
(197, 'qweerz ', '[censored]'),
(198, 'qweir ', '[censored]'),
(199, 'recktum ', '[censored]'),
(200, 'rectum ', '[censored]'),
(201, 'retard ', '[censored]'),
(202, 'sadist ', '[censored]'),
(203, 'scank ', '[censored]'),
(204, 'schlong ', '[censored]'),
(205, 'screwing ', '[censored]'),
(206, 'semen ', '[censored]'),
(207, 'sex ', '[censored]'),
(208, 'sexy ', '[censored]'),
(209, 'Sh!t ', '[censored]'),
(210, 'sh1t ', '[censored]'),
(211, 'sh1ter ', '[censored]'),
(212, 'sh1ts ', '[censored]'),
(213, 'sh1tter ', '[censored]'),
(214, 'sh1tz ', '[censored]'),
(215, 'shit ', '[censored]'),
(216, 'shits ', '[censored]'),
(217, 'shitter ', '[censored]'),
(218, 'Shitty ', '[censored]'),
(219, 'Shity ', '[censored]'),
(220, 'shitz ', '[censored]'),
(221, 'Shyt ', '[censored]'),
(222, 'Shyte ', '[censored]'),
(223, 'Shytty ', '[censored]'),
(224, 'Shyty ', '[censored]'),
(225, 'skanck ', '[censored]'),
(226, 'skank ', '[censored]'),
(227, 'skankee ', '[censored]'),
(228, 'skankey ', '[censored]'),
(229, 'skanks ', '[censored]'),
(230, 'Skanky ', '[censored]'),
(231, 'slut ', '[censored]'),
(232, 'sluts ', '[censored]'),
(233, 'Slutty ', '[censored]'),
(234, 'slutz ', '[censored]'),
(235, 'son-of-a-bitch ', '[censored]'),
(236, 'tit ', '[censored]'),
(237, 'turd ', '[censored]'),
(238, 'va1jina ', '[censored]'),
(239, 'vag1na ', '[censored]'),
(240, 'vagiina ', '[censored]'),
(241, 'vagina ', '[censored]'),
(242, 'vaj1na ', '[censored]'),
(243, 'vajina ', '[censored]'),
(244, 'vullva ', '[censored]'),
(245, 'vulva ', '[censored]'),
(246, 'w0p ', '[censored]'),
(247, 'wh00r ', '[censored]'),
(248, 'wh0re ', '[censored]'),
(249, 'whore ', '[censored]'),
(250, 'xrated ', '[censored]'),
(251, 'xxx', '[censored]'),
(252, 'b!+ch', '[censored]'),
(253, 'bitch', '[censored]'),
(254, 'blowjob', '[censored]'),
(255, 'clit', '[censored]'),
(256, 'arschloch', '[censored]'),
(257, 'fuck', '[censored]'),
(258, 'shit', '[censored]'),
(259, 'ass', '[censored]'),
(260, 'asshole', '[censored]'),
(261, 'b!tch', '[censored]'),
(262, 'b17ch', '[censored]'),
(263, 'b1tch', '[censored]'),
(264, 'bastard', '[censored]'),
(265, 'bi+ch', '[censored]'),
(266, 'boiolas', '[censored]'),
(267, 'buceta', '[censored]'),
(268, 'c0ck', '[censored]'),
(269, 'cawk', '[censored]'),
(270, 'chink', '[censored]'),
(271, 'cipa', '[censored]'),
(272, 'clits', '[censored]'),
(273, 'cock', '[censored]'),
(274, 'cum', '[censored]'),
(275, 'cunt', '[censored]'),
(276, 'dildo', '[censored]'),
(277, 'dirsa', '[censored]'),
(278, 'ejakulate', '[censored]'),
(279, 'fatass', '[censored]'),
(280, 'fcuk', '[censored]'),
(281, 'fuk', '[censored]'),
(282, 'fux0r', '[censored]'),
(283, 'hoer', '[censored]'),
(284, 'hore', '[censored]'),
(285, 'jism', '[censored]'),
(286, 'kawk', '[censored]'),
(287, 'l3itch', '[censored]'),
(288, 'l3i+ch', '[censored]'),
(289, 'lesbian', '[censored]'),
(290, 'masturbate', '[censored]'),
(291, 'masterbat*', '[censored]'),
(292, 'masterbat3', '[censored]'),
(293, 'motherfucker', '[censored]'),
(294, 's.o.b.', '[censored]'),
(295, 'mofo', '[censored]'),
(296, 'nazi', '[censored]'),
(297, 'nigga', '[censored]'),
(298, 'nigger', '[censored]'),
(299, 'nutsack', '[censored]'),
(300, 'phuck', '[censored]'),
(301, 'pimpis', '[censored]'),
(302, 'pusse', '[censored]'),
(303, 'pussy', '[censored]'),
(304, 'scrotum', '[censored]'),
(305, 'sh!t', '[censored]'),
(306, 'shemale', '[censored]'),
(307, 'shi+', '[censored]'),
(308, 'sh!+', '[censored]'),
(309, 'slut', '[censored]'),
(310, 'smut', '[censored]'),
(311, 'teets', '[censored]'),
(312, 'tits', '[censored]'),
(313, 'boobs', '[censored]'),
(314, 'b00bs', '[censored]'),
(315, 'teez', '[censored]'),
(316, 'testical', '[censored]'),
(317, 'testicle', '[censored]'),
(318, 'titt', '[censored]'),
(319, 'w00se', '[censored]'),
(320, 'jackoff', '[censored]'),
(321, 'wank', '[censored]'),
(322, 'whoar', '[censored]'),
(323, 'whore', '[censored]'),
(324, '*damn', '[censored]'),
(325, '*dyke', '[censored]'),
(326, '*fuck*', '[censored]'),
(327, '*shit*', '[censored]'),
(328, '@$$', '[censored]'),
(329, 'amcik', '[censored]'),
(330, 'andskota', '[censored]'),
(331, 'arse*', '[censored]'),
(332, 'assrammer', '[censored]'),
(333, 'ayir', '[censored]'),
(334, 'bi7ch', '[censored]'),
(335, 'bitch*', '[censored]'),
(336, 'bollock*', '[censored]'),
(337, 'breasts', '[censored]'),
(338, 'butt-pirate', '[censored]'),
(339, 'cabron', '[censored]'),
(340, 'cazzo', '[censored]'),
(341, 'chraa', '[censored]'),
(342, 'chuj', '[censored]'),
(343, 'Cock*', '[censored]'),
(344, 'cunt*', '[censored]'),
(345, 'd4mn', '[censored]'),
(346, 'daygo', '[censored]'),
(347, 'dego', '[censored]'),
(348, 'dick*', '[censored]'),
(349, 'dike*', '[censored]'),
(350, 'dupa', '[censored]'),
(351, 'dziwka', '[censored]'),
(352, 'ejackulate', '[censored]'),
(353, 'Ekrem*', '[censored]'),
(354, 'Ekto', '[censored]'),
(355, 'enculer', '[censored]'),
(356, 'faen', '[censored]'),
(357, 'fag*', '[censored]'),
(358, 'fanculo', '[censored]'),
(359, 'fanny', '[censored]'),
(360, 'feces', '[censored]'),
(361, 'feg', '[censored]'),
(362, 'Felcher', '[censored]'),
(363, 'ficken', '[censored]'),
(364, 'fitt*', '[censored]'),
(365, 'Flikker', '[censored]'),
(366, 'foreskin', '[censored]'),
(367, 'Fotze', '[censored]'),
(368, 'Fu(*', '[censored]'),
(369, 'fuk*', '[censored]'),
(370, 'futkretzn', '[censored]'),
(371, 'gay', '[censored]'),
(372, 'gook', '[censored]'),
(373, 'guiena', '[censored]'),
(374, 'h0r', '[censored]'),
(375, 'h4x0r', '[censored]'),
(376, 'hell', '[censored]'),
(377, 'helvete', '[censored]'),
(378, 'hoer*', '[censored]'),
(379, 'honkey', '[censored]'),
(380, 'Huevon', '[censored]'),
(381, 'hui', '[censored]'),
(382, 'injun', '[censored]'),
(383, 'jizz', '[censored]'),
(384, 'kanker*', '[censored]'),
(385, 'kike', '[censored]'),
(386, 'klootzak', '[censored]'),
(387, 'kraut', '[censored]'),
(388, 'knulle', '[censored]'),
(389, 'kuk', '[censored]'),
(390, 'kuksuger', '[censored]'),
(391, 'Kurac', '[censored]'),
(392, 'kurwa', '[censored]'),
(393, 'kusi*', '[censored]'),
(394, 'kyrpa*', '[censored]'),
(395, 'lesbo', '[censored]'),
(396, 'mamhoon', '[censored]'),
(397, 'masturbat*', '[censored]'),
(398, 'merd*', '[censored]'),
(399, 'mibun', '[censored]'),
(400, 'monkleigh', '[censored]'),
(401, 'mouliewop', '[censored]'),
(402, 'muie', '[censored]'),
(403, 'mulkku', '[censored]'),
(404, 'muschi', '[censored]'),
(405, 'nazis', '[censored]'),
(406, 'nepesaurio', '[censored]'),
(407, 'nigger*', '[censored]'),
(408, 'orospu', '[censored]'),
(409, 'paska*', '[censored]'),
(410, 'perse', '[censored]'),
(411, 'picka', '[censored]'),
(412, 'pierdol*', '[censored]'),
(413, 'pillu*', '[censored]'),
(414, 'pimmel', '[censored]'),
(415, 'piss*', '[censored]'),
(416, 'pizda', '[censored]'),
(417, 'poontsee', '[censored]'),
(418, 'poop', '[censored]'),
(419, 'porn', '[censored]'),
(420, 'p0rn', '[censored]'),
(421, 'pr0n', '[censored]'),
(422, 'preteen', '[censored]'),
(423, 'pula', '[censored]'),
(424, 'pule', '[censored]'),
(425, 'puta', '[censored]'),
(426, 'puto', '[censored]'),
(427, 'qahbeh', '[censored]'),
(428, 'queef*', '[censored]'),
(429, 'rautenberg', '[censored]'),
(430, 'schaffer', '[censored]'),
(431, 'scheiss*', '[censored]'),
(432, 'schlampe', '[censored]'),
(433, 'schmuck', '[censored]'),
(434, 'screw', '[censored]'),
(435, 'sh!t*', '[censored]'),
(436, 'sharmuta', '[censored]'),
(437, 'sharmute', '[censored]'),
(438, 'shipal', '[censored]'),
(439, 'shiz', '[censored]'),
(440, 'skribz', '[censored]'),
(441, 'skurwysyn', '[censored]'),
(442, 'sphencter', '[censored]'),
(443, 'spic', '[censored]'),
(444, 'spierdalaj', '[censored]'),
(445, 'splooge', '[censored]'),
(446, 'suka', '[censored]'),
(447, 'b00b*', '[censored]'),
(448, 'testicle*', '[censored]'),
(449, 'titt*', '[censored]'),
(450, 'twat', '[censored]'),
(451, 'vittu', '[censored]'),
(452, 'wank*', '[censored]'),
(453, 'wetback*', '[censored]'),
(454, 'wichser', '[censored]'),
(455, 'wop*', '[censored]'),
(456, 'yed', '[censored]'),
(457, 'zabourah', '[censored]');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genre_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT 'Genre Names'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genre_id`, `name`) VALUES
(1, 'Action'),
(2, 'Animation'),
(3, 'Comedy'),
(4, 'Documentary'),
(5, 'Family'),
(6, 'Horror'),
(7, 'Musical'),
(8, 'Romance'),
(9, 'Sport'),
(10, 'War'),
(11, 'Adventure'),
(12, 'Biography'),
(13, 'Crime'),
(14, 'Drama'),
(15, 'Fantasy'),
(16, 'History'),
(17, 'Music'),
(18, 'Mystery'),
(19, 'Sci-Fi'),
(20, 'Thriller'),
(21, 'Western');

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `movie_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `release_Year` int(4) NOT NULL,
  `age_rating` varchar(10) NOT NULL,
  `movie_length` int(3) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `price` double(4,2) NOT NULL,
  `quantity_in_stock` int(3) DEFAULT NULL,
  `avg_rating` decimal(2,1) DEFAULT '0.0',
  `num_ratings` int(11) DEFAULT '0',
  `rating_total` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`movie_id`, `title`, `description`, `release_Year`, `age_rating`, `movie_length`, `img_url`, `price`, `quantity_in_stock`, `avg_rating`, `num_ratings`, `rating_total`) VALUES
(1, 'Elysium', 'In the year 2159, two classes of people exist: the very wealthy who live on a pristine man-made space station called Elysium, and the rest, who live on an overpopulated, ruined Earth. Secretary Rhodes (Jodie Foster), a hard line government of?cial, will stop at nothing to enforce anti-immigration laws and preserve the luxurious lifestyle of the citizens of Elysium.  ', 2013, 'R', 120, 'https://image.tmdb.org/t/p/original/tHkjoAxmhp3Eau1h0Ir7maKMwUz.jpg', 29.99, 13, '3.0', 1, 3),
(2, 'Deadpool', 'Based upon Marvel Comics’ most unconventional anti-hero, DEADPOOL tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.', 2016, 'R', 108, 'https://image.tmdb.org/t/p/original/inVq3FRqcYIRl2la8iZikYYxFNR.jpg', 21.99, 0, '3.9', 10, 39),
(3, 'Race', 'Jesse Owens quest to become the greatest track and field athlete in history thrusts him onto the world stage of the 1936 Olympics, where he faces off against Adolf Hitlers vision of Aryan supremacy.', 2016, 'PG-13', 96, 'https://image.tmdb.org/t/p/original/szLfTQ2vLFQkNbP3dc0pnco1rje.jpg', 22.99, 5, '4.5', 2, 9),
(4, 'Risen', 'Follows the epic Biblical story of the Resurrection, as told through the eyes of a non-believer. Clavius, a powerful Roman Military Tribune, and his aide Lucius, are tasked with solving the mystery of what happened to Jesus in the weeks following the crucifixion, in order to disprove the rumors of a risen Messiah and prevent an uprising in Jerusalem.', 2015, 'PG-13', 107, 'https://image.tmdb.org/t/p/original/cr8lQd94bxlhyDfimweUMnLZoxf.jpg', 5.99, 4, '3.0', 1, 3),
(5, 'The Witch', 'A family in 1630s New England is torn apart by the forces of witchcraft, black magic and possession.', 2015, 'R', 93, 'https://image.tmdb.org/t/p/original/jfGsfzq5JrMAKbAOV2TmhsOs3tf.jpg', 10.99, 29, '0.0', 0, 0),
(6, 'Embrance of the Serpent', 'The story of the relationship between Karamakate, an Amazonian shaman and last survivor of his people, and two scientists who work together over the course of 40 years to search the Amazon for a sacred healing plant.', 2015, 'NR', 125, 'http://ia.media-imdb.com/images/M/MV5BMjIwNjA0NzM3Ml5BMl5BanBnXkFtZTgwNjU4NTQ0NzE@.jpg', 5.99, 9, '0.0', 0, 0),
(7, 'Busco novio para mi mujer', 'Paco, so fed up with his nagging and nitpicking wife, comes up with an ingenious plan to end the unhappy union: Find her a boyfriend by hiring a professional seducer nicknamed "El Taiger" to whisk her off her feet and out of the marriage.', 2016, 'PG-13', 92, 'https://image.tmdb.org/t/p/original/9q09alroCs3IidJyDaGqH6VZpPM.jpg', 18.99, 20, '0.0', 0, 0),
(8, 'Neerja', 'Neerja is a portrayal on the life of the courageous Neerja Bhanot, who sacrificed her life while protecting the lives of 359 passengers on the Pan Am flight 73 in 1986. The flight was hijacked by a terrorist organization.', 2016, 'NR', 122, 'https://image.tmdb.org/t/p/original/97qsAXZ31E2VYfQY2zgy4djxOWE.jpg', 23.99, 5, '1.0', 1, 1),
(9, 'Rolling Papers', 'At ground zero of the green rush, The Denver Post became the first major media outlet to appoint a marijuana editor. Policy news, strain reviews, parenting advice and edible recipes are the new norm in the unprecedented world of pot journalism.', 2015, 'R', 79, 'https://image.tmdb.org/t/p/original/ckPHkH3rmAqoMpj0cbnxTrZrwo4.jpg', 8.99, 9, '0.0', 0, 0),
(10, 'Kung Fu Panda 3', 'Continuing his "legendary adventures of awesomeness", Po must face two hugely epic, but different threats: one supernatural and the other a little closer to his home.', 2016, 'PG', 95, 'https://image.tmdb.org/t/p/original/9wCrh57WO3mPmn1hWnJRcvdFtIb.jpg', 50.00, 45, '0.0', 0, 0),
(11, 'How to Be Single', 'New York City is full of lonely hearts seeking the right match, and what Alice, Robin, Lucy, Meg, Tom and David all have in common is the need to learn how to be single in a world filled with ever-evolving definitions of love.', 2016, 'R', 110, 'https://image.tmdb.org/t/p/original/5pVA4m8oTGN1ML9iQXtmaFRzQoE.jpg', 25.99, 10, '0.0', 0, 0),
(12, 'Zoolander 2', 'Derek and Hansel are lured into modeling again, in Rome, where they find themselves the target of a sinister conspiracy.', 2014, 'PG-13', 110, 'https://image.tmdb.org/t/p/original/u2s2WOskiEH71WbGCZehZ0shduO.jpg', 15.00, 5, '0.0', 0, 0),
(13, 'The Revenant', 'A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.', 2015, 'R', 156, 'https://image.tmdb.org/t/p/original/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg', 18.99, 40, '0.0', 0, 0),
(14, 'Hail, Caesar!', 'A Hollywood fixer in the 1950s works to keep the studio''s stars in line.', 2016, 'PG-13', 106, 'https://image.tmdb.org/t/p/original/rzMYZYphL9qrbBClQq1Jc0zPMmY.jpg', 14.23, 12, '0.0', 0, 0),
(15, 'Star Wars: Episode VII - The Force Awakens', 'Three decades after the defeat of the Galactic Empire, a new threat arises. The First Order attempts to rule the galaxy and only a ragtag group of heroes can stop them, along with the help of the Resistance.', 2015, 'PG-13', 135, 'https://image.tmdb.org/t/p/original/fYzpM9GmpBlIC893fNjoWCwE24H.jpg', 60.99, 33, '0.0', 0, 0),
(16, 'The Choice', 'Travis and Gabby first meet as neighbors in a small coastal town and wind up in a relationship that is tested by life''s most defining events.', 2016, 'PG-13', 111, 'https://image.tmdb.org/t/p/original/eJDrrzhTTdSAt2weiFQo7BozYJe.jpg', 8.00, 19, '0.0', 0, 0),
(17, 'Ride Along 2', 'As his wedding day approaches, Ben heads to Miami with his soon-to-be brother-in-law James to bring down a drug dealer who''s supplying the dealers of Atlanta with product.', 2016, 'PG-13', 102, 'https://image.tmdb.org/t/p/original/A739odgY2FjoLvYLYdypUc6GpaY.jpg', 18.99, 49, '0.0', 0, 0),
(18, 'The Boy', 'An American nanny is shocked that her new English family''s boy is actually a life-sized doll. After she violates a list of strict rules, disturbing events make her believe that the doll is really alive.', 2016, 'PG-13', 97, 'https://image.tmdb.org/t/p/original/lISBWF6UUF3cQPcoLE8pMxnyJty.jpg', 4.99, 20, '0.0', 0, 0),
(19, 'Gods of Egypt', 'A common thief joins a mythical god on a quest through Egypt.', 2016, 'PG-13', 100, 'https://image.tmdb.org/t/p/original/yTdTuJww8NnL9YLaxL2LxDG5uQ7.jpg', 12.99, 33, '0.0', 0, 0),
(20, 'Eddie the Eagle', 'The story of Eddie Edwards, the notoriously tenacious British underdog ski jumper who charmed the world at the 1988 Winter Olympics.', 2016, 'PG-13', 105, 'https://image.tmdb.org/t/p/original/laEdJnz9CiojWcNpqFjPKhrKuBo.jpg', 12.00, 18, '0.0', 0, 0),
(21, 'Triple 9', 'A gang of criminals and corrupt cops plan the murder of a police officer in order to pull off their biggest heist yet across town.', 2016, 'R', 115, 'https://image.tmdb.org/t/p/original/dwRHF43p50wLIYAIxrUhLkK3jCI.jpg', 19.99, 2, '0.0', 0, 0),
(22, 'Crouching Tiger, Hidden Dragon: Sword of Destiny', 'A story of lost love, young love, a legendary sword and one last opportunity at redemption.', 2016, 'PG-13', 108, 'https://image.tmdb.org/t/p/original/8O5SSJX0jR1hgM68Q2aGuxF8wzl.jpg', 10.99, 13, '0.0', 0, 0),
(23, 'Tere Bin Laden Dead or Alive', 'A Bollywood director is hired by an American talent agent to make a film on Osama bin Laden. The plan to use an Osama doppelganger in order to prove his death boomerangs when a Taliban arms dealer hijacks their idea to prove he''s alive.', 2016, 'R', 96, 'http://ia.media-imdb.com/images/M/MV5BMTA3MTM1Mjk3NjheQTJeQWpwZ15BbWU4MDcwMTcwMDgx.jpg', 11.99, 48, '0.0', 0, 0),
(24, 'The Last Man on the Moon', 'When Apollo astronaut Gene Cernan stepped off the moon in December 1972 he left his footprints and his daughter''s initials in the lunar dust. Only now is he ready to share his epic but deeply personal story of fulfillment, love, and loss.', 2014, 'R', 95, 'https://image.tmdb.org/t/p/original/yx5vJ1QbPJpX3AvXfkexFt1AKH1.jpg', 9.99, 12, '0.0', 0, 0),
(25, 'Regression', 'A detective and a psychoanalyst uncover evidence of a satanic cult while investigating the rape of a young woman.', 2015, 'R', 106, 'https://image.tmdb.org/t/p/original/d1dtQQfJJWU31cGSFbfhyeLtsTm.jpg', 1.99, 2, '0.0', 0, 0),
(26, 'Southbound', 'Five interlocking tales of terror follow the fates of a group of weary travellers who confront their worst nightmares - and darkest secrets - over one long night on a desolate stretch of desert highway.', 2015, 'R', 89, 'https://image.tmdb.org/t/p/original/u4jDG1ANfVoCs7GChgafqkve3v8.jpg', 17.99, 10, '0.0', 0, 0),
(27, 'Eisenstein in Guanajuato', 'Rejected by Hollywood and facing pressure to return to Stalinist Russia, filmmaker Sergei Eisenstein travels to Mexico to shoot a new film. Chaperoned by his guide Palomino, he experiences the ties between Eros and Thanatos, happy to create their effects in cinema, troubled to suffer them in life.', 2015, 'UR', 105, 'https://image.tmdb.org/t/p/original/rNbt3n790eZlhEnf47xubg5cyPw.jpg', 13.99, 55, '0.0', 0, 0),
(28, 'Tumbledown', 'A young woman struggles to move on with her life after the death of her husband, an acclaimed folk singer, when a brash New York writer forces her to confront her loss and the ambiguous circumstances of his death.', 2015, 'R', 105, 'https://image.tmdb.org/t/p/original/kIgKxxRuTqG9yHGtYYgD066otcS.jpg', 17.99, 50, '0.0', 0, 0),
(29, 'Where to Invade Next', 'To learn what the USA can learn from other nations, Michael Moore playfully "invades" them to see what they have to offer.', 2015, 'R', 120, 'https://image.tmdb.org/t/p/original/xGV76KqETGXaJA4Z2CgiLxoYBa9.jpg', 9.99, 20, '0.0', 0, 0),
(30, 'Fitoor', 'Thirteen-year-old Noor falls in love with Firdaus, daughter of Begum, the wealthiest woman in town. As their potential romance grows despite their class issues, the vengeful Begum plots retribution on their young and innocent love.', 2016, '12A', 131, 'https://image.tmdb.org/t/p/original/wkF0R7sAMIGAk2SnIr2JpErJdTd.jpg', 22.33, 12, '0.0', 0, 0),
(31, 'Of Mind and Music', 'A story about a beautiful street musician suffering from memory loss and a disheartened neuroscientist intent on helping her, bringing together the city of New Orleans and the jazz that made it famous.', 2016, 'PG-13', 98, 'https://image.tmdb.org/t/p/original/sIvnmfRgzIKYzzGBQrLdFEu56ss.jpg', 11.99, 40, '0.0', 0, 0),
(32, 'Providence', 'Rachel Cartwright and Mitchell Little both grow up in the small town of Providence, Tennessee. Their paths keep crossing and at one point it looks like they''ll finally get together. But the opportunity slips away and they go their separate ways. It takes a tragedy many years later before they''re reunited.', 2016, 'R', 79, 'https://image.tmdb.org/t/p/original/3p21no6epUIaiPkkeyKjV43cw62.jpg', 5.99, 20, '0.0', 0, 0),
(33, 'Touched With Fire', 'Two bipolar patients meet in a psychiatric hospital and begin a romance that brings out all of the beauty and horror of their condition.', 2015, 'R', 110, 'https://image.tmdb.org/t/p/original/tJ4a5D1BDmtsncio5QgqnIyOLEj.jpg', 12.99, 30, '0.0', 0, 0),
(34, 'The Hateful Eight', 'In the dead of a Wyoming winter, a bounty hunter and his prisoner find shelter in a cabin currently inhabited by a collection of nefarious characters.', 2015, 'R', 187, 'https://image.tmdb.org/t/p/original/fqe8JxDNO8B8QfOGTdjh6sPCdSC.jpg', 7.99, 12, '0.0', 0, 0),
(35, 'Anomalisa', 'A man crippled by the mundanity of his life experiences something out of the ordinary.', 2015, 'R', 90, 'https://image.tmdb.org/t/p/original/h6orfYDihFwZfneMvXyGUeTufeK.jpg', 16.99, 3, '0.0', 0, 0),
(36, 'The Forest', 'A woman goes into Japan''s Suicide Forest to find her twin sister, and confronts supernatural terror.', 2016, 'PG-13', 93, 'https://image.tmdb.org/t/p/original/ox9y8ia8lGqtkmLZD3YghA7sZkU.jpg', 4.99, 13, '0.0', 0, 0),
(37, 'The Masked Saint', 'A pastor and professional wrestler accepts a position at a struggling church where he helps the community both in his official capacity and as his alter ego The Saint.', 2016, 'PG-13', 111, 'https://image.tmdb.org/t/p/original/dLmhTgJBUZI4d9CVqpTWWxumyXO.jpg', 8.99, 30, '0.0', 0, 0),
(38, 'Wazir', 'A grief-stricken cop and amputee grandmaster are brought together by a peculiar twist of fate as part of a wider conspiracy that has darkened their lives.', 2016, 'R', 102, 'https://image.tmdb.org/t/p/original/46Z7dVLnxtXHuw4D2EFV0QcE0gw.jpg', 41.99, 14, '0.0', 0, 0),
(39, 'Anesthesia', 'Multiple lives intersect in the aftermath of the violent mugging of a Columbia University philosophy professor.', 2015, 'R', 90, 'https://image.tmdb.org/t/p/original/biEy8kqIcW7NVhqVuqsgcYxAj6G.jpg', 11.99, 30, '0.0', 0, 0),
(40, 'The Treasure', 'Costi is a family man whose cash-strapped neighbor makes him an intriguing proposition: help him find the fortune reportedly buried somewhere on the grounds of his family''s country home in Romania and split the profits.', 2015, 'R', 90, 'https://image.tmdb.org/t/p/original/vtZnFDQfNhltyMX0xrDje07BV7c.jpg', 29.99, 8, '0.0', 0, 0),
(41, 'Lamb', 'When a man meets a young girl in a parking lot he attempts to help her avoid a bleak destiny by initiating her into the beauty of the outside world. The journey shakes them in ways neither expects.', 2015, 'R', 97, 'https://image.tmdb.org/t/p/original/908k7elpDQE9C0vOUu2cU1HqplT.jpg', 23.99, 38, '0.0', 0, 0),
(42, 'Yosemite', 'It''s the fall of 1985. The intertwining tales of three 5th grade friends, Chris, Joe and Ted, unfold in the suburban paradise of Palo Alto, as the threat of a mountain lion looms over the community.', 2015, 'R', 80, 'https://image.tmdb.org/t/p/original/kAJiCCkzc9Ffa9N2FdijCZmNHsp.jpg', 11.99, 11, '0.0', 0, 0),
(43, 'London Has Fallen', 'In London for the Prime Minister''s funeral, Mike Banning discovers a plot to assassinate all the attending world leaders.', 2016, '15A', 99, 'https://image.tmdb.org/t/p/original/7NcU61uT3ukSRU39G5fIiddhHHi.jpg', 33.99, 30, '0.0', 0, 0),
(44, '13 Hours: The Secret Soldiers of Benghazi', 'As an American ambassador is killed during an attack at a U.S. compound in Libya, a security team struggles to make sense out of the chaos.', 2016, 'R', 108, 'https://image.tmdb.org/t/p/original/3Pq1SIxKgJ2kxOuhwxyf5uv7Tx9.jpg', 44.99, 40, '0.0', 0, 0),
(45, 'Norm of the North', 'When a real estate development invades his Arctic home, Norm and his three lemming friends head to New York City, where Norm becomes the mascot of the corporation in an attempt to bring it down from the inside and protect his homeland.', 2016, 'PG', 90, 'https://image.tmdb.org/t/p/original/4Gq8EEtNCJyqbwRGXzvEgA6NZ5.jpg', 66.99, 30, '0.0', 0, 0),
(46, 'Band of Robbers', 'Tom Sawyer and Huck Finn are grown men, still searching for the hidden treasure that has eluded them since childhood.', 2015, 'R', 95, 'https://image.tmdb.org/t/p/original/clwjnCgUiziWPvJFBh8eZ2VspPZ.jpg', 34.99, 19, '0.0', 0, 0),
(47, 'The Benefactor', 'A philanthropist meddles in the lives of newly-married couples in an attempt to relive his past.', 2015, 'R', 90, 'https://image.tmdb.org/t/p/original/bZZ9HqCrdsiq7jGzINdCBK12RmG.jpg', 18.99, 5, '0.0', 0, 0),
(48, 'Moonwalkers', 'After failing to locate the legendary Stanley Kubrick, an unstable CIA agent (Ron Perlman) must instead team up with a seedy rock band manager (Rupert Grint) to develop the biggest con of all time-staging the moon landing.', 2015, 'R', 107, 'https://image.tmdb.org/t/p/original/f2TmM9dp20dpJqmEFNyC8Jcl3lR.jpg', 12.99, 3, '0.0', 0, 0),
(49, 'A Perfect Day', 'A group of aid workers work to resolve a crisis in an armed conflict zone.', 2015, 'R', 106, 'https://image.tmdb.org/t/p/original/izB9659XL9DILOg6StF6mEWAGWx.jpg', 3.99, 19, '0.0', 0, 0),
(50, 'Dirty Grandpa', 'Right before his wedding, an uptight guy is tricked into driving his grandfather, a lecherous former Army Lieutenant-Colonel, to Florida for spring break.', 2016, 'R', 102, 'https://image.tmdb.org/t/p/original/zRq8AhChMrOPEm2S67gAvOCsZsa.jpg', 16.99, 39, '2.3', 3, 7),
(51, 'The 5th Wave', 'Four waves of increasingly deadly alien attacks have left most of Earth decimated. Cassie is on the run, desperately trying to save her younger brother.', 2016, 'PG-13', 112, 'https://image.tmdb.org/t/p/original/cgxEscv6TQRK6a514FwuJOjcqQ5.jpg', 5.99, 3, '0.0', 0, 0),
(52, 'Ip Man 3', 'When a band of brutal gangsters led by a crooked property developer make a play to take over the city, Master Ip is forced to take a stand.', 2015, 'PG-13', 105, 'https://image.tmdb.org/t/p/original/3WD6E1QcYQHNMPWXEvtivyQ7waQ.jpg', 17.99, 50, '0.0', 0, 0),
(53, 'Monster Hunt', 'In an ancient world where monsters rule the land while humans keep to their own kingdom, a baby monster, Wuba, is born to a human father and monster queen. When mortals and creatures alike set out to capture the newborn, Wuba''s adventure begins. The cute baby monster Huba is the child of a human man and a monster queen, threatened by both monster-hating humans and monsters attempting to capture the new-born in an ancient world based on medieval China.', 2015, 'R', 117, 'https://image.tmdb.org/t/p/original/v6fLYRKyp8YtkdtdGpPotUuE6CT.jpg', 29.99, 12, '0.0', 0, 0),
(54, 'Aferim!', 'Set in early 19th century Romania, a policeman, Costandin, is hired by a nobleman to find a Gypsy slave who has run away from his estate after having an affair with his wife.', 2016, 'R', 108, 'https://image.tmdb.org/t/p/original/9QLKf2hqhYqs9HuzCRcUXYbgM9s.jpg', 23.99, 50, '0.0', 0, 0),
(55, 'Exposed', 'A police detective investigates the truth behind his partner''s death. The mysterious case reveals disturbing police corruption and a dangerous secret involving an unlikely young woman.', 2016, 'R', 102, 'https://image.tmdb.org/t/p/original/haIA5ig4qHx9AyQ9UxQJcANIX9e.jpg', 61.99, 4, '1.0', 2, 2),
(56, 'The Finest Hours', 'The Coast Guard makes a daring rescue attempt off the coast of Cape Cod after a pair of oil tankers are destroyed during a blizzard in 1952.', 2016, 'PG-13', 117, 'https://image.tmdb.org/t/p/original/zgtwmg19E7rID76K7Bg8MRMWwG1.jpg', 40.99, 5, '0.0', 0, 0),
(57, 'Fifty Shades of Black', 'An inexperienced college student meets a wealthy businessman whose sexual practices put a strain on their relationship.', 2016, 'R', 92, 'https://image.tmdb.org/t/p/original/b3UYVW4EiSiVcg23WveE7fLl3jR.jpg', 55.99, 12, '0.0', 0, 0),
(58, 'Jane Got a Gun', 'A woman asks her ex-lover for help in order to save her outlaw husband from a gang out to kill him.', 2016, 'R', 98, 'https://image.tmdb.org/t/p/original/4BffiEm4xkrC7de5KZ51kK3uNYJ.jpg', 22.99, 50, '0.0', 0, 0),
(59, 'Saala Khadoos', 'The story of a former boxer who quits boxing following a fallout with the authorities over the underlying politics but goes on to coach a fisherwoman to fulfill his dream through her.', 2016, 'R', 150, 'http://ia.media-imdb.com/images/M/MV5BM2Q5YmM3YjMtOTE2Mi00MDg5LThkZjctYzUxOWU2ZTM1ZDk4XkEyXkFqcGdeQXVyMTExNDQ2MTI@.jpg', 29.99, 56, '0.0', 0, 0),
(60, 'Krampus', 'A boy who has a bad Christmas ends up accidentally summoning a Christmas demon to his family home.', 2015, 'PG-13', 98, 'https://image.tmdb.org/t/p/original/27SiV4EBHcTBMz4IKANjhX5iIoX.jpg', 19.99, 7, '0.0', 0, 0),
(61, 'Chi-Raq', 'A modern day adaptation of the ancient Greek play Lysistrata by Aristophanes, set against the backdrop of gang violence in Chicago.', 2015, 'R', 127, 'https://image.tmdb.org/t/p/original/qiHhMWmK7V1h7U36M7aaFP2UoDS.jpg', 26.99, 9, '0.0', 0, 0),
(62, 'The Letters', 'A drama that explores the life of Mother Teresa through letters she wrote to her longtime friend and spiritual advisor, Father Celeste van Exem over a nearly 50-year period.', 2014, 'PG', 114, 'https://image.tmdb.org/t/p/original/4JnVKezbOkforfzyrRXc6JY6p1U.jpg', 10.99, 6, '0.0', 0, 0),
(63, 'Macbeth', 'Macbeth, the Thane of Glamis, receives a prophecy from a trio of witches that one day he will become King of Scotland. Consumed by ambition and spurred to action by his wife, Macbeth murders his king and takes the throne for himself.', 2015, 'R', 113, 'https://image.tmdb.org/t/p/original/417tcPgyz8oatgp1WE0SZZ4Odgl.jpg', 12.99, 5, '0.0', 0, 0),
(64, 'Youth', 'A retired orchestra conductor is on holiday with his daughter and his film director best friend in the Alps when he receives an invitation from Queen Elizabeth II to perform for Prince Philip''s birthday.', 2015, 'R', 124, 'https://image.tmdb.org/t/p/original/9vFNTKVhFDAMrAGOVmPBMT9XDee.jpg', 11.99, 8, '0.0', 0, 0),
(65, 'A Royal Night Out', 'On V.E. Day in 1945, as peace extends across Europe, Princesses Elizabeth and Margaret are allowed out to join the celebrations. It is a night full of excitement, danger and the first flutters of romance.', 2015, 'PG-13', 97, 'https://image.tmdb.org/t/p/original/q5GC6rZtr6y8GsV8IqHZ8FUHKgP.jpg', 11.99, 11, '0.0', 0, 0),
(66, 'The World of Kanako', 'As former detective Akikazu searches for his missing daughter, Kanako, he soon learns she has a mysterious secret life.', 2014, 'R', 118, 'https://image.tmdb.org/t/p/original/q6P1cHKNR9FrQr5ZJgZDej1up6T.jpg', 15.99, 23, '0.0', 0, 0),
(67, 'Life', 'A photographer for Life Magazine is assigned to shoot pictures of James Dean.', 2015, 'R', 111, 'https://image.tmdb.org/t/p/original/t3ijJkodD7fzgbnaEFLQ43ebDDT.jpg', 5.99, 2, '0.0', 0, 0),
(68, 'Hitchcock/Truffaut', 'Filmmakers discuss how Francois Truffaut''s 1966 book "Cinema According to Hitchcock" influenced their work.', 2015, 'PG-13', 79, 'https://image.tmdb.org/t/p/original/bJ6AcDLnagKDH6FTgMr5vZFS9lx.jpg', 11.99, 23, '0.0', 0, 0),
(69, 'Christmas Eve', 'Hilarity, romance, and transcendence prevail after a power outage traps six different groups of New Yorkers inside elevators on Christmas Eve.', 2015, 'PG', 95, 'https://image.tmdb.org/t/p/original/5tXhNyPOurxQ0Bo7jbz5TF2ooqG.jpg', 17.99, 33, '0.0', 0, 0),
(70, 'MI-5', 'When a terrorist escapes custody during a routine handover, Will Holloway must team with disgraced MI5 Intelligence Chief Harry Pearce to track him down before an imminent terrorist attack on London.', 2015, 'R', 104, 'https://image.tmdb.org/t/p/original/mKVpDAYAsepkLAklF8endJh9cDr.jpg', 13.99, 22, '0.0', 0, 0),
(71, 'In the Heart of the Sea', 'A recounting of a New England whaling ship''s sinking by a giant whale in 1820, an experience that later inspired the great novel Moby-Dick.', 2015, 'PG-13', 122, 'https://image.tmdb.org/t/p/original/uekIGnzTACciWrfR7jOuof7xHgR.jpg', 10.99, 22, '0.0', 0, 0),
(72, 'Legend', 'The film tells the story of the identical twin gangsters Reggie and Ronnie Kray, two of the most notorious criminals in British history, and their organised crime empire in the East End of London during the 1960s.', 2015, 'R', 132, 'https://image.tmdb.org/t/p/original/nXXI1MCJ0ugMetJ5Qn7ofDXANxf.jpg', 9.99, 12, '0.0', 0, 0),
(73, 'The Big Short', 'Four denizens in the world of high-finance predict the credit and housing bubble collapse of the mid-2000s, and decide to take on the big banks for their greed and lack of foresight.', 2015, 'R', 130, 'https://image.tmdb.org/t/p/original/p11Ftd4VposrAzthkhF53ifYZRl.jpg', 7.99, 6, '0.0', 0, 0),
(74, 'Zootopia', 'In a city of anthropomorphic animals, a fugitive con artist fox and a rookie bunny cop must work together to uncover a conspiracy.', 2016, 'PG', 108, 'https://image.tmdb.org/t/p/original/fOLtpih2cxw2hFlJZMohfsYnLZ9.jpg', 7.99, 4, '5.0', 3, 15),
(75, 'Whiskey Tango Foxtrot', 'A journalist recounts her wartime coverage in Afghanistan and Pakistan.', 2016, 'R', 108, 'https://image.tmdb.org/t/p/original/22G22rdZsMDO7khe4Ue3G2uwOaw.jpg', 20.99, 6, '0.0', 0, 0),
(76, 'Desierto', 'A group of people trying to cross the border from Mexico into the United States encounter a man who has taken border patrol duties into his own racist hands.', 2016, 'R', 94, 'https://image.tmdb.org/t/p/original/p6pxJbZnriwesRGqM2LOE1gFPAQ.jpg', 22.99, 22, '0.0', 0, 0),
(77, 'The Other Side of the Door', 'A family lives an idyllic existence abroad until a tragic accident takes the life of their young son. The inconsolable mother learns of an ancient ritual that will bring him back to say a final goodbye. She travels to an ancient temple, where a door serves as a mysterious portal between two worlds. But when she disobeys a sacred warning to never open that door, she upsets the balance between life and death.', 2016, 'R', 96, 'https://image.tmdb.org/t/p/original/zHHLU75HVOKs6TIvwazcSnPEoli.jpg', 9.99, 2, '0.0', 0, 0),
(78, 'Knight of Cups', 'Once there was a young prince whose father, the king of the East, sent him down into Egypt to find a pearl. But when the prince arrived, the people poured him a cup. Drinking it, he forgot he was the son of a king, forgot about the pearl and fell into a deep sleep.', 2016, 'R', 118, 'https://image.tmdb.org/t/p/original/kCiR0IkR3s4doOoy7GKJTkCnaOV.jpg', 61.99, 50, '0.0', 0, 0),
(79, 'The Wave', 'Even though awaited, no-one is really ready when the mountain pass of Åkneset above the scenic narrow Norwegian fjord Geiranger falls out and creates a 85 meter high violent tsunami. A geologist is one of those caught in the middle of it.', 2016, 'R', 104, 'https://image.tmdb.org/t/p/original/AnPHblkG8rcFeQVDa0BZXfNrhGs.jpg', 18.99, 25, '0.0', 0, 0),
(80, '10 Cloverfield Lane', 'Waking up from a car accident, a young woman finds herself in the basement of a man who says he''s saved her life from a chemical attack that has left the outside uninhabitable.', 2016, 'R', 105, 'https://image.tmdb.org/t/p/original/xian3gx1lzU8phrstN6UqO5gfDC.jpg', 13.99, 5, '0.0', 0, 0),
(81, 'The Dark Tower', 'Gunslinger Roland Deschain roams an Old West-like landscape in search of the dark tower, in the hopes that reaching it will preserve his dying world.', 2017, 'R', 99, 'https://image.tmdb.org/t/p/original/s4SAwfTpY735GWCiBSUgfSLlJqn.jpg', 81.99, 50, '0.0', 0, 0),
(82, 'Rogue One: A Star Wars Story', 'Rebels set out on a mission to steal the plans for the Death Star.', 2017, 'PG-13', 128, 'https://image.tmdb.org/t/p/original/oyxkHIaQgjP1EEDvtNeF1jkKo2Z.jpg', 41.99, 20, '0.0', 0, 0),
(83, 'Sing', 'A koala named Buster recruits his best friend to help him drum up business for his theater by hosting a singing competition.', 2017, 'R', 99, 'https://image.tmdb.org/t/p/original/s9ye87pvq2IaDvjv9x4IOXVjvA7.jpg', 21.99, 50, '0.0', 0, 0),
(84, 'Assassin''s Creed', 'When Callum Lynch (Michael Fassbender) explores the memories of his ancestor Aguilar and gains the skills of a Master Assassin, he discovers he is a descendant of the secret Assassins society.', 2017, 'R', 95, 'https://image.tmdb.org/t/p/original/hVhAUp56KKT1zGkoVrCz4xRc2st.jpg', 31.99, 15, '0.0', 0, 0),
(85, 'Miss Peregrine''s Home for Peculiar Children', 'Teenager Jacob follows clues that take him to a mysterious island, where he discovers the crumbling ruins of Miss Peregrine''s School for Peculiar Children. As Jacob explores the abandoned bedrooms and hallways, he discovers that its former occupants were far more than peculiar; they possessed incredible powers. And they may still be alive.', 2017, '15A', 105, 'https://image.tmdb.org/t/p/original/qe4dxCKrvC5COGpPnWOzXjzOCO6.jpg', 28.99, 12, '0.0', 0, 0),
(86, 'Doctor Strange', 'After his career is destroyed, a brilliant but arrogant and conceited surgeon gets a new lease on life when a sorcerer takes him under his wing and trains him to defend the world against evil.', 2017, 'R', 107, 'https://image.tmdb.org/t/p/original/1gAX6lJOF0fywb42utSvv6znjI1.jpg', 29.99, 38, '0.0', 0, 0),
(87, 'Trolls', 'Lovable and friendly, the trolls love to play around. But one day, a mysterious giant shows up to end the party.', 2017, 'PG-13', 97, 'https://image.tmdb.org/t/p/original/8xZNnhEXWe3CSo3iyhTqBMdjCE2.jpg', 41.99, 29, '0.0', 0, 0),
(88, 'Fantastic Beasts and Where to Find Them', 'The adventures of writer Newt Scamander in New York''s secret community of witches and wizards seventy years before Harry Potter reads his book in school.', 2017, 'R', 112, 'https://image.tmdb.org/t/p/original/2HBAFGnmstE42cQm884lRdoK4UW.jpg', 11.99, 8, '0.0', 0, 0),
(89, 'Moana', 'A young woman uses her navigational talents to set sail for a fabled island. Joining her on the adventure is her hero, the legendary demi-god Maui.', 2017, 'PG-13', 105, 'https://image.tmdb.org/t/p/original/fass9TJ2a06LYakYbiI11AeSdvQ.jpg', 36.99, 46, '0.0', 0, 0),
(90, 'La La Land', 'A jazz pianist falls for an aspiring actress in Los Angeles.', 2017, 'PG', 103, 'https://image.tmdb.org/t/p/original/mP53GTnwXPImogdXh4o522JsZPS.jpg', 32.99, 21, '0.0', 0, 0),
(91, 'The Accountant', 'A forensic accountant un-cooks the books for illicit clients', 2017, 'R', 108, 'https://image.tmdb.org/t/p/original/uZY4CJ2ytIzRbciVwVH2rZ0PWZL.jpg', 18.99, 2, '0.0', 0, 0),
(92, 'Inferno', 'fter waking up in a hospital room in Florence, Italy, with no memory of what has occurred for the last few days, Robert Langdon suddenly finds himself the target of a manhunt. With the help of Dr. Sienna Brooks and his knowledge of symbology, Langdon will try to escape whilst solving the most intricate riddle he''s ever faced.', 2017, 'R', 115, 'https://image.tmdb.org/t/p/original/j7npq2A30bKKwLZmlTn4O4C5Xgt.jpg', 39.99, 26, '0.0', 0, 0),
(93, 'A Monster Calls', 'A boy seeks the help of a tree monster to cope with his single mom''s terminal illness.', 2017, 'R', 98, 'https://image.tmdb.org/t/p/original/qylkqtaLlahR0xljCTS1Efne6Qm.jpg', 30.99, 15, '0.0', 0, 0),
(94, 'Storks', 'Storks deliver babies...or at least they used to. Now they deliver packages for global internet giant Cornerstore.com. Junior, the company''s top delivery stork, is about to be promoted when he accidentally activates the Baby Making Machine, producing an adorable and wholly unauthorized baby girl. Desperate to deliver this bundle of trouble before the boss gets wise, Junior and his friend Tulip, the only human on Stork Mountain, race to make their first-ever baby drop - in a wild and revealing journey that could make more than one family whole and restore the storks'' true mission in the world.', 2017, 'PG-13', 102, 'https://image.tmdb.org/t/p/original/5qVD5TD1CiALR5vUsMzh2BschVU.jpg', 51.99, 3, '0.0', 0, 0),
(95, 'Suicide Squad', 'A secret government agency recruits imprisoned supervillains to execute dangerous black ops missions in exchange for clemency.', 2017, 'PG', 97, 'https://image.tmdb.org/t/p/original/5EqplJN57aGrxXfda5ezihJtZ1s.jpg', 45.99, 3, '0.0', 0, 0),
(96, 'Pete''s Dragon', 'The adventure of an orphaned boy named Pete and his best friend Elliott, who just so happens to be a dragon.', 2017, 'PG', 95, 'https://image.tmdb.org/t/p/original/1deTZAM1a2S4PJB3j3cfJ7L6ZnR.jpg', 34.99, 4, '0.0', 0, 0),
(97, 'Kubo and the Two Strings', 'Kubo lives a quiet, normal life in a small shoreside village until a spirit from the past turns his life upside down by re-igniting an age-old vendetta. This causes all sorts of havoc as gods and monsters chase Kubo who, in order to survive, must locate a magical suit of armor once worn by his late father, a legendary Samurai warrior.', 2017, 'PG', 103, 'https://image.tmdb.org/t/p/original/3Kr9CIIMcXTPlm6cdZ9y3QTe4Y7.jpg', 50.99, 5, '0.0', 0, 0),
(98, 'Mechanic: Resurrection', 'Arthur Bishop thought he had put his murderous past behind him when his most formidable foe kidnaps the love of his life. Now he is forced to travel the globe to complete three impossible assassinations, and do what he does best, make them look like accidents.', 2017, 'R', 108, 'https://image.tmdb.org/t/p/original/g2BiFeMGWtJUbT1fSLEaqokXbEl.jpg', 45.99, 9, '0.0', 0, 0),
(99, 'title', 'description', 2016, 'ageRating', 60, 'imgUrl', 20.00, 3, '0.0', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `movie_in_genre`
--

CREATE TABLE `movie_in_genre` (
  `movie_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_in_genre`
--

INSERT INTO `movie_in_genre` (`movie_id`, `genre_id`) VALUES
(1, 1),
(1, 14),
(1, 19),
(2, 1),
(2, 3),
(2, 11),
(3, 9),
(3, 12),
(3, 14),
(4, 1),
(4, 11),
(4, 14),
(4, 18),
(5, 6),
(5, 18),
(6, 14),
(6, 18),
(7, 3),
(8, 12),
(8, 14),
(9, 3),
(9, 4),
(10, 1),
(10, 2),
(10, 3),
(10, 5),
(10, 11),
(11, 3),
(11, 8),
(12, 3),
(13, 11),
(13, 14),
(13, 20),
(13, 21),
(14, 3),
(14, 18),
(15, 1),
(15, 11),
(15, 15),
(15, 19),
(16, 8),
(16, 14),
(17, 1),
(17, 3),
(18, 6),
(18, 20),
(19, 11),
(19, 15),
(20, 3),
(20, 9),
(20, 12),
(20, 14),
(20, 16),
(21, 13),
(21, 20),
(22, 1),
(22, 11),
(22, 14),
(22, 15),
(23, 3),
(24, 4),
(24, 11),
(24, 12),
(24, 16),
(25, 13),
(25, 14),
(25, 18),
(25, 20),
(26, 6),
(26, 19),
(26, 20),
(27, 3),
(27, 8),
(27, 12),
(28, 3),
(28, 8),
(28, 17),
(29, 4),
(30, 8),
(30, 14),
(31, 7),
(31, 14),
(32, 8),
(32, 14),
(33, 8),
(33, 14),
(34, 13),
(34, 14),
(34, 18),
(34, 20),
(34, 21),
(35, 2),
(35, 3),
(35, 8),
(35, 14),
(36, 6),
(37, 1),
(37, 5),
(37, 12),
(37, 13),
(37, 14),
(37, 15),
(38, 13),
(38, 14),
(38, 18),
(38, 20),
(39, 14),
(39, 20),
(40, 3),
(41, 14),
(42, 14),
(43, 1),
(43, 3),
(44, 1),
(44, 10),
(44, 14),
(44, 20),
(45, 2),
(45, 3),
(45, 5),
(45, 11),
(46, 3),
(46, 11),
(46, 13),
(46, 20),
(47, 3),
(48, 14),
(49, 3),
(50, 1),
(50, 11),
(50, 19),
(50, 20),
(51, 1),
(51, 12),
(51, 14),
(51, 16),
(52, 1),
(52, 3),
(52, 15),
(53, 3),
(53, 11),
(53, 14),
(53, 16),
(53, 21),
(54, 14),
(55, 1),
(55, 14),
(55, 16),
(55, 20),
(56, 3),
(57, 1),
(57, 14),
(57, 21),
(58, 9),
(58, 14),
(59, 3),
(59, 6),
(59, 15),
(60, 3),
(60, 7),
(60, 13),
(60, 14),
(61, 14),
(62, 10),
(62, 14),
(63, 3),
(63, 14),
(64, 3),
(64, 8),
(64, 14),
(65, 13),
(65, 14),
(65, 18),
(66, 12),
(66, 14),
(67, 4),
(68, 3),
(69, 1),
(69, 14),
(69, 20),
(70, 1),
(70, 11),
(70, 12),
(70, 14),
(70, 16),
(70, 20),
(71, 8),
(71, 12),
(71, 13),
(71, 14),
(71, 20),
(72, 1),
(72, 12),
(72, 14),
(73, 12),
(73, 14),
(74, 1),
(74, 2),
(74, 3),
(74, 5),
(74, 11),
(75, 3),
(75, 10),
(76, 1),
(76, 14),
(76, 20),
(77, 6),
(78, 8),
(78, 14),
(79, 1),
(79, 14),
(79, 20),
(80, 14),
(80, 18),
(80, 19),
(80, 20),
(81, 6),
(81, 15),
(81, 19),
(81, 21),
(82, 1),
(82, 11),
(82, 15),
(82, 19),
(83, 2),
(83, 3),
(83, 7),
(84, 1),
(84, 11),
(84, 16),
(84, 19),
(85, 11),
(85, 15),
(86, 1),
(86, 11),
(86, 15),
(87, 2),
(87, 5),
(87, 7),
(87, 11),
(87, 15),
(88, 5),
(88, 11),
(88, 15),
(89, 2),
(89, 3),
(89, 5),
(89, 7),
(89, 11),
(89, 15),
(90, 12),
(90, 14),
(91, 14),
(92, 18),
(92, 20),
(93, 14),
(93, 15),
(94, 2),
(94, 3),
(94, 5),
(95, 1),
(95, 11),
(95, 15),
(95, 19),
(95, 20),
(96, 5),
(96, 11),
(97, 2),
(97, 5),
(97, 11),
(97, 15),
(98, 1),
(98, 13),
(98, 20);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total_order_price` double(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `order_id` int(11) NOT NULL DEFAULT '0',
  `movie_id` int(11) NOT NULL DEFAULT '0',
  `quantity` int(4) NOT NULL DEFAULT '0',
  `price` double(5,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `order_details`
--
DELIMITER $$
CREATE TRIGGER `updateQuantityOFMovies` BEFORE INSERT ON `order_details` FOR EACH ROW UPDATE movies 

SET quantity_in_stock = quantity_in_stock - NEW.quantity
	

WHERE movie_id = NEW.movie_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `review_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `review_text` varchar(1000) NOT NULL,
  `review_rating` double(2,1) NOT NULL,
  `reviews_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`review_id`, `movie_id`, `user_id`, `review_text`, `review_rating`, `reviews_date`) VALUES
(77, 2, 5, 'Firstly I would like to state that it is completely hilarious reading reviews with One Star because the movie had "Foul Language" and "Sex Scenes" or that someone had no idea that the movie was inappropriate for their 9 year old kid.', 5.0, '2016-03-08 14:04:11'),
(78, 2, 6, 'New respect for Ryan Reynolds after seeing this film.\r\n\r\nHe really pulls of the character and takes you on the ride with him.\r\n\r\nThe writers make it of course, which is what you would expect from a Marvel film.', 4.0, '2016-03-08 14:05:32'),
(79, 2, 8, 'well, the best part of the movie is its humor. Deal pool rocks with his sense of humor and every time when he cracks a joke you can''t stop yourself from laughing. The action is also quite well and literally kick ass. It has a love story in it.', 5.0, '2016-03-08 14:06:41'),
(80, 2, 9, 'Only reason I am not giving 1 star is, because it is just a bit above awful. Seriously, people who hyper liked this, should have to take an IQ test immediately. Please guys, write some genuine reviews for the sake of not so witty people like me.', 2.0, '2016-03-08 14:08:45'),
(81, 2, 10, 'Who needs a joke when you have references? Seriously that''s what this movie is packed full of. It''s like the writers only understood that Deadpool makes a lot of jokes and breaks the fourth wall, they didn''t put any effort into making those jokes work at all. This film''s goal is to make you laugh but it''s rated R with half the jokes being what you would hear on a middle school campus and the other half being references. There a few clever jokes but that''s pretty much it.', 1.0, '2016-03-08 14:12:10'),
(82, 2, 11, 'Deadpool (2016) omg!!!!! I''ve heard nothing but great things about this movie, and typically when I''m told that something is so awesome it usually means it is horrible; not this movie?. Deadpool (2016) is within the first 10 minutes of the movie the best Marvel film released to date! The action is nonstop, the comedy is off the chain; the entire theater was bursting into tears with laughter the entire film.', 5.0, '2016-03-08 14:16:53'),
(83, 2, 23, 'OK so I have seen Deadpool about 3-4 weeks ago now. This review is really to congratulate the people who made the film and the risk they took. They all did an excellent job with this movie and the movie was great. Firstly, the rated r. R rated movie are usually good e.g. Shawshank Redemption (1994), Pulp Fiction (1994), The Godfather (1972), Apocalypse Now (1979) and most recently Mad Max Fury Road (2015). But no r rated comic book was really consider great there were some good ones e.g. Blade (1998), Watchmen (2009). ', 4.0, '2016-03-08 14:18:37'),
(84, 2, 30, 'Deadpool is back after that crappy X-Men Origins: Wolverine film where they shut him up. This movie blew me away! Thankfully this film is here and it was awesome. Ryan Reynolds nailed the character of Wade Wilson so well and was hysterical throughout. The opening credits were so unexpected but hilarious. This movie recognizes its place in today''s film industry and plays along with it throughout with so many references to modern culture. The thing I really liked about Deadpool was how original its film making ideas were and how it was just such a totally different comic book movie in general.', 5.0, '2016-03-08 14:20:27'),
(85, 2, 13, 'Deadpool delivers action, comedy and a fun, entertaining twist on everything an anti-hero movie should be! Based on the famous comic book published by Marvel Comics, this film tells the story of Wade Wilson, played by Ryan Reynolds, as a former Special Forces operative turned mercenary.', 4.0, '2016-03-08 14:22:45'),
(86, 1, 34, 'What seems to be a trend in big budget SF films occurred, once again, in Elysium. I''m continually impressed with what is being created (visual effects wise)today, but remain disappointed when it comes to the associated screenplays/plot lines. At the end of this film, I had the same, perpetual feeling that no one out there making SF films gets the message: without a good story, you don''t have a really good film. Elysium hearkens me back to Prometheus, directed by Ridley Scott. ', 3.0, '2016-03-08 14:26:46'),
(87, 3, 12, 'Credit Race for showcasing its hero’s human flaws, but the movie unfortunately lets him get away with them a little too easily (his grand makeup gesture to Ruth comes off more creepy than romantic).', 4.0, '2016-04-27 11:55:49'),
(89, 3, 17, 'Owens’ triumph is long overdue for big-screen treatment, and director Stephen Hopkins delivers stirring moments amid the tension-free stretches, particularly once the action moves to Berlin.', 5.0, '2016-04-27 11:56:54'),
(90, 8, 47, 'An imperfect but nevertheless engaging reiteration of the Pan Am Flight 73 hijacking, Neerja respects its titular character and the victims in a tense, harrowing film.', 1.0, '2016-04-27 11:58:10'),
(91, 50, 20, 'There’s a certain perverse brilliance, however accidental, to a movie that creates a longing for a foulmouthed Aubrey Plaza/Robert De Niro romcom.', 3.0, '2016-04-27 11:59:25'),
(92, 50, 19, 'The screenplay, by John M. Phillips, is the written equivalent of a toddler discovering curse words. Yet some riffs draw chuckles.', 2.0, '2016-04-27 11:59:53'),
(93, 50, 18, 'As in Judd Apatow''s films, crassness is boasted as shamelessness, and calculated sentimentality is dressed up as empathy.', 2.0, '2016-04-27 12:00:23'),
(94, 74, 29, 'The year’s best film so far.', 5.0, '2016-04-27 12:01:37'),
(95, 74, 44, 'I would have never would have guess this movie would but so good. I think this is an amazing film.', 5.0, '2016-04-27 12:02:06'),
(96, 74, 12, 'Like Disney’s "Big Hero 6," the movie is busy, but not breathless with invention.', 5.0, '2016-04-27 12:02:27'),
(97, 55, 8, 'In certain mutilated pictures, you can detect the lineaments of greatness: Consider Orson Welles’s “The Magnificent Ambersons.” Here, that’s not the case.', 1.0, '2016-04-27 12:03:39'),
(98, 55, 13, 'Skip it. \r\n\r\nThis has all the makings of a fascinating urban fantasy but a really bad edit makes it a fascinating mess. ', 1.0, '2016-04-27 12:04:53'),
(99, 4, 1, 'hello test', 3.0, '2016-04-27 21:53:39');

--
-- Triggers `reviews`
--
DELIMITER $$
CREATE TRIGGER `num_ratings_update` BEFORE INSERT ON `reviews` FOR EACH ROW UPDATE movies 

SET num_ratings = num_ratings +1,
	rating_total = rating_total + NEW.review_rating,
	avg_rating = rating_total  / num_ratings

WHERE movie_id = NEW.movie_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `DOB` date DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `password` binary(60) NOT NULL,
  `email` varchar(50) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `firstName`, `lastName`, `DOB`, `phone`, `password`, `email`, `admin`) VALUES
(1, 'Lorenas', 'Orleckas', '1984-11-11', '353862611656', 0x2432612431302448494d7a357450654e464f706e4a6a6732346d423665567a59676842537a6133466b775a4e68507133425548355261595042385169, 'lorenas@gmail.com', 1),
(4, 'Andrius', 'Geciauskas', '1977-03-08', '353852551466', 0x24326124313024306375743341484e3264395364665730574166386c6567636b7370622f47396c4b364f6244747162642e65595141766f4744304e61, 'andrius@gmail.com', 1),
(5, 'Vitalijus', 'Nikovas', '1966-03-05', '353872612676', 0x243261243130247a4d5735333558587564794331767a6f7a397a5a5a4f6e4d7376313778484c572f62632f794b39486f534262627568313653374a71, 'vitalijus@gmail.com', 1),
(6, 'John', 'Delainey', '1984-06-17', '353862411561', 0x243261243130244f2e6a324c3772794f68444176746f52677a5043574f55623568686c6477312e2e6335544d3372364c2f564c734d7a38517643344f, 'John@gmail.com', 0),
(7, 'Connor', 'McGregor', '1980-09-18', '353864611614', 0x2432612431302468322f7a624a776b33517069335a4f7078647169372e5168366479535877735a476e473032524b4e774b593961456d643869585875, 'Connor@gmail.com', 0),
(8, 'Arnold', 'Swarzneger', '1979-11-22', '353863312678', 0x2432612431302443725559526f61337276625249586a6d5a61727a6f2e6c7a4261666e646f525372467239415a4532596646696744303177374d5436, 'arnold@gmail.com', 0),
(9, 'Kim', 'Kardashian', '1973-11-23', '353872617676', 0x243261243130244745643366467a6a6b692e4552445544696f5a78796550393163386c424e47525350332f572e7466384d6b306a666f77773644372e, 'kim@gmail.com', 0),
(10, 'Alex', 'Brewer', '1974-03-20', '353862611111', 0x243261243130244258766733466a337138465074306d46326f63416b4f70732e6b304b5a3831686a51352e575831444c417535767a4134633234302e, 'Alex@gmail.com', 0),
(11, 'Benjamin', 'Brewer', '1986-12-09', '353872689654', 0x24326124313024622e6136584c705349614536625144744b723530552e37487254586d3157494c616544447a6b2e4541743832533247544e486b5443, 'Benjamin@gmail.com', 0),
(12, 'Adam', 'Hirsch', '1977-03-20', '353852615655', 0x243261243130246b5931377a36326d3270774b75315167767150662f656177493478686d6c516e5853563579354442436d47504d5a58546b697a4943, 'Adam@gmail.com', 0),
(13, 'Nicolas', 'Cage', '1985-11-13', '353852691639', 0x2432612431302432634b30335875612f4a353538627076656a4d43782e344e4b425075527447533348724d4e4232733539424e343354353369573775, 'Nicolas@gmail.com', 0),
(14, 'Elijah', 'Wood', '1964-03-13', '353842611622', 0x24326124313024542e62576f4c6e6947572e6f767a365a57674357582e49456e6850532f57316b5146616d2e32417033695448507469754239383771, 'Elijah@gmail.com', 0),
(15, 'Jerry', 'Lewis', '1966-03-16', '353862411333', 0x243261243130243154714b2e44447a6e7371526f7452775630513262657a317046312f414f3753594c6d523553426e363473377559642f3050727632, 'Jerry@gmail.com', 0),
(16, 'Ilya', 'Naishuller', '1971-01-06', '353872611767', 0x24326124313024694c6648584d36306c43656e564e71767a6268434e75574a33506c777938342e35766474305563757043514f6a5a744539304a6a75, 'Ilya@gmail.com', 0),
(17, 'Haley', 'Bennett', '1981-02-12', '353862681868', 0x243261243130245048794e6e6a637258457a336b443865586d37484c75422e4b7a7a72506b466d517a706a543230624c4b332e5a4939506a526a2f57, 'Haley@gmail.com', 0),
(18, 'Tim', 'Roth', '1980-02-06', '353822621262', 0x243261243130244f37736837644f326a78576d663043504b44306b4f4f42754b703453764d634a44324d524d72564c30412f624c38356b374355682e, 'Tim@gmail.com', 0),
(19, 'Sharlto', 'Copley', '1970-08-14', '353842611464', 0x243261243130245a374f53314d7368316c683549647046466242585065624f335a684e5a3961553834314871364f3675707566522e6e4c636a6e3861, 'Sharlto@gmail.com', 0),
(20, 'Will', 'Stewart', '1972-09-20', '353862617667', 0x24326124313024797261667369693957754f686876595252734f4771756377506b5177667a6465475336527161574f312e4e4b486c44394f416c4136, 'Will@gmail.com', 0),
(21, 'Mark', 'Strong', '1972-10-18', '353892615699', 0x243261243130247a69304350713936507a624c38666664585a4c697165437353497a64622f537750595941653161575646454c38344f4a3838677371, 'Mark@gmail.com', 0),
(22, 'Chloe', 'Grace', '1977-01-11', '353852611555', 0x24326124313024456556726b7659626934706b326246626d584f412e4f70434b734775625732364f76586d4f6e5257373347346d7039353047753469, 'Chloe@gmail.com', 0),
(23, 'Frank', 'DAmico', '1979-03-01', '353852611121', 0x24326124313024547a477766616c646b7a41306b75335a413353516f653049705171666c7932764a6d3478666e5a6b55776e56595a7a675038346f6d, 'Frank@gmail.com', 0),
(24, 'Milana', 'Vayntrub', '1974-03-01', '353863611633', 0x24326124313024343544692e736868417754726f734677754479316e6574353037796746614170477161372e466f38476d36524963323065644a3065, 'Milana@gmail.com', 0),
(25, 'Freddie', 'Prinze', '1985-03-01', '353862618686', 0x2432612431302462773941574e57677537537841695564334877304775455a32556c304f693576584e595453446f566451625067716a483570665065, 'Freddie@gmail.com', 0),
(26, 'Aidan', 'Quinn', '1989-03-01', '353862611633', 0x24326124313024472f68384578714949566954414d444f6731706b646578522e794d373552615a58344f3956726231346b53476d595a5a6f6c4b4279, 'Aidan@gmail.com', 0),
(27, 'Boris', 'Kodjoe', '1990-03-01', '353862614644', 0x243261243130246b783575674b733666386b62494a3953577073507a4f2f44595145766d7a55364673774b666970574b415244703359626d485a414f, 'Boris@gmail.com', 0),
(28, 'James', 'Van Der', '1987-03-01', '+353862611666', 0x243261243130244d764c6d507a68745a66684a734d53423331584f737574734b613467706b6d755267643677596d4572482e76415a65647136426f43, 'James@gmail.com', 0),
(29, 'Jed', 'Rees', '1984-03-01', '+353862611666', 0x2432612431302466646834396e4773576a3470326855326b747654366556596d31546f71437065516b4a46564d45533131642f42336f7a5230656f75, 'Jed@gmail.com', 0),
(30, 'Devon', 'Werkheiser', '1983-03-01', '+353862611666', 0x24326124313024315168764f64386d4a4f6e79572e44353053324f787534334f4e52494f48617466744d7734325a2f6d465563625a7436414f466643, 'Devon@gmaill.com', 0),
(31, 'Cyd', 'Charisse', '1986-03-01', '+353862611666', 0x243261243130244336566b4f67707159587361797967412f4d7961462e5a334266664346723079567674464d49547257673830504338615a6c702f4b, 'Cyd@gmail.com', 0),
(32, 'Matt', 'Nable', '1990-03-01', '+353862611666', 0x24326124313024704735365453696973386b65366975514465794975756430687874715456566e4234594d6f4c6867646d487773426f6573386d6f2e, 'Matt@gmail.com', 0),
(33, 'Nick', 'Zano', '1995-03-01', '+353862611666', 0x243261243130244f714c4a4a53563243525041706779624b5459747a6544775a2e6d42693632725854577749774f67496d644537334b396d3463532e, 'Nick@gmail.com', 0),
(34, 'Nora', 'Noone', '1976-03-01', '+353862611666', 0x243261243130244f7a7767653749334c57384b70533877674c754e49652e5347732e6e6f706544304854742f6b7a4d6143475365395a4d3843586d69, 'Nora@gmail.com', 0),
(35, 'Andrea', 'Parler', '1950-03-01', '+353862611666', 0x243261243130245034374b576b3974442f71477a51762f3069386d6c65636d3470664c5342474d524161645639424a5a4772587558725161524a6265, 'Andrea@gmail.com', 0),
(36, 'Alison', 'Becker', '1992-03-01', '+353862611666', 0x243261243130244d37386c593661426b2f4a6b3171686448575557302e542e354e4b36506d4d39323139795677305167673565374c71784952763647, 'Alison@gmail.com', 0),
(37, 'Camryn', 'Manheim', '1988-03-01', '+353862611666', 0x243261243130244c2f5533513467695544574977394e6b4253324d32656356517a57454c6c6f41326a6147585050527a4662484a304944705579346d, 'Camryn@gmail.com', 0),
(38, 'Lesley', 'Arfin', '1985-03-01', '+353862611666', 0x243261243130246130717444456f627568592e514e724e5161784f49656f556d2e4536395033745a57695677766f6b4c685a42322e747a374e304753, 'Lesley@gmail.com', 0),
(39, 'Alan', 'Hale', '1980-03-01', '+353862611666', 0x243261243130246e55716865765337637373664535704b51574265422e3841325268465769317a745a74344c2e713463594864354e72536c2f646d57, 'Alan@gmail.com', 0),
(40, 'Susan', 'Clark', '1982-03-01', '+353862611666', 0x243261243130246a33664b646d564631735a557753334b37596d79424f7166324f72483142562e337a7a384d6d33776d2e43484966385633762e546d, 'Susan@gmail.com', 0),
(41, 'Katherine', 'Darchenber', '2016-03-01', '+353862611666', 0x243261243130247a536c347134584d6c73386730674c446e706946674f315643374750467a4b52525244364f4f737676384747476c51484f73707932, 'Katherine@gmail.com', 0),
(42, 'Claire', 'Trevor', '1983-03-01', '+353862611666', 0x243261243130243374434c567a3256556a68584a5a65537871434e327544385570395366622e544b566a364668416778474b6f697654553878654d69, 'Claire@gmail.com', 0),
(43, 'Lynn', 'Redgrave', '1990-03-01', '+353862611666', 0x2432612431302461496c7762316e7545455a4974436d76394b74386e2e4d6645745769636c2e5673716173624f4c762f415073714f4556794b377736, 'Lynn@gmail.com', 0),
(44, 'Charlie', 'Ray', '1985-03-01', '+353862611666', 0x2432612431302431357a69706a677641704d673966554a72677849492e537855722f2e32387a69584c336c34446254424f64774a5256557873746336, 'Charlie@gmail.com', 0),
(45, 'Cynthia', 'Rothrock', '2016-03-01', '+353862611666', 0x243261243130244d3764496a2f5970485a48414b4c6e567150577a306561455a6835347251366276723155374d5846765a61455953762e685a464d4f, 'Cynthia@gmail.com', 0),
(46, 'Jessica', 'Collins', '2016-03-01', '+353862611666', 0x243261243130246b6a2f316a797353564e6a3547523638614b6c343575556d2f556d335363774d6f744564466d44354841492e4b4370687268774743, 'Jessica@gmail.com', 0),
(47, 'Meredith', 'Scott', '2016-03-01', '+353862611666', 0x243261243130246365766c4f47726c51736c4b764846534478634450752f3056624d2e6a5368453754312e61376d5163726b48695545582f4f6c4c71, 'Meredith@gmail.com', 0),
(48, 'Micky', 'Dolenz', '1986-03-01', '+353862611666', 0x243261243130245649496a6e473969434d7a676258487744455668692e4a44573946634d6963746b7768536233417463687945643151467541507675, 'Micky@gmail.com', 0),
(49, 'Kristinia', 'DeBarge', '1970-03-01', '+353862611666', 0x243261243130246e73614e43562e4f542e684746704e653477746f562e633845542e54657265673574704e6d5a412e5a4d4278347a742e5055465357, 'Kristinia@gmail.com', 0),
(50, 'Barbara5', 'Harris5', '1988-03-01', '+353862611666', 0x24326124313024306e6f534d315565512e4434556d66442e476165724f6c6d4e2f37463036534a59494f4d2e454865454a314a786164504d75477975, 'Barbara@gmail.com', 0),
(63, 'Lexi', 'Forgt', NULL, NULL, 0x243261243130245553524b5443646858705448714162506c79624c742e3642786956304e6f2e52623331375a514c4f6d642e55476474703744484a4f, 'lexi@gmail.com', 0),
(64, 'tes', 'tes', NULL, NULL, 0x2432612431302450506257496b4d6a35386b7955666e4b55534b35487558505978382f61536771446a53597544524955326a57334b42436c30734175, 'tes@gmail.com', 0);

--
-- Triggers `users`
--
DELIMITER $$
CREATE TRIGGER `deleted_user_triger` AFTER DELETE ON `users` FOR EACH ROW BEGIN 
  INSERT INTO audit (description) 
  VALUES 
  (CONCAT("User deleted: "  , OLD.firstName, " ", OLD.lastName, " ", NOW())); 
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `new_user_registered_trigger` AFTER INSERT ON `users` FOR EACH ROW BEGIN 
  INSERT INTO audit (description) 
  VALUES 
  (CONCAT("New user Registered: ", NEW.firstName, " ", NEW.lastName, " ", NOW())); 
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_user_triger` AFTER UPDATE ON `users` FOR EACH ROW INSERT INTO audit (description) 
  VALUES 
  (CONCAT("User updated: "  , NEW.firstName, " ", NEW.lastName, " ", NOW()))
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`) USING BTREE,
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `audit`
--
ALTER TABLE `audit`
  ADD PRIMARY KEY (`audit_id`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`genre_id`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`movie_id`);

--
-- Indexes for table `movie_in_genre`
--
ALTER TABLE `movie_in_genre`
  ADD PRIMARY KEY (`movie_id`,`genre_id`),
  ADD KEY `genre_id` (`genre_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `address_id` (`address_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`order_id`,`movie_id`),
  ADD KEY `movie_id` (`movie_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `movie_id` (`movie_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
--
-- AUTO_INCREMENT for table `audit`
--
ALTER TABLE `audit`
  MODIFY `audit_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `genre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `movie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;
--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `movie_in_genre`
--
ALTER TABLE `movie_in_genre`
  ADD CONSTRAINT `movie_in_genre_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  ADD CONSTRAINT `movie_in_genre_ibfk_3` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_3` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_details_ibfk_4` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

