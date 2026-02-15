-- ========================================
-- CONFLICT TRACKER - DATOS DE PRUEBA
-- ========================================

-- PAÍSES
INSERT INTO COUNTRY(id, name, code) VALUES (1, 'Ukraine', 'UKR');
INSERT INTO COUNTRY(id, name, code) VALUES (2, 'Russia', 'RUS');
INSERT INTO COUNTRY(id, name, code) VALUES (3, 'Israel', 'ISR');
INSERT INTO COUNTRY(id, name, code) VALUES (4, 'Palestine', 'PSE');
INSERT INTO COUNTRY(id, name, code) VALUES (5, 'United States', 'USA');
INSERT INTO COUNTRY(id, name, code) VALUES (6, 'Syria', 'SYR');
INSERT INTO COUNTRY(id, name, code) VALUES (7, 'Turkey', 'TUR');

-- CONFLICTOS
INSERT INTO CONFLICT(id, name, start_date, status, description) 
VALUES (1, 'Ukraine War', '2022-02-24', 'ACTIVE', 
        'Armed conflict between Russia and Ukraine that began in February 2022. It involves territorial disputes and geopolitical tensions between Russia and Western nations.');

INSERT INTO CONFLICT(id, name, start_date, status, description)
VALUES (2, 'Israeli-Palestinian Conflict', '1948-05-15', 'ACTIVE',
        'Long-standing conflict between Israelis and Palestinians over land, resources, and sovereignty in the region. It has involved multiple wars and peace negotiations.');

INSERT INTO CONFLICT(id, name, start_date, status, description)
VALUES (3, 'Syrian Civil War', '2011-03-15', 'FROZEN',
        'Multi-sided armed conflict that began during the Arab Spring. It involves the Syrian government, opposition forces, and various international actors.');

-- RELACIONES CONFLICT-COUNTRIES (Países donde ocurre el conflicto)
INSERT INTO CONFLICT_COUNTRIES(conflict_id, countries_id) VALUES (1, 1); -- Ukraine War - Ukraine
INSERT INTO CONFLICT_COUNTRIES(conflict_id, countries_id) VALUES (1, 2); -- Ukraine War - Russia

INSERT INTO CONFLICT_COUNTRIES(conflict_id, countries_id) VALUES (2, 3); -- Israeli-Palestinian - Israel
INSERT INTO CONFLICT_COUNTRIES(conflict_id, countries_id) VALUES (2, 4); -- Israeli-Palestinian - Palestine

INSERT INTO CONFLICT_COUNTRIES(conflict_id, countries_id) VALUES (3, 6); -- Syrian War - Syria

-- FACCIONES
INSERT INTO FACTION(id, name, conflict_id) VALUES (1, 'Ukrainian Armed Forces', 1);
INSERT INTO FACTION(id, name, conflict_id) VALUES (2, 'Russian Armed Forces', 1);
INSERT INTO FACTION(id, name, conflict_id) VALUES (3, 'Israeli Defense Forces', 2);
INSERT INTO FACTION(id, name, conflict_id) VALUES (4, 'Hamas', 2);
INSERT INTO FACTION(id, name, conflict_id) VALUES (5, 'Syrian Government Forces', 3);
INSERT INTO FACTION(id, name, conflict_id) VALUES (6, 'Syrian Opposition', 3);

-- RELACIONES FACTION-SUPPORTERS (Países que apoyan cada facción)
INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (1, 1); -- Ukrainian Forces - Ukraine
INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (1, 5); -- Ukrainian Forces - USA

INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (2, 2); -- Russian Forces - Russia

INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (3, 3); -- IDF - Israel
INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (3, 5); -- IDF - USA

INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (4, 4); -- Hamas - Palestine

INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (5, 6); -- Syrian Gov - Syria
INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (5, 2); -- Syrian Gov - Russia

INSERT INTO FACTION_SUPPORTERS(faction_id, supporters_id) VALUES (6, 7); -- Syrian Opposition - Turkey

-- EVENTOS
INSERT INTO EVENT(id, event_date, location, description, conflict_id)
VALUES (1, '2022-02-24', 'Kyiv, Ukraine', 
        'Russian forces began a large-scale invasion of Ukraine', 1);

INSERT INTO EVENT(id, event_date, location, description, conflict_id)
VALUES (2, '2022-04-03', 'Bucha, Ukraine',
        'Discovery of civilian casualties in Bucha after Russian withdrawal', 1);

INSERT INTO EVENT(id, event_date, location, description, conflict_id)
VALUES (3, '2023-06-06', 'Kakhovka, Ukraine',
        'Kakhovka Dam destruction causing massive flooding', 1);

INSERT INTO EVENT(id, event_date, location, description, conflict_id)
VALUES (4, '2023-10-07', 'Gaza Strip',
        'Major escalation in violence in the Gaza region', 2);

INSERT INTO EVENT(id, event_date, location, description, conflict_id)
VALUES (5, '2011-03-15', 'Daraa, Syria',
        'Beginning of protests in Syria as part of the Arab Spring', 3);

INSERT INTO EVENT(id, event_date, location, description, conflict_id)
VALUES (6, '2018-04-07', 'Douma, Syria',
        'Alleged chemical weapons attack in Douma', 3);
