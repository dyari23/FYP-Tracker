CREATE DATABASE IF NOT EXISTS fyp_tracker;
USE fyp_tracker;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS entity_records (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(150) NOT NULL,
  description VARCHAR(500),
  status VARCHAR(30) NOT NULL DEFAULT 'New',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS projects (
  id INT AUTO_INCREMENT PRIMARY KEY,
  project_title VARCHAR(150) NOT NULL,
  supervisor_name VARCHAR(120),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS progress (
  id INT AUTO_INCREMENT PRIMARY KEY,
  entity_id INT,
  progress_percent INT DEFAULT 0,
  note VARCHAR(300),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (entity_id) REFERENCES entity_records(id) ON DELETE SET NULL
);

INSERT IGNORE INTO users(full_name, email, password)
VALUES ('Admin User', 'admin@test.com', '123456');

INSERT INTO entity_records(title, description, status) VALUES
('Proposal Submitted', 'Initial proposal uploaded for review', 'In Progress'),
('Literature Review', 'Collecting and summarizing related work', 'New'),
('Implementation', 'Building the web application modules', 'New');
