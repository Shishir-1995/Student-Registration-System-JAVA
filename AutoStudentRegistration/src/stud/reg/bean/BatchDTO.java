package stud.reg.bean;

public class BatchDTO {

	private int bid;
	private String bname;
	private int seats;
	private String cname;
	
	public BatchDTO() {}

	public BatchDTO(int bid, String bname, int seats, String cname) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.seats = seats;
		this.cname = cname;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Batch No : "+this.bid+"\nBatch Name : "+this.bname+"\nBatch Seats : "+this.seats+"\nCourse Name : "+this.cname;
	}
	
	
	
	
}
