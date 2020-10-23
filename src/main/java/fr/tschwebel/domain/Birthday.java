package fr.tschwebel.domain;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Birthday {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private final int id;
    private final String name;
    private final int day;
    private final int month;

    public Birthday(String name, int month, int day) {
        this.id = ATOMIC_INTEGER.getAndIncrement();
        this.name = name;
        this.month = month;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    // syntax
    // https://medium.com/collaborne-engineering/google-calendar-links-4fa1b6c01c7c
    // https://kloudless.com/blog/monday-mentorship-how-to-create-a-link-to-add-an-event-in-the-google-calendar-api/
    public String generateOutlookUrl() {
        String formatUrl = "https://calendar.google.com/calendar/u/0/r/eventedit?text=%s+Birthday&dates=%s&recur=%s";
        String year = String.format("2020%s%s/2020%s%s", toTwoDigit(month), toTwoDigit(day), toTwoDigit(month), toTwoDigit(day+1));

        return String.format(formatUrl, name, year, "RRULE:FREQ%3DYEARLY"); // adding recur here to avoid issue with %3D and the format fct
    }

    static String toTwoDigit(int value) {
        return value > 9 ? Integer.toString(value) : "0" + value;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "name='" + name + '\'' +
                ", day=" + day +
                ", month=" + month +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birthday birthday = (Birthday) o;
        return day == birthday.day &&
                month == birthday.month &&
                name.equals(birthday.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, day, month);
    }
}
