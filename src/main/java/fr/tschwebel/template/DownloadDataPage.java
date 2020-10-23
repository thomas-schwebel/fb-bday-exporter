package fr.tschwebel.template;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.tschwebel.service.FbUrlBuilder;

public class DownloadDataPage implements HtmlTemplate {

    static List<String> toElementList(Map<String, String> monthToUrl) {
        return monthToUrl.entrySet().stream()
                .map(kv -> HtmlTag.href(kv.getKey(), kv.getValue()))
                .collect(Collectors.toUnmodifiableList());
    }

    public String generateHTML() {
        Map<String, String> monthToUrl = FbUrlBuilder.generateURLs();

        return """
               <html>
                   <body>
                       <p>Download each month and save it with .js within in your project /src/main/resources/birthdays/</p>
                       %s
                   </body>
               </html>
               """
               .formatted(HtmlTag.list(toElementList(monthToUrl)));
    }

}
