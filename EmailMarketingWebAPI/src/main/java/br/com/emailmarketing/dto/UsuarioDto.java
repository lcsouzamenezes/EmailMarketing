package br.com.emailmarketing.dto;

import br.com.emailmarketing.entity.Usuario;

public class UsuarioDto {

    private String username;

    private String email;

    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Usuario buildUsuario(){
        Usuario usuario = new Usuario();
        usuario.setUsername(this.getUsername());
        usuario.setPassword(this.getPassword());
        usuario.setEmail(this.getEmail());
        return usuario;
    }

}
