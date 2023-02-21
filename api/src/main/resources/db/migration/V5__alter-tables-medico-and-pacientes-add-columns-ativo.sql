alter table medicos add ativo tinyint;
update medicos set ativo = 1;
alter table medicos modify ativo tinyint not null;

alter table pacientes add ativo tinyint;
update pacientes set ativo = 1;
alter table pacientes modify ativo tinyint not null;