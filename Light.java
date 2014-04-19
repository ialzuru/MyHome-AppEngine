package com.myhome;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Light {
	@Id
	private String IDName;
	private int HomeID;
	private int ID;
	private String Location;
	private int State;
	private String UpdateTime;

	// Constructor
	public Light() {
	}

	// get functions
	public String getIDName() {
		return IDName;
	}

	public int getHomeID() {
		return HomeID;
	}

	public int getID() {
		return ID;
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

	public void setID(int ID) {
		this.ID = ID;
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