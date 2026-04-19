INSERT INTO book (code, title, unit_price)
VALUES ('CLEAN_CODE', 'Clean Code (Robert Martin, 2008)'),
       ('THE_CLEAN_CODER', 'The Clean Coder (Robert Martin, 2011)'),
       ('CLEAN_ARCHITECTURE', 'Clean Architecture (Robert Martin, 2017)'),
       ('TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE', 'Test Driven Development by Example (Kent Beck, 2003)'),
       ('WORKING_EFFECTIVELY_WITH_LEGACY_CODE', 'Working Effectively With Legacy Code (Michael Feathers, 2004)');

INSERT INTO discount (set_size, discount_rate)
VALUES (1, 0.00),
       (2, 0.05),
       (3, 0.10),
       (4, 0.20),
       (5, 0.25);