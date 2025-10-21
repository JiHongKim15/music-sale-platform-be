INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES 
('Guitar', 'PRODUCT', NULL, '/1', 0, 1, NOW(), 'system', NOW(), 'system'),
('Piano', 'PRODUCT', NULL, '/2', 0, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
VALUES 
('Fender Stratocaster', 1, 'Fender', '{}', NOW(), 'system', NOW(), 'system'),
('Yamaha U1 Upright Piano', 2, 'Yamaha', '{}', NOW(), 'system', NOW(), 'system');

INSERT INTO product_item (catalog_id, price, `condition`, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
VALUES 
(1, 1500000, 'NEW', 10, 'ON_SALE', NOW(), 'system', NOW(), 'system'),
(2, 5000000, 'NEW', 5, 'ON_SALE', NOW(), 'system', NOW(), 'system');

INSERT INTO product_image (product_item_id, image_url, is_thumbnail, image_order, created_at, created_by, updated_at, updated_by) 
VALUES 
(1, 'https://images.unsplash.com/photo-1564186763535-ebb21ef5277f', 1, 0, NOW(), 'system', NOW(), 'system'),
(1, 'https://images.unsplash.com/photo-1510915228340-29c85a43dcfe', 0, 1, NOW(), 'system', NOW(), 'system'),
(2, 'https://images.unsplash.com/photo-1520523839897-bd0b52f945a0', 1, 0, NOW(), 'system', NOW(), 'system');
