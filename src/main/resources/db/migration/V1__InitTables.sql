CREATE TABLE SnackMachine
(
    SnackMachineID    bigint NOT NULL,
    OneCentCount      int    NOT NULL,
    FiveCentCount      int    NOT NULL,
    TenCentCount      int    NOT NULL,
    QuarterCount      int    NOT NULL,
    OneDollarCount    int    NOT NULL,
    FiveDollarCount   int    NOT NULL,
    TwentyDollarCount int    NOT NULL,
    CONSTRAINT PK_SnackMachine PRIMARY KEY
        (SnackMachineID)
);

CREATE TABLE Slot
(
    SlotID         bigint         NOT NULL,
    Quantity       int            NOT NULL,
    Price          decimal(18, 2) NOT NULL,
    SnackID        bigint         NOT NULL,
    SnackMachineID bigint         NOT NULL,
    Position       int            NOT NULL,
    CONSTRAINT PK_Slot PRIMARY KEY
    (SlotID)
);

CREATE TABLE Snack
(
    SnackID bigint       NOT NULL,
    Name    Varchar(200) NOT NULL,
    CONSTRAINT PK_Snack PRIMARY KEY
    (SnackID)
);