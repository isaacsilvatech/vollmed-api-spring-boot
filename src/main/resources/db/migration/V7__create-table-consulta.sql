create table consulta(
    id bigint not null auto_increment,
    paciente_id bigint not null,
    medico_id bigint not null,
    data date not null,
    motivoCancelamento varchar(100) not null,

    foreign key (paciente_id) references paciente(id),
    foreign key (medico_id) references medico(id),

    primary key (id)
);