INSERT INTO permissao VALUES (1, 'ROLE_CADASTRAR_USUARIO');

INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 1);

INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (
	(SELECT id FROM usuario WHERE email = 'admin@saude.com'), 1);
