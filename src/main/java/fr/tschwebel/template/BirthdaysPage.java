package fr.tschwebel.template;

import java.util.List;
import java.util.stream.Collectors;

import fr.tschwebel.domain.Birthday;

public class BirthdaysPage implements HtmlTemplate {

    private final List<Birthday> birthdays;

    public BirthdaysPage(List<Birthday> birthdays) {
        this.birthdays = birthdays;
    }

    private String toBdayElement(Birthday bday) {
        return """
               "%s" (%s / %s) %s"""
               .formatted(bday.getName(), bday.getDay(), bday.getMonth(), HtmlTag.href("Add to Google", bday.generateOutlookUrl()));
    }

    public String generateHTML() {
        List<String> bdays = birthdays.stream()
                .map(this::toBdayElement)
                .collect(Collectors.toList());

        return """
               <html>
                   <body>
                       <p>Birthdays:</p>
                       %s
                   </body>
               </html>
               """
               .formatted(HtmlTag.list(bdays));
    }

}
