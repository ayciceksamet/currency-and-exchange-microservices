create table conversion
(
   id BIGINT(20) not null,
   transactionid varchar(255) not null,
   amount DECIMAL(20, 2) not null,
   date BIGINT(20) not null,
   primary key(id)
);

