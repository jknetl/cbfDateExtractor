package cz.cbf.date.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.util.Duration;

/**
 * This class is used for converting matches to iCalendar events
 * @author jknetl
 *
 */
public class MatchConverter {

	static final Logger logger = LoggerFactory.getLogger(MatchConverter.class);

	public ICalendar createCalendar(List<Match> matches){
		ICalendar calendar = new ICalendar();

		for (Match match: matches){
			if (match.getDate() == null){
				logger.warn("Match {} has no specified date!", match);
			}
			else {
				VEvent matchEvent = new VEvent();
				matchEvent.setSummary(match.getHomeTeam() + " x " + match.getGuestTeam());
				LocalDate localDate = match.getDate();
				LocalTime localTime = match.getTime();
				if (localDate != null && localTime != null){
					LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
					Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
					matchEvent.setDateStart(date);
					matchEvent.setDuration(new Duration.Builder().hours(2).build());
				} else if (localDate != null && localTime == null) {
					Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
					matchEvent.setDateStart(date);
					matchEvent.setDuration(new Duration.Builder().days(1).build());
				}


				calendar.addEvent(matchEvent);
			}

		}

		return calendar;
	}

}
