package fr.tschwebel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

class FbUrlBuilderTest {

    @Test
    void getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new GregorianCalendar(2014, Calendar.SEPTEMBER, 11).getTime());

        assertEquals("September", FbUrlBuilder.getMonth(cal));
    }
}