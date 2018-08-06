package gr.komic.arnold.Infrastructure;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateService {

    public static String getTodayToString(String outputFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
        Date today = Calendar.getInstance().getInstance().getTime();

        return formatter.format(today);
    }

}
