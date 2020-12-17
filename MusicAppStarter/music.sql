-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 06, 2020 at 04:53 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: music
--
DROP DATABASE IF EXISTS music;
CREATE DATABASE IF NOT EXISTS music DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE music;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `AddAlbum`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddAlbum` (IN `inAlbumTitle` TEXT, IN `inArtist` TEXT)  BEGIN
 INSERT INTO album (albumtitle, artist)
        VALUES (inAlbumTitle, inArtist);
END$$

DROP PROCEDURE IF EXISTS `AddTrack`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddTrack` (IN `inTrackNo` INT, IN `inTrackTitle` TEXT, IN `inTrackLength` INT, IN `inAlbumId` INT)  BEGIN
 INSERT INTO track (TRACKNO, TRACKTITLE, TRACKLENGTH, ALBUMID)
        VALUES (inTrackNo, inTrackTitle, inTrackLength, inAlbumId);
END$$

DROP PROCEDURE IF EXISTS `GetAlbum`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAlbum` (IN `inAlbumId` INT)  BEGIN
 SELECT *
 FROM     album
 WHERE  albumid = inAlbumId ;
END$$

DROP PROCEDURE IF EXISTS `GetAlbums`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAlbums` ()  READS SQL DATA
BEGIN
  Select AlbumId, AlbumTitle, Artist From Album Order By AlbumId;    
END$$

DROP PROCEDURE IF EXISTS `GetAlbumTracks`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAlbumTracks` (IN `inAlbumId` INT)  NO SQL
BEGIN
  SELECT TRACKID, TRACKNO, TRACKTITLE, TRACKLENGTH FROM TRACK
  WHERE ALBUMID = inAlbumId 
  Order By TRACKNO;
End$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table album
--

DROP TABLE IF EXISTS album;
CREATE TABLE IF NOT EXISTS album (
  ALBUMID int(11) NOT NULL AUTO_INCREMENT,
  ALBUMTITLE varchar(30) NOT NULL,
  ARTIST varchar(30) NOT NULL,
  PRIMARY KEY (ALBUMID)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table album
--

INSERT INTO album (ALBUMID, ALBUMTITLE, ARTIST) VALUES
(1, 'Close To The Edge', 'Yes'),
(2, 'Florasongs', 'The Decemberists'),
(3, 'Picaresqueties', 'The Decemberists');

-- --------------------------------------------------------

--
-- Table structure for table track
--

DROP TABLE IF EXISTS track;
CREATE TABLE IF NOT EXISTS track (
  TRACKID int(11) NOT NULL AUTO_INCREMENT,
  TRACKNO int(11) NOT NULL,
  TRACKTITLE varchar(30) NOT NULL,
  TRACKLENGTH int(11) NOT NULL,
  ALBUMID int(11) NOT NULL,
  PRIMARY KEY (TRACKID),
  KEY ALBUMID (ALBUMID)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table track
--

INSERT INTO track (TRACKID, TRACKNO, TRACKTITLE, TRACKLENGTH, ALBUMID) VALUES
(1, 1, 'Close To The Edge', 1110, 1),
(2, 2, 'And You And I', 599, 1),
(3, 3, 'Siberian Khatru', 536, 1),
(4, 1, 'Why Would I Now?', 222, 2),
(5, 2, 'Riverswim', 293, 2),
(6, 3, 'Fits & Starts', 161, 2),
(7, 4, 'The Harrowed And The Haunted', 247, 2),
(8, 5, 'Stateside', 205, 2),
(9, 1, 'Bandit Queen', 230, 3),
(10, 2, 'Kingdom Of Spain', 354, 3),
(11, 4, 'America', 420, 1),
(12, 3, 'Constantinople', 320, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table track
--
ALTER TABLE track
  ADD CONSTRAINT track_ibfk_1 FOREIGN KEY (ALBUMID) REFERENCES album (ALBUMID);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
