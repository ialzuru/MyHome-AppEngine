package com.myhome;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Camera {
	@Id
	private String IDName;
	private int HomeID;
	private int Id;
	private String Location;
	private int State;
	private String UpdateTime;

	// Constructor
	public Camera() {
	}

	// get functions
	public String getIDName() {
		return IDName;
	}

	public int getHomeID() {
		return HomeID;
	}

	public int getId() {
		return Id;
	}

	public String getLocation() {
		return Location;
	}

	public int getState() {
		return State;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	// set functions
	public void setIDName(String IDName) {
		this.IDName = IDName;
	}

	public void setHomeID(int HomeID) {
		this.HomeID = HomeID;
	}

	public void setID(int Id) {
		this.Id = Id;
	}

	public void setLocation(String Location) {
		this.Location = Location;
	}

	public void setState(int State) {
		this.State = State;
	}

	public void setUpdateTime(String UpdateTime) {
		this.UpdateTime = UpdateTime;
	}
}
