DROP TABLE job_current;
DROP TABLE job_desired;
DROP TABLE job_listings;
DROP TABLE contact_seeking;
DROP TABLE contact_interest;
DROP TABLE my_contacts;
DROP TABLE profession;
DROP TABLE zip_code;
DROP TABLE status;
DROP TABLE interests;
DROP TABLE seeking;
DROP TABLE description;
DROP TABLE info_activities;
DROP TABLE info_location;
DROP TABLE clown_boss;
DROP TABLE clown_info;
DROP TABLE activities;
DROP TABLE location;

CREATE TABLE clown_info(
id int,
name varchar2(30),
gender varchar2(1) CHECK(gender='M' OR gender='F'),
description varchar2(30),
boss_id int,
PRIMARY KEY(id),
FOREIGN KEY(boss_id) REFERENCES clown_info(id)
);
CREATE TABLE clown_boss(
id int,
boss_id int,
PRIMARY KEY(id),
FOREIGN KEY(boss_id) REFERENCES clown_info(id)
);
CREATE TABLE location(
location_id int,
location varchar2(30),
PRIMARY KEY(location_id)
);
CREATE TABLE info_location(
id int,
location_id int,
when varchar2(30),
FOREIGN KEY(id) REFERENCES clown_info(id),
FOREIGN KEY(location_id) REFERENCES location(location_id)
);
CREATE TABLE activities(
activity_id int,
activity varchar2(30),
PRIMARY KEY(activity_id)
);
CREATE TABLE info_activities(
id int,
activity_id,
FOREIGN KEY(id) REFERENCES clown_info(id),
FOREIGN KEY(activity_id) REFERENCES activities(activity_id)
);
CREATE TABLE description(
id int,
gender varchar2(30),
description varchar2(30),
when varchar2(30),
FOREIGN KEY(id) REFERENCES clown_info(id)
);
CREATE TABLE seeking(
seeking_id int,
seeking varchar2(30),
PRIMARY KEY(seeking_id)
);
CREATE TABLE interests(
interest_id int,
interest varchar2(30),
PRIMARY KEY(interest_id)
);
CREATE TABLE status(
status_id int,
status varchar2(30),
PRIMARY KEY(status_id)
);
CREATE TABLE zip_code(
zip_code int,
city varchar2(30),
state varchar2(30),
PRIMARY KEY(zip_code)
);
CREATE TABLE profession(
prof_id int,
profession varchar2(30),
PRIMARY KEY(prof_id)
);
CREATE TABLE my_contacts(
contact_id int,
last_name varchar2(30),
first_name varchar2(30),
phone varchar2(30),
email varchar2(30),
gender varchar2(1) check(gender='M' OR gender='F'),
prof_id int,
zip_code int,
status_id int,
PRIMARY KEY(contact_id),
FOREIGN KEY(prof_id) REFERENCES profession(prof_id),
FOREIGN KEY(zip_code) REFERENCES zip_code(zip_code),
FOREIGN KEY(status_id) REFERENCES status(status_id)
);
CREATE TABLE contact_seeking(
contact_id int,
seeking_id int,
PRIMARY KEY(contact_id, seeking_id),
FOREIGN KEY(contact_id) REFERENCES my_contacts(contact_id),
FOREIGN KEY(seeking_id) REFERENCES seeking(seeking_id)
);
CREATE TABLE contact_interest(
contact_id int,
interest_id int,
PRIMARY KEY(contact_id,interest_id),
FOREIGN KEY(contact_id) REFERENCES my_contacts(contact_id),
FOREIGN KEY(interest_id) REFERENCES interests(interest_id)
);
CREATE TABLE job_current(
contact_id int,
title varchar2(30),
salary varchar2(30),
start_date varchar2(30),
PRIMARY KEY(contact_id),
FOREIGN KEY(contact_id) REFERENCES my_contacts(contact_id)
);
CREATE TABLE job_desired(
contact_id int,
title varchar2(30),
salary_low varchar2(30),
salary_high varchar2(30),
available varchar2(30),
years_exp varchar2(30),
PRIMARY KEY(contact_id),
FOREIGN KEY(contact_id) REFERENCES my_contacts(contact_id)
);
CREATE TABLE job_listings(
job_id int,
title varchar2(30),
salary varchar2(30),
zip varchar2(30),
description varchar2(30),
PRIMARY KEY(job_id)
);


