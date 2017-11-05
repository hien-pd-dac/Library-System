-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 04, 2017 lúc 08:37 PM
-- Phiên bản máy phục vụ: 10.1.28-MariaDB
-- Phiên bản PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db_itss`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `UserID` int(6) NOT NULL,
  `StartedDay` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `BookID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tittle` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `publisher` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ISBN` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'mã vạch'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bookcart`
--

CREATE TABLE `bookcart` (
  `bookCartID` int(9) NOT NULL,
  `CardID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `CopyID` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowedhistory`
--

CREATE TABLE `borrowedhistory` (
  `borrowedID` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `cardID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `lentAt` date NOT NULL COMMENT 'ngày mượn',
  `expectReturnAt` date NOT NULL COMMENT 'ngày trả dự kiến'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowedhistoryline`
--

CREATE TABLE `borrowedhistoryline` (
  `borrowedID` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `CopyID` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrower`
--

CREATE TABLE `borrower` (
  `UserID` int(6) NOT NULL,
  `StudentCardNum` varchar(10) COLLATE utf16_unicode_ci DEFAULT NULL,
  `period` varchar(10) COLLATE utf16_unicode_ci DEFAULT NULL,
  `isStudent` int(1) DEFAULT NULL COMMENT '0 là hs Hust 1 là không'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `CardID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `UserID` int(6) NOT NULL,
  `Activation Code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Expired Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `copyofbook`
--

CREATE TABLE `copyofbook` (
  `copyID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `BookID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(6) NOT NULL,
  `typeOfCopy` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(1) NOT NULL COMMENT '0 là sẵn sàng, 1 là để tham khảo, 2 là đăng kí mượn, 3 là đã có người mượn'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `librarian`
--

CREATE TABLE `librarian` (
  `UserID` int(6) NOT NULL,
  `StartedDay` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `registerborrow`
--

CREATE TABLE `registerborrow` (
  `registerID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `cardID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `registerAt` date NOT NULL,
  `status` int(1) NOT NULL COMMENT '0 là đã gd 1 là chưa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `registerborrowline`
--

CREATE TABLE `registerborrowline` (
  `registerID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `CopyID` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `UserID` int(6) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` int(1) NOT NULL COMMENT '0 là mane 1 là female',
  `contact` int(11) DEFAULT NULL,
  `role` int(1) NOT NULL COMMENT '0 là admin 1 là librarian 2 là borrower'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`UserID`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`BookID`);

--
-- Chỉ mục cho bảng `bookcart`
--
ALTER TABLE `bookcart`
  ADD PRIMARY KEY (`bookCartID`),
  ADD KEY `CardID` (`CardID`),
  ADD KEY `CopyID` (`CopyID`);

--
-- Chỉ mục cho bảng `borrowedhistory`
--
ALTER TABLE `borrowedhistory`
  ADD PRIMARY KEY (`borrowedID`),
  ADD KEY `cardID` (`cardID`);

--
-- Chỉ mục cho bảng `borrowedhistoryline`
--
ALTER TABLE `borrowedhistoryline`
  ADD PRIMARY KEY (`borrowedID`),
  ADD KEY `CopyID` (`CopyID`);

--
-- Chỉ mục cho bảng `borrower`
--
ALTER TABLE `borrower`
  ADD PRIMARY KEY (`UserID`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`CardID`),
  ADD KEY `UserID` (`UserID`);

--
-- Chỉ mục cho bảng `copyofbook`
--
ALTER TABLE `copyofbook`
  ADD PRIMARY KEY (`copyID`),
  ADD KEY `BookID` (`BookID`);

--
-- Chỉ mục cho bảng `librarian`
--
ALTER TABLE `librarian`
  ADD PRIMARY KEY (`UserID`);

--
-- Chỉ mục cho bảng `registerborrow`
--
ALTER TABLE `registerborrow`
  ADD PRIMARY KEY (`registerID`),
  ADD KEY `cardID` (`cardID`);

--
-- Chỉ mục cho bảng `registerborrowline`
--
ALTER TABLE `registerborrowline`
  ADD PRIMARY KEY (`registerID`),
  ADD KEY `CopyID` (`CopyID`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `borrowedhistory`
--
ALTER TABLE `borrowedhistory`
  ADD CONSTRAINT `borrowedhistory_ibfk_1` FOREIGN KEY (`cardID`) REFERENCES `cart` (`CardID`);

--
-- Các ràng buộc cho bảng `borrower`
--
ALTER TABLE `borrower`
  ADD CONSTRAINT `borrower_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `cart` (`UserID`);

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`CardID`) REFERENCES `bookcart` (`CardID`);

--
-- Các ràng buộc cho bảng `copyofbook`
--
ALTER TABLE `copyofbook`
  ADD CONSTRAINT `copyofbook_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`),
  ADD CONSTRAINT `copyofbook_ibfk_2` FOREIGN KEY (`copyID`) REFERENCES `borrowedhistoryline` (`CopyID`),
  ADD CONSTRAINT `copyofbook_ibfk_3` FOREIGN KEY (`copyID`) REFERENCES `registerborrowline` (`CopyID`),
  ADD CONSTRAINT `copyofbook_ibfk_4` FOREIGN KEY (`copyID`) REFERENCES `bookcart` (`CopyID`);

--
-- Các ràng buộc cho bảng `registerborrow`
--
ALTER TABLE `registerborrow`
  ADD CONSTRAINT `registerborrow_ibfk_1` FOREIGN KEY (`registerID`) REFERENCES `registerborrowline` (`registerID`),
  ADD CONSTRAINT `registerborrow_ibfk_2` FOREIGN KEY (`cardID`) REFERENCES `cart` (`CardID`);

--
-- Các ràng buộc cho bảng `registerborrowline`
--
ALTER TABLE `registerborrowline`
  ADD CONSTRAINT `registerborrowline_ibfk_1` FOREIGN KEY (`CopyID`) REFERENCES `copyofbook` (`copyID`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `borrower` (`UserID`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `librarian` (`UserID`),
  ADD CONSTRAINT `user_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `admin` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
