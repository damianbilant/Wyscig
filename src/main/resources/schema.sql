create table uczestnicyWyscigu
(
    uuid varchar(255) not null,
    zycieKierowcy varchar(255) not null,
    primary key (uuid)
);

create table zwyciezcyWyscigu
(
    id_zwyciezcyWyscigu varchar(255) not null,
    uuid varchar(255) not null,
    typSamochodu varchar(255) not null,
    wytrzymałośćSamochodu varchar(255) not null,
    przejechanydystans varchar(255) not null,
    nazwaKierowcy varchar(255) not null,
    zycieKierowcy varchar(255) not null,
    czasZakonczeniaWyscigu varchar(255) not null,
    primary key (id_zwyciezcyWyscigu)
);