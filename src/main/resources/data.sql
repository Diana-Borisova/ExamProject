-- Insert data into the Nutrition table
INSERT INTO food_planner.nutritions (food_type_id, kcal, protein, fat, carbs) VALUES
                                                                     (2, 52, 0.3, 0.2, 14),
                                                                     (4, 165, 31, 3.6, 3.6),
                                                                     (1, 55, 3.7, 0.6, 11),
                                                                     (3, 111, 2.6, 0.9, 23),
                                                                     (3, 884, 0, 100, 0),
                                                                     (7, 546, 5.5, 31, 61),
                                                                     (1, 87, 1.9, 0.1, 20.1),
                                                                     (7, 0, 0, 0, 0),
                                                                     (5, 121, 17, 5.4, 0),
                                                                     (6, 149, 8, 8, 12);

-- Insert data into the FoodProduct table
INSERT INTO food_planner.food_products (name, food_type, id) VALUES
                                                               ('Apple', 2, 1),
                                                               ('Chicken Breast', 4, 2),
                                                               ('Broccoli', 1, 3),
                                                               ('Brown Rice', 3, 4),
                                                               ('Olive Oil', 3, 5),
                                                               ('Chocolate', 7, 6),
                                                               ('Potato', 7, 7),
                                                               ('Water', 7, 8),
                                                               ('Salmon', 5, 9),
                                                               ('Whole milk', 6, 10);




