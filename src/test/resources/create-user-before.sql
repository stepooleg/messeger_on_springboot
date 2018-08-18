delete from user_role;
delete from usr;

insert into usr(ID, ACTIVE, password, username) values
(1, true, '$2a$08$8kzaq1yBn13NkKiBBx4U3esPBzdq8gtEZ1L3v2MNZPYznB1Idmh4q', 'admin');

insert into user_role(user_id, roles) values
(1, 'USER'), (1, 'ADMIN');

