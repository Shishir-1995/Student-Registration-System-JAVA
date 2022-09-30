package stud.reg.bean;

public class Batch {

	private int bid;
	private String name;
	private int duration;
	private int seats;
	private int cid;
	
	public Batch() {}

	public Batch(int bid, String name, int duration, int seats, int cid) {
		super();
		this.bid = bid;
		this.name = name;
		this.duration = duration;
		this.seats = seats;
		this.cid = cid;
	}



	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	
	
	
	
}
