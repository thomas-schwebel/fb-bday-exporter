package fr.tschwebel.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BirthdayTest {

    @Test
    void toTwoDigit() {
        assertEquals("01", Birthday.toTwoDigit(1));
        assertEquals("11", Birthday.toTwoDigit(11));
    }

    @Test
    void generateOutlookUrl() {
        Birthday bday = new Birthday("Toto", 10, 4);
        String expected = "https://calendar.google.com/calendar/u/0/r/eventedit?text=Toto+Birthday&dates=20201004/20201005&recur=RRULE:FREQ%3DYEARLY";

        assertEquals(expected, bday.generateOutlookUrl());
    }
}