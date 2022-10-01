package stud.reg.bean;

public class Admin {

	private int aid;
	private String aname;
	private String username;
	private String password;
	
	public Admin() {}
	
	public Admin(int aid, String aname, String username, String password) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.username = username;
		this.password = password;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin ID : "+this.aid+"\nAdmin Name : "+this.aname+"\nAdmin Username : "+this.username+"\nAdmin Password : "+this.password;
	}
	
	
	
}
