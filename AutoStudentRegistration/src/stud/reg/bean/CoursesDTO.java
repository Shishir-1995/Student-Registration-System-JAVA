package stud.reg.bean;

public class CoursesDTO {
	
	private int cid;
	private String cname;
	private int seats;
	
	public CoursesDTO() {}

	public CoursesDTO(int cid,String cname, int seats) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.seats = seats;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Course ID : "+this.cid+"\nCourse Name : "+this.cname+"\nTotal Seats Available : "+this.seats;
	};
	
	
	
	

}