SELECT zip FROM job_listings GROUP BY zip HAVING count(zip) > 3 ORDER BY zip;
--NOTE: The joins are more efficient than subqueries.

--NOTE: the NATURAL JOIN and ',' is different.
--For NATURAL JOIN try to merge the same column.
SELECT mc.first_name, mc.last_name, mc.phone, jc.title FROM job_current jc NATURAL JOIN my_contacts mc;
SELECT mc.first_name, mc.last_name, mc.phone, jc.title FROM job_current jc NATURAL JOIN my_contacts mc WHERE jc.title IN (SELECT title FROM job_listings);
SELECT mc.first_name, mc.last_name, (SELECT state FROM zip_code WHERE mc.zip_code = zip_code) AS state FROM my_contacts mc;
SELECT mc.first_name, mc.last_name, zc.state FROM my_contacts mc, zip_code zc;
SELECT mc.first_name, mc.last_name, mc.email FROM my_contacts mc WHERE EXISTS (SELECT ci.contact_id FROM contact_interest ci WHERE mc.contact_id=ci.contact_id);

--outer joins, self-joins, and unions, inner join
--You could replace INEER JOIN with ',' instead.
SELECT mc.first_name, mc.last_name, zc.state FROM my_contacts mc INNER JOIN zip_code zc ON mc.zip_code=zc.zip_code WHERE mc.first_name LIKE 'Andy%';
SELECT mc.first_name, mc.last_name, zc.state FROM my_contacts mc LEFT OUTER JOIN zip_code zc ON mc.zip_code=zc.zip_code;
SELECT mc.first_name, mc.last_name, zc.state FROM my_contacts mc RIGHT OUTER JOIN zip_code zc ON mc.zip_code=zc.zip_code;
SELECT c1.name, c2.name AS boss FROM clown_info c1 INNER JOIN clown_info c2 ON c1.boss_id=c2.id;
SELECT c1.name, c2.name AS boss FROM clown_info c1 INNER JOIN (SELECT cb.id, ci.name FROM clown_boss cb LEFT OUTER JOIN clown_info ci ON cb.boss_id=ci.id) c2 ON c1.id=c2.id;
--union can only take one ORDER BY at the end of the statement. and use the union we can get the DISTINCT answer.
SELECT title FROM job_current UNION SELECT title FROM job_desired UNION SELECT title FROM job_listings ORDER BY title;
--UNION ALL will return the never DISTINCT answer.
SELECT title FROM job_current UNION ALL SELECT title FROM job_desired UNION ALL SELECT title FROM job_listings ORDER BY title;

CREATE TABLE my_union AS SELECT title FROM job_current UNION SELECT title FROM job_desired UNION SELECT title FROM job_listings;
SELECT * FROM my_union;
DROP TABLE my_union;

--INTERSECT and EXCEPT
--INTERSECT returns only those columns that are in the first query and also in the second query.
SELECT title FROM job_current INTERSECT SELECT title FROM job_desired;
--EXCEPT returns only those columns taht are in the first query, but not in the second query.
--NOTE: there isn't 'EXCEPT' in oracle.We could use MINUS insted of EXCEPT.
SELECT title FROM job_current MINUS SELECT title FROM job_desired;

--SELECT mc.first_name, mc.last_name, mc.phone, jc.title FROM job_current jc NATURAL JOIN my_contacts mc WHERE jc.title IN (SELECT title FROM job_listings);
SELECT mc.first_name, mc.last_name, mc.phone, jc.title FROM job_current jc NATURAL JOIN my_contacts mc INNER JOIN job_listings jl ON jc.title = jl.title;

--Show create tables scription
SELECT dbms_metadata.get_ddl('TABLE', upper('job_current')) FROM dual;

DROP VIEW web_designers;
DROP VIEW tech_writer_jobs;

CREATE OR REPLACE VIEW web_designers AS SELECT mc.first_name, mc.last_name, mc.phone, mc.email FROM my_contacts mc NATURAL JOIN job_desired jd WHERE jd.title = 'Web Designer';
CREATE OR REPLACE VIEW tech_writer_jobs AS SELECT title, salary, description, zip FROM job_listings WHERE title='Techinal Writer';

--You can do more than just SELECT information form your tables with a view.
--In some instances, you can UPDATE, INSERT and DELETE your data as well.
SELECT * FROM web_designers;

--START TRANSACTION;
SAVEPOINT a;
ROLLBACK;
COMMIT;

