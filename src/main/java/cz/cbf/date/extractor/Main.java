package cz.cbf.date.extractor;

import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		MatchParser parser = new MatchParser();
		
		parser.parseMatches(new File("/home/kuba/rozpis.html"));
		
		
	}
}
