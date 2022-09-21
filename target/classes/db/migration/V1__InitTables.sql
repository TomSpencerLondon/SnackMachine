CREATE TABLE snackmachine(
    id BIGSERIAL PRIMARY KEY,
    one_cent_count integer NOT NULL,
    five_cent_count integer NOT NULL,
    ten_cent_count integer NOT NULL,
    quarter_count integer NOT NULL,
    one_dollar_count integer NOT NULL,
    five_dollar_count integer NOT NULL,
    twenty_dollar_count integer NOT NULL
)