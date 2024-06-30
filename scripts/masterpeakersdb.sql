CREATE DATABASE IF NOT EXISTS masterpeakers;

USE masterpeakers;

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role_id BIGINT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    image_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS purchases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    game_id BIGINT,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (game_id) REFERENCES games(id)
);

INSERT INTO roles (name) VALUES 
('ROLE_USER'),
('ROLE_ADMIN');

-- Insert admin user with NoOpPassword for simplicity but there's the hashed password option too inside project 
INSERT INTO users (username, password, email, role_id, is_active) VALUES 
('TheAdmin', '1985', 'midis007@gmail.com', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), TRUE);

INSERT INTO games (title, platform, price, image_name) VALUES 
('The Witcher 3: Wild Hunt', 'PC', 29.99, 'Witcher_3_wild_hunt.jpg'),
('God of War', 'PS4', 39.99, 'God_of_War_4_cover.jpg'),
('Halo Infinite', 'Xbox One', 59.99, 'Halo_Infinite.png'),
('Red Dead Redemption II', 'PC', 59.99, 'Red_Dead_Redemption_II.jpg'),
('GTAV', 'PC', 39.99, 'Grand_Theft_Auto_V.png'),
('Call of Duty - Black Ops', 'PS4', 29.99, 'CoD3.jpg'),
('Fortnite', 'PS5', 59.99, 'Fortnite_Save_The_World.jpg'),
('PUBG', 'PC', 49.99, 'Pubgbattlegrounds.png'),
('Mario Kart 8/Deluxe', 'PS5', 49.99, 'MarioKart8Boxart.jpg'),
('The Sims 4', 'PC', 39.99, 'Sims4_Rebrand.png'),
('Animal Crossing: New Horizons', 'PC', 29.99, 'Animal_Crossing_New_Horizons.jpg'),
('League of Legends', 'PC', 39.99, 'league-of-legends.jpg'),
('World of Warcraft', 'PS5', 49.99, 'World_of_Warcraft.png'),
('Call of Duty - BlackOps 6', 'Xbox Series X', 59.99, 'COD_BO6.jpg'),
('Age of Mythology', 'PC', 19.99, 'Age_of_Mythology_img.jpg');  
