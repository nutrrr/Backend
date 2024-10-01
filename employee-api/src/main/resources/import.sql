INSERT INTO department(code, name) VALUES (1, 'Mavel');
INSERT INTO department(code, name) VALUES (2, 'DC');

INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (1, 1, 'Tony','Stark','M',1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (2, 2, 'Bruce' , 'Wayne' , 'M' , 2,1);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (3, 3, 'Bruce' , 'Banner' , 'M' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (4, 4, 'Barry' , 'Allen' , 'M' , 2,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (5, 5, 'Diana' , 'Prince' , 'F' , 2,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (6, 6, 'Clark' , 'Kent' , 'M' , 2,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (7, 7, 'Carol' , 'Danvers' , 'F' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (8, 8, 'Steve' , 'Rogers' , 'M' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (9, 9, 'Natasha' , 'Romanova' , 'F' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (10, 10, 'Stephen' , 'Strange' , 'M' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (11, 11, 'Peter' , 'Parker' , 'M' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (12, 12, 'Peter' , 'Quill' , 'M' , 1,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (13, 13, 'Arthur' , 'Curry' , 'M' , 2,0);
INSERT INTO employee(id,seq_no, first_name,last_name,gender,department,version) VALUES (14, 14, 'Victor' , 'Stone' , 'M' , 2,0);

INSERT INTO ability(id , power , employee) VALUES (1 , 'Rich' , 1);

INSERT INTO employee_email(id,employee_id, email) VALUES (1 ,1, 'tony.s@mavel.com');
INSERT INTO employee_email(id,employee_id, email) VALUES (2 ,1, 'tony.s@gmail.com');


INSERT INTO running_seq(entity_class,current_sequence) VALUES ('th.co.cdgs.employee.Employee',14);
