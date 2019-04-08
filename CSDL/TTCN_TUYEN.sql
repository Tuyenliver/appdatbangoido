-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th4 07, 2019 lúc 10:37 AM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `TTCN_TUYEN`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `gia` float NOT NULL,
  `sl` float NOT NULL,
  `thanhtien` float NOT NULL,
  `trangthai` int(2) NOT NULL,
  `id_desk` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id`, `ten`, `gia`, `sl`, `thanhtien`, `trangthai`, `id_desk`, `id_user`) VALUES
(1, 'Do uong', 2, 1, 2, 0, 0, 0),
(2, 'do an', 1, 1, 1, 0, 0, 0),
(3, 'Do uong', 2, 1, 2, 0, 0, 0),
(4, 'do an', 1, 1, 1, 0, 0, 0),
(5, 'Do uong', 2, 13, 26, 0, 0, 0),
(6, 'do an', 1, 6, 6, 0, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_chitiethoadon`
--

CREATE TABLE `tbl_chitiethoadon` (
  `id` int(11) NOT NULL,
  `idhoadon` int(11) NOT NULL,
  `idsanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `thanhtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `tbl_chitiethoadon`
--

INSERT INTO `tbl_chitiethoadon` (`id`, `idhoadon`, `idsanpham`, `soluong`, `thanhtien`) VALUES
(1, -1, 0, 2, 4),
(2, -1, 0, 2, 2),
(3, -1, 0, 4, 8),
(4, -1, 0, 4, 4),
(5, -1, 0, 14, 28),
(6, -1, 0, 4, 8),
(7, -1, 0, 4, 4),
(8, -1, 1, 4, 8),
(9, -1, 2, 4, 4),
(10, -1, 1, 4, 8),
(11, -1, 2, 4, 4),
(12, -1, 1, 4, 8),
(13, -1, 2, 4, 4),
(14, 10, 1, 4, 8),
(15, 10, 2, 4, 4),
(16, 11, 0, 50, 100),
(17, 12, 1, 23, 46),
(18, 13, 1, 11, 22),
(19, 15, 1, 1, 2),
(20, 16, 2, 4, 4),
(21, 17, 3, 1, 2),
(22, 18, 1, 1, 2),
(23, 19, 1, 1, 2),
(24, 20, 1, 1, 2),
(25, 21, 1, 1, 2),
(26, 21, 2, 5, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_desk`
--

CREATE TABLE `tbl_desk` (
  `id` int(11) NOT NULL,
  `soban` int(11) NOT NULL,
  `trangthai` tinyint(1) NOT NULL,
  `soghe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `tbl_desk`
--

INSERT INTO `tbl_desk` (`id`, `soban`, `trangthai`, `soghe`) VALUES
(1, 1, 0, 4),
(2, 2, 1, 4),
(3, 3, 0, 4),
(4, 4, 0, 4),
(5, 5, 0, 4),
(6, 6, 0, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_hoadon`
--

CREATE TABLE `tbl_hoadon` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `datehoadon` date NOT NULL,
  `iddesk` int(11) NOT NULL,
  `tongtien` float NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `tbl_hoadon`
--

INSERT INTO `tbl_hoadon` (`id`, `iduser`, `datehoadon`, `iddesk`, `tongtien`, `trangthai`) VALUES
(12, 1, '2019-04-04', 5, 2, 1),
(13, 1, '2019-04-04', 1, 2, 1),
(14, 0, '2019-04-06', 0, 0, 0),
(15, 1, '2019-04-06', 2, 2, 1),
(16, 1, '2019-04-06', 1, 1, 1),
(17, 1, '2019-04-06', 3, 2, 1),
(18, 1, '2019-04-06', 2, 2, 1),
(19, 1, '2019-04-07', 2, 2, 1),
(20, 1, '2019-04-07', 2, 2, 0),
(21, 1, '2019-04-07', 1, 2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_sanpham`
--

CREATE TABLE `tbl_sanpham` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `sl` int(11) NOT NULL,
  `loai` int(11) NOT NULL,
  `img` varchar(255) NOT NULL,
  `gia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `tbl_sanpham`
--

INSERT INTO `tbl_sanpham` (`id`, `ten`, `sl`, `loai`, `img`, `gia`) VALUES
(1, 'Do uong', 2, 2, '2', 2),
(2, 'do an', 2, 1, '2', 1),
(3, 'Do uong', 2, 2, '2', 2),
(4, 'do an', 2, 1, '2', 1),
(5, 'Do uong', 2, 2, '2', 2),
(6, 'do an', 2, 1, '2', 1),
(7, 'Do uong', 2, 2, '2', 2),
(8, 'do an', 2, 1, '2', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `tendangnhap` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `hoten` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `tendangnhap`, `pass`, `level`, `hoten`) VALUES
(1, 'admin', 'admin', 1, 'admin'),
(2, '2', '2', 2, '2'),
(3, '3', '3', 2, '3');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_chitiethoadon`
--
ALTER TABLE `tbl_chitiethoadon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_desk`
--
ALTER TABLE `tbl_desk`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_hoadon`
--
ALTER TABLE `tbl_hoadon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `tbl_chitiethoadon`
--
ALTER TABLE `tbl_chitiethoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `tbl_desk`
--
ALTER TABLE `tbl_desk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `tbl_hoadon`
--
ALTER TABLE `tbl_hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
