CREATE TABLE `users` (
`id` int NOT NULL AUTO_INCREMENT,
`tckn` varchar(45) DEFAULT NULL,
`name` varchar(45) DEFAULT NULL,
`surname` varchar(45) DEFAULT NULL,
`password` varchar(45) DEFAULT NULL,
`email` varchar(45) DEFAULT NULL,
`phone` varchar(45) DEFAULT NULL,
`birthday` date DEFAULT NULL,
`address` varchar(45) DEFAULT NULL,
`gender` varchar(45) DEFAULT NULL,
`isActive` tinyint(1) DEFAULT NULL,
`created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `rooms` (
`id` int NOT NULL AUTO_INCREMENT,
`roomName` varchar(100) NOT NULL,
`roomNumber` int NOT NULL,
`capacity` int NOT NULL,
`price` decimal(10,2) NOT NULL,
`roomClass` varchar(50) DEFAULT NULL,
`description` text,
`hasSeaView` tinyint(1) DEFAULT NULL,
`hasJacuzzi` tinyint(1) DEFAULT NULL,
`hasSafeBox` tinyint(1) DEFAULT NULL,
`hasWifi` tinyint(1) DEFAULT NULL,
`isActive` tinyint(1) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `rezervations` (
`id` int NOT NULL AUTO_INCREMENT,
`userId` int NOT NULL,
`roomId` int NOT NULL,
`startDate` date NOT NULL,
`endDate` date NOT NULL,
`isActive` tinyint(1) NOT NULL,
`created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `payableservices` (
`id` int NOT NULL AUTO_INCREMENT,
`userId` int NOT NULL,
`serviceName` varchar(45) DEFAULT NULL,
`servicePrice` decimal(10,2) DEFAULT NULL,
`description` varchar(45) DEFAULT NULL,
`isActive` tinyint(1) NOT NULL DEFAULT '0',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `invoices` (
`id` int NOT NULL AUTO_INCREMENT,
`invoiceNumber` varchar(45) DEFAULT NULL,
`userId` int NOT NULL,
`totalAmount` decimal(10,2) DEFAULT NULL,
`invoiceDate` date NOT NULL,
`isActive` tinyint(1) NOT NULL DEFAULT '0',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci