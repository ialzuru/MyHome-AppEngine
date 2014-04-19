package com.myhome;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Action {
	@Id
	private String ID;
	private String ActionType;
	private int HomeID;
	private int SensorID;
	private String SensorType;
	private int State;

	// Constructor
	public Action() {
	}

	// get functions
	public String getID() {
		return ID;
	}

	public String getActionType() {
		return ActionType;
	}

	public int getHomeID() {
		return HomeID;
	}

	public int getSensorID() {
		return SensorID;
	}

	public String getSensorType() {
		return SensorType;
	}

	public int getState() {
		return State;
	}

	// set functions
	public void setID(String ID) {
		this.ID = ID;
	}

	public void setActionType(String ActionType) {
		this.ActionType = ActionType;
	}

	public void setHomeID(int HomeID) {
		this.HomeID = HomeID;
	}

	public void setSensorID(int SensorID) {
		this.SensorID = SensorID;
	}

	public void setSensorType(String SensorType) {
		this.SensorType = SensorType;
	}

	public void setState(int State) {
		this.State = State;
	}
}
