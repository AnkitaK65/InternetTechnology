CREATE DATABASE IF NOT EXISTS book_catalog;
USE book_catalog;

CREATE TABLE books (
    id VARCHAR(10) PRIMARY KEY,
    author VARCHAR(100) NOT NULL,
    title VARCHAR(200) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    publish_date DATE NOT NULL,
    description TEXT,
    rating DECIMAL(3,1),
    pages INT,
    language VARCHAR(30)
);

-- Insert books
INSERT INTO books VALUES
('bk101', 'Gambardella, Matthew', 'XML Developer''s Guide', 'Computer', 44.95, '2000-10-01', 'An in-depth look at creating applications with XML. Essential for modern web developers.', 4.5, 350, 'English'),
('bk102', 'Ralls, Kim', 'Midnight Rain', 'Fantasy', 5.95, '2000-12-16', 'A former architect battles corporate zombies in this dark urban fantasy.', 3.8, 280, 'English'),
('bk103', 'Corets, Eva', 'Maeve Ascendant', 'Fantasy', 5.95, '2000-11-17', 'After the collapse of a nanotechnology society, a young woman discovers her magical heritage.', 4.2, 320, 'English'),
('bk104', 'Corets, Eva', 'Oberon''s Legacy', 'Fantasy', 5.95, '2001-03-10', 'In post-apocalypse England, the mysterious agent known only as Oberon returns.', 4.0, 295, 'English'),
('bk105', 'Corets, Eva', 'The Sundered Grail', 'Fantasy', 5.95, '2001-09-10', 'The two daughters of Maeve battle for control of England in this epic conclusion.', 4.3, 410, 'English'),
('bk106', 'Randall, Cynthia', 'Lover Birds', 'Romance', 4.95, '2000-09-02', 'When Carla meets Paul at an ornithology conference, sparks fly in more ways than one.', 3.5, 220, 'English'),
('bk107', 'Thurman, Paula', 'Splish Splash', 'Romance', 4.95, '2000-11-02', 'A deep sea diver meets her match in this underwater romance.', 3.9, 240, 'English'),
('bk108', 'Knorr, Stefan', 'Creepy Crawlies', 'Horror', 4.95, '2000-12-06', 'An anthology of horror stories about insects that will make your skin crawl.', 4.1, 310, 'English'),
('bk109', 'Kress, Peter', 'Paradox Lost', 'Science Fiction', 6.95, '2000-11-02', 'Time travel has never been more dangerous in this mind-bending sci-fi thriller.', 4.4, 380, 'English'),
('bk110', 'O''Brien, Tim', 'Microsoft .NET: The Programming Bible', 'Computer', 36.95, '2000-12-09', 'The definitive guide to Microsoft''s .NET framework for professional developers.', 4.7, 450, 'English'),
('bk111', 'O''Brien, Tim', 'MSXML3: A Comprehensive Guide', 'Computer', 36.95, '2000-12-01', 'Everything you need to know about Microsoft''s XML implementation.', 4.3, 420, 'English'),
('bk112', 'Galos, Mike', 'Visual Studio 7: A Beginner''s Guide', 'Computer', 29.95, '2001-04-16', 'The perfect introduction to Microsoft''s flagship development environment.', 4.0, 320, 'English'),
('bk113', 'Tanaka, Yuki', 'Whispers in the Wind', 'Romance', 5.95, '2001-02-18', 'A beautiful love story set in the mountains of Japan.', 4.5, 260, 'Japanese'),
('bk114', 'Mendoza, Carlos', 'The Silent Killer', 'Mystery', 6.95, '2001-05-10', 'A detective must solve a series of murders where the victims never make a sound.', 4.2, 340, 'Spanish'),
('bk115', 'Chen, Li', 'The Art of War: Modern Applications', 'Business', 15.95, '2001-03-22', 'How Sun Tzu''s ancient strategies apply to today''s corporate battles.', 4.6, 290, 'Chinese');
