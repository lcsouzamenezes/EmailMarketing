package br.com.emailmarketing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "contato")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{contato.nome.null}")
	@Size(min = 5, max = 20, message = "{contato.nome.size}")
	@Column(length = 20, nullable = false, unique = false)
	private String nome;

	@NotNull(message = "{contato.email.null}")
	@Size(min = 5, max = 40, message = "{contato.email.size}")
	@Column(length = 40, nullable = false, unique = false)
	private String email;

	@JsonIgnore
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof  Contato) ){
            return false;
        }

        if(this == obj){
            return true;
        }

        Contato c = (Contato) obj;

        if(Objects.equals(this.email, c.email)){
            return true;
        }

        return false;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contato [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
