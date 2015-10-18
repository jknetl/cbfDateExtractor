/**
 *
 */
package cz.cbf.date.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import cz.cbf.date.extractor.constants.CbfRosterConstants;

/**
 * @author jknetl
 *
 */
public class MatchParser implements CbfRosterConstants {

	static final Logger logger = LoggerFactory.getLogger(MatchParser.class);

	public List<Match> parseMatches(String url) throws IOException {

		List<Match> matches = new ArrayList<Match>();

		Document doc = Jsoup.connect(url).get();
		String tableExpression = ".rozpis > tbody:nth-child(1) > *";
		Elements rows = doc.select(tableExpression);

		for (int i = 0; i < rows.size(); i++) {
			Element row = rows.get(i);
			Elements cols = row.select("td");

			if (cols.size() >= 7) {
				Match m = new Match();
				m.setHomeTeam(cols.get(HOME_TEAM_COLUMN).text());
				m.setGuestTeam(cols.get(GUEST_TEAM_COLUMN).text());

				//parse date
				String dateTime = cols.get(DATE_COLUMN).text();
				if (dateTime != null && !dateTime.isEmpty()) {
					String date = dateTime.substring(0, 10);

					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

					m.setDate(LocalDate.parse(date, dateFormatter));

					if (dateTime.length() > TIME_OFSET) {
						String time = dateTime.substring(11);
						DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
						m.setTime(LocalTime.parse(time, timeFormatter));
					}
				}


				//parse address
				Element details = cols.get(DETAILS_COLUMN);
				String address = details.select(ADDRESS_SELECTOR).get(0).text();
				m.setAddress(address);

				logger.debug("Parsing match: {}", m.toString());

				matches.add(m);
			}
		}

		return matches;
	}

}
