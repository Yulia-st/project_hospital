-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Сен 25 2021 г., 14:36
-- Версия сервера: 5.6.26
-- Версия PHP: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `hospital`
--

-- --------------------------------------------------------

--
-- Структура таблицы `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `a_id` int(11) NOT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `admin`
--

INSERT INTO `admin` (`a_id`, `firstname`, `lastname`, `username`, `password`, `r_id`) VALUES
(1, 'Anton', 'Ivanov', 'iva12345', '12345678', 2),
(2, 'AÐ?Ð»Ð°Ð´', 'AÐ¡Ð¸Ð´Ð¾Ñ?Ð¾Ð²', 'Ñ?Ð¸Ð´123', '12345678', 2),
(3, 'A????', 'A???????', 'sid555', '12345678', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
  `d_id` int(11) NOT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `doctor`
--

INSERT INTO `doctor` (`d_id`, `firstname`, `lastname`, `username`, `category`, `password`, `r_id`) VALUES
(1, 'Anna', 'Ivanova', 'iv321', 'therapist', '123', 3),
(2, 'Irina', 'Vetrova', 'vet123', 'dentist', '34567', 3),
(3, 'Mike', 'Tyson', 'ty567', 'traumatologist', 'aaa1111', 3),
(4, 'Kate', 'Moss', 'mos987', 'pediatrist', 'bbb222', 3),
(5, 'Naomi', 'Ivanova', 'iv987', 'psychiatrist', '12345678', 3),
(6, 'Serg', 'Bezrukov', 'be678', 'dermatologist', 'eee444', 3),
(7, 'Konstantin', 'Sidorov', 'si987', 'allergist', 'vvv999', 3),
(8, 'Alexsey', 'Radionov', 'ra987', 'microbiologist', 'rrr555', 3),
(9, 'Zina', 'Krilova', 'kr456', 'anaesthesiologist', 'ddd555', 3),
(10, 'Gleb', 'Ford', 'fo345', 'surgeon', 'qqq567', 3),
(11, 'Harry', 'Simonov', 'sim123', 'cardiologist', '1234', 3),
(12, 'Anton', 'cvb', 'Sid123', 'dentist', '123', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `hospital_card`
--

CREATE TABLE IF NOT EXISTS `hospital_card` (
  `hc_id` int(11) NOT NULL,
  `diagnosis` varchar(50) DEFAULT NULL,
  `med_prescription` varchar(120) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hospital_card`
--

INSERT INTO `hospital_card` (`hc_id`, `diagnosis`, `med_prescription`, `d_id`) VALUES
(1, 'headaches', 'yoga course', 1),
(2, NULL, NULL, NULL),
(3, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `p_id` int(11) NOT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL,
  `hc_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `patient`
--

INSERT INTO `patient` (`p_id`, `firstname`, `lastname`, `username`, `birthday`, `password`, `r_id`, `hc_id`) VALUES
(1, 'Ivan', 'Ivanov', 'iv555', '1991-04-07', '12345678', 1, 1),
(2, 'Liza', 'Egorova', 'ego123', '2021-09-01', '12345678', 1, 2),
(3, 'Vlad', 'Gelezkin', 'gel123', '2021-08-30', '12345678', 1, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `patient_doctor`
--

CREATE TABLE IF NOT EXISTS `patient_doctor` (
  `p_id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `patient_doctor`
--

INSERT INTO `patient_doctor` (`p_id`, `d_id`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `r_id` int(11) NOT NULL,
  `role` varchar(16) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `role`
--

INSERT INTO `role` (`r_id`, `role`) VALUES
(1, 'patient'),
(2, 'admin'),
(3, 'doc');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`a_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `r_fk` (`r_id`);

--
-- Индексы таблицы `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`d_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `role_fk` (`r_id`);

--
-- Индексы таблицы `hospital_card`
--
ALTER TABLE `hospital_card`
  ADD PRIMARY KEY (`hc_id`),
  ADD KEY `doc_fk` (`d_id`);

--
-- Индексы таблицы `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`p_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `hcard_fk` (`hc_id`);

--
-- Индексы таблицы `patient_doctor`
--
ALTER TABLE `patient_doctor`
  ADD PRIMARY KEY (`p_id`,`d_id`),
  ADD KEY `doct_fk` (`d_id`);

--
-- Индексы таблицы `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`r_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `admin`
--
ALTER TABLE `admin`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `doctor`
--
ALTER TABLE `doctor`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT для таблицы `hospital_card`
--
ALTER TABLE `hospital_card`
  MODIFY `hc_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `patient`
--
ALTER TABLE `patient`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `role`
--
ALTER TABLE `role`
  MODIFY `r_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `r_fk` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE SET NULL;

--
-- Ограничения внешнего ключа таблицы `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `role_fk` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `hospital_card`
--
ALTER TABLE `hospital_card`
  ADD CONSTRAINT `doc_fk` FOREIGN KEY (`d_id`) REFERENCES `doctor` (`d_id`) ON DELETE SET NULL;

--
-- Ограничения внешнего ключа таблицы `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `hcard_fk` FOREIGN KEY (`hc_id`) REFERENCES `hospital_card` (`hc_id`) ON DELETE SET NULL;

--
-- Ограничения внешнего ключа таблицы `patient_doctor`
--
ALTER TABLE `patient_doctor`
  ADD CONSTRAINT `doct_fk` FOREIGN KEY (`d_id`) REFERENCES `doctor` (`d_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `pat_fk` FOREIGN KEY (`p_id`) REFERENCES `patient` (`p_id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
