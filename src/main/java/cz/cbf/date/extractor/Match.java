package cz.cbf.date.extractor;

import java.time.LocalDateTime;

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
	private LocalDateTime date;

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Match [homeTeam=" + homeTeam + ", guestTeam=" + guestTeam + ", Address=" + Address + ", date=" + date
				+ "]";
	}
}
