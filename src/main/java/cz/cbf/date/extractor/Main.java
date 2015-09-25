package cz.cbf.date.extractor;

public class Main {

	public static void main(String[] args) throws Exception {
		MatchParser parser = new MatchParser();

		String url = "http://www.cbf.cz/souteze/rozpis-utkani/rozpis_3322.html";

		parser.parseMatches(url);
	}
}
