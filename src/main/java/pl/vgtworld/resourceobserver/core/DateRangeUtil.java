package pl.vgtworld.resourceobserver.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateRangeUtil {

	public static class DateRange {

		private Date startDate;
		private Date endDate;

		public DateRange(Date startDate, Date endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}

		public Date getStartDate() {
			return startDate;
		}

		public Date getEndDate() {
			return endDate;
		}
	}

	public static DateRange createRangeForMonth(int year, int month) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.SECOND, -1);
		Date endDate = calendar.getTime();
		return new DateRange(startDate, endDate);
	}

}
