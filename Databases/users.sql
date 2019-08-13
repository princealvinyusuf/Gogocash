-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2019 at 03:40 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `users`
--

-- --------------------------------------------------------

--
-- Table structure for table `air`
--

CREATE TABLE `air` (
  `no` int(11) NOT NULL,
  `idp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `air`
--

INSERT INTO `air` (`no`, `idp`) VALUES
(3, '1327658'),
(4, '5438596'),
(5, '8888888');

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `no` int(11) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `kuota` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`no`, `no_hp`, `kuota`) VALUES
(6, '082286960095', '5'),
(7, '081270238839', '45'),
(8, '081270238839', '50');

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `no` int(11) NOT NULL,
  `games` varchar(30) NOT NULL,
  `voucher` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`no`, `games`, `voucher`) VALUES
(6, 'PUBG', '120000'),
(16, 'MOBILE LEGEND', '50000'),
(19, 'POKEMON', '100000'),
(20, 'SIMS', '125000'),
(33, 'FREE FIRE', '580000'),
(35, 'COC', '340800'),
(37, 'POKEMON', '200000'),
(38, 'PUBG', '120000');

-- --------------------------------------------------------

--
-- Table structure for table `indihome`
--

CREATE TABLE `indihome` (
  `no` int(11) NOT NULL,
  `idp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `indihome`
--

INSERT INTO `indihome` (`no`, `idp`) VALUES
(39, '88888888'),
(40, '12365478'),
(41, '56456754');

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `deskripsi` varchar(80) NOT NULL,
  `date` date NOT NULL DEFAULT '2019-08-05',
  `love` enum('true','false') NOT NULL DEFAULT 'false',
  `picture` varchar(100) NOT NULL DEFAULT 'http://192.168.43.209/android/notes/notes_picture/logo.png'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`id`, `name`, `deskripsi`, `date`, `love`, `picture`) VALUES
(4, 'Bayar Listrik', 'ID : 12322321421', '2019-02-05', 'true', '/notes/notes_picture/1.png'),
(5, 'Bayar Air', 'ID : 12322321422', '1970-01-01', 'true', '/notes/notes_picture/2.png'),
(6, 'Beli Voucher Games', 'Mobile Legends 100K', '1970-01-01', 'false', '/notes/notes_picture/3.png');

-- --------------------------------------------------------

--
-- Table structure for table `pln`
--

CREATE TABLE `pln` (
  `no` int(11) NOT NULL,
  `idp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pln`
--

INSERT INTO `pln` (`no`, `idp`) VALUES
(1, '54347'),
(2, '88765'),
(3, '14683');

-- --------------------------------------------------------

--
-- Table structure for table `pulsa`
--

CREATE TABLE `pulsa` (
  `no` int(11) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `denom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pulsa`
--

INSERT INTO `pulsa` (`no`, `no_hp`, `denom`) VALUES
(5, '082392042422', '75000'),
(6, '0811698313', '1000000'),
(7, '081270238839', '100000');

-- --------------------------------------------------------

--
-- Table structure for table `users_table`
--

CREATE TABLE `users_table` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `photo` varchar(100) NOT NULL DEFAULT '	http://192.168.43.209/android/notes/notes_picture/logo.png',
  `saldo` varchar(50) NOT NULL DEFAULT '100000'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_table`
--

INSERT INTO `users_table` (`id`, `name`, `email`, `password`, `photo`, `saldo`) VALUES
(3, 'fahmi', 'fahmi@gmail.com', '$2y$10$ToMzeWz2a6jbjwwA2vRjHOqfOLJgXblCNRsh6zyUjRuSPHjCSob7a', '/notes/notes_picture/1.png', '100000'),
(9, 'uas', 'uas@gmail.com', '$2y$10$xlj/4aNU/V.iF/Dn9Gh3GOkqHtqWa9NxUAg9FJF0MB08UZ19X7WJi', '/notes/notes_picture/1.png', '100000'),
(16, 'PRINCE ALVIN YUSUF', 'princealvinyusuf@gmail.com', '$2y$10$NxTos3Tq/koboD.FZY6hB.eupGpKrdYamo2wYk3J76fiQfV0wkvIy', '/notes/notes_picture/1.png', '100000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `air`
--
ALTER TABLE `air`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `indihome`
--
ALTER TABLE `indihome`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pln`
--
ALTER TABLE `pln`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `pulsa`
--
ALTER TABLE `pulsa`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `users_table`
--
ALTER TABLE `users_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `air`
--
ALTER TABLE `air`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `indihome`
--
ALTER TABLE `indihome`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `pln`
--
ALTER TABLE `pln`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `pulsa`
--
ALTER TABLE `pulsa`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `users_table`
--
ALTER TABLE `users_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
