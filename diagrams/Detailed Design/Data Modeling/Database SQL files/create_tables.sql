CREATE TABLE docks (
	id serial primary key,
	name varchar(40) not null,
	address text not null,
	area numeric not null,
	numOfAvailableBikes int not null default 0,
	numOfEmptyPoints int not null
);

CREATE TABLE bike_types(
	id serial primary key,
	name varchar(20) not null
);

CREATE TABLE bikes(
	id serial primary key,
	licensePlate varchar(10) unique not null,
	barCode char(12) unique not null,
	dockId int REFERENCES docks(id),
	typeId int not null REFERENCES bike_types(id),
	price bigint not null,
	remainingBattery int
);

CREATE TABLE rentalInfo (
	id serial primary key,
	bikeId int not null REFERENCES bikes(id),
	rentDockId int not null REFERENCES docks(id),
	returnDockId int REFERENCES docks(id),
	rentStartTime timestamptz default now(),
	rentEndTime timestamptz,
	depositAmount bigint NOT NULL,
	rentalFee bigint
);

CREATE TABLE transactions (
	id serial primary key,
	content text,
	amount bigint NOT NULL,
	timeCreated timestamptz default now()
);


CREATE OR REPLACE FUNCTION updateNumBikes()
RETURNS TRIGGER AS $$
BEGIN
	IF NEW.dockId IS NOT NULL THEN
		UPDATE docks
		SET numOfAvailableBikes = numOfAvailableBikes + 1,
		numOfEmptyPoints = numOfEmptyPoints - 1
		WHERE docks.id = NEW.dockId;
	END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insertBikeTrigger 
AFTER INSERT ON bikes
FOR EACH ROW
EXECUTE FUNCTION updateNumBikes();
