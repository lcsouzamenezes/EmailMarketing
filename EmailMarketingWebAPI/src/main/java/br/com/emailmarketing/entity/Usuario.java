package br.com.emailmarketing.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@NamedQueries({
	@NamedQuery(name = "findByUsernameAndPassword", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name = "findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
})
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{usuario.username.null}")
	@Size(min = 5, max = 20, message = "{usuario.username.size}")
	@Column(length = 20, nullable = false, unique = true)
	private String username;

	@NotNull(message = "{usuario.password.null}")
	@Size(min = 5, max = 20, message = "{usuario.password.size}")
	@Column(length = 20, nullable = false)
	private String password;

	@NotNull(message = "{usuario.email.null}")
	@Size(min = 10, max = 40, message = "{usuario.email.size}")
	@Column(length = 40, nullable = false, unique = true)
	private String email;

	@Null
	@JoinTable(name="usuario_grupo", joinColumns = @JoinColumn(name = "fk_usuario"), inverseJoinColumns = @JoinColumn(name = "fk_grupo"))
	@Cascade(CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Grupo> grupos;

	@Null
	@JoinTable(name="usuario_mensagem", joinColumns = @JoinColumn(name = "fk_usuario"), inverseJoinColumns = @JoinColumn(name = "fk_mensagem"))
	@Cascade(CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Mensagem> mensagens;

	@Null
	@JoinTable(name="usuario_contato", joinColumns = @JoinColumn(name = "fk_usuario"), inverseJoinColumns = @JoinColumn(name = "fk_contato"))
	@Cascade(CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Contato> contatos;

	public Usuario() {

	}

	public Usuario(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", grupos=");
		builder.append(grupos);
		builder.append(", emails=");
		builder.append(mensagens);
		builder.append("]");
		return builder.toString();
	}

}
