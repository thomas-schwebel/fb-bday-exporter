package fr.tschwebel.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FbUrlBuilder {

    // TODO inject
    // get async_get_token from view-source:https://www.facebook.com/events/birthdays/
    public static final String ACCESS_TOKEN = "";

    public static final String MONTHLY_BIRTHDAYS_URL_FORMAT = "https://www.facebook.com/async/birthdays/?date=%d&__a=1&fb_dtsg_ag=%s";

    public static final int MS_TO_SECOND = 1000;

    public static Map<String, String> generateURLs() {
        // FB uses PST -> https://www.facebook.com/help/community/question/?id=1949627351935296
        Map<String, String> monthlyTimestamp = IntStream.range(0, 12).mapToObj(i -> {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST")); // keeps it immutable
            calendar.add(Calendar.MONTH, i);
            return calendar;
        }).collect(Collectors.toUnmodifiableMap(FbUrlBuilder::getMonth, FbUrlBuilder::formatUrl));

        return monthlyTimestamp;
    }

    public static String getMonth(Calendar cal) {
        return new SimpleDateFormat("MMMM")
                .format(cal.getTime());
    }

    private static String formatUrl(Calendar cal) {
        return String.format(MONTHLY_BIRTHDAYS_URL_FORMAT, cal.getTimeInMillis() / MS_TO_SECOND, ACCESS_TOKEN);
    }
}
