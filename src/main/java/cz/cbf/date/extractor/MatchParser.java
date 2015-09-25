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
import java.time.LocalDateTime;
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
				String date = cols.get(DATE_COLUMN).text();
				if (date != null && !date.isEmpty()) {
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
					m.setDate(LocalDateTime.parse(date, dateFormatter));
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
