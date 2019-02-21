-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Feb 2019 pada 17.29
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sjali_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `likes` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `books`
--

INSERT INTO `books` (`id`, `title`, `content`, `likes`, `created_at`, `updated_at`, `deleted_at`, `user_id`) VALUES
(2, 'Warrent Buffet', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut cursus nulla laoreet, molestie leo ut, vehicula arcu. Quisque varius, leo vitae elementum fringilla, diam metus sollicitudin quam, vitae consequat magna tellus a lectus. Nullam sit amet nulla eget urna efficitur aliquam id tempor elit. Duis fringilla ligula in turpis molestie lobortis eu at nibh. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec rutrum ante. Etiam et viverra neque. Donec blandit tellus vel mauris vulputate, sit amet mattis mi mattis. Duis feugiat, nisl sit amet pulvinar lobortis, lectus tortor bibendum erat, sed blandit mi ipsum pellentesque dolor. Fusce non sapien elit. Praesent interdum sed est at auctor. Nulla ultrices tempus velit. Pellentesque rhoncus aliquam erat faucibus hendrerit. Ut eleifend, eros a posuere gravida, metus ipsum lacinia enim, at dignissim dui felis id metus.', 1, '2018-12-08 00:00:00', '2019-01-08 17:00:00', NULL, 1),
(5, 'Gangster Without Hate', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ultrices, est vitae molestie imperdiet, lacus massa porta ex, sit amet convallis erat eros id velit. In sodales, tellus sed dictum bibendum, leo ex suscipit tortor, id ornare purus tortor et mi. Morbi purus ex, imperdiet eget hendrerit nec, ultricies vel orci. Sed sed risus libero. Sed volutpat felis turpis, ut hendrerit justo lacinia ac. Morbi porta gravida felis, eu varius mi hendrerit quis. Nullam ultricies maximus nibh. In hac habitasse platea dictumst. Vestibulum ultricies aliquam justo nec porta. Nam volutpat rhoncus ipsum in euismod. Nam malesuada risus non nulla tincidunt, sit amet volutpat mauris fermentum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque vitae maximus ipsum. Maecenas feugiat dui a est tristique, dignissim viverra mi viverra. Praesent egestas tincidunt metus non vestibulum. Curabitur ut velit ut nisl varius hendrerit.', 0, '2019-01-02 12:23:12', '2019-01-10 19:24:17', NULL, 1),
(22, 'ff', 'dfffggggg', 0, '2019-01-18 04:51:34', '2019-01-18 07:40:37', NULL, 1),
(23, 'hehr', 'dfffdss dddd ddddd dseed ddee ffggf ddsee fd fde ddedf ddss ddss ddeec hujjh ggyh hgf ffrff hbnnn ggg gggh hbj gggh gghh gggn gggg dww we eeee ', 0, '2019-01-18 17:13:18', '2019-01-18 10:13:18', NULL, 1),
(24, 'data baru', 'lorme ipsum lorme ipsumlorme ipsumlorme ipsumlorme ipsumlorme ipsum  lorme ipsum   lorme ipsum lorme ipsum', 20, '2019-01-15 06:04:23', '2019-01-24 00:05:21', NULL, 1),
(25, 'barulagi', 'lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem ', 0, '2019-01-30 05:00:00', '2019-01-22 17:00:00', NULL, 1),
(26, 'Coba', 'Workshop', 0, '2019-01-30 14:24:22', '2019-01-30 07:24:22', NULL, 1),
(27, 'behehr', 'ini isi vkaka', 0, '2019-01-31 14:18:59', '2019-01-31 07:18:59', NULL, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `favorites`
--

CREATE TABLE `favorites` (
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Seef Nasrul Islam Bambang', 'seefnasrul@gmail.com', 'asdfasdf', NULL, NULL, NULL),
(2, 'seef', 'seefnasrul@gmail.comms', '$2a$08$Vv8VVtC3r4XYDZ8G.DdXWuitfQD8JczT9SXFur61syiwv.6.j1.Wi', '2018-12-08 11:24:25', '2018-12-08 11:24:25', NULL),
(3, 'seef', 'seefnasrul@gmail.lol', '$2a$08$ILr9Ip.lDu94OM7nZEcTRuLrdxKnNGvIfOEdnvkULm5u7EZre5Ccy', '2018-12-08 11:27:10', '2018-12-08 11:27:10', NULL),
(4, 'seef', 'seefnasrul@gmail.loll', '$2a$08$5QeSBzMlcPzBRjOYNJIgK.p32pi3T1h1tzoWnBASgLh4rfp9/xo3y', '2018-12-08 11:29:14', '2018-12-08 11:29:14', NULL),
(5, 'Jayaaaa', 'jaya@gmail.com', 'asdfasdf', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_books_deleted_at` (`deleted_at`);

--
-- Indeks untuk tabel `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`book_id`,`user_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_users_deleted_at` (`deleted_at`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
