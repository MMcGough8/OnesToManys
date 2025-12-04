-- Synthetic Data for Sports Equipment System
-- Brands and their products with realistic sports equipment
-- NO COUNTRY FIELD

-- Insert Brands (Master records)
INSERT INTO brands (name, founding_year, logo_url, website, description, is_active) VALUES
('Rawlings', 1887, 'https://example.com/rawlings-logo.png', 'www.rawlings.com', 'Leading manufacturer of baseball equipment and sporting goods', TRUE),
('Wilson', 1913, 'https://example.com/wilson-logo.png', 'www.wilson.com', 'Premium sports equipment for baseball, tennis, football and more', TRUE),
('Nike', 1964, 'https://example.com/nike-logo.png', 'www.nike.com', 'Global leader in athletic footwear and apparel', TRUE),
('Adidas', 1949, 'https://example.com/adidas-logo.png', 'www.adidas.com', 'Iconic sports brand for performance gear across all sports', TRUE),
('Mizuno', 1906, 'https://example.com/mizuno-logo.png', 'www.mizuno.com', 'High-quality equipment for baseball, running, and golf', TRUE),
('Easton', 1922, 'https://example.com/easton-logo.png', 'www.easton.com', 'Innovation in baseball and softball equipment', TRUE),
('Spalding', 1876, 'https://example.com/spalding-logo.png', 'www.spalding.com', 'Official basketball of the NBA', TRUE),
('Under Armour', 1996, 'https://example.com/ua-logo.png', 'www.underarmour.com', 'Performance apparel and footwear', TRUE),
('New Balance', 1906, 'https://example.com/nb-logo.png', 'www.newbalance.com', 'Athletic shoes and apparel with superior fit', TRUE),
('Louisville Slugger', 1884, 'https://example.com/slugger-logo.png', 'www.slugger.com', 'Legendary manufacturer of baseball bats', TRUE);

-- Insert Products for Rawlings (brand_id = 1)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(1, 'Pro Preferred PROS205-2CBM', 'RAW-PROS205-2CBM', 359.99, 'Baseball', 'Glove', '11.75 inch infield glove with camel and black leather', 'https://example.com/pros205.jpg', '2023-01-15', 45, TRUE),
(1, 'Heart of the Hide PRO314-2B', 'RAW-PRO314-2B', 379.99, 'Baseball', 'Glove', '11.5 inch infield glove premium steer hide leather', 'https://example.com/pro314.jpg', '2022-11-20', 32, TRUE),
(1, 'Official MLB Baseball ROMLB', 'RAW-ROMLB', 24.99, 'Baseball', 'Ball', 'Official Major League Baseball game ball', 'https://example.com/mlb-ball.jpg', '2023-03-01', 500, TRUE),
(1, 'R9 Series R9205-6BG', 'RAW-R9205-6BG', 99.99, 'Baseball', 'Glove', '11.75 inch youth training glove', 'https://example.com/r9205.jpg', '2023-02-10', 120, TRUE),
(1, 'Mach EXT Batting Helmet', 'RAW-MACH-EXT', 89.99, 'Baseball', 'Helmet', 'Advanced protection batting helmet with jaw guard', 'https://example.com/mach-helmet.jpg', '2023-04-05', 75, TRUE);

-- Insert Products for Wilson (brand_id = 2)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(2, 'A2000 1787 Glove', 'WIL-A2000-1787', 369.99, 'Baseball', 'Glove', '11.75 inch Pro Stock Select leather infield glove', 'https://example.com/a2000.jpg', '2023-01-10', 55, TRUE),
(2, 'Pro Staff 97 V13', 'WIL-PS97-V13', 249.99, 'Tennis', 'Racket', 'Professional level tennis racket 97 sq inch head', 'https://example.com/ps97.jpg', '2022-10-15', 40, TRUE),
(2, 'NFL Duke Game Ball', 'WIL-NFL-DUKE', 159.99, 'Football', 'Ball', 'Official NFL game football', 'https://example.com/nfl-duke.jpg', '2023-09-01', 200, TRUE),
(2, 'Evolution Indoor Basketball', 'WIL-EVO-BBALL', 74.99, 'Basketball', 'Ball', 'Premium indoor game basketball', 'https://example.com/evolution.jpg', '2023-05-20', 150, TRUE),
(2, 'Clash 100 V2', 'WIL-CLASH-100', 229.99, 'Tennis', 'Racket', 'Flexible tennis racket with controlled power', 'https://example.com/clash100.jpg', '2023-03-12', 35, TRUE);

