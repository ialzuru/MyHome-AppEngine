package com.myhome;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String IDName;
	private String Address;
	private int Cellphone;
	private String Email;
	private String Firstname;
	private int HomeID;
	private int Id;
	private String Lastname;
	private String Login;
	private String Password;

	// Constructor
	public User() {
	}
	
	//////////////////////////////////////////////////////////////
	// get functions
	public String getIDName() {
		return IDName;
	}

	public String getAddress() {
		return Address;
	}	

	public int getCellphone() {
		return Cellphone;
	}	
	
	public String getEmail() {
		return Email;
	}	
	
	public String getFirstname() {
		return Firstname;
	}	
	
	public int getHomeID() {
		return HomeID;
	}

	public int getId() {
		return Id;
	}

	public String getLastname() {
		return Lastname;
	}

	public String getLogin() {
		return Login;
	}

	public String getPassword() {
		return Password;
	}

	//////////////////////////////////////////////////////////////
	// set functions
	public void setIDName(String IDName) {
		this.IDName = IDName;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public void setCellphone(int Cellphone) {
		this.Cellphone = Cellphone;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
	}

	public void setHomeID(int HomeID) {
		this.HomeID = HomeID;
	}
	
	public void setId(int Id) {
		this.Id = Id;
	}
	
	public void setLastname(String Lastname) {
		this.Lastname = Lastname;
	}
	
	public void setLogin(String Login) {
		this.Login = Login;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}	
}