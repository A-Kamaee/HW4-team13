package ir.sharif.splashscreen.util;

public class Calendar
{
	public static String getMonth(String dayOfYear)
	{
		return getMonth(Integer.parseInt(dayOfYear));
	}
	
	private static String getMonth(int dayOfYear)
	{
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(java.util.Calendar.DAY_OF_YEAR, dayOfYear);
		return calendar.toString();
	}
}