-- Insert Products for Nike (brand_id = 3)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(3, 'Air Zoom Pegasus 40', 'NIKE-PEG-40', 139.99, 'Running', 'Shoes', 'Premium running shoes with React foam', 'https://example.com/pegasus40.jpg', '2023-06-01', 300, TRUE),
(3, 'LeBron 21', 'NIKE-LBJ-21', 179.99, 'Basketball', 'Shoes', 'Signature basketball shoes with Zoom Air', 'https://example.com/lebron21.jpg', '2023-10-15', 250, TRUE),
(3, 'Mercurial Vapor 15', 'NIKE-MERC-15', 249.99, 'Soccer', 'Cleats', 'Elite soccer cleats for speed', 'https://example.com/mercurial.jpg', '2023-08-10', 180, TRUE),
(3, 'Alpha Huarache Elite 4', 'NIKE-ALP-HUR4', 119.99, 'Baseball', 'Cleats', 'Metal baseball cleats for optimal traction', 'https://example.com/alpha-huarache.jpg', '2023-03-15', 140, TRUE),
(3, 'Tiempo Legend 10', 'NIKE-TIEMPO-10', 234.99, 'Soccer', 'Cleats', 'Classic leather soccer cleats with premium touch', 'https://example.com/tiempo.jpg', '2023-07-20', 95, TRUE);

-- Insert Products for Adidas (brand_id = 4)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(4, 'Ultraboost 23', 'ADI-UB-23', 189.99, 'Running', 'Shoes', 'Energy return running shoes with Boost technology', 'https://example.com/ultraboost.jpg', '2023-07-01', 280, TRUE),
(4, 'Predator Accuracy.1', 'ADI-PRED-ACC1', 269.99, 'Soccer', 'Cleats', 'Control and precision soccer cleats', 'https://example.com/predator.jpg', '2023-09-05', 160, TRUE),
(4, 'Pro Madness Basketball', 'ADI-MADNESS', 89.99, 'Basketball', 'Ball', 'Professional indoor/outdoor basketball', 'https://example.com/madness.jpg', '2023-04-20', 220, TRUE),
(4, 'Copa Pure.1', 'ADI-COPA-PURE', 279.99, 'Soccer', 'Cleats', 'Premium leather soccer boots for touch and control', 'https://example.com/copa-pure.jpg', '2023-06-15', 75, TRUE),
(4, 'Adizero Boston 12', 'ADI-BOSTON-12', 149.99, 'Running', 'Shoes', 'Lightweight running shoes for speed training', 'https://example.com/boston12.jpg', '2023-05-10', 200, TRUE);

-- Insert Products for Mizuno (brand_id = 5)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(5, 'Pro Limited GMP700BK', 'MIZ-GMP700BK', 449.99, 'Baseball', 'Glove', '12.75 inch premium Japanese kip leather glove', 'https://example.com/pro-limited.jpg', '2023-02-01', 25, TRUE),
(5, 'Wave Rider 27', 'MIZ-WR-27', 139.99, 'Running', 'Shoes', 'Cushioned running shoes with Wave Plate technology', 'https://example.com/wave-rider.jpg', '2023-05-15', 180, TRUE),
(5, 'JPX923 Hot Metal', 'MIZ-JPX923', 129.99, 'Golf', 'Club', 'Forged iron with enhanced ball speed', 'https://example.com/jpx923.jpg', '2023-03-20', 95, TRUE),
(5, 'Morelia Neo IV', 'MIZ-MORELIA-4', 219.99, 'Soccer', 'Cleats', 'Ultra-lightweight kangaroo leather soccer cleats', 'https://example.com/morelia.jpg', '2023-08-05', 60, TRUE),
(5, 'Wave Inspire 19', 'MIZ-INSPIRE-19', 134.99, 'Running', 'Shoes', 'Stability running shoes for overpronators', 'https://example.com/inspire19.jpg', '2023-04-25', 150, TRUE);

-- Insert Products for Easton (brand_id = 6)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(6, 'Ghost Double Barrel', 'EAS-GHOST-DB', 399.99, 'Baseball', 'Bat', 'USSSA certified composite bat with double barrel design', 'https://example.com/ghost.jpg', '2023-01-20', 85, TRUE),
(6, 'Maxum 360 BBCOR', 'EAS-MAX-360', 449.99, 'Baseball', 'Bat', 'One-piece carbon composite bat for college/high school', 'https://example.com/maxum.jpg', '2023-02-15', 70, TRUE),
(6, 'Rival+ Catcher Set', 'EAS-RIV-CATCH', 179.99, 'Baseball', 'Protective Gear', 'Complete catchers gear set with helmet, chest protector, shin guards', 'https://example.com/rival-catch.jpg', '2023-04-01', 60, TRUE),
(6, 'ADV 360 USA Bat', 'EAS-ADV-360', 349.99, 'Baseball', 'Bat', 'Youth USA baseball bat with advanced composite design', 'https://example.com/adv360.jpg', '2023-03-10', 90, TRUE),
(6, 'Elite X Catcher Mitt', 'EAS-ELITE-X-CM', 199.99, 'Baseball', 'Glove', '34 inch professional catchers mitt', 'https://example.com/elite-x.jpg', '2023-02-28', 45, TRUE);

