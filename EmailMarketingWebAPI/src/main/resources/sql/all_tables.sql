
CREATE TABLE 'usuario' (
 'id' INT NOT NULL AUTO_INCREMENT,
 'username' VARCHAR(20) NOT NULL,
 'password' VARCHAR(20) NOT NULL,
 'email' VARCHAR(40) NOT NULL,
 PRIMARY KEY ('id'),
 UNIQUE KEY 'uk_usuario.username' ('username'),
 UNIQUE KEY 'uk_usuario.email' ('email')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


CREATE TABLE 'contato' (
 'id' INT NOT NULL AUTO_INCREMENT,
 'nome' VARCHAR(20) NOT NULL,
 'email' VARCHAR(40) NOT NULL,
 PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


CREATE TABLE 'mensagem' (
 'id' INT NOT NULL AUTO_INCREMENT,
 'titulo' VARCHAR(20) NOT NULL,
 'conteudo' BLOB,
 PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


CREATE TABLE 'grupo' (
 'id' INT NOT NULL AUTO_INCREMENT,
 'nome' VARCHAR(20) NOT NULL,
 PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


CREATE TABLE 'usuario_grupo' (
 'fk_usuario' INT NOT NULL,
 'fk_grupo' INT NOT NULL,
 CONSTRAINT 'fk_usuario_grupo.fk_usuario' FOREIGN KEY ('fk_usuario') REFERENCES 'usuario' ('id'),
 CONSTRAINT 'fk_usuario_grupo.fk_grupo' FOREIGN KEY ('fk_grupo') REFERENCES 'grupo' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE 'usuario_contato' (
 'fk_usuario' INT NOT NULL,
 'fk_contato' INT NOT NULL,
 CONSTRAINT 'fk_usuario_contato.fk_usuario' FOREIGN KEY ('fk_usuario') REFERENCES 'usuario' ('id'),
 CONSTRAINT 'fk_usuario_contato.fk_contato' FOREIGN KEY ('fk_contato') REFERENCES 'contato' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE 'usuario_mensagem' (
 'fk_usuario' INT NOT NULL,
 'fk_mensagem' INT NOT NULL,
 CONSTRAINT 'fk_usuario_mensagem.fk_usuario' FOREIGN KEY ('fk_usuario') REFERENCES 'usuario' ('id'),
 CONSTRAINT 'fk_usuario_mensagem.fk_mensagem' FOREIGN KEY ('fk_mensagem') REFERENCES 'mensagem' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE 'grupo_contato' (
 'fk_grupo' INT NOT NULL,
 'fk_contato' INT NOT NULL,
 CONSTRAINT 'fk_grupo_contato.fk_grupo' FOREIGN KEY ('fk_grupo') REFERENCES 'grupo' ('id'),
 CONSTRAINT 'fk_grupo_contato.fk_contato' FOREIGN KEY ('fk_contato') REFERENCES 'contato' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8