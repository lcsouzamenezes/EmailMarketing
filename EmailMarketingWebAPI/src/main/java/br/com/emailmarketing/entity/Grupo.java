package br.com.emailmarketing.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "grupo")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{grupo.nome.null}")
	@Size(min = 5, max = 20, message = "{grupo.nome.size}")
	@Column(length = 20, nullable = false, unique = false)
	private String nome;

	@JoinTable(name="grupo_contato", joinColumns = @JoinColumn(name = "fk_grupo"), inverseJoinColumns = @JoinColumn(name = "fk_contato"))
	@OneToMany(fetch = FetchType.EAGER)
	private List<Contato> contatos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
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
		builder.append("Grupo [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", contatos=");
		builder.append(contatos);
		builder.append("]");
		return builder.toString();
	}

}
