-- Grant proper privileges to music_user
-- Create both localhost and % users for compatibility
CREATE USER IF NOT EXISTS 'music_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'music_password';
GRANT ALL PRIVILEGES ON music_sale_db.* TO 'music_user'@'localhost';
GRANT ALL PRIVILEGES ON music_sale_db.* TO 'music_user'@'%';

FLUSH PRIVILEGES;
