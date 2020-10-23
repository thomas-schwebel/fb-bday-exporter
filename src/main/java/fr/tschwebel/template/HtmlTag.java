package fr.tschwebel.template;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlTag {

    static String href(String text, String url) {
        return """
               <a href="%s">%s</a>"""
               .formatted(url, text, text);
    }

    static String list(List<String> elements) {
        var htmlListElements = elements.stream()
                .map(e -> "<li>"+e+"</li>")
                .collect(Collectors.toUnmodifiableList());

        return """
               <ul>
                   %s
               </ul>"""
               .formatted(String.join("\n    ", htmlListElements));
    }
}
