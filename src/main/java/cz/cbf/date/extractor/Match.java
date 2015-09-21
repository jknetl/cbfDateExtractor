package cz.cbf.date.extractor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity object representing match.
 * 
 * @author jknetl
 *
 */
public class Match {
	
	private String homeTeam;
	private String guestTeam;
	
	private String Address;
	private LocalDate date;
	private LocalTime time;
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	

}
