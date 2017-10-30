CREATE TABLE IF NOT EXISTS COMMENTS(
  comment_id SERIAL PRIMARY KEY ,
  description text
);

CREATE TABLE IF NOT EXISTS ITEMS(
  item_id SERIAL PRIMARY KEY ,
  item_type VARCHAR(256),
  name VARCHAR(256),
  description text,
  comment_id INTEGER REFERENCES COMMENTS(comment_id),
  create_date TIMESTAMP WITHOUT TIME ZONE
);