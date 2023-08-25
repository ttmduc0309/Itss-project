-- insert dock
INSERT INTO docks(name, address, area, numOfEmptyPoints) VALUES(
	'BK', '2 Dai Co Viet, Hai Ba Trung', 200.2, 25
), (
	'HK', '5 Le Thai To, Hoan Kiem', 150.5, 20
), (
	'PG', '5 Hung Vuong, Ba Dinh', 175.4, 22
);

-- insert bike types
INSERT INTO bike_types(name) VALUES ('Standard Bike'), ('Standard E-Bike'), ('Twin Bike');

-- insert regular bike in existing dock
INSERT INTO bikes(licensePlate, barCode, dockId, typeId, price) VALUES (
	'29S-54244', '154645667786', 3, 1, 1000000
),  (
	'29T-76535', '154653252223', 2, 3, 1500000
),  (
	'29T-76536', '154653252224', 2, 3, 1600000
),  (
	'29T-76537', '154653252225', 3, 3, 1550000
),  (
	'29T-76538', '154653252226', 2, 3, 1570000
),(
	'29S-54249', '154645667790', 1, 1, 1200000
),(
	'29S-54250', '154645667792', 3, 1, 1300000
),(
	'29S-54251', '154645667796', 1, 1, 1400000
);


-- insert e-bike in existing dock
INSERT INTO bikes(licensePlate, barCode, dockId, typeId, price, remainingBattery) VALUES (
	'30E-74262', '154646346446', 1, 2, 2200000, 90 
),(
	'30E-74263', '154646346447', 1, 2, 2200000, 91 
),(
	'30E-74264', '154646346448', 2, 2, 2000000, 82 
),(
	'30E-74265', '154646346449', 2, 2, 1750000, 70 
),(
	'30E-74266', '154646346450', 2, 2, 1800000, 85
),(
	'30E-74267', '154646346451', 1, 2, 1900000, 86 
),(
	'30E-74268', '154646346452', 3, 2, 1850000, 80 
);
