-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2025 at 07:05 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aramotorhubdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cart_id`, `customer_id`, `created_at`) VALUES
(7, 2, '2025-06-08 18:49:32'),
(8, 10, '2025-06-09 07:45:09'),
(12, 34, '2025-06-18 05:56:18'),
(13, 59, '2025-06-18 05:57:55');

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `cart_item_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`cart_item_id`, `cart_id`, `product_id`, `quantity`) VALUES
(36, 7, 6, 1),
(37, 8, 1, 12),
(38, 8, 2, 3),
(39, 8, 6, 1),
(40, 8, 46, 1),
(41, 8, 75, 1),
(64, 12, 75, 1),
(66, 12, 72, 1),
(67, 12, 64, 1),
(68, 12, 50, 1),
(69, 12, 93, 1),
(70, 12, 60, 3);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `name`) VALUES
(1, 'Engine'),
(2, 'Suspension'),
(3, 'Wheels'),
(4, 'Oil'),
(5, 'Bolts'),
(6, 'Exterior'),
(7, 'Electrical'),
(8, 'Transmission ');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `address` text DEFAULT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `status` enum('PENDING','COMPLETED','CANCELLED') DEFAULT 'PENDING',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_id`, `customer_id`, `address`, `total_price`, `status`, `created_at`) VALUES
(60, 37, '123 Harmony St, Sampaloc, Manila', 2800.00, 'COMPLETED', '2025-06-16 13:04:51'),
(62, 39, '678 Diva Blvd, Sampaloc, Manila', 3200.00, 'PENDING', '2025-06-16 13:04:51'),
(63, 40, '222 Uptown Funk Rd, Sampaloc, Manila', 2800.00, 'PENDING', '2025-06-16 13:04:51'),
(64, 41, '5 Hello Street, Sampaloc, Manila', 1600.00, 'PENDING', '2025-06-16 13:04:51'),
(65, 42, '301 Umbrella Ave, Sampaloc, Manila', 900.00, 'PENDING', '2025-06-16 13:04:51'),
(68, 45, '15 Purpose Rd, Sampaloc, Manila', 750.00, 'PENDING', '2025-06-16 13:04:51'),
(69, 46, '303 Ocean Eyes Blvd, Sampaloc, Manila', 2800.00, 'PENDING', '2025-06-16 13:04:51'),
(70, 47, '606 Blinding Lights Ave, Sampaloc, Manila', 1100.00, 'PENDING', '2025-06-16 13:04:51'),
(71, 48, '454 Levitating Street, Sampaloc, Manila', 320.00, 'PENDING', '2025-06-16 13:04:51'),
(72, 49, '909 Watermelon Sugar Rd, Sampaloc, Manila', 1600.00, 'PENDING', '2025-06-16 13:04:51'),
(73, 50, '101 Chromatica Ave, Sampaloc, Manila', 650.00, 'PENDING', '2025-06-16 13:04:51'),
(1002, 59, 'com231 408mb', 1280.00, 'COMPLETED', '2025-06-18 05:58:41'),
(1003, 43, '99 Hotline Bling Dr, Sampaloc, Manila', 4500.00, 'COMPLETED', '2025-06-19 13:43:00'),
(1004, 44, '888 Sweetener St, Sampaloc, Manila', 7300.00, 'COMPLETED', '2025-06-19 13:43:00'),
(1005, 45, '15 Purpose Rd, Sampaloc, Manila', 9300.00, 'COMPLETED', '2025-06-19 13:43:00'),
(1006, 46, '303 Ocean Eyes Blvd, Sampaloc, Manila', 530.00, 'PENDING', '2025-06-19 13:43:00'),
(1007, 47, '606 Blinding Lights Ave, Sampaloc, Manila', 8300.00, 'PENDING', '2025-06-19 13:43:00'),
(1008, 48, '454 Levitating Street, Sampaloc, Manila', 7500.00, 'PENDING', '2025-06-19 13:43:00'),
(1009, 49, '909 Watermelon Sugar Rd, Sampaloc, Manila', 2800.00, 'PENDING', '2025-06-19 13:43:00'),
(1010, 50, '101 Chromatica Ave, Sampaloc, Manila', 7300.00, 'PENDING', '2025-06-19 13:43:00');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `order_item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price_at_purchase` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`order_item_id`, `order_id`, `product_id`, `quantity`, `price_at_purchase`) VALUES
(50, 60, 1, 1, 2800.00),
(52, 62, 3, 1, 3200.00),
(53, 63, 1, 1, 1500.00),
(54, 63, 4, 2, 650.00),
(55, 64, 5, 2, 800.00),
(56, 65, 6, 1, 900.00),
(59, 68, 8, 1, 750.00),
(60, 69, 1, 1, 2800.00),
(61, 70, 9, 1, 1100.00),
(62, 71, 10, 2, 160.00),
(63, 72, 3, 1, 1600.00),
(64, 73, 4, 1, 650.00),
(65, 1002, 75, 4, 320.00),
(66, 1003, 122, 1, 4500.00),
(67, 1004, 83, 2, 650.00),
(68, 1004, 84, 1, 2800.00),
(69, 1005, 120, 1, 7500.00),
(70, 1005, 82, 1, 180.00),
(71, 1005, 83, 1, 650.00),
(72, 1006, 83, 1, 650.00),
(73, 1006, 82, 1, 180.00),
(74, 1007, 121, 1, 6500.00),
(75, 1007, 83, 1, 650.00),
(76, 1007, 84, 1, 1150.00),
(77, 1008, 120, 1, 7500.00),
(78, 1009, 84, 1, 2800.00),
(79, 1010, 83, 2, 650.00),
(80, 1010, 84, 1, 2800.00);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `price` int(10) NOT NULL,
  `stock` int(11) DEFAULT 0,
  `status` enum('AVAILABLE','OUT_OF_STOCK') DEFAULT 'AVAILABLE',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `seller_id`, `category_id`, `name`, `description`, `price`, `stock`, `status`, `created_at`) VALUES
(1, 11, 1, 'V8 Engine', 'High-performance V8 engine', 25000, 5, 'AVAILABLE', '2025-06-08 12:26:52'),
(2, 10, 2, 'Shock Absorber', 'Durable suspension shock absorber', 1500, 10, 'AVAILABLE', '2025-06-08 12:26:52'),
(3, 11, 3, 'Alloy Wheel 18\"', 'Lightweight alloy wheels', 8000, 7, 'AVAILABLE', '2025-06-08 12:26:52'),
(4, 11, 4, 'Synthetic Motor Oil', 'Premium synthetic oil 5W-30', 1200, 20, 'AVAILABLE', '2025-06-08 12:26:52'),
(5, 11, 5, 'Titanium Bolts Set', 'Set of 20 titanium bolts', 500, 50, 'AVAILABLE', '2025-06-08 12:26:52'),
(6, 12, 1, 'V8 Engine', 'High-performance V8 engine', 25000, 5, 'AVAILABLE', '2025-06-08 12:27:26'),
(7, 10, 2, 'Shock Absorber', 'Durable suspension shock absorber', 1500, 10, 'AVAILABLE', '2025-06-08 12:27:26'),
(8, 11, 3, 'Alloy Wheel 18\"', 'Lightweight alloy wheels', 8000, 7, 'AVAILABLE', '2025-06-08 12:27:26'),
(9, 11, 4, 'Synthetic Motor Oil', 'Premium synthetic oil 5W-30', 1200, 20, 'AVAILABLE', '2025-06-08 12:27:26'),
(10, 11, 5, 'Titanium Bolts Set', 'Set of 20 titanium bolts', 500, 50, 'AVAILABLE', '2025-06-08 12:27:26'),
(11, 10, 1, 'V8 Engine', 'High-performance V8 engine', 25000, 5, 'AVAILABLE', '2025-06-08 12:29:05'),
(12, 10, 2, 'Shock Absorber', 'Durable suspension shock absorber', 1500, 10, 'AVAILABLE', '2025-06-08 12:29:05'),
(13, 11, 3, 'Alloy Wheel 18\"', 'Lightweight alloy wheels', 8000, 7, 'AVAILABLE', '2025-06-08 12:29:05'),
(14, 11, 4, 'Synthetic Motor Oil', 'Premium synthetic oil 5W-30', 1200, 20, 'AVAILABLE', '2025-06-08 12:29:05'),
(15, 11, 5, 'Titanium Bolts Set', 'Set of 20 titanium bolts', 500, 50, 'AVAILABLE', '2025-06-08 12:29:05'),
(17, 12, 1, 'Timing Belt', 'Durable timing belt for various models', 80, 30, 'AVAILABLE', '2025-06-08 14:12:15'),
(18, 12, 1, 'Fuel Injector', 'High precision fuel injector', 150, 25, 'AVAILABLE', '2025-06-08 14:12:15'),
(19, 12, 2, 'Coil Spring', 'High performance coil spring for off-road', 200, 12, 'AVAILABLE', '2025-06-08 14:12:15'),
(26, 12, 4, 'Brake Fluid DOT 4', 'Brake fluid meeting DOT 4 standards', 18, 40, 'AVAILABLE', '2025-06-08 14:12:15'),
(30, 12, 5, 'Socket Head Bolt M6', 'Socket head cap screw for brakes', 4, 350, 'AVAILABLE', '2025-06-08 14:12:15'),
(31, 12, 6, 'Side Mirror Assembly', 'Adjustable side mirror for sedans', 120, 10, 'AVAILABLE', '2025-06-08 14:12:15'),
(32, 12, 6, 'Car Door Handle', 'Exterior door handle with lock', 40, 25, 'AVAILABLE', '2025-06-08 14:12:15'),
(34, 12, 1, 'Oil Pump', 'High-efficiency oil pump for turbo engines', 95, 15, 'AVAILABLE', '2025-06-09 06:36:56'),
(35, 12, 1, 'Camshaft', 'Precision machined camshaft for performance', 180, 10, 'AVAILABLE', '2025-06-09 06:36:56'),
(36, 12, 2, 'Lowering Kit', 'Complete lowering kit for sedans', 300, 5, 'AVAILABLE', '2025-06-09 06:36:56'),
(37, 12, 2, 'Control Arm', 'Aluminum control arm set', 210, 8, 'OUT_OF_STOCK', '2025-06-09 06:36:56'),
(38, 12, 3, 'Steel Wheel 16\"', 'Heavy-duty steel wheel for trucks', 4000, 12, 'AVAILABLE', '2025-06-09 06:36:56'),
(39, 12, 3, 'Wheel Spacer Set', 'Aluminum spacers 15mm thick', 600, 20, 'AVAILABLE', '2025-06-09 06:36:56'),
(40, 12, 4, 'Engine Flush', 'Pre-oil-change engine flush', 22, 30, 'AVAILABLE', '2025-06-09 06:36:56'),
(41, 12, 4, 'Gear Oil 75W-90', 'Synthetic gear oil for differentials', 80, 25, 'AVAILABLE', '2025-06-09 06:36:56'),
(42, 12, 5, 'Exhaust Bolt Kit', 'Stainless steel bolt set for exhaust systems', 15, 100, 'AVAILABLE', '2025-06-09 06:36:56'),
(43, 12, 5, 'Head Bolt Set', 'Hardened bolts for cylinder head installation', 45, 60, 'AVAILABLE', '2025-06-09 06:36:56'),
(44, 12, 6, 'Spoiler Wing', 'Aerodynamic trunk spoiler', 350, 6, 'AVAILABLE', '2025-06-09 06:36:56'),
(45, 12, 6, 'Fog Light Kit', 'LED fog lights with wiring harness', 80, 18, 'AVAILABLE', '2025-06-09 06:36:56'),
(46, 12, 7, 'Alternator', '130A alternator for compact cars', 220, 4, 'OUT_OF_STOCK', '2025-06-09 06:36:56'),
(47, 12, 7, 'Starter Motor', '12V starter motor for diesel engines', 250, 5, 'OUT_OF_STOCK', '2025-06-09 06:36:56'),
(48, 12, 8, 'Clutch Kit', 'Complete clutch kit with flywheel', 600, 3, 'AVAILABLE', '2025-06-09 06:36:56'),
(49, 12, 8, 'Gearbox Mount', 'Polyurethane gearbox mount', 90, 7, 'AVAILABLE', '2025-06-09 06:36:56'),
(50, 10, 1, 'Crankshaft Pulley', 'Heavy-duty pulley for crankshaft systems', 2200, 15, 'AVAILABLE', '2025-06-09 06:37:07'),
(51, 10, 1, 'Oil Pump', 'Reliable oil circulation for performance engines', 3200, 10, 'AVAILABLE', '2025-06-09 06:37:07'),
(52, 10, 2, 'Control Arm', 'OEM front lower control arm', 1800, 20, 'OUT_OF_STOCK', '2025-06-09 06:37:07'),
(53, 10, 2, 'Strut Mount', 'Shock absorber mount with bearing', 950, 25, 'OUT_OF_STOCK', '2025-06-09 06:37:07'),
(54, 10, 3, 'Chrome Rim 17\"', 'Sleek chrome finish wheels', 9500, 5, 'OUT_OF_STOCK', '2025-06-09 06:37:07'),
(55, 10, 3, 'Steel Wheel 15\"', 'Durable steel wheels for trucks', 4300, 8, 'AVAILABLE', '2025-06-09 06:37:07'),
(56, 10, 7, 'Alternator', 'High output alternator for SUVs', 3500, 12, 'OUT_OF_STOCK', '2025-06-09 06:37:07'),
(57, 10, 7, 'Ignition Coil', 'Performance ignition coil pack', 600, 30, 'AVAILABLE', '2025-06-09 06:37:07'),
(58, 11, 4, 'Engine Flush', 'Pre-oil change engine cleaner', 450, 50, 'AVAILABLE', '2025-06-09 06:37:14'),
(59, 11, 4, 'Gear Oil 75W-90', 'High performance gear oil for manual transmissions', 700, 20, 'AVAILABLE', '2025-06-09 06:37:14'),
(60, 11, 5, 'Hex Bolt Set M10', 'Stainless steel M10 bolts pack of 25', 350, 60, 'AVAILABLE', '2025-06-09 06:37:14'),
(61, 11, 5, 'Washer & Nut Combo', 'Anti-rust washers and nuts set', 200, 100, 'AVAILABLE', '2025-06-09 06:37:14'),
(62, 11, 6, 'Spoiler Kit', 'Universal fit aerodynamic spoiler', 2100, 6, 'AVAILABLE', '2025-06-09 06:37:14'),
(63, 11, 6, 'Wind Deflectors', 'Side window deflectors for reduced wind noise', 900, 15, 'AVAILABLE', '2025-06-09 06:37:14'),
(64, 11, 8, 'Clutch Disc', 'Heavy-duty clutch disc for manual cars', 2500, 10, 'AVAILABLE', '2025-06-09 06:37:14'),
(65, 11, 8, 'Gearbox Mount', 'Gearbox vibration isolation mount', 1200, 20, 'AVAILABLE', '2025-06-09 06:37:14'),
(66, 10, 1, 'Timing Chain Kit', 'Full timing chain kit with gears and tensioner', 4800, 10, 'AVAILABLE', '2025-06-09 06:39:44'),
(70, 10, 2, 'Upper Control Arm', 'Heavy-duty front upper control arm', 2200, 18, 'AVAILABLE', '2025-06-09 06:39:44'),
(72, 10, 2, 'Ball Joint Kit', 'Front ball joints for off-road vehicles', 1350, 30, 'AVAILABLE', '2025-06-09 06:39:44'),
(73, 10, 3, 'Off-Road Alloy Wheel 17\"', 'Reinforced alloy wheel for rugged terrain', 8700, 6, 'OUT_OF_STOCK', '2025-06-09 06:39:44'),
(74, 10, 3, 'Hub Cap Set', 'Set of 4 wheel hub caps with logo', 600, 25, 'AVAILABLE', '2025-06-09 06:39:44'),
(75, 10, 4, '4-Stroke Engine Oil 1L', 'Motor oil for motorcycles and small engines', 320, 40, 'AVAILABLE', '2025-06-09 06:39:44'),
(76, 10, 4, 'Motor Oil Filter', 'High-efficiency filter compatible with 80+ models', 400, 50, 'AVAILABLE', '2025-06-09 06:39:44'),
(77, 10, 5, 'Engine Mount Bolts Set', 'High torque-resistant bolts for engine mounting', 500, 60, 'AVAILABLE', '2025-06-09 06:39:44'),
(78, 10, 5, 'Cylinder Head Bolt Kit', 'OEM spec cylinder head bolts for diesel engines', 1100, 20, 'AVAILABLE', '2025-06-09 06:39:44'),
(79, 10, 6, 'Hood Latch Assembly', 'Secure hood latch system with cable', 850, 15, 'AVAILABLE', '2025-06-09 06:39:44'),
(80, 10, 6, 'Grille Guard', 'Heavy-duty front grille guard for SUVs', 3800, 5, 'AVAILABLE', '2025-06-09 06:39:44'),
(81, 10, 7, 'Starter Motor', '12V starter motor for compact cars', 3200, 12, 'OUT_OF_STOCK', '2025-06-09 06:39:44'),
(82, 10, 7, 'Battery Terminal Connectors', 'Corrosion-resistant battery terminal clamps', 180, 70, 'AVAILABLE', '2025-06-09 06:39:44'),
(83, 10, 8, 'Transmission Fluid Filter', 'Auto transmission fluid filter replacement', 650, 35, 'AVAILABLE', '2025-06-09 06:39:44'),
(84, 10, 8, 'Shift Solenoid Kit', 'Full solenoid pack for automatic gearboxes', 2800, 10, 'AVAILABLE', '2025-06-09 06:39:44'),
(85, 11, 1, 'Crankshaft Pulley', 'Durable crankshaft pulley with vibration damper', 2800, 14, 'AVAILABLE', '2025-06-09 06:41:55'),
(86, 11, 1, 'Engine Mount', 'Hydraulic engine mount for smooth operation', 1400, 25, 'AVAILABLE', '2025-06-09 06:41:55'),
(87, 11, 1, 'Spark Plug Set', 'Set of 4 iridium spark plugs', 900, 35, 'AVAILABLE', '2025-06-09 06:41:55'),
(88, 11, 2, 'Rear Shock Absorber', 'Gas-charged rear shock absorber for sedans', 1600, 20, 'AVAILABLE', '2025-06-09 06:41:56'),
(89, 11, 2, 'Strut Mount Kit', 'Top strut mount kit with bearings', 1100, 18, 'AVAILABLE', '2025-06-09 06:41:56'),
(90, 11, 2, 'Leaf Spring', 'Heavy-duty leaf spring for pickups', 3200, 10, 'AVAILABLE', '2025-06-09 06:41:56'),
(91, 11, 3, 'Steel Rim 16\"', 'Durable steel rim for commercial vehicles', 4000, 8, 'AVAILABLE', '2025-06-09 06:41:56'),
(92, 11, 3, 'Wheel Bearing Hub', 'Complete front wheel hub assembly', 2400, 12, 'AVAILABLE', '2025-06-09 06:41:56'),
(93, 11, 4, 'Gear Oil 75W-90', 'Synthetic gear oil for manual transmissions', 650, 30, 'AVAILABLE', '2025-06-09 06:41:56'),
(94, 11, 4, 'Engine Flush', 'Engine flush additive for internal cleaning', 320, 40, 'AVAILABLE', '2025-06-09 06:41:56'),
(95, 11, 5, 'Brake Caliper Bolts', 'High-grade bolts for disc brake calipers', 250, 100, 'AVAILABLE', '2025-06-09 06:41:56'),
(96, 11, 5, 'Transmission Bolt Set', 'OEM bolts for auto transmission assembly', 750, 45, 'AVAILABLE', '2025-06-09 06:41:56'),
(97, 11, 6, 'Rear Spoiler', 'Aerodynamic spoiler for performance styling', 5600, 7, 'AVAILABLE', '2025-06-09 06:41:56'),
(98, 11, 6, 'Mud Guard Set', 'Universal 4-piece mudguard set', 900, 25, 'AVAILABLE', '2025-06-09 06:41:56'),
(99, 11, 7, 'Ignition Coil Pack', '4-coil ignition system for smooth firing', 2800, 18, 'AVAILABLE', '2025-06-09 06:41:56'),
(100, 11, 7, 'Headlight Wiring Harness', 'Plug-and-play wiring for headlights', 450, 30, 'AVAILABLE', '2025-06-09 06:41:56'),
(117, 10, 3, 'PCX Off-Road Scrambler Wheel ', 'color green', 500000, 10, 'AVAILABLE', '2025-06-18 09:38:33'),
(118, 10, 2, 'Öhlins STX 46', ' A premium rear shock absorber known for its performance and adjustability, often used in racing and high-end street bikes.', 5000, 12, 'AVAILABLE', '2025-06-18 12:48:04'),
(120, 10, 2, 'Showa SFF-BP (Separate Function Fork - Big Piston)', 'A front suspension fork used on many Honda and Kawasaki models for improved damping and handling.', 7500, 25, 'AVAILABLE', '2025-06-18 12:48:49'),
(121, 10, 2, 'WP APEX PRO 6746', 'A high-performance rear shock developed for KTM and Husqvarna motorcycles, commonly found on adventure and off-road bikes.', 6500, 25, 'AVAILABLE', '2025-06-18 12:49:07'),
(122, 10, 2, 'KYB USD (Upside Down) Forks', 'A front suspension system found on many Yamaha and Kawasaki models, providing better handling and rigidity for sport bikes.', 4500, 23, 'AVAILABLE', '2025-06-18 12:49:30');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(3) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `address` varchar(100) NOT NULL,
  `district` varchar(25) NOT NULL,
  `barangay` varchar(25) NOT NULL,
  `role` enum('CUSTOMER','SELLER','ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `first_name`, `last_name`, `address`, `district`, `barangay`, `role`) VALUES
(1, 'admin', '1234', '', '', '0', '0', '0', 'ADMIN'),
(2, 'customer', '1234', '', '', '0', '0', '0', 'CUSTOMER'),
(10, 'seller1@gmail.com', '1234', 'Moto', 'Zone', '123 fake street sampaloc manila', 'Sampaloc', 'Barangay 397', 'SELLER'),
(11, 'seller2@gmail.com', '1234', 'Rider\'s', 'Paradise', '456 Real Ave', 'Sampaloc', 'Barangay 395', 'SELLER'),
(12, 'seller3@gmail.com', '1234', 'Gear Up', 'Garage', '123 fake address', 'Sampaloc', 'Barangay 400', 'SELLER'),
(30, 'luke', '1234', '', '', '', '', '', 'ADMIN'),
(31, 'kyle', '1234', '', '', '', '', '', 'ADMIN'),
(34, 'carl@gmail.com', '1234', 'Carl', 'Anastacio', '123 Sampaloc Manila St. ', 'Sampaloc', 'Barangay 395', 'CUSTOMER'),
(35, 'joeydeleon@email.com', '1234', 'Joey', 'De Leon', '191 vicente cruz street sampaloc manila', 'Sampaloc', 'Barangay 398', 'CUSTOMER'),
(37, 'taylor.swift@gmail.com', '1234', 'Taylor', 'Swift', '123 Harmony St, Sampaloc, Manila', 'Sampaloc', 'Barangay 395', 'CUSTOMER'),
(38, 'ed.sheeran@gmail.com', '1234', 'Ed', 'Sheeran', '45 Melody Lane, Sampaloc, Manila', 'Sampaloc', 'Barangay 395', 'CUSTOMER'),
(39, 'beyonce.knowles@gmail.com', '1234', 'Beyonce', 'Knowles', '678 Diva Blvd, Sampaloc, Manila', 'Sampaloc', 'Barangay 396', 'CUSTOMER'),
(40, 'bruno.mars@gmail.com', '1234', 'Bruno', 'Mars', '222 Uptown Funk Rd, Sampaloc, Manila', 'Sampaloc', 'Barangay 396', 'CUSTOMER'),
(41, 'adele.laurie@gmail.com', '1234', 'Adele', 'Laurie', '5 Hello Street, Sampaloc, Manila', 'Sampaloc', 'Barangay 397', 'CUSTOMER'),
(42, 'rihanna.fenty@gmail.com', '1234', 'Rihanna', 'Fenty', '301 Umbrella Ave, Sampaloc, Manila', 'Sampaloc', 'Barangay 397', 'CUSTOMER'),
(43, 'drake.graham@gmail.com', '1234', 'Drake', 'Graham', '99 Hotline Bling Dr, Sampaloc, Manila', 'Sampaloc', 'Barangay 398', 'CUSTOMER'),
(44, 'ariana.grande@gmail.com', '1234', 'Ariana', 'Grande', '888 Sweetener St, Sampaloc, Manila', 'Sampaloc', 'Barangay 398', 'CUSTOMER'),
(45, 'justin.bieber@gmail.com', '1234', 'Justin', 'Bieber', '15 Purpose Rd, Sampaloc, Manila', 'Sampaloc', 'Barangay 399', 'CUSTOMER'),
(46, 'billie.eilish@gmail.com', '1234', 'Billie', 'Eilish', '303 Ocean Eyes Blvd, Sampaloc, Manila', 'Sampaloc', 'Barangay 399', 'CUSTOMER'),
(47, 'the.weeknd@gmail.com', '1234', 'The', 'Weeknd', '606 Blinding Lights Ave, Sampaloc, Manila', 'Sampaloc', 'Barangay 400', 'CUSTOMER'),
(48, 'dua.lipa@gmail.com', '1234', 'Dua', 'Lipa', '454 Levitating Street, Sampaloc, Manila', 'Sampaloc', 'Barangay 400', 'CUSTOMER'),
(49, 'harry.styles@gmail.com', '1234', 'Harry', 'Styles', '909 Watermelon Sugar Rd, Sampaloc, Manila', 'Sampaloc', 'Barangay 395', 'CUSTOMER'),
(50, 'lady.gaga@gmail.com', '1234', 'Lady', 'Gaga', '101 Chromatica Ave, Sampaloc, Manila', 'Sampaloc', 'Barangay 395', 'CUSTOMER'),
(51, 'shawn.mendes@gmail.com', '1234', 'Shawn', 'Mendes', '321 Stitches St, Sampaloc, Manila', 'Sampaloc', 'Barangay 396', 'CUSTOMER'),
(52, 'katy.perry@gmail.com', '1234', 'Katy', 'Perry', '777 Firework Blvd, Sampaloc, Manila', 'Sampaloc', 'Barangay 396', 'CUSTOMER'),
(53, 'zayn.malik@gmail.com', '1234', 'Zayn', 'Malik', '888 Pillowtalk Ave, Sampaloc, Manila', 'Sampaloc', 'Barangay 397', 'CUSTOMER'),
(54, 'selena.gomez@gmail.com', '1234', 'Selena', 'Gomez', '213 Revival Rd, Sampaloc, Manila', 'Sampaloc', 'Barangay 397', 'CUSTOMER'),
(55, 'marshmello.dj@gmail.com', '1234', 'Marshmello', 'DJ', '555 Happier St, Sampaloc, Manila', 'Sampaloc', 'Barangay 398', 'CUSTOMER'),
(56, 'charlie.puth@gmail.com', '1234', 'Charlie', 'Puth', '191 See You Again St, Sampaloc, Manila', 'Sampaloc', 'Barangay 398', 'CUSTOMER'),
(57, 'lebrongames@gmail.com', 'pass', 'lerbon', 'james', '441 patato', 'Sampaloc', 'Barangay 396', 'CUSTOMER'),
(59, 'quatrotayo@gmail.com', '4444', 'jensen', 'quatro', 'nu com231 408mb', 'Sampaloc', 'Barangay 400', 'CUSTOMER'),
(85, 'viceganda', '1234', '', '', '', '', '', 'ADMIN'),
(86, 'admin2', '1234', '', '', '', '', '', 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`cart_item_id`),
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`order_item_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `seller_id` (`seller_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `cart_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1011;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  ADD CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  ADD CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
