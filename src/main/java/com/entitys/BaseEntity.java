package com.entitys;

import java.io.Serializable;

public class BaseEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1865211865831415114L;
	
	private int id;//自增ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
