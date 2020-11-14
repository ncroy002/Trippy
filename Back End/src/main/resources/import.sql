use Trippy;
/*
Accounts:
id	email			password	first_name	last_name	role		username
1	admin@a.com		abc123		adminFN		adminLN		ROLE_ADMIN	admin@a.com
2	user@a.com		abc123		userFN		userLN		ROLE_USER	user@a.com
3	temp1@a.com		abc123		temp1FN		temp1LN		ROLE_USER	temp1@a.com
4	temp2@a.com		abc123		temp2FN		temp2LN		ROLE_USER	temp2@a.com
5	temp3@a.com		abc123		temp3FN		temp3LN		ROLE_USER	temp3@a.com
6	temp4@a.com		abc123		temp4FN		temp4LN		ROLE_USER	temp4@a.com
7	temp5@a.com		abc123		temp5FN		temp5LN		ROLE_USER	temp5@a.com
Events:
id	date		name	website
1	10/16/2020	event1	event1.com
2	10/20/2020	event2	event2.com
3	11/1/2020	event3	event3.com
Events Ratings:
id	rating	account_id	event_id
1	1	    1	        1
2	2	    1	        2
3	3	    1	        3
4	4	    2	        1
5	5	    2	        2
6	1	    2	        3
Hotels:
id	adult	children	destination_id	page_number	page_size	price	sort_order
1   1       1           1               1           1           1.0    "one"
2   2       2           2               2           2           2.0    "two"
3   3       3           3               3           3           3.0    "three"
4   4       4           4               4           4           4.0    "four"
5   5       5           5               5           5           5.0    "five"
Hotels Ratings:
id	rating	account_id	hotels_id
1	1	    1	        1
2	2	    1	        2
3	3	    1	        3
4	4	    1	        4
5	5	    1	        5
6	5	    2	        1
7	4	    2	        2
8	3	    2	        3
9	2	    2	        4
10	1	    2	        5
Forums:
id  forum_title     forum_post      forum_post_date     forum_user
1   Title1          Post1           10/23/19            userFN
2   Title2          Post2           07/14/19            temp1FN
3   Title3          Post3           12/05/15            temp2FN
Faqs:
id	message	                        answer
1	'How do I update my email'	   'You can update your email on your profile page'
2	'Where did my good price go'	'We cannot guarantee prices as they are set by third party websites.'
3	'How do I save a trip'         'You can save trips by clicking the save button on the trip card'
Helps:
id	question	                  email                 name            completed
1	'How do I update my email'	   'test@test.com'      'Sara Jane'      0
2	'Where did my good price go'    'email@email.com'	 'John Smith'     1
3	'How do I save a trip'          'qman@test.com'     'Don Berry'      0
SET IDENTITY_INSERT dbo.account ON;		Allows for insertion of id.
SET IDENTITY_INSERT dbo.account OFF;
*/
SET IDENTITY_INSERT dbo.account ON;
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (1, N'', N'', N'admin@a.com', N'adminFN', N'adminLN', N'$2a$10$3eAGv1i.jxYE83deMi0eKOGw1ZUOE52v/3C02M27/liqSvA82xvMm', N'', N'ROLE_ADMIN', N'admin@a.com');
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (2, N'', N'', N'user@a.com', N'userFN', N'userLN', N'$2a$10$u7imJGWKWHed5/KHNmMVFOw..dUbxEdRNEtfMpWD.ja1ZwvIwY6pq', N'', N'ROLE_USER', N'user@a.com');
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (3, N'', N'', N'temp1@a.com', N'temp1FN', N'temp1LN', N'$2a$10$ycVClUktPdqTlRYcmNfMyu4JwHluHF7wNUw3gCF7nZkNAkKGVHF76', N'', N'ROLE_USER', N'temp1@a.com');
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (4, N'', N'', N'temp2@a.com', N'temp2FN', N'temp2LN', N'$2a$10$zj5VKiFPRASvNsJ7onMfV.RFaGuugHqAyLEDUx7oOz6iRf3xqielu', N'', N'ROLE_USER', N'temp2@a.com');
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (5, N'', N'', N'temp3@a.com', N'temp3FN', N'temp3LN', N'$2a$10$zCdlBfGEBwAPnTJd.OnXnOKEfHI3HmuP/FnzLZmTSW0gS8ljS5.Iy', N'', N'ROLE_USER', N'temp3@a.com');
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (6, N'', N'', N'temp4@a.com', N'temp4FN', N'temp4LN', N'$2a$10$XEQKM.H9pyNS5VOLSm0jneDW8/UkSt0mRuEN095q6uXB3wmF.rPSG', N'', N'ROLE_USER', N'temp4@a.com');
INSERT INTO dbo.account (id, banner_image, description, email, first_name, last_name, password, profile_image, role, username) VALUES (7, N'', N'', N'temp5@a.com', N'temp5FN', N'temp5LN', N'$2a$10$j3ObZ7fGnLtoWcvliE5zP.8j8z6h1xldAVFD4h84kOQ4PQY6NhlBq', N'', N'ROLE_USER', N'temp5@a.com');
SET IDENTITY_INSERT dbo.account OFF;

