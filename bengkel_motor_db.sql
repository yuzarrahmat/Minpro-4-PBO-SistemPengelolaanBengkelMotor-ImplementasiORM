-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 13, 2026 at 12:09 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bengkel_motor_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `layanan_servis`
--

CREATE TABLE `layanan_servis` (
  `kode_servis` varchar(20) NOT NULL,
  `plat_nomor` varchar(15) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `biaya_jasa` double NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `detail_layanan` varchar(255) NOT NULL,
  `biaya_tambahan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `layanan_servis`
--

INSERT INTO `layanan_servis` (`kode_servis`, `plat_nomor`, `nama_pelanggan`, `biaya_jasa`, `kategori`, `detail_layanan`, `biaya_tambahan`) VALUES
('SRV-1', 'KT 3907 BBM', 'Ryu', 30000, 'Servis Rutin', 'Shell Advance 10W-40', 65000),
('SRV-2', 'KT 6767 HEI', 'Budi', 100000, 'Perbaikan Berat', 'V-Belt & Roller CVT', 250000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `layanan_servis`
--
ALTER TABLE `layanan_servis`
  ADD PRIMARY KEY (`kode_servis`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
