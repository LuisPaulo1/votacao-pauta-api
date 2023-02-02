DELETE FROM voto;
DELETE FROM sessao_votacao;
DELETE FROM pauta;

INSERT INTO pauta(id, nome) VALUES (1, 'Teste');
INSERT INTO sessao_votacao(id, data_hora_abertura, data_hora_fechamento, pauta_id) VALUES (1, CURRENT_TIMESTAMP, timestampadd('MINUTE', 1, CURRENT_TIMESTAMP), 1);
INSERT INTO voto(cpf_votante, resposta_do_voto, sessao_votacao_id) VALUES ('751.278.628-08', 'Sim', 1);

INSERT INTO pauta(id, nome) VALUES (2, 'Teste');

INSERT INTO pauta(id, nome) VALUES (3, 'Teste');
INSERT INTO sessao_votacao(id, data_hora_abertura, data_hora_fechamento, pauta_id) VALUES (3, CURRENT_TIMESTAMP, timestampadd('MINUTE', -1, CURRENT_TIMESTAMP), 3);
INSERT INTO voto(cpf_votante, resposta_do_voto, sessao_votacao_id) VALUES ('004.719.500-25', 'Sim', 3);

INSERT INTO pauta(id, nome) VALUES (4, 'Teste');
INSERT INTO sessao_votacao(id, data_hora_abertura, data_hora_fechamento, pauta_id) VALUES (4, CURRENT_TIMESTAMP, timestampadd('MINUTE', 1, CURRENT_TIMESTAMP), 4);