CREATE TABLE lotto_number_history
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    first_prize_amount  BIGINT                NOT NULL,
    winner_count        INT                   NOT NULL,
    round_date          DATE                  NOT NULL,
    first_lotto_number  INT                   NOT NULL,
    second_lotto_number INT                   NOT NULL,
    third_lotto_number  INT                   NOT NULL,
    fourth_lotto_number INT                   NOT NULL,
    fifth_lotto_number  INT                   NOT NULL,
    sixth_lotto_number  INT                   NOT NULL,
    CONSTRAINT pk_lottonumberhistory PRIMARY KEY (id)
);