SET IDENTITY_INSERT dbo.events ON;
INSERT INTO dbo.events (id, date, image, name, website) VALUES (1, N'10/16/2020', null, N'event1', N'event1.com');
INSERT INTO dbo.events (id, date, image, name, website) VALUES (2, N'10/20/2020', null, N'event2', N'event2.com');
INSERT INTO dbo.events (id, date, image, name, website) VALUES (3, N'11/1/2020', null, N'event3', N'event3.com');
SET IDENTITY_INSERT dbo.events OFF;

SET IDENTITY_INSERT dbo.events_ratings ON;
INSERT INTO Trippy.dbo.events_ratings (id, rating, account_id, event_id) VALUES (1, 1, 1, 1);
INSERT INTO Trippy.dbo.events_ratings (id, rating, account_id, event_id) VALUES (2, 2, 1, 2);
INSERT INTO Trippy.dbo.events_ratings (id, rating, account_id, event_id) VALUES (3, 3, 1, 3);
INSERT INTO Trippy.dbo.events_ratings (id, rating, account_id, event_id) VALUES (4, 4, 2, 1);
INSERT INTO Trippy.dbo.events_ratings (id, rating, account_id, event_id) VALUES (5, 5, 2, 2);
INSERT INTO Trippy.dbo.events_ratings (id, rating, account_id, event_id) VALUES (6, 1, 2, 3);
SET IDENTITY_INSERT dbo.events_ratings OFF;

SET IDENTITY_INSERT dbo.hotels ON;
INSERT INTO Trippy.dbo.hotels (id, adult, children, destination_id, page_number, page_size, price, sort_order) VALUES (1, 1, 1, 1, 1, 1, 1, N'1');
INSERT INTO Trippy.dbo.hotels (id, adult, children, destination_id, page_number, page_size, price, sort_order) VALUES (2, 2, 2, 2, 2, 2, 2, N'2');
INSERT INTO Trippy.dbo.hotels (id, adult, children, destination_id, page_number, page_size, price, sort_order) VALUES (3, 3, 3, 3, 3, 3, 3, N'3');
INSERT INTO Trippy.dbo.hotels (id, adult, children, destination_id, page_number, page_size, price, sort_order) VALUES (4, 4, 4, 4, 4, 4, 4, N'4');
INSERT INTO Trippy.dbo.hotels (id, adult, children, destination_id, page_number, page_size, price, sort_order) VALUES (5, 5, 5, 5, 5, 5, 5, N'5');
SET IDENTITY_INSERT dbo.hotels OFF;

SET IDENTITY_INSERT dbo.hotels_ratings ON;
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (1, 1, 1, 1);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (2, 2, 1, 2);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (3, 3, 1, 3);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (4, 4, 1, 4);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (5, 5, 1, 5);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (6, 5, 2, 1);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (7, 4, 2, 2);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (8, 3, 2, 3);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (9, 2, 2, 4);
INSERT INTO Trippy.dbo.hotels_ratings (id, rating, account_id, hotels_id) VALUES (10, 1, 2, 5);
SET IDENTITY_INSERT dbo.hotels_ratings OFF;

SET IDENTITY_INSERT dbo.faq ON;
INSERT INTO Trippy.dbo.faq (id, message, answer) VALUES (1, 'How do I update my email', 'You can update your email on your profile page');
INSERT INTO Trippy.dbo.faq (id, message, answer) VALUES (2, 'Where did my good price go', 'We cannot guarantee prices as they are set by third party websites.');
INSERT INTO Trippy.dbo.faq (id, message, answer) VALUES (3, 'How do I save a trip', 'You can save trips by clicking the save button on the trip card');

SET IDENTITY_INSERT dbo.faq OFF;

SET IDENTITY_INSERT dbo.forums ON;
INSERT INTO dbo.forums (id, forum_title, forum_post, forum_post_date, forum_user) VALUES (1, N'Title1', N'Post1', N'10/23/19', N'userFN');
INSERT INTO dbo.forums (id, forum_title, forum_post, forum_post_date, forum_user) VALUES (2, N'Title2', N'Post2', N'07/14/19', N'temp1FN');
INSERT INTO dbo.forums (id, forum_title, forum_post, forum_post_date, forum_user) VALUES (3, N'Title3', N'Post3', N'12/05/15', N'temp2FN');
SET IDENTITY_INSERT dbo.forums OFF;

SET IDENTITY_INSERT dbo.help ON;
INSERT INTO Trippy.dbo.help (id, question, name, email, completed) VALUES (1, 'How do I update my email', 'test@test.com','Sara Jane', 0 );
INSERT INTO Trippy.dbo.help (id, question, name, email, completed) VALUES (2, 'Where did my good price go', 'email@email.com', 'John Smith', 1);
INSERT INTO Trippy.dbo.help (id, question, name, email, completed) VALUES (3, 'How do I save a trip', 'qman@test.com', 'Don Berry', 0  );

SET IDENTITY_INSERT dbo.help OFF;
