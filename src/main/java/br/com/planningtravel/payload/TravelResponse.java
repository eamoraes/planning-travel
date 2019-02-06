package br.com.planningtravel.payload;

public class TravelResponse implements PayloadBaseResponse {
	
	private String place;
	
	public TravelResponse() {

	}
	
	public TravelResponse(String place) {
		super();
		this.place = place;
	}


	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	

}
