package br.com.emailmarketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mensagem")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{mensagem.titulo.null}")
	@Size(min = 5, max = 20, message = "{mensagem.titulo.size}")
	@Column(length = 20, nullable = false, unique = false)
	private String titulo;

	@NotNull(message = "{mensagem.conteudo.null}")
	@Lob
	@Column(nullable = false)
	private String conteudo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mensagem [id=");
		builder.append(id);
		builder.append(", titulo=");
		builder.append(titulo);
		builder.append(", conteudo=");
		builder.append(conteudo);
		builder.append("]");
		return builder.toString();
	}

}
