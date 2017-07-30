CREATE TABLE soil_type
(
  soil_id INT AUTO_INCREMENT,
  name VARCHAR(60),
  PRIMARY KEY soil_type(soil_id)
);

CREATE TABLE plant_month
(
  month_id INT AUTO_INCREMENT,
  name VARCHAR(100),
  days_in_month INT DEFAULT '31',
  PRIMARY KEY plant_month(month_id)
);

CREATE TABLE vegetable
(
  vegetable_id INT auto_increment,
  name VARCHAR(300),
  soil_id INT,
  PRIMARY KEY vegetable(vegetable_id),
  FOREIGN KEY vegetable(soil_id) REFERENCES soil_type(soil_id) ON DELETE RESTRICT
);

CREATE TABLE vegetable_sowing_month
(
  vegetable_id INT,
  month_id INT,
  PRIMARY KEY vegetable_sowing_month(vegetable_id, month_id),
  FOREIGN KEY vegetable_sowing_month(vegetable_id) REFERENCES vegetable(vegetable_id) ON DELETE CASCADE,
  FOREIGN KEY vegetable_sowing_month(vegetable_id) REFERENCES plant_month(month_id) ON DELETE CASCADE
);

CREATE TABLE vegetable_harvest_month
(
  vegetable_id INT,
  month_id INT,
  PRIMARY KEY vegetable_harvest_month(vegetable_id, month_id),
  FOREIGN KEY vegetable_harvest_month(vegetable_id) REFERENCES vegetable(vegetable_id) ON DELETE CASCADE,
  FOREIGN KEY vegetable_harvest_month(vegetable_id) REFERENCES plant_month(month_id) ON DELETE CASCADE
);

CREATE TABLE vegetable_pruning_month
(
  vegetable_id INT,
  month_id INT,
  PRIMARY KEY vegetable_pruning_month(vegetable_id, month_id),
  FOREIGN KEY vegetable_pruning_month(vegetable_id) REFERENCES vegetable(vegetable_id) ON DELETE CASCADE,
  FOREIGN KEY vegetable_pruning_month(vegetable_id) REFERENCES plant_month(month_id) ON DELETE CASCADE
);