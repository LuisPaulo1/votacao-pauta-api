create table IF NOT EXISTS pauta (id  serial not null, nome varchar(255), primary key (id));
create table IF NOT EXISTS sessao_votacao (id  serial not null, data_hora_abertura timestamp, data_hora_fechamento timestamp, pauta_id int4, primary key (id));
create table IF NOT EXISTS voto (cpf_votante varchar(255) not null, resposta_do_voto varchar(255), sessao_votacao_id int4, primary key (cpf_votante));
alter table sessao_votacao add constraint IF NOT EXISTS FKf74f8sm5id28fb93vh3eew3ff foreign key (pauta_id) references pauta;
alter table voto add constraint IF NOT EXISTS FKsr9gs7us7maap0ktox6jo6ns9 foreign key (sessao_votacao_id) references sessao_votacao;