CREATE TABLE users (
	id SERIAL NOT null,
	created_date timestamp NOT NULL,
	name varchar(255) NULL,
	updated_date timestamp NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id)
);
