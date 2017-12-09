package com.cricri.RunRace.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.cricri.RunRace.RunRace;
import com.cricri.RunRace.servicios.ServicioUsuarios;

@ManagedBean
@SessionScoped
public class LoginBean {

    public final static String USER_KEY="auth_user";

	private String correo;
	private String pass;
	
	public String login() {
		try {
			if (ServicioUsuarios.getInstance().loginUsuario(correo, pass)) {
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext extContext = context.getExternalContext();
				String url = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,
						RunRace.NAVEGACION_INICIO));
				UserBean user = new UserBean(ServicioUsuarios.getInstance().buscarUsuarioCorreo(correo));
				extContext.getSessionMap().put(USER_KEY, user);
				extContext.redirect(url);
				
				return RunRace.NAVEGACION_INICIO;
			}
			
			pass = new String();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrecta.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			return RunRace.NAVEGACION_FALLO;
		}
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}
