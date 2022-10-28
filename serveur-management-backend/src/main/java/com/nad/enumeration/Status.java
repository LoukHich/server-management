package com.nad.enumeration;

public enum Status {
	
	SERVER_UP("server_up"),
	SERVER_DOWN("server_down");
   // constructor privee
	private final String status ;
	Status(String status) {
		this.status=status;
	}

	public String getStatus() {
		return status;
	}


}
