CREATE TABLE business (
    id UUID PRIMARY KEY,
    address TEXT NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    latitude VARCHAR(30) NOT NULL,
    longitude VARCHAR(30) NOT NULL
);

INSERT INTO business (
    id, address, city, state, country, latitude, longitude
) VALUES (
    '6c485d83-4071-40a3-af25-cdfe8c5b7f1f',
    '123 Market Street',
    'San Francisco',
    'CA',
    'USA',
    '37.7749',
    '-122.4194'
);