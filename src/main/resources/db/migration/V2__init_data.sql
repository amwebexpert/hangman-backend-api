-- Categories
INSERT INTO category (uuid, lang_code, name)
VALUES
     (random_uuid(), 'fr', 'animaux'),
     (random_uuid(), 'fr', 'transport');

-- Text to guess
INSERT INTO text (uuid, category_id, original, normalized)
VALUES
     (random_uuid(), 1, 'Chien', 'CHIEN'),
     (random_uuid(), 1, 'Chat', 'CHAT'),
     (random_uuid(), 1, 'Poisson', 'POISSON'),
     (random_uuid(), 1, 'Reptile', 'REPTILE'),
     (random_uuid(), 1, 'Cheval', 'CHEVAL');
INSERT INTO text (uuid, category_id, original, normalized)
VALUES
     (random_uuid(), 2, 'Train', 'TRAIN'),
     (random_uuid(), 2, 'Automobile', 'AUTOMOBILE'),
     (random_uuid(), 2, 'Bicyclette', 'BICYCLETTE'),
     (random_uuid(), 2, 'Avion', 'AVION'),
     (random_uuid(), 2, 'Moto', 'MOTO'),
     (random_uuid(), 2, 'Tricycle', 'TRICYCLE');
