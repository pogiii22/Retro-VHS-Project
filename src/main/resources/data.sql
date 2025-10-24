-- =====================
-- USERS
-- =====================
INSERT INTO app_user (id, name, email, to_pay) VALUES
(1, 'Ana Kovač', 'ana.kovac@example.com', 0),
(2, 'Ivan Horvat', 'ivan.horvat@example.com', 0),
(3, 'Maja Babić', 'maja.babic@example.com', 0),
(4, 'Luka Marić', 'luka.maric@example.com', 0);

-- =====================
-- VHS TAPES
-- =====================
INSERT INTO vhs (id, title, genre, release_year, rented) VALUES
(1, 'The Matrix', 'Sci-Fi', 1999, TRUE),
(2, 'Back to the Future', 'Adventure', 1985, FALSE),
(3, 'Titanic', 'Drama', 1997, FALSE),
(4, 'Jurassic Park', 'Action', 1993, TRUE),
(5, 'Pulp Fiction', 'Crime', 1994, TRUE),
(6, 'The Lion King', 'Animation', 1994, FALSE),
(7, 'The Godfather', 'Crime', 1972, FALSE),
(8, 'The Shawshank Redemption', 'Drama', 1994, TRUE),
(9, 'Terminator 2: Judgment Day', 'Action', 1991, TRUE),
(10, 'Forrest Gump', 'Drama', 1994, FALSE);

-- =====================
-- RENTALS
-- =====================
-- 🔹 VRAĆENI NAJMOVI (neki na vrijeme, neki s kašnjenjem)
-- dueDate = rentalDate + 30 dana
-- lateFee = daysLate * 0.5
INSERT INTO rental (id, user_id, vhs_id, rental_date, due_date, return_date, days_late, late_fee) VALUES
(1, 1, 2, DATE '2025-09-01', DATE '2025-10-01', DATE '2025-10-01', 0, 0.0),   -- vraćeno na vrijeme
(2, 2, 3, DATE '2025-09-05', DATE '2025-10-05', DATE '2025-10-09', 4, 2.0),   -- kasnio 4 dana
(3, 3, 6, DATE '2025-09-10', DATE '2025-10-10', DATE '2025-10-12', 2, 1.0),   -- kasnio 2 dana
(4, 4, 7, DATE '2025-09-15', DATE '2025-10-15', DATE '2025-10-25', 10, 5.0);  -- kasnio 10 dana

-- 🔹 AKTIVNI NAJMOVI (još nisu vraćeni)
-- dueDate = rentalDate + 30 dana
INSERT INTO rental (id, user_id, vhs_id, rental_date, due_date, return_date, days_late, late_fee) VALUES
(5, 1, 1, DATE '2025-10-10', DATE '2025-11-09', NULL, NULL, NULL),            -- aktivan, još u roku
(6, 2, 4, DATE '2025-10-15', DATE '2025-11-14', NULL, NULL, NULL),            -- aktivan, još u roku
(7, 3, 5, DATE '2025-09-20', DATE '2025-10-20', NULL, NULL, NULL),            -- 🔸 aktivan, KASNI (due date prošao)
(8, 4, 8, DATE '2025-10-20', DATE '2025-11-19', NULL, NULL, NULL);            -- aktivan, još u roku

-- =====================
-- VHS STATUS UPDATE
-- =====================
UPDATE vhs SET rented = TRUE WHERE id IN (1, 4, 5, 8, 9);
UPDATE vhs SET rented = FALSE WHERE id IN (2, 3, 6, 7, 10);
