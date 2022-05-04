DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS uesr_roles;

CREATE TABLE user_roles (
	id SERIAL PRIMARY KEY,
	role VARCHAR(50) NOT NULL
);

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(200) NOT NULL, 
	email VARCHAR(150) NOT NULL UNIQUE,
	user_role_id INTEGER NOT NULL,
	
	CONSTRAINT fk_user_roles FOREIGN KEY (user_role_id) REFERENCES user_roles(id)
);

DROP TABLE IF EXISTS reimbursements; 
DROP TABLE IF EXISTS reimbursement_status;
DROP TABLE IF EXISTS reimbursement_type;


CREATE TABLE reimbursement_status (
	id SERIAL PRIMARY KEY,
	status VARCHAR(20)
);

CREATE TABLE reimbursement_type (
	id SERIAL PRIMARY KEY,
	type VARCHAR(20)
);

CREATE TABLE reimbursements (
	id SERIAL PRIMARY KEY,
	amount NUMERIC NOT NULL,
	reimb_submitted TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	reimb_resolved TIMESTAMP,
	description VARCHAR(250),
	receipt BYTEA,
	author_id INTEGER NOT NULL,
	resolver_id INTEGER,
	status_id INTEGER DEFAULT 1,
	type_id INTEGER,
	
	CONSTRAINT employee_fk FOREIGN KEY (author_id) REFERENCES users(user_id),
	CONSTRAINT fm_fk FOREIGN KEY (resolver_id) REFERENCES users(user_id),
	CONSTRAINT status_fk FOREIGN KEY (status_id) REFERENCES reimbursement_status(id),
	CONSTRAINT type_fk FOREIGN KEY (type_id) REFERENCES reimbursement_type(id)
);

CREATE VIEW reimbursements2 AS
	SELECT r.id as reimb_id, r.amount, r.reimb_submitted, r.reimb_resolved, r.description, r.receipt, u.user_id, u.first_name, u.last_name, u.email,  u.username, ur.role, r.resolver_id, rt.type, rs.status 
	FROM reimbursements r 
	LEFT JOIN users u 
	ON r.author_id = u.user_id 
	LEFT JOIN reimbursement_type rt 
	ON r.type_id = rt.id 
	LEFT JOIN reimbursement_status rs 
	ON r.status_id = rs.id
	LEFT JOIN user_roles ur
	ON u.user_role_id = ur.id; 

CREATE VIEW users2 AS
	SELECT u.user_id as user_id, u.username, u.password, u.first_name, u.last_name, u.email, ur.role 
	FROM users u 
	LEFT JOIN user_roles ur
	ON u.user_role_id = ur.id; 
