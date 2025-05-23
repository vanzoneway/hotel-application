INSERT INTO hotel (name, description, brand, check_in, check_out)
VALUES ('Grand Plaza', 'Luxury hotel in the city center with panoramic views', 'Plaza Hotels', '14:00', '12:00'),
       ('Seaside Resort', 'Beachfront property with private beach access', 'Oceanview Resorts', '15:00', '11:00'),
       ('Mountain Lodge', 'Cozy retreat in the mountains with spa facilities', 'Alpine Hospitality', '16:00', '10:00'),
       ('Urban Suites', 'Modern business hotel with conference facilities', 'Metro Hotels', '12:00', '14:00'),
       ('Royal Gardens', 'Historic hotel with beautiful botanical gardens', 'Heritage Hotels', '13:00', '11:00'),
       ('Sunset Inn', 'Budget-friendly accommodation near the airport', 'Sunset Hospitality', '14:00', '12:00'),
       ('The Grandeur', 'Five-star luxury with butler service', 'Luxury Collection', '15:00', '12:00'),
       ('Pine Valley', 'Eco-friendly resort surrounded by nature', 'Green Resorts', '14:00', '11:00'),
       ('Central Park Hotel', 'Iconic hotel overlooking Central Park', 'Plaza Hotels', '15:00', '11:00'),
       ('Ocean Breeze', 'Beachfront hotel with ocean views', 'Oceanview Resorts', '13:00', '10:00'),
       ('Alpine Retreat', 'Mountain chalet with ski-in/ski-out access', 'Alpine Hospitality', '14:00', '10:00'),
       ('Metropolitan', 'Stylish hotel in the financial district', 'Metro Hotels', '12:00', '15:00');

INSERT INTO hotel_information (id, phone, email, house_number, street, city, country, post_code)
VALUES (1, '+1 555 123 4567', 'reservations@grandplaza.com', 123, 'Main Street', 'New York', 'USA', '10001'),
       (2, '+1 555 234 5678', 'info@seasideresort.com', 456, 'Ocean Drive', 'Miami', 'USA', '33139'),
       (3, '+41 22 908 1234', 'bookings@mountainlodge.ch', 789, 'Alpenstrasse', 'Zermatt', 'Switzerland', '3920'),
       (4, '+44 20 7123 4567', 'contact@urbansuites.co.uk', 10, 'Canary Wharf', 'London', 'UK', 'E14 5AB'),
       (5, '+33 1 42 36 10 10', 'reservations@royalgardens.fr', 1, 'Rue de Rivoli', 'Paris', 'France', '75004'),
       (6, '+1 555 345 6789', 'info@sunsetinn.com', 789, 'Airport Boulevard', 'Los Angeles', 'USA', '90045'),
       (7, '+971 4 567 8901', 'concierge@grandeur.ae', 1, 'Sheikh Zayed Road', 'Dubai', 'UAE', '12345'),
       (8, '+1 604 123 4567', 'stay@pinevalley.ca', 234, 'Forest Lane', 'Vancouver', 'Canada', 'V6B 1H6'),
       (9, '+1 212 555 7890', 'info@centralpark.com', 59, 'Central Park West', 'New York', 'USA', '10023'),
       (10, '+1 305 555 1234', 'reservations@oceanbreeze.com', 321, 'Collins Avenue', 'Miami', 'USA', '33140'),
       (11, '+41 27 967 4567', 'welcome@alpineretreat.ch', 5, 'Matterhornstrasse', 'Zermatt', 'Switzerland', '3920'),
       (12, '+44 20 7654 3210', 'bookings@metropolitan.co.uk', 25, 'Threadneedle Street', 'London', 'UK', 'EC2R 8AY');

INSERT INTO hotel_amenity (hotel_id, amenity)
VALUES (1, 'Free WiFi'),
       (1, 'Swimming Pool'),
       (1, 'Spa'),
       (1, 'Restaurant'),
       (1, '24/7 Room Service'),

       (2, 'Private Beach'),
       (2, 'Free WiFi'),
       (2, 'Water Sports'),
       (2, 'Kids Club'),
       (2, 'Beach Bar'),

       (3, 'Ski Storage'),
       (3, 'Hot Tub'),
       (3, 'Free Parking'),
       (3, 'Mountain View'),
       (3, 'Spa'),

       (4, 'Business Center'),
       (4, 'Conference Rooms'),
       (4, 'Airport Shuttle'),
       (4, 'Fitness Center'),
       (4, 'Free WiFi'),

       (5, 'Botanical Garden'),
       (5, 'Historic Tours'),
       (5, 'Luxury Spa'),
       (5, 'Fine Dining'),
       (5, 'Concierge'),

       (6, 'Free WiFi'),
       (6, 'Airport Shuttle'),
       (6, 'Free Breakfast'),
       (6, 'Parking'),

       (7, 'Butler Service'),
       (7, 'Infinity Pool'),
       (7, 'Luxury Spa'),
       (7, 'Private Beach'),
       (7, 'Helipad'),

       (8, 'Eco-friendly'),
       (8, 'Hiking Trails'),
       (8, 'Organic Restaurant'),
       (8, 'Yoga Classes'),

       (9, 'Park View'),
       (9, 'Free WiFi'),
       (9, 'Fitness Center'),
       (9, 'Concierge'),
       (9, 'Bar'),

       (10, 'Ocean View'),
       (10, 'Free WiFi'),
       (10, 'Beach Access'),
       (10, 'Pool'),
       (10, 'Water Sports'),

       (11, 'Ski-in/Ski-out'),
       (11, 'Fireplace'),
       (11, 'Sauna'),
       (11, 'Mountain View'),
       (11, 'Ski Rental'),

       (12, 'Business Center'),
       (12, 'Free WiFi'),
       (12, 'Meeting Rooms'),
       (12, 'Concierge'),
       (12, 'Bar');