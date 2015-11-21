CREATE TABLE resources (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  url varchar(255) NOT NULL,
  active boolean NOT NULL,
  check_interval INT UNSIGNED NOT NULL,
  created_at DATETIME NOT NULL,
  UNIQUE(name),
  UNIQUE(url)
);

CREATE TABLE resource_observers (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  resource_id INT UNSIGNED NOT NULL,
  email varchar(255) NOT NULL,
  UNIQUE(resource_id, email),
  CONSTRAINT FOREIGN KEY resource_observers_resource_id_fkey (resource_id) REFERENCES resources(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE snapshots (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  hash CHAR(64) NOT NULL,
  resource TEXT NOT NULL,
  UNIQUE(hash)
);

CREATE TABLE scans (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  resource_id INT UNSIGNED NOT NULL,
  snapshot_id INT UNSIGNED,
  created_at DATETIME NOT NULL,
  CONSTRAINT FOREIGN KEY scans_resource_id_fkey (resource_id) REFERENCES resources(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY scans_snapshot_id_fkey (snapshot_id) REFERENCES snapshots(id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);
