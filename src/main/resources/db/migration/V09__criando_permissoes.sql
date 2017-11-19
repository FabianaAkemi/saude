INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (
	(SELECT id FROM usuario WHERE email = 'admin@saude.com'), 1);