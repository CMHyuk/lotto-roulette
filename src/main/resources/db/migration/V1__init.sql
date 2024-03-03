CREATE TABLE lottery_history
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    first_prize_amount    BIGINT                NOT NULL,
    winner_count          INT                   NOT NULL,
    first_lottery_number  INT                   NOT NULL,
    second_lottery_number INT                   NOT NULL,
    third_lottery_number  INT                   NOT NULL,
    fourth_lottery_number INT                   NOT NULL,
    fifth_lottery_number  INT                   NOT NULL,
    sixth_lottery_number  INT                   NOT NULL,
    round                 INT                   NOT NULL,
    CONSTRAINT pk_lotteryhistory PRIMARY KEY (id)
);

CREATE INDEX IDX_ROUND ON lottery_history (round);

CREATE TABLE lottery_number_frequency
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    lottery_number INT                   NOT NULL,
    frequency      INT                   NOT NULL,
    CONSTRAINT pk_lotterynumberfrequency PRIMARY KEY (id)
);
