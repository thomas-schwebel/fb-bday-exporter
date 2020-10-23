package fr.tschwebel.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class HtmlTagTest {

    @Test
    void href() {
        assertEquals("<a href=\"url\">text</a>", HtmlTag.href("text", "url"));
    }

    @Test
    void list() {
        String expected = """
               <ul>
                   <li><a href=\"url\">text</a></li>
                   <li><a href=\"url\">text</a></li>
               </ul>""";

        String href = HtmlTag.href("text", "url");
        List<String> l = List.of(href, href);

        assertEquals(expected, HtmlTag.list(l));
    }
}