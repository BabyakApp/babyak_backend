
LOAD DATA INFILE 'ewha_major.csv'
INTO TABLE MAJOR
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@college, @departure)
    set
    college = @college,
    departure = @departure;