CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NULL,
    username   VARCHAR(20) NULL,
    chat_id    INT         NOT NULL,
    created    TIMESTAMP   NOT NULL,
    modified   TIMESTAMP   NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS route
(
    id          SERIAL,
    color       VARCHAR(20)  NOT NULL,
    description VARCHAR(250) NULL,
    count       INT          NOT NULL,
    created     TIMESTAMP    NOT NULL,
    modified    TIMESTAMP    NOT NULL,
    user_id     INT          NOT NULL,
    CONSTRAINT pk_route PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);
