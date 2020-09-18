package fr.tschwebel.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class FbUrlTest {

    @Test
    void getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new GregorianCalendar(2014, Calendar.SEPTEMBER, 11).getTime());

        assertEquals("September", FbUrl.getMonth(cal));
    }
}