-- Categories
INSERT INTO category (uuid, lang_code, name)
VALUES
     ('580b0004-d581-49d1-9c84-bddffdb5f3fd', 'fr', 'animaux'),
     ('64d28a1e-24cf-40f7-a6f1-3a76fd534639', 'fr', 'transport');

-- Text to guess
INSERT INTO text (uuid, category_id, original, normalized)
VALUES
     ('92186b0b-f4c7-4e45-a321-1f34d9648f2f', 1, 'Chien', 'CHIEN'),
     ('c64c946b-549c-45b6-a4b8-edbca43fa7bd', 1, 'Chat', 'CHAT'),
     ('ed61c18a-d0e4-4dc7-afa6-c5f8bcf7238c', 1, 'Poisson', 'POISSON'),
     ('0eaa98db-5b07-48e4-81e7-57d2685ef547', 1, 'Reptile', 'REPTILE'),
     ('ff7c8cc1-b016-4fd8-8eae-6a081497aaaa', 1, 'Cheval', 'CHEVAL');
INSERT INTO text (uuid, category_id, original, normalized)
VALUES
     ('5b7c5eec-4e0d-49ec-9100-c052b0403d96', 2, 'Train', 'TRAIN'),
     ('503dbae8-6f0d-4a4c-8a1e-5c38d6d058d1', 2, 'Automobile', 'AUTOMOBILE'),
     ('a69ffd59-0887-4ee9-b527-e99f581718ba', 2, 'Bicyclette', 'BICYCLETTE'),
     ('981c5753-6ab2-40d9-b2b8-1a72ddbf57b6', 2, 'Avion', 'AVION'),
     ('361f0234-b418-453f-a3aa-7dfe153536f6', 2, 'Moto', 'MOTO'),
     ('b9f95d04-36f2-4e64-8405-6bc763487dd2', 2, 'Tricycle', 'TRICYCLE');
