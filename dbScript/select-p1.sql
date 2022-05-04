SELECT * 
FROM users;

SELECT *
FROM users 
WHERE user_id = 2;

SELECT *
FROM user_roles;

SELECT  u.user_id, u.first_name, u.last_name, u.username, u.password, u.email, ur.role 
FROM users u
INNER JOIN user_roles ur
ON u.user_role_id = ur.id;


SELECT u.user_id, u.username, u.password, ur.role 
FROM users u
INNER JOIN user_roles ur
ON u.user_role_id = ur.id;
WHERE users.username = 'jshim' AND users.password = 'Jiwon1234';

-- 

SELECT *
FROM users
WHERE 
	username = 'jcho' 
AND 
	password = 'joe1234';
	
--

Select username, email
FROM users
WHERE
	username = 'jcho'
OR
	email = 'jcho@revature.com';
	

select *
from users
where id = 2;

SELECT *
FROM reimbursement_type;

SELECT *
FROM reimbursement_status;
	

SELECT amount, description, author_id, resolver_id, status_id, type_id
FROM reimbursements;


SELECT r.id, r.amount, r.description, reimbursement_status.status, reimbursement_type.type, r.reimb_submitted, r.reimb_resolved,
		employee_user.user_id as employee_id, fm_user.user_id as fm_id,
		employee_user.username as employee_username, employee_user.password as employee_password,
		fm_user.username as fm_username, fm_user.password as fm_password
FROM reimbursements r
LEFT JOIN users employee_user
ON employee_user.user_id = r.author_id
LEFT JOIN users fm_user
ON fm_user.user_id = r.resolver_id
LEFT JOIN reimbursement_status
ON r.status_id = reimbursement_status.id
LEFT JOIN reimbursement_type 
ON r.type_id = reimbursement_type.id;



SELECT r.id, r.amount, r.description, reimbursement_status.status, reimbursement_type.type, r.reimb_submitted, r.reimb_resolved,
		employee_user.user_id as employee_id, fm_user.user_id as fm_id,
		employee_user.username as employee_username, employee_user.password as employee_password,
		fm_user.username as fm_username, fm_user.password as fm_password
FROM reimbursements r
LEFT JOIN users employee_user
ON employee_user.user_id = r.author_id
LEFT JOIN users fm_user
ON fm_user.user_id = r.resolver_id
LEFT JOIN reimbursement_status
ON r.status_id = reimbursement_status.id
LEFT JOIN reimbursement_type 
ON r.type_id = reimbursement_type.id
WHERE r.author_id = 2;


SELECT r.id, r.amount, r.description, reimbursement_status.status, reimbursement_type.type, r.reimb_submitted, r.reimb_resolved,
		employee_user.user_id as employee_id, fm_user.user_id as fm_id,
		employee_user.username as employee_username, employee_user.password as employee_password,
		fm_user.username as fm_username, fm_user.password as fm_password
FROM reimbursements r
LEFT JOIN users employee_user
ON employee_user.user_id = r.author_id
LEFT JOIN users fm_user
ON fm_user.user_id = r.resolver_id
LEFT JOIN reimbursement_status
ON r.status_id = reimbursement_status.id
LEFT JOIN reimbursement_type 
ON r.type_id = reimbursement_type.id
WHERE reimbursement_status.status = 'denied'
ORDER BY r.id;

UPDATE reimbursements 
SET status_id = (SELECT rs.id FROM reimbursement_status rs WHERE rs.status = 'approved'), reimb_resolved = current_timestamp, fm_id = 1
WHERE id = 3;


select *
from reimbursements2;

select *
FROM reimbursements2 rt
where rt.username = 'mkim';

select *
from reimbursements2
where status = 'approved'
order by reimb_id;

select *
from reimbursements2 rt 
where rt.reimb_id  = 2;

select *
from users 
where user_id = 2;

select u.user_id, u.username, u.password, u.email, ur.role, u.first_name, u.last_name 
from users u 
inner join user_roles ur 
on u.user_role_id = ur.id 
where u.user_id = 2;

select *
from reimbursements;
