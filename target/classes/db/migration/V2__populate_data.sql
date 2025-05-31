-- Inserindo Integrantes (jogadores)
INSERT INTO integrante (id, franquia, nome, funcao) VALUES
  (1, 'Los Angeles Lakers', 'LeBron James', 'Small Forward'),
  (2, 'Los Angeles Lakers', 'Anthony Davis', 'Power Forward'),
  (3, 'Los Angeles Lakers', 'DAngelo Russell', 'Point Guard'),
  (4, 'Los Angeles Lakers', 'Austin Reaves', 'Shooting Guard'),
  (5, 'Los Angeles Lakers', 'Jaxson Hayes', 'Center'),

  (6, 'Golden State Warriors', 'Stephen Curry', 'Point Guard'),
  (7, 'Golden State Warriors', 'Klay Thompson', 'Shooting Guard'),
  (8, 'Golden State Warriors', 'Andrew Wiggins', 'Small Forward'),
  (9, 'Golden State Warriors', 'Draymond Green', 'Power Forward'),
  (10, 'Golden State Warriors', 'Kevon Looney', 'Center'),

  (11, 'Boston Celtics', 'Jayson Tatum', 'Small Forward'),
  (12, 'Boston Celtics', 'Jaylen Brown', 'Shooting Guard'),
  (13, 'Boston Celtics', 'Kristaps Porziņģis', 'Power Forward'),
  (14, 'Boston Celtics', 'Jrue Holiday', 'Point Guard'),
  (15, 'Boston Celtics', 'Al Horford', 'Center'),

  (16, 'Milwaukee Bucks', 'Giannis Antetokounmpo', 'Power Forward'),
  (17, 'Milwaukee Bucks', 'Damian Lillard', 'Point Guard'),
  (18, 'Milwaukee Bucks', 'Khris Middleton', 'Small Forward'),
  (19, 'Milwaukee Bucks', 'Brook Lopez', 'Center'),
  (20, 'Milwaukee Bucks', 'Malik Beasley', 'Shooting Guard');

-- Inserindo Times (datas dos jogos)
INSERT INTO time (id, data) VALUES
  (1, '2024-04-01'), -- Lakers
  (2, '2024-04-02'), -- Warriors
  (3, '2024-04-03'), -- Celtics
  (4, '2024-04-04'); -- Bucks

-- Inserindo Composição dos Times
INSERT INTO composicao_time (id, id_time, id_integrante) VALUES
  -- Lakers
  (1, 1, 1),
  (2, 1, 2),
  (3, 1, 3),
  (4, 1, 4),
  (5, 1, 5),

  -- Warriors
  (6, 2, 6),
  (7, 2, 7),
  (8, 2, 8),
  (9, 2, 9),
  (10, 2, 10),

  -- Celtics
  (11, 3, 11),
  (12, 3, 12),
  (13, 3, 13),
  (14, 3, 14),
  (15, 3, 15),

  -- Bucks
  (16, 4, 16),
  (17, 4, 17),
  (18, 4, 18),
  (19, 4, 19),
  (20, 4, 20);
