-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 13, 2024 at 10:01 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket2cinema`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminid` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminid`, `username`, `password`) VALUES
(1, 'iamshiv', 'Shiv198922');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `movie_id` int(50) NOT NULL,
  `movie_name` varchar(50) NOT NULL,
  `movie_price` double NOT NULL,
  `movie_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movie_id`, `movie_name`, `movie_price`, `movie_time`) VALUES
(1, 'kalki', 200, 'morning'),
(4, 'spiderman', 500, 'morning'),
(5, 'Stree 2', 300, 'night'),
(6, 'Kill', 200, 'Morning'),
(7, 'Vedaa', 150, 'Night'),
(8, 'minions', 100, 'morning');

--
-- Triggers `movie`
--
DELIMITER $$
CREATE TRIGGER `updateMovie` AFTER UPDATE ON `movie` FOR EACH ROW BEGIN
    INSERT INTO movieupdateinfo (movie_id, movie_name, time)
    VALUES (NEW.movie_id, NEW.movie_name, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `movieupdateinfo`
--

CREATE TABLE `movieupdateinfo` (
  `updateid` int(50) NOT NULL,
  `movie_id` int(50) NOT NULL,
  `movie_name` varchar(100) NOT NULL,
  `time` timestamp(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movieupdateinfo`
--

INSERT INTO `movieupdateinfo` (`updateid`, `movie_id`, `movie_name`, `time`) VALUES
(2, 5, 'Stree 2', '2024-08-24 10:12:22.000000'),
(3, 7, 'Vedaa', '2024-08-29 05:30:14.000000'),
(4, 7, 'Vedaa', '2024-08-29 05:30:29.000000'),
(5, 7, 'Vedaa', '2024-08-29 05:30:48.000000'),
(6, 6, 'Kill', '2024-08-29 05:33:24.000000'),
(7, 6, 'Kill', '2024-08-29 05:33:32.000000'),
(8, 6, 'Kill', '2024-08-29 05:33:48.000000');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `registerid` int(50) NOT NULL,
  `userid` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `time` datetime(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`registerid`, `userid`, `username`, `time`) VALUES
(1, '14', 'iamsavan', '2024-08-24 15:31:49.000000'),
(2, '15', 'iamjaysvi', '2024-08-29 09:50:15.000000'),
(3, '16', 'iamshreya', '2024-08-29 10:53:22.000000'),
(4, '17', 'priya', '2024-08-30 22:13:24.000000'),
(5, '18', 'utsav', '2024-08-31 09:11:03.000000');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` int(10) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `userpassword` varchar(50) NOT NULL,
  `mobile_no` varchar(10) NOT NULL,
  `email_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `firstname`, `lastname`, `username`, `userpassword`, `mobile_no`, `email_id`) VALUES
(1, 'Shiv', 'Raghuvanshi', 'iamshiv', 'shiv198922', '1234567890', 'shivarajsinhr@gmail.com'),
(2, 'Akshat', 'Raval', 'akshatraval01', 'Akshat@420', '0987654321', 'akshatraval199@gmail.com'),
(5, 'hitarth', 'doshi', 'hitarthdoshi', 'heyHitarth', '', 'hitarth.82005@gmail.com'),
(6, 'meet', 'patel', 'meetpatel', 'patelmeet', '', ''),
(7, 'jadav', 'aditya', 'adityajadav', 'jadavaditya', '', ''),
(8, 'dhiraj', 'patil', 'dhirajpatil', 'Dhiraj@002', '', 'dhirajpatil11072006@gmail.com'),
(9, 'om', 'sonchhatra', 'whoisom', 'Om@9018', '9104359394', 'omsonchhatra07@gmail.com'),
(10, 'abhimanyusinh', 'raghuvanshi', 'jayasvi', '1671', '9824068664', 'abhimanyusinhraghuvanshi1234@gmail.com'),
(11, 'ansh', 'gohel', 'ansh1212', 'Ansh123', '9825903639', 'ansh12122005@gmail.com'),
(12, 'krrish', 'makwana', 'makwanakrish2201', 'yrqqb9k2nj', '9409000682', 'toup2201@gmail.com'),
(13, 'Mohitraj', 'Jadeja', 'mohitraj', 'mohit09', '2345678901', 'shivrajsinhr@gmail.com'),
(14, 'savan', 'dhandhukiya', 'iamsavan', 'savan', '0987654321', 'savan@gmail.com'),
(15, 'jayasvi', 'Raghuvanshi', 'iamjaysvi', 'jayasvi', '1902837465', 'shivrajsinhr@gmail.com'),
(16, 'Shreya', 'Patel', 'iamshreya', 'shreya1234', '0110928375', 'shivarajsinhr@gmail.com'),
(17, 'Priya', 'Shah', 'priya', 'priya', '1092837465', 'shivarajsinhr@gmail.com'),
(18, 'Utsav', 'Patel', 'utsav', 'utsav', '1092765554', 'shivarajsinhr@gmail.com');

--
-- Triggers `user`
--
DELIMITER $$
CREATE TRIGGER `register` AFTER INSERT ON `user` FOR EACH ROW BEGIN
    INSERT INTO register (userid, username, time)
    VALUES (new.userid, new.username, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateUser` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
    INSERT INTO userupdateinfo (userid, username, time)
    VALUES (NEW.userid, NEW.username, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `userupdateinfo`
--

CREATE TABLE `userupdateinfo` (
  `userupdateid` int(50) NOT NULL,
  `userid` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `time` datetime(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userupdateinfo`
--

INSERT INTO `userupdateinfo` (`userupdateid`, `userid`, `username`, `time`) VALUES
(1, '14', 'iamsavan', '2024-08-24 15:46:56.000000'),
(2, '1', 'iamshiv', '2024-08-26 15:52:42.000000'),
(3, '15', 'iamjaysvi', '2024-08-29 09:51:18.000000'),
(4, '17', 'priya', '2024-08-30 22:13:50.000000'),
(5, '17', 'priya', '2024-08-30 22:13:58.000000'),
(6, '1', 'iamshiv', '2024-08-30 22:52:48.000000'),
(7, '1', 'iamshiv', '2024-08-30 22:53:03.000000'),
(8, '1', 'iamshiv', '2024-08-31 09:09:24.000000'),
(9, '18', 'utsav', '2024-08-31 09:11:20.000000'),
(10, '18', 'utsav', '2024-08-31 09:11:23.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminid`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movie_id`);

--
-- Indexes for table `movieupdateinfo`
--
ALTER TABLE `movieupdateinfo`
  ADD PRIMARY KEY (`updateid`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`registerid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `userupdateinfo`
--
ALTER TABLE `userupdateinfo`
  ADD PRIMARY KEY (`userupdateid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `movie_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `movieupdateinfo`
--
ALTER TABLE `movieupdateinfo`
  MODIFY `updateid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `registerid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `userupdateinfo`
--
ALTER TABLE `userupdateinfo`
  MODIFY `userupdateid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
