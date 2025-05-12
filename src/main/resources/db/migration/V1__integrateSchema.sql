DROP TABLE IF EXISTS comments CASCADE ;
DROP TABLE IF EXISTS issues CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP EXTENSION IF EXISTS "uuid-ossp";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id       UUID         NOT NULL DEFAULT uuid_generate_v4(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE issues
(
    id       UUID         NOT NULL DEFAULT uuid_generate_v4(),
    owner_id UUID         NOT NULL,
    title    VARCHAR(255) NOT NULL,
    deadline TIMESTAMP    NULL     DEFAULT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE comments
(
    id       UUID         NOT NULL DEFAULT uuid_generate_v4(),
    body     VARCHAR(255) NOT NULL,
    issue_id UUID         NOT NULL,
    user_id  UUID         NOT NULL,


    PRIMARY KEY (id),
    FOREIGN KEY (issue_id) REFERENCES issues (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

