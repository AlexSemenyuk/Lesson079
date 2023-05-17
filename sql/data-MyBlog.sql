use myBlog;
insert into roles (mean)
VALUES ('User'), ('Admin');

insert into users (first_name, last_name, avatar, login, password, role_id)
VALUES ('Oleg', 'Luchenko', 'none', 'admin', 'admin', 1),
       ('Vitek', 'Verepianko', 'none', 'vitek', '123', 2),
       ('Anna', 'Yaschenko', 'none', 'anna', '321', 2);

insert into users (first_name, last_name, avatar, login, password, role_id)
VALUES ('Alex', 'Semenyuk', 'https://www.vhv.rs/dpng/d/276-2761771_transparent-avatar-png-vector-avatar-icon-png-png.png', 'alex', '111', 2);

insert into drafts (mean)
VALUES ('Yes'), ('No');

insert into posts (title, published, author_id, image_path, content, draft_id)
VALUES ('Garden', '01-05-2020 12:50:00', 1, 'none', 'different', 1);
#        ('Vitek', 'Verepianko', 'none', 'vitek', '123', 2),
#        ('Anna', 'Yaschenko', 'none', 'anna', '321', 2);

select * from users where login='admin' and password='admin';
SELECT * FROM users WHERE login = 'admin' and password = 'admin';
SELECT * FROM posts order by id desc;

insert into users (first_name, last_name, login, password)
VALUES ('Vitek', 'Konovalov', 'vitek', '531');




