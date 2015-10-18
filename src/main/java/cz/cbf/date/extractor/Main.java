package cz.cbf.date.extractor;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;

public class Main {

	static final Logger logger = LoggerFactory.getLogger(Main.class);

	static final String DEFAULT_OUTPUT_FILE = "match-schedule.ical";

	public static void main(String[] args) throws Exception {

		Options options = new Options();

		Option urlOption = Option.builder("u")
				.longOpt("url")
				.desc("Url from which match will be parsed.")
				.hasArg()
				.required()
				.build();

		Option teamFilerOption = Option.builder("t")
				.longOpt("teamFilter")
				.hasArg()
				.desc("Filter matches by given team.")
				.build();

		Option outputOption = Option.builder("o")
				.longOpt("output")
				.hasArg()
				.desc("Output file. Default is " + DEFAULT_OUTPUT_FILE)
				.build();

		options.addOption(urlOption);
		options.addOption(teamFilerOption);
		options.addOption(outputOption);

		CommandLineParser commandLineParser = new DefaultParser();

		try {
			CommandLine cmd = commandLineParser.parse(options, args);

			String url = cmd.getOptionValue(urlOption.getOpt());
			MatchParser matchParser = new MatchParser();
			List<Match> matches = matchParser.parseMatches(url);
			if (matches.isEmpty()) {
				logger.warn("No matches was parsed. Try another URL");
			} else {
				logger.info("{} matches was parsed.", matches.size());
			}

			if (cmd.hasOption(teamFilerOption.getOpt())) {
				matches = filterByTeam(matches, cmd.getOptionValue(teamFilerOption.getOpt()));
			}

			MatchConverter converter = new MatchConverter();
			ICalendar calendar = converter.createCalendar(matches);

			String outputFile = (cmd.hasOption(outputOption.getOpt())) ? cmd.getOptionValue(outputOption.getOpt())
					: DEFAULT_OUTPUT_FILE;

			File file = new File(outputFile);
			Biweekly.write(calendar).go(file);

		} catch (ParseException e) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("match-parser.jar", options);
		}

	}

	/**
	 * Creates new list which consists of matches where particular team is involved.
	 * @param matches matches to filter
	 * @param team Team used to filter
	 * @return
	 */
	public static List<Match> filterByTeam(List<Match> matches, String team) {
		List<Match> output = new ArrayList<>();
		for (Match m : matches) {
			if (m.getGuestTeam().toLowerCase().contains(team.toLowerCase()) ||
					m.getHomeTeam().toLowerCase().contains(team.toLowerCase())) {
				output.add(m);
			}
		}

		if (output.isEmpty() && !matches.isEmpty()) {
			logger.warn("None of them matches is played by the team {}. Try another team name", team);
		} else {
			logger.debug("{} matches parsed for the team {}.", output.size(), team);
		}

		return output;
	}
}
