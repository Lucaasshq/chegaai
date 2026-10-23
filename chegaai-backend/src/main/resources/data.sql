-- 1. Inserção dos Restaurantes (com data_criacao inclusa)
INSERT INTO restaurante (id, nome, cnpj, email, telefone, logo_url, banner_url, horario_abertura, horario_fechamento, tempo_medio_preparo_minutos, pedido_minimo, subdominio, ativo, data_criacao)
VALUES
    (1, 'Seu Zé', '07463931000175', 'seuze@gmail.com', '84981753790', '', '', '18:00:00', '23:00:00', 60, 20.00, 'seuze.com', true, NOW()),
    (2, 'Sabor Nordestino', '63756594000185', 'saborNordestino@gmail.com', '123456789', '', '', '10:00:00', '14:00:00', 60, 15.00, 'sabornordestino', true, NOW());

-- 2. Inserção dos Usuários (com senha e data_criacao inclusas)
INSERT INTO usuario (nome, email, senha, papel, restaurante_id, ativo, data_criacao)
VALUES
    ('João Zé', 'JoaoZe@gmail.com', '$2a$12$cIA61WV4iZX4M7fI3Ux3POnF4Nfm0.lE3ia0RISAhUss6KGcbVGGm', 'ROLE_DONO_RESTAURANTE', 1, true, NOW()),
    ('Maria zée', 'mariazee@maria.com', '$2a$12$cIA61WV4iZX4M7fI3Ux3POnF4Nfm0.lE3ia0RISAhUss6KGcbVGGm', 'ROLE_DONO_RESTAURANTE', 1, true, NOW()),
    ('zeca', 'zeca@maria.com', '$2a$12$cIA61WV4iZX4M7fI3Ux3POnF4Nfm0.lE3ia0RISAhUss6KGcbVGGm', 'ROLE_DONO_RESTAURANTE', 2, true, NOW());

-- 3. Ajuste das sequences para evitar erros em novos cadastros
SELECT setval(pg_get_serial_sequence('usuario', 'id'), (SELECT COALESCE(MAX(id), 1) FROM usuario));
SELECT setval(pg_get_serial_sequence('restaurante', 'id'), (SELECT COALESCE(MAX(id), 1) FROM restaurante));