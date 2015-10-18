package cz.cbf.date.extractor.constants;

/**
 * Represents positions in the roster table (columns, rows, ...).
 * Positions are indexed starting with 0.
 * @author kuba
 *
 */
public interface CbfRosterConstants {

	public static final int HOME_TEAM_COLUMN = 1;
	public static final int GUEST_TEAM_COLUMN = 3;
	public static final int DATE_COLUMN = 4;
	public static final int DETAILS_COLUMN = 7;

	/**
	 * Represents relative selector from cell with match detaiols to element with
	 * link to address.
	 */
	public static final String ADDRESS_SELECTOR = "div > p > a";

	/**
	 * Represents index in the date time string where the time starts.
	 */
	public static final int TIME_OFSET = 11;

}
