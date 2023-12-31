package net.inmobiles.ClientWebAPI;

public class Name {
	
	private String id;
	private String fname;
	private String lname;


	public Name(String id, String fname, String lname) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "id: " + id + ", fname: " + fname + ", lname: " + lname;
	}



}
