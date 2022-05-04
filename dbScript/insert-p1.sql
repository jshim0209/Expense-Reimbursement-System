INSERT INTO user_roles (
	role 
) VALUES 
	('finance_manager'),
	('employee');


INSERT INTO users (
	first_name, last_name, username, password, email, user_role_id 
) VALUES
	('Jiwon', 'Shim', 'jshim', 'jiwon1234', 'jshim@revature.com', 1),
	('Minah', 'Kim', 'mkim', 'minah1234', 'mkim@revature.com', 2),
	('Jason', 'Vonortas', 'jvonortas', 'jason1234', 'jvonortas@revature.com', 2),
	('Joe', 'Cho', 'jcho', 'joe1234', 'jcho@revature.com', 2);

INSERT INTO users (
	first_name, last_name, username, password, email, user_role_id 
) VALUES
	('Justin', 'Lee', 'jlee', 'justin1234', 'jlee@revature.com', 1),
	('young', 'Kim', 'ykim', 'youn1234', 'ykim@revature.com', 2),
	('Andy', 'Choi', 'achoi', 'andy1234', 'achoi@revature.com', 2),
	('Su', 'Kwon', 'skwon', 'su1234', 'skwon@revature.com', 2);


INSERT INTO reimbursement_status (
	status
) VALUES 
	('pending'),
	('approved'),
	('denied');

INSERT INTO reimbursement_type (
	type
) VALUES 
	('lodging'),
	('travel'),
	('food'),
	('other');

INSERT INTO reimbursements (
	amount,
	description,
	author_id,
	resolver_id,
	status_id,
	type_id
)
VALUES
	('98.99', 'Food expences for business trip', '3', null, '1', '3'),
	('480.86', 'Plane ticket to Dallas', '2', null, '1', '2'),
	('245.00', 'Oracle Java Cerification', '4', NULL , '1', '4');


INSERT INTO reimbursements (
	amount,
	description,
	author_id,
	resolver_id,
	status_id,
	type_id
)
VALUES
	('1700.98', 'Relocation Assistance', '6', null, '1', '4'),
	('198.86', 'Train ticket to Chicago', '7', null, '1', '2'),
	('68.99', 'Lunch with client', '8', NULL , '1', '1');
	
INSERT INTO reimbursements (
	amount, description, reimb_submitted, type_id, receipt, author_id  
)
VALUES (
'95.50', 'Amtrak', current_timestamp, (SELECT rt.id from reimbursement_type rt WHERE rt.type = 'travel'), null, 4);


INSERT INTO reimbursements (
	amount,
	description,
	status_id,
	type_id,
	author_id 
	
)
VALUES ('1600.75', 'lodging', 1, 1, 3);



INSERT INTO reimbursements (
	type_id, description, reimb_submitted, amount, receipt, author_id  
)
VALUES (
(SELECT rt.id from reimbursement_type rt WHERE rt.type = 'food'), 'Lunch with client', current_timestamp, '95.50', null, 3);

UPDATE reimbursements 
SET reimb_resolved = CURRENT_TIMESTAMP, fm_id = 1, status_id = 2
WHERE id = 2;

UPDATE reimbursements 
SET reimb_resolved = CURRENT_TIMESTAMP, fm_id = 1, status_id = 3
WHERE id = 1;



INSERT INTO reimbursements (
	amount, description, author_id, type_id
)
VALUES (
78.99, 'gas money', 7, 4);

