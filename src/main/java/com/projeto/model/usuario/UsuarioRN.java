package com.projeto.model.usuario;

public class UsuarioRN {

	public void inserir(Usuario u){
		new UsuarioDAO().insert(u);
		}
	
	public Boolean login(Usuario u){
	 return	new UsuarioDAO().select(u);
	}

}
