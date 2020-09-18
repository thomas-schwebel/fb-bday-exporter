package fr.tschwebel.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FbUrl {

    // TODO should not be static
    public static final String ACCESS_TOKEN = "";

    public static final int MS_TO_SECOND = 1000;

    public static Map<String, String> generateURLs() {
        // FB uses PST -> https://www.facebook.com/help/community/question/?id=1949627351935296
        Map<String, String> monthlyTimestamp = IntStream.range(0, 12).mapToObj(i -> {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST")); // keeps it immutable
            calendar.add(Calendar.MONTH, i);
            return calendar;
        }).collect(Collectors.toUnmodifiableMap(FbUrl::getMonth, FbUrl::formatUrl));

        return monthlyTimestamp;
    }

    static String getMonth(Calendar cal) {
        return new SimpleDateFormat("MMMM")
                .format(cal.getTime());
    }

    private static String formatUrl(Calendar cal) {
        String formatURL = "https://www.facebook.com/async/birthdays/?date=%d&__a=1&fb_dtsg_ag=%s";

        return String.format(formatURL, cal.getTimeInMillis() / MS_TO_SECOND, ACCESS_TOKEN);
    }
}
