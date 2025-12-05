insert into users_details ( id, birth_date, name)
VALUES
(10001, current_date(), 'Ranga'),
(10002, current_date(), 'Kingsley');


insert into post ( id, description, user_id)
VALUES
(20001, 'I want to learn AWS', 10001),
(20002, 'I want to learn DevOps', 10001),
(20003, 'I want to learn Spring boot', 10002),
(20004, 'I want to learn Caching', 10001),
(20005, 'I want to learn Dance', 10002);