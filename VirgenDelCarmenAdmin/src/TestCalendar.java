import java.util.Calendar;


public class TestCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
	    int i = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
	    calendar.add(Calendar.DATE, -i);		
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.print(calendar.getTime());

		
	}

}
