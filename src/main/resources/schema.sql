CREATE TABLE IF NOT EXISTS short_url (
    id UUID PRIMARY KEY,
    short_url VARCHAR (8) NOT NULL,
    url VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS statistic (
    id UUID PRIMARY KEY,
    total_number_of_calls BIGINT NOT NULL,
    average_forward_duration_in_millis BIGINT NOT NULL,
    time_of_last_call TIMESTAMP,
    short_url_id UUID NOT NULL,
    CONSTRAINT statistic_short_url FOREIGN KEY (short_url_id) REFERENCES short_url(id) ON DELETE CASCADE
);
