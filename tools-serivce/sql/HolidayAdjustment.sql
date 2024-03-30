CREATE TABLE `holiday_adjustment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateFrom` varchar(45) NOT NULL,
  `dateTo` varchar(45) NOT NULL,
  `isHoliday` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
