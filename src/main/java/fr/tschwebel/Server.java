package fr.tschwebel;

import fr.tschwebel.handler.HtmlHandler;
import fr.tschwebel.template.DataPage;
import io.undertow.Handlers;
import io.undertow.Undertow;

public class Server {
    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(
                    Handlers.path()
                        .addPrefixPath("/", new HtmlHandler(new DataPage()))
                ).build();
        server.start();
    }
}