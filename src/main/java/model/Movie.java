package model;

public class Movie {

	private long id;
	
	private String title;
	
	private int year;
	
	public Movie() {
		
	}

	

	public void setId(long id) {
		this.id = id;
	}



	public Movie(long id, String title, int year) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
	}



	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
