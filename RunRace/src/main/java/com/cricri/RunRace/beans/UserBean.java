package com.cricri.RunRace.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cricri.RunRace.modelo.Usuario;

@ManagedBean
@SessionScoped
public class UserBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Usuario usuario;
	
	public UserBean(){}

	public UserBean(Usuario usuario) {
		UserBean.usuario = usuario;
	}

	public static Usuario getUsuario() {
		return usuario;
	}
}
