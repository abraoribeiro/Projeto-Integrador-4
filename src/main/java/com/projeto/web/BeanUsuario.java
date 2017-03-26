package com.projeto.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import com.projeto.model.usuario.Usuario;
import com.projeto.model.usuario.UsuarioRN;


@ManagedBean (name="beanU")
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	Usuario u = new Usuario();

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public String verifica() {
		if (new UsuarioRN().login(u)) {
			if (u.getTipo().equals("ADMINISTRADOR")) {
				return "admin";
			} else {
				return "menuCon";
			}
		} else {
			return "erro";
		}
	}
	
	public String cadastra(){
		new UsuarioRN().inserir(u);
		return "admin";
	}

}
