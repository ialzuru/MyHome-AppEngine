package com.myhome;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovementSensor {
	@Id
	private String IDName;
	private int HomeID;
	private String LastMovement;
	private String Location;
	private String UpdateTime;

	// Constructor
	public MovementSensor() {
	}

	// get functions
	public String getIDName() {
		return IDName;
	}

	public int getHomeID() {
		return HomeID;
	}

	public String getLastMovement() {
		return LastMovement;
	}	
	
	public String getLocation() {
		return Location;
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

	public void setLastMovement(String LastMovement) {
		this.LastMovement = LastMovement;
	}
	
	public void setLocation(String Location) {
		this.Location = Location;
	}

	public void setUpdateTime(String UpdateTime) {
		this.UpdateTime = UpdateTime;
	}
}
