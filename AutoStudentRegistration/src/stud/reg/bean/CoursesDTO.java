package stud.reg.bean;

public class CoursesDTO {
	
	private String cname;
	private int seats;
	
	public CoursesDTO() {}

	public CoursesDTO(String cname, int seats) {
		super();
		this.cname = cname;
		this.seats = seats;
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
		return "Course Name : "+this.cname+"\nTotal Seats Available : "+this.seats;
	};
	
	
	
	

}
