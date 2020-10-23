package fr.tschwebel.template;

import java.util.List;

public class HomePage implements HtmlTemplate {

    public String generateHTML() {

        List<String> pages = List.of(
                HtmlTag.href("Download Links", "/links"),
                HtmlTag.href("Export Birthdays", "/birthdays")
            );

        return """
               <html>
                   <body>
                       <p>Steps:</p>
                       %s
                   </body>
               </html>
               """
               .formatted(HtmlTag.list(pages));
    }

}
