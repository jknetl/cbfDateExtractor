package cz.cbf.date.extractor;

import java.io.File;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;

public class Main {

	public static void main(String[] args) throws Exception {
		MatchParser parser = new MatchParser();

		String url = "http://www.cbf.cz/souteze/rozpis-utkani/rozpis_3322.html";

		List<Match> matches = parser.parseMatches(url);

		MatchConverter converter = new MatchConverter();

		ICalendar calendar = converter.createCalendar(matches);

		File file = new File("target/testCalendar.ical");
		Biweekly.write(calendar).go(file);
	}
}
