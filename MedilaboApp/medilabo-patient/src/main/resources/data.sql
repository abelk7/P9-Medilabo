INSERT INTO patients (id, adresse_postale, date_naissance, genre, nom, numero, prenom)
values (null, '1 Brookside St', CAST('1966-12-31 ' AS DATE), 'F', 'TestNone', '100-222-3333', 'Test'),
       (null, '2 High St', CAST('1945-06-24' AS DATE), 'M', 'TestBorderline', '200-333-4444', 'Test'),
       (null, '3 Club Road', CAST('2004-06-18' AS DATE), 'M', 'TestInDanger', '300-444-5555', 'Test'),
       (null, '4 Valley Dr', CAST('2004-06-18' AS DATE), 'F', 'TestEarlyOnset', '400-555-6666', 'Test');