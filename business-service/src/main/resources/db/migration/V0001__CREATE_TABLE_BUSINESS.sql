CREATE TABLE business (
    id UUID PRIMARY KEY,
    address TEXT NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    latitude VARCHAR(30) NOT NULL,
    longitude VARCHAR(30) NOT NULL
);