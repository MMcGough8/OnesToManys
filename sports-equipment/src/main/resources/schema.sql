DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS brands;

CREATE TABLE brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    founding_year INT,
    logo_url VARCHAR(500),
    website VARCHAR(255),
    description VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    CONSTTRAINT chk_founding_year CHECK (founding_year >= 1800 AND founding_year <= YEAR(CURRENT_DATE))
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL,
    sport_category VARCHAR(100) NOT NULL,
    product_type VARCHAR(100) NOT NULL,
    description VARCHAR (1000),
    image_url VARCHAR (500),
    release_date DATE,
    stock_quantity INT DEFAULT 0,
    is_available BOOLEAN DEFAULT TRUE,
    CONSTTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCE brands(id) ON DELETE CASCADE,
    CONSTTRAINT chk_price CHECK (price >= 0),
    CONSTTRAINT chk_stock CHECK (stock_quantity >= 0)
);

CREATE INDEX idx_brand_name ON brands(name);
CREATE INDEX idx_brand_active ON brands(is_active);
CREATE INDEX idx_product_brand ON products(brand_id);
CREATE INDEX idx_product_sku ON products(sku);
CREATE INDEX idx_product_category ON products(sport_category);
CREATE INDEX idx_product_type ON products(product_type);
CREATE INDEX idx_product_available ON products(is_available);