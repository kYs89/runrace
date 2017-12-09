package com.cricri.RunRace.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cricri.RunRace.RunRace;

@ManagedBean
@SessionScoped
public class MenuBean {
	public String navLogin(){
		return RunRace.NAVEGACION_LOGIN;
	}
	
	public String navError(){
		return RunRace.NAVEGACION_FALLO;
	}
	
	public String navInicio(){
		return RunRace.NAVEGACION_INICIO;
	}
	
	public String navCarreras(){
		return RunRace.NAVEGACION_CARRERAS;
	}
	
	public String navFormCarrera(){
		return RunRace.NAVEGACION_FORM_CARRERAS;
	}
	
	public String navGrupos(){
		return RunRace.NAVEGACION_GRUPOS;
	}
	
	public String navFormGrupo(){
		return RunRace.NAVEGACION_FORM_GRUPOS;
	}
	
	public String navAmistades(){
		return RunRace.NAVEGACION_AMISTADES;
	}
	
	public String navFormAmistad(){
		return RunRace.NAVEGACION_FORM_AMISTADES;
	}
}