-- Insert Products for Spalding (brand_id = 7)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(7, 'NBA Official Game Ball', 'SPA-NBA-OFFICIAL', 169.99, 'Basketball', 'Ball', 'Official game ball of the NBA', 'https://example.com/nba-official.jpg', '2023-10-01', 300, TRUE),
(7, 'TF-1000 Legacy', 'SPA-TF1000', 64.99, 'Basketball', 'Ball', 'Premium composite indoor basketball', 'https://example.com/tf1000.jpg', '2023-06-15', 250, TRUE),
(7, 'Portable Basketball System', 'SPA-PORT-SYS', 399.99, 'Basketball', 'Hoop', '54 inch backboard portable basketball system', 'https://example.com/portable-hoop.jpg', '2023-05-01', 45, TRUE),
(7, 'Precision Indoor Basketball', 'SPA-PRECISION', 54.99, 'Basketball', 'Ball', 'High-quality indoor game basketball', 'https://example.com/precision.jpg', '2023-07-10', 180, TRUE),
(7, 'Rookie Gear Youth Basketball', 'SPA-ROOKIE-27', 24.99, 'Basketball', 'Ball', 'Size 5 youth basketball for beginners', 'https://example.com/rookie.jpg', '2023-08-20', 400, TRUE);

-- Insert Products for Under Armour (brand_id = 8)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(8, 'Curry Flow 11', 'UA-CURRY-11', 159.99, 'Basketball', 'Shoes', 'Stephen Curry signature basketball shoes', 'https://example.com/curry11.jpg', '2023-11-01', 200, TRUE),
(8, 'HOVR Phantom 3', 'UA-HOV-PH3', 149.99, 'Running', 'Shoes', 'Connected running shoes with HOVR cushioning', 'https://example.com/hovr-phantom.jpg', '2023-07-10', 175, TRUE),
(8, 'Yard Turf Baseball Trainer', 'UA-YARD-TURF', 79.99, 'Baseball', 'Shoes', 'Turf training shoes for baseball practice', 'https://example.com/yard-turf.jpg', '2023-03-25', 130, TRUE),
(8, 'Harper 8 Mid RM', 'UA-HARPER-8', 124.99, 'Baseball', 'Cleats', 'Bryce Harper signature metal baseball cleats', 'https://example.com/harper8.jpg', '2023-04-15', 95, TRUE),
(8, 'Flow Velociti Elite 2', 'UA-FLOW-VEL2', 169.99, 'Running', 'Shoes', 'Elite racing shoes for speed workouts', 'https://example.com/flow-vel.jpg', '2023-09-05', 110, TRUE);

-- Insert Products for New Balance (brand_id = 9)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(9, 'Fresh Foam X 1080v13', 'NB-1080V13', 164.99, 'Running', 'Shoes', 'Max cushioned running shoes', 'https://example.com/1080v13.jpg', '2023-08-15', 220, TRUE),
(9, '4040v6 Metal Cleats', 'NB-4040V6', 134.99, 'Baseball', 'Cleats', 'Professional level metal baseball cleats', 'https://example.com/4040v6.jpg', '2023-04-10', 165, TRUE),
(9, 'FuelCell 4040v6 Turf', 'NB-FC4040-TURF', 119.99, 'Baseball', 'Shoes', 'Turf baseball training shoes', 'https://example.com/fc4040-turf.jpg', '2023-05-05', 145, TRUE),
(9, 'FuelCell SuperComp Elite v4', 'NB-SC-ELITE-V4', 274.99, 'Running', 'Shoes', 'Carbon-plated racing shoes for elite performance', 'https://example.com/sc-elite.jpg', '2023-06-20', 85, TRUE),
(9, 'Fresh Foam X More v5', 'NB-MORE-V5', 174.99, 'Running', 'Shoes', 'Maximum cushioning for long distance running', 'https://example.com/more-v5.jpg', '2023-07-25', 130, TRUE);

-- Insert Products for Louisville Slugger (brand_id = 10)
INSERT INTO products (brand_id, name, sku, price, sport_category, product_type, description, image_url, release_date, stock_quantity, is_available) VALUES
(10, 'Prime 919 BBCOR', 'LS-P919-BBCOR', 399.99, 'Baseball', 'Bat', '3-piece composite bat with VCX connection', 'https://example.com/prime919.jpg', '2023-01-25', 90, TRUE),
(10, 'Meta Prime BBCOR', 'LS-META-PRIME', 449.99, 'Baseball', 'Bat', 'EKO composite barrel with premium performance', 'https://example.com/meta-prime.jpg', '2023-02-20', 75, TRUE),
(10, 'MLB Prime Maple C271', 'LS-PRIME-C271', 119.99, 'Baseball', 'Bat', 'Pro-grade maple wood bat', 'https://example.com/maple-c271.jpg', '2023-03-10', 150, TRUE),
(10, 'Series 7 Batting Gloves', 'LS-S7-GLOVES', 49.99, 'Baseball', 'Gloves', 'Premium leather batting gloves', 'https://example.com/s7-gloves.jpg', '2023-04-15', 200, TRUE),
(10, 'Select 719 USA Bat', 'LS-SEL-719', 329.99, 'Baseball', 'Bat', 'Youth USA baseball bat with balanced swing weight', 'https://example.com/select719.jpg', '2023-02-05', 110, TRUE),
(10, 'Genuine Ash M110', 'LS-ASH-M110', 89.99, 'Baseball', 'Bat', 'Classic ash wood bat with natural feel', 'https://example.com/ash-m110.jpg', '2023-03-28', 180, TRUE);
