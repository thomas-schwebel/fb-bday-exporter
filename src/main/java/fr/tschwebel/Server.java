package fr.tschwebel;

import java.util.List;

import fr.tschwebel.domain.Birthday;
import fr.tschwebel.handler.HtmlHandler;
import fr.tschwebel.service.BirthdayImporter;
import fr.tschwebel.template.BirthdaysPage;
import fr.tschwebel.template.DownloadDataPage;
import fr.tschwebel.template.HomePage;
import io.undertow.Handlers;
import io.undertow.Undertow;

public class Server {

    public static void main(String[] args) {
        List<Birthday> birthdays = BirthdayImporter.readData();
        System.out.println("Loaded: " + birthdays.size() + " birthday(s)");
        System.out.println(birthdays);

        startServer(birthdays);
    }

    private static void startServer(List<Birthday> birthdays) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(
                        Handlers.path()
                                .addPrefixPath("/", new HtmlHandler(new HomePage()))
                                .addPrefixPath("/links", new HtmlHandler(new DownloadDataPage()))
                                .addPrefixPath("/birthdays", new HtmlHandler(new BirthdaysPage(birthdays)))
                )
                .build();
        server.start();
    }
}