-- create some brands and models
insert into vehicle_brand (name)
values ('Audi'), ('BMW'), ('Maruti'), ('Tata Motors'), ('Tesla'), ('Volkswagen');

insert into vehicle_model(name, year, vehicle_brand_id)
values
    ('X1', 2017, 1), ('X2', 2018, 1), ('X3', 2019, 1),
    ('Y1', 1977, 2), ('Y2', 2017, 2), ('Y3', 2014, 2), ('Y4', 1955, 2),
    ('Z1', 2016, 3), ('Z2', 2011, 3),
    ('A1', 2015, 4), ('A2', 2019, 4), ('A3', 1995, 4), ('A4', 2020, 4), ('A5', 2004, 4),
    ('B1', 1989, 5), ('B2', 2003, 5), ('B3', 2019, 5),
    ('C1', 2022, 6), ('C2', 2010, 6), ('C3', 2016, 6);

-- create a couple of customer
insert into customer (first_name, last_name, birth_date)
values
    ('nrapendra', 'kumar', '1989-01-01'),
    ('ajay', 'singh', '1990-01-01');

-- create some contracts
insert into contract (contract_number, customer_id, monthly_rate)
values
    (1, 1, 300),
    (2, 1, 350),
    (3, 2, 250),
    (4, 2, 450);

-- create some vehicles
insert into vehicle (vehicle_model_id, price, vin, contract_id)
values
    (1, 37000, 'WA1CMAFPXEA115549', 1),
    (5, 38000, '5UXKR6C56E0303444', 2),
    (8, 39000, null, 3),
    (10, 40000, null, 4),
    (17, 50000, 'WP0AF2A93BS785679', null),
    (19, 51000, null, null);