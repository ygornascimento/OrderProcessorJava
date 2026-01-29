CREATE TABLE IF NOT EXISTS orders (
    id CHAR(36) PRIMARY KEY,
    customer_name VARCHAR(200) NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    order_date DATETIME(6) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
    );
