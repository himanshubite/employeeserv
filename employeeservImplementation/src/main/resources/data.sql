Insert into ADDRESS (line1, line2, city,state, zipp_code) values ('Adarsh Palm Retreat', 'Bellandur', 'Bangalore','KA', 560103)
Insert into ADDRESS (line1,  city,state, zipp_code) values ('Vars Parkwood', 'Bangalore','KA', 560035)

Insert into Employee (first_name, last_name, date_of_birth, address_id) values ('Himanshu', 'Singh', '1988-03-20', (SELECT address_id FROM ADDRESS WHERE zipp_code=560103))
Insert into Employee (first_name, last_name, date_of_birth, address_id) values ('Tom', 'Thomas', '1982-03-23', (SELECT address_id FROM ADDRESS WHERE zipp_code=560035))