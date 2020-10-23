package fr.tschwebel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import fr.tschwebel.domain.Birthday;

class BirthdayImporterTest {

    @Test
    void toBday() {
        String snippet = "for (;;);{\"__ar\":1,\"payload\":null,\"domops\":[[\"replace\",\"#birthdays_pager\",false,{\"__html\":\"\\u003Cdiv class=\\\"_4-u2 _tzh _67d4 _4-u8\\\">\\u003Cdiv class=\\\"_4-u3 _5dwa _5dw9\\\" id=\\\"birthdays_monthly_card_1617306150\\\">\\u003Cspan class=\\\"_38my\\\">April\\u003Cspan class=\\\"_c1c\\\">\\u003C\\/span>\\u003C\\/span>\\u003Cspan class=\\\"_5dw8\\\">\\u003Cdiv class=\\\"_tzj\\\">\\u003Ca title=\\\"John Rambo\\\" href=\\\"https:\\/\\/www.facebook.com\\/jrambo\\\">John Rambo\\u003C\\/a>, \\u003Ca title=\\\"Max Mustermann\\\" href=\\\"https:\\/\\/www.facebook.com\\/m.mustermann\\\">Max Mustermann\\u003C\\/a> and 0 others\\u003C\\/div>\\u003C\\/span>\\u003Cdiv class=\\\"_3s3-\\\">\\u003C\\/div>\\u003C\\/div>\\u003Cdiv class=\\\"_4-u3\\\">\\u003Cdiv class=\\\"_43qm _tzu _43q9\\\">\\u003Cul class=\\\"uiList _4cg3 _509- _4ki\\\">\\u003Ca href=\\\"https:\\/\\/www.facebook.com\\/matthew.hugo.73\\\" class=\\\"link\\\" data-jsid=\\\"anchor\\\" data-hover=\\\"tooltip\\\" data-tooltip-content=\\\"John Rambo (4\\/23)\\\">\\u003Cimg class=\\\"_s0 _ry img\\\" src=\\\"https:\\/\\/scontent.ftxl2-1.fna.fbcdn.net\\/v\\/t1.0-1\\/cp0\\/p57x57\\/70913986_10157490146824361_666912335636605952_n.jpg?_nc_cat=102&amp;_nc_sid=dbb9e7&amp;_nc_ohc=uNfpipWNrxMAX-JEf4w&amp;_nc_ht=scontent.ftxl2-1.fna&amp;oh=2b2aa1d3242d858278a6602d6e0024ae&amp;oe=5F8B341E\\\" alt=\\\"John Rambo\\\" data-jsid=\\\"img\\\" \\/>\\u003C\\/a>\\u003C\\/li>\\u003Cli class=\\\"_43q7\\\">\\u003Ca href=\\\"https:\\/\\/www.facebook.com\\/m.mustermann\\\" class=\\\"link\\\" data-jsid=\\\"anchor\\\" data-hover=\\\"tooltip\\\" data-tooltip-content=\\\"Max Mustermann (4\\/30)\\\">\\u003Cimg class=\\\"_s0 _ry img\\\" src=\\\"https:\\/\\/scontent.ftxl2-1.fna.fbcdn.net\\/v\\/t1.0-1\\/cp0\\/p57x57\\/89357876_10157100189713231_21679700063467601392_n.jpg?_nc_cat=108&amp;_nc_sid=dbb9e7&amp;_nc_ohc=8WUwJxX8adQAX_Fm8Hd&amp;_nc_ht=scontent.ftxl2-1.fna&amp;oh=50d624d4aae57c096cf38574eaf2a29c&amp;oe=5F8B1FEF\\\" alt=\\\"Sanad F. Jumean\\\" data-jsid=\\\"img\\\" \\/>\\u003Cdiv class=\\\"clearfix uiMorePager stat_elem _52jv\\\" id=\\\"birthdays_pager\\\">\\u003Cdiv>\\u003Ca rel=\\\"ajaxify\\\" href=\\\"\\/async\\/birthdays\\/?date=1619898150\\\" class=\\\"pam uiBoxLightblue uiMorePagerPrimary\\\">May\\u003Ci class=\\\"mhs mts arrow img sp_rotgem0VnQW sx_f3d44a\\\">\\u003C\\/i>\\u003C\\/a>\\u003Cspan class=\\\"uiMorePagerLoader pam uiBoxLightblue\\\">\\u003Cimg class=\\\"img\\\" src=\\\"https:\\/\\/static.xx.fbcdn.net\\/rsrc.php\\/v3\\/yb\\/r\\/GsNJNwuI-UM.gif\\\" alt=\\\"\\\" width=\\\"16\\\" height=\\\"11\\\" \\/>\\u003C\\/span>\\u003C\\/div>\\u003C\\/div>\"}]],\"jsmods\":{\"define\":[[\"AsyncRequestConfig\",[],{\"retryOnNetworkError\":\"1\",";
        Pattern pattern = Pattern.compile(BirthdayImporter.PATTERN);
        Matcher matcher = pattern.matcher(snippet);

        assertTrue(matcher.find());

        Optional<Birthday> birthday = BirthdayImporter.toBday(matcher);
        assertTrue(birthday.isPresent());
        assertEquals("John Rambo", birthday.get().getName());
    }
}