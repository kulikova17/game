CREATE TABLE user_roles (
    id SERIAL,
    role VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (role));

CREATE  TABLE users (
    id SERIAL,
    email VARCHAR(45) NOT NULL ,
    password VARCHAR(45) NOT NULL ,
    role_id INT DEFAULT 0,
    has_car BOOLEAN DEFAULT false,
    PRIMARY KEY (id),
    UNIQUE (email),
    CONSTRAINT fkUserId FOREIGN KEY (role_id) REFERENCES user_roles (id) ON DELETE CASCADE );

CREATE  TABLE game_types (
    id SERIAL,
    type VARCHAR(45) NOT NULL,
    UNIQUE (type),
    PRIMARY KEY (id));

CREATE  TABLE games (
    id SERIAL,
    status VARCHAR(45),
    type_id INT,
    score_team1 INT,
    score_team2 INT,
    PRIMARY KEY (id),
    CONSTRAINT fkGameType FOREIGN KEY (type_id) REFERENCES game_types (id) ON DELETE SET NULL);

CREATE  TABLE user_game (
    id SERIAL,
    game_id INT,
    user_id INT NOT NULL,
    team INT,
    UNIQUE (user_id, game_id),
    PRIMARY KEY (id),
    CONSTRAINT fkUser FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fkGame FOREIGN KEY (game_id) REFERENCES games (id) ON DELETE CASCADE);


CREATE  TABLE permitted_list (
    id SERIAL,
    email VARCHAR(45) NOT NULL ,
    PRIMARY KEY (id),
    UNIQUE (email));

